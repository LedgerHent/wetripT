package com.viptrip.pay.wx.api.bean;
/**
 * 统一下单返回实体
 * @author selfwhisper
 *
 */
public class WXJSAPIPayRtn extends WXRtnBiz{

	private String trade_type;
	private String prepay_id;
	private String code_url;//

	public WXJSAPIPayRtn() {
		super();
	}

	public WXJSAPIPayRtn(String return_code, String return_msg) {
		super(return_code, return_msg);
	}

	public WXJSAPIPayRtn(String return_code, String return_msg, String appid,
                         String mch_id, String nonce_str, String sign,
                         String result_code, String err_code, String err_code_des, String trade_type, String prepay_id, String code_url) {
		super(return_code, return_msg, appid, mch_id, nonce_str, sign,
				result_code, err_code, err_code_des);
		this.trade_type = trade_type;
		this.prepay_id = prepay_id;
		this.code_url = code_url;
	}
	
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}

	public String getCode_url() {
		return code_url;
	}

	public void setCode_url(String code_url) {
		this.code_url = code_url;
	}
}
