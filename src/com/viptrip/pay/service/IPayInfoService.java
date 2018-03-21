package com.viptrip.pay.service;

import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.entity.PayNotify;
import com.viptrip.pay.task.Notify;

import java.util.List;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
public interface IPayInfoService {
    List<PayInfo> query(String orderno, Integer status);
    boolean existSuccessPayInfo(String orderno);
    boolean existCantPayRecode(String orderno);
    PayInfo getSuccessPayInfo(String orderno);
    PayInfo getPayInfo(String orderno,Integer payType,Integer status);
    PayNotify getPayNotify(String orderno,Integer type);
    void update(PayInfo payInfo);
    Notify update(Notify notify);
    void save(PayInfo payInfo);
    public PayInfo getPayInfoByNoAndType(Integer payType,String orderNo);
}
