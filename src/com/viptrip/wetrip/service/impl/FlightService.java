package com.viptrip.wetrip.service.impl;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.autoissue.service.AutoTicketService;
import com.viptrip.base.action.AjaxResp;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.BookErrorTypeCode;
import com.viptrip.base.common.MyEnum.PriceAgreementTypeCode;
import com.viptrip.common.controller.CreateTTmcApproveProcess;
import com.viptrip.common.controller.ListApprovalByTravller;
import com.viptrip.common.model.ApproveProcessTravellerMode;
import com.viptrip.common.model.BookerInfo;
import com.viptrip.common.model.Request_CreateTTmcApproveProcess;
import com.viptrip.common.model.Request_ListApprovalByTravller;
import com.viptrip.common.model.Response_CreateTTmcApproveProcess;
import com.viptrip.common.model.Response_ListApprovalByTravller;
import com.viptrip.common.model.TTmcApproveProcessInfo;
import com.viptrip.common.util.ApprovalEnum.ApprovalBussType;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.ibeserver.service.ReadIBEDataService;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.controller.TicketPolicyFilter;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.MailAndNoteMsg;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TCangwei;
import com.viptrip.wetrip.entity.TComment;
import com.viptrip.wetrip.entity.TExcessRelation;
import com.viptrip.wetrip.entity.TExcessRelationId;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.entity.TPnr;
import com.viptrip.wetrip.entity.TTravelItinerary;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_BookAirTicket;
import com.viptrip.wetrip.model.Request_GetChildBabyPrice;
import com.viptrip.wetrip.model.Request_GetFlightDetail;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Request_GetReschedueFlightList;
import com.viptrip.wetrip.model.Request_TicketPolicyFilter;
import com.viptrip.wetrip.model.Response_CancelOrder;
import com.viptrip.wetrip.model.Response_TicketPolicyFilter;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Contact;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_OverReason;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_BookAirTicket_Trip;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice_PriceInfo;
import com.viptrip.wetrip.model.flight.RespData_GetChildBabyPrice_Suggest;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.RespData_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.type.CabinType;
import com.viptrip.wetrip.model.orderlist.RespData_GetOrderDetail;
import com.viptrip.wetrip.model.policy.ApproveTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.service.BalancePay;
import com.viptrip.wetrip.service.BookOrderService;
import com.viptrip.wetrip.service.ICaiyunPayService;
import com.viptrip.wetrip.service.IFlightService;
import com.viptrip.wetrip.service.IGetOrderService;
import com.viptrip.wetrip.service.ServiceFee;
import com.viptrip.wetrip.util.Convertor;
import com.viptrip.wetrip.util.ConvertorFactory;
import com.viptrip.wetrip.util.ListObjectConverter;
import com.viptrip.wetrip.util.SendMessages;
import com.viptrip.wetrip.vo.CaiyunResp;
import com.viptrip.wetrip.vo.CompanyFlightCabinTypeCode;
import com.viptrip.wetrip.vo.PassengerIdTypeAndIdNum;

import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class FlightService implements IFlightService{
	private static Logger logger = LoggerFactory.getLogger(FlightService.class);
	
	@Resource
	private AutoTicketService autoTicketService;
	
	@Resource
	private ReadIBEDataService rimservice;
	
	@Resource
	private ServiceFee serviceFee;
	
	@Resource
	private BalancePay balancePay;
	
	@Resource
	private ComDao comDao;
	
	@Resource
	private BookOrderService bookOrderServiceImpl;
	
	@Resource(type=ICaiyunPayService.class)
	private ICaiyunPayService cps;
	
	@Resource
	private IGetOrderService getOrderService;

	
	/*public List<RespData_GetFlightList> getFlightList1(
			Request_GetFlightList para) {
		List<RespData_GetFlightList> result = null;
		//起飞地点\目的地点\起飞日期\'ALL'\航空公司拼接字符串，格式：CA_MU,传null 或者 "" 认为是全部航空公司 
		if(null!=para){
			List<ReqData_GetFlightList_TripInfo> tripInfos = para.getData().getTripInfo();
			
			if(null!=tripInfos && tripInfos.size()>0){
				ReqData_GetFlightList_TripInfo tripInfo = tripInfos.get(0);
				String depAirport = tripInfo.getDepAirport();//起飞城市
				String arrAirport = tripInfo.getArrAirport();//到达城市
				String date = tripInfo.getDate()==null?null:tripInfo.getDate().replaceAll("-", "");//将yyyy-MM-dd转成yyyyMMdd格式
				String airline = para.getData().getAirline();//航空公司二字码
				if(logger.isDebugEnabled()){
					logger.debug("FlightService.getFlightList()→paramName:depAirport,paramValue:{1}",depAirport);
					logger.debug("FlightService.getFlightList()→paramName:arrAirport,paramValue:{1}",arrAirport);
					logger.debug("FlightService.getFlightList()→paramName:date,paramValue:{1}",date);
					logger.debug("FlightService.getFlightList()→paramName:airline,paramValue:{1}",airline);
				}
				
				List<DispplayTrip> trip = rimservice.getIBEData(para);
				
				Integer cabinType = para.getData().getCabinType();//仓位类型
				if(null!=cabinType){
					trip = filterByCabinType(trip,cabinType);
				}
				
//				List<DispplayTrip> trip = delegate.getIBEData(depAirport, arrAirport, date, "PUB", airline);
				
//				trip = sortprice2(trip);
//				//设置起飞、到达城市
//				if(null!=trip&&trip.size()>0){
//					List<DispplayTrip> trip1 = new ArrayList<DispplayTrip>();
//					for(DispplayTrip t:trip){
//						if(StringUtil.isEmpty(t.getOrgcity())){
//							t.setOrgcity(depAirport);
//							t.setDetcity(arrAirport);
//						}
//						trip1.add(t);
//					}
//					trip = trip1;
//				}
				
				List<RespData_GetFlightList> to = new ArrayList<>();
				to = ListObjectConverter.convert(trip, to , ConvertorFactory.instance().getConvertor(DispplayTrip.class, RespData_GetFlightList.class));
				List<RespData_GetFlightList> to1 = new ArrayList<>();
				for(RespData_GetFlightList obj:to){
					obj.setMapKey(rimservice.getLeftFlightKey(para));//设置mapkey
//					obj.setMapKey(getMapKey(obj));//设置mapkey
					to1.add(obj);
				}
				result = to1;
			}
		}
		return result;
	}*/
	
	@Override
	public List<RespData_GetFlightList> getFlightList(
			Request_GetFlightList para) {
		List<RespData_GetFlightList> result = null;
		List<DispplayTrip> trip = rimservice.getIBEData(para);//模拟从rimservice得到数据
		
		//国内差旅管控过滤
        trip = filterPolicy(para, trip);
		result = ListObjectConverter.convert(trip, result, ConvertorFactory.instance().getConvertor(DispplayTrip.class, RespData_GetFlightList.class));
		return result;
	}

	/**
	 * 国内差旅管控过滤
	 * @param para
	 * @param trip
	 * @return
	 */
    public List<DispplayTrip> filterPolicy(Request_GetFlightList para, List<DispplayTrip> trip) {
        Request_TicketPolicyFilter reqPolicy=new Request_TicketPolicyFilter();
        if(para.getData()!=null && para.getData().getPassenger()!=null){
            reqPolicy.passengers=para.getData().getPassenger();
            reqPolicy.tripWay=para.data.tripWay.toString();
            reqPolicy.entitys=trip;
            TicketPolicyFilter policyFilter=new TicketPolicyFilter();
            OutputResult<Response_TicketPolicyFilter, String> orpolicy=policyFilter.ClientRequest(reqPolicy, Response_TicketPolicyFilter.class);
            if(orpolicy.IsSucceed()){
                trip=orpolicy.getResultObj().entitys;
            }
        }
        return trip;
    }
    /**
     * 国内差旅管控过滤
     * @param para
     * @param trip
     * @return
     */
    public List<DispplayTrip> filterPolicyForDetail(Request_GetFlightDetail para, List<DispplayTrip> trip) {
        Request_TicketPolicyFilter reqPolicy=new Request_TicketPolicyFilter();
        if(para.getPassengers()!=null && para.getPassengers()!=null){
            List<ReqData_GetFlightList_Passenger> passengers=new ArrayList<ReqData_GetFlightList_Passenger>();
            ReqData_GetFlightList_Passenger passenger=new ReqData_GetFlightList_Passenger();
            passenger.setPassengers(para.getPassengers());
            passengers.add(passenger);
            reqPolicy.passengers=passengers;
            reqPolicy.entitys=trip;
            reqPolicy.tripWay=String.valueOf(para.getTripWay().intValue());
            reqPolicy.userid=para.getUserId();
            reqPolicy.versionId=para.versionId;
            TicketPolicyFilter policyFilter=new TicketPolicyFilter();
            OutputResult<Response_TicketPolicyFilter, String> orpolicy=policyFilter.ClientRequest(reqPolicy, Response_TicketPolicyFilter.class);
            if(orpolicy.IsSucceed()){
                trip=orpolicy.getResultObj().entitys;
            }
        }
        return trip;
    }
	@Override
	public RespData_GetFlightDetail getFlightDetail(Request_GetFlightDetail para){
		RespData_GetFlightDetail result = null;
		if(null!=para){
			String mapKey = para.getMapKey();
			List<DispplayTrip> ibeDate = rimservice.getIBEData(mapKey,para.getUserId());
			//国内差旅管控过滤
			ibeDate = filterPolicyForDetail(para, ibeDate);
//			result = ListObjectConverter.convert(ibeDate, ConvertorFactory.instance().getConvertor(new Class, RespData_GetFlightDetail.class));
			Convertor<List, RespData_GetFlightDetail> convertor = ConvertorFactory.instance().getConvertor(List.class, RespData_GetFlightDetail.class);
			result = convertor.convert(ibeDate);
		}
		return result;
	}

	
	@Override
	public RespData_GetChildBabyPrice getChildBabyPrice(
			Request_GetChildBabyPrice para) {
		RespData_GetChildBabyPrice priceinfo=new RespData_GetChildBabyPrice();
		List<RespData_GetChildBabyPrice_PriceInfo> prices=new ArrayList<RespData_GetChildBabyPrice_PriceInfo>();
		RespData_GetChildBabyPrice_PriceInfo price=null;
		RespData_GetChildBabyPrice_Suggest groomPriceInfo=null;
		
		for (String mapKey : para.getMapKeys()) {
			DispplayTrip dispplayTrip=rimservice.getIBEDataForBook(mapKey,para.userId);
			
			price=new RespData_GetChildBabyPrice_PriceInfo();
			price.type=para.type;
			price.price=dispplayTrip.getPrice();//原价
			price.priceSource=dispplayTrip.getAgreementTypeCode();
			
			groomPriceInfo=new RespData_GetChildBabyPrice_Suggest();
			groomPriceInfo.cabin=dispplayTrip.getCangwei();
			
			if(1==para.type){//1儿童 ,2婴儿
				price.rebatePrice=comDao.getMathet(dispplayTrip.getyPrice());//儿童票打Y舱全价的五折
				if(dispplayTrip.getPrice() / dispplayTrip.getDiscountrate() >=0.5){
					groomPriceInfo.rebatePrice=comDao.getMathet(dispplayTrip.getyPrice());//(折扣大于五折时)推荐儿童票购买Y舱全价的五折
				}else{
					groomPriceInfo.rebatePrice=dispplayTrip.getPrice();//(折扣小于五折时)推荐儿童票购买成人票价
				}
				price.airportTax=0d;//儿童基建全免
				price.fuelSurTax=comDao.getMathet(dispplayTrip.getFueltax());//儿童燃油减半
			}else if(2==para.type){//婴儿无推荐价
				price.rebatePrice=comDao.getMathye(dispplayTrip.getyPrice());//Y舱全价的一折
				price.airportTax=0d;//婴儿基建，燃油全免
				price.fuelSurTax=0d;
			}
			price.suggest=groomPriceInfo;
			
			
			prices.add(price);
		}
		
		priceinfo.priceInfo=prices;
		return priceinfo;
	}

	@SuppressWarnings({ "unused", "rawtypes", "unchecked", "static-access" })
	@Override
	public RespData_BookAirTicket bookAirTicket(Request_BookAirTicket para) {
	    CreateTTmcApproveProcess cctmcProcess=new CreateTTmcApproveProcess();
		RespData_BookAirTicket rb =new RespData_BookAirTicket();
		TOrder order =null;
		try {
			Date sysDate=new Date();
			TAcUser tacuser=comDao.queryForEntity(para.userId,TAcUser.class);//当前登陆人信息
			TAcUser checkUser=null;
			if(para.data.auditorId!=null&&!"".equals(para.data.auditorId.toString())){
			    checkUser=comDao.queryForEntity(Long.valueOf(para.data.auditorId.toString()),TAcUser.class);//当前登陆人信息

			}
			
			TAcOrg tacorg=comDao.queryForEntity(tacuser.getOrgid(),TAcOrg.class);
			TAcOrg comOrg=comDao.queryForEntity(Long.valueOf(tacorg.getCompany()),TAcOrg.class);
			if(tacuser==null){
				rb.setOrderId(BookErrorTypeCode.登陆人id不对.getValue());
				return rb;
			}
			 order =new TOrder();
			order.setOrderno("");//订单号
			order.setTotalServiceFee(0d);//服务费总和
			
			order.setPayMethod(para.data.payMethod.toString());
			if("1".equals(order.getPayMethod()) || "4".equals(order.getPayMethod())){//付款方式：1-公司月结，2-个人支付，3-公司现结, 4-预付款
	            if ("0".equals(comOrg.getVerify())) {//  是否需要审核  1 需要  0不需要
	                order.setOrderStatus("3");// 3-已审核，
	            }else{
	            	order.setOrderStatus("1");// 1-待审核，
	            	if(checkUser==null){
	            		rb.setOrderId(BookErrorTypeCode.审核人id不对.getValue());
	            		return rb;
	            	}
	            	order.setCheckmenId(checkUser.getUserid());//审核人id
	            	order.setCheckmen(checkUser.getUsername());//审核人名字
	            	order.setChecktel(checkUser.getPhone());//审核人电话
	            	order.setCheckemail(checkUser.getEmail());//审核人邮箱
	            }
			}else {
	            order.setOrderStatus("2");// 2-待支付
	        }
			order.setOrderLockStatus("0");// 订单锁定状态(0、未锁定，1锁定)
			
			
			order.setSubscribeDate(sysDate);//预定时间
			order.setSubscribeName(tacuser.getUsername());//预定人名字
	        order.setSubscribeTel(tacuser.getPhone());//预定人电话
	        order.setSubscribeEmail(tacuser.getEmail());//预定人邮箱
	        order.setSubscribeId(tacuser.getUserid().toString());  //预定人id
	        
	        order.setCompanyName(comOrg.getOrgname());//企业名字
	        
	        order.setProcessstate(para.data.tripType.toString());//单程
			
	
	        
	        order.setIsWhere("5");//0:pc端  1:app端   2:网站  4.宝库  5:微信
	        if(para.businessType==1){
	        	order.setTravelType("0");//出行类型: 0:因公出行  1:因私出行
	        }else {
	        	order.setTravelType("1");
	        }
	        
	
	        
	        List<TPnr> tpnrs=new ArrayList<TPnr>();
	        TPnr pnr =null;
	        
	        List<TTravelItinerary> tts=new ArrayList<TTravelItinerary>();
	        List<TTravelItinerary> trs=null;
	        
	        Double totalPrice = 0d;
	        Double totalDiscount = 0d;
	        List<ApproveProcessTravellerMode> traveller=new ArrayList<ApproveProcessTravellerMode>();//给审批流使用
	        List<ReqData_BookAirTicket_OverReason> overReasons = para.data.getOverReason();//超标理由
	        for (ReqData_BookAirTicket_Trip trip : para.data.trips) {
	        	DispplayTrip dispplayTrip=rimservice.getIBEDataForBook(trip.mapKey,para.userId);
	        	
	        	pnr =new TPnr();
	        	pnr.setTOrder(order);
	            pnr.setAirlineCompany(dispplayTrip.getAirline().substring(0, 2));//航空公司
	            pnr.setOriginCity(dispplayTrip.getOrgcity());//起飞城市
	            pnr.setDestinationCity(dispplayTrip.getDetcity());//目的城市
	            pnr.setFlightDate(dispplayTrip.getStartdate());//出发日期，订票时选择的出发日期
	            pnr.setOriginTime(dispplayTrip.getStartdate());//起飞时间，包含起飞日期
	            pnr.setDestinationTime(dispplayTrip.getArrvidate());//到达时间，包括到达日期
	            pnr.setOrgterm(dispplayTrip.getOrgterm());//起飞航站楼
	            pnr.setDetterm(dispplayTrip.getDetterm());//到达航站楼
	            pnr.setAirline(dispplayTrip.getAirline());//航班号
	            pnr.setCangwei(dispplayTrip.getCangwei());//舱位
	            pnr.setPlanetype(dispplayTrip.getPlanetype());//飞机机型
	            
	            pnr.setFfstr(dispplayTrip.getFfstr());   //经停字符串
	            
	            if(overReasons!=null&&overReasons.size()>0){
	            	for (ReqData_BookAirTicket_OverReason reason : overReasons) {
						if(reason.type==10){
							pnr.setReason(reason.reason);//未选择最低价理由
						}
					}
	            }
	            
	            pnr.setFlighttype(trip.flowId.toString());//航程类型，1-去程(单程)航班，2-回程航班
	            if(PriceAgreementTypeCode.大客户协议价.getValue().equals(dispplayTrip.getAgreementTypeCode())){
	            	pnr.setIsorgdealprice("1");//是否企业协议价标志，0-否，1-是
	            }else{
	            	pnr.setIsorgdealprice("0");//是否企业协议价标志，0-否，1-是
	            }
                pnr.setAssurancePrice(0d);//保险总费用，存放具体金额，显示时要注意除20，显示为2*20的形式，只是针对此PNR的保险，回程如保险不一样单独放在回程的记录中
	            pnr.setEnterprisrdiscount(0d);//企业返点
	            pnr.setTicketYPrice(dispplayTrip.getyPrice());//Y舱全价,代码中已经被借用存票面价
	            //pnr.setTicketFPrice(0d);//头等舱价格，代码中已经被视为存相关舱位等级的全价
	            //pnr.setTaxes(0d);//税金
	            
	            pnr.setAirlineshare(dispplayTrip.getAirlineshare());  //航班代码共享
	            
	        	
	            //pnr.setLowestPrice(0d);  //当日最低价格
	            
	        	pnr.setPnrstatus("");//行动代码
	        	pnr.setBigplait("");//大编
	        	pnr.setPnr("");//
	            pnr.setSupplierId(0);//供应商ID，与供应商表关联，存放供应商表中的供应商ID(看以前数据皆为0，暂存为0)
	            pnr.setSuppliername(null);  //供应商name
	            pnr.setZwcpdate(null);//最晚出票时间
	
	         // 修改，如果pnr中记录的享受企业协议价标志为0，才要计算返点价格！为1则是享受协议价，不享受返点
	            if(!"2".equals(order.getPayMethod())){
	            	if (pnr.getIsorgdealprice() == null || "".equals(pnr.getIsorgdealprice())|| "0".equals(pnr.getIsorgdealprice())) {
	                    if (comOrg.getBjrebate() != null && Double.parseDouble(comOrg.getBjrebate().toString()) != 0.0
	                            && (pnr.getOriginCity().equals("PEK") || pnr.getOriginCity().equals("NAY"))) {
	                        pnr.setEnterprisrdiscount(Double.parseDouble(comOrg.getBjrebate().toString()));
	                    } else {
	                        if (comOrg.getShrebate() != null && Double.parseDouble(comOrg.getShrebate().toString()) != 0.0
	                                && (pnr.getOriginCity().equals("SHA") || pnr.getOriginCity().equals("PVG"))) {
	                            pnr.setEnterprisrdiscount(Double.parseDouble(comOrg.getShrebate().toString()));
	                        } else {
	                            if (comOrg.getInlandrebate() != null
	                                    && Double.parseDouble(comOrg.getInlandrebate().toString()) != 0.0) {
	                                pnr.setEnterprisrdiscount(Double.parseDouble(comOrg.getInlandrebate().toString()));
	                            } else {
	                                pnr.setEnterprisrdiscount(0.0);
	                            }
	                        }
	                    }
	                }
	            }
	            
	           
	            
	            trs=new ArrayList<TTravelItinerary>();
	            TTravelItinerary tr=null;
	            
	            Double assurance = 0d;
	            Double taxPrice = 0d;
	            Double fueltax = 0d;
	            Double proxyrate = 0d;
	            Double flightSum = 0d;
	        	for (ReqData_BookAirTicket_Passenger passenger : para.data.passengers) {
	            	tr=new TTravelItinerary();
	            	tr.setTPnr(pnr);
	            	ApproveProcessTravellerMode travellerobj=new ApproveProcessTravellerMode();
	            	
	            	if(1==passenger.type){//1=企业员工，2=常旅客
	            		TAcUser tUser=comDao.queryForEntity(Long.valueOf(passenger.id), TAcUser.class);
	            		if(tUser==null){
	            			rb.setOrderId(BookErrorTypeCode.乘机人id不对.getValue());
	                		return rb;
	            		}
	            		tr.setPassengerName(tUser.getUsername());
	            		tr.setPassengerType("1");//证件类型   企业员工都是成人
	            		tr.setPassengerTel(tacuser.getPhone());//手机号码
	            		tr.setPassengerEmail(tacuser.getEmail());//邮箱
	            		//审批流相关信息
	            		travellerobj.setId(tUser.getUserid());
	            		travellerobj.setName(tUser.getUsername());
	            		travellerobj.setMobile(tUser.getPhone());
	            		travellerobj.setEmail(tUser.getEmail());
	            		travellerobj.setOrgId(tUser.getOrgid());
	            		
	            	}else if(2==passenger.type){//1=企业员工，2=常旅客
	            		TTravelPassenger pUser=comDao.queryForEntity(Long.valueOf(passenger.id), TTravelPassenger.class);
	            		if(pUser==null){
	            			rb.setOrderId(BookErrorTypeCode.乘机人id不对.getValue());
	                		return rb;
	            		}
	            		tr.setPassengerName(pUser.getName());
	            		tr.setPassengerType(pUser.getPassengerType());//证件类型
	            		tr.setPassengerTel(pUser.getMobilephone());//手机号码
	            		tr.setPassengerEmail(pUser.getEmail());//邮箱
	            		//审批流相关信息
	            		travellerobj.setId(Long.valueOf(-1));
	            		travellerobj.setName(pUser.getName());
                        travellerobj.setMobile(pUser.getMobilephone());
                        travellerobj.setEmail(pUser.getEmail());
                        travellerobj.setOrgId(pUser.getDept());
                        
	            	}else{
	            		rb.setOrderId(BookErrorTypeCode.乘机人类型不对.getValue());
	            		return rb;
	            	}
	            	
	            	String idType=comDao.getIdType(passenger.idType);
	            	if("0".equals(idType)){
	            		rb.setOrderId(BookErrorTypeCode.乘机人证件类型不对.getValue());
	            		return rb;
	            	}else{
	            		tr.setPassengerIdtype(idType);
	            	}
	            	
	            	tr.setPassengerId(passenger.idNum);
	            	
	            	
	            	tr.setFlightNumber(pnr.getAirline());//航班号
	            	tr.setFlightTime(pnr.getFlightDate());//航班起飞时间
	            	//tr.setFlightTransfer(pnr.getFfstr());//航班经由地（联航需要此值）
	            	
	            	tr.setTicketPrice(dispplayTrip.getPrice());//票价（客户预订的时候点选的价格）
	            	tr.setTaxPrice(dispplayTrip.getTaxfee());//机场税 
	            	tr.setFueltax(dispplayTrip.getFueltax());//燃油税
	            	
	            	tr.setProxyRate(dispplayTrip.getDiscountrate());//实际存放的是机票的折扣率，数值来自IBE接口，存放的内容类似于40，50，60之类的，在数据分析报告生成的时候需要根据此字段查询
	            	tr.setEnterpriseOtherDiscount(null);//企业其他折扣（预留）
	            	tr.setDiscountPrice(null);//底价（弘景成本，实际采购价格，由出票人员出票时填写）
	            	tr.setZPercent(0d);//Z值
	            	tr.setProfit(null);//出票利润
	            	tr.setTicketPay(null);//应付（支付给供应商的钱   即  票面底价+机建+燃油）
	            	tr.setTaxes(null);//税金（国际订单补录时用到的税收，包含机建、燃油、安检等税收）
	            	tr.setIssuePrice(null);//出票票面价
	            	tr.setSpecialProfit(null);//特殊利润等于票面价（来自于航信，客户可见）减企业返点金额再减出票票面价
	            	tr.setServiceFee(0d);////出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	            	tr.setNightFee(0d);//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	            	tr.setCustomerServiceFee(0d);//机票客人预订出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	            	tr.setProxyPercent(0d);//代理费（百分比）
	            	tr.setQuotaProxyPercent(0d);//定额代理费
	            	tr.setElsePayable(0d);//其他应付总额
	            	tr.setElseReceiveable(0d);//其他应收总额
	            
	            	//已赋值
	            	tr.setEnterpriseDiscount(pnr.getEnterprisrdiscount());;//企业折扣（即返点）
	            	if ("1".equals(tr.getPassengerType())) {
	                    tr.setHxTicketPrice(comDao.exectu(tr.getTicketPrice().doubleValue()
	                            * (1 - (tr.getEnterpriseDiscount() == null ? 0 : tr.getEnterpriseDiscount().doubleValue()))));//返点后票面价
	                    // 返点要除去，如果有的话
	                }else if ("2".equals(tr.getPassengerType())) {// 儿童票打Y舱全价的五折不享受任何优惠
	                    if (tr.getTicketPrice() / pnr.getTicketYPrice() >= 0.5) {
	                    	tr.setTicketPrice(comDao.getMathet(pnr.getTicketYPrice()));// (折扣大于五折时)儿童票打Y舱全价的五折不享受任何优惠
	                    } else {
	                        if ("1".equals(trip.childBabySuggestBook)) {// 儿童是按照建议预定
	                            tr.setTicketPrice(tr.getTicketPrice());// (折扣小于五折时)儿童票等于成人票价（但是不享受任何优惠）
	                        } else if ("0".equals(trip.childBabySuggestBook)){// 儿童不按照建议预定(儿童票打Y舱全价的五折)
	                        	tr.setTicketPrice(comDao.getMathet(pnr.getTicketYPrice()));
	                        }
	                    }
	                    tr.setEnterpriseDiscount(0d);// 儿童无返点
	                    tr.setHxTicketPrice(tr.getTicketPrice()); //儿童票的航信票面价其实与儿童票价一样
	                }else if ("3".equals(tr.getPassengerType())) {
	                	tr.setTicketPrice(comDao.getMathye(pnr.getTicketYPrice()));// 婴儿票打Y舱全价的一折不享受任何优惠
	                    tr.setEnterpriseDiscount(0.0);// 婴儿无返点
	                    tr.setHxTicketPrice(tr.getTicketPrice()); // 婴儿票的航信票面价其实与婴儿票价一样
	                }
	            	
	            	
	            	TAcInsurance tc=comDao.queryForEntity(passenger.insuranceId, TAcInsurance.class);
	            	if(passenger.getInsuranceNum()>0&&tc!=null){
	            		tr.setAssuranceNum(Double.valueOf(passenger.getInsuranceNum()));
	            		tr.setAssurancePrice(tc.getPrice());
	            		tr.setInsuranceUpset(tc.getFloorPrice());
	            		tr.setInsuranceId(tc.getId());
	            	}else{
	            		tr.setAssuranceNum(0d);
		            	tr.setAssurancePrice(0d);
		            	tr.setInsuranceUpset(0d);
		            	tr.setInsuranceId(null);
	            	}
	            	
	            	
	            	 tr.setFlightSum(comDao.exectu(tr.getAssurancePrice() * tr.getAssuranceNum()
	                         + tr.getTaxPrice() + tr.getFueltax()
	                         + tr.getTicketPrice()
	                         - tr.getTicketPrice()
	                         * (tr.getEnterpriseDiscount() == null ? 0 : tr.getEnterpriseDiscount())));// 用户实际支付金额。
	            	 tr.setTicketRecieve(tr.getFlightSum());//应收（客户支付给景鴻的钱   即  票面价+机建+燃油）
	            	 
	            	 tr.setFlightStart(pnr.getOriginCity());// 起飞城市
	                 tr.setFlightEnd(pnr.getDestinationCity());// 目的城市
	            	
	            	
	            	tr.setFlightStatus("11");//机票状态（0-初始，1-已申请退票，2-退票处理拒绝，3-已申请改期，4-退票处理通过，即已退票，5-改期处理通过，即已改期，6-改期处理不通过，9-待航空公司退票，11-待出票，12-已出票，13-已删除）
	            	
	            	if(passenger.costCenter!=null){
	            		TAcOrg corg=comDao.queryForEntity(Long.valueOf(passenger.costCenter), TAcOrg.class);
		            	if(corg==null){
		            		if("1".equals(order.getPayMethod()) || "4".equals(order.getPayMethod()) ){//付款方式：1-公司月结，2-个人支付，3-公司现结, 4-预付款)
		            			rb.setOrderId(BookErrorTypeCode.乘机人的费用归属不对.getValue());
			            		return rb;
		            		}
		            	}
		            	tr.setOrgid(corg.getOrgname());
		            	tr.setCostDepartments(bookOrderServiceImpl.getTotalPath(Long.valueOf(passenger.costCenter)));
		            	
		            	travellerobj.setCostId(corg.getOrgid());
		            	travellerobj.setCostName(corg.getCompanyname());
	            	}else{
	            		if("1".equals(order.getPayMethod()) || "4".equals(order.getPayMethod()) ){//付款方式：1-公司月结，2-个人支付，3-公司现结, 4-预付款)
	            			rb.setOrderId(BookErrorTypeCode.乘机人的费用归属不对.getValue());
		            		return rb;
	            		}
	            	}
	            	
	            	
	            	tr.setProjectid(passenger.projectNo);
	            	tr.setTicketUseState("0");//机票使用状态（0-未使用，1-已使用，2-失效）
	            	//tr.setPayment("0");//支付方式（景鴻支付给供应商的支付方式）
	            	tr.setExpStatus("0");//账单比对状态（0-为未比对过的数据，1-比对过的数据）
	            	tr.setSupplierName(null);//供应商name（采购方式）
	            	tr.setAgreementCode(null);//协议编码
	            	tr.setCustomerPaymentPattern(order.getPayMethod());//客户支付方式,记录的是客户选择的支付类型（1-公司月结，2-个人支付，3-公司现结）
	            	tr.setRangeOfType(order.getProcessstate());//航程类型 （1-单程，2-往返）
	            	
	            	tr.setAdminId("");//管理员id
	            	tr.setAdminName("");//管理员姓名
	            	
	            	
	            	
	            	
	            	trs.add(tr);
	            	tts.add(tr);
	            	
	            	assurance+=tr.getAssuranceNum()*tr.getAssurancePrice();
	            	taxPrice+=tr.getTaxPrice();
	            	fueltax+=tr.getFueltax();
	            	proxyrate=tr.getProxyRate();
	            	flightSum+=tr.getFlightSum();
	            	
	            	traveller.add(travellerobj);
	    		}
	        	pnr.setTicketcount(Long.valueOf(pnr.getTTravelItineraries().size()));//机票数量
	        	pnr.setAssurancePrice(assurance);
	        	pnr.setTaxPrice(taxPrice);//机场建设费
	        	pnr.setFueltax(fueltax);//燃油税
	        	pnr.setDiscountPrice(flightSum);//折后价（内部）
	        	pnr.setTotal(pnr.getDiscountPrice());//总价（应收，即客户给景鴻的钱）
	            pnr.setTTravelItineraries(new HashSet(trs));
	            
	            totalPrice+=pnr.getTotal();
	            totalDiscount+=pnr.getDiscountPrice();
	            
	            tpnrs.add(pnr);
			}
	        order.setTicketCount(Long.valueOf(tts.size()));//机票张数
	        order.setTotalPrice(totalPrice);
	        order.setDiscount(totalDiscount);
	        order.setTPnrs(new HashSet(tpnrs));
	        
	        //保存订单
	        comDao.executeSave(order);
	        
	        String orderNo=comDao.creatOrderNo(order.getOId().toString(),MyEnum.OrderTypeEnum.国内机票.getValue());
	        order.setOrderno(orderNo);
	        comDao.executeUpdate(order);
	        
	        //增加审批流逻辑  2018.2.2 wjt
	        Request_ListApprovalByTravller reqApprovallist=new Request_ListApprovalByTravller();
	        reqApprovallist.businessType=ApprovalBussType.机票.getOperateType();
	        reqApprovallist.approvalType=0;
	        List<ApproveTravller> travellers=new ArrayList<ApproveTravller>();
	        for (int i = 0; i < traveller.size(); i++) {
	            ApproveTravller travellercc=new ApproveTravller();
	            travellercc.id=traveller.get(i).getId().intValue();
	            travellercc.orgId=traveller.get(i).getOrgId().intValue();
	            Integer [] costIds={traveller.get(i).getCostId().intValue()}; 
	            travellercc.costIds=costIds;
	            travellers.add(travellercc);
            }
	        reqApprovallist.travellers=travellers;
	        
	        ListApprovalByTravller listApproval=new ListApprovalByTravller();
	        OutputResult<Response_ListApprovalByTravller, String>  Res_approvalByTravller=listApproval.ClientRequest(reqApprovallist, Response_ListApprovalByTravller.class);
	        List<Res_ApprovalByTravller> approveList=Res_approvalByTravller.getResultObj()==null?null:Res_approvalByTravller.getResultObj().data;
	        if(approveList.size()>0){
	            Request_CreateTTmcApproveProcess req=new Request_CreateTTmcApproveProcess();
	            req.approvalId=Long.valueOf(approveList.get(0).id);
	            TTmcApproveProcessInfo reqInfo=new TTmcApproveProcessInfo();
	            reqInfo.businessType=ApprovalBussType.机票.getOperateType();
//	            reqInfo.businessType=ApprovalBussType.不限.getOperateType();
	            reqInfo.orderid=order.getOId().intValue();
	            reqInfo.orderno=orderNo;
	            reqInfo.approvalType=1;
	            reqInfo.orgId=comOrg.getOrgid();
	            reqInfo.orgName=comOrg.getOrgname();
	            reqInfo.amount=new BigDecimal(order.getTotalPrice());
	            reqInfo.payType=Integer.valueOf(order.getPayMethod());
	            reqInfo.status=order.getOrderStatus();
	            reqInfo.travelType=Integer.valueOf(order.getTravelType());
	            reqInfo.bookTime=order.getSubscribeDate();
	            String airlineCompany="";
	            String cangweidetail="";
	            Iterator<TPnr> tpnrs_=order.getTPnrs().iterator();
	            while(tpnrs_.hasNext()){
	                TPnr nextPnr = tpnrs_.next();
	                airlineCompany+=nextPnr.getAirline()+" ";
	                cangweidetail+=nextPnr.getCangwei()+" ";
	            }
	            reqInfo.productName=airlineCompany.trim();
	            reqInfo.productDetail=cangweidetail;
	            
	            BookerInfo bookinfo=new BookerInfo();
	            bookinfo.setId(Long.valueOf(order.getSubscribeId()));
	            bookinfo.setEmail(order.getSubscribeEmail());
	            bookinfo.setMobile(order.getSubscribeTel());
	            bookinfo.setName(order.getSubscribeName());
	            reqInfo.booker=bookinfo;
	            reqInfo.traveller=traveller;
	            req.data=reqInfo;
	            cctmcProcess.ClientRequest(req,  Response_CreateTTmcApproveProcess.class);
	        }
            
	        if(overReasons!=null&&overReasons.size()>0){
	        	TExcessRelation tExcessRelation=null;
				TExcessRelationId tExcessRelationId=null;
	        	for (ReqData_BookAirTicket_OverReason reason : overReasons) {
	        		if(reason.type!=10){
	        			tExcessRelation=new TExcessRelation();
		        		tExcessRelationId=new TExcessRelationId();
		        		tExcessRelationId.setOid(new BigDecimal(order.getOId()));
		        		Iterator<TPnr> iterators=order.getTPnrs().iterator();
		        		while(iterators.hasNext()){
		        		    TPnr next = iterators.next();
		        		    String commentcity="";
		        		    if(next.getOriginCity().equals("PVG")||next.getOriginCity().equals("SHA")){
		        		        commentcity+="SHA";
		        		    }else if(next.getOriginCity().equals("NAY")||next.getOriginCity().equals("PEK")){
		        		        commentcity+="PEK";
		        		    }
		        		    if(next.getDestinationCity().equals("PVG")||next.getDestinationCity().equals("SHA")){
                                commentcity+="SHA";
                            }else if(next.getDestinationCity().equals("NAY")||next.getDestinationCity().equals("PEK")){
                                commentcity+="PEK";
                            }
                            if(reason.getFlightCity().equals(commentcity)){
                                tExcessRelationId.setSubid(new BigDecimal(next.getPnrId()));
                            }
		        		}
						tExcessRelationId.setType(new BigDecimal(reason.getType()));
						tExcessRelationId.setBusinessTypes(new BigDecimal(1));
						tExcessRelation.setId(tExcessRelationId);
						tExcessRelation.setReason(reason.getReason());
						comDao.executeSave(tExcessRelation);
	        		}
	        		
				}
	        }
	        
	        
	        String[] pnrs = null;
             String PNRDERAIL = Config.PNRDERAIL;  //外部传入，只针对客服。
            if ("true".equals(PNRDERAIL) &&  !"5660".equals(comOrg.getOrgid().toString())) {
                if (tpnrs.size() == 2) {
                    //先判断pnr行程数量
                    TPnr tpp = new TPnr();
                    String strOrg = "";   //第一段航程（包括往返联程）的出发城市   
                    String strDet = "strDet";   //第二段航程（包括往返联程）的目的城市   如果这两个城市一样，则说明是往返  北京-上海-哈尔滨  北京-上海-北京
                    String strA1 = "";    //第一段航程的航司
                    String strA2 = "strA2";  //第二段航程的航司
                    for (int i = 0; i < tpnrs.size(); i++) {
                        tpp = tpnrs.get(i);
                        if (i == 0) {strOrg = tpp.getOriginCity(); strA1 = tpp.getAirlineCompany(); }
                        if (i == 1) {strDet = tpp.getDestinationCity(); strA2 = tpp.getAirlineCompany(); }
                    }
            /*2016最新需求
            a、不管是谁预订也不管是联程还是往返不同的航司都需要生成不同的PNR，国内航司是不可以两个航司放在同一PNR里出票的
            b、如果是同一个航司不管是联程还是往返
            客服订页面提供按钮供客服选择，即在生成订单前根据客户需求生成一个还是两个PNR
            客户订生成一个PNR，但一定要有提醒，提醒客户此机票必须按顺序使用等等
            */
                    if (strOrg.equals(strDet)) { //第一段的出发城市与第二段的到达是一个城市，则是往返
                        //往返行程
                        if (strA1.equals(strA2)) {
                            //同一航司 不是客服预订，生成同一pnr
                                String p = bookOrderServiceImpl.createPNR(order); 
                                //String p = "OHSPNR";  //测试，暂不生成pnr
                                pnrs = new String[1]; 
                                pnrs[0] = p; 
                        } else {
                            //不同航司，不管谁预订，两个pnr
                            pnrs = bookOrderServiceImpl.getPNR(order);
                            //pnr = new String[2]; pnr[0] = "GO_PNR";  pnr[1] = "RE_PNR"; //测试代码，不生成pnr
                        }
                    } else {
                        //联程航班规则目前和往返一样，但为防止今后规则变更，代码分开写
                        if (strA1.equals(strA2)) {
                            //同一航司不是客服预订，生成同一pnr
                                String p = bookOrderServiceImpl.createPNR(order); 
                                pnrs = new String[1];
                                pnrs[0] = p;
                        } else {
                            //不同航司，不管谁预订，两个pnr
                            pnrs = bookOrderServiceImpl.getPNR(order);
                        }
                    }
                } else {  //单程的规则不变
                    pnrs = bookOrderServiceImpl.getPNR(order);
                }
                if (pnrs == null || pnrs[0] == null || pnrs[0].length() == 0 || 
                        (pnrs.length > 1 && (pnrs[1] == null || pnrs[1].length() == 0))) {
                	comDao.executeDelete(order);
                    rb.setOrderId(BookErrorTypeCode.航班异常订座失败.getValue());
                    return rb;
                } else if (pnrs[0].length() > 6 ||
                        (pnrs.length > 1 && pnrs[1].length() > 6)) {
                	comDao.executeDelete(order);
                	rb.setOrderId(BookErrorTypeCode.航班异常订座失败.getValue());
                    return rb;
                }
                String[] bigplait = bookOrderServiceImpl.getBigplait(order.getOId(), pnrs);
                if (bigplait    == null || bigplait.length == 0 || 
                    bigplait[0] == null || bigplait[0].length() == 0 || 
                    (bigplait.length > 1 && (bigplait[1] == null || bigplait[1].length() == 0))) {
                	comDao.executeDelete(order);
                	rb.setOrderId(BookErrorTypeCode.航班异常订座失败.getValue());
                    return rb;
                } else if (bigplait[0].indexOf("HK") == -1 && bigplait[0].indexOf("KK") == -1 
							 && bigplait[0].indexOf("DK") == -1 && bigplait[0].indexOf("TK") == -1  && bigplait[0].indexOf("KL") == -1) {
                	comDao.executeDelete(order);
                	rb.setOrderId(BookErrorTypeCode.航班异常订座失败.getValue());
                    return rb;
                } else if (bigplait.length > 1 && bigplait[1].indexOf("HK") == -1 && bigplait[1].indexOf("KK") == -1 
							 && bigplait[1].indexOf("DK") == -1 && bigplait[1].indexOf("TK") == -1  && bigplait[1].indexOf("KL") == -1) {
                	comDao.executeDelete(order);
                	rb.setOrderId(BookErrorTypeCode.航班异常订座失败.getValue());
                    return rb;
                }
                
                bookOrderServiceImpl.updateOrder(order, pnrs, bigplait);
            } else {
                pnrs = new String[] { "ASDFGH", "QWERTY" };
                String[] bigplait = new String[] { "ASD123_HK", "QWE456_HK" };
                bookOrderServiceImpl.updateOrder(order, pnrs, bigplait);
            }
	        //服务费收取
	        serviceFee.saveSubscribeServiceFee(order.getOId());
	        
	        Double balance =0d;
	        Double money=order.getTotalPrice()*-1;
	        Boolean b=true;
	        String flowStatus=Config.JPFK;
	        Map<String,String> map_1 = new HashMap<String,String>();
	        
	        if("4".equals(order.getPayMethod()) || "1".equals(order.getPayMethod())){
	        	
	        	if(comOrg!=null){
		        	//balance=balancePay.getOrgBalance(comOrg.getOrgid());
		        	balance=balancePay.checkBalance(comOrg.getOrgid(), order.getPayMethod());
		        }
		        
		        if((money<0&&balance>=money*-1)||money >=0){//余额大于当前支付金额  或者 为负数余额（退票或改签）
		        	if("4".equals(order.getPayMethod())){
		        		CaiyunResp cr =null;
						AjaxResp re = cps.isUserBelongsToUban360(Long.parseLong(order.getSubscribeId()));
						
						Request_GetOrderDetail orderPara=new Request_GetOrderDetail();
						orderPara.setOrderID(order.getOId());
						//Response_GetOrderDetail orderDetails=getOrderService.getOrderDetail(orderPara);
						RespData_GetOrderDetail orderDetails = ConvertorFactory.instance().getConvertor(TOrder.class, RespData_GetOrderDetail.class).convert(order);
						String orderDetailStr=etuf.v1_0.common.JSON.ObjectToJson(orderDetails);
						
						if(re.getStatus()==0){
							if(Integer.parseInt(flowStatus)>0){
								 cr= cps.prePay(Long.parseLong(order.getSubscribeId()),order.getOrderno().toString(),money*-1,orderDetailStr);
							}else{
								 cr= cps.payRefund(Long.parseLong(order.getSubscribeId()),order.getOrderno().toString(),money,orderDetailStr);
							}
							if(cr.getSuccess()==true){
								balancePay.setMap(map_1, cr.getStatus().toString(),cr.getMessage());
								b=true;
							}else{
								balancePay.setMap(map_1, cr.getStatus().toString(),cr.getMessage());
								b=false;
								
								rb.setOrderId(BookErrorTypeCode.讯盟返回错误信息.getValue());
	 							rb.setErrorStr(map_1.get("message"));
							}
							
						}
		        	}
					
				}else{
					balancePay.setMap(map_1,"1", "余额不足！");
					b=false;
					
					rb.setOrderId(BookErrorTypeCode.讯盟返回错误信息.getValue());
					rb.setErrorStr(map_1.get("message"));
				}
	        }
	        
	        
	        //若支付方式为预付款支付 ，则首先扣除企业预付款余额，扣除流程正常，进行后续操作，否则，删除订单（级联删除pnr。）
	         if(b){
	        	 for(TTravelItinerary tti:tts){
	 	   		    Boolean flag = true;
	 	            Double fwf=order.getTotalServiceFee()/order.getTicketCount();
	 	            Double bx=tti.getAssuranceNum()*tti.getAssurancePrice();
	 	            Double tit=(tti.getFueltax()+tti.getTaxPrice()+tti.getTicketPrice()+fwf+bx);
	 	            String[] param=new String[]{"weixin",tacuser.getUserid().toString(),tacuser.getUsername()};
	 	            Map<String, String> map=null;
	 	            if("4".equals(order.getPayMethod()) || "1".equals(order.getPayMethod())){
	 	                //Integer result = balancePay.balancePay(Config.JPFK, tit*-1, order.getOrderno(), "", comOrg.getOrgid(), tacorg.getOrgname(),param);
	 	            	map = balancePay.balancePay(Config.JPFK, tit*-1, order.getOrderno(), "", comOrg.getOrgid(), tacorg.getOrgname(),order.getPayMethod(),param);
	 	            	if(!"0".equals(map.get("status"))){
	 	                    flag = false;
	 	                }
	 	            }
	 	            if(!flag){
	 	                //comDao.deleteTOrder(order.getOId());
	 	            	
	 	            	Integer status=comDao.deleteOrder(1,order.getOId(),map.get("message"));
	 	            		
	 	            	if(0==status){
	 	            		AjaxResp re = cps.isUserBelongsToUban360(Long.parseLong(order.getSubscribeId()));
	 						if(re.getStatus()==0){
	 							rb.setOrderId(BookErrorTypeCode.讯盟返回错误信息.getValue());
	 							//ErrShareUtil.set(map.get("message"));
	 							rb.setErrorStr(map.get("message"));
	 							
	 						}else{
	 							rb.setOrderId(BookErrorTypeCode.预付款余额不足.getValue());
	 						}
	 	            		
	 	            	}else{
	 	            		rb.setOrderId(BookErrorTypeCode.提交订单失败.getValue());	
	 	            	}
	 	            	
	 	            	return rb;
	 	            }
	 	       	 } 
	         }else{
	        	 Integer status=comDao.deleteOrder(1,order.getOId(),map_1.get("message"));
	        	 if(0==status){
	            		AjaxResp re = cps.isUserBelongsToUban360(Long.parseLong(order.getSubscribeId()));
						if(re.getStatus()==0){
							rb.setOrderId(BookErrorTypeCode.讯盟返回错误信息.getValue());
							//ErrShareUtil.set(map.get("message"));
							rb.setErrorStr(map_1.get("message"));
							
						}else{
							rb.setOrderId(BookErrorTypeCode.预付款余额不足.getValue());
						}
	            		
	            	}else{
	            		rb.setOrderId(BookErrorTypeCode.提交订单失败.getValue());	
	            	}
	            	
	            	return rb;
	         }
	       	 
	        
	       	String tlist = "";//乘机人邮箱集合
	       	String body="";
	       	List<TAcUser> emails=null;
	       	if(Config.TURNON.equals("TRUE")){
                for (TTravelItinerary tt : tts) {
                    tlist += tt.getPassengerEmail()+",";
                    /*黄玄添加  判断乘机人是否已录入系统,否则非客维和销售发邮件*/
                    if("1".equals(tt.getPassengerType())){
                    	tacuser=comDao.getIsTacuser(tt);
                        if(tacuser==null){
                        	body+="乘机人姓名:"+tt.getPassengerName()+";  乘机人"+comDao.getNameByIdType(tt.getPassengerIdtype())+":"
                        		 +tt.getPassengerId()+";  乘机人手机号:"+tt.getPassengerTel()+";";
                        	if(!"".equals(tt.getPassengerEmail())){
                        		body+="  乘机人邮箱:"+tt.getPassengerEmail()+";";
                        	}
                        	body+="  乘机人所属公司:"+order.getCompanyName()+";  所属部门:"+tt.getOrgid()+"</p>";
                        }
                    }
                }
                if(!"".equals(body)&&body!=null){
                	TAcOrg torg=comDao.queryForEntity(Long.valueOf("121"), TAcOrg.class);//暂时写死(取本公司的客服和销售)
	                emails=comDao.getEmail(torg);
	                body+="上述乘机人暂未录入系统,请尽快录入系统";
	                //SendEmailUtil sendEmailUtil = new SendEmailUtil();
	                for (TAcUser tuser : emails) {
	                	if(tuser.getEmail()!=null&&!"".equals(tuser.getEmail())){
	                		//sendEmailUtil.autoSendEmail1("", "1", "2", tuser.getEmail(), null, "乘机人信息录入提醒",body);
	                	}
					}
                }
            }
	       	 
	        List<MailAndNoteMsg> mlist = new ArrayList<MailAndNoteMsg>();
	        if(para.data.contacts!=null&&para.data.contacts.size()>0){
	            MailAndNoteMsg mailAndNoteMsg =null;
	            for (ReqData_BookAirTicket_Contact ct : para.data.contacts) {
	                mailAndNoteMsg = new MailAndNoteMsg();
	                mailAndNoteMsg.setRecevier(ct.getName());
	                mailAndNoteMsg.setContact(ct.getMobile());
	                mailAndNoteMsg.setMail(ct.getEmail());
	                mlist.add(mailAndNoteMsg);
	            }
	        }
	       
	      //--------------将通知人插入通知人表中--------
	        List<TNotifyPartyInformation> listTZR=new ArrayList<TNotifyPartyInformation>();
	         TNotifyPartyInformation tp=null;
	         if(para.data.contacts!=null&&para.data.contacts.size()>0){
	        	 for (ReqData_BookAirTicket_Contact ct : para.data.contacts) {
		        	 tp=new TNotifyPartyInformation();
		        	 tp.setSendTime(new Date());
		             tp.setPosition("国内通知人");
		             tp.setNotifyName(ct.getName());//通知人姓名
		             tp.setPassengerTel(ct.getMobile());//通知人电话
		             tp.setMail(ct.getEmail());//通知人邮箱
		             tp.setOrderid(Integer.valueOf(order.getOId().toString()));//
		             tp.setStatus("0");
		             tp.setNationalityStatus("0");
		             listTZR.add(tp);
		             comDao.executeSave(tp); 
				}
		         
	         }
	         if(checkUser!=null){
	        	 TNotifyPartyInformation t1=new TNotifyPartyInformation();
		            t1.setSendTime(new Date());
		            t1.setPosition("国内审核人");
		            t1.setNotifyName(order.getCheckmen());//审核人姓名
		            t1.setPassengerTel(order.getChecktel());//审核人电话
		            t1.setMail(order.getCheckemail());//审核人邮箱
		            t1.setOrderid(Integer.valueOf(order.getOId().toString()));//
		            t1.setStatus("0");
		            t1.setNationalityStatus("0");
		            comDao.executeSave(t1); 
	         }
	       
	            
	           TComment tComment=new TComment();
	           tComment.setPassengerName("系统");
	           tComment.setCommentMsg(order.getSubscribeName()+"预订");
	           tComment.setCommentDate(sysDate.toString());
	           tComment.setOId(order.getOId());
	           comDao.executeSave(tComment); 
	           
	           if("true".equals(Config.TURNON)){
	        	   SendMessages sendMessages=new SendMessages();
	        	   String status="";
	        	   if("1".equals(order.getOrderStatus())){//给审核人发送信息
	        		   status =sendMessages.SendCheckMen(order);
	        	   }
	        	   if("0".equals(status)){
	        		comDao.deleteTOrder(order.getOId());
	        		rb.setOrderId(BookErrorTypeCode.提交订单失败.getValue());
	       			return rb;
	        	   }
	        	   
	        	   if(StringUtil.isNotEmpty(order.getSubscribeTel())){
	        		   status =sendMessages.SendSubscribeMen(order);//给预定人发送信息
	        	   }
	        	   if("0".equals(status)){
		        		comDao.deleteTOrder(order.getOId());
		        		rb.setOrderId(BookErrorTypeCode.提交订单失败.getValue());
		       			return rb;
		           }
	        	   
	           }
	           
	           
	           rb.setOrderId(Integer.valueOf(order.getOId().toString()));
	           rb.setOrderNo(order.getOrderno());
	           rb.setAmount(order.getTotalPrice());
	           rb.setServiceFee(order.getTotalServiceFee());
	           rb.setOrderStatus(order.getOrderStatus());
	           
	           //不用审核的企业、并且支付方式是月结、预付的自动出票判断
	           if("1".equals(order.getPayMethod()) || "4".equals(order.getPayMethod())){ //付款方式：1-公司月结，2-个人支付，3-公司现结, 4-预付款
    	           if ("0".equals(comOrg.getVerify())) {//  是否需要审核  1 需要  0不需要
    	             //自动出票开关 
                       String autoMaticFlag=autoTicketService.getAutomaticTicket();
                       if(StringUtils.isNotEmpty(autoMaticFlag) && autoMaticFlag.equals("open")){
                           //自动出票-无审核（月结或预付款）
                           autoTicketService.addFromPay(order.getOrderno(), "3");
                       }
    	           }
	           }
	           return rb;
		} catch (Exception e) {
			e.printStackTrace();
			comDao.executeDelete(order);
			e.printStackTrace();
			String title="";
			for (int ii = 0; ii < e.getStackTrace().length; ii++) {
                title+="<p>"+e.getStackTrace()[ii].toString()+"</p>";
                
            }
            //SendEmailUtil sendEmailUtil = new SendEmailUtil();
            InetAddress address = null;
			try {
				address = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}//获取的是本地的IP地址
            String laiyuan="";
            if(order!=null){
            	laiyuan = comDao.getIsWhere(order.getIsWhere());
            }
            //if(address.toString().indexOf("172.16.249")<0){
                //sendEmailUtil.autoSendEmail1("", "1", "2", "jishubu.list@caissa.com.cn", null, "ip:"+address+" 国内机票提交订单(订单号："+order.getOrderno()+"，来源："+laiyuan+")异常:",title);
                //sendEmailUtil.autoSendEmail1("", "1", "2", "421089712@qq.com", null, "ip:"+address+" 国内机票提交订单(订单号："+order.getOrderno()+"，来源："+laiyuan+")异常:",title);
            //}
           
			rb.setOrderId(BookErrorTypeCode.提交订单失败.getValue());
			return rb;
		}
		// TODO Auto-generated method stub

	}
	
	
	
	
	/**
	 * 根据参数获取航班号
	 * @param flightNo
	 * 			航班号
	 * @param orgcity
	 * 			起飞城市
	 * @param detcity
	 * 			目的城市
	 * @param date
	 * 			起飞日期
	 * @return
	 */
	private List<TCangwei> getCangweiByPara(String flightNo,String orgcity,String detcity,String date){
		LinkedHashMap<String, String> conditions = new  LinkedHashMap<>();
		Object[] parameterArray = new Object[]{flightNo,orgcity,detcity,date};
		conditions.put("ariline", "=");
		conditions.put("orgcity", "=");
		conditions.put("detcity", "=");
		conditions.put("to_char(starttime,'yyyy-mm-dd')", "=");
		List<TCangwei> cws = comDao.queryForList(TCangwei.class, conditions, parameterArray);//获取仓位
		return cws;
	}
	
	private void getStopsByPara(){
		
	}
	
	
	
    /**
     * 根据价格排序，相同航班，相同起飞日期的分组排序
     * 
     * @param lst
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private List<DispplayTrip> sortprice2(List<DispplayTrip> lst) {
        // 用航班号加起飞日期做主键，将传入的list分组，分组之后再进行排序
        Map<String, List<DispplayTrip>> sortmap = new HashMap<String, List<DispplayTrip>>();
        String strKey = "";
        List<DispplayTrip> ltemp = new ArrayList<DispplayTrip>();
        List<DispplayTrip> lreturn = new ArrayList<DispplayTrip>();

        Map<String, String> map = new HashMap<String, String>();
        for (DispplayTrip tdt : lst) {
            // add by yl,用以区分一个城市两个机场的情况
            if (!"".equals(tdt.getOrgcity())) {
                map.put(tdt.getAirline(), tdt.getOrgcity() + "," + tdt.getDetcity());
            }
            // 组合key
            strKey = tdt.getAirline() + "_" + DateUtil.date2Str(tdt.getStartdate(), "yyyyMMdd");
//            strKey = tdt.getAirline() + "_" + DateUtil.date2Str(tdt.getStartdate(), "yyyyMMdd"); // 查看一下是否已有相应key对应的
            // 查看一下是否已有相应key对应的
            ltemp = sortmap.get(strKey);
//            tdt.setOrgcity("");
//            tdt.setDetcity("");
            if (ltemp != null) { // 如果找到
                ltemp.add(tdt); // list中继续累加该对象
            } else { // 如果没找到
                ltemp = new ArrayList<DispplayTrip>(); // 初始化list
                ltemp.add(tdt);
            }
            sortmap.put(strKey, ltemp); // 替换现在的
        }
        //DispplayTrip tdt = new DispplayTrip();
        Iterator itEntry = sortmap.entrySet().iterator();
        Entry entry = null;
        // 遍历map中的对象
        while (itEntry.hasNext()) {
            entry = (Entry) itEntry.next();
            strKey = (String) entry.getKey();
            // 取出存放的list，对此list中的元素进行排序
            ltemp = (List<DispplayTrip>) entry.getValue();
            ltemp = sortprice(ltemp);
            // 给list的第一个元素设置上城市
//            tdt = ltemp.get(0);
/*            String city = map.get(tdt.getAirline());
            if (city != null) {
            	tdt.setDetcity(city.split(",")[1]);
            	tdt.setOrgcity(city.split(",")[0]);
            }
*/            //ltemp.set(0, tdt);
            for (DispplayTrip tempdt : ltemp) {
            	String city = map.get(tempdt.getAirline());
            	if (city != null) {
            		tempdt.setDetcity(city.split(",")[1]);
            		tempdt.setOrgcity(city.split(",")[0]);
                }
                lreturn.add(tempdt);
            }
        }
        return lreturn;
    }
    
    
    /**
     * 冒泡排序根据价格进行排序
     * 
     * @param lst
     * @return
     */
    private List<DispplayTrip> sortprice(List<DispplayTrip> lst) {
        DispplayTrip tempEntity = new DispplayTrip();
        DispplayTrip tempjEntity = new DispplayTrip();
        DispplayTrip tempj1Entity = new DispplayTrip();
        for (int icount = 0; icount < lst.size() - 1; icount++) { // 最多做n-1趟排序
            for (int jcount = 0; jcount < lst.size() - icount - 1; jcount++) { // 对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
                tempjEntity = lst.get(jcount);
                tempj1Entity = lst.get(jcount + 1);
                if (tempjEntity.getPrice().doubleValue() > tempj1Entity.getPrice().doubleValue()) { // 把大的值交换到后面
                    tempEntity = tempjEntity;
                    tempjEntity = tempj1Entity;
                    lst.set(jcount, tempj1Entity);
                    tempj1Entity = tempEntity;
                    lst.set(jcount + 1, tempEntity);
                }
            }
        }
        return lst;
    }
	
    /**
     * 按照仓位过滤list
     * @param list List<DispplayTrip>类型
     * @param cabinType 仓位类型
     * @return
     */
    private List<DispplayTrip> filterByCabinType(List<DispplayTrip> list,Integer cabinType){
    	if(null!=cabinType && CabinType.Unlimited.getCode()!=cabinType){
    		List<CompanyFlightCabinTypeCode> clist = new ArrayList<>();
    		List<Object[]> qList = comDao.queryForList("select classtype as classtype,aircompany as aircompany,classcode as classcode from TAcClass group by classtype,aircompany,classcode");
    		if(null!=qList && qList.size()>0){
    			for(Object[] l:qList){
    				Integer classtype = (Integer) l[0];
    				String aircompany = (String) l[1];
    				String classcode = (String) l[2];
    				CompanyFlightCabinTypeCode cfct = new CompanyFlightCabinTypeCode();
    				cfct.setClasstype(classtype);
    				cfct.setAircompany(aircompany);
    				cfct.setClasscode(classcode);
    				clist.add(cfct);
    			}
    		}
    		Iterator<DispplayTrip> iterator = list.iterator();
    		while (iterator.hasNext()) {
    			DispplayTrip ele = (DispplayTrip) iterator.next();
    			String airline = ele.getAirline().substring(0,2);
    			String cangwei = ele.getCangwei();
    			if(CabinType.BusinessAndFirstClass.getCode()==cabinType){
    				if(!(clist.contains(new CompanyFlightCabinTypeCode(2,airline,cangwei)) || clist.contains(new CompanyFlightCabinTypeCode(3,airline,cangwei)))){
    					iterator.remove();
    					continue;
    				}
    			}
    			if(CabinType.EconomyClass.getCode()==cabinType){
    				if(!clist.contains(new CompanyFlightCabinTypeCode(CabinType.EconomyClass.getCode(),airline,cangwei))){
    					iterator.remove();
    					continue;
    				}
    			}
    			if(CabinType.BusinessClass.getCode()==cabinType){
    				if(!clist.contains(new CompanyFlightCabinTypeCode(CabinType.BusinessClass.getCode(),airline,cangwei))){
    					iterator.remove();
    					continue;
    				}
    			}
    			if(CabinType.FirstClass.getCode()==cabinType){
    				if(!clist.contains(new CompanyFlightCabinTypeCode(CabinType.FirstClass.getCode(),airline,cangwei))){
    					iterator.remove();
    					continue;
    				}
    			}
    		}
    	}
    	return list;
    }
	
    /**
     * 获取mapkey
     * @param trip
     * @return  MU1911_PEK_SHA_2017-05-01_2017-05-01
     */
    @SuppressWarnings("unused")
	private String getMapKey(DispplayTrip trip){
    	String result = null;
    	String airline = trip.getAirline();//航班号
		String orgcity = trip.getOrgcity();//起飞城市
		String detcity = trip.getDetcity();//到达城市
		Date startdata = trip.getStartdate();//起飞日期时间
		Date arrdate = trip.getArrvidate();//到达日期时间
		if(!StringUtil.isEmpty(airline)&&!StringUtil.isEmpty(orgcity)&&!StringUtil.isEmpty(detcity)&&null!=startdata&&null!=arrdate){
			result = airline + "_" + orgcity + "_" + detcity + "_" + DateUtil.SDF_yyyyOMMOdd.format(startdata) + "_" + DateUtil.SDF_yyyyOMMOdd.format(arrdate);
		}
    	return result;
    }
    
    /**
     * 获取mapkey
     * @param trip
     * @return  MU1911_PEK_SHA_2017-05-01_2017-05-01
     */
    private String getMapKey(RespData_GetFlightList trip){
    	String result = null;
    	String airline = trip.getFlightNo();//航班号
    	String orgcity = trip.getDepAirport();//起飞城市
    	String detcity = trip.getArrAirport();//到达城市
    	Date startdate = trip.getDepDateTime();//起飞日期时间
    	Date arrdate = trip.getArrDateTime();//到达日期时间
    	if(!StringUtil.isEmpty(airline)&&!StringUtil.isEmpty(orgcity)&&!StringUtil.isEmpty(detcity)&&null!=startdate&&null!=arrdate){
    		result = airline + "_" + orgcity + "_" + detcity + "_" + DateUtil.SDF_yyyyOMMOdd.format(startdate) + "_" + DateUtil.SDF_yyyyOMMOdd.format(arrdate);
    	}
    	return result;
    }
    
    private String getFlightKey(DispplayTrip trip){
    	String result = null;
    	String orgcity = trip.getOrgcity();//起飞城市
		String detcity = trip.getDetcity();//到达城市
		Date startdata = trip.getStartdate();//起飞日期时间
		if(StringUtils.isNoneEmpty(orgcity) && StringUtils.isNotEmpty(detcity) && null!=startdata){
			result = orgcity + "_" + detcity + "_" + DateUtil.SDF_yyyyOMMOdd.format(startdata);
		}
    	return result;
    }
    
    private String getFlightKey(RespData_GetFlightList trip){
    	String result = null;
    	String orgcity = trip.getDepAirport();//起飞城市
    	String detcity = trip.getArrAirport();//到达城市
    	Date startdate = trip.getDepDateTime();//起飞日期时间
		if(StringUtils.isNoneEmpty(orgcity) && StringUtils.isNotEmpty(detcity) && null!=startdate){
			result = orgcity + "_" + detcity + "_" + DateUtil.SDF_yyyyOMMOdd.format(startdate);
		}
    	return result;
    }
    
    

	@Override
	public PassengerIdTypeAndIdNum getBookTicketPassengerIdInfoByIdAndType(
			Integer id, Integer type) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getMapKey(ReqData_GetFlightList para){
		String result = "";
		if(null!=para){
			
		}
		return result;
	}


	public static void main(String[] args) throws ClassNotFoundException {
		Class<List<DispplayTrip>> forName = (Class<List<DispplayTrip>>) Class.forName("List<DispplayTrip>");
		System.out.println(forName.getSimpleName());
	}

	/**
     * 改期机票数据列表查询
     * @param para
     * @return
     */
    @Override
    public List<RespData_GetReschedueFlightList> getReschedueFlight(Request_GetReschedueFlightList para) {
        List<RespData_GetReschedueFlightList> result = null;
        List<DispplayTrip> trip = rimservice.getIBEData(para);//模拟从rimservice得到数据
        result = ListObjectConverter.convert(trip, result, ConvertorFactory.instance().getConvertor(DispplayTrip.class, RespData_GetReschedueFlightList.class));
        return result;
    }
}
