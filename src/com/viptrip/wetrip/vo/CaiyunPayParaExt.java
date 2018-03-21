package com.viptrip.wetrip.vo;

public class CaiyunPayParaExt{
	private String orderId;
	private Integer price;
	private Object extend;
	private String orderStatus;
	
	public CaiyunPayParaExt() {
	}
	
	public CaiyunPayParaExt(String orderId, Integer price, Object extend,
			CaiyunOrderStatus orderStatus) {
		this.orderId = orderId;
		this.price = price;
		this.extend = extend;
		if(null!=orderStatus){
			this.orderStatus = orderStatus.val();
		}
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public void setOrderStatus(CaiyunOrderStatus orderStatus) {
		this.orderStatus = orderStatus.val();
	}
	
}
