package com.viptrip.wetrip.entity;

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
 * Orderintegral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORDERINTEGRAL")
public class Orderintegral implements java.io.Serializable {

	// Fields

	private Long id;
	private String orderno;
	private Integer businesstype;
	private Long orgid;
	private Long userid;
	private Long integral;
	private Integer source;
	private Date issuedate;
	private Date expirydate;
	private Integer usestate;
	private Double money;
	private Long consumeId;
	private String orgname;

	// Constructors

	/** default constructor */
	public Orderintegral() {
	}

	/** minimal constructor */
	public Orderintegral(Long id) {
		this.id = id;
	}

	/** full constructor */
	public Orderintegral(Long id, String orderno, Integer businesstype,
			Long orgid, Long userid, Long integral, Integer source,
			Date issuedate, Date expirydate, Integer usestate, Double money,
			Long consumeId, String orgname) {
		this.id = id;
		this.orderno = orderno;
		this.businesstype = businesstype;
		this.orgid = orgid;
		this.userid = userid;
		this.integral = integral;
		this.source = source;
		this.issuedate = issuedate;
		this.expirydate = expirydate;
		this.usestate = usestate;
		this.money = money;
		this.consumeId = consumeId;
		this.orgname = orgname;
	}

	// Property accessors
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDERINTEGRAL")
	@SequenceGenerator(name = "SEQ_ORDERINTEGRAL", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_ORDERINTEGRAL")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORDERNO", length = 20)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "BUSINESSTYPE", precision = 2, scale = 0)
	public Integer getBusinesstype() {
		return this.businesstype;
	}

	public void setBusinesstype(Integer businesstype) {
		this.businesstype = businesstype;
	}

	@Column(name = "ORGID", precision = 12, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "USERID", precision = 12, scale = 0)
	public Long getUserid() {
		return this.userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "INTEGRAL", precision = 12, scale = 0)
	public Long getIntegral() {
		return this.integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	@Column(name = "SOURCE", precision = 2, scale = 0)
	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ISSUEDATE", length = 7)
	public Date getIssuedate() {
		return this.issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRYDATE", length = 7)
	public Date getExpirydate() {
		return this.expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

	@Column(name = "USESTATE", precision = 2, scale = 0)
	public Integer getUsestate() {
		return this.usestate;
	}

	public void setUsestate(Integer usestate) {
		this.usestate = usestate;
	}

	@Column(name = "MONEY", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "CONSUME_ID", precision = 12, scale = 0)
	public Long getConsumeId() {
		return this.consumeId;
	}

	public void setConsumeId(Long consumeId) {
		this.consumeId = consumeId;
	}

	@Column(name = "ORGNAME", length = 50)
	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}