package com.viptrip.common.service.impl;

import com.viptrip.common.model.ApproveIdModel;
import com.viptrip.common.model.ListApprovalMatchModel;
import com.viptrip.common.model.Request_GetApprovalDetail;
import com.viptrip.common.model.Request_ListApprovalByTravller;
import com.viptrip.common.service.PolicyService;
import com.viptrip.common.util.ApprovalEnum;
import com.viptrip.wetrip.dao.ext.ComDao;
import com.viptrip.wetrip.entity.*;
import com.viptrip.wetrip.model.policy.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.Map.Entry;

@Service
public class PolicyServiceImpl implements PolicyService {
	
	@Resource
	private ComDao dao;

	@Override
	public Res_ApprovalsDetail getApprovalDetail(Request_GetApprovalDetail id) {
		
		Res_ApprovalsDetail detail = new Res_ApprovalsDetail();
		try {
			TTmcApprovePolicy policy = dao.queryForEntity(Long.parseLong(id.approvalId.toString()),TTmcApprovePolicy.class);
			detail.id=Integer.valueOf(policy.getPolicyId().toString());
			detail.desc=policy.getMemo();
			detail.name=policy.getPolicuName();
			detail.type=Integer.valueOf(policy.getApproveType().toString());
			String hql=" from TTmcApprovePolicyFlow t where approve_policy_id ="+id.approvalId;
			List<TTmcApprovePolicyFlow> ttps = dao.queryForList(hql);
			//List<TTmcApprovePolicyFlow> ttps = (List<TTmcApprovePolicyFlow>) dao.queryBySQL(hql,null);
			String hsql="select approve_level from T_TMC_APPROVE_FLOW t where  approve_policy_id ="+id.approvalId+"  group by approve_level";
			List list =(List) dao.queryBySQL(hsql, null);
			String hqls="select approve_policy_id,lable_value,match_type from T_TMC_APPROVE_POLICY_LABLE where approve_policy_id= "+id.approvalId+" and lable_type =2";
			List<Object[]> lable=(List<Object[]> )dao.queryBySQL(hqls, null);
			Object [] dd=lable.get(0);
			if("1".equals(dd[2].toString())){
				detail.approvalType=0;
			}else{
				detail.approvalType=Integer.valueOf(dd[1].toString());
			}
			String hsql2="select approve_policy_id,lable_value,match_type from T_TMC_APPROVE_POLICY_LABLE where approve_policy_id= "+dd[0]+" and lable_type =1";
			List<Object[]> busslable=(List<Object[]> )dao.queryBySQL(hsql2, null);
			Object [] dds=busslable.get(0);
			if("1".equals(dds[2].toString())){
				detail.businessType=0;
			}else{
				detail.businessType=Integer.valueOf(dds[1].toString());
			}
			List<ApprovalsModel> resList = new ArrayList<ApprovalsModel>();
			for (int i = 0; i < list.size(); i++) {
				ApprovalsModel model = new ApprovalsModel();
				List<ApprovalsPerson> persons = new ArrayList<ApprovalsPerson>();
				model.flowId=Integer.valueOf(list.get(i).toString());
				model.type=Integer.valueOf(policy.getApproveMode().toString());
				for(TTmcApprovePolicyFlow ttp:ttps){
					if(ttp == null){
						continue;
					}
					if(ttp.getApprLevel().toString().equals(list.get(i).toString())){
						ApprovalsPerson person = new ApprovalsPerson();
						person.id=Integer.valueOf(ttp.getApproveUserId().toString());
						person.name=ttp.getApproveUserName();
						person.email=ttp.getApproveUserEmail();
						person.mobile=ttp.getApproveUserMobile();
						persons.add(person);
					}
				}
				model.auditors=persons;
				resList.add(model);
			}
			detail.approvals=resList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return detail;
		
	}

	@Override
	public List<Res_ApprovalByTravller> listApprovalByTravller(
			Request_ListApprovalByTravller arg) {
		List<Res_ApprovalByTravller> finals=new ArrayList<Res_ApprovalByTravller>();
		try {
			List<Object> oldpids=new ArrayList<Object>();
			List<Object> oldorgids=new ArrayList<Object>();
			List<Object> pids=new ArrayList<Object>();
			List<Object> orgids=new ArrayList<Object>();
			List<Object> allids=new ArrayList<Object>();
			
			List<ApproveTravller> list=arg.travellers;
			List idsList=new ArrayList();
			for(ApproveTravller at:list){
				if(!"".equals(at.id) && at.id!=null){
					oldpids.add(at.id);
				}
				
				if(at.costIds!=null && at.costIds.length>0){
					for(Integer id:at.costIds){
						oldorgids.add(id);
					}
				}
			}
			
			for(Object pid:oldpids){
				if(!pids.contains(pid)){
					pids.add(pid);
				}
			}
			
			
			//要查询员工的所属部门企业
			List userorg = new ArrayList();
			if(pids!=null && oldpids.size()>0){
				for(Object userId:pids){
					TAcUser user = dao.queryForEntity(Long.valueOf(userId.toString()), TAcUser.class);
					String sql="select distinct orgid from t_ac_org start with orgid = "+Long.valueOf(user.getOrgid())+" connect by prior parentid=orgid and orgtype in (2,3)";
					List<Integer> orgList =(List<Integer>) dao.queryBySQL(sql,null);
					oldorgids.addAll(orgList);
				}
			}
			
			
			for(Object orgid:oldorgids){
				if(orgids.indexOf(orgid.toString())== -1){
					orgids.add(orgid.toString());
				}
			}
			
			allids.addAll(orgids);
			allids.addAll(pids);
			//先根据    部门id和员工id  查询分组id
			TAcUser user =null;
			TAcOrg org = null;
			String companyId="";
			for(Object pid:pids){
				 user = dao.queryForEntity(Long.valueOf(pid.toString()), TAcUser.class);
				 org = dao.queryForEntity(Long.parseLong(user.getOrgid().toString()), TAcOrg.class);
				 if(!"-1".equals(org.getCompany())){
					 companyId=org.getCompany();
					 break;
				 }
			}
			for(Object orgid:orgids){
				org = dao.queryForEntity(Long.parseLong(orgid.toString()), TAcOrg.class);
				companyId=org.getCompany();
			}
			
			//String sql="select o.orgid,o.groupid from org_group o left join org_group_relation l on o.orgid=l.orgid and o.groupid=l.groupid where " +
//					"id in("+StringUtils.join(allids.toArray(),',')+")";
			
			
			String sql="select distinct groupid from org_group_relation where id in ("+StringUtils.join(allids.toArray(),',')+")";
			//List<Object> groupIds = (List<Object>) dao.queryBySQL("select groupid from ("+sql+") t where t.orgid='"+companyId+"' order by type desc ", null);
			List<Object> groupIds = (List<Object>) dao.queryBySQL(sql,null);
			List<Object> ruleMaps = new ArrayList<Object>();
		    Map<ApproveIdModel, ListApprovalMatchModel> map = new HashMap<>();
			
			//根据分组id查审批关联表查出审批规则id；
			for (int i = 0; i < groupIds.size(); i++) {
				String fenzu="select distinct t.group_name,r.rule_type_id from T_TMC_TRIP_RULE r inner join ORG_GROUP t on t.orgid="+companyId+" and t.GroupId= "+groupIds.get(i)+
						" where org_rule like '%,"+groupIds.get(i)+",%' and rule_type="+ApprovalEnum.RuleType.审批政策.getOperateType()+
						" and org_rule_type ="+ApprovalEnum.OgrRuleType.按分组.getOperateType()+" and state=1 ";
				List<Object []> groupList = (List<Object[]>)dao.queryBySQL(fenzu, null);
				if(groupList!=null && groupList.size()>0){         //理论上一个分组只能查出一条 审批政策
					for(Object [] match:groupList){
						ListApprovalMatchModel addAll = new ListApprovalMatchModel();
						addAll.matchType=ApprovalEnum.OgrRuleType.按分组.getOperateType();
						addAll.matchName=match[0].toString();
						addAll.matchValue=Integer.valueOf(groupIds.get(i).toString());
						map.put(new ApproveIdModel(match[1].toString()), addAll);
						ruleMaps.add(match[1]);
					}
				}
			}
			
			//根据部门企业id去查关联表
			if(orgids.size()>0 && orgids!=null){
				for(Object orgid:orgids){
					String orgs="select distinct o.orgname,t.rule_type_id，o.orgtype from T_TMC_TRIP_RULE t inner join t_ac_org o on o.orgid="+orgid+
							" where org_rule like '%,"+orgid+",%' and rule_type="+ApprovalEnum.RuleType.审批政策.getOperateType()+
							" and state=1 and org_rule_type in (1,2)";
					List<Object []> orgpoList = (List<Object[]>)dao.queryBySQL(orgs, null);
					if(orgpoList!=null && orgpoList.size()>0){    //理论上一个部门只能查出一条 审批政策
						for(Object [] match:orgpoList){
							ListApprovalMatchModel addAll = new ListApprovalMatchModel();
							if("2".equals(match[2])){
								addAll.matchType=ApprovalEnum.OgrRuleType.按部门.getOperateType();
							}else{
								addAll.matchType=ApprovalEnum.OgrRuleType.按企业.getOperateType();
							}
							addAll.matchName=match[0].toString();
							addAll.matchValue=Integer.valueOf(orgid.toString());
							map.put(new ApproveIdModel(match[1].toString()), addAll);
							ruleMaps.add(match[1]);
						}
					}
				}
			}
			
			//根据员工id去查关联表
			if(pids.size()>0 && pids!=null){
				for(Object pid:pids){   //理论上一个部门只能查出一条 审批政策
					String orgs="select distinct u.username,t.rule_type_id from T_TMC_TRIP_RULE t inner join t_ac_user u on u.userid="+pid+
							" where org_rule like '%,"+pid+",%' and rule_type="+ApprovalEnum.RuleType.审批政策.getOperateType()+
							" and org_rule_type = "+ApprovalEnum.OgrRuleType.按员工.getOperateType()+"and state=1";
					List<Object []> empList = (List<Object []>)dao.queryBySQL(orgs, null);
					if(empList!=null && empList.size()>0){    //理论上一个部门只能查出一条 审批政策
						for(Object [] match:empList){
							ListApprovalMatchModel addAll = new ListApprovalMatchModel();
							addAll.matchType=ApprovalEnum.OgrRuleType.按员工.getOperateType();
							addAll.matchName=match[0].toString();
							addAll.matchValue=Integer.valueOf(pid.toString());
							map.put(new ApproveIdModel(match[1].toString()), addAll);
							ruleMaps.add(match[1]);
						}
					}
				}
			}
			
			if(map.size()<1 || map==null){
				return finals;
			}
			
			ArrayList bussList = new ArrayList();
			ArrayList apprList = new ArrayList();
			//关联政策，通过 业务类型和审批类型筛选
			String join = StringUtils.join(ruleMaps.toArray(),",");
			String hql=" from TTmcApprovePolicyLable where approve_policy_id in ("+join+")";
			List<TTmcApprovePolicyLable> lablelist = dao.queryForList(hql);
			for(TTmcApprovePolicyLable tta:lablelist){
				if(arg.businessType==0){       //业务类型不限
					if(arg.approvalType==0){   //审批类型不限
						idsList.add(tta.getApprPolicyId());
					}else{
						if(tta.getLableType()==2){
							if(tta.getLableValue()==Long.valueOf(arg.approvalType) || tta.getLableValue()==(-1)){
								idsList.add(tta.getApprPolicyId());
							}
						}
					}
				}else{
					if(arg.approvalType==0){
						if(tta.getLableType()==1){
							if(tta.getLableValue()==Long.valueOf(arg.businessType) || tta.getLableValue()==(-1)){
								idsList.add(tta.getApprPolicyId());
							}
						}
					}else{
						if(tta.getLableType()==1){  //业务类型
							if(tta.getLableValue()==Long.valueOf(arg.businessType) || tta.getLableValue()==(-1)){
								bussList.add(tta.getApprPolicyId());
							}
						}
						
						if(tta.getLableType()==2){  //审批类型
							if(tta.getLableValue()==Long.valueOf(arg.approvalType) || tta.getLableValue()==(-1)){
								apprList.add(tta.getApprPolicyId());
							}
						}
						
					}
				}
			}
			
			bussList.retainAll(apprList);
			idsList.addAll(bussList);
			String finalIds = StringUtils.join(idsList.toArray(),",");
			if(StringUtils.isNotEmpty(finalIds)){
				hql=" from TTmcApprovePolicy where approve_policy_id in ("+finalIds+") and state="+ApprovalEnum.ApprovalState.已发布.getOperateType();
				List<TTmcApprovePolicy> policys = dao.queryForList(hql);
				for(TTmcApprovePolicy po:policys){
					Iterator<Entry<ApproveIdModel, ListApprovalMatchModel>> entries = map.entrySet().iterator(); 
					while(entries.hasNext()){
						 Entry<ApproveIdModel, ListApprovalMatchModel> entry = entries.next();
//						 ApproveIdModel key = entry.getKey();
						 if(po.getPolicyId().toString().equals(entry.getKey().approvalId)){
							 System.out.println(po.getPolicyId().toString()+"ssdsddf"+entry.getKey().approvalId);
							 Res_ApprovalByTravller travller = new Res_ApprovalByTravller();
							 travller.approvalType=arg.approvalType;
							 travller.businessType=arg.businessType;
							 travller.id=Integer.valueOf(po.getPolicyId().toString());
							 travller.name=po.getPolicuName();
							 travller.desc=po.getMemo();
							 
							 travller.matchType=entry.getValue().matchType;
							 travller.matchName=entry.getValue().matchName;
							 travller.matchValue=entry.getValue().matchValue;
							 finals.add(travller);
						 }
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finals;
	}
		

}
