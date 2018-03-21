package com.viptrip.wetrip.pay.weixin.api.bean;
/**
 * 退款请求实体
 * @author selfwhisper
 *
 */
public class WXMwebRefundReq extends WXMwebReqBaseBean{
	
	private String out_refund_no;
	private Integer total_fee;
	private Integer refund_fee;
	
	
	public WXMwebRefundReq() {
		super();
	}

	public WXMwebRefundReq(String out_trade_no,String out_refund_no, Integer total_fee,
			Integer refund_fee) {
		super(out_trade_no);
		this.out_refund_no = out_refund_no;
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
	}
	
	
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	
	
}
