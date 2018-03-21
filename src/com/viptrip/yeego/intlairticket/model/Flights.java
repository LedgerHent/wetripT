package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlElement;

public class Flights {
	@XmlElement(name="DepartureDate")
	public String DepartureDate;
	@XmlElement(name="DepartureTime")
	public String DepartureTime;
	@XmlElement(name="ArrivalDate")
	public String ArrivalDate;
	@XmlElement(name="ArrivalTime")
	public String ArrivalTime;
	@XmlElement(name="FlightNumber")
	public String FlightNumber;
	@XmlElement(name="ResBookDesigCode")
	public String ResBookDesigCode;
	@XmlElement(name="DepartureAirport")
	public String DepartureAirport;
	@XmlElement(name="ArrivalAirport")
	public String ArrivalAirport;
	@XmlElement(name="Equipment")
	public String Equipment;
	@XmlElement(name="MarketingAirline")
	public String MarketingAirline;
	public void setDepartureDate(String departureDate) {
		DepartureDate = departureDate;
	}
	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}
	public void setArrivalDate(String arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public void setFlightNumber(String flightNumber) {
		FlightNumber = flightNumber;
	}
	public void setResBookDesigCode(String resBookDesigCode) {
		ResBookDesigCode = resBookDesigCode;
	}
	public void setDepartureAirport(String departureAirport) {
		DepartureAirport = departureAirport;
	}
	public void setArrivalAirport(String arrivalAirport) {
		ArrivalAirport = arrivalAirport;
	}
	public void setEquipment(String equipment) {
		Equipment = equipment;
	}
	public void setMarketingAirline(String marketingAirline) {
		MarketingAirline = marketingAirline;
	}
	
	
}
