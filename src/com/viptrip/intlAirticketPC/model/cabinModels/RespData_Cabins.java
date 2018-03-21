package com.viptrip.intlAirticketPC.model.cabinModels;

import com.viptrip.intlAirticketPC.model.flightModels.Cabin;

public class RespData_Cabins {

	public Double totalBasePrice;			//票面价总价，往返对应的是打包总价	双精度数字
	public Double totalTaxPrice;		//税总价
	public int priceType;	//价格类型	整型数字，详见价格类型说明
	
	public Cabin cabin;//舱位组合信息
}
