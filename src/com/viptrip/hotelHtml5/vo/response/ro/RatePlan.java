package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RatePlan implements Serializable{

	/**
	 * 酒店 ID
	 */
	private String hotelId;

	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * rpID
	 */
	private String ratePlanId;
	
	/**
	 * rpName
	 */
	private String rpName;

	/**
	 * 入住日期
	 */
	private Date checkStartDate;
	/**
	 * 入住日期(yy-MM-dd)
	 */
	private String checkStartDateStr;

	/**
	 * 入住时长
	 */
	private Integer nightCount;

	/**
	 * 成人数量
	 */
	private Integer adultCount;
	
	/**
	 * 儿童年龄列表(从低到高)
	 */
	private String childAgeDl;	
	
	/**
	 * 税费总额
	 */
	private Double totalTaxPrice = 0d;

	/**
	 * 供应商代码(大写) 
	 */
	private String supplierCode;	
	

	/**
	 * 付费标志，1-预付, 2-现付， 3-担保
	 */	
	private String payType;	

	/**
	 * 最大入住人数
	 */
	private Integer occupancyMax = 1;
	
	/**
	 * 税后总房价
	 */
	private Double totleRoomPrice =0d;
	
	/**
	 * added by yuxingfei
	 * 人民币总价
	 * 
 	 */	
	private Double totleRoomPriceCNY =0d;
	
	/**
	 * 税费总额
	 */
	private Double totalTaxPriceCNY = 0d;	

	
	/**
	 * 货币符号
	 */
	private String currency;
	
	/**
	 * 汇率（不需要传值）
	 * zhangxiangwei加（2016/1/18）数据库中存。
	 * 
	 */
	private BigDecimal currencyRate;
	
	
	
	/**
	 * 订单变更政策公式
	 */
	private String orderChangePolicyDesc;
	/**
	 * 酒店变更政策公式
	 */
	private String hotelChangePolicyDesc;
	/**
	 * 订单取消公式
	 */
	private String orderCancelPolicyDesc;
	/**
	 * 酒店取消政策公式
	 */
	private String hotelCancelPolicyDesc;
	
	private Double extraPersonPrice = 0d; // 加人价
	private Double taxRate = 0d; // 税率
	private String  hasBreakfast;        //是否含早餐
	private String  hasLunch;            //是否含午餐
	private String  hasSupper;           //是否含晚餐
	private String  hasWine;             //是否含酒水
	private Integer breakfastNumber = 0; // 早餐份数
	private Integer lunchNumber = 0; // 午餐份数
	private Integer supperNumber = 0; // 晚餐份数
	
	private Integer childCount = 0; // 孩子总数，当儿童年龄不为0时，应读取预订条件的儿童年龄详情，若为0表示价格中不含儿童价格
	private String  freeCancel;			 //1为可以免费取消 0为不能免费取消  ->修改为0：收费取消；1:限时免费取消；2：不可取消
	private String  freeCancelEndDate;	 //最后免费取消日期（yyyy-MM-dd）为1的时候用的
	
	private String occupancy;           //入住人数
	private String childAddBed;         //儿童及加床
	private String orderStartTime;      //预订开始时间00:00
	private String orderEndTime;        //预订截止时间00:00
	
	/**
	 * 产品参考价格
	 */
	//private ProductPrice productPrice;
	private List<ProductPrice> productPriceDays;
	
	private String phk;
	
	private Integer orderOccupancyMax = 1;
	private Integer orderRoomsMax = 1;
	private Double dayAvgRoomPriceCNY =0d;
	private Double dayAvgRoomPrice =0d;
	
	private Integer roomCount;	   
	private Integer roomToSell;  
	private String  roomTotal="0";       //总房量
	private String pageTempletFlag;
	
	/**
	 * 订单填写页面上的变更取消描述
	 */
	private String orderPageChangeAndCancelDesc;
	private String  itemForFree;         //免费待遇项
	private String  drrOverallDesc;         //drr总体描述
	
	/**
	 * 最少预订房间数量
	 */
	private Integer minRoom;
	private String networkDesc;
	
	private Double dayAvgRoomCostCNY;
	private Double dayAvgRoomCost =0d;
	private Double totleRoomCost =0d;
	private Double totleRoomCostCNY =0d;
	
	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Date getCheckStartDate() {
		return checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}
	
	public String getCheckStartDateStr() {
		return checkStartDateStr;
	}

	public void setCheckStartDateStr(String checkStartDateStr) {
		this.checkStartDateStr = checkStartDateStr;
	}

	public Integer getNightCount() {
		return nightCount;
	}

	public void setNightCount(Integer nightCount) {
		this.nightCount = nightCount;
	}

	

	

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public String getChildAgeDl() {
		return childAgeDl;
	}

	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}

	public Double getTotalTaxPrice() {
		return totalTaxPrice;
	}

	public void setTotalTaxPrice(Double totalTaxPrice) {
		this.totalTaxPrice = totalTaxPrice;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getOccupancyMax() {
		return occupancyMax;
	}

	public void setOccupancyMax(Integer occupancyMax) {
		this.occupancyMax = occupancyMax;
	}

	public Double getTotleRoomPrice() {
		return totleRoomPrice;
	}

	public void setTotleRoomPrice(Double totleRoomPrice) {
		this.totleRoomPrice = totleRoomPrice;
	}

	public Double getTotleRoomPriceCNY() {
		return totleRoomPriceCNY;
	}

	public void setTotleRoomPriceCNY(Double totleRoomPriceCNY) {
		this.totleRoomPriceCNY = totleRoomPriceCNY;
	}

	public Double getTotalTaxPriceCNY() {
		return totalTaxPriceCNY;
	}

	public void setTotalTaxPriceCNY(Double totalTaxPriceCNY) {
		this.totalTaxPriceCNY = totalTaxPriceCNY;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(BigDecimal currencyRate) {
		this.currencyRate = currencyRate;
	}

	public String getOrderChangePolicyDesc() {
		return orderChangePolicyDesc;
	}

	public void setOrderChangePolicyDesc(String orderChangePolicyDesc) {
		this.orderChangePolicyDesc = orderChangePolicyDesc;
	}

	public String getHotelChangePolicyDesc() {
		return hotelChangePolicyDesc;
	}

	public void setHotelChangePolicyDesc(String hotelChangePolicyDesc) {
		this.hotelChangePolicyDesc = hotelChangePolicyDesc;
	}

	public String getOrderCancelPolicyDesc() {
		return orderCancelPolicyDesc;
	}

	public void setOrderCancelPolicyDesc(String orderCancelPolicyDesc) {
		this.orderCancelPolicyDesc = orderCancelPolicyDesc;
	}

	public String getHotelCancelPolicyDesc() {
		return hotelCancelPolicyDesc;
	}

	public void setHotelCancelPolicyDesc(String hotelCancelPolicyDesc) {
		this.hotelCancelPolicyDesc = hotelCancelPolicyDesc;
	}

	public Double getExtraPersonPrice() {
		return extraPersonPrice;
	}

	public void setExtraPersonPrice(Double extraPersonPrice) {
		this.extraPersonPrice = extraPersonPrice;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public String getHasBreakfast() {
		return hasBreakfast;
	}

	public void setHasBreakfast(String hasBreakfast) {
		this.hasBreakfast = hasBreakfast;
	}

	public String getHasLunch() {
		return hasLunch;
	}

	public void setHasLunch(String hasLunch) {
		this.hasLunch = hasLunch;
	}

	public String getHasSupper() {
		return hasSupper;
	}

	public void setHasSupper(String hasSupper) {
		this.hasSupper = hasSupper;
	}

	public String getHasWine() {
		return hasWine;
	}

	public void setHasWine(String hasWine) {
		this.hasWine = hasWine;
	}

	public Integer getBreakfastNumber() {
		return breakfastNumber;
	}

	public void setBreakfastNumber(Integer breakfastNumber) {
		this.breakfastNumber = breakfastNumber;
	}

	public Integer getLunchNumber() {
		return lunchNumber;
	}

	public void setLunchNumber(Integer lunchNumber) {
		this.lunchNumber = lunchNumber;
	}

	public Integer getSupperNumber() {
		return supperNumber;
	}

	public void setSupperNumber(Integer supperNumber) {
		this.supperNumber = supperNumber;
	}

	public Integer getChildCount() {
		return childCount;
	}

	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}

	public String getFreeCancel() {
		return freeCancel;
	}

	public void setFreeCancel(String freeCancel) {
		this.freeCancel = freeCancel;
	}

	public String getFreeCancelEndDate() {
		return freeCancelEndDate;
	}

	public void setFreeCancelEndDate(String freeCancelEndDate) {
		this.freeCancelEndDate = freeCancelEndDate;
	}

	public String getOccupancy() {
		return occupancy;
	}

	public void setOccupancy(String occupancy) {
		this.occupancy = occupancy;
	}

	public String getChildAddBed() {
		return childAddBed;
	}

	public void setChildAddBed(String childAddBed) {
		this.childAddBed = childAddBed;
	}

	public String getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(String orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public String getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(String orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public List<ProductPrice> getProductPriceDays() {
		return productPriceDays;
	}

	public void setProductPriceDays(List<ProductPrice> productPriceDays) {
		this.productPriceDays = productPriceDays;
	}

	public String getPhk() {
		return phk;
	}

	public void setPhk(String phk) {
		this.phk = phk;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public Integer getOrderOccupancyMax() {		
		return orderOccupancyMax;
	}

	public void setOrderOccupancyMax(Integer orderOccupancyMax) {
		this.orderOccupancyMax = orderOccupancyMax;
	}

	public Integer getOrderRoomsMax() {
		
		return orderRoomsMax;
	}

	public void setOrderRoomsMax(Integer orderRoomsMax) {		 
		this.orderRoomsMax = orderRoomsMax;
	}

	public Double getDayAvgRoomPriceCNY() {		
		
		return dayAvgRoomPriceCNY;
	}

	public void setDayAvgRoomPriceCNY(Double dayAvgRoomPriceCNY) {
		this.dayAvgRoomPriceCNY = dayAvgRoomPriceCNY;
	}

	public Double getDayAvgRoomPrice() {		
		return dayAvgRoomPrice;
	}

	public void setDayAvgRoomPrice(Double dayAvgRoomPrice) {
		this.dayAvgRoomPrice = dayAvgRoomPrice;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	public Integer getRoomToSell() {
		return roomToSell;
	}

	public void setRoomToSell(Integer roomToSell) {
		this.roomToSell = roomToSell;
	}

	public String getRoomTotal() {
		return roomTotal;
	}

	public void setRoomTotal(String roomTotal) {
		this.roomTotal = roomTotal;
	}

	public String getPageTempletFlag() {
		return pageTempletFlag;
	}

	public void setPageTempletFlag(String pageTempletFlag) {
		this.pageTempletFlag = pageTempletFlag;
	}

	public String getOrderPageChangeAndCancelDesc() {
		return orderPageChangeAndCancelDesc;
	}

	public void setOrderPageChangeAndCancelDesc(String orderPageChangeAndCancelDesc) {
		this.orderPageChangeAndCancelDesc = orderPageChangeAndCancelDesc;
	}

	public String getItemForFree() {
		return itemForFree;
	}

	public void setItemForFree(String itemForFree) {
		this.itemForFree = itemForFree;
	}

	public String getRpName() {
		return rpName;
	}

	public void setRpName(String rpName) {
		this.rpName = rpName;
	}

	public String getDrrOverallDesc() {
		return drrOverallDesc;
	}

	public void setDrrOverallDesc(String drrOverallDesc) {
		this.drrOverallDesc = drrOverallDesc;
	}

	public Integer getMinRoom() {
		return minRoom;
	}

	public void setMinRoom(Integer minRoom) {
		this.minRoom = minRoom;
	}

	public String getNetworkDesc() {
		return networkDesc;
	}

	public void setNetworkDesc(String networkDesc) {
		this.networkDesc = networkDesc;
	}

	public Double getDayAvgRoomCostCNY() {
		return dayAvgRoomCostCNY;
	}

	public void setDayAvgRoomCostCNY(Double dayAvgRoomCostCNY) {
		this.dayAvgRoomCostCNY = dayAvgRoomCostCNY;
	}

	public Double getDayAvgRoomCost() {
		return dayAvgRoomCost;
	}

	public void setDayAvgRoomCost(Double dayAvgRoomCost) {
		this.dayAvgRoomCost = dayAvgRoomCost;
	}

	public Double getTotleRoomCost() {
		return totleRoomCost;
	}

	public void setTotleRoomCost(Double totleRoomCost) {
		this.totleRoomCost = totleRoomCost;
	}

	public Double getTotleRoomCostCNY() {
		return totleRoomCostCNY;
	}

	public void setTotleRoomCostCNY(Double totleRoomCostCNY) {
		this.totleRoomCostCNY = totleRoomCostCNY;
	}
		
}
