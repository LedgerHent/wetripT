package com.viptrip.pay.wx.api.bean;


/**
 * 统一下单请求实体
 * @author selfwhisper
 *
 */
public class WXAPPPayReq extends WXMwebReqBaseBean{
	private String body;
	private Integer total_fee;
	private String spbill_create_ip;
	private String notify_url;
	private String trade_type;

	private WXAPPPayReq(){
		super();
	}

	public WXAPPPayReq(String out_trade_no, Integer total_fee, String ip, String body, String notifyURL) {
		super(out_trade_no);
		this.body = body;
		this.total_fee = total_fee;
		this.spbill_create_ip = ip;
		this.trade_type = "APP";
		this.notify_url = notifyURL;
	}
	

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

}
