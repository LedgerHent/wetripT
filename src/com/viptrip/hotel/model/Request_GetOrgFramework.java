package com.viptrip.hotel.model;

import com.viptrip.hotel.model.base.Request_Base;

public class Request_GetOrgFramework extends Request_Base {
	/**
	 * 2017-6-29 hanzhicheng
	 */
	private static final long serialVersionUID = 1L;
	
	public int type;
	public int orgId;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	
	
}
