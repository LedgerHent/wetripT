package com.viptrip.wetrip.model.orderlist;

import java.io.Serializable;
import java.util.Date;


public class RespData_GetOrderList_Flight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7585622647815684644L;

	/**
	 * 数字
	 * 其中N代表自然数，N>1：
		0—单程直达、经停；
		1—往返的去程、联程第一程
		N—往返去程中转第N程、联程第N程
		-1—往返的返程第一程
		-N—往返返程中转第N程
	 */
	public Integer flowId;
	
	/**
	 * 航班号
	 */
	public String flightNo;
	
	/**
	 * 航空公司编码
	 */
	public String airlineCode;
	
	/**
	 * 共享航班号
	 */
	public String shareflightNo;

	/**
	 * 共享航空公司编码
	 */
	public String shareAirline;
	
	/**
	 * 出发日期时间
	 */
	public Date depDateTime;

	/**
	 * 到达日期时间
	 */
	public Date arrDateTime;

	/**
	 * 出发城市
	 */
	public String depAirport;

	/**
	 * 到达城市
	 */
	public String arrAirport;

	
	
	public RespData_GetOrderList_Flight() {
	}

	/**
	 * 
	 * @param flowId
	 * 	0—单程直达、经停；
	 *	1—往返的去程、联程第一程
	 *  N—往返去程中转第N程、联程第N程
	 *  -1—往返的返程第一程
	 *  -N—往返返程中转第N程
	 * @param flightNo 航班号
	 * @param airlineCode 航司二字码
	 * @param shareflightNo 共享航班号
	 * @param shareAirline 共享航司二字码
	 * @param depDateTime 出发日期
	 * @param arrDateTime 到达日期
	 * @param depAirport 出发机场
	 * @param arrAirport 到达机场
	 */
	public RespData_GetOrderList_Flight(Integer flowId, String flightNo,
			String airlineCode, String shareflightNo, String shareAirline,
			Date depDateTime, Date arrDateTime, String depAirport,
			String arrAirport) {
		this.flowId = flowId;
		this.flightNo = flightNo;
		this.airlineCode = airlineCode;
		this.shareflightNo = shareflightNo;
		this.shareAirline = shareAirline;
		this.depDateTime = depDateTime;
		this.arrDateTime = arrDateTime;
		this.depAirport = depAirport;
		this.arrAirport = arrAirport;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public void setAirlineCode(String airlineCode) {
		this.airlineCode = airlineCode;
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
	
	
	
	
}
