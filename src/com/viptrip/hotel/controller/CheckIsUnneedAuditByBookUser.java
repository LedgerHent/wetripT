package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_CheckIsUnneedAuditByBookUser;
import com.viptrip.hotel.model.Response_CheckIsUnneedAuditByBookUser;
import com.viptrip.hotel.model.Response_GetBookRight;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class CheckIsUnneedAuditByBookUser  extends HotelClient<Request_CheckIsUnneedAuditByBookUser, Response_CheckIsUnneedAuditByBookUser>{

	@Override
	protected OutputSimpleResult DataValid(
			Request_CheckIsUnneedAuditByBookUser arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
				osr.code=Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数，用户编号不能为空";
		}
		
		return osr;
	}

	@Override
	protected OutputResult<Response_CheckIsUnneedAuditByBookUser, String> DoBusiness(
			Request_CheckIsUnneedAuditByBookUser arg0) {
		OutputResult<Response_CheckIsUnneedAuditByBookUser, String> or = new OutputResult<Response_CheckIsUnneedAuditByBookUser, String>();
		Response_CheckIsUnneedAuditByBookUser rg = new Response_CheckIsUnneedAuditByBookUser();
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		Integer data = service.CheckIsUnneedAuditByBookUser(arg0.userId);
		rg.data=data;
		or.setResultObj(rg);
		or.code=Constant.Code_Succeed;
		return or;
	}

}
