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
 * TCommonMsgBox entity.  MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_COMMON_MSG_BOX")
@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate = true)
public class TCommonMsgBox implements java.io.Serializable {

	private static final long serialVersionUID = -5980753566580706875L;
	
	// Fields
	private Long msgid;
	private String receiver; 
	private String sender;
	private Date msgtime;
	private String title;
	private String content;
	private String status;
	private String url;
	private String msgtype;//新增：消息类型，1是站内消息，2是邮件，3是短信
	private String contact;//联系方式，若为站内消息此项可空缺，若为邮件，此项填邮箱，若为短信，此项为电话号码
	private String msgstatus;//储存短信猫发送短信后返回的发送状态（判断是否发送成功）
	
	private Long receiverid; 
	private Long senderid;
	private Integer sort; 
	// Constructors



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** default constructor */
	public TCommonMsgBox() {
	}

	/** full constructor */
	public TCommonMsgBox(Long msgid, String receiver, String sender, Date msgtime,
			String title, String content, String status,String msgtype) {
		this.msgid = msgid;
		this.receiver = receiver;
		this.sender = sender;
		this.msgtime = msgtime;
		this.title = title;
		this.content = content;
		this.status = status;
		this.msgtype=msgtype;
	}

	// Property accessors
	@Id
	@Column(name = "MSGID", unique = true, nullable = false, precision = 16, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_COMMON_MSG_BOX")
	@SequenceGenerator(name="SEQ_COMMON_MSG_BOX",allocationSize=1,initialValue=1, sequenceName="SEQ_COMMON_MSG_BOX")
	public Long getMsgid() {
		return this.msgid;
	}

	public void setMsgid(Long msgid) {
		this.msgid = msgid;
	}

	@Column(name = "RECEIVER", nullable = false, length = 40)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "SENDER", nullable = false, length = 40)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MSGTIME", nullable = true, length = 7)
	public Date getMsgtime() {
		return this.msgtime;
	}

	public void setMsgtime(Date msgtime) {
		this.msgtime = msgtime;
	}

	@Column(name = "TITLE", nullable = false, length = 150)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT", nullable = false, length = 1000)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "STATUS", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Column(name = "MSGSTATUS", nullable = true, length = 1)
	public String getMsgstatus() {
		return msgstatus;
	}

	public void setMsgstatus(String msgstatus) {
		this.msgstatus = msgstatus;
	}

	@Column(name = "URL", length = 150)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	@Column(name = "MSGTYPE", nullable = false, length = 1)
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	@Column(name = "CONTACT", nullable = true, length = 200)
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name = "RECEIVERID", nullable = true, length = 16)
	public Long getReceiverid() {
		return receiverid;
	}

	public void setReceiverid(Long receiverid) {
		this.receiverid = receiverid;
	}

	@Column(name = "SENDERID", nullable = true, length = 16)
	public Long getSenderid() {
		return senderid;
	}

	public void setSenderid(Long senderid) {
		this.senderid = senderid;
	}

	@Column(name = "SORT", nullable = true, length = 10)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}