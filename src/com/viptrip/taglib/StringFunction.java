package com.viptrip.taglib;

/**
 * 字符串函数
 * @author selfwhisper
 *
 */
public class StringFunction {
	/**
	 * 格式化折扣为字符串
	 * @param discount
	 * @return
	 */
	public static String formatDiscount(Double discount){
		String result = "";
		if(discount>=100){
			result = "全价";
		}else{
			result = String.format("%1.1f折", discount/10);
		}
		return result;
	}
	
	/**
	 * 价格后面的小数点00
	 * @param args
	 */
	public static String formatPrice(Double discount){
		String result = "";
		if(discount!=null){
			String s = String.valueOf(discount);
			result= s+"0";
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(formatDiscount(50D));
		System.out.println(formatPrice(50.0));
	}
}
