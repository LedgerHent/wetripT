package com.viptrip.hotelHtml5.util;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtilsBean2;

public class ClassUtil {

	
	public static Object getField(Object obj,String name){
		try {
			Field f = getDeclaredField(obj, name);
			if(f!=null){
				f.setAccessible(true);
				return  f.get(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void setField(Object obj,String name,Object val){
		try {
			Field f = getDeclaredField(obj, name);
			if(f!=null){
				f.setAccessible(true);
				f.set(obj,val);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setField(Object sObj,Object dObj) throws Exception{
		BeanUtilsBean2.getInstance().copyProperties(dObj, sObj);
	}
	
	public static Field getDeclaredField(Object object, String fieldName){  
        Field field = null ;  
        Class<?> clazz = object.getClass() ;  
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {  
            try {  
                field = clazz.getDeclaredField(fieldName) ;  
                return field ;  
            } catch (Exception e) {  
            }   
        }  
        return null;  
    }     
	
}
