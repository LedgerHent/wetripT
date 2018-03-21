package com.viptrip.intlAirticketPC.model.flightModels;

/**
 * Created by admin on 2018/1/9.
 */
public class IntlFlightReq {
    private IntlFlightTrip trip;//行程信息
    private IntlFlightFilter filter;//筛选信息，可选
    private IntlFlightPolicy policy;//差旅政策，选填

    public IntlFlightTrip getTrip() {
        return trip;
    }

    public void setTrip(IntlFlightTrip trip) {
        this.trip = trip;
    }

    public IntlFlightFilter getFilter() {
        return filter;
    }

    public void setFilter(IntlFlightFilter filter) {
        this.filter = filter;
    }

    public IntlFlightPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(IntlFlightPolicy policy) {
        this.policy = policy;
    }
}
