package com.viptrip.hotel.controller;

import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_SmsCallBack;
import com.viptrip.hotel.model.Response_SmsCallBack;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class SmsCallBack extends HotelClient<Request_SmsCallBack,Response_SmsCallBack> {

	@Override
	protected OutputSimpleResult DataValid(Request_SmsCallBack arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code=Constant.Code_Succeed;
		return osr;
	}

	@Override
	protected OutputResult<Response_SmsCallBack, String> DoBusiness(
			Request_SmsCallBack arg0) {
		
		String s = etuf.v1_0.common.JSON.ObjectToJson(arg0) ;
		System.out.println(s);
		
		return null;
	}

}
