package com.viptrip.pay.ali.api;


import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.viptrip.pay.ali.util.AliConfig;
import com.viptrip.util.StringUtil;


public class AliPayApi {

	private static Logger log = LoggerFactory.getLogger(AliPayApi.class);
	
	public static AlipayClient alipayClient;
	public static String CHARSET = AliConfig.CHARSET.UTF8.value();
	public static String SERVICE_URL = AliConfig.ALI_SERVER_URL;
	public static String APP_ID = AliConfig.ALI_APP_ID;
	public static String PRIVATE_KEY = AliConfig.ALI_PRIVATE_KEY;
	public static String ALIPAY_PUBLIC_KEY = AliConfig.ALI_PUBLIC_KEY;
	public static String SIGN_TYPE = AliConfig.SIGN_TYPE;
	public static String FORMAT = "json";
	
	static {
		alipayClient = new DefaultAlipayClient(SERVICE_URL, APP_ID, PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
	}

	public static AlipayTradeCreateResponse create(String orderId, Double price, String title, String body){
		AlipayTradeCreateResponse result = null;
		AlipayTradeCreateRequest request  = new AlipayTradeCreateRequest();
		AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
		model.setOutTradeNo(orderId);
		model.setTotalAmount(Double.toString(price));
		model.setSubject(title);
		model.setBody(body);
		request.setBizModel(model);
		try {
			result = alipayClient.execute(request);
		} catch (AlipayApiException e) {
			log.error("调取支付宝wap支付接口异常\r\n" + StringUtil.getExceptionStr(e));
		}
		return result;
	}

	//H5支付
	public static String wapPay(AlipayTradeWapPayModel model,String returnUrl,String notifyUrl){
		String result = null;
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizModel(model);// 填充业务参数
		try {
//			result = alipayClient.execute(alipayRequest).getBody();// 调用SDK生成支付URL
			result = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (Exception e) {
			log.error("调取支付宝wap支付接口异常\r\n" + StringUtil.getExceptionStr(e));
			result = "调取支付宝wap支付接口异常," + e.getMessage();
		}
		return result;
	}

	//PC支付
	public static String pagePay(AlipayTradePagePayModel model, String returnUrl, String notifyUrl){
		String result = null;
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizModel(model);// 填充业务参数
		try {
//			result = alipayClient.execute(alipayRequest).get;// 调用SDK生成支付URL
			result = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (Exception e) {
			log.error("调取支付宝pagepay接口异常\r\n" + StringUtil.getExceptionStr(e));
			result = "调取支付宝pagepay接口异常," + e.getMessage();
		}
		return result;
	}
	//app支付
	public static String appPay(AlipayTradeAppPayModel model, String returnUrl, String notifyUrl){
		String result = null;
		AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(returnUrl);
		alipayRequest.setNotifyUrl(notifyUrl);// 在公共参数中设置回跳和通知地址
		alipayRequest.setBizModel(model);// 填充业务参数
		try {
			result = alipayClient.sdkExecute(alipayRequest).getBody(); // 调用SDK生成表单
			log.debug("调取支付宝apppay接口,返回值为：" + result);
		} catch (Exception e) {
			log.error("调取支付宝apppay接口异常\r\n" + StringUtil.getExceptionStr(e));
			result = "调取支付宝apppay支付接口异常," + e.getMessage();
		}
		return result;
	}

	public static AlipayTradeCloseResponse closePay(AlipayTradeCloseModel model){
		AlipayTradeCloseResponse result = null;
		AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();// 创建API对应的request
		request.setBizModel(model);// 填充业务参数
		try {
//			result = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
			result = alipayClient.execute(request);

		} catch (Exception e) {
			log.error("调取支付宝wap支付接口异常\r\n" + StringUtil.getExceptionStr(e));
//			result = "调取支付宝wap支付接口异常," + e.getMessage();
		}
		return result;
	}

	public static AlipayTradeRefundResponse refund(AlipayTradeRefundModel model){
		AlipayTradeRefundResponse result = null;
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();// 创建API对应的request
		alipayRequest.setBizModel(model);// 填充业务参数
		try {
			result = alipayClient.execute(alipayRequest);// 调用SDK生成表单
		} catch (Exception e) {
			log.error("调取支付宝wap支付接口异常\r\n" + StringUtil.getExceptionStr(e));
//			result = "调取支付宝wap支付接口异常," + e.getMessage();
		}
		return result;
	}

	//查询
	public static AlipayTradeQueryResponse query(AlipayTradeQueryModel model){
		AlipayTradeQueryResponse result = null;
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();// 创建API对应的request
		request.setBizModel(model);// 填充业务参数
		try {
			result = alipayClient.execute(request);// 调用SDK生成表单
		} catch (Exception e) {
			log.error("调取支付宝wap支付接口异常\r\n" + StringUtil.getExceptionStr(e));
//			result = "调取支付宝wap支付接口异常," + e.getMessage();
		}
		return result;
	}

	
}
