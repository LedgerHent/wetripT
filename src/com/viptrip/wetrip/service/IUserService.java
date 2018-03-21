package com.viptrip.wetrip.service;

import com.viptrip.hotel.model.userinfo4hotel.UserInfo4Hotel;
import com.viptrip.wetrip.entity.TAcUser;

public interface IUserService {
	/**
	 * 通过用户名查找用户
	 * @param username
	 * @return
	 */
	public TAcUser getUserByUsername(String username);
	
	/**
	 * 通过用户id查找用户
	 * @param id
	 * @return
	 */
	public TAcUser getUserById(Long id) ;
	
	/**
	 * 根据登录名取用户
	 * 	登录名：身份证号-手机号-邮箱-护照号-用户id
	 * @param username
	 * @return
	 */
	public TAcUser getUserByLoginUsername(String username);
	
	/**
	 * 根据凯撒系统userID获取彩云平台uID
	 * 
	 * @return uid 正整数：正常平台uId，null：平台ID不存在，-1：有多条记录，
	 */
	public String getUban360UId(Long userId);
	
	/**
	 * 根据平台ID和系统用户ID查询平台用户ID
	 * @param userId
	 * @param platformId
	 */
	public String getPlatformUid(Long userId, Long platformId) ;

	public UserInfo4Hotel getUserInfo4Hotel(long parseLong);

	public Boolean isMenu(Long userid, Integer menuId);
}
