package com.viptrip.common.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.model.policy.ApproveTravller;

public class Request_ListApprovalByTravller extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6733963300892203281L;
	
	public Integer businessType;
	public Integer approvalType;
	public List<ApproveTravller> travellers;

}
