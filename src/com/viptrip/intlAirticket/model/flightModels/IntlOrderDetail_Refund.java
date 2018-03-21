package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class IntlOrderDetail_Refund {

    public Integer type;// 1：退出票，2：退改期 整型数字

    public Integer flowid;// 顺序号，和航程顺序号一致，标红 整型数字

    public String ticketNo;// 退票票号（可能是原始票信息，也可能是改期后的票信息） 字符串

    public String opDatetime;// 退票操作时间 字符串

    public String reason;// 退票原因 字符串

    public String status;// 退票状态 整型数字

    public List<IntlOrderDetail_Refund_Fee> fees;// 改期发生费用，费用按人收取 JSONArray

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlowid() {
		return flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
	}

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public String getOpDatetime() {
		return opDatetime;
	}

	public void setOpDatetime(String opDatetime) {
		this.opDatetime = opDatetime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<IntlOrderDetail_Refund_Fee> getFees() {
		return fees;
	}

	public void setFees(List<IntlOrderDetail_Refund_Fee> fees) {
		this.fees = fees;
	}
    
    

}
