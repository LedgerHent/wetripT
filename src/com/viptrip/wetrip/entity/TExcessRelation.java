package com.viptrip.wetrip.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * TExcessRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_EXCESS_RELATION")
public class TExcessRelation implements java.io.Serializable {

	// Fields

	private TExcessRelationId id;
	private String reason;

	// Constructors

	/** default constructor */
	public TExcessRelation() {
	}

	/** minimal constructor */
	public TExcessRelation(TExcessRelationId id) {
		this.id = id;
	}

	/** full constructor */
	public TExcessRelation(TExcessRelationId id, String reason) {
		this.id = id;
		this.reason = reason;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "oid", column = @Column(name = "OID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "subid", column = @Column(name = "SUBID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "type", column = @Column(name = "TYPE", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "businessTypes", column = @Column(name = "BUSINESS_TYPES", nullable = false, precision = 22, scale = 0)) })
	public TExcessRelationId getId() {
		return this.id;
	}

	public void setId(TExcessRelationId id) {
		this.id = id;
	}

	@Column(name = "REASON", length = 4000)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}