package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderListAirline {
    
    private Integer type;// 航线类型，1：去程，2：回程

    private String startDatetime;// 第一程出发日期时间，2017-01-12 12:00

    private String endDatetime;// 最后一程到达日期时间，2017-01-12 12:00

    private String orgPortName;// 出发机场名称

    private String detPortName;// 到达机场名称

    private String orgTerm;// 出发航站楼

    private String detTerm;// 到达航站楼

    private String carriers;// 航司二字码列表，英文逗号分隔

    private String flightNos;// 航班号列表，英文逗号分隔

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getOrgPortName() {
        return orgPortName;
    }

    public void setOrgPortName(String orgPortName) {
        this.orgPortName = orgPortName;
    }

    public String getDetPortName() {
        return detPortName;
    }

    public void setDetPortName(String detPortName) {
        this.detPortName = detPortName;
    }

    public String getOrgTerm() {
        return orgTerm;
    }

    public void setOrgTerm(String orgTerm) {
        this.orgTerm = orgTerm;
    }

    public String getDetTerm() {
        return detTerm;
    }

    public void setDetTerm(String detTerm) {
        this.detTerm = detTerm;
    }

    public String getCarriers() {
        return carriers;
    }

    public void setCarriers(String carriers) {
        this.carriers = carriers;
    }

    public String getFlightNos() {
        return flightNos;
    }

    public void setFlightNos(String flightNos) {
        this.flightNos = flightNos;
    }

}
