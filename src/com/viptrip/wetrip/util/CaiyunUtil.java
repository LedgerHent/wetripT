package com.viptrip.wetrip.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.reflect.TypeToken;
import com.viptrip.resource.Const;
import com.viptrip.uban360.config.Config;
import com.viptrip.util.JSON;
import com.viptrip.util.PropertiesUtils;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.vo.CaiyunAccessToken;
import com.viptrip.wetrip.vo.CaiyunPayPara;
import com.viptrip.wetrip.vo.CaiyunPayParaExt;
import com.viptrip.wetrip.vo.CaiyunResp;

/**
 * 集团彩云接口访问工具
 * @author selfwhisper
 *
 */
public class CaiyunUtil {
	
	private static enum Inter{
		风控("风控"),订单回调("订单回调");
		private String name;
		
		private Inter(){
		}
		private Inter(String name){
			this.name = name;
		}
		public String val(){
			return this.name;
		}
		
	}
	
	private static Logger logger = LoggerFactory.getLogger(CaiyunUtil.class);
	
	private static final String baseUrl;
	private static final String appId;
	private static final String appSecret;
	private static final String platformId;
	
	private static final String path_accessToken;
	private static final String path_prePayCheck;
	private static final String path_orderCallback;
	
	static{
		String baseUrl1 = null;
		String appId1 = null;
		String appSecret1 = null;
		String platformId1 = null;
		String path_accessToken1 = null;
		String path_prePayCheck1 = null;
		String path_orderCallback1 = null;
		try {
			baseUrl1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_UBANBASEURL, Const.FILE_CAIYUN_CONFIG);
			appId1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_ID, Const.FILE_CAIYUN_CONFIG);
			appSecret1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_SECRET, Const.FILE_CAIYUN_CONFIG);
			platformId1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_PLATFORMID, Const.FILE_CAIYUN_CONFIG);
			path_accessToken1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_PATH_ACCESSTOKEN, Const.FILE_CAIYUN_CONFIG);
			path_prePayCheck1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_PATH_PREPAYCHECK, Const.FILE_CAIYUN_CONFIG);
			path_orderCallback1 = PropertiesUtils.getProperty(Const.PRO_CAIYUN_APP_PATH_ORDERCALLBACK, Const.FILE_CAIYUN_CONFIG);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		baseUrl = baseUrl1;
		appId = appId1;
		appSecret = appSecret1;
		platformId = platformId1;
		path_accessToken = path_accessToken1;
		path_prePayCheck = path_prePayCheck1;
		path_orderCallback = path_orderCallback1;
		Config.appId=appId;
		Config.appSecret=appSecret;
		Config.ubanBaseUrl=baseUrl;
		Config.platformId=Long.valueOf(platformId);
		
	}
	
	/**
	 * 访问获取access token
	 * @return
	 */
	@SuppressWarnings("serial")
	public static CaiyunAccessToken getToken(){
		CaiyunAccessToken token = null;
		String url = baseUrl + path_accessToken;
//		String url = baseUrl + path_accessToken + "?appId=" + appId + "&appSecret=" + appSecret;
		HttpRequest request = HttpRequest.get(url);
		Map<String, String> header = new HashMap<>();
		header.put("appId", appId);
		header.put("appSecret", appSecret);
		request.headers(header);
		request.contentType("application/x-www-form-urlencoded");
		String body = request.body();
		int code = request.code();
		if(logger.isDebugEnabled()){
			logger.debug("获取彩云集团accesstoken返回值为：" + body);
		}
		if(logger.isDebugEnabled()){
			logger.debug("获取彩云集团accesstoken返回状态为：" + code);
		}
		if(200==code){
			try {
				if(StringUtil.isNotEmpty(body)){
					CaiyunResp<CaiyunAccessToken> result = JSON.get().notEscapeHTML(true).fromJson(body, new TypeToken<CaiyunResp<CaiyunAccessToken>>(){}.getType());
					if(null!=result && null!=result.getStatus() && 0==result.getStatus()){
						token = result.getData();
						token.setCode(0);
						token.setMsg("成功");
					}else{
						token = new CaiyunAccessToken(1, result.getMessage(), null, null);
						logger.error("获取access_token失败，请求url为：" + url + "，返回信息为：" + body);
						logger.error("获取access_token失败，错误信息为：" + result.getMessage());
					}
				}else{
					token = new CaiyunAccessToken(1, "返回值为空", null, null);
					logger.error("获取access_token失败，请求url为：" + url + "，返回信息为空");
				}
			} catch (Exception e) {
				logger.error("获取access_token失败，请求url为：" + url + "，返回信息为：" + body);
				token = new CaiyunAccessToken(-1, "异常", null, null);
			}
		}
		return token;
	}
	
	/**
	 * 风控接口
	 * @param accessToken
	 * @param uid
	 * @param para
	 * @return
	 */
	public static CaiyunResp<?> prePayCheck(String accessToken,String uid,CaiyunPayPara para){
		String url = baseUrl + path_prePayCheck;
		HttpRequest request = HttpRequest.post(url);
		logger.info("调用彩云风控接口，接口地址为：" + url + ",参数accessToken→" + accessToken + ",uid→" + uid + ",para→" + JSON.get().notEscapeHTML(true).toJson(para));
		Map<String,String> header = new HashMap<String, String>();
		header.put("accessToken", accessToken);
		header.put("uid", uid);
		CaiyunResp<?> result = getCaiyunResult(request, header, para, Inter.风控);
		return result;
	}
	
	/**
	 * 订单回调接口
	 * @param accessToken
	 * @param uid
	 * @param para
	 * @return
	 */
	public static CaiyunResp<?> orderCallBack(String accessToken,String uid,CaiyunPayParaExt para){
		String url = baseUrl + path_orderCallback;
		logger.info("调用彩云订单回调接口，接口地址为：" + url + ",参数accessToken→" + accessToken + ",uid→" + uid + ",para→" + JSON.get().notEscapeHTML(true).toJson(para));
		HttpRequest request = HttpRequest.post(baseUrl + path_orderCallback );
		Map<String,String> header = new HashMap<String, String>();
		header.put("accessToken", accessToken);
		header.put("uid", uid);
//		String body = request.headers(header).form(getParaMap(para,null)).body();
//		logger.info("调用彩云订单回调接口，返回值为：" + body);
//		CaiyunResp<?> result = JSON.get().notEscapeHTML(true).fromJson(body,CaiyunResp.class);
		CaiyunResp<?> result = getCaiyunResult(request, header, para, Inter.订单回调);
		return result;
	}
	
	/*
	 * 彩云接口获取数据
	 * @param request
	 * @param header
	 * @param para
	 * @param flag
	 * @return
	 */
	private static CaiyunResp<?> getCaiyunResult(HttpRequest request,Map<String,String> header,Object para,Inter flag){
		CaiyunResp<?> result = null;
		boolean retryFlag = true;
		int count = 0;
		int retryCount = 2;
		while(retryFlag && retryCount>0){
			count ++;
			retryCount --;
			try {
				request.contentType("application/json");
				request.headers(header).send(JSON.get().toJson(getParaMap(para,null)));
//				request = request.headers(header).form(getParaMap(para,null));
				int code = request.code();
				String body = request.body();
				if(200==code){
					logger.info("调用讯盟" + flag.val() + "接口，状态码为：" + code + ",返回值为：" + body);
					result = JSON.get().notEscapeHTML(true).fromJson(body,CaiyunResp.class);
					retryFlag = false;
				}else{
					logger.info("调用讯盟" + flag.val() + "接口，状态码为：" + code + ",返回值为：" + body);
				}
			} catch (Exception e) {
				logger.error("调用讯盟" + flag.val() + "接口异常，异常内容为:\r\n" + StringUtil.getExceptionStr(e));
			}
		}
		if(null==result){
			result = new CaiyunResp<>();
			result.setMessage("调用讯盟" + flag.val() + "接口 " + count + "次，均发生异常，请检查网络");
		}
		return result;
	}
	
	/**
	 * 将对象转成map参数  格式为 属性名-属性值
	 * @param obj
	 * @param excludeFields
	 * @return
	 */
	private static Map<String,Object> getParaMap(Object obj,String[] excludeFields){
		Map<String,Object> map = new HashMap<String, Object>();
		if(null!=obj){
			Class<? extends Object> clz = obj.getClass();
			Field[] fields = clz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String fname = field.getName();
				if(null!=excludeFields && excludeFields.length>0){
					if(Arrays.asList(excludeFields).contains(fname))
						continue;
				}
				
				int mod = field.getModifiers();
				if(Modifier.isPublic(mod)&&Modifier.isStatic(mod)&&Modifier.isFinal(mod))//如果是public static final 跳过
					continue;
				
				String getterName = field.getType()==Boolean.class?"is":"get" + fname.substring(0,1).toUpperCase() + fname.substring(1);
				try {
					Method method = clz.getDeclaredMethod(getterName);
					Object val = method.invoke(obj);
					/*String v = val==null?null:val.toString();
					if(StringUtil.isEmpty(v)){
						v = null;
					}
					map.put(fname, v==null?null:v);*/
					map.put(fname, val);
				} catch (NoSuchMethodException e) {
					logger.error(StringUtil.getExceptionStr(e));
				} catch (SecurityException e) {
					logger.error(StringUtil.getExceptionStr(e));
				} catch (IllegalAccessException e) {
					logger.error(StringUtil.getExceptionStr(e));
				} catch (IllegalArgumentException e) {
					logger.error(StringUtil.getExceptionStr(e));
				} catch (InvocationTargetException e) {
					logger.error(StringUtil.getExceptionStr(e));
				}
			}
		}
		return map;
	} 
	
	public static void main(String[] args) {
		CaiyunPayParaExt paraExt = new CaiyunPayParaExt("123213", 2134, null, null);
		Map<String, Object> map = getParaMap(paraExt, null);
		System.out.println("CaiyunUtil.main()==map==" + JSON.get().notEscapeHTML(true).nullSerialize(true).toJson(map));
		
		try {
			JSON json = JSON.get();
			Method m = json.getClass().getDeclaredMethod("get");
			
			int modifiers = m.getModifiers();
			
			System.out.println("CaiyunUtil.main()==modifiers==" + modifiers);
			System.out.println("CaiyunUtil.main()==is public==" + Modifier.isPublic(modifiers));
			System.out.println("CaiyunUtil.main()==is static==" + Modifier.isStatic(modifiers));
		} catch ( SecurityException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
