package com.viptrip.wetrip.vo;

import java.util.Arrays;
import java.util.List;

/**
 * Created by selfwhisper on 0008 2017/12/8.
 */
public class RescheduleObj {
    private List<Long> tids;//票号id (长度大于1时为 多张行程相同的票同时改期)
    private String newDate;//新出发日期
    private String newFlightNo;//新的航班号
    private String newCangwei;//新的仓位
    private String newOrgCity;//新的出发城市
    private String newArrCity;//新的到达城市
    private Double newPrice;//票价
    private Double rescheduleFee;//改期费
    private Double serviceFee;//服务费
    private Double upgradeFee;//升舱费
    private String mapKey;//

    public RescheduleObj() {
    }

    public RescheduleObj(Long...tids) {
        if(null!=tids && tids.length>0){
            this.tids = Arrays.asList(tids);
        }
    }

    public boolean equalsWithoutTidsAndMapkey(RescheduleObj o) {
        if (this == o) return true;
        if (o == null) return false;

        RescheduleObj that = o;

        if (newDate != null ? !newDate.equals(that.newDate) : that.newDate != null) return false;
        if (newFlightNo != null ? !newFlightNo.equals(that.newFlightNo) : that.newFlightNo != null) return false;
        if (newCangwei != null ? !newCangwei.equals(that.newCangwei) : that.newCangwei != null) return false;
        if (newOrgCity != null ? !newOrgCity.equals(that.newOrgCity) : that.newOrgCity != null) return false;
        if (newArrCity != null ? !newArrCity.equals(that.newArrCity) : that.newArrCity != null) return false;
        if (newPrice != null ? !newPrice.equals(that.newPrice) : that.newPrice != null) return false;
        if (rescheduleFee != null ? !rescheduleFee.equals(that.rescheduleFee) : that.rescheduleFee != null)
            return false;
        if (serviceFee != null ? !serviceFee.equals(that.serviceFee) : that.serviceFee != null) return false;
        return upgradeFee != null ? upgradeFee.equals(that.upgradeFee) : that.upgradeFee == null;
    }


    public Double getRescheduleFee() {
        return rescheduleFee;
    }

    public void setRescheduleFee(Double rescheduleFee) {
        this.rescheduleFee = rescheduleFee;
    }

    public Double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(Double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public Double getUpgradeFee() {
        return upgradeFee;
    }

    public void setUpgradeFee(Double upgradeFee) {
        this.upgradeFee = upgradeFee;
    }

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public Double getNewPrice() {
        return newPrice;
    }
    public void setNewPrice(Double newPrice) {
        this.newPrice = newPrice;
    }
    public RescheduleObj(List<Long> tids) {
        this.tids = tids;
    }

    public List<Long> getTids() {
        return tids;
    }

    public void setTids(List<Long> tids) {
        this.tids = tids;
    }

    public String getNewDate() {
        return newDate;
    }

    public void setNewDate(String newDate) {
        this.newDate = newDate;
    }

    public String getNewFlightNo() {
        return newFlightNo;
    }

    public void setNewFlightNo(String newFlightNo) {
        this.newFlightNo = newFlightNo;
    }

    public String getNewCangwei() {
        return newCangwei;
    }

    public void setNewCangwei(String newCangwei) {
        this.newCangwei = newCangwei;
    }

    public String getNewOrgCity() {
        return newOrgCity;
    }

    public void setNewOrgCity(String newOrgCity) {
        this.newOrgCity = newOrgCity;
    }

    public String getNewArrCity() {
        return newArrCity;
    }

    public void setNewArrCity(String newArrCity) {
        this.newArrCity = newArrCity;
    }
}
