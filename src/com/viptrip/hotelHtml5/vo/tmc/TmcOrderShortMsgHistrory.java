/**
* @Title: Abstract TmcOrderShortMsgHistrory
* @Description:  TmcOrderShortMsgHistrory.
* @author Tangqingshan
* @date 2017-07-18 11:40:38
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderShortMsgHistrory
* @Description:  TmcOrderShortMsgHistrory.
* @author Tangqingshan 
* @date 2017-07-18 11:40:38
*/
public class TmcOrderShortMsgHistrory implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	private String templateType;
	private String userName;
	private String tel;
	private String shortMsg;
	private String sendState;
	private Date sendTime;
	private String params;

	private String sendTimeStr;
	
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
	public void setTel(String tel){
	this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setShortMsg(String shortMsg){
	this.shortMsg=shortMsg;
	}
	public String getShortMsg(){
		return shortMsg;
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
	public String getSendTimeStr() {
		return sendTimeStr;
	}
	public void setSendTimeStr(String sendTimeStr) {
		this.sendTimeStr = sendTimeStr;
	}
	
}

