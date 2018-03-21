package com.viptrip.hotel.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListStaffLikeTel;
import com.viptrip.hotel.model.Response_ListStaffLikeTel;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListStaffLikeTel extends HotelClient<Request_ListStaffLikeTel, Response_ListStaffLikeTel> {

	@Override
	protected OutputSimpleResult DataValid(Request_ListStaffLikeTel arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			if(HotelUtil.isOrg(arg0.orgId)){
				if(HotelUtil.isPage(arg0.page)){
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
	protected OutputResult<Response_ListStaffLikeTel, String> DoBusiness(
			Request_ListStaffLikeTel arg0) {
		OutputResult<Response_ListStaffLikeTel, String> or = new OutputResult<Response_ListStaffLikeTel, String>();
		Response_ListStaffLikeTel obj=new Response_ListStaffLikeTel();
		StaffList  data = null;
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
	    data = service.ListStaff(arg0.orgId,arg0.page,"likeTel",arg0.nameKey);
		obj.data = data; 
		or.code=Constant.Code_Succeed;
		or.setResultObj(obj);
		return or;
	}

	

}
