package com.viptrip.hotel.controller;

import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_PayCallBack;
import com.viptrip.hotel.model.Response_PayCallBack;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class PayCallBack extends HotelClient<Request_PayCallBack, Response_PayCallBack>{

	@Override
	protected OutputSimpleResult DataValid(Request_PayCallBack arg0) {
		return null;
	}

	@Override
	protected OutputResult<Response_PayCallBack, String> DoBusiness(
			Request_PayCallBack arg0) {
		return null;
	}

	public String encryptParam(String paramStr){
		String result = null;
		OutputSimpleResult encryptParams = EncryptParams(GetConfig(), paramStr);
		if(encryptParams.IsSucceed()&&encryptParams.code == Constant.Code_Succeed){
			result = encryptParams.result;
		}
		return result;
	}
	
	public String decryptParam(String encryptedStr){
		String result = null;
		OutputSimpleResult encryptParams = DecryptParams(GetConfig(), encryptedStr);
		if(encryptParams.IsSucceed()&&encryptParams.code == Constant.Code_Succeed){
			result = encryptParams.result;
		}
		return result;
	}
}
