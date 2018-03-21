package com.viptrip.common.entity;

// Generated 2017-11-7 18:29:51 by Hibernate Tools 4.3.1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TTmcApproveFlow generated by hbm2java
 */
@Entity
@Table(name = "T_TMC_APPROVE_FLOW")
public class TTmcApproveFlow implements java.io.Serializable {

	private TTmcApproveFlowId id;
	private Long approveUserId;
	private String approveUserName;
	private String approveUserMobile;
	private String approveUserEmail;

	public TTmcApproveFlow() {
	}

	public TTmcApproveFlow(TTmcApproveFlowId id) {
		this.id = id;
	}

	public TTmcApproveFlow(TTmcApproveFlowId id, Long approveUserId,
			String approveUserName, String approveUserMobile,
			String approveUserEmail) {
		this.id = id;
		this.approveUserId = approveUserId;
		this.approveUserName = approveUserName;
		this.approveUserMobile = approveUserMobile;
		this.approveUserEmail = approveUserEmail;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "approveLevel", column = @Column(name = "APPROVE_LEVEL", nullable = false, precision = 5, scale = 0)),
			@AttributeOverride(name = "approvePolicyId", column = @Column(name = "APPROVE_POLICY_ID", nullable = false, precision = 12, scale = 0)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 5, scale = 0)) })
	public TTmcApproveFlowId getId() {
		return this.id;
	}

	public void setId(TTmcApproveFlowId id) {
		this.id = id;
	}

	@Column(name = "APPROVE_USER_ID", precision = 12, scale = 0)
	public Long getApproveUserId() {
		return this.approveUserId;
	}

	public void setApproveUserId(Long approveUserId) {
		this.approveUserId = approveUserId;
	}

	@Column(name = "APPROVE_USER_NAME", length = 200)
	public String getApproveUserName() {
		return this.approveUserName;
	}

	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}

	@Column(name = "APPROVE_USER_MOBILE", length = 20)
	public String getApproveUserMobile() {
		return this.approveUserMobile;
	}

	public void setApproveUserMobile(String approveUserMobile) {
		this.approveUserMobile = approveUserMobile;
	}

	@Column(name = "APPROVE_USER_EMAIL", length = 200)
	public String getApproveUserEmail() {
		return this.approveUserEmail;
	}

	public void setApproveUserEmail(String approveUserEmail) {
		this.approveUserEmail = approveUserEmail;
	}

}
