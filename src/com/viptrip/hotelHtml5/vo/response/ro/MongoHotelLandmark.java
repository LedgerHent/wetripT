package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class MongoHotelLandmark extends MongoHotelObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String lmarkId;
	private String cnName;
	private String enName;
	private String abName;
	private String pyName;
	private String lon;
	private String lat;
	private Double distance=10d;
	
	public String getLmarkId() {
		return lmarkId;
	}
	public void setLmarkId(String lmarkId) {
		this.lmarkId = lmarkId;
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
	public String getLon() {
		return lon;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
}
