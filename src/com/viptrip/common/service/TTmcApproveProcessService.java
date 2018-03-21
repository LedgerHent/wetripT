package com.viptrip.common.service;

import com.viptrip.common.model.Request_CreateTTmcApproveProcess;
import com.viptrip.common.model.Request_DeleteApprovalProcess;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Request_ListApprovalPending;
import com.viptrip.common.model.Request_UpdateApproveProcess;
import com.viptrip.common.model.Response_CallbackApproval;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.model.Response_ListApprovalPending;
import com.viptrip.common.vo.TTmcApproveProcessVO;
public interface TTmcApproveProcessService {
	public String saveTTmcApproveProcess(Request_CreateTTmcApproveProcess request_CreateTTmcApproveProcess);
	public String saveTTmcApproveProcess(TTmcApproveProcessVO TTmcApproveProcessVO);
	public Response_ListApprovalPending listApprovalPending(Request_ListApprovalPending requestApproval);
	public String updateApproveProcess(Request_UpdateApproveProcess TTmcApproveProcess);	
	public Response_GetApprovalProcess getApprovalProcess(Request_GetApprovalProcess requestApproval);
	public String deleteApprovalProcess(Request_DeleteApprovalProcess request_DeleteApprovalProcess);
	public Response_CallbackApproval callbackApproval(Integer businessType,String orderno,Response_GetApprovalProcess response_GetApprovalProcess);
}
