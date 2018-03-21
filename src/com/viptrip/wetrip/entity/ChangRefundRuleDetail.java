package com.viptrip.wetrip.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ChangRefundRuleDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHANG_REFUND_RULE_DETAIL")
public class ChangRefundRuleDetail implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 259517316977010497L;

	// Fields

	private ChangRefundRuleDetailId id;
	private int minuteLowerLimit;  //开始时间
	private int minuteUpperLimit;  //结束时间
	
	private int state;             //允许退改 1允许；2不允许；
	private int freeTimes;         //免费退改次数 -1表示不限
	private int costPercent;       //退改费用(%)
	

	// Constructors

	/** default constructor */
	public ChangRefundRuleDetail() {
	}

	/** full constructor */
	public ChangRefundRuleDetail(ChangRefundRuleDetailId id, int minuteLowerLimit,
			int minuteUpperLimit, int state,
			int freeTimes, int costPercent) {
		this.id = id;
		this.minuteLowerLimit = minuteLowerLimit;
		this.minuteUpperLimit = minuteUpperLimit;
		this.state = state;
		this.freeTimes = freeTimes;
		this.costPercent = costPercent;
		
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "ruleid", column = @Column(name = "RULEID", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 6, scale = 0)) })
	public ChangRefundRuleDetailId getId() {
		return this.id;
	}

	public void setId(ChangRefundRuleDetailId id) {
		this.id = id;
	}

	@Column(name = "MINUTE_LOWER_LIMIT", nullable = false, precision = 6, scale = 0)
	public int getMinuteLowerLimit() {
		return this.minuteLowerLimit;
	}

	public void setMinuteLowerLimit(int minuteLowerLimit) {
		this.minuteLowerLimit = minuteLowerLimit;
	}

	@Column(name = "MINUTE_UPPER_LIMIT", nullable = false, precision = 6, scale = 0)
	public int getMinuteUpperLimit() {
		return this.minuteUpperLimit;
	}

	public void setMinuteUpperLimit(int minuteUpperLimit) {
		this.minuteUpperLimit = minuteUpperLimit;
	}

	@Column(name = "STATE", nullable = false, precision = 6, scale = 0)
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Column(name = "FREE_TIMES", nullable = false, precision = 6, scale = 0)
	public int getFreeTimes() {
		return this.freeTimes;
	}

	public void setFreeTimes(int freeTimes) {
		this.freeTimes = freeTimes;
	}

	@Column(name = "COST_PERCENT", nullable = false, precision = 6, scale = 0)
	public int getCostPercent() {
		return this.costPercent;
	}

	public void setCostPercent(int costPercent) {
		this.costPercent = costPercent;
	}


}