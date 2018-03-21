package com.viptrip.wetrip.controller;


import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.service.IGetOrderService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrderDetail extends TicketClient<Request_GetOrderDetail, Response_GetOrderDetail>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrderDetail para) {
		OutputSimpleResult osr = new OutputResult<Request_GetOrderDetail, String>();
		osr.code = 0;
		if(null==para){
			osr.code = 1;
			osr.result = "参数不完整";
		}else{
			if(null == para.getOrderID()){
				osr.code = 1;
				osr.result = "订单号不能为空";
			}
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrderDetail, String> DoBusiness(
			Request_GetOrderDetail para) {
		OutputResult<Response_GetOrderDetail, String> or = new OutputResult<Response_GetOrderDetail, String>();
		IGetOrderService service = ApplicationContextHelper.getInstance().getBean(IGetOrderService.class);
		Response_GetOrderDetail result = service.getOrderDetail(para);
		or.setResultObj(result);
		or.code = 0;
		return or;
	}

}
