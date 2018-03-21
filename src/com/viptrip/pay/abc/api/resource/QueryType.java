package com.viptrip.pay.abc.api.resource;

public enum QueryType {
	直接支付("ImmediatePay"),
	预授权支付("PreAuthPay"),
	分期支付("DividePay"),
	授权支付("AgentPay"),
	退款("Refund"),
	付款("DefrayPay"),
	预授权确认("preAuthed"),
	预授权取消("PreAuthCancel");
	
	private QueryType(String code){
		this.code = code;
	}
	
	private String code;
	
	public String code(){
		return this.code;
	}
	
}
