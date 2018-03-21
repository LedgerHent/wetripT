package com.viptrip.wetrip.model.policy;

import java.util.Date;
import java.util.List;


public class PolicyTicketInfo {
	private Long orgid;
	private Integer ruleid;
    private Integer policycontrol;
    private Integer policyType;
    private Integer orgruletype;
    private String orgrules;
    private Integer overproofbook;
    private Integer overproofaudit;
    private Integer overproofshow;
    private Integer overproofdefault;
    private Date validdatefrom;
    private Date validdateto;
    private String operator;
    private Date updatetime;
    private Integer state;
    private String userid;
    private String username;
    private List<RuleInfo> ruleList;
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	public Integer getRuleid() {
		return ruleid;
	}
	public void setRuleid(Integer ruleid) {
		this.ruleid = ruleid;
	}
	public Integer getPolicycontrol() {
		return policycontrol;
	}
	public void setPolicycontrol(Integer policycontrol) {
		this.policycontrol = policycontrol;
	}
	public Integer getPolicyType() {
		return policyType;
	}
	public void setPolicyType(Integer policyType) {
		this.policyType = policyType;
	}
	public Integer getOrgruletype() {
		return orgruletype;
	}
	public void setOrgruletype(Integer orgruletype) {
		this.orgruletype = orgruletype;
	}
	public String getOrgrules() {
		return orgrules;
	}
	public void setOrgrules(String orgrules) {
		this.orgrules = orgrules;
	}
	public Integer getOverproofbook() {
		return overproofbook;
	}
	public void setOverproofbook(Integer overproofbook) {
		this.overproofbook = overproofbook;
	}
	public Integer getOverproofaudit() {
		return overproofaudit;
	}
	public void setOverproofaudit(Integer overproofaudit) {
		this.overproofaudit = overproofaudit;
	}
	public Integer getOverproofshow() {
		return overproofshow;
	}
	public void setOverproofshow(Integer overproofshow) {
		this.overproofshow = overproofshow;
	}
	public Integer getOverproofdefault() {
		return overproofdefault;
	}
	public void setOverproofdefault(Integer overproofdefault) {
		this.overproofdefault = overproofdefault;
	}
	public Date getValiddatefrom() {
		return validdatefrom;
	}
	public void setValiddatefrom(Date validdatefrom) {
		this.validdatefrom = validdatefrom;
	}
	public Date getValiddateto() {
		return validdateto;
	}
	public void setValiddateto(Date validdateto) {
		this.validdateto = validdateto;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<RuleInfo> getRuleList() {
		return ruleList;
	}
	public void setRuleList(List<RuleInfo> ruleList) {
		this.ruleList = ruleList;
	}
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
 
}
