package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrgInterestDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORG_INTEREST_DETAIL")
public class OrgInterestDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orgid;
	private Double interest;
	private String orderno;
	private Date creattime;
	private Integer type;

	// Constructors

	/** default constructor */
	public OrgInterestDetail() {
	}

	/** minimal constructor */
	public OrgInterestDetail(Long orgid) {
		this.orgid = orgid;
	}

	/** full constructor */
	public OrgInterestDetail(Long orgid, Double interest, String orderno,
			Date creattime, Integer type) {
		this.orgid = orgid;
		this.interest = interest;
		this.orderno = orderno;
		this.creattime = creattime;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORGID", nullable = false, precision = 12, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "INTEREST", precision = 10)
	public Double getInterest() {
		return this.interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	@Column(name = "ORDERNO", length = 100)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "CREATTIME", length = 7)
	public Date getCreattime() {
		return this.creattime;
	}

	public void setCreattime(Date creattime) {
		this.creattime = creattime;
	}

	@Column(name = "TYPE", precision = 2, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}