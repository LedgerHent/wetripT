package com.viptrip.warning.entity;

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
 * PayLimitation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_LIMITATION")
public class PayLimitation implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orgid;
	private Integer type;
	private Integer otype;
	private Integer state;
	private Double amount;
	private Double leftLimitation;
	private Date optime;
	private Date invalidTime;

	// Constructors
	public PayLimitation(){

	}

	/** default constructor */
	public PayLimitation(Long orgid, Integer type, Integer otype, Double amount, Double leftLimitation, Date invalidTime) {
		this.orgid = orgid;
		this.type = type;
		this.otype = otype;
		this.state = 1;
		this.amount = amount;
		this.leftLimitation = leftLimitation;
		this.optime = new Date();
		this.invalidTime = invalidTime;
	}

	/** minimal constructor */
	public PayLimitation(Long orgid, Integer type, Integer otype,
			Integer state, Double amount, Double leftLimitation, Date optime) {
		this.orgid = orgid;
		this.type = type;
		this.otype = otype;
		this.state = state;
		this.amount = amount;
		this.leftLimitation = leftLimitation;
		this.optime = optime;
	}

	/** full constructor */
	public PayLimitation(Long orgid, Integer type, Integer otype,
			Integer state, Double amount, Double leftLimitation, Date optime,
			Date invalidTime) {
		this.orgid = orgid;
		this.type = type;
		this.otype = otype;
		this.state = state;
		this.amount = amount;
		this.leftLimitation = leftLimitation;
		this.optime = optime;
		this.invalidTime = invalidTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_LIMITATION")
	@SequenceGenerator(name="SEQ_PAY_LIMITATION", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_LIMITATION")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORGID", nullable = false, precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "TYPE", nullable = false, precision = 1, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "OTYPE", nullable = false, precision = 2, scale = 0)
	public Integer getOtype() {
		return this.otype;
	}

	public void setOtype(Integer otype) {
		this.otype = otype;
	}

	@Column(name = "STATE", nullable = false, precision = 1, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 12, scale = 2)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "LEFT_LIMITATION", nullable = false, precision = 12, scale = 2)
	public Double getLeftLimitation() {
		return this.leftLimitation;
	}

	public void setLeftLimitation(Double leftLimitation) {
		this.leftLimitation = leftLimitation;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPTIME", nullable = false, length = 7)
	public Date getOptime() {
		return this.optime;
	}

	public void setOptime(Date optime) {
		this.optime = optime;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INVALID_TIME", length = 7)
	public Date getInvalidTime() {
		return this.invalidTime;
	}

	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
	}

}