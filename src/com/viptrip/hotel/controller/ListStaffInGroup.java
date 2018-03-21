package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListStaffInGroup;
import com.viptrip.hotel.model.Response_GetBookStaffList;
import com.viptrip.hotel.model.Response_ListStaffInGroup;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListStaffInGroup extends HotelClient<Request_ListStaffInGroup,Response_ListStaffInGroup>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListStaffInGroup arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isOrg(arg.orgId)){
				if(arg.groupId != null){
					if(HotelUtil.isPage(arg.page)){
						osr.code = Constant.Code_Succeed;
					}else{
						osr.result = "无效的请求参数。分页参数错误";
					}
				}else{
					osr.result = "无效的请求参数。分组id不能为空";
				}
			}else{
				osr.result = "无效的请求参数。企业id不能为空";
			}
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected OutputResult<Response_ListStaffInGroup, String> DoBusiness(
			Request_ListStaffInGroup arg) {
		OutputResult<Response_ListStaffInGroup, String> or = new OutputResult();
		Response_ListStaffInGroup obj = new Response_ListStaffInGroup();
		StaffList  data = null;
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		data = service.ListStaff(arg.orgId,arg.page,"inGroup",arg.groupId);
		obj.data = data;
		or.code = Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

}
