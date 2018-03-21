package com.viptrip.wetrip.service;

import java.util.Date;

public interface IPayService {
	
	
	/**
	 * 支付成功更改支付状态
	 * @param flag 业务类别：1-国内机票
	 * @param orderId 订单id
	 * @param payTime 支付时间
	 * @param tradeNo 交易号
	 * @param payType 支付方式  AP-为支付宝  WP-为微信
	 */
	public void paySuccessCheckOrderStatus(Integer flag, Long orderId,Date payTime,String tradeNo,String payType);
}
