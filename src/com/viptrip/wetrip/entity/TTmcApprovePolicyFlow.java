package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(TTmcApprovePolicyFlow.class)
@Table(name="T_TMC_APPROVE_FLOW")
public class TTmcApprovePolicyFlow implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4507613889149561523L;
	private Long apprPolicyId;
	private Long apprLevel;
	private Long flowId;
	private Long approveUserId;
	private String approveUserName;
	private String approveUserMobile;
	private String approveUserEmail;
	
	
	@Id
	@Column(name="APPROVE_POLICY_ID",length=12)
	public Long getApprPolicyId() {
		return apprPolicyId;
	}
	public void setApprPolicyId(Long apprPolicyId) {
		this.apprPolicyId = apprPolicyId;
	}
	
	@Id
	@Column(name="APPROVE_LEVEL",length=5)
	public Long getApprLevel() {
		return apprLevel;
	}
	public void setApprLevel(Long apprLevel) {
		this.apprLevel = apprLevel;
	}
	
	@Id
	@Column(name="FLOWID",length=5)
	public Long getFlowId() {
		return flowId;
	}
	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
	
	@Column(name="APPROVE_USER_ID",length=12)
	public Long getApproveUserId() {
		return approveUserId;
	}
	public void setApproveUserId(Long approveUserId) {
		this.approveUserId = approveUserId;
	}
	
	@Column(name="APPROVE_USER_NAME",length=200)
	public String getApproveUserName() {
		return approveUserName;
	}
	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}
	
	@Column(name="APPROVE_USER_MOBILE",length=20)
	public String getApproveUserMobile() {
		return approveUserMobile;
	}
	public void setApproveUserMobile(String approveUserMobile) {
		this.approveUserMobile = approveUserMobile;
	}
	
	@Column(name="APPROVE_USER_EMAIL",length=200)
	public String getApproveUserEmail() {
		return approveUserEmail;
	}
	public void setApproveUserEmail(String approveUserEmail) {
		this.approveUserEmail = approveUserEmail;
	}
	
	

}
