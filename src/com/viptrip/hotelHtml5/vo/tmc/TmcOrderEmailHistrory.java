/**
* @Title: Abstract TmcOrderEmailHistrory
* @Description:  TmcOrderEmailHistrory.
* @author Tangqingshan
* @date 2017-07-18 11:41:13
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderEmailHistrory
* @Description:  TmcOrderEmailHistrory.
* @author Tangqingshan 
* @date 2017-07-18 11:41:13
*/
public class TmcOrderEmailHistrory implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	private String templateType;
	private String userName;
	private String email;
	private String sendState;
	private Date sendTime;
	private String params;
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
	public void setTemplateType(String templateType){
	this.templateType=templateType;
	}
	public String getTemplateType(){
		return templateType;
	}
	public void setUserName(String userName){
	this.userName=userName;
	}
	public String getUserName(){
		return userName;
	}
	public void setEmail(String email){
	this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setSendState(String sendState){
	this.sendState=sendState;
	}
	public String getSendState(){
		return sendState;
	}
	public void setSendTime(Date sendTime){
	this.sendTime=sendTime;
	}
	public Date getSendTime(){
		return sendTime;
	}
	public void setParams(String params){
	this.params=params;
	}
	public String getParams(){
		return params;
	}
}

