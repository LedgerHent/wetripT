package com.viptrip.wetrip.model.policy;

import java.util.List;


public class ApprovalsModel {

	public Integer flowId;
	public Integer type;
	public Integer count;
	public List<ApprovalsPerson> auditors;
	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<ApprovalsPerson> getAuditors() {
		return auditors;
	}
	public void setAuditors(List<ApprovalsPerson> auditors) {
		this.auditors = auditors;
	}
	
}
