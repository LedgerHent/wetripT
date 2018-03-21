package com.viptrip.util;

public class EnumUtil {
	
	/**
	 * 短信验证码发送类型
	 * @author Administrator
	 *
	 */
	public enum SmsType{
		用户绑定(1),用户解绑(2),用户注册(3),密码重置(4),敏感信息修改(5);
		private final int value;
		public int getValue(){
			return value;
		}
		SmsType(int value) {
			this.value = value;
		}
		@Override
		public String toString() {
			switch (value) {
				case 1:
					return "用户绑定";
				case 2:
					return "用户解绑";
				case 3:
					return "用户注册";
				case 4:
					return "密码重置";
				case 5:
					return "敏感信息修改";
				default:
					return "未知信息";
			}
		}
		
		public static SmsType getEnum(int value){
			switch (value) {
				case 1:
					return 用户绑定;
				case 2:
					return 用户解绑;
				case 3:
					return 用户注册;
				case 4:
					return 密码重置;
				case 5:
					return 敏感信息修改;
				default:
					return null;
			}
		}
		
	}
	
	/**
	 * 自动出票开关状态
	 * @author jetty
	 *
	 */
    public enum TAutoissueAirlineStatus{
        关闭("0"),开启("1");
        
        private final String value;

        public String getValue() {
            return value;
        }
        // 构造器默认也只能是private, 从而保证构造函数只能在内部使用
        TAutoissueAirlineStatus(String value) {
            this.value = value;
        }
    }
}
