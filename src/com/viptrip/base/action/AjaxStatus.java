package com.viptrip.base.action;

public enum AjaxStatus {
	success(0),fail(1);
	
	private AjaxStatus(Integer code) {
		this.code = code;
	}

	private Integer code;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
	
	public String msg(){
		return 0==this.code?"成功":"失败";
	}
	
}
