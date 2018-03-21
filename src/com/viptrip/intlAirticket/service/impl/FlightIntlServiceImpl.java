package com.viptrip.intlAirticket.service.impl;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.base.cache.CacheHelper;
import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.intlAirticket.common.Constants;
import com.viptrip.intlAirticket.dao.IntlAirTicketComDao;
import com.viptrip.intlAirticket.model.Request_GetIntlFlightList;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.model.flightModels.IntlFlightListQue;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.util.DateUtil;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TCOMPANYGROUP;
import com.viptrip.yeego.yeego;
import com.viptrip.yeego.intlairticket.model.AirRulesRQ;
import com.viptrip.yeego.intlairticket.model.Filter_QueryWebFlightsIntl_1_0;
import com.viptrip.yeego.intlairticket.model.Flight;
import com.viptrip.yeego.intlairticket.model.Request_GetAirRulesI_1_0;
import com.viptrip.yeego.intlairticket.model.Request_GetFlightPriceI_1_0;
import com.viptrip.yeego.intlairticket.model.Request_QueryWebFlightsI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_GetAirRulesI_1_0;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.Config;
import etuf.v1_0.model.base.output.OutputResult;
@Service
public class FlightIntlServiceImpl implements FlightIntlService{
	@Resource
	private IntlAirTicketComDao dao;
	private static Logger logger = LoggerFactory.getLogger(FlightIntlServiceImpl.class);
	
	
	/**
	 * @功能：加载登录用户信息
	 * @return
	 */
	public TAcUser getTAcUser(long userid) {

		TAcUser tAcUser = (TAcUser) dao.queryForEntity(userid, TAcUser.class);

		return tAcUser;

	}
	/**
	 * @功能：加载总公司机构信息
	 * @param orgId
	 * @return
	 */
	public TAcOrg getOrgids(Long orgId){
		
		TAcOrg org = null;
		org = (TAcOrg) dao.queryForEntity(orgId, TAcOrg.class);//子公司
		org = (TAcOrg) dao.queryForEntity(Long.parseLong(org.getCompany()), TAcOrg.class);//总公司

		return org==null?null:org;
	}
	
	public OutputResult<Response_QueryWebFlightsI_1_0, String> queryIntlFlight(
			Request_GetIntlFlightList req) {
		OutputResult<Response_QueryWebFlightsI_1_0, String> or = 
				new OutputResult<Response_QueryWebFlightsI_1_0, String>();
		try {
			Request_QueryWebFlightsI_1_0 qryFlight = new Request_QueryWebFlightsI_1_0();
			// A :所有航班(包括中转) 默 认 D :直达;此处默认全部
			String stopType = "A";
			// 是否仅使用机场代码查询 Y:表示请求内输入的三字 码含义为机场 N:表示请求内输入的三字 码为城市 不填默认为Y
			// 国际查询使用城市三字码查询，一个城市多个机场的要返回多个机场的航班信息
			String airportOnly = "N";
			qryFlight.setOrgcity(req.data.orgCity);
			qryFlight.setDetcity(req.data.detCity);
			qryFlight.setStarttime(req.data.startTime);
			qryFlight.setStopType(stopType);
			qryFlight.setTravelType(CommonMethodUtils.travelTypeToStr(req.data.travelType));
			if (req.data.travelType==MyEnum.TripTypeInt.单程.getValue()) {
				qryFlight.setArrvitime("");
			} else {
				qryFlight.setArrvitime(req.data.arrviTime);
			}
			qryFlight.setPsgCode(CommonMethodUtils.passengerTypeStr(req.data.passengerType));
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

		// qryFlight_.setCodes(qryFlight.codes);
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
	public String getQueryIntlFlightKey(String orgCityCode,
			String detCityCode, String psgType, String travelType,
			String goDatetime, String backDatetime,String ticketGroup) {
		Request_GetIntlFlightList req = new Request_GetIntlFlightList();
		req.data = new IntlFlightListQue();
		req.data.orgCity = orgCityCode;
		req.data.detCity = detCityCode;
		req.data.passengerType = CommonMethodUtils.passengerTypeStr(psgType);
		req.data.travelType = CommonMethodUtils.travelTypeToStr(travelType);
		req.data.startTime = goDatetime;
		req.data.arrviTime = backDatetime;
		req.data.ticketGroup = ticketGroup;
		return getQueryIntlFlightKey(req);
	}
	public String getQueryIntlFlightKey(Request_GetIntlFlightList req,String ticketGroup) {
		req.data.ticketGroup = ticketGroup;
		return getQueryIntlFlightKey(req);
	}

	/*
	 * 获取查询国际航班的key，格式为：PEK_HKG_ADT_RT_2017-02-20_2017-02-25
	 */
	public String getQueryIntlFlightKey(Request_GetIntlFlightList req) {
		StringBuffer keySB = new StringBuffer();
		keySB.append(req.data.orgCity.trim()).append("_");
		keySB.append(req.data.detCity.trim()).append("_");
		keySB.append(CommonMethodUtils.passengerTypeStr(req.data.passengerType)).append("_");
		keySB.append(CommonMethodUtils.travelTypeToStr(req.data.travelType) + "_");
		keySB.append(req.data.startTime.trim());
		if (req.data.travelType==MyEnum.TripTypeInt.往返.getValue()) {
			keySB.append("_" + req.data.arrviTime.trim());
		}
		if (req.data.ticketGroup!=null&&!"".equals(req.data.ticketGroup)) {
			keySB.append("_" + req.data.ticketGroup.trim());
		}
		return keySB.toString();
	}
	
	

	/////********返程航班列表查询----begin*********////////////
	public OutputResult<FlightResult,String> getFlightResult(Long userId,String mapKey,
			IntlAitTicketGetTargetFType type,String cangwei) {
		OutputResult<FlightResult,String> or=new OutputResult<FlightResult,String>();
		FlightResult result = new FlightResult();
		logger.info("返程航班信息mapKey-->>====="+mapKey);
		
		boolean isNeedNextStep=true;
		if((type== IntlAitTicketGetTargetFType.待确认类型查退改签 || type== IntlAitTicketGetTargetFType.待确认类型下单)  
				&& (cangwei == null || "".equals(cangwei))){
			isNeedNextStep=false;
			or.code=Constant.Code_Failed;
			or.result="查询退改签规则、提交订单，必须提供舱位信息。";
		}
		if(isNeedNextStep){
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
				Request_GetIntlFlightList req = getParam_RequestIntlFlightList(userId, key4GetFlight);
				OutputResult<Response_QueryWebFlightsI_1_0,String> tempOR=queryIntlFlight(req);
				if(tempOR.code==Constant.Code_Succeed){
					flightList=tempOR.getResultObj();
				}else{
					or.code=tempOR.code;
					or.result=tempOR.result;
				}
			}
			if (flightList != null) {
				if(type.getOperateType()<0){
					boolean isRT=CommonMethodUtils.isRTTravleType(key4GetFlight);
					if(type==IntlAitTicketGetTargetFType.待确认类型查详情){
						type = isRT ? IntlAitTicketGetTargetFType.往返查详情
								: IntlAitTicketGetTargetFType.单程查详情;
					}else if(type==IntlAitTicketGetTargetFType.待确认类型查更多舱位){
						type = isRT ? IntlAitTicketGetTargetFType.往返查更多舱位
								: IntlAitTicketGetTargetFType.单程查更多舱位;
					}else if(type==IntlAitTicketGetTargetFType.待确认类型查退改签){
						type = isRT ? IntlAitTicketGetTargetFType.往返查退改签
								: IntlAitTicketGetTargetFType.单程查退改签;
					}else if(type==IntlAitTicketGetTargetFType.待确认类型下单){
						type = isRT ? IntlAitTicketGetTargetFType.往返下单
								: IntlAitTicketGetTargetFType.单程下单;
					}
				}
				targetFs = GetTargetFs(splits[1], flightList, type);// 目标航班信息
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
	
	/*
	 * 获取查询国际航班列表的请求参数
	 */
	public Request_GetIntlFlightList getParam_RequestIntlFlightList(
			Long userId, String key4GetFlight) {
		Request_GetIntlFlightList req;
		String[] filter = key4GetFlight.split("_");
		req = new Request_GetIntlFlightList();
		req.userId = userId;
		req.data = new IntlFlightListQue();
		req.data.orgCity = filter[0];
		req.data.detCity = filter[1];
		req.data.passengerType = CommonMethodUtils.passengerTypeStr(filter[2].toString());
		req.data.travelType = CommonMethodUtils.travelTypeToStr(filter[3].toString());
		req.data.startTime = filter[4];
		req.data.arrviTime = filter.length > 6 && filter[5].length()>1? filter[5] : "";
		req.data.ticketGroup = filter[filter.length-1].length()< 2 ? filter[filter.length-1] :"";
		return req;
	}

	/*
	 * 根据航班key获取价格和舱位
	 */
	public CabinAndPrice getFlightCabinAndPrice(
			Response_QueryWebFlightsI_1_0 resq, String fKey,
			String passengerType) {
		// F结点下的F1 对应 H 结点下的F1
		CabinAndPrice f = new CabinAndPrice();
		Hashtable<String, Hashtable<String, Object[]>> ht = resq.flgithPrices.get(fKey);
		for (Entry<String, Hashtable<String, Object[]>> et : ht.entrySet()) {
			Object[] objs = (Object[]) et.getValue().get(passengerType);//TODO passengerType

			f.setTotalPrice(Double.valueOf(objs[0].toString()));
			f.setTotalPriceWithTax(Double.valueOf(( et.getValue().get("TotalFare"))[0].toString()));
			f.setCangwei(objs[4].toString());
			f.setCangweiType(objs[5].toString());
			break;
		}
		return f;
	}
	 /*
     * 根据航班key和舱位获取价格
     */
    public CabinAndPrice getFlightCabinAndPrice(
            Response_QueryWebFlightsI_1_0 resq, String fKey,
            String passengerType,String cangwei) {
        // F结点下的F1 对应 H 结点下的F1
        CabinAndPrice f = new CabinAndPrice();
        Hashtable<String, Hashtable<String, Object[]>> ht = resq.flgithPrices.get(fKey);
        for (Entry<String, Hashtable<String, Object[]>> et : ht.entrySet()) {
            Object[] objs = (Object[]) et.getValue().get(passengerType);//TODO passengerType
            if(StringUtil.isNotEmpty(objs[4].toString()) && objs[4].toString().equals(cangwei)){
                f.setTotalPrice(Double.valueOf(objs[0].toString()));
                f.setTotalPriceWithTax(Double.valueOf(( et.getValue().get("TotalFare"))[0].toString()));
                f.setCangwei(objs[4].toString());
                f.setCangweiType(objs[5].toString());
                break;
            }
        }
        return f;
    }
	/**
	 * 根据mapkey获取对应的航班信息 调用场景：单程查详情；往返查返程
	 * 
	 * @param flightNos
	 *            航班序列号，格式如：CA1234_MU520_FM1122*CA1235_MU521_FM1123
	 * @param flightList
	 *            航班列表，即F结点列表
	 * @param isNeedReturnTrip
	 *            是否需要回程信息：往返查详情的时候，需要回程信息，传true，其他情况不需要
	 * @return
	 */
	private Set<Entry<String, Hashtable<String, Object[]>>> GetTargetFs(
			String flightNos,
			Response_QueryWebFlightsI_1_0 flightList,
			IntlAitTicketGetTargetFType type) {
		Set<Entry<String, Hashtable<String, Object[]>>> targetFs = new HashSet<Entry<String, Hashtable<String, Object[]>>>();
		// CA1234_MU520_FM1122*CA1235_MU521_FM1123
		for (Entry<String, Hashtable<String, Object[]>> entry : flightList.flights
				.entrySet()) {
			Object[] go = entry.getValue().get("S1");
			Object[] flights = ((ArrayList) go[1]).toArray();
			Object[] firstFlight = ((ArrayList) flights[0]).toArray();
			if (flightNos.equals(CommonMethodUtils.getZhongZhuanFlightNos(
							entry.getValue(),
							type == IntlAitTicketGetTargetFType.往返查详情 ||
							type == IntlAitTicketGetTargetFType.往返查更多舱位 ||
							type == IntlAitTicketGetTargetFType.往返查退改签 ||
							type == IntlAitTicketGetTargetFType.往返下单))) {
				targetFs.add(entry);
				if (type != IntlAitTicketGetTargetFType.往返查返程) {
					break;
				}
			}
		}

		return targetFs;
	}
	public int getSeatsLeftByCabin(String exp, String cabin) {
		// C:4,D:4,Z:4,K:4,J:4,R:4,Y:9,B:9,M:9,A:9,H:9,S:9,O:9,E:9,Q:9,T:9,L:9,I:4
		String[] splits = exp.split(",");
		HashMap<String, String> hm = new HashMap<String, String>();
		for (String s : splits) {
			String[] sps = s.split(":");
			hm.put(sps[0], sps[1]);
		}
		String left = hm.get(cabin);
		if (left == null || "".equals(left))
			left = "9";
		return Integer.valueOf(left);
	}
	/*////////////更多舱位查询-begin///////////////*/
	public OutputResult<Response_QueryWebFlightsI_1_0,String> getIntlCabinList(FlightResult result) {
		OutputResult<Response_QueryWebFlightsI_1_0,String> or=null;
		// 从缓存中获取
		or=CacheHelper.GetFromCache(Response_QueryWebFlightsI_1_0.class, result.unformatMapkey);
		Response_QueryWebFlightsI_1_0 cabinList = or.getResultObj();

		if (cabinList != null) {
			or = new OutputResult<Response_QueryWebFlightsI_1_0,String>();
			or.code = Constant.Code_Succeed;
			or.setResultObj(cabinList);
		} else {
			List<Flight> list = new ArrayList<Flight>();
			Entry<String, Hashtable<String, Object[]>> targetEntry = result.targetFs
					.iterator().next();
//			Object[] targetH_PsgType = null;// ADT对应的内容
			Request_GetFlightPriceI_1_0 rgfp = new Request_GetFlightPriceI_1_0();
			for (Entry<String, Object[]> et : targetEntry.getValue().entrySet()) {
				Object[] trip = et.getValue();// S1、S2对应的value
				// 查政策的时候，不需要航班里的详情，中转的也不需要传中转的相关信息
				Object[] segments = ((ArrayList) trip[0]).toArray();
				// Object[] flights = ((ArrayList) trip[1]).toArray();
				// Object[] firstFlight = ((ArrayList) flights[0]).toArray();
				Flight f = new Flight();
				f.setAvNumber(segments[0].toString());
				f.setOrgcity(segments[2].toString());
				f.setDetcity(segments[6].toString());
				f.setFlightNodeMessage(segments[1].toString());
				f.setStarttime(segments[4].toString());
				if (et.getKey().equals("S1")) {
					list.add(0, f);
				} else {
					list.add(f);
				}
			}
			rgfp.setPsgCode(CommonMethodUtils.getPassengerType(result.baseTripKey));
			rgfp.setClassCode("");
			rgfp.setPhysicalCabins("");
			rgfp.setPsgQuantity("");
			rgfp.setSearchPriority("P");
			rgfp.setFlight(list);
			String[] sqKeys = result.baseTripKey.split("_");
			String ticketGroup = sqKeys[sqKeys.length-1].toString();
			or=getMoreCangwei(rgfp,ticketGroup);
			if(or.code==Constant.Code_Succeed && or.getResultObj()!=null){
				CacheHelper.SaveToCache(result.unformatMapkey, or.getResultObj(), MyEnum.CacheKeepTime.十分钟);
			}
		}
		return or;
	}
	private OutputResult<Response_QueryWebFlightsI_1_0,String> getMoreCangwei(
			Request_GetFlightPriceI_1_0 rgfp, String ticketGroup) {
		OutputResult<Response_QueryWebFlightsI_1_0,String> resq = new OutputResult<Response_QueryWebFlightsI_1_0,String>();
		resq.setResultObj(new Response_QueryWebFlightsI_1_0());
		Filter_QueryWebFlightsIntl_1_0 fliter=new Filter_QueryWebFlightsIntl_1_0();
    	fliter.Compress="N";
    	fliter.CostomerGroup = ticketGroup;
    	fliter.DataFormat="J";
		Boolean flag = yeego.Request(rgfp, resq, fliter);
		return resq;
	}
	/*----------------获取国际航班退改签begin---------------*/
	public OutputResult<Response_GetAirRulesI_1_0,String> getIntlFlightRule(
			FlightResult result,String cabin) {
		OutputResult<Response_GetAirRulesI_1_0,String> or = new OutputResult<Response_GetAirRulesI_1_0,String>();
		// 从缓存中获取
		or=CacheHelper.GetFromCache(Response_GetAirRulesI_1_0.class, result.unformatMapkey+"|"+cabin);
		Response_GetAirRulesI_1_0 rule = or.getResultObj();
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
						CacheHelper.SaveToCache(result.unformatMapkey+"|"+cabin, or.getResultObj(), MyEnum.CacheKeepTime.十分钟);
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
		OutputResult<Response_QueryWebFlightsI_1_0,String> or = new OutputResult<Response_QueryWebFlightsI_1_0,String>();
		// 从缓存中获取
		or=CacheHelper.GetFromCache(Response_QueryWebFlightsI_1_0.class, result.unformatMapkey );
		Response_QueryWebFlightsI_1_0 cabinList= or.getResultObj(); 
		if(cabinList!=null){//查过舱位列表，直接从列表中查询
			targetH_PsgType = (Object[]) cabinList.flgithPrices.entrySet().iterator().next().
					getValue().get(cabin)
					.get(CommonMethodUtils.getPassengerType(result.baseTripKey));
		}else{
			targetH_PsgType = (Object[]) result.resultObj.flgithPrices
					.get(result.targetFs.iterator().next().getKey()).get(cabin)
					.get(CommonMethodUtils.getPassengerType(result.baseTripKey));
		}
		return targetH_PsgType;
	}
	/**
	 * 国际航班退改签
	 * @param airRulesIntl
	 * @return
	 */
	private OutputResult<Response_GetAirRulesI_1_0,String> getIntlAirRules(
			List<AirRulesRQ> airRulesRQs) {
		Request_GetAirRulesI_1_0 airRulesIntl = new Request_GetAirRulesI_1_0();
		List<AirRulesRQ> airRulesRQList = new ArrayList<AirRulesRQ>();
		for (int i = 0; i < airRulesRQs.size(); i++) {
			airRulesRQList.add(airRulesRQs.get(i));
		}
		airRulesIntl.setAirRulesRQs(airRulesRQList);

		OutputResult<Response_GetAirRulesI_1_0,String> resq = new OutputResult<Response_GetAirRulesI_1_0,String>();
		resq.setResultObj(new Response_GetAirRulesI_1_0());
		Filter_QueryWebFlightsIntl_1_0 fi = new Filter_QueryWebFlightsIntl_1_0();
		fi.DataFormat = "J";
		Boolean flag = yeego.Request(airRulesIntl, resq, fi);
		return resq;
	}
	
}
