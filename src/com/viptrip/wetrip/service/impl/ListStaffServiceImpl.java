package com.viptrip.wetrip.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.util.PageParam;
import com.viptrip.util.Pager;
import com.viptrip.wetrip.dao.ext.ListStaffDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.OrgData;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListStaff;
import com.viptrip.wetrip.model.Response_ListStaff;
import com.viptrip.wetrip.service.ListStaffService;

@Service
public class ListStaffServiceImpl implements ListStaffService{


	@Resource
	private ListStaffDaoExt dao;
	
	@Override
	public List<TAcUser> getListStaff(
			Request_ListStaff para) {
		long id=para.getUserId();
		String hql1="select orgid from TAcUser t where t.userid = "+id+"";
		long orgid=dao.queryForInt(hql1);
		String hql = "from TAcUser t where t.orgid = "+orgid+" ";
		return dao.queryForList(hql);
	}

	//根据部门ID查找部门信息
	@Override
	public OrgData getOrgData(Long orgid) {
		TAcOrg org =  (TAcOrg)dao.queryForEntity( orgid,TAcOrg.class);
		OrgData data=new OrgData();
		data.setOrgid(org.getOrgid());
		data.setOrgname(org.getOrgname());
		return data;
	}
	
	@Override
	public Map<Long,String> getOrgDataMap(List<Long> orgid) {
	    StringBuffer orgidstr=new StringBuffer();
	    for (int i = 0; i < orgid.size(); i++) {
	        orgidstr.append(orgid.get(i));
	        if(i!=orgid.size()-1){
	            orgidstr.append(",");
	        }
        }
        String hql="from TAcOrg org where org.orgid in("+orgidstr.toString()+")";
        List<TAcOrg> orglist=dao.queryForList(hql, null);
        Map<Long,String> map=new HashMap<>();
        for (int i = 0; i < orglist.size(); i++) {
            map.put(orglist.get(i).getOrgid(), orglist.get(i).getOrgname());
        }
        return map;
    }

}
