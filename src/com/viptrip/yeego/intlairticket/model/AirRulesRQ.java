package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * 此对象用于查询国际航班的退改签规定，作为
 * 
 * @author jh
 */
/**
 * @author jh
 *
 */
public class AirRulesRQ {
	
	// <DepartureDate> 出发日期,从航班查询 S节点获得
	// <DepartureTime> 出发时间,从航班查询 S节点获得
	// <FareReference> 运价基础,从 H 节点获得
	// <FilingAirline> 发布运价的航司, 从 H节点获得
	// <DepartureAirport> 出发机场,从 H 节点获得
	// <ArrivalAirport> 到达机场,从 H 节点获得
	// <References> 退改签Sign,从H节点获得

	// 出发日期,从航班查询 S节点获得
	@XmlElement(name = "DepartureDate")
	public String startdate;
	// 出发时间,从航班查询 S节点获得
	@XmlElement(name = "DepartureTime")
	public String starttimeStr;
	// 运价基础,从 H 节点获得
	@XmlElement(name = "FareReference")
	public String priceBase;
	// 发布运价的航司, 从 H节点获得
	@XmlElement(name = "FilingAirline")
	public String carrier;
	// <DepartureAirport> 出发机场,从 H 节点获得
	@XmlElement(name = "DepartureAirport")
	public String orgcity;

	// <ArrivalAirport> 到达机场,从 H 节点获得
	@XmlElement(name = "ArrivalAirport")
	public String detcity;
	
	// <References> 退改签Sign,从H节点获得
	@XmlElement(name = "References")
	public String tuiGaiSign;

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public void setStarttimeStr(String starttimeStr) {
		this.starttimeStr = starttimeStr;
	}

	public void setPriceBase(String priceBase) {
		this.priceBase = priceBase;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public void setOrgcity(String orgcity) {
		this.orgcity = orgcity;
	}

	public void setDetcity(String detcity) {
		this.detcity = detcity;
	}

	public void setTuiGaiSign(String tuiGaiSign) {
		this.tuiGaiSign = tuiGaiSign;
	}

	
}
