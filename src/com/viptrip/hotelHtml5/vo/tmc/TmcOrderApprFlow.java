/**
* @Title: Abstract TmcOrderApprFlow
* @Description:  TmcOrderApprFlow.
* @author Tangqingshan
* @date 2017-07-13 16:54:04
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderApprFlow
* @Description:  TmcOrderApprFlow.
* @author Tangqingshan 
* @date 2017-07-13 16:54:04
*/
public class TmcOrderApprFlow implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	private String pengdingApprUser;
	/**
	 * 审批人ID序列（逗号分割）
	 */
	private String actualApprUserId;
	/**
	 * 审批人姓名序列（逗号分割） 
	 */
	private String actualApprUserName;
	private String agentApprUserId;
	private String agentApprUserName;
	private Date approveTime;
	private String approveState;
	private String rejectReson;
	private String memo;
	/**
	 * 审批流ID（审批顺序号）
	 */
	private long seq;
	private String pengdingApprUserId;

	private String approveTimeStr;
	
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
	public void setPengdingApprUser(String pengdingApprUser){
	this.pengdingApprUser=pengdingApprUser;
	}
	public String getPengdingApprUser(){
		return pengdingApprUser;
	}
	public void setActualApprUserId(String actualApprUserId){
	this.actualApprUserId=actualApprUserId;
	}
	public String getActualApprUserId(){
		return actualApprUserId;
	}
	public void setActualApprUserName(String actualApprUserName){
	this.actualApprUserName=actualApprUserName;
	}
	public String getActualApprUserName(){
		return actualApprUserName;
	}
	public void setAgentApprUserId(String agentApprUserId){
	this.agentApprUserId=agentApprUserId;
	}
	public String getAgentApprUserId(){
		return agentApprUserId;
	}
	public void setAgentApprUserName(String agentApprUserName){
	this.agentApprUserName=agentApprUserName;
	}
	public String getAgentApprUserName(){
		return agentApprUserName;
	}
	public void setApproveTime(Date approveTime){
	this.approveTime=approveTime;
	}
	public Date getApproveTime(){
		return approveTime;
	}
	public void setApproveState(String approveState){
	this.approveState=approveState;
	}
	public String getApproveState(){
		return approveState;
	}
	public void setRejectReson(String rejectReson){
	this.rejectReson=rejectReson;
	}
	public String getRejectReson(){
		return rejectReson;
	}
	public void setMemo(String memo){
	this.memo=memo;
	}
	public String getMemo(){
		return memo;
	}
	public void setSeq(long seq){
	this.seq=seq;
	}
	public long getSeq(){
		return seq;
	}
	public String getPengdingApprUserId() {
		return pengdingApprUserId;
	}
	public void setPengdingApprUserId(String pengdingApprUserId) {
		this.pengdingApprUserId = pengdingApprUserId;
	}
	public String getApproveTimeStr() {
		return approveTimeStr;
	}
	public void setApproveTimeStr(String approveTimeStr) {
		this.approveTimeStr = approveTimeStr;
	}
	
}

