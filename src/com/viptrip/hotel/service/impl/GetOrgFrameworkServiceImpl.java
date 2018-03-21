package com.viptrip.hotel.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.viptrip.hotel.model.Request_GetOrgFramework;
import com.viptrip.hotel.model.orglikename.OrgLikeName;
import com.viptrip.hotel.service.GetOrgFrameworkService;
import com.viptrip.wetrip.dao.ext.GetEnterpriseFrameworkDaoExt;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcRole;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.entity.TAcUserrole;
import com.viptrip.wetrip.model.employees.CompanyInfo;


@Service
public class GetOrgFrameworkServiceImpl implements GetOrgFrameworkService{

	
	@Resource
	private GetEnterpriseFrameworkDaoExt dao;
	
 
	
		 
	public CompanyInfo getAllOrg(Request_GetOrgFramework req,CompanyInfo topTacOrg,List<CompanyInfo> childs) {
		//获取最上面层的企业
		if(topTacOrg==null){
			String hql1="";
			long orgid=0;
			TAcOrg tOrg=null;
			if(req.getType()==0){//用户所在企业
				 hql1="select orgid from TAcUser t where t.userid = "+ req.userId+"";
				 orgid=dao.queryForInt(hql1);
				 tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
				 Long companyId=Long.parseLong(tOrg.getCompany());
				 tOrg=(TAcOrg)dao.queryForEntity(companyId,TAcOrg.class);
				 
			}else if(req.getType()==1){//指定企业
				TAcUser user = dao.queryForEntity(Long.valueOf(req.userId),TAcUser.class);
				
				//凯撒商旅员工				
				if("121".equals(user.getOrgid()+"") ||  "2443".equals(user.getOrgid()+"")){
					
					 tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(req.orgId),TAcOrg.class);
					 Long companyId=Long.parseLong(tOrg.getCompany());
					 tOrg=(TAcOrg)dao.queryForEntity(companyId,TAcOrg.class);
					
				
				}else{
						return	new CompanyInfo(); 
				}
			}else if(req.getType()==2){//所有企业	
				TAcUser user = dao.queryForEntity(Long.valueOf(req.userId),TAcUser.class);
				
				//凯撒商旅员工
				
				//判断是否客维销售角色
				if ("121".equals(user.getOrgid()+"")) { // 北京
					List<TAcRole> rolelist = this.getRole(req);
					if(rolelist!=null&&rolelist.size()>0){
					String rolename= rolelist.get(0).getRolename();
						
						if(rolename.contains("销售")||rolename.contains("客维")){
							orgid = 10;
						}else{
							
							 tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(user.getOrgid()),TAcOrg.class);
							 orgid=Long.parseLong(tOrg.getCompany());
						}
					}
					
					
					
				} else if ("2443".equals(user.getOrgid()+"")) {
					List<TAcRole> rolelist = this.getRole(req);
					if(rolelist!=null&&rolelist.size()>0){
						String rolename= rolelist.get(0).getRolename();
						
						if(rolename.contains("销售")||rolename.contains("客维")){
							orgid = 20;
						}else{
							
							 tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(user.getOrgid()),TAcOrg.class);
							 orgid=Long.parseLong(tOrg.getCompany());
						}
					}
					
				}else{
					
					return  new CompanyInfo();
					
				}
									
					 tOrg = (TAcOrg)dao.queryForEntity(Long.valueOf(orgid),TAcOrg.class);
					 
								
				}
			topTacOrg=new CompanyInfo();
			topTacOrg.setId(Integer.valueOf(tOrg.getOrgid().intValue()));
			topTacOrg.setName(tOrg.getOrgname());
			topTacOrg.setIsbottom(tOrg.getIsbottom());
			}
				
				
	
	

		//获取该企业下的所有部门以及部门下的子部门直到最后
		childs=this.getOrgChildById(topTacOrg.getId());
		topTacOrg.setChild(childs);
		for (int i = 0; i <childs.size(); i++) {
			CompanyInfo orgChild=childs.get(i);
			if(orgChild!=null){
				List<CompanyInfo> childs_=null;
				req.setOrgId(orgChild.getId());
				getAllOrg(req,orgChild,childs_);
			}
		}
		return topTacOrg;
	}
	
	
	public List<CompanyInfo> getOrgChildById(int id) {
		//通过parentid获取企业下的部门集合
		List<CompanyInfo> tacOrgList=new ArrayList<CompanyInfo>();
	
		String	hql="select t.orgid,t.orgname from T_AC_ORG t where  t.parentid = '"+id+"'  and status ='1'";
		List<Object[]>  orgObj=(List<Object[]>)dao.queryBySQL(hql,null);
		for (int i = 0; i < orgObj.size(); i++) {
			Object[] orgobj=orgObj.get(i);
			CompanyInfo org=new CompanyInfo();
			org.setId(Integer.valueOf(String.valueOf(orgobj[0])));
			org.setName(String.valueOf(orgobj[1]));
			
			tacOrgList.add(org);
		}
		return tacOrgList;
	}
	
	public List<TAcRole> getRole(Request_GetOrgFramework req){
		String hql="from TAcOrg where 1=1 and orgtype='2' and status ='1'";
		List<TAcUserrole> tlist = dao.queryForList("from TAcUserrole t where t.userid= "+req.userId);
		Long roleid = tlist.get(0).getRoleid();
		List<TAcRole> rlist = dao.queryForList("from TAcRole t where t.roleid= "+Long.parseLong(roleid.toString()));
		return rlist;
	}
	
}
