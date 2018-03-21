package com.viptrip.wetrip.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.controller.GetInsuranceList;
import com.viptrip.wetrip.model.Request_GetInsuranceList;
import com.viptrip.wetrip.model.Response_GetInsuranceList;
import com.viptrip.wetrip.service.InsuranceService;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/ins")
public class TAcInsuranceAction extends BaseAction{

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(TAcInsuranceAction.class);
	
	@Resource
	private InsuranceService is;
	
	@RequestMapping("/gilist.act")
	public String getInsuranceList(){
		Request_GetInsuranceList gilist = new Request_GetInsuranceList();
		gilist.setUserId(getUserId());
		GetInsuranceList gins = new GetInsuranceList();
		
		OutputResult<Response_GetInsuranceList, String> or = gins.ClientRequest(gilist, Response_GetInsuranceList.class);
		
		addAttr("ins", or);
		
		return "test/ins";
	}
}
