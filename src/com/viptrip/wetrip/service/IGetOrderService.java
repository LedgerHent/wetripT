package com.viptrip.wetrip.service;


import com.viptrip.wetrip.entity.TOrder;
import com.viptrip.wetrip.model.Request_GetOrderDetail;
import com.viptrip.wetrip.model.Request_GetOrderList;
import com.viptrip.wetrip.model.Response_GetOrderDetail;
import com.viptrip.wetrip.model.Response_GetOrderList;

public interface IGetOrderService {
	/**
	 * 获取订单列表
	 * @param para
	 * @return
	 */
	public Response_GetOrderList getOrderList(Request_GetOrderList para);
	
	/**
	 * 获取订单详情
	 * @param para
	 * @return
	 */
	public Response_GetOrderDetail getOrderDetail(Request_GetOrderDetail para);
	
	/**
	 * 通过id查找
	 * @param orderId
	 * @return
	 */
	public TOrder getOrderById(Long orderId);
	
	/**
	 * 通过订单号查找
	 * @param orderno
	 * @return
	 */
	public TOrder getOrderByOrderno(String orderno);
}
