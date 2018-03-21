package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;

public class Response_ListApprovalByTravller extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2807255471631564703L;
	

	public List<Res_ApprovalByTravller> data;
}
