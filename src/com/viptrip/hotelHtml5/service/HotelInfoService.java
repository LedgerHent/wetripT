package com.viptrip.hotelHtml5.service;

import java.util.List;

import com.viptrip.hotelHtml5.vo.request.Request_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelList;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductList;
import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelList;
import com.viptrip.hotelHtml5.vo.response.Response_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductList;
import com.viptrip.hotelHtml5.vo.response.ro.MongoHotelFacilittyItem;

public interface HotelInfoService {
	/**
	 * 关键字联想
	 * @param indexPoiQueryRequest
	 * @return
	 */
	Response_GetKeyAssociateInfo getH5KeyAssociateInfo(IndexPoiQueryRequest indexPoiQueryRequest);
	
	/**
	 * 酒店列表
	 * @param request
	 * @return
	 */
	Response_GetHotelList getH5HotelList(Request_GetHotelList request);
	
	/**
	 * 酒店详情
	 * @param request
	 * @return
	 */
	Response_GetHotelDetail getH5HotelDetail(Request_GetHotelDetail request);
	
	/**
	 * 房型列表
	 * @param request
	 * @return
	 */
	Response_GetProductList getH5ProductList(Request_GetProductList request);
	
	/**
	 * 房型详情
	 * @param productQueryRequest
	 * @return
	 */
	Response_GetProductDetail getH5ProductDetail(Request_GetProductDetail request);
	
	/**
	 * 酒店详情例举酒店设施
	 * @param facilittyItems
	 * @return
	 */
	public List<MongoHotelFacilittyItem> getMongoHotelFacilittyItem(List<MongoHotelFacilittyItem> facilittyItems);
}
