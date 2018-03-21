package com.viptrip.base.cache.manager;


import java.io.IOException;
import java.util.Arrays;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import ch.qos.logback.classic.Logger;

import com.google.gson.Gson;
import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;

/**
 * 缓存管理器
 * @author selfwhisper
 *
 */
public final class RedisCacheManager {
	
	final static Logger log =  (Logger) LoggerFactory.getLogger(RedisCacheManager.class);
	
	@SuppressWarnings("unchecked")
	private static RedisTemplate<String, Object> redisTemplate = ApplicationContextHelper.getInstance().getBean(RedisTemplate.class);
	
	private static final Long DEFAULT_LIVETIME = 600L;
	
	private static final Long livetime;
	
	private static final Gson gson = new Gson();
	
	static{
		long time = DEFAULT_LIVETIME;
		try {
			String timeStr = PropertiesUtils.getProperty(Const.PRO_CACHE_LIVETIME, Const.FILE_CONFIG);
			time = Long.parseLong(timeStr);
		} catch (IOException e) {
			log.error(StringUtil.getExceptionStr(e));
		}
		livetime = time;
	}
	
	
    /** 
     * 删除，根据key精确匹配 
     *  
     * @param key 
     */  
    public static void del(final String... key) {
    	if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.del(String[]) is call,parameter is " + key);
    	}
        redisTemplate.delete(Arrays.asList(key));  
    }  
  
    /** 
     * 批量删除，根据key模糊匹配 
     *  
     * @param pattern 
     */  
    public static void delpn(final String... pattern) {
    	if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.delpn(String[]) is call,parameter is " + pattern);
    	}
        for (String kp : pattern) {  
            redisTemplate.delete(redisTemplate.keys(kp + "*"));  
        }  
    }  
  
    
    /**
     * 是否存在
     * @param key
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean exists(final String key) {
    	if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.exists(String) is call,parameter is " + key);
    	}
        return (Boolean) redisTemplate.execute(new RedisCallback() {
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }
    
    
    /**
     * 保存
     * @param key
     * @param value
     */
    public static void save(final String key, Object value) {
    	if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.save(String,Object) is call,parameters is " + key + "," + value.toString());
    	}
    	save(key, value, livetime);
    }
    
    /**
     * 保存
     * @param key
     * @param value
     * @param sec
     */
    public static void save(final String key, Object value, final Long sec) {
    	if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.save(String,Object,Long) is call,parameters is " + key + "," + value.toString() + "," + sec);
    	}
		final byte[] vbytes = SerializeUtil.serialize(value);
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), vbytes);
				if (sec > 0) {
                    connection.expire(redisTemplate.getStringSerializer().serialize(key), sec);
                }
				return null;
			}
		});
	}

    /**
     * 读取
     * @param key
     * @param elementType
     * @return
     */
	public static <T> T get(final String key, Class<T> elementType) {
		if(log.isDebugEnabled()){
    		log.debug("RedisCacheManager.get(String,Class) is call,parameters is " + key + "," + elementType);
    	}
		return redisTemplate.execute(new RedisCallback<T>() {
			@Override
			public T doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keybytes = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keybytes)) {
					byte[] valuebytes = connection.get(keybytes);
					@SuppressWarnings("unchecked")
					T value = (T) SerializeUtil.unserialize(valuebytes);
					return value;
				}
				return null;
			}
		});
	}
	
	/**
	 * 生成key
	 * @param obj
	 * @return
	 */
	public static String genKey(Object obj){
		String key = null;
		if(obj != null){
			key = obj.getClass().getName() + "_" + gson.toJson(obj);
		}
		return key;
	}
    
}
