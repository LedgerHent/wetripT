package com.viptrip.common.model;

import com.viptrip.wetrip.model.base.Response_Base;
import com.viptrip.wetrip.model.policy.GetRulesByTravllerModel;

public class Response_GetRulesByTravller extends Response_Base{
	public GetRulesByTravllerModel data;

	public GetRulesByTravllerModel getData() {
		return data;
	}

	public void setData(GetRulesByTravllerModel data) {
		this.data = data;
	}
	
	
}
