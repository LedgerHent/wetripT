package com.viptrip.pay.wx.api.bean;
/**
 * 关闭交易返回实体
 * @author selfwhisper
 *
 */
public class WXMwebCloseRtn extends WXRtnBiz{

	public WXMwebCloseRtn() {
		super();
	}

	public WXMwebCloseRtn(String return_code, String return_msg) {
		super(return_code, return_msg);
	}

}
