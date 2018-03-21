package com.viptrip.wetrip.controller;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.RegUtil;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.model.Request_H5UserLogin;
import com.viptrip.wetrip.model.Response_H5UserLogin;
import com.viptrip.wetrip.model.base.UserInfo4Bind;
import com.viptrip.wetrip.service.impl.H5UserLoginServiceImpl;

public class H5UserLogin extends TicketClient<Request_H5UserLogin, Response_H5UserLogin>{

	@Override
	protected OutputSimpleResult DataValid(Request_H5UserLogin ul) {
		OutputSimpleResult osr=new OutputSimpleResult();
		//获取service层实例
		H5UserLoginServiceImpl userLoginSer = ApplicationContextHelper.getInstance().getBean(H5UserLoginServiceImpl.class);
		
		if(ul.data!=null){
			if(ul.data.platformId!=null){
				if(userLoginSer.checkHasPlatformId(ul.data.platformId)){
					if(!Common.IsNullOrEmpty(ul.data.user.uid)){
						if(!Common.IsNullOrEmpty(ul.data.user.name)){
							if(!Common.IsNullOrEmpty(ul.data.user.mobile)){
								if(RegUtil.isValidMobile(ul.data.user.mobile)){
									osr.code=Constant.Code_Succeed;
								}else osr.result="无效的手机号码，请确认后重试。";
							}else osr.result="手机号码为空。";
						}else osr.result="用户姓名为空。";
					}else osr.result="用户平台的id为空。";
				}else osr.result="平台信息错误！";
			}else osr.result="平台id为空。";
		}else osr.result="请求数据为null，请确认后重试。";

		return osr;
	}

	@Override
	protected OutputResult<Response_H5UserLogin, String> DoBusiness(
			Request_H5UserLogin arg0) {
		//获取service层实例
		H5UserLoginServiceImpl userLoginSer = ApplicationContextHelper.getInstance().getBean(H5UserLoginServiceImpl.class);
		
		OutputResult<Response_H5UserLogin, String> or = new OutputResult<Response_H5UserLogin, String>();
		Response_H5UserLogin resultObj = new Response_H5UserLogin();
		
		
		//根据合作方提供的信息，注册或者登录用户账户前的绑定操作
		UserInfo4Bind userInfo = userLoginSer.doUserLoginMap(arg0.data,or);//如果未关联，添加
		
		resultObj.data=userInfo;
		or.setResultObj(resultObj);
		return or;
	}

}
