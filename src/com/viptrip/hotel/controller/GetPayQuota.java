package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetPayQuota;
import com.viptrip.hotel.model.Response_GetBookRight;
import com.viptrip.hotel.model.Response_GetPayQuota;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class GetPayQuota extends HotelClient<Request_GetPayQuota,Response_GetPayQuota>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetPayQuota arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			if(arg0.type!=null&&!"".equals(arg0.type+"")){
			osr.code=Constant.Code_Succeed;
			}else osr.result="额度类别不能为空";
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetPayQuota, String> DoBusiness(
			Request_GetPayQuota arg0) {
		OutputResult<Response_GetPayQuota, String> or = new OutputResult<Response_GetPayQuota, String>();
		Response_GetPayQuota rg = new Response_GetPayQuota();
		
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		
		double data = service.GetPayQuota(arg0);
		
		rg.data=data;
		or.setResultObj(rg);
		or.code=Constant.Code_Succeed;
		return or;
	}

}
