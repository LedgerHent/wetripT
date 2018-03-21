package com.viptrip.taglib;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.viptrip.util.StringUtil;

/**
 * 自定义时间标签工具
 * @author selfwhisper
 *
 */
public class DateFunction {
	/**
	 * 格式化日期为字符串 默认样式为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @param style
	 * @return
	 */
	public static String format(Date date,String style){
		String result = "";
		try {
			if(StringUtil.isEmpty(style)){
				style = "yyyy-MM-dd HH:mm:ss";
			}
			if(date!=null){
				result = new SimpleDateFormat(style).format(date);
			}else{
				result = "";
			}
		} catch (Exception e) {
			return "Exception error!";
		}
		return result;
	}
	
	
	
	
	 
	
	 /**
		 * 格式化日期为字符串 默认样式为yyyy-MM-dd HH:mm:ss
		 * @param String
		 * @param style
		 * @return
		 */
		public static String format(String date,String style){
			String result = "";
			Date d=null;
			SimpleDateFormat sdf=null;
			try {
				if(StringUtil.isEmpty(style)){
					style = "yyyy-MM-dd HH:mm:ss";
				}
				if(date!=null){
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					d = sdf.parse(date);
					result = new SimpleDateFormat(style).format(d);
					
					
				}else{
					result = "";
				}
			} catch (Exception e) {
				return "Exception error!";
			}
			return result;
		}
	
		 /**
		 * 格式化日期为字符串 默认样式为yyyy-MM-dd HH:mm:ss
		 * @param String
		 * @param style
		 * @return
		 */
		public static String sformat(String date,String style){
			String result = "";
			Date d=null;
			SimpleDateFormat sdf=null;
			try {
				if(StringUtil.isEmpty(style)){
					style = "yyyy-MM-dd HH:mm:ss";
				}
				if(date!=null){
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					d = sdf.parse(date);
					result = new SimpleDateFormat(style).format(d).trim();
					
					
				}else{
					result = "";
				}
			} catch (Exception e) {
				return "Exception error!";
			}
			return result;
		}
	
		 /**
		 * 格式化日期为字符串 默认样式为yyyy-MM-dd HH:mm:ss
		 * @param String
		 * @param style
		 * @return
		 */
		public static String shformat(String date,String style){
			String result = "";
			Date d=null;
			SimpleDateFormat sdf=null;
			try {
				if(date!=null){
					sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					d = sdf.parse(date);
					result = new SimpleDateFormat(style).format(d).trim();
					
					
				}else{
					result = "";
				}
			} catch (Exception e) {
				return "Exception error!";
			}
			return result;
		}
		
	/**
	 * 用默认样式格式化日期为字符串  默认样式为yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @param style
	 * @return
	 */
	public static String format(Date date){
		String result = "";
		try {
			String style = "yyyy-MM-dd HH:mm:ss";
			if(date!=null){
				result = new SimpleDateFormat(style).format(date);
			}else{
				result = "";
			}
		} catch (Exception e) {
			return "Exception error!";
		}
		return result;
	}
	/**
	 * 获取时间差
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getDistanceTime(String str1, String str2, String format) {  
         DateFormat df = new SimpleDateFormat(StringUtil.isEmpty(format)?"yyyy-MM-dd HH:mm:ss":format);
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = ""; 
		if(day>0)
			result +=  day + "天";
		if(hour>0)
			result +=  hour + "小时";
		if(min>0)
			result +=  min + "分";
		return result;
    }
	
	
	
	/**
	 * 获取时间差
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static String getDistanceTime(Date d1, Date d2) {  
		String result = ""; 
		if(null!=d1 && null!=d2){
			long day = 0;
			long hour = 0;
			long min = 0;
			long sec = 0;
			
			long time1 = d1.getTime();
			long time2 = d2.getTime();
			long diff ;
			if(time1<time2) {  
				diff = time2 - time1;
			} else {  
				diff = time1 - time2;
			}  
			day = diff / (24 * 60 * 60 * 1000);
			hour = (diff / (60 * 60 * 1000) - day * 24);
			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
			
			if(day>0)
				result +=  day + "d";
			if(hour>0)
				result +=  hour + "h";
			if(min>0)
				result +=  min + "m";
		}
		return result;
	}  
	
	/**
	 * 获取时间差
	 * @param mins(总分钟，转换成 *天*时*分)
	 * @return
	 */
	public static String getDistanceTime(long mins) {  
        long day = 0;
        long hour = 0;
        long min = 0;
        try {
        	mins = mins * 60 * 1000;
            day = mins / (24 * 60 * 60 * 1000);
            hour = (mins / (60 * 60 * 1000) - day * 24);
            min = ((mins / (60 * 1000)) - day * 24 * 60 - hour * 60);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = ""; 
		if(day>0)
			result +=  day + "天";
		if(hour>0)
			result +=  hour + "小时";
		if(min>0)
			result +=  min + "分";
		return result;
    }
	
	
	
	/**
	 * 计算两个日期差是否跨天 跨几天
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static String getDistanceDay(String str1, String str2, String format) {  
         DateFormat df = new SimpleDateFormat(StringUtil.isEmpty(format)?"yyyy-MM-dd HH:mm:ss":format);
         
        Date one;
        Date two;
       
        long day = 0;
        
        try {
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
            
         
            one=sdf.parse(sdf.format(df.parse(str1)));
            two=sdf.parse(sdf.format(df.parse(str2)));
          
            
            long time1 = one.getTime();
            long time2 = two.getTime();
            
            if(time1==time2){
            	return "0";
            }
            
             
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
           
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String result = ""; 
		if(day>0)
			result = "+"+ day + "天";
		
		return result;
    }
	
	
	
	
}
