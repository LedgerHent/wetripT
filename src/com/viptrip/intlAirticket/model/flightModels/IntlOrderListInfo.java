package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;

public class IntlOrderListInfo {

    private Long orderId; // 订单id

    private String orderNo; // 订单号（用于显示）

    private String bookDatetime; // 预订日期时间，2017-01-15 20:00

    private Integer travelType; // 1:单程 2:往返

    private String orgCityName; // 出发城市名称

    private String detCityName; // 到达城市名称

    private List<IntlOrderListAirline> airlines;// 航线信息

    private Double amount; // 订单总额

    private Integer status; // 订单状态1-待审核|2-已通过（已审核）|3-已驳回（审核未通过）|4-待支付|5-已完成（已审核、已支付、已取消）|6-已完成（已审核、已支付）

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBookDatetime() {
        return bookDatetime;
    }

    public void setBookDatetime(String bookDatetime) {
        this.bookDatetime = bookDatetime;
    }

    public Integer getTravelType() {
        return travelType;
    }

    public void setTravelType(Integer travelType) {
        this.travelType = travelType;
    }

    public String getOrgCityName() {
        return orgCityName;
    }

    public void setOrgCityName(String orgCityName) {
        this.orgCityName = orgCityName;
    }

    public String getDetCityName() {
        return detCityName;
    }

    public void setDetCityName(String detCityName) {
        this.detCityName = detCityName;
    }

    public List<IntlOrderListAirline> getAirlines() {
        return airlines;
    }

    public void setAirlines(List<IntlOrderListAirline> airlines) {
        this.airlines = airlines;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
