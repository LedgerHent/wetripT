package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListStaffLike;
import com.viptrip.hotel.model.Response_ListStaffLike;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListStaffLike extends HotelClient<Request_ListStaffLike, Response_ListStaffLike>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListStaffLike arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			if(HotelUtil.isOrg(arg0.orgId)){
				if(HotelUtil.isPage(arg0.page)){
					if(arg0.key != null){
						if(arg0.key.key1 != null && arg0.key.key2 != null){
							osr.code = Constant.Code_Succeed;
							
						}else{
							osr.result = "无效的请求参数：关键字不能为空。";
						}
					}else{
						osr.result = "无效的请求参数。查询条件不能为空";
					}
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
	protected OutputResult<Response_ListStaffLike, String> DoBusiness(
			Request_ListStaffLike arg0) {
		OutputResult<Response_ListStaffLike, String> or = new OutputResult<Response_ListStaffLike, String>();
		Response_ListStaffLike obj=new Response_ListStaffLike();
		StaffList  data = null;
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		data = service.getListStaffLike(arg0);
		obj.data = data; 
		or.code = Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

}
