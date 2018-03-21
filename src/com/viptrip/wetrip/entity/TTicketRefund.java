package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TTicketRefund entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TICKET_REFUND")
public class TTicketRefund implements java.io.Serializable {

	// Fields

	private Long id;
	private Long tid;
	private String ticketNo;
	private String orderId;
	private String passengerName;
	private String pnr;
	private double shouldRec;
	private double shouldPay;
	private double refundProfits;
	private Integer isChangedate;
	private Integer refundType;
	private Integer refundStatus;
	private String refuseReson;
	private String applyRefuseReson;  //申请退票原因
	private String applyName;
	private Date applyTime;
	private String dealName;
	private Date dealTime;
	private double orgRefundFee;
	private double supRefundFee;
	private double orgTicketAmount;
	private double supTicketAmount;
	private double orgRate;
	private double refundTaxPrice;
	private double refundFueltax;
	private double refundTicketPrice;
	private String refundCertificate;
	
	private String isconfirm;
	private Double  refundServiceFee;//退票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String payMethed;//'客户支付方式 1：公司月结 2：个人支付 3：公司现结 4：预付款支付';
	
	private Double changedeal;
	
	private Date issuedDate;//退票出票时间
	private String ticketnos;//票号(退出票为原始票号,退改期为换开票号)
	private Long uid;
	// Constructors

	/** default constructor */
	public TTicketRefund() {
	}

	/** minimal constructor */
	public TTicketRefund(String ticketNo, String orderId, Integer refundType) {
		this.ticketNo = ticketNo;
		this.orderId = orderId;
		this.refundType = refundType;
	}

	/** full constructor */
	public TTicketRefund(String ticketNo, String orderId, String passengerName,
			String pnr, double shouldRec, double shouldPay,
			double refundProfits, Integer isChangedate,
			Integer refundType, Integer refundStatus, String refuseReson,
			String applyName, Date applyTime, String dealName, Date dealTime,
			double orgRefundFee, double supRefundFee, double orgTicketAmount,
			double supTicketAmount, double orgRate, double refundTaxPrice,
			double refundFueltax, double refundTicketPrice,Long uid) {
		this.ticketNo = ticketNo;
		this.orderId = orderId;
		this.passengerName = passengerName;
		this.pnr = pnr;
		this.shouldRec = shouldRec;
		this.shouldPay = shouldPay;
		this.refundProfits = refundProfits;
		this.isChangedate = isChangedate;
		this.refundType = refundType;
		this.refundStatus = refundStatus;
		this.refuseReson = refuseReson;
		this.applyName = applyName;
		this.applyTime = applyTime;
		this.dealName = dealName;
		this.dealTime = dealTime;
		this.orgRefundFee = orgRefundFee;
		this.supRefundFee = supRefundFee;
		this.orgTicketAmount = orgTicketAmount;
		this.supTicketAmount = supTicketAmount;
		this.orgRate = orgRate;
		this.refundTaxPrice = refundTaxPrice;
		this.refundFueltax = refundFueltax;
		this.refundTicketPrice = refundTicketPrice;
		this.uid = uid;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "T_ID" )
	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

	@Column(name = "TICKET_NO", nullable = false, length = 15)
	public String getTicketNo() {
		return this.ticketNo;
	}

	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}

	@Column(name = "ORDER_ID", nullable = false, length = 15)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "PASSENGER_NAME", length = 30)
	public String getPassengerName() {
		return this.passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	@Column(name = "PNR", length = 6)
	public String getPnr() {
		return this.pnr;
	}

	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	@Column(name = "SHOULD_REC", precision = 10)
	public double getShouldRec() {
		return this.shouldRec;
	}

	public void setShouldRec(double shouldRec) {
		this.shouldRec = shouldRec;
	}

	@Column(name = "SHOULD_PAY", precision = 10)
	public double getShouldPay() {
		return this.shouldPay;
	}

	public void setShouldPay(double shouldPay) {
		this.shouldPay = shouldPay;
	}

	@Column(name = "REFUND_PROFITS", precision = 10)
	public double getRefundProfits() {
		return this.refundProfits;
	}

	public void setRefundProfits(double refundProfits) {
		this.refundProfits = refundProfits;
	}

	@Column(name = "IS_CHANGEDATE", precision = 22, scale = 0)
	public Integer getIsChangedate() {
		return this.isChangedate;
	}

	public void setIsChangedate(Integer isChangedate) {
		this.isChangedate = isChangedate;
	}

	@Column(name = "REFUND_TYPE", nullable = false, precision = 22, scale = 0)
	public Integer getRefundType() {
		return this.refundType;
	}

	public void setRefundType(Integer refundType) {
		this.refundType = refundType;
	}

	@Column(name = "REFUND_STATUS", precision = 22, scale = 0)
	public Integer getRefundStatus() {
		return this.refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	@Column(name = "REFUSE_RESON", length = 500)
	public String getRefuseReson() {
		return this.refuseReson;
	}

	public void setRefuseReson(String refuseReson) {
		this.refuseReson = refuseReson;
	}

	@Column(name = "APPLY_REFUSE_RESON", length = 500)
	public String getApplyRefuseReson() {
		return applyRefuseReson;
	}

	public void setApplyRefuseReson(String applyRefuseReson) {
		this.applyRefuseReson = applyRefuseReson;
	}

	@Column(name = "APPLY_NAME", length = 20)
	public String getApplyName() {
		return this.applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPLY_TIME", length = 7)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	@Column(name = "DEAL_NAME", length = 20)
	public String getDealName() {
		return this.dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DEAL_TIME", length = 7)
	public Date getDealTime() {
		return this.dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	@Column(name = "ORG_REFUND_FEE", precision = 10)
	public double getOrgRefundFee() {
		return this.orgRefundFee;
	}

	public void setOrgRefundFee(double orgRefundFee) {
		this.orgRefundFee = orgRefundFee;
	}

	@Column(name = "SUP_REFUND_FEE", precision = 10)
	public double getSupRefundFee() {
		return this.supRefundFee;
	}

	public void setSupRefundFee(double supRefundFee) {
		this.supRefundFee = supRefundFee;
	}

	@Column(name = "ORG_TICKET_AMOUNT", precision = 10)
	public double getOrgTicketAmount() {
		return this.orgTicketAmount;
	}

	public void setOrgTicketAmount(double orgTicketAmount) {
		this.orgTicketAmount = orgTicketAmount;
	}

	@Column(name = "SUP_TICKET_AMOUNT", precision = 10)
	public double getSupTicketAmount() {
		return this.supTicketAmount;
	}

	public void setSupTicketAmount(double supTicketAmount) {
		this.supTicketAmount = supTicketAmount;
	}

	@Column(name = "ORG_RATE", precision = 10)
	public double getOrgRate() {
		return this.orgRate;
	}

	public void setOrgRate(double orgRate) {
		this.orgRate = orgRate;
	}

	@Column(name = "REFUND_TAX_PRICE", precision = 10)
	public double getRefundTaxPrice() {
		return this.refundTaxPrice;
	}

	public void setRefundTaxPrice(double refundTaxPrice) {
		this.refundTaxPrice = refundTaxPrice;
	}

	@Column(name = "REFUND_FUELTAX", precision = 10)
	public double getRefundFueltax() {
		return this.refundFueltax;
	}

	public void setRefundFueltax(double refundFueltax) {
		this.refundFueltax = refundFueltax;
	}

	@Column(name = "REFUND_TICKET_PRICE", precision = 10)
	public double getRefundTicketPrice() {
		return this.refundTicketPrice;
	}

	public void setRefundTicketPrice(double refundTicketPrice) {
		this.refundTicketPrice = refundTicketPrice;
	}

	@Column(name = "REFUND_CERTIFICATE")
	public String getRefundCertificate() {
		return refundCertificate;
	}

	public void setRefundCertificate(String refundCertificate) {
		this.refundCertificate = refundCertificate;
	}

	@Column(name = "ISCONFIRM", length = 1)
	public String getUsertype() {
		return this.isconfirm;
	}

	public void setUsertype(String isconfirm) {
		this.isconfirm = isconfirm;
	}
	@Column(name = "REFUNDSERVICEFEE", precision = 10)
	public Double getRefundServiceFee() {
		return refundServiceFee;
	}

	public void setRefundServiceFee(Double refundServiceFee) {
		this.refundServiceFee = refundServiceFee;
	}
	
	
	@Column(name = "NIGHTFEE", precision = 10)
	public Double getNightFee() {
		return nightFee;
	}

	public void setNightFee(Double nightFee) {
		this.nightFee = nightFee;
	}
	@Column(name = "PAY_METHED", length = 1)
	public String getPayMethed() {
		return payMethed;
	}

	public void setPayMethed(String payMethed) {
		this.payMethed = payMethed;
	}
	@Column(name = "CHANGEDEAL", precision = 10)
	public Double getChangedeal() {
		return changedeal;
	}

	public void setChangedeal(Double changedeal) {
		this.changedeal = changedeal;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ISSUED_DATE", length = 7)
	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	@Column(name = "TICKETNO", length = 15)
	public String getTicketnos() {
		return ticketnos;
	}

	public void setTicketnos(String ticketnos) {
		this.ticketnos = ticketnos;
	}
	@Column(name = "U_ID" )
	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}
	
}