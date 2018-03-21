package com.viptrip.pay.wx.api.bean;
/**
 * 退款请求实体
 * @author selfwhisper
 *
 */
public class WXMwebCloseReq extends WXMwebReqBaseBean{



	public WXMwebCloseReq() {
		super();
	}

	public WXMwebCloseReq(String out_trade_no) {
		super(out_trade_no);
	}
	
}
