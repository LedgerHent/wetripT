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
 * TIntlTicketTravel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_TICKET_TRAVEL")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TIntlTicketTravel implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6509691111529393899L;
	private Integer associatedid;
	private Integer orderid;
	private Integer tripid;
	private Integer rescdid;
	private String intlTicketNo;
	private String intlTicketStatus;
	private Integer rfdid;
	private Integer passid;
	private String intlIssuedName;
	private Date intlIssuedDate;
	private Date intlCreateDate;
	private String rescheduleTimes;
	private String chuPiaoStatus;
	// Constructors

	/** default constructor */
	public TIntlTicketTravel() {
	}

	/** minimal constructor */
	public TIntlTicketTravel(Integer orderid, Integer tripid,
			Integer rfdid) {
		this.orderid = orderid;
		this.tripid = tripid;
		this.rfdid = rfdid;
	}

	/** full constructor */
	public TIntlTicketTravel(Integer orderid, Integer tripid,
			Integer rescdid, String intlTicketNo, String intlTicketStatus,
			Integer rfdid,String rescheduleTimes) {
		this.orderid = orderid;
		this.tripid = tripid;
		this.rescdid = rescdid;
		this.intlTicketNo = intlTicketNo;
		this.intlTicketStatus = intlTicketStatus;
		this.rfdid = rfdid;
		this.rescheduleTimes = rescheduleTimes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_TICKET_TRAVEL")
	@SequenceGenerator(name="SEQ_INTL_TICKET_TRAVEL",allocationSize=1,initialValue=120, sequenceName="SEQ_INTL_TICKET_TRAVEL")
	@Column(name = "ASSOCIATEDID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getAssociatedid() {
		return this.associatedid;
	}

	public void setAssociatedid(Integer associatedid) {
		this.associatedid = associatedid;
	}

	@Column(name = "ORDERID", nullable = false, precision = 22, scale = 0)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "TRIPID", nullable = false, precision = 22, scale = 0)
	public Integer getTripid() {
		return this.tripid;
	}

	public void setTripid(Integer tripid) {
		this.tripid = tripid;
	}

	@Column(name = "RESCDID", precision = 22, scale = 0)
	public Integer getRescdid() {
		return this.rescdid;
	}

	public void setRescdid(Integer rescdid) {
		this.rescdid = rescdid;
	}

	@Column(name = "INTL_TICKET_NO", length = 50)
	public String getIntlTicketNo() {
		return this.intlTicketNo;
	}

	public void setIntlTicketNo(String intlTicketNo) {
		this.intlTicketNo = intlTicketNo;
	}

	@Column(name = "INTL_TICKET_STATUS", length = 5)
	public String getIntlTicketStatus() {
		return this.intlTicketStatus;
	}

	public void setIntlTicketStatus(String intlTicketStatus) {
		this.intlTicketStatus = intlTicketStatus;
	}

	@Column(name = "RFDID", precision = 22, scale = 0)
	public Integer getRfdid() {
		return this.rfdid;
	}

	public void setRfdid(Integer rfdid) {
		this.rfdid = rfdid;
	}

	@Column(name = "PASSID", nullable = false, precision = 22, scale = 0)
	public Integer getPassid() {
		return passid;
	}

	public void setPassid(Integer passid) {
		this.passid = passid;
	}

	@Column(name = "INTL_ISSUED_NAME", length = 100)
	public String getIntlIssuedName() {
		return intlIssuedName;
	}

	public void setIntlIssuedName(String intlIssuedName) {
		this.intlIssuedName = intlIssuedName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_ISSUED_DATE", length = 7)
	public Date getIntlIssuedDate() {
		return intlIssuedDate;
	}

	public void setIntlIssuedDate(Date intlIssuedDate) {
		this.intlIssuedDate = intlIssuedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_CREATE_DATE", length = 7)
	public Date getIntlCreateDate() {
		return intlCreateDate;
	}

	public void setIntlCreateDate(Date intlCreateDate) {
		this.intlCreateDate = intlCreateDate;
	}
	@Column(name = "RESCHEDULE_TIMES", length = 50)
	public String getRescheduleTimes() {
		return this.rescheduleTimes;
	}

	public void setRescheduleTimes(String rescheduleTimes) {
		this.rescheduleTimes = rescheduleTimes;
	}
	@Column(name = "CHUPIAOSTATUS", length = 5)
	public String getChuPiaoStatus() {
		return chuPiaoStatus;
	}

	public void setChuPiaoStatus(String chuPiaoStatus) {
		this.chuPiaoStatus = chuPiaoStatus;
	}
	
	

}