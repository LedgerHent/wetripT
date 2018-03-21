package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_Stop;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail_TicketRule;

public class RespData_GetOrderDetail_Flight implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6649517108933649497L;

	/**
	 * 数字
	 * 其中N代表自然数，N>1：
		0—单程直达、经停；
		1—往返的去程、联程第一程
		N—往返去程中转第N程、联程第N程
		-1—往返的返程第一程
		-N—往返返程中转第N程
	 */
	public int flowId;
	
	/**
	 * 航班号
	 */
	public String flightNo;
	
	/**
	 * 航空公司编码
	 */
	public String airline;
	
	/**
	 * 共享航班号
	 */
	public String shareflightNo;

	/**
	 * 共享航空公司编码
	 */
	public String shareAirline;
	
	/**
	 * 字符串
	 */
	public String planeType;
	//仓位
	public String cabinCode;
	//仓位名称
	public String cabinName;
	//票价原件
	public double price;
	//票价优惠价
	public double rebatePrice;
	//折扣 
	public int discount;
	//机场建设费
	public double airportTax;
	//燃油费
	public double fuelSurTax;
	//出发机场编号
	public String depAirport;
	//到达机场编号
	public String arrAirport;
	//出发航站楼
	public String depPointAT;
	//到达航站楼
	public String arrPointAT;
	//出发日期时间
	public Date depDateTime;
	//到达日期时间
	public Date arrDateTime;
	//票规
	public RespData_GetFlightDetail_TicketRule ticketRule;
	//航班经停信息
	public List<RespData_GetFlightDetail_Stop> stops;
	
	public int getFlowId() {
		return flowId;
	}
	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}
	public String getFlightNo() {
		return flightNo;
	}
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getShareflightNo() {
		return shareflightNo;
	}
	public void setShareflightNo(String shareflightNo) {
		this.shareflightNo = shareflightNo;
	}
	public String getShareAirline() {
		return shareAirline;
	}
	public void setShareAirline(String shareAirline) {
		this.shareAirline = shareAirline;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public String getCabinCode() {
		return cabinCode;
	}
	public void setCabinCode(String cabinCode) {
		this.cabinCode = cabinCode;
	}
	public String getCabinName() {
		return cabinName;
	}
	public void setCabinName(String cabinName) {
		this.cabinName = cabinName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public double getAirportTax() {
		return airportTax;
	}
	public void setAirportTax(double airportTax) {
		this.airportTax = airportTax;
	}
	public double getFuelSurTax() {
		return fuelSurTax;
	}
	public void setFuelSurTax(double fuelSurTax) {
		this.fuelSurTax = fuelSurTax;
	}
	public String getDepAirport() {
		return depAirport;
	}
	public void setDepAirport(String depAirport) {
		this.depAirport = depAirport;
	}
	public String getArrAirport() {
		return arrAirport;
	}
	public void setArrAirport(String arrAirport) {
		this.arrAirport = arrAirport;
	}
	public String getDepPointAT() {
		return depPointAT;
	}
	public void setDepPointAT(String depPointAT) {
		this.depPointAT = depPointAT;
	}
	public String getArrPointAT() {
		return arrPointAT;
	}
	public void setArrPointAT(String arrPointAT) {
		this.arrPointAT = arrPointAT;
	}
	public Date getDepDateTime() {
		return depDateTime;
	}
	public void setDepDateTime(Date depDateTime) {
		this.depDateTime = depDateTime;
	}
	public Date getArrDateTime() {
		return arrDateTime;
	}
	public void setArrDateTime(Date arrDateTime) {
		this.arrDateTime = arrDateTime;
	}
	public RespData_GetFlightDetail_TicketRule getTicketRule() {
		return ticketRule;
	}
	public void setTicketRule(RespData_GetFlightDetail_TicketRule ticketRule) {
		this.ticketRule = ticketRule;
	}
	public List<RespData_GetFlightDetail_Stop> getStops() {
		return stops;
	}
	public void setStops(List<RespData_GetFlightDetail_Stop> stops) {
		this.stops = stops;
	}
	
	
	
	
}
