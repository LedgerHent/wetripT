package com.viptrip.wetrip.service.impl;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.viptrip.base.common.MyEnum;
import com.viptrip.wetrip.dao.ext.AddPassenger;
import com.viptrip.wetrip.dao.ext.PassengerDao;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TTravelPassenger;
import com.viptrip.wetrip.model.Request_DelPassenger;
import com.viptrip.wetrip.model.Request_ListPassenger;
import com.viptrip.wetrip.service.IPassengerService;

@Service
public class PassengerService implements IPassengerService {

	
	@Resource
	private PassengerDao passenger;
	
	@Resource
	private AddPassenger ap;
	
	@Override
	public List<TTravelPassenger> getListPassenger(
			Request_ListPassenger para) {
		    long userid=para.getUserId();
			String hql=" from TTravelPassenger where userid= "+userid;
			List<TTravelPassenger> list = passenger.queryForList(hql);
		
			return list;
	}

	@Override
	public Integer addPassenger(TTravelPassenger para) {
		
		//TTravelPassenger tt=new TTravelPassenger();
		//TTravelPassenger ttp = (TTravelPassenger)JSONObject.toBean(json,TTravelPassenger.class);
		
		ap.executeSave(para);
		Long userid = para.getUserid();
		String sql="select max(id) from TTravelPassenger where userId="+userid;
		int queryForInt = ap.queryForInt(sql);
		System.out.println(queryForInt);
		return queryForInt;
	}

	@Override
	public void deletePassenger(Request_DelPassenger para) {
		
		String hql="delete from TTravelPassenger where userid=? and id=?";
		long user=para.getUserId();
		long id=Long.valueOf(para.getPassengerId()+"");
		Object [] params=new Object[]{user,id};
		
		ap.executeDelete(hql, params);
	}

	
	@Override
	public void updatePassenger(TTravelPassenger para) {
		ap.executeUpdate(para);
	}

	
	@Override
	public TTravelPassenger getOneById(long id) {
		TTravelPassenger  ttp= ap.queryForEntity(id, TTravelPassenger.class);
		
		return ttp;
	}

	@Override
	public List<TTravelPassenger> getPassByIdcard(String idcard,long userid) {
		String hql=" from TTravelPassenger where userid= '"+userid+"' and (idnumber= '"+idcard+"')";
		List<TTravelPassenger> queryForList = passenger.queryForList(hql);
		return queryForList;
	}

	public void updatePersonalInfo(TTravelPassenger para){
		
		//根据电话号和userid确定当前用户下是否有此常旅客（也就是本人）
		String hql=" from TTravelPassenger where mobilephone = '"+para.getMobilephone()+"' and " +
				" userid = "+para.getUserid();
		List<TTravelPassenger> queryForList = passenger.queryForList(hql);	
		
		String hql2=" from TAcUser where userid = "+para.getUserid();
		List<TAcUser> tacuser = passenger.queryForList(hql2);
		TAcUser user = tacuser.get(0);
		user.setUsername(para.getName());
		user.setPhone(para.getMobilephone());
		user.setEmail(para.getEmail());
		if(MyEnum.IdType.二代身份证.getValue()==Integer.parseInt(para.getIdtype())){
			user.setIdcard(para.getIdnumber());
		}else if(MyEnum.IdType.护照.getValue()==Integer.parseInt(para.getIdtype())){
			user.setPassportpublic(para.getIdnumber());
		}else if(MyEnum.IdType.港澳通行证.getValue()==Integer.parseInt(para.getIdtype())){
			user.setPublicgatxz(para.getIdnumber());
		}
		ap.executeUpdate(user);
		if(queryForList.size()>0){
			TTravelPassenger ttp = queryForList.get(0);
			ttp.setName(para.getName());
			ttp.setMobilephone(para.getMobilephone());
			ttp.setPassengerType(para.getPassengerType());
			ttp.setEmail(para.getEmail());
			ttp.setIdtype(para.getIdtype());
			ttp.setIdnumber(para.getIdnumber());
			ap.executeUpdate(ttp);
		}else{
			ap.executeSave(para);
		}
	}
	
	
}
