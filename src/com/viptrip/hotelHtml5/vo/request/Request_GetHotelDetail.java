package com.viptrip.hotelHtml5.vo.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetHotelDetail extends Request_Base{

	/**
	 * 平台酒店ID
	 */
	private String ptHotelId;

	/**
	 * 酒店名称，中文、英文、拼音、拼音首字母
	 */
	private String ptHotelName;
	
	/**
	 * 城市地区ID
	 */
	private String cityId;
	
	/**
	 * 城市名称
	 */
	private String cityName;
	
	
	/**
	 * 入住日期
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date checkStartDate;
		
	/**
	 * 入住日期yyyyMMdd
	 */
	private String checkStartDateStr;
	
	/**
	 * 入住时长(晚)
	 */
	private Integer nightCount;
	
	/**
	 * 成人数量
	 */
	private Integer adultCount;
	
	/**
	 * 儿童年龄详情
	 */
	private String childAgeDl;
	
	/**
	 * 酒店地址
	 */
	private String hotelAddr;
	
	/**
	 * 价格区间上限
	 */
	private Double topPrice;
	
	/**
	 * 价格区间下限
	 */
	private Double lowPrice;
	
	/**
	 * 酒店星级
	 */
	private String starLvs;
	
	/**
	 * 酒店位置, 可能是景区、地表、商圈
	 */
	private String locationId;
	
	/**
	 * 酒店位置类型,3-景区  4-地标  5-商圈
	 */
	private String locationType;
	
	/**
	 * 酒店品牌
	 */
	private String brandIds;
	
	/**
	 * 酒店类型
	 */
	private String hotelType;
	
	/**
	 * 酒店设施
	 */
	private String facilittyItemids;
	
	/**
	 * 排序字段：1是价格；2是星级
	 */
	private String sort;
	
	/**
	 * 排序方式：0是升序；1是降序
	 */
	private String sortWay;
	
	private String ptHotelIds[];
	
	private String lowestPriceFlag;//0、不包括；1、包括；2、包括且没有匹配有的
	
	//某段时间的结束时间
	private Date timeIntervalEndDate;
	
	/**
	 * 父ID
	 */
	private String parentId;
	
	private String  lat;                 //酒店纬度
    private String  lon;                 //酒店经度    
    private String  maptype = "1";       //地图类型，目前不知道是什么类型经纬度，暂时给1
    private String  maparea;             //范围1代表1千米，以（lon,lat）为中心，1千米范围内的酒店
    private String  searchType; //1普通查询(用户选择城市) ,2定位查询, 3、手工单平台酒店查询
    private String  filterType="1"; //1普通查询(用户选择城市) ,2子过滤
    private String  areaId;  //区域id(城市下查。app用)
    /**
	 * 酒店标签
	 */
	private String tagItems;
	
	private Integer enterpriseId; //企业id
	private String enterpriseName;//企业名称
	private String tripType; //出行方式：因公出行0000700001,因私出行0000700002
	private String tripUserId;	//预订人或出行人
	private String homeAbroadFlag;	//国内与国际
	
	private String choiceAgreementHotelFlag;	//精选酒店或协议酒店：0002400001 精选酒店，0002400002 协议酒店

	private String keyAssociateValue;
	public String getHomeAbroadFlag() {
		return homeAbroadFlag;
	}

	public void setHomeAbroadFlag(String homeAbroadFlag) {
		this.homeAbroadFlag = homeAbroadFlag;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getPtHotelId() {
		return ptHotelId;
	}

	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Date getCheckStartDate() {
		return checkStartDate;
	}

	public void setCheckStartDate(Date checkStartDate) {
		this.checkStartDate = checkStartDate;
	}

	public String getHotelAddr() {
		return hotelAddr;
	}

	public void setHotelAddr(String hotelAddr) {
		this.hotelAddr = hotelAddr;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}


	public Integer getNightCount() {
		return nightCount;
	}

	public void setNightCount(Integer nightCount) {
		this.nightCount = nightCount;
	}

	public Integer getAdultCount() {
		return adultCount;
	}

	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}

	public Integer getChildCount() {
		return (this.getChildAgeDl()!= null) && (!childAgeDl.trim().isEmpty()) ? this.getChildAgeDl().split("\\|").length:0;
	}

	public String getChildAgeDl() {
		return childAgeDl;
	}

	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}

	public String getStarLvs() {
		return starLvs;
	}

	public void setStarLvs(String starLvs) {
		this.starLvs = starLvs;
	}

	public Double getTopPrice() {
		return topPrice;
	}

	public void setTopPrice(Double topPrice) {
		this.topPrice = topPrice;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCheckStartDateStr() {
		return checkStartDateStr;
	}

	public void setCheckStartDateStr(String checkStartDateStr) {
		this.checkStartDateStr = checkStartDateStr;
	}

	public String getBrandIds() {
		return brandIds;
	}

	public void setBrandIds(String brandIds) {
		this.brandIds = brandIds;
	}

	public String getFacilittyItemids() {
		return facilittyItemids;
	}

	public void setFacilittyItemids(String facilittyItemids) {
		this.facilittyItemids = facilittyItemids;
	}

	public String getPtHotelName() {
		return ptHotelName;
	}

	public void setPtHotelName(String ptHotelName) {
		this.ptHotelName = ptHotelName;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String[] getPtHotelIds() {
		return ptHotelIds;
	}

	public void setPtHotelIds(String[] ptHotelIds) {
		this.ptHotelIds = ptHotelIds;
	}

	public String getSortWay() {
		return sortWay;
	}

	public void setSortWay(String sortWay) {
		this.sortWay = sortWay;
	}	

	public String getLowestPriceFlag() {
		return lowestPriceFlag;
	}

	public void setLowestPriceFlag(String lowestPriceFlag) {
		this.lowestPriceFlag = lowestPriceFlag;
	}

	public Date getTimeIntervalEndDate() {
		return timeIntervalEndDate;
	}

	public void setTimeIntervalEndDate(Date timeIntervalEndDate) {
		this.timeIntervalEndDate = timeIntervalEndDate;
	}

	public String getHotelType() {
		return hotelType;
	}

	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getMaptype() {
		return maptype;
	}

	public void setMaptype(String maptype) {
		this.maptype = maptype;
	}

	public String getMaparea() {
		return maparea;
	}

	public void setMaparea(String maparea) {
		this.maparea = maparea;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getFilterType() {
		return filterType;
	}

	public void setFilterType(String filterType) {
		this.filterType = filterType;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getTagItems() {
		return tagItems;
	}

	public void setTagItems(String tagItems) {
		this.tagItems = tagItems;
	}
	
	public String getTripUserId() {
		return tripUserId;
	}

	public void setTripUserId(String tripUserId) {
		this.tripUserId = tripUserId;
	}

	public String getChoiceAgreementHotelFlag() {
		return choiceAgreementHotelFlag;
	}

	public void setChoiceAgreementHotelFlag(String choiceAgreementHotelFlag) {
		this.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	}

	public String getKeyAssociateValue() {
		return keyAssociateValue;
	}

	public void setKeyAssociateValue(String keyAssociateValue) {
		this.keyAssociateValue = keyAssociateValue;
	}
}
