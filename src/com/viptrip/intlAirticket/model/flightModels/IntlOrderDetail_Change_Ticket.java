package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Change_Ticket extends IntlOrderDetail_Ticket {
//  ...tickets[0].ticketNo  原始票号    字符串
//  ...tickets[0].psgrId    乘机人id   整型数字
//  ...tickets[0].newTicketNo   新票号 字符串
//  ...tickets[0].flightNos 航班号序列，逗号分隔，CA123,MU2231 字符串
//  ...tickets[0].opDatetime    操作时间    字符串
//  ...tickets[0].status    改期状态    字符串
    
    public String newTicketNo;//newTicketNo

	public String getNewTicketNo() {
		return newTicketNo;
	}

	public void setNewTicketNo(String newTicketNo) {
		this.newTicketNo = newTicketNo;
	}
    
}
