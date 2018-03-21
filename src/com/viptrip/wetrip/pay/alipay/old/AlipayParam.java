package com.viptrip.wetrip.pay.alipay.old;

public class AlipayParam {
	//支付类型
	private String paymentType;
	//服务器异步通知页面Url
	private String notifyUrl;
	//页面跳转同步通知页面Url
	private String returnUrl;
    //商家支付宝账号
	private String sellerAlipayNo;
	//商品订单号
	private String orderNo;
	//商品名称
	private String subjectName;
	//支付金额
	private String totalPrice;
	//订单描述
	private String orderDescribe;
	//商品展示地址
	private String showUrl;
	//防钓鱼时间戳
	private String antiPhishingKey;
	//客户端ip
	private String exterInvokeIp;
	
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	
	
	private String service;
	//合作者身份ID
	private  String partner = "";
	// 商户的私钥
	private  String key = "";

	// 调试用，创建TXT日志文件夹路径
	private  String logPath = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	private  String inputCharset = "utf-8";
	
	// 签名方式 不需修改
	private  String signType = "MD5";

	private String sign;
	
	//add by yl
	private String defaultBank;
	
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getSellerAlipayNo() {
		return sellerAlipayNo;
	}
	public void setSellerAlipayNo(String sellerAlipayNo) {
		this.sellerAlipayNo = sellerAlipayNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDescribe() {
		return orderDescribe;
	}
	public void setOrderDescribe(String orderDescribe) {
		this.orderDescribe = orderDescribe;
	}
	public String getShowUrl() {
		return showUrl;
	}
	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}
	public String getAntiPhishingKey() {
		return antiPhishingKey;
	}
	public void setAntiPhishingKey(String antiPhishingKey) {
		this.antiPhishingKey = antiPhishingKey;
	}
	public String getExterInvokeIp() {
		return exterInvokeIp;
	}
	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLogPath() {
		return logPath;
	}
	public void setLogPath(String logPath) {
		this.logPath = logPath;
	}
	public String getInputCharset() {
		return inputCharset;
	}
	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getDefaultBank() {
		return defaultBank;
	}
	public void setDefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
	}
	
	
}
