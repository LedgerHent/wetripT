package com.viptrip.hotelHtml5.vo.tmc;

import java.io.Serializable;
import java.util.Date;

import com.viptrip.hotelHtml5.util.DateUtil;

public class TmcOrderInfoH5 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 订单状态
	 */
	private String orderState;
	/**
	 * 
	 */
	private String orderStateName;
	/**
	 * 支付状态
	 */
	private String payState;
	/**
	 * 
	 */
	private String payStateName;
	/**
	 * 审批状态
	 */
	private String approveState;
	/**
	 * 
	 */
	private String approveStateName;
	/**
	 * 订单总金额
	 */
	private double orderTotalAmount;
	/**
	 * 预订时间
	 */
	private String bookingTime;
	/**
	 * 
	 */
	private String bookingUserId;
	/**
	 * 
	 */
	private String bookingUserName;
	/**
	 * 
	 */
	private String bookingDeptId;
	/**
	 * 
	 */
	private String bookingDeptName;
	/**
	 * 
	 */
	private String bookingEnterpId;
	/**
	 * 
	 */
	private String bookingEnterpName;
	/**
	 * 
	 */
	private String agentBookingId;
	/**
	 * 
	 */
	private String agentBookingName;
	
	/**
	 * 
	 */
	private String hotelId;
	/**
	 * 
	 */
	private String hotelName;
	/**
	 * 
	 */
	private String roomTypeId;
	/**
	 * 
	 */
	private String roomTypeName;
	/**
	 * 
	 */
	private String rpId;
	/**
	 * 
	 */
	private String rpName;
	
	/**
	 * 
	 */
	private String bookingCheckinDate;
	/**
	 * 
	 */
	private String bookingCheckoutDate;
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Date bookingDate;
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Date checkInDate;
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Date checkOutnDate;
	
	/**
	 * 
	 */
	private String roomNum;
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderStateName() {
		return orderStateName;
	}
	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}
	public String getPayState() {
		return payState;
	}
	public void setPayState(String payState) {
		this.payState = payState;
	}
	public String getPayStateName() {
		return payStateName;
	}
	public void setPayStateName(String payStateName) {
		this.payStateName = payStateName;
	}
	public String getApproveState() {
		return approveState;
	}
	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}
	public String getApproveStateName() {
		return approveStateName;
	}
	public void setApproveStateName(String approveStateName) {
		this.approveStateName = approveStateName;
	}
	public double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getRpId() {
		return rpId;
	}
	public void setRpId(String rpId) {
		this.rpId = rpId;
	}
	public String getRpName() {
		return rpName;
	}
	public void setRpName(String rpName) {
		this.rpName = rpName;
	}
	public String getBookingCheckinDate() {
		return bookingCheckinDate;
	}
	public void setBookingCheckinDate(String bookingCheckinDate) {
		this.bookingCheckinDate = bookingCheckinDate;
	}
	public String getBookingCheckoutDate() {
		return bookingCheckoutDate;
	}
	public void setBookingCheckoutDate(String bookingCheckoutDate) {
		this.bookingCheckoutDate = bookingCheckoutDate;
	}
	public String getBookingUserId() {
		return bookingUserId;
	}
	public void setBookingUserId(String bookingUserId) {
		this.bookingUserId = bookingUserId;
	}
	public String getBookingUserName() {
		return bookingUserName;
	}
	public void setBookingUserName(String bookingUserName) {
		this.bookingUserName = bookingUserName;
	}
	public String getBookingDeptId() {
		return bookingDeptId;
	}
	public void setBookingDeptId(String bookingDeptId) {
		this.bookingDeptId = bookingDeptId;
	}
	public String getBookingDeptName() {
		return bookingDeptName;
	}
	public void setBookingDeptName(String bookingDeptName) {
		this.bookingDeptName = bookingDeptName;
	}
	public String getBookingEnterpId() {
		return bookingEnterpId;
	}
	public void setBookingEnterpId(String bookingEnterpId) {
		this.bookingEnterpId = bookingEnterpId;
	}
	public String getBookingEnterpName() {
		return bookingEnterpName;
	}
	public void setBookingEnterpName(String bookingEnterpName) {
		this.bookingEnterpName = bookingEnterpName;
	}
	public String getAgentBookingId() {
		return agentBookingId;
	}
	public void setAgentBookingId(String agentBookingId) {
		this.agentBookingId = agentBookingId;
	}
	public String getAgentBookingName() {
		return agentBookingName;
	}
	public void setAgentBookingName(String agentBookingName) {
		this.agentBookingName = agentBookingName;
	}
	public Date getBookingDate() {
		return DateUtil.parse(this.bookingTime, null);
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Date getCheckInDate() {
		return DateUtil.parse(this.bookingCheckinDate, null);
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutnDate() {
		return DateUtil.parse(this.bookingCheckoutDate, null);
	}
	public void setCheckOutnDate(Date checkOutnDate) {
		this.checkOutnDate = checkOutnDate;
	}
	public String getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}
	
}
