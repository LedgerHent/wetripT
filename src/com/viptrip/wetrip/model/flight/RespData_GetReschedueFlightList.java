package com.viptrip.wetrip.model.flight;

import java.io.Serializable;
import java.util.Date;

import com.viptrip.base.annotation.Description;

/**
 * 航班信息查询返回结果
 * @author selfwhisper
 *
 */
public class RespData_GetReschedueFlightList implements Serializable{

	private static final long serialVersionUID = 2754180727159875785L;
	
	@Description("航班的key值")
	public String mapKey;
	@Description("航班号")
	public String flightNo;
	@Description("航空公司二字码")
	public String airline;
	@Description("共享航班号")
	public String shareFlightNo;
	@Description("共享航空公司二字码")
	public String shareAirline;
	@Description("起飞日期时间")
	public Date depDateTime;
	@Description("到达日期时间")
	public Date arrDateTime;
	@Description("起飞机场")
	public String depAirport;
	@Description("到达机场")
	public String arrAirport;
	@Description("出发航站楼")
	public String depPointAT;
	@Description("到达航站楼")
	public String arrPointAT;
	@Description("价格")
	public Double price;
	@Description("优惠价格")
	public Double rebatePrice;
	@Description("折扣")
	public Double discount;
	@Description("舱位代码")
	public String cabin;
	@Description("舱位名称")
	public String cabinName;
	@Description("剩余票量")
	public Integer remain;
	@Description("机型")
	public String planeType;
	@Description("经停次数")
	public Integer stops;
	
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
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
	public String getShareFlightNo() {
		return shareFlightNo;
	}
	public void setShareFlightNo(String shareFlightNo) {
		this.shareFlightNo = shareFlightNo;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getRebatePrice() {
		return rebatePrice;
	}
	public void setRebatePrice(Double rebatePrice) {
		this.rebatePrice = rebatePrice;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public String getCabin() {
		return cabin;
	}
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}
	public String getCabinName() {
		return cabinName;
	}
	public void setCabinName(String cabinName) {
		this.cabinName = cabinName;
	}
	public Integer getRemain() {
		return remain;
	}
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	public String getPlaneType() {
		return planeType;
	}
	public void setPlaneType(String planeType) {
		this.planeType = planeType;
	}
	public Integer getStops() {
		return stops;
	}
	public void setStops(Integer stops) {
		this.stops = stops;
	}

}
