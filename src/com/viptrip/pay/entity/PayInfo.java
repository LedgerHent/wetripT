package com.viptrip.pay.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * PayInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_INFO")
public class PayInfo implements java.io.Serializable {

	// Fields
	private Long id;
	private String orderno;
	private Integer amount;
	private String subject;
	private String body;
	private String nonceStr;
	private String notifyUrl;
	private String returnUrl;
	private Integer type;
	private String payno;
	private String payerno;
	private Integer status;
	private Date payTime;
	private Integer refundAmount;
	private Date refundTime;

	// Constructors

	/** default constructor */
	public PayInfo() {
	}

	/** minimal constructor */
	public PayInfo(Long id,String orderno, Integer amount) {
		this.id = id;
		this.orderno = orderno;
		this.amount = amount;
	}
	
	public PayInfo(String orderno, Integer amount, String subject, String body, String nonceStr, String notifyUrl, String returnUrl, Integer type) {
		this(orderno, amount, subject, body, nonceStr, notifyUrl, returnUrl, type, null, null, null, null, null, null);
	}

	/** full constructor */
	public PayInfo(String orderno, Integer amount, String subject,
				   String body, String nonceStr, String notifyUrl, String returnUrl,
				   Integer type, String payno, String payerno, Integer status,
				   Date payTime, Integer refundAmount, Date refundTime) {
		this.orderno = orderno;
		this.amount = amount;
		this.subject = subject;
		this.body = body;
		this.nonceStr = nonceStr;
		this.notifyUrl = notifyUrl;
		this.returnUrl = returnUrl;
		this.type = type;
		this.payno = payno;
		this.payerno = payerno;
		this.status = status;
		this.payTime = payTime;
		this.refundAmount = refundAmount;
		this.refundTime = refundTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_INFO")
	@SequenceGenerator(name="SEQ_PAY_INFO", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_INFO")
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORDERNO", unique = true, nullable = false, length = 15)
	public String getOrderno() {
		return this.orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	@Column(name = "AMOUNT", nullable = false, precision = 8, scale = 0)
	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "SUBJECT", length = 256)
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(name = "BODY", length = 256)
	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Column(name = "NONCE_STR", length = 2000)
	public String getNonceStr() {
		return this.nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	@Column(name = "NOTIFY_URL", length = 1000)
	public String getNotifyUrl() {
		return this.notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Column(name = "RETURN_URL", length = 1000)
	public String getReturnUrl() {
		return this.returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	@Column(name = "TYPE", precision = 2, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "PAYNO", length = 64)
	public String getPayno() {
		return this.payno;
	}

	public void setPayno(String payno) {
		this.payno = payno;
	}

	@Column(name = "PAYERNO", length = 128)
	public String getPayerno() {
		return this.payerno;
	}

	public void setPayerno(String payerno) {
		this.payerno = payerno;
	}

	@Column(name = "STATUS", precision = 2, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "PAY_TIME", length = 7)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "REFUND_AMOUNT", precision = 8, scale = 0)
	public Integer getRefundAmount() {
		return this.refundAmount;
	}

	public void setRefundAmount(Integer refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Column(name = "REFUND_TIME", length = 7)
	public Date getRefundTime() {
		return this.refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

}