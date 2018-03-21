package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


	


/**
 * Platform entity. @author MyEclipse Persistence Tools
 * 平台信息维护表
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name = "PLATFORM")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Platform implements java.io.Serializable {

	private static final long serialVersionUID = 1822454999068644214L;

	// Fields
	private Long pid	;//NUMBER(10)	N			平台id
	private String name	;//VARCHAR2(200)	Y			平台名称
	private String memo	;//VARCHAR2(2000)	Y			平台描述
	private Integer status;//	NUMBER(10)	Y			状态，1-正常|2-停用
	private Date updateTime;//	DATE	Y			创建/修改时间
	private String authCode	;//VARCHAR2(200)	Y			授权密钥
	private Long orgid	;//NUMBER(12)	Y			关联的企业id

	// Constructors

	/** default constructor */
	public Platform() {
	}


	/** minimal constructor */
	public Platform(Long pid) {
		this.pid = pid;
	}


	/** full constructor */
	public Platform(Long pid, String name, String memo, Integer status,
			Date updateTime,String authCode,Long orgid) {
		this.pid = pid;
		this.name = name;
		this.memo = memo;
		this.status = status;
		this.updateTime = updateTime;
		this.authCode = authCode;
		this.orgid = orgid;
	}

	// Property accessors
	@Id
	@Column(name = "PID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name = "NAME", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "MEMO", length = 2000)
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "STATUS", precision = 10, scale = 0)
	public Integer getStatus() {
		return status;
	}
	
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_TIME", length = 7)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "AUTHCODE", precision = 200, scale = 0)
	public String getAuthCode() {
		return authCode;
	}


	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Column(name = "ORGID", precision = 12, scale = 0)
	public Long getOrgid() {
		return orgid;
	}


	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}


}