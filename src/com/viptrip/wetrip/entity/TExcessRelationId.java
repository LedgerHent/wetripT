package com.viptrip.wetrip.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TExcessRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TExcessRelationId implements java.io.Serializable {

	// Fields

	private BigDecimal oid;
	private BigDecimal subid;
	private BigDecimal type;
	private BigDecimal businessTypes;

	// Constructors

	/** default constructor */
	public TExcessRelationId() {
	}

	/** full constructor */
	public TExcessRelationId(BigDecimal oid, BigDecimal subid, BigDecimal type,
			BigDecimal businessTypes) {
		this.oid = oid;
		this.subid = subid;
		this.type = type;
		this.businessTypes = businessTypes;
	}

	// Property accessors

	@Column(name = "OID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOid() {
		return this.oid;
	}

	public void setOid(BigDecimal oid) {
		this.oid = oid;
	}

	@Column(name = "SUBID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getSubid() {
		return this.subid;
	}

	public void setSubid(BigDecimal subid) {
		this.subid = subid;
	}

	@Column(name = "TYPE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	@Column(name = "BUSINESS_TYPES", nullable = false, precision = 22, scale = 0)
	public BigDecimal getBusinessTypes() {
		return this.businessTypes;
	}

	public void setBusinessTypes(BigDecimal businessTypes) {
		this.businessTypes = businessTypes;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TExcessRelationId))
			return false;
		TExcessRelationId castOther = (TExcessRelationId) other;

		return ((this.getOid() == castOther.getOid()) || (this.getOid() != null
				&& castOther.getOid() != null && this.getOid().equals(
				castOther.getOid())))
				&& ((this.getSubid() == castOther.getSubid()) || (this
						.getSubid() != null && castOther.getSubid() != null && this
						.getSubid().equals(castOther.getSubid())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getBusinessTypes() == castOther.getBusinessTypes()) || (this
						.getBusinessTypes() != null
						&& castOther.getBusinessTypes() != null && this
						.getBusinessTypes()
						.equals(castOther.getBusinessTypes())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOid() == null ? 0 : this.getOid().hashCode());
		result = 37 * result
				+ (getSubid() == null ? 0 : this.getSubid().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37
				* result
				+ (getBusinessTypes() == null ? 0 : this.getBusinessTypes()
						.hashCode());
		return result;
	}

}