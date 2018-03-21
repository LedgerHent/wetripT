package com.viptrip.wetrip.pay.alipay.old;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.pay.AliConfigOld;

@Service
public class AliPayServiceOld {
	private static Logger log = LoggerFactory.getLogger(AliPayServiceOld.class);
	
	public void wapPay(HttpServletResponse response,String orderId,Double price,String title,String body,String baseUrl) throws AlipayApiException, IOException{
		AlipayParam params = new AlipayParam();
		
		if("true".equals(AliConfigOld.isTest)){
			price = 0.01d;
		}
		
		params.setOrderNo(orderId);
		params.setTotalPrice(price.toString());
		params.setSubjectName(title);
		
		params = setBaseParams(params,baseUrl);
		
		String finalUrl= AliConfigOld.ALI_OLD_URL + "?" + addSign(params);
		if(log.isDebugEnabled()){
			log.debug("阿里支付地址为：" + finalUrl);
		}
		response.sendRedirect(finalUrl);
	}
	
	
	public void refund(String orderId,Double price,String reason){
		//TODO 退款接口待处理
	}
	
	/**
	 *  设置基础参数
	 * @param params
	 * @return
	 */
	private AlipayParam setBaseParams(AlipayParam params,String baseUrl){
		if(null!=params){
			//获取service参数
			params.setService(AliConfigOld.ALI_OLD_SERVICE);
			//获取partner参数
			params.setPartner(AliConfigOld.ALI_OLD_PARTNER);
			//获取payment_type参数
			params.setPaymentType(AliConfigOld.ALI_OLD_PAYMENT_TYPE);
			//获取seller_email参数
			params.setSellerAlipayNo(AliConfigOld.ALI_OLD_SELLER_EMAIL);
			//获取key参数
			params.setKey(AliConfigOld.ALI_OLD_KEY);
			//获取_input_charset参数
			params.setInputCharset(AliConfigOld.ALI_OLD_CHARSET);
			//获取notify_url参数
			params.setNotifyUrl(baseUrl + AliConfigOld.URL_NOTIFY_PAY);
			//获取return_url参数
			params.setReturnUrl(baseUrl + AliConfigOld.URL_RETURN_PAY);
		}
		return params;
	}
	
	/**
	 * 业务参数绑定
	 * @param params
	 * @param order
	 * @return
	 */
	public AlipayParam addServiceParam(AlipayParam params,TOrder order){
		params.setOrderNo(order.getOrderno());
	
		params.setTotalPrice(order.getTotalPrice()+"");
		params.setSubjectName("AirplaneTicket");
		
		return params;
	}
	/**
	 * 绑定MD5签名,得到参数字符串
	 * @param params
	 * @return
	 */
	private String addSign(AlipayParam params){
		Map<String,String> sParaTemp=PaymentUtil.turnToMap(params);
		sParaTemp=PaymentUtil.paraFilter(sParaTemp);
		String mySign=PaymentUtil.buildRequestMysign(sParaTemp, params);
		sParaTemp.put("sign_type", "MD5");
		sParaTemp.put("sign", mySign);
		return PaymentUtil.createLinkString(sParaTemp);
	}

}
