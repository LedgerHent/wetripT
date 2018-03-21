package com.viptrip.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viptrip.ibeserver.service.ReadIBEDataService;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.type.CabinType;
import com.viptrip.wetrip.service.impl.FlightService;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestIBEservice {
	@Resource
	private ReadIBEDataService readIBEDataService;
	
	@Test
	public void testFlightService(){
		PageParam pp = new PageParam(1,100);
		ReqData_GetFlightList para = new ReqData_GetFlightList();
		para.setTripType(1);
		List<ReqData_GetFlightList_TripInfo> tripInfo = new ArrayList<ReqData_GetFlightList_TripInfo>();
		ReqData_GetFlightList_TripInfo info = new ReqData_GetFlightList_TripInfo();
		info.setDepAirport("PEK");
		info.setArrAirport("SHA");
		info.setDate("20170504");
		tripInfo.add(info);
		para.setTripInfo(tripInfo);
		para.setCabinType(CabinType.BusinessClass);
		String keys=readIBEDataService.getLeftFlightKey(para);
		System.out.println("keys:"+keys);
		
	}
	
}
