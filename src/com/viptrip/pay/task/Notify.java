package com.viptrip.pay.task;

import java.util.Date;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
public interface Notify {
    void setSending(Integer sending);
    void setSendTime(Date sendTime);
    void setStatus(Integer status);
    Integer getCount();
    String getUrl();
    String getOrderno();
    int nextCount();

}
