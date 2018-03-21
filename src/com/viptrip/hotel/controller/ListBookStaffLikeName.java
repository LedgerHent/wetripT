package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListBookStaffLikeName;
import com.viptrip.hotel.model.Response_GetBookStaffList;
import com.viptrip.hotel.model.Response_ListBookStaffLikeName;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListBookStaffLikeName extends HotelClient<Request_ListBookStaffLikeName,Response_ListBookStaffLikeName>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListBookStaffLikeName arg) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg.userId)){
			if(HotelUtil.isPage(arg.page)){
				if(arg.businessType != null && !"".equals(arg.businessType)){
					if(arg.nameKey !=null && !"".equals(arg.nameKey)){
						osr.code = Constant.Code_Succeed;
					}else{
						osr.result = "无效的请求参数,请求参数类型错误";
					}
				}else{
					osr.result = "无效的请求参数,业务类型错误";
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
	protected OutputResult<Response_ListBookStaffLikeName, String> DoBusiness(
			Request_ListBookStaffLikeName arg) {
		OutputResult<Response_ListBookStaffLikeName, String> or = new OutputResult();
		Response_ListBookStaffLikeName obj = new Response_ListBookStaffLikeName();
		StaffList  data = null;
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		data = service.BokeListStaff(arg.userId,arg.page,arg.businessType,arg.nameKey);
		obj.data = data;
		or.code = Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

}
