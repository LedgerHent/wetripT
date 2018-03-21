package com.viptrip.wetrip.controller;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.common.Validator;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.Userbinding;
import com.viptrip.wetrip.model.Request_BindUser;
import com.viptrip.wetrip.model.Response_BindUser;
import com.viptrip.wetrip.model.base.MobileAndCheckCode;
import com.viptrip.wetrip.model.base.UserInfo4Bind;
import com.viptrip.wetrip.service.impl.UserBindService;

public class BindUser extends TicketClient<Request_BindUser, Response_BindUser> {

	@Override
	protected OutputSimpleResult DataValid(Request_BindUser reqStruct) {
		
		OutputSimpleResult osr = new OutputSimpleResult();
		if(!Common.IsNullOrEmpty(reqStruct.uuid)){
				
				osr = Validator.MobileAndCheckCode(reqStruct.data, true);
				
		}else osr.result="微信用户唯一ID为空，请确认输入后重试。";
		
		return osr;
	}

	@Override
	protected OutputResult<Response_BindUser, String> DoBusiness(
			Request_BindUser reqStruct) {
		OutputResult<Response_BindUser, String> or = new OutputResult<>();

		// 获取service层实例
		UserBindService service = ApplicationContextHelper.getInstance()
				.getBean(UserBindService.class);
		OutputResult<Userbinding, String> orUser = UserBind(service,reqStruct);
		if (orUser.IsSucceed()) {
			long userId = orUser.getResultObj().getUserId().longValue();
			// 根据主键获取用户信息
			OutputResult<UserInfo4Bind, String> orUB = service.GetUserInfo4Bind(userId);
			if(orUB.IsSucceed()){
				Response_BindUser res=new Response_BindUser();
				res.data=orUB.getResultObj();
				or.setResultObj(res);
				or.code=Constant.Code_Succeed;
			}else{
				or.result = orUB.result;
			}
		} else {
			or.result = orUser.result;
		}

		return or;
	}

	private OutputResult<Userbinding, String> UserBind(UserBindService service,
			Request_BindUser reqStruct) {
		OutputResult<Userbinding, String> or = new OutputResult<>();
		OutputResult<Long, String> orUid = GetOrAddUser(service, reqStruct.data);
		if (orUid.IsSucceed()) {
			long userId = orUid.getResultObj().longValue();
			or = service.AddOrUpdateUserBinding(reqStruct, userId);
		} else {
			or.result = orUid.result;
		}

		return or;
	}

	private OutputResult<Long, String> GetOrAddUser(UserBindService service,
			MobileAndCheckCode macc) {
		OutputResult<Long, String> or = new OutputResult<>();

		OutputSimpleResult osr = service.CheckSMSCodeValid(macc,1);//用于用户绑定的短信校验
		if (osr.IsSucceed()) {// 短信验证码合法，进行下一步：获取或注册用户
			OutputResult<TAcUser, String> orUser = service
					.GetOrAddUser(macc.mobile);
			if (orUser.IsSucceed()) {
				or.setResultObj(orUser.getResultObj().getUserid());
				or.code = orUser.code;
			} else {
				or.result = orUser.result;
			}
		} else {
			or.result = osr.result;
		}

		return or;
	}

}
