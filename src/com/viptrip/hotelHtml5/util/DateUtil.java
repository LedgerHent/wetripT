package com.viptrip.hotelHtml5.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类
 */
public class DateUtil {
	
	public static final String PATTERN_DATETIME_STANRD = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_DATE_STANRD = "yyyy-MM-dd";
	public static final String PATTERN_TIME_STANRD = "HH:mm:ss";
	public static final String PATTERN_TIME_STANRD_2 = "HH:mm";

    /**
     * 将日期格式化为指定pattern的字符串 ，如果指定pattern不存在，使用默认pattern，格式为:
     *  "yyyy-MM-dd hh:mm:ss"
     */
    public static String format(Date date, String pattern) {
    	if (date == null) {
    		return null;
    	}
    	
    	String usePattern = null;
        try {
        	if (StringUtils.isNotEmpty(pattern)) {
        		usePattern = pattern;    		
        	} else {
        		usePattern = PATTERN_DATETIME_STANRD;
        	}
        	SimpleDateFormat sdf = new SimpleDateFormat(usePattern);
        	return sdf.format(date);
        } catch (Exception e) {
        	return null;
        }
    }
    
    /**
     * 将日期格式化为"yyyy-MM-dd"形式的字符串
     */
    public static String formatDate(Date date) {
    	return format(date, PATTERN_DATE_STANRD);
    }
    
    /**
     * 将日期格式化为"HH:mm:ss"形式的字符串
     */
    public static String formatTime(Date date) {
    	return format(date, PATTERN_TIME_STANRD);
    }

    /**
     * 根据指定Pattern解析字符串中的日期 ，如果指定pattern不存在，使用默认pattern，格式为:
     * "yyyy-MM-dd hh:mm:ss"
     */
    public static Date parse(String dateStr, String pattern) {
    	if (dateStr == null) {
    		return null;
    	}
    	
    	String usePattern = null;
        try {
        	dateStr = dateStr.trim();
        	if (StringUtils.isNotEmpty(pattern)) {
        		usePattern = pattern;        		
        	} else if (dateStr.length() == 10) {
        		usePattern = PATTERN_DATE_STANRD;
        	} else if (dateStr.length() == 8) {
        		usePattern = PATTERN_TIME_STANRD;
        	} else {
        		usePattern = PATTERN_DATETIME_STANRD;
        	}
        	SimpleDateFormat sdf = new SimpleDateFormat(usePattern);
        	return sdf.parse(dateStr);
        } catch (Exception e) {
        	return null;
        }
    }
    
    /**
     * 将指定Pattern为"yyyy-MM-dd"字符串解析为Date类型
     */
    public static Date parseDate(String dateStr) {
    	return parse(dateStr, PATTERN_DATE_STANRD);
    }
    
    /**
     * 将指定Pattern为"HH:mm:ss"字符串解析为Date类型
     */
    public static Date parseTime(String dateStr) {
    	return parse(dateStr, PATTERN_TIME_STANRD);
    }

    /**
     * 取本月第一天
     */
    public static String getFirstDayOfMonth() {
    	SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_STANRD);
    	Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
        String firstDay = sdf.format(c.getTime());
        return firstDay;
    }
    
    /**
     * 取得当前日期 new Date();
     */
    public static Date getSysDate() {
        return new Date();
    }
    
    /**
     * 取得当前时间的字符串形式:HH:mm:ss
     */
    public static String getCurTime() {
    	return format(new Date(), "HH:mm:ss");
    }

    public static Date parseCSTDate(String s) {
        try {
        	SimpleDateFormat simples = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.US);
            Date date = simples.parse(s);
            return date;
        } catch (Exception e) {
            return null;
        }
    }
    
   /**
    * 转换日期和时间为指定格式的字符串
    * 用例： format(new Now(), "yyyy-MM-dd")
    *     format(new Now(), "yyyy-MM")
    *     format(new Now(), "yyyy-MM-dd hh:mm:ss")
    */
    public static String format(Calendar c, String format) {
        return format(c == null ? (Date) null : c.getTime(), format);
    }

    /**
     * 例如：addDate(new Date(), UserDate.DAY, 10) -- 在当前日期上加10天
     */
    public static Date addDate(Date date, int field, int amount) {
    	GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, amount);
        return gc.getTime();
    }

    /**
     * 返回 d1 - d2 之间相差的月份数
     */
    public static int monthsBetween(Date d1, Date d2) {
    	Calendar cal1 = new GregorianCalendar();
    	cal1.setTime(d1);
    	Calendar cal2 = new GregorianCalendar();
    	cal2.setTime(d2);
    	int c =
    	(cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + 
    	(cal1.get(Calendar.MONTH)- cal2.get(Calendar.MONTH));
    	return c;
    }

    /*
    * 返回 d1 - d2 之间相差的天数
    */
    public static int daysBetween(Date d1, Date d2) {
    	Calendar cal = Calendar.getInstance();  
        cal.setTime(d1);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(d2);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);  
          
       return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 判断当前时间是否在指定时间date之前,指定时间以字符串形式需要解析，
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static boolean isAfterNow(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATETIME_STANRD);
            return isAfterNow(sdf.parse(dateStr));
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * 判断当前时间是否在指定时间date之前
     */
    public static boolean isAfterNow(Date date) {
        try {
            Date now = new Date();
            return now.before(date);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据日历字段(Calendar.YEAR, Calendar.MONTH...etc)和相应增量（正数为加负数为减）修改指定日期
     * @param d
     * @param type
     * @param number
     * @return
     */
    public static Date incrDateByFieldType(Date d, String type, int number){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(d);
    	switch(type) {
    	case "YEAR":
    		calendar.add(Calendar.YEAR, number);
    		break;
    	case "MONTH":
    		calendar.add(Calendar.MONTH, number);
    		break;
    	case "DAY":
    		calendar.add(Calendar.DAY_OF_MONTH,number);
    		break;
    	case "HOUR": 
    		calendar.add(Calendar.HOUR_OF_DAY,number);
    		break;
    	case "MINUTE": 
    		calendar.add(Calendar.MINUTE,number);
    		break;
    	default:
    		break;
    	}
    	
    	return calendar.getTime();
    }

    /**
     * 获取中文周名称
     * @param date
     * @return
     * zhangxw
     */
    public static String getCNWeekName(Date date) {
    	if (date == null) return null;
    	
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String[] weeksCN = new String[]{"周六", "周日", "周一", "周二", "周三", "周四", "周五"}; 
        int day = c.get(Calendar.DAY_OF_WEEK);
        return weeksCN[day % 7];
    }
    
    /**
     * 得到日期的星期数字[周一:1,周二：2,...周日：7]
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date) {
    	if (date == null) {
    		return 0;
    	}
    	int[] days = new int[]{6, 7, 1, 2, 3, 4, 5};
    	Calendar c = Calendar.getInstance();
        c.setTime(date);
       
        return days[c.get(Calendar.DAY_OF_WEEK) % 7];
    }

	/**
	 * 当前日期下一天转换成YYYY-MM-DD形式
	 */
	public static String getNextDay(){
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * long 时间 转成 seccod（秒）
	 * @param time
	 * @return
	 */
	public static int convertMills2Second(long time){
		if(time <= 0) {
			return 0;
		}
		
		return new Long(time / 1000).intValue();
	}
	
	/**
     * 返回日期的 "yyyy-MM-dd"形式
     */
    public static String toDateString(Date d) {
        if (d== null) {
        	return null;
        }
        
        try {
        	SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DATE_STANRD);
            return sdf.format(d);
        } catch (Exception e) {
        	return null;
        }
    }
    
    /**
     * 补充日期到参数日期的午夜(23:59:59)
     * 
     * @return
     */
    public static final Date patchDateToMidnight(Date src) {
        if (src == null) {
            return src;
        } 

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(src);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    
    /**
     * 判断是否周末
     */
    public static boolean isWeekend(Calendar theDay) {
        int dayOfWeek = theDay.get(Calendar.DAY_OF_WEEK);

        return (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY);
    }
    
    /**
     * 获取yyyy-MM-dd 格式当前日期
     * @return
     */
    public static String getCurDateStr(){
    	return formatDate(getSysDate());
    }
    
    /**
     * 获取当前时间 HH:mm:ss SSS000
     * @return
     */
    public static String getCurTimeStr(){
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss SSS000");
    	String curTimeStr = sdf.format(getSysDate());
    	return curTimeStr;
    }
    
    /**
     * 将日期格式化为"HH:mm"形式的字符串
     */
    public static String formatTime2(Date date) {
    	return format(date, PATTERN_TIME_STANRD_2);
    }
    
	/**
	 * 获取年龄
	 * @param birthday
	 * @return
	 */
	public static int getAgeByBirth(Date birthday) {
		int age = 0;
		if(birthday == null){
			return age;
		}
		try {
			Calendar now = Calendar.getInstance();
			now.setTime(getSysDate());	// 当前时间
			Calendar birth = Calendar.getInstance();
			birth.setTime(birthday);
			if (birth.after(now)) {
				
			} else {
				age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
				if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
					age += 1;
				}
			}
			return age;
		} catch (Exception e) {
			return age;
		}
	}
    
}
