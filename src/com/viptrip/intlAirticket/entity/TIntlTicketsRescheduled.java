package com.viptrip.intlAirticket.entity;

import java.sql.Timestamp;
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
 * TIntlTicketsRescheduled entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_TICKETS_RESCHEDULED")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TIntlTicketsRescheduled implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4060236952760041173L;
	private Integer rescdid;
	private Integer passid;
	private Date intlApplyDate;
	private String intlApplyName;
	private Date intlAuditDate;
	private String intlAuditName;
	private Double intlRescheduledReceive;
	private Double intlRescheduledPay;
	private Double intlRescheduledProfits;
	private String intlRescheduledStatus;
	private Double intlUpgradesReceive;
	private Double intlUpgradesPay;
	private Integer orderid;
	private String customerTransfersOfPayment;
	private String intlMethodOfPayment;
	private String intlSupplier;
	private String intlRefuseToReason;
	private String intlCustomerPaymentState; 		//国际机票客户支付方式国际机票客户支付状态
	private String intlCustomerPaymentPattern;		//国际机票客户支付方式
	private Date intlCustomerPaymentTime;			//国际机票客户支付时间
	private String intlCustomerPaymentNumber;		//国际机票客户支付交易号
	private String intlPayMethodDetail;				//国际机票在线支付方式
	private String intlChangePayName;				//国际机票改期支付人
	private String intlChangePayTel;				//国际机票改期支付人电话
	private String intlChangePayEmail;				//国际机票改期支付人邮箱
	private String intlChangePayId;					//国际机票改期支付人证件号
	private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private Double  changeServiceFee;//改期服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String intlRescheduleTimes;//国际机票改期次数
	
    private Double elseReceiveable;//其他应收总额
	private Double elsePayable;//其他应付总额
	// Constructors

	/** default constructor */
	public TIntlTicketsRescheduled() {
	}

	/** minimal constructor */
	public TIntlTicketsRescheduled(Integer passid) {
		this.passid = passid;
	}

	/** full constructor */
	public TIntlTicketsRescheduled(Integer passid, Timestamp intlApplyDate,
			String intlApplyName, Timestamp intlAuditDate, String intlAuditName,
			Double intlRescheduledReceive, Double intlRescheduledPay,
			Double intlRescheduledProfits, String intlRescheduledStatus,String intlRescheduleTimes,
			Double elseReceiveable,Double elsePayable) {
		this.passid = passid;
		this.intlApplyDate = intlApplyDate;
		this.intlApplyName = intlApplyName;
		this.intlAuditDate = intlAuditDate;
		this.intlAuditName = intlAuditName;
		this.intlRescheduledReceive = intlRescheduledReceive;
		this.intlRescheduledPay = intlRescheduledPay;
		this.intlRescheduledProfits = intlRescheduledProfits;
		this.intlRescheduledStatus = intlRescheduledStatus;
		this.intlRescheduleTimes = intlRescheduleTimes;
		this.elseReceiveable=elseReceiveable;
        this.elsePayable=elsePayable;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_TICKETS_RESCHEDULED")
	@SequenceGenerator(name="SEQ_INTL_TICKETS_RESCHEDULED",allocationSize=1,initialValue=120, sequenceName="SEQ_INTL_TICKETS_RESCHEDULED")
	@Column(name = "RESCDID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getRescdid() {
		return this.rescdid;
	}

	public void setRescdid(Integer rescdid) {
		this.rescdid = rescdid;
	}

	@Column(name = "PASSID", nullable = false, precision = 22, scale = 0)
	public Integer getPassid() {
		return this.passid;
	}

	public void setPassid(Integer passid) {
		this.passid = passid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_APPLY_DATE", length = 7)
	public Date getIntlApplyDate() {
		return this.intlApplyDate;
	}

	public void setIntlApplyDate(Date intlApplyDate) {
		this.intlApplyDate = intlApplyDate;
	}

	@Column(name = "INTL_APPLY_NAME", length = 100)
	public String getIntlApplyName() {
		return this.intlApplyName;
	}

	public void setIntlApplyName(String intlApplyName) {
		this.intlApplyName = intlApplyName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_AUDIT_DATE", length = 7)
	public Date getIntlAuditDate() {
		return this.intlAuditDate;
	}

	public void setIntlAuditDate(Date intlAuditDate) {
		this.intlAuditDate = intlAuditDate;
	}

	@Column(name = "INTL_AUDIT_NAME", length = 100)
	public String getIntlAuditName() {
		return this.intlAuditName;
	}

	public void setIntlAuditName(String intlAuditName) {
		this.intlAuditName = intlAuditName;
	}

	@Column(name = "INTL_RESCHEDULED_RECEIVE", precision = 10)
	public Double getIntlRescheduledReceive() {
		return this.intlRescheduledReceive;
	}

	public void setIntlRescheduledReceive(Double intlRescheduledReceive) {
		this.intlRescheduledReceive = intlRescheduledReceive;
	}

	@Column(name = "INTL_RESCHEDULED_PAY", precision = 10)
	public Double getIntlRescheduledPay() {
		return this.intlRescheduledPay;
	}

	public void setIntlRescheduledPay(Double intlRescheduledPay) {
		this.intlRescheduledPay = intlRescheduledPay;
	}

	@Column(name = "INTL_RESCHEDULED_PROFITS", precision = 10)
	public Double getIntlRescheduledProfits() {
		return this.intlRescheduledProfits;
	}

	public void setIntlRescheduledProfits(Double intlRescheduledProfits) {
		this.intlRescheduledProfits = intlRescheduledProfits;
	}

	@Column(name = "INTL_RESCHEDULED_STATUS", length = 5)
	public String getIntlRescheduledStatus() {
		return this.intlRescheduledStatus;
	}

	public void setIntlRescheduledStatus(String intlRescheduledStatus) {
		this.intlRescheduledStatus = intlRescheduledStatus;
	}

	@Column(name = "INTL_UPGRADES_RECEIVE", precision = 10)
	public Double getIntlUpgradesReceive() {
		return intlUpgradesReceive;
	}

	public void setIntlUpgradesReceive(Double intlUpgradesReceive) {
		this.intlUpgradesReceive = intlUpgradesReceive;
	}

	@Column(name = "INTL_UPGRADES_PAY", precision = 10)
	public Double getIntlUpgradesPay() {
		return intlUpgradesPay;
	}

	public void setIntlUpgradesPay(Double intlUpgradesPay) {
		this.intlUpgradesPay = intlUpgradesPay;
	}

	@Column(name = "ORDERID", nullable = false, precision = 22, scale = 0)
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "CUSTOMER_TRANSFERS_OF_PAYMENT", length = 10)
	public String getCustomerTransfersOfPayment() {
		return customerTransfersOfPayment;
	}

	public void setCustomerTransfersOfPayment(String customerTransfersOfPayment) {
		this.customerTransfersOfPayment = customerTransfersOfPayment;
	}

	@Column(name = "INTL_METHOD_OF_PAYMENT", length = 10)
	public String getIntlMethodOfPayment() {
		return intlMethodOfPayment;
	}

	public void setIntlMethodOfPayment(String intlMethodOfPayment) {
		this.intlMethodOfPayment = intlMethodOfPayment;
	}

	@Column(name = "INTL_SUPPLIER", length = 200)
	public String getIntlSupplier() {
		return intlSupplier;
	}

	public void setIntlSupplier(String intlSupplier) {
		this.intlSupplier = intlSupplier;
	}

	@Column(name = "INTL_REFUSE_TO_REASON", length = 500)
	public String getIntlRefuseToReason() {
		return intlRefuseToReason;
	}

	public void setIntlRefuseToReason(String intlRefuseToReason) {
		this.intlRefuseToReason = intlRefuseToReason;
	}

	@Column(name = "INTL_CUSTOMER_PAYMENT_STATE", length = 5)
	public String getIntlCustomerPaymentState() {
		return intlCustomerPaymentState;
	}

	public void setIntlCustomerPaymentState(String intlCustomerPaymentState) {
		this.intlCustomerPaymentState = intlCustomerPaymentState;
	}

	@Column(name = "INTL_CUSTOMER_PAYMENT_PATTERN", length = 5)
	public String getIntlCustomerPaymentPattern() {
		return intlCustomerPaymentPattern;
	}

	public void setIntlCustomerPaymentPattern(String intlCustomerPaymentPattern) {
		this.intlCustomerPaymentPattern = intlCustomerPaymentPattern;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_CUSTOMER_PAYMENT_TIME", length = 7)
	public Date getIntlCustomerPaymentTime() {
		return intlCustomerPaymentTime;
	}

	public void setIntlCustomerPaymentTime(Date intlCustomerPaymentTime) {
		this.intlCustomerPaymentTime = intlCustomerPaymentTime;
	}

	@Column(name = "INTL_CUSTOMER_PAYMENT_NUMBER", length = 50)
	public String getIntlCustomerPaymentNumber() {
		return intlCustomerPaymentNumber;
	}

	public void setIntlCustomerPaymentNumber(String intlCustomerPaymentNumber) {
		this.intlCustomerPaymentNumber = intlCustomerPaymentNumber;
	}

	@Column(name = "INTL_PAY_METHOD_DETAIL", length = 20)
	public String getIntlPayMethodDetail() {
		return intlPayMethodDetail;
	}

	public void setIntlPayMethodDetail(String intlPayMethodDetail) {
		this.intlPayMethodDetail = intlPayMethodDetail;
	}

	@Column(name = "INTL_CHANGE_PAY_NAME", length = 200)
	public String getIntlChangePayName() {
		return intlChangePayName;
	}

	public void setIntlChangePayName(String intlChangePayName) {
		this.intlChangePayName = intlChangePayName;
	}

	@Column(name = "INTL_CHANGE_PAY_TEL", length = 20)
	public String getIntlChangePayTel() {
		return intlChangePayTel;
	}

	public void setIntlChangePayTel(String intlChangePayTel) {
		this.intlChangePayTel = intlChangePayTel;
	}

	@Column(name = "INTL_CHANGE_PAY_EMAIL", length = 50)
	public String getIntlChangePayEmail() {
		return intlChangePayEmail;
	}

	public void setIntlChangePayEmail(String intlChangePayEmail) {
		this.intlChangePayEmail = intlChangePayEmail;
	}

	@Column(name = "INTL_CHANGE_PAY_ID", length = 50)
	public String getIntlChangePayId() {
		return intlChangePayId;
	}

	public void setIntlChangePayId(String intlChangePayId) {
		this.intlChangePayId = intlChangePayId;
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
	@Column(name = "INTL_RESCHEDULE_TIMES", length = 50)
	public String getIntlRescheduleTimes() {
		return intlRescheduleTimes;
	}

	public void setIntlRescheduleTimes(String intlRescheduleTimes) {
		this.intlRescheduleTimes = intlRescheduleTimes;
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