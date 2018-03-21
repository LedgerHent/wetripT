package com.viptrip.wetrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.wetrip.dao.ext.PassengerDao;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.service.IOrgBussinessTypeService;

@Service
public class OrgBussinessTypeService implements IOrgBussinessTypeService {

	@Resource
	private PassengerDao passenger;
	
	@Override
	public Integer getBussinessState(long orgid, Integer bussType,
			Integer source) {
		
		String hql="select state from OrgBussinessSwitch where orgid="+orgid+" and bussinesstype="
				+bussType+" and source="+source;
		List<Integer> state = passenger.queryForList(hql);
		if(state.size()>0){
			return state.get(0);
		}
		return 3;
		
	}

	@Override
	public TAcOrg getOrgById(long orgid) {
		String hql="from TAcOrg where orgid="+orgid;
		List<TAcOrg> list = passenger.queryForList(hql);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	

}
