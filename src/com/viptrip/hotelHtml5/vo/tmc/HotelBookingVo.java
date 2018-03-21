package com.viptrip.hotelHtml5.vo.tmc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HotelBookingVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8635830554316091213L;

	/**
	 * 预订人ID
	 */
	private String bookingUserId;
	/**
	 * 预订人姓名
	 */
	private String bookingUserName;
	/**
	 * 预订人部门ID
	 */
	private String bookingDeptId;
	/**
	 * 预订人部门名称
	 */
	private String bookingDeptName;
	/**
	 * 预订人企业ID
	 */
	private String bookingEnterpId;
	/**
	 * 预订人企业名称
	 */
	private String bookingEnterpName;
	/**
	 * 代预订人ID
	 */
	private String agentBookingId;
	/**
	 * 代预订人姓名
	 */
	private String agentBookingName;
	
	
	/**
	 * 出行类型
	 */
	private String tripType;
	/**
	 * 酒店下单唯一标识
	 */
	private String phk;
	
	/*房型信息*/
	/**
	 * 房型ID
	 */
	private String roomtypeId;
	/**
	 * 房型名称
	 */
	private String roomTypeName;  
	/**
	 * 床型
	 */
	private String bedType;         
	/**
	 * RpId
	 */
	private String rateplanId;
	/**
	 * rpName
	 */
	private String rateplanName;
	/**
	 * 产品ID
	 */
	private String productId;
	/**
	 * 政策	 0为不能免费取消  保存日期（yyyy-MM-dd）时，为最后取消时间  需要去判断是否可免费取消
	 */
	private String hotelPolicy;
	/**
	 * 政策描述	
	 */
	private String hotelPolicyDesc;
	private String  freeCancel;			 //<!-- 0：收费取消；1:限时免费取消；2：不可取消 -->
	private String  freeCancelEndDate;	 //最后免费取消日期（yyyy-MM-dd）为1的时候用的
	/**
	 * 是否早餐	0含1不含
	 */
	private String isBreakfast;
	/**
	 * 早餐份数
	 */
	private Integer breakfastNumber = 0;
	/**
	 * 宽带信息
	 */
	private String hotelNetwork;

	/*酒店信息*/
	/**
	 * 酒店ID
	 */
	private String hotelId;
	/**
	 * 酒店名称
	 */
	private String hotelName;
	/**
	 * 酒店地址
	 */
	private String hotelAddress;
	/**
	 * 酒店电话
	 */
	private String hotelTelephone;
	/**
	 * 酒店星级
	 */
	private String hotelStar;
	/**
	 * 品牌
	 */
	private String hotelBrand;
	/**
	 * 供应商CODE
	 */
	private String supplierId = "caissa";
	/**
	 * 供应商NAME
	 */
	private String supplierName = "北京凯撒国际旅行社有限责任公司";
	/**
	 * 入住城市ID
	 */
	private String checkinCityId;
	/**
	 * 行政区
	 */
	private String region;
	/**
	 * 酒店种类：0001000001 国内，0001000002  国际 
	 */
	private String hotelType;
	/**
	 * 酒店图片
	 */
	private List<PtHotelPic> hotelPics = new ArrayList<PtHotelPic>();

	
	/**
	 * 订单总额
	 */
	private Double orderTotalAmount;
	/**
	 * 酒店接口返回的房费（包含税）
	 */
	private Double bookingTotalPrice;
	/**
	 * 房间费(不含税)    （bookingTotalPrice - taxFee） = roomFee
	 */
	private Double roomFee;
	/**
	 * 税费
	 */
	private double taxFee;
	/**
	 * 预订费
	 */
	private Double bookingFee;
	/**
	 * 夜间费
	 */
	private Double nightFee;
	/**
	 * 入住日期
	 */
	private Date checkInDate;
	/**
	 * 入住日期
	 */
	private Date checkOutDate;

	/**
	 * 房间数量
	 */
	private Integer roomCount;
	/**
	 * 最少预订房间数量
	 */
	private Integer minRoom;  
	/**
	 * 可售房量
	 */
	private Integer roomToSell;  
	/**
	 * 入住时长
	 */
	private Integer nightCount;

	/**
	 * 成人数量
	 */
	private Integer adultCount;
	
	/**
	 * 儿童年龄列表(从低到高)
	 */
	private String childAgeDl;	
	/**
	 * 最大入住人数
	 */
	private Integer occupancyMax = 1;
	/**
	 * 页面模板
	 */
	private String pageTempletFlag;
	/**
	 * 产品参考价格
	 */
	private List<ProductPrice> productPriceDays;
	
	/**
	 * 景鸿支付方式(因私)
	 */
	private List<ParameterResponseVO> privatePayMethodList;
	/**
	 * 景鸿支付方式(因公)
	 */
	private List<ParameterResponseVO> commonPayMethodList;
	
	/**
	 * 企业架构信息
	 */
	private OrgFrameworkResponse orgFrameworkResponse;
	
	/**
	 * 因公可预订权限
	 */
	private String commonBookRightType;
	/**
	 * 因私可预订权限
	 */
	private String privateBookRightType;
	
	/**
	 * 企业项目号状态	项目号状态，0-不显示，1-选填，2-必填
	 */
	private String enterpProjectNo;
	/**
	 * 企业审核状态		审核状态，0-不需要，1-需要
	 */
	private String enterpverify;
	
	/**
	 * 城市Id
	 */
	private String cityId;
	/**
	 * 城市Id
	 */
	private String cityCnName;
	/**
	 * 州id
	 */
	private String  deltaId;             
	
	/**
	 * 儿童年龄列表
	 */
	private List<String> childAgeDlList;
	/**
	 * 平台酒店ID
	 */
	private String ptHotelId;
	
	private String channelId;
	
	/**
	 *景鸿企业信息接口的业务城市 		业务城市，中文，如北京
	 */
	private String localCity;
	
	private StaffGroup staffGroup;

	/**
	 * 差旅政策 
	 */
	private TripPolicyRuleVo tripPolicyRuleVo;
	
	/**
	 * 是否确认预订
	 */
	private boolean confirmBooking = false;
	
	/**
	 * 是否TMC客服
	 */
	private boolean isTMC = false;
	
	/**
	 * 发生异常时不为空
	 */
	private String errMsg;
	
	/**
	 * 精选酒店或协议酒店：0002400001 精选酒店，0002400002 协议酒店
	 */
	private String choiceAgreementHotelFlag;
	
	private Double bookingTotalCost;
	

	/**
	 * 用户积分
	 */
	private Double userIntegral;
	/**
	 * 用户利息
	 */
	private Double userInterest;
	

	/**
	 * 入住日期
	 */
	private String checkInDateStr;
	/**
	 * 入住日期
	 */
	private String checkOutDateStr;
	/**
	 * 是否常规/超规审批     （超规审批：超标且设置超规审批   常规：其他）
	 */
	private Boolean isSuperRule;
	
	public String getTripType() {
		return tripType;
	}
	public void setTripType(String tripType) {
		this.tripType = tripType;
	}
	public String getPhk() {
		return phk;
	}
	public void setPhk(String phk) {
		this.phk = phk;
	}
	public String getRoomtypeId() {
		return roomtypeId;
	}
	public void setRoomtypeId(String roomtypeId) {
		this.roomtypeId = roomtypeId;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getRateplanId() {
		return rateplanId;
	}
	public void setRateplanId(String rateplanId) {
		this.rateplanId = rateplanId;
	}
	public String getRateplanName() {
		return rateplanName;
	}
	public void setRateplanName(String rateplanName) {
		this.rateplanName = rateplanName;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getHotelPolicy() {
		return hotelPolicy;
	}
	public void setHotelPolicy(String hotelPolicy) {
		this.hotelPolicy = hotelPolicy;
	}
	public String getHotelPolicyDesc() {
		return hotelPolicyDesc;
	}
	public void setHotelPolicyDesc(String hotelPolicyDesc) {
		this.hotelPolicyDesc = hotelPolicyDesc;
	}
	public String getIsBreakfast() {
		return isBreakfast;
	}
	public void setIsBreakfast(String isBreakfast) {
		this.isBreakfast = isBreakfast;
	}
	public String getHotelNetwork() {
		return hotelNetwork;
	}
	public void setHotelNetwork(String hotelNetwork) {
		this.hotelNetwork = hotelNetwork;
	}
	public String getHotelId() {
		return hotelId;
	}
	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelAddress() {
		return hotelAddress;
	}
	public void setHotelAddress(String hotelAddress) {
		this.hotelAddress = hotelAddress;
	}
	public String getHotelStar() {
		return hotelStar;
	}
	public void setHotelStar(String hotelStar) {
		this.hotelStar = hotelStar;
	}
	public String getHotelBrand() {
		return hotelBrand;
	}
	public void setHotelBrand(String hotelBrand) {
		this.hotelBrand = hotelBrand;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCheckinCityId() {
		return checkinCityId;
	}
	public void setCheckinCityId(String checkinCityId) {
		this.checkinCityId = checkinCityId;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getHotelType() {
		return hotelType;
	}
	public void setHotelType(String hotelType) {
		this.hotelType = hotelType;
	}
	public List<PtHotelPic> getHotelPics() {
		return hotelPics;
	}
	public void setHotelPics(List<PtHotelPic> hotelPics) {
		this.hotelPics = hotelPics;
	}
	public Double getOrderTotalAmount() {
		return orderTotalAmount;
	}
	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	public Double getBookingTotalPrice() {
		return bookingTotalPrice;
	}
	public void setBookingTotalPrice(Double bookingTotalPrice) {
		this.bookingTotalPrice = bookingTotalPrice;
	}
	public Double getBookingFee() {
		return bookingFee;
	}
	public void setBookingFee(Double bookingFee) {
		this.bookingFee = bookingFee;
	}
	public Double getNightFee() {
		return nightFee;
	}
	public void setNightFee(Double nightFee) {
		this.nightFee = nightFee;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
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
	public String getChildAgeDl() {
		return childAgeDl;
	}
	public void setChildAgeDl(String childAgeDl) {
		this.childAgeDl = childAgeDl;
	}
	public Integer getOccupancyMax() {
		return occupancyMax;
	}
	public void setOccupancyMax(Integer occupancyMax) {
		this.occupancyMax = occupancyMax;
	}
	public List<ProductPrice> getProductPriceDays() {
		return productPriceDays;
	}
	public void setProductPriceDays(List<ProductPrice> productPriceDays) {
		this.productPriceDays = productPriceDays;
	}
	public String getBookingUserId() {
		return bookingUserId;
	}
	public void setBookingUserId(String bookingUserId) {
		this.bookingUserId = bookingUserId;
	}
	public String getBookingUserName() {
		return bookingUserName;
	}
	public void setBookingUserName(String bookingUserName) {
		this.bookingUserName = bookingUserName;
	}
	public String getBookingDeptId() {
		return bookingDeptId;
	}
	public void setBookingDeptId(String bookingDeptId) {
		this.bookingDeptId = bookingDeptId;
	}
	public String getBookingDeptName() {
		return bookingDeptName;
	}
	public void setBookingDeptName(String bookingDeptName) {
		this.bookingDeptName = bookingDeptName;
	}
	public String getBookingEnterpId() {
		return bookingEnterpId;
	}
	public void setBookingEnterpId(String bookingEnterpId) {
		this.bookingEnterpId = bookingEnterpId;
	}
	public String getBookingEnterpName() {
		return bookingEnterpName;
	}
	public void setBookingEnterpName(String bookingEnterpName) {
		this.bookingEnterpName = bookingEnterpName;
	}
	public String getAgentBookingId() {
		return agentBookingId;
	}
	public void setAgentBookingId(String agentBookingId) {
		this.agentBookingId = agentBookingId;
	}
	public String getAgentBookingName() {
		return agentBookingName;
	}
	public void setAgentBookingName(String agentBookingName) {
		this.agentBookingName = agentBookingName;
	}
	public List<ParameterResponseVO> getPrivatePayMethodList() {
		return privatePayMethodList;
	}
	public void setPrivatePayMethodList(List<ParameterResponseVO> privatePayMethodList) {
		this.privatePayMethodList = privatePayMethodList;
	}
	public List<ParameterResponseVO> getCommonPayMethodList() {
		return commonPayMethodList;
	}
	public void setCommonPayMethodList(List<ParameterResponseVO> commonPayMethodList) {
		this.commonPayMethodList = commonPayMethodList;
	}
	public OrgFrameworkResponse getOrgFrameworkResponse() {
		return orgFrameworkResponse;
	}
	public void setOrgFrameworkResponse(OrgFrameworkResponse orgFrameworkResponse) {
		this.orgFrameworkResponse = orgFrameworkResponse;
	}
	public String getEnterpProjectNo() {
		return enterpProjectNo;
	}
	public void setEnterpProjectNo(String enterpProjectNo) {
		this.enterpProjectNo = enterpProjectNo;
	}
	public String getEnterpverify() {
		return enterpverify;
	}
	public void setEnterpverify(String enterpverify) {
		this.enterpverify = enterpverify;
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
	public List<String> getChildAgeDlList() {
		return childAgeDlList;
	}
	public void setChildAgeDlList(List<String> childAgeDlList) {
		this.childAgeDlList = childAgeDlList;
	}
	public String getPtHotelId() {
		return ptHotelId;
	}
	public void setPtHotelId(String ptHotelId) {
		this.ptHotelId = ptHotelId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getLocalCity() {
		return localCity;
	}
	public void setLocalCity(String localCity) {
		this.localCity = localCity;
	}
	public Integer getMinRoom() {
		return minRoom;
	}
	public void setMinRoom(Integer minRoom) {
		this.minRoom = minRoom;
	}
	public Integer getRoomToSell() {
		return roomToSell;
	}
	public void setRoomToSell(Integer roomToSell) {
		this.roomToSell = roomToSell;
	}
	public String getPageTempletFlag() {
		return pageTempletFlag;
	}
	public void setPageTempletFlag(String pageTempletFlag) {
		this.pageTempletFlag = pageTempletFlag;
	}
	public Integer getBreakfastNumber() {
		return breakfastNumber;
	}
	public void setBreakfastNumber(Integer breakfastNumber) {
		this.breakfastNumber = breakfastNumber;
	}
	public String getFreeCancel() {
		return freeCancel;
	}
	public void setFreeCancel(String freeCancel) {
		this.freeCancel = freeCancel;
	}
	public String getFreeCancelEndDate() {
		return freeCancelEndDate;
	}
	public void setFreeCancelEndDate(String freeCancelEndDate) {
		this.freeCancelEndDate = freeCancelEndDate;
	}
	public String getCityCnName() {
		return cityCnName;
	}
	public void setCityCnName(String cityCnName) {
		this.cityCnName = cityCnName;
	}
	public StaffGroup getStaffGroup() {
		return staffGroup;
	}
	public void setStaffGroup(StaffGroup staffGroup) {
		this.staffGroup = staffGroup;
	}
	public TripPolicyRuleVo getTripPolicyRuleVo() {
		return tripPolicyRuleVo;
	}
	public void setTripPolicyRuleVo(TripPolicyRuleVo tripPolicyRuleVo) {
		this.tripPolicyRuleVo = tripPolicyRuleVo;
	}
	public boolean isConfirmBooking() {
		return confirmBooking;
	}
	public void setConfirmBooking(boolean confirmBooking) {
		this.confirmBooking = confirmBooking;
	}
	public String getHotelTelephone() {
		return hotelTelephone;
	}
	public void setHotelTelephone(String hotelTelephone) {
		this.hotelTelephone = hotelTelephone;
	}
	public boolean isTMC() {
		return isTMC;
	}
	public void setTMC(boolean isTMC) {
		this.isTMC = isTMC;
	}
	public double getTaxFee() {
		return taxFee;
	}
	public void setTaxFee(double taxFee) {
		this.taxFee = taxFee;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public String getCommonBookRightType() {
		return commonBookRightType;
	}
	public void setCommonBookRightType(String commonBookRightType) {
		this.commonBookRightType = commonBookRightType;
	}
	public String getPrivateBookRightType() {
		return privateBookRightType;
	}
	public void setPrivateBookRightType(String privateBookRightType) {
		this.privateBookRightType = privateBookRightType;
	}
	public Double getRoomFee() {
		return roomFee;
	}
	public void setRoomFee(Double roomFee) {
		this.roomFee = roomFee;
	}
	public String getChoiceAgreementHotelFlag() {
		return choiceAgreementHotelFlag;
	}
	public void setChoiceAgreementHotelFlag(String choiceAgreementHotelFlag) {
		this.choiceAgreementHotelFlag = choiceAgreementHotelFlag;
	}
	public Double getBookingTotalCost() {
		return bookingTotalCost;
	}
	public void setBookingTotalCost(Double bookingTotalCost) {
		this.bookingTotalCost = bookingTotalCost;
	}
	public Double getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(Double userIntegral) {
		this.userIntegral = userIntegral;
	}
	public Double getUserInterest() {
		return userInterest;
	}
	public void setUserInterest(Double userInterest) {
		this.userInterest = userInterest;
	}
	public String getCheckInDateStr() {
		return checkInDateStr;
	}
	public void setCheckInDateStr(String checkInDateStr) {
		this.checkInDateStr = checkInDateStr;
	}
	public String getCheckOutDateStr() {
		return checkOutDateStr;
	}
	public void setCheckOutDateStr(String checkOutDateStr) {
		this.checkOutDateStr = checkOutDateStr;
	}
	public Boolean getIsSuperRule() {
		return isSuperRule;
	}
	public void setIsSuperRule(Boolean isSuperRule) {
		this.isSuperRule = isSuperRule;
	}
	
}
