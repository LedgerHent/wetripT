package com.viptrip.warning.vo;

import com.viptrip.warning.resource.MEnum;
import com.viptrip.warning.resource.MEnum.State;

/**
 * 状态消息
 * Created by selfwhisper on 0028 2017/8/28.
 */
public class MSM {
    private boolean state;
    private String msg;


    public MSM() {
    }

    public MSM(State state, String msg) {
        if(null!=state){
            switch (state){
                case 成功:
                    this.state = true;
                    break;
                default:
                    this.state = false;
                    break;
            }
        }
        this.msg = msg;
    }


    public MSM(boolean state, String msg) {
        this.state = state;
        this.msg = msg;
    }

    public MSM(State state) {
        if(null!=state){
            switch (state){
                case 成功:
                    this.state = true;
                    break;
                default:
                    this.state = false;
                    break;
            }
            this.msg = state.getDesc();
        }
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
