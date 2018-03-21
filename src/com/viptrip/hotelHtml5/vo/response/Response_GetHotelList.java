package com.viptrip.hotelHtml5.vo.response;


import com.viptrip.hotelHtml5.common.ConfigConstants;
import com.viptrip.hotelHtml5.util.PageUtil;
import com.viptrip.hotelHtml5.vo.response.ro.HotelQueryResponse;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetHotelList extends Response_Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code = ConfigConstants.H5_SEARCH_RESULT_CODE.SUCCESS_CODE;
	private String msg = ConfigConstants.H5_SEARCH_RESULT_MSG.SUCCESS_MSG;
	PageUtil<HotelQueryResponse> choicePageInfo;		//精选酒店
	PageUtil<HotelQueryResponse> agreementPageInfo;		//协议酒店
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public PageUtil<HotelQueryResponse> getChoicePageInfo() {
		return choicePageInfo;
	}
	public void setChoicePageInfo(PageUtil<HotelQueryResponse> choicePageInfo) {
		this.choicePageInfo = choicePageInfo;
	}
	public PageUtil<HotelQueryResponse> getAgreementPageInfo() {
		return agreementPageInfo;
	}
	public void setAgreementPageInfo(PageUtil<HotelQueryResponse> agreementPageInfo) {
		this.agreementPageInfo = agreementPageInfo;
	}
	
}
