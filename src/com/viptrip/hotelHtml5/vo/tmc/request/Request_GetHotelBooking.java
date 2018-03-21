package com.viptrip.hotelHtml5.vo.tmc.request;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetHotelBooking extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否TMC客服
	 */
	private Boolean isTMC;
	/**
	 * 精选或协议酒店	0002400001（精选）0002400002（协议）
	 */
	private String choiceAgreementHotelFlag;
	
	/**
	 * 酒店房型唯一标志
	 */
	private String phk;
	/**
	 * 房间数量
	 */
	private Integer roomCount;
	
	/**
	 * 国内国际	-99（国际）	
	 */
	private String homeAbroadFlag;
	/**
	 * 出行类型	0000700001（因公）0000700002（因私）
	 */
	private String tripType;
	/**
	 * 预订人
	 */
	private String tripUserId;
	
	/**
	 * 
	 */
	private String ptHotelId;
	/**
	 * 
	 */
	private Integer adultCount;
	/**
	 * 
	 */
	private Integer nightCount;
	/**
	 * yyyy-MM-dd
	 */
	private String checkStartDateStr;
	/**
	 * 是否常规/超规审批     （超规审批：超标且设置超规审批   常规：其他）
	 */
	private Boolean isSuperRule;
	
	public Boolean getIsTMC() {
		return isTMC;
	}
	public void setIsTMC(Boolean isTMC) {
		this.isTMC = isTMC;
	}
	public String getChoiceAgreementHotelFlag() {
		return choiceAgreementHotelFlag;
	}
	public void setChoiceAgreementHotelFlag(String choiceAgreementHotelFlag) {
		this.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	}
	public String getPhk() {
		return phk;
	}
	public void setPhk(String phk) {
		this.phk = phk;
	}
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	public String getHomeAbroadFlag() {
		return homeAbroadFlag;
	}
	public void setHomeAbroadFlag(String homeAbroadFlag) {
		this.homeAbroadFlag = homeAbroadFlag;
	}
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getTripUserId() {
		return tripUserId;
	}
	public void setTripUserId(String tripUserId) {
		this.tripUserId = tripUserId;
	}
	public String getPtHotelId() {
		return ptHotelId;
	}
	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}
	public Integer getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}
	public Integer getNightCount() {
		return nightCount;
	}
	public void setNightCount(Integer nightCount) {
		this.nightCount = nightCount;
	}
	public String getCheckStartDateStr() {
		return checkStartDateStr;
	}
	public void setCheckStartDateStr(String checkStartDateStr) {
		this.checkStartDateStr = checkStartDateStr;
	}
	public Boolean getIsSuperRule() {
		return isSuperRule;
	}
	public void setIsSuperRule(Boolean isSuperRule) {
		this.isSuperRule = isSuperRule;
	}
	
}
