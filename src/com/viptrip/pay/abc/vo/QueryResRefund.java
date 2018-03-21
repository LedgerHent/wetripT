package com.viptrip.pay.abc.vo;

public class QueryResRefund extends QueryResObj{

	private String RefundAmount;
	private String iRspRef;
	private String MerRefundAccountNo;
	private String MerRefundAccountName;

	public String getRefundAmount() {
		return RefundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		RefundAmount = refundAmount;
	}
	public String getiRspRef() {
		return iRspRef;
	}
	public void setiRspRef(String iRspRef) {
		this.iRspRef = iRspRef;
	}
	public String getMerRefundAccountNo() {
		return MerRefundAccountNo;
	}
	public void setMerRefundAccountNo(String merRefundAccountNo) {
		MerRefundAccountNo = merRefundAccountNo;
	}
	public String getMerRefundAccountName() {
		return MerRefundAccountName;
	}
	public void setMerRefundAccountName(String merRefundAccountName) {
		MerRefundAccountName = merRefundAccountName;
	}
	
}
