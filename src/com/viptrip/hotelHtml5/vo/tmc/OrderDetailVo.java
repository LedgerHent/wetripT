package com.viptrip.hotelHtml5.vo.tmc;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class OrderDetailVo extends OrderInfoRequestVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1269928527582581981L;
	
	/**
	 * 订单状态名称（待审核、待支付、待确认、订单完成、订单取消、待退款、退订中、退款完成、结算完成）
	 */
	private String orderStatusName;
	/**
	 * 订单支付状态名称（未支付、支付失败、支付成功、退款失败、退款成功）
	 */
	private String payStatusName;
	/**
	 * 订单审批状态名称（未审核、审核通过、审核驳回）
	 */
	private String approveStatusName;
	/**
	 * 支付方式名称（个人支付、公司月结、公司现结、公司预付）
	 */
	private String payTypeName;
	/**
	 * 出行类型名称
	 */
	private String tripTypeName;
	
	/**
	 * 审批规则（旧版，暂不用）
	 */
	private TmcApprovePolicy tmcApprovePolicy;
	/**
	 * 历史短信
	 */
	private List<TmcOrderShortMsgHistroryVo> tmcOrderShortMsgHistroryList;
	
	/**
	 * 是否展示预订信息（暂不需要）
	 */
	@Deprecated
	private boolean isShowBooked = false;
	/**
	 * 是否展示退订信息（根据是否有退订日志、且退订成功（没有被驳回），订单状态为退订中、待退款、退款完成、结算完成）
	 * 默认false
	 */
	private boolean isShowUnsubscribe = false;
	/**
	 * 是否常规审批（默认常规）
	 */
	private boolean isUsuallyRule = true;
	/**
	 * 订单信息中的交易金额
	 */
	private double orderTransactionAmount;
	/**
	 * 供应商信息中的交易金额
	 */
	private double supplierTransactionAmount;
	/**
	 * 接口酒店返回的价格（房费+税费）
	 */
	private double hotelRoomPrice;
	/**
	 * 房间费（不含税）
	 */
	private double roomPrice;
	
	/**
	 * 住几晚   （间夜数 / 房间数）
	 */
	private Integer nithtCount;
	
	
	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	public String getPayStatusName() {
		return payStatusName;
	}
	public void setPayStatusName(String payStatusName) {
		this.payStatusName = payStatusName;
	}
	public String getApproveStatusName() {
		return approveStatusName;
	}
	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}
	public String getPayTypeName() {
		return payTypeName;
	}
	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}
	public String getTripTypeName() {
		return tripTypeName;
	}
	public void setTripTypeName(String tripTypeName) {
		this.tripTypeName = tripTypeName;
	}
	public TmcApprovePolicy getTmcApprovePolicy() {
		return tmcApprovePolicy;
	}
	public void setTmcApprovePolicy(TmcApprovePolicy tmcApprovePolicy) {
		this.tmcApprovePolicy = tmcApprovePolicy;
	}
	public List<TmcOrderShortMsgHistroryVo> getTmcOrderShortMsgHistroryList() {
		return tmcOrderShortMsgHistroryList;
	}
	public void setTmcOrderShortMsgHistroryList(List<TmcOrderShortMsgHistroryVo> tmcOrderShortMsgHistroryList) {
		this.tmcOrderShortMsgHistroryList = tmcOrderShortMsgHistroryList;
	}
	public boolean isShowBooked() {
		return isShowBooked;
	}
	public void setShowBooked(boolean isShowBooked) {
		this.isShowBooked = isShowBooked;
	}
	public boolean isShowUnsubscribe() {
		return isShowUnsubscribe;
	}
	public void setShowUnsubscribe(boolean isShowUnsubscribe) {
		this.isShowUnsubscribe = isShowUnsubscribe;
	}
	public boolean isUsuallyRule() {
		return isUsuallyRule;
	}
	public void setUsuallyRule(boolean isUsuallyRule) {
		this.isUsuallyRule = isUsuallyRule;
	}
	public double getOrderTransactionAmount() {
		return orderTransactionAmount;
	}
	public void setOrderTransactionAmount(double orderTransactionAmount) {
		this.orderTransactionAmount = orderTransactionAmount;
	}
	public double getSupplierTransactionAmount() {
		return supplierTransactionAmount;
	}
	public void setSupplierTransactionAmount(double supplierTransactionAmount) {
		this.supplierTransactionAmount = supplierTransactionAmount;
	}
	public double getHotelRoomPrice() {
		return hotelRoomPrice;
	}
	public void setHotelRoomPrice(double hotelRoomPrice) {
		this.hotelRoomPrice = hotelRoomPrice;
	}
	public double getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(double roomPrice) {
		this.roomPrice = roomPrice;
	}
	public Integer getNithtCount() {
		return nithtCount;
	}
	public void setNithtCount(Integer nithtCount) {
		this.nithtCount = nithtCount;
	}

	public String getTripPolicyText(){
		String tripPolicyText = "";
		if(getTmcOrderTripPolicy()!=null){
			Double priceLimit = getTmcOrderTripPolicy().getPriceLimit();
			if(priceLimit!=null && priceLimit!=0){
				tripPolicyText = "金额上限"+priceLimit+"元";
			}else{
				tripPolicyText = "金额上限不限制";
			}
			tripPolicyText+=",";
			Integer starLimit = getTmcOrderTripPolicy().getStarLimit();
			if(starLimit!=null && starLimit<999){
				if(starLimit == 0){
					tripPolicyText += "星级上限无星级";
				}else if(starLimit == 1){
					tripPolicyText += "星级上限一星级";
				}else if(starLimit == 2){
					tripPolicyText += "星级上限二星级";
				}else if(starLimit == 3){
					tripPolicyText += "星级上限三星级";
				}else if(starLimit == 4){
					tripPolicyText += "星级上限四星级";
				}else if(starLimit >= 5){
					tripPolicyText += "星级上限五星级";
				}
			}else{
				tripPolicyText += "星级上限不限制";
			}
		}
		//金额上限XX元，星级上限X星级
		return tripPolicyText;
	}
	
	public String getLimitMemo(){
		String limitMemo = "符合差旅标准";
		if(getTmcOrderTripPolicy()!=null){
			Integer starLimit = getTmcOrderTripPolicy().getStarLimit();
			if(StringUtils.isNotEmpty(getTmcOrderHotel().getStar()) && starLimit!=null && starLimit< Integer.parseInt(getTmcOrderHotel().getStar())){
				String hotelStarDesc = "";
				if(starLimit == 0){
					hotelStarDesc = "无星";
				}else if(starLimit == 1){
					hotelStarDesc = "一星";
				}else if(starLimit == 2){
					hotelStarDesc = "二星";
				}else if(starLimit == 3){
					hotelStarDesc = "三星";
				}else if(starLimit == 4){
					hotelStarDesc = "四星";
				}else if(starLimit >= 5){
					hotelStarDesc = "五星";
				}
				limitMemo = "星级上限"+hotelStarDesc+"级已超出";
			}else{
				Double priceLimit = getTmcOrderTripPolicy().getPriceLimit();
				if(priceLimit!=null && priceLimit!=0 && priceLimit<getTmcOrderHotel().getBookingPrice()){
					limitMemo = "金额上限"+priceLimit+"元已超出";
				}
			}
		}
		return limitMemo;
	}
}
