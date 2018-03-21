package com.viptrip.hotel.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgLike;
import com.viptrip.hotel.model.Response_GetOrgLike;
import com.viptrip.hotel.model.org.OrgInfoPage;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgLike extends HotelClient<Request_GetOrgLike,Response_GetOrgLike>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgLike arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isPage(arg.page)){
				if(arg.key != null){
					if(arg.key.key1 != null && arg.key.key2 != null){
						osr.code = Constant.Code_Succeed;
					}else{
						osr.result = "无效的请求参数：关键字不能同时为空。";
					}
				}else{
					osr.result = "无效的请求参数。查询条件不能为空";
				}
			}else{
				osr.result = "无效的请求参数。分页参数错误";
			}
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgLike, String> DoBusiness(
			Request_GetOrgLike arg) {
		OutputResult<Response_GetOrgLike, String> or=new OutputResult<>();
		Response_GetOrgLike res=new Response_GetOrgLike();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		String key1=arg.key.key1;
		String key2=arg.key.key2;
		OrgInfoPage info = service.getOrgNameList(arg.userId, key1+"_"+key2,"4",arg.page);
		 res.data = info;
		 or.setResultObj(res);
		 or.code=Constant.Code_Succeed;
		return or;
	}

}
