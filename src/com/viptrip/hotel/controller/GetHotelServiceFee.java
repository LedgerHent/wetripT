package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetHotelServiceFee;
import com.viptrip.hotel.model.Response_GetBookStaffList;
import com.viptrip.hotel.model.Response_GetHotelServiceFee;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetHotelServiceFee extends HotelClient<Request_GetHotelServiceFee,Response_GetHotelServiceFee> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetHotelServiceFee arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			osr.code=Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetHotelServiceFee, String> DoBusiness(
			Request_GetHotelServiceFee arg0) {
		OutputResult<Response_GetHotelServiceFee, String> or = new OutputResult<Response_GetHotelServiceFee, String>();
		Response_GetHotelServiceFee obj = new Response_GetHotelServiceFee();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		obj = service.getServiceFee(arg0.userId);
		or.code = Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

}
