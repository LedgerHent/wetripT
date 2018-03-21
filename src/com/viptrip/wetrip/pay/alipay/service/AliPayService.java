package com.viptrip.wetrip.pay.alipay.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.wetrip.pay.AliConfig;
import com.viptrip.wetrip.pay.PayConfig;
import com.viptrip.wetrip.pay.alipay.api.AliPayApi;
import com.viptrip.wetrip.pay.alipay.old.AliPayServiceOld;
import com.viptrip.wetrip.pay.alipay.vo.AliPayNTFBeanNew;
import com.viptrip.wetrip.pay.alipay.vo.AliPayNTFBeanOld;
import com.viptrip.wetrip.pay.alipay.vo.AliPayRTNBeanOld;
import com.viptrip.wetrip.pay.alipay.vo.AliResp;

@Service
public class AliPayService {

	final static Logger log =  (Logger) LoggerFactory.getLogger(AliPayService.class);
	
	private static final String useOldInterface;
	
	private static final String default_useOldInterface = "true";
	
	
	static{
		String useOldInterface1;
		try {
			useOldInterface1 = PropertiesUtils.getProperty(Const.PRO_PAY_USE_OLD, Const.FILE_PAY_CONFIG);
		} catch (IOException e) {
			useOldInterface1 = default_useOldInterface;
			e.printStackTrace();
		}
		useOldInterface = useOldInterface1;
	}
	
	/**
	 * 支付宝支付
	 * @param response
	 * @param orderId 订单号
	 * @param price 价格
	 * @param title 
	 * @param body
	 * @throws AlipayApiException
	 * @throws IOException
	 */
	public void wapPay(HttpServletResponse response,String orderId,Double price,String title,String body,String baseUrl) throws AlipayApiException, IOException{
		if("true".equals(PayConfig.isTest)){
			price = 0.01d;
			title = "H5测试下单支付";
		}
		if("true".equals(useOldInterface)){
			AliPayServiceOld serviceOld = ApplicationContextHelper.getInstance().getBean(AliPayServiceOld.class);
			serviceOld.wapPay(response, orderId, price, title, body, baseUrl);
		}else{
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setOutTradeNo(orderId);
			model.setTotalAmount(Double.toString(price));
//		model.setProductCode(productCode);
			model.setSubject(title);
			model.setBody(body);
			String form = AliPayApi.wapPay(model, baseUrl + AliConfig.URL_RETURN_PAY,baseUrl +  AliConfig.URL_NOTIFY_PAY);
			//直接跳转到支付页面
			response.setContentType("text/html;charset=" + AliConfig.CHARSET.UTF8.value());
			response.getWriter().write(form);// 直接将完整的表单html输出到页面
			response.getWriter().flush();
		}
	}
	
	/**
	 * 获取支付回跳参数对象
	 * @param req
	 * @return
	 */
	public AliPayRTNBeanOld getReturnBeanOld(HttpServletRequest req){
		Map<String, String> params = getParam(req);
		if(log.isDebugEnabled()){
			log.debug("getReturnBean所有返回参数：" + JSON.get().toJson(params));
		}
		AliPayRTNBeanOld bean = JSON.get().fromJson(JSON.get().toJson(params), AliPayRTNBeanOld.class);
		return bean;
	}
	/**
	 * 获取支付回跳参数对象
	 * @param req
	 * @return
	 */
	public AliResp getReturnBean(HttpServletRequest req){
		Map<String, String> params = getParam(req);
		if(log.isDebugEnabled()){
			log.debug("getReturnBean所有返回参数：" + JSON.get().toJson(params));
		}
		AliResp bean = JSON.get().fromJson(JSON.get().toJson(params), AliResp.class);
		return bean;
	}
	
	/**
	 * 获取支付通知参数对象
	 * @param req
	 * @return
	 */
	public AliPayNTFBeanOld getNotifyBeanOld(HttpServletRequest req){
		Map<String, String> params = getParam(req);
		if(log.isDebugEnabled()){
			log.debug("getNotifyBean所有返回参数：" + JSON.get().toJson(params));
		}
		AliPayNTFBeanOld bean = JSON.get().fromJson(JSON.get().toJson(params), AliPayNTFBeanOld.class);
		return bean;
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
	
	
	public AlipayTradeQueryResponse query(String orderId) throws AlipayApiException{
		AlipayTradeQueryModel model = new AlipayTradeQueryModel();
		model.setOutTradeNo(orderId);
		return AliPayApi.tradeQuery(model);
	}
	
	/**
	 * 退款
	 * @param orderId
	 * @param price
	 * @param reason
	 * @return
	 * @throws AlipayApiException
	 */
	public void refund(String orderId,Double price,String reason) throws AlipayApiException{
		if("true".equals(PayConfig.isTest)){
			price = 0.01d;
			reason = "测试退款";
		}
		if("true".equals(useOldInterface)){
			AliPayServiceOld serviceOld = ApplicationContextHelper.getInstance().getBean(AliPayServiceOld.class);
			serviceOld.refund( orderId, price, reason);
		}else{
			AlipayTradeRefundModel model = new AlipayTradeRefundModel();
			model.setOutTradeNo(orderId);
			model.setRefundAmount(Double.toString(price));
//			return AliPayApi.tradeRefund(model);
		}
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
