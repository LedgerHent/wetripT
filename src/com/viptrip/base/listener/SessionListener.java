package com.viptrip.base.listener;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.cache.CacheHelper;
import com.viptrip.base.cache.UserCacheHelper;
import com.viptrip.base.cache.manager.RedisCacheManager;
import com.viptrip.resource.Const;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.util.CaiyunUtil;

import etuf.v1_0.model.base.output.OutputResult;

/**
 * session初始化
 * 	添加token令牌
 * @author selfwhisper
 *
 */
public class SessionListener implements HttpSessionListener {
	private static Logger logger = LoggerFactory.getLogger(SessionListener.class);
	
	private static final Map<String,String> TOKEN = Collections.synchronizedMap(new HashMap<String, String>());
	
	private static String baseKey4UserId="UserId-";
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		event.getSession().setAttribute(Const.SESSION_CONFIG_TOKEN, TOKEN);
		
		logger.debug("SessionListener.sessionCreated(),sessionId=" + event.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		String userIdKey = "[" + session.getId() + "]" + baseKey4UserId;
		Long userId = null;
		if(RedisCacheManager.exists(userIdKey)){
			userId = RedisCacheManager.get(userIdKey, Long.class);
//			RedisCacheManager.del(userIdKey);//删除用户id缓存
			UserCacheHelper.DeleteUserIdFromCache(session);
			if(null!=userId){
//				RedisCacheManager.del("[" + session.getId() + "]" + baseKey4TAcUser + userId);//删除用户缓存
				UserCacheHelper.DeleteUserFromCache(session, userId);
			}
		}
	}

}
