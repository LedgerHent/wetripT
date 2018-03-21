package com.viptrip.wetrip.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.controller.GetApprovalDetail;
import com.viptrip.wetrip.model.Request_GetApprovalDetail;
import com.viptrip.wetrip.model.Response_GetApprovalDetail;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;


@Controller
@RequestMapping("/approve")
public class ApprovePolicyAction extends BaseAction{
	
	private static Logger logger = LoggerFactory
			.getLogger(PassengerAction.class);
	
	
	@RequestMapping("/detail")
	public String getApprovalDetail(Integer approvalId){
		
		Request_GetApprovalDetail detail = new Request_GetApprovalDetail();
		detail.approvalId=approvalId;
		GetApprovalDetail approvalDetail = new GetApprovalDetail();
		
		OutputResult<Response_GetApprovalDetail, Response_BaseMsg> result = approvalDetail.ClientRequest(
				detail, Response_GetApprovalDetail.class,Response_BaseMsg.class);
		System.out.println("result.getResultObj()"+new Gson().toJson(result.getResultObj()));
		
		return null;
		
	}
	

}
