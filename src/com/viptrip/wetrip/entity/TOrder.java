package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
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

import org.hibernate.annotations.OrderBy;



/**
 * TOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ORDER")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TOrder extends AuditableEntity  implements java.io.Serializable {

    // Fields

    /**
	 * 
	 */
    private static final long serialVersionUID = 4495504019908534109L;

    private Long OId;

    private String orderno;

    private String subscribeName;

    private Date subscribeDate;

    private String orderStatus;

    private String orderLockStatus;

    private Double totalPrice;

    private Double discount;

    private String opUser;

    private String opReason;

    private Date opTime;

    private String payMethod;

    private Long ticketCount;

    private String checkmen;

    private String checkordermen;

    private Date checkdate;

    private String companyName;

    private String subscribeEmail;

    private String subscribeTel;

    private String checkcomment;

    private String checkresult;

    private String checktel;

    private String checkemail;

    private String serviceName;

    private String issuedName;

    private Date issuedDate;

    private String processstate;

    private Set<TPnr> TPnrs = new HashSet<TPnr>(0);

    private Date payTime;

    private String othersysorderno;

    private String tradeNo;

    private String proxySubscribeName;// 代预订人

    private String proxyCheckordermen;// 代审核人

    private Date proxySubscribeDate; // 代预订时间

    private Date proxyCheckdate;// 代审核时间

    private String proxySubscribeEmail;// 代订票人Email

    private String proxySubscribeTel;// 代订票人电话

    private String proxyChecktel;// 代审核人电话

    private String proxyCheckemail;// 代审核人Email

    private String payMethodDetail;// 在线支付方式，包括支付宝、荷包（PP:phonepay-荷包支付；AP:alipay-支付宝;WP:微信支付）

    private String isExport;// 是否补录，1 补录散客  2补录团队

    private String payName; // 支付人

    private String payTel; // 支付人电话

    private String payEmail; // 支付人邮箱

    private String payId; // 支付人证件号

    private String subscribeId; // 预订人在用户表中的ID号

    private Long checkmenId; // 预计审核人ID（下订单时填审核人的ID）

    private Long checkordermenId; // 实际审核人ID（如果有代审的情况，此处为代审核人的ID）

    private Double totalServiceFee;// 服务费合计 和学银 加

    private String pahOrderno; // 机加酒订单号， 李荣春 增加

    private String detailPnr; // 描述pnr
    
    private String isWhere;//订单区分: 0:pc端  1:app端   2:网站
    
    private String travelType;//出行类型: 0:因公出行  1:因私出行

    // Constructors

    /** default constructor */
    public TOrder() {
    }

    /** minimal constructor */
    public TOrder(String subscribeName) {
        this.subscribeName = subscribeName;
    }

    /** full constructor */
    public TOrder(String orderno, String subscribeName, Timestamp subscribeDate, String orderStatus,
            String orderLockStatus, Double totalPrice, Double discount, String opUser, String opReason,
            Timestamp opTime, String payMethod, Long ticketCount, String checkmen, String checkordermen,
            Timestamp checkdate, String companyName, String subscribeEmail, String subscribeTel, String checkcomment,
            String checkresult, String checktel, String checkemail, String serviceName, String issuedName,String travelType,
            Set<TPnr> TPnrs) {
        this.orderno = orderno;
        this.subscribeName = subscribeName;
        this.subscribeDate = subscribeDate;
        this.orderStatus = orderStatus;
        this.orderLockStatus = orderLockStatus;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.opUser = opUser;
        this.opReason = opReason;
        this.opTime = opTime;
        this.payMethod = payMethod;
        this.ticketCount = ticketCount;
        this.checkmen = checkmen;
        this.checkordermen = checkordermen;
        this.checkdate = checkdate;
        this.companyName = companyName;
        this.subscribeEmail = subscribeEmail;
        this.subscribeTel = subscribeTel;
        this.checkcomment = checkcomment;
        this.checkresult = checkresult;
        this.checktel = checktel;
        this.checkemail = checkemail;
        this.serviceName = serviceName;
        this.issuedName = issuedName;
        this.TPnrs = TPnrs;
        this.travelType=travelType;
    }

    // Property accessors
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER")
    @SequenceGenerator(name = "SEQ_ORDER", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_ORDER")
    @Column(name = "O_ID", unique = true, nullable = false, precision = 22, scale = 0)
    public Long getOId() {
        return this.OId;
    }

    public void setOId(Long OId) {
        this.OId = OId;
    }

    @Column(name = "ORDERNO", length = 15)
    public String getOrderno() {
        return this.orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    @Column(name = "SUBSCRIBE_NAME", nullable = false, length = 15)
    public String getSubscribeName() {
        return this.subscribeName;
    }

    public void setSubscribeName(String subscribeName) {
        this.subscribeName = subscribeName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "SUBSCRIBE_DATE", length = 7)
    public Date getSubscribeDate() {
        return this.subscribeDate;
    }

    public void setSubscribeDate(Date subscribeDate) {
        this.subscribeDate = subscribeDate;
    }

    @Column(name = "ORDER_STATUS", length = 2)
    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "ORDER_LOCK_STATUS", length = 2)
    public String getOrderLockStatus() {
        return this.orderLockStatus;
    }

    public void setOrderLockStatus(String orderLockStatus) {
        this.orderLockStatus = orderLockStatus;
    }

    @Column(name = "TOTAL_PRICE", precision = 10)
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Column(name = "DISCOUNT", precision = 10)
    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Column(name = "OP_USER", length = 20)
    public String getOpUser() {
        return this.opUser;
    }

    public void setOpUser(String opUser) {
        this.opUser = opUser;
    }

    @Column(name = "OP_REASON", length = 200)
    public String getOpReason() {
        return this.opReason;
    }

    public void setOpReason(String opReason) {
        this.opReason = opReason;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "OP_TIME", length = 7)
    public Date getOpTime() {
        return this.opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    @Column(name = "PAY_METHOD", length = 2)
    public String getPayMethod() {
        return this.payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    @Column(name = "TICKET_COUNT", precision = 22, scale = 0)
    public Long getTicketCount() {
        return this.ticketCount;
    }

    public void setTicketCount(Long ticketCount) {
        this.ticketCount = ticketCount;
    }

    @Column(name = "CHECKMEN", length = 50)
    public String getCheckmen() {
        return this.checkmen;
    }

    public void setCheckmen(String checkmen) {
        this.checkmen = checkmen;
    }

    @Column(name = "CHECKORDERMEN", length = 50)
    public String getCheckordermen() {
        return this.checkordermen;
    }

    public void setCheckordermen(String checkordermen) {
        this.checkordermen = checkordermen;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CHECKDATE", length = 7)
    public Date getCheckdate() {
        return this.checkdate;
    }

    public void setCheckdate(Date checkdate) {
        this.checkdate = checkdate;
    }

    @Column(name = "COMPANY_NAME", length = 50)
    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name = "SUBSCRIBE_EMAIL", length = 30)
    public String getSubscribeEmail() {
        return this.subscribeEmail;
    }

    public void setSubscribeEmail(String subscribeEmail) {
        this.subscribeEmail = subscribeEmail;
    }

    @Column(name = "SUBSCRIBE_TEL", length = 15)
    public String getSubscribeTel() {
        return this.subscribeTel;
    }

    public void setSubscribeTel(String subscribeTel) {
        this.subscribeTel = subscribeTel;
    }

    @Column(name = "CHECKCOMMENT", length = 3000)
    public String getCheckcomment() {
        return this.checkcomment;
    }

    public void setCheckcomment(String checkcomment) {
        this.checkcomment = checkcomment;
    }

    @Column(name = "CHECKRESULT", length = 1)
    public String getCheckresult() {
        return this.checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    @Column(name = "CHECKTEL", length = 15)
    public String getChecktel() {
        return this.checktel;
    }

    public void setChecktel(String checktel) {
        this.checktel = checktel;
    }

    @Column(name = "CHECKEMAIL", length = 30)
    public String getCheckemail() {
        return this.checkemail;
    }

    public void setCheckemail(String checkemail) {
        this.checkemail = checkemail;
    }

    @Column(name = "SERVICE_NAME", length = 15)
    public String getServiceName() {
        return this.serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(name = "ISSUED_NAME", length = 15)
    public String getIssuedName() {
        return this.issuedName;
    }

    public void setIssuedName(String issuedName) {
        this.issuedName = issuedName;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ISSUED_DATE", length = 7)
    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "TOrder")
    @OrderBy(clause = "ORIGIN_TIME asc")
    public Set<TPnr> getTPnrs() {
        return this.TPnrs;
    }

    public void setTPnrs(Set<TPnr> TPnrs) {
        this.TPnrs = TPnrs;
    }

    @Column(name = "PROCESSSTATE", length = 15)
    public String getProcessstate() {
        return processstate;
    }

    public void setProcessstate(String processstate) {
        this.processstate = processstate;
    }

    @Column(name = "PAY_TIME")
    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    @Column(name = "OTHERSYSORDERNO", length = 30)
    public String getOthersysorderno() {
        return othersysorderno;
    }

    public void setOthersysorderno(String othersysorderno) {
        this.othersysorderno = othersysorderno;
    }

    @Column(name = "TRADENO", length = 30)
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    @Column(name = "PROXY_SUBSCRIBE_NAME", length = 15)
    public String getProxySubscribeName() {
        return proxySubscribeName;
    }

    public void setProxySubscribeName(String proxySubscribeName) {
        this.proxySubscribeName = proxySubscribeName;
    }

    @Column(name = "PROXY_CHECKORDERMEN", length = 50)
    public String getProxyCheckordermen() {
        return proxyCheckordermen;
    }

    public void setProxyCheckordermen(String proxyCheckordermen) {
        this.proxyCheckordermen = proxyCheckordermen;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROXY_SUBSCRIBE_DATE", length = 7)
    public Date getProxySubscribeDate() {
        return proxySubscribeDate;
    }

    public void setProxySubscribeDate(Date proxySubscribeDate) {
        this.proxySubscribeDate = proxySubscribeDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PROXY_CHECKDATE", length = 7)
    public Date getProxyCheckdate() {
        return proxyCheckdate;
    }

    public void setProxyCheckdate(Date proxyCheckdate) {
        this.proxyCheckdate = proxyCheckdate;
    }

    @Column(name = "PROXY_SUBSCRIBE_EMAIL", length = 15)
    public String getProxySubscribeEmail() {
        return proxySubscribeEmail;
    }

    public void setProxySubscribeEmail(String proxySubscribeEmail) {
        this.proxySubscribeEmail = proxySubscribeEmail;
    }

    @Column(name = "PROXY_SUBSCRIBE_TEL", length = 15)
    public String getProxySubscribeTel() {
        return proxySubscribeTel;
    }

    public void setProxySubscribeTel(String proxySubscribeTel) {
        this.proxySubscribeTel = proxySubscribeTel;
    }

    @Column(name = "PROXY_CHECKTEL", length = 15)
    public String getProxyChecktel() {
        return proxyChecktel;
    }

    public void setProxyChecktel(String proxyChecktel) {
        this.proxyChecktel = proxyChecktel;
    }

    @Column(name = "PROXY_CHECKEMAIL", length = 30)
    public String getProxyCheckemail() {
        return proxyCheckemail;
    }

    public void setProxyCheckemail(String proxyCheckemail) {
        this.proxyCheckemail = proxyCheckemail;
    }
    @Column(name = "PAY_METHOD_DETAIL", length = 20)
    public String getPayMethodDetail() {
        return payMethodDetail;
    }

    
    public void setPayMethodDetail(String payMethodDetail) {
        this.payMethodDetail = payMethodDetail;
    }
    @Column(name = "IS_EXPORT", length = 10)
    public String getIsExport() {
        return isExport;
    }

    
    public void setIsExport(String isExport) {
        this.isExport = isExport;
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

    @Column(name = "SUBSCRIBE_ID", length = 15)
    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }

    @Column(name = "CHECKMEN_ID", precision = 22, scale = 0)
    public Long getCheckmenId() {
        return checkmenId;
    }

    public void setCheckmenId(Long checkmenId) {
        this.checkmenId = checkmenId;
    }

    @Column(name = "CHECKORDERMEN_ID", precision = 22, scale = 0)
    public Long getCheckordermenId() {
        return checkordermenId;
    }

    public void setCheckordermenId(Long checkordermenId) {
        this.checkordermenId = checkordermenId;
    }

    @Column(name = "TOTAL_SERVICE_FEE", precision = 10)
    public Double getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(Double totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    @Column(name = "PAH_ORDERNO", length = 15)
    public String getPahOrderno() {
        return pahOrderno;
    }

    public void setPahOrderno(String pahOrderno) {
        this.pahOrderno = pahOrderno;
    }

    @Column(name = "DETAIL_PNR", length = 4000)
    public String getDetailPnr() {
        return detailPnr;
    }

    public void setDetailPnr(String detailPnr) {
        this.detailPnr = detailPnr;
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
		return this.travelType;
	}

	public void setTravelType(String travelType) {
		this.travelType = travelType;
	}

	
	
	
	
}