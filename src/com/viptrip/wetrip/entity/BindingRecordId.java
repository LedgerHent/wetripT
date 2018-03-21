package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * BindingRecordId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class BindingRecordId implements java.io.Serializable {

	// Fields

	private String openid;
	private Long flowid;

	// Constructors

	/** default constructor */
	public BindingRecordId() {
	}

	/** full constructor */
	public BindingRecordId(String openid, Long flowid) {
		this.openid = openid;
		this.flowid = flowid;
	}

	// Property accessors

	@Column(name = "OPENID", nullable = false, length = 200)
	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Column(name = "FLOWID", nullable = false, precision = 10, scale = 0)
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
		if (!(other instanceof BindingRecordId))
			return false;
		BindingRecordId castOther = (BindingRecordId) other;

		return ((this.getOpenid() == castOther.getOpenid()) || (this
				.getOpenid() != null && castOther.getOpenid() != null && this
				.getOpenid().equals(castOther.getOpenid())))
				&& ((this.getFlowid() == castOther.getFlowid()) || (this
						.getFlowid() != null && castOther.getFlowid() != null && this
						.getFlowid().equals(castOther.getFlowid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getOpenid() == null ? 0 : this.getOpenid().hashCode());
		result = 37 * result
				+ (getFlowid() == null ? 0 : this.getFlowid().hashCode());
		return result;
	}

}