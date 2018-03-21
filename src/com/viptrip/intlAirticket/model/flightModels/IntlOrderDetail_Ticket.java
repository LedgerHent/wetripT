package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Ticket {
    
    public String ticketNo;// 票号 字符串

    public Integer psgrId;// 乘机人id 整型数字

    public String flightNos;// 航班号序列，逗号分隔，CA123,MU2231 字符串

    public String opDatetime;// 操作时间 字符串

    public String status;// 票状态 字符串

	public String getTicketNo() {
		return ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	public Integer getPsgrId() {
		return psgrId;
	}

	public void setPsgrId(Integer psgrId) {
		this.psgrId = psgrId;
	}

	public String getFlightNos() {
		return flightNos;
	}

	public void setFlightNos(String flightNos) {
		this.flightNos = flightNos;
	}

	public String getOpDatetime() {
		return opDatetime;
	}

	public void setOpDatetime(String opDatetime) {
		this.opDatetime = opDatetime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
