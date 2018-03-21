package com.viptrip.pay.vo;

import com.viptrip.util.StringUtil;

/**
 * 公共枚举
 * Created by selfwhisper on 0020 2017/11/20.
 */
public class E {

    /**
     * 支付状态
     */
    public enum UnipayStatus{
        //支付状态，1-待支付 2-支付完成 3-支付关闭(超时或取消) 4-支付结束(退款完成)
        Unknown(-1,"未知状态"),Waiting(1,"待支付"),Success(2,"支付完成"),Close(3,"支付关闭"),Finished(4,"支付结束");
        private Integer code;
        private String msg;

        private UnipayStatus(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static UnipayStatus as(Integer code){
            UnipayStatus result = null;
            if(null!=code){
                UnipayStatus[] values = UnipayStatus.values();
                for (UnipayStatus us:values
                     ) {
                    if(code.intValue() == us.getCode().intValue()){
                        result = us;
                        break;
                    }
                }
            }
            return result;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 支付方式
     */
    public enum PayType{
        //支付方式
        /*
          11-支付宝H5 12-支付宝扫码付	13-支付宝APP	 14-支付宝PC支付
          21-微信H5 	22-微信扫描支付	23-微信APP	24-微信刷卡支付	25-微信公众号支付	26-小程序支付
          31-农行H5  32-农行APP 33-农行PC
          41-易宝
        */
        支付宝H5(11,"支付宝H5"),
        支付宝扫码付(12,"支付宝扫码付"),
        支付宝APP(13,"支付宝APP"),
        支付宝PC(14,"支付宝PC"),
        微信H5(21,"微信H5"),
        微信扫码付(22,"微信扫码付"),
        微信APP(23,"微信APP"),
        微信刷卡支付(24,"微信刷卡支付"),
        微信公众号(25,"微信公众号"),
        微信小程序支付(26,"微信小程序支付"),
        农行H5(31,"农行H5"),
        农行APP(32,"农行APP"),
        农行PC(33,"农行PC"),
        易宝H5(41,"易宝H5"),
        易宝PC(42,"易宝PC"),
        易宝扫码(43,"易宝扫码");
        private Integer code;
        private String msg;

        private PayType(Integer code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public static PayType as(Integer code){
            PayType result = null;
            if(null!=code){
                PayType[] values = PayType.values();
                for (PayType us:values
                        ) {
                    if(code.intValue() == us.getCode().intValue()){
                        result = us;
                        break;
                    }
                }
            }
            return result;
        }

        public Integer getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum PayTypeSource{
        PC("PC"),H5("H5"),WX("WX"),APP("APP");
        private String source;

        PayTypeSource(String source) {
            this.source = source;
        }

        public static PayTypeSource as(String source){
            PayTypeSource result = null;
            if(StringUtil.isNotEmpty(source)){
                PayTypeSource[] values = PayTypeSource.values();
                for (PayTypeSource pts:values
                        ) {
                    if(pts.getSource().equals(source)){
                        result = pts;
                        break;
                    }
                }
            }
            return result;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }


}
