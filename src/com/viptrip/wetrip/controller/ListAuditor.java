package com.viptrip.wetrip.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListAuditor;
import com.viptrip.wetrip.model.Response_ListAuditor;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.model.employees.Auditor;
import com.viptrip.wetrip.service.impl.ListAuditorServiceImpl;
import com.viptrip.wetrip.service.impl.ListStaffServiceImpl;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class ListAuditor extends TicketClient<Request_ListAuditor,Response_ListAuditor>{

	//TODO 数据校验
	@Override
	protected OutputSimpleResult DataValid(Request_ListAuditor rla) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if(rla!=null){
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}else{
			osr.result = Msg.IncompletePara.getInfo();
		}
		return osr;
	}

	@Override
	protected OutputResult<Response_ListAuditor, String> DoBusiness(
			Request_ListAuditor rla) {
		// TODO 业务逻辑处理
	
		List<TAcUser> lta = null;
		/*
		String key = new Gson().toJson(rla);
		ListAuditorServiceImpl service = ApplicationContextHelper.getInstance().getBean(ListAuditorServiceImpl.class);
		if(RedisCacheManager.exists(key)){//读取缓存
			TypeToken<List<TAcUser>> typeToken = new TypeToken<List<TAcUser>>(){};
			lta = (List<TAcUser>) RedisCacheManager.get(key, typeToken.getClass());
		}else{//读库
			//获取service层实例
			List<String> checkmen = new ArrayList<String>();
			checkmen=service.getOrgCheckManId(rla);//获取审核人的ID集合
			if(null!=checkmen&&checkmen.size()!=0){
				lta=service.queryTAcUserPhoneandEmail(checkmen);//TAcUser信息
			}
			if(null!=lta){
				RedisCacheManager.save(key, lta);//写入缓存
			}
		}*/
	//获取service层实例
	ListAuditorServiceImpl service = ApplicationContextHelper.getInstance().getBean(ListAuditorServiceImpl.class);
	OutputResult<Response_ListAuditor, String> or=new OutputResult<Response_ListAuditor, String>();	 
	List<String> checkmen = new ArrayList<String>();
	checkmen=service.getOrgCheckManId(rla);//获取审核人的ID集合
	lta=service.queryTAcUserPhoneandEmail(checkmen);//TAcUser信息
	Response_ListAuditor la=new Response_ListAuditor();
	List<Auditor> lau=new ArrayList<Auditor>();
	if(null!=lta){
	for (int i = 0; i < lta.size(); i++) {
		TAcOrg org = new TAcOrg();
		TAcUser tau=lta.get(i);
         	Auditor au=new Auditor();
             org = (TAcOrg) service.queryForTAcOrg(tau.getOrgid(),org ); // 根据此用户的部门id找出对应的部门名称
               au.setId(tau.getUserid().intValue());
               au.setName(tau.getUsername());
               au.setMobile(tau.getPhone());
               au.setEmail(tau.getEmail());
               if(org.getOrgid()!=null&&!"".equals(org.getOrgid())){
            	   au.setDepartmentId(org.getOrgid().intValue());
               }
             au.setDepartmentName(org.getOrgname());
             String vervifyOrgids="";
             String ids=tau.getVerifyorg();
           	if(ids !=null && !"".equals(ids)){
           		//去掉前后的无用逗号 ,
           		if(ids.charAt(0)==','){
           			ids=ids.substring(1);
           		}
           		if(ids.charAt(ids.length()-1)==','){
           			ids=ids.substring(0,ids.length()-1);
           		}
           		vervifyOrgids=ids;
           	}
           	au.setAuditDepartmentIds(vervifyOrgids);
           	lau.add(au);
		 
	}
	}
	la.setData(lau);
	System.out.println("***************coll=====" + la.getData().toString());
	if(0!=la.data.size()){
		
		if (la.status == 0) {
			or.code = Msg.Success.getCode();
		} else
			or.result = " status Error";
		or.setResultObj(la);
	}	
		return or;
	}

}
