package com.viptrip.wetrip.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.wetrip.controller.Test;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_Test;
import com.viptrip.wetrip.model.Response_Test;
import com.viptrip.wetrip.service.impl.TestService;

import etuf.v1_0.model.base.output.OutputResult;
@Controller("tAction")
@RequestMapping("/test")
public class TestAction extends BaseAction{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(TestAction.class);
	
	
	@Resource
	private TestService testService;

	@RequestMapping("/test.act")
	public String test(Model model,Integer userId,Request_Test test) throws UnsupportedEncodingException{
		/*Request_Test para = new Request_Test();
		para.setUserId(userId);
		
		Test testC=new Test();
		
		System.out.println("TestAction.test()==para==" + new Gson().toJson(para));
		
		OutputResult<Response_Test, String> result = testC.ClientRequest(para, Response_Test.class);
		String resultStr = new Gson().toJson(result);
//		result = new Gson().fromJson(resultStr,new TypeToken<OutputResult<Response_Test, String>>(){}.getClass());
		
		System.out.println("TestAction.test()==is succeed==" + result.IsSucceed());
		model.addAttribute("info", result);

		System.out.println("TestAction.test()==result==" + new Gson().toJson(result));
		System.out.println("TestAction.test()==result.resultObj==" + new Gson().toJson(result.getResultObj()));
		System.out.println("TestAction.test()==result.resultObj.id==" + new Gson().toJson(result.getResultObj().getId()));
		System.out.println("TestAction.test()==result.resultObj.name==" + new String(new Gson().toJson(result.getResultObj().getName())));
		
		
		
		PageObject<TAcUser> list = testService.list();
		model.addAttribute("list", list);*/
		return "test/test";
	}
	
	@RequestMapping("/demo.act")
	@ResponseBody
	public void test1(){
		String url =  "http://123.206.25.12:8080/Test1/test/show.act";
		//http://localhost:8080/Test1/test/show.act?u=user&p=123456
		HttpRequest request = null;
		int code = 0;
		boolean flag = true;
		int retryCount = 5;
		while(flag && retryCount>0){
			retryCount--;
			try {
				request = HttpRequest.post(url);
//				System.out.println("TestAction.test1()==ok==" + request.ok());
//				System.out.println("TestAction.test1()==serverError==" + request.serverError());
//				System.out.println("TestAction.test1()==badRequest==" + request.badRequest());
//				System.out.println("TestAction.test1()==created==" + request.created());
				Map<String, String> values = new HashMap<String, String>();
				values.put("u", "user1");
				values.put("p", "1234567");
				request.form(values);
				code = request.code();
				System.out.println("TestAction.test1()==code==" + code);
				if(200==code){
					String body = request.body();
					System.out.println("TestAction.test1()==body==" + body);
					flag = false;
				}else{
					continue;
				}
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
	
	@RequestMapping("/test1.act")
	public void test2(){
		String string = req.getQueryString();
		System.out.println("TestAction.test2()==string==" + string);
	}
	
	@RequestMapping("/toTest1.act")
	public String toTest1(){
		return "test/test1";
	}
	@RequestMapping("/toTest2.act")
	public String toTest2() throws InterruptedException{
		Thread.sleep(5000L);
		return "test/test2";
	}
	
	
}
