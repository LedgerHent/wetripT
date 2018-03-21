package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;



public class MongoHotelTrafficItem extends MongoHotelObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String id;
    private String type;
    private String name;
    private String distance;
    private String way;
    private String wayType;
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getWayType() {
		return wayType;
	}
	public void setWayType(String wayType) {
		this.wayType = wayType;
	}
}
