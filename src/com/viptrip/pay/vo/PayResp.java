package com.viptrip.pay.vo;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
public class PayResp {
    private Integer code;
    private String msg;
    private Object data;

    private PayResp() {
    }

    public PayResp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
