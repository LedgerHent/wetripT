package com.viptrip.autoissue.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.autoissue.constant.AutoIssueConstant;
import com.viptrip.autoissue.dao.AutoissueDao;
import com.viptrip.autoissue.entity.TAutoTicket;
import com.viptrip.autoissue.entity.TAutoissueAirline;
import com.viptrip.autoissue.entity.TAutoissueSwitch;
import com.viptrip.autoissue.entity.TBspLimit;
import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.util.DateUtil;
import com.viptrip.util.EnumUtil.TAutoissueAirlineStatus;
import com.viptrip.wetrip.dao.TOrderDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;


/**
 * @author Administrator
 *
 */
@Service
public class AutoTicketImpl implements AutoTicketService{
    private static Logger logger = LoggerFactory.getLogger(AutoTicketImpl.class);
    
    @Resource
    private AutoissueDao autoissueDao;
    
    public AutoissueDao getAutoissueDao() {
        return autoissueDao;
    }

    public void setAutoissueDao(AutoissueDao autoissueDao) {
        this.autoissueDao = autoissueDao;
    }

    @Resource
    private TOrderDao tod;
    public TOrderDao getTod() {
        return tod;
    }

    public void setTod(TOrderDao tod) {
        this.tod = tod;
    }
    
	/**
	 * 根据订单号 作为条件
	 * @param orderno
	 * @param fromFlag:1:支付入口；2：审核入口；3：下单入口（月结、预支付无需审核的）4:财务直接修改支付状态入口（个人支付、现结走线下付款的入口）
	 * @return
	 */
	public String addFromPay(String orderno,String fromFlag){
		TOrder order = this.getOrderByNo(orderno);//根据订单号获取订单
		return addTAuTicket(fromFlag, order);
	}

	/**
	 * 增加自动出票记录数据逻辑
	 * @param fromFlag
	 * @param order
	 * @return
	 */
	public String addTAuTicket(String fromFlag, TOrder order) {
		TAutoTicket tautoTicet=new TAutoTicket();
		String validatMessage=autoissueValidate(order, fromFlag);
		if(StringUtils.isEmpty(validatMessage)){
			//写入记录表信息
			tautoTicet.setOrderNo(order.getOrderno());
			tautoTicet.setOrderId(order.getOId());
			tautoTicet.setPrice(order.getTotalPrice());
			Date dt=new Date();
			tautoTicet.setCreateTime(dt);
			tautoTicet.setStatus(1);//状态为待自动出票
			autoissueDao.executeSave(tautoTicet);
			return "ADD_OVER";
		}else{
			logger.info("----->>"+DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss")+ "自动出票记录："+order.getOrderno()+":"+validatMessage+";fromFlag:"+fromFlag);
			return "ADD_ERROR";
		}
	}
	
	/**
	 * 用订单ID列表做条件 录入数据
	 * @param orderIdList
	 * @param fromFlag
	 * @return
	 */
	public String addFromPay(List<String> orderIdList,String fromFlag){
		for (int i = 0; i < orderIdList.size(); i++) {
			String orderId=orderIdList.get(i);
//			TOrder order = orderManager.getTOrderById(Long.valueOf(orderId));//根据订单号获取订单
			TOrder order =tod.findOne(Long.valueOf(orderId));
			String message=addTAuTicket(fromFlag, order);
		}
		return "";
	}
	
	
    /**
     * 获取配置文件是否开启自动出票
     * 
     * @return
     */
    public String getAutomaticTicket() {
        ResourceBundle bundle = PropertyResourceBundle.getBundle("com.viptrip.autoissue.properties.autoissueApplicationSwich");
        String automaticTicket = bundle.getString("AutomaticTicket");
        return automaticTicket;
    }
	
	/**
	 * 判断各个订单入口是否可以做自动出票处理
	 * @param orderno
	 * @param fromFlag 
	 * @return
	 */
	public String autoissueValidate(TOrder order, String fromFlag) {
		//自动出票开关，系统控制总开关，设置在properties
		String autoMaticFlag=this.getAutomaticTicket();
		if(StringUtils.isEmpty(autoMaticFlag)||autoMaticFlag.equals("close")){
			return "AUTOMATIC_CLOSE";
		}
		//TOrder order = this.getOrderByNo(orderno);
		//支付入口
		if(fromFlag.equals("1")||fromFlag.equals("4")){
			//付款方式：1-公司月结，2-个人支付，3-公司现结
			if(!order.getPayMethod().equals("2") && !order.getPayMethod().equals("3")){
				return "PAYMETHOD_ERROR";
			}
//			if(fromFlag.equals("4")){
//				先不考虑验证订单的情况
//			}
		}
		//判断是否为补录（支付状态）
		if(fromFlag.equals("1")||fromFlag.equals("4")){
			//补录：1-补录散客，2-补录团队
			if(order.getIsExport()!=null&&(order.getIsExport().equals("1")||order.getIsExport().equals("2"))){
					return "ISEXPORT_ERROR";
				}
		}
		//审核入口
		if(fromFlag.equals("2")){
				if(!order.getPayMethod().equals("1") && !order.getPayMethod().equals("4")){
					return "CHECK_PAYMETHOD_ERROR";
					
				}
				
		}
		
		//判断是否为补录（审核）
		if(fromFlag.equals("2")){
			//补录：1-补录散客，2-补录团队
			if(order.getIsExport()!=null&&(order.getIsExport().equals("1")||order.getIsExport().equals("2"))){
				return "CHECK_ISEXPORT_ERROR";
			
    		}
    		
    	}
		//下单入口（无审核）
		if(fromFlag.equals("3")){
			if(!order.getPayMethod().equals("1") && !order.getPayMethod().equals("4") ){
				return "NOCHECK_PAYMETHOD_ERROR";
			}
		}
		//判断是否需要审核
		if(fromFlag.equals("3")){
			if(!order.getOrderStatus().equals("3")  ){
				return "CHECKSTATUS_ERROR";
			}
		}
		//判断是否为自动出票企业
        TAcOrg tAcOrg=this.getOrgByOrgName(order.getCompanyName());
        if(StringUtils.isEmpty(tAcOrg.getAutoTicket())||tAcOrg.getAutoTicket().equals("0")){
            return "AUTO_TICKET_NO";
        }
        //订单里出现的航司
        List<String> airlines=new ArrayList<String>();
        Iterator<TPnr> iterators=order.getTPnrs().iterator();
        while(iterators.hasNext()){
           String iteratorCompany= iterators.next().getAirlineCompany();
           if(!airlines.contains(iteratorCompany)){
               airlines.add(iteratorCompany);
           }
        }
//        System.out.println(aikrlines.size());
        //判断是否为自动出票航司
        List<TAutoissueAirline> airlineswichList=this.getAutoissueAirline(airlines);
        if(airlineswichList==null || (airlineswichList !=null && airlineswichList.size()<airlines.size())){
            return "AUTO_AIRLINE_CLOSE";
        }
        
        //判断是否开启了自动出票小开关(根据航司、企业联合设置的开关)
        List<TAutoissueSwitch> swichList=this.getAutoissueSwich(airlines,order.getCompanyName(),"");
        if(swichList==null || (swichList !=null && swichList.size()<1)){
            return "AUTO_SWITCH_CLOSE";
        }
		//判断订单里是否含儿童,只要包含儿童或者婴儿返回
		Set<TPnr> pnrs=order.getTPnrs();
		Iterator<TPnr> pnrIterator=pnrs.iterator();
		while(pnrIterator.hasNext()){
				TPnr tPnr=pnrIterator.next();
				Set<TTravelItinerary> tts=tPnr.getTTravelItineraries();
				Iterator<TTravelItinerary>  ttsIterator=tts.iterator();
				while(ttsIterator.hasNext()){
					TTravelItinerary tt=ttsIterator.next();
					if(!tt.getPassengerType().equals("1")){
						// 乘客为非成人类型
						return "PASSENGER_TYPE_NOADULT";
					}
				}
		}
		return "";
	}
	
	   /**
     * 判断各个订单入口是否可以做自动出票处理
     * @param orderno
     * @param fromFlag 
     * @return
     */
    public String autoissueBeforeValidate(List<String> airlines,String companyName,String payMethod,boolean onePNR,boolean allAdult) {
        //自动出票开关，系统控制总开关，设置在properties
        String autoMaticFlag=this.getAutomaticTicket();
        if(StringUtils.isEmpty(autoMaticFlag)||autoMaticFlag.equals("close")){
            return "AUTOMATIC_CLOSE";
        }
        //下单入口（无审核）
        if(!payMethod.equals("1") && !payMethod.equals("4") ){
            return "NOCHECK_PAYMETHOD_ERROR";
        }
  
        //判断是否为自动出票企业
        TAcOrg tAcOrg=this.getOrgByOrgName(companyName);
        if(StringUtils.isEmpty(tAcOrg.getAutoTicket())||tAcOrg.getAutoTicket().equals("0")){
            return "AUTO_TICKET_NO";
        }
        //判断是否为自动出票航司
        List<TAutoissueAirline> airlineswichList=this.getAutoissueAirline(airlines);
        if(airlineswichList==null || (airlineswichList !=null && airlineswichList.size()<airlines.size())){
            return "AUTO_AIRLINE_CLOSE";
        }
        
        //判断是否开启了自动出票小开关(根据航司、企业联合设置的开关)
        List<TAutoissueSwitch> swichList=this.getAutoissueSwich(airlines,companyName,"");
        if(swichList==null || (swichList !=null && swichList.size()<1)){
            return "AUTO_SWITCH_CLOSE";
        }
        //多个PNR 不做自动出票（规则）
        if(!onePNR){
            return "MORE_ONE_PNR";
        }
        //判断订单里是否含儿童,只要包含儿童或者婴儿返回,只有全部是成人的才做自动出票
        if(!allAdult){
            return "PASSENGER_TYPE_NOADULT";
        }
        /*Set<TPnr> pnrs=order.getTPnrs();
        Iterator<TPnr> pnrIterator=pnrs.iterator();
        while(pnrIterator.hasNext()){
                TPnr tPnr=pnrIterator.next();
                Set<TTravelItinerary> tts=tPnr.getTTravelItineraries();
                Iterator<TTravelItinerary>  ttsIterator=tts.iterator();
                while(ttsIterator.hasNext()){
                    TTravelItinerary tt=ttsIterator.next();
                    if(!tt.getPassengerType().equals("1")){
                        // 乘客为非成人类型
                        return "PASSENGER_TYPE_NOADULT";
                    }
                }
        }*/
        return "";
    }
	
	   /**
     * 通过机构名称查询出该机构的总公司
     * @param orgname
     * @return
     */
    @SuppressWarnings("unchecked")
    public TAcOrg getOrgByOrgName(String  orgname) {
        String hql="from TAcOrg  where orgname ='"+orgname+"'  and status='1'";
        List<TAcOrg> list=autoissueDao.queryForList(hql);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }
    
	//判断BSP额度是否可以自动出票
	public String bspLimits(){
		//判断限额表中的最新额度是否大于采购价总和，如果不足，转手工出票
		TBspLimit tbspl=this.queryBspLimits();//BSP中最新创建数据
		String sumPrice=this.queryPurchasePrice(AutoIssueConstant.AUTOMATIC_TICKET_SUCCESS);//采购价总和
		Double sumPurchase=Double.parseDouble(sumPrice);
		if(tbspl.getBspLimits()<sumPurchase){
			return "INSUFFICIENT_AMOUNT";
		}
		return "";
	}
	
	/**
     * 查询BSP限额信息
     */
    public TBspLimit queryBspLimits(){
        String hql=" from TBspLimit order by createtime DESC";
//        List<TBspLimit> list = dao.findByHQL(hql);
        List<TBspLimit> list =autoissueDao.queryForList(hql, null);
        return list.get(0);
    }
    
    /**
     * 查询采购价之和
     */
    public String queryPurchasePrice(int status){
        String sql="select  sum(t1.purchaseprice) from t_auto_ticket t1 where t1.updatetime>(select max(t2.createtime) from t_bsp_limit t2 )  and t1.status='"+AutoIssueConstant.AUTOMATIC_TICKET_SUCCESS+"' ";
//        Object findUniqueBySQL = this.dao.findUniqueBySQL(sql,AutoIssueConstant.AUTOMATIC_TICKET_SUCCESS);
        Double findUniqueBySQL=autoissueDao.queryForDouble(sql);
        String sumPrice=findUniqueBySQL==null?"0":findUniqueBySQL.toString();
        return sumPrice;
    }
    /**
     * 根据订单号获取订单
     * 
     * @param orderno
     * @return
     */
    public TOrder getOrderByNo(String orderno) {    
        String hql = "from TOrder where orderno='"+orderno+"'";
        List<TOrder> orders =autoissueDao.queryForList(hql, null);
//      List<TOrder> orders = autoissueDao.findByHQL(hql, orderno);
        return orders.get(0);
    }
	
  //根据ID查找TAcOrg
    public TAcOrg queryForTAcOrg(Object id,TAcOrg tac ) {
        return autoissueDao.queryForEntity(id,TAcOrg.class);
    }
	
    /**
     *从记录表中根据创建时间优先处理的原则获取 一条数据
     */
	
    public TAutoTicket getOneByDataTime(){
  
    	String hql="from TAutoTicket where status=1 order by createTime asc";
//    	List<TAutoTicket> orders = this.dao.findByHQL(hql);
    	List<TAutoTicket> orders = autoissueDao.queryForList(hql, null);
    	if(orders==null || orders.size()<1){
    		return null;
    	}
        return orders.get(0);
  
    }

        /**
         * 判断自动出票航司、企业小开关
         * @param airlines
         * @return
         */
        public List<TAutoissueSwitch> getAutoissueSwich(List<String> airlines,String orgname,String orgid) {
            String airline="";
            for (int i = 0; i < airlines.size(); i++) {
                if(StringUtils.isNotEmpty(airline)){
                    airline+="%";
                }
                airline+="%"+airlines.get(i);
            }
            String sql = "from TAutoissueSwitch where air2char like '"+airline+"' and orgname like '%"+orgname+"%' and status='1'";
            if(StringUtils.isNotEmpty(orgid)){
                sql = "from TAutoissueSwitch where air2char like '"+airline+"' and orgname like '%"+orgname+"%' and status='1' and orgid like '%"+orgid.trim()+"%'";
            }
            List<TAutoissueSwitch> lstAutoissueAirline =autoissueDao.queryForList(sql,null);
            return lstAutoissueAirline;
        }
    
        /**
         * 判断自动出票航司开关
         * @param airlines
         * @return
         */
        public List<TAutoissueAirline> getAutoissueAirline(List<String> airlines) {
    	    String airline="";
    	    for (int i = 0; i < airlines.size(); i++) {
    	        if(StringUtils.isNotEmpty(airline)){
    	            airline+=",";
    	        }
    	        airline+="'"+airlines.get(i)+"'";
            }
            String sql = "from TAutoissueAirline where air2char in("+airline+") and status='"+TAutoissueAirlineStatus.开启.getValue()+"'";
            List<TAutoissueAirline> lstAutoissueAirline = autoissueDao.queryForList(sql, null);
            return lstAutoissueAirline;
        }
    	
}
