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
import com.viptrip.intlAirticketPC.model.Request_GetIntlPCTicketRule;
import com.viptrip.intlAirticketPC.model.Response_GetIntlPCTicketRule;
import com.viptrip.intlAirticketPC.model.ruleModels.RespIntlTicketRuleModel;
import com.viptrip.intlAirticketPC.service.PCFlightIntlService;
import com.viptrip.yeego.intlairticket.model.Response_GetAirRulesI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntlPCTicketRule extends IntlTicketPCClient<Request_GetIntlPCTicketRule, Response_GetIntlPCTicketRule>{
	FlightIntlService service = 
			ApplicationContextHelper.getInstance().getBean(FlightIntlService.class);
	@Override
	protected OutputSimpleResult DataValid(Request_GetIntlPCTicketRule req) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(req.data.mapKey)){
			if(!Common.IsNullOrEmpty(req.data.cabinCode)){
				osr.code=Constant.Code_Succeed;
			}else osr.result="舱位不能为空，请确认输入后重试。";
		}else osr.result="航班信息key不能为空，请确认输入后重试。";
		return osr;
	}
	PCFlightIntlService servicePC = 
			ApplicationContextHelper.getInstance().getBean(PCFlightIntlService.class);
	@Override
	protected OutputResult<Response_GetIntlPCTicketRule, String> DoBusiness(
			Request_GetIntlPCTicketRule req) {
		OutputResult<Response_GetIntlPCTicketRule, String> or = 
				new OutputResult<Response_GetIntlPCTicketRule, String>();
		try{
			OutputResult<FlightResult, String> orResult = servicePC.getFlightResult(req.userId, req.data.mapKey, "rule", req.data.cabinCode);
			FlightResult result = orResult.getResultObj();
			if (result.succeed) {
				OutputResult<Response_GetAirRulesI_1_0,String> res = service.getIntlFlightRule(result,req.data.cabinCode);
				if (res.IsSucceed()) {
					if (res.getResultObj().airRules.size() > 0) {
						Response_GetIntlPCTicketRule l = new Response_GetIntlPCTicketRule();
						l.data = new ArrayList<RespIntlTicketRuleModel>();
						for (Entry<String, Hashtable<String, Object[]>> tripET : res.getResultObj().airRules.entrySet()) {
							RespIntlTicketRuleModel qut = new RespIntlTicketRuleModel();
							qut.flowId="S1".equals(tripET.getKey()) ? 1: 2;
							Object[] values = tripET.getValue().get("Penalties");
							for (Object o : values) {
								Object[] rules = ((ArrayList) o).toArray();
								String key = rules[0].toString();
								String value = rules[1].toString();
								if (key.equals("Cancel/Refund")) {
									qut.tuipiao=value;
									continue;
								} else if (key.equals("Change")) {
									qut.gaiqian=value;
									continue;
								} else if (key.equals("Noshow")) {
									qut.wuji=value;
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
		}catch(Exception ex){
			or.result="错误代码：201701091206。错误信息：查询退改规则异常。"+ex.getMessage();
		}
		return or;
	}

}
