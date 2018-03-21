package com.viptrip.hotel.service;

import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;


public interface IPayService {
	/**
	 * 支付宝支付 单位元
	 * @param orderno 订单号
	 * @param amount 支付金额 单位元
	 * @param ali_rtnurl 回跳地址
	 * @param ali_ntfurl 异步通知地址
	 * @param subject 
	 * @param body
	 * @param nonce
	 * @return
	 */
	public String alipay(String orderno,Double amount,String rtnurl,String ntfurl,String subject,String body,String nonce);
	
	/**
	 * 支付宝退款
	 * @param orderno 订单号
	 * @param refundAmount 退款金额 单位元
	 * @param reason 退款理由
	 * @param subno 退款序号
	 * @return
	 */
	public AlipayTradeRefundResponse aliRefund(String orderno,Double refundAmount,String reason,String subno);
	
	/**
	 * 支付宝退款查询
	 * @param orderno 订单号
	 * @param subno 退款序号
	 * @return
	 */
	public AlipayTradeFastpayRefundQueryResponse aliRefundQuery(String orderno,String subno);
	
}
