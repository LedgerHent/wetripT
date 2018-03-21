package com.viptrip.intlAirticket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * TIntlServicefee entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_INTL_SERVICEFEE")
public class TIntlServicefee implements java.io.Serializable{
    
	
	private Long SId;
	private Long orgid;
	private String nationalCalcServiceFeeType;
	private String startarea;
	private String toarea;
	private String nationalServiceFeeType;
	private Double nationalServiceFee;
	private String nationalChangeServiceFeeType;
	private Double nationalChangeServiceFee;
	private String nationalRefundServiceFeeType;
	private Double nationalRefundServiceFee;
	private String nationalNightFeeType;
	private Double nationalNightFee;
	public TIntlServicefee() {
	}

	public TIntlServicefee(Long sId, 
			String nationalCalcServiceFeeType, String startarea, String toarea,
			String nationalServiceFeeType, Double nationalServiceFee,
			String nationalChangeServiceFeeType,
			Double nationalChangeServiceFee,
			String nationalRefundServiceFeeType,
			Double nationalRefundServiceFee, String nationalNightFeeType,
			Double nationalNightFee) {
		this.SId = sId;
		this.nationalCalcServiceFeeType = nationalCalcServiceFeeType;
		this.startarea = startarea;
		this.toarea = toarea;
		this.nationalServiceFeeType = nationalServiceFeeType;
		this.nationalServiceFee = nationalServiceFee;
		this.nationalChangeServiceFeeType = nationalChangeServiceFeeType;
		this.nationalChangeServiceFee = nationalChangeServiceFee;
		this.nationalRefundServiceFeeType = nationalRefundServiceFeeType;
		this.nationalRefundServiceFee = nationalRefundServiceFee;
		this.nationalNightFeeType = nationalNightFeeType;
		this.nationalNightFee = nationalNightFee;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_T_INTL_SERVICEFEE")
	@SequenceGenerator(name="SEQ_T_INTL_SERVICEFEE", allocationSize=1,initialValue=100,sequenceName="SEQ_T_INTL_SERVICEFEE")
	@Column(name = "SID", unique = true, nullable = false, precision = 22, scale = 0)
	public Long getSId() {
		return SId;
	}

	public void setSId(Long sId) {
		SId = sId;
	}
	@Column(name = "ORGID", precision = 22, scale = 0)
	public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	/*@OneToOne(fetch=FetchType.LAZY,cascade=(CascadeType.ALL)) 
	@JoinColumn(name="ORGID")
	public TAcOrg getOrgid() {
		return orgid;
	}

	
	public void setOrgid(TAcOrg orgid) {
		this.orgid = orgid;
	}*/
	
	@Column(name = "NATIONAL_CALCSERVICEFEE_TYPE", length = 2)
	public String getNationalCalcServiceFeeType() {
		return nationalCalcServiceFeeType;
	}

	public void setNationalCalcServiceFeeType(String nationalCalcServiceFeeType) {
		this.nationalCalcServiceFeeType = nationalCalcServiceFeeType;
	}
	@Column(name = "STARTAREA", length = 20)
	public String getStartarea() {
		return startarea;
	}
	
	public void setStartarea(String startarea) {
		this.startarea = startarea;
	}
	@Column(name = "TOAREA", length = 20)
	public String getToarea() {
		return toarea;
	}

	public void setToarea(String toarea) {
		this.toarea = toarea;
	}
	@Column(name = "NATIONAL_SERVICEFEE_TYPE", length = 2)
	public String getNationalServiceFeeType() {
		return nationalServiceFeeType;
	}

	public void setNationalServiceFeeType(String nationalServiceFeeType) {
		this.nationalServiceFeeType = nationalServiceFeeType;
	}
	@Column(name = "NATIONAL_SERVICEFEE", precision = 10)
	public Double getNationalServiceFee() {
		return nationalServiceFee;
	}

	public void setNationalServiceFee(Double nationalServiceFee) {
		this.nationalServiceFee = nationalServiceFee;
	}
	@Column(name = "NATIONAL_CHANGESERVICEFEE_TYPE", length = 2)
	public String getNationalChangeServiceFeeType() {
		return nationalChangeServiceFeeType;
	}

	public void setNationalChangeServiceFeeType(String nationalChangeServiceFeeType) {
		this.nationalChangeServiceFeeType = nationalChangeServiceFeeType;
	}
	@Column(name = "NATIONAL_CHANGESERVICEFEE", precision = 10)
	public Double getNationalChangeServiceFee() {
		return nationalChangeServiceFee;
	}

	public void setNationalChangeServiceFee(Double nationalChangeServiceFee) {
		this.nationalChangeServiceFee = nationalChangeServiceFee;
	}
	@Column(name = "NATIONAL_REFUNDSERVICEFEE_TYPE", length = 2)
	public String getNationalRefundServiceFeeType() {
		return nationalRefundServiceFeeType;
	}

	public void setNationalRefundServiceFeeType(String nationalRefundServiceFeeType) {
		this.nationalRefundServiceFeeType = nationalRefundServiceFeeType;
	}
	@Column(name = "NATIONAL_REFUNDSERVICEFEE", precision = 10)
	public Double getNationalRefundServiceFee() {
		return nationalRefundServiceFee;
	}

	public void setNationalRefundServiceFee(Double nationalRefundServiceFee) {
		this.nationalRefundServiceFee = nationalRefundServiceFee;
	}
	@Column(name = "NATIONAL_NIGHTFEE_TYPE", length = 2)
	public String getNationalNightFeeType() {
		return nationalNightFeeType;
	}

	public void setNationalNightFeeType(String nationalNightFeeType) {
		this.nationalNightFeeType = nationalNightFeeType;
	}
	@Column(name = "NATIONAL_NIGHTFEE", precision = 10)
	public Double getNationalNightFee() {
		return nationalNightFee;
	}

	public void setNationalNightFee(Double nationalNightFee) {
		this.nationalNightFee = nationalNightFee;
	}


	
	/*@ManyToOne(fetch=FetchType.LAZY,cascade=(CascadeType.ALL)) 
	@JoinColumn(name="ORGID")
	public TAcOrg getOrgid() {
		return orgid;
	}

	public void setOrgid(TAcOrg orgid) {
		this.orgid = orgid;
	}*/


}
