package com.viptrip.pay.abc.vo;

public class QueryReqObj {
	
	private String PayTypeID;
	private String OrderNo;
	private boolean QueryDetail = false;
	
	public QueryReqObj() {
		super();
	}
	
	public QueryReqObj(String payTypeID, String orderNo, boolean queryDetail) {
		PayTypeID = payTypeID;
		OrderNo = orderNo;
		QueryDetail = queryDetail;
	}

	public String getPayTypeID() {
		return PayTypeID;
	}
	public void setPayTypeID(String payTypeID) {
		PayTypeID = payTypeID;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getQueryDetail() {
		return QueryDetail?"1":"0";
	}
	public void setQueryDetail(boolean queryDetail) {
		QueryDetail = queryDetail;
	}
	
	
}
