package com.viptrip.wetrip.vo;

public class CaiyunPayPara {
	protected String orderId;
	protected Integer price;
	protected Object extend;
	
	
	public CaiyunPayPara() {
	}
	
	public CaiyunPayPara(String orderId, Integer price, Object extend) {
		this.orderId = orderId;
		this.price = price;
		this.extend = extend;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Object getExtend() {
		return extend;
	}
	public void setExtend(Object extend) {
		this.extend = extend;
	}
	
}
