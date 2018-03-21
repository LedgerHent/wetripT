package com.viptrip.hotel.model.fee;

public class Protocol {
	public Integer type;
	public Integer payType;
	public Integer feeType;
	public Double fee;
	/**
	 * 
	 * @param type 1-企业自有协议预订服务费，2-景鸿代开发协议预订服务费，3-景鸿协议预订服务费，4-变更服务费，5-夜间服务费
	 * @param payType 预订服务费付款方式，0-默认（变更或者夜间时有效），1-预付，2-现付。
	 * @param feeType 服务费收取方式，1-定额，2-百分比
	 * @param fee 服务费或者百分比
	 */
	public Protocol(Integer type, Integer payType, Integer feeType, Double fee) {
		super();
		this.type = type;
		this.payType = payType;
		this.feeType = feeType;
		this.fee = fee;
	}
	public Protocol(){
		
	}
	
	
}
