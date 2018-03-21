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
 * TEndorse entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ENDORSE")
public class TEndorse implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 123546576876321L;
	private Long endorseid;
	private String refund;//退票规则
	private String endorsement; //改期规则
	private String cangwei;
	private String air2char;
	private String air42char;
	private String refunden;
	private String endorsementen;
	private String updateperson;
	private Date updatetime;
	private String isdel;
	private Date effectivedate;//生效日期
	private Date expirydate;  //失效日期
	// Constructors

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** default constructor */
	public TEndorse() {
	}

	/** full constructor */
	public TEndorse(String refund, String endorsement,
			String cangwei, String air2char, String air42char,
			String refunden, String endorsementen, String updateperson,
			Date updatetime) {
		this.refund = refund;
		this.endorsement = endorsement;
	
		this.cangwei = cangwei;
		this.air2char = air2char;
		this.air42char = air42char;
		this.refunden = refunden;
		this.endorsementen = endorsementen;
		this.updateperson = updateperson;
		this.updatetime = updatetime;
	}

	// Property accessors
	@Id
	@Column(name = "ENDORSEID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_ENDORSE")
	@SequenceGenerator(name="SEQ_ENDORSE",allocationSize=1,initialValue=1, sequenceName="SEQ_ENDORSE")
	public Long getEndorseid() {
		return this.endorseid;
	}

	public void setEndorseid(Long endorseid) {
		this.endorseid = endorseid;
	}

	@Column(name = "REFUND", length = 200)
	public String getRefund() {
		return this.refund;
	}

	public void setRefund(String refund) {
		this.refund = refund;
	}

	@Column(name = "ENDORSEMENT", length = 200)
	public String getEndorsement() {
		return this.endorsement;
	}

	public void setEndorsement(String endorsement) {
		this.endorsement = endorsement;
	}

	@Column(name = "CANGWEI", length = 200)
	public String getCangwei() {
		return this.cangwei;
	}

	public void setCangwei(String cangwei) {
		this.cangwei = cangwei;
	}

	@Column(name = "AIR2CHAR", length = 2)
	public String getAir2char() {
		return this.air2char;
	}

	public void setAir2char(String air2char) {
		this.air2char = air2char;
	}

	@Column(name = "AIR42CHAR", length = 10)
	public String getAir42char() {
		return this.air42char;
	}

	public void setAir42char(String air42char) {
		this.air42char = air42char;
	}


	@Column(name = "REFUNDEN", length = 200)
	public String getRefunden() {
		return this.refunden;
	}

	public void setRefunden(String refunden) {
		this.refunden = refunden;
	}

	@Column(name = "ENDORSEMENTEN", length = 200)
	public String getEndorsementen() {
		return this.endorsementen;
	}

	public void setEndorsementen(String endorsementen) {
		this.endorsementen = endorsementen;
	}

	@Column(name = "UPDATEPERSON", length = 20)
	public String getUpdateperson() {
		return this.updateperson;
	}

	public void setUpdateperson(String updateperson) {
		this.updateperson = updateperson;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATETIME", length = 7)
	public Date getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	@Column(name = "ISDEL",length=1)
	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EFFECTIVEDATE", length = 7)
	public Date getEffectivedate() {
		return effectivedate;
	}

	public void setEffectivedate(Date effectivedate) {
		this.effectivedate = effectivedate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "EXPIRYDATE", length = 7)
	public Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(Date expirydate) {
		this.expirydate = expirydate;
	}

}