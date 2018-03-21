package com.viptrip.hotelHtml5.vo.tmc;

import java.util.List;

public class OrderInfoRequestVo extends TmcOrderInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2711100691941692221L;
	
	/**
	 * 订单酒店信息
	 */
	private TmcOrderHotel tmcOrderHotel;
	
	/**
	 * 差旅政策 
	 */
	private TmcOrderTripPolicy tmcOrderTripPolicy;
	
	/**
	 * 订单联系人
	 */
	private List<TmcOrderContacts> tmcOrderContactsList;

	/**
	 * 订单客人
	 */
	private List<TmcOrderGuest> tmcOrderGuestList;
	
	/**
	 * 订单费用归属
	 */
	private List<TmcOrderCostShare> tmcOrderCostShareList;
	
	/**
	 * 订单服务费（暂无用）
	 */
	private List<TmcOrderServiceFee> tmcOrderServiceFeeList;
	
	/**
	 * 订单审批
	 */
	private List<TmcOrderApprFlow> tmcOrderApprFlowList;
	
	/**
	 * 每日价格
	 */
	private List<TmcOrderDayPrice> tmcOrderDayPriceList;
	
	private List<TmcOrderLog> tmcOrderLogList;

	public TmcOrderHotel getTmcOrderHotel() {
		return tmcOrderHotel;
	}

	public void setTmcOrderHotel(TmcOrderHotel tmcOrderHotel) {
		this.tmcOrderHotel = tmcOrderHotel;
	}

	public TmcOrderTripPolicy getTmcOrderTripPolicy() {
		return tmcOrderTripPolicy;
	}

	public void setTmcOrderTripPolicy(TmcOrderTripPolicy tmcOrderTripPolicy) {
		this.tmcOrderTripPolicy = tmcOrderTripPolicy;
	}

	public List<TmcOrderContacts> getTmcOrderContactsList() {
		return tmcOrderContactsList;
	}

	public void setTmcOrderContactsList(List<TmcOrderContacts> tmcOrderContactsList) {
		this.tmcOrderContactsList = tmcOrderContactsList;
	}

	public List<TmcOrderGuest> getTmcOrderGuestList() {
		return tmcOrderGuestList;
	}

	public void setTmcOrderGuestList(List<TmcOrderGuest> tmcOrderGuestList) {
		this.tmcOrderGuestList = tmcOrderGuestList;
	}

	public List<TmcOrderCostShare> getTmcOrderCostShareList() {
		return tmcOrderCostShareList;
	}

	public void setTmcOrderCostShareList(List<TmcOrderCostShare> tmcOrderCostShareList) {
		this.tmcOrderCostShareList = tmcOrderCostShareList;
	}

	public List<TmcOrderServiceFee> getTmcOrderServiceFeeList() {
		return tmcOrderServiceFeeList;
	}

	public void setTmcOrderServiceFeeList(List<TmcOrderServiceFee> tmcOrderServiceFeeList) {
		this.tmcOrderServiceFeeList = tmcOrderServiceFeeList;
	}

	public List<TmcOrderApprFlow> getTmcOrderApprFlowList() {
		return tmcOrderApprFlowList;
	}

	public void setTmcOrderApprFlowList(List<TmcOrderApprFlow> tmcOrderApprFlowList) {
		this.tmcOrderApprFlowList = tmcOrderApprFlowList;
	}

	public List<TmcOrderDayPrice> getTmcOrderDayPriceList() {
		return tmcOrderDayPriceList;
	}

	public void setTmcOrderDayPriceList(List<TmcOrderDayPrice> tmcOrderDayPriceList) {
		this.tmcOrderDayPriceList = tmcOrderDayPriceList;
	}

	public List<TmcOrderLog> getTmcOrderLogList() {
		return tmcOrderLogList;
	}

	public void setTmcOrderLogList(List<TmcOrderLog> tmcOrderLogList) {
		this.tmcOrderLogList = tmcOrderLogList;
	}

	
}
