package com.viptrip.hotel.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListPassengerLikeName;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.Response_ListPassengerLikeName;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class ListPassengerLikeName extends HotelClient<Request_ListPassengerLikeName, Response_ListPassengerLikeName>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListPassengerLikeName arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		
		Page page = arg0.page;
	
		if (!Common.IsNullOrEmpty(arg0.userId+"")){
			if(page!=null){
				if(!Common.IsNullOrEmpty(page.index+"")){
					if(!Common.IsNullOrEmpty(page.size+"")){						
						osr.code=Constant.Code_Succeed;											
					}else osr.result="页码尺寸为空";
				}else osr.result="页码索引为空";
			}else osr.result="请求页码为空";			
		}else osr.result="用户id为空";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_ListPassengerLikeName, String> DoBusiness(
			Request_ListPassengerLikeName arg0) {
		OutputResult<Response_ListPassengerLikeName, String> or = new OutputResult<Response_ListPassengerLikeName, String>();
		or.code = Constant.Code_Succeed;// 成功标志
		
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		Response_ListPassengerLikeName passenger = service.ListPassengerLikeName(arg0);
		 
		or.setResultObj(passenger);
		return or;
	}

}
