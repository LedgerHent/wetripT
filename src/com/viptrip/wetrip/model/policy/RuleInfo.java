package com.viptrip.wetrip.model.policy;


public class RuleInfo {

	
	private RuleId id;
    private Integer ruletype;
    private String rules;
    private Double upperLimit;
    private Double lowerLimit;
	public RuleId getId() {
		return id;
	}
	public void setId(RuleId id) {
		this.id = id;
	}
	public Integer getRuletype() {
		return ruletype;
	}
	public void setRuletype(Integer ruletype) {
		this.ruletype = ruletype;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public Double getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(Double upperLimit) {
		this.upperLimit = upperLimit;
	}
	public Double getLowerLimit() {
		return lowerLimit;
	}
	public void setLowerLimit(Double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}
    
}
