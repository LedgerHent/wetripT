package com.viptrip.wetrip.entity;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OrgPolicyManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORG_POLICY_MANAGE")
public class OrgPolicyManage implements java.io.Serializable {

	// Fields

	private Long orgid;
	private Long policyControl;
	private Long overproofBook;
	private Long overproofAudit;
	private String memo;
	private String operator;
	private Date updatetime;
	private Long overproofShow;
	private Long overproofDefault;
	

	// Constructors

	/** default constructor */
	public OrgPolicyManage() {
	}

	/** minimal constructor */
	public OrgPolicyManage(Long policyControl) {
		this.policyControl = policyControl;
	}

	/** full constructor */
	public OrgPolicyManage(Long policyControl, Long overproofBook,
			Long overproofAudit, String memo, String operator,
			Timestamp updatetime) {
		this.policyControl = policyControl;
		this.overproofBook = overproofBook;
		this.overproofAudit = overproofAudit;
		this.memo = memo;
		this.operator = operator;
		this.updatetime = updatetime;
	}

	// Property accessors
	@Id
	@Column(name = "ORGID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "POLICY_CONTROL", nullable = false, precision = 22, scale = 0)
	public Long getPolicyControl() {
		return this.policyControl;
	}

	public void setPolicyControl(Long policyControl) {
		this.policyControl = policyControl;
	}

	@Column(name = "OVERPROOF_BOOK", precision = 22, scale = 0)
	public Long getOverproofBook() {
		return this.overproofBook;
	}

	public void setOverproofBook(Long overproofBook) {
		this.overproofBook = overproofBook;
	}

	@Column(name = "OVERPROOF_AUDIT", precision = 22, scale = 0)
	public Long getOverproofAudit() {
		return this.overproofAudit;
	}

	public void setOverproofAudit(Long overproofAudit) {
		this.overproofAudit = overproofAudit;
	}

	@Column(name = "MEMO", length = 2000)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "OPERATOR", length = 50)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "UPDATETIME", length = 7)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String saveToString() {
		return  orgid +","+ policyControl+"," + overproofBook
				+ "," + overproofAudit + "," + memo
				+ "," + operator + "," + updatetime ;
	}
	public String updateToString() {
		return  policyControl+"," + overproofBook
				+ "," + overproofAudit + "," + memo
				+ "," + operator + "," + updatetime+","+orgid;
	}
	@Column(name = "OVERPROOFSHOW", precision = 22, scale = 0)
	public Long getOverproofShow() {
		return overproofShow;
	}

	public void setOverproofShow(Long overproofShow) {
		this.overproofShow = overproofShow;
	}
	@Column(name = "OVERPROOFDEFAULT", precision = 22, scale = 0)
	public Long getOverproofDefault() {
		return overproofDefault;
	}

	public void setOverproofDefault(Long overproofDefault) {
		this.overproofDefault = overproofDefault;
	}

}