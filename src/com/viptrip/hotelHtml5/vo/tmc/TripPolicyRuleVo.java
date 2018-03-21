package com.viptrip.hotelHtml5.vo.tmc;

import java.io.Serializable;

public class TripPolicyRuleVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6897189826166355875L;
	
	/**
	 * 规则主键id
	 */
	private String dbid;
	/**
	 * 差旅规则
	 */
	private String ruleType;
	/**
	 * 适用对象ID
	 */
	private String applyObjId;
	/**
	 * 适用对象ID
	 */
	private String applyObjName;
	/**
	 * 差旅政策ID
	 */
	private String tripPolicyId;
	/**
	 * 差旅政策名称
	 */
	private String policyamNe;
	/**
	 * 提醒方式
	 */
	private String remindType;
	/**
	 * 金额限制  0为不限制
	 */
	private Double priceLimit;
	private Integer starLimit;
	/**
	 * 差旅政策明细ID
	 */
	private String tripDetaiId;
	
	/**
	 * 订单填写页面的入住人或者部门（用于差旅政策超标描述中的对象）
	 */
	private String applyObjNameDesc;
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getApplyObjId() {
		return applyObjId;
	}
	public void setApplyObjId(String applyObjId) {
		this.applyObjId = applyObjId;
	}
	public String getTripPolicyId() {
		return tripPolicyId;
	}
	public void setTripPolicyId(String tripPolicyId) {
		this.tripPolicyId = tripPolicyId;
	}
	public String getPolicyamNe() {
		return policyamNe;
	}
	public void setPolicyamNe(String policyamNe) {
		this.policyamNe = policyamNe;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	public Double getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(Double priceLimit) {
		this.priceLimit = priceLimit;
	}
	public Integer getStarLimit() {
		return starLimit;
	}
	public void setStarLimit(Integer starLimit) {
		this.starLimit = starLimit;
	}
	public String getApplyObjName() {
		return applyObjName;
	}
	public void setApplyObjName(String applyObjName) {
		this.applyObjName = applyObjName;
	}
	public String getApplyObjNameDesc() {
		return applyObjNameDesc;
	}
	public void setApplyObjNameDesc(String applyObjNameDesc) {
		this.applyObjNameDesc = applyObjNameDesc;
	}
	public String getTripDetaiId() {
		return tripDetaiId;
	}
	public void setTripDetaiId(String tripDetaiId) {
		this.tripDetaiId = tripDetaiId;
	}

}
