package com.viptrip.wetrip.service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.wetrip.dao.AuditOrderDao;
import com.viptrip.wetrip.dao.TAcRoleDao;
import com.viptrip.wetrip.dao.TAcUserDao;
import com.viptrip.wetrip.dao.TOrderDao;
import com.viptrip.wetrip.entity.MailAndNoteMsg;
import com.viptrip.wetrip.entity.MessageModel;
import com.viptrip.wetrip.entity.TAcDictdeta;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcRole;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TAcUserrole;
import com.viptrip.wetrip.entity.TComment;
import com.viptrip.wetrip.entity.TCommonMsgBox;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.model.Request_AuditOrder;


/**
 * @author hanzhicheng
 *
 */
@Service
@Transactional
public class AuditOrderService {
	@Resource
	private TOrderDao tod;
	public TOrderDao getTod() {
		return tod;
	}

	public void setTod(TOrderDao tod) {
		this.tod = tod;
	}
	@Resource
	private	AuditOrderDao auditOrderDao;

	
	public AuditOrderDao getAuditOrderDao() {
		return auditOrderDao;
	}

	public void setAuditOrderDao(AuditOrderDao auditOrderDao) {
		this.auditOrderDao = auditOrderDao;
	}

	@Resource
	public AutoTicketService autoTicketService;
	/*
	 * 
	 * 订单审核
	 */
	public Integer AuditOrder(Request_AuditOrder req){
			
		String type = (String) req.data.get("type");
		String orderid=""+ req.data.getLong("orderId");//O_ID
		int status=1;
		if("1".equals(type)){
			status=passOrder(req);//审核通过
			//自动出票的相关逻辑
			if(StringUtils.isNotEmpty(orderid)){
			    TOrder order=tod.findOne(Long.valueOf(orderid));
			  //自动出票开关 
                String autoMaticFlag=autoTicketService.getAutomaticTicket();
                if(StringUtils.isNotEmpty(autoMaticFlag) && autoMaticFlag.equals("open")){
                    //自动出票-无审核（月结或预付款）
                    autoTicketService.addFromPay(order.getOrderno(), "2");//审核（月结或预付）-自动出票
                }
			}
		}else if("2".equals(type)){
			
			status=unpassOrder(req);//审核拒绝
			
		}else{
			
			
			status= authority(req);
		}
		
		return status;
					
	}
	
	/*
	 * 订单审核通过
	 */
	public Integer passOrder(Request_AuditOrder req){
		long orderid =req.data.getLong("orderId");
		long userId = req.userId;
		TOrder theorder = getOneOrder(orderid);
		if(!theorder.getOrderStatus().equals("1")){
			return 1;
		}
		Set<TPnr> list = theorder.getTPnrs();
		theorder.setCheckresult("Y");
		String checkmen = theorder.getCheckmen();//预计审核人
		
		// 根据userId查找用户
		TAcUser loginuser = getUserByLoginname(userId);
		String username = loginuser.getUsername();
		
		if (username.equals(checkmen)) {
			// 如果登录用户与预计审核人相同，则保存实际审核人时不需增加代审二字，否则要加上
			// 审核人相关字段
			theorder.setCheckordermen(username);
			theorder.setCheckdate(new Date());
			theorder.setCheckemail(loginuser.getEmail());
			theorder.setChecktel(loginuser.getPhone());
			theorder.setCheckordermenId(loginuser.getUserid());// 际审核人的id号
		} else {
		
			theorder.setProxyCheckordermen(username);
			// 代审核人相关字段
			theorder.setProxyCheckdate(new Date());
			theorder.setProxyCheckemail(loginuser.getEmail());
			theorder.setProxyChecktel(loginuser.getPhone());
			theorder.setCheckordermenId(loginuser.getUserid());// 待审核人的id号
		}

		theorder.setOrderStatus("3"); // 审核通过之后，变为已审核状态

		setSystemcomment(theorder.getOId(),username + "审核通过");
		
		
		//由于暂时找不到审核出现null问题,所以准备发送邮件到技术部邮箱,以确认什么地方出现这个问题
		/*String title=getSessionAttribute("_username").toString() + "审核通过;  来源:OrderMgrAction.passCheckOrder()方法";
		SendEmailUtil sendEmailUtil = new SendEmailUtil();
        sendEmailUtil.autoSendEmail1("", "1", "2", "jishubu.list@caissa.com.cn", null, "国内机票审核通过:",title);*/
		
		// 订单待审核通过发送给所有后台客服站内消息
		//To_Examine(theorder.getOId());

		TTravelItinerary passenger = null;
		Set newPassengers = new HashSet();
		Set newPnrs = new HashSet();
		Iterator itPassenger = null;
		for (TPnr pnr : list) {
			
			itPassenger = pnr.getTTravelItineraries().iterator();
			while (itPassenger.hasNext()) {
				passenger = (TTravelItinerary) itPassenger.next();
				passenger.setFlightStatus("11");
				newPassengers.add(passenger);
			}
			pnr.setTTravelItineraries(newPassengers);
			newPnrs.add(pnr);
		}
		theorder.setTPnrs(newPnrs);
		updateOrderTicket(theorder);
		return 0;
	}
	
	
	@Autowired
	private TAcRoleDao trd;
	
	@Autowired
	private CancelOrderService cos;
	/*
	 * 
	 * 订单审核拒绝
	 */
	public Integer unpassOrder(Request_AuditOrder req){

		try {
			
			long orderid =req.data.getLong("orderId");
			long userId =  req.userId;
			String memo =  req.data.getString("memo").toString();
			TOrder theorder = getOneOrder(orderid);
			if(!theorder.getOrderStatus().equals("1")){
				return 1;
			}
			theorder.setCheckresult("N");
			theorder.setCheckcomment(memo);
			
					
			String checkmen = theorder.getCheckmen();//预计审核人
			
			// 根据userId查找用户
			TAcUser loginuser = getUserByLoginname(userId);
			String username = loginuser.getUsername();
		
			
			if (username.equals(checkmen)) {
				// 如果登录用户与预计审核人相同，则保存实际审核人时不需增加代审二字，否则要加上
				// 审核人相关字段
				theorder.setCheckordermen(username);
				theorder.setCheckdate(new Date());
				theorder.setCheckdate(new Date());
				theorder.setCheckemail(loginuser.getEmail());
				theorder.setChecktel(loginuser.getPhone());
				theorder.setCheckordermenId(loginuser.getUserid());// 实际审核人的id号
			} else {
				// 代审核人相关字段
				theorder.setProxyCheckordermen(username + "(代审)");
				theorder.setProxyCheckordermen(username);
				theorder.setProxyCheckdate(new Date());
				theorder.setProxyCheckdate(new Date());
				theorder.setProxyCheckemail(loginuser.getEmail());
				theorder.setProxyChecktel(loginuser.getPhone());
				theorder.setCheckordermenId(loginuser.getUserid());// 待审核人的id号
			}
			
			
			
			
			
			
					
				theorder.setOrderStatus("5"); // 审核不通过之后，变为已取消状态，前台显示时需注意，对于已取消且审核不通过的订单，显示审核不通过原因

				updateOrderTicket(theorder);
	
				// 预付款 退款
				if (theorder.getPayMethod().equals("4")&&(theorder.getOrderStatus().equals("1"))) {
					String hql="from TAcOrg  where orgname ='"+theorder.getCompanyName()+"'";
					TAcOrg org=null;
					List<TAcOrg > listorg = auditOrderDao.queryForList(hql);
					if(listorg.size()>0){
						 org = listorg.get(0);
					}
					
					cos.balancePay("-2",theorder.getTotalPrice(),
							theorder.getOrderno(), theorder.getOpReason(),
							org.getOrgid(), org.getOrgname());
				}
			//msgBoxManager.SHBTG_to_YDR(orderno);// 订单审核，审核不通过给预发送站内消息及短信
				
			
			String str=username + "审核不通过,原因"+memo;
			setSystemcomment(theorder.getOId(),str);
			
			//由于暂时找不到审核出现null问题,所以准备发送邮件到技术部邮箱,以确认什么地方出现这个问题
			/*String title=getSessionAttribute("_username").toString() + "审核不通过;  来源:OrderMgrAction.notPassCheckOrder()方法";
			SendEmailUtil sendEmailUtil = new SendEmailUtil();
	        sendEmailUtil.autoSendEmail1("", "1", "2", "jishubu.list@caissa.com.cn", null, "国内机票拒绝审核:",title);*/
			
		}catch (Exception e) {
			
		}
		
		return 0;
	}
	
	

	//根据订单号获取实体
	public TOrder getOneOrder(long orderId){
		
		
		TOrder tOrder = tod.findOne(orderId);
		
		return tOrder;
	}
	@Autowired
	private TAcUserDao itd;
	
	public TAcUser getUserByLoginname(long  userId){
		
		TAcUser tAcUser = itd.findOne(userId);
		
		return tAcUser;
	}
	
	
	 /**
     * 系统添加订单备注
     * 
     * @param dao
     * @param name
     * @param str
     * @return
     */
	@Transactional(propagation=Propagation.REQUIRED)
    public boolean setSystemcomment(Long orderId, String str) {

    SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss");
	String date= sdf.format(new Date());
    	
    TComment tcom = new TComment();
    tcom.setPassengerName("系统");
    tcom.setCommentMsg(str);
    tcom.setCommentDate(date);
    tcom.setOId(orderId);
    
    auditOrderDao.executeSave(tcom);
    return true;

    }
    
    //审核通过给后台客服发送站内消息
  	@SuppressWarnings("unchecked")
  	public void To_Examine(Long oid){
  		//收件人
  		//查找所有的后台客服
  		try{
  			
  			TOrder order = tod.findOne(oid);
  		  			
  			String regex = ".*[A-M].*"; // 区分北京上海订单
  			Pattern pattern = Pattern.compile(regex);
  			Matcher matcher = pattern.matcher(order.getOrderno());

  			long [] role =new long[5];

  			if (matcher.matches()) {
  				// System.out.println("这是北京订单");
  				role[0]=(121);
  			} else {
  				// System.out.println("这是上海订单");
  				role[0]=(2443);
  			}

  			/*String sql = "select u.userid,u.username,u.phone,u.email,u.ORGID from t_Ac_User u,t_ac_userrole r "
  					+ " where   r.roleid in (61, 68, 71, 72, 92, 93, 95, 96)  and u.userid=r.userid"
  					+ " and u.ORGID=?";*/
  			String hql = "select u.userid,u.username,u.phone,u.email,u.orgid from TAcUser u,TAcUserrole r "
  					+ " where   r.roleid in (61, 68, 71, 72, 92, 93, 95, 96)  and u.userid=r.userid"
  					+ " and u.orgid="+role[0];
  			List users= findlistByHQL(hql);// 客服集合
  			
  			
  			
  			
  			// 测试系统消息（站内及短信）
  			MailAndNoteMsg mailandnotemsg = new MailAndNoteMsg();
  			
  			//发件人
  			mailandnotemsg.setSender(order.getSubscribeName());
  			
  			//信息内容拼接
  			MessageModel msgtext=new MessageModel();
  			
  			
  			//给所有客服站内消息  审核结果通知
  			
  			for(Object[] user:(List<Object[]>)users){
  				TCommonMsgBox m=new TCommonMsgBox();
  				
  				//发送给通知人 站内信息
  				// 邮件标题
  				m.setTitle("订单"+order.getOrderno()+"审核结果通知!");
  				//收件人
  				m.setReceiver(user[1]==null?"":user[1].toString());
  				m.setReceiverid(new Long(user[0].toString())); //用户id
  				m.setContact(user[2]==null?"":user[2].toString());//联系电话
  				//拼接字符串  各位工作人员请注意，订单20150211A001可出票，请及时处理！

  				
  	
  				//发件人
  				m.setSender("system");
  				
  				m.setMsgtime(new Date());
  				m.setStatus("0");
  				m.setUrl("/ticket/orderMgr/orderinfo.hlt?orderno="
  						+ order.getOrderno() + "&dpo=0&msg=msg");
  				m.setMsgtype("1");
  				m.setContent("订单"+order.getOrderno()+"可出票，请及时处理！");
  				
  				
  				//发送站内消息   第一个参数为1，只发“站内消息”
  				auditOrderDao.executeSave(m);//发送给通知人 站内信息
  				
  			}
  			

  		}catch (Exception e) {
  			// TODO: handle exception
  			e.printStackTrace();
  		}
  	}	
  	
  	public void updateOrderTicket(TOrder theorder){
  		auditOrderDao.executeSave(theorder);
  		
  	}
  	
  	public List findlistByHQL(String hql){
  		List<Object> list = auditOrderDao.queryForList(hql);
  		  
	  return list;
  }
  	
  	public Integer authority(Request_AuditOrder req){
  		String rolename="";
  		
  		String status1 = "2";
  		String status2="2";
		List<TAcUserrole> list = auditOrderDao.queryForList("from TAcUserrole t where t.userid= "+req.userId);
						
		Long roleid = list.get(0).getRoleid();
		
		long orderid =req.data.getLong("orderId");
		
		TOrder theorder = getOneOrder(orderid);
		String checkmen = theorder.getCheckmen();
		String orderStatus = theorder.getOrderStatus();
		
		TAcUser loginuser = getUserByLoginname(req.userId);
		String username = loginuser.getUsername();
		if(list.size()>0){
			String hql="from TAcRole where roleid="+roleid;
			List<TAcRole> tlist = auditOrderDao.queryForList(hql);
		
			if(tlist.size()>0){
				rolename=tlist.get(0).getRolename();
				
			}
			
			//审核订单按钮
			if((rolename.contains("客服")||username.equals(checkmen))&&("1".equals(orderStatus))){
				status1="1";
			}
			//取消订单按钮
			if((rolename.contains("客服")&&("1".equals(orderStatus)||("2".equals(orderStatus))||("3".equals(orderStatus))||("4".equals(orderStatus))))
					||(username.equals(theorder.getSubscribeName())&&("1".equals(orderStatus)||("2".equals(orderStatus))))){
				status2="1";
			}
			
			System.out.println("rolename="+rolename);
		}
		return Integer.valueOf(status1+status2);
  		
  		
  	}
  	
  /*	@SuppressWarnings("unchecked")
	public void SHBTG_to_YDR(String oid){

		//根据订单号查询信息
		List<TOrder> olist=null;
		List<TPnr> tpnrList=new ArrayList<TPnr>();
		List<TTravelItinerary> Tlist=new ArrayList<TTravelItinerary>();
		TPnr tPnr=new TPnr();
		try{
			//查询订单信息
			olist=auditOrderDao.queryForList("from TOrder where orderno="+ oid);
			//获得pnr表里的信息
			tpnrList = auditOrderDao.queryForList("from TPnr where TOrder.orderno="+oid);
			
		
			
			//行程单信息
			long pnrid = 0;
			for (int i = 0; i < tpnrList.size(); i++) {
				tPnr = tpnrList.get(i);
				if ("1".equals(tPnr.getFlighttype())) {
					pnrid = tPnr.getPnrId();
				}
			}
			Tlist = auditOrderDao.queryForList(
					"from TTravelItinerary where TPnr.pnrId = "+ pnrid);
		}catch (Exception e) {
			// TODO: handle exception
			//logger.info("获取订单信息出错，消息无法发送...");
			e.printStackTrace();
		}
		
		
		try{
			
			TOrder order=olist.get(0);
			
			//机构信息
			
			
			TAcOrg	tacorg = getOrgByOrgName(order.getCompanyName());
			
			
			//乘机人拼接
			StringBuffer cjrsb=new StringBuffer();
			
			for(int j=0;j<Tlist.size();j++){
				TTravelItinerary xcd=Tlist.get(j);
				//cjrsb.append(xcd.getPassengerName()+",");
				
				if(j+1!=Tlist.size()){
					cjrsb.append(xcd.getPassengerName()+",");
				}else{
					cjrsb.append(xcd.getPassengerName());
				}
			}
			
			
			String regex = ".*[A-M].*"; // 区分北京上海订单
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(order.getOrderno());

			List role = new ArrayList();

			if (matcher.matches()) {
				// System.out.println("这是北京订单");
				role.add("121");
			} else {
				// System.out.println("这是上海订单");
				role.add("2443");
			}

			String sql = "select u.userid,u.username,u.phone,u.email,u.ORGID from t_Ac_User u,t_ac_userrole r "
					+ " where   r.roleid in (61, 68, 71, 72, 92, 93, 95, 96)  and u.userid=r.userid"
					+ " and u.ORGID=?";

			List users= auditOrderDao.queryForList(sql);// 客服集合
			
			
			
			for(int i=0;i<tpnrList.size();i++){

				tPnr=tpnrList.get(i);

				//转换起飞时间
				SimpleDateFormat df11 = new SimpleDateFormat("HH:mm");
				//转换起飞日期
				SimpleDateFormat df12 = new SimpleDateFormat("yyyy年MM月dd日");
				String time11 = df11.format(tPnr.getOriginTime());
				String time111 = df12.format(tPnr.getOriginTime());
				
				//转换到达时间
				String time22=df11.format(tPnr.getDestinationTime());
				String time222 = df12.format(tPnr.getDestinationTime());
				//拼接内容
				
				//总价（应收，即客户给景鴻的钱）
				String totalPrice=getPnrTotalPrice( this.getTravelItineraryByPnrId(tPnr.getPnrId()));
				String cangwei="";
				
				
				
				
				//舱位等级（1-经济舱，2-公务舱，3-头等舱）
				String level =dao.findByHQL("select t.classtype from TAcClass t where  t.aircompany = ? and t.classcode = ? ",
							tPnr.getAirline().substring(0,2),tPnr.getCangwei()).get(0).toString();
				
				//System.out.println(level+"============================================"+tPnr.getAirline().substring(0,2)+tPnr.getCangwei());

				if("1".equals(level)){
					cangwei="经济舱";
				}else if("2".equals(level)){
					cangwei="公务舱";
						
				}else if("3".equals(level)){
					cangwei="头等舱";
				}
			
				TAcDictdeta	origincitys = (TAcDictdeta) dao.findUnique("select t from TAcDictdeta t where t.dicttype=1 and t.dictcode=? order by t.sortno",tPnr.getOriginCity());
				String origincity=origincitys.getDictname();
				TAcDictdeta	destinationcitys = (TAcDictdeta) dao.findUnique("select t from TAcDictdeta t where t.dicttype=1 and t.dictcode=? order by t.sortno",tPnr.getDestinationCity());
				String destinationcity=destinationcitys.getDictname();
				
				String updateStatusTime=PropertyResourceBundle.getBundle("com.viptrip.common.properties.quartz").
					getString("unpaidOrders.updateStatusTime");
				
				  

				
				//判断是否起飞到达是否同天
				String toWho="";
				if(time111.equals(time222)){
					toWho="SHBTGDX_to_YDR";
				}else{
					toWho="SHBTGDX_to_YDR2";
				}
				
				//发送消息
				MailAndNoteMsg mailandnotemsg=new MailAndNoteMsg();
				MessageModel msgtext=new MessageModel();
				
				//发件人
				if(StringUtil.isNotEmpty(order.getCheckmen())){
					mailandnotemsg.setSender(order.getCheckmen());
				}else{
					mailandnotemsg.setSender("");
				}
				
				//2.发送站内消息给预订人  站内消息
				mailandnotemsg.setTitle("订单"+order.getOrderno()+"审核未通过!");
				//收件人
				
				
//				if(order.getProxySubscribeName()==null||order.getSubscribeName().equals("")){
//					mailandnotemsg.setRecevier(order.getSubscribeName());
//					mailandnotemsg.setContact(order.getSubscribeTel());//联系电话
//				}else{
//					mailandnotemsg.setRecevier(order.getProxySubscribeName());
//					mailandnotemsg.setContact(order.getSubscribeTel());//联系电话
//					 
//				}
					//企业月结，且企业需审核
				if("1".equals(order.getPayMethod())&&"1".equals(tacorg.getVerify())){
					
					mailandnotemsg.setRecevier(order.getSubscribeName());
					mailandnotemsg.setRecevierid(order.getSubscribeId()); //用户id
					mailandnotemsg.setContact(order.getSubscribeTel());//联系电话
					
					
					msgtext=msgtext.SHBTG_to_YDR(order.getSubscribeName(), order.getCheckmen(),
							origincity, 
							destinationcity, 
							tPnr.getAirline(), 
							time11,
							time111,
							time22,
							time222,
							cjrsb.toString(),
							cangwei,
							totalPrice,
							order.getOrderno());  //这个方法只是设置了MessageModel的对象msgtext
					msgtext.setWebsiteurl("/ticket/orderMgr/orderinfo.hlt?orderno="
							+ order.getOrderno() + "&dpo=1&msg=msg");
					mailandnotemsg.setText(msgtext);
					
					
					
					
					TCommonMsgBox tCommonMsgBox7 = SendMessageUtil.send(
							1, "SHBTG_to_YDR", mailandnotemsg, dao);
					//发送站内消息   第一个参数为1，只发“站内消息”
					dao.save(tCommonMsgBox7);//发送站内消息给预订人  站内消息
					
					
					
					if(bundle.getString("TURNON").equals("TRUE")&&"1".equals(tacorg.getAutoSendMsg())){//判断一下配置文件重的发送开关配置是否为true
						
						if(StringUtil.isNotEmpty(order.getSubscribeTel())){
							//发送短信给预订人   发送短信
							msgtext=msgtext.SHBTG_to_YDR(order.getSubscribeName(), order.getCheckmen(),
									origincity, 
									destinationcity, 
									tPnr.getAirline(), 
									time11,
									time111,
									time22,
									time222,
									cjrsb.toString(),
									cangwei,
									totalPrice,
									order.getOrderno());  //这个方法只是设置了MessageModel的对象msgtext
							msgtext.setWebsiteurl("/ticket/orderMgr/orderinfo.hlt?orderno="
									+ order.getOrderno() + "&dpo=1&msg=msg");
							mailandnotemsg.setText(msgtext);
							mailandnotemsg.setContact(order.getSubscribeTel());
							
							
							 TCommonMsgBox tCommonMsgBox8=SendMessageUtil.send(3,toWho, mailandnotemsg,dao);
							dao.save(tCommonMsgBox8);
							
							
						}
						
						
		
					}
					//给所有客服站内消息  审核结果通知
					

						
						
						for(Object[] user:(List<Object[]>)users){
							
							//发送给通知人 站内信息
							// 邮件标题
							mailandnotemsg.setTitle("订单"+order.getOrderno()+"审核结果通知!");
							//收件人
							mailandnotemsg.setRecevier(user[1]==null?"":user[1].toString());
							mailandnotemsg.setRecevierid(user[0].toString()); //用户id
							mailandnotemsg.setContact(user[2]==null?"":user[2].toString());//联系电话
							//拼接字符串
							msgtext=msgtext.SHBTG_to_YDR(order.getSubscribeName(), order.getCheckmen(),
									origincity, 
									destinationcity, 
									tPnr.getAirline(), 
									time11,
									time111,
									time22,
									time222,
									cjrsb.toString(),
									cangwei,
									totalPrice,
									order.getOrderno());   //这个方法只是设置了MessageModel的对象msgtext

							
							//保存内容
							msgtext.setWebsiteurl("/ticket/orderMgr/orderinfo.hlt?orderno="
									+ order.getOrderno() + "&dpo=0&msg=msg");
							mailandnotemsg.setText(msgtext);
							TCommonMsgBox tCommonMsgBox6 = SendMessageUtil.send(
									1, "SHCFDXTZ", mailandnotemsg, dao);
							//发送站内消息   第一个参数为1，只发“站内消息”
							dao.save(tCommonMsgBox6);//发送给通知人 站内信息
							
						}
						
						
					
					
					
					
				}
				
				
				
			
				
			
			}
			dao.flush();
		}catch (Exception e) {
			// TODO: handle exception
			logger.info("消息保存失败！");
			e.printStackTrace();
		}*/
		
}
