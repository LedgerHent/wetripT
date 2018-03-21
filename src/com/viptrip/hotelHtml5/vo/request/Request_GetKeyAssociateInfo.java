package com.viptrip.hotelHtml5.vo.request;


import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;

import etuf.v1_0.model.v2.base.Request_Base;

public class Request_GetKeyAssociateInfo extends Request_Base{

	private IndexPoiQueryRequest indexPoiQueryRequest;

	public IndexPoiQueryRequest getIndexPoiQueryRequest() {
		return indexPoiQueryRequest;
	}

	public void setIndexPoiQueryRequest(IndexPoiQueryRequest indexPoiQueryRequest) {
		this.indexPoiQueryRequest = indexPoiQueryRequest;
	}

}
