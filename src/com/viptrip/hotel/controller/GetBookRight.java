package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetBookRight;
import com.viptrip.hotel.model.Response_GetBookRight;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetBookRight extends HotelClient<Request_GetBookRight, Response_GetBookRight> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetBookRight arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		
		if(HotelUtil.isUser(arg0.userId)){
			if(arg0.businessType==1||arg0.businessType==2){
				osr.code=Constant.Code_Succeed;
			}else{
				osr.result="业务类型参数错误";
			}
			
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		
		return osr;
	}

	@Override
	protected OutputResult<Response_GetBookRight, String> DoBusiness(
			Request_GetBookRight arg0) {
		OutputResult<Response_GetBookRight, String> or = new OutputResult<Response_GetBookRight, String>();
		Response_GetBookRight rg = new Response_GetBookRight();
		
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		
		Integer getBookRight = service.GetBookRight(arg0);
		
		rg.data=getBookRight;
		or.setResultObj(rg);
		or.code=Constant.Code_Succeed;
		return or;
	}

}
