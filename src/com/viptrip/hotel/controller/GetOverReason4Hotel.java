package com.viptrip.hotel.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOverReason4Hotel;
import com.viptrip.hotel.model.Response_GetOrgGroupsByIds;
import com.viptrip.hotel.model.Response_GetOverReason4Hotel;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.hotel.service.GetOverReason4HotelService;
import com.viptrip.hotel.service.IComService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOverReason4Hotel extends HotelClient<Request_GetOverReason4Hotel,Response_GetOverReason4Hotel>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOverReason4Hotel arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(arg0.userId != null){
			osr.code = Constant.Code_Succeed;
		}else{
			osr.result = "无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOverReason4Hotel, String> DoBusiness(
			Request_GetOverReason4Hotel arg0) {
		OutputResult<Response_GetOverReason4Hotel, String> or=new OutputResult<>();
		Response_GetOverReason4Hotel obj = new Response_GetOverReason4Hotel();
		GetOverReason4HotelService service = ApplicationContextHelper.getInstance().getBean(GetOverReason4HotelService.class);
		List<String> list = service.getOverReason4HotelList();
		obj.data = list;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
