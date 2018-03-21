package com.viptrip.hotelHtml5.vo.request;


import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetProductDetail extends Request_Base {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String phk;
	/**
	 * 
	 */
	private String ptHotelId;
	/**
	 * 
	 */
	private String nightCount;
	/**
	 * 
	 */
	private String adultCount;
	/**
	 * 
	 */
	private String childAgeDl;
	/**
	 * 
	 */
	private String roomCount;
	/**
	 * 
	 */
	private String checkStartDate;
	/**
	 * 
	 */
	private String cityId;
	/**
	 * 
	 */
	private String tripType;
	/**
	 * 
	 */
	private String tripUserId;
	/**
	 * 
	 */
	private String enterpriseId;
	/**
	 * 
	 */
	private String enterpriseName;
	/**
	 * 国内国际
	 */
	private String homeAbroadFlag;
	/**
	 * 是否客服
	 */
	private Boolean tmcType;
	
	private String choiceAgreementHotelFlag;	//精选酒店或协议酒店：0002400001 精选酒店，0002400002 协议酒店
	public String getPhk() {
		return phk;
	}
	public void setPhk(String phk) {
		this.phk = phk;
	}
	public String getPtHotelId() {
		return ptHotelId;
	}
	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}
	public String getNightCount() {
		return nightCount;
	}
	public void setNightCount(String nightCount) {
		this.nightCount = nightCount;
	}
	public String getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(String adultCount) {
		this.adultCount = adultCount;
	}
	public String getChildAgeDl() {
		return childAgeDl;
	}
	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}
	public String getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(String roomCount) {
		this.roomCount = roomCount;
	}
	public String getCheckStartDate() {
		return checkStartDate;
	}
	public void setCheckStartDate(String checkStartDate) {
		this.checkStartDate = checkStartDate;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
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
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getHomeAbroadFlag() {
		return homeAbroadFlag;
	}
	public void setHomeAbroadFlag(String homeAbroadFlag) {
		this.homeAbroadFlag = homeAbroadFlag;
	}
	public Boolean getTmcType() {
		return tmcType;
	}
	public void setTmcType(Boolean tmcType) {
		this.tmcType = tmcType;
	}
	public String getChoiceAgreementHotelFlag() {
		return choiceAgreementHotelFlag;
	}
	public void setChoiceAgreementHotelFlag(String choiceAgreementHotelFlag) {
		this.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	}
	
}
