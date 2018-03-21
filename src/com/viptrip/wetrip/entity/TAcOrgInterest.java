package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_AC_ORG_INTEREST")
public class TAcOrgInterest {
	private Long orgid;
	private Double balance;
	private Double interest;
	private Double interestPolicy;

	// Constructors

	/** default constructor */
	public TAcOrgInterest() {
	}

	/** full constructor */
	public TAcOrgInterest(Long orgid,Double balance,Double interest,Double interestPolicy) {
		this.orgid = orgid;
		this.orgid = orgid;
		this.balance = balance;
		this.interest = interest;
		this.interestPolicy = interestPolicy;
	}
	
	@Id
	@Column(name = "ORGID",unique = true, nullable = false,  precision = 12, scale = 0)
	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	@Column(name = "BALANCE", precision = 10)
	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
	@Column(name = "INTEREST", precision = 10)
	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}
	@Column(name = "INTEREST_POLICY", precision = 10)
	public Double getInterestPolicy() {
		return interestPolicy;
	}

	public void setInterestPolicy(Double interestPolicy) {
		this.interestPolicy = interestPolicy;
	}
	
}
