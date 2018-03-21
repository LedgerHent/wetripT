package com.viptrip.wetrip.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viptrip.base.action.BaseAction;
import com.viptrip.base.common.MyEnum.UserPlatform;
import com.viptrip.wetrip.controller.PersonalInfo;
import com.viptrip.wetrip.dao.ext.AddPassenger;
import com.viptrip.wetrip.entity.TAcRole;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TAcUserrole;
import com.viptrip.wetrip.model.Request_PersonalInfo;
import com.viptrip.wetrip.model.Response_PersonalInfo;
import com.viptrip.wetrip.model.passenger.ReqData_CertificateMessage;
import com.viptrip.wetrip.model.passenger.Req_Passenger;

import etuf.v1_0.model.base.output.OutputResult;

@Controller
@RequestMapping("/wetrip")
public class PersonalCenterAction extends BaseAction{
	private static String page = "public/personal";
	private static String personalinfo="passenger/personinfo";
	
	
    @Resource
	private AddPassenger ap;
	
	@RequestMapping("/show.act")
	private String page(Integer status) {
		
		Long userId = getUserId();
		
		List<TAcUser> oneuser = ap.queryForList(" from TAcUser t where userid= "+userId);
		
		List<TAcUserrole> list = ap.queryForList("from TAcUserrole t where t.userid= "+userId);
		
		if(null!=list && list.size()>0){
			Long roleid = list.get(0).getRoleid();
			
			List<TAcRole> list2 = ap.queryForList(" from TAcRole t where t.roleid= "+roleid);
			String rolename = list2.get(0).getRolename();
			addAttr("rolename",rolename );
		}
		
		if(null==status){
			status = 0;
		}
		
		addAttr("user", oneuser.get(0));
		addAttr("status",status);
		addAttr("isWechat",getPlatform()== UserPlatform.微信?1:0);
		return page;
	}
	
	@RequestMapping("updateInfo.act")
	public String aboutPersonal(String id,String name,String email,	String phone,
			String usertype,String idcard){
		addAttr("id", id);
		addAttr("name", name);
		addAttr("email", email);
		addAttr("phone", phone);
		addAttr("usertype", usertype);
		addAttr("idcard", idcard);
		return personalinfo;
	}
	
	@RequestMapping("updateorsave.act")
	public String updateOrSave(Req_Passenger addP, Integer idType,
			String num){
		Request_PersonalInfo passenger = new Request_PersonalInfo();
		ReqData_CertificateMessage personData = new ReqData_CertificateMessage();
		
		passenger.setUserId(getUserId());
		List<ReqData_CertificateMessage> list = new ArrayList<ReqData_CertificateMessage>();
		personData.setType(idType);
		personData.setNum(num);
		
		list.add(personData);
		addP.setIds(list);
		passenger.setAddP(addP);

		PersonalInfo passInfo = new PersonalInfo();

		OutputResult<Response_PersonalInfo, String> result = passInfo
				.ClientRequest(passenger, Response_PersonalInfo.class);
		
		
		return this.page(null);
	}
}
