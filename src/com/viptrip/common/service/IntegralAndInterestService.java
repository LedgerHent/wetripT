package com.viptrip.common.service;

import com.viptrip.common.model.Request_GetIntegral;
import com.viptrip.common.model.Request_GetInterest;
import com.viptrip.common.model.Request_IntegralConsumption;
import com.viptrip.common.model.Request_IntegralRefund;
import com.viptrip.common.model.Request_InterestRefund;
public interface IntegralAndInterestService {
	
	
	/**
	 * 积分查询
	 * @param para
	 * @return
	 */
	public abstract int gettotalIntegral(Request_GetIntegral para);
	/**
	 * 积分消费
	 * @param para
	 * @return
	 */
	public abstract String integralConsumption(Request_IntegralConsumption para);
	/**
	 * 返还积分
	 * @param para
	 * @return
	 */
	public abstract String backIntegration(Request_IntegralRefund para);
	/**
	 * 利息查询
	 * @param para
	 * @return
	 */
	public abstract double gettotalInterest(Request_GetInterest para);
	/**
	 * 利息消费
	 * @param para
	 * @return
	 */
	public abstract String useInterest(Request_IntegralConsumption para);
	/**
	 * 返还利息
	 * @param para
	 * @return
	 */
	public abstract String refundInterest(Request_InterestRefund para);
	
}
