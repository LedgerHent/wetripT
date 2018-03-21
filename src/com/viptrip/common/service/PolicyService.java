package com.viptrip.common.service;

import java.util.List;

import com.viptrip.common.model.Request_GetApprovalDetail;
import com.viptrip.common.model.Request_ListApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;

public interface PolicyService {

	public Res_ApprovalsDetail getApprovalDetail(Request_GetApprovalDetail arg);
	
	public List<Res_ApprovalByTravller> listApprovalByTravller(Request_ListApprovalByTravller arg);
}
