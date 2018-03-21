package com.viptrip.hotelHtml5.vo.response.ro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HotelQueryResponse implements java.io.Serializable {

	
	private Double lowestPrice;				//酒店最低价
	private String businessCircleCnName;	//商圈名称
	private String  areaName;  				//区域名称	
	private String currencyCode;			//币种Code
	private Double currencylowestPrice;		//币种最低价
	private List<PtHotelPic> hotelPics=new ArrayList<PtHotelPic>();
	private String areaCnName;  			//区域名称
	private String areaEnName;  			//区域名称
	private String cityCnName;  			//区域名称
	private String cityEnName;  			//区域名称
	private String countryCnName;  			//区域名称
	private String countryEnName;  			//区域名称
	
	private String  hotelId;             	//最低价的酒店id
	private String  checkInTime;         	//入住日期
    private Map<String,Object>  hotelPriceLowest;     //酒店最低价格1[天][人数]：value例如：10102:200 连住一天两个人的最低价为200
    private String currency;            	//币种
    private String  maparea;    			//范围1代表1千米，以（lon,lat）为中心，1千米范围内的酒店
    private String distance;   				//经纬度查询的时候返回实际的距离是多少
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  id;                  //平台酒店id
	private String  cityId;              //城市id
	private String  deltaId;             //州id
	private String  countryId;           //国家id

	private String  ptHotelId;           //平台酒店id
//	private String  supplierId;          //该酒店支持的所有供应商id，用,分开
    private String  hotelChineseName;    //酒店中文名
    private String  hotelEnglishName;    //酒店英文名
    private String  pyName;              //酒店拼音
    private String  abName;              //酒店首字母
    private String  areaId;              //区域id
    private List<MongoHotelScenicSpot>     sightings=new ArrayList<MongoHotelScenicSpot>();          		//景点
    private List<MongoHotelBusinessCircle> businessCircles=new ArrayList<MongoHotelBusinessCircle>();    	//商圈

    private String  starLv;              //酒店星级
    private String  ghId;                //集团id
    private MongoHotelGH gh;             //集团
    private String  brandId;             //品牌id，多个用，分开
    private MongoHotelBrand brand;       //品牌
    private String  hotelType;           //酒店类型
    private String  phoneExchange;       //总机
    private String  telephone;           //电话
    private String  email;               //邮件地址
    private String  lat;                 //纬度
    private String  lon;                 //经度
//  private Date    debutDate;           //开业时间
//  private Date    decorationDate;      //装修时间
    private String  debutDate;           //开业时间
    private String  decorationDate;      //装修时间
    private Integer roomTotalNumber;     //房间总数
    private Short   floors;              //楼层数
    private String  particularAddress;   //地址
    private String  briefIntroduction;   //简介
    
    private String  pCheckInTime;         //政策的入住时间
    private String  pCheckOutTime;        //政策的离店时间
    private String  childAddBed;         //儿童加床数量
    private String  diet;                //膳食安排
    private String  withPet;             //是否带宠物
    private String  cancelPolicy;        //取消政策
    private String  deposit;             //押金/预付款
    private String  specialItems;        //特殊条款
    private String  cardType;            //银行卡类型
    private String  onlineFlag;          //上下线状态
    private double  recommendLv=0.0d;    //推荐级别

 
    private List<MongoHotelFacilittyItem> facilittyItems=new ArrayList<MongoHotelFacilittyItem>();
    private List<MongoHotelTrafficItem>   trafficItems=new ArrayList<MongoHotelTrafficItem>();
    private List<MongoHotelLandmark>      landmarks=new ArrayList<MongoHotelLandmark>();
    private RecommendHotelSearchCondition hotelSearchCondition;  //相应查询条件
    
    private long modifyTime;             //数据修改时间
    private String internationFlag;
    private String recommendReason; 	 //推荐理由
    private String tripPolicyRemindType; //差旅策略类型：0000100001-仅提醒|0000100002-禁止预订|0000100003-超标审批|0000100004-无
    private String tripPolicyTypeDesc; 	 //差旅策略描述
    
	public String getTripPolicyRemindType() {
		return tripPolicyRemindType;
	}
	public void setTripPolicyRemindType(String tripPolicyRemindType) {
		this.tripPolicyRemindType = tripPolicyRemindType;
	}
	public Double getLowestPrice() {
		return lowestPrice;
	}
	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}
	public String getBusinessCircleCnName() {
		return businessCircleCnName;
	}
	public void setBusinessCircleCnName(String businessCircleCnName) {
		this.businessCircleCnName = businessCircleCnName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public Double getCurrencylowestPrice() {
		return currencylowestPrice;
	}
	public void setCurrencylowestPrice(Double currencylowestPrice) {
		this.currencylowestPrice = currencylowestPrice;
	}
	public List<PtHotelPic> getHotelPics() {
		return hotelPics;
	}
	public void setHotelPics(List<PtHotelPic> hotelPics) {
		this.hotelPics = hotelPics;
	}
	public String getAreaCnName() {
		return areaCnName;
	}
	public void setAreaCnName(String areaCnName) {
		this.areaCnName = areaCnName;
	}
	public String getAreaEnName() {
		return areaEnName;
	}
	public void setAreaEnName(String areaEnName) {
		this.areaEnName = areaEnName;
	}
	public String getCityCnName() {
		return cityCnName;
	}
	public void setCityCnName(String cityCnName) {
		this.cityCnName = cityCnName;
	}
	public String getCityEnName() {
		return cityEnName;
	}
	public void setCityEnName(String cityEnName) {
		this.cityEnName = cityEnName;
	}
	public String getCountryCnName() {
		return countryCnName;
	}
	public void setCountryCnName(String countryCnName) {
		this.countryCnName = countryCnName;
	}
	public String getCountryEnName() {
		return countryEnName;
	}
	public void setCountryEnName(String countryEnName) {
		this.countryEnName = countryEnName;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getCheckInTime() {
		return checkInTime;
	}
	public void setCheckInTime(String checkInTime) {
		this.checkInTime = checkInTime;
	}
	public Map<String, Object> getHotelPriceLowest() {
		return hotelPriceLowest;
	}
	public void setHotelPriceLowest(Map<String, Object> hotelPriceLowest) {
		this.hotelPriceLowest = hotelPriceLowest;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMaparea() {
		return maparea;
	}
	public void setMaparea(String maparea) {
		this.maparea = maparea;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getDeltaId() {
		return deltaId;
	}
	public void setDeltaId(String deltaId) {
		this.deltaId = deltaId;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public String getPtHotelId() {
		return ptHotelId;
	}
	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}
	public String getHotelChineseName() {
		return hotelChineseName;
	}
	public void setHotelChineseName(String hotelChineseName) {
		this.hotelChineseName = hotelChineseName;
	}
	public String getHotelEnglishName() {
		return hotelEnglishName;
	}
	public void setHotelEnglishName(String hotelEnglishName) {
		this.hotelEnglishName = hotelEnglishName;
	}
	public String getPyName() {
		return pyName;
	}
	public void setPyName(String pyName) {
		this.pyName = pyName;
	}
	public String getAbName() {
		return abName;
	}
	public void setAbName(String abName) {
		this.abName = abName;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
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
	public String getStarLv() {
		return starLv;
	}
	public void setStarLv(String starLv) {
		this.starLv = starLv;
	}
	public String getGhId() {
		return ghId;
	}
	public void setGhId(String ghId) {
		this.ghId = ghId;
	}
	public MongoHotelGH getGh() {
		return gh;
	}
	public void setGh(MongoHotelGH gh) {
		this.gh = gh;
	}
	public String getBrandId() {
		return brandId;
	}
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	public MongoHotelBrand getBrand() {
		return brand;
	}
	public void setBrand(MongoHotelBrand brand) {
		this.brand = brand;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public String getPhoneExchange() {
		return phoneExchange;
	}
	public void setPhoneExchange(String phoneExchange) {
		this.phoneExchange = phoneExchange;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
//	public Date getDebutDate() {
//		return debutDate;
//	}
//	public void setDebutDate(Date debutDate) {
//		this.debutDate = debutDate;
//	}
//	public Date getDecorationDate() {
//		return decorationDate;
//	}
//	public void setDecorationDate(Date decorationDate) {
//		this.decorationDate = decorationDate;
//	}
	public Integer getRoomTotalNumber() {
		return roomTotalNumber;
	}
	public String getDebutDate() {
		return debutDate;
	}
	public void setDebutDate(String debutDate) {
		this.debutDate = debutDate;
	}
	public String getDecorationDate() {
		return decorationDate;
	}
	public void setDecorationDate(String decorationDate) {
		this.decorationDate = decorationDate;
	}
	public void setRoomTotalNumber(Integer roomTotalNumber) {
		this.roomTotalNumber = roomTotalNumber;
	}
	public Short getFloors() {
		return floors;
	}
	public void setFloors(Short floors) {
		this.floors = floors;
	}
	public String getParticularAddress() {
		return particularAddress;
	}
	public void setParticularAddress(String particularAddress) {
		this.particularAddress = particularAddress;
	}
	public String getBriefIntroduction() {
		return briefIntroduction;
	}
	public void setBriefIntroduction(String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}
	public String getpCheckInTime() {
		return pCheckInTime;
	}
	public void setpCheckInTime(String pCheckInTime) {
		this.pCheckInTime = pCheckInTime;
	}
	public String getpCheckOutTime() {
		return pCheckOutTime;
	}
	public void setpCheckOutTime(String pCheckOutTime) {
		this.pCheckOutTime = pCheckOutTime;
	}
	public String getChildAddBed() {
		return childAddBed;
	}
	public void setChildAddBed(String childAddBed) {
		this.childAddBed = childAddBed;
	}
	public String getDiet() {
		return diet;
	}
	public void setDiet(String diet) {
		this.diet = diet;
	}
	public String getWithPet() {
		return withPet;
	}
	public void setWithPet(String withPet) {
		this.withPet = withPet;
	}
	public String getCancelPolicy() {
		return cancelPolicy;
	}
	public void setCancelPolicy(String cancelPolicy) {
		this.cancelPolicy = cancelPolicy;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getSpecialItems() {
		return specialItems;
	}
	public void setSpecialItems(String specialItems) {
		this.specialItems = specialItems;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getOnlineFlag() {
		return onlineFlag;
	}
	public void setOnlineFlag(String onlineFlag) {
		this.onlineFlag = onlineFlag;
	}
	public double getRecommendLv() {
		return recommendLv;
	}
	public void setRecommendLv(double recommendLv) {
		this.recommendLv = recommendLv;
	}
	public List<MongoHotelFacilittyItem> getFacilittyItems() {
		return facilittyItems;
	}
	public void setFacilittyItems(List<MongoHotelFacilittyItem> facilittyItems) {
		this.facilittyItems = facilittyItems;
	}
	public List<MongoHotelTrafficItem> getTrafficItems() {
		return trafficItems;
	}
	public void setTrafficItems(List<MongoHotelTrafficItem> trafficItems) {
		this.trafficItems = trafficItems;
	}
	public List<MongoHotelLandmark> getLandmarks() {
		return landmarks;
	}
	public void setLandmarks(List<MongoHotelLandmark> landmarks) {
		this.landmarks = landmarks;
	}
	public long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getInternationFlag() {
		return internationFlag;
	}
	public void setInternationFlag(String internationFlag) {
		this.internationFlag = internationFlag;
	}
	public String getRecommendReason() {
		return recommendReason;
	}
	public void setRecommendReason(String recommendReason) {
		this.recommendReason = recommendReason;
	}
	public String getTripPolicyTypeDesc() {
		return tripPolicyTypeDesc;
	}
	public void setTripPolicyTypeDesc(String tripPolicyTypeDesc) {
		this.tripPolicyTypeDesc = tripPolicyTypeDesc;
	}
	public RecommendHotelSearchCondition getHotelSearchCondition() {
		return hotelSearchCondition;
	}
	public void setHotelSearchCondition(RecommendHotelSearchCondition hotelSearchCondition) {
		this.hotelSearchCondition = hotelSearchCondition;
	}    


}
