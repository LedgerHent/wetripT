package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Request_Base;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Request_Pay extends Request_Base {
    public Integer payType;
    public String orderId;
    public Double price;
    public String title;
    public String body;
    public String notifyURL;
    public String returnURL;
    public String ip;

    public Request_Pay() {
    }

    public Request_Pay(Integer payType, String orderId, Double price, String title, String body, String notifyURL, String returnURL, String ip) {
        this.payType = payType;
        this.orderId = orderId;
        this.price = price;
        this.title = title;
        this.body = body;
        this.notifyURL = notifyURL;
        this.returnURL = returnURL;
        this.ip = ip;
    }
}
