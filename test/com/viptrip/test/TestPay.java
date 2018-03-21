package com.viptrip.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.kevinsawicki.http.HttpRequest;
import com.viptrip.util.JSON;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebPayRtn;
import com.viptrip.wetrip.pay.weixin.service.WXPayService;
import com.viptrip.wetrip.service.impl.CaiyunPayService;
import com.viptrip.wetrip.util.ErrShareUtil;
import com.viptrip.wetrip.vo.CaiyunOrderStatus;
import com.viptrip.wetrip.vo.CaiyunPayParaExt;
import com.viptrip.wetrip.vo.CaiyunResp;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试  
@ContextConfiguration({"classpath:conf/spring/spring-context.xml"}) //加载配置文件
public class TestPay {
	@Resource
	private CaiyunPayService service;
	
	@Resource
	private WXPayService wx;
	
	@Test
	public void testPay(){
		Map<String,String> extend = new HashMap<String, String>();
		Long userId = 300276L;
		String orderId = "201706071001";
		Double price = 0.01D;
//		CaiyunResp<?> resp = service.prePayCheck(userId, orderId, price, extend);
		
//		System.out.println("TestPay.testPay()==resp==" + JSON.get().toJson(resp));
		
		
		/*CaiyunPayParaExt para = new CaiyunPayParaExt(orderId, 1, JSON.get().toJson(extend),CaiyunOrderStatus.PAY_SUCCESS);
		CaiyunResp<?> payCallBack = service.payCallBack(userId, para);
		
		System.out.println("===========" + ErrShareUtil.get());
		
		System.out.println("TestPay.testPay()==payCallBack==" + JSON.get().toJson(payCallBack));
		
		CaiyunResp<?> payRefund = service.payRefund(userId, orderId, price, JSON.get().toJson(extend));
		
		System.out.println("TestPay.testPay()==payRefund==" + JSON.get().toJson(payRefund));*/
		
		
		WXMwebPayRtn mwebPay = wx.mwebPay(orderId, price, null, "机票支付", "61.51.80.178", "http://we.viptrip365.com/wetrip/");
		
		System.out.println("TestPay.testPay()=========" + JSON.get().notEscapeHTML(true).toJson(mwebPay));
		System.out.println("===================================================================================");
		if(null!=mwebPay && StringUtil.isEmpty(mwebPay.getMweb_url())){
			String html = HttpRequest.post(mwebPay.getMweb_url()).header("referer", "viptrip365.com").body();
			System.out.println(html);
		}
		System.out.println("===================================================================================");
		
		
		
	}
	
	
	public static void main(String[] args) {
		WXMwebPayRtn mweb = new WXMwebPayRtn();
		Field[] field = mweb.getClass().getFields();
		for(Field f:field){
			System.out.println("TestPay.main()" + JSON.get().toJson(f.getName()));
		}

	}
	
}
