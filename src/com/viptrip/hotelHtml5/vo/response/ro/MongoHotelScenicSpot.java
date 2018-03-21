package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelScenicSpot extends MongoHotelObject implements Serializable{

	private static final long serialVersionUID = 1L;
	private String ssId;
	private String cnName;
	private String enName;
	private String abName;
	private String pyName;
	public String getSsId() {
		return ssId;
	}
	public void setSsId(String ssId) {
		this.ssId = ssId;
	}
	public String getCnName() {
		return cnName;
	}
	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getAbName() {
		return abName;
	}
	public void setAbName(String abName) {
		this.abName = abName;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
}
