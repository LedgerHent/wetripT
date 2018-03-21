package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class IntlOrderDetail_Change {
    public Integer flowid;// 顺序号，和航程顺序号一致，标红 整型数字

    public List<IntlOrderDetail_Change_Ticket> tickets;// 改期票信息 JSONArray

    public List<IntlOrderDetail_Change_Fee> fees;// 改期发生费用，费用按人收取 JSONArray

	public Integer getFlowid() {
		return flowid;
	}

	public void setFlowid(Integer flowid) {
		this.flowid = flowid;
	}

	public List<IntlOrderDetail_Change_Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<IntlOrderDetail_Change_Ticket> tickets) {
		this.tickets = tickets;
	}

	public List<IntlOrderDetail_Change_Fee> getFees() {
		return fees;
	}

	public void setFees(List<IntlOrderDetail_Change_Fee> fees) {
		this.fees = fees;
	}
    
    

}
