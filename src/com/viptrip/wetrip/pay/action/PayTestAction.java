package com.viptrip.wetrip.pay.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.viptrip.base.action.BaseAction;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.AliConfig;
import com.viptrip.wetrip.pay.alipay.old.AliPayServiceOld;
import com.viptrip.wetrip.pay.alipay.service.AliPayService;
import com.viptrip.wetrip.pay.alipay.vo.AliPayRTNBeanOld;


public class PayTestAction extends BaseAction{
	private static Logger log = LoggerFactory.getLogger(PayTestAction.class);
	@Resource
	private AliPayService aliService;
	@Resource
	private AliPayServiceOld aliServiceO;
	
	/**
	 * 测试支付
	 * @param orderId
	 * @param price
	 * @param title
	 * @param body
	 */
	@RequestMapping("/testpay.act")
	@ResponseBody
	public void aliPay(String orderId,Double price,String title,String body){
		if(null==price || price>0.01){
			price = 0.01;
		}
		try {
			//aliService.wapPay(resp, orderId, price, title, body);
			aliServiceO.wapPay(resp, orderId, price, title, body, baseURL);
		} catch (Exception e) {
			try {
				resp.sendRedirect(AliConfig.URL_RETURN_PAY);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 支付回跳页面
	 */
	@RequestMapping("/alirtn.act")
	public void aliRtnAction(String out_trade_no,String trade_no,String trade_status){
		System.out.println("PayTestAction.aliRtnAction()==out_trade_no==" + out_trade_no);
		System.out.println("PayTestAction.aliRtnAction()==trade_no==" + trade_no);
		System.out.println("PayTestAction.aliRtnAction()==trade_status==" + trade_status);
		Map<String,String> params = getParam(req);
		System.out.println("PayTestAction.aliRtnAction()==params==" + JSON.get().toJson(params));
		AliPayRTNBeanOld bean = JSON.get().fromJson(JSON.get().toJson(params), AliPayRTNBeanOld.class);
		System.out.println("PayTestAction.aliRtnAction()==bean==" + JSON.get().toJson(bean));
		boolean verify_result = false;
		try {
			verify_result = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), "RSA2");
			System.out.println("PayTestAction.aliRtnAction()==verify_result==" + verify_result);
			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码
				//该页面可做页面美工编辑
				//out.clear();
				//out.println("验证成功<br />");
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{
				//该页面可做页面美工编辑
				//out.clear();
				//out.println("验证失败");
			}
		} catch (AlipayApiException e) {
			log.error("支付宝支付验证异常\r\n" + StringUtil.getExceptionStr(e));
		}
		
	}
	
	/**
	 * 支付通知页面
	 * @param out_trade_no
	 * @param trade_no
	 * @param trade_status
	 */
	@RequestMapping("/alintf.act")
	public void aliNtfAction(String out_trade_no,String trade_no,String trade_status){
		System.out.println("PayTestAction.aliNtfAction()==out_trade_no==" + out_trade_no);
		System.out.println("PayTestAction.aliNtfAction()==trade_no==" + trade_no);
		System.out.println("PayTestAction.aliNtfAction()==trade_status==" + trade_status);
		Map<String,String> params = getParam(req);
		System.out.println("PayTestAction.aliNtfAction()==params==" + JSON.get().toJson(params));
		boolean verify_result = false;
		try {
			verify_result = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), "RSA2");
			System.out.println("PayTestAction.aliNtfAction()==verify_result==" + verify_result);
			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//业务逻辑程序代码
				//
				//out.clear();
				//out.println("验证成功<br />");
				//

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{
				//
				//out.clear();
				//out.println("验证失败");
			}
		} catch (AlipayApiException e) {
			log.error("支付宝支付验证异常\r\n" + StringUtil.getExceptionStr(e));
		}
	}
	
	public Map<String,String> getParam(HttpServletRequest req){
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = req.getParameterMap();
		for (Iterator<?> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}
	
}
