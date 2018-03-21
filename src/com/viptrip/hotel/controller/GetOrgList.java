package com.viptrip.hotel.controller;

import java.util.List;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_GetOrgList;
import com.viptrip.hotel.model.Response_GetOrgList;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.org.OrgInfoPage;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;
import com.viptrip.hotel.util.HotelUtil;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class GetOrgList extends HotelClient<Request_GetOrgList, Response_GetOrgList>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrgList arg0) {
		OutputSimpleResult osr=new OutputSimpleResult();
		if(HotelUtil.isUser(arg0.userId)){
			if(HotelUtil.isPage(arg0.page)){
					osr.code = Constant.Code_Succeed;
			}else{
				osr.result = "无效的请求参数。分页参数错误";
			}
		}else{
			osr.result="无效的请求参数。用户id不能为空";
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrgList, String> DoBusiness(
			Request_GetOrgList arg0) {
		OutputResult<Response_GetOrgList, String> or = new OutputResult<Response_GetOrgList, String>();
		or.code = Constant.Code_Succeed;// 成功标志
		Response_GetOrgList res = new Response_GetOrgList();
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		OrgInfoPage info = service.GetOrgList(arg0);
		res.data = info;
		or.setResultObj(res);
		return or;
	}

}
