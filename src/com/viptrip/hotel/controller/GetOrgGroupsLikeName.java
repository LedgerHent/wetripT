package com.viptrip.hotel.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgGroupsLikeName;
import com.viptrip.hotel.model.Response_GetOrgGroups;
import com.viptrip.hotel.model.Response_GetOrgGroupsLikeName;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.hotel.model.orggroups.OrgGroupData;
import com.viptrip.hotel.service.IComService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgGroupsLikeName extends HotelClient<Request_GetOrgGroupsLikeName,Response_GetOrgGroupsLikeName>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgGroupsLikeName arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(arg0.userId != null&&arg0.orgId != null && arg0.orgId != -1){
			if(arg0.page!=null){
				if(!Common.IsNullOrEmpty(arg0.page.index+"")){
					if(!Common.IsNullOrEmpty(arg0.page.size+"")){						
						osr.code=Constant.Code_Succeed;											
					}else osr.result="页码尺寸为空";
				}else osr.result="页码索引为空";
			}else osr.result="请求页码数据为空";
		}else{
			osr.result = "无效的请求参数。用户id或企业id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgGroupsLikeName, String> DoBusiness(
			Request_GetOrgGroupsLikeName arg0) {
		OutputResult<Response_GetOrgGroupsLikeName, String> or=new OutputResult<>();
		Response_GetOrgGroupsLikeName obj = new Response_GetOrgGroupsLikeName();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		OrgGroupData data = service.getOrgGroupsLikeNameList(arg0);
		obj.data=data;
		//obj.data = list;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
