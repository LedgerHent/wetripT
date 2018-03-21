package com.viptrip.hotel.vo;

public class AliBaseResp {
	protected String code;
	protected String msg;
	protected String sub_code;
	protected String sub_msg;
	protected String sign;
	
	
	
	public AliBaseResp(String code, String msg) {
		this(code, msg, null);
	}
	
	public AliBaseResp(String code, String msg, String sign) {
		this(code, msg, null, null, sign);
	}
	
	public AliBaseResp(String code, String msg, String sub_code,
			String sub_msg, String sign) {
		this.code = code;
		this.msg = msg;
		this.sub_code = sub_code;
		this.sub_msg = sub_msg;
		this.sign = sign;
	}
	
	public AliBaseResp() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getSub_code() {
		return sub_code;
	}
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}
	public String getSub_msg() {
		return sub_msg;
	}
	public void setSub_msg(String sub_msg) {
		this.sub_msg = sub_msg;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
