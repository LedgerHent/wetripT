package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.model.Request_DelPassenger;
import com.viptrip.wetrip.model.Response_DelPassenger;
import com.viptrip.wetrip.service.impl.PassengerService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class DelPassenger extends
		TicketClient<Request_DelPassenger, Response_DelPassenger> {

	@Override
	protected OutputSimpleResult DataValid(Request_DelPassenger arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();

		if (null == arg0 || 0l == arg0.getUserId()
				|| null == arg0.getPassengerId()) {
			osr.result = Msg.IncompletePara.getInfo();
		} else {
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}

		return osr;
	}

	@Override
	protected OutputResult<Response_DelPassenger, String> DoBusiness(
			Request_DelPassenger delPassenger) {
		OutputResult<Response_DelPassenger, String> or = new OutputResult<Response_DelPassenger, String>();

		PassengerService service = ApplicationContextHelper.getInstance()
				.getBean(PassengerService.class);
		service.deletePassenger(delPassenger);
		or.code = Constant.Code_Succeed;// 成功标志
		or.result = "成功";
		or.setResultObj(new Response_DelPassenger());
		return or;
	}

}
