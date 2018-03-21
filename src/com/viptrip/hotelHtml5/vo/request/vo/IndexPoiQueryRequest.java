package com.viptrip.hotelHtml5.vo.request.vo;

public class IndexPoiQueryRequest {
	/**
	 * poi城市查询
	 */
	public static final String POI_CITY_TYPE = "1";
	/**
	 * poi关键字查询
	 */
	public static final String POI_HOTELKEYWORD_TYPE  = "9";
	private String poiType; //多个用，分开 1代表城市  9关键字
	private String content; //查询内容
	private int    start;
	private int    rows;
	private String pid; 			//≠36916 不等于   36916等于
	
	private String cityId; 			//区域ID
	private String cityName;
	private String charCode;
	
	private String homeAbroadFlag;  //国内国外的标志(关键字查询)
	
	public String getPoiType() {
		return poiType;
	}
	public void setPoiType(String poiType) {
		this.poiType = poiType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCharCode() {
		return charCode;
	}
	public void setCharCode(String charCode) {
		this.charCode = charCode;
	}
	public String getHomeAbroadFlag() {
		return homeAbroadFlag;
	}
	public void setHomeAbroadFlag(String homeAbroadFlag) {
		this.homeAbroadFlag = homeAbroadFlag;
	}
}
