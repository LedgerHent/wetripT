package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.Response_GetOrderList;
import com.viptrip.wetrip.model.orderlist.ReqData_GetOrderList;
import com.viptrip.wetrip.service.IGetOrderService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetOrderList extends TicketClient<Request_GetOrderList, Response_GetOrderList>{

	@Override
	protected OutputSimpleResult DataValid(Request_GetOrderList para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code = 0;
		if(null==para || para.getData() == null){
			osr.code = 1;
			osr.result = "条件不完整";
		}else{
			ReqData_GetOrderList data = para.getData();
			int status = data.getStatus();
			if(status<0){
				osr.code = 1;
				osr.result = "条件错误";
			}
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetOrderList, String> DoBusiness(
			Request_GetOrderList para) {
		OutputResult<Response_GetOrderList, String> or = new OutputResult<Response_GetOrderList, String>();
		IGetOrderService service = ApplicationContextHelper.getInstance().getBean(IGetOrderService.class);
		Response_GetOrderList orderList = null;
		orderList = service.getOrderList(para);
		or.setResultObj(orderList);
		or.code = 0;
		return or;
	}

}
