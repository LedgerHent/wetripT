package com.viptrip.wetrip.pay.vo;

/**
 * 统一支付回跳参数bean
 * Created by selfwhisper on 0004 2018/1/4.
 */
public class RtnBean {
    private Integer code;
    private String msg;
    private String orderId;
    private Double amount;
    private Integer payType;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
