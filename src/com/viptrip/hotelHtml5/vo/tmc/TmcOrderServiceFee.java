/**
* @Title: Abstract TmcOrderServiceFee
* @Description:  TmcOrderServiceFee.
* @author Tangqingshan
* @date 2017-06-28 20:11:57
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderServiceFee
* @Description:  TmcOrderServiceFee.
* @author Tangqingshan 
* @date 2017-06-28 20:11:57
*/
public class TmcOrderServiceFee implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	private String feeType;
	private double fee;
	private String createUserId;
	private String createUserName;
	private Date createTime;

	private String createTimeStr;
	
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
	public void setFeeType(String feeType){
	this.feeType=feeType;
	}
	public String getFeeType(){
		return feeType;
	}
	public void setFee(double fee){
	this.fee=fee;
	}
	public double getFee(){
		return fee;
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
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
}

