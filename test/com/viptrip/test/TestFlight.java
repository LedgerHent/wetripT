package com.viptrip.test;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

import org.apache.xml.resolver.apps.resolver;
import org.junit.Test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.model.Request_GetFlightDetail;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.RespData_GetFlightDetail;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.model.flight.type.CabinType;
import com.viptrip.wetrip.model.flight.type.TripType;
import com.viptrip.wetrip.service.impl.FlightService;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestFlight {
	@Resource
	private FlightService flightService;
	@Resource
	private ComDao comDao;
	
	@Test
	public void testFlightService(){
		/*ReqData_GetFlightList para = new ReqData_GetFlightList();
		para.setTripType(1);
		List<ReqData_GetFlightList_TripInfo> tripInfo = new ArrayList<ReqData_GetFlightList_TripInfo>();
		ReqData_GetFlightList_TripInfo info = new ReqData_GetFlightList_TripInfo();
		info.setDepAirport("PEK");
		info.setArrAirport("SHA");
		info.setDate("20170505");
		info.setFlowId(1);
		tripInfo.add(info);
		para.setTripInfo(tripInfo);
		para.setCabinType(CabinType.Unlimited);
		
		Request_GetFlightList data = new Request_GetFlightList();
		data.setUserId(245621l);
		data.setData(para);
		List<RespData_GetFlightList> list = flightService.getFlightList(data);
		System.out.println("Test.testFlightService()==flightList==" + new Gson().toJson(list));
		if(null!=list && list.size()>0){
//			String mapkey = list.get(0).getMapKey();
//			System.out.println("TestFlight.testFlightService()==mapkey==" + mapkey);
//			Request_GetFlightDetail rpara = new Request_GetFlightDetail(mapkey);
//			RespData_GetFlightDetail listDetail = flightService.getFlightDetail(rpara);
//			System.out.println("TestFlight.testFlightService()==listDetail==" + new Gson().toJson(listDetail));
		}*/
		
		/*System.out.println("list=" + new Gson().toJson(list));
		
		RedisCacheManager.save("list", list);
		
		TypeToken<List<RespData_GetFlightList>> typeToken = new TypeToken<List<RespData_GetFlightList>>(){};
		
		Object obj = RedisCacheManager.get("list",typeToken.getClass());
		
		System.out.println("obj=" + new Gson().toJson(obj));
		
		RedisCacheManager.del("list");*/
		
		/*List<Object> list = comDao.queryForList("select classtype as classtype,aircompany as aircompany,classcode as classcode from TAcClass group by classtype,aircompany,classcode");
		System.out.println("TestFlight.testFlightService()==list==" + new Gson().toJson(list));*/
	}
}
