package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

/**
 * 通知人信息
 */
public class Req_Data_OrderInfo_Informer {

	private String toName;			//通知人姓名
	
	private String toTel;			//通知人电话
	
	private String toMail;			//通知人邮箱
	
	

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


	
}
