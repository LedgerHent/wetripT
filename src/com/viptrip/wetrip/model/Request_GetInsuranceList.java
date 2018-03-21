package com.viptrip.wetrip.model;

import com.viptrip.wetrip.model.base.Request_UserId;

public class Request_GetInsuranceList extends Request_UserId{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5285944604304232334L;
	
	/**
	 * 业务类型：1-国内机票
	 */
	public int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
}
