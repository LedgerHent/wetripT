package com.viptrip.wetrip.util;

import java.util.Iterator;
import java.util.LinkedHashMap;


/**
 * 彩云集团错误信息
 * 	线程级保存信息
 * @author selfwhisper
 *
 */
public class ErrShareUtil {
	
	private static final int max_size = 2000;
	
	private static final LinkedHashMap<Long, ThreadLocal<String>> container = new LinkedHashMap<>(max_size);
	
	/**
	 * 获取错误信息
	 * @return
	 */
	public static String get(){
		long id = Thread.currentThread().getId();
		String result = null;
		ThreadLocal<String> tl = container.get(id);
		if(null!=tl){
			result = tl.get();
		}
		return result;
	}
	
	/**
	 * 设置错误信息
	 * @param val
	 */
	public static void set(String val){
		clearOld();
		long id = Thread.currentThread().getId();
		ThreadLocal<String> tl = container.get(id);
		if(null != tl){
			tl.set(val);
		}else{
			tl = new ThreadLocal<String>();
			tl.set(val);
			container.put(id, tl);
		}
	}
	
	/**
	 * 清空
	 */
	private synchronized static void clearOld(){
		if(max_size == container.size()){
			Iterator<Long> it = container.keySet().iterator();
			for(int i=0;i<max_size/2;i++){
				if(it.hasNext()){
					Long next = it.next();
					container.remove(next);
				}
			}
		}
	}
	
}
