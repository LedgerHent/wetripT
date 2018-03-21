package com.viptrip.warning.resource;

import java.util.Arrays;
import java.util.List;


public class MEnum {
	/**
	 * 额度操作类型
	 * @author selfwhisper
	 *
	 */
	public enum Otype{
		初始额度(0,"初始额度"),
		手动调额(1,"手动调额"),
		临时调额(2,"临时调额"),
		消费调额(3,"消费调额"),
		退款调额(4,"退款调额"),
		月结回正(5,"月结回正"),
		临额失效(6,"临额失效"),
		额度校正(7,"额度校正");
		
		private int code;
		private String desc;
		
		private Otype(int code,String desc){
			this.code = code;
			this.desc = desc;
		}
		
		public static List<Otype> list(){
			return Arrays.asList(Otype.values());
		}
		
		public String desc(){
			return this.desc;
		}
		
		public int code(){
			return this.code;
		}
		
		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		/**
		 * 根据code获取对应的权限码
		 * @param code
		 * @return
		 */
		public static Otype asOtype(int code){
			Otype result = null;
			for(Otype pc:Otype.values()){
				if(pc.code() == code){
					result = pc;
					break;
				}
			}
			return result;
		}
	}
	
	/**
	 * 通知状态
	 * @author selfwhisper
	 *
	 */
	public enum WarningStatus{
		初始状态(0,"初始状态"),
		已全局通知(1,"已全局通知"),
		已企业通知(2,"已企业通知"),
		全局和企业通知(3,"全局和企业通知");
		
		private int code;
		private String desc;
		
		private WarningStatus(int code,String desc){
			this.code = code;
			this.desc = desc;
		}
		
		public static List<WarningStatus> list(){
			return Arrays.asList(WarningStatus.values());
		}
		
		public String desc(){
			return this.desc;
		}
		
		public int code(){
			return this.code;
		}
		
		public int getCode() {
			return code;
		}
		
		public String getDesc() {
			return desc;
		}
		
		/**
		 * 根据code获取对应的权限码
		 * @param code
		 * @return
		 */
		public static WarningStatus asWarningStatus(int code){
			WarningStatus result = null;
			for(WarningStatus pc:WarningStatus.values()){
				if(pc.code() == code){
					result = pc;
					break;
				}
			}
			return result;
		}
	}
	
	
	/**
	 * 通知方式
	 * @author selfwhisper
	 *
	 */
	public enum WarningMethod{
		短信(1,"短信"),
		邮件(2,"邮件"),
		短信和邮件(3,"短信和邮件");

		private int code;
		private String desc;

		private WarningMethod(int code,String desc){
			this.code = code;
			this.desc = desc;
		}

		public static List<WarningMethod> list(){
			return Arrays.asList(WarningMethod.values());
		}

		public String desc(){
			return this.desc;
		}

		public int code(){
			return this.code;
		}

		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		/**
		 * 根据code获取对应的权限码
		 * @param code
		 * @return
		 */
		public static WarningMethod asWarningMethod(Integer code){
			WarningMethod result = null;
			if(null!=code){
				for(WarningMethod pc:WarningMethod.values()){
					if(pc.code() == code.intValue()){
						result = pc;
						break;
					}
				}
			}
			return result;
		}
	}

	/**
	 * 通知方式
	 * @author selfwhisper
	 *
	 */
	public enum State{
		成功(1,"成功"),
		失败(2,"失败"),
		未知(3,"未知");

		private int code;
		private String desc;

		private State(int code,String desc){
			this.code = code;
			this.desc = desc;
		}

		public static List<State> list(){
			return Arrays.asList(State.values());
		}

		public String desc(){
			return this.desc;
		}

		public int code(){
			return this.code;
		}

		public int getCode() {
			return code;
		}

		public String getDesc() {
			return desc;
		}

		/**
		 * 根据code获取对应的权限码
		 * @param code
		 * @return
		 */
		public static State asState(Integer code){
			State result = null;
			if(null!=code){
				for(State pc: State.values()){
					if(pc.code() == code.intValue()){
						result = pc;
						break;
					}
				}
			}
			return result;
		}
	}




}
