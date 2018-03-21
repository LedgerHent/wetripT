package com.viptrip.wetrip.entity.policy;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * OrgGroupRelation entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORG_GROUP_RELATION")
public class OrgGroupRelation implements java.io.Serializable {

	// Fields

	private OrgGroupRelationId id;

	// Constructors

	/** default constructor */
	public OrgGroupRelation() {
	}

	/** full constructor */
	public OrgGroupRelation(OrgGroupRelationId id) {
		this.id = id;
	}

	// Property accessors
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "orgid", column = @Column(name = "ORGID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "groupid", column = @Column(name = "GROUPID", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "type", column = @Column(name = "TYPE", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "id", column = @Column(name = "ID", nullable = false, precision = 22, scale = 0)) })
	public OrgGroupRelationId getId() {
		return this.id;
	}

	public void setId(OrgGroupRelationId id) {
		this.id = id;
	}

}