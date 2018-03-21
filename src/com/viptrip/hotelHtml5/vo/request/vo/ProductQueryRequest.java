package com.viptrip.hotelHtml5.vo.request.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.viptrip.hotelHtml5.util.DateUtil;

public class ProductQueryRequest {
	/**
	 * 平台酒店ID
	 */
	private String ptHotelId;

	/**
	 * 入住日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date checkStartDate;

	/**
	 * 入住时长(晚)
	 */
	private Integer nightCount = 1;

	/**
	 * 成人数量
	 */
	private Integer adultCount = 1;

	/**
	 * 儿童年龄详情
	 */
	private String childAgeDl;

	/**
	 * 售卖渠道
	 */
	private String channelCode;
	/**
	 * 售卖渠道
	 */
	private Integer roomCount = 1;
	
	/**
	 * 预订日期
	 */
	private Date bookingDate;
	
	private String bookingDateStr =  DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");;
	
	
	private List<String> notCacheSupplierCodes = new ArrayList<String>();
	
	/**
	 * 区分正常预订（1）和团产品预订（2）（显示不同的取消政策）
	 */
	private String orderFlag;
	
	private String phk;
	
	private String homeAbroadFlag;	//国内与国际
	
	public String getPtHotelId() {
		return ptHotelId;
	}

	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}

	public Date getCheckStartDate() {
		return checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
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

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Integer getChildCount() {
		return this.getChildAgeDl()!= null && this.getChildAgeDl().length()>0 ? this.getChildAgeDl().split("\\|").length:0;
	}


	public String getChildAgeDl() {
		return childAgeDl;
	}

	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public List<Integer> getChildAgeList(){
		List<Integer> retVal = new ArrayList<Integer>();
		if(null != this.getChildAgeDl() && this.getChildAgeDl().length() > 0){
			String[] ages = this.getChildAgeDl().split("\\|");
			for(String age : ages){
				retVal.add(Integer.valueOf(age));
			}
		}
		
		return retVal;
	}

	public List<String> getNotCacheSupplierCodes() {
		return notCacheSupplierCodes;
	}

	public void setNotCacheSupplierCodes(List<String> notCacheSupplierCodes) {
		this.notCacheSupplierCodes = notCacheSupplierCodes;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getPhk() {
		return phk;
	}

	public void setPhk(String phk) {
		this.phk = phk;
	}

	public String getHomeAbroadFlag() {
		return homeAbroadFlag;
	}

	public void setHomeAbroadFlag(String homeAbroadFlag) {
		this.homeAbroadFlag = homeAbroadFlag;
	}

	public String getBookingDateStr() {
		return bookingDateStr;
	}

	public void setBookingDateStr(String bookingDateStr) {
		this.bookingDateStr = bookingDateStr;
	}
	
}
