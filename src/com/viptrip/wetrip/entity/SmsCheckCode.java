package com.viptrip.wetrip.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * SmsCheckCode entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SMS_CHECK_CODE")
public class SmsCheckCode implements java.io.Serializable {

	// Fields

	private SmsCheckCodeId id;
	private Long userid;
	private Long smsType;
	private String smsCode;
	private Long smsAgent;
	private String extend;
	private Timestamp sendTime;
	private Timestamp validTime;
	private BigDecimal sid;
	private Long reportState;
	private Long state;

	// Constructors

	/** default constructor */
	public SmsCheckCode() {
	}

	/** minimal constructor */
	public SmsCheckCode(SmsCheckCodeId id, Long userid, Long smsType,
			String smsCode) {
		this.id = id;
		this.userid = userid;
		this.smsType = smsType;
		this.smsCode = smsCode;
	}

	/** full constructor */
	public SmsCheckCode(SmsCheckCodeId id, Long userid, Long smsType,
			String smsCode, Long smsAgent, String extend, Timestamp sendTime,
			Timestamp validTime, BigDecimal sid, Long reportState, Long state) {
		this.id = id;
		this.userid = userid;
		this.smsType = smsType;
		this.smsCode = smsCode;
		this.smsAgent = smsAgent;
		this.extend = extend;
		this.sendTime = sendTime;
		this.validTime = validTime;
		this.sid = sid;
		this.reportState = reportState;
		this.state = state;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "mobile", column = @Column(name = "MOBILE", nullable = false, length = 20)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 10, scale = 0)) })
	public SmsCheckCodeId getId() {
		return this.id;
	}

	public void setId(SmsCheckCodeId id) {
		this.id = id;
	}

	@Column(name = "USERID", nullable = false, precision = 10, scale = 0)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "SMS_TYPE", nullable = false, precision = 10, scale = 0)
	public Long getSmsType() {
		return this.smsType;
	}

	public void setSmsType(Long smsType) {
		this.smsType = smsType;
	}

	@Column(name = "SMS_CODE", nullable = false, length = 20)
	public String getSmsCode() {
		return this.smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	@Column(name = "SMS_AGENT", precision = 10, scale = 0)
	public Long getSmsAgent() {
		return this.smsAgent;
	}

	public void setSmsAgent(Long smsAgent) {
		this.smsAgent = smsAgent;
	}

	@Column(name = "EXTEND", length = 2000)
	public String getExtend() {
		return this.extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@Column(name = "SEND_TIME", length = 7)
	public Timestamp getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "VALID_TIME", length = 7)
	public Timestamp getValidTime() {
		return this.validTime;
	}

	public void setValidTime(Timestamp validTime) {
		this.validTime = validTime;
	}

	@Column(name = "SID", precision = 22, scale = 0)
	public BigDecimal getSid() {
		return this.sid;
	}

	public void setSid(BigDecimal sid) {
		this.sid = sid;
	}

	@Column(name = "REPORT_STATE", precision = 10, scale = 0)
	public Long getReportState() {
		return this.reportState;
	}

	public void setReportState(Long reportState) {
		this.reportState = reportState;
	}

	@Column(name = "STATE", precision = 10, scale = 0)
	public Long getState() {
		return this.state;
	}

	public void setState(Long state) {
		this.state = state;
	}

}