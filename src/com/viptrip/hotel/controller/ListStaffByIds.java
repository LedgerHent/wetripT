package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListStaffByIds;
import com.viptrip.hotel.model.Response_ListStaffByIds;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.model.staff.StaffListIds;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListStaffByIds extends HotelClient<Request_ListStaffByIds, Response_ListStaffByIds> {

	@Override
	protected OutputSimpleResult DataValid(Request_ListStaffByIds arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			if(HotelUtil.isOrg(arg0.orgId)){
					osr.code = Constant.Code_Succeed;
			}else{
				osr.result = "无效的请求参数。企业id不能为空";
			}
		}else{
			osr.result = "无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_ListStaffByIds, String> DoBusiness(
			Request_ListStaffByIds arg0) {
		OutputResult<Response_ListStaffByIds, String> or = new OutputResult<Response_ListStaffByIds, String>();
		IComService service = ApplicationContextHelper.getInstance().getBean(IComService.class);
		Response_ListStaffByIds obj = new Response_ListStaffByIds();
		String ids = "";
		if(arg0.ids!=null&&arg0.ids.size()>0){
			ids = arg0.ids.toString().replaceAll(" +","");
			ids = ids.substring(1,ids.length()-1);
		}
		arg0.page = new Page();
		arg0.page.index = 1;
		arg0.page.size = 10000 ; 
		StaffList  data = null;
		data=service.ListStaff(arg0.orgId,arg0.page,"byIds",ids);
		StaffListIds vo = new StaffListIds();
		vo.list  = data.list; 
		vo.org = data.org;
		obj.data = vo; 
		or.setResultObj(obj);
		or.code=Constant.Code_Succeed;
		return or;
	}

}
