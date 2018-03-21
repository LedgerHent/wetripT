package com.viptrip.ibeserver.config;
/**
 * 连接IBE的配置信息
 * @author wjt
 *
 */
public class IbeConstants {
	/**
	 * 连接IBE的账号信息
	 */
	public static String Ibe_Office="pek315";
	public static String Ibe_Customno="38315";
	public static String Ibe_Validationno="38";
	public static String Ibe_AppName="jhht";
	public static String Ibe_Connection_IP="10.0.4.152";
	public static int Ibe_Connection_Port=6891;
	
	public static String getIbeOffice(){
		return Ibe_Office;
	}
	public static String getIbeCustomno(){
		return Ibe_Customno;
	}
	public static String getIbeValidationno(){
		return Ibe_Validationno;
	}
	public static String getIbeAppName(){
		return Ibe_AppName;
	}
	public static String getIbeConnectionIP(){
		return Ibe_Connection_IP;
	}
	public static int getIbeConnectionPort(){
		return Ibe_Connection_Port;
	}
	
	private static String Ibe_AirportTax = "50";//default 50
	private static String Ibe_FuelTax = "0";//default 120
	
	public static String getAirportTax(){
		return Ibe_AirportTax;
	}
	public static String getFuelTax(){
		return Ibe_FuelTax;
	}
	
}
