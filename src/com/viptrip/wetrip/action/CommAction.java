package com.viptrip.wetrip.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.viptrip.base.action.BaseAction;
import com.viptrip.wetrip.service.IComService;

@Controller
@RequestMapping("/comm")
public class CommAction extends BaseAction{
	@Resource
	private IComService service;
	
	@RequestMapping("/get3CharCityName.act")
	@ResponseBody
	public Map<String,String> get3Char2City(RedirectAttributes attr){
		return service.get3CharCityMap();
	}
	
	@RequestMapping("/get3CharAirportName.act")
	@ResponseBody
	public Map<String,String> get3Char2Airport(){
		return service.get3CharAirportNameMap();
	}
	
	@RequestMapping("/act1.act")
	@ResponseBody
	public String act1(){
		return "forword:/comm/act2.act";
	}
	
	@RequestMapping("/act2.act")
	@ResponseBody
	public String act2(){
		return "";
	}
	
	@RequestMapping("/login.act")
	public String testLogin() throws UnsupportedEncodingException{
		String path = req.getContextPath();
		String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+"/";
		
		basePath = URLEncoder.encode(basePath,"utf-8");
		
		String url = "redirect:https://sso.viptrip.com/cas/login?service=" + basePath + "&user=110108196907293438&pass=123456&submitDirection=true";
		
		return url;
	}
	
}
