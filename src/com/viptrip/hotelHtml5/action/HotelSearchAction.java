package com.viptrip.hotelHtml5.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.controller.GetRulesByTravller;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.common.model.Response_GetRulesByTravller;
import com.viptrip.hotel.HotelServer;
import com.viptrip.hotelHtml5.service.impl.EmployeeServiceImpl;
import com.viptrip.hotelHtml5.util.JhUtil;
import com.viptrip.hotelHtml5.vo.EmpAndOrg;
import com.viptrip.hotelHtml5.vo.request.Request_GetCityList;
import com.viptrip.hotelHtml5.vo.request.Request_GetHotCity;
import com.viptrip.hotelHtml5.vo.request.Request_GetKeyAssociateInfo;
import com.viptrip.hotelHtml5.vo.request.vo.IndexPoiQueryRequest;
import com.viptrip.hotelHtml5.vo.response.Response_GetCityList;
import com.viptrip.hotelHtml5.vo.response.Response_GetHotCity;
import com.viptrip.hotelHtml5.vo.response.Response_GetKeyAssociateInfo;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Response_GetPayMethodList;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.model.policy.HATPolicyModel;
import com.viptrip.wetrip.model.policy.HATRules;
import com.viptrip.wetrip.model.policy.PolicyValueModel;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.v2.base.Response_BaseMsg;

@Controller
@RequestMapping("/hotel")
public class HotelSearchAction extends BaseAction{

	private static Logger logger = LoggerFactory.getLogger(HotelServer.class);
	private static final String page = "hotelHtml5/hotel-homepage";//首页
	private static final String roompage = "hotelHtml5/hotel-room";
	private static final String keypage = "hotelHtml5/key-search";
	private static final String travelpage = "hotelHtml5/travel";
	private static final String travelerpage = "hotelHtml5/hotel-traveler";
	private static final String citySearchpage = "hotelHtml5/hotel-citysearch";
	private static final String  citySpage="hotelHtml5/hotel-keySearch";
	private static final String cityMap = "hotelHtml5/map";
	/*
	 * 初始页面跳转
	 */
	@RequestMapping("/page.act")
	public String toPage(){
		TAcUser user = getUser();
		Long userId = getUserId();
		addAttr("user",user); 
		addAttr("userId",userId);
		addAttr("roomCount","1");
		addAttr("audltCount","2");
		addAttr("childCount","0");
		addAttr("coo", "coo");
		return page;
	}
	@RequestMapping("/position.act")
	public String position(){
		
		return "hotelHtml5/position";
	}
	/*
	 * 地图测试
	 */
	@RequestMapping("/map.act")
	public String toMap(String longitude,String latitude){
		 try {
					addAttr("longitude",longitude);
					addAttr("latitude",latitude);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cityMap;
	}
	
	//返回页面
	@RequestMapping("/returnpage.act")
	public String returnPage(String room,String cont,String id,String finalage,String keyw,String keyAssociateValue,String cityname,String cityid,String pagee) {
		try {
			addAttr("roomCount",room);
			addAttr("audltCount",cont);
			addAttr("childCount",id);
			addAttr("fage",finalage);
			addAttr("keyword",keyw);
			addAttr("keyAssociateValue",keyAssociateValue);
			addAttr("cityname",cityname);
			addAttr("cityid",cityid);
			addAttr("pagee",pagee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	/**
	 * 房间数 人数页面
	 */
	@RequestMapping("/room.act")
	public String toPageRoom(String room,String audlt,String child,String cage){
		addAttr("room",room==""?"1":room);
		addAttr("audlt",audlt==""?"2":audlt);
		addAttr("child",child==""?"0":child);
		addAttr("age",cage==""?"":cage);
		return roompage;
	}
	/**
	 * 关键词搜索页面
	 */
	@RequestMapping("/key.act")
	public String toKeyPage(){
		
		return keypage;
	}
	/**
	 * 差旅页面
	 */
	@RequestMapping("/travel.act")
	public String travelPage(String name,String userId){
		Request_GetRulesByTravller rg=new Request_GetRulesByTravller();
		List<Integer> blist=new ArrayList();
		List<Integer> tlist=new ArrayList();
		blist.add(3);
		if(userId==null){
			rg.setUserId(getUserId());
		}else{
			rg.setUserId(Long.parseLong(userId));
		}
		rg.businessTypes=blist;
		tlist.add((int) rg.userId);
		rg.travellers=tlist;
		GetRulesByTravller grb=new GetRulesByTravller();
		OutputResult<Response_GetRulesByTravller, String> or = grb.ClientRequest(rg, Response_GetRulesByTravller.class);
		Response_GetRulesByTravller rpg=new Response_GetRulesByTravller();
		if(or.IsSucceed()){
			rpg=or.getResultObj();
			List<HATPolicyModel> hplist=new ArrayList<HATPolicyModel>();
			HATPolicyModel htp=new HATPolicyModel();
			hplist=rpg.getData().policy;
			List<HATRules> hrlist=new ArrayList<HATRules>();
			HATRules htr=new HATRules();
			Map<String, Object> maps=new HashMap<String, Object>();
			if (null!=hplist&&hplist.size() > 0) {
				for (int i = 0; i < hplist.size(); i++) {
					htp = hplist.get(i);
					hrlist = htp.getRules();
					for (int j = 0; j < hrlist.size(); j++) {
						htr = hrlist.get(j);
						Map<String, Object> map = JSONObject.fromObject(htr
								.getPolicyDetailValue());
						for (Entry<String, Object> entry : map.entrySet()) {
							List<PolicyValueModel> pvmList = new ArrayList<PolicyValueModel>();
							String type = entry.getKey();
							JSONArray strval1 = (JSONArray) entry.getValue();
							for (Object ss : strval1) {
								PolicyValueModel pvm = new PolicyValueModel();
								Map<String, Object> map1 = JSONObject
										.fromObject(ss);
								for (Entry<String, Object> entry1 : map1
										.entrySet()) {
									// {"recKey":"continent-1","topPrice":1000}
									// h":[{"recKey":"cityLevel-1","topLv":"1","topPrice":1000},{"recKey":"cityLevel-4","topLv":"3","topPrice":2000}
									if ("a".equals(type)) {
										if ("recKey".equals(entry1.getKey()))
											pvm.setRecKey(entry1.getValue()
													.toString()
													.replace("continent-", ""));
										if ("topPrice".equals(entry1.getKey()))
											pvm.setTopPrice(Double
													.parseDouble(entry1
															.getValue()
															.toString()));
									}
									if ("h".equals(type)) {
										if ("recKey".equals(entry1.getKey()))
											pvm.setRecKey(entry1.getValue()
													.toString()
													.replace("cityLevel-", ""));
										if ("topPrice".equals(entry1.getKey()))
											pvm.setTopPrice(Double
													.parseDouble(entry1
															.getValue()
															.toString()));
										if ("topLv".equals(entry1.getKey()))
											pvm.setTopLv(entry1.getValue()
													.toString());
									}
								}
								pvmList.add(pvm);

							}
							maps.put(type, pvmList);
						}
					}
				}
				System.out.println(userId+"maps差旅"+new Gson().toJson(maps));
				addAttr("rule",maps);
			}else{
				addAttr("rule",null);
			}
			return travelpage;
		}
		return null;
	}
	/**
	 * 差旅员工搜索
	 */
	@RequestMapping("/traveler.act")
	public String travelerPage(){
		return travelerpage;
	}
	@RequestMapping("/travelinfo.act")
	@ResponseBody
	public String infoPage(String username){
		EmployeeServiceImpl bean = ApplicationContextHelper.getInstance().getBean(EmployeeServiceImpl.class);
		TAcUser user = getUser();
		List<EmpAndOrg> queryData = bean.queryData(username, user);
		//List<EmpAndOrg> queryData = bean.queryData(username, user.getOrgid());
		String ss="";
		String empname="";
		String userid="";
		EmpAndOrg eao=new EmpAndOrg();
		for (int i = 0; i < queryData.size(); i++) {
			eao=queryData.get(i);
			String orgname=eao.getOrgname();
			String comname=eao.getCompanyname();
			if(orgname.equals(comname)){
				empname=eao.getUsername()+"-";
				ss=ss+empname;
				orgname=eao.getOrgname()+"&";
				ss=ss+orgname;
			}else{
				empname=eao.getUsername()+"-";
				ss=ss+empname;
				orgname=eao.getOrgname()+"-";
				ss=ss+orgname;
				comname=eao.getCompanyname()+"&";
				ss=ss+comname;
			}
			userid=eao.getUserid()+",";
			ss=ss+userid;
		}
		System.out.println("user"+ss);
		PrintWriter pw;
		try {
			pw = resp.getWriter();
			pw.write(ss);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.debug("城市查詢失败！");
		}
		return null;
	}
	/**
	 * 城市搜索
	 */
	@RequestMapping("/citySearch.act")
	public String citySearchPage(){
		return citySearchpage;
	}
	/**
	 * 跳转城市搜索页面
	 */
	@RequestMapping("/citySP.act")
	public String citySearch(String country){
		addAttr("country", country);
		return citySpage;
	}
	/**
	 * 关键词及城市搜索
	 */
	@RequestMapping("/search.act")
	@ResponseBody
	public String searchWord(String cityid,String cityname,String homeabroadflag,String content,String flag){
		try {
			Request_GetKeyAssociateInfo request = new Request_GetKeyAssociateInfo();
			request.method = "GetKeyAssociateInfo";
			IndexPoiQueryRequest indexPoiQueryRequest = new IndexPoiQueryRequest();
			String type="";
			//String typ = new String(homeabroadflag.getBytes("iso-8859-1"),"UTF-8");//修改乱码
			logger.info("=======================homeabroadflag="+homeabroadflag);
			if(!"".equals(homeabroadflag)&&"1".equals(homeabroadflag)){
				type="36916";
			}else{
				type="-99";
			}
			if(!"".equals(flag)&&"city".equals(flag)){
				indexPoiQueryRequest.setPoiType("1");
				indexPoiQueryRequest.setPid(type);
				indexPoiQueryRequest.setContent(content);
			}else{
				indexPoiQueryRequest.setPoiType("9");
				indexPoiQueryRequest.setHomeAbroadFlag(type);
				indexPoiQueryRequest.setCityId(cityid);
				indexPoiQueryRequest.setCityName(URLEncoder.encode(cityname, "utf-8"));
				indexPoiQueryRequest.setContent(URLEncoder.encode(content, "utf-8"));
			}
			request.setIndexPoiQueryRequest(indexPoiQueryRequest);
			Response_GetKeyAssociateInfo result = JhUtil.sendReqJh(request, Response_GetKeyAssociateInfo.class);
			String jsonS = JSONArray.fromObject(result.getBusiData()).toString();
			PrintWriter pw;
			try {
				pw = resp.getWriter();
				pw.write(jsonS);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("城市查詢失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 	null;
	}
	
	/**
	 * 城市搜索
	 */
	@RequestMapping("/allcity.act")
	@ResponseBody
	public String allcity(String citytype) {
		try {
			Request_GetCityList request = new Request_GetCityList();
			request.method = "GetCityList";
			if (!"".equals(citytype) && citytype .equals( "国内")) {
				request.setParentId("36916");
			} else {
				request.setParentId("-99");
			}
			request.setLv("3");
			request.setPageNum("1");
			request.setPageSize("100");
			Response_GetCityList result;
			result = JhUtil.sendReqJh(request, Response_GetCityList.class);
			String jsonS = JSONArray.fromObject(result.getBusiData()).toString();
			System.out.println("allcity"+jsonS);
			PrintWriter pw;
				pw = resp.getWriter();
				pw.write(jsonS);
				pw.flush();
				pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			String errStr = "===========================错误代码：201712121116。错误信息：wetrip查询所有城市发生异常\r\n";
			logger.debug(errStr);
		}

		return null;
	}
	/**
	 * 热门城市搜索
	 */
	@RequestMapping("/hotcity.act")
	@ResponseBody
	public String hotcity() {
		  try {
			Request_GetHotCity request = new Request_GetHotCity();
			request.method = "GetHotCity";
		   	Response_GetHotCity result = JhUtil.sendReqJh(request, Response_GetHotCity.class);
		   	String jsonS = JSONObject.fromObject(result.getBusiData()).toString();
		   	System.out.println("hotcity!!!!!!!!!!!!!!!"+jsonS);
			PrintWriter pw;
			try {
				pw = resp.getWriter();
				pw.write(jsonS);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.debug("热门城市查询失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
}
