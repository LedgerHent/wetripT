package com.viptrip.intlAirticket.model.flightModels;
/**
 * 国际航班信息
 * @author Administrator
 *
 */
public class IntlFlight {
	
	private int transitCount;		//中转次数，0表示直飞无中转
	private String carrier;			//航空公司二字码
	private String carrierStr;		//航司中文名称
	private String airline;			//航班号
	private String orgAirPortStr;	//出发机场中文名
	private String detAirPortStr;	//到达机场中文名
	private String orgTerm;			//出发航站楼
	private String detTerm;			//到达航站楼
	private String startStringTime;	//起飞时间  格式：2018-08-08 08:08
	private String endStringTime;	//到达时间  格式：2018-08-08 08:08
	private String planeType;		//机型
	private String cangwei;			//舱位
	private String cangweiDesc;		//舱位类型，如经济舱
	private int flightTime;		//飞行时长，单位分钟
	private int seatsLeft;		//剩余票数
	private String food;			//餐     0-无餐，1-有餐
	private String airlineshare;	//共享航班号

	public int getTransitCount() {
		return transitCount;
	}

	public void setTransitCount(int transitCount) {
		this.transitCount = transitCount;
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

	public String getStartStringTime() {
		return startStringTime;
	}

	public void setStartStringTime(String startStringTime) {
		this.startStringTime = startStringTime;
	}

	public String getEndStringTime() {
		return endStringTime;
	}

	public void setEndStringTime(String endStringTime) {
		this.endStringTime = endStringTime;
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

	public String getCangweiDesc() {
		return cangweiDesc;
	}

	public void setCangweiDesc(String cangweiDesc) {
		this.cangweiDesc = cangweiDesc;
	}

	public int getFlightTime() {
		return flightTime;
	}

	public void setFlightTime(int flightTime) {
		this.flightTime = flightTime;
	}

	public int getSeatsLeft() {
		return seatsLeft;
	}

	public void setSeatsLeft(int seatsLeft) {
		this.seatsLeft = seatsLeft;
	}

	public String getAirlineshare() {
		return airlineshare;
	}

	public void setAirlineshare(String airlineshare) {
		this.airlineshare = airlineshare;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}
}
