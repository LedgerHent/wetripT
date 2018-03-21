package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Response_Base;

/**
 * Created by selfwhisper on 0023 2018/1/23.
 */
public class Response_CancleOnlinePay  extends Response_Base {
    public Integer code;
    public String msg;

    public Response_CancleOnlinePay() {
    }

    public Response_CancleOnlinePay(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
