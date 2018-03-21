package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;


public class RespData_RefundETktForOrder_model implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -2662721771208969320L;

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
    
    @Description("退票单号")
    public String refundno;
    
    @Description("标记成功-0失败-1")
    public int code;
    
    @Description("失败原因描述")
    public String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getRefundno() {
        return refundno;
    }

    public void setRefundno(String refundno) {
        this.refundno = refundno;
    }
    
}
