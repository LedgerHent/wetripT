package com.viptrip.ibeserver.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.annotation.Resource;

import jodd.util.StringUtil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.travelsky.ebuild.clientapi.FlightShoppping.Agency;
import com.travelsky.ebuild.clientapi.FlightShoppping.AvJourneys;
import com.travelsky.ebuild.clientapi.FlightShoppping.AvOpt;
import com.travelsky.ebuild.clientapi.FlightShoppping.AvailableJourneyType;
import com.travelsky.ebuild.clientapi.FlightShoppping.ClassType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Codeshare;
import com.travelsky.ebuild.clientapi.FlightShoppping.FCs;
import com.travelsky.ebuild.clientapi.FlightShoppping.FareComponentType;
import com.travelsky.ebuild.clientapi.FlightShoppping.FareInterface;
import com.travelsky.ebuild.clientapi.FlightShoppping.FlightShopRequestType;
import com.travelsky.ebuild.clientapi.FlightShoppping.FlightShopResult;
import com.travelsky.ebuild.clientapi.FlightShoppping.FlightShoppping;
import com.travelsky.ebuild.clientapi.FlightShoppping.FsFlightType;
import com.travelsky.ebuild.clientapi.FlightShoppping.HeaderInType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Input;
import com.travelsky.ebuild.clientapi.FlightShoppping.NFares;
import com.travelsky.ebuild.clientapi.FlightShoppping.NetFareType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Option;
import com.travelsky.ebuild.clientapi.FlightShoppping.OriginDestinationInfoType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Output;
import com.travelsky.ebuild.clientapi.FlightShoppping.PFares;
import com.travelsky.ebuild.clientapi.FlightShoppping.PSn;
import com.travelsky.ebuild.clientapi.FlightShoppping.PassengerType;
import com.travelsky.ebuild.clientapi.FlightShoppping.PricingSolutionType;
import com.travelsky.ebuild.clientapi.FlightShoppping.PsAvBindingType;
import com.travelsky.ebuild.clientapi.FlightShoppping.PsAvBinds;
import com.travelsky.ebuild.clientapi.FlightShoppping.PubFareType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Request;
import com.travelsky.ebuild.clientapi.FlightShoppping.Result;
import com.travelsky.ebuild.clientapi.FlightShoppping.SpecificCarrierInfo;
import com.travelsky.ebuild.clientapi.FlightShoppping.TaxType;
import com.travelsky.ebuild.clientapi.FlightShoppping.Taxes;
import com.travelsky.ebuild.clientapi.FlightShoppping.Term;
import com.travelsky.ebuild.clientapi.FlightShoppping.TravelPreferencesType;
import com.travelsky.ebuild.clientapi.FlightShoppping.YFares;
import com.travelsky.ibe.client.FD;
import com.travelsky.ibe.client.FDResult;
import com.travelsky.ibe.client.FF;
import com.travelsky.ibe.client.FFResult;
import com.travelsky.ibe.client.pnr.BookContact;
import com.travelsky.ibe.client.pnr.PAT;
import com.travelsky.ibe.client.pnr.PATResult;
import com.travelsky.ibe.client.pnr.PNRAirSeg;
import com.travelsky.ibe.client.pnr.PnrManage;
import com.travelsky.ibe.client.pnr.SellSeat;
import com.travelsky.util.QDateTime;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum;
import com.viptrip.ibeserver.config.IbeConstants;
import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.ibeserver.model.Request_IBEData;
import com.viptrip.ibeserver.service.ReadIBEDataService;
import com.viptrip.resource.Const;
import com.viptrip.wetrip.wsclient.GetIBEBigplaitImplDelegate;
import com.viptrip.wetrip.wsclient.GetIBEBigplaitImplService;
import com.viptrip.util.DateUtil;
import com.viptrip.util.JSON;
import com.viptrip.wetrip.dao.TAcUserDao;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.TAcClass;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TCangwei;
import com.viptrip.wetrip.entity.TPreferentialRules;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.Request_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.ReqData_GetReschedueFlightList;
import com.viptrip.wetrip.model.flight.type.CabinType;
import com.viptrip.wetrip.model.flight.type.TripType;
import com.viptrip.wetrip.service.IComService;
import com.viptrip.wetrip.service.PolicyManageService;
import com.viptrip.wetrip.vo.CompanyFlightCabinTypeCode;

@Service
public class ReadIBEDataImpl implements ReadIBEDataService {

    private static Logger logger = LoggerFactory.getLogger(ReadIBEDataImpl.class);

    @Resource
    private ComDao comDao;

    @Resource
    IComService comService;
    
    @Autowired
    private TAcUserDao itd;
    
    @Resource
    private PolicyManageService policyManageService;

    public static void main(String[] args) {
        Long time1 = System.currentTimeMillis();
        ReadIBEDataImpl ridi = new ReadIBEDataImpl();

        // ridi.getBaseFlightKey("UN_ADT_Y_PEK_SHA_20170505|HU7609#Y-ELSE");
        List<DispplayTrip> dt = ridi.getIBEData("PEK", "SZX", "20170823", "ALL", "");
        Long time2 = System.currentTimeMillis();
        System.out.println("dt.size:" + dt.size() + "|用时：" + (time2 - time1));
        DispplayTrip d = new DispplayTrip();

        for (int i = 0; i < dt.size(); i++) {
            d = dt.get(i);
            System.out.println(d.getAirline() + " " + d.getCangwei() + " " + d.getSeatsLeft() + " " + d.getStartdate()
                    + " " + d.getArrvitime() + " " + d.getPrice() + " " + d.getyPrice() + "  " + d.getPlanetype()+"  "+d.getyPrice()+"  "+d.getDiscountrate()
                    + "    --" + (StringUtils.isEmpty(d.getRulecode()) ? "RULECODE" : d.getRulecode())+"  "+d.getCangwei());//
            // /+" "+d.getyPrice());
        }
    }

    /**
     * 测试用
     */
    @Override
    public List<DispplayTrip> getIBEData(String sOCity, String sDCity, String sDate, String pricetype, String airlines) {
        String flightKey = sOCity + "_" + sDCity + "_" + sDate;
        logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " --->" + flightKey);
        List<DispplayTrip> trip = null;
        // @SuppressWarnings("unchecked")
        // List<DispplayTrip> trip = RedisCacheManager.get(flightKey,
        // List.class);
        if (trip == null) {
            trip = this.getIBEDataFromIBE(sOCity, sDCity, sDate, pricetype, "");
            // RedisCacheManager.save(flightKey, trip,
            // MyEnum.CacheKeepTime.五分钟.getValue());
        }   

        // 对航空公司做一下过滤
        if (StringUtil.isNotEmpty(airlines) && !"ALL".equals(airlines)) {
            List<DispplayTrip> trip_fillter = new ArrayList<DispplayTrip>();
            for (DispplayTrip tripobj : trip) {
                if (airlines.contains(tripobj.getAirline().substring(0, 2))) {
                    trip_fillter.add(tripobj);
                }
            }
            return trip_fillter;
        }
        return trip;
    }

    @Override
    public List<DispplayTrip> getIBEData(Request_GetFlightList para) {
        long userId = para.getUserId();
        ReqData_GetFlightList data = para.getData();
        String baseFlightKey = this.getBaseFlightKey(data);
        StringBuffer mapKey = new StringBuffer().append(this.getLeftFlightKey(data));
        String pricetype = "ALL";
        logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " --->" + baseFlightKey);
        @SuppressWarnings("unchecked")
        List<DispplayTrip> trip = null;
        RedisCacheManager.get(baseFlightKey, List.class);
        if (trip == null || trip.size() == 0) {
            trip = this.getIBEDataFromIBE(baseFlightKey.split("_")[0], baseFlightKey.split("_")[1],
                    baseFlightKey.split("_")[2], pricetype, "");
            RedisCacheManager.save(baseFlightKey, trip, MyEnum.CacheKeepTime.三分钟.getValue());
        }

        // 舱位等级过滤
        trip = this.filterByCabinType(trip, data.getCabinType());
        //匹配过滤大客户协议价,此处从请求userId所在企业获取相关企业的大客户政策
        trip=this.filterPreferentialRules(para.getUserId(), trip);
        
        // 当前预定人高级舱位不可查过滤
        filterDiscount(userId, trip);
        
        // 排序、获取每个航班的最低价数据
        trip = this.paixuTrips(trip, 0);
        // 列表Key 赋值
        for (int i = 0; i < trip.size(); i++) {
            trip.get(i).setMapKey(mapKey.toString() + "|" + trip.get(i).getAirline());
        }
        return trip;
    }

    /**
     * 当前预定人高级舱位不可查过滤
     * @param userId
     * @param trip
     */
    private void filterDiscount(long userId, List<DispplayTrip> trip) {
        TAcUser user=this.getUserByLoginname(userId);
        if (user.getHighestDiscount() != null) {
            for (int it = trip.size() - 1; it >= 0; it--) {
                // System.out.println(((TDispplaytrip)
                // lst.get(it)).getDiscountrate() / 10);
                if (((DispplayTrip) trip.get(it)).getDiscountrate()
                        .doubleValue() / 10 > user.getHighestDiscount()
                        .doubleValue())
                    // 如果当前的折扣数（8.5）大于该员工可最高折扣（7.0）
                    trip.remove(it); // 将当前元素删除
            }
        }
    }

    /*
     * flightKey OW_ADT_A_PEK_SHA_2017-04-15|CA1234 (non-Javadoc)
     * 
     * @see
     * com.viptrip.ibeserver.service.ReadIBEDataService#getIBEData(java.lang
     * .String)
     */
    @Override
    public List<DispplayTrip> getIBEData(String flightKey,Long userid) {
        //logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " --->" + flightKey);
        String pricetype = "ALL";
        // OW_ADT_A_PEK_SHA_2017-04-15|CA1234

        Request_IBEData req = this.getBaseFlightKey(flightKey);
        String baseFlightKey = req.getsDCity() + "_" + req.getsOCity() + "_" + req.getsDate();
        String bookFlightKey = req.getsDCity() + "_" + req.getsOCity() + "_" + req.getsDate() + "_" + req.getAirlines();
        logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " --->" + baseFlightKey);
        @SuppressWarnings("unchecked")
        List<DispplayTrip> trip = RedisCacheManager.get(baseFlightKey, List.class);
        if (trip == null || trip.size() == 0) {
            trip = this.getIBEDataFromIBE(baseFlightKey.split("_")[0], baseFlightKey.split("_")[1],
                    baseFlightKey.split("_")[2].replace("-", ""), pricetype, "");
            RedisCacheManager.save(baseFlightKey, trip, MyEnum.CacheKeepTime.五分钟.getValue());
        }
        // 航班过滤
        trip = airlineFilter(req, trip);
        // 舱位等级过滤
        trip = this.filterByCabinType(trip, req.getCabinType());
        // 匹配过滤大客户协议价
        trip=this.filterPreferentialRules(userid, trip);
        // 当前预定人高级舱位不可查过滤
        filterDiscount(userid, trip);
        // 排序 sortCase(0, tripList);
        trip = this.sortCase(0, trip);
        //列表Key 赋值
        for (int i = 0; i < trip.size(); i++) {
            trip.get(i).setMapKey(this.getDetailFlightListKey(flightKey.split("\\|")[0], trip.get(i)));
        }
        return trip;
    }
   
    /**
     * 获取当前登录人信息
     * @param userId
     * @return
     */
    public TAcUser getUserByLoginname(long  userId){
        TAcUser tAcUser = itd.findOne(userId);
        return tAcUser;
    }
    
    /**
     * 过滤大客户协议规则
     * @param userid
     * @param entity
     * @param fromFlag  0：查航班    1：预定
     * @return
     * @throws Exception
     */
    public List<DispplayTrip> filterPreferentialRules(Long userid,List<DispplayTrip> entitys){
        //先把企业的优惠规则从缓存读出来
        TAcUser loginUser=getUserByLoginname(userid);
        // 先把企业的优惠规则从缓存读出来
        @SuppressWarnings("unchecked")
        List<TPreferentialRules> ruluesList=RedisCacheManager.get(Const.CACHE_PREFERENTIAL_RULES, List.class);
        if(ruluesList==null || ruluesList.size()==0){
            ruluesList=comService.quyRules();
            RedisCacheManager.save(Const.CACHE_PREFERENTIAL_RULES,ruluesList,MyEnum.CacheKeepTime.一天.getValue());
        }
        //当前登录人企业的大客户规则
        List<TPreferentialRules> rules  =new ArrayList<TPreferentialRules>();
        for(TPreferentialRules rule_:ruluesList){
            if(rule_.getOrgid().equals(loginUser.getOrgid())){
                rules.add(rule_);
            }
        }
        List<DispplayTrip>  tripsResult=new ArrayList<DispplayTrip>();
        for (int i = 0; i < entitys.size(); i++) {
           DispplayTrip dtreturn = entitys.get(i);
           String orgcity = dtreturn.getOrgcity();
           String detcity = dtreturn.getDetcity();
           List<DispplayTrip> trips=this.convert_v2(dtreturn, orgcity, detcity, rules);
           if(trips.size()>0){
               for (int j = 0; j <trips.size(); j++) {
                   tripsResult.add(trips.get(j));
               }
           }
        }

        return tripsResult;
    }
    
    
    /**
     * 根据运价类型返回多个价格不同的对象
     * @param entity
     * @param strOrgcity
     * @param strDetcity
     * @param rules
     * @return
     * @deprecated
     */
    public List<DispplayTrip> convert_v0(DispplayTrip entity, String strOrgcity, String strDetcity,List<TPreferentialRules> rules) {
        List<DispplayTrip> dispplayTripList = new ArrayList<DispplayTrip>();
        DispplayTrip dtreturn = new DispplayTrip();
        dtreturn.setAirline(entity.getAirline());
        dtreturn.setAirlineshare(entity.getAirlineshare());
        dtreturn.setArrvidate(entity.getArrvidate());
        dtreturn.setArrvitime(DateUtil.date2Str(entity.getArrvidate(), "HH:mm"));

        dtreturn.setCangwei(entity.getCangwei());
        dtreturn.setDetcity(strDetcity);
        dtreturn.setDetterm(entity.getDetterm());
        dtreturn.setDiscountrate(entity.getDiscountrate());
        dtreturn.setEnddate(entity.getEnddate());
        dtreturn.setFood(entity.getFood());
        dtreturn.setFueltax(entity.getFueltax());
        dtreturn.setOrgcity(strOrgcity);
        dtreturn.setOrgterm(entity.getOrgterm());
        dtreturn.setPlanetype(entity.getPlanetype());
        dtreturn.setPrice(new Double(java.lang.Math.round(entity.getPrice() / 10) * 10));
        dtreturn.setRulecode(entity.getRulecode());
        dtreturn.setStartdate(entity.getStartdate());
        dtreturn.setStarttime(DateUtil.date2Str(entity.getStartdate(), "HH:mm"));
        dtreturn.setTaxfee(entity.getTaxfee());
        dtreturn.setSeatsLeft(entity.getSeatsLeft());
        dtreturn.setFfstr(entity.getFfstr()); // 把经停信息传递
        dtreturn.setyPrice(entity.getyPrice());
        dtreturn.setCbClass(entity.getCbClass());
        dtreturn.setPubPrice(entity.getPubPrice());
        dtreturn.setRebatePrice(entity.getRebatePrice());
        dtreturn.setAgreementTypeCode(entity.getAgreementTypeCode());
        // 是否有企业协议价
        dtreturn.setFfstr(entity.getFfstr());
        // 公布运价不为空  
        if (dtreturn.getPubPrice() != null) {
            double dbldiscount = discount(dtreturn, strOrgcity, strDetcity,rules);
            //有企业协议价
            if( dbldiscount < 1 && dbldiscount > 0){
                DispplayTrip dtreturn_ = (DispplayTrip) dtreturn.clone();
                // 根据舱位等级，获取舱位的全价，并使用全价去乘以企业协议价的折扣，才是最终该企业员工可享受的价格。 李荣春
                dtreturn_.setDiscountrate(dbldiscount);
                dtreturn_.setPrice(new Double(java.lang.Math.round((dtreturn.getPubPrice() * dbldiscount) / 10) * 10));// 把价格取到10尾数
                dtreturn_.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.大客户协议价.getValue());
                dispplayTripList.add(dtreturn_);
            }
        }
        // 公布运价不为空 
        if (dtreturn.getPubPrice() != null && dtreturn.getPubPrice().doubleValue()==dtreturn.getPrice().doubleValue() ) {
            DispplayTrip dtreturn_ =(DispplayTrip) dtreturn.clone();
            double z_discount = Math.floor((dtreturn_.getPrice() / dtreturn.getPubPrice()) * 100);
            dtreturn_.setDiscountrate(z_discount);
            dtreturn_.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.公布运价.getValue());
            dispplayTripList.add(dtreturn_);
        }
        //公布运价为空
        if(dtreturn.getPubPrice()==null || dtreturn.getPubPrice().doubleValue()==0){
            dtreturn.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.其他.getValue());
            dispplayTripList.add(dtreturn);
        }

        return dispplayTripList;
    }
    
    /**
     * 根据运价类型返回多个价格不同的对象,配合从航班数据获取运价的改动
     * @param entity
     * @param strOrgcity
     * @param strDetcity
     * @param rules
     * @return
     */
    public List<DispplayTrip> convert_v2(DispplayTrip entity, String strOrgcity, String strDetcity,
            List<TPreferentialRules> rules) {
        List<DispplayTrip> dispplayTripList = new ArrayList<DispplayTrip>();
        DispplayTrip dtreturn = new DispplayTrip();
        dtreturn.setAirline(entity.getAirline());
        dtreturn.setAirlineshare(entity.getAirlineshare());
        dtreturn.setArrvidate(entity.getArrvidate());
        dtreturn.setArrvitime(DateUtil.date2Str(entity.getArrvidate(), "HH:mm"));
        dtreturn.setCangwei(entity.getCangwei());  
        dtreturn.setDetcity(strDetcity);
        dtreturn.setDetterm(entity.getDetterm());
        dtreturn.setDiscountrate(entity.getDiscountrate());
        dtreturn.setEnddate(entity.getEnddate());
        dtreturn.setFood(entity.getFood());
        dtreturn.setFueltax(entity.getFueltax());
        dtreturn.setOrgcity(strOrgcity);
        dtreturn.setOrgterm(entity.getOrgterm());
        dtreturn.setPlanetype(entity.getPlanetype());
        dtreturn.setPrice(new Double(java.lang.Math.round(entity.getPrice() / 10) * 10));
        dtreturn.setRulecode(entity.getRulecode());
        dtreturn.setStartdate(entity.getStartdate());
        dtreturn.setStarttime(DateUtil.date2Str(entity.getStartdate(), "HH:mm"));
        dtreturn.setTaxfee(entity.getTaxfee());
        dtreturn.setSeatsLeft(entity.getSeatsLeft());
        dtreturn.setFfstr(entity.getFfstr()); // 把经停信息传递
        dtreturn.setyPrice(entity.getyPrice());
        dtreturn.setAprice(entity.getAprice());
        dtreturn.setFprice(entity.getFprice());
        dtreturn.setCbClass(entity.getCbClass());
        dtreturn.setPubPrice(entity.getPubPrice());
        dtreturn.setRebatePrice(entity.getRebatePrice());
        dtreturn.setAgreementTypeCode(entity.getAgreementTypeCode());
        // 是否有企业协议价
        dtreturn.setFfstr(entity.getFfstr());
//        if(dtreturn.getAirline().contains("ZH") &&  dtreturn.getCangwei().contains("M")){
//            System.out.println();
//        }
        // 公布运价不为空
        if (dtreturn.getPubPrice() != null) {
            double dbldiscount = discount(dtreturn, strOrgcity, strDetcity,rules);// discount(dtreturn,
                                                                                  // strOrgcity,
                                                                                  // strDetcity,rules);
            // 有企业协议价
            if (dbldiscount < 1 && dbldiscount > 0) {
                DispplayTrip dtreturn_ = (DispplayTrip) dtreturn.clone();
                // 根据舱位等级，获取舱位的全价，并使用全价去乘以企业协议价的折扣，才是最终该企业员工可享受的价格。 李荣春
                dtreturn_.setDiscountrate(dbldiscount);
                dtreturn_.setPrice(new Double(java.lang.Math.round((dtreturn.getPubPrice() * dbldiscount) / 10) * 10));// 把价格取到10尾数
                dtreturn_.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.大客户协议价.getValue());
                dispplayTripList.add(dtreturn_);
            }
        }
        // 公布运价不为空,公布运价=舱位价格
        if (dtreturn.getPubPrice() != null && dtreturn.getPubPrice().doubleValue() == dtreturn.getPrice().doubleValue()) {
            DispplayTrip dtreturn_ = (DispplayTrip) dtreturn.clone();
            dtreturn_.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.公布运价.getValue());
            dispplayTripList.add(dtreturn_);
        }
        // 公布运价不为空,公布运价!=舱位价格 
        if (dtreturn.getPubPrice() != null && dtreturn.getPubPrice().doubleValue() != dtreturn.getPrice().doubleValue()) {
            DispplayTrip dtreturn_ = (DispplayTrip) dtreturn.clone();
            dispplayTripList.add(dtreturn_);
            
            DispplayTrip dtreturn_2 = (DispplayTrip) dtreturn.clone();
            dtreturn_2.setPrice(dtreturn.getPubPrice());
            dtreturn_2.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.公布运价.getValue());
            dispplayTripList.add(dtreturn_2);
        }
        // 公布运价为空
        if (dtreturn.getPubPrice() == null || dtreturn.getPubPrice().doubleValue() == 0) {
            if(dtreturn.getyPrice()!=null){
                double z_discount = Math.floor((dtreturn.getPrice() / dtreturn.getyPrice()) * 100);
                dtreturn.setDiscountrate(z_discount);
            }
            dispplayTripList.add(dtreturn);
        }
        return dispplayTripList;
    }
    
    @Override
    public DispplayTrip getIBEDataForBook(String flightKey,Long userid) {
        logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " ---> flightKey：" + flightKey);
        String pricetype = "ALL";
        // OW_ADT_A_PEK_SHA_2017-04-15|CA1234
        Request_IBEData req = this.getBaseFlightKey(flightKey);
        String baseFlightKey = req.getsDCity() + "_" + req.getsOCity() + "_" + req.getsDate();
        // String
        // bookFlightKey=req.getsDCity()+"_"+req.getsOCity()+"_"+req.getsDate()+"_"+req.getAirlines();
        // logger.info("  "+DateUtil.date2Str(new Date(),
        // "yyyy-MM-dd HH:mm:ss")+" ---> baseFlightKey：" +baseFlightKey);
        @SuppressWarnings("unchecked")
        List<DispplayTrip> trip = RedisCacheManager.get(baseFlightKey, List.class);
        if (trip == null || trip.size() == 0) {
            trip = this.getIBEDataFromIBE(req.getsDCity(), req.getsOCity(), req.getsDate(), pricetype, "");
            RedisCacheManager.save(baseFlightKey, trip, MyEnum.CacheKeepTime.五分钟.getValue());
        }
        
        // 航班过滤
        trip = airlineFilter(req, trip);
        // 舱位等级过滤
        trip = this.filterByCabinType(trip, req.getCabinType());
        //  匹配过滤大客户协议价
        trip=this.filterPreferentialRules(userid, trip);
        // 排序 sortCase(0, tripList);
        trip = this.sortCase(0, trip);
        DispplayTrip tripResult=null;
        for (int i = 0; i <trip.size(); i++) {
            if (StringUtils.isNotEmpty(req.getAgreementTypeCode())) {
                if (trip.get(i).getAgreementTypeCode().equals(req.getAgreementTypeCode())) {
                    tripResult = trip.get(i);
                }
            }
        }
        if(tripResult!=null){
            String detailFlightListKey = this.getDetailFlightListKey(flightKey.split("\\|")[0],tripResult);
            if(!flightKey.equals(detailFlightListKey)){
                logger.error("传入：["+flightKey+"];  生成：["+detailFlightListKey+"]");
            }else{
                tripResult.setMapKey(flightKey);
            }
        }
        return tripResult;
    }

    /**
     * 根据req条件过滤
     * 
     * @param req
     * @param trip
     * @return
     */
    public List<DispplayTrip> airlineFilter(Request_IBEData req, List<DispplayTrip> trip) {
        List<DispplayTrip> trip_ = new ArrayList<DispplayTrip>();
        for (int i = 0; i < trip.size(); i++) {
            boolean needadd = false;
            if (trip.get(i).getAirline().contains(req.getAirlines())) {
                if (StringUtil.isNotEmpty(req.getCangwei())) {
                    if (trip.get(i).getCangwei().contains(req.getCangwei())) {
                        needadd = true;
                    }
                } else {
                    needadd = true;
                }
            }
            if (needadd) {
                trip_.add(trip.get(i));
            }
        }
        return trip_;
    }

    /**
     * 按照仓位过滤list
     * 
     * @param list
     *            List<DispplayTrip>类型
     * @param cabinType
     *            仓位类型
     * @return
     */
    private List<DispplayTrip> filterByCabinType(List<DispplayTrip> list, Integer cabinType) {
        if (null != cabinType && CabinType.Unlimited.getCode() != cabinType) {
            List<CompanyFlightCabinTypeCode> clist = new ArrayList<>();
            List<Object[]> qList = comDao
                    .queryForList("select classtype as classtype,aircompany as aircompany,classcode as classcode from TAcClass group by classtype,aircompany,classcode");
            if (null != qList && qList.size() > 0) {
                for (Object[] l : qList) {
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
                String airline = ele.getAirline().substring(0, 2);
                String cangwei = ele.getCangwei();
                if (CabinType.BusinessAndFirstClass.getCode() == cabinType) {
                    if (!(clist.contains(new CompanyFlightCabinTypeCode(2, airline, cangwei)) || clist
                            .contains(new CompanyFlightCabinTypeCode(3, airline, cangwei)))) {
                        iterator.remove();
                        continue;
                    }
                }
                if (CabinType.EconomyClass.getCode() == cabinType) {
                    if (!clist.contains(new CompanyFlightCabinTypeCode(CabinType.EconomyClass.getCode(), airline,
                            cangwei))) {
                        iterator.remove();
                        continue;
                    }
                }
                if (CabinType.BusinessClass.getCode() == cabinType) {
                    if (!clist.contains(new CompanyFlightCabinTypeCode(CabinType.BusinessClass.getCode(), airline,
                            cangwei))) {
                        iterator.remove();
                        continue;
                    }
                }
                if (CabinType.FirstClass.getCode() == cabinType) {
                    if (!clist
                            .contains(new CompanyFlightCabinTypeCode(CabinType.FirstClass.getCode(), airline, cangwei))) {
                        iterator.remove();
                        continue;
                    }
                }
            }
        }
        return list;
    }

    /**
     * 排序
     * 
     * @param ldtt
     * @param by
     * @param flightCitys
     * @return
     */
    public List<DispplayTrip> paixuTrips(List<DispplayTrip> ldtt, int by) {
        // 排序逻辑
        Map<String, List<DispplayTrip>> allDispplayTripMap = new HashMap<String, List<DispplayTrip>>();
        // 排序，按照航班号分组
        for (int i = 0; i < ldtt.size(); i++) {
            DispplayTrip trip = ldtt.get(i);
            String airline = trip.getAirline().split("\\$")[0];
            List<DispplayTrip> tripList = allDispplayTripMap.get(airline);
            if (tripList == null) {
                tripList = new ArrayList<DispplayTrip>();
                tripList.add(trip);
                allDispplayTripMap.put(airline, tripList);
            } else {
                tripList.add(trip);
                allDispplayTripMap.put(airline, tripList);
            }
        }
        // 排序，按照给定的方式 排序
        Iterator<String> iteratorKey = allDispplayTripMap.keySet().iterator();
        while (iteratorKey.hasNext()) {
            String key_ = iteratorKey.next();
            List<DispplayTrip> tripList = allDispplayTripMap.get(key_);
            tripList = sortCase(0, tripList);
            allDispplayTripMap.put(key_, tripList);
        }

        // 排序，最外层再排序，排哪个航班的放第一个
        List<DispplayTrip> tripList_2 = new ArrayList<DispplayTrip>();
        Iterator<String> iteratorKey2 = allDispplayTripMap.keySet().iterator();
        while (iteratorKey2.hasNext()) {
            String key_ = iteratorKey2.next();
            List<DispplayTrip> tripList = allDispplayTripMap.get(key_);
            tripList_2.add(tripList.get(0));
        }
        // 再排序一次
        tripList_2 = sortCase(0, tripList_2);
        return tripList_2;
    }

    public List<DispplayTrip> sortCase(int by, List<DispplayTrip> tripList) {
        Comparator<Object> comparator = null;
        switch (by) {
        case 0: // 0-按价格升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    return a2.getPrice().compareTo(b2.getPrice());
                }
            };
            break;
        case 1: // 1-按价格降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    return b2.getPrice().compareTo(a2.getPrice());
                }
            };
        case 2: // 2-按时间升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    return a2.getPrice().compareTo(b2.getPrice());
                }
            };
            break;
        case 3: // 3-按时间降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    DispplayTrip a2 = (DispplayTrip) a;
                    DispplayTrip b2 = (DispplayTrip) b;
                    return b2.getPrice().compareTo(a2.getPrice());
                }
            };
            break;
        }
        try {
            Collections.sort(tripList, comparator);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tripList;
    }

    /**
     * 生成KEY左半部分-OW_ADT_C_PEK_SHA_20170504
     * 
     * @param para
     * @author wjt
     * @return
     */
    @Override
    public String getLeftFlightKey(ReqData_GetFlightList para) {
        // OW/RT行程类型
        int tripType = para.getTripType().intValue();
        // 如果是多个乘机人里全是儿童，就赋值儿童，如果有一个成人，就赋值成人。
        List<ReqData_GetFlightList_Passenger> passengers = para.getPassenger();
        // 航程信息
        List<ReqData_GetFlightList_TripInfo> tripInfo2 = para.getTripInfo();
        // 舱位
        Integer cabinType = para.getCabinType();
        
        return getLeftFlightKeys(tripType, passengers, tripInfo2, cabinType);
    }

    /**
     * 生成KEY左半部分-OW_ADT_C_PEK_SHA_20170504
     * 
     * @param para
     * @author wjt
     * @return
     */
    @Override
    public String getReschedueLeftFlightKey(ReqData_GetReschedueFlightList para) {
        // OW/RT行程类型
        int tripType = para.getTripType().intValue();
        // 如果是多个乘机人里全是儿童，就赋值儿童，如果有一个成人，就赋值成人。
        List<ReqData_GetFlightList_Passenger> passengers = para.getPassenger();
        // 航程信息
        List<ReqData_GetFlightList_TripInfo> tripInfo2 = para.getTripInfo();
        // 舱位
        Integer cabinType = para.getCabinType();
        
        return getLeftFlightKeys(tripType, passengers, tripInfo2, cabinType);
    }
    private String getLeftFlightKeys(int tripType, List<ReqData_GetFlightList_Passenger> passengers,
            List<ReqData_GetFlightList_TripInfo> tripInfo2, Integer cabinType) {
        // 示例：RT_ADT_A_PEK_SHA_2017-04-15*2017-04-25|CA1234#F1-PUBLIC*CA1235#F-AGREEMENT_MU538#C-PRIVATE
        StringBuffer result = new StringBuffer();
        if (tripType == TripType.OneWay.getCode().intValue()) {
            result.append(MyEnum.TripTypeCode.单程.getValue());
        } else if (tripType == TripType.ComeAndGo.getCode().intValue()) {
            result.append(MyEnum.TripTypeCode.往返.getValue());
        } else if (tripType == TripType.Connecting.getCode().intValue()) {
            result.append(MyEnum.TripTypeCode.联程.getValue());
        }

        String passengerTypeCodeStr = MyEnum.PassengerTypeCode.成人.getValue();
       
        if (passengers != null) {
            for (int i = 0; i < passengers.size(); i++) {
                ReqData_GetFlightList_Passenger passenger = passengers.get(i);
                if (passenger.getType().intValue() == MyEnum.PassengerTypeCodeInt.成人.getValue()) {
                    passengerTypeCodeStr = MyEnum.PassengerTypeCode.成人.getValue();// 乘客类型
                    break;
                }
                if (i == passengers.size() - 1) {
                    passengerTypeCodeStr = MyEnum.PassengerTypeCode.儿童.getValue();// 乘客类型
                }
            }
        }
        result.append("_" + passengerTypeCodeStr);// 乘客类型
        
        if (CabinType.BusinessAndFirstClass.getCode().intValue() == cabinType.intValue()) {
            result.append("_" + MyEnum.CabinTypeCode.头等舱公务舱.getValue());
        } else if (CabinType.BusinessClass.getCode().intValue() == cabinType.intValue()) {
            result.append("_" + MyEnum.CabinTypeCode.公务舱.getValue());
        } else if (CabinType.FirstClass.getCode().intValue() == cabinType.intValue()) {
            result.append("_" + MyEnum.CabinTypeCode.头等舱.getValue());
        } else if (CabinType.EconomyClass.getCode().intValue() == cabinType.intValue()) {
            result.append("_" + MyEnum.CabinTypeCode.经济舱.getValue());
        } else if (CabinType.Unlimited.getCode().intValue() == cabinType.intValue()) {
            result.append("_" + MyEnum.CabinTypeCode.不限.getValue());
        }
        
        result.append("_" + tripInfo2.get(0).getDepAirport());
        result.append("_" + tripInfo2.get(0).getArrAirport());

        for (ReqData_GetFlightList_TripInfo tripInfo : tripInfo2) {
            result.append("_" + tripInfo.getDate());
        }
        return result.toString();
    }

    /**
     * 生成KEY部分-RT_ADT_A_PEK_SHA_2017-04-15*2017-04-25
     * |CA1234#F1-PUBLIC*CA1235#F-AGREEMENT_MU538#C-PRIVATE
     * 
     * @param para
     * @author wjt
     * @return
     */
    @Override
    public String getDetailFlightListKey(String leftKey, DispplayTrip trip) {
        StringBuffer flightListKey = new StringBuffer();
        flightListKey.append(leftKey);
        flightListKey.append("|");
        flightListKey.append(trip.getAirline());
        flightListKey.append("#");
        flightListKey.append(trip.getCangwei());
        flightListKey.append("-");
        flightListKey.append(trip.getAgreementTypeCode());
        return flightListKey.toString();
    }

    @Override
    public String getBaseFlightKey(ReqData_GetFlightList para) {
        String sOCity = para.getTripInfo().get(0).getDepAirport();
        String sDCity = para.getTripInfo().get(0).getArrAirport();
        String sDate = para.getTripInfo().get(0).getDate();
        return sOCity + "_" + sDCity + "_" + sDate;
    }

    @Override
    public String getReschedueBaseFlightKey(ReqData_GetReschedueFlightList para) {
        String sOCity = para.getTripInfo().get(0).getDepAirport();
        String sDCity = para.getTripInfo().get(0).getArrAirport();
        String sDate = para.getTripInfo().get(0).getDate();
        return sOCity + "_" + sDCity + "_" + sDate;
    }
    
    /**
     * 转换请求数据参数对象 暂时未考虑多程
     * 
     * @param reqKeystr
     *            OW_ADT_A_PEK_SHA_2017-04-15|CA1234///示例：RT_ADT_A_PEK_SHA_2017-
     *            04-15*2017-04-25|CA1234#F1-PUBLIC*CA1235#F-AGREEMENT_MU538#C-
     *            PRIVATE
     * @return
     */
    @Override
    public Request_IBEData getBaseFlightKey(String reqKeystr) {
        Request_IBEData req = new Request_IBEData();
        String[] split = reqKeystr.split("\\|");
        String[] leftvalues = split[0].split("_");
        String cabinType = leftvalues[2];
        // 舱位
        if (cabinType.equals(MyEnum.CabinTypeCode.头等舱公务舱.getValue())) {
            req.setCabinType(CabinType.BusinessAndFirstClass.getCode().intValue());
        } else if (cabinType.equals(MyEnum.CabinTypeCode.公务舱.getValue())) {
            req.setCabinType(CabinType.BusinessClass.getCode().intValue());
        } else if (cabinType.equals(MyEnum.CabinTypeCode.头等舱.getValue())) {
            req.setCabinType(CabinType.FirstClass.getCode().intValue());
        } else if (cabinType.equals(MyEnum.CabinTypeCode.经济舱.getValue())) {
            req.setCabinType(CabinType.EconomyClass.getCode().intValue());
        } else if (cabinType.equals(MyEnum.CabinTypeCode.不限.getValue())) {
            req.setCabinType(CabinType.Unlimited.getCode().intValue());
        }

        req.setsDCity(leftvalues[3]);
        req.setsOCity(leftvalues[4]);
        req.setsDate(leftvalues[5]);
        String rightStr = split[1];
        if (StringUtils.isNotEmpty(rightStr)) {
            String[] rightvalues = rightStr.split("\\*");
            String mainAirlines = rightvalues[0];
            if (mainAirlines.contains("#")) {
                req.setAirlines(mainAirlines.substring(0, mainAirlines.indexOf("#")));
                if (mainAirlines.contains("-")) {
                    req.setCangwei(mainAirlines.substring(mainAirlines.indexOf("#") + 1, mainAirlines.indexOf("-")));
                    req.setAgreementTypeCode(mainAirlines.substring(mainAirlines.indexOf("-") + 1,
                            mainAirlines.length()));
                }
            } else {
                req.setAirlines(mainAirlines);
            }
        }
        return req;
    }

    /**
     * 调用IBE查询航班信息
     * 
     * @param sOCity
     * @param sDCity
     * @param sDate
     * @param pricetype
     * @param airlines
     * @return
     */
    private List<DispplayTrip> getIBEDataFromIBE(String sOCity, String sDCity, String sDate, String pricetype,
            String airlines) {
        if (sDate.contains("-")) {
            sDate = sDate.replace("-", "");
        }
        Long time1 = System.currentTimeMillis();
        logger.info("开始获取 航班信息" + time1);
//        StringBuffer keys = new StringBuffer().append("IBE_").append(sOCity).append("_").append(sDCity).append("_")
//                .append(sDate);
        
        List<DispplayTrip> disList = new ArrayList<DispplayTrip>();
//        String string = RedisCacheManager.get(keys.toString(), String.class);
//        System.out.println("ReadIBEDataImpl.getIBEDataFromIBE()==111111111111111111=="+string);
//        Result result= JSON.get().fromJson(RedisCacheManager.get(keys.toString(), String.class),Result.class);
//        Result result= (Result) unserizlize(RedisCacheManager.get(keys.toString(), byte[].class));
        Result result=null;
        long begin = System.currentTimeMillis();
//        if(result==null){
            // 初始化连接信息
            FlightShoppping fltshopping = new FlightShoppping();
            // (String office, String customno, String validationno)
            fltshopping.setAgentInfo(IbeConstants.getIbeOffice(), IbeConstants.getIbeCustomno(),
                    IbeConstants.getIbeValidationno());
            fltshopping.setAppName(IbeConstants.getIbeAppName());
            fltshopping.setConnectionInfo(IbeConstants.getIbeConnectionIP(), IbeConstants.getIbeConnectionPort());
    
            // 国内shopping 请求
            FareInterface service = new FareInterface();
            Input input = new Input();
            // 设置HeaderIn
            input.setHeaderInType(getHeaderInType());
            Request request = new Request();
            FlightShopRequestType flightShopRequestType = new FlightShopRequestType();
            // 设置OD
            Vector originDestinationInfo = new Vector();
            try {
                originDestinationInfo.add(getOriginDestinationInfoType(sOCity, sDCity, sDate, airlines));
            } catch (Exception e) {
                e.printStackTrace();
            }
            flightShopRequestType.setOriginDestinationInfo(originDestinationInfo);
            // 设置TravelPreferences
            flightShopRequestType.setTravelPreferencesType(getTravelPreferencesType());
            // 设置Option
            flightShopRequestType.setOption(getOption(pricetype));
            // 设置大客户编码 4月29日
            // flightShopRequestType.setSpecificCarrierInfo(getSpecificCarrierInfo());
            request.setFlightShopRequestType(flightShopRequestType);
            input.setRequest(request);
            service.setInput(input);
            
            Output out = new Output();
            logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-->  连接航信服务器并拿到数据：时间"
                    + (System.currentTimeMillis() - time1) + ";" + sOCity + "->" + sDCity);
            try {
                out = fltshopping.doFlightShopping(service);
            } catch (Exception e) {
                System.out.println("无可用航班");
                e.printStackTrace();
            }
    
            Long time2 = System.currentTimeMillis();
            logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-->  连接航信服务器并拿到数据："
                    + (time2 - time1));
            result = out.getResult();
            if (result == null) {
                return disList;
            } 
            // 异常处理
            //存缓存1分钟
//            System.out.println(result.toString());
            
//            System.out.println("ReadIBEDataImpl.getIBEDataFromIBE()==222222222222222==" + JSON.get().toJson(result));
//            RedisCacheManager.save(keys.toString(), JSON.get().toJson(result), MyEnum.CacheKeepTime.五分钟.getValue());
//            RedisCacheManager.save(keys.toString(), serialize(result.toString()), MyEnum.CacheKeepTime.一分钟.getValue());
//        }
        FlightShopResult fsre = result.getFlightShopResult(); // 获取FlightShopResult
        if (fsre == null) {// 异常处理
            return disList;
        }
        
        disList=parseIBEDATA(sOCity, sDCity, sDate, time1, disList, begin, fsre);
        return disList;
    }
    //序列化 
    public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    //反序列化
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        try {
            bis=new ByteArrayInputStream(byt);
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            logger.info("byt is null");
        }
        return null;
    }
    private List<DispplayTrip> parseIBEDATA(String sOCity, String sDCity, String sDate, Long time1,
            List<DispplayTrip> disList, long begin, FlightShopResult fsre) {
        Map<String, String> airportNameMap = RedisCacheManager.get(Const.APP_MAP_3CHAR_AIRPORTNAME, Map.class);
        Map<String, Double> cangweiPrice = new HashMap<String, Double>();
        Map<String,TAcClass> allFullTacClass=comService.getAllFullTacClass();
        
        DispplayTrip dispplayTrip = new DispplayTrip();
        DispplayTrip dispplayTrip1 = new DispplayTrip();
        
        String p_ftypevalue = "single"; // 行程类型 single or double .net中是由外部传入
        String ftype = "1"; // 航班类型 1或2 去程是1，回程是2
        String CCPriceList = "";// 航空公司各舱位价格列表
        DecimalFormat df = new DecimalFormat("#0");
        // 机场建设费
        String AirportTax = "0";
        // 燃油附加费用
        String fuelTax = "0";
        String _discontY = "0";
        String _priceY = "0";
        String _isorder = "1"; // 在.net中是否允许预订的标志，1430行开始的若干代码
        String _allPrice = "0";
        String Returnflight = "";
        String strres = ""; // 返回的重要字符串之一
        int FlightNum = 0; // 航班数量

        PFares pfares = fsre.getPFares();
        NFares nfares = fsre.getNFares();
        // List<String[]> pubFares=new ArrayList<String[]>();
        // key=PEK_HGH_CA_F
        // value=6000.000000_F_F(公布运价、舱位名称[M1归属为M]、舱位[同等舱位的全价舱])
        Map<String, String> pubFaresMap = new HashMap<String, String>();
        @SuppressWarnings("unchecked")
        Vector<PubFareType> pfaresVector = pfares.getPFare();
        Iterator<PubFareType> pubFareTypes = pfaresVector.iterator();
        
        HashMap<String, String> cityMap = new HashMap<String, String>();
        cityMap.put("PEK", "PEK_NAY");// "PEK", "PEK_NAY"
        cityMap.put("SHA", "SHA_PVG");// "SHA", "SHA_PVG"
        
        // rPH 编号，rcarr 承运人，rori始发地，des 目的地，amt 价格，fbc 运价基础，
        // bkClass 舱位，cbClass 舱位等级，currCode 货币类型，ruleNo 规则编号
        // PEK_HGH_CA_F_6000.000000_F_F
        while (pubFareTypes.hasNext()) {
            PubFareType pubobj = pubFareTypes.next();
            
            String ori = pubobj.getOri();
            String des = pubobj.getDes();
            
            String orgCityValue = cityMap.get(ori) == null ? ori : cityMap.get(ori);
            String detCityValue = cityMap.get(des) == null ? des : cityMap.get(des);

            String[] strOrgcitys = orgCityValue.split("_");
            String[] strDetcitys = detCityValue.split("_");
            
            for (int i = 0; i < strOrgcitys.length; i++) {
                for (int j = 0; j < strDetcitys.length; j++) {
                    pubFaresMap.put(strOrgcitys[i] + "_" + strDetcitys[j] + "_" + pubobj.getCarr() + "_" + pubobj.getFbc(),
                            pubobj.getAmt() + "_" + pubobj.getBkClass() + "_" + pubobj.getCbClass());
                }
            }
        }
        Vector sAV = fsre.getAvJourneys(); // 航班和舱位信息
        PsAvBinds sAVB = fsre.getPsAvBinds(); // 运价和航班舱位绑定情况信息
        PSn sPSN = fsre.getPSn(); // PricingSolution
        AvJourneys faj = new AvJourneys();
        float lowPrice = 100000;
        float lowPrice2 = 100000;
        if (sAV != null && sAV.size() > 0 && sAVB != null && sPSN != null) {
            faj = (AvJourneys) sAV.get(0); // FltShopAvJourney //只有第0个元素 //av信息
            Vector avj_List = faj.getAvJourney();
            AvailableJourneyType avjT = (AvailableJourneyType) avj_List.get(0); // 也是只有第0个元素

            // 将AVH 运价关联的数据存储到List<FltShopPsAvBind>
            // FltShopPsAvBind
            List<PsAvBindingType> _SEQlist = new ArrayList<PsAvBindingType>();
            Vector pab = sAVB.getPsAvBind();
            for (int i = 0; i < pab.size(); i++) {
                _SEQlist.add((PsAvBindingType) pab.get(i));
            }
            // 将运价数据存储到List<FltShopPsAvBind>
            List<PricingSolutionType> _sPSNlist = new ArrayList<PricingSolutionType>();
            Vector ps = sPSN.getPS();
            for (int i = 0; i < ps.size(); i++) {
                _sPSNlist.add((PricingSolutionType) ps.get(i));
            }
            
            ////价格类型是公布运价的仓位价格集合
            PricingSolutionType pst_ = new PricingSolutionType();
//          List<PricingSolutionType> pst_pub = new ArrayList<PricingSolutionType>();
              for (int e = 0; e < _sPSNlist.size(); e++) {
                  pst_ = _sPSNlist.get(e);
                  //价格类型是公布运价的仓位价格集合
                  if(pst_.getItiType()==0){
    //                  if(!pst_pub.contains(pst_)){
    //                      pst_pub.add(pst_);
    //                   }
                      FareComponentType yfctype2 =(FareComponentType)pst_.getFCs().getFC().get(0);
//                        FareComponentType yfctype2 = JSONObject.parseObject( pst_.getFCs().getFC().get(0).toString(),
//                              FareComponentType.class);
                      if(yfctype2.getFareBasis().length()==1){
                          String key = pst_.getFc().split(" ")[1]+"_"+yfctype2.getFareBasis().substring(0,1);
                          Double valueStr = Double.valueOf(pst_.getFc().split(" ")[4].replace("CNY", "").replace("END",""));
        //                  if(pst_.getFc().split(" ")[1].contains("FM")){
        //                      System.out.println(key+"___________________"+valueStr);
        //                  }
                          cangweiPrice.put(key,valueStr);
                      }
                  }
              }
            
            // 得到AVOPTV数据 .GetAvOptV
            Vector av_Shopping = avjT.getAvOpt();

            // 航班数量
            int flightAV = av_Shopping.size();
            // System.out.println("航班数量-----------"+flightAV);
            // 读取舱位所需要的信息
            // DataSet dssf =
            // FlightParams.get_flight_searchinfo(int.Parse(TBCid), departCode,
            // arriveCode, seachDate);
            // 这个好像是个配置方面的信息
            AvOpt fltV = new AvOpt();
            Vector _fltList = new Vector();
            FsFlightType flt = new FsFlightType();
            String _carrierFCode = "";
            String _carrierCode = "";
            String _carriername = "";
            String flightno = "";
            String _tpm = "";
            String departTime = "";
            String arriveTime = "";
            String departDate = "";
            String arrDate = "";
            String[] citysCode = new String[2];
            String[] citys = new String[2];
            String _price = "";
            String _yprice = "";
            Codeshare codeshare = new Codeshare();
            String shareClass = "";
            // String single_Flight = "";
            String planStyle = "";
            int stopnum = 0;
            Term Term = new Term();
            String BoardPointAT = "";
            String OffPointAT = "";
            String CarrierClass = "";
            int p_classgrade = 0;
            // String NullClass = "";
            String RPH = "";
            List<ClassType> _Classlist = new ArrayList<ClassType>();
            Vector v_class = new Vector();
            String _discont = "";
            ClassType fclass = new ClassType();
            String ShowAllPrice = "";
            boolean isClass = true;
            String seat = "";
            String code = "";
            String seat_Str = "";
            Vector smallClas = new Vector();
            Vector bkClass = new Vector();
            PsAvBindingType _psAVBind = new PsAvBindingType();
            PsAvBindingType pavbt = new PsAvBindingType();
            String cangwei = "";
            String seq = "";
            PricingSolutionType getFltShopPS = new PricingSolutionType();
            boolean brflag = false;
            PricingSolutionType pst = new PricingSolutionType();
            Taxes _taxs = new Taxes();
            List<TaxType> _Tax = new ArrayList<TaxType>();
            Vector tax = new Vector();
            TaxType taxt = new TaxType();
            FCs _fcs = new FCs();
            Vector _fc = new Vector();
            FareComponentType yfctype = new FareComponentType();
            YFares yfares = new YFares();
            Vector yfare = new Vector();
            String _yfcPrice = "";
            FFResult fresult = new FFResult();
            FF ff = new FF();
            String ffstr = "";
            // 循环航班
            for (int i = 0; i < av_Shopping.size(); i++) {
                ffstr = "";
                fltV = (AvOpt) av_Shopping.get(i);
                // GetFlt
                _fltList = fltV.getFlt();

                flt = (FsFlightType) _fltList.get(0); // 只有一个元素
                // 获取基本的航班信息 start
                // 航空公司+航班号
                _carrierFCode = flt.getAirline() + flt.getFltNo();
                // dispplayTrip.setAirline(_carrierFCode);
                // 航空公司简拼
                _carrierCode = flt.getAirline();
                // System.out.println("航空公司简拼---"+_carrierCode);
                // 航空公司名称
                _carriername = ""; // 根据代码获取中文航空公司名称；//FlightParams.getF1_siglecarriername(_carrierCode);
                // 通过转义表获取，略
                // 航班号
                flightno = flt.getFltNo();
                // 公里数
                _tpm = String.valueOf(flt.getTpm());
                // 出发时间
                departTime = flt.getDeptm();
                departTime = DateUtil.date2Str(DateUtil.strToDate(departTime, "HHmm"), "HH:mm");
                // 到达时间
                arriveTime = flt.getArrtm();
                arriveTime = DateUtil.date2Str(DateUtil.strToDate(arriveTime, "HHmm"), "HH:mm");
                departDate = flt.getDt();
                dispplayTrip = new DispplayTrip();
                dispplayTrip.setStartdate(DateUtil.strToDateENG(flt.getDt() + departTime, "ddMMMyyHH:mm"));
                arrDate = DateUtil.date2Str(
                        DateUtil.calcDate(DateUtil.strToDateENG(flt.getDt() + arriveTime, "ddMMMyyHH:mm"),
                                flt.getArrad() == null ? 0 : Integer.parseInt(flt.getArrad())), "yyyy-MM-dd");
                // 出发城市编码
                citysCode = new String[2];
                citysCode[0] = flt.getDep();// 出发
                citysCode[1] = flt.getArr();// 到达
                // 出发到达城市名称
                citys = new String[2];
                // 通过转义表获取，代码略
                // 获取基本的航班信息 end
                _price = "";
                _yprice = "";
                // 获取舱位是否有效的信息（共享航班或者航班是否过期）
                // 获取共享航班
                codeshare = flt.getCodeshare();
                shareClass = codeshare.getAirline();
                // 计划机型
                planStyle = flt.getDev();
                // 经停几次
                stopnum = flt.getStop();
                if (stopnum > 0) {
                    try {
                        fresult = ff.flightTime(flt.getAirline() + flt.getFltNo(),
                                DateUtil.strToDateENG(flt.getDt() + departTime, "ddMMMyyHH:mm"));
                        for (int cn = 0; cn < fresult.getCityNumber(); cn++) {
                            if (fresult.getFromTime(cn) != null && fresult.getToTime(cn) != null) {
                                System.out.println("经停城市：" + fresult.getCity(cn) + "到达时间" + fresult.getToTime(cn)
                                        + "出发时间" + fresult.getFromTime(cn));
                                ffstr = ffstr + fresult.getCity(cn) + "_"
                                        + DateUtil.date2Str(fresult.getToTime(cn), "yyyyMMdd HH:mm") + "_"
                                        + DateUtil.date2Str(fresult.getFromTime(cn), "yyyyMMdd HH:mm") + "^";
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(flt.getAirline() + flt.getFltNo() + "获取经停出错");

                        for (DispplayTrip ddt : disList) {
                            if (ddt.getAirline().equals(codeshare.getAirline() + "" + codeshare.getFltno())) {
                                ffstr = ddt.getFfstr();
                            }
                        }
                        //e.printStackTrace();
                    }
                    

                    //查询共享航班使用的航班号
                    String stopAirline=flt.getAirline() + flt.getFltNo();
                    if(StringUtils.isNotEmpty(shareClass)){
                        stopAirline=codeshare.getAirline()+codeshare.getFltno();
                    }
                    ffstr= this.getIBEFlightStopMessage(stopAirline, DateUtil.date2Str(DateUtil.strToDateENG(flt.getDt() + departTime, "ddMMMyyHH:mm"), "yyyy-MM-dd HH:mm"));
                    String newFfstr=ffstr.substring(2,ffstr.length());
                    String[] ffstrs=newFfstr.split("\\^");
                   
                    
                    for (int j = 0; j < ffstrs.length; j++) {
                        String ffstr_=ffstrs[j].substring(0,3);
                        String ffstr_value=airportNameMap.get(ffstr_);
//                        String ffstr_value=this.getDictNameByIdFromCache("AIRPORT", ffstr_);
                        
                        newFfstr=newFfstr.replaceAll(ffstr_, ffstr_value+(ffstr_));
                    }
                    if(StringUtils.isNotEmpty(newFfstr)){
                        ffstr=newFfstr;
                    }
                    //System.out.println("经停信息：______________:"+ffstr);
                    if(StringUtils.isEmpty(ffstr)){
                        ffstr="经停";
                    }
                    //System.out.println("经停几次---------" + stopnum+"__"+newFfstr);
                                    
                    
                }
                // 航站楼
                Term = flt.getTerm();
                // 出发航站楼
                BoardPointAT = Term.getDep();
                // dispplayTrip.setOrgterm(BoardPointAT);
                // System.out.println("出发航站楼-------"+BoardPointAT);
                // 到达航站楼
                OffPointAT = Term.getArr();
                // dispplayTrip.setDetterm(OffPointAT);
                // System.out.println("到达航站楼-------"+OffPointAT);
                // 航空公司 航班号 机型 经停 出发、到达航站楼 end

                // 变量初始化 start
                /*
                 * String currentClass = ""; float lowprice_Class =
                 * 999999999;//设定最低价格 String low_Class = ""; //设定最低舱位 String
                 * low_Discount = "0";//设定最低折扣 String Effective_Price = "";
                 * //拼接有效价格 || 隔开 String lowerClass = "";//最低折扣舱位信息 int
                 * is_XYPrice = 0; int _usecang = 0; String bg = "";//出发日期 时间
                 * 
                 * //bg = departDateTime; 需上面赋值，暂略 // 变量初始化 end
                 * 
                 * //是否有效舱位 boolean ISClassStatus = false;
                 */

                // 舱位等级
                CarrierClass = "";
                p_classgrade = 0;
                // String p_classgrade =
                // PublicFunction.GetQueryString_new("ClassLevelString", fsinfo,
                // '#', "^");//舱位等级
                // 在.net中，这个舱位等级是作为参数传入到这个函数中，值包括1，2，3三个，但用处不十分清楚，暂时搁置
                // 主要是不了解下面FlightParams.carrierClass的返回值是什么以及对后续程序有什么影响
                switch (p_classgrade) {
                case 3:
                    // CarrierClass =
                    // FlightParams.carrierClass(_carrierCode.ToUpper() +
                    // "top"); //头等？
                    break;
                case 2:
                    // CarrierClass =
                    // FlightParams.carrierClass(_carrierCode.ToUpper() +
                    // "Business"); //商务？
                    break;
                case 1:
                    // CarrierClass =
                    // FlightParams.carrierClass(_carrierCode.ToUpper() +
                    // "Economy"); //经济？
                    break;
                }
                // 得到Y舱价格
                // 从运价得到Y舱价格
                RPH = flt.getRPH();// RPH 根据此编码取运价
                // System.out.println("RPH-------------"+RPH);
                // 获取所有舱位 FltShopClass
                _Classlist = new ArrayList<ClassType>();
                v_class = flt.get_Class();
                for (int j = 0; j < v_class.size(); j++) {
                    _Classlist.add((ClassType) v_class.get(j));
                }
                // 把Vector转换为list
                _discont = "0"; // 暂不清楚什么用处
                // 循环所有舱位
                for (int c = 0; c < _Classlist.size(); c++) {
                    _discont = "99";
                    fclass = (ClassType) _Classlist.get(c);
                    // 显示所有价格
                    ShowAllPrice = "";
                    isClass = true;
                    // 验证舱位 头等舱、公务舱
                    if (CarrierClass != null && CarrierClass != "") { // 这个是上面216行返回的
                        if (CarrierClass.indexOf(fclass.getName().trim().toLowerCase()) == -1) {
                            // fclass.getName() 得到的是什么？疑惑
                            isClass = false;
                        }
                    }
                    if (fclass.getName().trim() != "-" && isClass == true) {
                        // 有效舱位
                        _price = "0";
                        // String Order_button = ""; //存储预订信息
                        // String _seatsinfo = "&nbsp;";//存储票量紧张 高端经济舱

                        // 获取舱位名称和座位数 start
                        // 座位数
                        seat = fclass.getAv();
                        // 舱位
                        code = fclass.getName();
                        seat_Str = "123456789A";
                        smallClas = fclass.getSubClass(); // 获取子舱位
                        // System.out.println("座位数------"+seat);

                        if (smallClas.size() > 0) {
                            // 小舱位座位数 ?? 这应该是子舱位的名字吧？
                            code = smallClas.get(0).toString();
                        }
                        // 获取舱位名称和座位数 end
                        dispplayTrip1 = new DispplayTrip();
                        if (!"A".equals(seat)) {
                            dispplayTrip1.setSeatsLeft(seat);
                        } else {
                            dispplayTrip1.setSeatsLeft("大于9");
                        }
                        // GetAvRPHV GetBkClassV
                        _psAVBind = new PsAvBindingType();
                        for (int d = 0; d < _SEQlist.size(); d++) {
                            pavbt = (PsAvBindingType) _SEQlist.get(d);
                            if (pavbt.getAvRPH().get(0).toString().equals(RPH)) {
                                for (int j = 0; j < pavbt.getAvRPH().size(); j++) {
                                    if (pavbt.getBkClass().get(0).toString().equals(code)) {
                                        _psAVBind = pavbt;

                                        bkClass = _psAVBind.getBkClass();
                                        cangwei = bkClass.size() > 0 ? (String) bkClass.get(0) : "";
//                                        System.out.println("仓位------" + _psAVBind.getBkClass());
                                        // System.out.println("舱位------"+cangwei);
                                        dispplayTrip1.setCangwei(cangwei);
                                    }
                                }
                            }
                        }
                        // PsAvBindingType _psAVBind = _SEQlist.Where(
                        // T => T.GetAvRPH()[0].ToString() == RHP &&
                        // T.GetBkClass()[0].ToString() == code).
                        // FirstOrDefault(); 希望这段.net代码可以用上面的替代
                        // 判断该舱位是否存在座位
                        if (seat_Str.indexOf(seat) != -1 && _psAVBind != null) {
                            // 该舱位有座位
                            // boolean is_XY = false;
                            // boolean is_discountClass = false;
                            // 对应运价表的SEQ
                            seq = _psAVBind.getSeq();
                            getFltShopPS = new PricingSolutionType();
                            brflag = false; // 标记这个getFltShopPS获取到了值
                            for (int e = 0; e < _sPSNlist.size(); e++) {
                                pst = _sPSNlist.get(e);
                                if (pst.getSeq().equals(seq)) {
                                    getFltShopPS = pst;
                                    brflag = true;
                                    break;
                                }
                            }
                            // PricingSolutionType getFltShopPS =
                            // _sPSNlist.Where(T => T.GetSeq() == seq).
                            // FirstOrDefault();
                            if (getFltShopPS != null && brflag == true) {
                                _taxs = getFltShopPS.getTaxes();
                                _Tax = new ArrayList<TaxType>();
                                tax = new Vector();
                                if (_taxs != null) {
                                    tax = _taxs.getTax();
                                    for (int f = 0; f < tax.size(); f++) {
                                        _Tax.add((TaxType) tax.get(f));
                                        // (tax.OfType<TaxType>())
                                    }
                                }
                                if (_Tax != null) {
                                    for (int j = 0; j < tax.size(); j++) {
                                        taxt = (TaxType) tax.get(j);
                                        if ("CN".equals(taxt.getCode())) {
                                            AirportTax = taxt.getAmt();
                                            // System.out.println("机建税------"+AirportTax);
                                            dispplayTrip1.setTaxfee(Double.valueOf(AirportTax));
                                        } else if ("YQ".equals(taxt.getCode())) { // 燃油税
                                            fuelTax = taxt.getAmt();
                                            dispplayTrip1.setFueltax(Double.valueOf(fuelTax));
                                            // System.out.println("燃油税-----"+fuelTax);
                                        }
                                    }
                                }
                                _price = getFltShopPS.getDisAmt();

                                _fcs = getFltShopPS.getFCs();
                                _yprice = "0";
                                if (_fcs != null) {
                                    _fc = _fcs.getFC();
                                    if (_fc != null && _fc.size() > 0) {
                                        yfctype = (FareComponentType) _fc.get(0);
                                        dispplayTrip1.setRulecode(yfctype.getFareBasis());
                                        // 李荣春 2015年2月26日 17:40:12
                                        // 增加，运价基础的属性中包含了给景鸿特惠产品的代码，根据此代码来确定是否为特惠产品
                                        yfares = yfctype.getYfares();
                                        yfare = yfares.getyFareAmount();
                                        if(cangweiPrice.get(_carrierCode+"_"+code)==null && yfares!=null){
                                           // cangweiPrice.put(_carrierCode+"_"+code,Double.valueOf(yfctype.getDisAmt()));
                                        }
                                        if (yfare != null && yfare.size() > 0) {
                                            _yprice = yfare.get(0).toString();
                                        }
                                    }
                                }
                                _yprice = String.valueOf((Float.parseFloat(_yprice) * 100) / 100);
                                _yfcPrice = "0";
                                if (code.toUpperCase().trim().equals("Y")) {
                                    _yfcPrice = _yprice;
                                } else {
                                    _yfcPrice = _price;
                                }
                                if (!"0".equals(_price) && !"".equals(_price) && !"0".equals(_yprice)
                                        && !"".equals(_yprice)) {
                                    // 获取协议价格
                                    if (_price != null && _yprice != null && !"0".equals(_price) && !"".equals(_price)
                                            && !"0".equals(_yprice) && !"".equals(_yprice)) {
                                        _discont = df
                                                .format(Float.parseFloat(_price) / Float.parseFloat(_yprice) * 100);
                                        _discontY = _discont;// 全价

                                        dispplayTrip.setAirline(_carrierFCode);
                                        dispplayTrip.setStarttime(departTime);
                                        dispplayTrip.setArrvitime(arriveTime);
                                        dispplayTrip.setStartdate(DateUtil.strToDateENG(flt.getDt() + departTime,
                                                "ddMMMyyHH:mm"));
                                        dispplayTrip.setArrvidate(DateUtil.calcDate(
                                                DateUtil.strToDateENG(flt.getDt() + arriveTime, "ddMMMyyHH:mm"),
                                                flt.getArrad() == null ? 0 : Integer.parseInt(flt.getArrad())));
                                        dispplayTrip.setOrgcity(citysCode[0]);
                                        dispplayTrip.setDetcity(citysCode[1]);
                                        if (flt.getMeal() == null) {// 是否有餐食
                                            dispplayTrip.setFood("N");
                                        } else {
                                            dispplayTrip.setFood("Y");
                                        }
                                        dispplayTrip.setPlanetype(planStyle);
                                        dispplayTrip.setOrgterm(BoardPointAT);
                                        dispplayTrip.setDetterm(OffPointAT);
                                        if (codeshare != null && codeshare.getAirline() != null
                                                && codeshare.getFltno() != null) {
                                            // dispplayTrip.setAirlineshare(dispplayTrip.getAirline());
                                            dispplayTrip.setAirlineshare(codeshare.getAirline() + codeshare.getFltno());
                                        }
                                        // 李荣春 2015年1月26日 16:38:53
                                        // 增加，如果有共享代码，把该代码保存到显示类
                                        // codeshare.getAirline() +
                                        // codeshare.getFltno()里放的是实际
                                        // 乘坐的航班（参考去哪儿网），显示的航班号是另一个，所以要交换一下
                                        // System.out.println("票面价--------"+_price);
                                        // System.out.println("Y全价---------"+_yprice);
                                        // System.out.println("折扣----------"+_discontY);
                                        dispplayTrip1.setPrice(Double.valueOf(_price));
                                        dispplayTrip1.setRebatePrice(Double.valueOf(_price));
                                        dispplayTrip1.setyPrice(Double.valueOf(_yprice));
                                        dispplayTrip1.setDiscountrate(Double.valueOf(_discontY));
                                        dispplayTrip1.setAirline(dispplayTrip.getAirline());
                                        dispplayTrip1.setAirlineshare(dispplayTrip.getAirlineshare());
                                        dispplayTrip1.setStarttime(dispplayTrip.getStarttime());
                                        dispplayTrip1.setArrvitime(dispplayTrip.getArrvitime());
                                        dispplayTrip1.setStartdate(dispplayTrip.getStartdate());
                                        dispplayTrip1.setArrvidate(dispplayTrip.getArrvidate());
                                        dispplayTrip1.setOrgcity(dispplayTrip.getOrgcity());
                                        dispplayTrip1.setDetcity(dispplayTrip.getDetcity());
                                        dispplayTrip1.setFood(dispplayTrip.getFood());
                                        dispplayTrip1.setPlanetype(dispplayTrip.getPlanetype());
                                        dispplayTrip1.setOrgterm(dispplayTrip.getOrgterm());
                                        dispplayTrip1.setDetterm(dispplayTrip.getDetterm());
                                        dispplayTrip1.setFfstr(ffstr);
                                        String key = dispplayTrip1.getOrgcity() + "_"
                                                + dispplayTrip1.getDetcity() + "_"
                                                + dispplayTrip1.getAirline().substring(0, 2) + "_"
                                                + dispplayTrip1.getCangwei();
                                        String pubfare = pubFaresMap.get(key);
                                        
                                        if(dispplayTrip1.getAirline().contains("MU3072") && dispplayTrip1.getCangwei().contains("E")){
                                            System.out.println("________pubfare-key________"+key+"___________"+pubfare);
                                        }
                                        
                                        if (StringUtil.isNotEmpty(pubfare) && dispplayTrip1.getAirlineshare()==null) {
                                            dispplayTrip1.setPubPrice(Double.valueOf(pubfare.split("_")[0]));
                                            dispplayTrip1.setCbClass(pubfare.split("_")[2]);
                                            if (dispplayTrip1.getPubPrice().doubleValue() == dispplayTrip1.getPrice()
                                                    .intValue()) {
                                                dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.公布运价
                                                        .getValue());
                                            } else if (dispplayTrip1.getPubPrice().doubleValue() > dispplayTrip1
                                                    .getPrice().intValue()) {
                                                dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.优享直减
                                                        .getValue());
                                            }else if (dispplayTrip1.getPubPrice().doubleValue() < dispplayTrip1
                                                    .getPrice().intValue()) {
                                                dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.其他
                                                        .getValue());
                                            }
                                        } else {
                                            //再从FC集合中 判断一次公布运价
                                            Double pubfarePrice=cangweiPrice.get(dispplayTrip1.getAirline().substring(0, 2) + "_"+ dispplayTrip1.getCangwei());
                                            if (pubfarePrice!=null && dispplayTrip1.getAirlineshare()==null) {
                                                dispplayTrip1.setPubPrice(pubfarePrice);
//                                            dispplayTrip1.setCbClass(pubfare.split("_")[2]);
                                                if (dispplayTrip1.getPubPrice().doubleValue() == dispplayTrip1.getPrice()
                                                        .intValue()) {
                                                    dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.公布运价
                                                            .getValue());
                                                } else if (dispplayTrip1.getPubPrice().doubleValue() > dispplayTrip1
                                                        .getPrice().intValue()) {
                                                    dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.私有运价
                                                            .getValue());
                                                }
                                            }
                                            if(dispplayTrip1.getAgreementTypeCode()==null){
                                                if (StringUtils.isNotEmpty(dispplayTrip1.getAirlineshare())) {
                                                    dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.其他.getValue());
                                                } else {
                                                    dispplayTrip1.setAgreementTypeCode(MyEnum.PriceAgreementTypeCode.优享直减.getValue());
                                                }
                                            }
                                        }
                                        disList.add(dispplayTrip1);
                                    }
                                } // 有效舱位 存在价格 此if 结束
                            } // if (getFltShopPS != null) 此if 结束
                        } // 判断该舱位是否存在座位 结束
                    } // if (fclass.getName().trim() != "-" && isClass == true)
                      // 此if 结束

                    // 显示航班信息------- - 最机建燃油时长 最低价格行
                    // ( 绑定最低价格行信息 判断是否为最后一个舱位)
                } // 循环所有舱位结束
                  // } 代码共享的if结束
            } // 循环航班结束
        } else {// if (sAV != null && sAV.size() > 0 && sAVB != null && sPSN !=
                // null ) 结束
            //strres = "暂无查询匹配航班信息(无航班或运价)";
        }
        Long time3 = System.currentTimeMillis();
        logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-->  " + sOCity + "_"
                + sDCity + "_" + sDate + ";Time：" + (time3 - time1));
        long end = System.currentTimeMillis();
        // disList = sortmenu(disList);
        System.out.println("运行时间：" + (end - begin) + "ms");
        //重新对各等级全价舱赋值
        for (int i = 0; i < disList.size(); i++) {
            DispplayTrip trip=disList.get(i);
            TAcClass codeClass=allFullTacClass.get(trip.getAirline().substring(0,2)+"_"+trip.getCangwei());
            Double price=0d;
            try {
                 price=cangweiPrice.get(trip.getAirline().substring(0,2)+"_"+codeClass.getClasscode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            if(price!=null){
                if(codeClass!=null){
                    //经济舱
                    if(codeClass.getClasstype()==1){
    //                    trip.setYprice(price);   //Y舱上面已经赋值 不用管理
                    }
                    //公务舱
                    if(codeClass.getClasstype()==2){
                        trip.setAprice(price);
                    }
                    //头等舱
                    if(codeClass.getClasstype()==3){
                        trip.setFprice(price);
                    }
                    String _discont = df.format(trip.getPrice() / price * 100);
                    trip.setDiscountrate(Double.valueOf(_discont));
                }
            }
            //System.out.println(trip.getAirline()+"_"+trip.getCangwei()+"____"+trip.getYprice()+"____"+trip.getAprice()+"____"+trip.getFprice());
        }
        return disList;
    }
    
    public Result getIBEResult(String sOCity, String sDCity, String sDate, String pricetype,
            String airlines) {
        if (sDate.contains("-")) {
            sDate = sDate.replace("-", "");
        }
        // 李荣春 2015年2月27日 16:19:18 增加参数，pricetype， 如果是PUB，查公布运价（未优惠的价格），
        // 如果是ALL，查优惠后价格
        Long time1 = System.currentTimeMillis();
        logger.info("开始获取 航班信息" + time1);
        // 初始化连接信息
        FlightShoppping fltshopping = new FlightShoppping();
        // (String office, String customno, String validationno)
        fltshopping.setAgentInfo(IbeConstants.getIbeOffice(), IbeConstants.getIbeCustomno(),
                IbeConstants.getIbeValidationno());
        fltshopping.setAppName(IbeConstants.getIbeAppName());
        fltshopping.setConnectionInfo(IbeConstants.getIbeConnectionIP(), IbeConstants.getIbeConnectionPort());

        // 国内shopping 请求
        FareInterface service = new FareInterface();
        Input input = new Input();
        // 设置HeaderIn
        input.setHeaderInType(getHeaderInType());
        Request request = new Request();
        FlightShopRequestType flightShopRequestType = new FlightShopRequestType();
        // 设置OD
        Vector originDestinationInfo = new Vector();
        try {
            originDestinationInfo.add(getOriginDestinationInfoType(sOCity, sDCity, sDate, airlines));
        } catch (Exception e) {
            e.printStackTrace();
        }
        flightShopRequestType.setOriginDestinationInfo(originDestinationInfo);
        // 设置TravelPreferences
        flightShopRequestType.setTravelPreferencesType(getTravelPreferencesType());
        // 设置Option
        flightShopRequestType.setOption(getOption(pricetype));
        // 设置大客户编码 4月29日
        // flightShopRequestType.setSpecificCarrierInfo(getSpecificCarrierInfo());
        request.setFlightShopRequestType(flightShopRequestType);
        input.setRequest(request);
        service.setInput(input);
        Output out = new Output();
        logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-->  连接航信服务器并拿到数据：时间"
                + (System.currentTimeMillis() - time1) + ";" + sOCity + "->" + sDCity);
        try {
            out = fltshopping.doFlightShopping(service);
        } catch (Exception e) {
            System.out.println("无可用航班");
            e.printStackTrace();
        }

        Long time2 = System.currentTimeMillis();
        logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + "-->  连接航信服务器并拿到数据："
                + (time2 - time1));

        Result result = out.getResult();
        return result;
    }
    /**
     * 获取经停信息
     * @param flightno
     * @param departTime
     * @return
     */
    public String getIBEFlightStopMessage(String flightno,String departTime) {
        GetIBEBigplaitImplService ibeBigplaitImplService = new GetIBEBigplaitImplService();
        GetIBEBigplaitImplDelegate port = ibeBigplaitImplService.getGetIBEBigplaitImplPort();
        String res = port.getStopStr(flightno, departTime);
        return res;
    }
    // 冒泡排序将获取的shopping信息以航班为单位按照价格排序
    private List<DispplayTrip> sortmenu(List<DispplayTrip> lst) {
        DispplayTrip tempEntity = new DispplayTrip();
        DispplayTrip tempjEntity = new DispplayTrip();
        DispplayTrip tempj1Entity = new DispplayTrip();
        DispplayTrip entity = new DispplayTrip();
        DispplayTrip entityMore = new DispplayTrip();
        boolean needCity = true;
        for (int icount = 0; icount < lst.size() - 1; icount++) { // 最多做n-1趟排序
            for (int jcount = 0; jcount < lst.size() - icount - 1; jcount++) { // 对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
                tempjEntity = lst.get(jcount);
                tempj1Entity = lst.get(jcount + 1);
                if (tempjEntity.getAirline().equals(tempj1Entity.getAirline())) {
                    if (tempjEntity.getPrice() > tempj1Entity.getPrice()) { // 把大的值交换到后面
                        tempEntity = tempjEntity;
                        tempjEntity = tempj1Entity;
                        lst.set(jcount, tempj1Entity);
                        tempj1Entity = tempEntity;
                        lst.set(jcount + 1, tempEntity);
                        if (needCity) {
                            lst.get(jcount);
                            needCity = false;
                        } else if (jcount >= 1 && lst.get(jcount - 1).getPrice().equals(lst.get(jcount).getPrice())) {
                            // lst.get(jcount).setOrgcity("");//清空非最低价格的起飞城市
                            // lst.get(jcount).setDetcity("");//清空非最低价格的到达城市
                        } else {
                            // lst.get(jcount+1).setOrgcity("");//清空非最低价格的起飞城市
                            // lst.get(jcount+1).setDetcity("");//清空非最低价格的到达城市
                        }
                    }
                }
            }
        }
        return lst;
    }

    // 设置HeaderIn内容 用户信息
    private static HeaderInType getHeaderInType() {
        HeaderInType hi = new HeaderInType();
        hi.setSysCode("CRS"); // ICS 或者CRS
        hi.setChannelID("1E"); // CAET 或者1E
        hi.setChannelType(""); // B2B 或者B2C，建议为空
        Agency agency = new Agency();
        agency.setOfficeId("PEK315"); // 如：BJS999 BJS472
        agency.setPid("38315"); // 如：20200
        agency.setCity("BJS");
        hi.setAgency(agency);
        hi.setLanguage("CN"); // EN 或者CN
        hi.setCommandType("CS"); // FS 或CS
        return hi;
    }

    // 设置OD信息
    private static OriginDestinationInfoType getOriginDestinationInfoType(String sOCity, String sDCity, String sDate,
            String airlines) throws ParseException {
        OriginDestinationInfoType od = new OriginDestinationInfoType();
        // od.setOri("CSX");//起始地
        // od.setDes("PEK");//目的地
        // od.setDepartureDate("05AUG14");//起始出发日期，格式为DDMMMYY
        od.setOri(sOCity);// 起始地
        od.setDes(sDCity);// 目的地
        Locale l = new Locale("en");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(sDate);
        String day = String.format("%td", date);
        String month = String.format(l, "%tb", date).toUpperCase();
        String year = String.format("%ty", date);
        System.out.println(day + month + year);
        od.setDepartureDate(day + month + year);// 起始出发日期，格式为DDMMMYY
        Vector carrier = new Vector();
        if (!StringUtils.isEmpty(airlines)) {
            String[] airlines_ = airlines.split("_");// 多个指定的航空公司用"_"拼接，如:"CA_MU"
            for (int i = 0; i < airlines_.length; i++) {
                carrier.add(airlines_[i]);
            }
        } else {
            carrier.add("ALL"); // 航空公司
        }
        od.setCarrier(carrier);
        od.setDepartureTime1("0000");
        od.setDepartureTime2("2359");
        // od.setDepartureDate2("27MAY14");//格式为DDMMMYY CS 查询时使用
        // od.setFixedstayDays(0);
        return od;
    }

    // 设置TravelPreferencesType
    private static TravelPreferencesType getTravelPreferencesType() {
        TravelPreferencesType tp = new TravelPreferencesType();
        // tp.setCabinClass("Y");//服务等级F/C/Y
        tp.setDisplayCurrCode("CNY");// 显示用货币类型
        tp.setCurrCode("CNY");// 运价发布所有货币类型
        tp.setIsDirectFlightOnly(true); // true - 只要直达航班；false - 包括非直达航班
        tp.setJourneyType("OW"); // OW 、RT 、TS、MS
        tp.setIsDealModel(false);
        Vector passenger = new Vector();
        PassengerType pa = new PassengerType();
        pa.setNumber(1);
        pa.setType("AD");
        passenger.add(pa);
        tp.setPassenger(passenger);
        return tp;
    }

    /**
     * 设置Option，李荣春 2015年2月27日 16:29:14 增加参数，为了查找优惠价及非优惠价
     */
    private static Option getOption(String pricetype) {
        Option op = new Option();
        op.setFcFeature("0");// 组合限制取值为0/1，默认值为0
        op.setIsAvNeeded("Y");// 结果中是否需要AV 信息。Y/N，缺省为Y
        op.setIsPSnNeeded("Y");// 结果中是否需要PS 信息。Y/N，缺省为Y
        op.setIsPsAvBindsNeeded("Y");// 结果中是否需要PsAvBinding 信息。Y/N，缺省为Y
        op.setIsFaresNeeded("Y");// 结果中是否需要fare 信息。Y/N，缺省为Y
        op.setRuleTypeNeeded("ALL");// ALL:需要所有文本规则；NON：不需要文本规则；SIM：只需要文本规则前两项缺省为ALL
        op.setFormat("NOR");// SIM:简化格式；NOR：正常格式,缺省为NOR
        op.setLowestOrAll("A");// L:每个航班返回最低价; A:返回所有可用舱位价格。
        if (pricetype == null || "".equals(pricetype)) {
            op.setFareSource("ALL");
        } else {
            op.setFareSource(pricetype);// PUB-查公布价, YPUB-只查Y 舱公布价,
                                        // ALL-包括公布价和净价。缺省为ALL
        }
        op.setIsRefundReissueTextRuleNeeded("Y");
        return op;

    }

    private Vector<SpecificCarrierInfo> getSpecificCarrierInfo() {
        SpecificCarrierInfo carrierInfo = new SpecificCarrierInfo();
        carrierInfo.setAccountCode("8916104");
        // 大客户编码（可不填）
        carrierInfo.setCarrier("MU");
        // 航空公司（必填）
        carrierInfo.setType("2");
        // 大客户请求类型1/2/3(可不填)：
        // 1:返回大客户和普通运价中最低
        // 2：分别返回大客户和普通运价最低
        // 3：只返回大客户最低
        Vector<SpecificCarrierInfo> vsc = new Vector<SpecificCarrierInfo>();
        vsc.add(carrierInfo);
        return vsc;
    }


    
    /**
     * 企业协议价匹配
     * 
     * @return
     */
    private double discount(Object dtreturn, String strOrgcity, String strDetcity,List<TPreferentialRules> rules) {
        double dblreturn = 0.0;
        // //system.out.println(rules);
        // 根据传入的Object类型，取不同属性进行不同操作
        if (dtreturn instanceof DispplayTrip) {
            DispplayTrip disp = (DispplayTrip) dtreturn;
            // 航班号
            if (disp.getAirlineshare() != null)
                return dblreturn;
            String airlineUpp = disp.getAirline().toUpperCase();
            if (airlineUpp.contains("$"))
                airlineUpp = airlineUpp.substring(0, airlineUpp.indexOf('$'));
            String orgCity = disp.getOrgcity().equals("") ? strOrgcity : disp.getOrgcity();
            String detCity = disp.getDetcity().equals("") ? strDetcity : disp.getDetcity();
            String airlineCompany = disp.getAirline().substring(0, 2);
            String cangwei = disp.getCangwei().substring(disp.getCangwei().indexOf("^") + 1);

            String godate = ""; // 航班的出发时间
            String rsdate = ""; // 规则中的开始时间 政策起始日期
            String redate = ""; // 规则中的结束时间 政策结束日期 后续不作为判断依据
            String salesdate = ""; // 允许的销售起始时间
            String saleedate = ""; // 允许的销售截止时间
            String tripsdate = ""; // 允许的旅行起始时间
            String tripedate = ""; // 允许的旅行截止时间
            String airwaysdate = "";// 航线中的旅行起始时间
            String airwayedate = "";// 航线中的旅行结束时间
            String nowdate = DateUtil.date2Str(new Date(), "yyyyMMdd"); // 当前日期
            String[] strExcepttime = null; // 规则中的例外时间，组成数组
            // TODO map方式分区进行比较，多线程方式分别查询规则和航班以及FD价格
            for (TPreferentialRules rule : rules) {
                // dblreturn = 0.0;
                godate = DateUtil.date2Str(disp.getStartdate(), "yyyyMMdd"); // 例如
                                                                             // 20131114
                                                                             // 航班的出发时间
                rsdate = DateUtil.date2Str(rule.getStartDate(), "yyyyMMdd"); // 例如
                                                                             // 20131101
                                                                             // 政策起始日期
                redate = DateUtil.date2Str(rule.getEndDate(), "yyyyMMdd"); // 例如
                                                                           // 20131130
                                                                           // 政策结束日期
                salesdate = DateUtil.date2Str(rule.getSalesStartDeadline(), "yyyyMMdd"); // 例如
                                                                                         // 20131101
                                                                                         // 销售起始时间
                saleedate = DateUtil.date2Str(rule.getSalesEndDeadline(), "yyyyMMdd"); // 例如
                                                                                       // 20131101
                                                                                       // 销售截止时间
                tripsdate = DateUtil.date2Str(rule.getStartTripDate(), "yyyyMMdd"); // 例如
                                                                                    // 20131101
                                                                                    // 旅行起始时间
                tripedate = DateUtil.date2Str(rule.getEndTripDate(), "yyyyMMdd"); // 例如
                                                                                  // 20131101
                                                                                  // 旅行截止时间
                airwaysdate = DateUtil.date2Str(rule.getAirwaystartdate(), "yyyyMMdd");
                airwayedate = DateUtil.date2Str(rule.getAirwayenddate(), "yyyyMMdd");
                // 判断航空公司
                if (rule.getAircompany().indexOf(airlineCompany) != -1) {
                    // 判断舱位
                    // if (rule.getCangwei().indexOf(cangwei) != -1) {
                    // //这种判断方式会导致Q1\Q都进入下面的逻辑(Q1.indexOf(Q)!=-1)永远为true
                    if (rule.getCangwei().equals(cangwei)) {
                        // 判断起飞时间是否在旅行起止时间内 李荣春 2015年3月10日 15:15:15 修改
                        // if (DateUtil.getDiffDay(rsdate, godate, "yyyyMMdd")
                        // >= 0 &&
                        // DateUtil.getDiffDay(godate, redate, "yyyyMMdd") >= 0)
                        // { //getDiffDay是算后面的时间减去前面的时间
                        if (tripsdate.equals("") || tripedate.equals("")) {
                            continue;
                        }

                        if (DateUtil.getDiffDay(tripsdate, godate, "yyyyMMdd") >= 0
                                && DateUtil.getDiffDay(godate, tripedate, "yyyyMMdd") >= 0) { // getDiffDay是算后面的时间减去前面的时间
                            if (salesdate.equals("") || saleedate.equals("")) {
                                continue;
                            }
                            // 判断销售时间是否包含了当天
                            if (DateUtil.getDiffDay(salesdate, nowdate, "yyyyMMdd") >= 0
                                    && DateUtil.getDiffDay(nowdate, saleedate, "yyyyMMdd") >= 0) {
                                // 判断起飞时间是否是例外
                                // 把例外的时间放到一个数组中
                                if (rule.getExceptiontime() != null) {
                                    boolean isDateOK = true;// 时间是否合适
                                    // 例外时间段，可存放多段具体时间，格式为：20140101~20140201，多段时间之间用半角逗号分割
                                    strExcepttime = rule.getExceptiontime().split(",");
                                    for (String atime : strExcepttime) {
                                        if (DateUtil.getDiffDay(atime.substring(0, atime.indexOf("~")), godate,
                                                "yyyyMMdd") >= 0
                                                && DateUtil.getDiffDay(godate, atime.substring(atime.indexOf("~") + 1),
                                                        "yyyyMMdd") >= 0) {
                                            // //如果起飞日期在例外日期区间内，例外日期段为20131110~20131115，现在是20131114，则在此时间段内，不满足优惠条件，返回
                                            // dblreturn = 1.0;
                                            // return dblreturn;
                                            isDateOK = false;
                                        }
                                    }
                                    if (!isDateOK) {// 如果时间不合适，判断下一条规则
                                        continue;
                                    }
                                }
//                                System.out.println("旅行日期" + tripsdate + "日期" + tripedate + "godate" + godate);

                                // 该航班号在规则中，但是勾选了例外
                                if (rule.getAirline() != null
                                        && rule.getAirline().toUpperCase().indexOf(airlineUpp) != -1
                                        && "Y".equals(rule.getExceptairlineyn())) {
                                    continue;
                                }

                                // 该航班号在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                if (rule.getAirline() != null
                                        && rule.getAirline().toUpperCase().indexOf(airlineUpp) != -1
                                        && "N".equals(rule.getExceptairlineyn())) {
                                    return rule.getDiscountrate();
                                }

                                // 该航班号不在规则中，或者没填写航班号(新规则改为还需判断出港机场)
                                if ((rule.getAirline() != null && rule.getAirline().toUpperCase().indexOf(airlineUpp) == -1)
                                        || rule.getAirline() == null) {

                                    if (rule.getAirway() != null) { // 该规则中录入了航线
                                        if (rule.getAirway().indexOf(
                                                orgCity.toUpperCase() + "-" + detCity.toUpperCase()) != -1
                                                && "Y".equals(rule.getExceptairwayyn())) {
                                            // 出港城市和到港城市的组合在设置的航线内，但是例外
                                            // CTU-LZY/LZY-CTU/CTU-RKZ/RKZ-CTU/CTU-BPX/BPX-CTU/CTU-NGQ/NGQ-CTU/CTU-NGQ
                                            if (!"".equals(airwaysdate) && !"".equals(airwayedate)) { // 录入了适用日期
                                                if (DateUtil.getDiffDay(airwaysdate, godate, "yyyyMMdd") >= 0
                                                        && DateUtil.getDiffDay(godate, airwayedate, "yyyyMMdd") >= 0) {
                                                    // 在日期范围内
                                                    continue;
                                                } else {
                                                    return rule.getDiscountrate();
                                                }
                                            } else { // 没设置日期，那么就是不适用这个折扣
                                                continue;
                                            }
                                        } else if (rule.getAirway().indexOf(
                                                orgCity.toUpperCase() + "-" + detCity.toUpperCase()) != -1
                                                && ("N".equals(rule.getExceptairwayyn()) || rule.getExceptairwayyn() == null)) {
                                            // 出港城市和到港城市的组合在设置的航线内，非例外（且在设置的时间内），则直接享受折扣，不再判断其他条件
                                            if (!"".equals(airwaysdate) && !"".equals(airwayedate)) { // 录入了适用日期
                                                if (DateUtil.getDiffDay(airwaysdate, godate, "yyyyMMdd") >= 0
                                                        && DateUtil.getDiffDay(godate, airwayedate, "yyyyMMdd") >= 0) {
                                                    // 在日期范围内
                                                    return rule.getDiscountrate();
                                                } else { // 不在日期范围内，则不适用折扣
                                                    continue;
                                                }
                                            } else { // 没设置日期，则所有设置航线范围内的都可以享受折扣
                                                return rule.getDiscountrate();
                                            }
                                        } // 在设置的航线范围之外，则该怎么着就怎么着
                                    }

                                    // 该航班出港机场在规则中，但是勾选了例外
                                    if (rule.getAirport() != null
                                            && rule.getAirport().toUpperCase().indexOf(orgCity) != -1
                                            && "Y".equals(rule.getExceptairportyn())) {
                                        continue;
                                    }

                                    // 该航班出港机场在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                    if (rule.getAirport() != null
                                            && rule.getAirport().toUpperCase().indexOf(orgCity) != -1
                                            && ("N".equals(rule.getExceptairportyn()) || rule.getExceptairportyn() == null)) {
                                        return rule.getDiscountrate();
                                    }

                                    // 该航班出港机场不在规则中，或者没填写出港机场(新规则改为还需判断到港机场)
                                    if ((rule.getAirport() != null && rule.getAirport().toUpperCase().indexOf(orgCity) == -1)
                                            || (rule.getAirport() == null)) {

                                        // 该航班到港机场在规则中，但是勾选了例外
                                        if (rule.getEndairport() != null
                                                && rule.getEndairport().toUpperCase().indexOf(detCity) != -1
                                                && "Y".equals(rule.getExceptendairport())) {
                                            continue;
                                        }
                                        // 该航班到港机场在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                        if (rule.getEndairport() != null
                                                && rule.getEndairport().toUpperCase().indexOf(detCity) != -1
                                                && ("N".equals(rule.getExceptendairport()) || rule
                                                        .getExceptendairport() == null)) {
                                            return rule.getDiscountrate();
                                        }

                                        // 增加规则：到港机场有设置了，且勾选了例外，同时，出港机场也有设置，也勾选例外，
                                        // 此航班的出发机场在到港例外并抵达机场在出港例外，则直接返回折扣。(2017.03.22
                                        // wjt)
                                        if (rule.getAirport() != null
                                                && rule.getAirport().toUpperCase().indexOf(orgCity) == -1
                                                && "Y".equals(rule.getExceptairportyn())
                                                && rule.getEndairport() != null
                                                && rule.getEndairport().toUpperCase().indexOf(detCity) == -1
                                                && "Y".equals(rule.getExceptendairport())) {
                                            return rule.getDiscountrate();
                                        }

                                        // 该航班到港机场不在规则中，或者没填写到港机场（李荣春
                                        // 2016年2月15日18:00:13修改，没填到港机场，应继续后面的判断）
                                        if ((rule.getEndairport() != null && rule.getEndairport().toUpperCase()
                                                .indexOf(detCity) == -1)) { // ||
                                                                            // (rule.getEndairport()
                                                                            // ==
                                                                            // null))
                                                                            // {
                                            continue;
                                        }
                                    }

                                }

                                // 所有判断都通过，可以使用该企业协议价，且没设置出港到港机场（如果设置，上面满足条件的已经返回折扣）
                                if ((rule.getAirport() == null || rule.getAirport().equals("") || rule.getAirport()
                                        .toUpperCase().indexOf(orgCity) == -1)
                                        && // 李荣春 增加
                                        (rule.getEndairport() == null || rule.getEndairport().equals("") || rule
                                                .getEndairport().toUpperCase().indexOf(detCity) == -1)) { // 李荣春
                                                                                                          // 增加
                                    if ("Y".equals(rule.getExceptairportyn()) && rule.getAirport() != null) { // 如果出港勾选了“不参加折让”
                                        // 调用方法找出规则内维护的出港机场之外的机场
                                        String allorgcity = this.getothercity(rule.getAirport());
                                        // 继续判断出港机场是否在上一行返回的机场集合中，如果在此集合中，折扣为该规则的折扣
                                        if (allorgcity.indexOf(orgCity) != -1)
                                            dblreturn = rule.getDiscountrate();
                                    } else if ("N".equals(rule.getExceptairportyn())) { // 如果出港没勾选“不参加折让”
                                        if (rule.getAirport() != null && rule.getAirport().indexOf(orgCity) != -1)
                                            dblreturn = rule.getDiscountrate();
                                    }
                                    if ("Y".equals(rule.getExceptendairport()) && rule.getEndairport() != null) { // 如果到港勾选了“不参加折让”
                                        // 调用方法找出规则内维护的到港机场之外的机场
                                        String alldetcity = this.getothercity(rule.getEndairport());
                                        // 继续判断到港机场是否在上一行返回的机场集合中，如果在此集合中，折扣为该规则的折扣
                                        if (alldetcity.indexOf(detCity) != -1)
                                            dblreturn = rule.getDiscountrate();
                                    } else if ("N".equals(rule.getExceptendairport())) { // 如果到港没勾选“不参加折让”
                                        if (rule.getEndairport() != null && rule.getEndairport().indexOf(detCity) != -1)
                                            dblreturn = rule.getDiscountrate();
                                    }
                                    // 没勾选“不参加折让”，则是该规则的折扣
                                    if (dblreturn == 0)
                                        dblreturn = rule.getDiscountrate();
                                    // }
                                }
                                // return dblreturn;//已经获取到匹配的 最新维护的 企业协议价，直接返回
                            }
                        }
                    }
                }
            }
        } else {
            TCangwei cw = (TCangwei) dtreturn;
            // 航班号
            String airlineUpp = cw.getAirline().toUpperCase();
            if (airlineUpp.contains("$"))
                airlineUpp = airlineUpp.substring(0, airlineUpp.indexOf('$'));
            String orgCity = cw.getOrgcity();
            String detCity = cw.getDetcity();
            String airlineCompany = cw.getAirline().substring(0, 2);
            String cangwei = cw.getCangwei().substring(cw.getCangwei().indexOf("^") + 1);

            String godate = ""; // 航班的出发时间
            String rsdate = ""; // 规则中的开始时间 政策起始日期
            String redate = ""; // 规则中的结束时间 政策结束日期 后续不作为判断依据
            String salesdate = ""; // 允许的销售起始时间
            String saleedate = ""; // 允许的销售截止时间
            String tripsdate = ""; // 允许的旅行起始时间
            String tripedate = ""; // 允许的旅行截止时间
            String airwaysdate = "";// 航线中的旅行起始时间
            String airwayedate = "";// 航线中的旅行结束时间

            String airlinestartdate = "";// 折扣适用航班号开始日期
            String airlineenddate = "";// 折扣适用航班号截止日期

            String nowdate = DateUtil.date2Str(new Date(), "yyyyMMdd"); // 当前日期
            String[] strExcepttime = null; // 规则中的例外时间，组成数组
            logger.info("TCangwei");
            for (TPreferentialRules rule : rules) {
                // dblreturn = 0.0;
                godate = DateUtil.date2Str(cw.getStarttime(), "yyyyMMdd"); // 例如
                                                                           // 20131114
                                                                           // 航班的出发时间
                rsdate = DateUtil.date2Str(rule.getStartDate(), "yyyyMMdd"); // 例如
                                                                             // 20131101
                                                                             // 政策起始日期
                redate = DateUtil.date2Str(rule.getEndDate(), "yyyyMMdd"); // 例如
                                                                           // 20131130
                                                                           // 政策结束日期
                salesdate = DateUtil.date2Str(rule.getSalesStartDeadline(), "yyyyMMdd"); // 例如
                                                                                         // 20131101
                                                                                         // 销售起始时间
                saleedate = DateUtil.date2Str(rule.getSalesEndDeadline(), "yyyyMMdd"); // 例如
                                                                                       // 20131101
                                                                                       // 销售截止时间
                tripsdate = DateUtil.date2Str(rule.getStartTripDate(), "yyyyMMdd"); // 例如
                                                                                    // 20131101
                                                                                    // 旅行起始时间
                tripedate = DateUtil.date2Str(rule.getEndTripDate(), "yyyyMMdd"); // 例如
                                                                                  // 20131101
                                                                                  // 旅行截止时间
                airwaysdate = DateUtil.date2Str(rule.getAirwaystartdate(), "yyyyMMdd");
                airwayedate = DateUtil.date2Str(rule.getAirwayenddate(), "yyyyMMdd");

                airlinestartdate = DateUtil.date2Str(rule.getAirlinestartdate(), "yyyyMMdd");// 折扣适用航班号起始日期，如果上一字段为例外，则此段时间内的不适用此折扣
                airlineenddate = DateUtil.date2Str(rule.getAirlineenddate(), "yyyyMMdd");// 折扣适用航班号截止日期

                // 判断航空公司
                if (rule.getAircompany().indexOf(airlineCompany) != -1) {
                    // 判断舱位
                    // if (rule.getCangwei().indexOf(cangwei) != -1) {
                    if (rule.getCangwei().equals(cangwei)) {
                        if (tripsdate.equals("") || tripedate.equals("")) {
                            continue;
                        }
                        // 判断起飞时间是否在旅行起止时间内 李荣春 2015年3月10日 15:15:15 修改
                        // if (DateUtil.getDiffDay(rsdate, godate, "yyyyMMdd")
                        // >= 0 &&
                        // DateUtil.getDiffDay(godate, redate, "yyyyMMdd") >= 0)
                        // { //getDiffDay是算后面的时间减去前面的时间
                        if (DateUtil.getDiffDay(tripsdate, godate, "yyyyMMdd") >= 0
                                && DateUtil.getDiffDay(godate, tripedate, "yyyyMMdd") >= 0) { // getDiffDay是算后面的时间减去前面的时间
                            // 判断销售时间是否包含了当天
                            if (salesdate.equals("") || saleedate.equals("")) {
                                continue;
                            }
                            if (DateUtil.getDiffDay(salesdate, nowdate, "yyyyMMdd") >= 0
                                    && DateUtil.getDiffDay(nowdate, saleedate, "yyyyMMdd") >= 0) {
                                // 判断起飞时间是否是例外
                                // 把例外的时间放到一个数组中
                                if (rule.getExceptiontime() != null) {
                                    boolean isDateOK = true;// 时间是否合适
                                    // 例外时间段，可存放多段具体时间，格式为：20140101~20140201，多段时间之间用半角逗号分割
                                    strExcepttime = rule.getExceptiontime().split(",");
                                    for (String atime : strExcepttime) {
                                        if (DateUtil.getDiffDay(atime.substring(0, atime.indexOf("~")), godate,
                                                "yyyyMMdd") >= 0
                                                && DateUtil.getDiffDay(godate, atime.substring(atime.indexOf("~") + 1),
                                                        "yyyyMMdd") >= 0) {
                                            // //如果起飞日期在例外日期区间内，例外日期段为20131110~20131115，现在是20131114，则在此时间段内，不满足优惠条件，返回
                                            // dblreturn = 1.0;
                                            // return dblreturn;
                                            isDateOK = false;
                                        }
                                    }
                                    if (!isDateOK) {// 如果时间不合适，判断下一条规则
                                        continue;
                                    }
                                }

                                // 该航班号在规则中，但是勾选了例外
                                if (rule.getAirline() != null
                                        && rule.getAirline().toUpperCase().indexOf(airlineUpp) != -1
                                        && "Y".equals(rule.getExceptairlineyn())
                                        && DateUtil.getDiffDay(airlinestartdate, nowdate, "yyyyMMdd") >= 0
                                        && DateUtil.getDiffDay(nowdate, airlineenddate, "yyyyMMdd") >= 0) {
                                    continue;
                                }

                                // 该航班号在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                if (rule.getAirline() != null
                                        && rule.getAirline().toUpperCase().indexOf(airlineUpp) != -1
                                        && "N".equals(rule.getExceptairlineyn())
                                        && DateUtil.getDiffDay(airlinestartdate, nowdate, "yyyyMMdd") >= 0
                                        && DateUtil.getDiffDay(nowdate, airlineenddate, "yyyyMMdd") >= 0) {
                                    return rule.getDiscountrate();
                                }

                                // 该航班号不在规则中，或者没填写航班号(新规则改为还需判断出港机场)
                                if ((rule.getAirline() != null && rule.getAirline().toUpperCase().indexOf(airlineUpp) == -1)
                                        || rule.getAirline() == null) {

                                    if (rule.getAirway() != null) { // 该规则中录入了航线
                                        if (rule.getAirway().indexOf(
                                                orgCity.toUpperCase() + "-" + detCity.toUpperCase()) != -1
                                                && "Y".equals(rule.getExceptairwayyn())) {
                                            // 出港城市和到港城市的组合在设置的航线内，但是例外
                                            if (!"".equals(airwaysdate) && !"".equals(airwayedate)) { // 录入了适用日期
                                                if (DateUtil.getDiffDay(airwaysdate, godate, "yyyyMMdd") >= 0
                                                        && DateUtil.getDiffDay(godate, airwayedate, "yyyyMMdd") >= 0) {
                                                    // 在日期范围内
                                                    continue;
                                                } else {
                                                    return rule.getDiscountrate();
                                                }
                                            } else { // 没设置日期，那么就是不适用这个折扣
                                                continue;
                                            }
                                        } else if (rule.getAirway().indexOf(
                                                orgCity.toUpperCase() + "-" + detCity.toUpperCase()) != -1
                                                && ("N".equals(rule.getExceptairwayyn()) || rule.getExceptairwayyn() == null)) {
                                            // 出港城市和到港城市的组合在设置的航线内，非例外（且在设置的时间内），则直接享受折扣，不再判断其他条件
                                            if (!"".equals(airwaysdate) && !"".equals(airwayedate)) { // 录入了适用日期
                                                if (DateUtil.getDiffDay(airwaysdate, godate, "yyyyMMdd") >= 0
                                                        && DateUtil.getDiffDay(godate, airwayedate, "yyyyMMdd") >= 0) {
                                                    // 在日期范围内
                                                    return rule.getDiscountrate();
                                                } else { // 不在日期范围内，则不适用折扣
                                                    continue;
                                                }
                                            } else { // 没设置日期，则所有设置航线范围内的都可以享受折扣
                                                return rule.getDiscountrate();
                                            }
                                        } // 在设置的航线范围之外，则该怎么着就怎么着
                                    }

                                    // 该航班出港机场在规则中，但是勾选了例外
                                    if (rule.getAirport() != null
                                            && rule.getAirport().toUpperCase().indexOf(orgCity) != -1
                                            && "Y".equals(rule.getExceptairportyn())) {
                                        continue;
                                    }

                                    // 该航班出港机场在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                    if (rule.getAirport() != null
                                            && rule.getAirport().toUpperCase().indexOf(orgCity) != -1
                                            && ("N".equals(rule.getExceptairportyn()) || rule.getExceptairportyn() == null)) {
                                        return rule.getDiscountrate();
                                    }

                                    // 该航班出港机场不在规则中，或者没填写出港机场(新规则改为还需判断到港机场)
                                    if ((rule.getAirport() != null && rule.getAirport().toUpperCase().indexOf(orgCity) == -1)
                                            || (rule.getAirport() == null)) {

                                        // 该航班到港机场在规则中，但是勾选了例外
                                        if (rule.getEndairport() != null
                                                && rule.getEndairport().toUpperCase().indexOf(detCity) != -1
                                                && "Y".equals(rule.getExceptendairport())) {
                                            continue;
                                        }
                                        // 该航班到港机场在规则中，且没有勾选例外(新规则改为直接使用该企业协议价)
                                        if (rule.getEndairport() != null
                                                && rule.getEndairport().toUpperCase().indexOf(detCity) != -1
                                                && ("N".equals(rule.getExceptendairport()) || rule
                                                        .getExceptendairport() == null)) {
                                            return rule.getDiscountrate();
                                        }

                                        // 该航班到港机场不在规则中，或者没填写到港机场（李荣春
                                        // 2016年2月15日18:00:13修改，没填到港机场，应继续后面的判断）
                                        if ((rule.getEndairport() != null && rule.getEndairport().toUpperCase()
                                                .indexOf(detCity) == -1)) {// &&
                                                                           // (rule.getEndairport()
                                                                           // ==
                                                                           // null))
                                                                           // {
                                            continue;
                                        }
                                    }

                                }

                                // 所有判断都通过，可以使用该企业协议价，且没设置出港到港机场（如果设置，上面满足条件的已经返回折扣）
                                if ((rule.getAirport() == null || rule.getAirport().equals(""))
                                        && (rule.getEndairport() == null || rule.getEndairport().equals(""))) {
                                    // return rule.getDiscountrate(); //
                                    dblreturn = rule.getDiscountrate();
                                }
                                // return dblreturn;//已经获取到匹配的 最新维护的 企业协议价，直接返回
                            }
                        }
                    }
                }
            }
        }
        return dblreturn;
    }

    /**
     * 在字典表中查找传入三字码之外的机场三字码。传入的参数是逗号分隔的机场三字码
     * 
     * @param city3char
     * @return
     */
    public String getothercity(String city3char) {
        @SuppressWarnings("unchecked")
        List<String> char3CityList = RedisCacheManager.get(Const.CACHE_3CHAR_CITY_LIST, List.class);
        if (char3CityList == null || char3CityList.size() == 0) {
            char3CityList = comService.get3CharCity();
            RedisCacheManager.save(Const.CACHE_3CHAR_CITY_LIST, char3CityList,
                    MyEnum.CacheKeepTime.十二小时.getValue());
        }
        StringBuffer strreturn = new StringBuffer().append("");
        for (String str : char3CityList) {
            if (StringUtils.isNotEmpty(strreturn.toString())) {
                strreturn.append(",");
            }
            if (!city3char.contains(str.substring(0, str.indexOf("_")))) {
                strreturn.append(str.substring(0, str.indexOf("_")));
            }
        }
        return strreturn.toString();
    }

    /**
     * 私有方法，根据价格进行排序 参数中List里String数组的格式： " 舱位价格:" + strFD[0] + " 机场税:" +
     * strFD[1] + " 燃油附加税:" + strFD[2] + " 折扣率:" + strFD[3] + " 截止日期:" +
     * strFD[4] + " 运价规则代码:" + strFD[5] + " 舱位:" + strFD[6] + " 航班代码：" +
     * strFD[7] + " 起飞日期(YYYY-MM-DD):" + strFD[8]
     * 
     * @param lstprice
     * @return
     */
    private List<String[]> sortprice(List<String[]> lstprice) {
        String[] temp = null;
        String[] strj = null;
        String[] strj1 = null;
        double t = 0.0;
        double t1 = 0.0;
        for (int o = 0; o < lstprice.size(); o++) {
            strj = lstprice.get(o);
            // logger.info("o: " + o + " " + strj[0]);
        }
        for (int i = 1; i < lstprice.size(); i++) {
            for (int j = 0; j < lstprice.size() - i; j++) {
                strj = lstprice.get(j);
                strj1 = lstprice.get(j + 1);
                if ("".equals(strj[0]))
                    strj[0] = "0.0";
                if ("".equals(strj1[0]))
                    strj1[0] = "0.0";
                t = new Double(strj[0]).doubleValue();
                t1 = new Double(strj1[0]).doubleValue();
                // logger.info("t: " + t + " t1: " + t1);
                if (t > t1) {
                    temp = strj;
                    lstprice.set(j, strj1);
                    lstprice.set(j + 1, temp);
                }
            }
        }
        return lstprice;
    }

    /**
     * 北京、上海多机场处理
     * 
     * @param sOCity
     * @param sDCity
     * @param startdate
     * @param priceTable
     * @param fd
     */
    private void dealPrice(String sOCity, String sDCity, String startdate, Hashtable<String, String[]> priceTable, FD fd) {
        if ("PEK".equals(sOCity)) {
            String sOCity2 = "NAY";
            dealDetail(sOCity2, sDCity, startdate, priceTable, fd);
        }
        if ("SHA".equals(sOCity)) {
            String sOCity2 = "PVG";
            dealDetail(sOCity2, sDCity, startdate, priceTable, fd);
        }
        if ("SHA".equals(sDCity)) {
            String sDCity2 = "PVG";
            dealDetail(sOCity, sDCity2, startdate, priceTable, fd);
            if ("PEK".equals(sOCity)) {
                String sOCity2 = "NAY";
                dealDetail(sOCity2, sDCity2, startdate, priceTable, fd);
            }
        }
        if ("PEK".equals(sDCity)) {
            String sDCity2 = "NAY";
            dealDetail(sOCity, sDCity2, startdate, priceTable, fd);
            if ("SHA".equals(sOCity)) {
                String sOCity2 = "PVG";
                dealDetail(sOCity2, sDCity2, startdate, priceTable, fd);
            }
        }
    }

    private void dealDetail(String sOCity, String sDCity, String startdate, Hashtable<String, String[]> priceTable,
            FD fd) {
        try {
            FDResult fdres = null; // 查询运价之后的结果集
            // 初始化航空公司加舱位代码的hashtable，存放价格、机场税、燃油附加税、折扣率、截止日期、运价规则代码
            // 字符串信息，按固定顺序生成字符串数组
            String airCompany = ""; // 航空公司代码，从FDResult中获取
            String cangweiCode = ""; // 舱位代码，从FDResult中获取
            String[] strFD = new String[9]; // 存放在hashtable中的舱位及舱位价格等信息

            fdres = fd.findPrice(sOCity, sDCity, startdate, "ALL", "330", "AD", true); // 机型
            // 查询成人运价
            // logger.info("fdres: " + fdres);
            for (int iFdCount = 0; iFdCount < fdres.getElementNum(); iFdCount++) { // FDResult中放的也是一个集合，提供了获取集合里对象数量的方法
                airCompany = fdres.getAirline(iFdCount); // 取航空公司代码
                cangweiCode = fdres.getCabinType(iFdCount); // 舱位代码
                strFD = new String[9];
                strFD[0] = fdres.getSinglePrice(iFdCount); // 舱位价格，FDResult返回的是字符串
                                                           // 单程票价
                if (StringUtils.isEmpty(strFD[0]) || "null".equals(strFD[0]))
                    continue; // 如果没有该舱位的价格，则直接抛弃该数据，继续下一个
                strFD[1] = fdres.getAirportTax(iFdCount); // 机场税，FDResult返回的是字符串
                strFD[2] = fdres.getFuelTax(iFdCount); // 燃油附加税
                strFD[3] = fdres.getDiscountRate(iFdCount); // 折扣率
                strFD[4] = fdres.getEndDate(iFdCount); // 截止日期，这个是干什么用的？
                strFD[5] = fdres.getRule(iFdCount); // 运价规则代码，这个是干什么用的？
                strFD[6] = cangweiCode; // 舱位代码

                // ****************************基建燃油为空处理
                // begin*****************************************
                if (StringUtils.isEmpty(strFD[1]) || "null".equals(strFD[1])) {
                    strFD[1] = IbeConstants.getAirportTax();
                }
                if (Double.valueOf(strFD[1]) == 0) {
                    strFD[1] = IbeConstants.getAirportTax();
                }
                if (StringUtils.isEmpty(strFD[2]) || "null".equals(strFD[2])) {
                    strFD[1] = IbeConstants.getFuelTax();
                }
                if (Double.valueOf(strFD[2]) == 0) {
                    strFD[1] = IbeConstants.getFuelTax();
                }
                // ****************************基建燃油为空处理
                // end*****************************************

                priceTable.put(airCompany + sOCity + sDCity + cangweiCode, strFD);
                // 此hashtable中存放了:key-航空公司代码+出发城市+目的城市+舱位代码;value-对应的价格及其他相关信息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * pat 特价舱位
     * 
     * @param airline
     * @param cangwei
     * @param orgCity
     * @param desCity
     * @param dateStr
     */
    private static void patPrice(String airline, char cangwei, String orgCity, String desCity, String dateStr,
            List<DispplayTrip> listdt, DispplayTrip otherdispplaytrip) {
        SellSeat sellseat = new SellSeat();
        String aircompany = airline.substring(0, 2);
        String name = "name" + "/" + "name";
        dateStr = DateUtil.strToDateEng(dateStr, "yyyy-MM-dd", "yyyyMMdd");
        String pnrno = "";

        Double price = 0d;
        Double tax = 0d;
        Double fueltax = 0d;
        try {
            sellseat.addAdult(name);
            // 输入旅客姓名(NM)
            sellseat.addAirSeg(airline, cangwei, orgCity, desCity, "NN", 1, dateStr);
            sellseat.addSSR_FOID(aircompany, "NI", "513122197606152710", name);
            String contactinfo = "010-84669396";
            sellseat.addContact(contactinfo);
            BookContact bc = new BookContact(); // 乘机人联系信息组
            bc.setCity("PEK");
            bc.setContact("13535353535");
            bc.setpsgrName(name);
            // CTCT
            sellseat.addContact(bc);
            // 输入联系组(CT)
            sellseat.setTimelimit(DateUtil.getHoursAfterTime(2));// "2013-08-07 14:40:00");

            // sellseat.addRMK("TJ AUTH", "PEK523");

            // 出票时限(TK:TL)
            pnrno = sellseat.commit();
            // SSResult ssr = sellseat.commit1(); // 另一种提交方式
            // //提交(@)
            System.out.println("pnrno:" + pnrno);
            // pnrno = ssr.getPnrno();
            // PAT
            PAT pat = new PAT();
            PATResult patres = pat.doPatA(pnrno, false);
            // System.out.println(patres);
            String fare = patres.getFareItem(0).getFare();
            // System.out.println(fare);

            // fare : 0:PY07 FARE:CNY1130.00 TAX:CNY50.00 YQ:CNY120.00
            // TOTAL:1300.00
            price = Double.valueOf(fare.substring(fare.indexOf("FARE:CNY") + 8, fare.indexOf("FARE:CNY") + 8 + 7)
                    .trim());
            tax = Double.valueOf(fare.substring(fare.indexOf("TAX:CNY") + 7, fare.indexOf("TAX:CNY") + 7 + 6).trim());
            fueltax = Double.valueOf(fare.substring(fare.indexOf("YQ:CNY") + 6, fare.indexOf("YQ:CNY") + 6 + 6).trim());
            System.out.println("FARE:" + price);
            System.out.println("TAX:" + tax);
            System.out.println("YQ:" + fueltax);
            System.out.println("TOTAL:" + fare.substring(fare.indexOf("TOTAL:") + 6, fare.indexOf("TOTAL:") + 6 + 7));
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            PnrManage pm = new PnrManage();
            try {
                System.out.println("xpnr:" + pnrno + ":" + pm.deletePnr(pnrno));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                // e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        otherdispplaytrip.setPrice(price);
        otherdispplaytrip.setTaxfee(tax);
        otherdispplaytrip.setFueltax(fueltax);
        if (price > 0)// 价格等于0的不返回显示
            listdt.add(otherdispplaytrip);
    }

    /**
     * pat 特价舱位
     * 
     * @param airline
     * @param cangwei
     * @param orgCity
     * @param desCity
     * @param dateStr
     */
    private void patPriceNoPnr(String airline, char cangwei, String orgCity, String desCity, String dateStr,
            List<DispplayTrip> listdt, DispplayTrip otherdispplaytrip) {
        Double price = 0d;
        Double tax = 0d;
        Double fueltax = 0d;

        try {
            PAT pat = new PAT();
            Vector segments = new Vector();
            PNRAirSeg seg = new PNRAirSeg(airline, cangwei, orgCity, desCity, "HK", 1, QDateTime.stringToDate(dateStr
                    + " 0000", "YYYYMMDD HHMI"), QDateTime.stringToDate(dateStr + " 0000", "YYYYMMDD HHMI"), false,
                    PNRAirSeg.AIRSEG_NORMAL);
            segments.add(seg);
            String[] planemodes = new String[] { "300" };

            PATResult patres = pat.doPatA("PEK", airline.substring(0, 2), "pek315", "", "", segments, planemodes);

            // FN/FCNY1130.00/SCNY1130.00/C3.00/TCNY50.00CN/TCNY120.00YQ
            String fare = patres.getFareItem(0).getFnText();
            System.out.println("FN TEXT:\t" + fare);
            System.out.println("FARE:" + fare.substring(fare.indexOf("FCNY") + 4, fare.indexOf("FCNY") + 4 + 6));
            System.out.println("TAX:" + fare.substring(fare.indexOf("TCNY") + 4, fare.indexOf("TCNY") + 4 + 5));
            System.out.println("FUELTAX:" + fare.substring(fare.indexOf("YQ") - 6, fare.indexOf("YQ")));

            price = Double.valueOf(fare.substring(fare.indexOf("FCNY") + 4, fare.indexOf("FCNY") + 4 + 6));
            tax = Double.valueOf(fare.substring(fare.indexOf("TCNY") + 4, fare.indexOf("TCNY") + 4 + 5).trim());
            fueltax = Double.valueOf(fare.substring(fare.indexOf("YQ") - 6, fare.indexOf("YQ")).trim());
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
        otherdispplaytrip.setPrice(price);
        otherdispplaytrip.setTaxfee(tax);
        otherdispplaytrip.setFueltax(fueltax);
        if (price > 0)// 价格等于0的不返回显示
            listdt.add(otherdispplaytrip);
    }

    @Override
    public List<DispplayTrip> getIBEData(Request_GetReschedueFlightList para) {
        long userId = para.getUserId();
        ReqData_GetReschedueFlightList data = para.getData();
        String baseFlightKey = this.getReschedueBaseFlightKey(data);
        StringBuffer mapKey = new StringBuffer().append(this.getReschedueLeftFlightKey(data));
        String pricetype = "ALL";
        logger.info("  " + DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss") + " --->" + baseFlightKey);
        @SuppressWarnings("unchecked")
        List<DispplayTrip> trip = null;
        RedisCacheManager.get(baseFlightKey, List.class);
        if (trip == null || trip.size() == 0) {
            trip = this.getIBEDataFromIBE(baseFlightKey.split("_")[0], baseFlightKey.split("_")[1],
                    baseFlightKey.split("_")[2], pricetype, "");
            RedisCacheManager.save(baseFlightKey, trip, MyEnum.CacheKeepTime.三分钟.getValue());
        }
        
        // 航司过滤
        
        // 舱位等级过滤
        trip = this.filterByCabinType(trip, data.getCabinType());
        //匹配过滤大客户协议价,此处从请求userId所在企业获取相关企业的大客户政策
        trip=this.filterPreferentialRules(para.getUserId(), trip);
        
        // 当前预定人高级舱位不可查过滤
        filterDiscount(userId, trip);
        
        // 排序、获取每个航班的最低价数据
        trip = this.paixuTrips(trip, 0);
        // 列表Key 赋值
        for (int i = 0; i < trip.size(); i++) {
            trip.get(i).setMapKey(mapKey.toString() + "|" + trip.get(i).getAirline());
        }
        return trip;
    
    }
    
    
    
}
