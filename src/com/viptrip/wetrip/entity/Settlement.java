package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SETTLEMENT")
public class Settlement implements java.io.Serializable {
	private Long sid;
	private String name;
	private String memo;
	private Long state;
	
//	private List<> settList;
	@Id
	@Column(name = "SID", unique = true, nullable = false, precision = 5, scale = 0)
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	@Column(name = "NAME", length = 20)
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
	@Column(name = "STATE", length = 5)
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Settlement [sid=" + sid + ", name=" + name + ", memo=" + memo
				+ ", state=" + state + "]";
	}
	
	
	
}
