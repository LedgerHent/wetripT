package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendHotelSearchCondition implements Serializable {		
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 入住日期yyyyMMdd
	 */
	private String checkStartDateStr;
	
	/**
	 * 入住时长(晚)
	 */
	private Integer nightCount;
	
	/**
	 * 成人数量
	 */
	private Integer adultCount;
	
	/**
	 * 儿童年龄详情
	 */
	private String childAgeDl;	

	public String getCheckStartDateStr() {
		return checkStartDateStr;
	}

	public void setCheckStartDateStr(String checkStartDateStr) {
		this.checkStartDateStr = checkStartDateStr;
	}

	public Integer getNightCount() {
		return nightCount;
	}

	public void setNightCount(Integer nightCount) {
		this.nightCount = nightCount;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public String getChildAgeDl() {
		return childAgeDl;
	}

	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}

}
