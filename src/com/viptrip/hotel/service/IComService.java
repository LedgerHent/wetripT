package com.viptrip.hotel.service;

import java.util.List;

import com.viptrip.common.model.CityLevelModel;
import com.viptrip.common.model.Request_GetCityGrade;
import com.viptrip.common.model.Request_GetCustomerCityGrade;
import com.viptrip.common.model.Request_GetRuleById;
import com.viptrip.common.model.Request_GetRulesByEnterprise;
import com.viptrip.common.model.Request_GetRulesByTravller;
import com.viptrip.common.model.Response_GetCityGrade;
import com.viptrip.hotel.model.Request_GetBookRight;
import com.viptrip.hotel.model.Request_GetBookStaffList;
import com.viptrip.hotel.model.Request_GetOrgGroups;
import com.viptrip.hotel.model.Request_GetOrgGroupsByIds;
import com.viptrip.hotel.model.Request_GetOrgGroupsLikeName;
import com.viptrip.hotel.model.Request_GetOrgLike;
import com.viptrip.hotel.model.Request_GetOrgList;
import com.viptrip.hotel.model.Request_GetPayQuota;
import com.viptrip.hotel.model.Request_HotelServiceFee;
import com.viptrip.hotel.model.Request_ListPassenger;
import com.viptrip.hotel.model.Request_ListPassengerLikeName;
import com.viptrip.hotel.model.Request_ListStaffByIds;
import com.viptrip.hotel.model.Request_ListStaffInDep;
import com.viptrip.hotel.model.Request_ListStaffInGroup;
import com.viptrip.hotel.model.Request_ListStaffLike;
import com.viptrip.hotel.model.Request_ListStaffLikeName;
import com.viptrip.hotel.model.Request_ListStaffLikeTel;
import com.viptrip.hotel.model.Request_PayByAdvance;
import com.viptrip.hotel.model.Response_GetBookStaffList;
import com.viptrip.hotel.model.Response_GetHotelServiceFee;
import com.viptrip.hotel.model.Response_GetOrgGroups;
import com.viptrip.hotel.model.Response_HotelServiceFee;
import com.viptrip.hotel.model.Response_ListPassenger;
import com.viptrip.hotel.model.Response_ListPassengerLikeName;
import com.viptrip.hotel.model.Response_ListStaffByIds;
import com.viptrip.hotel.model.Response_ListStaffInDep;
import com.viptrip.hotel.model.Response_ListStaffInGroup;
import com.viptrip.hotel.model.Response_ListStaffLike;
import com.viptrip.hotel.model.Response_ListStaffLikeName;
import com.viptrip.hotel.model.Response_ListStaffLikeTel;
import com.viptrip.hotel.model.org.OrgInfoPage;
import com.viptrip.hotel.model.orggroups.OrgGroup;
import com.viptrip.hotel.model.orggroups.OrgGroupData;
import com.viptrip.hotel.model.orggroups.OrgGroupDataIds;
import com.viptrip.hotel.model.orglikename.OrgLikeName;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.staff.StaffList;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;
import com.viptrip.wetrip.model.Request_GetApprovalDetail;
import com.viptrip.wetrip.model.Request_ListApprovalByTravller;
import com.viptrip.wetrip.model.policy.GetRulesByTravllerModel;
import com.viptrip.wetrip.model.policy.PolicyByenterprise;
import com.viptrip.wetrip.model.policy.Res_ApprovalByTravller;
import com.viptrip.wetrip.model.policy.Res_ApprovalsDetail;

public interface IComService {
	/**
	 * 模糊查询企业名称
	 * @param nameKey1 
	 */
	public  OrgInfoPage getOrgNameList(Integer userId,String nameKey, String type,Page page);
	
	public  List<OrgLikeName> getOrgNameListIds(Integer userId,String nameKey, String type);
	
	public OrgGroupData getOrgGroupList(Request_GetOrgGroups arg0);
	/**
	 * 企业分组模糊查询
	 */
	public OrgGroupData getOrgGroupsLikeNameList(Request_GetOrgGroupsLikeName arg0);
	
	
	/**
	 * 企业分组查询-按id序列
	 */
	public OrgGroupDataIds getOrgGroupsByIdsList(Request_GetOrgGroupsByIds arg);
	//常旅客列表
	public Response_ListPassenger ListPassenger(Request_ListPassenger arg0);
	//常旅客模糊查询
	public Response_ListPassengerLikeName ListPassengerLikeName(Request_ListPassengerLikeName arg0);
	
	public Integer BookStaffOrg(Request_GetBookStaffList arg);

	public StaffList getListStaffLike(Request_ListStaffLike arg0);

	public StaffList ListStaff(Integer orgId, Page page,Object ...val);
	//企业信息（列表）查询
	public OrgInfoPage GetOrgList(Request_GetOrgList arg0);

	public Response_GetHotelServiceFee getServiceFee(Integer userId);
	
	//计算服务费
	public Response_HotelServiceFee countServiceFee(Request_HotelServiceFee arg);

	public StaffList BokeListStaff(Integer orgId, Page page,
			Integer businessType, String nameKey);
	//可预订权限查询
	public Integer GetBookRight(Request_GetBookRight arg0);
	
	//预付/授信额度查询
	public double GetPayQuota(Request_GetPayQuota arg0);

	public Double PayBalance(Request_PayByAdvance arg0);

	public String getUserInfoByPhone(String phone);

	public Integer CheckIsUnneedAuditByBookUser(Integer userId);

	public GetRulesByTravllerModel getPolicyDetail(Request_GetRulesByTravller arg);
	
	public Res_ApprovalsDetail getApprovalDetail(Request_GetApprovalDetail arg);
	
	public List<Res_ApprovalByTravller> listApprovalByTravller(Request_ListApprovalByTravller arg);

	public CityLevelModel getCityLevel(Request_GetCityGrade arg0);

	public GetRulesByTravllerModel getPolicyById(Request_GetRuleById arg);

	public PolicyByenterprise getPolicyByEnterprise(Request_GetRulesByEnterprise arg);

	public List<CityLevelModel> getCustomerCityGrade(Request_GetCustomerCityGrade arg);

}
