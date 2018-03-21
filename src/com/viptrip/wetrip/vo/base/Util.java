package com.viptrip.wetrip.vo.base;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.vo.TestVo;

public class Util {
	private static Logger logger = LoggerFactory.getLogger(Util.class);
	
	/**
	 * 将非final、static属性类型为String的字段编码从gbk转成utf-8
	 * @param obj
	 */
	public static void changeCharset(BaseVo obj){
		changeCharset(obj, Charset.GBK.getCharset(), Charset.UTF8.getCharset());
	}
	
	/**
	 * 将非final、static属性类型为String的字段编码从 fromCharset 转成 toCharset
	 * @param obj
	 */
	public static void changeCharset(BaseVo obj,String fromCharset,String toCharset){
		if(null!=obj){
			fromCharset = StringUtil.isEmpty(fromCharset)?Charset.GBK.toString():fromCharset;
			toCharset = StringUtil.isEmpty(fromCharset)?Charset.UTF8.toString():toCharset;
			Class<? extends BaseVo> clz = obj.getClass();
			Field[] fields = clz.getDeclaredFields();
			for(Field f:fields){
				// 跳过public、static属性
				if(Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers()))
					continue;
				String setter = setter(f);
				String getter = getter(f);
				try {
					Method set = clz.getDeclaredMethod(setter, f.getType());
					Method get = clz.getDeclaredMethod(getter);
					Object value = get.invoke(obj);
					if(null != value && value instanceof String){
						String val = (String) value;
						set.invoke(obj, new String(val.getBytes(fromCharset),toCharset));
					}
				} catch (NoSuchMethodException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				} catch (SecurityException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				} catch (IllegalAccessException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				} catch (IllegalArgumentException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				} catch (InvocationTargetException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				} catch (UnsupportedEncodingException e) {
					logger.error(StringUtil.getExceptionStr(e));
					continue;
				}
				
				
			}
		}
	}
	
	/**
	 * 获取setter名称
	 * @param f
	 * @return
	 */
	private static String setter(Field f){
		String result = null;
		if(null != f){
			String name = f.getName();
			if(!StringUtil.isEmpty(name)){
				result = "set" + name.substring(0,1).toUpperCase() + name.substring(1);
			}
		}
		return result;
	}
	
	/**
	 * 获取getter名称
	 * @param f
	 * @return
	 */
	private static String getter(Field f){
		String result = null;
		if(null != f){
			String name = f.getName();
			if(!StringUtil.isEmpty(name)){
				result = ((f.getType()==Boolean.class || f.getType()==boolean.class)?"is":"get") + name.substring(0,1).toUpperCase() + name.substring(1);
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		TestVo vo = new TestVo();
		changeCharset(vo);
	}
}
