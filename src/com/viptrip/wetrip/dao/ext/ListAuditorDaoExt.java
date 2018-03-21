package com.viptrip.wetrip.dao.ext;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viptrip.base.common.support.PageObject;
import com.viptrip.base.dao.BaseDaoForJPA;
import com.viptrip.base.dao.PagingationDaoSupportForJPA;
import com.viptrip.util.PageParam;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Response_ListAuditor;

@Component
public class ListAuditorDaoExt extends BaseDaoForJPA{
	
	/*//查询审核人ID
	public List<String> getOrgCheckManId(String queryHQL,
			Object[] parameterArray){
		
		return queryForList(queryHQL, parameterArray);
	}

	//查询审核人姓名、邮箱等
	public PageObject<List<TAcUser>> queryTAcUserPhoneandEmail(PageParam pp,String queryHQL){
		return queryForPaginationList(pp,queryHQL);
	}*/
	
	public TAcOrg queryForTAcOrg(Object id ) {
		return queryForEntity(id,TAcOrg.class);
	}
	
}
