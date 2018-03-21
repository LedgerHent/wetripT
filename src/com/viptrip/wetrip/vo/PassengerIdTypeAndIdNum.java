package com.viptrip.wetrip.vo;

import java.io.Serializable;

public class PassengerIdTypeAndIdNum implements Serializable{
	
	private static final long serialVersionUID = 4767440447191996114L;
	
	private Integer idType;
	private String idNum;
	
	public PassengerIdTypeAndIdNum() {
		super();
	}
	
	public PassengerIdTypeAndIdNum(Integer idType, String idNum) {
		super();
		this.idType = idType;
		this.idNum = idNum;
	}

	public Integer getIdType() {
		return idType;
	}
	public void setIdType(Integer idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
}
