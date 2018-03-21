package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class RespData_IntlTicketData {

	private String mapKey;		//航班信息
	
	private Double totalPrice;		//票价（不含税）
	
	private Double totalTaxPrice;	//税总价
	
	private List<Segment> segments;		//航段
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getTotalTaxPrice() {
		return totalTaxPrice;
	}
	public void setTotalTaxPrice(Double totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	
}
