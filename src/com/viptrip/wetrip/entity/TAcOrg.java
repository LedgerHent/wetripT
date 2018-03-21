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
 * TAcOrg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AC_ORG")
@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate = true)
public class TAcOrg implements java.io.Serializable {

	@Override
	public String toString() {
		return "TAcOrg [account=" + account + ", accountsstartdate="
				+ accountsstartdate + ", address=" + address + ", bank=" + bank
				+ ", bjrebate=" + bjrebate + ", clientname=" + clientname
				+ ", clientphone=" + clientphone + ", company=" + company
				+ ", companyname=" + companyname + ", contactduty="
				+ contactduty + ", contractdate=" + contractdate
				+ ", createBy=" + createBy + ", createTime=" + createTime
				+ ", email=" + email + ", grogshoprebate=" + grogshoprebate
				+ ", identifynum=" + identifynum + ", inlandrebate="
				+ inlandrebate + ", internationrebate=" + internationrebate
				+ ", invoiceaddress=" + invoiceaddress + ", invoicephone="
				+ invoicephone + ", isbottom=" + isbottom
				+ ", itgrogshoprebate=" + itgrogshoprebate + ", lastModifyBy="
				+ lastModifyBy + ", lastModifyTime=" + lastModifyTime
				+ ", linkman=" + linkman + ", nextaccountsdate="
				+ nextaccountsdate + ", orgcode=" + orgcode + ", orgid="
				+ orgid + ", orgname=" + orgname + ", orgshortcode="
				+ orgshortcode + ", orgshortname=" + orgshortname
				+ ", orgtype=" + orgtype + ", pactenddate=" + pactenddate
				+ ", pactstartdate=" + pactstartdate + ", parentid=" + parentid
				+ ", paytype=" + paytype + ", phone=" + phone + ", postcode="
				+ postcode + ", remark=" + remark + ", sellname=" + sellname
				+ ", sellphone=" + sellphone + ", servicemark=" + servicemark
				+ ", settledate=" + settledate + ", shrebate=" + shrebate
				+ ", sortno=" + sortno + ", stopdate=" + stopdate
				+ ", treelayer=" + treelayer + ", treelevel=" + treelevel + "]";
	}

	// Fields
	private static final long serialVersionUID = -8295955310606521995L;
	private Long orgid;
	private String orgcode;
	private String orgname;
	private String orgshortname;
	private String orgshortcode;
	private Long parentid;
	private String orgtype;
	private String linkman;
	private String phone;
	private String address;
	private String email;
	private String postcode;
	private String bank;
	private String account;
	private String isbottom;
	private Long treelevel; 
	private String treelayer;
	private String company;
	private String remark;
	private String sortno;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;
	private String contactduty;
	private String contractdate;
	private String stopdate;
	private String clientname;
	private String clientphone;
	private String sellname;
	private String sellphone;
	private String pactstartdate;
	private String pactenddate;
	private String inlandrebate;
	private String internationrebate;
	private String grogshoprebate;
	private String itgrogshoprebate;
	private String bjrebate;
	private String shrebate;
	private String accountsstartdate;
	private String nextaccountsdate;
	private String paytype;
	private String settledate;
	private String identifynum;
	private String companyname;
	private String invoiceaddress;
	private String invoicephone;
	private String servicemark;
	private String status;
	private String nolowpricereason;
	private String projectmust;
	private String attribution;
	private String billRecPerson;
	private String billRecEmail;
	private String caiwuContactPerson;
	private String contractNo;
	private String autoSendMsg;
	private String autoSendEmail;
	private String hotelAutoSendMsg;
	private String hotelAutoSendEmail;
	private Double creditLine;
	private Integer creditWarm;
	private Integer settlementDay;
	private Integer repaymentDays;
	private Integer settlementDays;
	private String costbelongmust;
	private String verify;//新增 是否需要审核  1 需要 0不需要
	private String personaltobill;//个人支付订单是否进入对账单 新增wlh 2014 05 29
	private String caiwucontactphone;//财务联系人手机  2014 07 29
	private String billRecPersonQQ;     //账单接收人qq
	private String caiwuContactPersonQQ;//财务联系人qq
	private String billortraveltinerary;//发票or行程单
	private Date linedate;
	
	private String calcServiceFeeType;//计算服务费的方式，按机票收取（1），按订单收取（2）。设置之后，以下各服务费均按此方式收取。
	
	private String  serviceFeeType;//收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
	private Double  serviceFee;//出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	private String  changeServiceFeeType;//收取改期服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
	private Double  changeServiceFee;//改期服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	private String  refundServiceFeeType;//收取退票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（TICKET_PRICE）计算
	private Double  refundServiceFee;//退票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	private String  nightFeeType;//收取夜间服务费方式，定额（1）百分比（2），20点至8点为夜间服务时间，此期间内的出票、改期、退票均收取相等的服务费（且是在原有的各种服务费基础上累加）。计算百分比也按票面价计算。
	private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	private String serviceStatus;//是否收取服务费，否（1），是（2）
	
	
	private String customerServiceFeeType;//机票收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（）计算
	
	private Double customerServiceFee;//机票客人预订出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	
	
	private String trainCalcServiceFeeType;//火车票计算服务费的方式，按机票收取（1），按订单收取（2）。设置之后，以下各服务费均按此方式收取。
	
	private String  trainServiceFeeType;//火车票收取出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（）计算
	private Double  trainServiceFee;//火车票出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	

	private String  trainNightFeeType;//火车票收取夜间服务费方式，定额（1）百分比（2），20点至8点为夜间服务时间，此期间内的出票、改期、退票均收取相等的服务费（且是在原有的各种服务费基础上累加）。计算百分比也按票面价计算。
	private Double  trainNightFee;//火车票夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	private String trainServiceStatus;//火车票是否收取服务费，否（1），是（2）
	
	
	private String trainCustServiceFeeType;//火车票收取客人预订出票服务费方式，定额（1）百分比（2），计算百分比按预订时的票面价（）计算
	
	private Double trainCustServiceFee;//火车票客人预订出票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	
	
	
	
	private Double trainRefundServiceFee;			//火车票退票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private Double trainUpdateServiceFee;//火车票改签服务费
	private String ladderWay;//机票是否按阶梯方式收取，否（1），是（2）	
	
	private String  trainLadderWay;//火车票是否按阶梯方式收取，否（1），是（2）
	
	
	private String remindDate;// 未回款提醒时间
	private String stopIssuanceDate;// 停止出票时间
	private String confirmOrderDate;//企业确认账单时间
	

	
	private String hotelServiceStatus;//酒店预订，是否收取服务费，否（1），是（2）
	private String HotelErpNowpayType;//酒店预订，企业自有协议使用，现付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelErpNowpayFee;//酒店预订，企业自有协议使用，现付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelErpPrepaidType;//酒店预订，企业自有协议使用，预付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelErpPrepaidFee;//酒店预订，企业自有协议使用，预付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelPerpNowpayType;//酒店预订，景鸿代企业开发协议使用，现付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelPerpNowpayFee;//酒店预订，景鸿代企业开发协议使用，现付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelPerpPrepaidType;//酒店预订，景鸿代企业开发协议使用，预付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelPerpPrepaidFee;//酒店预订，景鸿代企业开发协议使用，预付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelNowpayType;//酒店预订，景鸿自有协议使用，现付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelNowpayFee;//酒店预订，景鸿自有协议使用，现付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelPrepaidType;//酒店预订，景鸿自有协议使用，预付订单服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelPrepaidFee;//酒店预订，景鸿自有协议使用，预付订单服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelChangeType;//酒店预订，订单变更服务费收取方式，定额（1）百分比（2），按订单收取
	private Double hotelChangeFee;//酒店预订，订单变更服务费如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private String hotelNightType;//酒店预订，夜间服务费方式，定额（1）百分比（2），20点至8点为夜间服务时间，（且是在原有的各种服务费基础上累加）。计算百分比也按票面价计算。
	private Double hotelNightFee;//酒店预订，夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	private String hotelServicefeeType;//酒店预定，计算服务费方式。1-按订单收取，2-按每人每间夜收取（前提是收取服务费方式是按定额）
	
	private String isBalancePay; //是否为预付款支付 0：是 1不是
	private Double minBalance; //最小余额（当企业余额小于该值时，自动提醒）
	private String isBalanceRefund;//是否已退款 0是，1 否
	private String taxPoint;//退款发票税点
	private String poundagePoint;//手续费
	// Constructors
	 //private Set<TIntlServicefee> tisf=new HashSet<TIntlServicefee>();
	private String intlServiceStatus;//国际机票是否收取服务费，否（1），是（2）
	public String settlementMonthlyWay;//月结方式 0-月结，1-半月结

	private String autoTicket;   //企业自动出票的标识
	/** default constructor */
	public TAcOrg() {
	}

	/** minimal constructor */
	public TAcOrg(String orgname, Long parentid, String orgtype) {
		this.orgname = orgname;
		this.parentid = parentid;
		this.orgtype = orgtype;
	}

	/** full constructor */
	public TAcOrg(String orgcode, String orgname, String orgshortname,
			String orgshortcode, Long parentid, String orgtype, String linkman,
			String phone, String address, String email, String postcode,
			String bank, String account, String isbottom, Long treelevel,
			String treelayer, String company, String remark, String sortno,
			Date createTime, String createBy, Date lastModifyTime,
			String lastModifyBy, String contactduty, String contractdate,
			String stopdate, String clientname, String clientphone,
			String sellname, String sellphone, String pactstartdate,
			String pactenddate, String inlandrebate, String internationrebate,
			String grogshoprebate, String itgrogshoprebate, String bjrebate,
			String shrebate, String accountsstartdate, String nextaccountsdate,
			String paytype, String settledate, String identifynum,
			String companyname, String invoiceaddress, String invoicephone,
			String servicemark,String settlementMonthlyWay) {
		this.orgcode = orgcode;
		this.orgname = orgname;
		this.orgshortname = orgshortname;
		this.orgshortcode = orgshortcode;
		this.parentid = parentid;
		this.orgtype = orgtype;
		this.linkman = linkman;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.postcode = postcode;
		this.bank = bank;
		this.account = account;
		this.isbottom = isbottom;
		this.treelevel = treelevel;
		this.treelayer = treelayer;
		this.company = company;
		this.remark = remark;
		this.sortno = sortno;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.contactduty = contactduty;
		this.contractdate = contractdate;
		this.stopdate = stopdate;
		this.clientname = clientname;
		this.clientphone = clientphone;
		this.sellname = sellname;
		this.sellphone = sellphone;
		this.pactstartdate = pactstartdate;
		this.pactenddate = pactenddate;
		this.inlandrebate = inlandrebate;
		this.internationrebate = internationrebate;
		this.grogshoprebate = grogshoprebate;
		this.itgrogshoprebate = itgrogshoprebate;
		this.bjrebate = bjrebate;
		this.shrebate = shrebate;
		this.accountsstartdate = accountsstartdate;
		this.nextaccountsdate = nextaccountsdate;
		this.paytype = paytype;
		this.settledate = settledate;
		this.identifynum = identifynum;
		this.companyname = companyname;
		this.invoiceaddress = invoiceaddress;
		this.invoicephone = invoicephone;
		this.servicemark = servicemark;
		this.settlementMonthlyWay = settlementMonthlyWay;
	}


	// Property accessors
	@Id
	@Column(name = "ORGID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AC_ORG")
	@SequenceGenerator(name="SEQ_AC_ORG",allocationSize=1,initialValue=1, sequenceName="SEQ_AC_ORG")
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "ORGCODE", length = 40)
	public String getOrgcode() {
		return this.orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	@Column(name = "ORGNAME", nullable = false, length = 150)
	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "ORGSHORTNAME", length = 40)
	public String getOrgshortname() {
		return this.orgshortname;
	}

	public void setOrgshortname(String orgshortname) {
		this.orgshortname = orgshortname;
	}

	@Column(name = "ORGSHORTCODE", length = 150)
	public String getOrgshortcode() {
		return this.orgshortcode;
	}

	public void setOrgshortcode(String orgshortcode) {
		this.orgshortcode = orgshortcode;
	}

	@Column(name = "PARENTID", nullable = false, precision = 10, scale = 0)
	public Long getParentid() {
		return this.parentid;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	@Column(name = "ORGTYPE", nullable = false, length = 2)
	public String getOrgtype() {
		return this.orgtype;
	}

	public void setOrgtype(String orgtype) {
		this.orgtype = orgtype;
	}

	@Column(name = "LINKMAN", length = 40)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "PHONE", length = 40)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDRESS", length = 400)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "POSTCODE", length = 6)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "BANK", length = 200)
	public String getBank() {
		return this.bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	@Column(name = "ACCOUNT", length = 100)
	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Column(name = "ISBOTTOM", length = 1)
	public String getIsbottom() {
		return this.isbottom;
	}

	public void setIsbottom(String isbottom) {
		this.isbottom = isbottom;
	}

	@Column(name = "ORGLEVEL", precision = 5, scale = 0)
	public Long getTreelevel() {
		return treelevel;
	}

	public void setTreelevel(Long treelevel) {
		this.treelevel = treelevel;
	}


	@Column(name = "ORGLAYER", length = 400)
	public String getTreelayer() {
		return this.treelayer;
	}

	public void setTreelayer(String treelayer) {
		this.treelayer = treelayer;
	}

	@Column(name = "COMPANY", length = 200)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	

	@Column(name = "REMARK", length = 4000)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "SORTNO", length = 20)
	public String getSortno() {
		return this.sortno;
	}

	public void setSortno(String sortno) {
		this.sortno = sortno;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_BY", length = 40)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFY_TIME", length = 7)
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name = "LAST_MODIFY_BY", length = 40)
	public String getLastModifyBy() {
		return this.lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

	@Column(name = "CONTACTDUTY", length = 100)
	public String getContactduty() {
		return this.contactduty;
	}

	public void setContactduty(String contactduty) {
		this.contactduty = contactduty;
	}

	@Column(name = "CONTRACTDATE", length = 20)
	public String getContractdate() {
		return this.contractdate;
	}

	public void setContractdate(String contractdate) {
		this.contractdate = contractdate;
	}

	@Column(name = "STOPDATE", length = 20)
	public String getStopdate() {
		return this.stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	@Column(name = "CLIENTNAME", length = 20)
	public String getClientname() {
		return this.clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
	}

	@Column(name = "CLIENTPHONE", length = 20)
	public String getClientphone() {
		return this.clientphone;
	}

	public void setClientphone(String clientphone) {
		this.clientphone = clientphone;
	}

	@Column(name = "SELLNAME", length = 20)
	public String getSellname() {
		return this.sellname;
	}

	public void setSellname(String sellname) {
		this.sellname = sellname;
	}

	@Column(name = "SELLPHONE", length = 20)
	public String getSellphone() {
		return this.sellphone;
	}

	public void setSellphone(String sellphone) {
		this.sellphone = sellphone;
	}

	@Column(name = "PACTSTARTDATE", length = 20)
	public String getPactstartdate() {
		return this.pactstartdate;
	}

	public void setPactstartdate(String pactstartdate) {
		this.pactstartdate = pactstartdate;
	}

	@Column(name = "PACTENDDATE", length = 20)
	public String getPactenddate() {
		return this.pactenddate;
	}

	public void setPactenddate(String pactenddate) {
		this.pactenddate = pactenddate;
	}

	@Column(name = "INLANDREBATE", length = 10)
	public String getInlandrebate() {
		return this.inlandrebate;
	}

	public void setInlandrebate(String inlandrebate) {
		this.inlandrebate = inlandrebate;
	}

	@Column(name = "INTERNATIONREBATE", length = 10)
	public String getInternationrebate() {
		return this.internationrebate;
	}

	public void setInternationrebate(String internationrebate) {
		this.internationrebate = internationrebate;
	}

	@Column(name = "GROGSHOPREBATE", length = 10)
	public String getGrogshoprebate() {
		return this.grogshoprebate;
	}

	public void setGrogshoprebate(String grogshoprebate) {
		this.grogshoprebate = grogshoprebate;
	}

	@Column(name = "ITGROGSHOPREBATE", length = 10)
	public String getItgrogshoprebate() {
		return this.itgrogshoprebate;
	}

	public void setItgrogshoprebate(String itgrogshoprebate) {
		this.itgrogshoprebate = itgrogshoprebate;
	}

	@Column(name = "BJREBATE", length = 10)
	public String getBjrebate() {
		return this.bjrebate;
	}

	public void setBjrebate(String bjrebate) {
		this.bjrebate = bjrebate;
	}

	@Column(name = "SHREBATE", length = 10)
	public String getShrebate() {
		return this.shrebate;
	}

	public void setShrebate(String shrebate) {
		this.shrebate = shrebate;
	}

	@Column(name = "ACCOUNTSSTARTDATE", length = 20)
	public String getAccountsstartdate() {
		return this.accountsstartdate;
	}

	public void setAccountsstartdate(String accountsstartdate) {
		this.accountsstartdate = accountsstartdate;
	}

	@Column(name = "NEXTACCOUNTSDATE", length = 20)
	public String getNextaccountsdate() {
		return this.nextaccountsdate;
	}

	public void setNextaccountsdate(String nextaccountsdate) {
		this.nextaccountsdate = nextaccountsdate;
	}

	@Column(name = "PAYTYPE", length = 1)
	public String getPaytype() {
		return this.paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	@Column(name = "SETTLEDATE", length = 30)
	public String getSettledate() {
		return this.settledate;
	}

	public void setSettledate(String settledate) {
		this.settledate = settledate;
	}

	@Column(name = "IDENTIFYNUM", length = 20)
	public String getIdentifynum() {
		return this.identifynum;
	}

	public void setIdentifynum(String identifynum) {
		this.identifynum = identifynum;
	}

	@Column(name = "COMPANYNAME", length = 100)
	public String getCompanyname() {
		return this.companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Column(name = "INVOICEADDRESS", length = 100)
	public String getInvoiceaddress() {
		return this.invoiceaddress;
	}

	public void setInvoiceaddress(String invoiceaddress) {
		this.invoiceaddress = invoiceaddress;
	}

	@Column(name = "INVOICEPHONE", length = 20)
	public String getInvoicephone() {
		return this.invoicephone;
	}

	public void setInvoicephone(String invoicephone) {
		this.invoicephone = invoicephone;
	}

	@Column(name = "SERVICEMARK", length = 100)
	public String getServicemark() {
		return this.servicemark;
	}

	public void setServicemark(String servicemark) {
		this.servicemark = servicemark;
	}

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "NOLOWPRICEREASON", length = 1)
	public String getNolowpricereason() {
		return nolowpricereason;
	}

	public void setNolowpricereason(String nolowpricereason) {
		this.nolowpricereason = nolowpricereason;
	}

	@Column(name = "PROJECTMUST", length = 1)
	public String getProjectmust() {
		return projectmust;
	}

	public void setProjectmust(String projectmust) {
		this.projectmust = projectmust;
	}

	@Column(name = "BILL_REC_PERSON", length = 40)
	public String getBillRecPerson() {
		return this.billRecPerson;
	}

	public void setBillRecPerson(String billRecPerson) {
		this.billRecPerson = billRecPerson;
	}

	@Column(name = "BILL_REC_EMAIL", length = 40)
	public String getBillRecEmail() {
		return this.billRecEmail;
	}

	public void setBillRecEmail(String billRecEmail) {
		this.billRecEmail = billRecEmail;
	}

	@Column(name = "CAIWU_CONTACT_PERSON", length = 40)
	public String getCaiwuContactPerson() {
		return this.caiwuContactPerson;
	}

	public void setCaiwuContactPerson(String caiwuContactPerson) {
		this.caiwuContactPerson = caiwuContactPerson;
	}

	@Column(name = "CONTRACT_NO", length = 30)
	public String getContractNo() {
		return this.contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	@Column(name = "AUTO_SEND_MSG", length = 1)
	public String getAutoSendMsg() {
		return this.autoSendMsg;
	}

	public void setAutoSendMsg(String autoSendMsg) {
		this.autoSendMsg = autoSendMsg;
	}

	@Column(name = "AUTO_SEND_EMAIL", length = 1)
	public String getAutoSendEmail() {
		return this.autoSendEmail;
	}

	public void setAutoSendEmail(String autoSendEmail) {
		this.autoSendEmail = autoSendEmail;
	}

	@Column(name = "CREDIT_LINE", precision = 10)
	public Double getCreditLine() {
		return this.creditLine;
	}

	public void setCreditLine(Double creditLine) {
		this.creditLine = creditLine;
	}

	@Column(name = "CREDIT_WARM", precision = 22, scale = 0)
	public Integer getCreditWarm() {
		return this.creditWarm;
	}

	public void setCreditWarm(Integer creditWarm) {
		this.creditWarm = creditWarm;
	}

	@Column(name = "SETTLEMENT_DAY", precision = 22, scale = 0)
	public Integer getSettlementDay() {
		return this.settlementDay;
	}

	public void setSettlementDay(Integer settlementDay) {
		this.settlementDay = settlementDay;
	}

	@Column(name = "REPAYMENT_DAYS", precision = 22, scale = 0)
	public Integer getRepaymentDays() {
		return this.repaymentDays;
	}

	public void setRepaymentDays(Integer repaymentDays) {
		this.repaymentDays = repaymentDays;
	}

	@Column(name = "SETTLEMENT_DAYS", precision = 22, scale = 0)
	public Integer getSettlementDays() {
		return this.settlementDays;
	}

	public void setSettlementDays(Integer settlementDays) {
		this.settlementDays = settlementDays;
	}

	@Column(name = "COSTBELONGMUST", length = 1)
	public String getCostbelongmust() {
		return costbelongmust;
	}

	public void setCostbelongmust(String costbelongmust) {
		this.costbelongmust = costbelongmust;
	}
	@Column(name = "VERIFY", length = 2)
	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}
	@Column(name = "PERSONAL_TO_BILL", length = 2)
	public String getPersonaltobill() {
		return personaltobill;
	}

	public void setPersonaltobill(String personaltobill) {
		this.personaltobill = personaltobill;
	}
	@Column(name = "CAIWU_CONTACT_PHONE", length = 20)
	public String getCaiwucontactphone() {
		return caiwucontactphone;
	}

	public void setCaiwucontactphone(String caiwucontactphone) {
		this.caiwucontactphone = caiwucontactphone;
	}
	@Column(name = "BILLRECPERSONQQ", length = 20)
	public String getBillRecPersonQQ() {
		return billRecPersonQQ;
	}

	public void setBillRecPersonQQ(String billRecPersonQQ) {
		this.billRecPersonQQ = billRecPersonQQ;
	}
	@Column(name = "CAIWUCONTACTPERSONQQ", length = 20)
	public String getCaiwuContactPersonQQ() {
		return caiwuContactPersonQQ;
	}

	public void setCaiwuContactPersonQQ(String caiwuContactPersonQQ) {
		this.caiwuContactPersonQQ = caiwuContactPersonQQ;
	}
	@Column(name = "BILLORTRAVELTINERARY", length = 20)
	public String getBillortraveltinerary() {
		return billortraveltinerary;
	}

	public void setBillortraveltinerary(String billortraveltinerary) {
		this.billortraveltinerary = billortraveltinerary;
	}
	
	@Column(name = "CALCSERVICEFEETYPE", length = 2)
	public String getCalcServiceFeeType() {
		return calcServiceFeeType;
	}

	public void setCalcServiceFeeType(String calcServiceFeeType) {
		this.calcServiceFeeType = calcServiceFeeType;
	}

	@Column(name = "SERVICEFEE", precision = 10)
	public Double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Column(name = "SERVICEFEETYPE", length = 2)
	public String getServiceFeeType() {
		return serviceFeeType;
	}

	
	
	public void setServiceFeeType(String serviceFeeType) {
		this.serviceFeeType = serviceFeeType;
	}
	
	
	@Column(name = "CHANGESERVICEFEETYPE", length = 2)
	public String getChangeServiceFeeType() {
		return changeServiceFeeType;
	}

	public void setChangeServiceFeeType(String changeServiceFeeType) {
		this.changeServiceFeeType = changeServiceFeeType;
	}
	@Column(name = "CHANGESERVICEFEE", precision = 10)
	public Double getChangeServiceFee() {
		return changeServiceFee;
	}

	public void setChangeServiceFee(Double changeServiceFee) {
		this.changeServiceFee = changeServiceFee;
	}
	
	
	@Column(name = "REFUNDSERVICEFEETYPE", length = 2)
	public String getRefundServiceFeeType() {
		return refundServiceFeeType;
	}

	public void setRefundServiceFeeType(String refundServiceFeeType) {
		this.refundServiceFeeType = refundServiceFeeType;
	}
	@Column(name = "REFUNDSERVICEFEE", precision = 10)
	public Double getRefundServiceFee() {
		return refundServiceFee;
	}

	public void setRefundServiceFee(Double refundServiceFee) {
		this.refundServiceFee = refundServiceFee;
	}

	
	
	@Column(name = "NIGHTFEETYPE", length = 2)
	public String getNightFeeType() {
		return nightFeeType;
	}

	public void setNightFeeType(String nightFeeType) {
		this.nightFeeType = nightFeeType;
	}
	@Column(name = "NIGHTFEE", precision = 10)
	public Double getNightFee() {
		return nightFee;
	}

	public void setNightFee(Double nightFee) {
		this.nightFee = nightFee;
	}

	@Column(name = "SERVICESTATUS", length = 2)
	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@Column(name = "CUSTOMERSERVICEFEETYPE", length = 2)
	public String getCustomerServiceFeeType() {
		return customerServiceFeeType;
	}

	public void setCustomerServiceFeeType(String customerServiceFeeType) {
		this.customerServiceFeeType = customerServiceFeeType;
	}
	
	@Column(name = "CUSTOMERSERVICEFEE", precision = 10)
	public Double getCustomerServiceFee() {
		return customerServiceFee;
	}

	public void setCustomerServiceFee(Double customerServiceFee) {
		this.customerServiceFee = customerServiceFee;
	}
	
	@Column(name = "TRAINCALCSERVICEFEETYPE", length = 2)
	public String getTrainCalcServiceFeeType() {
		return trainCalcServiceFeeType;
	}

	public void setTrainCalcServiceFeeType(String trainCalcServiceFeeType) {
		this.trainCalcServiceFeeType = trainCalcServiceFeeType;
	}
	@Column(name = "TRAINSERVICEFEETYPE", length = 2)
	public String getTrainServiceFeeType() {
		return trainServiceFeeType;
	}

	public void setTrainServiceFeeType(String trainServiceFeeType) {
		this.trainServiceFeeType = trainServiceFeeType;
	}
	
	@Column(name = "TRAINSERVICEFEE", precision = 10)
	public Double getTrainServiceFee() {
		return trainServiceFee;
	}

	public void setTrainServiceFee(Double trainServiceFee) {
		this.trainServiceFee = trainServiceFee;
	}
	
	@Column(name = "TRAINNIGHTFEETYPE", length = 2)
	public String getTrainNightFeeType() {
		return trainNightFeeType;
	}

	public void setTrainNightFeeType(String trainNightFeeType) {
		this.trainNightFeeType = trainNightFeeType;
	}
	
	@Column(name = "TRAINNIGHTFEE", precision = 10)
	public Double getTrainNightFee() {
		return trainNightFee;
	}

	public void setTrainNightFee(Double trainNightFee) {
		this.trainNightFee = trainNightFee;
	}
	
	@Column(name = "TRAINSERVICESTATUS", length = 2)
	public String getTrainServiceStatus() {
		return trainServiceStatus;
	}

	public void setTrainServiceStatus(String trainServiceStatus) {
		this.trainServiceStatus = trainServiceStatus;
	}
	
	@Column(name = "TRAINCUSTSERVICEFEETYPE", length = 2)
	public String getTrainCustServiceFeeType() {
		return trainCustServiceFeeType;
	}

	public void setTrainCustServiceFeeType(String trainCustServiceFeeType) {
		this.trainCustServiceFeeType = trainCustServiceFeeType;
	}
	
	@Column(name = "TRAINCUSTSERVICEFEE", precision = 10)
	public Double getTrainCustServiceFee() {
		return trainCustServiceFee;
	}

	
	public void setTrainCustServiceFee(Double trainCustServiceFee) {
		this.trainCustServiceFee = trainCustServiceFee;
	}

	@Column(name = "LADDERWAY", length = 2)
	public String getLadderWay() {
		return ladderWay;
	}

	public void setLadderWay(String ladderWay) {
		this.ladderWay = ladderWay;
	}

	@Column(name = "TRAINLADDERWAY", length = 2)
	public String getTrainLadderWay() {
		return trainLadderWay;
	}

	public void setTrainLadderWay(String trainLadderWay) {
		this.trainLadderWay = trainLadderWay;
	}

	
	@Column(name = "TRAINREFUNDSERVICEFEE", precision = 10)
	public Double getTrainRefundServiceFee() {
		return trainRefundServiceFee;
	}

	public void setTrainRefundServiceFee(Double trainRefundServiceFee) {
		this.trainRefundServiceFee = trainRefundServiceFee;
	}
	@Column(name = "REMIND_DATE", length = 20)
	public String getRemindDate() {
		return remindDate;
	}

	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	@Column(name = "STOP_ISSUANCE_DATE", length = 20)
	public String getStopIssuanceDate() {
		return stopIssuanceDate;
	}

	public void setStopIssuanceDate(String stopIssuanceDate) {
		this.stopIssuanceDate = stopIssuanceDate;
	}
    @Column(name="CONFIRM_ORDER_DATE", length = 10)
	public String getConfirmOrderDate() {
		return confirmOrderDate;
	}

	public void setConfirmOrderDate(String confirmOrderDate) {
		this.confirmOrderDate = confirmOrderDate;
	}
	
	@Column(name = "HOTEL_SERVICE_STATUS", length = 2)
	public String getHotelServiceStatus() {
		return hotelServiceStatus;
	}
	
	public void setHotelServiceStatus(String hotelServiceStatus) {
		this.hotelServiceStatus = hotelServiceStatus;
	}

	@Column(name = "HOTEL_ERP_NOWPAY_TYPE", length = 2)
	public String getHotelErpNowpayType() {
		return HotelErpNowpayType;
	}

	public void setHotelErpNowpayType(String hotelErpNowpayType) {
		HotelErpNowpayType = hotelErpNowpayType;
	}

	@Column(name = "HOTEL_ERP_NOWPAY_FEE", precision = 10)
	public Double getHotelErpNowpayFee() {
		return hotelErpNowpayFee;
	}

	public void setHotelErpNowpayFee(Double hotelErpNowpayFee) {
		this.hotelErpNowpayFee = hotelErpNowpayFee;
	}

	@Column(name = "HOTEL_ERP_PREPAID_TYPE", length = 2)
	public String getHotelErpPrepaidType() {
		return hotelErpPrepaidType;
	}

	public void setHotelErpPrepaidType(String hotelErpPrepaidType) {
		this.hotelErpPrepaidType = hotelErpPrepaidType;
	}

	@Column(name = "HOTEL_ERP_PREPAID_FEE", precision = 10)
	public Double getHotelErpPrepaidFee() {
		return hotelErpPrepaidFee;
	}

	public void setHotelErpPrepaidFee(Double hotelErpPrepaidFee) {
		this.hotelErpPrepaidFee = hotelErpPrepaidFee;
	}

	@Column(name = "HOTEL_PERP_NOWPAY_TYPE", length = 2)
	public String getHotelPerpNowpayType() {
		return hotelPerpNowpayType;
	}

	public void setHotelPerpNowpayType(String hotelPerpNowpayType) {
		this.hotelPerpNowpayType = hotelPerpNowpayType;
	}

	@Column(name = "HOTEL_PERP_NOWPAY_FEE", precision = 10)
	public Double getHotelPerpNowpayFee() {
		return hotelPerpNowpayFee;
	}

	public void setHotelPerpNowpayFee(Double hotelPerpNowpayFee) {
		this.hotelPerpNowpayFee = hotelPerpNowpayFee;
	}

	@Column(name = "HOTEL_PERP_PREPAID_TYPE", length = 2)
	public String getHotelPerpPrepaidType() {
		return hotelPerpPrepaidType;
	}

	public void setHotelPerpPrepaidType(String hotelPerpPrepaidType) {
		this.hotelPerpPrepaidType = hotelPerpPrepaidType;
	}

	
	@Column(name = "HOTEL_PERP_PREPAID_FEE", precision = 10)
	public Double getHotelPerpPrepaidFee() {
		return hotelPerpPrepaidFee;
	}

	public void setHotelPerpPrepaidFee(Double hotelPerpPrepaidFee) {
		this.hotelPerpPrepaidFee = hotelPerpPrepaidFee;
	}

	@Column(name = "HOTEL_NOWPAY_TYPE", length = 2)
	public String getHotelNowpayType() {
		return hotelNowpayType;
	}

	public void setHotelNowpayType(String hotelNowpayType) {
		this.hotelNowpayType = hotelNowpayType;
	}

	@Column(name = "HOTEL_NOWPAY_FEE", precision = 10)
	public Double getHotelNowpayFee() {
		return hotelNowpayFee;
	}

	public void setHotelNowpayFee(Double hotelNowpayFee) {
		this.hotelNowpayFee = hotelNowpayFee;
	}

	@Column(name = "HOTEL_PREPAID_TYPE", length = 2)
	public String getHotelPrepaidType() {
		return hotelPrepaidType;
	}

	public void setHotelPrepaidType(String hotelPrepaidType) {
		this.hotelPrepaidType = hotelPrepaidType;
	}

	@Column(name = "HOTEL_PREPAID_FEE", precision = 10)
	public Double getHotelPrepaidFee() {
		return hotelPrepaidFee;
	}

	public void setHotelPrepaidFee(Double hotelPrepaidFee) {
		this.hotelPrepaidFee = hotelPrepaidFee;
	}

	@Column(name = "HOTEL_CHANGE_TYPE", length = 2)
	public String getHotelChangeType() {
		return hotelChangeType;
	}

	public void setHotelChangeType(String hotelChangeType) {
		this.hotelChangeType = hotelChangeType;
	}

	@Column(name = "HOTEL_CHANGE_FEE", precision = 10)
	public Double getHotelChangeFee() {
		return hotelChangeFee;
	}

	public void setHotelChangeFee(Double hotelChangeFee) {
		this.hotelChangeFee = hotelChangeFee;
	}

	@Column(name = "HOTEL_NIGHT_TYPE", length = 2)
	public String getHotelNightType() {
		return hotelNightType;
	}

	public void setHotelNightType(String hotelNightType) {
		this.hotelNightType = hotelNightType;
	}

	@Column(name = "HOTEL_NIGHT_FEE", precision = 10)
	public Double getHotelNightFee() {
		return hotelNightFee;
	}

	public void setHotelNightFee(Double hotelNightFee) {
		this.hotelNightFee = hotelNightFee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Column(name = "MIN_BALANCE", precision = 22, scale = 0)
	public Double getMinBalance() {
		return minBalance;
	}
	public void setMinBalance(Double minBalance) {
		this.minBalance = minBalance;
	}
	@Column(name = "IS_BALANCEPAY", precision = 22, scale = 0)
	public String getIsBalancePay() {
		return isBalancePay;
	}

	public void setIsBalancePay(String isBalancePay) {
		this.isBalancePay = isBalancePay;
	}
	@Column(name = "IS_BALANCEREFUND", precision = 22, scale = 0)
	public String getIsBalanceRefund() {
		return isBalanceRefund;
	}

	public void setIsBalanceRefund(String isBalanceRefund) {
		this.isBalanceRefund = isBalanceRefund;
	}
	@Column(name="TAX_POINT", length = 10)
	public String getTaxPoint() {
		return taxPoint;
	}

	public void setTaxPoint(String taxPoint) {
		this.taxPoint = taxPoint;
	}

	@Column(name="POUNDAGE_POINT", length = 10)
	public String getPoundagePoint() {
		return poundagePoint;
	}

	public void setPoundagePoint(String poundagePoint) {
		this.poundagePoint = poundagePoint;
	}

	@Column(name="HOTELSERVICEFEETYPE",length = 2)
	public String getHotelServicefeeType() {
		return hotelServicefeeType;
	}

	public void setHotelServicefeeType(String hotelServicefeeType) {
		this.hotelServicefeeType = hotelServicefeeType;
	}
	//@OneToOne(mappedBy="ORGID",cascade=(CascadeType.ALL))
	/*@OneToMany(mappedBy="orgid",cascade=(CascadeType.ALL)) 
	public Set<TIntlServicefee> getTisf() {
		return tisf;
	}

	public void setTisf(Set<TIntlServicefee> tisf) {
		this.tisf = tisf;
	}*/
	@Column(name="INTL_SERVICESTATUS",length = 2)
	public String getIntlServiceStatus() {
		return intlServiceStatus;
	}

	public void setIntlServiceStatus(String intlServiceStatus) {
		this.intlServiceStatus = intlServiceStatus;
	}
	@Column(name = "SETTLEMENT_MONTHLY_WAY", length = 2)
	public String getSettlementMonthlyWay() {
		return settlementMonthlyWay;
	}

	public void setSettlementMonthlyWay(String settlementMonthlyWay) {
		this.settlementMonthlyWay = settlementMonthlyWay;
	}
	@Column(name = "HOTEL_AUTO_SEND_MSG", length = 1)
	public String getHotelAutoSendMsg() {
		return hotelAutoSendMsg;
	}

	public void setHotelAutoSendMsg(String hotelAutoSendMsg) {
		this.hotelAutoSendMsg = hotelAutoSendMsg;
	}
	
	@Column(name = "HOTEL_AUTO_SEND_EMAIL", length = 1)
	public String getHotelAutoSendEmail() {
		return hotelAutoSendEmail;
	}

	public void setHotelAutoSendEmail(String hotelAutoSendEmail) {
		this.hotelAutoSendEmail = hotelAutoSendEmail;
	}

	@Column(name = "ATTRIBUTION", length = 2)
	public String getAttribution() {
		return attribution;
	}

	public void setAttribution(String attribution) {
		this.attribution = attribution;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LINEDATE", length = 7)
	public Date getLinedate() {
		return linedate;
	}

	public void setLinedate(Date linedate) {
		this.linedate = linedate;
	}
	
	@Column(name = "TRAIN_UPDATE_SERVICEFEE", precision = 22, scale = 0)
	public Double getTrainUpdateServiceFee() {
		return trainUpdateServiceFee;
	}

	public void setTrainUpdateServiceFee(Double trainUpdateServiceFee) {
		this.trainUpdateServiceFee = trainUpdateServiceFee;
	}
	@Column(name = "AUTO_TICKET", length = 2)
	public String getAutoTicket() {
		return autoTicket;
	}

	public void setAutoTicket(String autoTicket) {
		this.autoTicket = autoTicket;
	}

	
	
	

	
	

	


	
	
	
	
	
	
}