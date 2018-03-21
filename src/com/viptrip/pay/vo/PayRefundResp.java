package com.viptrip.pay.vo;

import java.util.Date;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
public class PayRefundResp {
    private Integer code;
    private String msg;
    private Double refundAmount;
    private Date refundTime;

    public PayRefundResp() {
        this.code = 1;
        this.msg = "失败";
    }

    public PayRefundResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return this.code == 0;
    }

    public void success(Double refundAmount,Date refundTime){
        this.code = 0;
        this.msg = "成功";
        this.refundAmount = refundAmount;
        this.refundTime = refundTime;
    }

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

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }
}
