package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class IntlOrderDetail_Airline {
    
    public Integer type;// 航线类型，1：去程，2：回程 整型数字

    public String rule;// 票退改签规则 字符串

    public List<IntlOrderDetail_Flight> flights; // 航班列表 JSONArray

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public List<IntlOrderDetail_Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<IntlOrderDetail_Flight> flights) {
		this.flights = flights;
	}
    
    


}
