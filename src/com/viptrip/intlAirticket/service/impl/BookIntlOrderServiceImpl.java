package com.viptrip.intlAirticket.service.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.BookErrorTypeCode;
import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.intlAirticket.common.IntlEnum;
import com.viptrip.intlAirticket.entity.TIntlAirTicketItinerary;
import com.viptrip.intlAirticket.entity.TIntlComment;
import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.entity.TIntlTicketTravel;
import com.viptrip.intlAirticket.entity.TIntlTravelItinerary;
import com.viptrip.intlAirticket.model.Request_BookIntlOrder;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderList;
import com.viptrip.intlAirticket.model.Response_BookIntlOrder;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderList;
import com.viptrip.intlAirticket.model.flightModels.BookIntlOrderParam;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderListAirline;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderListCarrier;
import com.viptrip.intlAirticket.model.flightModels.IntlOrderListInfo;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_IntlOrderListInfo;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo_Informer;
import com.viptrip.intlAirticket.model.flightModels.Req_Data_OrderInfo_Informer_Passenger;
import com.viptrip.intlAirticket.model.flightModels.RespData_BookIntlOrder_OrderInfo;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_GetIntlOrderList;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_IntlOrderDetail;
import com.viptrip.intlAirticket.service.BookIntlOrderService;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.PageParam;
import com.viptrip.util.StringUtil;
import com.viptrip.wechat.config.Config;
import com.viptrip.wetrip.dao.TAcOrgDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.dao.ext.TestDaoExt;
import com.viptrip.wetrip.entity.TAcInsurance;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TNotifyPartyInformation;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.service.BalancePay;
import com.viptrip.wetrip.service.IComService;
import com.viptrip.wetrip.service.ServiceFee;
import com.viptrip.yeego.yeego;
import com.viptrip.yeego.intlairticket.model.AirRulesRQ;
import com.viptrip.yeego.intlairticket.model.Filter_QueryWebFlightsIntl_1_0;
import com.viptrip.yeego.intlairticket.model.Request_GetAirRulesI_1_0;
import com.viptrip.yeego.intlairticket.model.Request_GetFlightPriceI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_GetAirRulesI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;

@Service
@Transactional
public class BookIntlOrderServiceImpl implements BookIntlOrderService {
    
    @Resource
    private ComDao comDao;
    
    @Resource
    private TAcOrgDao tacOrgDao;
    
    @Resource
    private IComService iComService;
    
    @Resource
    private FlightIntlService flightIntlService;
    
    @Resource
    private ServiceFee serviceFee;
    
    @Resource
    private BalancePay balancePay;
    
    @Resource
    private TestDaoExt extDao;//扩展testdao
    
    @Override
    public Response_BookIntlOrder goBookIntlOrder(Request_BookIntlOrder para) {
        Response_BookIntlOrder rbo=new Response_BookIntlOrder();
        Req_Data_OrderInfo req_OrderInfo=para.getData();
        String mapKey=req_OrderInfo.getMapKey(); // 若是单程传入单程返回的mapKey,若是往返行程,传入返程返回的mapKey
        String cangwei = req_OrderInfo.getCangwei();
        try {
            Long userId=para.userId;
            long checkManId=req_OrderInfo.getCheckManId(); // 审核人id
            TAcUser tacuser=comDao.queryForEntity(userId,TAcUser.class);//当前登陆人信息
            if(tacuser==null){
                rbo.status=BookErrorTypeCode.登陆人id不对.getValue();
                return rbo;
            }
//            TAcUser checkUser=comDao.queryForEntity(Long.valueOf(checkManId),TAcUser.class);//当前审核人
           
            TAcOrg tacorg_sub=comDao.queryForEntity(tacuser.getOrgid(),TAcOrg.class);
            TAcOrg tacorg=comDao.queryForEntity(Long.valueOf(tacorg_sub.getCompany()),TAcOrg.class);
//            TAcOrg comOrg=comDao.queryForEntity(Long.valueOf(tacorg.getCompany()),TAcOrg.class);
            
            
            if(StringUtils.isEmpty(cangwei)){
                rbo.status=BookErrorTypeCode.参数不全.getValue();
                return rbo;
            }
            //校验往返行程未查返程直接调用接口
            String[] splits = mapKey.split("\\|");
            if (splits == null || splits.length == 0) {
                rbo.status=Constant.Code_Failed;
                System.out.println("无效的mapKey--" + mapKey);
                return rbo;
            }else{
                String key4GetFlight = splits[0];
                boolean valid = true;
                boolean isRTTrip = CommonMethodUtils.isRTTravleType(key4GetFlight);
                if (isRTTrip) {
                    valid = splits[1].contains("*");
                }
                if (!valid) {
                    rbo.status=Constant.Code_Failed;
                   // rbo.setStatus("往返行程，必须获取返程后再调用该接口。" );
                    return rbo;
                }
            }
            
            
            String verify=req_OrderInfo.getVerify();//是否需要审核  1 需要 0不需要

            int payMethod=req_OrderInfo.getPayMethod(); // 1-公司月结，2-在线支付，3-线下支付，4-预付款支付

            int travelType=req_OrderInfo.getTravelType(); // 出行类型：0-因公出行，1-因私出行

           
            FlightResult  result =  this.getFlightResults(userId, mapKey, IntlAitTicketGetTargetFType.待确认类型下单,cangwei);
            OutputResult<Response_QueryWebFlightsI_1_0, String>  rsq=null;
            if (result.succeed) {

                String pType=CommonMethodUtils.getPassengerType(result.baseTripKey);
                CabinAndPrice cp=flightIntlService.getFlightCabinAndPrice(result.resultObj,
                        result.targetFs.iterator().next().getKey(),pType);//得到的是价钱对象
                String cangweiStr="";
                if(!cangwei.equals(cp.getCangwei())){//需要查更多舱位了
                     rsq=flightIntlService.getIntlCabinList(result);
                     if (rsq.code == Constant.Code_Succeed) {
                        if(rsq.getResultObj()!=null){
                            cp= flightIntlService.getFlightCabinAndPrice(rsq.getResultObj(), "F1",pType,cangwei);
                            if(cp.getCangwei()==null || "".equals(cp.getCangwei())){
//                                returnError("提未查询到指定的舱位价格，请重新查询航班后再提交订单。");
//                                return;
                                rbo.status=Constant.Code_Failed;
                                System.out.println(BookErrorTypeCode.航班异常订座失败.toString());
                                return rbo;
                            }else{
                                cangweiStr= CommonMethodUtils.getCabinTypeName(rsq.getResultObj(),cp.getCangweiType());
                            }
                        }else{
//                            returnError("未查询到指定的舱位价格，请重新查询航班后再提交订单。");
//                            return;
                            rbo.status=Constant.Code_Failed;
                            System.out.println(BookErrorTypeCode.航班异常订座失败.toString());
                            return rbo;
                        }
                    }else{
//                        returnError("错误代码：201701181540。错误信息："+rsq.result);
//                        return;
                        rbo.status=Constant.Code_Failed;
                        System.out.println(BookErrorTypeCode.航班异常订座失败.toString());
                        return rbo;
                    }
                     /* */
                }else{
                    cangweiStr= CommonMethodUtils.getCabinTypeName(result.resultObj,cp.getCangweiType());
                }
                ArrayList<String> rules = getTicketRule (result,cangwei);//退改签规则，0：去程  1：回程
                
//                    OutputResult<Response_QueryWebFlightsI_1_0,String> or=null;
//                    List<Flight> list = new ArrayList<Flight>();
                    Entry<String, Hashtable<String, Object[]>> targetEntry = result.targetFs.iterator().next();
                    
                    Object[] targetH_PsgType = null;// ADT对应的内容
                    Request_GetFlightPriceI_1_0 rgfp = new Request_GetFlightPriceI_1_0();
                    List<TIntlAirTicketItinerary> flightList = new ArrayList<TIntlAirTicketItinerary>();
                    
                    TIntlAirTicketItinerary flight = null;
                    int goFlightCount=getGoFlightCount(targetEntry);//去程航班数量
                    for (Entry<String, Object[]> et : targetEntry.getValue().entrySet()) {
                        Object[] trip = et.getValue();// S1、S2对应的value
                        
                        int a1=0;
                        int a2=0;
                        // 查政策的时候，不需要航班里的详情，中转的也不需要传中转的相关信息
                        Object[] segments = ((ArrayList) trip[1]).toArray();
                        for (int i = 0; i < segments.length; i++) {
                            
                            Object[] airlines= ((ArrayList) segments[i]).toArray();
                            
                            flight = new TIntlAirTicketItinerary();
                            flight.setIntlFlightNumber(airlines[1].toString()); // 航班号
                            if("ARNK".equals(airlines[1].toString())){
                                flight.setIntlAirlineCompany(airlines[1].toString());// 航空公司
                            }else{
                                flight.setIntlAirlineCompany(airlines[1].toString().substring(0, 2));// 航空公司
                            }
                            flight.setIntlDepartureAirport(airlines[2].toString());// 出发机场
                            flight.setIntlReachAirport(airlines[3].toString());// 目的机场
                            flight.setIntlDepartureCity(airlines[2].toString());// 出发城市
                            flight.setIntlReachCity(airlines[3].toString());// 目的城市
                            flight.setIntlDepartureTerminal(airlines[11].toString());// 出发站楼
                            flight.setIntlReachTerminal(airlines[12].toString());// 达到站楼
                            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            flight.setIntlDepartureTime(sdf.parse(airlines[4].toString()+" "+airlines[5].toString()));// 出发日期和时刻
                            flight.setIntlReachTime(sdf.parse(airlines[6].toString()+" "+airlines[7].toString())); // 到达日期和时刻

                            
                            flight.setIntlPnr("AAAAAA");//暂时写死pnr
                            
                            
                            /*intlClassType = intlAirOrderBookManager.getIntlClassType(flight.getIntlAirlineCompany(), flight.getIntlCangwei());
                            if(!"".equals(intlClassType)&&intlClassType!=null){
                                flight.setIntlClassType(intlClassType);
                            }*/
                            
                            if ("S1".equals(et.getKey())) {
                                flight.setFlightType(1);//去程
                                flight.setEndorserule(rules.get(0));//去程退改签
                                flight.setIntlCangwei(cp.getCangwei().split("\\/")[0].split(",")[a1]);// 舱位
                                a1++;
                                flight.setIntlClassType(getCangweiStr(
                                        "".equals(cangweiStr)?"": cangweiStr.split("\\/")[0]));
                                if(segments.length>1){
                                    flight.setTransferType(Integer.valueOf(1));//去程中转
                                }
                                flight.setIntlSegmentNo(i+1);// 航段序号
                            } else if ("S2".equals(et.getKey())) {
                                flight.setFlightType(2);//返程
                                flight.setEndorserule(rules.get(1));//返程退改签
                                flight.setIntlCangwei(cp.getCangwei().split("\\/")[1].split(",")[a2]);// 舱位
                                a2++;
                                flight.setIntlClassType(this.getCangweiStr(
                                        "".equals(cangweiStr)?"": cangweiStr.split("\\/")[1]));
                                if(segments.length>1){
                                    flight.setTransferType(Integer.valueOf(2));//返程中转
                                }
                                flight.setIntlSegmentNo(i+1+goFlightCount);// 航段序号
                            }
                            flight.setIntlVoyageStatus("0");// 航段类型，0原始，1改期
                            
                            flightList.add(flight);
                        }
                    }
                
                    
//            JSONArray chenjirens = (JSONArray) jsonObject.get("passengerList");
//            List<IntlPassengerInfo> passengers = (List)JSONArray.toCollection(chenjirens, IntlPassengerInfo.class);
            
//            JSONArray informerList = (JSONArray) jsonObject.get("informerList");
//            List<InformerList> tongzhirens = (List)JSONArray.toCollection(chenjirens, InformerList.class);
            
          
            List<Req_Data_OrderInfo_Informer_Passenger> passengers=req_OrderInfo.getPassengerList(); // 乘机人信息
            //try {
                TAcUser tAcUser = tacuser;
//                String p_orgid = appLoginManager.get2Orgid(tAcUser.getOrgid() + "");//
//                TAcOrg tacorg = tacorg;//(TAcOrg)appLoginManager.findById(TAcOrg.class, Long.parseLong(p_orgid));
                
                List<TNotifyPartyInformation> nlist = new ArrayList<TNotifyPartyInformation>();
                
                // ============================订单信息============================================
                TIntlOrder addOrder = new TIntlOrder();
                addOrder.setTravelType(String.valueOf(travelType));
                addOrder.setCompanyOfAffiliationId(tacorg.getOrgid().toString());// 订单所属公司名称id
                addOrder.setCompany(tacorg.getOrgname());
                addOrder.setIntlSubscribeName(tAcUser.getUsername());
                addOrder.setIntlSubscribeId(tAcUser.getUserid().toString()); 
                addOrder.setIntlSubscribeDate(DateUtil.getSysDate());
                addOrder.setIsExport("3");//1 补录散客  2补录团队  3 线上预订
                addOrder.setIsWhere("5");//订单区分: 订单区分: 0:pc端  1:app端   2:网站  4.宝库  5:微信
                addOrder.setIntlPayMethod(String.valueOf(payMethod));
                
                TAcUser checkInfo=null;
                if (1==payMethod || 4==payMethod) {// 公司月结 预付款支付
                    if(!"".equals(checkManId)){//不为空表示需要审核
                        checkInfo=flightIntlService.getTAcUser(checkManId);
                        addOrder.setIntlCheckordermen(checkInfo.getUsername());
                        addOrder.setIntlOrderStatus("1");//待审核
                        addOrder.setIntlCheckdate(new Date());
                        TNotifyPartyInformation check = new TNotifyPartyInformation();// 审核人
                        
                        check.setNotifyName(checkInfo.getUsername());
                        check.setPassengerTel(checkInfo.getPhone());
                        check.setMail(checkInfo.getEmail());
                        check.setPosition("审核人");
                        check.setStatus("0");
                        check.setNationalityStatus("1");
                        nlist.add(check);
                    }else{//为空不需要审核
                        addOrder.setIntlCheckordermen("");
                        addOrder.setIntlOrderStatus("3");// 暂时确认为已审核(按逻辑是待审核)
                    }
                }else {
                    addOrder.setIntlOrderStatus("2");// 待支付
                }
                
                // 乘机人信息================================================
                String orgRate = getOrgRate(tacorg.getOrgid());// 获取企业返点信息
               
                double totalPrice = 0d;
                double totalTaxfee = 0d;
                double tempTotalPrice = 0d;// 临时存放纯票面价总和，计算服务费使用
                double tempPrice = 0d;// 临时存放纯票面价，计算服务费使用
                double intlAssurancePrice = 0d;
                double IntlAssuranceNum = 0d;
                double totalAssurancePrice = 0d;
                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                List<TIntlTravelItinerary> psgList = new ArrayList<TIntlTravelItinerary>();
                TAcInsurance tacInsurance = null;//保险，用来查询保险底价
                
                if (passengers.size() > 0) {
                    TIntlTravelItinerary psg = null;
                    TAcUser tu=new TAcUser();
                    TTravelPassenger tl=new TTravelPassenger();
                    TAcInsurance taAcInsurance=null;
                    for(Req_Data_OrderInfo_Informer_Passenger passenger: passengers){
                        psg = new TIntlTravelItinerary();
                        if(passenger.getType()==1){//1：企业员工，
//                            tu= (TAcUser)tacUserDao.findOne(passenger.getId());
                            tu=comDao.queryForEntity(Long.valueOf(passenger.getId()), TAcUser.class);
                            psg.setIntlPassengerType("1");
                            psg.setIntlPassengerName(tu.getUsername());
                            psg.setIntlPassengerId(getPassID(tu,String.valueOf(passenger.getIdType())));
                            psg.setIntlPassengerTel(tu.getPhone());
                            psg.setIntlMail(tu.getEmail());
                            
                        }else{//2：常旅客     (暂时用国内常旅客)
//                            tl= (TTravelPassenger)intlAirOrderBookManager.findById(TTravelPassenger.class, Long.parseLong(passenger.getId()));
                              tl=(TTravelPassenger)comDao.queryForEntity(Long.valueOf(passenger.getId()), TTravelPassenger.class);

                            psg.setIntlPassengerType(tl.getPassengerType().toString());
                            psg.setIntlPassengerName(tl.getName());
                            psg.setIntlPassengerId(tl.getIdnumber());
                            //psg.setIntlPassengerId(intlAirOrderBookManager.getPassID(tl,passenger.getIdType()));
                            
                            psg.setIntlPassengerTel(tl.getMobilephone());
                            psg.setIntlMail(tl.getEmail());
                        }
                        
                        psg.setIntlPassengerIdtype(String.valueOf(passenger.getIdType()));
                        psg.setIntlMileageCard(passenger.getMileage());
                        if (payMethod==1 ||payMethod==4) {
                            //只有预付款支付和公司月结才需要费用归属
//                            TAcOrg t=(TAcOrg)appLoginManager.findById(TAcOrg.class, Long.valueOf(passenger.getOrgid()));
                            TAcOrg t=comDao.queryForEntity(Long.valueOf(passenger.getOrgid()), TAcOrg.class);
                            psg.setIntlCostOfBelonging(t.getOrgname());
                        }else{
                             psg.setIntlCostOfBelonging("");
                        }
                        psg.setIntlProjectNo(passenger.getProject());
                        
                        
                        if(0!=passenger.getInsuranceNum()){
//                            taAcInsurance=(TAcInsurance)intlAirOrderBookManager.findById(TAcInsurance.class, Long.parseLong(passenger.getInsuranceId()));
                            taAcInsurance=(TAcInsurance)comDao.queryForEntity(Long.valueOf(passenger.getInsuranceId()).intValue(), TAcInsurance.class);
                            psg.setIntlAssuranceNum(String.valueOf(passenger.getInsuranceNum()));//保险份数
                            psg.setIntlAssurancePrice(taAcInsurance.getPrice());//保险卖价
                            psg.setIntlInsuranceReserve(taAcInsurance.getFloorPrice());//保险低价
                            psg.setInsuranceId(Long.valueOf(passenger.getInsuranceId()).intValue());//保险id
                        }else{
                            psg.setIntlAssurancePrice(0d);
                            psg.setIntlInsuranceReserve(0d);
                            psg.setIntlAssuranceNum("0");
                        }
         
                        /*if ("儿童".equals(psg.getIntlPassengerType())) {
                            psg.setIntlTicketPrice(Double.valueOf(getRequest().getParameter("etTicketPrice")));
                            psg.setIntlSingleTax(Double.valueOf(getRequest().getParameter("etTaxPrice")));
                        } else if ("婴儿".equals(psg.getIntlPassengerType())) {
                            psg.setIntlTicketPrice(Double.valueOf(getRequest().getParameter("yeTicketPrice")));
                            psg.setIntlSingleTax(Double.valueOf(getRequest().getParameter("yeTaxPrice")));
                        } else {
                            psg.setIntlTicketPrice(Double.valueOf(getRequest().getParameter("crTicketPrice")));
                            psg.setIntlSingleTax(Double.valueOf(getRequest().getParameter("crTaxPrice")));
                        }*/
                        psg.setIntlTicketPrice(Double.valueOf(cp.getTotalPrice()));
                        psg.setIntlSingleTax(Double.valueOf(cp.getTotalPriceWithTax())-Double.valueOf(cp.getTotalPrice()));
                        
                        tempTotalPrice += psg.getIntlTicketPrice();
                        tempPrice = psg.getIntlTicketPrice();
    
                        if (orgRate != null) {
                            psg.setIntlTicketPrice(psg.getIntlTicketPrice() * (1 - Double.valueOf(orgRate) / 100));
                        }
                        totalPrice += psg.getIntlTicketPrice();
                        totalTaxfee += psg.getIntlSingleTax();
    
                        intlAssurancePrice = psg.getIntlAssurancePrice();
                        IntlAssuranceNum = Double.valueOf(psg.getIntlAssuranceNum());
                        totalAssurancePrice += intlAssurancePrice * IntlAssuranceNum;
                        
                        
                        HashMap<String,String> idCardTypeMap = (HashMap<String, String>)RedisCacheManager.get(Const.APP_MAP_IDCARDTYPE,HashMap.class);
                        if(idCardTypeMap==null){
                            idCardTypeMap=(HashMap<String, String>) iComService.getIdcardTypeMap();
                        }
                        Iterator<String> idCardKeys=idCardTypeMap.keySet().iterator();
                        while(idCardKeys.hasNext()){
                            String next = idCardKeys.next();
                            if(psg.getIntlPassengerIdtype().equals(idCardTypeMap.get(next))){
                                psg.setIntlPassengerIdtype(next);// 把证件类型转换为ID
                            }
                        }
                        HashMap<String,String> personnalTypeMap = (HashMap<String, String>)RedisCacheManager.get(Const.APP_MAP_PERSONNELTYPE,HashMap.class);
                        if(personnalTypeMap==null){
                            personnalTypeMap=(HashMap<String, String>) iComService.getPersonnelTypeMap();
                        }
                        Iterator<String> personnalTypeKeys=personnalTypeMap.keySet().iterator();
                        while(personnalTypeKeys.hasNext()){
                            String next = personnalTypeKeys.next();
                            if(psg.getIntlPassengerType().equals(personnalTypeMap.get(next))){
                                psg.setIntlPassengerType(next);// 把证件类型转换为ID
                            }
                        }
//                        psg.setIntlPassengerIdtype(getDictCodeByNameFromCache("IDCARD_TYPE", psg.getIntlPassengerIdtype()));// 把证件类型转换为ID
//                        psg.setIntlPassengerType(getDictCodeByNameFromCache("PERSONNEL_TYPE", psg.getIntlPassengerType()));// 把乘机人类型转换为ID
    
                        psgList.add(psg);
                    }
                }
                
             // 订单总价
                addOrder.setIntlOrdernoPrice(totalPrice);
                addOrder.setIntlTaxesTotalPrice(totalTaxfee);
                addOrder.setIntlOrdernoPrice(totalPrice + totalTaxfee + totalAssurancePrice);

                // 操作人集合
                TNotifyPartyInformation subInfo = new TNotifyPartyInformation();// 预订人
                subInfo.setNotifyName(tAcUser.getUsername());
                subInfo.setPassengerTel(tAcUser.getPhone());
                subInfo.setMail(tAcUser.getEmail());
                subInfo.setPosition("预订人");
                subInfo.setStatus("0");
                subInfo.setNationalityStatus("1");
                nlist.add(subInfo);
                
                List<Req_Data_OrderInfo_Informer> tongzhirens=req_OrderInfo.getInformerList(); // 通知人信息

                // 通知人
                if (tongzhirens!=null&&tongzhirens.size() > 0) {
                    for (Req_Data_OrderInfo_Informer il :tongzhirens) {
                        if (StringUtil.isEmpty(il.getToName()))
                            continue;
                        TNotifyPartyInformation npinfo = new TNotifyPartyInformation();
                        npinfo.setNotifyName(il.getToName());
                        npinfo.setPassengerTel(il.getToTel());
                        npinfo.setMail(il.getToMail());
                        npinfo.setPosition("通知人");
                        npinfo.setStatus("0");
                        npinfo.setNationalityStatus("1");
                        nlist.add(npinfo);
                    }
                }
                
                BookIntlOrderParam bp=new BookIntlOrderParam();
                bp.setIntlOrder(addOrder);
                bp.setIntlXingChegn(flightList);
                bp.setIntlPassengers(psgList);
                bp.setTacuser(tAcUser);
                bp.setTacorg(tacorg);
                bp.setNpinfo(nlist);
                String status=this.bookIntlOrder(bp);
                if("1".equals(status)){//1:服务费有误，请联系客服。  2:余额不足。
//                    returnError("服务费有误，请联系客服。");  
                    rbo.status=Constant.Code_Failed;
                    System.out.println(BookErrorTypeCode.服务费有误.toString());
                    return rbo;
                }else if("2".equals(status)){//1:服务费有误，请联系客服。  2:余额不足。
                    rbo.status=Constant.Code_Failed;
                    System.out.println(BookErrorTypeCode.预付款余额不足.toString());
                    return rbo;
                }else{//表示成功
                    RespData_BookIntlOrder_OrderInfo rb=new RespData_BookIntlOrder_OrderInfo();
                    rb.setOrderId(addOrder.getOrderid());
                    rb.setOrderno(addOrder.getIntlOrderno());
                    rb.setTotalPrice(addOrder.getIntlOrdernoPrice()==null?0d:Double.valueOf(Math.round(Math.ceil(addOrder.getIntlOrdernoPrice()))));
                    rbo.status=Constant.Code_Succeed;
                    rbo.setData(rb);
                    
                    return rbo;
                }   
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return rbo;
    
    }
    
    
    private FlightResult getFlightResults(Long userId, String mapKey,IntlAitTicketGetTargetFType type,String cangwei) {
        OutputResult<FlightResult,String> or=flightIntlService.getFlightResult(userId, mapKey, type, cangwei);
        if(or.code==Constant.Code_Succeed){
            return or.getResultObj();
        }else{
            return new FlightResult();
        }
    }
    
    //------------退改签相关 ----开始------
    
    private ArrayList<String> getTicketRule(FlightResult result,String cangwei)
    {
        ArrayList<String> rule=new ArrayList<String>();
        OutputResult<Response_GetAirRulesI_1_0,String> res = this.getIntlFlightRule(result,cangwei);
        if (res.code == Constant.Code_Succeed) {
            if (res.getResultObj().airRules.size() > 0) {
                for (Entry<String, Hashtable<String, Object[]>> tripET : res.getResultObj().airRules.entrySet()) {
                    Object[] values = tripET.getValue().get("ResultData");
                    Object[] rules = ((ArrayList) values[0]).toArray();
                    String value = rules[1].toString();
                    if("S1".equals(tripET.getKey())){
                        rule.add(0, value);
                    }else{
                        rule.add(value);
                    }
                }
            }
        }
        if(rule.size()==0){
            rule.add("暂未查询到退改签规则,具体规则请以航空公司规定为准。");
            rule.add("暂未查询到退改签规则,具体规则请以航空公司规定为准。");
        }
        return rule;
    }
    
    public OutputResult<Response_GetAirRulesI_1_0,String> getIntlFlightRule(
            FlightResult result,String cabin) {
        OutputResult<Response_GetAirRulesI_1_0,String> or = new OutputResult<Response_GetAirRulesI_1_0,String>();
        Response_GetAirRulesI_1_0 rule = null;
//        mcManager.getIntlFlightRule(result.unformatMapkey+"|"+cabin);
        if (rule != null) {
            or.code = Constant.Code_Succeed;
            or.setResultObj(rule);
        } else {
            try{
                boolean isError=false;
                List<AirRulesRQ> req = new ArrayList<AirRulesRQ>();
                Entry<String, Hashtable<String, Object[]>> targetEntry = result.targetFs
                        .iterator().next();
                Object[] targetH_PsgType = null;// ADT对应的内容
                for (Entry<String, Object[]> et : targetEntry.getValue().entrySet()) {
                    int tripIndex = "S1".equals(et.getKey()) ? 0 : 1;// 拆分内容用到的序号
                    Object[] trip = et.getValue();// S1、S2对应的value
                    // 查政策的时候，不需要航班里的详情，中转的也不需要传中转的相关信息
                    Object[] segments = ((ArrayList) trip[0]).toArray();
                    // Object[] flights = ((ArrayList) trip[1]).toArray();
                    // Object[] firstFlight = ((ArrayList) flights[0]).toArray();
                    AirRulesRQ r = new AirRulesRQ();
                    r.setStartdate(segments[4].toString());
                    r.setStarttimeStr(segments[5].toString());
                    if (targetH_PsgType == null || targetH_PsgType.length == 0) {
                        targetH_PsgType = getTargetH_PsgType(result, cabin);
                        if(targetH_PsgType==null)
                        {
                            isError=true;
                            or.code = Constant.Code_Failed;
                            or.result="找不到舱位对应的价格信息，请确认后重试。";
                            break;
                        }
                    }
                    r.setPriceBase(targetH_PsgType[8].toString().split(",")[tripIndex]);
                    r.setCarrier(targetH_PsgType[1].toString().split("/")[tripIndex]);
                    r.setOrgcity(targetH_PsgType[2].toString().split("/")[tripIndex]);
                    r.setDetcity(targetH_PsgType[3].toString().split("/")[tripIndex]);
                    r.setTuiGaiSign(targetH_PsgType[6].toString().split("_")[tripIndex]);
                    req.add(r);
                }
                if(!isError){
                    or = getIntlAirRules(req);
                    if( or.code == Constant.Code_Succeed && or.getResultObj()!=null){
//                        mcManager.putIntlFlightRule(result.unformatMapkey+"|"+cabin, or.resultObj);
                    }
                }
            }catch(Exception ex){
                or.code = Constant.Code_Failed;
                or.result="错误代码：201701041406。错误信息："+ex.getMessage();
            }
        }
        return or;
    }
    public Object[] getTargetH_PsgType(FlightResult result, String cabin) {
        Object[] targetH_PsgType;
        Response_QueryWebFlightsI_1_0 cabinList= null;
        //mcManager.getIntlFlightCabinList(result.unformatMapkey);
        if(cabinList!=null){//查过舱位列表，直接从列表中查询
            targetH_PsgType = (Object[]) cabinList.flgithPrices.entrySet().iterator().next().
                    getValue().get(cabin)
                    .get(CommonMethodUtils.getPassengerType(result.baseTripKey));
        }else{
            targetH_PsgType = (Object[]) result.resultObj.flgithPrices
                    .get(result.targetFs.iterator().next().getKey()).get(cabin)
                    //.entrySet().iterator().next().getValue()
                    .get(CommonMethodUtils.getPassengerType(result.baseTripKey));
        }
        return targetH_PsgType;
    }
    
    /**
     * 国际航班退改签
     * @param airRulesIntl
     * @return
     */
    private OutputResult<Response_GetAirRulesI_1_0,String> getIntlAirRules(List<AirRulesRQ> airRulesRQs) {
        Request_GetAirRulesI_1_0 airRulesIntl = new Request_GetAirRulesI_1_0();
        List<AirRulesRQ> airRulesRQList = new ArrayList<AirRulesRQ>();
        for (int i = 0; i < airRulesRQs.size(); i++) {
            airRulesRQList.add(airRulesRQs.get(i));
        }
        airRulesIntl.setAirRulesRQs(airRulesRQList);

        OutputResult<Response_GetAirRulesI_1_0,String> resq = new OutputResult<Response_GetAirRulesI_1_0,String>();
//        resq.resultObj = new Response_GetAirRulesI_1_0();
        resq.setResultObj(new Response_GetAirRulesI_1_0());
        Filter_QueryWebFlightsIntl_1_0 fi = new Filter_QueryWebFlightsIntl_1_0();
        fi.DataFormat = "J";
        Boolean flag = yeego.Request(airRulesIntl, resq, fi);
        return resq;
    }
    
    //------------退改签相关 ----结束------
    
    private int getGoFlightCount(Entry<String, Hashtable<String, Object[]>> targetEntry){
        int count=0;
        for (Entry<String, Object[]> et : targetEntry.getValue().entrySet()) {
            if("S1".equals(et.getKey())){
                Object[] trip = et.getValue();// S1、S2对应的value
                count = ((ArrayList) trip[1]).toArray().length;
            }
        }
        return count;
    }
    
    //根据舱位返回中文舱位名称,根据中文名称返回相应的数字等级   舱位等级，1-经济舱，2-公务舱，3-头等舱
    public String getCangweiStr(String cangwei){
        String cangweiStr="";
        if(cangwei.indexOf("经济舱")>=0){
            cangweiStr="1";
        }else if(cangwei.indexOf("公务舱")>=0){
            cangweiStr="2";
        }else if(cangwei.indexOf("头等舱")>=0){
            cangweiStr="3";
        }
        return cangweiStr;
    }
    
    /**
     * 获取企业返点
     * 
     * @param _orgid
     */
    public String getOrgRate(long _orgid) {
        TAcOrg org = (TAcOrg) tacOrgDao.findOne(_orgid);// 获取机构信息
        // 如果企业返点不为空，就拿企业返点计算折后价
        if (org.getInternationrebate() != null) {
            return org.getInternationrebate();
        } else {
            // 如果企业返点为空，就拿出港城市返点计算折后价
            if (org.getBjrebate() != null) {
                return org.getBjrebate();
            } else if (org.getShrebate() != null) {
                return org.getShrebate();
            }

        }
        return null;
    }
    
    /**@title:根据证件类型和员工信息获取相应的证件ID
     * @author:hx
     * @date:2017-1-5
     * @return:String 
     */
    public String getPassID(TAcUser tacuser,String type){
        String passID="";
        if("1".equals(type)){//身份证号
            passID=tacuser.getIdcard();
        }else if("2".equals(type)){//因公护照
            passID=tacuser.getPassportpublic();
        }else if("3".equals(type)){//回乡证
            passID=tacuser.getHxz();
        }else if("4".equals(type)){//因公港澳通行证
            passID=tacuser.getPublicgatxz();
        }else if("5".equals(type)){//军官证
            passID=tacuser.getJgz();
        }else if("6".equals(type)){//入台证
            passID=tacuser.getRtz();
        }else if("7".equals(type)){//大陆往来通行证
            passID=tacuser.getDlwltxz();
        }else if("8".equals(type)){//因私护照
            passID=tacuser.getPassport();
        }else if("9".equals(type)){//因私港澳通行证
            passID=tacuser.getGatxz();
        }
        return passID;
    } 
    
    /**
     * @功能：添加订单表记录、PNR表对应订单表单程或往返记录、乘机人表对应的PNR表记录
     * @时间:2016/10/24
     * @param 
     * @author hx
     * @return
     */
    public Integer saveIntlFlightOrder(TIntlOrder order, List<TIntlAirTicketItinerary> flightList,
            List<TIntlTravelItinerary> psgList, Long _orgid, List<TNotifyPartyInformation> nlist, String username) {
        Date currDate =DateUtil.getSysDate();// 数据库当前时间
        Integer orderId = 0;
        try {
            order.setIntlSubscribeDate(currDate);
            // 添加订单号
//            OrderManager orderManager = (OrderManager) Struts2Util.getBean("orderManager");// 自动生成订单号订单号
            String date = DateUtil.date2Str(new Date(), "yyyyMMdd HH:mm:ss:SS");
//            orderManager.addOrderNo(_orgid.toString(), date, true);// 最后一个参数为false标识是国内机票订单，true标识是国际机票订单

            // 根据生成订单号的时间，获取订单号
          
//            TOrderno orderno = (TOrderno) dao.findByHQL("from TOrderno where CREATETIME = ?", date).get(0);
//            order.setIntlOrderno(orderno.getOrderno());
//            dao.save(order);
          //保存订单
          //保存订单
            comDao.executeSave(order);
            String intlOrderno=comDao.creatOrderNo(order.getOrderid().toString(),MyEnum.OrderTypeEnum.国内机票.getValue());
            order.setIntlOrderno(intlOrderno);
            comDao.executeUpdate(order);
            // 刚添加的订单ID
            orderId = order.getOrderid();

            // 黄玄添加,预订信息保存到T_INTL_COMMENT表
            setAutoComment(username, "预订", orderId.toString());

            // 插入航段信息
            for (TIntlAirTicketItinerary flight : flightList) {
                // 添加订单id
                flight.setOrderid(orderId);
                comDao.executeSave(flight);
            }

            // 插入乘机人信息
            for (TIntlTravelItinerary psg : psgList) {
                // psg.setOrderid(orderId);
                psg.settIntlOrder(order);
                comDao.executeSave(psg);
            }

            // 插入关联表数据
            for (TIntlTravelItinerary psg : psgList) {
                for (TIntlAirTicketItinerary flight : flightList) {
                    TIntlTicketTravel ticket = new TIntlTicketTravel();
                    ticket.setOrderid(orderId);
                    ticket.setPassid(psg.getPassid());
                    ticket.setTripid(flight.getTripid());
                    ticket.setIntlCreateDate(currDate);
                    ticket.setIntlTicketStatus("0");
                    comDao.executeSave(ticket);
                }
            }

            // 通知人信息
            for (TNotifyPartyInformation npinfo : nlist) {
                npinfo.setOrderid(orderId);
                npinfo.setSendTime(DateUtil.getSysDate());
                comDao.executeSave(npinfo);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return orderId;

    }
    
    public String bookIntlOrder(BookIntlOrderParam bp){
        String status="";
        this.saveIntlFlightOrder(bp.getIntlOrder(), bp.getIntlXingChegn(), bp.getIntlPassengers(), 
                bp.getTacorg().getOrgid(),bp.getNpinfo(), bp.getTacuser().getUsername());
        
        String intlClassType = this.getMaxClassType(bp.getIntlOrder().getOrderid());
        for (TIntlTravelItinerary psg : bp.getIntlPassengers()) {
            psg.setIntlClassType(intlClassType);
            this.savePsg(psg);
        }
        
//        ServiceFeeManager serviceFeeManager = (ServiceFeeManager) Struts2Util.getBean("serviceFeeManager");
//        OrgBanlanceManager balanceManager = (OrgBanlanceManager) Struts2Util.getBean("orgBalanceManager");
     // 服务费插入
        try {
            serviceFee.saveIntlSubscribeServiceFee(bp.getIntlOrder(), bp.getIntlPassengers(),
                    bp.getIntlXingChegn().size(),bp.getIntlOrder().getIntlOrdernoPrice(),
                    bp.getIntlOrder().getIntlOrdernoPrice());
        } catch (Exception e) {
            e.printStackTrace();
            return status="1";
        }
        
        //TAcRole role = appLoginManager.getRole(tAcUser.getUserid());
        
      //黄玄添加
        Boolean flag = true;
        Integer orderid = bp.getIntlOrder().getOrderid();
		if(bp.getIntlOrder().getIntlPayMethod().equals("4") || "1".equals(bp.getIntlOrder().getIntlPayMethod())){//表示预付款
			Double balance=balancePay.checkBalance(bp.getTacorg().getOrgid(),bp.getIntlOrder().getIntlPayMethod());
			
			Double money=bp.getIntlOrder().getIntlOrdernoPrice()*-1;
			Map<String, String> map=null;
			if((money<0&&balance>=money*-1)||money >=0){//余额大于当前支付金额  或者 为负数余额（退票或改签）
				// 若支付方式为预付款支付 ，则首先扣除企业预付款余额，扣除流程正常，进行后续操作，否则，删除订单（级联删除pnr。）
		        // ----窦鹏志修改
		        double totalPeisonPrice=0d;
		        
		        if (bp.getIntlOrder().getIntlPayMethod().equals("4") || "1".equals(bp.getIntlOrder().getIntlPayMethod())) {
		            String[] param=new String[]{"app",bp.getTacuser().getUserid().toString(),bp.getTacuser().getUsername().toString()};
		            
		            for (TIntlTravelItinerary psgs : bp.getIntlPassengers()) {
		                if(flag){
		                    totalPeisonPrice=  (psgs.getIntlTicketPrice()==null?0:psgs.getIntlTicketPrice())
		                                      +(psgs.getIntlSingleTax()==null?0:psgs.getIntlSingleTax())
		                                      +((psgs.getIntlAssurancePrice()==null?0:psgs.getIntlAssurancePrice()) * (Double.valueOf(psgs.getIntlAssuranceNum()==null?"0":psgs.getIntlAssuranceNum())))
		                                      +(psgs.getServiceFee()==null?0:psgs.getServiceFee())
		                         +(psgs.getElseReceiveable()==null?0:psgs.getElseReceiveable());
		                    
		                    map = balancePay.balancePay(Config.JPFK, Double.valueOf(totalPeisonPrice*-1),bp.getIntlOrder().getIntlOrderno(), "",  bp.getTacorg().getOrgid(), bp.getTacorg().getOrgname(),bp.getIntlOrder().getIntlPayMethod(),param);
		                                   //balancePay(String     ,Double                              ,String                            ,String,Long orgId              ,String orgName              ,String payMethod,String...value){

		                    if(!"0".equals(map.get("status"))){
		                        flag = false;
		                    }
		                    
		                }
		            }
		            
		        }
			}else{
				flag = false;
			}
		}
		
     
        if (!flag) {
            //this.deleteOrder(orderid);
//            deleteOrder(2, orderid.toString(), "预付款余额不足");
        	comDao.deleteOrder(2, Long.valueOf(orderid.toString()), ("1".equals(bp.getIntlOrder().getIntlPayMethod())?"月结额度":"预付款余额") + "不足");
            /*Integer status_=comDao.deleteOrder(2,Long.valueOf(orderid.toString()),map.get("message"));
            System.out.println(orderid.toString()+" 支付状态："+status_);*/
            return "2";
            // 返回余额不足，重新跳转到查询页面
        }
        
        return status;
    }
    
    /**
     * 自动添加订单备注(操作人，操作内容，订单ID)
     */
    public boolean setAutoComment(String name, String comment, String orderID) {
        String comment_msg = name + ":" + comment;

        String sqlString = "insert into T_INTL_COMMENT(COMMENT_ID,passenger_name,comment_msg,comment_date,o_id) values(SEQ_INTL_COMMENT.NEXTVAL,'"
                + "系统"
                + "','"
                + comment_msg
                + "','"
                + comDao.getDBTime().toString()
                + "',"
                + Long.parseLong(orderID)
                + ")";
        
//        TIntlComment comment2=new TIntlComment();
//        comment2.setCommentMsg(comment_msg);
//        comment2
        boolean flag = false;
        int num = 0;
        Connection conn = null;
        Statement stmt = null;
        try {
             comDao.executeSQL(sqlString,null);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (Exception e2) {
                // TODO: handle exception
                e2.printStackTrace();
            }

        }

        return flag;

    }
    
    public String getMaxClassType(Integer orderid) {
    	if(null==orderid){
    		return "";
    	}
    	//TIntlAirTicketItinerary
        String sql = "select max(intlClassType)  from TIntlAirTicketItinerary  where orderid="+orderid.intValue()+" and intlVoyageStatus='0' ";
        Integer intlClassType = Integer.valueOf(comDao.queryForObject(sql,null).toString());
        if (intlClassType == null) {
            return "";
        } else {  
            return intlClassType.toString();
        }
    }
    
    public void savePsg(TIntlTravelItinerary psg) {
        comDao.executeSave(psg);

    }


    @Override
    public Response_GetIntlOrderList getIntlOrderList(Request_GetIntlOrderList para) {
        Response_GetIntlOrderList intlOrderListObj=new Response_GetIntlOrderList();
        Req_Data_IntlOrderListInfo intlListInfo=para.getData();
        intlListInfo.getCount();
        intlListInfo.getStart();
        intlListInfo.getStatus();
        try {
            String sql=getSql4QueryOrderList(para);
            if("".equals(sql)){
                intlOrderListObj.status=Constant.Code_Failed;
                System.out.println("无效的用户id--"+para.userId+"。");
                return intlOrderListObj;
            }
            String sql2="select count(t.orderid) from "+sql.split("from")[1];
            int totalCount=comDao.queryForInt(sql2);
            PageParam pp = new PageParam(intlListInfo.getStart(),intlListInfo.getCount());
            pp.getTotalPage();
//            comDao.queryForInt(queryIntHQL, parameterArray)
            PageObject<T> list = extDao.list_(sql, null, pp);
            List<T> intlOrderLists=list.getPageList();
            List<TIntlOrder> intlOrderList_=new ArrayList<TIntlOrder>();
            for (int i = 0; i < intlOrderLists.size(); i++) {
                TIntlOrder intlorder=TIntlOrder.class.cast(intlOrderLists.get(i));
                intlOrderList_.add(intlorder);
            }
            if(intlOrderLists.size()>0){
                intlOrderListObj =getOrderList(para,intlOrderList_);
                intlOrderListObj.getData().setTotalCount(totalCount);
                intlOrderListObj.status=Constant.Code_Succeed;
            } else{
                intlOrderListObj.status=Constant.Code_Succeed;
                System.out.println("没有更多数据了。"+para.userId+"。");
                return intlOrderListObj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intlOrderListObj;
    }
    
    private String getSql4QueryOrderList(Request_GetIntlOrderList reqs){
        Req_Data_IntlOrderListInfo req=reqs.getData();
        String status = getFormatOrderStatus(req);
        /*String sql="select t.ORDERID, t.INTL_ORDERNO,t.INTL_ORDERNO_PRICE,to_char(t.intl_subscribe_date,'yyyy-MM-dd HH:mm')," +
                "t.INTL_ORDER_STATUS from T_INTL_ORDER t where  t.INTL_ORDER_STATUS in ("+status+")  ";
        
        //订单审核并且查待审核订单的时候，查待该人审核的所有订单
        if(req.getStatus().intValue()>=100 && req.getStatus().intValue()<=200){
            TAcUser user=(TAcUser)comDao.queryForEntity(reqs.userId, TAcUser.class);
            if(user==null){
                sql="";
                return sql;
            }
            String companyId=comDao.queryForEntity(user.getOrgid(), TAcOrg.class).getOrgid().toString();
//            String companyId=get2Orgid(user.getOrgid().toString());
            String name=user.getUsername();
            sql+="and t.company_of_affiliation_id='"+companyId+"' and t.intl_checkordermen='"+name+"'";
        }else{
            sql+=" and t.intl_subscribe_id="+reqs.userId;
        }
        sql+=" order by t.ORDERID desc";
        return sql;*/
        String sql="select t from TIntlOrder t where  t.intlOrderStatus in ("+status+")  ";
        
        //订单审核并且查待审核订单的时候，查待该人审核的所有订单
        if(req.getStatus().intValue()>=100 && req.getStatus().intValue()<=200){
            TAcUser user=(TAcUser)comDao.queryForEntity(reqs.userId, TAcUser.class);
            if(user==null){
                sql="";
                return sql;
            }
            String companyId=comDao.queryForEntity(user.getOrgid(), TAcOrg.class).getOrgid().toString();
//            String companyId=get2Orgid(user.getOrgid().toString());
            String name=user.getUsername();
            sql+="and t.companyOfAffiliationId='"+companyId+"' and t.intlCheckordermen='"+name+"'";
        }else{
            sql+=" and t.intlSubscribeId="+reqs.userId;
        }
        //sql+=" order by t.orderid desc";
        sql+=" order by t.intlSubscribeDate desc";
        return sql;
        
    }
    
    
    
    
    //国际订单状态（1-待审核，2-待支付，7-已删除，3-审核已同过，5-审核已拒绝，4-已支付,6-已取消）
    private String getFormatOrderStatus(Req_Data_IntlOrderListInfo req) {
        Integer reqStatus=req.getStatus();
        String status="'1','2','3','4','5','6'";//0 eq reqStatus 全部，不含已删除
        //订单审核，默认待审核，审核通过，审核拒绝
//        if("1".equals(req.orderType)){
//            status="'1','2','3'";
//        }
        if(reqStatus.intValue()>=100 && reqStatus.intValue()<=200){
            if(IntlEnum.IntlOrderStatus.全部订单_管理员_审核员.getCode()==reqStatus.intValue()){ //自己预订的订单
                //默认
            }else if(IntlEnum.IntlOrderStatus.待审核的订单_管理员_审核员.getCode()==reqStatus.intValue()){
                status="'1'";
            }else if(IntlEnum.IntlOrderStatus.审核通过的订单_管理员_审核员.getCode()==reqStatus.intValue()){
                status="'3'";
            }else if(IntlEnum.IntlOrderStatus.审核驳回的订单_管理员_审核员.getCode()==reqStatus.intValue()){
                status="'5'";
            }else if(IntlEnum.IntlOrderStatus.已完成订单_管理员_审核员.getCode()==reqStatus.intValue()){
                status="'3','4','6'";
            }
        }else{
            if(IntlEnum.IntlOrderStatus.待审核订单.getCode()==reqStatus.intValue()){
                status="'1'";
            }else if(IntlEnum.IntlOrderStatus.审核通过的订单.getCode()==reqStatus.intValue()){
                status="'3'";
            }else if(IntlEnum.IntlOrderStatus.审核驳回的订单.getCode()==reqStatus.intValue()){
                status="'5'";
            }else if(IntlEnum.IntlOrderStatus.待支付订单.getCode()==reqStatus.intValue()){
                status="'2'";
            }else if(IntlEnum.IntlOrderStatus.已完成订单.getCode()==reqStatus.intValue()){
                status="'3','4','6'";
            }
        }
        return status;
    }
    
    private Response_GetIntlOrderList getOrderList(Request_GetIntlOrderList req,List<TIntlOrder> list) {
        Response_GetIntlOrderList res=new Response_GetIntlOrderList();
        Resp_Data_GetIntlOrderList data=new Resp_Data_GetIntlOrderList();
        //航班信息
        List<IntlOrderListCarrier> carriers=new ArrayList<IntlOrderListCarrier>();
        //
        List<IntlOrderListInfo> listInfo=new ArrayList<IntlOrderListInfo>();
        ArrayList<String> carrierAL=new ArrayList<String>();
        for(TIntlOrder os :list){
            Integer oid=os.getOrderid();
            List<TIntlAirTicketItinerary> segList= comDao.queryForList("from TIntlAirTicketItinerary where intlVoyageStatus='0' and orderid="+
                    oid+" order by flightType,tripid asc");
//            Order4List item=new Order4List();
            IntlOrderListInfo item=new IntlOrderListInfo();
            item.setOrderId(Long.valueOf(os.getOrderid()));//item.orderId=oid.toString();
            item.setOrderNo(os.getIntlOrderno());//item.orderNo=os[1].toString();
            item.setBookDatetime(DateUtil.date2Str(os.getIntlSubscribeDate(), "yyyy-MM-dd HH:mm"));//item.bookDatetime=os[3].toString();
            item.setAmount(os.getIntlOrdernoPrice());//item.amount=getDoubleStringWithoutDot(Double.valueOf(os[2].toString()));
            item.setStatus(Integer.valueOf(os.getIntlOrderStatus()));//item.status=GetOrderStatusDesc(os[4].toString());
            List<IntlOrderListAirline> airlines=new ArrayList<IntlOrderListAirline>();
//            item.airlines=new ArrayList<Airline4List>();
            boolean isBack=false;
            int backFirstIndex=-1;
            IntlOrderListAirline al=null;//Airline4List al=null;
           
            for(int i=0;i<segList.size();i++){
                TIntlAirTicketItinerary p=segList.get(i);
                if(p.getFlightType() !=null && p.getFlightType()==2){
                    isBack=true;
                    if(backFirstIndex==-1){
                        backFirstIndex=i;
                    }
                }
                if(i==0 || i==backFirstIndex || i==backFirstIndex-1 || i==segList.size()-1){
                    if(i==0 || i==backFirstIndex ){ 
                        al=new IntlOrderListAirline();
//                        al.carriers="";//待遍历所有
//                        al.flightNos="";//待遍历所有
                        Integer type=(p.getFlightType() !=null && p.getFlightType()==2?2:1);
                        al.setType(type);
                        String startDatetime=DateUtil.date2Str(p.getIntlDepartureTime(),"yyyy-MM-dd HH:mm");//第一段
                        al.setStartDatetime(startDatetime);
                        //al.orgPortName=GetPortNameByCode(p.getIntlDepartureAirport());//第一段
                        String orgTerm=p.getIntlDepartureTerminal();//第一段
                        al.setOrgTerm(orgTerm);
                        airlines.add(al);//item.airlines.add(al);
                    }//往返直飞的有问题，下面几个值没赋值，所以加了 i==0
                    if(i==0 || i==backFirstIndex-1 || i==segList.size()-1 || segList.size()==1 || segList.size() == backFirstIndex){
                        String endDatetime=DateUtil.date2Str(p.getIntlReachTime(),"yyyy-MM-dd HH:mm");//最后一段
                        al.setEndDatetime(endDatetime);
//                        al.detPortName=GetPortNameByCode(p.getIntlReachAirport());//最后一段
                        String detTerm=p.getIntlReachTerminal();//最后一段
                        al.setDetTerm(detTerm);
                    }
                }
                if(!carrierAL.contains(p.getIntlAirlineCompany())){
                    carrierAL.add(p.getIntlAirlineCompany());
                }
                if(StringUtils.isNotEmpty(al.getCarriers())) {
                    String carrierss=al.getCarriers()+",";
                    al.setCarriers(carrierss);
                    al.setCarriers(al.getCarriers()+p.getIntlAirlineCompany());//遍历所有
                }else{
                    al.setCarriers(p.getIntlAirlineCompany());//遍历所有
                }
                
                
                
                /*if(!"".equals(al.flightNos)) {
                    al.flightNos+=",";
                }
                al.flightNos+=p.getIntlFlightNumber();//遍历所有 */                
                if(StringUtils.isNotEmpty(al.getFlightNos())) {
                    String flightnos=al.getFlightNos()+",";
                    al.setFlightNos(flightnos);
                    al.setFlightNos(al.getFlightNos()+p.getIntlFlightNumber());//遍历所有
                }else{
                    al.setFlightNos(p.getIntlFlightNumber());//遍历所有
                }
                
            }
            item.setAirlines(airlines);
            if(!isBack) backFirstIndex=segList.size();
            TIntlAirTicketItinerary tempGoLast=segList.get(backFirstIndex-1);
            //String travelType=isBack?"RT":"OW";
            int travelType=isBack?2:1;
            item.setTravelType(travelType);
            String orgCityName=GetCityNameByCode(segList.get(0).getIntlDepartureCity());//去程第一段
            item.setOrgCityName(orgCityName);
            String detCityName=GetCityNameByCode(tempGoLast.getIntlReachCity());//去程最后一段
            item.setDetCityName(detCityName);
            
            listInfo.add(item);//res.list.add(item);
            /*if(carrierAL.size()>0){
                res.carriers=new ArrayList<Carrier>();
                for(String c:carrierAL){
                    Carrier car=new Carrier();
                    car.code=c;
                    car.name=GetAirCompanyNameByCode(c);
                    car.shortName="";
                    res.carriers.add(car);
                }
            }*/
        }
        for(String c:carrierAL){
            IntlOrderListCarrier car=new IntlOrderListCarrier();
            car.setCode(c);
            car.setName(GetAirCompanyNameByCode(c));
            car.setShortName("");
            carriers.add(car);
        }
        data.setCarriers(carriers);
        data.setList(listInfo);
        res.setData(data);
        return res;
    }
    
    public String GetCityNameByCode(String code){
        HashMap<String, String> hashMap = (HashMap<String, String>)RedisCacheManager.get(Const.APP_MAP_3CHAR_CITY,Map.class);
        Iterator<String> keys=hashMap.keySet().iterator();
        while(keys.hasNext()){
           String key= keys.next();
           if(key.equals(code)){
              return  hashMap.get(key);
           }
        }
        return code;
    }
    
    public String GetAirCompanyNameByCode(String code){
        HashMap<String, String> hashMap = (HashMap<String, String>)RedisCacheManager.get(Const.APP_MAP_AC2NAME,Map.class);
        Iterator<String> keys=hashMap.keySet().iterator();
        while(keys.hasNext()){
           String key= keys.next();
           if(key.equals(code)){
              return  hashMap.get(key);
           }
        }
        return code;
    }

    
    
}
