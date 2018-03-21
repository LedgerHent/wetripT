package com.viptrip.hotelHtml5.vo.response.ro;

import java.util.ArrayList;
import java.util.List;


public class HotelList {
	private List<MongoHotelScenicSpot>     sightings=new ArrayList<MongoHotelScenicSpot>();          //景点
    private List<MongoHotelBusinessCircle> businessCircles=new ArrayList<MongoHotelBusinessCircle>();    //商圈
    private List<MongoHotelLandmark>      landmarks=new ArrayList<MongoHotelLandmark>();    
    private List<AreaQueryResponse>      areas=new ArrayList<AreaQueryResponse>();
    private List<MongoHotelBrand>      brands=new ArrayList<MongoHotelBrand>();
    private List<HotelQueryResponse> busiData=new ArrayList<HotelQueryResponse>();
    
    private String code="1000";
	private String message="sucess";
	public List<MongoHotelScenicSpot> getSightings() {
		return sightings;
	}
	public void setSightings(List<MongoHotelScenicSpot> sightings) {
		this.sightings = sightings;
	}
	public List<MongoHotelBusinessCircle> getBusinessCircles() {
		return businessCircles;
	}
	public void setBusinessCircles(List<MongoHotelBusinessCircle> businessCircles) {
		this.businessCircles = businessCircles;
	}
	public List<MongoHotelLandmark> getLandmarks() {
		return landmarks;
	}
	public void setLandmarks(List<MongoHotelLandmark> landmarks) {
		this.landmarks = landmarks;
	}
	public List<AreaQueryResponse> getAreas() {
		return areas;
	}
	public void setAreas(List<AreaQueryResponse> areas) {
		this.areas = areas;
	}
	public List<HotelQueryResponse> getBusiData() {
		return busiData;
	}
	public void setBusiData(List<HotelQueryResponse> busiData) {
		this.busiData = busiData;
	}
	public List<MongoHotelBrand> getBrands() {
		return brands;
	}
	public void setBrands(List<MongoHotelBrand> brands) {
		this.brands = brands;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
