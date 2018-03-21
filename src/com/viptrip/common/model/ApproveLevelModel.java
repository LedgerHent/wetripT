package com.viptrip.common.model;

import java.io.Serializable;
import java.util.List;

public class ApproveLevelModel implements Serializable {
	public Integer approveLevel;
	public Integer count;
	public Integer state;
	public List<ApproveAuditModel> auditors;
	public Integer getApproveLevel() {
		return approveLevel;
	}
	public void setApproveLevel(Integer approveLevel) {
		this.approveLevel = approveLevel;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<ApproveAuditModel> getAuditors() {
		return auditors;
	}
	public void setAuditors(List<ApproveAuditModel> auditors) {
		this.auditors = auditors;
	}
	
	
	

}
