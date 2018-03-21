/**
* @Title: Abstract TmcOrderInfo
* @Description:  TmcOrderInfo.
* @author Tangqingshan
* @date 2017-06-28 20:11:44
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderInfo
* @Description:  TmcOrderInfo.
* @author Tangqingshan 
* @date 2017-06-28 20:11:44
*/
public class TmcOrderInfo implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String orderNo;
	/**
	 * 订单状态
	 */
	private String orderState;
	/**
	 * 支付状态
	 */
	private String payState;
	/**
	 * 审批状态
	 */
	private String approveState;
	/**
	 * 订单总额
	 */
	private double orderTotalAmount;
	/**
	 * 支付总额
	 */
	private double payAmount;
	/**
	 * 出行类型
	 */
	private String tripType;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 预订人编码
	 */
	private String bookingUserId;
	/**
	 * 预订人名称
	 */
	private String bookingUserName;
	/**
	 * 预订人所属部门编码
	 */
	private String bookingDeptId;
	/**
	 * 预订人所属部门
	 */
	private String bookingDeptName;
	/**
	 * 预订人企业编码
	 */
	private String bookingEnterpId;
	/**
	 * 预订人企业名称
	 */
	private String bookingEnterpName;
	/**
	 * 代预订人code
	 */
	private String agentBookingId;
	/**
	 * 代预订人名称
	 */
	private String agentBookingName;
	/**
	 * 预订时间
	 */
	private Date bookingTime;
	/**
	 * 跟单人ID
	 */
	private String opUserId;
	/**
	 * 跟单人名称
	 */
	private String opUserName;
	/**
	 * 财务收款状态
	 */
	private String receivablesState;
	/**
	 * 财务收款人
	 */
	private String receivaUserId;
	/**
	 * 财务收款人名称
	 */
	private String receivaUserName;
	/**
	 * 收款时间
	 */
	private Date receivaTime;
	/**
	 * 差旅政策规则ID
	 */
	private String tripRuleId;
	/**
	 * 备注信息
	 */
	private String orderMemo;
	/**
	 * 审批规则ID
	 */
	private String approvePolicyId;
	/**
	 * 业务城市
	 */
	private String localCity;
	/**
	 * 财务审批时间
	 */
	private Date financialApprovalDate;
	/**
	 * 财务审批人ID
	 */
	private String financialApprovalUserId;
	/**
	 * 财务审批人姓名
	 */
	private String financialApprovalUserName;
	/**
	 * 超规理由
	 */
	private String superRulrReason;
	/**
	 * 支付渠道(支付宝、微信等) 
	 */
	private String payChannel;
	/**
	 * 审批政策名称 
	 */
	private String approvePolicyName;
	/**
	 * 供应商结算单号(付款单号) 
	 */
	private String supplierBalanceOrder;
	/**
	 * 供应商结算状态(付款状态)
	 */
	private String supplierBalanceState;
	/**
	 * 订单类型（预订单、手工单） 
	 */
	private String orderType;
	/**
	 * 开票信息（部门/公司）code 
	 */
	private String invoiceDeptCode;
	/**
	 *  开票信息（部门/公司）name 
	 */
	private String invoiceDeptName;
	/**
	 * 利息折扣（RMB） 
	 */
	private Double interestDiscount;
	/**
	 *  积分折扣（RMB） 
	 */
	private Double integralDiscount;

	/**
	 * 预订时间字符串
	 */
	private String bookingTimeStr;
	/**
	 * 收款时间字符串
	 */
	private String receivaTimeStr;
	/**
	 * 财务审批时间字符串
	 */
	private String financialApprovalDateStr;
	/**
	 * 精选/协议酒店：0002400001 精选酒店，0002400002 协议酒店
	 */
	private String choiceAgreementHotelFlag;
	
	/**
	 * 订单实际支付金额 （总额 - 优惠金额） （优惠金额包括积分/利息     将来可能还有优惠券）
	 */
	private String actualPayablePrice;

	
	public void setOrderNo(String orderNo){
	this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setOrderState(String orderState){
	this.orderState=orderState;
	}
	public String getOrderState(){
		return orderState;
	}
	public void setPayState(String payState){
	this.payState=payState;
	}
	public String getPayState(){
		return payState;
	}
	public void setApproveState(String approveState){
	this.approveState=approveState;
	}
	public String getApproveState(){
		return approveState;
	}
	public void setOrderTotalAmount(double orderTotalAmount){
	this.orderTotalAmount=orderTotalAmount;
	}
	public double getOrderTotalAmount(){
		return orderTotalAmount;
	}
	public void setPayAmount(double payAmount){
	this.payAmount=payAmount;
	}
	public double getPayAmount(){
		return payAmount;
	}
	public void setTripType(String tripType){
	this.tripType=tripType;
	}
	public String getTripType(){
		return tripType;
	}
	public void setProjectName(String projectName){
	this.projectName=projectName;
	}
	public String getProjectName(){
		return projectName;
	}
	public void setPayType(String payType){
	this.payType=payType;
	}
	public String getPayType(){
		return payType;
	}
	public void setBookingUserId(String bookingUserId){
	this.bookingUserId=bookingUserId;
	}
	public String getBookingUserId(){
		return bookingUserId;
	}
	public void setBookingUserName(String bookingUserName){
	this.bookingUserName=bookingUserName;
	}
	public String getBookingUserName(){
		return bookingUserName;
	}
	public void setBookingDeptId(String bookingDeptId){
	this.bookingDeptId=bookingDeptId;
	}
	public String getBookingDeptId(){
		return bookingDeptId;
	}
	public void setBookingDeptName(String bookingDeptName){
	this.bookingDeptName=bookingDeptName;
	}
	public String getBookingDeptName(){
		return bookingDeptName;
	}
	public void setBookingEnterpId(String bookingEnterpId){
	this.bookingEnterpId=bookingEnterpId;
	}
	public String getBookingEnterpId(){
		return bookingEnterpId;
	}
	public void setBookingEnterpName(String bookingEnterpName){
	this.bookingEnterpName=bookingEnterpName;
	}
	public String getBookingEnterpName(){
		return bookingEnterpName;
	}
	public void setAgentBookingId(String agentBookingId){
	this.agentBookingId=agentBookingId;
	}
	public String getAgentBookingId(){
		return agentBookingId;
	}
	public void setAgentBookingName(String agentBookingName){
	this.agentBookingName=agentBookingName;
	}
	public String getAgentBookingName(){
		return agentBookingName;
	}
	public void setBookingTime(Date bookingTime){
	this.bookingTime=bookingTime;
	}
	public Date getBookingTime(){
		return bookingTime;
	}
	public void setOpUserId(String opUserId){
	this.opUserId=opUserId;
	}
	public String getOpUserId(){
		return opUserId;
	}
	public void setOpUserName(String opUserName){
	this.opUserName=opUserName;
	}
	public String getOpUserName(){
		return opUserName;
	}
	public void setReceivablesState(String receivablesState){
	this.receivablesState=receivablesState;
	}
	public String getReceivablesState(){
		return receivablesState;
	}
	public void setReceivaUserId(String receivaUserId){
	this.receivaUserId=receivaUserId;
	}
	public String getReceivaUserId(){
		return receivaUserId;
	}
	public void setReceivaUserName(String receivaUserName){
	this.receivaUserName=receivaUserName;
	}
	public String getReceivaUserName(){
		return receivaUserName;
	}
	public void setReceivaTime(Date receivaTime){
	this.receivaTime=receivaTime;
	}
	public Date getReceivaTime(){
		return receivaTime;
	}
	public void setTripRuleId(String tripRuleId){
	this.tripRuleId=tripRuleId;
	}
	public String getTripRuleId(){
		return tripRuleId;
	}
	public String getOrderMemo() {
		return orderMemo;
	}
	public void setOrderMemo(String orderMemo) {
		this.orderMemo = orderMemo;
	}
	public String getApprovePolicyId() {
		return approvePolicyId;
	}
	public void setApprovePolicyId(String approvePolicyId) {
		this.approvePolicyId = approvePolicyId;
	}
	public String getLocalCity() {
		return localCity;
	}
	public void setLocalCity(String localCity) {
		this.localCity = localCity;
	}
	public Date getFinancialApprovalDate() {
		return financialApprovalDate;
	}
	public void setFinancialApprovalDate(Date financialApprovalDate) {
		this.financialApprovalDate = financialApprovalDate;
	}
	public String getFinancialApprovalUserId() {
		return financialApprovalUserId;
	}
	public void setFinancialApprovalUserId(String financialApprovalUserId) {
		this.financialApprovalUserId = financialApprovalUserId;
	}
	public String getFinancialApprovalUserName() {
		return financialApprovalUserName;
	}
	public void setFinancialApprovalUserName(String financialApprovalUserName) {
		this.financialApprovalUserName = financialApprovalUserName;
	}
	public String getSuperRulrReason() {
		return superRulrReason;
	}
	public void setSuperRulrReason(String superRulrReason) {
		this.superRulrReason = superRulrReason;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getApprovePolicyName() {
		return approvePolicyName;
	}
	public void setApprovePolicyName(String approvePolicyName) {
		this.approvePolicyName = approvePolicyName;
	}
	public String getSupplierBalanceOrder() {
		return supplierBalanceOrder;
	}
	public void setSupplierBalanceOrder(String supplierBalanceOrder) {
		this.supplierBalanceOrder = supplierBalanceOrder;
	}
	public String getSupplierBalanceState() {
		return supplierBalanceState;
	}
	public void setSupplierBalanceState(String supplierBalanceState) {
		this.supplierBalanceState = supplierBalanceState;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getInvoiceDeptCode() {
		return invoiceDeptCode;
	}
	public void setInvoiceDeptCode(String invoiceDeptCode) {
		this.invoiceDeptCode = invoiceDeptCode;
	}
	public String getInvoiceDeptName() {
		return invoiceDeptName;
	}
	public void setInvoiceDeptName(String invoiceDeptName) {
		this.invoiceDeptName = invoiceDeptName;
	}
	public Double getInterestDiscount() {
		return interestDiscount;
	}
	public void setInterestDiscount(Double interestDiscount) {
		this.interestDiscount = interestDiscount;
	}
	public Double getIntegralDiscount() {
		return integralDiscount;
	}
	public void setIntegralDiscount(Double integralDiscount) {
		this.integralDiscount = integralDiscount;
	}
	public String getBookingTimeStr() {
		return bookingTimeStr;
	}
	public void setBookingTimeStr(String bookingTimeStr) {
		this.bookingTimeStr = bookingTimeStr;
	}
	public String getReceivaTimeStr() {
		return receivaTimeStr;
	}
	public void setReceivaTimeStr(String receivaTimeStr) {
		this.receivaTimeStr = receivaTimeStr;
	}
	public String getFinancialApprovalDateStr() {
		return financialApprovalDateStr;
	}
	public void setFinancialApprovalDateStr(String financialApprovalDateStr) {
		this.financialApprovalDateStr = financialApprovalDateStr;
	}
	public String getChoiceAgreementHotelFlag() {
		return choiceAgreementHotelFlag;
	}
	public void setChoiceAgreementHotelFlag(String choiceAgreementHotelFlag) {
		this.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	}
	public String getActualPayablePrice() {
		return actualPayablePrice;
	}
	public void setActualPayablePrice(String actualPayablePrice) {
		this.actualPayablePrice = actualPayablePrice;
	}
	
}

