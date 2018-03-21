package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * CHANGE_REFUND_RULE entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CHANGE_REFUND_RULE",  uniqueConstraints = @UniqueConstraint(columnNames = {
		"T_ENDORDE_ID" }))
public class ChangRefundRule implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 263097059904919347L;
	// Fields

	private Long ruleid;             //ID           
	private Long tendordeId;          //关联t_endorde表i
	private int type;                //退改类型 1：退；2：改 
	private int discountLowerLimit;  //最低折扣(含)      
	private int discountUpperLimit;  //最高折扣         
	private int state;               //允许退改 1允许；2不允许
	private int offsetMinute;      //误差分钟数
	private int amountLimit;       //最低收费

	
	// Constructors

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** default constructor */
	public ChangRefundRule() {
	}

	/** full constructor */
	public ChangRefundRule(Long ruleid, Long tendordeId, int type,
			int discountLowerLimit, int discountUpperLimit, int state, int offsetMinute, int amountLimit) {
		this.ruleid = ruleid;
		this.tendordeId = tendordeId;
		this.type = type;
		this.discountLowerLimit = discountLowerLimit;
		this.discountUpperLimit = discountUpperLimit;
		this.state = state;
		this.offsetMinute = offsetMinute;
		this.amountLimit = amountLimit;
	}
	// Property accessors
	@Id
	@Column(name = "RULEID", unique = true, nullable = false, precision = 12, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_CHANGE_REFUND_RULE")
	@SequenceGenerator(name="SEQ_CHANGE_REFUND_RULE",allocationSize=1,initialValue=1, sequenceName="SEQ_CHANGE_REFUND_RULE")
	public Long getRuleid() {
		return ruleid;
	}

	public void setRuleid(Long ruleid) {
		this.ruleid = ruleid;
	}
	@Column(name = "T_ENDORDE_ID", nullable = false, precision = 10, scale = 0)
	public Long getTendordeId() {
		return tendordeId;
	}

	public void setTendordeId(Long tendordeId) {
		this.tendordeId = tendordeId;
	}
	@Column(name = "TYPE", nullable = false, precision = 6, scale = 0)
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	@Column(name = "DISCOUNT_LOWER_LIMIT", nullable = false, precision = 6, scale = 0)
	public int getDiscountLowerLimit() {
		return discountLowerLimit;
	}

	public void setDiscountLowerLimit(int discountLowerLimit) {
		this.discountLowerLimit = discountLowerLimit;
	}
	@Column(name = "DISCOUNT_UPPER_LIMIT", nullable = false, precision = 6, scale = 0)
	public int getDiscountUpperLimit() {
		return discountUpperLimit;
	}

	public void setDiscountUpperLimit(int discountUpperLimit) {
		this.discountUpperLimit = discountUpperLimit;
	}
	@Column(name = "STATE", nullable = false, precision = 6, scale = 0)
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	@Column(name = "OFFSET_MINUTE", nullable = false, precision = 6, scale = 0)
	public int getOffsetMinute() {
		return this.offsetMinute;
	}

	public void setOffsetMinute(int offsetMinute) {
		this.offsetMinute = offsetMinute;
	}
	@Column(name = "AMOUNT_LIMIT", nullable = false, precision = 6, scale = 0)
	public int getAmountLimit() {
		return this.amountLimit;
	}

	public void setAmountLimit(int amountLimit) {
		this.amountLimit = amountLimit;
	}


}