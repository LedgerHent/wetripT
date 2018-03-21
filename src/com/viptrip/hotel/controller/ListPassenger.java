package com.viptrip.hotel.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.hotel.HotelClient;
import com.viptrip.hotel.model.Request_ListPassenger;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotel.service.impl.ComServiceImpl;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;
import com.viptrip.wetrip.service.impl.PassengerService;
import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class ListPassenger extends HotelClient<Request_ListPassenger, Response_ListPassenger>{

	@Override
	protected OutputSimpleResult DataValid(Request_ListPassenger arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		
		Page page = arg0.page;
	
		if (!Common.IsNullOrEmpty(arg0.userId+"")){
			if(page!=null){
				if(!Common.IsNullOrEmpty(page.index+"")){
					if(!Common.IsNullOrEmpty(page.size+"")){						
						osr.code=Constant.Code_Succeed;											
					}else osr.result="页码尺寸为空";
				}else osr.result="页码索引为空";
			}else osr.result="请求数据为空";
		}else osr.result="用户id为空";
		
		return osr;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected OutputResult<Response_ListPassenger, String> DoBusiness(
			Request_ListPassenger arg0) {
		OutputResult<Response_ListPassenger, String> or = new OutputResult<Response_ListPassenger, String>();
		or.code = Constant.Code_Succeed;// 成功标志
		
		IComService service = ApplicationContextHelper.getInstance().getBean(ComServiceImpl.class);
		
		Response_ListPassenger passenger = service.ListPassenger(arg0);
		or.setResultObj(passenger);
		return or;
	}

}
