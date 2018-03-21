package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_GetIntegral;
import com.viptrip.common.model.Response_GetIntegral;
import com.viptrip.common.service.IntegralAndInterestService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetIntegral extends TicketClient<Request_GetIntegral, Response_GetIntegral>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetIntegral para) {
		OutputSimpleResult o = new OutputSimpleResult();
		if(null==para || 0==para.userId){
			o.code = Msg.IncompletePara.getCode();
			o.result = Msg.IncompletePara.getInfo();
		}else{
			o.code=Constant.Code_Succeed;	
		}
		
		return o;
	}

	@Override
	protected OutputResult<Response_GetIntegral, String> DoBusiness(
			Request_GetIntegral arg0) {
		OutputResult<Response_GetIntegral, String> or=new OutputResult<Response_GetIntegral, String>();
		Response_GetIntegral response = new Response_GetIntegral();
		IntegralAndInterestService service = ApplicationContextHelper.getInstance().getBean(IntegralAndInterestService.class);
		int data = service.gettotalIntegral(arg0);
		response.amount = data;
		or.setResultObj(response);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
