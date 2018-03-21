package com.viptrip.wetrip.model.orderlist;

import java.util.List;

public class RespData_GetOrderDetail_Change {
	private Integer flowId;
	private List<RespData_GetOrderDetail_Flight> flights;
	private List<RespData_GetOrderDetail_Ticket> tickets;
	
	
	
	public RespData_GetOrderDetail_Change() {
		super();
	}
	
	
	public RespData_GetOrderDetail_Change(Integer flowId,
			List<RespData_GetOrderDetail_Flight> flights,List<RespData_GetOrderDetail_Ticket> tickets) {
		this.flowId = flowId;
		this.flights = flights;
		this.tickets = tickets;
	}

	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public List<RespData_GetOrderDetail_Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<RespData_GetOrderDetail_Flight> flights) {
		this.flights = flights;
	}
	public List<RespData_GetOrderDetail_Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<RespData_GetOrderDetail_Ticket> tickets) {
		this.tickets = tickets;
	}


	
	
}
