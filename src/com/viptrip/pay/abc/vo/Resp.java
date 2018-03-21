package com.viptrip.pay.abc.vo;

public class Resp<E> {
	private Integer code;
	private String msg;
	private E data;
	
	public Resp<E> success(E data){
		return success(null,data);
	}
	
	public Resp<E> success(String msg,E data){
		this.code = 0;
		this.msg = msg;
		this.data = data;
		return this;
	}
	
	@SuppressWarnings("rawtypes")
	public Resp fail(String msg){
		this.code = 1;
		this.msg = msg;
		return this;
	}
	
	public boolean isSuccess(){
		return this.code==null?false:0==this.code;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}
	
	
	
}
