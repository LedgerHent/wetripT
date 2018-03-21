package com.viptrip.common.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;

public class Response_GetApprovalProcess extends Response_Base {
	public long id;	
	public String name;
	public Integer type;
	public Integer mode;
	public Integer state;
	public Integer currentApproveLevel;
	public List<ApproveLevelModel> approvals;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMode() {
		return mode;
	}
	public void setMode(Integer mode) {
		this.mode = mode;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCurrentApproveLevel() {
		return currentApproveLevel;
	}
	public void setCurrentApproveLevel(Integer currentApproveLevel) {
		this.currentApproveLevel = currentApproveLevel;
	}
	public List<ApproveLevelModel> getApprovals() {
		return approvals;
	}
	public void setApprovals(List<ApproveLevelModel> approvals) {
		this.approvals = approvals;
	}
	
	
	
}
