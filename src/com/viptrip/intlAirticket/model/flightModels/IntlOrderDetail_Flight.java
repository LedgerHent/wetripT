package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Flight {
    
    public Integer flowid;// 顺序号，1，2，3 整型数字

    public String carrier;// 航司二字码 字符串

    public String carrierStr;// 航司中文简称 字符串

    public String flightNo;// 航班号 字符串

    public String shareFlightNo;// 共享航班号 字符串

    public String orgAirPortStr;// 出发机场名称 字符串

    public String detAirPortStr;// 到达机场名称 字符串

    public String orgTerm;// 出发航站楼 字符串

    public String detTerm;// 到达航站楼 字符串

    public String startDateTime;// 出发日期时间 字符串

    public String endDateTime;// 到达日期时间 字符串

    public String planeType;// 机型 字符串

    public String cangwei;// 舱位，C 字符串

    public String cangweiType;// 舱位等级，公务舱 字符串

	public Integer getFlowid() {
		return flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
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

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getShareFlightNo() {
		return shareFlightNo;
	}

	public void setShareFlightNo(String shareFlightNo) {
		this.shareFlightNo = shareFlightNo;
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

	public String getCangweiType() {
		return cangweiType;
	}

	public void setCangweiType(String cangweiType) {
		this.cangweiType = cangweiType;
	}
    
    

    
}
