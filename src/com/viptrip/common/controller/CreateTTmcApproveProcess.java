package com.viptrip.common.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_CreateTTmcApproveProcess;
import com.viptrip.common.model.Response_CreateTTmcApproveProcess;
import com.viptrip.common.service.TTmcApproveProcessService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class CreateTTmcApproveProcess extends CommonClient<Request_CreateTTmcApproveProcess, Response_CreateTTmcApproveProcess>{
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	@Override
	protected OutputSimpleResult DataValid(Request_CreateTTmcApproveProcess arg0) {
		// TODO Auto-generated method stub
		OutputSimpleResult result = new OutputSimpleResult();
		result.code=Constant.Code_Succeed;
		return result;
	}

	@Override
	protected OutputResult<Response_CreateTTmcApproveProcess, String> DoBusiness(
			Request_CreateTTmcApproveProcess arg0) {
		OutputResult<Response_CreateTTmcApproveProcess, String> result=new OutputResult<Response_CreateTTmcApproveProcess, String>();
		Response_CreateTTmcApproveProcess response_CreateTTmcApproveProcess=new Response_CreateTTmcApproveProcess();
		TTmcApproveProcessService service = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
		String approveProcessId= service.saveTTmcApproveProcess(arg0);
		if (StringUtils.isNotBlank(approveProcessId)) {
			response_CreateTTmcApproveProcess.approveProcessId=Long.valueOf(approveProcessId);
		}	
		result.setResultObj(response_CreateTTmcApproveProcess);
		result.code = Constant.Code_Succeed;		
		return result;
	}

}
