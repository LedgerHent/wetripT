package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_GetRuleById;
import com.viptrip.common.model.Response_GetRuleById;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.policy.GetRulesByTravllerModel;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetRuleById extends TicketClient<Request_GetRuleById, Response_GetRuleById>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetRuleById arg) {
		OutputSimpleResult osr = new OutputSimpleResult();
		//--------------------测试----------------------------
		if(arg.businessType != null){
			if(arg.ruleId != null){
				osr.code=Constant.Code_Succeed;						
			}else{
				osr.code = 1;
				osr.result = "政策编号为空";
			}
		}else{
			osr.code = 1;
			osr.result = "业务类型为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetRuleById, String> DoBusiness(
			Request_GetRuleById arg) {
		OutputResult<Response_GetRuleById, String> or=new OutputResult<>();
		Response_GetRuleById obj = new Response_GetRuleById();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		GetRulesByTravllerModel data = service.getPolicyById(arg);
		obj.data = data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
