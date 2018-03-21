package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class Resp_Data_IntlOrderDetail {

    public Integer orderId;// 订单编号 整型数字

    public String orderNo;// 订单编号 字符串，用于显示

    public Integer type;// 0-企业出行1-个人出行 整型数字

    public Integer payMethod;// 支付方式，1-公司月结,2-在线支付,3-线下支付,4-预付款支付 整型数字

    public String bookDatetime;// 预订日期时间，2017-01-15 20:00 字符串

    public String pnr;// pnr 字符串

    public Integer travelType;// 1:单程 2:往返 整型数字

    public String orgCityName;// 出发城市名称 字符串

    public String detCityName;// 到达城市名称 字符串

    public String flightDate;// 去程出发日期，2017-01-12 字符串

    public Double amount;// 订单总额 双精度数字

    public String status;// 订单状态，已审核 整型数字

    public IntlOrderDetail_CheckMan checkMan; // 审核人对象 JSONObject

    public List<IntlOrderDetail_Segment> segments;// 航程信息 JSONArray

    public List<IntlOrderDetail_Passenger> passengers;// 乘机人对象 JSONArray

    public List<IntlOrderDetail_Informer> informaers;// 通知人 JSONArray

    public List<IntlOrderDetail_Ticket> tickets;// 原始票信息 JSONArray

    public List<IntlOrderDetail_Change> changes;// 改期信息（可多次改期） JSONArray

    public List<IntlOrderDetail_Refund> refunds;// 退票信息 JSONArray

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(Integer payMethod) {
		this.payMethod = payMethod;
	}

	public String getBookDatetime() {
		return bookDatetime;
	}

	public void setBookDatetime(String bookDatetime) {
		this.bookDatetime = bookDatetime;
	}

	public String getPnr() {
		return pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	public Integer getTravelType() {
		return travelType;
	}

	public void setTravelType(Integer travelType) {
		this.travelType = travelType;
	}

	public String getOrgCityName() {
		return orgCityName;
	}

	public void setOrgCityName(String orgCityName) {
		this.orgCityName = orgCityName;
	}

	public String getDetCityName() {
		return detCityName;
	}

	public void setDetCityName(String detCityName) {
		this.detCityName = detCityName;
	}

	public String getFlightDate() {
		return flightDate;
	}

	public void setFlightDate(String flightDate) {
		this.flightDate = flightDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public IntlOrderDetail_CheckMan getCheckMan() {
		return checkMan;
	}

	public void setCheckMan(IntlOrderDetail_CheckMan checkMan) {
		this.checkMan = checkMan;
	}

	public List<IntlOrderDetail_Segment> getSegments() {
		return segments;
	}

	public void setSegments(List<IntlOrderDetail_Segment> segments) {
		this.segments = segments;
	}

	public List<IntlOrderDetail_Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<IntlOrderDetail_Passenger> passengers) {
		this.passengers = passengers;
	}

	public List<IntlOrderDetail_Informer> getInformaers() {
		return informaers;
	}

	public void setInformaers(List<IntlOrderDetail_Informer> informaers) {
		this.informaers = informaers;
	}

	public List<IntlOrderDetail_Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<IntlOrderDetail_Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<IntlOrderDetail_Change> getChanges() {
		return changes;
	}

	public void setChanges(List<IntlOrderDetail_Change> changes) {
		this.changes = changes;
	}

	public List<IntlOrderDetail_Refund> getRefunds() {
		return refunds;
	}

	public void setRefunds(List<IntlOrderDetail_Refund> refunds) {
		this.refunds = refunds;
	}
    
    

}
