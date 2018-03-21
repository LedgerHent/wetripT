package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class ReqData_RefundETktForOrder_model implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 8894666778647486357L;
    
    @Description("原始行程ID")
    public Long tid;
    
    @Description("要退票的PNR号")
    public String pnr;
    
    @Description("出发城市三字码")
    public String orgcity;
    
    @Description("抵达城市三字码")
    public String arrcity;
    
    @Description("退票业务类型,1=原始行程,2=改期行程")
    public int businessType;
    
    @Description("退票票号")
    public String travelItineraryNo;
    
    @Description("净退票价")
    public Double netRefund;
    

    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    public String getPnr() {
        return pnr;
    }

    public void setPnr(String pnr) {
        this.pnr = pnr;
    }

    public String getOrgcity() {
        return orgcity;
    }

    public void setOrgcity(String orgcity) {
        this.orgcity = orgcity;
    }

    public String getArrcity() {
        return arrcity;
    }

    public void setArrcity(String arrcity) {
        this.arrcity = arrcity;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public String getTravelItineraryNo() {
        return travelItineraryNo;
    }

    public void setTravelItineraryNo(String travelItineraryNo) {
        this.travelItineraryNo = travelItineraryNo;
    }

    public Double getNetRefund() {
        return netRefund;
    }

    public void setNetRefund(Double netRefund) {
        this.netRefund = netRefund;
    }

}
