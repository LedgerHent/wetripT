package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListStaffInDep;
import com.viptrip.hotel.model.Response_ListStaffInDep;
import com.viptrip.hotel.model.Response_ListStaffLike;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListStaffInDep extends HotelClient<Request_ListStaffInDep,Response_ListStaffInDep>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListStaffInDep arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isOrg(arg.orgId)){
				if(HotelUtil.isPage(arg.page)){
					osr.code = Constant.Code_Succeed;
				}else{
					osr.result = "无效的请求参数。分页参数错误";
				}
			}else{
				osr.result = "无效的请求参数。企业id不能为空";
			}
		}else{
			osr.result = "无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_ListStaffInDep, String> DoBusiness(
			Request_ListStaffInDep arg) {
		OutputResult<Response_ListStaffInDep, String> or = new OutputResult<Response_ListStaffInDep, String>();
		Response_ListStaffInDep obj=new Response_ListStaffInDep();
		StaffList  data = null;
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		data = service.ListStaff(arg.orgId,arg.page,"inDep");
		or.code = Constant.Code_Succeed;
		obj.data = data;
		or.setResultObj(obj);
		
		return or;
	}

}
