package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class PlatformUserMapKeys implements java.io.Serializable {

	private static final long serialVersionUID = -5216955787827165240L;
	private Long pid	;//     NUMBER(10)	N 平台id                                   
	private String platformUserId;//  VARCHAR2(200)	N 平台用户id                             
	private Long userId	;// 
	
	/** default constructor */
	public PlatformUserMapKeys() {
	}

	/** full constructor */
	public PlatformUserMapKeys(Long pid, String platformUserId, Long userId) {
		this.pid = pid;
		this.platformUserId = platformUserId;
		this.userId = userId;
	}

	@Column(name = "PID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPid() {
		return pid;
	}


	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "PLATFORM_USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public String getPlatformUserId() {
		return platformUserId;
	}


	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}

	@Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlatformUserMapKeys))
			return false;
		PlatformUserMapKeys castOther = (PlatformUserMapKeys) other;

		return ((this.getPid() == castOther.getPid()) || (this.getPid() != null
				&& castOther.getPid() != null && this.getPid().equals(
				castOther.getPid())))
				&& ((this.getPlatformUserId() == castOther.getPlatformUserId()) || (this
						.getPlatformUserId() != null && castOther.getPlatformUserId() != null && this
						.getPlatformUserId().equals(castOther.getPlatformUserId())))
				&&((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(
						castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPid() == null ? 0 : this.getPid().hashCode());
		result = 37 * result
				+ (getPlatformUserId() == null ? 0 : this.getPlatformUserId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}
	
	
}
