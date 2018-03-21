package com.viptrip.hotelHtml5.vo.response;

import java.util.ArrayList;

import com.viptrip.hotelHtml5.vo.response.ro.RoomTypeResponse;
import com.viptrip.hotelHtml5.vo.response.ro.TripPolicyMatchResponseVo;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetProductList extends Response_Base{
	private ArrayList<RoomTypeResponse> busiData=new ArrayList<RoomTypeResponse>();
	private TripPolicyMatchResponseVo tripPolicyMatchResponseVo;
	private String code;
	private String message;
	public ArrayList<RoomTypeResponse> getBusiData() {
		return busiData;
	}

	public void setBusiData(ArrayList<RoomTypeResponse> busiData) {
		this.busiData = busiData;
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
