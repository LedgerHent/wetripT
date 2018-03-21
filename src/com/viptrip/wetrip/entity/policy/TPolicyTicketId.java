package com.viptrip.wetrip.entity.policy;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TPolicyTicketId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class TPolicyTicketId implements java.io.Serializable {

	// Fields

	private Long orgid;
	private Long flowid;

	// Constructors

	/** default constructor */
	public TPolicyTicketId() {
	}

	/** full constructor */
	public TPolicyTicketId(Long orgid, Long flowid) {
		this.orgid = orgid;
		this.flowid = flowid;
	}

	// Property accessors

	@Column(name = "ORGID", nullable = false, precision = 22, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "FLOWID", nullable = false, precision = 22, scale = 0)
	public Long getFlowid() {
		return this.flowid;
	}

	public void setFlowid(Long flowid) {
		this.flowid = flowid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TPolicyTicketId))
			return false;
		TPolicyTicketId castOther = (TPolicyTicketId) other;

		return ((this.getOrgid() == castOther.getOrgid()) || (this.getOrgid() != null
				&& castOther.getOrgid() != null && this.getOrgid().equals(
				castOther.getOrgid())))
				&& ((this.getFlowid() == castOther.getFlowid()) || (this
						.getFlowid() != null && castOther.getFlowid() != null && this
						.getFlowid().equals(castOther.getFlowid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOrgid() == null ? 0 : this.getOrgid().hashCode());
		result = 37 * result
				+ (getFlowid() == null ? 0 : this.getFlowid().hashCode());
		return result;
	}

}