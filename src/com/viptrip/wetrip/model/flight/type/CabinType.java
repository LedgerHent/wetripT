package com.viptrip.wetrip.model.flight.type;


import com.viptrip.base.common.MyEnum.CabinTypeCode;

/**
 * 舱位类型
 * @author selfwhisper
 *
 */
public enum CabinType {
	/**
	 * 不限
	 */
	Unlimited(0,"不限"),
	/**
	 * 经济舱
	 */
	EconomyClass(1,"经济舱"),
	/**
	 * 公务舱
	 */
	BusinessClass(2,"公务舱"),
	/**
	 * 头等舱
	 */
	FirstClass(3,"头等舱"),
	/**
	 * 头等舱和公务舱
	 */
	BusinessAndFirstClass(23,"头等舱公务舱");
	
	private Integer code;
	private String desc;
	
	private CabinType(int code,String desc){
		this.code = code;
		this.desc = desc;
	}
	
	/**
	 * 通过仓位类型代码获取  仓位类型
	 * @param typeCode
	 * @return
	 */
	public static CabinType getCabinType(CabinTypeCode typeCode){
		CabinType result = CabinType.Unlimited;
		switch (typeCode) {
		case 头等舱:
			result = CabinType.FirstClass;
			break;
		case 公务舱:
			result = CabinType.BusinessClass;
			break;
		case 经济舱:
			result = CabinType.EconomyClass;
			break;
		case 头等舱公务舱:
			result = CabinType.BusinessAndFirstClass;
			break;
		}
		return result;
	}
	/**
	 * 获取舱位类型
	 * @param typeCode
	 * @return
	 */
	public static CabinType getCabinType(String typeCode){
		CabinType result = CabinType.Unlimited;
		switch (typeCode) {
		case "F":
			result = CabinType.FirstClass;
			break;
		case "C":
			result = CabinType.BusinessClass;
			break;
		case "Y":
			result = CabinType.EconomyClass;
			break;
		case "FC":
			result = CabinType.BusinessAndFirstClass;
			break;
		}
		return result;
	}
	
	/**
	 * 通过仓位类型获取  仓位类型代码
	 * @param type
	 * @return
	 */
	public static CabinTypeCode getCabinTypeCode(CabinType type){
		CabinTypeCode result = CabinTypeCode.不限;
		switch (type) {
		case FirstClass:
			result = CabinTypeCode.头等舱;
			break;
		case BusinessClass:
			result = CabinTypeCode.公务舱;
			break;
		case EconomyClass:
			result = CabinTypeCode.经济舱;
			break;
		case BusinessAndFirstClass:
			result = CabinTypeCode.头等舱公务舱;
			break;
		}
		return result;
	}
	
	/**
	 * 通过类型code获取描述
	 * @param code
	 * @return
	 */
	public static String getDesc(int code){
		for(CabinType type:CabinType.values()){
			if(type.getCode()==code){
				return type.getDesc();
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	

}
