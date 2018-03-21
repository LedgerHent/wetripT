package com.viptrip.base.common;

import org.nustaq.serialization.annotations.Serialize;



public class MyEnum {

	/**
	 * 用户登录平台
	 * 
	 * @author lxd
	 * 
	 */
	@Serialize
	public enum UserPlatform {
		H5(100), 微信(200),杭州讯盟(201);

		private final int value;

		public int getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		UserPlatform(int value) {
			this.value = value;
		}
	}
	
	/**
	 * 微信用户绑定类型
	 * 
	 * @author lxd
	 * 
	 */
	public enum WechatBindState {
		已绑定(1), 未绑定(2);

		private final int value;

		public int getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		WechatBindState(int value) {
			this.value = value;
		}
	}

	/**
	 * 微信用户关注状态
	 * 
	 * @author lxd
	 * 
	 */
	public enum WechatCareState {
		已关注(1), 未关注(2);

		private final int value;

		public int getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		WechatCareState(int value) {
			this.value = value;
		}
	}

	/**
	 * 微信用户绑定类型
	 * 
	 * @author lxd
	 * 
	 */
	public enum WechatBindType {
		关注(1), 取消关注(2), 绑定(3), 取消绑定(4), 网页授权(5);
		private final int value;

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		WechatBindType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			if (value == 1)
				return "用户关注";
			else if (value == 2)
				return "用户取消关注";
			else if (value == 3)
				return "用户绑定";
			else if (value == 4)
				return "用户取消绑定";
			else if (value == 5)
				return "用户通过网页授权";
			else
				return "未知信息";
		}

	}

	/**
	 * 微信菜单枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum WechatMenuType {
		去预订(10),国内机票(11),酒店(13), 
		个人中心(20), 机票订单(21), 常旅客(22),  注册(23), 酒店订单(24),
		重定向(9999);
		private final int value;

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		WechatMenuType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public String toString() {
			switch (value) {
				case 10:
					return "去预订";
				case 11:
					return "国内机票";
				case 13:
					return "酒店";
				case 20:
					return "个人中心";
				case 21:
					return "机票订单";
				case 22:
					return "常旅客";
				case 23:
					return "注册";
				case 24:
					return "酒店订单";
				case 9999:
					return "重定向";
				default:
					return "未知信息";
			}
		}

		/**
		 * int转换成对应的菜单类型枚举
		 * @param v
		 * @return
		 */
		public static WechatMenuType getEnum(int v) {
			switch (v) {
				case 10:
					return 去预订;
				case 11:
					return 国内机票;
				case 13:
					return 酒店;
					
				case 20:
					return 个人中心;
				case 21:
					return 机票订单;
				case 22:
					return 常旅客;
				case 23:
					return 注册;
				case 24:
					return 酒店订单;
					
				case 9999:
					return 重定向;
				default:
					return null;
			}
		}
	}
	
	/**
	 * 缓存保留时限，单位秒
	 * @author lxd
	 *
	 */
	public enum CacheKeepTime{
		永久(0), 三十天(30*24*60*60), 三天(3*24*60*60), 一天(24*60*60), 
		十二小时(12*60*60),三小时(3*60*60),一小时(60*60),十分钟(10*60),五分钟(5*60),三分钟(3*60),一分钟(1*60);
		
		private final long value;

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		CacheKeepTime(long value) {
			this.value = value;
		}

		public long getValue() {
			return value;
		}
	}
	
	/**
	 * 行程类型代码枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum TripTypeCode {
		单程("OW"), 往返("RT"),联程("UN");

		private final String value;

		public String getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		TripTypeCode(String value) {
			this.value = value;
		}
	}
	/**
	 * 行程类型代码枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum TripTypeInt {
		单程(1), 往返(2),联程(3);
		
		private final int value;
		
		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		TripTypeInt(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
		public String toString(){
			switch(value){
				case 1:
					return "OW";
				case 2:
					return "RT";
				case 3:
					return "UN";
				default:
					return "OW";
			}
		}
	}
	
	/**
	 * 乘机人类型代码枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum PassengerTypeCode {
		成人("ADT"), 儿童("CHD"),婴儿("INF");

		private final String value;

		public String getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		PassengerTypeCode(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 乘机人类型代码枚举
	 * 
	 * @author wjt
	 * @说明：data.passenger[0].type	乘客类型枚举	数字，1=成人，2=儿童，3=婴儿
	 */
	public enum PassengerTypeCodeInt {
		成人(1), 儿童(2),婴儿(3);

		private final int value;

		public int getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		PassengerTypeCodeInt(int value) {
			this.value = value;
		}
	}
	/**
	 * 舱位类型代码枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum CabinTypeCode {
		不限("A"), 头等舱("F"),公务舱("C"),经济舱("Y"),头等舱公务舱("FC");

		private final String value;

		public String getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		CabinTypeCode(String value) {
			this.value = value;
		}
	}
	
	/**
	 * 乘机人类型代码枚举
	 * 
	 * @author lxd
	 * @说明：优享直减(特价折扣)SPECIALDISCOUNT
	 */
	public enum PriceAgreementTypeCode { 
		公布运价("PUBLIC"), 大客户协议价("AGREEMENT"),优享直减("SPECIALDISCOUNT"),私有运价("PRIVATE"),其他("ELSE");

		private final String value;

		public String getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		PriceAgreementTypeCode(String value) {
			this.value = value;
		}
	}
	/**
	 * 提交订单错误枚举
	 * 
	 * @author lxd
	 * 
	 */
	public enum BookErrorTypeCode {
		登陆人id不对(-1), 审核人id不对(-2),乘机人id不对(-3),乘机人的费用归属不对(-4),预付款余额不足(-5),
		乘机人类型不对(-6),乘机人证件类型不对(-7),提交订单失败(-8),参数不全(-9),航班异常订座失败(-10),
		短信发送失败(-11),订单id不对(-12),讯盟返回错误信息(-13),服务费有误(-14);

		private final Integer value;

		public Integer getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		BookErrorTypeCode(Integer value) {
			this.value = value;
		}
		@Override
		public String toString(){
			switch(value){
				case -1:
					return "登陆人id不对";
				case -2:
					return "审核人id不对";
				case -3:
					return "乘机人id不对";
				case -4:
					return "乘机人的费用归属不对";
				case -5:
					return "预付款余额不足";
				case -6:
					return "乘机人类型不对";
				case -7:
					return "乘机人证件类型不对";
				case -8:
					return "提交订单失败";
				case -9:
					return "参数不全";
				case -10:
					return "航班异常，订座失败，请联系4006020365。";
				case -11:
					return "短信发送失败";
				case -12:
					return "订单id不对";
				case -13:
					return "讯盟返回错误信息";
				case -14:
                    return "服务费有误";
				default:
					return "未知错误";
			}
		}
		public static BookErrorTypeCode getBookErrorStr(int v) {
			switch (v) {
			case -1:
				return 登陆人id不对;
			case -2:
				return 审核人id不对;
			case -3:
				return 乘机人id不对;
			case -4:
				return 乘机人的费用归属不对;
			case -5:
				return 预付款余额不足;
			case -6:
				return 乘机人类型不对;
			case -7:
				return 乘机人证件类型不对;
			case -8:
				return 提交订单失败;
			case -9:
				return 参数不全;
			case -10:
				return 航班异常订座失败;
			case -11:
				return 短信发送失败;
			case -12:
				return 订单id不对;
			case -13:
				return 讯盟返回错误信息;
			default:
				return null;
			}
		}
	}
	
	/**
	 * 证件类型代码枚举      数字 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	 * @author Administrator
	 *
	 */
	public enum IdType {
		二代身份证(1),护照(2),海员证(3),回乡证(4),军官证(5),港澳通行证(6),台胞证(7),其他(99);

		private final int value;
		
		public int getValue(){
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		IdType(int value) {
			this.value = value;
		}
		@Override
		public String toString(){
			switch(value){
				case 1:
					return "二代身份证";
				case 2:
					return "护照";
				case 3:
					return "海员证";
				case 4:
					return "回乡证";
				case 5:
					return "军官证";
				case 6:
					return "港澳通行证";
				case 7:
					return "台胞证";
				case 99:
					return "其他";
				default:
					return "未知证件";
			}
		}
		public static IdType getIdTypeStr(int v) {
			switch (v) {
			case 1:
				return 二代身份证;
			case 2:
				return 护照;
			case 3:
				return 海员证;
			case 4:
				return 回乡证;
			case 5:
				return 军官证;
			case 6:
				return 港澳通行证;
			case 7:
				return 台胞证;
			case 99:
				return 其他;
			default:
				return null;
			}
		}
	}
	
	/**
	 * 性别代码枚举
	 * @author Administrator
	 *
	 */
	public enum Sex {
		未知(0),男(1),女(2),保密(3);
		private int value;
		public int getValue(){
			return value;
		}
		
		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		Sex(int value){
			this.value = value;
		}
		
		@Override
		public String toString(){
			switch(value){
				case 0:
					return "0";
				case 1:
					return "1";
				case 2:
					return "2";
				case 3:
					return "3";
				default:
					return "-1";
			/*		case 0:
					return "未知";
				case 1:
					return "男";
				case 2:
					return "女";
				case 3:
					return "保密";
				default:
					return "未知信息";*/
			}
		}
	}
	
	/**
	 * 订单类型(国内机票("AT"), 国际机票("IA"), 国内酒店("HT"), 国际酒店("IH"), 机加酒("AH"), 火车票("TT"), 签证("VA"),租车("CT"))
	 * 
	 * @author hx
	 * 
	 */
	public enum OrderTypeEnum {
		国内机票("AT"), 国际机票("IA"),国内酒店("HT"),国际酒店("IH"),机加酒("AH"),火车票("TT"),签证("VA"),租车("CT");

		private final String value;

		public String getValue() {
			return value;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		OrderTypeEnum(String value) {
			this.value = value;
		}
	}
	
    /**
     * 1-国内机票|2-国际机票|3-国内酒店|4-国际酒店|5-火车票|6-签证|7-租车
     * 
     * @author hx
     * 
     */
    public enum BussinessType {
        国内机票(1),国际机票(2),国内酒店(3),国际酒店(4),火车票(5),签证(6),租车(7),机加酒(8);

        private final int value;

        public int getValue() {
            return value;
        }

        BussinessType(int value) {
            this.value = value;
        }
        
        public String BussinessTypeString() {
            switch (value) {
            case 1:
                return "国内机票";
            case 2:
                return "国际机票";
            case 3:
                return "国内酒店";
            case 4:
                return "国际酒店";
            case 5:
                return "火车票";
            case 6:
                return "签证";
            case 7:
                return "租车";
            case 8:
                return "机加酒";
            default:
                return "null";
            }
        }
        
        public static BussinessType getEnum(int value){
            switch (value) {
                case 1:
                    return 国内机票;
                case 2:
                    return 国际机票;
                case 3:
                    return 国内酒店;
                case 4:
                    return 国际酒店;
                case 5:
                    return 火车票;
                case 6:
                    return 签证;
                case 7:
                    return 租车;
                case 8:
                    return 机加酒;
                default:
                    return null;
            }
        }
    }
	/**
	 * 国际机票查询类型枚举
	 * @author Administrator
	 *
	 */
	public enum IntlAitTicketGetTargetFType {
		待确认类型查详情(-1),
		待确认类型查更多舱位(-2),
		待确认类型查退改签(-3),
		待确认类型下单(-4),
		
		单程查详情(1), 
		往返查返程(2), 
		往返查详情(3), 
		单程查更多舱位(4), 
		往返查更多舱位(5), 
		单程查退改签(6), 
		往返查退改签(7),
		单程下单(8),
		往返下单(9);

		private final int operateType;

		public int getOperateType() {
			return operateType;
		}

		// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
		IntlAitTicketGetTargetFType(int operateType) {
			this.operateType = operateType;
		}
	}
	
	/**
     * 国际机票查询类型枚举
     * @author Administrator
     *
     */
    public enum IntlpayMethod {
        公司月结(1),
        在线支付(2),
        线下支付(3),
        预付款支付(4);

        private final int intlPayMethod;

        public int getIntlPayMethod() {
            return intlPayMethod;
        }

        // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
        IntlpayMethod(int intlPayMethod) {
            this.intlPayMethod = intlPayMethod;
        }
    }
    
    
    /**
     * 超标类型：
        1：不是最低价超标                      
        2：金额在预定权限超标                  
        3：折扣在预定权限超标                  
        4：舱位不在预定权限超标
        5：航司不在预定权限超标               
        6：航线不在预定权限超标               
        7：航班不在预定权限超标
     * @author jh
     *
     */
    public enum Excessinfo{
        可预定(0),
        非最低价超标(1),                      
        金额不在预定权限超标(2),                  
        折扣不在预定权限超标(3),                  
        舱位不在预定权限超标(4),
        航司不在预定权限超标(5),              
        航线不在预定权限超标(6),               
        航班不在预定权限超标(7),
        提前预定天数不在预定权限超标(8),
        未设置差旅规则(9999);
         private final int operateType;
         public int getOperateType() {
             return operateType;
         }
         Excessinfo(int operateType) {
             this.operateType = operateType;
         }
    }

    // 超标准预定规则，10-不允许预定|20-提示超标允许预定|21-选择超标理由允许预定
    public enum OverproofBook{
        不允许预定 (10),提示超标允许预定(20),选择超标理由允许预定(21);
        
        private final int operateType;
        public int getOperateType() {
            return operateType;
        }
        OverproofBook(int operateType) {
            this.operateType = operateType;
        }
    }
    // 显示规则，10-隐藏|20-正常显示|21-超标显示
    public enum OverproofShow{
        隐藏(10),正常显示(20),超标显示(21);
        
        private final int value;
        public int getValue() {
            return value;
        }
        OverproofShow(int value) {
            this.value = value;
        }
    }
    
    //超标准审批规则，0-默认|1-需要审批|2-无需审批
    public enum OverproofAudit{
        默认(0),需要审批(1),无需审批(2);
        
        private final int operateType;
        public int getOperateType() {
            return operateType;
        }
        OverproofAudit(int operateType) {
            this.operateType = operateType;
        }
    }
    //差旅规则类型，0-未开启|1-按金额|2-按折扣|3-按舱位
    public enum PolicyType{
        未开启(0),按金额(1),按折扣(2),按舱位(3);
        
        private final int operateType;
        public int getOperateType() {
            return operateType;
        }
        PolicyType(int operateType) {
            this.operateType = operateType;
        }
    }
    //差旅规则类型二期变量有改动.差旅规则类型:1-按金额|2-按折扣|3-最低价|4-提前预定天数|101-按舱位|111-按舱位等级
    public enum PolicyType_v2{
    	按金额(1),按折扣(2),最低价(3),提前预定天数(4),按舱位(101),按舱位等级(111);
        
        private final int value;
        public int getValue() {
            return value;
        }
        PolicyType_v2(int value) {
            this.value = value;
        }
        
    }
    //差旅规则类型二期变量有改动.差旅规则类型:1-按金额|2-按折扣|3-最低价|4-提前预定天数|101-按舱位|111-按舱位等级
    public enum PolicyType_String{
    	按金额("金额超标",1),按折扣("折扣超标",2),最低价("最低价超标",3),提前预定天数("提前预定天数超标",4),按舱位("舱位超标",101),按舱位等级("舱位等级超标",111);
    	
    	 private String name;
         private int index;
         private PolicyType_String(String name, int index) {
             this.name = name;
             this.index = index;
         }
         public static String getName(int index) {
             for (ExcessinfoString c : ExcessinfoString.values()) {
                 if (c.getIndex() == index) {
                     return c.name;
                 }
             }
             return null;
         }
         public int getIndex() {
             return index;
         }
         public String getName() {
             return name;
         }
    }
    //差旅规则类型二期变量有改动.详情类型 10-航线规则|20-航司规则|30-航班规则|40-舱位规则|41-舱位等级规则
    public enum RuleitemsType_String{
    	航线规则("航线规则超标",10),航司规则("航司规则超标",20),航班规则("航班规则超标",30),舱位规则("舱位规则超标",40),舱位等级规则("舱位等级规则超标",41);
    	
    	private String name;
    	private int index;
    	private RuleitemsType_String(String name, int index) {
    		this.name = name;
    		this.index = index;
    	}
    	public static String getName(int index) {
    		for (ExcessinfoString c : ExcessinfoString.values()) {
    			if (c.getIndex() == index) {
    				return c.name;
    			}
    		}
    		return null;
    	}
    	public int getIndex() {
    		return index;
    	}
    	public String getName() {
    		return name;
    	}
    }
    
    //未匹配到规则的默认值，1-超标|2-不超标 overproofdefault
    public enum  Overproofdefault_v2{
        超标(1),不超标(2);
        private final int value;
        public int getValue() {
            return value;
        }
        Overproofdefault_v2(int value) {
            this.value = value;
        }
    }
    
    public enum isOpenType{
        关闭(0),开启(1);
        
        private final int operateType;
        public int getOperateType() {
            return operateType;
        }
        isOpenType(int operateType) {
            this.operateType = operateType;
        }
    }
    //企业规则类型，1-按企业|2-按分组|3-按部门|4-按员工
    public enum OrgRuleType{
        按企业(1),按分组(2),按部门(3),按员工(4);
        private final int operateType;
        public int getOperateType() {
            return operateType;
        }
        OrgRuleType(int operateType) {
            this.operateType = operateType;
        }
    }

    public enum ExcessinfoString{
        预定("可预定",0),
        非最低价("最低价超标.",1),                      
        金额不在预定权限("金额超标.",2),                  
        折扣不在预定权限("折扣超标.",3),                  
        舱位不在预定权限("舱位超标.",4),
        航司不在预定权限("舱位超标.",5),              
        航线不在预定权限("所选航线不符合该企业下的差旅预订规则.",6),               
        航班不在预定权限("所选航班不符合该企业下的差旅预订规则.",7),
        提前预定天数不在预定权限("提前预订天数超标.",8),
        未设置("默认规则超标",9999);
        private String name;
        private int index;
        private ExcessinfoString(String name, int index) {
            this.name = name;
            this.index = index;
        }
        public static String getName(int index) {
            for (ExcessinfoString c : ExcessinfoString.values()) {
                if (c.getIndex() == index) {
                    return c.name;
                }
            }
            return null;
        }
        public int getIndex() {
            return index;
        }
        public String getName() {
            return name;
        }
    }
    
    
    /**
     * 积分来源
     * @author hx
     * 积分来源 (0：积分完善   1：机票订单成交发放积分   2：酒店订单成交发放积分   3：机加酒订单成交发放积分    4：签到发放积分   5：过期直减积分    6：订单审核未通过或者取消或删除发放积分   -1 ：机票提交订单直减积分   -2：酒店提交订单直减积分  -3：机加酒订单成交直减积分)
     */
    public enum IntegralType{
    	积分完善(0),机票订单成交发放积分(1),酒店订单成交发放积分(2),机加酒订单成交发放积分(3),签到发放积分(4),过期直减积分(5),订单审核未通过或者取消或删除发放积分(6),机票提交订单直减积分(-1),酒店提交订单直减积分(-2),机加酒订单成交直减积分(-3),
        查询全部(1000);
        private final Integer value;
        
        public Integer getValue() {
            return value;
        }
        IntegralType(Integer value) {
            this.value = value;
        }
        public String IntegralTypeString() {
			switch (value) {
			case 0:
				return "积分完善";
			case 1:
				return "机票订单成交发放积分";
			case 2:
				return "酒店订单成交发放积分";
			case 3:
				return "机加酒订单成交发放积分";
			case 4:
				return "签到发放积分";
			case 5:
				return "过期直减积分";
			case 6:
				return "订单审核未通过或者取消或删除发放积分";
			case -1:
				return "机票提交订单直减积分";
			case -2:
				return "酒店提交订单直减积分";
			case -3:
				return "机加酒订单成交直减积分";
			case -100:
				return "查询全部";
			default:
				return null;
			}
		}
		
		public static IntegralType getEnum(int value){
			switch (value) {
			case 0:
				return 积分完善;
			case 1:
				return 机票订单成交发放积分;
			case 2:
				return 酒店订单成交发放积分;
			case 3:
				return 机加酒订单成交发放积分;
			case 4:
				return 签到发放积分;
			case 5:
				return 过期直减积分;
			case 6:
				return 订单审核未通过或者取消或删除发放积分;
			case -1:
				return 机票提交订单直减积分;
			case -2:
				return 酒店提交订单直减积分;
			case -3:
				return 机加酒订单成交直减积分;
			case -100:
				return 查询全部;
			default:
				return null;
			}
		}
    }
    public enum CabinType{
    	头等舱(1),公务舱(2),经济舱(3),超值经济舱(4),不限(5);
        private final int cabinType;
        public int getCabinType() {
            return cabinType;
        }
        CabinType(int cabinType) {
            this.cabinType = cabinType;
        }
        public static int getIntValue(String type) {
			switch (type) {
			case "1":
				return 3;
			case "2":
				return 2;
			case "3":
				return 1;
			case "4":
				return 4;
			}
			return Integer.valueOf(type);
		}
		
		public static CabinType getCabinType(int cabinType){
			switch (cabinType) {
			case 1:
				return 头等舱;
			case 2:
				return 公务舱;
			case 3:
				return 经济舱;
			case 4:
				return 超值经济舱;
			case 5:
				return 不限;
			default:
				return null;
			}
		}
    }
    public static void main(String[] args) {
		System.out.println(CabinType.不限.getCabinType());
	}
}
