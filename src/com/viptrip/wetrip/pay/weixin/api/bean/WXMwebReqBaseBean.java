package com.viptrip.wetrip.pay.weixin.api.bean;

import com.viptrip.wetrip.pay.WxConfig;

public class WXMwebReqBaseBean {
	protected String appid;
	protected String mch_id;
	protected String nonce_str;
	protected String sign;
	protected String sign_type;
	protected String out_trade_no;
	
	
	public WXMwebReqBaseBean(String out_trade_no) {
		this();
		this.out_trade_no = out_trade_no;
	}
	public WXMwebReqBaseBean() {
		this.appid = WxConfig.APPID;
		this.mch_id = WxConfig.MCHID;
		this.nonce_str = String.valueOf(System.currentTimeMillis());
		this.sign_type = WxConfig.SIGNTYPE.MD5.val();
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appid == null) ? 0 : appid.hashCode());
		result = prime * result + ((mch_id == null) ? 0 : mch_id.hashCode());
		result = prime * result
				+ ((nonce_str == null) ? 0 : nonce_str.hashCode());
		result = prime * result
				+ ((out_trade_no == null) ? 0 : out_trade_no.hashCode());
		result = prime * result + ((sign == null) ? 0 : sign.hashCode());
		result = prime * result
				+ ((sign_type == null) ? 0 : sign_type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WXMwebReqBaseBean other = (WXMwebReqBaseBean) obj;
		if (appid == null) {
			if (other.appid != null)
				return false;
		} else if (!appid.equals(other.appid))
			return false;
		if (mch_id == null) {
			if (other.mch_id != null)
				return false;
		} else if (!mch_id.equals(other.mch_id))
			return false;
		if (nonce_str == null) {
			if (other.nonce_str != null)
				return false;
		} else if (!nonce_str.equals(other.nonce_str))
			return false;
		if (out_trade_no == null) {
			if (other.out_trade_no != null)
				return false;
		} else if (!out_trade_no.equals(other.out_trade_no))
			return false;
		if (sign == null) {
			if (other.sign != null)
				return false;
		} else if (!sign.equals(other.sign))
			return false;
		if (sign_type == null) {
			if (other.sign_type != null)
				return false;
		} else if (!sign_type.equals(other.sign_type))
			return false;
		return true;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	
	
}
