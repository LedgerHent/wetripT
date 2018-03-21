package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.common.model.Response_GetRulesByTravller;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.policy.GetRulesByTravllerModel;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetRulesByTravller extends
CommonClient<Request_GetRulesByTravller, Response_GetRulesByTravller>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetRulesByTravller arg) {
		OutputSimpleResult osr = new OutputSimpleResult();
		//--------------------测试----------------------------
		if(arg.businessTypes != null&&arg.businessTypes.size() > 0 ){
			if(arg.travellers != null&&arg.travellers.size() > 0 ){
				osr.code=Constant.Code_Succeed;						
			}else{
				osr.code = 1;
				osr.result = "用户id为空";
			}
		}else{
			osr.code = 1;
			osr.result = "业务类型为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetRulesByTravller, String> DoBusiness(
			Request_GetRulesByTravller arg) {
		OutputResult<Response_GetRulesByTravller, String> or=new OutputResult<>();
		Response_GetRulesByTravller obj = new Response_GetRulesByTravller();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		GetRulesByTravllerModel data = service.getPolicyDetail(arg);
		obj.data = data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
