package com.viptrip.hotelHtml5.vo.tmc.response;

import etuf.v1_0.model.v2.base.Response_Base;

 /** 
 * @ClassName: Response_SaveHotelOrder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * 	Tmc酒店接口已经返回对应错误码和信息，为方便使用在response类中加入酒店返回码和酒店信息字段
 * 
 * @author zhaojian
 * @date 2017年10月30日 上午10:09:41 
 *
 */
public class Response_SaveTmcOrder extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String hotelCode;
	/**
	 * 
	 */
	private String hotelMsg;
	
	private OrderResponse data;
	
	public static class OrderResponse{
		/**
		 * 
		 */
		private String orderNo;
		/**
		 * 订单总金额
		 */
		private double orderTotalAmount;
		/**
		 * 服务费（包含预订费和夜间费）
		 */
		private double serviceFee;
		/**
		 * 订单状态Code
		 */
		private String orderStatue;
		/**
		 * 订单状态名称
		 */
		private String orderStatueName;
		/**
		 * 
		 */
		private String bookingUserId;
		/**
		 * 订单实际应付金额（总额 - 优惠金额） （优惠金额包括积分/利息     将来可能还有优惠券）
		 */
		private Double actualPayablePrice;
		
		public String getOrderNo() {
			return orderNo;
		}
		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}
		public double getOrderTotalAmount() {
			return orderTotalAmount;
		}
		public void setOrderTotalAmount(double orderTotalAmount) {
			this.orderTotalAmount = orderTotalAmount;
		}
		public double getServiceFee() {
			return serviceFee;
		}
		public void setServiceFee(double serviceFee) {
			this.serviceFee = serviceFee;
		}
		public String getOrderStatue() {
			return orderStatue;
		}
		public void setOrderStatue(String orderStatue) {
			this.orderStatue = orderStatue;
		}
		public String getOrderStatueName() {
			return orderStatueName;
		}
		public void setOrderStatueName(String orderStatueName) {
			this.orderStatueName = orderStatueName;
		}
		public String getBookingUserId() {
			return bookingUserId;
		}
		public void setBookingUserId(String bookingUserId) {
			this.bookingUserId = bookingUserId;
		}
		public Double getActualPayablePrice() {
			return actualPayablePrice;
		}
		public void setActualPayablePrice(Double actualPayablePrice) {
			this.actualPayablePrice = actualPayablePrice;
		}
	}

	public String getHotelCode() {
		return hotelCode;
	}

	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	public String getHotelMsg() {
		return hotelMsg;
	}

	public void setHotelMsg(String hotelMsg) {
		this.hotelMsg = hotelMsg;
	}

	public OrderResponse getData() {
		return data;
	}

	public void setData(OrderResponse data) {
		this.data = data;
	}
	
}
