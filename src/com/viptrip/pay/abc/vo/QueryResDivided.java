package com.viptrip.pay.abc.vo;

public class QueryResDivided extends QueryResObj{
	private String OrderAmount;
	private String InstallmentCode;
	private String InstallmentNum;
	private String PaymentLinkType;
	private String AcctNo;
	private String CommodityType;
	private String ReceiverAddress;
	private String BuyIP;
	private String iRspRef;
	private String ReceiveAccount;
	private String ReceiveAccName;
	private String MerchantRemarks;

	public String getOrderAmount() {
		return OrderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		OrderAmount = orderAmount;
	}
	public String getInstallmentCode() {
		return InstallmentCode;
	}
	public void setInstallmentCode(String installmentCode) {
		InstallmentCode = installmentCode;
	}
	public String getInstallmentNum() {
		return InstallmentNum;
	}
	public void setInstallmentNum(String installmentNum) {
		InstallmentNum = installmentNum;
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
