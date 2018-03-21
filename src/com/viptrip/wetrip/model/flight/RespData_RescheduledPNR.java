package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class RespData_RescheduledPNR implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6962170747506682754L;
    public RespData_RescheduledPNR() {
        // TODO Auto-generated constructor stub
    }
    
    public RespData_RescheduledPNR(String arrcity,int businessType,String orgcity,
            String pnr,Long tid,String travelItineraryNo) {
        this.arrcity=arrcity;
        this.businessType=businessType;
        this.orgcity=orgcity;
        this.pnr=pnr;
        this.tid=tid;
        this.travelItineraryNo=travelItineraryNo;
    }
    
    @Description("原始行程ID")
    public Long tid;
    
    @Description("要改期的PNR号")
    public String pnr;
    
    @Description("出发城市三字码")
    public String orgcity;
    
    @Description("抵达城市三字码")
    public String arrcity;
    
    @Description("改期业务类型,1=原始行程,2=改期行程")
    public int businessType;
    
    @Description("改期原始票号")
    public String travelItineraryNo;
    
    
    @Description("改期后的PNR号")
    public String newPnr;
    
    @Description("改期后的票号")
    public String newTravelItineraryNo;
    
    @Description("改期失败原因")
    public String message;

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

    public String getNewPnr() {
        return newPnr;
    }

    public void setNewPnr(String newPnr) {
        this.newPnr = newPnr;
    }

    public String getNewTravelItineraryNo() {
        return newTravelItineraryNo;
    }

    public void setNewTravelItineraryNo(String newTravelItineraryNo) {
        this.newTravelItineraryNo = newTravelItineraryNo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
