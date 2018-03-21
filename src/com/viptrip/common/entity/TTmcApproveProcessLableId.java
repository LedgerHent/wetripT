package com.viptrip.common.entity;

// Generated 2017-11-7 18:29:51 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TTmcApproveProcessLableId generated by hbm2java
 */
@Embeddable
public class TTmcApproveProcessLableId implements java.io.Serializable {

	private int lableType;
	private long approveProcessId;
	private int lableValue;

	public TTmcApproveProcessLableId() {
	}

	public TTmcApproveProcessLableId(int lableType, long approveProcessId,
			int lableValue) {
		this.lableType = lableType;
		this.approveProcessId = approveProcessId;
		this.lableValue = lableValue;
	}

	@Column(name = "LABLE_TYPE", nullable = false, precision = 5, scale = 0)
	public int getLableType() {
		return this.lableType;
	}

	public void setLableType(int lableType) {
		this.lableType = lableType;
	}

	@Column(name = "APPROVE_PROCESS_ID", nullable = false, precision = 12, scale = 0)
	public long getApproveProcessId() {
		return this.approveProcessId;
	}

	public void setApproveProcessId(long approveProcessId) {
		this.approveProcessId = approveProcessId;
	}

	@Column(name = "LABLE_VALUE", nullable = false, precision = 5, scale = 0)
	public int getLableValue() {
		return this.lableValue;
	}

	public void setLableValue(int lableValue) {
		this.lableValue = lableValue;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TTmcApproveProcessLableId))
			return false;
		TTmcApproveProcessLableId castOther = (TTmcApproveProcessLableId) other;

		return (this.getLableType() == castOther.getLableType())
				&& (this.getApproveProcessId() == castOther
						.getApproveProcessId())
				&& (this.getLableValue() == castOther.getLableValue());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getLableType();
		result = 37 * result + (int) this.getApproveProcessId();
		result = 37 * result + this.getLableValue();
		return result;
	}

}