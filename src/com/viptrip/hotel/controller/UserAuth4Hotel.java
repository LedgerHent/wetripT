package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_UserAuth4Hotel;
import com.viptrip.hotel.model.Response_UserAuth4Hotel;
import com.viptrip.hotel.util.HotelUtil;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.IUserService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class UserAuth4Hotel extends HotelClient<Request_UserAuth4Hotel,Response_UserAuth4Hotel>{

	@Override
	protected OutputSimpleResult DataValid(Request_UserAuth4Hotel arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(arg.token!=null&&arg.token.length()>20){
				if(arg.menuId !=null){
					osr.code=Constant.Code_Succeed;
				}else{
					osr.result="无效的请求参数。菜单id不能为空";
				}
			}else{
				osr.result="无效的请求参数。登录令牌不能为空";
			}
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_UserAuth4Hotel, String> DoBusiness(
			Request_UserAuth4Hotel arg) {
		OutputResult<Response_UserAuth4Hotel, String> or=new OutputResult<>();
		Response_UserAuth4Hotel res=new Response_UserAuth4Hotel();
		IUserService service = ApplicationContextHelper.getInstance().getBean(IUserService.class);
		try{
		 TAcUser user = service.getUserById(Long.parseLong(arg.userId.toString()));
		 Boolean b = false;
		 if(user!=null){
			 if(!"4".equals(user.getStatus())){
				 if(arg.menuId == -1){
					 res.data=0;
				 }else{
					b = service.isMenu(user.getUserid(),arg.menuId);
					if(b){
						 res.data=0;
					}else{
						res.data=2;
					}
				 }
			 }else{
				 res.data=0; 
			 }
		 }else{
			 res.data=1;
		 }
		 or.setResultObj(res);
		 or.code=Constant.Code_Succeed;
		}catch(Exception e ){
			or.setResultObj(new Response_UserAuth4Hotel());
			or.code=Constant.Code_Failed;
		}
		return or;
	}

}
