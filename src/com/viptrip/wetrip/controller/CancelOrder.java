package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_CancelOrder;
import com.viptrip.wetrip.model.Response_AuditOrder;
import com.viptrip.wetrip.model.Response_CancelOrder;
import com.viptrip.wetrip.service.CancelOrderService;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class CancelOrder extends TicketClient<Request_CancelOrder, Response_CancelOrder>{

	@Override
	protected OutputSimpleResult DataValid(Request_CancelOrder req) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(null==req ){
			osr.result = Msg.IncompletePara.getInfo();
		}else{
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_CancelOrder, String> DoBusiness(Request_CancelOrder req) {
		OutputResult<Response_CancelOrder, String> or = new OutputResult<Response_CancelOrder, String>();
		or.code = Constant.Code_Succeed;//成功标志
		CancelOrderService cos = ApplicationContextHelper.getInstance().getBean(CancelOrderService.class);
		Integer status = cos.CancelOrder(req);
		Response_CancelOrder resultObj = new Response_CancelOrder();
		resultObj.status=status;
		or.setResultObj(resultObj);
		return or;
	}

	

}
