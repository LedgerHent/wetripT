package com.viptrip.intlAirticket.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.viptrip.base.common.MyEnum;
import com.viptrip.intlAirticket.service.impl.FlightIntlServiceImpl;
import com.viptrip.util.StringUtil;
import com.viptrip.wetrip.ConfigUtil;
import com.viptrip.yeego.intlairticket.model.Response_QueryWebFlightsI_1_0;

import etuf.v1_0.common.Common;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.Config;
import etuf.v1_0.model.base.output.OutputResult;

public class CommonMethodUtils {
	private static boolean isFormatMapkey = false;
	private static final String INTLT_PREFIX = "intlTicket";//配置文件属性前缀
	private static Logger logger = LoggerFactory.getLogger(FlightIntlServiceImpl.class);
	/**
	 * 往返行程未查返程直接调接口 判断
	 * @param mapKey
	 * @return 
	 */
	public static OutputResult<T, String> valid_往返行程未查返程直接调接口(String mapKey) {
		OutputResult<T, String> out =
				new OutputResult<T, String>();
		String unformatMapkey = getUnFormatMapkey(mapKey);
		String[] splits = unformatMapkey.split("\\|");
		if (splits == null || splits.length == 0) {
			out.result="无效的mapKey--" + mapKey;
		}else{
			String key4GetFlight = splits[0];
			boolean valid = true;
			boolean isRTTrip = isRTTravleType(key4GetFlight);
			if (isRTTrip) {
				valid = splits[1].contains("*");
			}
			if (!valid) {
				out.result="往返行程，必须获取返程后再调用该接口。";
			}else out.code=Constant.Code_Succeed;;
		}
		return out;
	}
	/*
	 * 获取中转航班号序列，多个航班号以下划线_分隔,往返以星号*分隔
	 */
	public static String getZhongZhuanFlightNos(
			Hashtable<String, Object[]> segments, boolean isNeedReturnTrip) {
		Object[] go = segments.get("S1");
		String nos = getZhongZhuanFlightNos(go);
		if (isNeedReturnTrip) {
			Object[] back = segments.get("S2");
			String backNos = getZhongZhuanFlightNos(back);
			if (backNos != null && !"".equals(backNos)) {
				nos += "*" + backNos;
			}
		}
		return nos;
	}
	public static String getZhongZhuanFlightNos2(Hashtable<String, Object[]> segments) {
		Object[] go = segments.get("S1");
		String nos = getZhongZhuanFlightNos(go);
		try {
			if (segments.size()>1) {
				Object[] back = segments.get("S2");
				String backNos = getZhongZhuanFlightNos(back);
				if (backNos != null && !"".equals(backNos)) {
					nos += "*" + backNos;
				}
			}
			
		} catch (Exception e) {
			logger.error("错误号：201801231418Li，查询往返程拼接航班号信息报错，返程拼接报错，已有航班号："+nos+"，错误信息："+e.getMessage());
		}
		return nos;
	}

	@SuppressWarnings("rawtypes")
	public static String getZhongZhuanFlightNos(Object[] seg) {
		String nos = "";
		if (seg != null && seg.length > 0) {
			Object[] flights = ((ArrayList) seg[1]).toArray();
		for (int i = 0; i < flights.length; i++) {
			if (i > 0) {
				nos += "_";
			}
				nos += ((ArrayList) flights[i]).toArray()[1].toString();
		}
		}

		return nos;
	}
	/*
	 * 获取中转城列表
	 */
	@SuppressWarnings("rawtypes")
	public static ArrayList<String> getZhongZhuanCitys(
			Response_QueryWebFlightsI_1_0 resq, Object[] zhongZhuans) {
		// "机型代码":["机型名称","型号","机体",最少人数,最多人数]
		ArrayList<String> citys = new ArrayList<String>();
		if (zhongZhuans.length > 0) {
			for (Object o : zhongZhuans) {
				String airportCode = ((ArrayList) o).toArray()[0].toString();
				citys.add(getAirportLocationCityName(resq, airportCode));
			}
		}
		return citys;
	}
	/*
	 * 
	 * 根据机场三字码获取所在城市名称（简称）
	 */
	public static String getAirportLocationCityName(
			Response_QueryWebFlightsI_1_0 resq, String port) {
		// "机场三字码":["机场简称","机场全称","机场所在城市","城市三字码"]
		String name=resq.airPortsHt.get(port)[2];
		if(name==null || "".equals(name)){
			name=port;
		}
		return name;
	}
	/*
	 * 获取非格式化（原始）的mapkey
	 */
	public static String getUnFormatMapkey(String mapkey) {
		//获取配置信息
		Config c= null;
		try {
			c = ConfigUtil.config(INTLT_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		if (isFormatMapkey) {
			try {
				return etuf.v1_0.common.EncryptHelper.DESDecrypt(
						URLDecoder.decode(mapkey,"utf-8"), c.getDesKey(),c.getDesIV(),c.getCharset());
		} catch (Exception e) {
			}
		}
		return mapkey;
	}
	/*
	 * 根据mapkey获取航程类型
	 */
	public static String getTravleType(String mapKey) {
		// PEK_HKG_ADT_RT_2017-02-20_2017-02-25|CA1234_MU520_FM1122*CA1235_MU521_FM1123
		String[] splits = mapKey.split("\\|")[0].split("_");
		return splits[3].toString();//TODO 转换类型
	}

	/*
	 * 根据mapkey获取是否是往返的航程类型
	 */
	public static boolean isRTTravleType(String mapKey) {
		return MyEnum.TripTypeInt.往返.toString().equals(getTravleType(mapKey));
	}
	/*
	 * 获取格式化的mapkey
	 */
	public static String GetFormatMapkey(String mapkey) {
		//获取配置信息
		Config c= null;
		try {
			c = ConfigUtil.config(INTLT_PREFIX);
		} catch (IOException e) {
			logger.error(StringUtil.getExceptionStr(e));
		}
		if (isFormatMapkey) {
			try {
				return URLEncoder.encode(etuf.v1_0.common.EncryptHelper.DESDecrypt(
						mapkey, c.getDesKey(),c.getDesIV(),c.getCharset()),"UTF-8");
			} catch (Exception e) {
			}
		}
		return mapkey;
	}
	/*
	 * 根据航司二字码获取名称（简称）
	 */
	public static String getCarrierName(Response_QueryWebFlightsI_1_0 resq,
			String carrier) {
		return getCarrierName(resq,carrier,true);
	}
	/*
	 * 根据航司二字码获取名称（简称）
	 */
	public static String getCarrierName(Response_QueryWebFlightsI_1_0 resq,
			String carrier,boolean isNeedShortName) {
		// "航空公司二字码":["航空公司名称","航空公司简称","是否有网上值机"]
		return resq.airLines.get(carrier)[isNeedShortName?1:0];
	}
	/*
	 * 根据机场三字码获取名称（简称）
	 */
	public static String getAirportName(Response_QueryWebFlightsI_1_0 resq,
			String port) {
		// "机场三字码":["机场简称","机场全称","机场所在城市","城市三字码"]
		String name=resq.airPortsHt.get(port)[0];
		if(name==null || "".equals(name)){
			name=port;
		}
		return name;
	}
	/*
	 * 根据日期和时间拼出来日期时间串，空格相连
	 */
	public static String getDateTime(String date, String time) {
		return date.trim() + " " + time.trim();
	}
	/*
	 * 根据机型获取机型名称
	 */
	public static String getPlaneTypeName(Response_QueryWebFlightsI_1_0 resq,
			String planeType) {
		// "机型代码":["机型名称","型号","机体",最少人数,最多人数]
		String name="";
		try {
			name=resq.airType.get(planeType)[0].toString();
			if(!Common.IsNullOrEmpty(name)){
				String model=resq.airType.get(planeType)[2].toString();
				if(!Common.IsNullOrEmpty(name)){
					name+="("+model+")";
				}
			}
		} catch (Exception e) {
			logger.error("根据机型获取机型名称:"+e.getMessage());
		}
		return name;
	}
	/*
	 * 根据mapkey获取乘机人类型
	 */
	public static String getPassengerType(String mapKey) {
		// PEK_HKG_ADT_RT_2017-02-20_2017-02-25|CA1234_MU520_FM1122*CA1235_MU521_FM1123
		String[] splits = mapKey.split("\\|")[0].split("_");
		return splits[2].toString();//TODO 转换类型
	}
	/**
	 * 乘机人类型转换int-->str
	 * @param passengerType
	 * @return
	 */
	public static String passengerTypeStr(int passengerType) {
		String pasgTypeStr = "";
//		ADT：成人 CHD：儿童 INF：婴儿  不填为成人||1=成人，2=儿童，3=婴儿
		if(passengerType==MyEnum.PassengerTypeCodeInt.成人.getValue())
			pasgTypeStr = MyEnum.PassengerTypeCode.成人.getValue();
		if(passengerType==MyEnum.PassengerTypeCodeInt.儿童.getValue())
			pasgTypeStr = MyEnum.PassengerTypeCode.儿童.getValue();
		if(passengerType==MyEnum.PassengerTypeCodeInt.婴儿.getValue())
			pasgTypeStr = MyEnum.PassengerTypeCode.婴儿.getValue();
		return pasgTypeStr;
	}
	/**
	 * 乘机人类型转换str-->int
	 * @param passengerType
	 * @return
	 */
	public static int passengerTypeStr(String passengerType) {
		int pasgTypeStr = 0;
//		ADT：成人 CHD：儿童 INF：婴儿  不填为成人||1=成人，2=儿童，3=婴儿
		if(MyEnum.PassengerTypeCode.成人.getValue().equals(passengerType))
			pasgTypeStr = MyEnum.PassengerTypeCodeInt.成人.getValue();
		if(MyEnum.PassengerTypeCode.儿童.getValue().equals(passengerType))
			pasgTypeStr = MyEnum.PassengerTypeCodeInt.儿童.getValue();
		if(MyEnum.PassengerTypeCode.婴儿.getValue().equals(passengerType))
			pasgTypeStr = MyEnum.PassengerTypeCodeInt.婴儿.getValue();
		return pasgTypeStr;
	}
	/**
	 * 行程类型转换int-->str
	 * @param passengerType
	 * @return
	 */
	public static String travelTypeToStr(int travelType) {
		String travelStr = "";
		if(MyEnum.TripTypeInt.单程.getValue()==travelType)
			travelStr = MyEnum.TripTypeInt.单程.toString();
		else if(MyEnum.TripTypeInt.往返.getValue()==travelType)
			travelStr = MyEnum.TripTypeInt.往返.toString();
		else if(MyEnum.TripTypeInt.联程.getValue()==travelType)
			travelStr = MyEnum.TripTypeInt.联程.toString();
		return travelStr;
	}
	/**
	 * 行程类型转换str-->int
	 * @param passengerType
	 * @return
	 */
	public static int travelTypeToStr(String travelType) {
		int travelStr = 0;
		if(MyEnum.TripTypeInt.单程.toString().equals(travelType))
			travelStr = MyEnum.TripTypeInt.单程.getValue();
		else if(MyEnum.TripTypeInt.往返.toString().equals(travelType))
			travelStr = MyEnum.TripTypeInt.往返.getValue();
		else if(MyEnum.TripTypeInt.联程.toString().equals(travelType))
			travelStr = MyEnum.TripTypeInt.往返.getValue();
		return travelStr;
	}
	/*
	 * 返回不带小数点的数字，有小数点，直接进位
	 */
	public static Double getDoubleStringWithoutDot(double d) {
		return Double.valueOf(Math.round(Math.ceil(d)));
	}
	/*
	 * 
	 * 根据舱位类型获取舱位中文类型描述 往返E,B/E格式
	 */
	public static String getCabinTypeName(Response_QueryWebFlightsI_1_0 resq,String type) {
		String[] splits = type.split("/");
		String typeNames="";
		for(int i=0;i<splits.length;i++){
			if(i>0){
				typeNames+="/";
			}
			String[] ss=splits[i].split(",");
			for(int j=0;j<ss.length;j++){
				if(!"".equals(ss[j])){
					if(j>0){
						typeNames+=",";
					}
					typeNames+=resq.cangweiLevelDesc.get(ss[j]);
				}
			}
		}
		return typeNames;
	}
	/**
	 * 根据餐类代码，得到对应中文名称。
	 * @param object
	 * @return
	 */
	public static String getFoodStr(Object food) {
		if(null!=food && !Common.IsNullOrEmpty(food.toString())){
			switch (food.toString()) {
			case "B":
				return "早餐";
			case "C":
				return "点心或快餐";
			case "D":
				return "正餐";
			case "M":
				return "配餐";
			case "S":
				return "小吃 点心或快餐";
			case "L":
				return "午餐";
			case "O":
				return "冷快餐";
			case "H":
				return "热快餐";
			default:
				return "无";
			}
		}else return "无";
	}
	
}
