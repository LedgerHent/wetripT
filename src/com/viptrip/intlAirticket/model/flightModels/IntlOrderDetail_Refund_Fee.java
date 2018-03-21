package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Refund_Fee {
    
    public Integer psgrId;// 乘机人id 整型数字

    public Double refund;// 退票费 双精度数字

    public Double service;// 服务费 双精度数字

	public Integer getPsgrId() {
		return psgrId;
	}

	public void setPsgrId(Integer psgrId) {
		this.psgrId = psgrId;
	}

	public Double getRefund() {
		return refund;
	}

	public void setRefund(Double refund) {
		this.refund = refund;
	}

	public Double getService() {
		return service;
	}

	public void setService(Double service) {
		this.service = service;
	}
    
    
}
