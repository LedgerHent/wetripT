package com.viptrip.hotel.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgbyIds;
import com.viptrip.hotel.model.Response_GetOrgbyIds;
import com.viptrip.hotel.model.org.OrgInfo;
import com.viptrip.hotel.model.orglikename.OrgLikeName;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgbyIds  extends HotelClient<Request_GetOrgbyIds,Response_GetOrgbyIds>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgbyIds arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			osr.code=Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgbyIds, String> DoBusiness(
			Request_GetOrgbyIds arg) {
		OutputResult<Response_GetOrgbyIds, String> or=new OutputResult<>();
		Response_GetOrgbyIds res=new Response_GetOrgbyIds();
		res.data = new OrgInfo();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		String ids = "";
		if(arg.ids!=null&&arg.ids.size()>0){
			ids = arg.ids.toString().replaceAll(" +","");
			ids = ids.substring(1,ids.length()-1);
		}
		List<OrgLikeName> list=service.getOrgNameListIds(arg.userId,ids,"3");
		 
	     res.data.list = list;
	     
		 or.setResultObj(res);
		 or.code=Constant.Code_Succeed;
		return or;
	}

}
