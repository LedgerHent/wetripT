package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Request_Base;

/**
 * Created by selfwhisper on 0023 2018/1/23.
 */
public class Request_CancleOnlinePay  extends Request_Base {
    public Integer payType;
    public String orderId;

    public Request_CancleOnlinePay() {
    }

    public Request_CancleOnlinePay(Integer payType, String orderId) {
        this.payType = payType;
        this.orderId = orderId;
    }
}
