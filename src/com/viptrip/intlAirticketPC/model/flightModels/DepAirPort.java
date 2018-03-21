package com.viptrip.intlAirticketPC.model.flightModels;

/**
 * 出发机场信息
 * @author admin
 *
 */
public class DepAirPort extends Airport{
	public String terminal;//	航站楼	字符串
	public String arrDatetime;//	到达日期时间，出发无意义	日期时间字符串
	public String depDatetime;//	起飞日期时间，到达无意义	日期时间字符串
}
