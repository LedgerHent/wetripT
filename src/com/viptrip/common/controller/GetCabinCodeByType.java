package com.viptrip.common.controller;

import com.viptrip.base.common.MyEnum;
import com.viptrip.base.common.MyEnum.CabinType;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_GetCabinCodeByType;
import com.viptrip.common.model.Response_GetCabinCodeByType;
import com.viptrip.common.service.GetCabinCodeService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetCabinCodeByType extends TicketClient<Request_GetCabinCodeByType, Response_GetCabinCodeByType> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetCabinCodeByType para) {
		OutputSimpleResult o = new OutputSimpleResult();
		if(CabinType.getCabinType(para.cabinType)==null){
			o.code = Msg.IncompletePara.getCode();
			o.result = Msg.IncompletePara.getInfo();
		}else{
			o.code=Constant.Code_Succeed;	
		}
		
		return o;
	}

	@Override
	protected OutputResult<Response_GetCabinCodeByType, String> DoBusiness(
			Request_GetCabinCodeByType arg0) {
		OutputResult<Response_GetCabinCodeByType, String> or=new OutputResult<Response_GetCabinCodeByType, String>();
		Response_GetCabinCodeByType response = new Response_GetCabinCodeByType();
		GetCabinCodeService service = ApplicationContextHelper.getInstance().getBean(GetCabinCodeService.class);
		response = service.GetCabinCodeByType(arg0);
		or.setResultObj(response);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
