package com.viptrip.pay.vo;

import com.viptrip.wetrip.model.base.Response_Base;

import java.util.List;

/**
 * Created by selfwhisper on 0031 2018/1/31.
 */
public class Response_QueryPayType extends Response_Base {
    public List<PagePayTypeObj> list;

    public Response_QueryPayType() {
    }

    public Response_QueryPayType(List<PagePayTypeObj> list) {
        this.list = list;
    }

    public List<PagePayTypeObj> getList() {
        return list;
    }

    public void setList(List<PagePayTypeObj> list) {
        this.list = list;
    }
}
