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
 * TPreferentialRules entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_PREFERENTIAL_RULES")
public class TPreferentialRules extends AuditableEntity implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1051362982512124760L;
	private Long rulesid;             //优惠规则ID
	private String orgid;             //企业ID
	private String aircompany;        //航空公司两字代码，对于一个企业，一行记录只针对一个航空公司
	private String cangwei;           //舱位代码，可能存放多个，用逗号分割
	private Date startDate;           //优惠规则开始时间
	private Date endDate;             //优惠规则结束时间
	private String exceptiontime;     //例外时间段，可存放多段具体时间，格式为：20140101^20140201，多段时间之间用半角逗号分割
	private String airport;           //出港机场三字码，可存放多个，用逗号分割
	private String exceptairportyn;   //出港机场是否例外，如果是例外（Y），则不是此出港机场出港的所有该航空公司航班都适用此折扣，如果为否（N），则此出港机场出港的所有该航空公司航班都适用此折扣
	
	private String airline;           //航班号，完整航班号，可存放多个，用逗号分割
	private String exceptairlineyn;   //航班号是否例外，是否规则参考出港机场是否例外字段
	
	private Date airlinestartdate;     //折扣适用航班号起始日期，如果上一字段为例外，则此段时间内的不适用此折扣
	private Date airlineenddate;       //折扣适用航班号截止日期
	
	private Double discountrate;      //折扣，存放0.9之类的
	private String endairport;        //到港机场三字码，可存放多个，用逗号分割
	private String exceptendairport;  //到港机场是否例外，如果是例外（Y），则不是此出港机场出港的所有该航空公司航班都适用此折扣，如果为否（N），则此出港机场出港的所有该航空公司航班都适用此折扣
	private String protocolcode;      //协议编码的录入  ：机票产品人员可任意录入 2014 07 02（增加）
	
	private Date startTripDate;		  //航班起飞（旅行）开始时间
	private Date endTripDate;		  //航班起飞（旅行）结束时间
	private Date salesStartDeadline;  //销售起始日期
	private Date salesEndDeadline;    //销售截止日期
	
	private String airway;            //折扣适用航线，出发地-到达地/出发地-到达地/……     以这样的格式保存
	private String exceptairwayyn;    //折扣适用航线是否例外，Y/N，如果是例外，则该航线不适用此折扣（与下两个字段配合），如果不是例外，则该航线适用此折扣
	private Date airwaystartdate;     //折扣适用航线旅行起始日期，如果上一字段为例外，则此段时间内的不适用此折扣
	private Date airwayenddate;       //折扣适用航线旅行截止日期

	// Constructors
	/** default constructor */
	public TPreferentialRules() {
	}

	/** full constructor */
	public TPreferentialRules(String orgid, String aircompany, 
			String cangwei, Date startDate, Date endDate,
			String exceptiontime, String airport,
			String exceptairportyn, String airline,
			String exceptairlineyn, Double discountrate,
			Date startTripDate, Date endTripDate,Date salesStartDeadline,Date salesEndDeadline
	       ) {
		this.orgid = orgid;
		this.aircompany = aircompany;
		this.cangwei = cangwei;
		this.startDate = startDate;
		this.endDate = endDate;
		this.exceptiontime = exceptiontime;
		this.airport = airport;
		this.exceptairportyn = exceptairportyn;
		this.airline = airline;
		this.exceptairlineyn = exceptairlineyn;
		this.discountrate = discountrate;
		this.startTripDate=startTripDate;
		this.endTripDate=endTripDate;
		this.salesStartDeadline=salesStartDeadline;
		this.salesEndDeadline=salesEndDeadline;
	}

	// Property accessors
	@Id
	@Column(name = "RULESID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_PREFERENTIAL_RULES")
	@SequenceGenerator(name="SEQ_PREFERENTIAL_RULES",allocationSize=1,initialValue=1, sequenceName="SEQ_PREFERENTIAL_RULES")
	public Long getRulesid() {
		return this.rulesid;
	}

	public void setRulesid(Long rulesid) {
		this.rulesid = rulesid;
	}

	@Column(name = "ORGID", length = 10)
	public String getOrgid() {
		return this.orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	@Column(name = "AIRCOMPANY", length = 2)
	public String getAircompany() {
		return this.aircompany;
	}

	public void setAircompany(String aircompany) {
		this.aircompany = aircompany;
	}

	@Column(name = "CANGWEI", length = 30)
	public String getCangwei() {
		return this.cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_DATE", length = 7)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_DATE", length = 7)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "EXCEPTIONTIME", length = 400)
	public String getExceptiontime() {
		return this.exceptiontime;
	}

	public void setExceptiontime(String exceptiontime) {
		this.exceptiontime = exceptiontime;
	}

	@Column(name = "AIRPORT", length = 400)
	public String getAirport() {
		return this.airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	@Column(name = "EXCEPTAIRPORTYN", length = 1)
	public String getExceptairportyn() {
		return this.exceptairportyn;
	}

	public void setExceptairportyn(String exceptairportyn) {
		this.exceptairportyn = exceptairportyn;
	}

	@Column(name = "AIRLINE", length = 4000)
	public String getAirline() {
		return this.airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	@Column(name = "EXCEPTAIRLINEYN", length = 1)
	public String getExceptairlineyn() {
		return this.exceptairlineyn;
	}

	public void setExceptairlineyn(String exceptairlineyn) {
		this.exceptairlineyn = exceptairlineyn;
	}

	@Column(name = "DISCOUNTRATE", precision = 12)
	public Double getDiscountrate() {
		return this.discountrate;
	}

	public void setDiscountrate(Double discountrate) {
		this.discountrate = discountrate;
	}
	@Column(name = "ENDAIRPORT", length = 400)
	public String getEndairport() {
		return endairport;
	}

	public void setEndairport(String endairport) {
		this.endairport = endairport;
	}
	
	@Column(name = "EXCEPTENDAIRPORT", length = 1)
	public String getExceptendairport() {
		return exceptendairport;
	}

	public void setExceptendairport(String exceptendairport) {
		this.exceptendairport = exceptendairport;
	}
	@Column(name = "PROTOCOLCODE", length = 400)
	public String getProtocolcode() {
		return protocolcode;
	}

	public void setProtocolcode(String protocolcode) {
		this.protocolcode = protocolcode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "START_TRIP_DATE", length = 7)
	public Date getStartTripDate() {
		return startTripDate;
	}

	public void setStartTripDate(Date startTripDate) {
		this.startTripDate = startTripDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "END_TRIP_DATE", length = 7)
	public Date getEndTripDate() {
		return endTripDate;
	}

	public void setEndTripDate(Date endTripDate) {
		this.endTripDate = endTripDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALES_START_DEADLINE", length = 7)
	public Date getSalesStartDeadline() {
		return salesStartDeadline;
	}

	public void setSalesStartDeadline(Date salesStartDeadline) {
		this.salesStartDeadline = salesStartDeadline;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SALES_END_DEADLINE", length = 7)
	public Date getSalesEndDeadline() {
		return salesEndDeadline;
	}

	public void setSalesEndDeadline(Date salesEndDeadline) {
		this.salesEndDeadline = salesEndDeadline;
	}

	@Column(name = "AIRWAY", length = 4000)
	public String getAirway() {
		return airway;
	}

	public void setAirway(String airway) {
		this.airway = airway;
	}

	@Column(name = "EXCEPTAIRWAYYN", length = 1)
	public String getExceptairwayyn() {
		return exceptairwayyn;
	}

	public void setExceptairwayyn(String exceptairwayyn) {
		this.exceptairwayyn = exceptairwayyn;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AIRWAYSTARTDATE", length = 7)
	public Date getAirwaystartdate() {
		return airwaystartdate;
	}

	public void setAirwaystartdate(Date airwaystartdate) {
		this.airwaystartdate = airwaystartdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AIRWAYENDDATE", length = 7)
	public Date getAirwayenddate() {
		return airwayenddate;
	}

	public void setAirwayenddate(Date airwayenddate) {
		this.airwayenddate = airwayenddate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AIRLINESTARTDATE", length = 7)
	public Date getAirlinestartdate() {
		return airlinestartdate;
	}
	public void setAirlinestartdate(Date airlinestartdate) {
		this.airlinestartdate = airlinestartdate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "AIRLINESENDDATE", length = 7)
	public Date getAirlineenddate() {
		return airlineenddate;
	}
	public void setAirlineenddate(Date airlineenddate) {
		this.airlineenddate = airlineenddate;
	}
	
	
}