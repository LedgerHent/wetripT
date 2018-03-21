package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * TAcServicefee entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AC_SERVICEFEE")
public class TAcServicefee implements java.io.Serializable {

	// Fields
	private Integer SId;
	private Long orgid;
	private String type;
	private Double startprice;
	private Double endprice;
	private String servicefeetype;
	private Double servicefee;
	private String customerservicefeetype;
	private Double customerservicefee;

	// Constructors

	/** default constructor */
	public TAcServicefee() {
	}

	/** minimal constructor */
	public TAcServicefee(Integer SId, Long orgid, String type,
			Double startprice) {
		this.SId = SId;
		this.orgid = orgid;
		this.type = type;
		this.startprice = startprice;
	}

	/** full constructor */
	public TAcServicefee(Integer SId, Long orgid, String type,
			Double startprice, Double endprice, String servicefeetype,
			Double servicefee, String customerservicefeetype,
			Double customerservicefee) {
		this.SId = SId;
		this.orgid = orgid;
		this.type = type;
		this.startprice = startprice;
		this.endprice = endprice;
		this.servicefeetype = servicefeetype;
		this.servicefee = servicefee;
		this.customerservicefeetype = customerservicefeetype;
		this.customerservicefee = customerservicefee;
	}

	// Property accessors
	@Id
	@Column(name = "S_ID", unique = true, nullable = false, precision = 22, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AC_SERVICEFEE")
	@SequenceGenerator(name="SEQ_AC_SERVICEFEE",allocationSize=1,initialValue=1, sequenceName="SEQ_AC_SERVICEFEE")
	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	@Column(name = "ORGID", nullable = false, precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "TYPE", nullable = false, length = 2)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "STARTPRICE", nullable = false, precision = 10)
	public Double getStartprice() {
		return this.startprice;
	}

	public void setStartprice(Double startprice) {
		this.startprice = startprice;
	}

	@Column(name = "ENDPRICE", precision = 10)
	public Double getEndprice() {
		return this.endprice;
	}

	public void setEndprice(Double endprice) {
		this.endprice = endprice;
	}

	@Column(name = "SERVICEFEETYPE", length = 2)
	public String getServicefeetype() {
		return this.servicefeetype;
	}

	public void setServicefeetype(String servicefeetype) {
		this.servicefeetype = servicefeetype;
	}

	@Column(name = "SERVICEFEE", precision = 10)
	public Double getServicefee() {
		return this.servicefee;
	}

	public void setServicefee(Double servicefee) {
		this.servicefee = servicefee;
	}

	@Column(name = "CUSTOMERSERVICEFEETYPE", length = 2)
	public String getCustomerservicefeetype() {
		return this.customerservicefeetype;
	}

	public void setCustomerservicefeetype(String customerservicefeetype) {
		this.customerservicefeetype = customerservicefeetype;
	}

	@Column(name = "CUSTOMERSERVICEFEE", precision = 10)
	public Double getCustomerservicefee() {
		return this.customerservicefee;
	}

	public void setCustomerservicefee(Double customerservicefee) {
		this.customerservicefee = customerservicefee;
	}

}