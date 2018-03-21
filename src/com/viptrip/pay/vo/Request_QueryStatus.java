package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Request_Base;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Request_QueryStatus extends Request_Base {
    public Integer payType;
    public String orderId;


    public Request_QueryStatus() {
    }

    public Request_QueryStatus(Integer payType, String orderId) {
        this.payType = payType;
        this.orderId = orderId;
    }
}
