package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelBusinessCircle extends MongoHotelObject implements Serializable{

	private static final long serialVersionUID = 1L;
	private String bcId;
    private String cnName;
    private String enName;
    private String abName;
    private String pyName;
	public String getBcId() {
		return bcId;
	}
	public void setBcId(String bcId) {
		this.bcId = bcId;
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
