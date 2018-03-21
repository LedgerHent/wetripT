package com.viptrip.hotelHtml5.util;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viptrip.resource.Const;
import com.viptrip.util.PropertiesUtils;


public class JhUtil {
	private static final Logger logger = LoggerFactory.getLogger(JhUtil.class);
	
	private static final String HOTEL_PREFIX = "hotelHtml5";


	public static <T> String getRequestString(T t) throws Exception{
		String json = JSON.toJSONString(t);
		String str = json+"&sign="+EncryptHelper.MD5Encrypt(json);
		logger.info(str);
		str = EncryptHelper.DESEncrypt(str);
		str = "data="+URLEncoder.encode(str,"utf-8");
		return str;
	}
	
	public static <T> T getResponseObject(String res,Class<T> t) throws Exception{
		res = URLDecoder.decode(res,"utf-8");
		res = EncryptHelper.DESDecrypt(res);
		logger.info(res);
		return JSONObject.parseObject(res.split("&")[0],t);
	}
	
	public static <T,Y> T sendReqJh(Y y,Class<T> t) throws Exception{
		String req = JhUtil.getRequestString(y);
		String url = PropertiesUtils.getProperty(HOTEL_PREFIX + "." + Const.PRO_SERVER_URL, Const.FILE_CONFIG);
		String res = HttpUtils.doPost(url, req, 30000, 30000);
		logger.info("asdasdasdasdasd:"+res);
		return JhUtil.getResponseObject(res, t);
	}
	
	
}
