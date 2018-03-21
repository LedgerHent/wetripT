package com.viptrip.intlAirticketPC.model.flightModels;


public class HangBan implements Cloneable{
	private String  carrier;	//航空公司二字码
	private String  carrierStr;	//航司中文名称
	private String  airline;	//航班号
	
	private String  startAirportCode;//起飞机场代码
	private String  orgAirPortStr;	//出发机场中文名
	private String  detAirPortStr;	//到达机场中文名
	private String  endAirportCode;//到达机场代码
	private String  orgTerm;	//出发航站楼
	private String  detTerm;	//到达航站楼
	private String  startDateTime;	//起飞时间，格式如：2018-08-08 08:08
	private String  endDateTime;	//到达时间，格式如：2018-08-08 08:08
	private String travlTime;//行程时间
	private String  planeType;	//机型
	private String  cangwei;	//舱位
	private String  seatsLeft;	//舱位
	
	@Override  
    public HangBan clone() throws CloneNotSupportedException  
    {  
        return (HangBan)super.clone();  
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
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public String getCangwei() {
		return cangwei;
	}
	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}
	public String getStartAirportCode() {
		return startAirportCode;
	}
	public void setStartAirportCode(String startAirportCode) {
		this.startAirportCode = startAirportCode;
	}
	public String getEndAirportCode() {
		return endAirportCode;
	}
	public void setEndAirportCode(String endAirportCode) {
		this.endAirportCode = endAirportCode;
	}
	public String getTravlTime() {
		return travlTime;
	}
	public void setTravlTime(String travlTime) {
		this.travlTime = travlTime;
	}
	public String getSeatsLeft() {
		return seatsLeft;
	}
	public void setSeatsLeft(String seatsLeft) {
		this.seatsLeft = seatsLeft;
	}
	
}
