package com.viptrip.common.entity;

// Generated 2017-11-7 18:29:51 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TTmcApproveRecord generated by hbm2java
 */
@Entity
@Table(name = "T_TMC_APPROVE_RECORD")
public class TTmcApproveRecord implements java.io.Serializable {

	private TTmcApproveRecordId id;
	private Integer approveFlowid;
	private Integer approveType;
	private Long operatorId;
	private String operatorName;
	private String operatorMobile;
	private String operatorEmail;
	private Integer state;
	private String memo;
	private Date approveTime;

	public TTmcApproveRecord() {
	}

	public TTmcApproveRecord(TTmcApproveRecordId id) {
		this.id = id;
	}

	public TTmcApproveRecord(TTmcApproveRecordId id, Integer approveFlowid,
			Integer approveType, Long operatorId, String operatorName,
			String operatorMobile, String operatorEmail, Integer state,
			String memo, Date approveTime) {
		this.id = id;
		this.approveFlowid = approveFlowid;
		this.approveType = approveType;
		this.operatorId = operatorId;
		this.operatorName = operatorName;
		this.operatorMobile = operatorMobile;
		this.operatorEmail = operatorEmail;
		this.state = state;
		this.memo = memo;
		this.approveTime = approveTime;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "approveLevel", column = @Column(name = "APPROVE_LEVEL", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "approveProcessId", column = @Column(name = "APPROVE_PROCESS_ID", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 5, scale = 0)) })
	public TTmcApproveRecordId getId() {
		return this.id;
	}

	public void setId(TTmcApproveRecordId id) {
		this.id = id;
	}

	@Column(name = "APPROVE_FLOWID", precision = 5, scale = 0)
	public Integer getApproveFlowid() {
		return this.approveFlowid;
	}

	public void setApproveFlowid(Integer approveFlowid) {
		this.approveFlowid = approveFlowid;
	}

	@Column(name = "APPROVE_TYPE", precision = 5, scale = 0)
	public Integer getApproveType() {
		return this.approveType;
	}

	public void setApproveType(Integer approveType) {
		this.approveType = approveType;
	}

	@Column(name = "OPERATOR_ID", precision = 12, scale = 0)
	public Long getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@Column(name = "OPERATOR_NAME", length = 200)
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "OPERATOR_MOBILE", length = 20)
	public String getOperatorMobile() {
		return this.operatorMobile;
	}

	public void setOperatorMobile(String operatorMobile) {
		this.operatorMobile = operatorMobile;
	}

	@Column(name = "OPERATOR_EMAIL", length = 200)
	public String getOperatorEmail() {
		return this.operatorEmail;
	}

	public void setOperatorEmail(String operatorEmail) {
		this.operatorEmail = operatorEmail;
	}

	@Column(name = "STATE", precision = 5, scale = 0)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "MEMO", length = 2000)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "APPROVE_TIME", length = 7)
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

}
