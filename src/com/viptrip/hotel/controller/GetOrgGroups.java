package com.viptrip.hotel.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgGroups;
import com.viptrip.hotel.model.Response_GetOrgGroups;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.hotel.model.orggroups.OrgGroupData;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgGroups extends HotelClient<Request_GetOrgGroups,Response_GetOrgGroups>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgGroups arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isOrg(arg.orgId)){
				if(arg.page!=null){
					if(!Common.IsNullOrEmpty(arg.page.index+"")){
						if(!Common.IsNullOrEmpty(arg.page.size+"")){						
							osr.code=Constant.Code_Succeed;											
						}else osr.result="页码尺寸为空";
					}else osr.result="页码索引为空";
				}else osr.result="请求页码数据为空";
			}else{
				osr.result = "无效的请求参数。企业id不能为空";
			}
		}else{
			osr.result = "无效的请求参数。用户id不能为空";
		}
		
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgGroups, String> DoBusiness(
			Request_GetOrgGroups arg0) {
		OutputResult<Response_GetOrgGroups, String> or=new OutputResult<>();
		Response_GetOrgGroups obj = new Response_GetOrgGroups();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		OrgGroupData data = service.getOrgGroupList(arg0);
		//obj.data = list;
		obj.data=data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
