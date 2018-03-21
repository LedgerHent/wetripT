package com.viptrip.intlAirticket.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map.Entry;


import com.viptrip.base.common.MyEnum.IntlAitTicketGetTargetFType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_GetIntlTicketRule;
import com.viptrip.intlAirticket.model.Response_GetIntlTicketRule;
import com.viptrip.intlAirticket.model.flightModels.FlightResult;
import com.viptrip.intlAirticket.model.flightModels.Resp_Data_QuitUpdateTicket;
import com.viptrip.intlAirticket.service.FlightIntlService;
import com.viptrip.intlAirticket.util.CommonMethodUtils;
import com.viptrip.yeego.intlairticket.model.Response_GetAirRulesI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntlTicketRule extends IntlTicketClient<Request_GetIntlTicketRule, Response_GetIntlTicketRule>{
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlTicketRule req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.data.mapKey)){
			if(!Common.IsNullOrEmpty(req.data.cangwei)){
				osr.code=Constant.Code_Succeed;
			}else osr.result="舱位能为空，请确认输入后重试。";
		}else osr.result="航班信息key为空，请确认输入后重试。";
		return osr;
	}

	@Override
	protected OutputResult<Response_GetIntlTicketRule, String> DoBusiness(
			Request_GetIntlTicketRule req) {
		OutputResult<Response_GetIntlTicketRule, String> or = 
				new OutputResult<Response_GetIntlTicketRule, String>();
		try{
			OutputSimpleResult osr = new OutputSimpleResult();
			osr = CommonMethodUtils.valid_往返行程未查返程直接调接口(req.data.mapKey);
			if (osr.IsSucceed()) {
				OutputResult<FlightResult,String> orResult =getFlightResult(req,IntlAitTicketGetTargetFType.待确认类型查退改签,req.data.cangwei);
				FlightResult result = orResult.getResultObj();
				if (result.succeed) {
					OutputResult<Response_GetAirRulesI_1_0,String> res = service.getIntlFlightRule(result,req.data.cangwei);
					if (res.IsSucceed()) {
						if (res.getResultObj().airRules.size() > 0) {
							Response_GetIntlTicketRule l = new Response_GetIntlTicketRule();
							l.data = new ArrayList<Resp_Data_QuitUpdateTicket>();
							for (Entry<String, Hashtable<String, Object[]>> tripET : res.getResultObj().airRules.entrySet()) {
								Resp_Data_QuitUpdateTicket qut = new Resp_Data_QuitUpdateTicket();
								qut.setFlightType("S1".equals(tripET.getKey()) ? 1: 2);
								Object[] values = tripET.getValue().get("Penalties");
								for (Object o : values) {
									Object[] rules = ((ArrayList) o).toArray();
									String key = rules[0].toString();
									String value = rules[1].toString();
									if (key.equals("Cancel/Refund")) {
										qut.setTuipiao(value);
										continue;
									} else if (key.equals("Change")) {
										qut.setGaiqian(value);
										continue;
									} else if (key.equals("Noshow")) {
										qut.setWuji(value);
										continue;
									}
								}
								l.data.add(qut);
							}
							or.code=Constant.Code_Succeed;
							or.setResultObj(l);
						} else or.result="未找到匹配的退改签信息。";
		
					} else {
						or.result=res.result;
					}
				}else {
					or.result=orResult.result;
				}
			}else or.result = osr.result;
		}catch(Exception ex){
			or.result="错误代码：201701091206。错误信息：查询退改规则异常。"+ex.getMessage();
		}
		return or;
	}

	private OutputResult<FlightResult, String> getFlightResult(Request_GetIntlTicketRule req, 
			IntlAitTicketGetTargetFType type,String cangwei) {
		OutputResult<FlightResult, String> or = service.getFlightResult(req.userId, req.data.mapKey, type, cangwei);
		if (!or.IsSucceed()) {
			or.setResultObj(new FlightResult());
		}
		return or;
	}

}
