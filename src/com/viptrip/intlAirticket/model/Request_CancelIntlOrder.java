package com.viptrip.intlAirticket.model;

import com.viptrip.intlAirticket.model.base.Request_Base;
import com.viptrip.intlAirticket.model.base.Request_UserId;


@SuppressWarnings("serial")
public class Request_CancelIntlOrder extends Request_Base{
	
	private int orderId;//订单编号
	private String memo;//取消理由
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}
