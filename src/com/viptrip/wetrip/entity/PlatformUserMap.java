package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * PlatformUserMap entity. @author MyEclipse Persistence Tools
 * 平台用户关联信息表
 * 
 * TODO 联合主键注解示例类：PlatformUserMap PlatformUser
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name = "PLATFORM_USER_MAP")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class PlatformUserMap implements java.io.Serializable {

	private static final long serialVersionUID = -5917932756587050216L;
	
//	private Long pid	;//     NUMBER(10)	N 平台id                                   
//	private String platformUserId;//  VARCHAR2(200)	N 平台用户id                             
//	private Long userId	;//    NUMBER(10)	N 商旅系统用户id   
	private PlatformUserMapKeys id;
	private String extend	;//  VARCHAR2(2000)	Y 扩展字段，将来可能会记录用户在平台那边的基本信息，比如说所属企业等  
	
	
	// Constructors

	/** default constructor */
	public PlatformUserMap() {
	}

	/** minimal constructor */
	public PlatformUserMap(PlatformUserMapKeys id) {
		this.id = id;
	}

	/** full constructor */
	public PlatformUserMap(PlatformUserMapKeys id, String extend) {
		this.id = id;
		this.extend = extend;
	}
	
	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "pid", column = @Column(name = "PID", unique = true, nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "platformUserId", column = @Column(name = "PLATFORM_USER_ID", unique = true, nullable = false, precision = 10, scale = 0)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)) })
	public PlatformUserMapKeys getId() {
		return id;
	}

	public void setId(PlatformUserMapKeys id) {
		this.id = id;
	}

	/*@Id
	@Column(name = "PLATFORM_USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public String getPlatformUserId() {
		return platformUserId;
	}

	public void setPlatformUserId(String platformUserId) {
		this.platformUserId = platformUserId;
	}
	
	@Id
	@Column(name = "USER_ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}*/

	@Column(name = "EXTEND", length = 2000)
	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

}
