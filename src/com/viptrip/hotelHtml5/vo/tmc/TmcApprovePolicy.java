/**
* @Title: Abstract TmcApprovePolicy
* @Description:  TmcApprovePolicy.
* @author Tangqingshan
* @date 2017-07-09 11:14:06
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcApprovePolicy
* @Description:  TmcApprovePolicy.
* @author Tangqingshan 
* @date 2017-07-09 11:14:06
*/
public class TmcApprovePolicy implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String approvePolicyName;
	private String commonApprRequire;
	private String commonApprType;
	private String exceedApprRequire;
	private String exceedApprType;
	private String createUserId;
	private String createUserName;
	private Date createTime;
	private String lastUpdUserId;
	private String lastUpdUserName;
	private String delFlag;
	private Date lastUpdDate;
	private String enterpId;
	private String enterpName;
	
	private String createTimeStr;
	private String lastUpdDateStr;
	
	public void setDbid(String dbid){
	this.dbid=dbid;
	}
	public String getDbid(){
		return dbid;
	}
	public void setApprovePolicyName(String approvePolicyName){
	this.approvePolicyName=approvePolicyName;
	}
	public String getApprovePolicyName(){
		return approvePolicyName;
	}
	public void setCommonApprRequire(String commonApprRequire){
	this.commonApprRequire=commonApprRequire;
	}
	public String getCommonApprRequire(){
		return commonApprRequire;
	}
	public void setCommonApprType(String commonApprType){
	this.commonApprType=commonApprType;
	}
	public String getCommonApprType(){
		return commonApprType;
	}
	public void setExceedApprRequire(String exceedApprRequire){
	this.exceedApprRequire=exceedApprRequire;
	}
	public String getExceedApprRequire(){
		return exceedApprRequire;
	}
	public void setExceedApprType(String exceedApprType){
	this.exceedApprType=exceedApprType;
	}
	public String getExceedApprType(){
		return exceedApprType;
	}
	public void setCreateUserId(String createUserId){
	this.createUserId=createUserId;
	}
	public String getCreateUserId(){
		return createUserId;
	}
	public void setCreateUserName(String createUserName){
	this.createUserName=createUserName;
	}
	public String getCreateUserName(){
		return createUserName;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setLastUpdUserId(String lastUpdUserId){
	this.lastUpdUserId=lastUpdUserId;
	}
	public String getLastUpdUserId(){
		return lastUpdUserId;
	}
	public void setLastUpdUserName(String lastUpdUserName){
	this.lastUpdUserName=lastUpdUserName;
	}
	public String getLastUpdUserName(){
		return lastUpdUserName;
	}
	public void setDelFlag(String delFlag){
	this.delFlag=delFlag;
	}
	public String getDelFlag(){
		return delFlag;
	}
	public void setLastUpdDate(Date lastUpdDate){
	this.lastUpdDate=lastUpdDate;
	}
	public Date getLastUpdDate(){
		return lastUpdDate;
	}
	public void setEnterpId(String enterpId){
	this.enterpId=enterpId;
	}
	public String getEnterpId(){
		return enterpId;
	}
	public void setEnterpName(String enterpName){
	this.enterpName=enterpName;
	}
	public String getEnterpName(){
		return enterpName;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	public String getLastUpdDateStr() {
		return lastUpdDateStr;
	}
	public void setLastUpdDateStr(String lastUpdDateStr) {
		this.lastUpdDateStr = lastUpdDateStr;
	}
	
}

