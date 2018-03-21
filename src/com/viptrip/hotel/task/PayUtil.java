package com.viptrip.hotel.task;

import java.util.HashMap;
import java.util.Map;

import com.viptrip.hotel.controller.PayCallBack;
import com.viptrip.hotel.entity.HotelPayInfo;
import com.viptrip.hotel.model.Request_PayCallBack;
import com.viptrip.hotel.model.payCallBack.PayCallBack_Data;
import com.viptrip.util.JSON;


public class PayUtil{
	/**
	 * 获取通知参数
	 * @param hpi
	 * @param type 通知类型 1-回跳 2-通知
	 * @param payType 支付方式 1-支付宝 2-微信 3-易宝
	 * @param status 支付状态 0-未知 1-成功 2-失败
	 * @param memo 支付说明
	 * @return
	 */
	public static Map<String,Object> getNotifyParamMap(HotelPayInfo hpi,int type,int payType,int status,String memo){
		Map<String,Object> map = new HashMap<>();
		if(null!=hpi){
			map.put("orderNo", hpi.getOrderno() );
			map.put("amount", hpi.getAmount() );
			map.put("returnUrl", hpi.getReturnUrl());
			map.put("notifyUrl", hpi.getNotifyUrl());
			map.put("subject", hpi.getSubject());
			map.put("body", hpi.getBody());
			map.put("nonceStr", hpi.getNonceStr());
			map.put("type", type);
			map.put("payType", payType);
			map.put("payStatus", status);
			map.put("payMemo", memo);
		}
		return map;
	}
	
	/**
	 * 获取加密后的参数字符串
	 * @param map 参数map
	 * @return
	 */
	public static String getEncryptParamStr(Map<String,Object> map){
		Request_PayCallBack param = new Request_PayCallBack();
		PayCallBack_Data data = new PayCallBack_Data(map);
		param.setData(data);
		return new PayCallBack().encryptParam(JSON.get().toJson(param));
	}
	
	/**
	 * 获取加密后的参数字符串
	 * @param map 参数map
	 * @return
	 */
	public static String getDecryptParamStr(String encryptedStr){
		return new PayCallBack().decryptParam(encryptedStr);
	}
}