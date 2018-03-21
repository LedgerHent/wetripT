package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Response_Base;

import java.util.Date;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Response_QueryStatus extends Response_Base {
    private Integer code; //支付状态，1-待支付 2-支付完成 3-支付关闭(超时或取消) 4-支付结束(退款完成)
    private String msg;

    public Response_QueryStatus() {
    }

    public Response_QueryStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
