package com.viptrip.common.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.CityLevelModel;
import com.viptrip.common.model.Request_GetCustomerCityGrade;
import com.viptrip.common.model.Response_GetCityGrade;
import com.viptrip.common.model.Response_GetCustomerCityGrade;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetCustomerCityGrade extends TicketClient<Request_GetCustomerCityGrade, Response_GetCustomerCityGrade>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetCustomerCityGrade arg) {
		OutputSimpleResult o = new OutputSimpleResult();
		if(null == arg ){
			o.code = Msg.IncompletePara.getCode();
			o.result = Msg.IncompletePara.getInfo();
		}else{
			if(arg.enterpriseId == null){
				o.code = Msg.IncompletePara.getCode();
				o.result = Msg.IncompletePara.getInfo();
			}else{
				o.code = Constant.Code_Succeed;	
			}
		}
		return o;
	}

	@Override
	protected OutputResult<Response_GetCustomerCityGrade, String> DoBusiness(
			Request_GetCustomerCityGrade arg) {
		OutputResult<Response_GetCustomerCityGrade, String> or=new OutputResult<>();
		Response_GetCustomerCityGrade obj = new Response_GetCustomerCityGrade();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		List<CityLevelModel> data = service.getCustomerCityGrade(arg);
		obj.data = data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;}

}
