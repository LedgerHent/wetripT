package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;



@Entity
@IdClass(TTmcApprovePolicyLable.class)
@Table(name="T_TMC_APPROVE_POLICY_LABLE")
public class TTmcApprovePolicyLable implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5961214128425378758L;
	private Long  apprPolicyId;
	private Long  lableType;
	private Long  lableValue;
	private Long  matchType;
	
	
	@Id
	@Column(name="APPROVE_POLICY_ID",length=12)
	public Long getApprPolicyId() {
		return apprPolicyId;
	}
	public void setApprPolicyId(Long apprPolicyId) {
		this.apprPolicyId = apprPolicyId;
	}
	@Id
	@Column(name="LABLE_TYPE",length=5)
	public Long getLableType() {
		return lableType;
	}
	public void setLableType(Long lableType) {
		this.lableType = lableType;
	}
	@Id
	@Column(name="LABLE_VALUE",length=5)
	public Long getLableValue() {
		return lableValue;
	}
	public void setLableValue(Long lableValue) {
		this.lableValue = lableValue;
	}
	
	@Column(name="MATCH_TYPE",length=5)
	public Long getMatchType() {
		return matchType;
	}
	public void setMatchType(Long matchType) {
		this.matchType = matchType;
	}
	
	
	
	
	

}
