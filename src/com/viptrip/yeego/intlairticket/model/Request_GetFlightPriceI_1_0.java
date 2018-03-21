package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Request_Base;
/**
 * 此接口用于查询国际航班全舱位运价
 * @author jh
 *
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "GetFlightPriceI_1_0")
public class Request_GetFlightPriceI_1_0 extends Request_Base {
	public Request_GetFlightPriceI_1_0() {
	}

	/*
	 * <SearchPriority> 排序规则: 取值为 P,D,T,A 这四个字母的 24 种顺序组合。该项取值可为 1-4
	 * 个字母，如果取值为“PDTA”则表示结 果顺序按照不含税价、 直达与否、起飞时间、含税总价进行排序，如果取值为“A”则表示结果顺序按照含税
	 * 总价进行排序， 相同总价内的航班组合顺序为随机。 P：price 价格（不含税） D：direct 直达联程 T：time 时间 A：总价（含税）
	 * 不填为 ADT
	 */
	@XmlElement(name = "SearchPriority")
	public String searchPriority;

	/* <PsgCode> 乘客类型 ADT：成人 CHD：儿童 INF：婴儿 不填为成人 */
	@XmlElement(name = "PsgCode")
	public String psgCode;

	// <PsgQuantity> 乘客数量，不填为 1 数字
	@XmlElement(name = "PsgQuantity")
	public String psgQuantity;

	// <ClassCode> 舱位代码，例如：Y
	@XmlElement(name = "ClassCode")
	public String classCode;

	// <PhysicalCabins> 舱位级别 B: 公务舱 E: 经济舱 ES: 高端经济舱 F: 头等舱
	@XmlElement(name = "PhysicalCabins")
	public String physicalCabins;

	@XmlElementWrapper(name = "FlightSegment")  
    @XmlElement(name = "Flight")  
	public List<Flight>  flight;

	public void setSearchPriority(String searchPriority) {
		this.searchPriority = searchPriority;
	}

	public void setPsgCode(String psgCode) {
		this.psgCode = psgCode;
	}

	public void setPsgQuantity(String psgQuantity) {
		this.psgQuantity = psgQuantity;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public void setPhysicalCabins(String physicalCabins) {
		this.physicalCabins = physicalCabins;
	}

	public void setFlight(List<Flight> flight) {
		this.flight = flight;
	}

	

}
