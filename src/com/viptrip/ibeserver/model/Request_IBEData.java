package com.viptrip.ibeserver.model;

public class Request_IBEData {
	/**
	 * 起始地
	 */
	public String sOCity;
	/**
	 * 目的地
	 */
	public String sDCity;
	/**
	 * 起飞时间 yyyyMMdd
	 */
	public String sDate;
	/**
	 * 
	 */
	public String pricetype;
	/**
	 * 仓位类型
	 */
	public Integer cabinType;
	/**
	 * 航班号，非航司组合
	 */
	public String airlines;
	
	public String cangwei;
	
	public String agreementTypeCode;
	
	public String getsOCity() {
		return sOCity;
	}
	public void setsOCity(String sOCity) {
		this.sOCity = sOCity;
	}
	public String getsDCity() {
		return sDCity;
	}
	public void setsDCity(String sDCity) {
		this.sDCity = sDCity;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String getPricetype() {
		return pricetype;
	}
	public void setPricetype(String pricetype) {
		this.pricetype = pricetype;
	}
	public String getAirlines() {
		return airlines;
	}
	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	public Integer getCabinType() {
		return cabinType;
	}
	public void setCabinType(Integer cabinType) {
		this.cabinType = cabinType;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	public String getAgreementTypeCode() {
		return agreementTypeCode;
	}
	public void setAgreementTypeCode(String agreementTypeCode) {
		this.agreementTypeCode = agreementTypeCode;
	}
	
	
}
