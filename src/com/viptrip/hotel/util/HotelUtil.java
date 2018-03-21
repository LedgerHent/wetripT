package com.viptrip.hotel.util;

import java.util.Map;

import com.viptrip.hotel.controller.PayCallBack;
import com.viptrip.hotel.model.Request_PayCallBack;
import com.viptrip.hotel.model.Request_SmsCallBack;
import com.viptrip.hotel.model.page.Page;
import com.viptrip.hotel.model.payCallBack.PayCallBack_Data;
import com.viptrip.util.JSON;

public class HotelUtil {
	
	public static boolean isUser(Integer userId){
		if(userId!= null&&userId > 0){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isOrg(Integer orgId){
		if(orgId!= null&&orgId > -1){
			return true;
		}else{
			return false;
		}
	}
	public static boolean isPage(Page page){
		if(page!= null){
			if(page.index != null && page.index > 0 && page.size > 0 && page.size >0){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * NULL字符串转换，参数对象为null时，返回空字符串
	 * @param o 转换原对象
	 * @return 字符串
	 */
	public static String nvl(Object o) {
		if (o == null) {
			return "";
		}
		return o.toString().trim();
	}
	
	/**
	 * 获取加密后的参数字符串
	 * @param map 参数map
	 * @return
	 */
	public static String getEncryptParamStr(Request_SmsCallBack message){
		return new PayCallBack().encryptParam(JSON.get().toJson(message));
	}
}
