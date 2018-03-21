package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/1/10.
 */
public class Trips {

    public String mapKey;
    public Double totalBasePrice;
    public Double totalTaxPrice;
    public Date date;//出发日期，用于差旅管控
    public List<HangchengSegments> segments;
    public Overproof overproof;
}
