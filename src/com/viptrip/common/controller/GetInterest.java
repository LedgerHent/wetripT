package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_GetInterest;
import com.viptrip.common.model.Response_GetInterest;
import com.viptrip.common.service.IntegralAndInterestService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetInterest extends TicketClient<Request_GetInterest, Response_GetInterest>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetInterest para) {
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
	protected OutputResult<Response_GetInterest, String> DoBusiness(
			Request_GetInterest para) {
		OutputResult<Response_GetInterest, String> or=new OutputResult<Response_GetInterest,String>();
		Response_GetInterest response = new Response_GetInterest();
		IntegralAndInterestService service = ApplicationContextHelper.getInstance().getBean(IntegralAndInterestService.class);
		double data = service.gettotalInterest(para);
		response.amount = data;
		or.setResultObj(response);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
