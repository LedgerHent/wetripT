package com.viptrip.hotel.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgLikeContact;
import com.viptrip.hotel.model.Response_GetOrgLikeContact;
import com.viptrip.hotel.model.org.OrgInfoPage;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgLikeContact extends HotelClient<Request_GetOrgLikeContact,Response_GetOrgLikeContact>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgLikeContact arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isPage(arg.page)){
				if(arg.nameKey == null || "".equals(arg.nameKey)){
					osr.code = Constant.Code_Succeed;
				}else{
					osr.result = "无效的请求参数。关键字为空";
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
	protected OutputResult<Response_GetOrgLikeContact, String> DoBusiness(
			Request_GetOrgLikeContact arg) {
		OutputResult<Response_GetOrgLikeContact, String> or=new OutputResult<>();
		Response_GetOrgLikeContact res=new Response_GetOrgLikeContact();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		OrgInfoPage info = service.getOrgNameList(arg.userId, arg.nameKey,"2",arg.page);
		 res.data = info;
		 or.setResultObj(res);
		 or.code=Constant.Code_Succeed;
		return or;
	}

}
