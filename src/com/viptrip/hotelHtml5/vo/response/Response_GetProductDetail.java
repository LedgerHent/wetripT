package com.viptrip.hotelHtml5.vo.response;

import java.util.List;

import com.viptrip.hotelHtml5.vo.response.ro.RatePlan;
import com.viptrip.hotelHtml5.vo.response.ro.TripPolicyMatchResponseVo;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetProductDetail extends Response_Base{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8121555907316077390L;
	private String roomTypeId;        
	private String roomTypeEnName;    
	private String roomTypeCnName;    
	private String roomArea;          
	private Integer roomCount;         
	private String roomFacilities;    
	private Integer roomToSell;        
	private String ptRoomTypeId;        
	private String bedSize;           
	private String bedType;           
	private String remark;            
	private String floor;             
	private String enName;            
	private String isSmoking;
	private String roomStatus;
	private List<RatePlan> ratePlans;
	private String  roomTotal="0";       //总房量
	
	/**
	 * 差旅政策信息
	 */
	private TripPolicyMatchResponseVo tripPolicyMatchResponseVo;

	private String code;
	private String message;
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomTypeEnName() {
		return roomTypeEnName;
	}
	public void setRoomTypeEnName(String roomTypeEnName) {
		this.roomTypeEnName = roomTypeEnName;
	}
	public String getRoomTypeCnName() {
		return roomTypeCnName;
	}
	public void setRoomTypeCnName(String roomTypeCnName) {
		this.roomTypeCnName = roomTypeCnName;
	}
	public String getRoomArea() {
		return roomArea;
	}
	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	public String getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(String roomFacilities) {
		this.roomFacilities = roomFacilities;
	}
	public Integer getRoomToSell() {
		return roomToSell;
	}
	public void setRoomToSell(Integer roomToSell) {
		this.roomToSell = roomToSell;
	}
	public String getPtRoomTypeId() {
		return ptRoomTypeId;
	}
	public void setPtRoomTypeId(String ptRoomTypeId) {
		this.ptRoomTypeId = ptRoomTypeId;
	}
	public String getBedSize() {
		return bedSize;
	}
	public void setBedSize(String bedSize) {
		this.bedSize = bedSize;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getEnName() {
		return enName;
	}
	public void setEnName(String enName) {
		this.enName = enName;
	}
	public String getIsSmoking() {
		return isSmoking;
	}
	public void setIsSmoking(String isSmoking) {
		this.isSmoking = isSmoking;
	}
	
	public String getRoomStatus() {
		return roomStatus;
	}
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public List<RatePlan> getRatePlans() {
		return ratePlans;
	}
	public void setRatePlans(List<RatePlan> ratePlans) {
		this.ratePlans = ratePlans;
	}
	public String getRoomTotal() {
		return roomTotal;
	}
	public void setRoomTotal(String roomTotal) {
		this.roomTotal = roomTotal;
	}
	public TripPolicyMatchResponseVo getTripPolicyMatchResponseVo() {
		return tripPolicyMatchResponseVo;
	}

	public void setTripPolicyMatchResponseVo(TripPolicyMatchResponseVo tripPolicyMatchResponseVo) {
		this.tripPolicyMatchResponseVo = tripPolicyMatchResponseVo;
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
