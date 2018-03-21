package com.viptrip.wetrip.controller;



import com.google.gson.Gson;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.wetrip.model.Request_Test;
import com.viptrip.wetrip.model.Response_Test;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.service.impl.TestService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public class Test extends TicketClient<Request_Test, Response_Test>{

	@Override
	protected OutputSimpleResult DataValid(Request_Test para) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(null==para){
			osr.result = Msg.IncompletePara.getInfo();
		}else{
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}
		return osr;
	}


	@Override
	protected OutputResult<Response_Test, String> DoBusiness(Request_Test para) {
		OutputResult<Response_Test, String> or = new OutputResult<Response_Test, String>();
		or.code = Constant.Code_Succeed;//成功标志
		Response_Test resultObj = new Response_Test();
		resultObj.setId(1);
		resultObj.setName("张三");
		
		//获取service层实例
		TestService service = ApplicationContextHelper.getInstance().getBean(TestService.class);
		
		PageObject<TAcUser> list = service.list();
		System.out.println("Test.DoBusiness()==list==" + new Gson().toJson(list));
		
		or.setResultObj(resultObj);
		return or;
	}
	
}
