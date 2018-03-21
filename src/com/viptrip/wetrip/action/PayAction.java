package com.viptrip.wetrip.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.controller.GetPayMethodList;
import com.viptrip.wetrip.model.Request_GetPayMethodList;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.service.InsuranceService;

import etuf.v1_0.model.base.output.OutputResult;

@Controller("payMethodAction")
@RequestMapping("/pay")
public class PayAction extends BaseAction{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(PayAction.class);
	
	@Resource
	private InsuranceService is;
	
	@RequestMapping("/paylist.act")
	public String getPayMethodList(){
		Request_GetPayMethodList gpml = new Request_GetPayMethodList();
		gpml.setUserId(getUserId());
		GetPayMethodList pml = new GetPayMethodList();
		OutputResult<Response_GetPayMethodList, String> or = pml.ClientRequest(gpml, Response_GetPayMethodList.class);
		
		addAttr("pay", or);
		
		return "public/orderList";
	}
}
