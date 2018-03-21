package com.viptrip.intlAirticketPC.service.impl;

import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.base.cache.CacheHelper;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.BussinessType;
import com.viptrip.base.common.MyEnum.Excessinfo;
import com.viptrip.base.common.MyEnum.ExcessinfoString;
import com.viptrip.base.common.MyEnum.OverproofShow;
import com.viptrip.base.common.MyEnum.Overproofdefault_v2;
import com.viptrip.base.common.MyEnum.PolicyType_String;
import com.viptrip.base.common.MyEnum.PolicyType_v2;
import com.viptrip.base.common.MyEnum.RuleitemsType_String;
import com.viptrip.base.common.MyEnum.isOpenType;
import com.viptrip.common.controller.GetRulesByTravller;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.common.model.Response_GetRulesByTravller;
import com.viptrip.intlAirticket.common.Constants;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.intlAirticketPC.dao.IntlAirTicketPCComDao;
import com.viptrip.intlAirticketPC.model.Request_GetIntlPCFlightList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCCabinList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCFlightList;
import com.viptrip.intlAirticketPC.model.cabinModels.RespData_Cabins;
import com.viptrip.intlAirticketPC.model.flightModels.Airport;
import com.viptrip.intlAirticketPC.model.flightModels.ArrAirPort;
import com.viptrip.intlAirticketPC.model.flightModels.Cabin;
import com.viptrip.intlAirticketPC.model.flightModels.Cabin4Flight;
import com.viptrip.intlAirticketPC.model.flightModels.Carrier;
import com.viptrip.intlAirticketPC.model.flightModels.City;
import com.viptrip.intlAirticketPC.model.flightModels.DepAirPort;
import com.viptrip.intlAirticketPC.model.flightModels.Flights;
import com.viptrip.intlAirticketPC.model.flightModels.GetIntlFlightList;
import com.viptrip.intlAirticketPC.model.flightModels.HangchengSegments;
import com.viptrip.intlAirticketPC.model.flightModels.Info;
import com.viptrip.intlAirticketPC.model.flightModels.IntlFlightFilter;
import com.viptrip.intlAirticketPC.model.flightModels.IntlFlightPolicy;
import com.viptrip.intlAirticketPC.model.flightModels.IntlFlightTrip;
import com.viptrip.intlAirticketPC.model.flightModels.IntlPCFlightListQue;
import com.viptrip.intlAirticketPC.model.flightModels.Overproof;
import com.viptrip.intlAirticketPC.model.flightModels.Passenger;
import com.viptrip.intlAirticketPC.model.flightModels.PlaneType;
import com.viptrip.intlAirticketPC.model.flightModels.Rule;
import com.viptrip.intlAirticketPC.model.flightModels.Segments;
import com.viptrip.intlAirticketPC.model.flightModels.Share;
import com.viptrip.intlAirticketPC.model.flightModels.Staffs;
import com.viptrip.intlAirticketPC.model.flightModels.Stops;
import com.viptrip.intlAirticketPC.model.flightModels.Trips;
import com.viptrip.intlAirticketPC.model.flightModels.Type;
import com.viptrip.intlAirticketPC.service.PCFlightIntlService;
import com.viptrip.resource.Const;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.OrgPolicyManage;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TCOMPANYGROUP;
import com.viptrip.wetrip.entity.policy.TExcessManage;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_PassengerMessage;
import com.viptrip.wetrip.model.policy.BerthPolicyInfo;
import com.viptrip.wetrip.model.policy.DomTicketModel;
import com.viptrip.wetrip.model.policy.IntlTicketModel;
import com.viptrip.wetrip.model.policy.PolicyTicketInfo;
import com.viptrip.wetrip.model.policy.RuleId;
import com.viptrip.wetrip.model.policy.RuleInfo;
import com.viptrip.wetrip.model.policy.RuleMaps;
import com.viptrip.wetrip.model.policy.TicketItems;
import com.viptrip.wetrip.model.policy.TicketRules;
import com.viptrip.yeego.yeego;
import com.viptrip.yeego.intlairticket.model.Filter_QueryWebFlightsIntl_1_0;
import com.viptrip.yeego.intlairticket.model.Request_QueryWebFlightsI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
@Service
public class PCFlightIntlServiceImpl implements PCFlightIntlService{
	@Resource
	private IntlAirTicketPCComDao dao;
	@Resource
    private ComDao comDao;
	private static Logger logger = LoggerFactory.getLogger(PCFlightIntlServiceImpl.class);
	
	/**
	 * 查询航班信息,包括航班详情和返程列表信息
	 * @param req
	 * @return
	 */
	public OutputResult<Response_QueryWebFlightsI_1_0, String> queryIntlFlight(
			Request_GetIntlPCFlightList req) {
		OutputResult<Response_QueryWebFlightsI_1_0, String> or = 
				new OutputResult<Response_QueryWebFlightsI_1_0, String>();
		try {
			Request_QueryWebFlightsI_1_0 qryFlight = new Request_QueryWebFlightsI_1_0();
			// A :所有航班(包括中转) 默 认 D :直达;此处默认全部
			String stopType = "A";
			// 是否仅使用机场代码查询 Y:表示请求内输入的三字 码含义为机场 N:表示请求内输入的三字 码为城市 不填默认为Y
			// 国际查询使用城市三字码查询，一个城市多个机场的要返回多个机场的航班信息
			String airportOnly = "N";
			qryFlight.setOrgcity(req.data.trip.segments.get(0).depAirport);
			qryFlight.setDetcity(req.data.trip.segments.get(0).arrAirport);
			qryFlight.setStarttime(req.data.trip.segments.get(0).date);
			qryFlight.setStopType(stopType);
			qryFlight.setTravelType(CommonMethodUtils.travelTypeToStr(req.data.trip.type));//字段名称问题，flightType为单程往返标识。
			if (req.data.trip.type==MyEnum.TripTypeInt.单程.getValue()) {
				qryFlight.setArrvitime("");
			} else {
				qryFlight.setArrvitime(req.data.trip.segments.get(1).date);
			}
			qryFlight.setPsgCode(CommonMethodUtils.passengerTypeStr(req.data.filter.passenger.get(0).type));
			if(req.userId!=null||"A".equals(req.data.ticketGroup)){
				List objList = dao.queryBySQL("select ORGID from t_ac_org tt where tt.orgtype  = '2' " +
						"start with orgid =( select orgid from t_ac_user tac where " +
						" tac.userid = ?) connect by prior tt.parentid = tt.orgid ",new Object[]{req.userId});
				if(null!=objList&&objList.size()>0)
					qryFlight.setOrgid(objList.get(0).toString());
				else
					or.result="查询机构信息异常。";
			}
			if(!"A".equals(req.data.ticketGroup)){
				qryFlight.setTicketGroup(req.data.ticketGroup);
			}
			qryFlight.setAirportOnly(airportOnly);
			or = new OutputResult<Response_QueryWebFlightsI_1_0,String>();
			or = queryWebFlightsIntl(qryFlight);

		} catch (Exception e) {
			logger.error("--->>国际航班查询易购平台返回,"+DateUtil.date2Str(new Date(),"yyyy-MM-dd HH:mm:ss")+":"+e.getMessage());
			e.printStackTrace();
			or.code=Constant.Code_Failed;
			or.result=e.getMessage();
		}
		return or;
	}
	
	public OutputResult<Response_QueryWebFlightsI_1_0,String> queryWebFlightsIntl(
			Request_QueryWebFlightsI_1_0 qryFlight) {

		Request_QueryWebFlightsI_1_0 qryFlight_ = new Request_QueryWebFlightsI_1_0();
		OutputResult<Response_QueryWebFlightsI_1_0,String> resq = new OutputResult<Response_QueryWebFlightsI_1_0,String>();
		String message = "";
		String ticketGroup = "A";
		if(!"A".equals(qryFlight.ticketGroup)&&qryFlight.ticketGroup!=null&&!"".equals(qryFlight.ticketGroup)){
			ticketGroup = qryFlight.ticketGroup;
		}
 		if (StringUtil.isEmpty(qryFlight.orgcity)) {
			message = "行程必须输入出发地";
			resq.code = 1;
			resq.result = message;
			return resq;
		} else {
			qryFlight_.setOrgcity(qryFlight.orgcity);
		}

		if (StringUtil.isEmpty(qryFlight.detcity)) {
			message = "行程必须输入出发地";
			resq.code = 1;
			resq.result = message;
			return resq;
		} else {
			qryFlight_.setDetcity(qryFlight.detcity);
		}
		if (StringUtil.isEmpty(qryFlight.starttime)) {
			message = "行程必须输入出发时间";
			resq.code = 1;
			resq.result = message;
			return resq;
		} else {
			qryFlight_.setStarttime(qryFlight.starttime);
		}
		qryFlight_.setStopType(qryFlight.stopType);
		qryFlight_.setTravelType(qryFlight.travelType);

		// 注意：单程也需要传递属性过去
		if (qryFlight.travelType.equals("RT")) {
			if (StringUtils.isEmpty(qryFlight.arrvitime)) {
				message = "往返行程必须输入返程日期";
				resq.code = 1;
				resq.result = message;
				return resq;
			}
		}
		qryFlight_.setArrvitime(qryFlight.arrvitime);

		if (StringUtil.isEmpty(qryFlight.carrier)) {
			qryFlight_.setCarrier("");
		} else {
			qryFlight_.setCarrier(qryFlight.carrier);
		}

		if (StringUtil.isEmpty(qryFlight.carrier)) {
			qryFlight_.setCarrier("");
		} else {
			qryFlight_.setCarrier(qryFlight.carrier);
		}

		qryFlight_.setAirportOnly(qryFlight.airportOnly);// 默认根据机场三字码查询

		qryFlight_.setSearchPriority(qryFlight.searchPriority);// 排序规则

		qryFlight_.setPsgCode(qryFlight.psgCode);
		qryFlight_.setPsgQuantity(qryFlight.psgQuantity);

		qryFlight_.setSingleTravel(qryFlight.singleTravel);
		if ("Y".equals(qryFlight.singleTravel)) {
			qryFlight_.setSingleTravelType(qryFlight.singleTravelType);
		}

		if (StringUtil.isEmpty(qryFlight.flightNo)) {
			qryFlight_.setFlightNo("");
		} else {
			qryFlight_.setFlightNo(qryFlight.flightNo);
		}

		if (StringUtil.isEmpty(qryFlight_.negotiatedFaresOnly)) {
			qryFlight_.setNegotiatedFaresOnly("N");
		} else {
			qryFlight_.setNegotiatedFaresOnly(qryFlight.negotiatedFaresOnly);
		}

//		 qryFlight_.setCodes(qryFlight.codes);
		if (StringUtils.isEmpty(qryFlight.searchPriority)) {
			qryFlight_.setSearchPriority("");
		} else {
			qryFlight_.setSearchPriority(qryFlight.searchPriority);
		}

		if (StringUtils.isEmpty(qryFlight.psgCode)) {
			qryFlight_.setPsgCode("ADT");
		} else {
			qryFlight_.setPsgCode(qryFlight.psgCode);
		}

		if (StringUtils.isEmpty(qryFlight.psgQuantity)) {
			qryFlight_.setPsgQuantity("1");
		} else {
			qryFlight_.setPsgQuantity(qryFlight.psgQuantity);
		}
		if(StringUtil.isNotEmpty(qryFlight.orgid)&&"A".equals(ticketGroup)){
			TCOMPANYGROUP group = (TCOMPANYGROUP) dao.queryForEntity(Long.parseLong(qryFlight.orgid),TCOMPANYGROUP.class);
			if(group != null){
				ticketGroup = group.getCompanyGroup().toString();
			}
		}
		String key = getQueryIntlFlightKey(qryFlight_.orgcity,
				qryFlight_.detcity, qryFlight_.psgCode, qryFlight_.travelType,
				qryFlight_.starttime, qryFlight_.arrvitime,ticketGroup);
		
		qryFlight_.setMaxResultCount(Constants.MAXRESULT);
		Response_QueryWebFlightsI_1_0 resultobj = null;
		try {
			// 从缓存中获取
			resq=CacheHelper.GetFromCache(Response_QueryWebFlightsI_1_0.class, key);
			resultobj = resq.getResultObj();
			logger.info("------->>>FlightIntlServiceImpl-- 国际机票从缓存获取航班信息。");
		} catch (Exception e) {
			logger.info("--ERROR-->FlightIntlServiceImpl---queryWebFlightsIntl-->> 国际机票从缓存获取航班出错.");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		if (resultobj != null && resultobj.flights != null && resultobj.flights.size() > 0) {
			resq.setResultObj(resultobj);
		} else {
			resq.setResultObj(new Response_QueryWebFlightsI_1_0());
			Filter_QueryWebFlightsIntl_1_0 fi = new Filter_QueryWebFlightsIntl_1_0();
			fi.DataFormat = "J";
			if(!"".equals(ticketGroup)){
				fi.CostomerGroup = ticketGroup;
			}
			Boolean flag = yeego.Request(qryFlight_, resq, fi);
			String strTriptyp = "1";
			if (qryFlight_.travelType.equals("RT")) {
				strTriptyp = "2";
			}
			if(flag){
				// 同步到缓存
				CacheHelper.SaveToCache(key, resq.getResultObj(), MyEnum.CacheKeepTime.十分钟);
				logger.info("------->>>FlightIntlServiceImpl-- 国际机票航班信息存入缓存，实效10分钟。");
			}else resq.code = Constant.Code_Failed;
			
		}
		if(resq.getResultObj()!=null)
			resq.getResultObj().ticketGroup = ticketGroup;
		return resq;
	}
	
	/*
	 * 获取查询国际航班的key，格式为：PEK_HKG_ADT_RT_2017-02-20_2017-02-25_C
	 */
//	public String getQueryIntlFlightKey(String orgCityCode,
//			String detCityCode, String psgType, String travelType,
//			String goDatetime, String backDatetime,String ticketGroup) {
//		Request_GetIntlPCFlightList req = new Request_GetIntlPCFlightList();
//		req.data = new IntlPCFlightListQue();
//		req.data.trip.segments.get(0).depAirport=orgCityCode;//出发城市
//		req.data.trip.segments.get(0).arrAirport = detCityCode;//到达机场/城市三字码
//		req.data.filter.passenger.get(0).type = CommonMethodUtils.passengerTypeStr(psgType);//乘客类型枚举
//		req.data.trip.type = CommonMethodUtils.travelTypeToStr(travelType);//航程类型枚举
//		req.data.trip.segments.get(0).date = goDatetime;//出发时间
//		req.data.trip.segments.get(1).date = backDatetime;//到达时间
//		req.data.ticketGroup = ticketGroup;
//		return getQueryIntlFlightKey(req);
//	}
	public String getQueryIntlFlightKey(String orgCityCode,
			String detCityCode, String psgType, String travelType,
			String goDatetime, String backDatetime,String ticketGroup) {
		StringBuffer keySB = new StringBuffer();
		keySB.append(orgCityCode.trim()).append("_");//出发城市
		keySB.append(detCityCode.trim()).append("_");//到达机场/城市三字码
		keySB.append(psgType.trim()).append("_");//乘客类型枚举
		keySB.append(travelType.trim()).append("_");//航程类型枚举
		keySB.append(goDatetime.trim());//出发时间
		if (CommonMethodUtils.travelTypeToStr(travelType)==MyEnum.TripTypeInt.往返.getValue()) {
			keySB.append("_" + backDatetime.trim());//到达时间
		}
		if (ticketGroup!=null&&!"".equals(ticketGroup)) {
			keySB.append("_" + ticketGroup.trim());
		}
		return keySB.toString();
	}
	public String getQueryIntlFlightKey(Request_GetIntlPCFlightList req,String ticketGroup) {
		req.data.ticketGroup = ticketGroup;
		return getQueryIntlFlightKey(req);
	}

	/*
	 * 获取查询国际航班的key，格式为：PEK_HKG_ADT_RT_2017-02-20_2017-02-25
	 */
	public String getQueryIntlFlightKey(Request_GetIntlPCFlightList req) {
		StringBuffer keySB = new StringBuffer();
		keySB.append(req.data.trip.segments.get(0).depAirport.trim()).append("_");
		keySB.append(req.data.trip.segments.get(0).arrAirport.trim()).append("_");
		keySB.append(CommonMethodUtils.passengerTypeStr(req.data.filter.passenger.get(0).type)).append("_");
		keySB.append(CommonMethodUtils.travelTypeToStr(req.data.trip.type) + "_");
		keySB.append(req.data.trip.segments.get(0).date.trim());
		if (req.data.trip.type==MyEnum.TripTypeInt.往返.getValue()) {
			keySB.append("_" + req.data.trip.segments.get(1).date.trim());
		}
		if (req.data.ticketGroup!=null&&!"".equals(req.data.ticketGroup)) {
			keySB.append("_" + req.data.ticketGroup.trim());
		}
		return keySB.toString();
	}
	
	/*
	 * 获取查询国际航班列表的请求参数
	 */
	public Request_GetIntlPCFlightList getParam_RequestIntlFlightList( Long userId, String key4GetFlight) {
		Request_GetIntlPCFlightList req;
		String[] filter = key4GetFlight.split("_");
		req = new Request_GetIntlPCFlightList();
		req.userId = userId;
		req.data = new IntlPCFlightListQue();
		req.data.trip = new IntlFlightTrip();
		req.data.trip.segments = new ArrayList();
		Segments s = new Segments();
		s.depAirport = filter[0];
		s.arrAirport = filter[1];
		s.date = filter[4];
		req.data.trip.segments.add(s);
		req.data.filter = new IntlFlightFilter();
		req.data.filter.passenger = new ArrayList();
		Passenger p = new Passenger();
		p.type = CommonMethodUtils.passengerTypeStr(filter[2]);
		req.data.filter.passenger.add(p);
		req.data.trip.type = CommonMethodUtils.travelTypeToStr(filter[3]);
		if(filter.length > 6 && filter[5].length()>1){
			s = new Segments();
			s.depAirport = filter[1];
			s.arrAirport = filter[0];
			s.date = filter[5];
			req.data.trip.segments.add(s);
		}
		req.data.ticketGroup = filter[filter.length-1].length()< 2 ? filter[filter.length-1] :"";
		return req;
	}

	/**
	 * 往返查询舱位剩余数量
	 * @param exp
	 * @param cabin
	 * @return
	 */
	public static String getSeatsLeftByCabin(List<String> exps,String cabin){
		String seatLeft="9";
		String[] splits=cabin.split("/");
		int index=0;
		for(String s:splits){
			String[] ss=s.split(",");
			for(String signalCabin:ss){
				seatLeft=getSamllerStringNumber(seatLeft,
						getSeatsLeftByCabin(exps.get(index),signalCabin));
				index++;
			}
		}
		return seatLeft;
	}
	
	private static String getSamllerStringNumber(String num1,String num2){
		
		if(Integer.parseInt(num1)>Integer.parseInt(num2)){
			return num2;
		}
		return num1;
	}
	
	public static String getSeatsLeftByCabin(String exp, String cabin) {
		// C:4,D:4,Z:4,K:4,J:4,R:4,Y:9,B:9,M:9,A:9,H:9,S:9,O:9,E:9,Q:9,T:9,L:9,I:4
		String[] splits = exp.split(",");
		HashMap<String, String> hm = new HashMap<String, String>();
		for (String s : splits) {
			String[] sps = s.split(":");
			hm.put(sps[0], sps[1]);
		}
		String left = hm.get(cabin);
		if (left == null || "".equals(left)){
			left = "9";
			logger.info("==========根据舱位获取剩余座位数为空，设置座位数为9。=========="+hm+",cabin="+cabin);
		}
		return left;
	}
	
	/**
	 * object 转 字符串
	 * @param object
	 * @return
	 */
	private  String obj2str(Object object) {
		if(null!=object){
			return object.toString();
		}else{
			return null;
		}
	}
	/**
	 * object 转 double
	 * @param object
	 * @return
	 */
	private  Double str2double(String str) {
		if(null!=str && !"".equals(str)){
			return Double.valueOf(str);
		}else{
			return 0.0d;
		}
	}
	/**
	 * object 转 integer
	 * @param object
	 * @return
	 */
	private  Integer str2int(String str) {
		if(null!=str && !"".equals(str)){
			return Integer.valueOf(str);
		}else{
			return 0;
		}
	}
	
	/**
	 * 航司响应数据转换为凯撒接口响应对象
	 */
	public OutputResult<Response_GetIntlPCFlightList, String> transition2Object(Response_QueryWebFlightsI_1_0 repson, Request_GetIntlPCFlightList req){
		OutputResult<Response_GetIntlPCFlightList, String> out = 
				new OutputResult<Response_GetIntlPCFlightList, String>();
		Response_GetIntlPCFlightList resp = new Response_GetIntlPCFlightList();
		resp.data = new GetIntlFlightList();
		
		Hashtable<String, String[]> airPortsHt=repson.airPortsHt;//机场
		Hashtable<String, String[]> airLinesTable=repson.airLines;//航空公司信息
		Hashtable<String, String[]> airType=repson.airType;//机型代码
		
		Hashtable<String,Hashtable<String,Object[]>> flights = repson.flights;//航段
		Hashtable<String, Hashtable<String,Hashtable<String,Object[]>>> flgithPrices = repson.flgithPrices;//航段价格列表
		
		String ticketGroup = repson.ticketGroup;
		
		//机场信息
		resp.data.airports =getAirpModel(airPortsHt);
		//航空公司信息
		resp.data.carriers = getAirLineModel(airLinesTable);
		//机型信息
		resp.data.planeTypes = getPlaneModel(airType);
		
		//行程列表信息
		List<Trips> trips = new ArrayList<Trips>();
		Trips tripMdl = null;
		try {
			Set<String> keys = flights.keySet();//航段 F1~...F27...~Fn
			String mapKeyStr = getQueryIntlFlightKey(req, ticketGroup);
			for(String key :keys){
				tripMdl = new Trips();
				tripMdl.date = DateUtil.parse(req.data.trip.segments.get(0).date,"yyyy-MM-dd");
				//航班信息key--mapKey  PEK_HKG_ADT_RT_2017-02-20_2017-02-25|CA1234_MU520_FM1122*CA1235_MU521_FM1123
				StringBuilder mapKey = new StringBuilder();
				mapKey.append(mapKeyStr).append("|");// PEK_HKG_ADT_RT_2017-02-20_2017-02-25|
				Hashtable<String,Hashtable<String,Object[]>> price = flgithPrices.get(key);//航段价格
				Hashtable<String,Object[]> value = flights.get(key);//航段
				Cabin cabinMdl = null;
				HangchengSegments segmentsMdl = null;
				tripMdl.segments = new ArrayList<HangchengSegments>();
				String cabinCode = "";
				for (Entry<String, Hashtable<String, Object[]>> et : price.entrySet()) {
					cabinCode = et.getKey();//舱位组合 去程/回程
					tripMdl.totalBasePrice = str2double(obj2str(((Object[]) et.getValue().get("BaseFare"))[0]));//票面价总计金额
					tripMdl.totalTaxPrice = str2double(obj2str(((Object[]) et.getValue().get("TotalFare"))[0]))-tripMdl.totalBasePrice;//含税总计金额
					
					//航程信息 去程或回程的信息循环
					for(int segNum = 1;segNum<cabinCode.split("/").length+1;segNum++){
						segmentsMdl = new HangchengSegments();
						segmentsMdl.flowId = segNum;//航程序号，1表示第一程，2表示第二程，如往返返程或联程第二段
						mapKey.append(segNum==1?"":"*");
						Object[] qu = value.get("S"+segNum);//去程1，回程2
						ArrayList<Object> o1 = (ArrayList<Object>) qu[0]; 
						segmentsMdl.transitCount = str2int(obj2str(o1.get(10)));//中转次数
						segmentsMdl.flyMinutes = str2int(obj2str(o1.get(11)));//总飞行分钟数
						//去程或回程的 舱位组合信息
						Object[] objs = (Object[]) et.getValue().get("ADT");
						cabinMdl = new Cabin();
						cabinMdl.code = obj2str(objs[4]).split("/")[segNum-1].toUpperCase().replace(",", "/");//舱位代码组合  Q,Q/V,Q
						cabinMdl.desc = getCabinDescsByTypes(obj2str(objs[5]).split("/")[segNum-1].toUpperCase(),repson.cangweiLevelDesc);//舱位描述组合--字符串，经济舱/超值公务舱
						cabinMdl.type = getCabinTypesByTypes(cabinMdl.desc);//舱位等级组合--字符串，1/2
						cabinMdl.seats = str2int(obj2str(objs[13]));//舱位组合剩余票数
						segmentsMdl.cabin = cabinMdl;
						
						//航班信息 去程或回程的 中转 详情
						List<Flights> flightList = new ArrayList<Flights>();
						Flights flightMdl = null;
						ArrayList<Object> o2 = (ArrayList<Object>) qu[1];
//					segmentsMdl.stopCount//经停次数 放中转详情中。TODO  L484 可获取经停次数
						int flightNum =0;
						System.out.println(o2.size()+"~~~~~~~~~~o2.size()~~~~~经停次数~~~~~~~~~~~~~~~~~~~");
						for(Object o: o2){
							ArrayList<Object> o3 = (ArrayList<Object>) o;
							flightMdl = new Flights();
							flightMdl.flowId = ++flightNum;//航班序号,1,2,3,4...
							flightMdl.carrier = obj2str(o3.get(0));//航空公司二字码
							flightMdl.flightNo = obj2str(o3.get(1));//航班号
							mapKey.append((flightMdl.flowId==1?"":"_")+flightMdl.flightNo);
							//				flightMdl.share = ;//共享信息（实际承运） TODO 
							flightMdl.share = new Share();
							flightMdl.share.carrier = obj2str(o3.get(19));//共享航司二字码  
							flightMdl.share.flightNo = obj2str(o3.get(19));//共享航班号
							
							DepAirPort dep = new DepAirPort();//出发机场信息
							dep.terminal = obj2str(o3.get(11));//起飞航站楼
							dep.depDatetime = obj2str(o3.get(4))+" "+obj2str(o3.get(5));//起飞日期时间
							dep.code = obj2str(o3.get(2));//出发机场三字码
							flightMdl.depAirPort = dep;
							
							ArrAirPort arr = new ArrAirPort();//到达机场信息
							arr.terminal = obj2str(o3.get(12));//到达航站楼
							arr.arrDatetime = obj2str(o3.get(6))+" "+obj2str(o3.get(7));//到达时间
							arr.code = obj2str(o3.get(3));//到达机场三字码
							flightMdl.arrAirPort = arr;
							
							List<Stops> stopsList = new ArrayList<Stops>();//经停机场信息
							Stops ss = null;
							List<Object> jingting = (List<Object>) o3.get(10);
							ss = new Stops();
							ss.code = obj2str(obj2str(jingting.get(1)));//经停机场三字码
							ss.minues = str2int(obj2str(jingting.get(2)));//经停分钟数
							stopsList.add(ss);
							flightMdl.stops = stopsList;
							
//					for (Object obj : (List<Object>)o3.get(10)) {
////						if(!"0".equals(obj2str(obj[1]))){
////							ss = new Stops();
////							ss.airport = obj2str(obj[1]);//经停机场三字码
////							ss.minues = str2int(obj2str(obj[2]));//经停分钟数
////							stopsList.add(ss);
////						}
//					}
							flightMdl.type = new Type();
							flightMdl.type.code = obj2str(o3.get(9));//机型信息--机型代码
							flightMdl.type.info = resp.data.planeTypes.get(flightMdl.type.code).name
									+resp.data.planeTypes.get(flightMdl.type.code).airframe;//机型信息，用于简述信息
							
							flightMdl.flyMinutes = str2int(obj2str(o3.get(8)));//飞行分钟数
							
							flightMdl.meal = obj2str(o3.get(13));//餐食信息
							flightMdl.mileages = str2int(obj2str(o3.get(14)));//里程数
							flightMdl.fun = str2int(obj2str(o3.get(17)));//娱乐标识
							flightMdl.eTicket = str2int(obj2str(o3.get(18)));//电子客票标识
							flightMdl.cabin = new Cabin4Flight();
							flightMdl.cabin.code = cabinMdl.code.split("/")[flightNum-1].toUpperCase();// 去程或回程的舱位信息 
							flightMdl.cabin.desc = cabinMdl.desc;//舱位描述  经济舱全价   去程经停 循环
							flightMdl.cabin.type = getCabinTypeByCode(flightMdl.cabin.desc);//舱位等级	1=经济舱，2=公务舱，3=头等舱
							flightMdl.seats = str2int(getSeatsLeftByCabin(obj2str(o3.get(15)),cabinCode.split("/")[segNum-1].toUpperCase()));
//					int indexSeat = obj2str(o3.get(15)).indexOf(cabinCode.split("/")[segmentsFlowId].toUpperCase());
//					flightMdl.cabin.code = obj2str(o3.get(15)).substring(indexSeat, indexSeat+1);//舱位信息 
//					flightMdl.seats = str2int(obj2str(o3.get(15)).substring(indexSeat+2, indexSeat+3));// 剩余票数 C:9,D:9,J:9,Y:9,B:9,H:9,K:9,L:9,M:9,N:9,S:9,X:9,Q:9,T:5,V:5,W:5,R:5
							
							//				flightMdl.overproof = //超标信息 TODO
							
							/*	HangBan hangBan = new HangBan();
	//				hangBan.setCarrier(obj2str(o3.get(0)));//航司二字码
	//				hangBan.setAirline(obj2str(o3.get(1)));//航班号
	//				hangBan.setStartAirportCode(obj2str(o3.get(2)));//起飞机场代码
	//				hangBan.setEndAirportCode(obj2str(o3.get(3)));//到达机场代码
					hangBan.setStartDateTime(obj2str(o3.get(4))+" "+obj2str(o3.get(5)));//起飞时间
					hangBan.setEndDateTime(obj2str(o3.get(6))+" "+obj2str(o3.get(7)));//到达时间
	//				hangBan.setTravlTime(obj2str(o3.get(8)));//行程时间
	//				hangBan.setTravlTime(DateUtil.getDiffTimeStr(Long.valueOf(Integer.valueOf(hangBan.getTravlTime())*60000)));
	//				hangBan.setPlaneType(obj2str(o3.get(9)));//机型
					hangBan.setOrgTerm(obj2str(o3.get(11)));//起飞航站楼
					hangBan.setDetTerm(obj2str(o3.get(12)));//到达航站楼
					hangBan.setCangwei(obj2str(o3.get(15)));//仓位
							 */	//				TODO 以上未注释信息需要增加
							flightList.add(flightMdl);
						}
						
						segmentsMdl.flights = flightList; 
						
						/*HangBan hangBan = new HangBan();
				hangBan.setStartAirportCode(obj2str(o1.get(2)));//出发地机场代码
				hangBan.setOrgTerm(obj2str(o1.get(3)));//出发地机场航站楼
				hangBan.setStartDateTime(obj2str(o1.get(4))+" "+obj2str(o1.get(5)));//出发地起飞时间
				hangBan.setEndAirportCode(obj2str(o1.get(6)));//目的地机场代码
				hangBan.setDetTerm(obj2str(o1.get(7)));//目的地机场航站楼
				hangBan.setEndDateTime(obj2str(o1.get(8))+" "+obj2str(o1.get(9)));//目的地到达时间
				hangBan.setTravlTime(obj2str(o1.get(11)));//航程时间
				hangBan.setTravlTime(DateUtil.getDiffTimeStr(Long.valueOf(Integer.valueOf(hangBan.getTravlTime())*60000)));*/
						//***设置航程的中转次数
						//			hangcheng.setTransitCount(obj2str(o1.get(10)));
						
						//			segmentsMdl.stopCount = //经停次数 TODO int zhongzhuanCount = flights.length - 1;// 中转次数
						tripMdl.segments.add(segmentsMdl);//航程信息
						
					}
					
				}
				tripMdl.mapKey= mapKey.toString();
				trips.add(tripMdl);//行程列表信息
			}
			//差旅信息
			resp.data.trips = filterPolicyForTrips(req.userId, req.data.policy, trips);
			
			out.setResultObj(resp);
			out.code=Constant.Code_Succeed;
		} catch (Exception e) {
			out.result="行程列表转换报错，错误代码201801221547Li，错误原因："+e.getMessage();
			logger.error("行程列表转换报错，错误代码201801221547Li，resp.data："+resp.data);
		}
		return out;
		
	}
	
	/**
	 * 通过舱位代码获取舱位描述  1=经济舱，2=公务舱，3=头等舱
	 * @param code 舱位类型  "E,E"
	 * @return
	 */
	private String getCabinDescsByTypes(String code, Hashtable<String, String> cangweiLevelDesc) {
		String cabinDescs = "";
		String[] cabinDesc= code.split(",");
		if(cabinDesc.length>1){//往返舱位组合
			cabinDescs = cangweiLevelDesc.get(cabinDesc[0])+"/"+cangweiLevelDesc.get(cabinDesc[1]);
			
		}else if(cabinDesc.length>0){
			cabinDescs = cangweiLevelDesc.get(cabinDesc[0]);
		}
		return cabinDescs;
	}

	/**
	 * 通过舱位描述获取舱位等级  1=经济舱，2=公务舱，3=头等舱
	 * @param cabinDescs 舱位描述
	 * @return 舱位等级组合
	 */
	private String getCabinTypesByTypes(String cabinDescs) {
		String[] strCabin= cabinDescs.split("/");
		String cabinType = "";
		if(strCabin.length>1){//往返舱位组合
			cabinType = getCabinTypeByCode(strCabin[0])+"/"+getCabinTypeByCode(strCabin[1]);
			
		}else if(strCabin.length>0){
			cabinType = getCabinTypeByCode(strCabin[0])+"";
		}
		return cabinType;
	}
	private Integer getCabinTypeByCode(String code) {
		Integer cabinType = 0;
		if(null!=code && !"".equals(code)){
			cabinType = code.indexOf("公务")>-1?2:(code.indexOf("头等")>-1?3:1);
		}else{
			logger.error("舱位等级转换异常，报错代码 201801181440Li; 舱位描述==="+code);
			cabinType = 0;
		}
		return cabinType;
	}

	/**
	 * 机场信息
	 * @param airPortsHt
	 * @return
	 */
	private Map<String, Airport> getAirpModel(Hashtable<String, String[]> airPortsHt) {
		Map<String, Airport> airpModel = new HashMap<String, Airport>();
		Airport airEntity = null;
		for(Entry<String, String[]> entry: airPortsHt.entrySet()){//"机场三字码":["机场简称","机场全称","机场所在城市","城市三字码"]
			airEntity = new Airport();
			airEntity.code = entry.getKey();//机场三字码
			airEntity.name = entry.getValue()[0];//机场名称（简称）
			airEntity.fullName = entry.getValue()[1];//机场全称
//			airEntity.terminal = entry.getValue()[];//航站楼
			airEntity.city = new City();//机场所在城市信息
			airEntity.city.code = entry.getValue()[3];//城市三字码
			airEntity.city.name = entry.getValue()[2];//城市名称
			
			airpModel.put(entry.getKey(), airEntity);
		}
		return airpModel;
	}
	/**
	 * 航空公司信息
	 * @param airLinesTable
	 * @return
	 */
	private Map<String, Carrier> getAirLineModel(Hashtable<String, String[]> airLinesTable) {
		Map<String,Carrier> airLineModel = new HashMap<String,Carrier>();
		Carrier c = null;
		for(Entry<String,String[]> entry:airLinesTable.entrySet()){//"MU":["东方航空","东航","1"]
			c = new Carrier();
			c.code = entry.getKey();//航司二字代码
			c.fullName = entry.getValue()[0];//航司中文名全称
			c.name = entry.getValue()[1];//航司中文名简称
			
			airLineModel.put(entry.getKey(), c);
		}
		return airLineModel;
	}
	/**
	 * 机型信息
	 * @param airType
	 * @return
	 */
	private Map<String, PlaneType> getPlaneModel(Hashtable<String, String[]> airType) {
		Map<String,PlaneType> planeModel = new HashMap<String,PlaneType>();
		PlaneType p = null;
		for(Entry<String,String[]> entry:airType.entrySet()){//"73H":["波音73H","737-800 Winglet","窄体","162","189"]
			p = new PlaneType();
			p.code = entry.getKey();//机型代码
			p.name = entry.getValue()[0];//机型名称
			p.model = entry.getValue()[1];//型号
			p.airframe = entry.getValue()[2];//机体描述
			p.min = "".equals(entry.getValue()[3])?0:Integer.valueOf(entry.getValue()[3]);//最少人数
			p.max = "".equals(entry.getValue()[4])?0:Integer.valueOf(entry.getValue()[4]);//最多人数
			
			planeModel.put(entry.getKey(), p);
		}
		
		return planeModel;
	}
	
	/**
	 * 更多舱位--航班列表查询
	 * 
	 */
	public OutputResult<FlightResult,String> getFlightResult(Long userId,String mapKey, String type,String cangwei) {
		OutputResult<FlightResult,String> or=new OutputResult<FlightResult,String>();
		boolean isNeedNextStep=true;
		if((StringUtil.isNotEmpty(type) && "rule".equals(type))
				&& (cangwei == null || "".equals(cangwei))){
			isNeedNextStep=false;
			or.code=Constant.Code_Failed;
			or.result="查询退改签规则，必须提供舱位信息。";
		}
		if(isNeedNextStep){
			FlightResult result = new FlightResult();
			
			String unformatMapkey = CommonMethodUtils.getUnFormatMapkey(mapKey);
			Set<Entry<String, Hashtable<String, Object[]>>> targetFs = null;
			String[] splits = unformatMapkey.split("\\|");
			if (splits == null || splits.length == 0) {
				or.code=Constant.Code_Failed;
				or.result="无效的mapKey--" + mapKey;
			}
			String key4GetFlight = splits[0];
			//从缓存中获取
			OutputResult<Response_QueryWebFlightsI_1_0,String> resq = 
			CacheHelper.GetFromCache(Response_QueryWebFlightsI_1_0.class, key4GetFlight);
			Response_QueryWebFlightsI_1_0 flightList = resq.getResultObj();
			
			if (flightList == null || flightList.flights == null) {// 缓存没有，则查询
				Request_GetIntlPCFlightList req = getParam_RequestIntlFlightList(userId, key4GetFlight);
				OutputResult<Response_QueryWebFlightsI_1_0,String> tempOR=queryIntlFlight(req);
				if(tempOR.code==Constant.Code_Succeed){
					flightList=tempOR.getResultObj();
				}else{
					or.code=tempOR.code;
					or.result=tempOR.result;
				}
			}
			if (flightList != null) {
				targetFs = GetTargetFs(splits[1], flightList);// 目标航班信息
				if (targetFs == null || targetFs.size() == 0) {
					or.code=Constant.Code_Failed;
					or.result="查询航班信息失败。";
				} else {
					result.succeed = true;
					result.unformatMapkey = unformatMapkey;
					result.baseTripKey = key4GetFlight;
					result.flightNoKey = splits[1];
					result.resultObj = flightList;
					result.targetFs = targetFs;
					or.setResultObj(result);
					or.code=Constant.Code_Succeed;
				}
			}
		}
		return or;
	}
	/**
	 * 筛选符合航班mapkey的航段信息
	 * @param flightNos
	 * @param flightList
	 * @param type
	 * @return
	 */
	private Set<Entry<String, Hashtable<String, Object[]>>> GetTargetFs(String flightNos,
			Response_QueryWebFlightsI_1_0 flightList) {
		Set<Entry<String, Hashtable<String, Object[]>>> targetFs = new HashSet<Entry<String, Hashtable<String, Object[]>>>();
		// CA1234_MU520_FM1122*CA1235_MU521_FM1123
		for (Entry<String, Hashtable<String, Object[]>> entry : flightList.flights.entrySet()) {
			if (flightNos.equals(CommonMethodUtils.getZhongZhuanFlightNos2(entry.getValue()))) {
				targetFs.add(entry);
			}
		}

		return targetFs;
	}

	@Override
	public OutputResult<Response_GetIntlPCCabinList, String> formatResp4Cabin(
			Hashtable<String, Hashtable<String, Object[]>> htFs, FlightResult result) {
		OutputResult<Response_GetIntlPCCabinList, String> out = new OutputResult<Response_GetIntlPCCabinList, String>();
		Response_GetIntlPCCabinList l = new Response_GetIntlPCCabinList();
		try {
			l.data = new ArrayList<RespData_Cabins>();
			RespData_Cabins cabinModel = null;
			for (Entry<String, Hashtable<String, Object[]>> et : htFs.entrySet()) {
				cabinModel = new RespData_Cabins();
				Object[] psgPrice = et.getValue().get(CommonMethodUtils.getPassengerType(result.baseTripKey));
				cabinModel.totalBasePrice = null==psgPrice[0]?0.0:Double.valueOf(psgPrice[0].toString());
				cabinModel.totalTaxPrice = CommonMethodUtils.getDoubleStringWithoutDot(Double.valueOf(et
						.getValue().get("TotalFare")[0].toString())
						- Double.valueOf(cabinModel.totalBasePrice));
//			cabinModel.priceType = ; TODO 价格类型？
				Cabin c = new Cabin();
				c.code = String.valueOf(psgPrice[4]);
				c.desc = CommonMethodUtils.getCabinTypeName(result.resultObj, String.valueOf(psgPrice[5]));
				c.seats = null==psgPrice[13]?0:Integer.valueOf(psgPrice[13].toString());
				cabinModel.cabin = c;
				
				l.data.add(cabinModel);
			}
			out.setResultObj(l);
			out.code = Constant.Code_Succeed;
			
		} catch (Exception e) {
			out.result = "错误代码：201801231438Li,更多舱位返回信息格式化错误，错误原因："+e.getMessage();
		}
		return out;
	}
	
	/**
	 * 国际机票PC差旅管控--begin---------------------
	 * @param userid
	 * @param passengers
	 * @param trips
	 * @return
	 */
	public List<Trips> filterPolicyForTrips(long userid, IntlFlightPolicy passengers, List<Trips> trips) {
        if (trips==null || passengers == null || passengers.staffIds!=null || passengers.businessType!=null) {
            return trips;
        }
        TAcUser tacuser = null;
        String selectorguser = "";
        OrgPolicyManage orgPolicyManage = new OrgPolicyManage(); // 企业差旅政策管理
        Map<String, List<PolicyTicketInfo>> regularMap = null;
        Map<String, String> selectUserMap = new HashMap<String, String>();
        try {
			
	        List<Integer> userIds = Arrays.asList(passengers.staffIds);
	        for (Integer uid : userIds) {
	            selectUserMap.put(String.valueOf(uid),getUserNameByid(uid));
			}
	
	        Request_GetRulesByTravller rg = new Request_GetRulesByTravller();
	        List<Integer> blist = new ArrayList<Integer>();
	        List<Integer> tlist = userIds;
	        blist.add(BussinessType.国际机票.getValue());
	        if (userid == 0) {
	            // rg.setUserId(para.getUserId());
	        } else {
	            rg.setUserId(userid);
	        }
	        rg.businessTypes = blist;
	//        tlist.add((int) rg.userId);
	        rg.travellers = tlist;
	        GetRulesByTravller grb = new GetRulesByTravller();
	        OutputResult<Response_GetRulesByTravller, String> or = grb.ClientRequest(rg, Response_GetRulesByTravller.class);
	        Response_GetRulesByTravller rpg = new Response_GetRulesByTravller();
	        if (or.IsSucceed()) {
	            rpg = or.getResultObj();
	            IntlTicketModel ticketModel = rpg.getData().getIntlTicket();
	            // 整型数字，0-关闭，1-开启
	            if (ticketModel.state.intValue() == 1) {
	                regularMap = new HashMap<String, List<PolicyTicketInfo>>();
	                orgPolicyManage.setOverproofAudit(Long.valueOf(ticketModel.audit));
	                orgPolicyManage.setOverproofDefault(Long.valueOf(ticketModel.policyDefault));
	                orgPolicyManage.setOverproofBook(Long.valueOf(ticketModel.book));
	                orgPolicyManage.setPolicyControl(Long.valueOf(ticketModel.state));
	                orgPolicyManage.setOverproofShow(Long.valueOf(ticketModel.show));
	
	                List<RuleMaps> ruleMaps = ticketModel.ruleMaps;
	                for (int i = 0; ruleMaps!=null && i < ruleMaps.size(); i++) {
	                    List<PolicyTicketInfo> policyInfoList = new ArrayList<PolicyTicketInfo>();
	                    RuleMaps ruleMap = ruleMaps.get(i);
	                    List<TicketRules> rules = ruleMap.rules;
	                    for (int j = 0; j < rules.size(); j++) {
	                        TicketRules rule = rules.get(i);
	                        PolicyTicketInfo ticketInfo = new PolicyTicketInfo();
	                        List<RuleInfo> ruleList = new ArrayList<RuleInfo>();
	                        ticketInfo.setPolicyType(rule.type);
	                        ticketInfo.setOverproofshow(rule.show);
	                        ticketInfo.setOverproofbook(rule.book);
	                        ticketInfo.setOverproofaudit(rule.audit);
	                        List<TicketItems> items = rule.items;
	                        for (int k = 0; k < items.size(); k++) {
	                            RuleInfo info = new RuleInfo();
	                            RuleId ruleId = new RuleId();
	                            ruleId.setFlowid(items.get(i).flowId);
	                            info.setId(ruleId);
	                            info.setRules(items.get(i).rule);
	                            info.setLowerLimit(items.get(i).lowerLimit);
	                            info.setRuletype(items.get(i).type);
	                            info.setUpperLimit(items.get(i).upperLimit);
	                            ruleList.add(info);
	                        }
	                        ticketInfo.setRuleList(ruleList);
	                        policyInfoList.add(ticketInfo);
	                    }
	                    regularMap.put(ruleMap.traveller.toString(), policyInfoList);
	                }
	            }
	        }
	
	        if (userIds.size() > 0) {
	            for (int i = 0; i < userIds.size(); i++) {
	                tacuser = (TAcUser) this.comDao.queryForEntity(Long.valueOf(userIds.get(i)), TAcUser.class);
	                if (tacuser != null) {
	                    if (selectorguser.length() > 0) {
	                        selectorguser += ",";
	                    }
	                    selectorguser += tacuser.getIdcard() + "_" + tacuser.getUserid() + "_" + tacuser.getOrgid() + "_"
	                            + selectUserMap.get(tacuser.getUserid().toString()).toString() + "_" + tacuser.getUsername();
	                }
	            }
	        }
	        if(logger.isDebugEnabled())
	        	logger.debug("======================差旅管控接口调用完成，以下为解析赋值。");
	        
	       //过滤差旅管控政策       出行类型，因私出行不受差旅政策限制:1-因公出行，2-因私出行;超标详情	整型数字，0-不需要（默认），1-需要
	        if ("1".equals(passengers.businessType) && StringUtils.isNotEmpty(selectorguser) && orgPolicyManage != null
	                && orgPolicyManage.getPolicyControl().intValue() == isOpenType.开启.getOperateType()) {
	            trips = this.getPartTimeMinPrice_v2(trips, regularMap, orgPolicyManage, selectUserMap,passengers.overproofDetail);
	
	        }

		} catch (Exception e) {
			logger.error("差旅管控查询信息时报错，错误信息："+e.getMessage());
		}
        return trips;
    }

    private String getUserNameByid(Integer uid) {
		String userName = "";
		String hql="from TAcUser  where userid ='"+uid+"'  and status <>1 ";
		List<TAcUser> list=comDao.queryForList(hql);
		if(list.size()>0){
			return list.get(0).getUsername();
		}
		return null;
	}

	/**
     * 差旅管控二期
     * 
     * @param trips
     * @param regularMap
     * @param orgPolicyManage
     * @param selectUserMap
     * @param overproofDetail 超标详情	整型数字，0-不需要（默认），1-需要
     * @return
     */
	public List<Trips> getPartTimeMinPrice_v2(List<Trips> trips,
            Map<String, List<PolicyTicketInfo>> regularMap, OrgPolicyManage orgPolicyManage,
            Map<String, String> selectUserMap, int overproofDetail) {
        // RegularManager regularManagers;

        Long time1 = System.currentTimeMillis();
        if (trips==null || trips.size() == 0) {
            return trips;
        }

        // --------->>>对所有有人的规则 进行分类整理，[前后最低价、提前天数、按金额、按舱位]---国际没有折扣信息，暂时忽略。
        Map<String, List<PolicyTicketInfo>> regularTyesMap = new HashMap<String, List<PolicyTicketInfo>>();
        String regularTypesKey = "POLICY_TYPE_";
        String usernames = "";
        Iterator<String> userIterators = selectUserMap.values().iterator();
        while (userIterators.hasNext()) {
            String usernames_ = userIterators.next();
            usernames += usernames_ + "、";
        }
        usernames = usernames.substring(0, usernames.length() - 1);
        // --------->>>未设置差旅规则
        // --------->>>如果没有匹配到任何规则，就意味着是没有设置差旅规则。
        // */规则，意味着所有规则都包含在内
        //基础规则匹配----begin-----------------
        if (regularMap == null || regularMap.size() == 0) {
            int overproofdefault = orgPolicyManage.getOverproofDefault().intValue();// 未匹配到规则的默认值，1-超标|2-不超标
            String overproofshow = orgPolicyManage.getOverproofShow().toString();// 超标显示规则，10-隐藏|20-正常显示|21-超标显示
            int overproofBook = orgPolicyManage.getOverproofBook().intValue(); // 默认预定规则
                                                                               // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
            if (overproofdefault == Overproofdefault_v2.超标.getValue()) {
                List<Trips> ldtts = new ArrayList<Trips>();
                for (int i = 0; i < trips.size(); i++) {
                    Trips trip = trips.get(i);
                    trip = setOverproof2trip(trip,orgPolicyManage,"未设置差旅规则",usernames);
                    
                    ldtts.add(trip);
                }
                return ldtts;
            } else {
                return trips;    
            }
        } else {
            List<PolicyTicketInfo> lists = null;
            List<PolicyTicketInfo> typelists = null;
            PolicyTicketInfo iteratorPolicyInfo = null;
            Iterator<String> userids = regularMap.keySet().iterator();
            while (userids.hasNext()) {//根据出行人id匹配规则。
                String useridsstr = userids.next();
                lists = regularMap.get(useridsstr);
                for (int i = 0; i < lists.size(); i++) {
                    iteratorPolicyInfo = lists.get(i);
                    if (iteratorPolicyInfo != null) {
                        String keyStr = new StringBuffer().append(regularTypesKey)
                                .append(iteratorPolicyInfo.getPolicyType().intValue()).toString();
                        typelists = regularTyesMap.get(keyStr);
                        if (typelists == null || typelists.size() == 0) {
                            typelists = new ArrayList<PolicyTicketInfo>();
                        }
                        iteratorPolicyInfo.setUserid(useridsstr);
                        iteratorPolicyInfo.setUsername(selectUserMap.get(useridsstr));
                        typelists.add(iteratorPolicyInfo);
                        regularTyesMap.put(keyStr, typelists);
                    }
                }
                if (lists.size() == 0) {
                    int overproofdefault = orgPolicyManage.getOverproofDefault().intValue();// 未匹配到规则的默认值，1-超标|2-不超标
                    String overproofshow = orgPolicyManage.getOverproofShow().toString();// 超标显示规则，10-隐藏|20-正常显示|21-超标显示
                    int overproofBook = orgPolicyManage.getOverproofBook().intValue(); // 默认预定规则
                                                                                       // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
                    if (overproofdefault == Overproofdefault_v2.超标.getValue()) {
                        List<Trips> ldtts = new ArrayList<Trips>();
                        for (int i = 0; i < trips.size(); i++) {
                            Trips trip = trips.get(i);
                            // 同一个企业下的所有员工的默认设置都一样，如果没设置规则，则都初始为"未设置差旅规则"
                            trip = setOverproof2trip(trip,orgPolicyManage,"未设置差旅规则",selectUserMap.get(useridsstr));
                            ldtts.add(trip);
                        }
                        trips = ldtts;
                    }
                }
            }

        }
        //基础规则匹配----end-----------------
        long time4 = System.currentTimeMillis();
        if(logger.isDebugEnabled())
        	logger.debug("---------未设置差旅过滤------->>" + (time4 - time1));
        
        // --------->>> 提前预定天数判断,如果判断超标组合信息
        Date tripdate = trips.get(0).date;//日期，年月日 :出发日期 
        Long oo = DateUtil.getDiffDay(DateUtil.date2Str(new Date(), "yyyy-MM-dd"),
                DateUtil.date2Str(tripdate, "yyyy-MM-dd"), "yyyy-MM-dd");
        List<PolicyTicketInfo> preBookDaysList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.提前预定天数.getValue()).toString());
        if (preBookDaysList != null && preBookDaysList.size() > 0) {
        	for (int j = 0; j < trips.size(); j++) {
        		Trips trip = trips.get(j);
	            for (int i = 0; i < preBookDaysList.size(); i++) {
	                PolicyTicketInfo info = preBookDaysList.get(i);
	                RuleInfo ruleinfo = info.getRuleList().get(0);
	                Double preBookDay = ruleinfo.getUpperLimit();// 存储了提前预定天数
	                if (oo.intValue() < preBookDay.intValue()) {
	                    //保存超标信息
	                	mikeData4Proof(trip.overproof,info,overproofDetail);
                    }
                }
            }
        }
        long time5 = System.currentTimeMillis();
        if(logger.isDebugEnabled())
        	logger.info("---------提前天数--------->>" + (time5 - time4));

        // --------->>>处理前后多少小时最低价过滤
        List<PolicyTicketInfo> lowerpriceList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.最低价.getValue()).toString());
        Map<String, List<Trips>> allTripsMap = new HashMap<String, List<Trips>>();
        // 排序，按照航班号分组
        for (int i = 0; i < trips.size(); i++) {
            Trips trip = trips.get(i);
//            String airline = trip.getAirline().split("\\$")[0];
            String airline = trip.segments.get(0).flights.get(0).flightNo;//航班号
            List<Trips> tripList = allTripsMap.get(airline);
            if (tripList == null) {
                tripList = new ArrayList<Trips>();
                tripList.add(trip);
            } else {
                tripList.add(trip);
            }
            allTripsMap.put(airline, tripList);
        }
        // 获取航班最低价格的数据集合
        List<Trips> tripList_minprices = new ArrayList<Trips>();
        List<Trips> tripList_new = new ArrayList<Trips>();
        // 排序，按照给定的方式 排序
        Iterator<String> iteratorKey = allTripsMap.keySet().iterator();
        while (iteratorKey.hasNext()) {
            String key_ = iteratorKey.next();
            List<Trips> tripList = allTripsMap.get(key_);
            tripList = sortCase(0, tripList); // 按照价格升序
            allTripsMap.put(key_, tripList);
            tripList_minprices.add(tripList.get(0));
        }

        double rightTime = 0d;
        double leftTime = 0d;
        if (lowerpriceList != null && lowerpriceList.size() > 0) {
            for (Trips disobj : trips) {
                for (int i = 0; i < lowerpriceList.size(); i++) {
                    PolicyTicketInfo info = lowerpriceList.get(i);
                    RuleInfo ruleinfo = info.getRuleList().get(0);
                    leftTime = ruleinfo.getLowerLimit();
                    rightTime = ruleinfo.getUpperLimit();// 存储了提前预定天数
                    // 前几个小时 后几个小时区间，是否为最低价判断
                    double res = disobj.totalBasePrice;
                    Date disobjdate = DateUtil.str2Date(DateUtil.date2Str(disobj.date, "yyyy-MM-dd") + " "
                            + disobj.segments.get(0).flights.get(0).depAirPort.depDatetime, "yyyy-MM-dd HH:mm");
                    for (Trips dis : tripList_minprices) {
                        Date disdate = DateUtil.str2Date(DateUtil.date2Str(dis.date, "yyyy-MM-dd") + " "
                                + dis.segments.get(0).flights.get(0).depAirPort.depDatetime, "yyyy-MM-dd HH:mm");
                        long diffMs = DateUtil.getDiffMs(disobjdate, disdate) / 60000;// 内全循环对象起飞时间-外循环对象起飞时间
                        if ((diffMs < 0 && diffMs * -1 <= leftTime * 60) || (diffMs > 0 && diffMs <= rightTime * 60)
                                || diffMs == 0) {
                            if (dis.totalBasePrice.doubleValue() < disobj.totalBasePrice.doubleValue()) {
                                res = dis.totalBasePrice;
                                break;
                            }
                        }
                    }
                    if (res < disobj.totalBasePrice) {// 非最低价
                    	//保存超标信息
	                	mikeData4Proof(disobj.overproof,info,overproofDetail);
                    }
                }
                tripList_new.add(disobj);
            }
            trips = tripList_new;
        }
        long time6 = System.currentTimeMillis();
        if(logger.isDebugEnabled())
        	logger.info("-------最低价--------->>" + (time6 - time5));

        tripList_new = null;
        tripList_minprices = null;
        tripList_minprices = new ArrayList<Trips>();
       
        // --------->>>处理金额 规则的过滤
        List<PolicyTicketInfo> moneyPolicyList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.按金额.getValue()).toString());
        if ((moneyPolicyList != null && moneyPolicyList.size() > 0)) {
            for (int i = 0; i < trips.size(); i++) {
                Trips trip = trips.get(i);
                // 进行其他判断,判断金额
                for (int j = 0; moneyPolicyList != null && j < moneyPolicyList.size(); j++) {
                    PolicyTicketInfo info = moneyPolicyList.get(j);
                    RuleInfo ruleinfo = info.getRuleList().get(0);
                    Double ruleLeft = ruleinfo.getLowerLimit();
                    Double ruleRight = ruleinfo.getUpperLimit();
                    //保存超标信息
                	mikeData4Proof(trip.overproof,info,overproofDetail);
                }
                tripList_minprices.add(trip);
            }
            // 直接放list
            trips = tripList_minprices;
        }
        long time7 = System.currentTimeMillis();
        if(logger.isDebugEnabled())
        	logger.info("-------金额 -------->>" + (time7 - time6));
        
        
        tripList_minprices = new ArrayList<Trips>();
        List<PolicyTicketInfo> cangweiPolicyList = regularTyesMap.get(new StringBuffer().append(regularTypesKey)
                .append(PolicyType_v2.按舱位.getValue()).toString());
        if (cangweiPolicyList != null && cangweiPolicyList.size() > 0) {
            Map<String, List<PolicyTicketInfo>> regularTyesCangweiMap = new HashMap<String, List<PolicyTicketInfo>>();
            for (int i = 0; i < cangweiPolicyList.size(); i++) {
                PolicyTicketInfo cangweiRuleInfo = cangweiPolicyList.get(i);
                List<PolicyTicketInfo> iffos = regularTyesCangweiMap.get(cangweiRuleInfo.getUsername());
                if (iffos == null) {
                    iffos = new ArrayList<PolicyTicketInfo>();
                }
                iffos.add(cangweiRuleInfo);
                regularTyesCangweiMap.put(cangweiRuleInfo.getUsername(), iffos);
            }
            Iterator<String> usernamestr = regularTyesCangweiMap.keySet().iterator();
            long time71 = System.currentTimeMillis();
            if(logger.isDebugEnabled())
            	logger.info("----------------------舱位1----->>" + (time71 - time7));
            while (usernamestr.hasNext()) {
                String usernamestr_ = usernamestr.next();
                List<PolicyTicketInfo> iffosList = regularTyesCangweiMap.get(usernamestr_);
                iffosList = this.compareByDate(iffosList, "desc");
                long time72 = System.currentTimeMillis();
                if(logger.isDebugEnabled())
                	logger.info("-------------------------------time72-time71-------->>" + (time72 - time71));
                for (int i = 0; i < trips.size(); i++) {

                    Trips trip = trips.get(i);
                    List<BerthPolicyInfo> berthPolicyInfo = this.getBerthPolicyInfo(trip);
                    String username = usernamestr_;

                    long time82 = System.currentTimeMillis();
                    
                    PolicyTicketInfo ticketInfo = this.getNewRule(iffosList, berthPolicyInfo);
                    long time83 = System.currentTimeMillis();
                    //保存超标信息
                	mikeData4Proof(trip.overproof,ticketInfo,overproofDetail);
                    
                }
                long time73 = System.currentTimeMillis();
                if(logger.isDebugEnabled())
                	logger.info("-------------------------------------73-72-->>" + (time73 - time72));
                trips = tripList_minprices;
                tripList_minprices = new ArrayList<Trips>();
            }
            tripList_minprices = trips;
        } else {
            tripList_minprices = trips;
        }

        long time8 = System.currentTimeMillis();
        if(logger.isDebugEnabled())
        	logger.info("-------舱位--------->>" + (time8 - time7));



        Long time2 = System.currentTimeMillis();
        logger.info(DateUtil.date2Str(new Date(), "yyyy-MM-dd HH:mm:ss"), "  差旅规则过滤时间:" + (time2 - time1));
        
        return tripList_minprices;
    }
	/**
	 * 
	 * @param over 超标model
	 * @param info 接口返回超标信息
	 * @param overproofDetail 超标详情	整型数字，0-不需要（默认），1-需要
	 * @return PolicyType_v2
	 */
    private boolean mikeData4Proof(Overproof over, PolicyTicketInfo info,
			int overproofDetail) {
    	boolean flag = false;
    	try {
    		if(null==over){
    			over = new Overproof();
    			over.rule = new Rule();
    		}
    		if(info.getPolicyType()>99){//1-按金额|2-按折扣|3-最低价|4-提前预定天数|101-按舱位|111-按舱位等级 //TODO 详细规则匹配是否正确待查证。
    			over.memo += info.getUsername()+RuleitemsType_String.getName(info.getRuleList().get(0).getRuletype())+",";
    		}else{
    			over.memo += info.getUsername()+PolicyType_String.getName(info.getPolicyType())+",";
    		}
    		
    		over.key += info.getUsername()+info.getUserid()+PolicyType_String.getName(info.getPolicyType())+",";
    		//获得最严格标准
    		over.rule = getMaxLimit(over.rule,info);
    		//详细超标规则设置
    		if(1==overproofDetail){
    			if(null==over.info){
    				over.info = new ArrayList<Info>();
    			}
    			Info i = new Info();
    			i.type = info.getPolicyType();
    			i.memo = PolicyType_String.getName(info.getPolicyType());
    			
    			Staffs s = new Staffs();
    			s.id = "".equals(info.getUserid())?-1:Integer.valueOf(info.getUserid());
    			s.name = info.getUsername();
    			s.rule = new Rule();
    			s.rule.book = info.getOverproofbook();
    			s.rule.show = info.getOverproofshow();
    			s.rule.audit = info.getOverproofaudit();
    			i.staffs.add(s);
    			
    			over.info.add(i);
    		}
    		
    		
    		flag = true;
		} catch (Exception e) {
			logger.error("赋值超标信息报错，报错位置20180305li，报错信息："+e.getMessage());
		}
    	
    	
		return flag;
	}

    /**
     * 比较获取最严格超标规则
     * @param rule
     * @param info
     * @return
     */
	private Rule getMaxLimit(Rule rule, PolicyTicketInfo info) {
		if(null==rule){
			rule = new Rule();
		}
		//超标显示级别由高到低：0-不显示，2-超标显示，1-正常显示
		if(null==rule.show || 1==rule.show){//没有超标显示规则或者超标显示规则为最不严格的1-正常显示
			rule.show = info.getOverproofshow();
		}else if(2==rule.show && 0==info.getOverproofshow()){//当前超标=最严格超标
			rule.show = info.getOverproofshow();
		}
		//超标预订级别由高到低：0-不允许预订，2-选择理由允许预订，1-提示超标允许预订
		if(null==rule.book || 1==rule.book){//没有超标预订规则或者超标预订规则为最不严格的1-正常显示
			rule.book = info.getOverproofbook();
		}else if(2==rule.book && 0==info.getOverproofbook()){//当前超标=最严格超标
			rule.book = info.getOverproofbook();
		}
		//超标审批级别由高到低：2-需要超标审批，1-需要正常审批，0-无需审批
		if(null==rule.audit || 0==rule.audit){//没有超标审批规则或者超标审批规则为最不严格的1-正常显示
			rule.audit = info.getOverproofaudit();
		}else if(1==rule.audit && 2==info.getOverproofaudit()){//当前超标=最严格超标
			rule.audit = info.getOverproofaudit();
		}
		return rule;
	}

	/**
     * 设置超标基础数据
     * @param trip
     * @param orgPolicyManage
     * @param string
     * @param usernames 
     * @return
     */
    private Trips setOverproof2trip(Trips trip,
			OrgPolicyManage orgPolicyManage, String str, String usernames) {
    	trip.overproof.memo = usernames+str;
        Rule ruleModel = new Rule();
        trip.overproof.key = ruleModel.show+"_"+ruleModel.book+"_"+ruleModel.audit;
        ruleModel.show = toSwitchShow(orgPolicyManage.getOverproofShow());//超标显示规则	整型数字，0-不显示，1-正常显示，2-超标显示
        ruleModel.book = orgPolicyManage.getOverproofBook().intValue();
        ruleModel.audit = orgPolicyManage.getOverproofAudit().intValue();
        trip.overproof.rule = ruleModel;
		return trip;
	}

	/**
     * 转换数字标识，与文档一致。超标显示规则	整型数字，10-隐藏|20-正常显示|21-超标显示
     */
    private Integer toSwitchShow(Long overproofshow) {
    	Integer show  = 0;
    	switch (overproofshow.intValue()) {
		case 10:
			show = 0;
			break;
		case 20:
			show = 1;
			break;
		case 21:
			show = 2;
			break;
		default:
			show = 0;
		}
		return show;
	}


    /**
     * 舱位过滤使用转换的方法
     * 
     * @param trip
     * @return
     */
    protected List<BerthPolicyInfo> getBerthPolicyInfo(Trips trip) {
    	List<BerthPolicyInfo> listBerth = new ArrayList<BerthPolicyInfo>();
        BerthPolicyInfo berthPolicyInfo = null;
//        berthPolicyInfo.setAirCompany(trip.getAirline().split("\\$")[0].substring(0, 2));
        for(int i=0;i<trip.segments.size();i++){
        	for(int j=0;j<trip.segments.get(i).flights.size();j++){
        		berthPolicyInfo = new BerthPolicyInfo();
        		berthPolicyInfo.setBerth(trip.segments.get(i).cabin.code.split("/")[j]);
        		berthPolicyInfo.setAirCompany(trip.segments.get(i).flights.get(j).carrier);//航司二字码
        		berthPolicyInfo.setFlightnum(trip.segments.get(i).flights.get(j).flightNo);//航班号
        		berthPolicyInfo.setAirline(trip.mapKey.split("|")[0].split("_")[0]+(
        				trip.mapKey.split("|").length>1?trip.mapKey.split("|")[0].split("_")[1]:""));
        		listBerth.add(berthPolicyInfo);
        	}
        }

        return listBerth;
    }

    public String GetPortNameByCode(String code) {
        HashMap<String, String> hashMap = (HashMap<String, String>) RedisCacheManager.get(
                Const.APP_MAP_3CHAR_AIRPORTNAME, Map.class);
        Iterator<String> keys = hashMap.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key.equals(code)) {
                return hashMap.get(key);
            }
        }
        return code;
    }

    // -------------------------- 规则排序 相关代码-----------
    /**
     * 仓位规则排序
     * 
     * @param cangweiPolicyList
     *            规则list by（desc：降序 asc：升序）
     */
    public List<PolicyTicketInfo> compareByDate(List<PolicyTicketInfo> cangweiPolicyList, String by) {
        String[] sortNameArr = { "orgruletype", "updatetime" };
        // 按orgruletype降序、updatetime降序
        boolean flag = true;
        if ("desc".equals(by)) {
            flag = false; // 降序
        }
        boolean[] isAscArr = { flag, flag };
        sort(cangweiPolicyList, sortNameArr, isAscArr);
        return cangweiPolicyList;
    }

    /**
     * 给list的每个属性都指定是升序还是降序
     * 
     * @param list
     * @param sortnameArr
     *            参数数组
     * @param typeArr
     *            每个属性对应的升降序数组， true升序，false降序
     */
    public static <E> void sort(List<E> list, final String[] sortnameArr, final boolean[] typeArr) {
        if (sortnameArr.length != typeArr.length) {
            throw new RuntimeException("属性数组元素个数和升降序数组元素个数不相等");
        }
        Collections.sort(list, new Comparator<E>() {
            public int compare(E a, E b) {
                int ret = 0;
                try {
                    for (int i = 0; i < sortnameArr.length; i++) {
                        ret = compareObject(sortnameArr[i], typeArr[i], a, b);
                        if (0 != ret) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });
    }

    /**
     * 对2个对象按照指定属性名称进行排序
     * 
     * @param sortname
     *            属性名称
     * @param isAsc
     *            true升序，false降序
     * @param a
     * @param b
     * @return
     * @throws Exception
     */
    private static <E> int compareObject(final String sortname, final boolean isAsc, E a, E b) throws Exception {
        int ret;
        Object value1 = forceGetFieldValue(a, sortname);
        Object value2 = forceGetFieldValue(b, sortname);
        String str1 = value1.toString();
        String str2 = value2.toString();
        if (value1 instanceof Number && value2 instanceof Number) {
            int maxlen = Math.max(str1.length(), str2.length());
            str1 = addZero2Str((Number) value1, maxlen);
            str2 = addZero2Str((Number) value2, maxlen);
        } else if (value1 instanceof Date && value2 instanceof Date) {
            long time1 = ((Date) value1).getTime();
            long time2 = ((Date) value2).getTime();
            int maxlen = Long.toString(Math.max(time1, time2)).length();
            str1 = addZero2Str(time1, maxlen);
            str2 = addZero2Str(time2, maxlen);
        }
        if (isAsc) {
            ret = str1.compareTo(str2);
        } else {
            ret = str2.compareTo(str1);
        }
        return ret;
    }

    /**
     * 获取指定对象的指定属性值（去除private,protected的限制）
     * 
     * @param obj
     *            属性名称所在的对象
     * @param fieldName
     *            属性名称
     * @return
     * @throws Exception
     */
    public static Object forceGetFieldValue(Object obj, String fieldName) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        Object object = null;
        boolean accessible = field.isAccessible();
        if (!accessible) {
            // 如果是private,protected修饰的属性，需要修改为可以访问的
            field.setAccessible(true);
            object = field.get(obj);
            // 还原private,protected属性的访问性质
            field.setAccessible(accessible);
            return object;
        }
        object = field.get(obj);
        return object;
    }

    /**
     * 给数字对象按照指定长度在左侧补0.
     * 
     * 使用案例: addZero2Str(11,4) 返回 "0011", addZero2Str(-18,6)返回 "-000018"
     * 
     * @param numObj
     *            数字对象
     * @param length
     *            指定的长度
     * @return
     */
    public static String addZero2Str(Number numObj, int length) {
        NumberFormat nf = NumberFormat.getInstance();
        // 设置是否使用分组
        nf.setGroupingUsed(false);
        // 设置最大整数位数
        nf.setMaximumIntegerDigits(length);
        // 设置最小整数位数
        nf.setMinimumIntegerDigits(length);
        return nf.format(numObj);
    }

    // --------------
    /**
     * 按照指定方式排序
     * 
     * @param by
     * @param tripList
     * @return
     */
    protected List<Trips> sortCase(int by, List<Trips> tripList) {
        Comparator<Object> comparator = null;
        switch (by) {
        case 0: // 0-按价格升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    Trips a2 = (Trips) a;
                    Trips b2 = (Trips) b;
                    double one = a2.totalBasePrice.doubleValue();
                    double two = b2.totalBasePrice.doubleValue();
                    // if(a2.getCangwei().equals(b2.getCangwei())){
                    // return 0;
                    // } else {
                    if (one >= two) {
                        return 1;
                    } else {
                        return -1;
                    }
                    // }

                }
            };
            break;
        case 1: // 1-按价格降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    Trips a2 = (Trips) a;
                    Trips b2 = (Trips) b;
                    double one = a2.totalBasePrice.doubleValue();
                    double two = b2.totalBasePrice.doubleValue();
                    // if (a2.getCangwei().equals(b2.getCangwei())) {
                    // return 0;
                    // } else {
                    if (one < two) {
                        return 1;
                    } else {
                        return -1;
                    }
                    // }
                }
            };
        case 2: // 2-按时间升序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    Trips a2 = (Trips) a;
                    Trips b2 = (Trips) b;
                    Date one = a2.date;
                    Date two = b2.date;
                    if (one.compareTo(two) < 0) {
                        return -1;
                    } else if (one.compareTo(two) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            };
            break;
        case 3: // 3-按时间降序
            comparator = new Comparator<Object>() {
                public int compare(Object a, Object b) {
                    Trips a2 = (Trips) a;
                    Trips b2 = (Trips) b;
                    Date one = a2.date;
                    Date two = b2.date;
                    if (one.compareTo(two) > 0) {
                        return -1;
                    } else if (one.compareTo(two) == 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            };
            break;
        }
        Collections.sort(tripList, comparator);
        return tripList;
    }
    /**
     * 匹配符合航班相关规则的超标规则。
     * 
     */
    public PolicyTicketInfo getNewRule(List<PolicyTicketInfo> cangweiPolicyList, List<BerthPolicyInfo> berthPolicyInfo) {
    	PolicyTicketInfo ruleInfo = null;
    	
    	for(int b=0;b<berthPolicyInfo.size();b++){
			for (int j = 0; j < cangweiPolicyList.size(); j++) {
				PolicyTicketInfo tPolicyTicket = cangweiPolicyList.get(j);
				List<RuleInfo> ruleinfoList = tPolicyTicket.getRuleList();
				String airlineRules = "*";// 航线规则
		        String companyRules = "*";// 航司规则
		        String flightRules = "*";// 航班规则
		        String cabinrules = "*";// 舱位规则
				for (int i = 0; i < ruleinfoList.size(); i++) {
					RuleInfo info = ruleinfoList.get(i);
					if (info.getRuletype().intValue() == 20) {
						companyRules = "," + info.getRules() + ",";// 航司规则
						if (companyRules.contains("*/")) {
							String[] companyRules_ = companyRules.split("\\*/");
							companyRules = companyRules_[0] + "*/," + companyRules_[1];
						}
						if ("*".equals(info.getRules())) {
							companyRules = info.getRules();
						}
					} else if (info.getRuletype().intValue() == 40) {
						cabinrules = "," + info.getRules() + ",";// 舱位规则
						if (cabinrules.contains("*/")) {
							String[] cabinrules_ = cabinrules.split("\\*/");
							cabinrules = cabinrules_[0] + "*/," + cabinrules_[1];
						}
						if ("*".equals(info.getRules())) {
							cabinrules = info.getRules();
						}
					}
				}
				 String berth2 = "," + berthPolicyInfo.get(b).getBerth() + ",";
			     String airCompany = "," + berthPolicyInfo.get(b).getAirCompany() + ",";
			        if (!"*".equals(airlineRules)
			                && ((airlineRules.indexOf("*/") >= 0 && airlineRules.indexOf(berthPolicyInfo.get(b).getAirline()) >= 0) || (airlineRules
			                        .indexOf("*/") < 0 && airlineRules.indexOf(berthPolicyInfo.get(b).getAirline()) < 0))) {// 航线判断
			        	ruleInfo = tPolicyTicket;
						break;
			        } else {
			            if (!"*".equals(companyRules)
			                    && ((companyRules.indexOf("*/") >= 0 && companyRules.indexOf(airCompany) >= 0) || (companyRules
			                            .indexOf("*/") < 0 && companyRules.indexOf(airCompany) < 0))) {// 航司判断
			            	ruleInfo = tPolicyTicket;
							break;
			            } else {
			                if (!"*".equals(flightRules)
			                        && ((flightRules.indexOf("*/") >= 0 && flightRules.indexOf(berthPolicyInfo.get(b).getFlightnum()) >= 0) || (flightRules
			                                .indexOf("*/") < 0 && flightRules.indexOf(berthPolicyInfo.get(b).getFlightnum()) < 0))) {// 航班判断
			                	ruleInfo = tPolicyTicket;
								break;
			                } else {
			                    if (!"*".equals(cabinrules)
			                            && ((cabinrules.indexOf("*/") >= 0 && cabinrules.indexOf(berth2) >= 0) || (cabinrules
			                                    .indexOf("*/") < 0 && cabinrules.indexOf(berth2) < 0))) {// 舱位判断
			                    	ruleInfo = tPolicyTicket;
									break;
			                    } else {
			                       
			                    }
			                }
			            }
			        }
				
			}

    	}
    	return ruleInfo;
    }
}
