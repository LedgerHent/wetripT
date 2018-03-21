package com.viptrip.wetrip.pay.weixin.api.bean;
/**
 * 退款返回实体
 * @author selfwhisper
 *
 */
public class WXMwebRefundRtn extends WXRtnBiz{
	private String out_trade_no;//商户订单号
	private String out_refund_no;//商户退款单号
	private String refund_id;//微信退款单号
	private Integer refund_fee;//退款金额
	private Integer total_fee;//标价金额
	private Integer cash_fee;//现金支付金额
	
	
	
	public WXMwebRefundRtn() {
		super();
	}
	
	public WXMwebRefundRtn(String return_code, String return_msg) {
		super(return_code, return_msg);
	}
	
	public WXMwebRefundRtn(String return_code, String return_msg, String appid,
			String mch_id, String nonce_str, String sign, String result_code,
			String err_code, String err_code_des) {
		super(return_code, return_msg, appid, mch_id, nonce_str, sign, result_code,
				err_code, err_code_des);
	}
	
	public WXMwebRefundRtn(String return_code, String return_msg, String appid,
			String mch_id, String nonce_str, String sign, String result_code,
			String err_code, String err_code_des,String out_trade_no, String out_refund_no,
			String refund_id, Integer refund_fee, Integer total_fee,
			Integer cash_fee) {
		super(return_code, return_msg, appid, mch_id, nonce_str, sign, result_code,
				err_code, err_code_des);
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.refund_id = refund_id;
		this.refund_fee = refund_fee;
		this.total_fee = total_fee;
		this.cash_fee = cash_fee;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	
	
	
}
