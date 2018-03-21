package com.viptrip.intlAirticket.entity;

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

/**
 * TIntlTravelItinerary entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_TRAVEL_ITINERARY")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TIntlTravelItinerary implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
    private static final long serialVersionUID = 7395044850926196098L;

    private Integer passid;

    private TIntlOrder tIntlOrder;

    private String intlPassengerName;

    private String intlPassengerType;

    private String intlPassengerIdtype;

    private String intlPassengerId;

    private String intlPassengerTel;

    private Double intlAssurancePrice;

    private String intlCostOfBelonging;

    private Double intlTicketPrice;

    private Double intlSingleTax;

    private Double intlProxyRate;

    private String intlProjectNo;

    private String intlMail;

    private String intlSex;

    private String intlNationality;

    private Date intlPassportValidity;

    private Date intlDateOfBirth;

    private Double intlTicketDiscountPrice;

    private Double intlInsuranceReserve;

    private String intlSupplier;

    private String intlMethodOfPayment;

    private String intlMileageCard;

    private Double serviceFee;// 出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数

    private Double nightFee;// 夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数

    private String intlAssuranceNum;

    private String modifyPerson;

    private Date modifyDate;

    private String intlIssuedName;

    private Date intlIssuedDate;

    private String intlDocs;

    private String intlClassType;// 舱位等级，1-经济舱，2-公务舱，3-头等舱

    private Double intlFullPrice;// 全价舱价格

    private String adminName;// 管理员姓名

    private String adminId;// 管理员id

    private Integer insuranceId;// 保险表id
    
    private String agreementcode;//协议编码
    
    private Double elseReceiveable;//其他应收总额
    private Double elsePayable;//其他应付总额

    private String employeeNo;     //员工编号
    // Constructors

    /** default constructor */
    public TIntlTravelItinerary() {
    }

    /** minimal constructor */
    public TIntlTravelItinerary(TIntlOrder tIntlOrder) {
        this.tIntlOrder = tIntlOrder;
    }

    /** full constructor */
    public TIntlTravelItinerary(TIntlOrder tIntlOrder, String intlPassengerName, String intlPassengerType,
            String intlPassengerIdtype, String intlPassengerId, String intlPassengerTel, Double intlAssurancePrice,
            String intlCostOfBelonging, Double intlTicketPrice, Double intlSingleTax, Double intlProxyRate,
            String intlProjectNo, String intlAssuranceNum, String intlIssuedName, Date intlIssuedDate, String intlDocs,
            String intlClassType, Double intlFullPrice, String adminId, String adminName, Integer insuranceId,
            String agreementcode,Double elseReceiveable,Double elsePayable,String employeeNo) {
        this.tIntlOrder = tIntlOrder;
        this.intlPassengerName = intlPassengerName;
        this.intlPassengerType = intlPassengerType;
        this.intlPassengerIdtype = intlPassengerIdtype;
        this.intlPassengerId = intlPassengerId;
        this.intlPassengerTel = intlPassengerTel;
        this.intlAssurancePrice = intlAssurancePrice;
        this.intlCostOfBelonging = intlCostOfBelonging;
        this.intlTicketPrice = intlTicketPrice;
        this.intlSingleTax = intlSingleTax;
        this.intlProxyRate = intlProxyRate;
        this.intlProjectNo = intlProjectNo;
        this.intlAssuranceNum = intlAssuranceNum;
        this.intlIssuedName = intlIssuedName;
        this.intlIssuedDate = intlIssuedDate;
        this.intlDocs = intlDocs;
        this.intlClassType = intlClassType;
        this.intlFullPrice = intlFullPrice;
        this.adminId = adminId;
        this.adminName = adminName;
        this.insuranceId = insuranceId;
        this.agreementcode= agreementcode;
        this.elseReceiveable=elseReceiveable;
        this.elsePayable=elsePayable;
        this.employeeNo=employeeNo;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_INTL_TRAVEL_ITINERARY")
    @SequenceGenerator(name = "SEQ_INTL_TRAVEL_ITINERARY", allocationSize = 1, initialValue = 120, sequenceName = "SEQ_INTL_TRAVEL_ITINERARY")
    @Column(name = "PASSID", unique = true, nullable = false, precision = 22, scale = 0)
    public Integer getPassid() {
        return this.passid;
    }

    public void setPassid(Integer passid) {
        this.passid = passid;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDERID", nullable = false)
    public TIntlOrder gettIntlOrder() {
        return tIntlOrder;
    }

    public void settIntlOrder(TIntlOrder tIntlOrder) {
        this.tIntlOrder = tIntlOrder;
    }

    @Column(name = "INTL_PASSENGER_NAME", length = 100)
    public String getIntlPassengerName() {
        return this.intlPassengerName;
    }

    public void setIntlPassengerName(String intlPassengerName) {
        this.intlPassengerName = intlPassengerName;
    }

    @Column(name = "INTL_PASSENGER_TYPE", length = 10)
    public String getIntlPassengerType() {
        return this.intlPassengerType;
    }

    public void setIntlPassengerType(String intlPassengerType) {
        this.intlPassengerType = intlPassengerType;
    }

    @Column(name = "INTL_PASSENGER_IDTYPE", length = 10)
    public String getIntlPassengerIdtype() {
        return this.intlPassengerIdtype;
    }

    public void setIntlPassengerIdtype(String intlPassengerIdtype) {
        this.intlPassengerIdtype = intlPassengerIdtype;
    }

    @Column(name = "INTL_PASSENGER_ID", length = 50)
    public String getIntlPassengerId() {
        return this.intlPassengerId;
    }

    public void setIntlPassengerId(String intlPassengerId) {
        this.intlPassengerId = intlPassengerId;
    }

    @Column(name = "INTL_PASSENGER_TEL", length = 20)
    public String getIntlPassengerTel() {
        return this.intlPassengerTel;
    }

    public void setIntlPassengerTel(String intlPassengerTel) {
        this.intlPassengerTel = intlPassengerTel;
    }

    @Column(name = "INTL_ASSURANCE_PRICE", precision = 10)
    public Double getIntlAssurancePrice() {
        return this.intlAssurancePrice;
    }

    public void setIntlAssurancePrice(Double intlAssurancePrice) {
        this.intlAssurancePrice = intlAssurancePrice;
    }

    @Column(name = "INTL_COST_OF_BELONGING", length = 500)
    public String getIntlCostOfBelonging() {
        return this.intlCostOfBelonging;
    }

    public void setIntlCostOfBelonging(String intlCostOfBelonging) {
        this.intlCostOfBelonging = intlCostOfBelonging;
    }

    @Column(name = "INTL_TICKET_PRICE", precision = 10)
    public Double getIntlTicketPrice() {
        return this.intlTicketPrice;
    }

    public void setIntlTicketPrice(Double intlTicketPrice) {
        this.intlTicketPrice = intlTicketPrice;
    }

    @Column(name = "INTL_SINGLE_TAX", precision = 10)
    public Double getIntlSingleTax() {
        return this.intlSingleTax;
    }

    public void setIntlSingleTax(Double intlSingleTax) {
        this.intlSingleTax = intlSingleTax;
    }

    @Column(name = "INTL_PROXY_RATE", precision = 10)
    public Double getIntlProxyRate() {
        return this.intlProxyRate;
    }

    public void setIntlProxyRate(Double intlProxyRate) {
        this.intlProxyRate = intlProxyRate;
    }

    @Column(name = "INTL_PROJECT_NO", length = 50)
    public String getIntlProjectNo() {
        return this.intlProjectNo;
    }

    public void setIntlProjectNo(String intlProjectNo) {
        this.intlProjectNo = intlProjectNo;
    }

    @Column(name = "INTL_SEX", length = 10)
    public String getIntlSex() {
        return intlSex;
    }

    public void setIntlSex(String intlSex) {
        this.intlSex = intlSex;
    }

    @Column(name = "INTL_NATIONALITY", length = 50)
    public String getIntlNationality() {
        return intlNationality;
    }

    public void setIntlNationality(String intlNationality) {
        this.intlNationality = intlNationality;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INTL_PASSPORT_VALIDITY", length = 7)
    public Date getIntlPassportValidity() {
        return intlPassportValidity;
    }

    public void setIntlPassportValidity(Date intlPassportValidity) {
        this.intlPassportValidity = intlPassportValidity;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INTL_DATE_OF_BIRTH", length = 7)
    public Date getIntlDateOfBirth() {
        return intlDateOfBirth;
    }

    public void setIntlDateOfBirth(Date intlDateOfBirth) {
        this.intlDateOfBirth = intlDateOfBirth;
    }

    @Column(name = "INTL_TICKET_DISCOUNT_PRICE", precision = 10)
    public Double getIntlTicketDiscountPrice() {
        return intlTicketDiscountPrice;
    }

    public void setIntlTicketDiscountPrice(Double intlTicketDiscountPrice) {
        this.intlTicketDiscountPrice = intlTicketDiscountPrice;
    }

    @Column(name = "INTL_INSURANCE_RESERVE", precision = 10)
    public Double getIntlInsuranceReserve() {
        return intlInsuranceReserve;
    }

    public void setIntlInsuranceReserve(Double intlInsuranceReserve) {
        this.intlInsuranceReserve = intlInsuranceReserve;
    }

    @Column(name = "INTL_SUPPLIER", length = 200)
    public String getIntlSupplier() {
        return intlSupplier;
    }

    public void setIntlSupplier(String intlSupplier) {
        this.intlSupplier = intlSupplier;
    }

    @Column(name = "INTL_METHOD_OF_PAYMENT", length = 10)
    public String getIntlMethodOfPayment() {
        return intlMethodOfPayment;
    }

    public void setIntlMethodOfPayment(String intlMethodOfPayment) {
        this.intlMethodOfPayment = intlMethodOfPayment;
    }

    @Column(name = "INTL_MILEAGE_CARD", length = 50)
    public String getIntlMileageCard() {
        return intlMileageCard;
    }

    public void setIntlMileageCard(String intlMileageCard) {
        this.intlMileageCard = intlMileageCard;
    }

    @Column(name = "INTL_MAIL", length = 50)
    public String getIntlMail() {
        return intlMail;
    }

    public void setIntlMail(String intlMail) {
        this.intlMail = intlMail;
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

    @Column(name = "INTL_ASSURANCE_NUM", length = 10)
    public String getIntlAssuranceNum() {
        return intlAssuranceNum;
    }

    public void setIntlAssuranceNum(String intlAssuranceNum) {
        this.intlAssuranceNum = intlAssuranceNum;
    }

    @Column(name = "MODIFY_NAME", length = 100)
    public String getModifyPerson() {
        return modifyPerson;
    }

    public void setModifyPerson(String modifyPerson) {
        this.modifyPerson = modifyPerson;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFY_DATE", length = 7)
    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "INTL_ISSUED_NAME", length = 100)
    public String getIntlIssuedName() {
        return intlIssuedName;
    }

    public void setIntlIssuedName(String intlIssuedName) {
        this.intlIssuedName = intlIssuedName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "INTL_ISSUED_DATE", length = 7)
    public Date getIntlIssuedDate() {
        return intlIssuedDate;
    }

    public void setIntlIssuedDate(Date intlIssuedDate) {
        this.intlIssuedDate = intlIssuedDate;
    }

    @Column(name = "INTL_DOCS", length = 500)
    public String getIntlDocs() {
        return intlDocs;
    }

    public void setIntlDocs(String intlDocs) {
        this.intlDocs = intlDocs;
    }

    @Column(name = "INTL_CLASSTYPE", length = 2)
    public String getIntlClassType() {
        return intlClassType;
    }

    public void setIntlClassType(String intlClassType) {
        this.intlClassType = intlClassType;
    }

    @Column(name = "INTL_FULL_PRICE", precision = 10)
    public Double getIntlFullPrice() {
        return intlFullPrice;
    }

    public void setIntlFullPrice(Double intlFullPrice) {
        this.intlFullPrice = intlFullPrice;
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
    @Column(name = "AGREEMENTCODE", length = 500)
	public String getAgreementcode() {
		return agreementcode;
	}

	public void setAgreementcode(String agreementcode) {
		this.agreementcode = agreementcode;
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
	@Column(name = "EMPLOYEE_NO", length = 20)
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo= employeeNo;
	}
}