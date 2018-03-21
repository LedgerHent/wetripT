package com.viptrip.wetrip.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viptrip.wetrip.common.CacheInit;

@Service
public class MyTask {
	private static Logger logger = LoggerFactory.getLogger(MyTask.class);
	/**
	 * 刷新票规
	 */
	public void refreshCache(){
		logger.info("开始刷新缓存数据");
		CacheInit.setEndorse();
		CacheInit.set3CHAR_AIRPORTNAME();
		CacheInit.set3CHAR_CITY();
		CacheInit.setAC2NAME();
		CacheInit.setCABINCODE();
		CacheInit.setCABINNAME();
		CacheInit.setPAYTYPE();
		CacheInit.setPLANETYPE();
		CacheInit.setDICTTYPECODE();
		logger.info("刷新缓存数据完成");
	}
}
