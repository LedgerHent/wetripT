package com.viptrip.wetrip.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.common.model.RefundPriceModel;
import com.viptrip.intlAirticket.entity.TIntlTicketsRescheduled;
import com.viptrip.util.StringUtil;
import com.viptrip.warning.resource.MEnum.Otype;
import com.viptrip.warning.service.WarningManager;
import com.viptrip.warning.vo.MSM;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcOrgBalance;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TCommonMsgBox;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TUpdateDate;
import com.viptrip.wetrip.util.PropertiesUtil;
import com.viptrip.wetrip.util.Sendemail;
import com.viptrip.wetrip.vo.CaiyunResp;
@Service
@Transactional
public class BalancePay {
	@Resource
	private ComDao comDao;
	
	@Resource(type=ICaiyunPayService.class)
	private ICaiyunPayService cps;
	@Resource
	private WarningManager wm;
	
	public Double getOrgBalance(Long orgId){
		Double balance =null;
		StringBuffer hql = new StringBuffer();
		List<Long> list = new ArrayList<Long>();
		hql.append("SELECT nvl(SUM(money),0) FROM TAcOrgBalance T WHERE 1=1");
		if(orgId!=null){
			hql.append(" AND T.orgid = "+orgId+" ");
		}
		balance = (Double) comDao.queryForDouble(hql.toString());
		balance = (balance==null?0:balance);
		return balance;
	}
	/**
	 * 预付款支付、退票 、改签
	 * @param flowStatus 流动情况：11火车票付款 12火车票退款 -3.机加酒退款-2.机票退款-1.酒店退款0.财务充值1.酒店付款 2.机票付款.3机加酒付款.4酒店变更5.机票改签6.机加酒改签（在Constants中有对应调用）
	 * @param money 消费金额（付款，金额为负数，退票为正数）
	 * @param orderNo 订单号
	 * @param note 备注 
	 * @param orgId 企业id
	 * @param orgName 企业名称
	 * @return 0：操作成功；1：余额不足；若机构不存在（在预付款表中不存在，可看做余额不足）；-1：其他错误（插入异常）
	 */
	public Map<String,String> balancePay(String flowStatus,Double money,String orderNo,String note,Long orgId,String orgName,String payMethod,String...value){
		Double balance =null;
		Map<String,String> map = new HashMap<String,String>();
		
		MSM msm = wm.changeLimitation(orgId, money, money>=0?Otype.退款调额:Otype.消费调额, null);
		
		if(msm.isState() && "4".equals(payMethod)){
			
			boolean b = true;
			//证明为新的订单添加,则首先查询当前余额
			//StringBuffer hql = new StringBuffer();
			List<Long> list = new ArrayList<Long>();
			/*hql.append("SELECT nvl(SUM(money),0) FROM TAcOrgBalance T WHERE 1=1");
		if(orgId!=null){
			hql.append(" AND T.orgid = "+orgId+" ");
		}
		balance = (Double) comDao.queryForDouble(hql.toString());
		balance = (balance==null?0:balance);*/
			if(orgId!=null){
				balance=getOrgBalance(orgId);
			}
			
			/*List<TOrder> lists =comDao.queryForList("from TOrder where orderno = '"+orderNo+"'");
		if(lists!=null&&lists.size()>0){
			TOrder order = lists.get(0);
			if(order!=null&&!"".equals(order.getSubscribeId())){
				if((money<0&&balance>money*-1)||money >=0){//余额大于当前支付金额  或者 为负数余额（退票或改签）
					CaiyunResp cr =null;
					AjaxResp re = cps.isUserBelongsToUban360(Long.parseLong(order.getSubscribeId()));
					if(re.getStatus()==0){
						if(Integer.parseInt(flowStatus)>0){
							 cr= cps.prePay(Long.parseLong(order.getSubscribeId()),order.getOId().toString(),money*-1,null);
						}else{
							 cr= cps.payRefund(Long.parseLong(order.getSubscribeId()),order.getOId().toString(),money,null);
						}
						if(cr.getSuccess()==true){
							setMap(map, cr.getStatus().toString(),cr.getMessage());
							b=true;
						}else{
							setMap(map, cr.getStatus().toString(),cr.getMessage());
							b=false;
						}
						
					}
				}else{
					setMap(map,"1", "余额不足！");
					b=false;
				}
				
			}
		}*/
			if(b){
				//增加了money==0的情况：
				if((money<0&&balance>=money*-1)||money >=0){//余额大于当前支付金额  或者 为负数余额（退票或改签）
					TAcOrgBalance entity = new TAcOrgBalance();
					Date operDate = comDao.str2Date(comDao.getDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
					entity.setOperatingDate(operDate);
					entity.setFlowStatus(flowStatus);
					entity.setOrgid(orgId);
					entity.setOrgname(orgName);
					entity.setOrderNo(orderNo);
					entity.setMoney(money);
					if(value!=null&&value.length>0){
						if("weixin".equals(value[0])){
							entity.setUserId(Long.valueOf(value[1]));
							entity.setUserName(value[2]);
						}
					}
					
					entity.setNote(note);
					comDao.executeSave(entity);
					
					
					//每次支付完成之后，都执行余额不足提醒方法
					balanceReminder(entity.getOrgid());
					//发送站内消息
					String title = Config.BALANCEMAP.get(entity.getFlowStatus())+"消息提醒";
					String content = "您好，订单号为"+entity.getOrderNo()+"，的交易消费金额"
							+entity.getMoney()+"元，交易类型为"
							+Config.BALANCEMAP.get(entity.getFlowStatus())
							+"。企业预付款剩余金额为"+balance+"元。";
					sendMessage(entity.getOrgid(),title,content);
					//发送邮件或短信（根据相关人的设置而定）
					balanceChangeRemind(entity);
					setMap(map,"0", "成功");
					
				}else{
					setMap(map,"1", "余额不足！");
				}
			}
		}else if(msm.isState()){
			setMap(map,"0", "成功");
		}else{
			setMap(map,"1", ("1".equals(payMethod)?"额度":"余额") + "不足！");
		}
		return map;
	}
	public void setMap(Map<String, String> map,String status,String message) {
		map.put("status", status);
		map.put("message",message);
	}
	/**
	 * 预付款变动提醒（发送邮件或短信）
	 * @param entity
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void balanceChangeRemind(TAcOrgBalance entity) {
		//获取邮件/短信接收人信息
		String sql="select u.EMAIL,u.phone,u.BALANCE_REMIND from t_Ac_User u,(select * from t_ac_userrole where  roleid=? or roleid=?) r where  u.orgid=? and u.userid=r.userid";
		String[] role=new String[3];  
		role[0]="65";
		role[1]="55";
		role[2]=entity.getOrgid().toString();
		String to = "";
		List<String> phoneList = new ArrayList<String>();
		List<Object[]> sjr=(List<Object[]>) comDao.queryBySQL(sql,role);
		for (int i = 0; i < sjr.size(); i++) {
			Object[] obj = sjr.get(i);
			if((obj[2]==null?"":obj[2]).toString().equals("0")){
				phoneList.add((obj[1]==null?"":obj[1]).toString());
			}else{
				to +="," +(obj[0]==null?"":obj[0]).toString();
			}
		}
		//获取邮件模板
		String scontent="";
		String templateSql="select T.TEMPLATEDESC1,T.TEMPLATEDESC2 from T_EMAIL_BROWSE T WHERE T.PUBLIC_CUSTOMIZE_TYPE = 2 and T.AIR_HOTEL_TYPE = 3 AND T.COMPANY = ? AND T.ISUSE = 1";
		String[] company=new String[1];  
		company[0]=entity.getOrgname();
		List<Object[]> templateReturn=(List<Object[]>) comDao.queryBySQL(templateSql,company);
		if(templateReturn!=null&&templateReturn.size()>0){//如果该企业有定制模板
			Object[] objects = templateReturn.get(0);
			scontent = comDao.nullTo(objects[0], "") + comDao.nullTo(objects[1], "");
		}else{//如果该企业没有定制模板，选择公用模板
			String templateSql2="select T.TEMPLATEDESC1,T.TEMPLATEDESC2 from T_EMAIL_BROWSE T WHERE T.PUBLIC_CUSTOMIZE_TYPE = 1 and T.AIR_HOTEL_TYPE = 3";
			List<Object[]> templateReturn2=(List<Object[]>) comDao.queryBySQL(templateSql2,null);
			Object[] objects = templateReturn2.get(0);
			scontent = comDao.nullTo(objects[0], "") + comDao.nullTo(objects[1], "");
		}
		
		List<Object> balanceList = new ArrayList<Object>();
		String balanceSql ="SELECT nvl(SUM(money),0) FROM TAcOrgBalance T WHERE 1=1 AND T.orgid = ?";
		balanceList.add(entity.getOrgid());
		Double balance = (Double) comDao.queryForDouble(balanceSql,balanceList.toArray());
		balance = (balance==null?0:balance);
		scontent = scontent.replace("${balance}", balance+"")
				.replace("{seiviceTel}", "北京："+Config.beijingTel+"上海：+"+Config.shanghaiTel+"")
				.replace("${orderNo}", "/")
				.replace("${money}", entity.getMoney()+"")
				.replace("${operatingDate}",comDao.date2Str(entity.getOperatingDate(), "yyyy-MM-dd HH:mm:ss"))
				.replace("${flowStatus}", Config.BALANCEMAP.get(entity.getFlowStatus()));
		if(to.length()>0)
			sendEmail(to, null, "预付款变动提醒", scontent);
		//获取短信模板
		String msg="您好，截止短信最后一笔交易，您所在公司"+entity.getOrgname()+"预付款余额为："+balance+"元。该笔交易类型为"
		+Config.BALANCEMAP.get(entity.getFlowStatus())+"，";
		if(!entity.getFlowStatus().equals("0")&&!entity.getFlowStatus().equals("7")){
			msg +="订单号为"+entity.getOrderNo();
		}
		
		msg += "，金额变动"+entity.getMoney()+"元。如有问题，请及时拨打24小时服务热线！北京："+Config.beijingTel+"上海：+"+Config.shanghaiTel+"。";
		

	    if("TRUE".equals(Config.TURNON)&&phoneList.size()>0){//短信开关为打开状态
			Date date=new Date();
			String phone="";
			for (String object : phoneList) {
			    if(!"".equals(phone)){
			        phone=phone+","+object;
			    }else{
			        phone=object;
			        
			    }
			    
            }
			
			
	    }
	}
	/**
	 * 邮件发送
	 * @return
	 */
	@SuppressWarnings("unused")
	public void sendEmail(String to,String cc,String subject,String body){
		String host = PropertiesUtil.getResourcesProperty("email_host"); // smtp服务器
		String user = PropertiesUtil.getResourcesProperty("email_user"); // 用户名
		String pwd = PropertiesUtil.getResourcesProperty("email_pwd"); // 密码
		String from = PropertiesUtil.getResourcesProperty("email_from"); // 发件人地址
		String isBalancePay = PropertiesUtil.getResourcesProperty("ISBALANCEPAY");
		/*if("FALSE".equals(isBalancePay)){
			return;
		}*/
		try {
			body = URLDecoder.decode(body,"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		try {
			Sendemail.sendHTML(host, from, user, pwd, to, cc, null, subject, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	   /**
		 * 余额不足提醒功能
		 * @param orgId 企业id
		 */
		public void balanceReminder(Long orgId){
			StringBuffer hql1 = new StringBuffer();
			List<Long> list1 = new ArrayList<Long>();
			hql1.append("SELECT nvl(SUM(money),0) FROM TAcOrgBalance T WHERE 1=1");
			if(orgId!=null){
				hql1.append(" AND T.orgid = "+orgId+" ");
			}
			Double balance = (Double) comDao.queryForDouble(hql1.toString());
			balance = (balance==null?0:balance);
			StringBuffer hql2 = new StringBuffer();
			List<Long> list2 = new ArrayList<Long>();
			hql2.append("SELECT nvl(minBalance,0) FROM TAcOrg T WHERE 1=1");
			if(orgId!=null){
				hql2.append(" AND T.orgid = "+orgId+" ");
			}
			Double minBalance = (Double) comDao.queryForDouble(hql2.toString());
			minBalance = (minBalance==null?0:minBalance);
			if(balance<minBalance){//当前余额小于企业设定的最小余额设定
				//向企业管理员发送充值成功消息
				String title = "余额不足提醒";
				String content = "您好，你企业当前余额为"+balance+"元，请及时充值！";
				sendMessage(orgId,title,content);
			}
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		/**
		 * 站内消息发送
		 * @param dateTime 
		 * @param oid
		 */
		
		public void sendMessage(Long orgId,String title, String Content){
			//收件人
			//查找所有的后台客服
				String sql="select u.username,u.phone from t_Ac_User u,(select * from t_ac_userrole where  roleid=? or roleid=?) r where  u.orgid=? and u.userid=r.userid";
				String[] role=new String[3];  
				role[0]="65";
				role[1]="55";
				role[2]=orgId.toString();
				List<Object[]> sjr=(List<Object[]>) comDao.queryBySQL(sql,role);
				
				TCommonMsgBox m=new TCommonMsgBox();
				for(int i=0;i<sjr.size();i++){
					//发送消息
					//MailAndNoteMsg m=new MailAndNoteMsg();
					// 邮件标题
						Object s0 = sjr.get(i)[0];
						Object s1 = sjr.get(i)[1];
						if(s0!=null&&s1!=null){
						m.setTitle(title);	
						m.setReceiver(s0.toString());
						//发件人
						m.setSender("system");
						m.setContact(s1.toString());
						m.setMsgtime(new Date());
						m.setStatus("0");
						m.setMsgtype("1");
						m.setContent(Content);
						comDao.executeSave(m);
					}
				}
		}
		
		 @SuppressWarnings("unchecked")
			public Double checkBalance(Long companyId,String paymethod){
		    	Double balance=0d;
		    	
		    	if(companyId!=null){
		    		String hql="";
		    		if("1".equals(paymethod)){
		    			hql="select t.leftLimitation from PayLimitation t where t.id=(select max(id) from PayLimitation where orgid="+companyId+")";
		        	}else if("4".equals(paymethod)){
		        		hql="SELECT SUM(nvl(t.money,0)) FROM TAcOrgBalance t where t.orgid="+companyId+" GROUP BY t.orgid";
		        	}
		    		if(StringUtil.isNotEmpty(hql)){
		    			balance = (Double) comDao.queryForDouble(hql.toString());
			    		balance = (balance==null?0:balance);
		    		}
				}
		    	return balance;
		    }
		    /**
		     * 预付款或者月结扣款失败,删除或者退还已经扣除的金额
		     */
		    @SuppressWarnings("unchecked")
			public String refundTicketPrice(RefundPriceModel refundPriceModel){
		    	String status="0";
		    	TAcOrg org=comDao.queryForEntity(refundPriceModel.companyId,TAcOrg.class);
		    	if(org==null){
		    		return "企业id不正确！";
		    	}
		    	Double money=0d;
		    	if("1".equals(refundPriceModel.paymethod) || "4".equals(refundPriceModel.paymethod)){
		    		if(StringUtil.isNotEmpty(refundPriceModel.orderNo)){
		    			if("1".equals(refundPriceModel.type)){//出票
		    				String hql="from TAcOrgBalance where orderNo='"+refundPriceModel.orderNo+"' order by id";
		        	    	List<TAcOrgBalance> balances=comDao.queryForList(hql);
		        	    	if(balances!=null && balances.size()>0){
		        	    		for (TAcOrgBalance b : balances) {
		        	    			money+=Double.valueOf(b.getMoney());
		    					}
		        	    	}
		        	    	money=money*-1;
		    	    	}else if("2".equals(refundPriceModel.type)){//改期
		    	    		if("国内机票".equals(refundPriceModel.bussiness)){
		    	        		if(refundPriceModel.updateTickets!=null && refundPriceModel.updateTickets.size()>0){
		    	        			for (TUpdateDate u : refundPriceModel.updateTickets) {
		    	        				money+=((u.getClassFee() == null ? 0d : u.getClassFee())+ (u.getChangeTheFreight() == null ? 0d: u.getChangeTheFreight())
		    	    							+ (u.getNightFee() == null ? 0d : u.getNightFee())+ (u.getChangeServiceFee() == null ? 0d: u.getChangeServiceFee()));
									}
		    	        		}
		    	        	}else if("国际机票".equals(refundPriceModel.bussiness)){
		    	        		if(refundPriceModel.intlUpdateTickets!=null && refundPriceModel.intlUpdateTickets.size()>0){
		    	        			for (TIntlTicketsRescheduled u : refundPriceModel.intlUpdateTickets) {
		    	        				money+=((u.getIntlRescheduledReceive() == null ? 0d : u.getIntlRescheduledReceive())+(u.getIntlUpgradesReceive() == null ? 0d : u.getIntlUpgradesReceive())
		    	        						   +(u.getChangeServiceFee() == null ? 0d : u.getChangeServiceFee()) +(u.getNightFee() == null ? 0d : u.getNightFee()));
									}
		    	        		}
		    	        	}else{
		    	        		status="该物业暂时不支持退款,请联系客服进行退款";
		    	        	}
		    	    		
		        		}else{
		        			status="请输入正确的type";
		        		}
		    			if("0".equals(status)){
			    			if(money>0){
			    				Map<String,String> map= balancePay(Config.JPTK, money, refundPriceModel.orderNo, "",org.getOrgid(),org.getOrgname(),refundPriceModel.paymethod,"");
			    				if(!"0".equals(map.get("status"))){
			    					status="该订单由于"+("1".equals(refundPriceModel.paymethod)?"月结额度":"预付款余额")+"不足已取消,退款失败,请联系客服进行退款！";
			 	                }
		    	    		}
			    		}
		    		}
		    		
		    	}
		    	
		    	return status;
		    }
}
