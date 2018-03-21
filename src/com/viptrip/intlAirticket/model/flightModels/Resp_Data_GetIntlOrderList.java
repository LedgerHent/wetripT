package com.viptrip.intlAirticket.model.flightModels;

import java.util.List;


public class Resp_Data_GetIntlOrderList{

    
    private int totalCount;//记录总数

    private List<IntlOrderListCarrier> carriers;//航司对象数组
    
    private List<IntlOrderListInfo> list;//订单列表

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<IntlOrderListCarrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<IntlOrderListCarrier> carriers) {
        this.carriers = carriers;
    }

    public List<IntlOrderListInfo> getList() {
        return list;
    }

    public void setList(List<IntlOrderListInfo> list) {
        this.list = list;
    }
    
    
}
