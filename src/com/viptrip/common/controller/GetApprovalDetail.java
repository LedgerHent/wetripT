package com.viptrip.common.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.CommonClient;
import com.viptrip.common.model.Request_GetApprovalDetail;
import com.viptrip.common.model.Response_GetApprovalDetail;
import com.viptrip.common.service.PolicyService;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class GetApprovalDetail extends CommonClient<Request_GetApprovalDetail, Response_GetApprovalDetail> {

	@Override
	protected OutputSimpleResult DataValid(Request_GetApprovalDetail arg0) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if("".equals(arg0.approvalId) || arg0.approvalId==null){
			osr.code = 1;
			osr.result = "审批政策id为空";
		}else{
			osr.code=Constant.Code_Succeed;	
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_GetApprovalDetail, String> DoBusiness(Request_GetApprovalDetail arg0) {
		OutputResult<Response_GetApprovalDetail, String> result = new OutputResult<Response_GetApprovalDetail, String>();
		Response_GetApprovalDetail obj = new Response_GetApprovalDetail();
		PolicyService service = ApplicationContextHelper.getInstance().getBean(PolicyService.class);
		Res_ApprovalsDetail approvalDetail = service.getApprovalDetail(arg0);
		obj.data=approvalDetail;
		result.setResultObj(obj);
		result.code=Constant.Code_Succeed;
		return result;
	}

}
