package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Request_Base;
/**
 * 用于国际航班查询请求
 * @author wjt
 *
 */
@SuppressWarnings("serial")
@XmlRootElement(name="QueryWebFlightsI_1_0")
public class Request_QueryWebFlightsI_1_0 extends Request_Base{
	
	public Request_QueryWebFlightsI_1_0() {
		
	}
	
	//出发城市，例如：PEK 
	@XmlElement(name="BoardPoint")
	public  String orgcity;
	
	//目的城市，例如：FRA
	@XmlElement(name="OffPoint")
	public  String detcity;
	
	//出发日期，例如：2015-04-01
	@XmlElement(name="DepartureDate")
	public  String starttime;
	
	//返程日期，例如：2015-04-10
	@XmlElement(name="ReturnDate")
	public String arrvitime;	
	
	//A :所有航班(包括中转) 默认 D :直达
	@XmlElement(name="StopType")
	public String stopType;
	
	//航司
	@XmlElement(name="Carrier")
	public  String carrier;
	
	//是否仅使用机场代码查询 Y:表示请求内输入的三字码含义为机场 ;N:表示请求内输入的三字码为城市不填默认为Y
	@XmlElement(name="AirportOnly")
	public String airportOnly;
	
	//OW:单程 RT:往返当选择 RT 时，ReturnDate 为必填项
	@XmlElement(name="TravelType")
	public String travelType;
	
	//<ClassCode> 舱位代码，例如：Y 
	@XmlElement(name="ClassCode")
	public String classCode;
	
	//<PhysicalCabins> 舱位级别 B: 公务舱 E: 经济舱 ES: 高端经济舱 F: 头等舱
	@XmlElement(name="PhysicalCabins")
	public String physicalCabins;
	
	/*<SearchPriority> 排序规则:
	取值为 P,D,T,A 这四个字母的 24 种顺序组合。该项取值可为 1-4 个字母，如果取值为“PDTA”则表示结
	果顺序按照不含税价、 直达与否、起飞时间、含税总价进行排序，如果取值为“A”则表示结果顺序按照含税
	总价进行排序， 相同总价内的航班组合顺序为随机。
	P：price 价格（不含税）
	D：direct 直达联程
	T：time 时间
	A：总价（含税）
	不填为 ADT
	*/
	@XmlElement(name="SearchPriority")
	public String searchPriority;
	
	/*<PsgCode> 乘客类型 ADT：成人 CHD：儿童 INF：婴儿  不填为成人*/
	@XmlElement(name="PsgCode")
	public String psgCode;
	
	//<PsgQuantity> 乘客数量，不填为 1  数字 
	@XmlElement(name="PsgQuantity")
	public String psgQuantity;
	
	//<SingleTravel> 是否仅返回单程结果 Y：是 N：否
	@XmlElement(name="SingleTravel")
	public String singleTravel;
	
	/*<SingleTravelType> Y：仅看去程,N：仅看回程,当 SingleTravel 选择为 Y时，该项有效*/
	@XmlElement(name="SingleTravelType")
	public String singleTravelType;
	
	/*<FlightNo> 航班号，例如：CA931,当 SingleTravel 选择为 Y时，该项有效*/
	@XmlElement(name="FlightNo")
	public String flightNo;
	
	/*<NegotiatedFaresOnly>是否仅返回大客户运价
	Y： 是(仅返回指定编码的大客户运价,如果没有大客户运价则返回空)
	N： 否(返回大客户运价和普通运价)字符  */
	@XmlElement(name="NegotiatedFaresOnly")
	public String negotiatedFaresOnly;
	
	/*
	 * 返回数量
	 */
	@XmlElement(name="MaxResultCount")
	public String maxResultCount;
	
	@XmlElementWrapper(name = "NegotiatedFareCode")  
    @XmlElement(name = "Code")  
	public List<NegotiatedFareCodeModule>  codes;
	
	public String orgid;
	
	public String ticketGroup;

	public void setOrgcity(String orgcity) {
		this.orgcity = orgcity;
	}

	public void setDetcity(String detcity) {
		this.detcity = detcity;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setArrvitime(String arrvitime) {
		this.arrvitime = arrvitime;
	}

	public void setStopType(String stopType) {
		this.stopType = stopType;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setAirportOnly(String airportOnly) {
		this.airportOnly = airportOnly;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public void setPhysicalCabins(String physicalCabins) {
		this.physicalCabins = physicalCabins;
	}

	public void setSearchPriority(String searchPriority) {
		this.searchPriority = searchPriority;
	}

	public void setPsgCode(String psgCode) {
		this.psgCode = psgCode;
	}

	public void setPsgQuantity(String psgQuantity) {
		this.psgQuantity = psgQuantity;
	}

	public void setSingleTravel(String singleTravel) {
		this.singleTravel = singleTravel;
	}

	public void setSingleTravelType(String singleTravelType) {
		this.singleTravelType = singleTravelType;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public void setNegotiatedFaresOnly(String negotiatedFaresOnly) {
		this.negotiatedFaresOnly = negotiatedFaresOnly;
	}

	public void setCodes(List<NegotiatedFareCodeModule> codes) {
		this.codes = codes;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public void setTicketGroup(String ticketGroup) {
		this.ticketGroup = ticketGroup;
	}

	public void setMaxResultCount(String maxResultCount) {
		this.maxResultCount = maxResultCount;
	}

	
}
