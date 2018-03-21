package com.viptrip.intlAirticketPC.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;

import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.intlAirticketPC.IntlTicketPCClient;
import com.viptrip.intlAirticketPC.model.Request_GetIntlPCCabinList;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCCabinList;
import com.viptrip.intlAirticketPC.model.cabinModels.RespData_Cabins;
import com.viptrip.intlAirticketPC.model.flightModels.Cabin;
import com.viptrip.intlAirticketPC.service.PCFlightIntlService;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;
/**
 * 国际机票PC端更多舱位接口实现。
 * @author admin
 *
 */
public class GetIntlPCCabinList extends 
				IntlTicketPCClient<Request_GetIntlPCCabinList,Response_GetIntlPCCabinList>{
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	PCFlightIntlService servicePC = 
			ApplicationContextHelper.getInstance().getBean(PCFlightIntlService.class);
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlPCCabinList req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.mapKey)){
			osr.code=Constant.Code_Succeed;
		}else osr.result="航班信息key为空，请确认输入后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_GetIntlPCCabinList, String> DoBusiness(
			Request_GetIntlPCCabinList req) {
		OutputResult<Response_GetIntlPCCabinList, String> out = new OutputResult<Response_GetIntlPCCabinList, String>();
		try{
			OutputResult<FlightResult, String> orResult = servicePC.getFlightResult(req.userId, req.mapKey, null, null);
			if (!orResult.IsSucceed()) {
				orResult.setResultObj(new FlightResult());
			}
			
			FlightResult result = orResult.getResultObj();
			if (result.succeed) {
				OutputResult<Response_QueryWebFlightsI_1_0,String> rsq=service.getIntlCabinList(result);
				if (rsq.code == Constant.Code_Succeed) {
					Hashtable<String, Hashtable<String, Object[]>> htFs = rsq.getResultObj().flgithPrices
							.entrySet().iterator().next().getValue();// H-F
					out = servicePC.formatResp4Cabin(htFs,result);

				} else {
					out.result = rsq.result;
				}
			}else{
				out.result = orResult.result;
			}
		}catch(Exception ex){
			out.result="错误代码：201801231441Li。错误信息：查询舱位列表抛出异常。"+ex.getMessage();
		}
		return out;
	}

}
