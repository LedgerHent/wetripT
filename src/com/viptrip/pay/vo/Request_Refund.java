package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Request_Base;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Request_Refund extends Request_Base {
    public Integer payType;
    public String orderId;
    public String refundNo;
    public Double refundAmount;
    public String refundReason;

    public Request_Refund() {
    }

    public Request_Refund(Integer payType, String orderId, String refundNo, Double refundAmount, String refundReason) {
        this.payType = payType;
        this.orderId = orderId;
        this.refundNo = refundNo;
        this.refundAmount = refundAmount;
        this.refundReason = refundReason;
    }
}
