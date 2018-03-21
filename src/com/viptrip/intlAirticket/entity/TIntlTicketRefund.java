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
 * TIntlTicketRefund entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_INTL_TICKET_REFUND")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TIntlTicketRefund implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2503781573237673456L;
	private Integer rfdid;
	private Date intlRfdApplyDate;
	private String intlRfdApplyName;
	private Date intlRfdAuditDate;
	private String intlRfdAuditName;
	private String intlRfdReson;
	private String intlRfdInstructions;
	private Double intlOrgRefundFee;
	private Double intlSupTicketAmount;
	private Double intlRfdProfits;
	private String intlRfdStatus;
	private Double intlOrgRefundUpdateFee;
	private Double intlSupTicketUpdateAmount;
	private Integer passid;
	private Integer orderid;
	private String rfdtype;
	private String intlpassengername;
	private String intlRetrunCertificate;//非自愿退票证明
	private String isconfirm;

	private Double intlUpgradesUpdateFee;//国际机票退改期升舱费
	private Double intlRescheduledUpdateFee;//国际机票退改期费
	
	private Double  refundServiceFee;//退票服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	private Double  nightFee;//夜间服务费，如果选定额，则单位是元，可带两位小数，否则单位是百分比，也可带两位小数
	
	private String payMethed;
	private String businessType;
	private Date issuedDate;//退票出票时间
	private String ticketno;//票号(退出票为出票票号,退改期为换开票号)
	// Constructors

	/** default constructor */
	public TIntlTicketRefund() {
	}
	public TIntlTicketRefund(Integer passid,Integer orderid){
		this.passid=passid;
		this.orderid=orderid;
	}

	/** full constructor */
	public TIntlTicketRefund(Integer passid,Integer orderid ,Timestamp intlRfdApplyDate, String intlRfdApplyName,
			Timestamp intlRfdAuditDate, String intlRfdAuditName,
			String intlRfdReson, String intlRfdInstructions,
			Double intlOrgRefundFee, Double intlSupTicketAmount,
			Double intlRfdProfits, String intlRfdStatus) {
		this.passid=passid;
		this.orderid=orderid;
		this.intlRfdApplyDate = intlRfdApplyDate;
		this.intlRfdApplyName = intlRfdApplyName;
		this.intlRfdAuditDate = intlRfdAuditDate;
		this.intlRfdAuditName = intlRfdAuditName;
		this.intlRfdReson = intlRfdReson;
		this.intlRfdInstructions = intlRfdInstructions;
		this.intlOrgRefundFee = intlOrgRefundFee;
		this.intlSupTicketAmount = intlSupTicketAmount;
		this.intlRfdProfits = intlRfdProfits;
		this.intlRfdStatus = intlRfdStatus;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_INTL_TICKET_REFUND")
	@SequenceGenerator(name="SEQ_INTL_TICKET_REFUND",allocationSize=1,initialValue=120, sequenceName="SEQ_INTL_TICKET_REFUND")
	@Column(name = "RFDID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getRfdid() {
		return this.rfdid;
	}

	public void setRfdid(Integer rfdid) {
		this.rfdid = rfdid;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_RFD_APPLY_DATE", length = 7)
	public Date getIntlRfdApplyDate() {
		return this.intlRfdApplyDate;
	}

	public void setIntlRfdApplyDate(Date intlRfdApplyDate) {
		this.intlRfdApplyDate = intlRfdApplyDate;
	}

	@Column(name = "INTL_RFD_APPLY_NAME", length = 100)
	public String getIntlRfdApplyName() {
		return this.intlRfdApplyName;
	}

	public void setIntlRfdApplyName(String intlRfdApplyName) {
		this.intlRfdApplyName = intlRfdApplyName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTL_RFD_AUDIT_DATE", length = 7)
	public Date getIntlRfdAuditDate() {
		return this.intlRfdAuditDate;
	}

	public void setIntlRfdAuditDate(Date intlRfdAuditDate) {
		this.intlRfdAuditDate = intlRfdAuditDate;
	}

	@Column(name = "INTL_RFD_AUDIT_NAME", length = 100)
	public String getIntlRfdAuditName() {
		return this.intlRfdAuditName;
	}

	public void setIntlRfdAuditName(String intlRfdAuditName) {
		this.intlRfdAuditName = intlRfdAuditName;
	}

	@Column(name = "INTL_RFD_RESON", length = 500)
	public String getIntlRfdReson() {
		return this.intlRfdReson;
	}

	public void setIntlRfdReson(String intlRfdReson) {
		this.intlRfdReson = intlRfdReson;
	}

	@Column(name = "INTL_RFD_INSTRUCTIONS", length = 500)
	public String getIntlRfdInstructions() {
		return this.intlRfdInstructions;
	}

	public void setIntlRfdInstructions(String intlRfdInstructions) {
		this.intlRfdInstructions = intlRfdInstructions;
	}

	@Column(name = "INTL_ORG_REFUND_FEE", precision = 10)
	public Double getIntlOrgRefundFee() {
		return this.intlOrgRefundFee;
	}

	public void setIntlOrgRefundFee(Double intlOrgRefundFee) {
		this.intlOrgRefundFee = intlOrgRefundFee;
	}

	@Column(name = "INTL_SUP_TICKET_AMOUNT", precision = 10)
	public Double getIntlSupTicketAmount() {
		return this.intlSupTicketAmount;
	}

	public void setIntlSupTicketAmount(Double intlSupTicketAmount) {
		this.intlSupTicketAmount = intlSupTicketAmount;
	}

	@Column(name = "INTL_RFD_PROFITS", precision = 10)
	public Double getIntlRfdProfits() {
		return this.intlRfdProfits;
	}

	public void setIntlRfdProfits(Double intlRfdProfits) {
		this.intlRfdProfits = intlRfdProfits;
	}

	@Column(name = "INTL_RFD_STATUS", length = 5)
	public String getIntlRfdStatus() {
		return this.intlRfdStatus;
	}

	public void setIntlRfdStatus(String intlRfdStatus) {
		this.intlRfdStatus = intlRfdStatus;
	}

	@Column(name = "INTL_ORG_REFUND_UPDATE_FEE", precision = 10)
	public Double getIntlOrgRefundUpdateFee() {
		return intlOrgRefundUpdateFee;
	}

	public void setIntlOrgRefundUpdateFee(Double intlOrgRefundUpdateFee) {
		this.intlOrgRefundUpdateFee = intlOrgRefundUpdateFee;
	}

	@Column(name = "INTL_SUP_TICKET_UPDATE_AMOUNT", precision = 10)
	public Double getIntlSupTicketUpdateAmount() {
		return intlSupTicketUpdateAmount;
	}

	public void setIntlSupTicketUpdateAmount(Double intlSupTicketUpdateAmount) {
		this.intlSupTicketUpdateAmount = intlSupTicketUpdateAmount;
	}
	@Column(name = "PASSID")
	public Integer getPassid() {
		return passid;
	}

	public void setPassid(Integer passid) {
		this.passid = passid;
	}
	@Column(name = "ORDERID")
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	@Column(name = "INTL_RFD_TYPE", length = 100)
	public String getRfdtype() {
		return rfdtype;
	}
	public void setRfdtype(String rfdtype) {
		this.rfdtype = rfdtype;
	}
	@Column(name = "INTL_PASSENGER_NAME", length = 100)
	public String getIntlpassengername() {
		return intlpassengername;
	}
	public void setIntlpassengername(String intlpassengername) {
		this.intlpassengername = intlpassengername;
	}
	
	@Column(name = "INTL_RETRUN_CERTIFICATE", length = 100)
	public String getIntlRetrunCertificate() {
		return intlRetrunCertificate;
	}
	public void setIntlRetrunCertificate(String intlRetrunCertificate) {
		this.intlRetrunCertificate = intlRetrunCertificate;
	}
	
	@Column(name = "ISCONFIRM", length = 1)
	public String getIsconfirm() {
		return isconfirm;
	}
	public void setIsconfirm(String isconfirm) {
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
	@Column(name = "PAY_METHED", length = 2)
	public String getPayMethed() {
		return payMethed;
	}
	public void setPayMethed(String payMethed) {
		this.payMethed = payMethed;
	}
	@Column(name = "BUSINESS_TYPE", length = 2)
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ISSUED_DATE", length = 7)
	public Date getIssuedDate() {
		return issuedDate;
	}
	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}
	@Column(name = "TICKETNO", length = 50)
	public String getTicketno() {
		return ticketno;
	}
	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}
	

}