package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgGroupsByIds;
import com.viptrip.hotel.model.Response_GetOrgGroupsByIds;
import com.viptrip.hotel.model.orggroups.OrgGroupDataIds;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrgGroupsByIds extends HotelClient<Request_GetOrgGroupsByIds,Response_GetOrgGroupsByIds>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgGroupsByIds arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isOrg(arg.orgId)){
				if(arg.ids != null&&arg.ids.size() > 0 ){
					osr.code=Constant.Code_Succeed;											
				}else osr.result="请求参数为空";
			}else{
				osr.result = "无效的请求参数。企业id不能为空";
			}
		}else{
			osr.result = "无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgGroupsByIds, String> DoBusiness(
			Request_GetOrgGroupsByIds arg0) {
		OutputResult<Response_GetOrgGroupsByIds, String> or=new OutputResult<>();
		Response_GetOrgGroupsByIds obj = new Response_GetOrgGroupsByIds();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		OrgGroupDataIds data = service.getOrgGroupsByIdsList(arg0);
		obj.data = data;
		or.setResultObj(obj);
		or.code = Constant.Code_Succeed;
		return or;
	}

}
