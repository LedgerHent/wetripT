package com.viptrip.test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import net.sf.json.JSONObject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.JSON;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_Test;
import com.viptrip.wetrip.model.Response_Test;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_TripInfo;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.service.impl.FlightService;
import com.viptrip.wetrip.service.impl.TestService;
import com.viptrip.wetrip.util.CaiyunUtil;
import com.viptrip.wetrip.util.CommUtil;
import com.viptrip.wetrip.vo.CaiyunAccessToken;
import com.viptrip.wetrip.wsclient.DispplayTrip;
import com.viptrip.wetrip.wsclient.ReadIBEDataImplDelegate;
import com.viptrip.wetrip.wsclient.ReadIBEDataImplService;

import etuf.v1_0.common.EncryptHelper;
import etuf.v1_0.model.base.output.OutputResult;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class Test {
	@Resource
	private TestService testService;
	
	
	@org.junit.Test
	public void test(){
		/*PageObject<TAcUser> list = testService.list();
		System.out.println("Test.test()==list==" + new Gson().toJson(list));
		
		Long id = 245621L;
		TAcUser user = testService.get(id);
		System.out.println("Test.test()==user==" + user);*/
		
		//flightClient
		
		String arg0 = "PEK"; //北京
		String arg1 = "SHA"; //上海
		String arg2 = "20170508";//日期
		String arg3 = "ALL";//
		String arg4 = "";//
		
		/*ReadIBEDataImplService service = ApplicationContextHelper.getInstance().getBean(ReadIBEDataImplService.class);
		ReadIBEDataImplDelegate delegate = service.getReadIBEDataImplPort();
		List<DispplayTrip> data = delegate.getIBEData(arg0, arg1, arg2, arg3, arg4);*/
		
		
		
		/*ReadIBEDataImplDelegate delegate = ApplicationContextHelper.getInstance().getBean(ReadIBEDataImplDelegate.class);
		List<DispplayTrip> data = delegate.getIBEData(arg0, arg1, arg2, arg3, arg4);
		
		
		System.out.println("Test.test()==data==" + new Gson().toJson(data));*/
		/*
		String key = "testKey";
		RedisCacheManager.save(key, 123456L);
		RedisCacheManager.get(key,Long.class);*/
		/*String md5Encrypt = EncryptHelper.MD5Encrypt("123456");
		System.out.println("Test.test()==md5Encrypt==" +md5Encrypt);*/
		
		//e10adc3949ba59abbe56e057f20f883e
		//E10ADC3949BA59ABBE56E057F20F883E
		
		String token = CommUtil.getAccessToken().getAccessToken();
		System.out.println("Test.test()===token===" + token);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		/*int max = 255;
		int i1 = 1;
		int i2 = 2;
		int i4 = 4;
		int i8 = 8;
		
		int j = 7;
		
		System.out.println(i1==(i1&j));
		//System.out.println(i2&j);
		//System.out.println(i4&j);
		String decode = URLDecoder.decode("http%3A%2F%2F172.16.249.23%3A8080%2Fwetrip%2FinitAcrdtion%2FinitAcrd.act8dctpNwfNTs4eidxLRCIgd%2FfMn8T1wMHBOfgxkR%2FXx0Ee3ZP5kFfUVn5bqLy6TB%2FVyIGVxbpdZQu%0D%0ANS%2BmXJg5rzT9GWMz4vB");
		System.out.println(decode);*/
		
		
		
		String str = "";
		JSONObject object = JSONObject.fromObject(str);
		System.out.println(object);
	}
	
	
	
	
	
}
