package com.viptrip.pay.task;

import com.viptrip.hotelHtml5.util.EncryptHelper;
import com.viptrip.pay.PayConfig;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.util.DateUtil;
import com.viptrip.util.JSON;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class PayUtil {
	/**
	 * 获取通知参数
	 * @param hpi
	 * @param type 通知类型 1-回跳 2-通知
	 * @param payType 支付方式
	 * 				  11-支付宝H5	12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
	 * 				  21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
	 * 				  31-农行H5  32-农行APP 33-农行PC
	 * 				  41-易宝
	 * @param status 支付状态 0-未知 1-成功 2-失败
	 * @param memo 支付说明
	 * @return
	 */
	public static Map<String,Object> getNotifyParamMap(PayInfo hpi, int type, int payType, int status, String memo){
		Map<String,Object> map = new HashMap<>();
		if(null!=hpi){
			map.put("orderId", hpi.getOrderno() );
			map.put("amount", hpi.getAmount()/100D);
			//map.put("returnUrl", hpi.getReturnUrl());
			//map.put("notifyUrl", hpi.getNotifyUrl());
			map.put("title", hpi.getSubject());
			map.put("body", hpi.getBody());
			//map.put("nonceStr", hpi.getNonceStr());
			//map.put("type", type);
			map.put("payType", payType);
			map.put("payStatus", status);
			map.put("payMemo", memo);

			map.put("payTime", DateUtil.format(hpi.getPayTime(),"yyyy-MM-dd HH:mm:ss"));
			map.put("payNo",hpi.getPayerno());
			map.put("payerNo",hpi.getPayerno());
		}
		return map;
	}
	
	/**
	 * 获取加密后的参数字符串
	 * @param map 参数map
	 * @return
	 */
	public static String getEncryptParamStr(Map<String,Object> map){
		String decrypt = EncryptHelper.DESDecrypt(JSON.get().toJson(map), PayConfig.desKey, PayConfig.desIV, Charset.forName(PayConfig.charset));
		return decrypt;
	}
	
	/**
	 * 获取加密后的参数字符串
	 * @return
	 */
	public static String getDecryptParamStr(String encryptedStr){
		String decrypt = EncryptHelper.DESDecrypt(encryptedStr, PayConfig.desKey, PayConfig.desIV, Charset.forName(PayConfig.charset));
		return decrypt;
	}
}