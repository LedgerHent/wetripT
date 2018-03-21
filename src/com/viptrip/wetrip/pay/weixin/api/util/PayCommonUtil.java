package com.viptrip.wetrip.pay.weixin.api.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.util.MD5Util;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.pay.WxConfig;
import com.viptrip.wetrip.pay.weixin.api.bean.WXMwebReqBaseBean;

public class PayCommonUtil {
	
	private static Logger log = LoggerFactory.getLogger(PayCommonUtil.class);
	
	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isTenpaySign(String characterEncoding, SortedMap<String, Object> packageParams,
			String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator<?> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) String.valueOf(entry.getValue());
			if (!"sign".equals(k) && null != v && !"".equals(v)) {
				sb.append(k + "=" + v + "&");
			}
		}

		sb.append("key=" + API_KEY);

		// 算出摘要
		String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();
		String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();

		return tenpaySign.equals(mysign);
	}

	/**
	 * 创建签名
	 * @Description：sign签名
	 * @param characterEncoding
	 *            编码格式
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String createSign(String characterEncoding, SortedMap<String, Object> packageParams, String API_KEY) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) String.valueOf(entry.getValue());
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + API_KEY);
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
		return sign;
	}

	/**
	 * 获取请求xml字符串
	 * @Description：将请求参数map转换为xml格式的string
	 * @param parameters
	 *            请求参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String getRequestXml(SortedMap<String, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set<?> es = parameters.entrySet();
		Iterator<?> it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) String.valueOf(entry.getValue());
			if (!StringUtil.isEmpty(v) && entry.getValue().getClass() == String.class) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
/*			if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {
				sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");
			} else {
				sb.append("<" + k + ">" + v + "</" + k + ">");
			}
*/		}
		sb.append("</xml>");
		return sb.toString();
	}
	
	

	/**
	 * 将对象转换成xml字符串
	 * @param obj
	 * @return
	 */
	public static <T> String convertToXML(T obj){
		SortedMap<String, Object> params = getParam(obj);
		//判断签名类型 调用不同的签名方法
		String xml = PayCommonUtil.getRequestXml(params);
		return xml;
	}
	
	
	/**
	 * 获取xml格式请求字符串
	 * @param obj
	 * @return
	 */
	public static <T extends WXMwebReqBaseBean> String getPayReqXML(T obj){
		SortedMap<String, Object> params = getParam(obj);
		//判断签名类型 调用不同的签名方法
		String sign = PayCommonUtil.createSign(WxConfig.CHARSET.UTF8.val(), params, WxConfig.KEY);
		params.put("sign", sign);
		String xml = PayCommonUtil.getRequestXml(params);
		return xml;
	}
	
	/**
	 * 获取请求map
	 * @param obj
	 * @return
	 */
	public static <T extends WXMwebReqBaseBean> Map<String, Object> getReqMap(T obj){
		SortedMap<String, Object> params = getParam(obj);
		String sign = PayCommonUtil.createSign(WxConfig.CHARSET.UTF8.val(), params, WxConfig.KEY);
		params.put("sign", sign);
		return params;
	}
	
	/**
	 * 获取按照属性排序的 字段名-值 map
	 * @param obj
	 * @return
	 */
	public static <T> SortedMap<String, Object> getParam(T obj){
		SortedMap<String, Object> result = new TreeMap<>();
		Field[] fields = obj.getClass().getDeclaredFields();
		Field[] superField = obj.getClass().getSuperclass().getDeclaredFields();
		String[] fnames = new String[fields.length + superField.length];
		int i = 0;
		for (; i < fields.length; i++) {
			fnames[i] = fields[i].getName();
		}
		for(;i<fnames.length;i++){
			fnames[i] = superField[i-fields.length].getName();
		}
		Arrays.sort(fnames);
		for(String fname:fnames){
			String getterName = "get" + (fname.charAt(0)+"").toUpperCase() + fname.substring(1);
			Object value = null;
			try {
				Method method = obj.getClass().getMethod(getterName);
				value = method.invoke(obj);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			if(null!=value&&!"".equals(value)){
				result.put(fname, value);
			}
		}
		return result;
	}
	
	/**
	 * 组装跳转url 跳转url进行urlencoder
	 * @param url
	 * @param redirectURL
	 * @return
	 */
	public static String buildEncodeRedirectURL(String url,String redirectURL){
		if(!StringUtil.isEmpty(url) && !StringUtil.isEmpty(redirectURL)){
			try {
				url = url + (url.indexOf("?")>0?"&":"?") + "redirect_url=" + URLEncoder.encode(redirectURL,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error(StringUtil.getExceptionStr(e));
			}
		}
		return url;
	}
	

	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于32
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		if (length>=32){
			System.out.println("随机数出错");
			return 0;
		}
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmss
	 * 
	 * @return String
	 */
	public static String getCurrTime() {
		Date now = new Date();
		SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = outFormat.format(now);
		return s;
	}
	
	/**
	 * 获取本机的ip地址
	 * @return String*/
	public static String getHostIP(){
		InetAddress addr;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString(); //获取本机ip 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
		return ip;
	}

}
