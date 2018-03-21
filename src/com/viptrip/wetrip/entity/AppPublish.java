package com.viptrip.wetrip.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="APP_PUBLISH",uniqueConstraints=@UniqueConstraint(columnNames="VERSION"))
public class AppPublish implements Serializable{
	private static final long serialVersionUID = 7682706781895390300L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_APP_PUBLISH")
	@SequenceGenerator(name="SEQ_APP_PUBLISH",allocationSize=1,initialValue=1, sequenceName="SEQ_APP_PUBLISH")
	private Integer id;
	@Column(length=2,nullable=false)
	private Integer type;
	@Column(columnDefinition="VARCHAR2(20)",nullable=false,unique=true)
	private String version;
	@Column(columnDefinition="VARCHAR2(2000)")
	private String description;
	@Column(columnDefinition="VARCHAR2(200)")
	private String rule;
	@Column(columnDefinition="DATE")
	private Date time;
	@Column(columnDefinition="VARCHAR2(1000)")
	private String url;
	@Column(length=1)
	private Integer status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRule() {
		return rule;
	}
	public void setRule(String rule) {
		this.rule = rule;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
