package com.viptrip.wetrip.model.policy;

import java.util.List;

public class Res_ApprovalsDetail {
	public Integer id;
	public String name;
	public String desc;
	public Integer businessType;
	public Integer type;
	public Integer approvalType;
	public List<ApprovalsModel> approvals;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getApprovalType() {
		return approvalType;
	}
	public void setApprovalType(Integer approvalType) {
		this.approvalType = approvalType;
	}
	public List<ApprovalsModel> getApprovals() {
		return approvals;
	}
	public void setApprovals(List<ApprovalsModel> approvals) {
		this.approvals = approvals;
	}
	
}
