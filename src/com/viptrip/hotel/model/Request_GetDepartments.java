package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

public class Request_GetDepartments extends Request_Base{

	/**
	 * 2017-7-4 hanzhicheng
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Integer orgId;


	public Integer getOrgId() {
		return orgId;
	}


	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	
	
}
