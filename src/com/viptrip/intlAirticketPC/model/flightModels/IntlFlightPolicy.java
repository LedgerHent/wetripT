package com.viptrip.intlAirticketPC.model.flightModels;

/**
 * Created by admin on 2018/1/9.
 */
public class IntlFlightPolicy {
    public Integer businessType;//出行类型，因私出行不受差旅政策限制  1-因公出行，2-因私出行
    public Integer [] staffIds;//员工id序列，最多选5人，选填。
    public int overproofDetail;//超标详情 0-不需要（默认），1-需要

}
