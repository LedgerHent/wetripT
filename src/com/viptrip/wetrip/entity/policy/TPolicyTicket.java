package com.viptrip.wetrip.entity.policy;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TPolicyTicket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_POLICY_TICKET")
public class TPolicyTicket implements java.io.Serializable {

	// Fields

	private TPolicyTicketId id;
	private Long policyType;
	private Long orgRuleType;
	private Long upperLimit;
	private Long lowerLimit;
	private Long cabingRade;
	private String cabinDesc;
	private String cabinRules;
	private String companyRules;
	private String airlineRules;
	private String flightRules;
	private String orgRules;
	private String operator;
	private Date updatetime;

	// Constructors

	/** default constructor */
	public TPolicyTicket() {
	}

	/** minimal constructor */
	public TPolicyTicket(TPolicyTicketId id) {
		this.id = id;
	}

	/** full constructor */
	public TPolicyTicket(TPolicyTicketId id, Long policyType,
			Long orgRuleType, Long upperLimit,
			Long lowerLimit, Long cabingRade, String cabinDesc,
			String cabinRules, String companyRules, String airlineRules,
			String flightRules, String orgRules, String operator,
			Timestamp updatetime) {
		this.id = id;
		this.policyType = policyType;
		this.orgRuleType = orgRuleType;
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.cabingRade = cabingRade;
		this.cabinDesc = cabinDesc;
		this.cabinRules = cabinRules;
		this.companyRules = companyRules;
		this.airlineRules = airlineRules;
		this.flightRules = flightRules;
		this.orgRules = orgRules;
		this.operator = operator;
		this.updatetime = updatetime;
	}

	// Property accessors
	@EmbeddedId
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_T_POLICY_TICKET")
	@SequenceGenerator(name="SEQ_T_POLICY_TICKET",allocationSize=1,initialValue=1, sequenceName="SEQ_T_POLICY_TICKET")
	@AttributeOverrides({
			@AttributeOverride(name = "orgid", column = @Column(name = "ORGID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 22, scale = 0)) })
	public TPolicyTicketId getId() {
		return this.id;
	}

	public void setId(TPolicyTicketId id) {
		this.id = id;
	}

	@Column(name = "POLICY_TYPE", precision = 22, scale = 0)
	public Long getPolicyType() {
		return this.policyType;
	}

	public void setPolicyType(Long policyType) {
		this.policyType = policyType;
	}

	@Column(name = "ORG_RULE_TYPE", precision = 22, scale = 0)
	public Long getOrgRuleType() {
		return this.orgRuleType;
	}

	public void setOrgRuleType(Long orgRuleType) {
		this.orgRuleType = orgRuleType;
	}

	@Column(name = "UPPER_LIMIT", precision = 22, scale = 0)
	public Long getUpperLimit() {
		return this.upperLimit;
	}

	public void setUpperLimit(Long upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Column(name = "LOWER_LIMIT", precision = 22, scale = 0)
	public Long getLowerLimit() {
		return this.lowerLimit;
	}

	public void setLowerLimit(Long lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Column(name = "CABING_RADE", precision = 22, scale = 0)
	public Long getCabingRade() {
		return this.cabingRade;
	}

	public void setCabingRade(Long cabingRade) {
		this.cabingRade = cabingRade;
	}

	@Column(name = "CABIN_DESC", length = 200)
	public String getCabinDesc() {
		return this.cabinDesc;
	}

	public void setCabinDesc(String cabinDesc) {
		this.cabinDesc = cabinDesc;
	}

	@Column(name = "CABIN_RULES", length = 4000)
	public String getCabinRules() {
		return this.cabinRules;
	}

	public void setCabinRules(String cabinRules) {
		this.cabinRules = cabinRules;
	}

	@Column(name = "COMPANY_RULES", length = 4000)
	public String getCompanyRules() {
		return this.companyRules;
	}

	public void setCompanyRules(String companyRules) {
		this.companyRules = companyRules;
	}

	@Column(name = "AIRLINE_RULES", length = 4000)
	public String getAirlineRules() {
		return this.airlineRules;
	}

	public void setAirlineRules(String airlineRules) {
		this.airlineRules = airlineRules;
	}

	@Column(name = "FLIGHT_RULES", length = 4000)
	public String getFlightRules() {
		return this.flightRules;
	}

	public void setFlightRules(String flightRules) {
		this.flightRules = flightRules;
	}

	@Column(name = "ORG_RULES", length = 4000)
	public String getOrgRules() {
		return this.orgRules;
	}

	public void setOrgRules(String orgRules) {
		this.orgRules = orgRules;
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