package com.viptrip.hotelHtml5.vo.tmc;

public class Ids implements JhData{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738491004300235944L;
	/*
	 * 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	 */
	private Integer type;
	/*
	 * 证件号
	 */
	private String num;
	
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
