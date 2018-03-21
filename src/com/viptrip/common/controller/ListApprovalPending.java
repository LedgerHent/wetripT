package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_ListApprovalPending;
import com.viptrip.common.model.Response_CreateTTmcApproveProcess;
import com.viptrip.common.model.Response_ListApprovalPending;
import com.viptrip.common.service.TTmcApproveProcessService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListApprovalPending extends CommonClient<Request_ListApprovalPending, Response_ListApprovalPending> {

	@Override
	protected OutputSimpleResult DataValid(Request_ListApprovalPending arg0) {
		OutputSimpleResult result = new OutputSimpleResult();
		result.code=Constant.Code_Succeed;
		return result;
	}

	@Override
	protected OutputResult<Response_ListApprovalPending, String> DoBusiness(
			Request_ListApprovalPending arg0) {
		OutputResult<Response_ListApprovalPending, String> result=new OutputResult<Response_ListApprovalPending, String>();
		TTmcApproveProcessService service = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
		result.setResultObj(service.listApprovalPending(arg0));
		result.code = Constant.Code_Succeed;		
		return result;
	}

}
