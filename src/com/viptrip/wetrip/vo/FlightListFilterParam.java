package com.viptrip.wetrip.vo;

import java.io.Serializable;

public class FlightListFilterParam implements Serializable{
	private static final long serialVersionUID = 4613065322921769133L;
	
	private Integer listParam_timeArea; //0为all 1为第一个 2为第二个 3为第三个 4位第四个
	private String listParam_code;//航空公司二字码
	private String listParam_cabin;//仓位代码
	
	public Integer getListParam_timeArea() {
		return listParam_timeArea;
	}
	public void setListParam_timeArea(Integer listParam_timeArea) {
		this.listParam_timeArea = listParam_timeArea;
	}
	public String getListParam_code() {
		return listParam_code;
	}
	public void setListParam_code(String listParam_code) {
		this.listParam_code = listParam_code;
	}
	public String getListParam_cabin() {
		return listParam_cabin;
	}
	public void setListParam_cabin(String listParam_cabin) {
		this.listParam_cabin = listParam_cabin;
	}
	
}
