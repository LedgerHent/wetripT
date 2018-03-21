package com.viptrip.wetrip.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.TicketClient;
import com.viptrip.wetrip.controller.res.Msg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListStaff;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.model.base.Response_CertificateMessage;
import com.viptrip.wetrip.model.employees.ListEmployee;
import com.viptrip.wetrip.model.flight.RespData_GetFlightList;
import com.viptrip.wetrip.service.IFlightService;
import com.viptrip.wetrip.service.impl.ListStaffServiceImpl;
import com.viptrip.wetrip.service.impl.UserService;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;


public  class ListStaff extends TicketClient<Request_ListStaff, Response_ListStaff>{

	
	@Override
	protected OutputSimpleResult DataValid(Request_ListStaff rls) {
		//TODO 数据校验 
		OutputSimpleResult osr=new OutputSimpleResult();
		if(rls!=null){
			osr.code = Msg.Success.getCode();
			osr.result = Msg.Success.getInfo();
		}else{
			osr.result = Msg.IncompletePara.getInfo();
		}
		
		return osr;
	}

	@Override
	protected OutputResult<Response_ListStaff, String> DoBusiness(
			Request_ListStaff rls) {
		//TODO 逻辑处理
		
		List<TAcUser> lta = null;
		
		//String key = new Gson().toJson(rls);
		ListStaffServiceImpl service = ApplicationContextHelper.getInstance().getBean(ListStaffServiceImpl.class);
		UserService userService=ApplicationContextHelper.getInstance().getBean(UserService.class);
			lta = service.getListStaff(rls);
	//ListStaffServiceImpl service = ApplicationContextHelper.getInstance().getBean(ListStaffServiceImpl.class);
	OutputResult<Response_ListStaff, String> or=new OutputResult<Response_ListStaff, String>();
	Response_ListStaff rs=new Response_ListStaff();
	//lta = service.getListStaff(rls);
	List<ListEmployee> el = new ArrayList<ListEmployee>();
	Long userId=rls.getUserId();
	boolean isPlatFromOrg=userService.isPlatFormOrg(userId);
	List<Long> orgid=new ArrayList<Long>();
	for (int i = 0; i < lta.size(); i++) {
        TAcUser ta=lta.get(i);
        orgid.add(ta.getOrgid());
	}
	Map<Long, String>  map=service.getOrgDataMap(orgid);
	
	for (int i = 0; i < lta.size(); i++) {
		TAcUser ta=lta.get(i);
		ListEmployee lel=new ListEmployee();
		lel.setId(ta.getUserid().intValue());
		lel.setName(ta.getUsername());
		lel.setMobile(ta.getPhone());
		lel.setEmail(ta.getEmail());
		lel.setType(1);
		if(ta.getBirthday()!=null&&!"".equals(ta.getBirthday())){
			lel.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(ta.getBirthday()));
		}
		lel.setSex(ta.getSex()==null?2:Integer.valueOf(ta.getSex()));
		//数字 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
		List<Response_CertificateMessage> rc= new ArrayList<Response_CertificateMessage>();
			Response_CertificateMessage cm = null;
			if(ta.getIdcard()!=null&&!"".equals(ta.getIdcard())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getIdcard());
				cm.setType(1);
				rc.add(cm);
			}
			
			if(!"".equals(ta.getPassport())&&ta.getPassport()!=null){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getPassport());
				cm.setType(2);
				rc.add(cm);
			}
			if(ta.getDlwltxz()!=null&&!"".equals(ta.getDlwltxz())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getDlwltxz());
				cm.setType(3);
				rc.add(cm);
			}
			if(ta.getHxz()!=null&&!"".equals(ta.getHxz())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getHxz());
				cm.setType(4);
				rc.add(cm);
			}
			if(ta.getJgz()!=null&&!"".equals(ta.getJgz())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getJgz());
				cm.setType(5);
				rc.add(cm);
			}
			if(ta.getGatxz()!=null&&!"".equals(ta.getGatxz())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getGatxz());
				cm.setType(6);
				rc.add(cm);
			}
			if(ta.getRtz()!=null&&!"".equals(ta.getRtz())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getRtz());
				cm.setType(7);
				rc.add(cm);
			}
			if(ta.getOtherNum()!=null&&!"".equals(ta.getOtherNum())){
				cm = new Response_CertificateMessage();
				cm.setNum(ta.getOtherNum());
				cm.setType(99);
				rc.add(cm);
			}
		lel.setIds(rc);
		if(ta.getOrgid()!=null&&!"".equals(ta.getOrgid())){
			lel.setDepartmentId(ta.getOrgid().intValue());
		}
//		lel.setDepartmentName(service.getOrgData(ta.getOrgid()).getOrgname());
		lel.setDepartmentName(map.get(ta.getOrgid()));
		lel.setMileageCardNo(ta.getMileage());
		lel.setIsplatFormOrg(isPlatFromOrg);
		el.add(lel);
	}
	rs.setData(el);
	System.out.println("***************coll=====" + rs.getData().toString());
		if(rs.status==0){
			or.code=Msg.Success.getCode();
		}else or.result=" status Error";
		or.setResultObj(rs);
		return or;
	}
	
}
