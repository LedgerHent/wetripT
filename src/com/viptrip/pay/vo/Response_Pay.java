package com.viptrip.pay.vo;

import etuf.v1_0.model.v2.base.Response_Base;

/**
 * Created by selfwhisper on 0029 2017/11/29.
 */
public class Response_Pay extends Response_Base {
    public String form;

    public Response_Pay() {
    }

    public Response_Pay(String form) {
        this.form = form;
    }
}
