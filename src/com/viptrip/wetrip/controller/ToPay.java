package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_ToPay;
import com.viptrip.wetrip.model.Response_ToPay;
import com.viptrip.wetrip.model.flight.RespData_BookAirTicket;
import com.viptrip.wetrip.service.BookOrderService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ToPay extends TicketClient<Request_ToPay, Response_ToPay>{

	@Override
	protected OutputSimpleResult DataValid(Request_ToPay para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr.code=0;
		if(null == para || para.orderID==null ){
			osr.code = Msg.IncompletePara.getCode();
			osr.result = Msg.IncompletePara.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_ToPay, String> DoBusiness(Request_ToPay para) {
        OutputResult<Response_ToPay, String> or = new OutputResult<Response_ToPay, String>();
		
		BookOrderService service = ApplicationContextHelper.getInstance().getBean(BookOrderService.class);
		RespData_BookAirTicket data = service.toPay(para);
		Response_ToPay result = new Response_ToPay(data);
		
		or.setResultObj(result);
		or.code = Msg.Success.getCode();
		return or;
	}

}
