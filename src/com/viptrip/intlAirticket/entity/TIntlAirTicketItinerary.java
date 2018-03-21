package com.viptrip.intlAirticket.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TIntlAirTicketItinerary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_AIR_TICKET_ITINERARY")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TIntlAirTicketItinerary implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3049003743054884982L;
	private Integer tripid;
	private Integer orderid;
	private Integer intlSegmentNo;
	private String intlDepartureCity;
	private String intlReachCity;
	private String intlDepartureAirport;
	private String intlReachAirport;
	private String intlDepartureTerminal;
	private String intlReachTerminal;
	private Date intlDepartureTime;
	private Date intlReachTime;
	private String intlFlightNumber;
	private String intlAirlineCompany;
	private String intlCangwei;
	private String intlVoyageStatus;
	private String intlPnr;
	private String rescheduleTimes;
	private String intlClassType;
	
	private Integer flightType;//航程类型，1-去程(单程)航班，2-回程航班(补录该字段为空)
	private Integer transferType;//中转类型     1-中转   2-直飞(补录该字段为空,不是中转航段为空)
	private String endorserule;//退改签规则,去程放去程退改签,返程放返程退改签(只针对线上预订和app)
	// Constructors

	/** default constructor */
	public TIntlAirTicketItinerary() {
	}

	/** minimal constructor */
	public TIntlAirTicketItinerary(Integer orderid) {
		this.orderid = orderid;
	}

	/** full constructor */
	public TIntlAirTicketItinerary(Integer orderid,
			Integer intlSegmentNo, String intlDepartureCity,
			String intlReachCity, String intlDepartureAirport,
			String intlReachAirport, String intlDepartureTerminal,
			String intlReachTerminal, Date intlDepartureTime,
			Date intlReachTime, String intlFlightNumber,
			String intlAirlineCompany, String intlCangwei,
			String intlVoyageStatus, String intlPnr,String rescheduleTimes,String intlClassType,Integer flightType,Integer transferType,String endorserule) {
		this.orderid = orderid;
		this.intlSegmentNo = intlSegmentNo;
		this.intlDepartureCity = intlDepartureCity;
		this.intlReachCity = intlReachCity;
		this.intlDepartureAirport = intlDepartureAirport;
		this.intlReachAirport = intlReachAirport;
		this.intlDepartureTerminal = intlDepartureTerminal;
		this.intlReachTerminal = intlReachTerminal;
		this.intlDepartureTime = intlDepartureTime;
		this.intlReachTime = intlReachTime;
		this.intlFlightNumber = intlFlightNumber;
		this.intlAirlineCompany = intlAirlineCompany;
		this.intlCangwei = intlCangwei;
		this.intlVoyageStatus = intlVoyageStatus;
		this.intlPnr = intlPnr;
		this.rescheduleTimes=rescheduleTimes;
		this.intlClassType=intlClassType;
		this.flightType=flightType;
		this.transferType=transferType;
		this.endorserule=endorserule;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_AIR_TICKET_ITINERARY")
	@SequenceGenerator(name="SEQ_INTL_AIR_TICKET_ITINERARY",allocationSize=1,initialValue=120, sequenceName="SEQ_INTL_AIR_TICKET_ITINERARY")
	@Column(name = "TRIPID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getTripid() {
		return this.tripid;
	}

	public void setTripid(Integer tripid) {
		this.tripid = tripid;
	}

	@Column(name = "ORDERID", nullable = false, precision = 22, scale = 0)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "INTL_SEGMENT_NO", precision = 22, scale = 0)
	public Integer getIntlSegmentNo() {
		return this.intlSegmentNo;
	}

	public void setIntlSegmentNo(Integer intlSegmentNo) {
		this.intlSegmentNo = intlSegmentNo;
	}

	@Column(name = "INTL_DEPARTURE_CITY", length = 200)
	public String getIntlDepartureCity() {
		return this.intlDepartureCity;
	}

	public void setIntlDepartureCity(String intlDepartureCity) {
		this.intlDepartureCity = intlDepartureCity;
	}

	@Column(name = "INTL_REACH_CITY", length = 200)
	public String getIntlReachCity() {
		return this.intlReachCity;
	}

	public void setIntlReachCity(String intlReachCity) {
		this.intlReachCity = intlReachCity;
	}

	@Column(name = "INTL_DEPARTURE_AIRPORT", length = 100)
	public String getIntlDepartureAirport() {
		return this.intlDepartureAirport;
	}

	public void setIntlDepartureAirport(String intlDepartureAirport) {
		this.intlDepartureAirport = intlDepartureAirport;
	}

	@Column(name = "INTL_REACH_AIRPORT", length = 100)
	public String getIntlReachAirport() {
		return this.intlReachAirport;
	}

	public void setIntlReachAirport(String intlReachAirport) {
		this.intlReachAirport = intlReachAirport;
	}

	@Column(name = "INTL_DEPARTURE_TERMINAL", length = 5)
	public String getIntlDepartureTerminal() {
		return this.intlDepartureTerminal;
	}

	public void setIntlDepartureTerminal(String intlDepartureTerminal) {
		this.intlDepartureTerminal = intlDepartureTerminal;
	}

	@Column(name = "INTL_REACH_TERMINAL", length = 5)
	public String getIntlReachTerminal() {
		return this.intlReachTerminal;
	}

	public void setIntlReachTerminal(String intlReachTerminal) {
		this.intlReachTerminal = intlReachTerminal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_DEPARTURE_TIME", length = 7)
	public Date getIntlDepartureTime() {
		return this.intlDepartureTime;
	}

	public void setIntlDepartureTime(Date intlDepartureTime) {
		this.intlDepartureTime = intlDepartureTime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_REACH_TIME", length = 7)
	public Date getIntlReachTime() {
		return this.intlReachTime;
	}

	public void setIntlReachTime(Date intlReachTime) {
		this.intlReachTime = intlReachTime;
	}

	@Column(name = "INTL_FLIGHT_NUMBER", length = 20)
	public String getIntlFlightNumber() {
		return this.intlFlightNumber;
	}

	public void setIntlFlightNumber(String intlFlightNumber) {
		this.intlFlightNumber = intlFlightNumber;
	}

	@Column(name = "INTL_AIRLINE_COMPANY", length = 10)
	public String getIntlAirlineCompany() {
		return this.intlAirlineCompany;
	}

	public void setIntlAirlineCompany(String intlAirlineCompany) {
		this.intlAirlineCompany = intlAirlineCompany;
	}

	@Column(name = "INTL_CANGWEI", length = 5)
	public String getIntlCangwei() {
		return this.intlCangwei;
	}

	public void setIntlCangwei(String intlCangwei) {
		this.intlCangwei = intlCangwei;
	}

	@Column(name = "INTL_VOYAGE_STATUS", length = 5)
	public String getIntlVoyageStatus() {
		return this.intlVoyageStatus;
	}

	public void setIntlVoyageStatus(String intlVoyageStatus) {
		this.intlVoyageStatus = intlVoyageStatus;
	}

	@Column(name = "INTL_PNR", length = 10)
	public String getIntlPnr() {
		return this.intlPnr;
	}

	public void setIntlPnr(String intlPnr) {
		this.intlPnr = intlPnr;
	}
	@Column(name = "RESCHEDULE_TIMES", length = 10)
	public String getRescheduleTimes() {
		return rescheduleTimes;
	}

	public void setRescheduleTimes(String rescheduleTimes) {
		this.rescheduleTimes = rescheduleTimes;
	}
	@Column(name = "INTL_CLASSTYPE", length = 5)
	public String getIntlClassType() {
		return intlClassType;
	}

	public void setIntlClassType(String intlClassType) {
		this.intlClassType = intlClassType;
	}
	@Column(name = "FLIGHTTYPE", precision = 22, scale = 0)
	public Integer getFlightType() {
		return flightType;
	}

	public void setFlightType(Integer flightType) {
		this.flightType = flightType;
	}
	@Column(name = "TRANSFERTYPE", precision = 22, scale = 0)
	public Integer getTransferType() {
		return transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}
	@Column(name = "ENDORSERULE", length = 4000)
	public String getEndorserule() {
		return endorserule;
	}

	public void setEndorserule(String endorserule) {
		this.endorserule = endorserule;
	}

}