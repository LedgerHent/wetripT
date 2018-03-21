package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * BindingRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BINDING_RECORD")
public class BindingRecord implements java.io.Serializable {

	// Fields

	private BindingRecordId id;
	private Long type;
	private Timestamp createTime;
	private String extend;
	private String memo;

	// Constructors

	/** default constructor */
	public BindingRecord() {
	}

	/** minimal constructor */
	public BindingRecord(BindingRecordId id) {
		this.id = id;
	}

	/** full constructor */
	public BindingRecord(BindingRecordId id, Long type, Timestamp createTime,
			String extend, String memo) {
		this.id = id;
		this.type = type;
		this.createTime = createTime;
		this.extend = extend;
		this.memo = memo;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "openid", column = @Column(name = "OPENID", nullable = false, length = 200)),
			@AttributeOverride(name = "flowid", column = @Column(name = "FLOWID", nullable = false, precision = 10, scale = 0)) })
	public BindingRecordId getId() {
		return this.id;
	}

	public void setId(BindingRecordId id) {
		this.id = id;
	}

	@Column(name = "TYPE", precision = 10, scale = 0)
	public Long getType() {
		return this.type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "EXTEND", length = 200)
	public String getExtend() {
		return this.extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	@Column(name = "MEMO", length = 1000)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}