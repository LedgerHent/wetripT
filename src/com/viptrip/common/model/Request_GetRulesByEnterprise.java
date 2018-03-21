package com.viptrip.common.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Request_UserId;

public class Request_GetRulesByEnterprise extends Request_UserId{
	public List<Integer> businessTypes; 
	public Integer enterpriseId;
	public String filter;

}
