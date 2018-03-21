package com.viptrip.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import etuf.v1_0.common.Common;

public class RegUtil {

	/**
	 * 是否是有效的手机号
	 * 
	 * @param mobile
	 * @return
	 */
	public static boolean isValidMobile(String mobile) {
		return isNotNullOrEmptyAndMatchRegex(mobile,"^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0-9]))[0-9]{8}$");
	}
	
	private static boolean isNotNullOrEmptyAndMatchRegex(String str,String regex){
		if (!Common.IsNullOrEmpty(str)) {
			Pattern p = Pattern
					.compile(regex);
			Matcher m = p.matcher(str);
			return m.matches();
		}
		return false;
	}
	
	/**
	 * 是否是有效邮箱
	 * @param mobile
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		return isNotNullOrEmptyAndMatchRegex(email,"^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$");
	}
}
