package com.viptrip.wechat.model;

import java.io.Serializable;

import com.viptrip.base.common.MyEnum;

@SuppressWarnings("serial")
public class WechatBase implements Serializable {
	private String PAID;
	private String openID ;
	private String unionID ;
	private String mobile;
	private MyEnum.WechatMenuType fromMenuType;
	
	
	private boolean absolutePath=false;
	private String redirectUrl;
	private String redirectParamStr;
	
	public String getPAID() {
		return PAID;
	}
	public void setPAID(String pAID) {
		PAID = pAID;
	}
	public String getOpenID() {
		return openID;
	}
	public void setOpenID(String openID) {
		this.openID = openID;
	}
	public String getUnionID() {
		return unionID;
	}
	public void setUnionID(String unionID) {
		this.unionID = unionID;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public MyEnum.WechatMenuType getFromMenuType() {
		return fromMenuType;
	}
	public void setFromMenuType(MyEnum.WechatMenuType fromMenuType) {
		this.fromMenuType = fromMenuType;
	}
	
	
	
	public boolean isAbsolutePath() {
		return absolutePath;
	}
	public void setAbsolutePath(boolean absolutePath) {
		this.absolutePath = absolutePath;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getRedirectParamStr() {
		return redirectParamStr;
	}
	public void setRedirectParamStr(String redirectParamStr) {
		this.redirectParamStr = redirectParamStr;
	}
	
	
}
