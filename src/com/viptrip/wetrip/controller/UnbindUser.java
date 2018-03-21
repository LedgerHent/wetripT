package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_UnbindUser;
import com.viptrip.wetrip.model.Response_UnbindUser;
import com.viptrip.wetrip.service.impl.UserBindService;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class UnbindUser extends
		TicketClient<Request_UnbindUser, Response_UnbindUser> {

	// 获取service层实例
	UserBindService service = ApplicationContextHelper.getInstance().getBean(
			UserBindService.class);

	@Override
	protected OutputSimpleResult DataValid(Request_UnbindUser arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if (!Common.IsNullOrEmpty(arg0.uuid)) {
			if (arg0.userId != 0) {
				osr.code = Constant.Code_Succeed;
			} else
				osr.result = "用户编号为空，请确认输入后重试。";
		} else
			osr.result = "微信用户唯一ID为空，请确认输入后重试。";

		return osr;
	}

	@Override
	protected OutputResult<Response_UnbindUser, String> DoBusiness(
			Request_UnbindUser arg0) {
		OutputResult<Response_UnbindUser, String> or = new OutputResult<>();
		OutputResult<Response_UnbindUser, String> or1 = service
				.doUnBindUser(arg0);
		// 修改绑定信息为解绑，增加解绑记录
		if (or1.IsSucceed()) {
			or.code = or1.code;
			or.setResultObj(or1.getResultObj()==null?new Response_UnbindUser():or1.getResultObj());
		} else {
			or.result = or1.result;
			or.exception = or1.exception;
		}

		return or;
	}

}
