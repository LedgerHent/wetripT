package com.viptrip.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.viptrip.hotel.model.Request_GetOrgFramework;
import com.viptrip.hotel.model.Request_ListPassenger;
import com.viptrip.hotel.model.Request_ListStaffLike;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.hotel.service.GetOrgFrameworkService;
import com.viptrip.hotel.service.IComService;
import com.viptrip.hotelHtml5.service.TmcHotelOrderService;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.vo.request.Request_GetCityList;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotCity;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotelList;
import com.viptrip.hotelHtml5.vo.request.Request_SaveTmcOrder;
import com.viptrip.hotelHtml5.vo.response.Response_GetCityList;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotCity;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotelList;
import com.viptrip.hotelHtml5.vo.tmc.OrderDetailRequestVo;
import com.viptrip.hotelHtml5.vo.tmc.ParameterResponseVO;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderContacts;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderGuest;
import com.viptrip.hotelHtml5.vo.tmc.TmcOrderHotel;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.request.Request_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetHotelBooking;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_GetTmcOrderDetail;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_ListTmcOrder;
import com.viptrip.hotelHtml5.vo.tmc.response.Response_SaveTmcOrder;
import com.viptrip.wetrip.model.employees.CompanyInfo;

import jodd.util.StringUtil;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestTmcHotel {
	
	@Autowired
	private TmcHotelOrderService tmcHotelOrderService;
	@Autowired
	private IComService iComService; 
	@Autowired
	private GetOrgFrameworkService getOrgFrameworkService; 
	
	@Test
	public void testH5CityList(){
		Request_GetCityList request = new Request_GetCityList();
		request.method = "GetCityList";
		request.setParentId("36916");
//		request.setParentId("-99");
		request.setLv("3");
		request.setPageNum("1");
		request.setPageSize("100");
		try {
			long s = System.currentTimeMillis();
			Response_GetCityList result = JhUtil.sendReqJh(request, Response_GetCityList.class);
			long l = System.currentTimeMillis();
			System.out.println(JSONObject.toJSONString(result));
			System.out.println("---" + (l - s));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testHotCityList(){
		
		Request_GetHotCity request = new Request_GetHotCity();
		try {
			long s = System.currentTimeMillis();
			request.method = "GetHotCity";
			Response_GetHotCity result = JhUtil.sendReqJh(request, Response_GetHotCity.class);
			long l = System.currentTimeMillis();
			System.out.println((l - s) / 1000 + "------" + JSONObject.toJSONString(result));
			
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
			System.out.println(JSONObject.toJSONString(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void test_gethotelBooking(){
		Request_GetHotelBooking request = new Request_GetHotelBooking();
		request.setPhk("HotelBooking10475504991510560514490#^-^#4");
		request.setChoiceAgreementHotelFlag("0002400001");
		request.setCheckStartDateStr("2017-11-17");
		request.setHomeAbroadFlag("36916");
		request.setTripType("0000700001");
		request.setPtHotelId("1179131");
		request.setTripUserId("299438");
		request.setAdultCount(2);
		request.setNightCount(1);
		request.setRoomCount(1);
		request.setIsTMC(true);
		
		Response_GetHotelBooking result = tmcHotelOrderService.getHotelBooking(request);
		System.out.println(JSONObject.toJSONString(result));
	}
	
	@Test
	public void test_orderDetail(){
		Request_GetTmcOrderDetail request = new Request_GetTmcOrderDetail();
		request.setOrderNo("HT171031001187");
		Response_GetTmcOrderDetail result = tmcHotelOrderService.getTmcOrderDetail(request);
		System.out.println(JSONObject.toJSONString(result));
	}

	@Test
	public void test_orderList(){
		Request_ListTmcOrder request = new Request_ListTmcOrder();
//		request.setOrderNo("HT171031001187");
		request.setPageNum(1);
		request.setPageSize(10);
		Response_ListTmcOrder result = this.tmcHotelOrderService.listTmcOrder(request);
		System.out.println(JSONObject.toJSONString(result));
	}
	
	@Test
	public void test_saveOrder(){
		Request_SaveTmcOrder request = new Request_SaveTmcOrder();
		OrderDetailRequestVo orderDetail = new OrderDetailRequestVo();
		orderDetail.setPhk("HotelBooking15606371331511160727367");
		orderDetail.setAdultCount(0);
		orderDetail.setBookingDeptId("5660");
		orderDetail.setBookingDeptName("测试企业");
		orderDetail.setBookingEnterpId("5660");
		orderDetail.setBookingEnterpName("测试企业");
		orderDetail.setBookingUserId("299438");
		orderDetail.setBookingUserName("钱晔");
		orderDetail.setChildCount(0);
		orderDetail.setConfirmBooking(false);
		orderDetail.setInvoiceDeptName("test");
		orderDetail.setOrderMemo("test");
		orderDetail.setOrderTotalAmount(460);
		orderDetail.setPageTempletFlag("1");
		orderDetail.setPayAmount(0);
		orderDetail.setPayType("0000900002");
		orderDetail.setTripType("0000700002");
		orderDetail.setPlicyRuleListLength(0);
		orderDetail.setIntegralDiscount(0.0);
		orderDetail.setInterestDiscount(0.0);
		TmcOrderHotel tmcOrderHotel = new TmcOrderHotel();
		tmcOrderHotel.setBookingCheckinDateStr("2017-11-30");
		tmcOrderHotel.setBookingCheckoutDateStr("2017-12-01");
		tmcOrderHotel.setBookingFee(440.0);
		tmcOrderHotel.setNightFee(440.0);
		tmcOrderHotel.setTaxFee(440.0);
		tmcOrderHotel.setRoomNum(1);
		orderDetail.setTmcOrderHotel(tmcOrderHotel);
		List<TmcOrderGuest> tmcOrderGuestList = new ArrayList<TmcOrderGuest>();
		TmcOrderGuest g1 = new TmcOrderGuest();
		g1.setEmail("13817141315@163.com");
		g1.setGuestId("299438");
		g1.setGuestName("钱晔");
		g1.setTel("13817141314");
		g1.setRoomNo("0");
		tmcOrderGuestList.add(g1);
		orderDetail.setTmcOrderGuestList(tmcOrderGuestList);
		List<TmcOrderContacts> tmcOrderContactsList = new ArrayList<TmcOrderContacts>();
		TmcOrderContacts c1 = new TmcOrderContacts();
		c1.setEmail("13817141315@163.com");
		c1.setTel("13817141314");
		c1.setContact("钱晔");
		c1.setSeq(0);
		tmcOrderContactsList.add(c1);
		orderDetail.setTmcOrderContactsList(tmcOrderContactsList);
		request.setOrderDetailRequestVo(orderDetail);
		System.out.println(JSONObject.toJSONString(request));
		Response_SaveTmcOrder result = tmcHotelOrderService.saveTmcOrder(request);
		System.out.println(JSONObject.toJSONString(result));
	}
	
	@Test
	public void test(){
		Page page = new Page();
		page.index = 1;
		page.size = 10;
		
/*		Request_ListPassenger request = new Request_ListPassenger();
		request.setPage(page);
		request.userId = 303175;
		Response_ListPassenger result = iComService.ListPassenger(request);
		System.out.println(result);*/
		
		StaffList result = iComService.BokeListStaff(299438, page, 1, "");
		System.out.println(result);
	}
	
	@Test
	public void test_org(){
		List<ParameterResponseVO> list = new ArrayList<ParameterResponseVO>();
		CompanyInfo topTacOrg = null;
		List<CompanyInfo> child = null;
		Request_GetOrgFramework req = new Request_GetOrgFramework();
		req.type = 0;	//查询类别，凯撒商旅员工允许使用所有类别，普通企业员工只允许用0，此时orgId字段无意义。0-用户所在企业，1-指定企业，2-所有企业
		req.userId = Integer.valueOf("294274");
		req.orgId = Integer.valueOf("5660");
		CompanyInfo companyInfo = getOrgFrameworkService.getAllOrg(req, topTacOrg, child);
		String temp = "";
		list = getOrgList(companyInfo, list, temp);
		System.out.println(JSONObject.toJSONString(list));
	}
	
	public static List<ParameterResponseVO> getOrgList(CompanyInfo companyInfo, List<ParameterResponseVO> list, String temp){
		if(companyInfo != null){
			ParameterResponseVO vo = new ParameterResponseVO();
			vo.setV(companyInfo.id.toString());
			vo.setN(companyInfo.name);
			if(StringUtil.isEmpty(temp)){
				vo.setAttribute(companyInfo.name);
			}else{
				vo.setAttribute(temp + "-" + companyInfo.name);
			}
			temp = vo.getAttribute();
			list.add(vo);
			List<CompanyInfo> childs = companyInfo.child;
			if(!CollectionUtils.isEmpty(childs)){
				for(CompanyInfo child : childs){
					getOrgList(child, list, temp);
				}
			}
		}
		return list;
	}
	
}
