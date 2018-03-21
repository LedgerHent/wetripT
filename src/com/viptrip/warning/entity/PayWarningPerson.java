package com.viptrip.warning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * PayWarningPerson entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_WARNING_PERSON")
public class PayWarningPerson implements java.io.Serializable {

	// Fields

	private Long id;
	private Long userid;
	private Long orgid;
	private String pname;
	private Integer method;
	private String mobile;
	private String email;

	// Constructors

	/** default constructor */
	public PayWarningPerson() {
	}

	/** minimal constructor */
	public PayWarningPerson(Long orgid) {
		this.orgid = orgid;
	}

	/** full constructor */
	public PayWarningPerson(Long userid, Long orgid, String pname, Integer method,
			String mobile, String email) {
		this.userid = userid;
		this.orgid = orgid;
		this.pname = pname;
		this.method = method;
		this.mobile = mobile;
		this.email = email;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_WARNING_PERSON")
	@SequenceGenerator(name="SEQ_PAY_WARNING_PERSON", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_WARNING_PERSON")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "USERID", nullable = false, precision = 10, scale = 0)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "ORGID", nullable = false, precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "PNAME", length = 200)
	public String getPname() {
		return this.pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Column(name = "METHOD", precision = 1, scale = 0)
	public Integer getMethod() {
		return this.method;
	}

	public void setMethod(Integer method) {
		this.method = method;
	}

	@Column(name = "MOBILE", length = 11)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "EMAIL", length = 200)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}