package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_HotelServiceFee;
import com.viptrip.hotel.model.Response_HotelServiceFee;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class HotelServiceFee extends HotelClient<Request_HotelServiceFee,Response_HotelServiceFee> {

	@Override
	protected OutputSimpleResult DataValid(Request_HotelServiceFee arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(null!=arg.data.amount){
				if(!Common.IsNullOrEmpty(arg.data.time)){
					osr.code=Constant.Code_Succeed;
				}else osr.result="无效的请求参数。请求时间不能为空";
			}else osr.result="无效的请求参数。订单金额不能为空";
		}else osr.result="无效的请求参数。用户id不能为空";
		return osr;
	}

	@Override
	protected OutputResult<Response_HotelServiceFee, String> DoBusiness(
			Request_HotelServiceFee arg0) {
		OutputResult<Response_HotelServiceFee, String> or = new OutputResult<Response_HotelServiceFee, String>();
		Response_HotelServiceFee obj = new Response_HotelServiceFee();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		obj = service.countServiceFee(arg0);
		or.code = Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

}
