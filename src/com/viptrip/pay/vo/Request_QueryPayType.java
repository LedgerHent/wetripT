package com.viptrip.pay.vo;

import com.viptrip.wetrip.model.base.Request_Base;

/**
 * Created by selfwhisper on 0031 2018/1/31.
 */
public class Request_QueryPayType extends Request_Base {

    public String source;

    public Request_QueryPayType() {
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Request_QueryPayType(String source) {
        this.source = source;
    }
}
