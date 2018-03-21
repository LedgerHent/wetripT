package com.viptrip.hotelHtml5.vo.tmc.request;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_ListApprovePolicyInfo extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3316757767686623020L;

	/**
	 * 入住人ID集合（数组）
	 */
	private String userIds;
	/**
	 * 费用归属部门ID集合（数组）
	 */
	private String deptIds;
	/**
	 * 企业ID
	 */
	private String bookingEnterpId;
	/**
	 * 预订人ID
	 */
	private String bookingUserId;
	/**
	 * 酒店类型  国内/国际
	 */
	private String hotelType;
	/**
	 * 城市/洲ID
	 */
	private String areaId;
	/**
	 * 酒店星级
	 */
	private Integer hotelStar;
	/**
	 * 单间房价格
	 */
	private Double hotelRoomPrice;
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getDeptIds() {
		return deptIds;
	}
	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}
	public String getBookingEnterpId() {
		return bookingEnterpId;
	}
	public void setBookingEnterpId(String bookingEnterpId) {
		this.bookingEnterpId = bookingEnterpId;
	}
	public String getBookingUserId() {
		return bookingUserId;
	}
	public void setBookingUserId(String bookingUserId) {
		this.bookingUserId = bookingUserId;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Integer getHotelStar() {
		return hotelStar;
	}
	public void setHotelStar(Integer hotelStar) {
		this.hotelStar = hotelStar;
	}
	public Double getHotelRoomPrice() {
		return hotelRoomPrice;
	}
	public void setHotelRoomPrice(Double hotelRoomPrice) {
		this.hotelRoomPrice = hotelRoomPrice;
	}
	
}
