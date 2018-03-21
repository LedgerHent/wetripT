package com.viptrip.wetrip.service;

import com.viptrip.base.action.AjaxResp;
import com.viptrip.wetrip.vo.CaiyunOrderStatus;
import com.viptrip.wetrip.vo.CaiyunPayParaExt;
import com.viptrip.wetrip.vo.CaiyunResp;

public interface ICaiyunPayService {
	/**
	 * 彩云集团支付 先风控再回调
	 * @param userId 用户id
	 * @param orderId 订单id
	 * @param price 价格 单位为元
	 * @param extend 订单详情 json格式
	 * @return
	 */
	public CaiyunResp<?> prePay(Long userId,String orderId,Double price,String extend);
	
	/**
	 * 付款预检查
	 * @param userId 用户id
	 * @param orderId 订单id
	 * @param price 价格 单位为元
	 * @param extend 订单详情 json格式
	 * @return
	 */
	public CaiyunResp<?> prePayCheck(Long userId,String orderId,Double price,String extend);
	
	/**
	 * 订单回调
	 * @param userId
	 * @param para 参数
	 * @return
	 */
	public CaiyunResp<?> payCallBack(Long userId,CaiyunPayParaExt para);
	
	/**
	 * 订单回调
	 * @param userId
	 * @param orderId
	 * @param price
	 * @param extend
	 * @param status
	 * @return
	 */
	public CaiyunResp<?> payCallBack(Long userId,String orderId,Double price,Object extend,CaiyunOrderStatus status);
	
	/**
	 * 订单回调 支付成功
	 * @param userId
	 * @param orderId
	 * @param price
	 * @param extend
	 * @return
	 */
	public CaiyunResp<?> paySuccess(Long userId,String orderId,Double price,Object extend);
	
	/**
	 * 订单回调 退款成功
	 * @param userId
	 * @param orderId
	 * @param price
	 * @param extend
	 * @return
	 */
	public CaiyunResp<?> payRefund(Long userId,String orderId,Double price,Object extend);
	
	/**
	 * 订单回调 交易关闭
	 * @param userId
	 * @param orderId
	 * @param price
	 * @param extend
	 * @return
	 */
	public CaiyunResp<?> payClose(Long userId,String orderId,Double price,Object extend);
	
	/**
	 * 用户是否为彩云用户
	 * @param userId
	 * @return
	 */
	public AjaxResp isUserBelongsToUban360(Long userId);

}
