package com.viptrip.intlAirticket.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_GetIntlFlightDetail;
import com.viptrip.intlAirticket.model.Response_GetIntlFlightDetail;
import com.viptrip.intlAirticket.model.flightModels.CabinAndPrice;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.model.flightModels.IntlFlight;
import com.viptrip.intlAirticket.model.flightModels.RespData_IntlTicketData;
import com.viptrip.intlAirticket.model.flightModels.Segment;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntlFlightDetail extends 
IntlTicketClient<Request_GetIntlFlightDetail,Response_GetIntlFlightDetail>{
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlFlightDetail req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.mapKey)){
			osr.code=Constant.Code_Succeed;
		}else osr.result="航班信息key为空，请确认输入后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_GetIntlFlightDetail, String> DoBusiness(
			Request_GetIntlFlightDetail req) {
		OutputResult<Response_GetIntlFlightDetail, String> out = 
				new OutputResult<Response_GetIntlFlightDetail, String>();
		try{
			OutputSimpleResult osr = new OutputSimpleResult();
			osr = CommonMethodUtils.valid_往返行程未查返程直接调接口(req.mapKey);
			if (osr.IsSucceed()) {
				OutputResult<FlightResult,String> orResult = getFlightResult(req,IntlAitTicketGetTargetFType.待确认类型查详情,null);
				FlightResult result = orResult.getResultObj();
				if (result.succeed) {
					Response_GetIntlFlightDetail detail = getIntlFlightDetail(
							result.unformatMapkey, result.resultObj,
							result.targetFs,req.mapKey);
					out.setResultObj(detail);
					out.code=Constant.Code_Succeed;
				}
			}else out.result = osr.result;
		}catch(Exception ex){
			ex.printStackTrace();
			out.result="错误代码：2017010912012。错误信息：查询航班详情异常。"+ex.getCause();
		}
	
		
		return out;
	}
//	private OutputResult<Response_GetIntlFlightDetail, String> valid_往返行程未查返程直接调接口(String mapKey) {
//		OutputResult<Response_GetIntlFlightDetail, String> out =
//				new OutputResult<Response_GetIntlFlightDetail, String>();
//		String unformatMapkey = CommonMethodUtils.getUnFormatMapkey(mapKey);
//		String[] splits = unformatMapkey.split("\\|");
//		if (splits == null || splits.length == 0) {
//			out.result="无效的mapKey--" + mapKey;
//		}else{
//			String key4GetFlight = splits[0];
//			boolean valid = true;
//			boolean isRTTrip = CommonMethodUtils.isRTTravleType(key4GetFlight);
//			if (isRTTrip) {
//				valid = splits[1].contains("*");
//			}
//			if (!valid) {
//				out.result="往返行程，必须获取返程后再调用该接口。";
//			}else out.code=Constant.Code_Succeed;;
//		}
//		return out;
//	}
	

	private OutputResult<FlightResult, String> getFlightResult(
			Request_GetIntlFlightDetail req, IntlAitTicketGetTargetFType type,
			String cangwei) {
		OutputResult<FlightResult, String> or = service.getFlightResult(
				req.userId, req.mapKey, type, cangwei);
		if (!or.IsSucceed()) {
			or.setResultObj(new FlightResult());
		}
		return or;
	}
	private Response_GetIntlFlightDetail getIntlFlightDetail(
			String unformatMapkey, Response_QueryWebFlightsI_1_0 flightList,
			Set<Entry<String, Hashtable<String, Object[]>>> targetFs, String mapKey) {
		Response_GetIntlFlightDetail resp_detail = new Response_GetIntlFlightDetail();
		RespData_IntlTicketData detail = new RespData_IntlTicketData();
		resp_detail.data = new RespData_IntlTicketData();
		
		Entry<String, Hashtable<String, Object[]>> targetF = targetFs.iterator().next();
		String fKey = targetF.getKey();// F1 F2 F3
		CabinAndPrice rs = service.getFlightCabinAndPrice(flightList, fKey,
				CommonMethodUtils.getPassengerType(unformatMapkey));
		detail.setMapKey(mapKey);
		detail.setTotalPrice(rs.getTotalPrice());
		detail.setTotalTaxPrice(CommonMethodUtils.getDoubleStringWithoutDot(Double
				.valueOf(rs.getTotalPriceWithTax())
				- Double.valueOf(rs.getTotalPrice())));
		String cabinTypeName=CommonMethodUtils.getCabinTypeName(flightList, rs.getCangweiType());
		Hashtable<String, Object[]> ht = targetF.getValue();
		ArrayList<Segment> segs = new ArrayList<Segment>();
		for (Entry<String, Object[]> et : ht.entrySet()) {
			Segment seg = new Segment();
			seg.setFlightType(et.getKey().trim().equals("S1") ? 1 : 2);
			List<IntlFlight> list = new ArrayList<IntlFlight>();
			Object[] flights = et.getValue();
			Object fSeg = flights[0];
			Object[] fs = ((ArrayList) flights[1]).toArray();

			int zhongZhuanCount = fs.length - 1;
			for (int i = 0; i < fs.length; i++) {
				// for (Object o : fs) {
				Object o = fs[i];
				IntlFlight l = new IntlFlight();
				Object[] os = ((ArrayList) o).toArray();
				l.setTransitCount(zhongZhuanCount);
				l.setCarrier(os[0].toString());
				l.setCarrierStr(CommonMethodUtils.getCarrierName(flightList,
						l.getCarrier()));
				l.setAirline(os[1].toString());
				l.setOrgAirPortStr(CommonMethodUtils.getAirportName(
						flightList, os[2].toString()));
				l.setDetAirPortStr(CommonMethodUtils.getAirportName(
						flightList, os[3].toString()));
				l.setStartStringTime(CommonMethodUtils.getDateTime(
						os[4].toString(),
						os[5].toString()));
				l.setEndStringTime(CommonMethodUtils.getDateTime(
						os[6].toString(), os[7].toString()));
				l.setFlightTime(Integer.valueOf(os[8].toString()));
				l.setPlaneType(CommonMethodUtils.getPlaneTypeName(
						flightList, os[9].toString()));
				l.setOrgTerm(os[11].toString());
				l.setDetTerm(os[12].toString());
				l.setFood(CommonMethodUtils.getFoodStr(os[13]));
				String[] cabins = rs.getCangwei().split("/");
				l.setCangwei(1==seg.getFlightType() ? cabins[0]
						.split(",")[i] : cabins[1].split(",")[i]);
				l.setCangweiDesc(getCabinTypeName(2==seg.getFlightType(),i,cabinTypeName));
				// 如果是往返中转，舱位是A,B/C,D,E格式
				l.setSeatsLeft(service.getSeatsLeftByCabin(os[15].toString(),l.getCangwei()));
				l.setAirlineshare(null==os[19]?"":(os[19].toString()));
				list.add(l);
			}
			seg.setIntlFlights(list);
			segs.add(seg);
		}
		detail.setSegments(segs);
		resp_detail.data = detail;
		return resp_detail;
	}

	public String getCabinTypeName(boolean isBackTrip,int flightIndex,String cabinTypeNames){
		if(cabinTypeNames==null || "".equals(cabinTypeNames)){
			cabinTypeNames="经济舱1/经济舱1";
		}
		String[] splits=cabinTypeNames.split("/")[isBackTrip?1:0].split(",");
		if(flightIndex>splits.length-1){
			return splits[0];
		}else
		{
			return splits[flightIndex];
		}
	}

}
