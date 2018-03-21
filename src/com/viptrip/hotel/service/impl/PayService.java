package com.viptrip.hotel.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.viptrip.hotel.entity.HotelPayInfo;
import com.viptrip.hotel.service.IHotelPayInfoService;
import com.viptrip.hotel.service.IPayService;
import com.viptrip.hotel.util.PayConfig;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.AliConfig;
import com.viptrip.wetrip.pay.alipay.api.AliPayApi;

@Service("hotelPayService")
public class PayService implements IPayService{
	
	private static Logger logger = LoggerFactory.getLogger(PayService.class);
	
	@Resource
	private IHotelPayInfoService service;
	
	@Override
	public String alipay(String orderno, Double amount, String rtnurl,
			String ntfurl, String subject, String body, String nonce) {
		String result = null;
		if("true".equals(PayConfig.isTest)){
			amount = 0.01D;
		}
		AlipayTradePagePayModel model = new AlipayTradePagePayModel();
		model.setProductCode("FAST_INSTANT_TRADE_PAY");
		model.setOutTradeNo(orderno);
		model.setTotalAmount(amount.toString());
		model.setSubject(subject);
		model.setBody(body);
		logger.debug("alipay().model=" + JSON.get().toJson(model));
		//model.setPassbackParams(nonce);
		try {
			result = AliPayApi.tradePage(model , PayConfig.ali_ntfurl, PayConfig.ali_rtnurl);
			
			if(StringUtil.isEmpty(ntfurl)){
				ntfurl = PayConfig.ali_ntfurl_r;
			}
			if(StringUtil.isEmpty(rtnurl)){
				rtnurl = PayConfig.ali_rtnurl_r;
			}
			amount *= 100;
			HotelPayInfo hpi = service.getHotelPayInfoByOrderno(orderno);
			if(hpi==null){
				hpi = new HotelPayInfo(orderno, amount.intValue(), subject, body, nonce, ntfurl, rtnurl, 1);
				hpi.setStatus(1);//待支付
				service.save(hpi);//保存支付信息
			}
			if(logger.isDebugEnabled()){
				logger.debug("PayService.alipay result=" + result);
			}
		} catch (AlipayApiException | IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		return result;
	}
	
	
	public AlipayTradeRefundResponse aliRefund(String orderno,Double refundAmount,String reason,String subno){
		if(logger.isDebugEnabled()){
			logger.debug("com.viptrip.hotel.service.impl.PayService.aliRefund()被调用，参数为orderno=" + orderno + ",refundAmount=" + refundAmount + ",reason=" + reason + ",subno=" + subno);
		}
		AlipayTradeRefundResponse resp = null;
		AlipayTradeRefundModel model = new AlipayTradeRefundModel();
		model.setOutTradeNo(orderno);
		if(!StringUtil.isEmpty(subno)){
			model.setOutRequestNo(subno);
		}
		model.setRefundAmount(refundAmount.toString());
		model.setRefundReason(reason);
		try {
			resp = AliPayApi.tradeRefundToResponse(model);
			
			Map<String, String> params = resp.getParams();
			boolean sign = AlipaySignature.rsaCheckV1(params, AliConfig.ALI_PUBLIC_KEY, AliConfig.CHARSET.UTF8.value(), AliConfig.SIGN_TYPE);
			if(!sign){
				resp = new AlipayTradeRefundResponse();
				resp.setCode("-2");
				resp.setMsg("调用支付宝退款接口错误");
			}
		} catch (AlipayApiException e) {
			logger.error(StringUtil.getExceptionStr(e));
			resp = new AlipayTradeRefundResponse();
			resp.setCode("-1");
			resp.setMsg("调用支付宝退款接口异常");
		}
		if(logger.isDebugEnabled()){
			logger.debug("com.viptrip.hotel.service.impl.PayService.aliRefund()被调用，返回结果为：" + JSON.get().notEscapeHTML(true).toJson(resp));
		}
		return resp;
	}
	
	public AlipayTradeFastpayRefundQueryResponse aliRefundQuery(String orderno,String subno){
		if(logger.isDebugEnabled()){
			logger.debug("com.viptrip.hotel.service.impl.PayService.aliRefundQuery()被调用，参数为orderno=" + orderno + ",subno=" + subno);
		}
		AlipayTradeFastpayRefundQueryResponse resp = null;
		AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
		model.setOutTradeNo(orderno);
		if(!StringUtil.isEmpty(subno)){
			model.setOutRequestNo(subno);
		}
		try {
			resp = AliPayApi.tradeRefundQueryToResponse(model);
		} catch (AlipayApiException e) {
			logger.error(StringUtil.getExceptionStr(e));
			resp = new AlipayTradeFastpayRefundQueryResponse();
			resp.setCode("-1");
			resp.setMsg("调用支付宝退款查询接口异常");
		}
		if(logger.isDebugEnabled()){
			logger.debug("com.viptrip.hotel.service.impl.PayService.aliRefundQuery()被调用，返回结果为：" + JSON.get().notEscapeHTML(true).toJson(resp));
		}
		return resp;
	}
	
	
	public static void main(String[] args) {
		AlipayTradeRefundResponse resp = new AlipayTradeRefundResponse();
		//resp.setErrorCode("-1");
		//resp.setCode("-2");
		//resp.setMsg("调用支付宝退款接口错误");
		boolean success = resp.isSuccess();
		System.out.println(success);
		
	}
	
}
