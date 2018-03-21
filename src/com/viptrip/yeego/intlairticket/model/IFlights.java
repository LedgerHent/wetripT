package com.viptrip.yeego.intlairticket.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class IFlights {
	@XmlElement(name="IFlight")
	public List<IFlight> iflight;

	public void setIflight(List<IFlight> iflight) {
		this.iflight = iflight;
	}
	
}
