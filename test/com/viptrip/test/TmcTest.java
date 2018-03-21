package com.viptrip.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetCityList;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotCity;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelList;
import com.viptrip.hotelHtml5.vo.request.Request_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;
import com.viptrip.hotelHtml5.vo.response.Response_GetCityList;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotCity;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelList;
import com.viptrip.hotelHtml5.vo.response.Response_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetHotelBooking;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TmcTest {
	@Test
	public void testHotelBooking(){
		Request_GetHotelBooking request = new Request_GetHotelBooking();
		request.method = "GetHotelBooking";
		request.setPhk("HotelBooking5304624481510211236364#^-^#4");
		
		request.setRoomCount(1);
		request.setIsTMC(true);
		request.setChoiceAgreementHotelFlag("0002400001");
		request.setHomeAbroadFlag("36916");
		request.setTripType("0000700001");
		request.setTripUserId("299438");
		request.setPtHotelId("1179131");
		request.setCheckStartDateStr("2017-11-17");
		request.setAdultCount(2);
		request.setNightCount(1);
		
		try {
			Response_GetHotelBooking result = JhUtil.sendReqJh(request, Response_GetHotelBooking.class);
			System.out.println(JSONObject.toJSONString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testH5CityList(){
		Request_GetCityList request = new Request_GetCityList();
		request.method = "GetCityList";
		request.setParentId("36916");
		request.setLv("3");
		request.setPageNum("1");
		request.setPageSize("100");
		try {
			Response_GetCityList result = JhUtil.sendReqJh(request, Response_GetCityList.class);
			System.out.println(JSONObject.toJSONString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	@Test
	public void testHotCityList(){
		Request_GetHotCity request = new Request_GetHotCity();
		try {
			request.method = "GetHotCity";
			Response_GetHotCity result = JhUtil.sendReqJh(request, Response_GetHotCity.class);
			System.out.println(JSONObject.toJSONString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testGetKeyAssociateInfo(){
		Request_GetKeyAssociateInfo request = new Request_GetKeyAssociateInfo();
		request.method = "GetKeyAssociateInfo";
		IndexPoiQueryRequest indexPoiQueryRequest = new IndexPoiQueryRequest();
		indexPoiQueryRequest.setCityId("75021");
		indexPoiQueryRequest.setCityName("beijing");
		indexPoiQueryRequest.setPoiType("9");
		indexPoiQueryRequest.setPid("36916");
		indexPoiQueryRequest.setContent("bei");
		indexPoiQueryRequest.setHomeAbroadFlag("36916");
		
		request.setIndexPoiQueryRequest(indexPoiQueryRequest);
		try {
			Response_GetKeyAssociateInfo result = JhUtil.sendReqJh(request, Response_GetKeyAssociateInfo.class);
			System.out.println(JSONObject.toJSONString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void getHotelList(){
		Request_GetHotelList request = new Request_GetHotelList();
		request.method="GetHotelList";
		request.setCityId("75021");
		request.setCheckStartDateStr("2017-11-30");
		request.setAdultCount(2);
		request.setNightCount(2);
		request.setTripUserId("294274");
		request.setEnterpriseId(5660);
		request.setHomeAbroadFlag("36916");
		request.setTripType("0000700001");
		request.setNightCount(1);
		request.setAdultCount(2);
		request.setSort("0");
		request.setSortWay("0");
		Response_GetHotelList result = new Response_GetHotelList();
		try {
			result = JhUtil.sendReqJh(request, Response_GetHotelList.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(JSONObject.toJSONString(result));
	
	}

}
