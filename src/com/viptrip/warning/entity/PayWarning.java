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
 * PayWarning entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_WARNING")
public class PayWarning implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -725481433703606786L;
	private Integer id;
	private Long orgid;
	private Integer type;
	private Integer method;
	private Integer status;
	private Double warningValue;
	private Date warningTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public PayWarning() {
	}

	/** minimal constructor */
	public PayWarning(Long orgid, Integer type, Integer state) {
		this.orgid = orgid;
		this.type = type;
		this.state = state;
	}

	/** full constructor */
	public PayWarning(Long orgid, Integer type, Integer method,Integer status,
			Double warningValue, Date warningTime, Integer state) {
		this.orgid = orgid;
		this.type = type;
		this.method = method;
		this.status = status;
		this.warningValue = warningValue;
		this.warningTime = warningTime;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_WARNING")
	@SequenceGenerator(name="SEQ_PAY_WARNING", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_WARNING")
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	@Column(name = "METHOD", nullable = false, precision = 1, scale = 0)
	public Integer getMethod() {
		return this.method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "WARNING_VALUE", precision = 12, scale = 2)
	public Double getWarningValue() {
		return this.warningValue;
	}

	public void setWarningValue(Double warningValue) {
		this.warningValue = warningValue;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "WARNING_TIME", length = 7)
	public Date getWarningTime() {
		return this.warningTime;
	}

	public void setWarningTime(Date warningTime) {
		this.warningTime = warningTime;
	}

	@Column(name = "STATE", nullable = false, precision = 1, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}