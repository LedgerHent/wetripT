package com.viptrip.wetrip.entity.policy;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrgGroupRelationId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class OrgGroupRelationId implements java.io.Serializable {

	// Fields

	private BigDecimal orgid;
	private BigDecimal groupid;
	private BigDecimal type;
	private BigDecimal id;

	// Constructors

	/** default constructor */
	public OrgGroupRelationId() {
	}

	/** full constructor */
	public OrgGroupRelationId(BigDecimal orgid, BigDecimal groupid,
			BigDecimal type, BigDecimal id) {
		this.orgid = orgid;
		this.groupid = groupid;
		this.type = type;
		this.id = id;
	}

	// Property accessors

	@Column(name = "ORGID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getOrgid() {
		return this.orgid;
	}

	public void setOrgid(BigDecimal orgid) {
		this.orgid = orgid;
	}

	@Column(name = "GROUPID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getGroupid() {
		return this.groupid;
	}

	public void setGroupid(BigDecimal groupid) {
		this.groupid = groupid;
	}

	@Column(name = "TYPE", nullable = false, precision = 22, scale = 0)
	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	@Column(name = "ID", nullable = false, precision = 22, scale = 0)
	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrgGroupRelationId))
			return false;
		OrgGroupRelationId castOther = (OrgGroupRelationId) other;

		return ((this.getOrgid() == castOther.getOrgid()) || (this.getOrgid() != null
				&& castOther.getOrgid() != null && this.getOrgid().equals(
				castOther.getOrgid())))
				&& ((this.getGroupid() == castOther.getGroupid()) || (this
						.getGroupid() != null && castOther.getGroupid() != null && this
						.getGroupid().equals(castOther.getGroupid())))
				&& ((this.getType() == castOther.getType()) || (this.getType() != null
						&& castOther.getType() != null && this.getType()
						.equals(castOther.getType())))
				&& ((this.getId() == castOther.getId()) || (this.getId() != null
						&& castOther.getId() != null && this.getId().equals(
						castOther.getId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgid() == null ? 0 : this.getOrgid().hashCode());
		result = 37 * result
				+ (getGroupid() == null ? 0 : this.getGroupid().hashCode());
		result = 37 * result
				+ (getType() == null ? 0 : this.getType().hashCode());
		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		return result;
	}

}