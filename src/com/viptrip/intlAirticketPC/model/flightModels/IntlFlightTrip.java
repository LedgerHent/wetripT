package com.viptrip.intlAirticketPC.model.flightModels;

import java.util.List;

/**
 * Created by admin on 2018/1/9.
 */
public class IntlFlightTrip {

	public Integer type;//航程类型 1=单程，2=往返，3=联程（国际暂不支持联程）
	public List<Segments> segments;//航程信息

}
