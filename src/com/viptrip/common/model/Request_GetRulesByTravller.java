package com.viptrip.common.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.model.base.Request_UserId;

@SuppressWarnings("serial")
public class Request_GetRulesByTravller  extends Request_UserId{
	public List<Integer> businessTypes; 
	public List<Integer> travellers;
	public List<Integer> getBusinessTypes() {
		return businessTypes;
	}
	public void setBusinessTypes(List<Integer> businessTypes) {
		this.businessTypes = businessTypes;
	}
	public List<Integer> getTravellers() {
		return travellers;
	}
	public void setTravellers(List<Integer> travellers) {
		this.travellers = travellers;
	}
	
}
