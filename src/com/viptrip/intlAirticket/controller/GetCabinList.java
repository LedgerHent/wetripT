package com.viptrip.intlAirticket.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_GetCabinList;
import com.viptrip.intlAirticket.model.Response_GetCabinList;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.model.flightModels.RespData_Cabins;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetCabinList extends 
		IntlTicketClient<Request_GetCabinList,Response_GetCabinList>{
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputSimpleResult DataValid(Request_GetCabinList req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.mapKey)){
			osr.code=Constant.Code_Succeed;
		}else osr.result="航班信息key为空，请确认输入后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_GetCabinList, String> DoBusiness(
			Request_GetCabinList req) {
		OutputResult<Response_GetCabinList, String> out = 
				new OutputResult<Response_GetCabinList, String>();
		try{
			OutputSimpleResult osr = new OutputSimpleResult();
			osr = CommonMethodUtils.valid_往返行程未查返程直接调接口(req.mapKey);
			if (osr.IsSucceed()) {
				OutputResult<FlightResult,String> orResult = 
						getFlightResult(req,IntlAitTicketGetTargetFType.待确认类型查更多舱位,null);
				FlightResult result = orResult.getResultObj();
				if (result.succeed) {
					OutputResult<Response_QueryWebFlightsI_1_0,String> rsq=service.getIntlCabinList(result);
					if (rsq.code == Constant.Code_Succeed) {
						Hashtable<String, Hashtable<String, Object[]>> htFs = rsq.getResultObj().flgithPrices
								.entrySet().iterator().next().getValue();// H-F
						Response_GetCabinList l = new Response_GetCabinList();
						l.data = new ArrayList<RespData_Cabins>();
						for (Entry<String, Hashtable<String, Object[]>> et : htFs
								.entrySet()) {
							RespData_Cabins cabin = new RespData_Cabins();
							Object[] psgPrice = et.getValue().get(CommonMethodUtils.getPassengerType(result.baseTripKey));
							cabin.setTotalPrice(null==psgPrice[0]?0.0:Double.valueOf(psgPrice[0].toString()));
							cabin.setCangwei(String.valueOf(psgPrice[4]));
							cabin.setCangweiDesc(CommonMethodUtils.getCabinTypeName(
									result.resultObj, String.valueOf(psgPrice[5])));
							cabin.setSeatsLeft(null==psgPrice[13]?0:Integer.valueOf(psgPrice[13].toString()));
							cabin.setDiscount("");// TOTO 中航易购国际接口没有折扣，也没有基本运价，所以不能计算得出
							cabin.setIsAgreementPrice(0);// TOTO 中航易购国际接口目前不支持
	
							cabin.setTotalTaxPrice(CommonMethodUtils.getDoubleStringWithoutDot(Double.valueOf(et
									.getValue().get("TotalFare")[0].toString())
									- Double.valueOf(cabin.getTotalPrice())));
	
							l.data.add(cabin);
						}
						out.setResultObj(l);
						out.code = Constant.Code_Succeed;
	
					} else {
						out.result = rsq.result;
					}
				}else{
					out.result = orResult.result;
				}
			}else out.result = osr.result;
		}catch(Exception ex){
			out.result="错误代码：201701091209。错误信息：查询舱位列表异常。"+ex.getMessage();
		}
		return out;
	}
/*	private OutputResult<Response_GetIntlFlightDetail, String> valid_往返行程未查返程直接调接口(String mapKey) {
		OutputResult<Response_GetIntlFlightDetail, String> out =
				new OutputResult<Response_GetIntlFlightDetail, String>();
		String unformatMapkey = flightIntlService.getUnFormatMapkey(mapKey);
		String[] splits = unformatMapkey.split("\\|");
		if (splits == null || splits.length == 0) {
			out.result="无效的mapKey--" + mapKey;
		}else{
			String key4GetFlight = splits[0];
			boolean valid = true;
			boolean isRTTrip = flightIntlService.isRTTravleType(key4GetFlight);
			if (isRTTrip) {
				valid = splits[1].contains("*");
			}
			if (!valid) {
				out.result="往返行程，必须获取返程后再调用该接口。";
			}else out.code=Constant.Code_Succeed;;
		}
		return out;
	}*/
	private OutputResult<FlightResult, String> getFlightResult(
			Request_GetCabinList req, IntlAitTicketGetTargetFType type,
			String cangwei) {
		OutputResult<FlightResult, String> or = service.getFlightResult(
				req.userId, req.mapKey, type, cangwei);
		if (!or.IsSucceed()) {
			or.setResultObj(new FlightResult());
		}
		return or;
	}

}
