package com.viptrip.hotelHtml5.vo.tmc.request;

import java.util.List;

import etuf.v1_0.model.v2.base.Request_Base;

 /** 
 * @ClassName: Request_ListTmcOrder 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhaojian
 * @date 2017年12月1日 上午9:43:07 
 *
 */
public class Request_ListTmcOrder extends Request_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int pageNum = 1;
	private int pageSize = 10000;
	/**
	 * 总条数，  查审批列表时使用
	 */
	private int totalRows = 0;
	
	/**
	 * 查询类型   1、根据bookingUserId查询 ，用于全部订单列表   2、根据orderNoList查询，用于待审批列表 
	 */
	private Integer searchType;
	
	/**
	 * 订单号集合      若存在则 bookingUserId不使用
	 */
	private List<String> orderNoList;
	/**
	 * 订单状态CODE
	 */
	private String orderState;
	/**
	 * 预订人ID 若存在  orderNoList 不使用， 优先级高
	 */
	private String bookingUserId;
	
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getBookingUserId() {
		return bookingUserId;
	}
	public void setBookingUserId(String bookingUserId) {
		this.bookingUserId = bookingUserId;
	}
	public List<String> getOrderNoList() {
		return orderNoList;
	}
	public void setOrderNoList(List<String> orderNoList) {
		this.orderNoList = orderNoList;
	}
	public Integer getSearchType() {
		return searchType;
	}
	public void setSearchType(Integer searchType) {
		this.searchType = searchType;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	
}
