package com.viptrip.pay.abc.api.resource;

public enum PayType {
	PC(1),MOBILE(2),TV(3),APP(4);
	
	private PayType(int code){
		this.code = code;
	}
	
	private PayType(){
		
	}
	
	private int code;
	
	public int code(){
		return this.code;
	}
}
