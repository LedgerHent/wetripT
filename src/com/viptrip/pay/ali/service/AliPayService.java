package com.viptrip.pay.ali.service;



import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.domain.*;
import com.alipay.api.response.*;
import com.viptrip.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.pay.ali.api.AliPayApi;
import com.viptrip.pay.ali.vo.AliPayNTFBeanNew;
import com.viptrip.util.JSON;

@Service("ali_unipay")
public class AliPayService {
	private static Logger log = LoggerFactory.getLogger(AliPayService.class);
	/**
	 * 支付宝H5支付
	 * @param orderId
	 * @param price 支付金额 单位元
	 * @param title 标题
	 * @param body 主体
	 * @param returnURL 回跳地址
	 * @param notifyURL 通知地址
	 * @return
	 */
	public String wapPay(String orderId,Double price,String title,String body,String returnURL,String notifyURL){
		
		AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
		model.setOutTradeNo(orderId);
		model.setTotalAmount(new DecimalFormat("0.##").format(price));
		model.setSubject(title);
		model.setBody(body);

		/*AlipayTradeCreateResponse response = AliPayApi.create(orderId, price, title, body);
		log.info("wapPay===response==" + JSON.get().toJson(response));*/
		return AliPayApi.wapPay(model, returnURL, notifyURL);
		
	}
	/**
	 * PC端支付
	 * @param orderId
	 * @param price 支付金额 单位元
	 * @param title 标题
	 * @param body 主体
	 * @param returnURL 回跳地址
	 * @param notifyURL 通知地址
	 * @return
	 */
	public String pagePay(String orderId,Double price,String title,String body,String returnURL,String notifyURL){

		AlipayTradePagePayModel model = new AlipayTradePagePayModel();
		model.setOutTradeNo(orderId);
		model.setTotalAmount(new DecimalFormat("0.##").format(price));
		model.setSubject(title);
		model.setBody(body);
		model.setProductCode("FAST_INSTANT_TRADE_PAY");
		/*AlipayTradeCreateResponse response = AliPayApi.create(orderId, price, title, body);
		log.info("pagePay===response==" + JSON.get().toJson(response));*/
		return AliPayApi.pagePay(model, returnURL, notifyURL);

	}
	/**
	 * APP支付
	 * @param orderId
	 * @param price 支付金额 单位元
	 * @param title 标题
	 * @param body 主体
	 * @param returnURL 回跳地址
	 * @param notifyURL 通知地址
	 * @return
	 */
	public String appPay(String orderId,Double price,String title,String body,String returnURL,String notifyURL){

		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setOutTradeNo(orderId);
		model.setTotalAmount(new DecimalFormat("0.##").format(price));
		model.setSubject(title);
		model.setBody(body);

		return AliPayApi.appPay(model, returnURL, notifyURL);

	}

	//TODO
	public String query(String orderId){
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(orderId);
		AlipayTradeQueryResponse query = AliPayApi.query(model);
		log.info(StringUtil.getLogInfo(null,null,"调用支付查询接口返回值为：" + JSON.get().toJson(query)));
		return null;

	}

	//TODO
	public String payClose(String orderId){
		query(orderId);
		AlipayTradeCloseModel model = new AlipayTradeCloseModel();
		model.setOutTradeNo(orderId);
		AlipayTradeCloseResponse s = AliPayApi.closePay(model);
		log.info(StringUtil.getLogInfo(null,null,"调用支付关闭接口返回值为：" + JSON.get().toJson(s)));
		return null;

	}

	public AlipayTradeRefundResponse refund(String orderId,String refundNo,Double refundAmount,String refundReason){
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setOutTradeNo(orderId);
		model.setOutRequestNo(refundNo);
		model.setRefundAmount(new DecimalFormat("0.##").format(refundAmount));
		model.setRefundReason(refundReason);
		AlipayTradeRefundResponse refund = AliPayApi.refund(model);
		log.info(StringUtil.getLogInfo(null,null,"调用支付退款接口返回值为：" + JSON.get().toJson(refund)));
		return refund;
	}

	
	/**
	 * 获取支付通知参数对象
	 * @param req
	 * @return
	 */
	public AliPayNTFBeanNew getNotifyBean(HttpServletRequest req){
		Map<String, String> params = getParam(req);
		if(log.isDebugEnabled()){
			log.debug("getNotifyBean所有返回参数：" + JSON.get().toJson(params));
		}
		AliPayNTFBeanNew bean = JSON.get().fromJson(JSON.get().toJson(params), AliPayNTFBeanNew.class);
		return bean;
	}
	
	public Map<String,String> getParam(HttpServletRequest req){
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = req.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			/*//乱码解决，这段代码在出现乱码时使用
			try {
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				log.error(StringUtil.getExceptionStr(e));
			}*/
			params.put(name, valueStr);
		}
		return params;
	}
}
