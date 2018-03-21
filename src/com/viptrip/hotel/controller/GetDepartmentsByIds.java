package com.viptrip.hotel.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.entity.CompanyInfo;
import com.viptrip.hotel.model.Request_GetDepartmentsByIds;
import com.viptrip.hotel.model.Response_GetDepartmentsByIds;
import com.viptrip.hotel.model.Response_GetDepartmentsLikeName;
import com.viptrip.hotel.service.impl.GetDepartmentsByIdsServiceImpl;
import com.viptrip.hotel.service.impl.GetDepartmentsLikeNameServiceImpl;
import com.viptrip.hotel.util.HotelUtil;
import com.viptrip.wetrip.controller.res.Msg;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class GetDepartmentsByIds extends HotelClient<Request_GetDepartmentsByIds,Response_GetDepartmentsByIds> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetDepartmentsByIds arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			osr.code=Constant.Code_Succeed;
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetDepartmentsByIds, String> DoBusiness(
			Request_GetDepartmentsByIds arg0) {
		//获取service层实例
		GetDepartmentsByIdsServiceImpl service = ApplicationContextHelper.getInstance().getBean(GetDepartmentsByIdsServiceImpl.class);
		
		OutputResult<Response_GetDepartmentsByIds, String> or=new OutputResult<Response_GetDepartmentsByIds, String>();	
		Response_GetDepartmentsByIds gef=new Response_GetDepartmentsByIds();
		
		CompanyInfo topTacOrg=null;
		List<CompanyInfo> childs=null;
		String ids = "";
		if(arg0.ids!=null&&arg0.ids.size()>0){
			ids = arg0.ids.toString().replaceAll(" +","");
			ids = ids.substring(1,ids.length()-1);
		}
		
		CompanyInfo info=service.getAllOrg(arg0.orgId,ids,topTacOrg, childs);//获取
		
		//childs = info.child;
							
		
		gef.data=info;
		System.out.println("***************coll=====" + gef.data.toString());
		if(gef.status==0){
			or.code=Msg.Success.getCode();
		}else or.result=" status Error";
		or.setResultObj(gef);
		
			
		return or;
	}

}
