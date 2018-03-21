package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



/**
 * TTravelItinerary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TRAVEL_ITINERARY")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TTravelItinerary  extends AuditableEntity implements java.io.Serializable {

    private static final long serialVersionUID = -3393208319335638689L;
    // Fields

    private Long TId;
    private TPnr TPnr;
    private String travelItineraryNo;
    private String flightNumber;
    private String passengerName;
    private String passengerId;
    private String passengerIdtype;
    private Date flightTime;
    private String flightStart;
    private String flightTransfer;
    private String flightEnd;
    private Double ticketPrice;
    private Double taxPrice;
    private Double proxyRate;
    private Double enterpriseDiscount;
    private Double enterpriseOtherDiscount;
    private Double flightSum;
    private Double discountPrice;
    private Double ZPercent;
    private Double profit;
    private Double returnCost;
    private String retrunCertificate;
    private String flightStatus;
    private String passengerType;
    private String passengerTel;
    private String passengerEmail;
    private Double fueltax;
    private Double assurancePrice;
    private String orgid;
    private String parentorgid;  //费用所属部门的上级部门id
    private String projectid;
    private Double payfees;
    private Double assuranceNum;
    private String objection;
    private Double refundprofit;
    private String refundtype;
    private String proposer;
    private Date applicationdate;
    private String handlingperson;
    private Date handlingtime;
    private String ticketUseState;
    private Double ticketPay;
    private Double ticketRecieve;
    private String payment;
    private Double taxes;
    private String expStatus;
    private Double insuranceUpset;
    private String costDepartments;
    private String toname;
    private String totel;
    private String tomail;
    private String tocard;
    private String tocheckd;
    private String modifyBy;
    private Date modifyTime;
    private String supplierName;
    private String applyRetrunCertificate;
    private String agreementCode;//协议编码
    private Double issuePrice;//出票票面价
    private Double specialProfit;//特殊利润等于票面价（来自于航信，客户可见）减企业返点金额再减出票票面价
    private Double hxTicketPrice;
    private String rangeOfType;  // 航程类型  add by wwm
    
    private Double  serviceFee;//出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
    private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
    
    
    private Double customerServiceFee;//机票客人预订出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
    private String customerPaymentPattern;//客户支付方式,记录的是客户选择的支付类型（1-公司月结，2-个人支付，3-公司现结）
    // Constructors
    private Double proxyPercent;
    private String adminName;//管理员姓名
    private String adminId;//管理员id
    
    private Integer insuranceId;// 保险表id
    
    //窦鹏志添加(冗余字段)，退票退 款时 如果有改期 则需要用到改期时支付的升舱费
    private Double changedeal;//改期应付升舱费
    
    private Integer gicenInsuranceId;//赠送的保险id
    private Double gicenAssuranceNum;//赠送的保险份数
    private Double gicenInsuranceUpset;//赠送的保险底价
    
    private Double quotaProxyPercent;//定额代理费
    
   
    private Double elseReceiveable;//其他应收总额
    private Double elsePayable;//其他应付总额

    /** default constructor */
    public TTravelItinerary() {
    }

    /** minimal constructor */
    public TTravelItinerary(TPnr TPnr) {
        this.TPnr = TPnr;
    }

    /** full constructor */
    public TTravelItinerary(TPnr TPnr, String travelItineraryNo,
            String flightNumber, String passengerName, String passengerId,
            String passengerIdtype, Timestamp flightTime, String flightStart,
            String flightTransfer, String flightEnd, Double ticketPrice,
            Double taxPrice, Double proxyRate, Double enterpriseDiscount,
            Double enterpriseOtherDiscount, Double flightSum,
            Double discountPrice, Double ZPercent, Double profit,
            Double returnCost, String retrunCertificate, String flightStatus,
            String passengerType, String passengerTel, String passengerEmail,
            Double fueltax, Double assurancePrice, String orgid,
            String projectid, Double payfees, Double assuranceNum,String adminName,String adminId,Integer insuranceId,Integer gicenInsuranceId,Double gicenAssuranceNum,
            Double gicenInsuranceUpset,Double quotaProxyPercent,Double elseReceiveable,Double elsePayable) {
        this.TPnr = TPnr;
        this.travelItineraryNo = travelItineraryNo;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
        this.passengerId = passengerId;
        this.passengerIdtype = passengerIdtype;
        this.flightTime = flightTime;
        this.flightStart = flightStart;
        this.flightTransfer = flightTransfer;
        this.flightEnd = flightEnd;
        this.ticketPrice = ticketPrice;
        this.taxPrice = taxPrice;
        this.proxyRate = proxyRate;
        this.enterpriseDiscount = enterpriseDiscount;
        this.enterpriseOtherDiscount = enterpriseOtherDiscount;
        this.flightSum = flightSum;
        this.discountPrice = discountPrice;
        this.ZPercent = ZPercent;
        this.profit = profit;
        this.returnCost = returnCost;
        this.retrunCertificate = retrunCertificate;
        this.flightStatus = flightStatus;
        this.passengerType = passengerType;
        this.passengerTel = passengerTel;
        this.passengerEmail = passengerEmail;
        this.fueltax = fueltax;
        this.assurancePrice = assurancePrice;
        this.orgid = orgid;
        this.projectid = projectid;
        this.payfees = payfees;
        this.assuranceNum = assuranceNum;
        this.adminName = adminName;
        this.adminId = adminId;
        this.insuranceId = insuranceId;
        this.gicenInsuranceId = gicenInsuranceId;
        this.gicenAssuranceNum = gicenAssuranceNum;
        this.gicenInsuranceUpset = gicenInsuranceUpset;
        this.quotaProxyPercent=quotaProxyPercent;
        this.elseReceiveable=elseReceiveable;
        this.elsePayable=elsePayable;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_TTravelItinerary")
    @SequenceGenerator(name="SEQ_TTravelItinerary",allocationSize=1,initialValue=1, sequenceName="SEQ_TTravelItinerary")
    @Column(name = "T_ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getTId() {
        return this.TId;
    }

    public void setTId(Long TId) {
        this.TId = TId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PNR_ID", nullable = false)
    public TPnr getTPnr() {
        return this.TPnr;
    }

    public void setTPnr(TPnr TPnr) {
        this.TPnr = TPnr;
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
    
    @Column(name = "APPLY_RETRUN_CERTIFICATE", length = 500)
    public String getApplyRetrunCertificate() {
        return applyRetrunCertificate;
    }

    public void setApplyRetrunCertificate(String applyRetrunCertificate) {
        this.applyRetrunCertificate = applyRetrunCertificate;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    @Column(name = "PASSENGER_NAME", length = 20)
    public String getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    @Column(name = "PASSENGER_ID", length = 20)
    public String getPassengerId() {
        return this.passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    @Column(name = "PASSENGER_IDTYPE", length = 10)
    public String getPassengerIdtype() {
        return this.passengerIdtype;
    }

    public void setPassengerIdtype(String passengerIdtype) {
        this.passengerIdtype = passengerIdtype;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FLIGHT_TIME", length = 7)
    public Date getFlightTime() {
        return this.flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }

    @Column(name = "FLIGHT_START", length = 3)
    public String getFlightStart() {
        return this.flightStart;
    }

    public void setFlightStart(String flightStart) {
        this.flightStart = flightStart;
    }

    @Column(name = "FLIGHT_TRANSFER", length = 3)
    public String getFlightTransfer() {
        return this.flightTransfer;
    }

    public void setFlightTransfer(String flightTransfer) {
        this.flightTransfer = flightTransfer;
    }

    @Column(name = "FLIGHT_END", length = 3)
    public String getFlightEnd() {
        return this.flightEnd;
    }

    public void setFlightEnd(String flightEnd) {
        this.flightEnd = flightEnd;
    }

    @Column(name = "TICKET_PRICE", precision = 10)
    public Double getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Column(name = "TAX_PRICE", precision = 10)
    public Double getTaxPrice() {
        return this.taxPrice;
    }

    public void setTaxPrice(Double taxPrice) {
        this.taxPrice = taxPrice;
    }

    @Column(name = "PROXY_RATE", precision = 10)
    public Double getProxyRate() {
        return this.proxyRate;
    }

    public void setProxyRate(Double proxyRate) {
        this.proxyRate = proxyRate;
    }

    @Column(name = "ENTERPRISE_DISCOUNT", precision = 10)
    public Double getEnterpriseDiscount() {
        return this.enterpriseDiscount;
    }

    public void setEnterpriseDiscount(Double enterpriseDiscount) {
        this.enterpriseDiscount = enterpriseDiscount;
    }

    @Column(name = "ENTERPRISE_OTHER_DISCOUNT", precision = 10)
    public Double getEnterpriseOtherDiscount() {
        return this.enterpriseOtherDiscount;
    }

    public void setEnterpriseOtherDiscount(Double enterpriseOtherDiscount) {
        this.enterpriseOtherDiscount = enterpriseOtherDiscount;
    }

    @Column(name = "FLIGHT_SUM", precision = 10)
    public Double getFlightSum() {
        return this.flightSum;
    }

    public void setFlightSum(Double flightSum) {
        this.flightSum = flightSum;
    }

    @Column(name = "DISCOUNT_PRICE", precision = 10)
    public Double getDiscountPrice() {
        return this.discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Column(name = "Z_PERCENT", precision = 12)
    public Double getZPercent() {
        return this.ZPercent;
    }

    public void setZPercent(Double ZPercent) {
        this.ZPercent = ZPercent;
    }

    @Column(name = "PROXY_PERCENT", precision = 12)
    public Double getProxyPercent() {
        return proxyPercent;
    }

    public void setProxyPercent(Double proxyPercent) {
        this.proxyPercent = proxyPercent;
    }

    @Column(name = "PROFIT", precision = 10)
    public Double getProfit() {
        return this.profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Column(name = "RETURN_COST", precision = 10)
    public Double getReturnCost() {
        return this.returnCost;
    }

    public void setReturnCost(Double returnCost) {
        this.returnCost = returnCost;
    }

    @Column(name = "RETRUN_CERTIFICATE", length = 200)
    public String getRetrunCertificate() {
        return this.retrunCertificate;
    }

    public void setRetrunCertificate(String retrunCertificate) {
        this.retrunCertificate = retrunCertificate;
    }

    @Column(name = "FLIGHT_STATUS", length = 2)
    public String getFlightStatus() {
        return this.flightStatus;
    }

    public void setFlightStatus(String flightStatus) {
        this.flightStatus = flightStatus;
    }

    @Column(name = "PASSENGER_TYPE", length = 3)
    public String getPassengerType() {
        return this.passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    @Column(name = "PASSENGER_TEL", length = 20)
    public String getPassengerTel() {
        return this.passengerTel;
    }

    public void setPassengerTel(String passengerTel) {
        this.passengerTel = passengerTel;
    }

    @Column(name = "PASSENGER_EMAIL", length = 30)
    public String getPassengerEmail() {
        return this.passengerEmail;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    @Column(name = "FUELTAX", precision = 10)
    public Double getFueltax() {
        return this.fueltax;
    }

    public void setFueltax(Double fueltax) {
        this.fueltax = fueltax;
    }

    @Column(name = "ASSURANCE_PRICE", precision = 10)
    public Double getAssurancePrice() {
        return this.assurancePrice;
    }

    public void setAssurancePrice(Double assurancePrice) {
        this.assurancePrice = assurancePrice;
    }

    @Column(name = "ORGID", length = 50)
    public String getOrgid() {
        return this.orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    @Column(name = "PARENTORGID", length = 20)
    public String getParentorgid() {
        return parentorgid;
    }

    public void setParentorgid(String parentorgid) {
        this.parentorgid = parentorgid;
    }

    @Column(name = "PROJECTID", length = 50)
    public String getProjectid() {
        return this.projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Column(name = "PAYFEES", precision = 10)
    public Double getPayfees() {
        return this.payfees;
    }

    public void setPayfees(Double payfees) {
        this.payfees = payfees;
    }

    @Column(name = "ASSURANCE_NUM", precision = 10)
    public Double getAssuranceNum() {
        return this.assuranceNum;
    }

    public void setAssuranceNum(Double assuranceNum) {
        this.assuranceNum = assuranceNum;
    }
    @Column(name = "OBJECTION", length = 200)
    public String getObjection() {
        return objection;
    }
    
    public void setObjection(String objection) {
        this.objection = objection;
    }

    @Column(name = "REFUNDPROFIT", precision = 10)
    public Double getRefundprofit() {
        return refundprofit;
    }

    public void setRefundprofit(Double refundprofit) {
        this.refundprofit = refundprofit;
    }
    @Column(name = "REFUNDTYPE", length = 30)
    public String getRefundtype() {
        return refundtype;
    }

    public void setRefundtype(String refundtype) {
        this.refundtype = refundtype;
    }
    
    @Column(name = "PROPOSER", length = 30)
    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "APPLICATIONDATE", length = 7)
    public Date getApplicationdate() {
        return applicationdate;
    }

    public void setApplicationdate(Date applicationdate) {
        this.applicationdate = applicationdate;
    }

    @Column(name = "HANDLINGPERSON", length = 30)
    public String getHandlingperson() {
        return handlingperson;
    }

    public void setHandlingperson(String handlingperson) {
        this.handlingperson = handlingperson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "HANDLINGTIME", length = 7)
    public Date getHandlingtime() {
        return handlingtime;
    }

    public void setHandlingtime(Date handlingtime) {
        this.handlingtime = handlingtime;
    }

    @Column(name = "TICKET_USE_STATE", length = 2)
    public String getTicketUseState() {
        return ticketUseState;
    }

    public void setTicketUseState(String ticketUseState) {
        this.ticketUseState = ticketUseState;
    }
    
    @Column(name = "TICKET_PAY", precision = 10)
    public Double getTicketPay() {
        return ticketPay;
    }

    public void setTicketPay(Double ticketPay) {
        this.ticketPay = ticketPay;
    }

    @Column(name = "TICKET_RECIEVE", precision = 10)
    public Double getTicketRecieve() {
        return ticketRecieve;
    }

    public void setTicketRecieve(Double ticketRecieve) {
        this.ticketRecieve = ticketRecieve;
    }
    @Column(name = "TAXES", precision = 10)
    public Double getTaxes() {
        return taxes;
    }

    public void setTaxes(Double taxes) {
        this.taxes = taxes;
    }

    @Column(name = "EXP_STATUS", length = 2)
    public String getExpStatus() {
        return expStatus;
    }

    public void setExpStatus(String expStatus) {
        this.expStatus = expStatus;
    }

    @Column(name = "INSURANCE_UPSET", precision = 10)
    public Double getInsuranceUpset() {
        return insuranceUpset;
    }

    public void setInsuranceUpset(Double insuranceUpset) {
        this.insuranceUpset = insuranceUpset;
    }

    @Column(name = "COST_DEPARTMENTS")
    public String getCostDepartments() {
        return costDepartments;
    }

    public void setCostDepartments(String costDepartments) {
        this.costDepartments = costDepartments;
    }

    
    @Column(name = "PAYMENT" , length = 4)
    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
    @Column(name = "TONAME", length = 20)
    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }
    @Column(name = "TOTEL", length = 20)
    public String getTotel() {
        return totel;
    }

    public void setTotel(String totel) {
        this.totel = totel;
    }
    @Column(name = "TOMAIL", length = 50)
    public String getTomail() {
        return tomail;
    }

    public void setTomail(String tomail) {
        this.tomail = tomail;
    }
    @Column(name = "TOCARD", length = 50)
    public String getTocard() {
        return tocard;
    }

    public void setTocard(String tocard) {
        this.tocard = tocard;
    }
    @Column(name = "TOCHECKD", length = 2)
    public String getTocheckd() {
        return tocheckd;
    }

    public void setTocheckd(String tocheckd) {
        this.tocheckd = tocheckd;
    }

    @Column(name = "MODIFY_BY", length = 40)
    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_TIME", length = 7)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Column(name = "SUPPLIER_NAME", length = 100)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
   @Column(name = "AGREEMENTCODE", length = 40)
    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }
    @Column(name = "ISSUEPRICE", precision = 10)
    public Double getIssuePrice() {
        return issuePrice;
    }

    public void setIssuePrice(Double issuePrice) {
        this.issuePrice = issuePrice;
    }
    @Column(name = "SPECIALPROFIT", precision = 10)
    public Double getSpecialProfit() {
        return specialProfit;
    }

    public void setSpecialProfit(Double specialProfit) {
        this.specialProfit = specialProfit;
    }

    @Column(name = "HX_TICKET_PRICE", precision = 10)
    public Double getHxTicketPrice() {
        return hxTicketPrice;
    }
    
    public void setHxTicketPrice(Double hxTicketPrice) {
        this.hxTicketPrice = hxTicketPrice;
    }

    @Column(name = "RANGE_OF_TYPE", length = 20)
    public String getRangeOfType() {
        return rangeOfType;
    }

    public void setRangeOfType(String rangeOfType) {
        this.rangeOfType = rangeOfType;
    }
    
    
    @Column(name = "SERVICEFEE", precision = 10)
    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }
    
    @Column(name = "NIGHTFEE", precision = 10)
    public Double getNightFee() {
        return nightFee;
    }

    public void setNightFee(Double nightFee) {
        this.nightFee = nightFee;
    }
    
    @Column(name = "CUSTOMERSERVICEFEE", precision = 10)
    public Double getCustomerServiceFee() {
        return customerServiceFee;
    }

    public void setCustomerServiceFee(Double customerServiceFee) {
        this.customerServiceFee = customerServiceFee;
    }

    @Column(name = "CUSTOMER_PAYMENT_PATTERN", length = 5)
    public String getCustomerPaymentPattern() {
        return customerPaymentPattern;
    }
    
    public void setCustomerPaymentPattern(String customerPaymentPattern) {
        this.customerPaymentPattern = customerPaymentPattern;
    }
    @Transient
    public Double getChangedeal() {
        return changedeal;
    }

    public void setChangedeal(Double changedeal) {
        this.changedeal = changedeal;
    }
    
    @Column(name = "ADMINID", length = 50)
    public String getAdminId() {
        return adminId;
    }
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    @Column(name = "ADMINNAME", length = 30)
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    
    @Column(name = "INSURANCE_ID", precision = 22, scale = 0)
    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }
    @Column(name = "GICEN_INSURANCE_ID", precision = 22, scale = 0)
    public Integer getGicenInsuranceId() {
        return gicenInsuranceId;
    }

    public void setGicenInsuranceId(Integer gicenInsuranceId) {
        this.gicenInsuranceId = gicenInsuranceId;
    }
    @Column(name = "GICEN_ASSURANCE_NUM", precision = 10)
    public Double getGicenAssuranceNum() {
        return gicenAssuranceNum;
    }

    public void setGicenAssuranceNum(Double gicenAssuranceNum) {
        this.gicenAssuranceNum = gicenAssuranceNum;
    }
    @Column(name = "GICEN_INSURANCE_UPSET", precision = 10)
    public Double getGicenInsuranceUpset() {
        return gicenInsuranceUpset;
    }

    public void setGicenInsuranceUpset(Double gicenInsuranceUpset) {
        this.gicenInsuranceUpset = gicenInsuranceUpset;
    }
    @Column(name = "QUOTA_PROXY_PERCENT", precision = 10)
	public Double getQuotaProxyPercent() {
		return quotaProxyPercent;
	}

	public void setQuotaProxyPercent(Double quotaProxyPercent) {
		this.quotaProxyPercent = quotaProxyPercent;
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