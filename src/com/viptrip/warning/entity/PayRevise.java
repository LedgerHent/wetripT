package com.viptrip.warning.entity;

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
 * PayRevise entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PAY_REVISE")
public class PayRevise implements java.io.Serializable {

	// Fields

	private Long id;
	private Long orgid;
	private Long requestUserid;
	private String requestUsername;
	private String requestComment;
	private Date requestTime;
	private Long approveUserid;
	private String approveUsername;
	private Integer approveResult;
	private String approveComment;
	private Date approveTime;
	private Integer state;

	// Constructors

	/** default constructor */
	public PayRevise() {
	}

	/** minimal constructor */
	public PayRevise(Long orgid) {
		this.orgid = orgid;
	}

	/** full constructor */
	public PayRevise(Long orgid, Long requestUserid, String requestUsername,
			String requestComment, Date requestTime, Long approveUserid,
			String approveUsername, Integer approveResult,
			String approveComment, Date approveTime, Integer state) {
		this.orgid = orgid;
		this.requestUserid = requestUserid;
		this.requestUsername = requestUsername;
		this.requestComment = requestComment;
		this.requestTime = requestTime;
		this.approveUserid = approveUserid;
		this.approveUsername = approveUsername;
		this.approveResult = approveResult;
		this.approveComment = approveComment;
		this.approveTime = approveTime;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PAY_REVISE")
	@SequenceGenerator(name="SEQ_PAY_REVISE", allocationSize=1,initialValue=1,sequenceName="SEQ_PAY_REVISE")
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ORGID", nullable = false, precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "REQUEST_USERID", precision = 10, scale = 0)
	public Long getRequestUserid() {
		return this.requestUserid;
	}

	public void setRequestUserid(Long requestUserid) {
		this.requestUserid = requestUserid;
	}

	@Column(name = "REQUEST_USERNAME", length = 100)
	public String getRequestUsername() {
		return this.requestUsername;
	}

	public void setRequestUsername(String requestUsername) {
		this.requestUsername = requestUsername;
	}

	@Column(name = "REQUEST_COMMENT", length = 2000)
	public String getRequestComment() {
		return this.requestComment;
	}

	public void setRequestComment(String requestComment) {
		this.requestComment = requestComment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REQUEST_TIME", length = 7)
	public Date getRequestTime() {
		return this.requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	@Column(name = "APPROVE_USERID", precision = 10, scale = 0)
	public Long getApproveUserid() {
		return this.approveUserid;
	}

	public void setApproveUserid(Long approveUserid) {
		this.approveUserid = approveUserid;
	}

	@Column(name = "APPROVE_USERNAME", length = 100)
	public String getApproveUsername() {
		return this.approveUsername;
	}

	public void setApproveUsername(String approveUsername) {
		this.approveUsername = approveUsername;
	}

	@Column(name = "APPROVE_RESULT", precision = 1, scale = 0)
	public Integer getApproveResult() {
		return this.approveResult;
	}

	public void setApproveResult(Integer approveResult) {
		this.approveResult = approveResult;
	}

	@Column(name = "APPROVE_COMMENT", length = 2000)
	public String getApproveComment() {
		return this.approveComment;
	}

	public void setApproveComment(String approveComment) {
		this.approveComment = approveComment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPROVE_TIME", length = 7)
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "STATE", precision = 1, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

}