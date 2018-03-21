package com.viptrip.wetrip.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TNotifyPartyInformation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_NOTIFY_PARTY_INFORMATION")
public class TNotifyPartyInformation implements java.io.Serializable {

	// Fields

	private Integer notifyId;
	private Integer orderid;
	private String notifyName;
	private String passengerTel;
	private String mail;
	private String position;
	private String status;
	private Date sendTime;
	private String nationalityStatus;

	// Constructors

	/** default constructor */
	public TNotifyPartyInformation() {
	}

	/** minimal constructor */
	public TNotifyPartyInformation(Integer notifyId) {
		this.notifyId = notifyId;
	}

	/** full constructor */
	public TNotifyPartyInformation(Integer notifyId, Integer orderid,
			String notifyName, String passengerTel, String mail,
			String position, String status, Date sendTime,
			String nationalityStatus) {
		this.notifyId = notifyId;
		this.orderid = orderid;
		this.notifyName = notifyName;
		this.passengerTel = passengerTel;
		this.mail = mail;
		this.position = position;
		this.status = status;
		this.sendTime = sendTime;
		this.nationalityStatus = nationalityStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_T_NOTIFY_PARTY_INFORMATION")
	@SequenceGenerator(name="SEQ_T_NOTIFY_PARTY_INFORMATION",allocationSize=1,initialValue=1, sequenceName="SEQ_T_NOTIFY_PARTY_INFORMATION")
	@Column(name = "NOTIFY_ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getNotifyId() {
		return this.notifyId;
	}

	public void setNotifyId(Integer notifyId) {
		this.notifyId = notifyId;
	}

	@Column(name = "ORDERID", precision = 22, scale = 0)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "NOTIFY_NAME", length = 100)
	public String getNotifyName() {
		return this.notifyName;
	}

	public void setNotifyName(String notifyName) {
		this.notifyName = notifyName;
	}

	@Column(name = "PASSENGER_TEL", length = 20)
	public String getPassengerTel() {
		return this.passengerTel;
	}

	public void setPassengerTel(String passengerTel) {
		this.passengerTel = passengerTel;
	}

	@Column(name = "MAIL", length = 50)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "POSITION", length = 50)
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Column(name = "STATUS", length = 5)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "SEND_TIME", length = 7)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "NATIONALITY_STATUS", length = 5)
	public String getNationalityStatus() {
		return this.nationalityStatus;
	}

	public void setNationalityStatus(String nationalityStatus) {
		this.nationalityStatus = nationalityStatus;
	}

}