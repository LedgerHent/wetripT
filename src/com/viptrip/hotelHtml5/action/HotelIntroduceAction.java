package com.viptrip.hotelHtml5.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.viptrip.base.action.BaseAction;
import com.viptrip.hotel.HotelServer;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelDetail;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelDetail;

@Controller
@RequestMapping("/hotel")
public class HotelIntroduceAction extends BaseAction{

	private static Logger logger = LoggerFactory.getLogger(HotelServer.class);
	private static final String introducepage = "hotelHtml5/hotel-introduce";
	
	/**
	 * 酒店介绍页面
	 */
	@RequestMapping("/introduce.act")
	public String toKeyPage(String ptHotelId,Integer adultCount,Integer nightCount,String checkStartDateStr){
		
		try {
			Request_GetHotelDetail request = new Request_GetHotelDetail();
			request.method = "GetHotelDetail";
			request.setPtHotelId(ptHotelId);
			Response_GetHotelDetail result = JhUtil.sendReqJh(request,
					Response_GetHotelDetail.class);
			String jsonS = JSONObject.toJSONString(result.getBusiData());
			addAttr("result", result.getBusiData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return introducepage;
	}
}
