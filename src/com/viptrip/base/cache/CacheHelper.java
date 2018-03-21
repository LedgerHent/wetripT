package com.viptrip.base.cache;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.servlet.http.HttpSession;

import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.base.common.MyEnum;

import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

public class CacheHelper {
	
	/**
	 * 保存信息到缓存【基于session】
	 * 
	 * @param session
	 * @param key
	 * @param value
	 * @return
	 */
	public static <T extends Serializable> OutputSimpleResult SaveToCache(
			HttpSession session, String key, T value,
			MyEnum.CacheKeepTime cacheKeepTime) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if (session != null) {
			String sId = session.getId();
			String formatKey = MessageFormat.format("[{0}]{1}", sId, key);
			osr=SaveToCache(formatKey,value,cacheKeepTime);
		} else {
			osr.result = "当前HttpSession为null，不予处理。";
		}
		return osr;
	}

	/**
	 * 保存信息到缓存【全局共享】
	 * @param key
	 * @param value
	 * @param cacheKeepTime
	 * @return
	 */
	public static <T extends Serializable> OutputSimpleResult SaveToCache(
			String key, T value, MyEnum.CacheKeepTime cacheKeepTime) {
		OutputSimpleResult osr = new OutputSimpleResult();
		if (value != null) {
			RedisCacheManager.save(key, value, cacheKeepTime.getValue());
			osr.code = Constant.Code_Succeed;
		} else {
			osr.result = "存储的数据对象为null，不予处理。";
		}
		return osr;
	}

	/**
	 * 从缓存中读取信息【基于session】
	 * 
	 * @param session
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T extends Serializable> OutputResult<T, String> GetFromCache(
			Class<T> clazz, HttpSession session, String key) {
		OutputResult<T, String> or = new OutputResult<>();
		if (session != null) {
			String formatKey = MessageFormat.format("[{0}]{1}",
					session.getId(), key);
			or=GetFromCache(clazz,formatKey);
		} else {
			or.result = "当前HttpSession为null，不予处理。";
		}
		return or;
	}
	
	/**
	 * 从缓存中读取信息【全局共享】
	 * @param clazz
	 * @param key
	 * @return
	 */
	public static <T extends Serializable> OutputResult<T, String> GetFromCache(
			Class<T> clazz, String key) {
		OutputResult<T, String> or = new OutputResult<>();
		T t = RedisCacheManager.get(key, clazz);
		if (t != null) {
			or.setResultObj(t);
			or.code = Constant.Code_Succeed;
		} else {
			or.result = "未知错误：读取缓存信息失败。";
		}
		return or;
	}

	/**
	 * 从缓存中删除信息【基于session】
	 * 
	 * @param session
	 * @param key
	 */
	public static void DeleteFromCache(HttpSession session, String key) {
		if (session != null) {
			String formatKey = MessageFormat.format("[{0}]{1}",
					session.getId(), key);
			DeleteFromCache(formatKey);
		}
	}
	
	/**
	 * 从缓存中删除信息【全局共享】
	 * @param key
	 */
	public static void DeleteFromCache(String key) {
		RedisCacheManager.del(key);
	}
}
