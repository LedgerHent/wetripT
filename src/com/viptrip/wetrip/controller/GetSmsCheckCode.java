package com.viptrip.wetrip.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.EnumUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.common.Validator;
import com.viptrip.wetrip.model.Request_GetSmsCheckCode;
import com.viptrip.wetrip.model.Response_GetSmsCheckCode;
import com.viptrip.wetrip.service.impl.SmsCheckCodeService;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetSmsCheckCode extends
		TicketClient<Request_GetSmsCheckCode, Response_GetSmsCheckCode> {

	// 获取service层实例
	SmsCheckCodeService smsCheckSer = ApplicationContextHelper
			.getInstance().getBean(SmsCheckCodeService.class);

	@Override
	protected OutputSimpleResult DataValid(Request_GetSmsCheckCode reqStruct) {
		OutputSimpleResult osr = new OutputSimpleResult();
		osr=Validator.MobileAndCheckCode(reqStruct.data, false);

		// 以后增加页面验证码的校验
		return osr;
	}

	@Override
	protected OutputResult<Response_GetSmsCheckCode, String> DoBusiness(
			Request_GetSmsCheckCode reqStruct) {
		OutputResult<Response_GetSmsCheckCode, String> or = new OutputResult<Response_GetSmsCheckCode, String>();
		Response_GetSmsCheckCode resultObj = new Response_GetSmsCheckCode();

		// UserBinding 用户绑定信息表中是否有手机号记录且绑定状态为未绑定的发验证码，已绑定的提示信息。
		Integer bindFlag = smsCheckSer.checkMobelBind(reqStruct.data);// 返回绑定状态，为null则没有绑定记录

		if (bindFlag == null || bindFlag == 2) {// 没有绑定信息，从用户表查，停用外用户都发验证码 以外
			// 发送验证码,先保存验证码信息，在发短信
			or = smsCheckSer.sendExceptBindSmsCode(reqStruct.data,EnumUtil.SmsType.用户绑定.getValue());
		} else if (bindFlag == 1) {// 绑定。不发验证码，提示信息
			or.result = "该手机号已绑定，请勿重复绑定。";
		} else if (bindFlag == -1) {// 多条绑定。不发验证码，提示信息
			or.result = "该手机号出现多条绑定信息，请联系客服。";
		} else{
			or.result = "绑定信息无效，请联系客服。";
		}
		or.setResultObj(resultObj);
		return or;
	}

}
