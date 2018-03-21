package com.viptrip.wetrip.pay.alipay.old;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.struts2.ServletActionContext;


/**
 * 
 * @author lrc
 * 本文件夹中第4个文件，李荣春 检查完毕 2015年6月12日 10:21:50
 */
public class PaymentUtil {

	 /**
     * 签名字符串
     * @param signOrg 需要签名的字符串
     * @param key 密钥
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static String md5Sign(String signOrg,String key,String input_charset) {
    	String sign_str=signOrg+key;
        return DigestUtils.md5Hex(getContentBytes(sign_str, input_charset));
    }
    
    /**
     *验证签名
     * @param signOrg 需要签名的字符串
     * @param sign 签名结果
     * @param input_charset 编码格式
     * @return 签名结果
     */
    public static boolean md5Verify(String signOrg,String sign,String input_charset) {
    	String mysign = DigestUtils.md5Hex(signOrg.getBytes());
    	try {
			mysign=new String(mysign.getBytes(),input_charset);
		} catch (UnsupportedEncodingException e) {
			 throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + input_charset);
		}
    	if(mysign.equals(sign)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException 
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			 throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
		}
    }
    
    /**
     * 将alipay支付参数打包成Map
     * @param param
     * @return
     */
    public static Map<String,String> turnToMap(AlipayParam param){
    	Map<String,String> sParaTemp=new HashMap<String,String>();
    	sParaTemp.put("service", param.getService());
        sParaTemp.put("partner",param.getPartner());
        sParaTemp.put("_input_charset", param.getInputCharset());
		sParaTemp.put("payment_type", param.getPaymentType());
		sParaTemp.put("notify_url", param.getNotifyUrl());
		sParaTemp.put("return_url", param.getReturnUrl());
		sParaTemp.put("seller_email", param.getSellerAlipayNo());
		sParaTemp.put("out_trade_no", param.getOrderNo());
		sParaTemp.put("subject",param.getSubjectName());
		sParaTemp.put("total_fee",param.getTotalPrice());
		sParaTemp.put("body", param.getOrderDescribe());
		sParaTemp.put("show_url", param.getShowUrl());
		sParaTemp.put("anti_phishing_key", param.getAntiPhishingKey());
		sParaTemp.put("exter_invoke_ip", param.getExterInvokeIp());
		sParaTemp.put("defaultbank", param.getDefaultBank());
    	return sParaTemp;
    }
    /**
     * 去掉Map里的空值
     * @param sArray
     * @return
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        String value = "";
        for (String key : sArray.keySet()) {
            value = sArray.get(key);
            if (value == null || value.equals("")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
   
    /**
     * 参数排序组合拼接,转换Map<String，String>类型
     * @param params
     * @return
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        String key = "";
        String value = "";
        for (int i = 0; i < keys.size(); i++) {
            key = keys.get(i);
            value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 生成签名结果
     * @param sPara 要签名的数组
     * @return 签名结果字符串
     */
	public static String buildRequestMysign(Map<String, String> sPara,AlipayParam param) {
		//把数组所有元素，通过字母表顺序排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
    	String prestr = createLinkString(sPara); 
        String mysign = "";
        	mysign = md5Sign(prestr, param.getKey(), param.getInputCharset());
        return mysign;
    }
    /**
     * 获取当前机器IP地址
     * @return
     */
    public static String getCurrentIp(){
    	
    
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			//system.out.println("未知ip地址");
			e.printStackTrace();
		}
		return null;
    }
   /* public static String getCurrentIpPort(){
    	HttpServletRequest request=Struts2Util.getRequest();
    	return request.getScheme()+"://"+getCurrentIp()+":"+request.getServerPort();
    }*/
    /**
     * 获取客户端IP
     * @param request
     * @return
     */
    public static String getClientIp(HttpServletRequest request){
    
        String ip = request.getHeader("x-forwarded-for");  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
           ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        //system.out.println("ip="+ip);
        return ip;  
    }
    /**
     * 加载属性文件
     * @param param
     * @return
     */
    /*public static String getPaymentProp(String fileName,String param){
    	//通过webroot获取绝对路径
    	String path=ServletActionContext.getServletContext().getRealPath("/WEB-INF/conf/");
    	//加载properties文件
    	Properties prop = new Properties();
		InputStream is=null;
		try {
			is =new FileInputStream(new File(path+"/"+fileName));
			prop.load(is);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			//system.out.println("文件加载错误！");
			e.printStackTrace();
		}
		//读取properties文件
    	return prop.getProperty(param).trim();  
    }*/
    /**
     * 日期格式转换
     * @param date
     * @return
     */
    public static String dateSwitch(Date date){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
	    if(date==null){
	    	return "";
	    }
	    return sdf.format(date);
    }
    /**
     * 获取RequestId
     * @return
     */
    public static String getRequestId(){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSS");
    	Date date=new Date(System.currentTimeMillis());
    	return sdf.format(date);
    }
    /**
     * 将金额转换为以分为单位的整数
     * @return
     */
    public static String moneyToCount(double price){
        int count=(int)(price*100);
    	return count+"";
//        return "1";//测试用，支付金额1分钱
    }
    
    /**
     * 参数排序组合拼接,转换Map<String，Object>类型
     * @param params
     * @return
     */
    public static<T> String createLinkStringForObject(Map<String, T> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        String key = "";
        String value = "";
        for (int i = 0; i < keys.size(); i++) {
            key = keys.get(i);
            value = params.get(key)+"";

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    
}
