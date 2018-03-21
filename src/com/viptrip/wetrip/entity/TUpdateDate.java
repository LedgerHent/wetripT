package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TUpdateDate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_UPDATE_DATE")
public class TUpdateDate implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -7516384074863955573L;
	private Long UId;
	private Long TId;
	private String travelItineraryNo;
	private String flightNumber;
	private Date flightTime;
	private Double ticketPrice;
	
	private Double enterpriseDiscount;
	
	private String auditStatus;
	
	
	private String applyName;
	private Date applyDate;
	private String auditName;
	private Date auditDate;
	private Double classFee;
	private Double changeTheFreight;
	private String flightStart;
	
	private String flightEnd;
	private Double taxPrice;
	private Double enterpriseOtherDiscount;
	private String ticketUseState;
	
	/**
	 * 0 已出票可以改期
	 * 3 已申请改期
	 * 5 已通过改期
	 * 6 已拒绝改期
	 * 7 改期通过且可以再次改期
	 */
	private String flightStatus;
	private Double fueltax;
	private String notThroughReason;
	private String cangwei;
	private String changeBanks;
	private String newPnr;
	private Double rescheduledProfits;
	private String buyPortal;
	private String suppliername;//新增供应商字段
	private String payment;
	private Double changedeal;//改期应付升舱费
	
	private String isconfirm;
	private String orgterm;
	private String detterm;
	private Date destinationTime;
	private String planetype;
	private String airlineCompany;
	private Double changePayFee;//改期应付改期手续费
	private String customerPaymentPattern;//客户支付方式,记录的是客户选择的支付类型（1-公司月结，2-个人支付，3-公司现结）
	private Date customerPaymentTime;//客户支付时间
	private String customerPaymentNumber;//客户支付交易好
	private String customerPaymentState;//客户支付状态（0-未支付，1-已支付）
	
	private String payMethodDetail;//在线支付方式，包括支付宝、荷包（PP:phonepay-荷包支付；AP:alipay-支付宝）
	private String changePayName; 				//支付人
	private String changePayTel; 					//支付人电话
	private String changePayEmail;				//支付人邮箱
	private String changePayId;					//支付人证件号
	private String agreementcode;				//协议编码
	
	private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private Double  changeServiceFee;//改期服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String orderno;
	
	
	private Double elseReceiveable;//其他应收总额
    private Double elsePayable;//其他应付总额
	// Constructors

	/** default constructor */
	public TUpdateDate() {
	}

	
/** minimal constructor */
	public TUpdateDate(Long TId) {
		this.TId = TId;
	}

	/** full constructor */
	public TUpdateDate(Long uId, Long tId, String travelItineraryNo,
			String flightNumber, Date flightTime, Double ticketPrice,
			Double enterpriseDiscount, String auditStatus, String applyName,
			Date applyDate, String auditName, Date auditDate, Double classFee,
			Double changeTheFreight, String flightStart, String flightEnd,
			Double taxPrice, Double enterpriseOtherDiscount,
			String flightStatus, Double fueltax, String notThroughReason,
			String cangwei, String changeBanks, String newPnr,
			Double rescheduledProfits, String buyPortal,String suppliername,Double elseReceiveable,Double elsePayable) {
		super();
		UId = uId;
		TId = tId;
		this.travelItineraryNo = travelItineraryNo;
		this.flightNumber = flightNumber;
		this.flightTime = flightTime;
		this.ticketPrice = ticketPrice;
		this.enterpriseDiscount = enterpriseDiscount;
		this.auditStatus = auditStatus;
		this.applyName = applyName;
		this.applyDate = applyDate;
		this.auditName = auditName;
		this.auditDate = auditDate;
		this.classFee = classFee;
		this.changeTheFreight = changeTheFreight;
		this.flightStart = flightStart;
		this.flightEnd = flightEnd;
		this.taxPrice = taxPrice;
		this.enterpriseOtherDiscount = enterpriseOtherDiscount;
		this.flightStatus = flightStatus;
		this.fueltax = fueltax;
		this.notThroughReason = notThroughReason;
		this.cangwei = cangwei;
		this.changeBanks = changeBanks;
		this.newPnr = newPnr;
		this.rescheduledProfits = rescheduledProfits;
		this.buyPortal = buyPortal;
		this.suppliername=suppliername;
		this.elseReceiveable=elseReceiveable;
        this.elsePayable=elsePayable;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TUPDATEDATE")
	@SequenceGenerator(name="SEQ_TUPDATEDATE",allocationSize=1,initialValue=1, sequenceName="SEQ_TUPDATEDATE")
	@Column(name = "U_ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getUId() {
		return this.UId;
	}

	


	public void setUId(Long UId) {
		this.UId = UId;
	}


	@Column(name = "TRAVEL_ITINERARY_NO", length = 15)
	public String getTravelItineraryNo() {
		return this.travelItineraryNo;
	}

	public void setTravelItineraryNo(String travelItineraryNo) {
		this.travelItineraryNo = travelItineraryNo;
	}

	@Column(name = "FLIGHT_NUMBER", length = 20)
	public String getFlightNumber() {
		return this.flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FLIGHT_TIME", length = 7)
	public Date getFlightTime() {
		return this.flightTime;
	}

	public void setFlightTime(Date flightTime) {
		this.flightTime = flightTime;
	}

	@Column(name = "TICKET_PRICE", precision = 10)
	public Double getTicketPrice() {
		return this.ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	

	@Column(name = "ENTERPRISE_DISCOUNT", precision = 10)
	public Double getEnterpriseDiscount() {
		return this.enterpriseDiscount;
	}

	public void setEnterpriseDiscount(Double enterpriseDiscount) {
		this.enterpriseDiscount = enterpriseDiscount;
	}

	

	@Column(name = "AUDIT_STATUS", length = 2)
	public String getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	

	@Column(name = "APPLY_NAME", length = 20)
	public String getApplyName() {
		return this.applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPLY_DATE", length = 7)
	public Date getApplyDate() {
		return this.applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	@Column(name = "AUDIT_NAME", length = 20)
	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AUDIT_DATE", length = 7)
	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	@Column(name = "CLASS_FEE", precision = 10)
	public Double getClassFee() {
		return this.classFee;
	}

	public void setClassFee(Double classFee) {
		this.classFee = classFee;
	}

	@Column(name = "CHANGE_THE_FREIGHT", precision = 10)
	public Double getChangeTheFreight() {
		return this.changeTheFreight;
	}

	public void setChangeTheFreight(Double changeTheFreight) {
		this.changeTheFreight = changeTheFreight;
	}

	@Column(name = "FLIGHT_START", length = 3)
	public String getFlightStart() {
		return this.flightStart;
	}

	public void setFlightStart(String flightStart) {
		this.flightStart = flightStart;
	}

	

	@Column(name = "FLIGHT_END", length = 3)
	public String getFlightEnd() {
		return this.flightEnd;
	}

	public void setFlightEnd(String flightEnd) {
		this.flightEnd = flightEnd;
	}

	@Column(name = "TAX_PRICE", precision = 10)
	public Double getTaxPrice() {
		return this.taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	@Column(name = "ENTERPRISE_OTHER_DISCOUNT", precision = 10)
	public Double getEnterpriseOtherDiscount() {
		return this.enterpriseOtherDiscount;
	}

	public void setEnterpriseOtherDiscount(Double enterpriseOtherDiscount) {
		this.enterpriseOtherDiscount = enterpriseOtherDiscount;
	}



	@Column(name = "FLIGHT_STATUS", length = 2)
	public String getFlightStatus() {
		return this.flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	@Column(name = "FUELTAX", precision = 10)
	public Double getFueltax() {
		return this.fueltax;
	}

	public void setFueltax(Double fueltax) {
		this.fueltax = fueltax;
	}

	@Column(name = "NOT_THROUGH_REASON", length = 400)
	public String getNotThroughReason() {
		return this.notThroughReason;
	}

	public void setNotThroughReason(String notThroughReason) {
		this.notThroughReason = notThroughReason;
	}

	@Column(name = "CANGWEI", length = 2)
	public String getCangwei() {
		return this.cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	/**
	 * @return the changeBanks
	 */
	@Column(name = "CHANGE_BANKS", length = 15)
	public String getChangeBanks() {
		return changeBanks;
	}

	/**
	 * @param changeBanks the changeBanks to set
	 */
	public void setChangeBanks(String changeBanks) {
		this.changeBanks = changeBanks;
	}

	/**
	 * @return the newPnr
	 */
	@Column(name = "NEW_PNR", length = 30)
	public String getNewPnr() {
		return newPnr;
	}

	/**
	 * @param newPnr the newPnr to set
	 */
	public void setNewPnr(String newPnr) {
		this.newPnr = newPnr;
	}

	/**
	 * @return the rescheduledProfits
	 */
	@Column(name = "RESCHEDULED_PROFITS", precision = 10)
	public Double getRescheduledProfits() {
		return rescheduledProfits;
	}

	/**
	 * @param rescheduledProfits the rescheduledProfits to set
	 */
	public void setRescheduledProfits(Double rescheduledProfits) {
		this.rescheduledProfits = rescheduledProfits;
	}


	/**
	 * @return the tId
	 */
	@Column(name = "T_ID", nullable = false, precision = 22, scale = 0)
	public Long getTId() {
		return TId;
	}


	/**
	 * @param tId the tId to set
	 */
	public void setTId(Long tId) {
		TId = tId;
	}


	/**
	 * @return the buyPortal
	 */
	@Column(name = "BUY_PORTAL", length = 30)
	public String getBuyPortal() {
		return buyPortal;
	}


	/**
	 * @param buyPortal the buyPortal to set
	 */
	public void setBuyPortal(String buyPortal) {
		this.buyPortal = buyPortal;
	}

	@Column(name = "PAYMENT", length = 30)
	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	@Column(name = "CHANGE_DEAL", precision = 10)
	public Double getChangedeal() {
		return changedeal;
	}


	public void setChangedeal(Double changedeal) {
		this.changedeal = changedeal;
	}

	@Column(name = "ISCONFIRM", length = 1)
	public String getUsertype() {
		return this.isconfirm;
	}

	public void setUsertype(String isconfirm) {
		this.isconfirm = isconfirm;
	}

	@Column(name = "ORGTERM", length = 10)
	public String getOrgterm() {
		return orgterm;
	}


	public void setOrgterm(String orgterm) {
		this.orgterm = orgterm;
	}

	@Column(name = "DETTERM", length = 10)
	public String getDetterm() {
		return detterm;
	}


	public void setDetterm(String detterm) {
		this.detterm = detterm;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DESTINATION_TIME", length = 7)
	public Date getDestinationTime() {
		return destinationTime;
	}


	public void setDestinationTime(Date destinationTime) {
		this.destinationTime = destinationTime;
	}

	@Column(name = "PLANETYPE", length = 10)
	public String getPlanetype() {
		return planetype;
	}


	public void setPlanetype(String planetype) {
		this.planetype = planetype;
	}

	@Column(name = "AIRLINE_COMPANY", length = 4)
	public String getAirlineCompany() {
		return airlineCompany;
	}


	public void setAirlineCompany(String airlineCompany) {
		this.airlineCompany = airlineCompany;
	}

	@Column(name = "TICKET_USE_STATE", length = 2)
	public String getTicketUseState() {
		return ticketUseState;
	}

	public void setTicketUseState(String ticketUseState) {
		this.ticketUseState = ticketUseState;
	}

	@Column(name = "SUPPLIERNAME", length = 100)
	public String getSuppliername() {
		return suppliername;
	}


	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}


	@Column(name = "CHANGE_PAY_FEE", precision = 10)
	public Double getChangePayFee() {
		return changePayFee;
	}


	public void setChangePayFee(Double changePayFee) {
		this.changePayFee = changePayFee;
	}


	@Column(name = "CUSTOMER_PAYMENT_PATTERN", length = 5)
	public String getCustomerPaymentPattern() {
		return customerPaymentPattern;
	}
	
	public void setCustomerPaymentPattern(String customerPaymentPattern) {
		this.customerPaymentPattern = customerPaymentPattern;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CUSTOMER_PAYMENT_TIME", length = 7)
	public Date getCustomerPaymentTime() {
		return customerPaymentTime;
	}

	public void setCustomerPaymentTime(Date customerPaymentTime) {
		this.customerPaymentTime = customerPaymentTime;
	}

	@Column(name = "CUSTOMER_PAYMENT_NUMBER", length = 50)
	public String getCustomerPaymentNumber() {
		return customerPaymentNumber;
	}

	public void setCustomerPaymentNumber(String customerPaymentNumber) {
		this.customerPaymentNumber = customerPaymentNumber;
	}

	@Column(name = "CUSTOMER_PAYMENT_STATE", length = 5)
	public String getCustomerPaymentState() {
		return customerPaymentState;
	}

	public void setCustomerPaymentState(String customerPaymentState) {
		this.customerPaymentState = customerPaymentState;
	}
	
	@Column(name = "PAY_METHOD_DETAIL", length = 20)
	public String getPayMethodDetail() {
		return payMethodDetail;
	}
	
	public void setPayMethodDetail(String payMethodDetail) {
		this.payMethodDetail = payMethodDetail;
	}
	
	@Column(name = "CHANGE_PAY_NAME", length = 200)
	public String getChangePayName() {
		return changePayName;
	}

	public void setChangePayName(String changePayName) {
		this.changePayName = changePayName;
	}

	@Column(name = "CHANGE_PAY_TEL", length = 20)
	public String getChangePayTel() {
		return changePayTel;
	}

	public void setChangePayTel(String changePayTel) {
		this.changePayTel = changePayTel;
	}

	@Column(name = "CHANGE_PAY_EMAIL", length = 50)
	public String getChangePayEmail() {
		return changePayEmail;
	}

	public void setChangePayEmail(String changePayEmail) {
		this.changePayEmail = changePayEmail;
	}

	@Column(name = "CHANGE_PAY_ID", length = 50)
	public String getChangePayId() {
		return changePayId;
	}

	public void setChangePayId(String changePayId) {
		this.changePayId = changePayId;
	}

	@Column(name = "AGREEMENTCODE", length = 20)
	public String getAgreementcode() {
		return agreementcode;
	}

	public void setAgreementcode(String agreementcode) {
		this.agreementcode = agreementcode;
	}
	@Column(name = "CHANGESERVICEFEE", precision = 10)
	public Double getChangeServiceFee() {
		return changeServiceFee;
	}

	public void setChangeServiceFee(Double changeServiceFee) {
		this.changeServiceFee = changeServiceFee;
	}
	@Column(name = "NIGHTFEE", precision = 10)
	public Double getNightFee() {
		return nightFee;
	}

	public void setNightFee(Double nightFee) {
		this.nightFee = nightFee;
	}

	@Column(name = "ORDERNO", length = 15)
	public String getOrderno() {
		return orderno;
	}


	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	
	@Column(name = "ELSE_RECEIVEABLE", precision = 10)
	public Double getElseReceiveable() {
		return elseReceiveable;
	}

	public void setElseReceiveable(Double elseReceiveable) {
		this.elseReceiveable = elseReceiveable;
	}
	@Column(name = "ELSE_PAYABLE", precision = 10)
	public Double getElsePayable() {
		return elsePayable;
	}

	public void setElsePayable(Double elsePayable) {
		this.elsePayable = elsePayable;
	}
	
}