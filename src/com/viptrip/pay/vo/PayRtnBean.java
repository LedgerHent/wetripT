package com.viptrip.pay.vo;

/**
 * 支付返回通用实体
 * @author selfwhisper
 *
 */
public class PayRtnBean {
	
	private String orderId;
	private String orderNo;
	private String tradeNo;
	private String price;
	private String title;
	private String identity;
	private String extend;
	private String msg;
	
	
	public PayRtnBean(String orderId, String orderNo, String msg) {
		this(orderId, orderNo, null, null, msg);
	}
	
	public PayRtnBean(String orderId, String orderNo, String tradeNo, String price) {
		this(orderId, orderNo, tradeNo, price, null);
	}
	public PayRtnBean(String orderId, String orderNo, String tradeNo, String price, String msg) {
		this(orderId, orderNo, tradeNo, price, null, msg);
	}
	
	public PayRtnBean(String orderId, String orderNo, String tradeNo,String price, String title, String msg) {
		this(orderId, orderNo, tradeNo, price, title, null, null, msg);
	}
	
	public PayRtnBean(String orderId, String orderNo, String tradeNo,
			String price, String title, String identity, String extend,
			String msg) {
		this.orderId = orderId;
		this.orderNo = orderNo;
		this.tradeNo = tradeNo;
		this.price = price;
		this.title = title;
		this.identity = identity;
		this.extend = extend;
		this.msg = msg;
	}
	public PayRtnBean() {
	}
	
	
	public PayRtnBean ali(){
		this.identity = "alipay";
		return this;
	}
	
	public PayRtnBean wx(){
		this.identity = "wxpay";
		return this;
	}
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
