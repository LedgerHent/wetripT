package com.viptrip.wetrip.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.dao.ext.ListAuditorDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_ListAuditor;
import com.viptrip.wetrip.service.ListAuditorService;


@Service
public class ListAuditorServiceImpl implements ListAuditorService{
	
	@Resource
	private ListAuditorDaoExt dao;
	
	@Override
	public List<String> getOrgCheckManId(Request_ListAuditor para) {
		long id=para.getUserId();
		String hql1="select orgid from TAcUser t where t.userid = "+id+"";
		long orgid=dao.queryForInt(hql1);
		String strSQL = "select to_char(t.userid) from t_ac_user t , t_ac_org o, t_ac_userrole ur where " +
				"t.orgid = o.orgid and ur.roleid in (66, 55) and t.userid = ur.userid and o.orgid in " +
				"(select orgid from t_ac_org start with orgid in (select orgid from t_ac_org where orgtype = '2' " +
				"start with orgid = '" + orgid + "' connect by prior  parentid = orgid) connect by prior orgid = parentid)";
		List<String> qList=(List<String>) dao.queryBySQL(strSQL,null);
		return qList;
	}
	//根据ID查询user信息
	@Override
	public List<TAcUser> queryTAcUserPhoneandEmail(
			List<String> checkmen) {
        String ids="";
        	for(String id:checkmen){
        		if(!"".equals(ids)){
        			ids+=",";
        		}
        		ids+=id;
        	}
        	if(StringUtil.isEmpty(ids)){
        		return null;
        	}
		String queryHQL="from TAcUser where USERID in ( "+ ids +") ";
		
		return dao.queryForList(queryHQL);
}
	//根据ID查找TAcOrg
	@Override
	public TAcOrg queryForTAcOrg(Object id,TAcOrg tac ) {
		return dao.queryForEntity(id,TAcOrg.class);
	}
	
	
	}