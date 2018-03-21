package com.viptrip.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.service.InsuranceService;
import com.viptrip.wetrip.service.PayMethodService;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestIns {
	
	@Resource
	private InsuranceService is;
	
	@Resource
	private PayMethodService pms;
	
	
	@Test
	public void testins(){
//		Response_GetInsuranceList gil = new Response_GetInsuranceList();
//		List<Response_PriceAndDescription> gilList = is.getInsuranceList();
//		gil.setData(gilList);
//		System.out.println("===============" + gil.getData().toString());
//		for(int i = 0;i < gil.getData().size();i++){
//			System.out.println("************" + gil.getData().get(i).toString());
//		}
		
		Response_GetPayMethodList gpml = new Response_GetPayMethodList();
		Long userid = 254157L;
		TAcUser user = pms.findUser(userid);
		System.out.println("++++++++++++++++" + user.getOrgid().longValue());
		gpml.setData(pms.getPayMethodList(user.getOrgid().longValue(),"0"));
		System.out.println("**************" + gpml.getData().toString());
	}
}
