package com.viptrip.wetrip.entity.policy;

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
 * TOrgPolicyTicketManage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ORG_POLICY_TICKET_MANAGE")
public class TOrgPolicyTicketManage implements java.io.Serializable {

	// Fields

	private Long orgid;
	private Long policyControl;
	private Long overproofBook;
	private Long overproofAudit;
	private Long minPriceControl;
	private Long frontHours;
	private Long backHours;
	private Long preBookDays;
	private Long policyType;
	private String operator;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public TOrgPolicyTicketManage() {
	}

	/** minimal constructor */
	public TOrgPolicyTicketManage(Long policyControl) {
		this.policyControl = policyControl;
	}

	/** full constructor */
	public TOrgPolicyTicketManage(Long policyControl,
			Long overproofBook, Long overproofAudit,
			Long minPriceControl, Long frontHours,
			Long backHours, Long preBookDays,
			Long policyType, String operator, Timestamp updatetime) {
		this.policyControl = policyControl;
		this.overproofBook = overproofBook;
		this.overproofAudit = overproofAudit;
		this.minPriceControl = minPriceControl;
		this.frontHours = frontHours;
		this.backHours = backHours;
		this.preBookDays = preBookDays;
		this.policyType = policyType;
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

	/**
	 * 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
	 * @return
	 */
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

	@Column(name = "MIN_PRICE_CONTROL", precision = 22, scale = 0)
	public Long getMinPriceControl() {
		return this.minPriceControl;
	}

	public void setMinPriceControl(Long minPriceControl) {
		this.minPriceControl = minPriceControl;
	}

	@Column(name = "FRONT_HOURS", precision = 22, scale = 0)
	public Long getFrontHours() {
		return this.frontHours;
	}

	public void setFrontHours(Long frontHours) {
		this.frontHours = frontHours;
	}

	@Column(name = "BACK_HOURS", precision = 22, scale = 0)
	public Long getBackHours() {
		return this.backHours;
	}

	public void setBackHours(Long backHours) {
		this.backHours = backHours;
	}

	@Column(name = "PRE_BOOK_DAYS", precision = 22, scale = 0)
	public Long getPreBookDays() {
		return this.preBookDays;
	}

	public void setPreBookDays(Long preBookDays) {
		this.preBookDays = preBookDays;
	}

	@Column(name = "POLICY_TYPE", precision = 22, scale = 0)
	public Long getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(Long policyType) {
		this.policyType = policyType;
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

}