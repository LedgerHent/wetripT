package com.viptrip.hotelHtml5.vo.response;

import com.viptrip.hotelHtml5.vo.response.ro.HotelQueryResponse;

public class Response_GetHotelDetail {

	private HotelQueryResponse busiData;
	private String code;
	private String message;

	public HotelQueryResponse getBusiData() {
		return busiData;
	}

	public void setBusiData(HotelQueryResponse busiData) {
		this.busiData = busiData;
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
