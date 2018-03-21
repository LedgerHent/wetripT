package com.viptrip.common.model;

import java.io.Serializable;

public class ApproveAuditModel extends ApproveAuditorModel {	
	public Integer state;
	public String memo;
	public Integer type;
	public String datetime;
	public ApproveAuditorModel operator;
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public ApproveAuditorModel getOperator() {
		return operator;
	}
	public void setOperator(ApproveAuditorModel operator) {
		this.operator = operator;
	}
	
	
}
