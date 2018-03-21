package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.viptrip.yeego.model.Request_Base;
@SuppressWarnings("serial")
@XmlRootElement(name="GetAirPriceI_1_0")
public class Request_GetAirPriceI_1_0 extends Request_Base {
	@XmlElement(name="TicketingCarrier")
	public String TicketingCarrier;
	@XmlElement(name="PsgCode")
	public String PsgCode;
	@XmlElement(name="PsgQuantity")
	public String PsgQuantity;
	
	@XmlElementWrapper(name = "FlightRange")  
    @XmlElement(name = "FlightSegment")  
	public List<FlightSegment> FlightRange;

	public void setTicketingCarrier(String ticketingCarrier) {
		TicketingCarrier = ticketingCarrier;
	}

	public void setPsgCode(String psgCode) {
		PsgCode = psgCode;
	}

	public void setPsgQuantity(String psgQuantity) {
		PsgQuantity = psgQuantity;
	}

	public void setFlightRange(List<FlightSegment> flightRange) {
		FlightRange = flightRange;
	}
	
	
}
