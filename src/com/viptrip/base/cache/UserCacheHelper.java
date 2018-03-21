package com.viptrip.base.cache;

import javax.servlet.http.HttpSession;

import com.viptrip.base.common.MyEnum;
import com.viptrip.wetrip.entity.TAcUser;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class UserCacheHelper {
	private static boolean isCacheUserAsGloble = false;

	//TAcUser对象基础key
	private static String baseKey4TAcUser="TAcUser-";
	
	private static String baseKey4UserId="UserId-";
	
	private static String baseKey4UserPlatform="UserPlatform-";
	
	/**
	 * 保存TAcUser到redis缓存
	 * 
	 * @param session
	 * @param user
	 * @return
	 */
	public static OutputSimpleResult SaveUserToCache(HttpSession session,
			TAcUser user) {
		OutputSimpleResult osr = new OutputSimpleResult();

		if (user != null) {
			String key = baseKey4TAcUser + user.getUserid().longValue();
			if (isCacheUserAsGloble) {
				osr=CacheHelper.SaveToCache(key, user, MyEnum.CacheKeepTime.一天);
			} else {
				osr=CacheHelper.SaveToCache(session, key, user,
						MyEnum.CacheKeepTime.一天);
			}
		} else {
			osr.result = "用户对象为空，不予处理。";
		}

		return osr;
	}
	
	/**
	 * 从redis缓存中获取用户信息TAcUser
	 * @param session
	 * @param userId
	 * @return
	 */
	public static OutputResult<TAcUser, String> GetUserFromCache(HttpSession session,long userId){
		OutputResult<TAcUser, String> or=new OutputResult<TAcUser, String>();
		String key = baseKey4TAcUser + userId;
		if (isCacheUserAsGloble) {
			or=CacheHelper.GetFromCache(TAcUser.class, key);
		} else {
			or=CacheHelper.GetFromCache(TAcUser.class,session, key);
		}
		
		return or;
	}
	
	/**
	 * 保存userid到缓存
	 * @param session
	 * @param userId
	 * @return
	 */
	public static OutputSimpleResult SaveUserIdToCache(HttpSession session,
			long userId) {
		return CacheHelper.SaveToCache(session, baseKey4UserId, userId,MyEnum.CacheKeepTime.一天);
	}

	/**
	 * 从缓存中获取userid
	 * @param session
	 * @return
	 */
	public static OutputResult<Long, String> GetUserIdFromCache(HttpSession session){
		return CacheHelper.GetFromCache(Long.class,session, baseKey4UserId);
	}
	
	/**
	 * 从缓存中删除userid
	 * @param session
	 */
	public static void DeleteUserIdFromCache(HttpSession session){
		CacheHelper.DeleteFromCache(session, baseKey4UserId);
	}
	
	/**
	 * 保存userPlatform到缓存
	 * @param session
	 * @param userPlatform
	 * @return
	 */
	public static OutputSimpleResult SaveUserPlatformToCache(HttpSession session,
			MyEnum.UserPlatform platform) {
		return CacheHelper.SaveToCache(session, baseKey4UserPlatform, platform,MyEnum.CacheKeepTime.一天);
	}

	/**
	 * 从缓存中获取userPlatform
	 * @param session
	 * @return
	 */
	public static OutputResult<MyEnum.UserPlatform, String> GetUserPlatformFromCache(HttpSession session){
		return CacheHelper.GetFromCache(MyEnum.UserPlatform.class,session, baseKey4UserPlatform);
	}
	
	/**
	 * 从缓存中删除userPlatform
	 * @param session
	 */
	public static void DeleteUserPlatformFromCache(HttpSession session){
		CacheHelper.DeleteFromCache(session, baseKey4UserPlatform);
	}
	
	/**
	 * 从缓存中删除user
	 * @param session
	 * @param userId
	 */
	public static void DeleteUserFromCache(HttpSession session,long userId){
		CacheHelper.DeleteFromCache(session, baseKey4TAcUser + userId);
	}
}
