package com.viptrip.wetrip.model.policy;

import java.util.List;

public class HATPolicyModel {
	public Integer busnessType;
	public Integer state;
	public Integer show;
	public Integer book;
	public Integer audit;
	public Integer policyDefault;
	public List<HATRules> rules;
	public Integer getBusnessType() {
		return busnessType;
	}
	public void setBusnessType(Integer busnessType) {
		this.busnessType = busnessType;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getShow() {
		return show;
	}
	public void setShow(Integer show) {
		this.show = show;
	}
	public Integer getBook() {
		return book;
	}
	public void setBook(Integer book) {
		this.book = book;
	}
	public Integer getAudit() {
		return audit;
	}
	public void setAudit(Integer audit) {
		this.audit = audit;
	}
	public Integer getPolicyDefault() {
		return policyDefault;
	}
	public void setPolicyDefault(Integer policyDefault) {
		this.policyDefault = policyDefault;
	}
	public List<HATRules> getRules() {
		return rules;
	}
	public void setRules(List<HATRules> rules) {
		this.rules = rules;
	}
	
}
