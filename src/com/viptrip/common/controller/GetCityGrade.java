package com.viptrip.common.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.CityLevelModel;
import com.viptrip.common.model.Request_GetCityGrade;
import com.viptrip.common.model.Response_GetCityGrade;
import com.viptrip.hotel.service.IComService;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetCityGrade extends TicketClient<Request_GetCityGrade, Response_GetCityGrade>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetCityGrade arg) {
		OutputSimpleResult o = new OutputSimpleResult();
		if(null == arg || 0 == arg.userId){
			o.code = Msg.IncompletePara.getCode();
			o.result = Msg.IncompletePara.getInfo();
		}else{
			if(arg.cityId == null){
				o.code = Msg.IncompletePara.getCode();
				o.result = Msg.IncompletePara.getInfo();
			}else{
				o.code = Constant.Code_Succeed;	
			}
		}
		return o;
	}

	@Override
	protected OutputResult<Response_GetCityGrade, String> DoBusiness(
			Request_GetCityGrade arg0) {
		OutputResult<Response_GetCityGrade, String> or=new OutputResult<>();
		Response_GetCityGrade obj = new Response_GetCityGrade();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		CityLevelModel data = service.getCityLevel(arg0);
		obj.data = data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
