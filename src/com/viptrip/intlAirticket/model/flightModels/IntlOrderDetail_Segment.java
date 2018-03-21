package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class IntlOrderDetail_Segment {
    
    public Integer type;// 1：出票，2：改期 整型数字

    public Integer flowid;// 顺序号 整型数字

    public List<IntlOrderDetail_Airline> airlines; //

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlowid() {
		return flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
	}

	public List<IntlOrderDetail_Airline> getAirlines() {
		return airlines;
	}

	public void setAirlines(List<IntlOrderDetail_Airline> airlines) {
		this.airlines = airlines;
	}
    
    

    
}
