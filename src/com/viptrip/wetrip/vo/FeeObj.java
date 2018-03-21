package com.viptrip.wetrip.vo;

/**
 * Created by selfwhisper on 0011 2017/12/11.
 */
public class FeeObj {
    private Double upgradeFee;//升舱费
    private Double serviceFee;//服务费
    private Double rescheduleFee;//改期费

    public Double getUpgradeFee() {
        return upgradeFee;
    }

    public void setUpgradeFee(Double upgradeFee) {
        this.upgradeFee = upgradeFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getRescheduleFee() {
        return rescheduleFee;
    }

    public void setRescheduleFee(Double rescheduleFee) {
        this.rescheduleFee = rescheduleFee;
    }
}
