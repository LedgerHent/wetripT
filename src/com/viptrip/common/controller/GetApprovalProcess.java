package com.viptrip.common.controller;


import org.springframework.beans.factory.annotation.Autowired;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.service.TTmcApproveProcessService;


import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetApprovalProcess extends CommonClient<Request_GetApprovalProcess, Response_GetApprovalProcess>{
	@Autowired
	private TTmcApproveProcessService tTmcApproveProcessService;
	@Override
	protected OutputSimpleResult DataValid(Request_GetApprovalProcess arg0) {
		// TODO Auto-generated method stub
		OutputSimpleResult result = new OutputSimpleResult();
		result.code=Constant.Code_Succeed;
		return result;
	}

	@Override
	protected OutputResult<Response_GetApprovalProcess, String> DoBusiness(
			Request_GetApprovalProcess arg0) {
		OutputResult<Response_GetApprovalProcess, String> result=new OutputResult<Response_GetApprovalProcess, String>();
		TTmcApproveProcessService service = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
		//service.updateApproveProcess(arg0);
		Response_GetApprovalProcess approvalProcess = service.getApprovalProcess(arg0);
		result.setResultObj(approvalProcess);
		result.code = Constant.Code_Succeed;		
		return result;
	}

}
