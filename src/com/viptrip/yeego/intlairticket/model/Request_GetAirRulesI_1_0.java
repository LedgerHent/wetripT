package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Request_Base;
/**
 * 此接口用于查询国际航班的退改签规定
 * @author jh
 *
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "GetAirRulesI_1_0")
public class Request_GetAirRulesI_1_0 extends Request_Base {
	
	public Request_GetAirRulesI_1_0() {
	}
	
    @XmlElement(name="AirRulesRQ")
	public List<AirRulesRQ>  airRulesRQs;

	public void setAirRulesRQs(List<AirRulesRQ> airRulesRQs) {
		this.airRulesRQs = airRulesRQs;
	}
	
	
	
}
