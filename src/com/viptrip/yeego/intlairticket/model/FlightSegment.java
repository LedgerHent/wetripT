package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class FlightSegment {
	@XmlElement(name="Flight")
	public List<Flights> Flight;

	public List<Flights> getFlight() {
		return Flight;
	}

	public void setFlight(List<Flights> flight) {
		Flight = flight;
	}
	
}
