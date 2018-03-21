package com.viptrip.intlAirticket.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TIntlOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_ORDER")
public class TIntlOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2065575335365061032L;
	private Integer orderid;
	private String intlOrderno;
	private Double intlOrdernoPrice;
	private Double intlTaxesTotalPrice;
	private String intlSubscribeName;
	private String intlSubscribeId;         //预订人在用户表中的ID号
	private String intlProxySubscribeName;
	private String intlCheckordermen;
	private String intlProxyCheckordermen;
	private Date intlSubscribeDate;
	private Date intlProxySubscribeDate;
	private Date intlCheckdate;
	private Date intlProxyCheckdate;
	private String companyOfAffiliationId;
	private String intlOrderStatus;
	private String intlPayMethod;
	private String payName; 				//支付人
	private String payTel; 					//支付人电话
	private String payEmail;				//支付人邮箱
	private String payId;					//支付人证件号	
	private Date payTime;					//支付时间
	private String tradeno;					//支付交易号
	private String payMethodDetail;			//在线支付方式
	private String intlEndorseTheBackRules; //国际机票退改签规则
	private Set<TIntlTravelItinerary> tIntlTravelItineraries = new HashSet<TIntlTravelItinerary>(
			0);

	
	private Double totalServiceFee;//服务费合计  和学银 加
	private String company;
	
	private String opReason;
	private String intlDetailPnr;
	private String intlOpUser;
	private String intlLockStatus;
	
	private String isExport;// 是否补录，1 补录散客  2补录团队 3 线上预订
    private String isWhere;//订单区分: 0:pc端  1:app端   2:网站
    private String travelType;//出行类型: 0:因公出行  1:因私出行 
	// Constructors

	/** default constructor */
	public TIntlOrder() {
	}

	/** full constructor */
	public TIntlOrder(String intlOrderno, Double intlOrdernoPrice,
			Double intlTaxesTotalPrice, String intlSubscribeName,
			String intlProxySubscribeName, String intlCheckordermen,
			String intlProxyCheckordermen, Date intlSubscribeDate,
			Date intlProxySubscribeDate, Date intlCheckdate,
			Date intlProxyCheckdate, String companyOfAffiliationId,
			String intlOrderStatus,
			Set<TIntlTravelItinerary> tIntlTravelItineraries,String company,String isExport,String isWhere) {
		this.intlOrderno = intlOrderno;
		this.intlOrdernoPrice = intlOrdernoPrice;
		this.intlTaxesTotalPrice = intlTaxesTotalPrice;
		this.intlSubscribeName = intlSubscribeName;
		this.intlProxySubscribeName = intlProxySubscribeName;
		this.intlCheckordermen = intlCheckordermen;
		this.intlProxyCheckordermen = intlProxyCheckordermen;
		this.intlSubscribeDate = intlSubscribeDate;
		this.intlProxySubscribeDate = intlProxySubscribeDate;
		this.intlCheckdate = intlCheckdate;
		this.intlProxyCheckdate = intlProxyCheckdate;
		this.companyOfAffiliationId = companyOfAffiliationId;
		this.intlOrderStatus = intlOrderStatus;
		this.tIntlTravelItineraries = tIntlTravelItineraries;
		this.company = company;
		this.isExport = isExport;
		this.isWhere = isWhere;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_ORDER")
	@SequenceGenerator(name="SEQ_INTL_ORDER", allocationSize=1,initialValue=120,sequenceName="SEQ_INTL_ORDER")
	@Column(name = "ORDERID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getOrderid() {
		return this.orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	@Column(name = "INTL_ORDERNO", length = 15)
	public String getIntlOrderno() {
		return this.intlOrderno;
	}

	public void setIntlOrderno(String intlOrderno) {
		this.intlOrderno = intlOrderno;
	}

	@Column(name = "INTL_ORDERNO_PRICE", precision = 10)
	public Double getIntlOrdernoPrice() {
		return this.intlOrdernoPrice;
	}

	public void setIntlOrdernoPrice(Double intlOrdernoPrice) {
		this.intlOrdernoPrice = intlOrdernoPrice;
	}

	@Column(name = "INTL_TAXES_TOTAL_PRICE", precision = 10)
	public Double getIntlTaxesTotalPrice() {
		return this.intlTaxesTotalPrice;
	}

	public void setIntlTaxesTotalPrice(Double intlTaxesTotalPrice) {
		this.intlTaxesTotalPrice = intlTaxesTotalPrice;
	}

	@Column(name = "INTL_SUBSCRIBE_NAME", length = 100)
	public String getIntlSubscribeName() {
		return this.intlSubscribeName;
	}

	public void setIntlSubscribeName(String intlSubscribeName) {
		this.intlSubscribeName = intlSubscribeName;
	}

	@Column(name = "INTL_SUBSCRIBE_ID", length = 15)
	public String getIntlSubscribeId() {
		return intlSubscribeId;
	}

	public void setIntlSubscribeId(String intlSubscribeId) {
		this.intlSubscribeId = intlSubscribeId;
	}

	@Column(name = "INTL_PROXY_SUBSCRIBE_NAME", length = 100)
	public String getIntlProxySubscribeName() {
		return this.intlProxySubscribeName;
	}

	public void setIntlProxySubscribeName(String intlProxySubscribeName) {
		this.intlProxySubscribeName = intlProxySubscribeName;
	}

	@Column(name = "INTL_CHECKORDERMEN", length = 100)
	public String getIntlCheckordermen() {
		return this.intlCheckordermen;
	}

	public void setIntlCheckordermen(String intlCheckordermen) {
		this.intlCheckordermen = intlCheckordermen;
	}

	@Column(name = "INTL_PROXY_CHECKORDERMEN", length = 100)
	public String getIntlProxyCheckordermen() {
		return this.intlProxyCheckordermen;
	}

	public void setIntlProxyCheckordermen(String intlProxyCheckordermen) {
		this.intlProxyCheckordermen = intlProxyCheckordermen;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_SUBSCRIBE_DATE", length = 7)
	public Date getIntlSubscribeDate() {
		return this.intlSubscribeDate;
	}

	public void setIntlSubscribeDate(Date intlSubscribeDate) {
		this.intlSubscribeDate = intlSubscribeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_PROXY_SUBSCRIBE_DATE", length = 7)
	public Date getIntlProxySubscribeDate() {
		return this.intlProxySubscribeDate;
	}

	public void setIntlProxySubscribeDate(Date intlProxySubscribeDate) {
		this.intlProxySubscribeDate = intlProxySubscribeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_CHECKDATE", length = 7)
	public Date getIntlCheckdate() {
		return this.intlCheckdate;
	}

	public void setIntlCheckdate(Date intlCheckdate) {
		this.intlCheckdate = intlCheckdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_PROXY_CHECKDATE", length = 7)
	public Date getIntlProxyCheckdate() {
		return this.intlProxyCheckdate;
	}

	public void setIntlProxyCheckdate(Date intlProxyCheckdate) {
		this.intlProxyCheckdate = intlProxyCheckdate;
	}

	@Column(name = "COMPANY_OF_AFFILIATION_ID", length = 500)
	public String getCompanyOfAffiliationId() {
		return this.companyOfAffiliationId;
	}

	public void setCompanyOfAffiliationId(String companyOfAffiliationId) {
		this.companyOfAffiliationId = companyOfAffiliationId;
	}

	@Column(name = "INTL_ORDER_STATUS", length = 5)
	public String getIntlOrderStatus() {
		return this.intlOrderStatus;
	}

	public void setIntlOrderStatus(String intlOrderStatus) {
		this.intlOrderStatus = intlOrderStatus;
	}

	@Column(name = "INTL_PAY_METHOD", length = 10)
	public String getIntlPayMethod() {
		return intlPayMethod;
	}

	public void setIntlPayMethod(String intlPayMethod) {
		this.intlPayMethod = intlPayMethod;
	}
	
	@Column(name = "PAY_NAME", length = 200)
	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	@Column(name = "PAY_TEL", length = 20)
	public String getPayTel() {
		return payTel;
	}

	public void setPayTel(String payTel) {
		this.payTel = payTel;
	}

	@Column(name = "PAY_EMAIL", length = 50)
	public String getPayEmail() {
		return payEmail;
	}

	public void setPayEmail(String payEmail) {
		this.payEmail = payEmail;
	}

	@Column(name = "PAY_ID", length = 50)
	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PAY_TIME", length = 7)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "TRADENO", length = 30)
	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}

	@Column(name = "PAY_METHOD_DETAIL", length = 20)
	public String getPayMethodDetail() {
		return payMethodDetail;
	}

	public void setPayMethodDetail(String payMethodDetail) {
		this.payMethodDetail = payMethodDetail;
	}

	
	
	@OneToMany(cascade=(CascadeType.ALL),mappedBy = "tIntlOrder")
	public Set<TIntlTravelItinerary> gettIntlTravelItineraries() {
		return tIntlTravelItineraries;
	}

	public void settIntlTravelItineraries(
			Set<TIntlTravelItinerary> tIntlTravelItineraries) {
		this.tIntlTravelItineraries = tIntlTravelItineraries;
	}

	@Column(name = "INTL_ENDORSE_THE_BACK_RULES", length = 4000)
	public String getIntlEndorseTheBackRules() {
		return intlEndorseTheBackRules;
	}

	public void setIntlEndorseTheBackRules(String intlEndorseTheBackRules) {
		this.intlEndorseTheBackRules = intlEndorseTheBackRules;
	}
	
	@Column(name = "TOTAL_SERVICE_FEE", precision = 10)
	public Double getTotalServiceFee() {
		return totalServiceFee;
	}

	public void setTotalServiceFee(Double totalServiceFee) {
		this.totalServiceFee = totalServiceFee;
	}
	@Column(name = "COMPANY", length = 500)
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	@Column(name = "OP_REASON", length = 500)
	public String getOpReason() {
		return opReason;
	}

	public void setOpReason(String opReason) {
		this.opReason = opReason;
	}
	@Column(name = "INTL_DETAIL_PNR", length = 4000)
	public String getIntlDetailPnr() {
		return intlDetailPnr;
	}

	public void setIntlDetailPnr(String intlDetailPnr) {
		this.intlDetailPnr = intlDetailPnr;
	}

	@Column(name = "INTL_OP_USER", length = 20)
	public String getIntlOpUser() {
		return intlOpUser;
	}

	public void setIntlOpUser(String intlOpUser) {
		this.intlOpUser = intlOpUser;
	}

	@Column(name = "INTL_LOCK_STATUS", length = 2)
	public String getIntlLockStatus() {
		return intlLockStatus;
	}

	public void setIntlLockStatus(String intlLockStatus) {
		this.intlLockStatus = intlLockStatus;
	}
	
	@Column(name = "IS_EXPORT", length = 10)
	public String getIsExport() {
        return isExport;
    }

    public void setIsExport(String isExport) {
        this.isExport = isExport;
    }
    @Column(name = "IS_WHERE", length = 2)
	public String getIsWhere() {
		return isWhere;
	}

	public void setIsWhere(String isWhere) {
		this.isWhere = isWhere;
	}
	@Column(name = "TRAVEL_TYPE", length = 2)
	public String getTravelType() {
		return travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}
    
}