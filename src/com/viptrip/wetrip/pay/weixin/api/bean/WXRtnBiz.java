package com.viptrip.wetrip.pay.weixin.api.bean;

public class WXRtnBiz extends WXRtnBase{
	
	protected String appid;
	protected String mch_id;
	protected String nonce_str;
	protected String sign;
	protected String result_code;
	protected String err_code;
	protected String err_code_des;
	
	public WXRtnBiz() {
		super();
	}
	
	public WXRtnBiz(String return_code,String return_msg) {
		super(return_code,return_msg);
	}
	
	public WXRtnBiz(String return_code,String return_msg,String appid, String mch_id,
			String nonce_str, String sign, String result_code, String err_code,
			String err_code_des) {
		super(return_code,return_msg);
		this.appid = appid;
		this.mch_id = mch_id;
		this.nonce_str = nonce_str;
		this.sign = sign;
		this.result_code = result_code;
		this.err_code = err_code;
		this.err_code_des = err_code_des;
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
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	
	
	
}
