package com.viptrip.wetrip.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@IdClass(OrgBussinessSwitch.class)
@Table(name="ORGBUSSINESSSWITCH")
public class OrgBussinessSwitch implements Serializable{
	private static final long serialVersionUID = 7364795619405143591L;
	
	
	
	private long orgid;
	private Integer bussinessType;
	private Integer source;
	private Integer state;
	
	@Id
	@Column(name="ORGID",length=12)
	public long getOrgid() {
		return orgid;
	}
	public void setOrgid(long orgid) {
		this.orgid = orgid;
	}
	
	@Id
	@Column(name="BUSSINESSTYPE",length=5)
	public Integer getBussinessType() {
		return bussinessType;
	}
	public void setBussinessType(Integer bussinessType) {
		this.bussinessType = bussinessType;
	}
	
	@Id
	@Column(name="SOURCE",length=5)
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	
	@Column(name="STATE",length=5)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
}
