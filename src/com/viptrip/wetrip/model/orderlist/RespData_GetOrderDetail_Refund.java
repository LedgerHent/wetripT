package com.viptrip.wetrip.model.orderlist;

import java.util.List;

public class RespData_GetOrderDetail_Refund {
	private Integer type;//1-退出票|2-退改期
	private Integer flowId;
	private List<RespData_GetOrderDetail_Ticket> tickets;
	
	
	public RespData_GetOrderDetail_Refund(Integer type, Integer flowId,
			List<RespData_GetOrderDetail_Ticket> tickets) {
		this.type = type;
		this.flowId = flowId;
		this.tickets = tickets;
	}
	public RespData_GetOrderDetail_Refund() {
		super();
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public List<RespData_GetOrderDetail_Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<RespData_GetOrderDetail_Ticket> tickets) {
		this.tickets = tickets;
	}
	
	
	
}
