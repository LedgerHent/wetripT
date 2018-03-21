package com.viptrip.wetrip.vo;

/**
 * Created by selfwhisper on 0013 2017/12/13.
 */
public class Status {
    public static final int SUCCESS = 0;
    public static final int DEFAULT = -1;
    private int code;
    private String msg;

    private Status(){
        this.code = DEFAULT;
        this.msg = "默认信息";
    }

    public Status(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess(){
        return SUCCESS==this.code;
    }

    public static Status success(){
        return new Status(SUCCESS,"成功");
    }
    public static Status fail(String msg){
        Status status = new Status();
        if(null!=msg){
            status.setMsg(msg);
        }
        return status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
