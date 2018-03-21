package com.viptrip.test;

import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.viptrip.util.JSON;
import com.viptrip.warning.service.WarningManager;
import com.viptrip.wetrip.service.BalancePay;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestWarning {
	@Resource
	private WarningManager wm;
	
	@Resource
	private BalancePay bpay;

	@Test
	public void testChange(){
		Long orgid = 5660l;
		String orgName = "测试企业";
		Double amount = 1000d;
		String orderNo = "H20170913A032";
		
		/*MSM msm = wm.changeLimitation(orgid, amount, Otype.退款调额, null);
		System.out.println("TestWarning.testChange()==msms==" + JSON.get().toJson(msm));*/
		
		Map<String, String> balancePay = bpay.balancePay("2", amount, orderNo, "", orgid, orgName, "4", "");
		
		System.out.println("TestWarning.testChange()==balancePay==" + JSON.get().toJson(balancePay));
		
	}
	

	
	
}
