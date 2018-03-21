package com.viptrip.warning.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.viptrip.util.StringUtil;
import com.viptrip.warning.entity.PayWarningPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.warning.resource.C;
import com.viptrip.warning.resource.MEnum.WarningMethod;
/**
 * 临额提醒全局配置工具类
 * @author selfwhisper
 *
 */
public class GlobalWarningUtil {

	private final static Logger log =  (Logger) LoggerFactory.getLogger(GlobalWarningUtil.class);
	
	private static final Properties GLOBALWARNING_MAP ;
	
	static{
		Properties map = null;
		try {
			map = PropertiesUtils.loadFile(C.FILE_WARNING_CONFIG);
		} catch (IOException e) {
			log.error("加载临额提醒全局配置文件异常\r\n" + StringUtil.getExceptionStr(e));
			map = new Properties();
		}
		GLOBALWARNING_MAP = map;
	}
	
	/**
	 * 设置全局通知开关
	 * @param status
	 */
	public synchronized static void setGlobalWarningStatus(Integer status){
		if(null!=status){
			GLOBALWARNING_MAP.setProperty(C.PRO_GLOBAL_WARNING_STATUS, status.intValue() + "");
			refreshConfigFile();
		}
	}

	/**
	 * 设置是否为测试
	 * @param status
	 */
	public synchronized  static void setTest(Integer status){
		if(null!=status){
			GLOBALWARNING_MAP.setProperty(C.PRO_GLOBAL_WARNING_ISTEST,status.intValue()+"");
			refreshConfigFile();
		}
	}

	/**
	 * 是否为测试
	 * @return
	 */
	public static boolean isTest(){
		return  "1".equals(GLOBALWARNING_MAP.getProperty(C.PRO_GLOBAL_WARNING_ISTEST));
	}

	/**
	 * 获取测试用户
	 * @return
	 */
	public static List<PayWarningPerson> getTestPersonList(Long orgid){
		List<PayWarningPerson> result = null;
		String testUserStr = GLOBALWARNING_MAP.getProperty(C.PRO_GLOBAL_WARNING_TESTUSER);
		if(StringUtil.isNotEmpty(testUserStr)){
			String[] users = testUserStr.split(";");
			if(null!=users && users.length>0){
				result = new ArrayList<>();
				for(String user:users){
					if(StringUtil.isNotEmpty(user)){
						String[] fs = user.split(",");
						if(null!=fs && fs.length>0){
							PayWarningPerson p = new PayWarningPerson();
							p.setMobile(fs[0]);
							p.setEmail(fs[1]);
							p.setOrgid(orgid);
							p.setMethod(WarningMethod.短信和邮件.code());
							result.add(p);
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * 设置全局通知值
	 * @param val
	 */
	public synchronized static void setGlobalWarningVal(Double val){
		if(null!=val){
			GLOBALWARNING_MAP.setProperty(C.PRO_GLOBAL_WARNING_VAL, val.doubleValue() + "");
			refreshConfigFile();
		}
	}
	/**
	 * 设置全局通知内容
	 * @param val
	 */
	public synchronized static void setGlobalWarningText(String val){
		if(StringUtil.isNotEmpty(val)){
			GLOBALWARNING_MAP.setProperty(C.PRO_GLOBAL_WARNING_TEXT, val);
			refreshConfigFile();
		}
	}
	
	/**
	 * 设置后台通知模板
	 * @param content
	 * @param method
	 */
	public synchronized static void setWarningtplBG(String content,WarningMethod method){
		if(null!=method&&StringUtil.isNotEmpty(content)){
			switch (method) {
			case 短信:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS_BG, content);
				break;
			case 邮件:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT_BG, content);
				break;
			case 短信和邮件:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS_BG, content);
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT_BG, content);
				break;
			}
		}else if(null==method){
			GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS_BG, content);
			GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT_BG, content);
		}
		refreshConfigFile();
	}
	
	/**
	 * 设置企业通知模板
	 * @param content
	 * @param method 
	 */
	public synchronized static void setWarningtpl(String content,WarningMethod method){
		if(null!=method&&StringUtil.isNotEmpty(content)){
			switch (method) {
			case 短信:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS, content);
				break;
			case 邮件:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT, content);
				break;
			case 短信和邮件:
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS, content);
				GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT, content);
				break;
			}
		}else if(null==method){
			GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_SMS, content);
			GLOBALWARNING_MAP.setProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT, content);
		}
		refreshConfigFile();
	}
	
	/**
	 * 获取全局通知开关状态
	 * @return
	 */
	public static Integer getGlobalWarningStatus(){
		Integer result = null;
		String property = GLOBALWARNING_MAP.getProperty(C.PRO_GLOBAL_WARNING_STATUS);
		if(StringUtil.isNotEmpty(property)){
			try {
				result = Integer.parseInt(property);
			} catch (Exception e) {
				log.error("获取临额提醒全局开关状态异常\r\n" + StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}
	
	/**
	 * 获取全局通知值
	 * @return
	 */
	public static Double getGlobalWarningVal(){
		Double result = null;
		String property = GLOBALWARNING_MAP.getProperty(C.PRO_GLOBAL_WARNING_VAL);
		if(StringUtil.isNotEmpty(property)){
			try {
				result = Double.parseDouble(property);
			} catch (Exception e) {
				log.error("获取临额提醒全局值异常\r\n" + StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}
	/**
	 * 获取全局通知内容
	 * @param isBG 是否为后台用户
	 * @return
	 */
	public static String getGlobalWarningText(boolean isBG){
		return  GLOBALWARNING_MAP.getProperty(isBG?C.PRO_GLOBAL_WARNING_TEXT_BG:C.PRO_GLOBAL_WARNING_TEXT);
	}
	
	/**
	 * 获取后台通知模板
	 * @param method 短信或邮件
	 * @return
	 */
	public static String getWarningTplBG(WarningMethod method){
		String result = null;
		if(null!=method){
			switch (method) {
			case 短信:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS_BG);
				break;
			case 邮件:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT_BG);
				break;
			default:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS_BG);
				break;
			}
		}else{
			result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS_BG);
		}
		return result;
	}
	
	/**
	 * 获取企业通知模板
	 * @param method 短信或邮件
	 * @return
	 */
	public static String getWarningTplContent(WarningMethod method){
		String result = null;
		if(null!=method){
			switch (method) {
			case 短信:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS);
				break;
			case 邮件:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_EMAIL_CONTENT);
				break;
			default:
				result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS);
				break;
			}
		}else{
			result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_SMS);
		}
		return result;
	}

	/**
	 * 获取邮件通知标题
	 * @param isEnterprisePerson 是否为企业通知用户
	 * @return
	 */
	public static String getEmailTitle(boolean isEnterprisePerson){
		String result = null;
		if(isEnterprisePerson){
			result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_EMAIL_TITLE);
		}else
			result = GLOBALWARNING_MAP.getProperty(C.PRO_WARNING_TPL_EMAIL_TITLE_BG);
		return result;
	}
	
	
	/**
	 * 刷新配置文件
	 */
	public synchronized static void refreshConfigFile(){
		try {
			PropertiesUtils.writeFile(C.FILE_WARNING_CONFIG, GLOBALWARNING_MAP);
		} catch (IOException e) {
			log.error("刷新临额提醒全局配置文件异常\r\n" + StringUtil.getExceptionStr(e));
		}
	}

	/**
	 * 获取预付款页面提示
	 * @return
	 */
	public synchronized static String getPageTipPre(){
		return GLOBALWARNING_MAP.getProperty(C.PRO_PAGE_TIP_PRE);
	}

	/**
	 * 获取月结页面提示
	 * @return
	 */
	public synchronized static String getPageTipMon(){
		return GLOBALWARNING_MAP.getProperty(C.PRO_PAGE_TIP_MON);
	}

	/**
	 * 获取财务以及客服邮件地址
	 * @return
	 */
	public static List<String> getEmailAddrBG(){
		List<String> result = null;
		String str = GLOBALWARNING_MAP.getProperty(C.PRO_EMAIL_ADDR_BG);
		if(StringUtil.isNotEmpty(str)){
			String[] strs = str.split(",");
			if (null!=strs && strs.length>0){
				result = new ArrayList<>();
				String regx = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
				for (String email:strs){
					if (regxVerify(regx,email))
						result.add(email);
				}
			}
		}
		return  result;
	}


	/**
	 * 用正则表达式验证
	 * @param regx 正则表达式
	 * @param val 内容
	 * @return 是否通过
	 */
	public static boolean regxVerify(String regx,String val){
		boolean result = false;
		if(StringUtil.isNotEmpty(regx) && StringUtil.isNotEmpty(val)){
			Pattern regex = Pattern.compile(regx);
			Matcher matcher = regex.matcher(val);
			result = matcher.matches();
		}
		return result;
	}


	/**
	 * 获取短信usekey
	 * @return
	 */
	public static String getUserKey(){
		return  GLOBALWARNING_MAP.getProperty(C.PRO_USER_KEY);
	}


	/**
	 * 获取开始结算的日期
	 * @return
	 */
	public static Date getStartSettleDate(){
		Date result = null;
		String dateStr = GLOBALWARNING_MAP.getProperty(C.PRO_SETTLEDATE);
		String pattern = GLOBALWARNING_MAP.getProperty(C.PRO_DATEPATTERN);
		if(!StringUtil.isNotEmpty(dateStr) && !StringUtil.isNotEmpty(pattern)){
			try {
				result = new SimpleDateFormat(pattern).parse(dateStr);
			} catch (ParseException e) {
				log.error(StringUtil.getExceptionStr(e));
			}
		}
		return result;
	}
}
