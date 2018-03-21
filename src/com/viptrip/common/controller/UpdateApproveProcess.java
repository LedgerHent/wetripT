package com.viptrip.common.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Request_UpdateApproveProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.model.Response_UpdateApproveProcess;
import com.viptrip.common.service.TTmcApproveProcessService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class UpdateApproveProcess extends CommonClient<Request_UpdateApproveProcess, Response_UpdateApproveProcess>{
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	@Override
	protected OutputSimpleResult DataValid(Request_UpdateApproveProcess arg0) {
		// TODO Auto-generated method stub
		OutputSimpleResult result = new OutputSimpleResult();
		result.code=Constant.Code_Succeed;
		return result;
	}

	@Override
	protected OutputResult<Response_UpdateApproveProcess, String> DoBusiness(
			Request_UpdateApproveProcess arg0) {
		OutputResult<Response_UpdateApproveProcess, String> result=new OutputResult<Response_UpdateApproveProcess, String>();
		TTmcApproveProcessService service = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
		service.updateApproveProcess(arg0);
		Request_GetApprovalProcess requestApproval=new Request_GetApprovalProcess();
		requestApproval.orderno=arg0.orderno;
		requestApproval.businessType=arg0.businessType;
		Response_GetApprovalProcess approvalProcess = service.getApprovalProcess(requestApproval);
		Response_UpdateApproveProcess response_UpdateApproveProcess=new Response_UpdateApproveProcess();
		BeanUtils.copyProperties(approvalProcess, response_UpdateApproveProcess);	
		result.setResultObj(response_UpdateApproveProcess);
		result.code = Constant.Code_Succeed;		
		return result;
	}

}
