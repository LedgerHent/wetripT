package com.viptrip.hotelHtml5.vo.response.ro;

import com.viptrip.hotelHtml5.common.DataTypeUtil;

public class TripPolicyMatchResponseVo {
	private String tripPolicyId;
	private String tripPolicyDetailId;
	private String TripRuleId;
	
	private String ruleType; //用户、组、部门、公司
	private String applyObjId;//用户、组、部门、公司id
	private String applyObjName;//用户、组、部门、公司名称
	
	private String policyName; //策略名称
	private Double priceLimit;
	private Double starLimit;
	/**
	 * 差旅政策提醒方式
	 * "0000100001", "仅提醒"
	 * "0000100002", "禁止预订"
	 * "0000100003", "超标审批"
	 * "0000100004", "无"
	 */
	private String remindType=DataTypeUtil.TRIPPOLICY_REMIND_TYPE_4.getKey(); 
	/**
	 * 酒店展示方式
	 * 隐藏：过滤超标价格
	 * 正常显示：不显示超标话术
	 * 超标显示：显示超标话术
	 */
	private String hotelShowType=DataTypeUtil.HOTEL_SHOW_TYPE_4.getKey();
	/**
	 * 未匹配差旅政策默认值
	 * "0003100001", "超标"
	 * "0003100002", "不超标"
	 * "0003100003", "无"
	 */
	private String defaultPolicy = DataTypeUtil.POLICY_DEFAULT_3.getKey();
	
	
	public String getTripPolicyId() {
		return tripPolicyId;
	}
	public void setTripPolicyId(String tripPolicyId) {
		this.tripPolicyId = tripPolicyId;
	}
	public String getTripPolicyDetailId() {
		return tripPolicyDetailId;
	}
	public void setTripPolicyDetailId(String tripPolicyDetailId) {
		this.tripPolicyDetailId = tripPolicyDetailId;
	}
	public String getTripRuleId() {
		return TripRuleId;
	}
	public void setTripRuleId(String tripRuleId) {
		TripRuleId = tripRuleId;
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
	public String getApplyObjName() {
		return applyObjName;
	}
	public void setApplyObjName(String applyObjName) {
		this.applyObjName = applyObjName;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public Double getPriceLimit() {
		return priceLimit;
	}
	public void setPriceLimit(Double priceLimit) {
		this.priceLimit = priceLimit;
	}
	public Double getStarLimit() {
		return starLimit;
	}
	public void setStarLimit(Double starLimit) {
		this.starLimit = starLimit;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	public String getHotelShowType() {
		return hotelShowType;
	}
	public void setHotelShowType(String hotelShowType) {
		this.hotelShowType = hotelShowType;
	}
	public String getDefaultPolicy() {
		return defaultPolicy;
	}
	public void setDefaultPolicy(String defaultPolicy) {
		this.defaultPolicy = defaultPolicy;
	}
}
