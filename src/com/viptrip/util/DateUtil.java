package com.viptrip.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * String 默认日期格式 yyyyMMdd
	 */
	public static final String P_DEFAULT = "yyyyMMdd";
	/**
	 * String 日期格式 yyyy MM dd
	 */
	public static final String P_yyyy_MM_dd = "yyyy MM dd";
	/**
	 * String 日期格式 yyyyMMdd
	 */
	public static final String P_yyyyMMdd = "yyyyMMdd";
	/**
	 * String 日期格式 yyyyMMddHHmmss
	 */
	public static final String P_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	/**
	 * String 日期格式 yyyyMMdd HHmmss
	 */
	public static final String P_yyyyMM_ddHHmmss = "yyyyMMdd HHmmss";
	/**
	 * String 日期格式 yyyy-MM-dd
	 */
	public static final String P_yyyyOMMOdd = "yyyy-MM-dd";
	/**
	 * String 【数据库用】默认日期时间格式 yyyy-MM-dd hh24:mi:ss
	 */
	public static final String DefaultDateTimeFormat4DB = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * String 默认日期时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DefaultDateTimeFormat = "yyyy-MM-dd HH:mm:ss";

	/**
	 * SimpleDateFormat 默认日期格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final SimpleDateFormat SDF_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat(
			DefaultDateTimeFormat);

	/**
	 * SimpleDateFormat 默认日期格式 yyyyMMdd
	 */
	public static final SimpleDateFormat SDF_DEFAULT = new SimpleDateFormat(
			"yyyyMMdd");
	/**
	 * SimpleDateFormat 日期格式 yyyy MM dd
	 */
	public static final SimpleDateFormat SDF_yyyy_MM_dd = new SimpleDateFormat(
			"yyyy MM dd");
	/**
	 * SimpleDateFormat 日期格式 yyyyMMdd
	 */
	public static final SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	/**
	 * SimpleDateFormat 日期格式 yyyyMMddHHmmss
	 */
	public static final SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	/**
	 * SimpleDateFormat 日期格式 yyyyMMdd HHmmss
	 */
	public static final SimpleDateFormat SDF_yyyyMM_ddHHmmss = new SimpleDateFormat(
			"yyyyMM ddHHmmss");
	/**
	 * SimpleDateFormat 日期格式 yyyy-MM-dd
	 */
	public static final SimpleDateFormat SDF_yyyyOMMOdd = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 时间的最大值
	 */
	public static final String TIME_MAX = "23:59:59";
	/**
	 * 时间的最小值
	 */
	public static final String TIME_MIN = "00:00:00";
	/**
	 * 夜间开始时间
	 */
	public static final String NIGHT_BEGIN = "20:00";
	/**
	 *夜间结束时间
	 */
	public static final String NIGHT_END = "8:00";

    /**
     * 当前系统时间
     */
    public static Date getSysDate() {
        return new Date();
    }
	/**
	 * 用默认yyyyMMdd格式日期为字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return null == date ? null : format(date, SDF_DEFAULT);
	}

	/**
	 * 格式化日期为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return StringUtil.isEmpty(pattern) ? format(date, SDF_DEFAULT)
				: format(date, new SimpleDateFormat(pattern));
	}

	/**
	 * 格式化日期为字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, SimpleDateFormat pattern) {
		pattern = (null == pattern ? SDF_DEFAULT : pattern);
		return null == date ? null : pattern.format(date);
	}

	/**
	 * 用默认格式yyyyMMdd将字符串转化为日期
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date) throws ParseException {
		return parse(date, SDF_DEFAULT);
	}

	/**
	 * 将字符串转化为日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, String pattern) throws ParseException {
		return StringUtil.isEmpty(pattern) ? parse(date, SDF_DEFAULT) : parse(
				date, new SimpleDateFormat(pattern));
	}

	/**
	 * 将字符串转化为日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String date, SimpleDateFormat pattern)
			throws ParseException {
		pattern = (null == pattern ? SDF_DEFAULT : pattern);
		return StringUtil.isEmpty(date) ? null : pattern.parse(date);
	}
	 /**
	  * 查询当前时间的前后几分钟的时间
	  * @param num 分钟数
	  * @return String 类型的时间
	  */
	 public static String getString_AddMinutes(SimpleDateFormat sdf,String dataStr,int minutes){
		 String aDataStr=null;
		 try {
			 Date date = sdf.parse(dataStr);
			 aDataStr=getString_AddMinutes(sdf,date,minutes);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return aDataStr;
	 }
	 
	 /**
	  * 查询当前时间的前后几分钟的时间
	  * @param num 分钟数
	  * @return String 类型的时间
	  */
	 public static String getString_AddMinutes(SimpleDateFormat sdf,Date date,int minutes){
		 String aDataStr=null;
		 try {
			 date.setTime(date.getTime()+minutes*60*1000);
			 aDataStr=sdf.format(date);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return aDataStr;
	 }
	 
	 
	 
	 /**
	  * 查询当前时间的前后几分钟的时间
	  * @param num
	  * @return Date 类型的时间
	  */
	 public static Date getDate_AddMinutes(SimpleDateFormat sdf,String dataStr,int minutes){
		 try {
			 Date date = sdf.parse(dataStr);
			 return getDate_AddMinutes(date,minutes);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return null;
	 }
	 
	 /**
	  * 查询当前时间的前后几分钟的时间
	  * @param num
	  * @return Date 类型的时间
	  */
	 public static Date getDate_AddMinutes(Date date,int minutes){
		 if(date!=null){
			 date.setTime(date.getTime()+minutes*60*1000);
			 return date;
		 }
		 return null;
	 }

	/**
	 * 将XMLGregorianCalendar格式时间转成java.util.Date时间
	 * 
	 * @param xc
	 * @return
	 */
	public static Date convert(XMLGregorianCalendar xc) {
		Date result = null;
		if (null != xc) {
			int year = xc.getYear();
			int month = xc.getMonth();
			int day = xc.getDay();
			int hour = xc.getHour();
			int minute = xc.getMinute();
			int second = xc.getSecond();
			String dateStr = "" + year
					+ (month < 10 ? ("0" + month) : (month + ""))
					+ (day < 10 ? ("0" + day) : (day + ""))
					+ (hour < 10 ? ("0" + hour) : (hour + ""))
					+ (minute < 10 ? ("0" + minute) : (minute + ""))
					+ (second < 10 ? ("0" + second) : (second + ""));
			try {
				result = SDF_yyyyMMddHHmmss.parse(dateStr);
			} catch (ParseException e) {
				logger.error(StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}

	/**
	 * java.util.Data --> java.sql.Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp Date2Timestamp(Date date) {
		return new Timestamp(date.getTime());
	}
    /**
     * 将日期转成字符串
     * 
     * @param Date
     *            date
     * @param String
     *            [] patterns
     * @return String
     */
    public static String date2Str(Date date, String pattern) {
        // return DateFormatUtils.format(date, pattern);
        try {
            if (date != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                return formatter.format(date);
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 把日期转换为某格式，参数为字符串类型
     * 
     * @param strDate
     * @return
     * 
     */
    public static String strToDateEng(String sDate, String pattern) {
        String m = null;
        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date dDate = str2Date(sDate, pattern);// "yyyyMMdd");
        m = df.format(dDate);
        return m;
    }
    
	/**
	 * 把日期转换为某格式，参数为字符串类型
	 * 把yyyyMMdd格式的日期字符串，转换为传入参数格式的字符串
	 * @param strDate 
	 * @return
	 * 
	 */
	public static String strToDateEng(String sDate, String pattern1, String pattern2) {
        String m = null;
        DateFormat df = new SimpleDateFormat(pattern2, Locale.ENGLISH);
        Date dDate = str2Date(sDate, pattern1);
        m = df.format(dDate);
        return m;
    }
    /**
     * 把日期转换为某格式
     * 
     * @param strDate
     * @return
     * 
     */
    public static String strToDateEng(Date dDate, String pattern) {
        String m = null;
        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
        m = df.format(dDate);
        return m;
    }
    
	/**
	 * 日期字符串按pattern中的格式转换为日期，针对英文月份的情况
	 * @param sDate
	 * @param pattern
	 * @return
	 */
	public static Date strToDateENG(String sDate, String pattern) {
		Date m = null;
        DateFormat df = new SimpleDateFormat(pattern, Locale.ENGLISH);
       
        try {
			m = df.parse(sDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return m;
    }
	/**
	 * 取当前时间之后的一个时间
	 * @param intHour
	 * @return
	 */
	public static String getHoursAfterTime(int intHour) {
        String HoursAfterTime = "";
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8"); 
		TimeZone.setDefault(tz); 
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + intHour);//设置时间为当前时间+传入参数的小时
        HoursAfterTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
        return HoursAfterTime;
    } 
	/**
	 * 获取与指定日期间隔若干天的日期
	 * @param adate
	 * @param iDay  传入负数即是之前，传入正数即是之后
	 * @return
	 */
	public static Date calcDate(Date adate, int iDay) {
		TimeZone tz = TimeZone.getTimeZone("ETC/GMT-8"); 
		TimeZone.setDefault(tz); 
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(adate); 
		calendar.add(Calendar.DATE, iDay);
		Date returnDate = calendar.getTime();
		return returnDate;
	}
    
    /**
     * 将字符串转成日期
     * 
     * @param String
     *            str
     * @param String
     *            pattern
     * @return Date
     */
    public static Date str2Date(String str, String pattern) {
        try {
            return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[] { pattern });
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 把日期转换为某格式，参数为字符串类型
     * 
     * @param strDate
     * @return
     * 
     */
    public static Date strToDate(String sDate, String pattern) {
        Date m = null;
        DateFormat df = new SimpleDateFormat(pattern);

        try {
            m = df.parse(sDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return m;
    }

 // 根据日期取得星期几
    public static String getWeek(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE", Locale.CHINESE);
        String week = sdf.format(date);
        if (week.equals("星期一")) {
            week = "周一";
        } else if (week.equals("星期二")) {
            week = "周二";
        } else if (week.equals("星期三")) {
            week = "周三";
        } else if (week.equals("星期四")) {
            week = "周四";
        } else if (week.equals("星期五")) {
            week = "周五";
        } else if (week.equals("星期六")) {
            week = "周六";
        } else if (week.equals("星期日")) {
            week = "周日";
        }
        return week;
    }
    /**
     * @描述: 计算两个日期差的分钟数
     * 
     * @日期：2017-11-11 上午11:03:17
     * @param startTime
     * @param endTime
     * @param pattern
     * @return
     * @return long
     */
    public static long getDiffMinute(String startTime, String endTime, String pattern) {
    	long nd = 1000 * 60;// 一天的毫秒数
        // long nh = 1000 * 60 * 60;// 一小时的毫秒数
        // long nm = 1000 * 60;// 一分钟的毫秒数
        // long ns = 1000;// 一秒钟的毫秒数
        long diff;
        // 获得两个时间的毫秒时间差异
        diff = DateUtil.str2Date(endTime, pattern).getTime() - DateUtil.str2Date(startTime, pattern).getTime();
        long day = diff / nd;// 计算差多少分钟
        return day;
    }
    /**
     * @描述: 计算两个日期差的天数
     * 
     * @日期：2011-12-1 上午11:03:17
     * @param startTime 
     * @param endTime
     * @param pattern
     * @return
     * @return long
     */
    public static long getDiffDay(String startTime, String endTime, String pattern) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        // long nh = 1000 * 60 * 60;// 一小时的毫秒数
        // long nm = 1000 * 60;// 一分钟的毫秒数
        // long ns = 1000;// 一秒钟的毫秒数
        long diff;
        // 获得两个时间的毫秒时间差异
        diff = DateUtil.str2Date(endTime, pattern).getTime() - DateUtil.str2Date(startTime, pattern).getTime();
        long day = diff / nd;// 计算差多少天
        // long hour = diff % nd / nh;// 计算差多少小时
        // long min = diff % nd % nh / nm;// 计算差多少分钟
        // long sec = diff % nd % nh % nm / ns;// 计算差多少秒
        // // 输出结果
        // //system.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟"
        // + sec + "秒。");
        return day;
    }
    
    /**
     * 计算前一个日期比后一个日期 的时间差  12:20 格式
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
				
				day = diff / (24 * 60 * 60 * 1000);
				hour = (diff / (60 * 60 * 1000) - day * 24);
				min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
				sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
				
				if(min>=0)
					result +=  (min<10?"0":"") + min + ":";
				if(sec>=0)
					result +=  (sec<10?"0":"") + sec ;
			} else {  
				result = "00:00";
			}  
			
		}
		return result==""?"00:00":result;
	}  
    /**
     * 返回值类似为2小时20分钟
     * @param diff
     * @return
     */
    public static String getDiffTimeStr(long diff) {

        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
//        // long ns = 1000;// 一秒钟的毫秒数
//        long diff;
//        // 获得两个时间的毫秒时间差异
//        diff = endTime.getTime() - startTime.getTime();
        long day = diff / nd;// 计算差多少天
        long hour = diff % nd / nh;// 计算差多少小时
        long min = diff % nd % nh / nm;// 计算差多少分钟
        // long sec = diff % nd % nh % nm / ns;// 计算差多少秒
        // // 输出结果
        String strdiff = hour + "h" + min + "m";
        // system.out.println("时间相差：" + hour + "小时" + min + "分钟");
        // + sec + "秒。");
        return strdiff;
    }
    
    /**
     * 计算倒计时  12:20 格式
     * @param d1
     * @param d2
     * @return
     */
    public static String getCountDownTimeStr(Date d,long countDownSecs) {  
    	String result = ""; 
    	if(null!=d){
    		long day = 0;
    		long hour = 0;
    		long min = 0;
    		long sec = 0;
    		
    		long time1 = d.getTime() + countDownSecs*1000;
    		long now = new Date().getTime();
    		long diff ;
    		if(time1>=now) {  
    			diff = time1 - now;
    			day = diff / (24 * 60 * 60 * 1000);
    			hour = (diff / (60 * 60 * 1000) - day * 24);
    			min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
    			sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
    			
    			if(min>=0)
    				result +=  (min<10?"0":"") + min + ":";
    			if(sec>=0)
    				result +=  (sec<10?"0":"") + sec ;
    		} else {  
    			result = "00:00";
    		}  
    		
    	}
    	return result==""?"00:00":result;
    }  
    
    
    /**
     * 计算当前月份n年后的月份的第一天
     * @param dateStr "yyyy-MM-dd"
     * @param integrationOverdue 年数（如：n年前=-n   n年后=n）
     * @return
     */
    public static String getLaterNyearDate(String dateStr,int integrationOverdue) {
        try {
        	Date date=str2Date(dateStr, "yyyy-MM-dd");
        	Calendar calendar = Calendar.getInstance();
        	calendar.setTime(date);
        	calendar.add(Calendar.YEAR, integrationOverdue);
            calendar.set(Calendar.DATE, 1);     //设置为该月第一天
            //calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        	date = calendar.getTime();
            dateStr=date2Str(date, "yyyy-MM-dd");
        	return dateStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
        
    }
    /**
     * 判断是否是夜间
     * @param time
     * @return
     */
    public static boolean isNight(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String currentDate = "";
        try {
			currentDate = sdf.format(sdf.parse(time)).split(":")[0] + sdf.format(sdf.parse(time)).split(":")[1];// 當前時間
		} catch (ParseException e) {
			e.printStackTrace();
		}
        String beginDate = DateUtil.NIGHT_BEGIN;
        String endDate = DateUtil.NIGHT_END;
        int begin = Integer.valueOf(beginDate.split(":")[0] + beginDate.split(":")[1]);
        int end = Integer.valueOf(endDate.split(":")[0] + endDate.split(":")[1]);

        boolean is = false;
        int hour = Integer.valueOf(currentDate);

        if (hour >= begin || hour < end) {
            is = true;// System.out.println("晚班"+hour);
        } else {
            is = false;// System.out.println("早班"+hour);
        }
        return is;
	}
    /**
     * 
     * @param datestr yyyy-MM-dd HH:mm:ss
     * @return 08Dec
     */
    public static String getDDMM(String datestr){
        Date date=DateUtil.str2Date(datestr, "yyyy-MM-dd HH:mm:ss");
        return date.toString().split(" ")[2]+""+date.toString().split(" ")[1];
    }

    /**
     * 取日期sDate与eDate之间相差的毫秒数
     * 
     * @param sDate
     * @param eDate
     * @return
     */
    public static long getDiffMs(Date sDate, Date eDate) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long diff;
        diff = eDate.getTime() - sDate.getTime();
        return diff;
    }
    
	public static void main(String[] args) {
//		String format = SDF_yyyyMMdd.format();
//		System.out.println(format);
	}
}
