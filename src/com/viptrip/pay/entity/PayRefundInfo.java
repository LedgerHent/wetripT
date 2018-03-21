package com.viptrip.pay.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * PayInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_REFUND_INFO")
public class PayRefundInfo implements java.io.Serializable {

	// Fields
	private Long id;
	private Long payId;
	private String refundId;
	private Integer changeFlag;
	private Integer refundAmount;
	private Date refundTime;

	// Constructors

	/** default constructor */
	public PayRefundInfo() {
	}

	public PayRefundInfo(Long payId, String refundId, Integer changeFlag, Integer refundAmount, Date refundTime) {
		this.payId = payId;
		this.refundId = refundId;
		this.changeFlag = changeFlag;
		this.refundAmount = refundAmount;
		this.refundTime = refundTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_REFUND_INFO")
	@SequenceGenerator(name="SEQ_PAY_REFUND_INFO", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_REFUND_INFO")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "PAY_ID", nullable = false, precision = 8, scale = 0)
	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}

	@Column(name = "REFUND_ID", nullable = false, length = 50)
	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	@Column(name = "CHANGE_FLAG", nullable = false, precision = 1, scale = 0)
	public Integer getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}

	@Column(name = "REFUND_AMOUNT", nullable = false, precision = 8, scale = 0)
	public Integer getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Column(name = "REFUND_TIME")
	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}
}