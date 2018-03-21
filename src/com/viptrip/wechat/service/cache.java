package com.viptrip.wechat.service;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import com.viptrip.base.cache.CacheHelper;
import com.viptrip.base.common.MyEnum;

import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class cache {

	private static MyEnum.CacheKeepTime redisKeepTime=MyEnum.CacheKeepTime.一天;
	
	/**
	 * 保存信息到缓存【默认key】，读取也需要用默认key，否则可能会读取不到
	 * @param session
	 * @param value
	 * @return
	 */ 
	public static <T extends Serializable> OutputSimpleResult SaveToCache(
			HttpSession session, T value) {
		String key=null;
		if(value!=null){
			key=value.getClass().getName();
		}
		return CacheHelper.SaveToCache(session,key,value,redisKeepTime);
	}
	
	
	/**
	 * 从缓存中读取信息【默认key】
	 * @param clazz
	 * @param session
	 * @return
	 */
	public static <T extends Serializable> OutputResult<T,String> GetFromCache(
			Class<T> clazz,HttpSession session){
		return CacheHelper.GetFromCache(clazz,session,clazz.getName());
	}
	
	
	
	/**
	 * 从缓存中删除信息【默认key】
	 * @param clazz
	 * @param session
	 */
	public static  <T extends Serializable> void DeleteFromCache(Class<T> clazz,HttpSession session){
		CacheHelper.DeleteFromCache(session,clazz.getName());
	}
	

	
}
