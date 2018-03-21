package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SmsCheckCodeId entity. @author MyEclipse Persistence Tools
 */
@Embeddable
public class SmsCheckCodeId implements java.io.Serializable {

	// Fields

	private String mobile;
	private Long flowid;

	// Constructors

	/** default constructor */
	public SmsCheckCodeId() {
	}

	/** full constructor */
	public SmsCheckCodeId(String mobile, Long flowid) {
		this.mobile = mobile;
		this.flowid = flowid;
	}

	// Property accessors

	@Column(name = "MOBILE", nullable = false, length = 20)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
		if (!(other instanceof SmsCheckCodeId))
			return false;
		SmsCheckCodeId castOther = (SmsCheckCodeId) other;

		return ((this.getMobile() == castOther.getMobile()) || (this
				.getMobile() != null && castOther.getMobile() != null && this
				.getMobile().equals(castOther.getMobile())))
				&& ((this.getFlowid() == castOther.getFlowid()) || (this
						.getFlowid() != null && castOther.getFlowid() != null && this
						.getFlowid().equals(castOther.getFlowid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getMobile() == null ? 0 : this.getMobile().hashCode());
		result = 37 * result
				+ (getFlowid() == null ? 0 : this.getFlowid().hashCode());
		return result;
	}

}