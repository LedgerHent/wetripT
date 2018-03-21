package com.viptrip.hotel.vo;

public class AliRefundQueryResp extends AliBaseResp{

	private String trade_no;
	private String out_trade_no;
	private String out_request_no;
	private String refund_reason;
	private Double total_amount;
	private Double refund_amount;
	
	
	public AliRefundQueryResp(String code, String msg, String sub_code, String sub_msg, String sign, String trade_no,
			String out_trade_no, String out_request_no, String refund_reason,
			Double total_amount, Double refund_amount) {
		super(code, msg, sub_code, sub_msg, sign);
		this.trade_no = trade_no;
		this.out_trade_no = out_trade_no;
		this.out_request_no = out_request_no;
		this.refund_reason = refund_reason;
		this.total_amount = total_amount;
		this.refund_amount = refund_amount;
	}
	public AliRefundQueryResp() {
		super();
	}
	public AliRefundQueryResp(String code, String msg, String sub_code,
			String sub_msg, String sign) {
		super(code, msg, sub_code, sub_msg, sign);
	}
	public AliRefundQueryResp(String code, String msg, String sign) {
		super(code, msg, sign);
	}
	public AliRefundQueryResp(String code, String msg) {
		super(code, msg);
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_request_no() {
		return out_request_no;
	}
	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}
	public String getRefund_reason() {
		return refund_reason;
	}
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	public Double getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(Double total_amount) {
		this.total_amount = total_amount;
	}
	public Double getRefund_amount() {
		return refund_amount;
	}
	public void setRefund_amount(Double refund_amount) {
		this.refund_amount = refund_amount;
	}
	
	
	
}
