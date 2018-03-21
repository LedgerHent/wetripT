package com.viptrip.intlAirticket.entity;

/**
 * 审核人信息
 * 通知人信息
 * */
public class RrqInformer {

	public String toType;		//审核人or通知人，判断用
	public String toId;			//审核人id
	public String toName;
	public String toTel;
	public String toMail;
	
	
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getToTel() {
		return toTel;
	}
	public void setToTel(String toTel) {
		this.toTel = toTel;
	}
	public String getToMail() {
		return toMail;
	}
	public void setToMail(String toMail) {
		this.toMail = toMail;
	}
	public String getToType() {
		return toType;
	}
	public void setToType(String toType) {
		this.toType = toType;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	
	
}
