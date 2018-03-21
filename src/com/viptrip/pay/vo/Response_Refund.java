package com.viptrip.pay.vo;

import com.viptrip.util.DateUtil;
import etuf.v1_0.model.v2.base.Response_Base;

import java.util.Date;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Response_Refund extends Response_Base {
    private Integer code;//0-成功 其他-失败
    private String msg;
    private Double refundAmount;
    private String refundTime;

    public Response_Refund() {
    }

    public Response_Refund(Integer code, String msg, Double refundAmount, Date refundTime) {
        this.code = code;
        this.msg = msg;
        this.refundAmount = refundAmount;
        if(null!=refundTime){
            this.refundTime = DateUtil.SDF_yyyy_MM_dd_HH_mm_ss.format(refundTime);
        }
    }
}
