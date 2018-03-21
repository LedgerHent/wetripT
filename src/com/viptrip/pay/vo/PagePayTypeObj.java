package com.viptrip.pay.vo;

/**
 * Created by selfwhisper on 0031 2018/1/31.
 */
public class PagePayTypeObj {
    private Integer payType;
    private String payTypeTxt;
    private Boolean enable;

    public PagePayTypeObj() {
    }

    public PagePayTypeObj(Integer payType, String payTypeTxt,Boolean enable) {
        this.payType = payType;
        this.payTypeTxt = payTypeTxt;
        this.enable = enable;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeTxt() {
        return payTypeTxt;
    }

    public void setPayTypeTxt(String payTypeTxt) {
        this.payTypeTxt = payTypeTxt;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
