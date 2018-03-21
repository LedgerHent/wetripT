package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetUserInfo4Hotel;
import com.viptrip.hotel.model.Response_GetUserInfo4Hotel;
import com.viptrip.hotel.model.userinfo4hotel.UserInfo4Hotel;
import com.viptrip.hotel.util.HotelUtil;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.IUserService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetUserInfo4Hotel extends HotelClient<Request_GetUserInfo4Hotel,Response_GetUserInfo4Hotel>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetUserInfo4Hotel arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			osr.code=Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetUserInfo4Hotel, String> DoBusiness(
			Request_GetUserInfo4Hotel arg) {
		OutputResult<Response_GetUserInfo4Hotel, String> or=new OutputResult<>();
		Response_GetUserInfo4Hotel res=new Response_GetUserInfo4Hotel();
		IUserService service = ApplicationContextHelper.getInstance().getBean(IUserService.class);
		UserInfo4Hotel user = service.getUserInfo4Hotel(Long.parseLong(arg.userId.toString()));
		 if(user != null){
			 res.data= user;
		 }else{
			 res.data=new UserInfo4Hotel();
		 }
		 or.setResultObj(res);
		 or.code=Constant.Code_Succeed;
		return or;
	}

}
