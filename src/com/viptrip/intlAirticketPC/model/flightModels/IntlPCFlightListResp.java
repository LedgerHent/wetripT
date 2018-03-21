package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.ArrayList;

public class IntlPCFlightListResp {
	public String  mapKey;	//航班信息key
	public String  carrier;	//航空公司二字码
	public String  carrierStr;	//航司中文名称
	public String  airline;	//航班号
	
	public String  orgAirPortStr;	//出发机场中文名
	public String  detAirPortStr;	//到达机场中文名
	public String  orgTerm;	//出发航站楼
	public String  detTerm;	//到达航站楼
	public String  start;	//起飞时间，格式如：2018-08-08 08:08
	public String  end;	//到达时间，格式如：2018-08-08 08:08
	public String  planeType;	//机型
	public int  transitCount;	//中转次数，0表示直飞无中转
	public ArrayList<String>  transitCities;	//JSONArray	中转城市数组，直飞无意义
	
	public double  totalPrice;	//总价（不含税），往返对应的是打包总价。
	public double totalTaxPrice;//税总价
	public String  cangwei;	//舱位
	public String  cangweiDesc;	//舱位类型
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getCarrierStr() {
		return carrierStr;
	}
	public void setCarrierStr(String carrierStr) {
		this.carrierStr = carrierStr;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getOrgAirPortStr() {
		return orgAirPortStr;
	}
	public void setOrgAirPortStr(String orgAirPortStr) {
		this.orgAirPortStr = orgAirPortStr;
	}
	public String getDetAirPortStr() {
		return detAirPortStr;
	}
	public void setDetAirPortStr(String detAirPortStr) {
		this.detAirPortStr = detAirPortStr;
	}
	public String getOrgTerm() {
		return orgTerm;
	}
	public void setOrgTerm(String orgTerm) {
		this.orgTerm = orgTerm;
	}
	public String getDetTerm() {
		return detTerm;
	}
	public void setDetTerm(String detTerm) {
		this.detTerm = detTerm;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public int getTransitCount() {
		return transitCount;
	}
	public void setTransitCount(int transitCount) {
		this.transitCount = transitCount;
	}
	public ArrayList<String> getTransitCities() {
		return transitCities;
	}
	public void setTransitCities(ArrayList<String> transitCities) {
		this.transitCities = transitCities;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalTaxPrice() {
		return totalTaxPrice;
	}
	public void setTotalTaxPrice(double totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	public String getCangweiDesc() {
		return cangweiDesc;
	}
	public void setCangweiDesc(String cangweiDesc) {
		this.cangweiDesc = cangweiDesc;
	}
	
	
}
