package com.viptrip.hotel.model.staff;

public class Ids {
	/*
	 * 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	 */
	public Integer type;
	/*
	 * 证件号
	 */
	public String num;
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}

	
	
}
