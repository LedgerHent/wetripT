package com.viptrip.pay.abc.vo;

public class QueryResImmediate extends QueryResObj{

	private String OrderAmount;
	private String OrderDesc;
	private String OrderURL;
	private String PaymentLinkType;
	private String AcctNo;
	private String CommodityType;
	private String ReceiverAddress;
	private String BuyIP;
	private String iRspRef;

	private String ReceiveAccount;

	private String ReceiveAccName;
	private String MerchantRemarks;
	public String getOrderDesc() {
		return OrderDesc;
	}


	public String getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		OrderAmount = orderAmount;
	}
	public void setOrderDesc(String orderDesc) {
		OrderDesc = orderDesc;
	}
	public String getOrderURL() {
		return OrderURL;
	}
	public void setOrderURL(String orderURL) {
		OrderURL = orderURL;
	}
	public String getPaymentLinkType() {
		return PaymentLinkType;
	}
	public void setPaymentLinkType(String paymentLinkType) {
		PaymentLinkType = paymentLinkType;
	}
	public String getAcctNo() {
		return AcctNo;
	}
	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}
	public String getCommodityType() {
		return CommodityType;
	}
	public void setCommodityType(String commodityType) {
		CommodityType = commodityType;
	}
	public String getReceiverAddress() {
		return ReceiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		ReceiverAddress = receiverAddress;
	}
	public String getBuyIP() {
		return BuyIP;
	}
	public void setBuyIP(String buyIP) {
		BuyIP = buyIP;
	}
	public String getiRspRef() {
		return iRspRef;
	}
	public void setiRspRef(String iRspRef) {
		this.iRspRef = iRspRef;
	}
	public String getReceiveAccount() {
		return ReceiveAccount;
	}
	public void setReceiveAccount(String receiveAccount) {
		ReceiveAccount = receiveAccount;
	}
	public String getReceiveAccName() {
		return ReceiveAccName;
	}
	public void setReceiveAccName(String receiveAccName) {
		ReceiveAccName = receiveAccName;
	}
	public String getMerchantRemarks() {
		return MerchantRemarks;
	}
	public void setMerchantRemarks(String merchantRemarks) {
		MerchantRemarks = merchantRemarks;
	}
	
	
	
}
