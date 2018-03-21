package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class ReqData_BookAirTicket_OverReason implements Serializable{
	private static final long serialVersionUID = -7833487052482322260L;
	@Description("超标类型")
	public Integer type;//10=未选择最低价，21=金额超标，22=折扣超标，23=舱位超标，30=提前预定天数超标
	@Description("超标理由")
	public String reason;
	@Description("行程城市")
	public String flightCity;
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
    public String getFlightCity() {
        return flightCity;
    }
    public void setFlightCity(String flightCity) {
        this.flightCity = flightCity;
    }
	
	
}
