package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;

public class IFlight {
	@XmlElement(name="SegmentID")
	public String SegmentID;
	@XmlElement(name="SegmentType")
	public String SegmentType;
	@XmlElement(name="TicketingCarrier")
	public String TicketingCarrier;
	@XmlElement(name="ArrivalDate")
	public String ArrivalDate;
	@XmlElement(name="ArrivalTime")
	public String ArrivalTime;
	@XmlElement(name="BoardPoint")
	public String BoardPoint;
	@XmlElement(name="BoardPointAT")
	public String BoardPointAT;
	@XmlElement(name="Carrier")
	public String Carrier;
	@XmlElement(name="ClassCode")
	public String ClassCode;
	@XmlElement(name="ClassRank")
	public String ClassRank;
	@XmlElement(name="DepartureDate")
	public String DepartureDate;
	@XmlElement(name="DepartureTime")
	public String DepartureTime;
	@XmlElement(name="FlightNo")
	public String FlightNo;
	@XmlElement(name="OffPoint")
	public String OffPoint;
	@XmlElement(name="OffPointAT")
	public String OffPointAT;
	public void setSegmentID(String segmentID) {
		SegmentID = segmentID;
	}
	public void setSegmentType(String segmentType) {
		SegmentType = segmentType;
	}
	public void setTicketingCarrier(String ticketingCarrier) {
		TicketingCarrier = ticketingCarrier;
	}
	public void setArrivalDate(String arrivalDate) {
		ArrivalDate = arrivalDate;
	}
	public void setArrivalTime(String arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	public void setBoardPoint(String boardPoint) {
		BoardPoint = boardPoint;
	}
	public void setBoardPointAT(String boardPointAT) {
		BoardPointAT = boardPointAT;
	}
	public void setCarrier(String carrier) {
		Carrier = carrier;
	}
	public void setClassCode(String classCode) {
		ClassCode = classCode;
	}
	public void setClassRank(String classRank) {
		ClassRank = classRank;
	}
	public void setDepartureDate(String departureDate) {
		DepartureDate = departureDate;
	}
	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}
	public void setFlightNo(String flightNo) {
		FlightNo = flightNo;
	}
	public void setOffPoint(String offPoint) {
		OffPoint = offPoint;
	}
	public void setOffPointAT(String offPointAT) {
		OffPointAT = offPointAT;
	}
	
	
}
