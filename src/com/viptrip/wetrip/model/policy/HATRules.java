package com.viptrip.wetrip.model.policy;

public class HATRules {
	public Integer traveller;
	public Long policyId;
	public String policyName;
	public Integer bookingRemindType;
	public Integer displayRemindType;
	public Integer auditRemindType;
	public String policyDetailValue;
	public Integer getTraveller() {
		return traveller;
	}
	public void setTraveller(Integer traveller) {
		this.traveller = traveller;
	}
	public Integer getBookingRemindType() {
		return bookingRemindType;
	}
	public void setBookingRemindType(Integer bookingRemindType) {
		this.bookingRemindType = bookingRemindType;
	}
	public Integer getDisplayRemindType() {
		return displayRemindType;
	}
	public void setDisplayRemindType(Integer displayRemindType) {
		this.displayRemindType = displayRemindType;
	}
	public Integer getAuditRemindType() {
		return auditRemindType;
	}
	public void setAuditRemindType(Integer auditRemindType) {
		this.auditRemindType = auditRemindType;
	}
	public String getPolicyDetailValue() {
		return policyDetailValue;
	}
	public void setPolicyDetailValue(String policyDetailValue) {
		this.policyDetailValue = policyDetailValue;
	}
	
}
