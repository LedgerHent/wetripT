package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class Req_Data_OrderInfo {

    private String mapKey; // 若是单程传入单程返回的mapKey,若是往返行程,传入返程返回的mapKey

    private long checkManId; // 审核人id
    private Long approvalId; // 审批id

	private String verify;//是否需要审核  1 需要 0不需要

    private int payMethod; // 1-公司月结，2-在线支付，3-线下支付，4-预付款支付

    private int travelType; // 出行类型：0-因公出行，1-因私出行

    private String cangwei; // 舱位

    private List<Req_Data_OrderInfo_Informer> informerList; // 通知人信息

    private List<Req_Data_OrderInfo_Informer_Passenger> passengerList; // 乘机人信息

    public String getMapKey() {
        return mapKey;
    }

    public void setMapKey(String mapKey) {
        this.mapKey = mapKey;
    }

    public long getCheckManId() {
        return checkManId;
    }

    public void setCheckManId(long checkManId) {
        this.checkManId = checkManId;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getTravelType() {
        return travelType;
    }

    public void setTravelType(int travelType) {
        this.travelType = travelType;
    }

    public String getCangwei() {
        return cangwei;
    }

    public void setCangwei(String cangwei) {
        this.cangwei = cangwei;
    }
    
    
    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public List<Req_Data_OrderInfo_Informer> getInformerList() {
        return informerList;
    }

    public void setInformerList(List<Req_Data_OrderInfo_Informer> informerList) {
        this.informerList = informerList;
    }

    public List<Req_Data_OrderInfo_Informer_Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(List<Req_Data_OrderInfo_Informer_Passenger> passengerList) {
        this.passengerList = passengerList;
    }

	public Long getApprovalId() {
		return approvalId;
	}

	public void setApprovalId(Long approvalId) {
		this.approvalId = approvalId;
	}

}
