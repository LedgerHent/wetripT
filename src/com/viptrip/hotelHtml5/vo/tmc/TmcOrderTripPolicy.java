/**
* @Title: Abstract TmcOrderTripPolicy
* @Description:  TmcOrderTripPolicy.
* @author Tangqingshan
* @date 2017-08-19 14:34:11
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

/**
* @ClassName:  Abstract TmcOrderTripPolicy
* @Description:  TmcOrderTripPolicy.
* @author Tangqingshan 
* @date 2017-08-19 14:34:11
*/
public class TmcOrderTripPolicy implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 政策名称
	 */
	private String policyName;
	/**
	 * 提醒方式（仅提醒、禁止预订、超标审批）
	 */
	private String remindType;
	/**
	 *  差旅政策ID
	 */
	private String tripPolicyId;
	/**
	 * 金额上限
	 */
	private Double priceLimit;
	/**
	 * 星级上限
	 */
	private Integer starLimit;
	public void setDbid(String dbid){
	this.dbid=dbid;
	}
	public String getDbid(){
		return dbid;
	}
	public void setOrderNo(String orderNo){
	this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setPolicyName(String policyName){
	this.policyName=policyName;
	}
	public String getPolicyName(){
		return policyName;
	}
	public void setRemindType(String remindType){
	this.remindType=remindType;
	}
	public String getRemindType(){
		return remindType;
	}
	public void setTripPolicyId(String tripPolicyId){
	this.tripPolicyId=tripPolicyId;
	}
	public String getTripPolicyId(){
		return tripPolicyId;
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
	
}

