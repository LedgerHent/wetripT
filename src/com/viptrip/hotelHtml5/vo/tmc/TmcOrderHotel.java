/**
* @Title: Abstract TmcOrderHotel
* @Description:  TmcOrderHotel.
* @author Tangqingshan
* @date 2017-06-29 11:12:14
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderHotel
* @Description:  TmcOrderHotel.
* @author Tangqingshan 
* @date 2017-06-29 11:12:14
*/
public class TmcOrderHotel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 产品ID
	 */
	private String prdId;
	/**
	 * 酒店ID
	 */
	private String hotelId;
	/**
	 * 酒店名称
	 */
	private String hotelName;
	/**
	 * 房型ID
	 */
	private String roomTypeId;
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	/**
	 * RPID
	 */
	private String rpId;
	/**
	 * RP名称
	 */
	private String rpName;
	/**
	 * 酒店星级
	 */
	private String star;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 供应商ID
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 入住城市ID
	 */
	private String checkinCityId;
	/**
	 * 行政区
	 */
	private String region;
	/**
	 * 预订入住日期
	 */
	private Date bookingCheckinDate;
	/**
	 * 预订离店日期
	 */
	private Date bookingCheckoutDate;
	/**
	 * 预订间夜数（总间夜数 = 入住几晚 * 房间数） 
	 */
	private Integer bookingNum;
	/**
	 * 实际入住日期
	 */
	private Date actualCheckinDate;
	/**
	 * 实际离店日期
	 */
	private Date actualCheckoutDate;
	/**
	 * 实际间夜数（总间夜数 =入住几晚 * 房间数） 
	 */
	private Integer actualNum;
	/**
	 * 房间号
	 */
	private String roomNo;
	/**
	 * 预订房间单价
	 */
	private Double bookingPrice;
	/**
	 * 预订房间成本
	 */
	private Double bookingCost;
	/**
	 * 预订房间总价
	 */
	private Double bookingTotalPrice;
	/**
	 * 预订房间总成本
	 */
	private Double bookingTotalCost;
	/**
	 * 预订房间总利润
	 */
	private Double bookingTotalProfit;
	/**
	 * 实际房间总价
	 */
	private Double actualTotalPrice;
	/**
	 * 实际房间总成本
	 */
	private Double actualTotalCost;
	/**
	 * 实际房间总利润
	 */
	private Double actualTotalProfit;
	/**
	 * 预订服务费
	 */
	private Double bookingFee;
	/**
	 * 夜间服务费
	 */
	private Double nightFee;
	/**
	 * 实际应收
	 */
	private Double actualReceiva;
	/**
	 * 实际应付
	 */
	private Double actualPay;
	/**
	 * 酒店取消金
	 */
	private Double hotelRefund;
	/**
	 * 企业取消金
	 */
	private Double enterpRefund;
	/**
	 * 供应商返佣
	 */
	private Double supplierRebate;
	/**
	 * 是否团队
	 */
	private String isTeam;
	/**
	 * 利润
	 */
	private Double profit;
	/**
	 * 酒店种类：0001000001 国内，0001000002  国际 
	 */
	private String hotelType;
	/**
	 * 酒店地址
	 */
	private String hotelAddress;
	/**
	 * 房间数量
	 */
	private Integer roomNum;
	/**
	 * 是否含早
	 */
	private String isBreakfast;
	/**
	 * 宽带信息
	 */
	private String hotelNetwork;
	/**
	 * 酒店政策(0/收费取消、1/显示免费取消、2/不可取消)
	 */
	private String hotelPolicy;
	/**
	 * 酒店政策描述
	 */
	private String hotelPolicyDesc;
	/**
	 * 供应商订单号
	 */
	private String supplierOrderNo;
	/**
	 * 酒店联系电话
	 */
	private String hotelTelephone;
	/**
	 * 税费
	 */
	private Double taxFee;
	/**
	 * 入住总人数
	 */
	private Integer checkManCount;
	/**
	 * 预订入住日期
	 */
	private String bookingCheckinDateStr;
	/**
	 * 预订离店日期
	 */
	private String bookingCheckoutDateStr;
	/**
	 * 实际入住时间
	 */
	private String actualCheckinDateStr;
	/**
	 * 实际离店时间
	 */
	private String actualCheckoutDateStr;
	/**
	 * 平台酒店ID
	 */
	private String ptHotelId;
	/**
	 * 平台酒店房型ID
	 */
	private String ptRoomTypeId;
	
	public void setDbid(String dbid){
	this.dbid=dbid;
	}
	public String getDbid(){
		return dbid;
	}
	public void setOrderNo(String orderNo){
	this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setPrdId(String prdId){
	this.prdId=prdId;
	}
	public String getPrdId(){
		return prdId;
	}
	public void setHotelId(String hotelId){
	this.hotelId=hotelId;
	}
	public String getHotelId(){
		return hotelId;
	}
	public void setHotelName(String hotelName){
	this.hotelName=hotelName;
	}
	public String getHotelName(){
		return hotelName;
	}
	public void setRoomTypeId(String roomTypeId){
	this.roomTypeId=roomTypeId;
	}
	public String getRoomTypeId(){
		return roomTypeId;
	}
	public void setRoomTypeName(String roomTypeName){
	this.roomTypeName=roomTypeName;
	}
	public String getRoomTypeName(){
		return roomTypeName;
	}
	public void setRpId(String rpId){
	this.rpId=rpId;
	}
	public String getRpId(){
		return rpId;
	}
	public void setRpName(String rpName){
	this.rpName=rpName;
	}
	public String getRpName(){
		return rpName;
	}
	public void setStar(String star){
	this.star=star;
	}
	public String getStar(){
		return star;
	}
	public void setBrand(String brand){
	this.brand=brand;
	}
	public String getBrand(){
		return brand;
	}
	public void setSupplierId(String supplierId){
	this.supplierId=supplierId;
	}
	public String getSupplierId(){
		return supplierId;
	}
	public void setSupplierName(String supplierName){
	this.supplierName=supplierName;
	}
	public String getSupplierName(){
		return supplierName;
	}
	public void setCheckinCityId(String checkinCityId){
	this.checkinCityId=checkinCityId;
	}
	public String getCheckinCityId(){
		return checkinCityId;
	}
	public void setRegion(String region){
	this.region=region;
	}
	public String getRegion(){
		return region;
	}
	public void setBookingCheckinDate(Date bookingCheckinDate){
	this.bookingCheckinDate=bookingCheckinDate;
	}
	public Date getBookingCheckinDate(){
		return bookingCheckinDate;
	}
	public void setBookingCheckoutDate(Date bookingCheckoutDate){
	this.bookingCheckoutDate=bookingCheckoutDate;
	}
	public Date getBookingCheckoutDate(){
		return bookingCheckoutDate;
	}
	public void setActualCheckinDate(Date actualCheckinDate){
	this.actualCheckinDate=actualCheckinDate;
	}
	public Date getActualCheckinDate(){
		return actualCheckinDate;
	}
	public void setActualCheckoutDate(Date actualCheckoutDate){
	this.actualCheckoutDate=actualCheckoutDate;
	}
	public Date getActualCheckoutDate(){
		return actualCheckoutDate;
	}
	public void setRoomNo(String roomNo){
	this.roomNo=roomNo;
	}
	public String getRoomNo(){
		return roomNo;
	}
	public void setBookingPrice(Double bookingPrice){
	this.bookingPrice=bookingPrice;
	}
	public Double getBookingPrice(){
		return bookingPrice;
	}
	public void setBookingCost(Double bookingCost){
	this.bookingCost=bookingCost;
	}
	public Double getBookingCost(){
		return bookingCost;
	}
	public void setBookingTotalPrice(Double bookingTotalPrice){
	this.bookingTotalPrice=bookingTotalPrice;
	}
	public Double getBookingTotalPrice(){
		return bookingTotalPrice;
	}
	public void setBookingTotalCost(Double bookingTotalCost){
	this.bookingTotalCost=bookingTotalCost;
	}
	public Double getBookingTotalCost(){
		return bookingTotalCost;
	}
	public void setBookingTotalProfit(Double bookingTotalProfit){
	this.bookingTotalProfit=bookingTotalProfit;
	}
	public Double getBookingTotalProfit(){
		return bookingTotalProfit;
	}
	public void setActualTotalPrice(Double actualTotalPrice){
	this.actualTotalPrice=actualTotalPrice;
	}
	public Double getActualTotalPrice(){
		return actualTotalPrice;
	}
	public void setActualTotalCost(Double actualTotalCost){
	this.actualTotalCost=actualTotalCost;
	}
	public Double getActualTotalCost(){
		return actualTotalCost;
	}
	public void setActualTotalProfit(Double actualTotalProfit){
	this.actualTotalProfit=actualTotalProfit;
	}
	public Double getActualTotalProfit(){
		return actualTotalProfit;
	}
	public void setBookingFee(Double bookingFee){
	this.bookingFee=bookingFee;
	}
	public Double getBookingFee(){
		return bookingFee;
	}
	public void setNightFee(Double nightFee){
	this.nightFee=nightFee;
	}
	public Double getNightFee(){
		return nightFee;
	}
	public void setActualReceiva(Double actualReceiva){
	this.actualReceiva=actualReceiva;
	}
	public Double getActualReceiva(){
		return actualReceiva;
	}
	public void setActualPay(Double actualPay){
	this.actualPay=actualPay;
	}
	public Double getActualPay(){
		return actualPay;
	}
	public void setHotelRefund(Double hotelRefund){
	this.hotelRefund=hotelRefund;
	}
	public Double getHotelRefund(){
		return hotelRefund;
	}
	public void setEnterpRefund(Double enterpRefund){
	this.enterpRefund=enterpRefund;
	}
	public Double getEnterpRefund(){
		return enterpRefund;
	}
	public void setSupplierRebate(Double supplierRebate){
	this.supplierRebate=supplierRebate;
	}
	public Double getSupplierRebate(){
		return supplierRebate;
	}
	public void setIsTeam(String isTeam){
	this.isTeam=isTeam;
	}
	public String getIsTeam(){
		return isTeam;
	}
	public void setProfit(Double profit){
	this.profit=profit;
	}
	public Double getProfit(){
		return profit;
	}
	public void setHotelType(String hotelType){
	this.hotelType=hotelType;
	}
	public String getHotelType(){
		return hotelType;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public Integer getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(Integer roomNum) {
		this.roomNum = roomNum;
	}
	public String getIsBreakfast() {
		return isBreakfast;
	}
	public void setIsBreakfast(String isBreakfast) {
		this.isBreakfast = isBreakfast;
	}
	public String getHotelNetwork() {
		return hotelNetwork;
	}
	public void setHotelNetwork(String hotelNetwork) {
		this.hotelNetwork = hotelNetwork;
	}
	public String getHotelPolicy() {
		return hotelPolicy;
	}
	public void setHotelPolicy(String hotelPolicy) {
		this.hotelPolicy = hotelPolicy;
	}
	public String getHotelPolicyDesc() {
		return hotelPolicyDesc;
	}
	public void setHotelPolicyDesc(String hotelPolicyDesc) {
		this.hotelPolicyDesc = hotelPolicyDesc;
	}
	public Integer getBookingNum() {
		return bookingNum;
	}
	public void setBookingNum(Integer bookingNum) {
		this.bookingNum = bookingNum;
	}
	public Integer getActualNum() {
		return actualNum;
	}
	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}
	public String getSupplierOrderNo() {
		return supplierOrderNo;
	}
	public void setSupplierOrderNo(String supplierOrderNo) {
		this.supplierOrderNo = supplierOrderNo;
	}
	public String getHotelTelephone() {
		return hotelTelephone;
	}
	public void setHotelTelephone(String hotelTelephone) {
		this.hotelTelephone = hotelTelephone;
	}
	public Double getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(Double taxFee) {
		this.taxFee = taxFee;
	}
	public Integer getCheckManCount() {
		return checkManCount;
	}
	public void setCheckManCount(Integer checkManCount) {
		this.checkManCount = checkManCount;
	}
	public String getBookingCheckinDateStr() {
		return bookingCheckinDateStr;
	}
	public void setBookingCheckinDateStr(String bookingCheckinDateStr) {
		this.bookingCheckinDateStr = bookingCheckinDateStr;
	}
	public String getBookingCheckoutDateStr() {
		return bookingCheckoutDateStr;
	}
	public void setBookingCheckoutDateStr(String bookingCheckoutDateStr) {
		this.bookingCheckoutDateStr = bookingCheckoutDateStr;
	}
	public String getActualCheckinDateStr() {
		return actualCheckinDateStr;
	}
	public void setActualCheckinDateStr(String actualCheckinDateStr) {
		this.actualCheckinDateStr = actualCheckinDateStr;
	}
	public String getActualCheckoutDateStr() {
		return actualCheckoutDateStr;
	}
	public void setActualCheckoutDateStr(String actualCheckoutDateStr) {
		this.actualCheckoutDateStr = actualCheckoutDateStr;
	}
	public String getPtHotelId() {
		return ptHotelId;
	}
	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}
	public String getPtRoomTypeId() {
		return ptRoomTypeId;
	}
	public void setPtRoomTypeId(String ptRoomTypeId) {
		this.ptRoomTypeId = ptRoomTypeId;
	}
	
}

