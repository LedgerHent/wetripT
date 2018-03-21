package com.viptrip.hotel.model.userinfo4hotel;

public class UserInfo4Hotel {
	//用户id
	public Integer userId;
	//用户类别 1=散客，2=企业用户
	public Integer userType;
	//企业编号 散客为-1，企业客户为自然数(>0)
	public Integer enterpriseId;
	//企业名称
	public String enterpriseName;
	//用户姓名
	public String userName;
	//交易密码状态（暂时不用）0=不需要，1=需要提供，2=需要设置
	public Integer tradePwdstate;
	//所属部门编号 散客为-1，企业客户为自然数(>0)
	public Integer departmentId;
	//所属部门名称
	public String departmentName;
	//用户角色名称
	public String role;
	//分组id
	public Integer groupId;
	
}
