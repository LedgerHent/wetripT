package com.viptrip.hotelHtml5.vo.response;

import java.util.ArrayList;

import com.viptrip.hotelHtml5.common.ConfigConstants;
import com.viptrip.hotelHtml5.vo.response.ro.AssociateInfoResponse;

import etuf.v1_0.model.v2.base.Response_Base;

public class Response_GetKeyAssociateInfo extends Response_Base{
	
	private static final long serialVersionUID = -1294469013935071683L;
	private ArrayList<AssociateInfoResponse> busiData=new ArrayList<AssociateInfoResponse>();
	private String code;
	private String message;
	private String msg;

	public ArrayList<AssociateInfoResponse> getBusiData() {
		return busiData;
	}

	public void setBusiData(ArrayList<AssociateInfoResponse> busiData) {
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
