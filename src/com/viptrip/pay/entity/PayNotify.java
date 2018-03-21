package com.viptrip.pay.entity;

import com.viptrip.pay.task.Notify;

import javax.persistence.*;
import java.util.Date;

/**
 * PayNotify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_NOTIFY")
public class PayNotify implements java.io.Serializable,Notify {

	// Fields
	private Long id;
	private String orderno;
	private Integer type;
	private String url;
	private Integer status;
	private Integer count;
	private Date sendTime;
	private Integer sending;

	// Constructors

	/** default constructor */
	public PayNotify() {
		this.status = 0;
		this.count = 1;
		this.sending = 0;
	}

	/** minimal constructor */
	public PayNotify(String orderno, Integer type, String url) {
		this();
		this.orderno = orderno;
		this.type = type;
		this.url = url;
	}

	/** full constructor */
	public PayNotify(String orderno, Integer type, String url,
					 Integer status, Integer count, Date sendTime, Integer sending) {
		this.orderno = orderno;
		this.type = type;
		this.url = url;
		this.status = status;
		this.count = count;
		this.sendTime = sendTime;
		this.sending = sending;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_NOTIFY")
	@SequenceGenerator(name="SEQ_PAY_NOTIFY", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_NOTIFY")
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

	@Column(name = "TYPE", nullable = false, precision = 1, scale = 0)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "URL", nullable = false, length = 1000)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "STATUS", precision = 1, scale = 0)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "COUNT", precision = 2, scale = 0)
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "SEND_TIME", length = 7)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "SENDING", precision = 1, scale = 0)
	public Integer getSending() {
		return this.sending;
	}

	public void setSending(Integer sending) {
		this.sending = sending;
	}

	public int nextCount() {
		this.count += 1;
		return this.count;
	}

}