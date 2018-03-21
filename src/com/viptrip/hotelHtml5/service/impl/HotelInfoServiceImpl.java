package com.viptrip.hotelHtml5.service.impl;


import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.viptrip.hotelHtml5.common.ConfigConstants;
import com.viptrip.hotelHtml5.service.HotelInfoService;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelList;
import com.viptrip.hotelHtml5.vo.request.Request_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductDetail;
import com.viptrip.hotelHtml5.vo.request.Request_GetProductList;
import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelList;
import com.viptrip.hotelHtml5.vo.response.Response_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetProductList;
import com.viptrip.hotelHtml5.vo.response.ro.MongoHotelFacilittyItem;
import com.viptrip.hotelHtml5.vo.response.ro.RatePlan;
import com.viptrip.hotelHtml5.vo.response.ro.RoomTypeResponse;

import jodd.util.ArraysUtil;
@Service
public class HotelInfoServiceImpl implements HotelInfoService{
	private static Logger logger = LoggerFactory.getLogger(HotelInfoServiceImpl.class);
	@Override
	public Response_GetKeyAssociateInfo getH5KeyAssociateInfo(IndexPoiQueryRequest indexPoiQueryRequest) {
		Request_GetKeyAssociateInfo request = new Request_GetKeyAssociateInfo();
		request.method = ConfigConstants.TMC_API_METHOD.GETKEYASSOCIATEINFO;
		request.setIndexPoiQueryRequest(indexPoiQueryRequest);
		Response_GetKeyAssociateInfo result = null;
		try {
			logger.info(JSONObject.toJSONString("getH5KeyAssociateInfo request:"+indexPoiQueryRequest));
			result = JhUtil.sendReqJh(request, Response_GetKeyAssociateInfo.class);
			if(result!=null && result.status==1){
				result.setCode(ConfigConstants.H5_SEARCH_RESULT_CODE.FAIL_CODE);
				result.setMsg(result.getMsg());
			}
			logger.info(JSONObject.toJSONString("getH5KeyAssociateInfo response:"+result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public Response_GetHotelList getH5HotelList(Request_GetHotelList request) {
		request.method=ConfigConstants.TMC_API_METHOD.GETHOTELLIST;
		Response_GetHotelList result = new Response_GetHotelList();
		try {
			logger.info(JSONObject.toJSONString("getH5HotelList request:"+request));
			result = JhUtil.sendReqJh(request, Response_GetHotelList.class);
			if(result!=null && result.status==1){
				logger.info("getH5HotelList result"+result.getMsg());
				result.setCode(ConfigConstants.H5_SEARCH_RESULT_CODE.FAIL_CODE);
				result.setMsg(ConfigConstants.H5_SEARCH_RESULT_MSG.FAIL_MSG);
			}
			logger.info(JSONObject.toJSONString("getH5HotelList response:"+result));
		}catch (SocketTimeoutException e){
			result.setCode(ConfigConstants.H5_SEARCH_RESULT_CODE.SOCKETTIMEOUT_CODE);
			result.setMsg(ConfigConstants.H5_SEARCH_RESULT_MSG.SOCKETTIMEOUT_MSG);
			e.printStackTrace();
		}catch(Exception e) {
			result.setCode(ConfigConstants.H5_SEARCH_RESULT_CODE.QUERY_EXCEPTION_CODE);
			result.setMsg(ConfigConstants.H5_SEARCH_RESULT_MSG.QUERY_EXCEPTION_MSG);
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Response_GetHotelDetail getH5HotelDetail(Request_GetHotelDetail request) {
		request.method=ConfigConstants.TMC_API_METHOD.GETHOTELDETAIL;
		Response_GetHotelDetail response_GetHotelDetail = new Response_GetHotelDetail();
		try {
			logger.info(JSONObject.toJSONString("getH5HotelDetail request:"+JSONObject.toJSONString(request)));
			response_GetHotelDetail = JhUtil.sendReqJh(request, Response_GetHotelDetail.class);
			logger.info(JSONObject.toJSONString("getH5HotelDetail response:"+JSONObject.toJSONString(response_GetHotelDetail)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response_GetHotelDetail;
	}

	@Override
	public Response_GetProductList getH5ProductList(Request_GetProductList request) {
		request.method = ConfigConstants.TMC_API_METHOD.GETPRODUCTLIST;
		Response_GetProductList result = new Response_GetProductList();
		try {
			logger.info(JSONObject.toJSONString("getH5ProductList request:"+request));
			result = JhUtil.sendReqJh(request, Response_GetProductList.class);
			logger.info(JSONObject.toJSONString("getH5ProductList response:"+result));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//筛选rp最低价
		if(result != null && result.getBusiData()!=null && result.getBusiData().size()>0){
			for(RoomTypeResponse roomTypeResponse:result.getBusiData()){
				if(roomTypeResponse!=null){
					Double lowestRoomPrice = Double.MAX_VALUE; 
					List<RatePlan> ratePlans = roomTypeResponse.getRatePlans();
					if(ratePlans!=null && ratePlans.size()>0){
						for(RatePlan ratePlan:ratePlans){
							if(ratePlan.getDayAvgRoomPriceCNY()<lowestRoomPrice){
								lowestRoomPrice = ratePlan.getDayAvgRoomPriceCNY();
							}
						}
					}
					roomTypeResponse.setLowestRoomPrice(lowestRoomPrice);
				}
			}
		}
		return result;
	}
	
	@Override
	public Response_GetProductDetail getH5ProductDetail(Request_GetProductDetail request) {
		request.method = ConfigConstants.TMC_API_METHOD.GETPRODUCTDETAIL;
		Response_GetProductDetail result = new Response_GetProductDetail();
		try {
			logger.info(JSONObject.toJSONString("getH5ProductDetail request:"+request));
			result = JhUtil.sendReqJh(request, Response_GetProductDetail.class);
			if(result!=null && result.status==1){
				result.setCode("-1");
				result.setMessage(result.getMessage());
			}
			logger.info(JSONObject.toJSONString("getH5ProductDetail response:"+result));
		}catch(SocketTimeoutException e){
			result.setCode("-1");
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}catch (Exception e) {
			result.setCode("-1");
			result.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<MongoHotelFacilittyItem> getMongoHotelFacilittyItem(List<MongoHotelFacilittyItem> facilittyItems){
		boolean FREE_WIFI=true;
		boolean CHARGE_WIFI=true;
		boolean PUBLIC_WASHROOM=true;
		boolean CENTRAL_AIR_CONDITIONING=true;
		boolean BELLPERSON=true;
		boolean VALET_PARKING=true;
		List<MongoHotelFacilittyItem> facilittyItems1 = new ArrayList<MongoHotelFacilittyItem>();
		if(facilittyItems!=null && facilittyItems.size()>0){
			for(MongoHotelFacilittyItem mongoHotelFacilittyItem:facilittyItems){
				if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.FREE_WIFI, mongoHotelFacilittyItem.getItemId())
					&& FREE_WIFI){
					facilittyItems1.add(mongoHotelFacilittyItem);
					FREE_WIFI=false;
				}else if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.CHARGE_WIFI, mongoHotelFacilittyItem.getItemId())
					&& CHARGE_WIFI){
					facilittyItems1.add(mongoHotelFacilittyItem);
					CHARGE_WIFI =false;
				}else if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.PUBLIC_WASHROOM, mongoHotelFacilittyItem.getItemId())
						&& PUBLIC_WASHROOM){
					facilittyItems1.add(mongoHotelFacilittyItem);
					PUBLIC_WASHROOM =false;
				}else if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.CENTRAL_AIR_CONDITIONING, mongoHotelFacilittyItem.getItemId())
						&& CENTRAL_AIR_CONDITIONING){
					facilittyItems1.add(mongoHotelFacilittyItem);
					CENTRAL_AIR_CONDITIONING =false;
				}else if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.BELLPERSON, mongoHotelFacilittyItem.getItemId())
						&& BELLPERSON){
					facilittyItems1.add(mongoHotelFacilittyItem);
					BELLPERSON =false;
				}else if(ArraysUtil.contains(ConfigConstants.TMCH5_FACILITTYITEMS.VALET_PARKING, mongoHotelFacilittyItem.getItemId())
						&& VALET_PARKING){
					facilittyItems1.add(mongoHotelFacilittyItem);
					VALET_PARKING =false;
				}
			}
		}
		return facilittyItems1;
	}
}
