package com.viptrip.wetrip.pay.vo;

/**
 * Created by selfwhisper on 0004 2018/1/4.
 */
public class JSAPIPayObj {
    private String appId;	//是	String(16)	wx8888888888888888	商户注册具有支付权限的公众号成功后即可获得
    private String timeStamp;	//是	String(32)	1414561699	当前的时间，其他详见时间戳规则
    private String nonceStr;	//是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
    private String package1; //是	String(128)	prepay_id=123456789	统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
    private String signType; //	是	String(32)	MD5	签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
    private String paySign;
}
