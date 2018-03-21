package com.viptrip.common.controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_DeleteApprovalProcess;
import com.viptrip.common.model.Response_DeleteApprovalProcess;
import com.viptrip.common.service.TTmcApproveProcessService;
import com.viptrip.wetrip.TicketClient;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class DeleteApprovalProcess extends CommonClient<Request_DeleteApprovalProcess, Response_DeleteApprovalProcess>{
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	@Override
	protected OutputSimpleResult DataValid(Request_DeleteApprovalProcess arg0) {
		// TODO Auto-generated method stub
		OutputSimpleResult result = new OutputSimpleResult();
		result.code=Constant.Code_Succeed;
		return result;
	}

	@Override
	protected OutputResult<Response_DeleteApprovalProcess, String> DoBusiness(
			Request_DeleteApprovalProcess arg0) {
		OutputResult<Response_DeleteApprovalProcess, String> result=new OutputResult<Response_DeleteApprovalProcess, String>();
		TTmcApproveProcessService service = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
		service.deleteApprovalProcess(arg0);
		Response_DeleteApprovalProcess response_DeleteApprovalProcess=new Response_DeleteApprovalProcess();
		response_DeleteApprovalProcess.status=Constant.Code_Succeed;
		result.setResultObj(response_DeleteApprovalProcess);
		result.code = Constant.Code_Succeed;		
		return result;
	}

}
