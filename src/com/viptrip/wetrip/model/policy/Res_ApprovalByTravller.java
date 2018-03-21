package com.viptrip.wetrip.model.policy;

public class Res_ApprovalByTravller {

	public Integer id;
	public String name;
	public String desc;
	public Integer businessType;
	public Integer approvalType;
	public Integer matchType;
	public Integer matchValue;
	public String matchName;
	
	public Integer getMatchType() {
		return matchType;
	}
	public void setMatchType(Integer matchType) {
		this.matchType = matchType;
	}
	public Integer getMatchValue() {
		return matchValue;
	}
	public void setMatchValue(Integer matchValue) {
		this.matchValue = matchValue;
	}
	public String getMatchName() {
		return matchName;
	}
	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getBusinessType() {
		return businessType;
	}
	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}
	public Integer getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	
}
