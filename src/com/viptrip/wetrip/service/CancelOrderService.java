package com.viptrip.wetrip.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.wetrip.dao.AuditOrderDao;
import com.viptrip.wetrip.dao.TAcOrgDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcOrgBalance;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.model.Request_CancelOrder;
import com.viptrip.wetrip.service.AuditOrderService;
import com.viptrip.wetrip.service.impl.CaiyunPayService;
import com.viptrip.wetrip.vo.CaiyunResp;

/**
 * @author hanzhicheng
 *
 */

@Service
@Transactional
public class CancelOrderService {
	@Autowired
	private	AuditOrderDao auditOrderDao;
	@Autowired
	private AuditOrderService aos;
	@Autowired
	private TAcOrgDao tod;
	@Resource(type=ICaiyunPayService.class)
	private ICaiyunPayService cps;
	long userId;
	
	long orderid;
	
	TAcUser tAcUser=null;
	/*
	 * 取消订单
	 */
	public Integer CancelOrder(Request_CancelOrder req){
				// 取消订单，普通客户及有预订权限的用户进行操作
				String cancelreason =req.memo;
				userId = req.userId;
			    orderid =req.orderId;
				tAcUser = aos.getUserByLoginname(userId);
				TOrder theorder = aos.getOneOrder(orderid);
		
				int cancelresult = 0; // 取消结果，0代表成功
				if ("1".equals(theorder.getOrderLockStatus())
						&& !theorder.getOpUser().equals(
								tAcUser.getUsername())) { // 如果提交取消订单的时候，已经被别人锁定，则提示不能取消
					cancelresult = 1;
				} else if (!"1".equals(theorder.getOrderStatus())
						&& !"2".equals(theorder.getOrderStatus())
						&& !"3".equals(theorder.getOrderStatus())
						&& !"4".equals(theorder.getOrderStatus())) {
					// 如果提交取消订单的时候，已经不是“待支付”或“待审核”的状态，则提示不能取消     //黄玄修改, 修改为待支付,待审核,已审核,已支付的都能取消订单
					cancelresult = 1;
							/*+ getDictNameByIdFromCache("ORDER_STATUS",
									theorder.getOrderStatus())*/;
				} else {
					theorder.setOrderStatus("5"); // 修改订单状态为已取消
					theorder.setOpReason(cancelreason); // 设置取消原因
					theorder.setOpUser(tAcUser.getUsername());
					// 设置操作人为当前登录用户
					theorder.setOpTime(new Date());
				
					
					 String flag=cancelOrDeleteOrder("1","1",theorder.getOId());
					 
			
					 if(flag==null){
						
						    deleteOrderTicket(theorder);
			              
			            }
				}
				/*Struts2Util.renderText(cancelresult);*/
		
		
		
		
		return cancelresult;
	}
	
	
	
	/**
	 * @title:国内国际机票订单删除或者取消退钱方法
	 * @param orderType   1:国内  2：国际
	 * @param cancelOrDelete   1:取消  2：删除
	 * @param orderId   订单号  国内传国内订单ID   国际传国际订单ID
	 */
	@SuppressWarnings({ "unchecked" })
	public String cancelOrDeleteOrder(String orderType,String cancelOrDelete,long orderId){
		Boolean flag=true;
		try{
			TOrder torder = aos.getOneOrder(orderId);
			
		
			List obj =null;
			String status="";
			String orderno="";
			if("1".equals(orderType)){//1:国内
				orderno=torder.getOrderno();
			    status=torder.getPayMethod();
			}
			
			if("1".equals(cancelOrDelete)){//1:取消  如果是预付款就冲抵预付款
				if("4".equals(status)){
					
					
					String sql="select sum(t.money), t.orgid from TAcOrgBalance t where t.orderNo= '" +orderno+"' GROUP BY t.orgid";
					obj = auditOrderDao.queryForList(sql);
				 
					if(obj!=null && obj.size()>0){
						Object[] results=(Object[])obj.get(0);
						TAcOrg tacorg = tod.findOne(Long.valueOf(results[1].toString()));
						
					
						Double tit=Double.valueOf(results[0]==null?"0":results[0].toString());
						
						Map<String,String> map = balancePay("-2", tit*-1, orderno, "", tacorg.getOrgid(), tacorg.getOrgname(),"");		
			            
						if(!"0".equals(map.get("status"))){
		                    flag = false;
		                }
					}
				}
	            if(!flag){
	                return "预付款退款失败，请联系管理员";
	            }
			}
		}catch (Exception e) {
			e.printStackTrace();
			if("1".equals(cancelOrDelete)){
				return "取消失败!";
			}
		}
		return null;
	}
	
	/**
     * 删除订单，机票表中机票状态要修改
     * 
     * @param order
     */
    public void deleteOrderTicket(TOrder order) {
        for (TPnr pnr : order.getTPnrs()) {
            for (TTravelItinerary ticket : pnr.getTTravelItineraries()) {
                ticket.setFlightStatus("13");
                ticket.setTravelItineraryNo("");
            
                auditOrderDao.executeSave(ticket);
            }
        }
      
        auditOrderDao.executeSave(order);

        //dao.batchExecute("delete TTicketRealtimeProfit where orderno=?", order.getOrderno());// 删除实时利润表
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
	public Map<String,String> balancePay(String flowStatus,Double money,String orderNo,String note,Long orgId,String orgName,String...value){
		Double balance =null;
		Map<String,String> map = new HashMap<String,String>();
		//证明为新的订单添加,则首先查询当前余额
		boolean b=true;
		String hql="SELECT SUM(money) FROM TAcOrgBalance T WHERE 1=1 AND T.orgid = "+orgId;
		
		balance= auditOrderDao.queryForDouble(hql);
		
	
		balance = (balance==null?0:balance);
		List<TOrder> lists =auditOrderDao.queryForList("from TOrder where orderno = '"+orderNo+"'");
		if(lists!=null&&lists.size()>0){
			TOrder order = lists.get(0);
			if(order!=null&&!"".equals(order.getSubscribeId())){
				CaiyunResp cr =null;
				AjaxResp re = cps.isUserBelongsToUban360(Long.parseLong(order.getSubscribeId()));
				if(re.getStatus()==0){
					if(Integer.parseInt(flowStatus)>0){
						 cr= cps.prePay(Long.parseLong(order.getSubscribeId()),order.getOrderno().toString(),money*-1,null);
					}else{
						 cr= cps.payRefund(Long.parseLong(order.getSubscribeId()),order.getOrderno().toString(),0d,null);//因为wetrip项目只有审核和订单取消才会走这个方法,所以退款费用暂时写死,0
					}
					if(cr.getSuccess()==true){
						setMap(map, cr.getStatus().toString(),cr.getMessage());
						b=true;
					}else{
						setMap(map, cr.getStatus().toString(),cr.getMessage());
						b=false;
					}
					
				}
			}
		}
		if(b){
		//增加了money==0的情况：
		if((money<0&&balance>=money*-1)||money >=0){//余额大于当前支付金额  或者 为负数余额（退票或改签）
			
				TAcOrgBalance entity = new TAcOrgBalance();
				
				SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String str = sim.format(new Date());
				Date d;
				try {
					d = sim.parse(str);
					entity.setOperatingDate(d);
					entity.setFlowStatus(flowStatus);
					entity.setOrgid(orgId);
					entity.setOrgname(orgName);
					entity.setOrderNo(orderNo);
					entity.setMoney(money);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			
				
				/*if(value!=null&&value.length>0){
					if(value[0]!=null&&!"".equals(value[0])&&!"app".equals(value[0])){
						entity.setTicketno(value[0]);
						entity.setUserId(Long.valueOf(userId));
						entity.setUserName(tAcUser.getUsername());
					}else if("app".equals(value[0])){
						entity.setUserId(Long.valueOf(value[1]));
						entity.setUserName(value[2]);
					}
				}else{
					entity.setTicketno("");
					entity.setUserId(Long.valueOf(userId));
					entity.setUserName(tAcUser.getUsername());
				}*/
				
				entity.setNote(note);
				
				auditOrderDao.executeSave(entity);
			
				
				
				
				
				/*//每次支付完成之后，都执行余额不足提醒方法
				balanceReminder(entity.getOrgid());
				//发送站内消息
				String title = Constants.BALANCEMAP.get(entity.getFlowStatus())+"消息提醒";
				String content = "您好，订单号为"+entity.getOrderNo()+"，的交易消费金额"
								+entity.getMoney()+"元，交易类型为"
								+Constants.BALANCEMAP.get(entity.getFlowStatus())
								+"。企业预付款剩余金额为"+balance+"元。";
				sendMessage(entity.getOrgid(),title,content);
				//发送邮件或短信（根据相关人的设置而定）
				balanceChangeRemind(entity);*/
				setMap(map,"0", "成功");
		}else{
			setMap(map,"1", "余额不足！");
		}
		}
		return map;
	}
	public void setMap(Map<String, String> map,String status,String message) {
		map.put("status", status);
		map.put("message",message);
	}
}
