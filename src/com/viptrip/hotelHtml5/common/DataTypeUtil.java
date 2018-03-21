package com.viptrip.hotelHtml5.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 /** 
 * @ClassName: DataTypeUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhaojian
 * @date 2017年6月29日 上午11:42:19 
 *
 */
public enum DataTypeUtil {

	/************************** ：00001 差旅政策提醒方式 **************************/
	TRIPPOLICY_REMIND_TYPE("00001", "差旅政策提醒方式"), 
	TRIPPOLICY_REMIND_TYPE_1("0000100001", "仅提醒"), 
	TRIPPOLICY_REMIND_TYPE_2("0000100002", "禁止预订"), 
	TRIPPOLICY_REMIND_TYPE_3("0000100003", "超标审批"),
	TRIPPOLICY_REMIND_TYPE_4("0000100004", "无"),
	
	/************************** ：00002  城市等级 **************************/
	CITY_DEGREE("00002", "城市等级"),
	CITY_DEGREE_1("0000200001", "一级"),
	CITY_DEGREE_2("0000200002", "二级"),
	CITY_DEGREE_3("0000200003", "三级"),
	CITY_DEGREE_4("0000200004", "四级"),
	CITY_DEGREE_5("0000200005", "五级"),
	CITY_DEGREE_6("0000200006", "六级"),
	CITY_DEGREE_7("0000200007", "七级"),
	CITY_DEGREE_8("0000200008", "八级"),
	CITY_DEGREE_9("0000200009", "九级"),
	CITY_DEGREE_10("0000200010", "其他"),

	/************************** ：00003  酒店星级 **************************/
	HOTEL_STARS("00003", "酒店星级"),
	HOTEL_STARS_5("0000300005", "五星级"),
	HOTEL_STARS_4("0000300004", "四星级"),
	HOTEL_STARS_3("0000300003", "三星级"),
	HOTEL_STARS_2("0000300002", "二星级"),
	HOTEL_STARS_1("0000300001", "一星级"),
	HOTEL_STARS_0("0000300000", "无星级"),
	

	/************************** ：00004  订单状态 **************************/
	ORDER_STATUS("00004", "订单状态"),
	ORDER_STATUS_1("0000400001", "待审核"),
	ORDER_STATUS_2("0000400002", "待支付"),
	ORDER_STATUS_3("0000400003", "待确认"),
	ORDER_STATUS_4("0000400004", "订单完成"),
	ORDER_STATUS_5("0000400005", "订单取消"),
	//@Deprecated
	//ORDER_STATUS_6("0000400006", "确认失败"),
	ORDER_STATUS_7("0000400007", "待退款"),
	//ORDER_STATUS_8("0000400008", "取消中"),
	ORDER_STATUS_9("0000400009", "退订中"),
	ORDER_STATUS_10("0000400010", "退款完成"),
	ORDER_STATUS_11("0000400011", "结算完成"),
	//@Deprecated
	//ORDER_STATUS_12("0000400012", "退订失败"),
	//ORDER_STATUS_13("0000400013", "确认超时"),
	//ORDER_STATUS_14("0000400014", "取消失败"),
	/**
	 * 查询订单状态是“订单完成”的订单，且入住日期是在当前系统日期之后的订单
	 */
	ORDER_STATUS_15("0000400015", "未出行订单"),
	   
	/************************** ：00005  审核状态 **************************/
	REVIEW_STATUS("00005", "审核状态"),
	REVIEW_STATUS_1("0000500001", "未审核"),
	REVIEW_STATUS_2("0000500002", "审核通过"),
	REVIEW_STATUS_3("0000500003", "审核驳回"),
	
	/************************** ：00006  支付状态 **************************/
	PAYMENT_STATUS("00006", "支付状态"),
	PAYMENT_STATUS_1("0000600001", "未支付"),
	PAYMENT_STATUS_2("0000600002", "支付失败"),
	PAYMENT_STATUS_3("0000600003", "支付成功"),
	PAYMENT_STATUS_4("0000600004", "退款失败"),
	PAYMENT_STATUS_5("0000600005", "退款成功"),

	/************************** ：00007 出行类型 **************************/
	TRIP_TYPE("00007", "出行类型"),
	TRIP_TYPE_1("0000700001", "因公出行"),
	TRIP_TYPE_2("0000700002", "因私出行"),

	/************************** ：00008 客人类型 **************************/
	GUEST_TYPE("00008", "宾客类型"),
	GUEST_TYPE_1("0000800001", "内宾"),
	GUEST_TYPE_2("0000800002", "外宾"),

	/************************** ：00009  支付方式 **************************/
	PAY_TYPE("00009", "支付方式"),
	PAY_TYPE_1("0000900001", "个人支付"),
	PAY_TYPE_2("0000900002", "公司月结"),
	PAY_TYPE_3("0000900003", "公司现结"),
	PAY_TYPE_4("0000900004", "公司预付"),
	
	/************************** ：00010  酒店种类 **************************/
	HOTEL_TYPE("00010", "酒店种类"),
	HOTEL_TYPE_1("0001000001", "国内"),
	HOTEL_TYPE_2("0001000002", "国际"),
	HOTEL_TYPE_3("0001000003", "城市"),
	
	/************************** ：00011 日期类型 **************************/
	DATE_TYPE("00011","日期类型"),
	DATE_TYPE_1("0001100001","入住日期"),
	DATE_TYPE_2("0001100002","离店日期"),
	DATE_TYPE_3("0001100003","预订日期"),
	DATE_TYPE_4("0001100004","财务审核日期"),

	/************************** ：00012 差旅规则类型 **************************/
	TRIP_RULE_TYPE("00012","差旅规则类型"),
	TRIP_RULE_TYPE_1("0001200001","企业"),
	TRIP_RULE_TYPE_2("0001200002","部门"),
	TRIP_RULE_TYPE_3("0001200003","组"),
	TRIP_RULE_TYPE_4("0001200004","员工"),
	
	/************************** ：00013 证件类型类型 **************************/
	CARD_TYPE("00013", "证件类型"),
	CARD_TYPE_1("0001300001", "身份证"),
	CARD_TYPE_2("0001300002", "护照"),
	
	/************************** ：00014 登录用户类型 **************************/
	LOGIN_USER_TYPE("00014", "登录用户类型"),
	LOGIN_USER_TYPE_1("0001400001", "企业用户"),
	LOGIN_USER_TYPE_2("0001400002", "TMC客服"),
	
	/************************** ：00015 费用类型**************************/
	CHARGE_TYPE("00015", "费用类型"),
	CHARGE_TYPE_1("0001500001", "金额"),
	CHARGE_TYPE_2("0001500002", "比例"),
	
	/************************** ：00016  操作日志类型（即功能模块）**************************/
	OPERATE_LOG_TYPE("00016", "操作日志类型"),
	OPERATE_LOG_TYPE_1("0001600001", "预订"),
	OPERATE_LOG_TYPE_2("0001600002", "退订"),
	OPERATE_LOG_TYPE_3("0001600003", "备注"),
	OPERATE_LOG_TYPE_4("0001600004", "收款审核"),
	OPERATE_LOG_TYPE_5("0001600005", "订单确认"),
	OPERATE_LOG_TYPE_6("0001600006", "订单取消"),
	OPERATE_LOG_TYPE_7("0001600007", "支付页面回调"),
	OPERATE_LOG_TYPE_8("0001600008", "申请退单"),
	OPERATE_LOG_TYPE_9("0001600009", "下单成功"),
	
	
	/************************** ：00017 任务类型**************************/
	TASK_TYPE("00017", "任务类型"),
	TASK_TYPE_1("0001700001", "酒店确认状态查询任务"),
	TASK_TYPE_2("0001700002", "酒店取消状态查询任务"),
	TASK_TYPE_3("0001700003", "发送短信"),
	TASK_TYPE_4("0001700004", "发送邮件"),
	
	/************************** ：00018 执行状态**************************/
	HANDLE_STATUS("00018", "执行状态"),
	HANDLE_STATUS_1("0001800001", "未执行"),
	HANDLE_STATUS_2("0001800002", "执行成功"),
	HANDLE_STATUS_3("0001800003", "执行失败"),
	
	/************************** ：00019  短信类型**************************/
	SMS_TYPE("00019", "短信类型"),
	SMS_TYPE_1("0001900001", "下单成功"),
	SMS_TYPE_2("0001900002", "待审核"),
	SMS_TYPE_3("0001900003", "待支付"),
	SMS_TYPE_4("0001900004", "审核驳回/订单取消"),
	SMS_TYPE_5("0001900005", "订单确认"),
	SMS_TYPE_6("0001900006", "确认驳回/订单取消"),
	SMS_TYPE_7("0001900007", "退订完成"),
	SMS_TYPE_8("0001900008", "退订驳回/取消申请"),
	
	/************************** ：00020  员工可预订类型**************************/
	BOOKRIGHT_TYPE("00020", "员工可预订类型"),
	BOOKRIGHT_TYPE_0("0002000000", "不限制预订"),
	BOOKRIGHT_TYPE_1("0002000001", "仅允许给自己预订"),
	BOOKRIGHT_TYPE_2("0002000002", "仅允许给指定企业员工预订"),
	BOOKRIGHT_TYPE_3("0002000003", "仅允许给常旅客预订"),
	BOOKRIGHT_TYPE_4("0002000004", "允许给自己和常旅客预订"),
	
	/************************** ：00021操作人类型**************************/
	OPERATOR_TYPE("00021", "员工可预订类型"),
	OPERATOR_TYPE_1("0002100001", "客服人员"),
	OPERATOR_TYPE_2("0002100002", "跟单人员"),
	OPERATOR_TYPE_3("0002100003", "预订人员"),
	
	/************************** ：00022支付渠道**************************/
	PAY_CHANNEL("00022", "支付渠道"),
	PAY_CHANNEL_ZFB("0002200001", "支付宝支付"),
	PAY_CHANNEL_WX("0002200002", "微信支付"),
	PAY_CHANNEL_YIBO("0002200003", "易宝支付"),
	
	 /************************** ：00023  报表类型 **************************/
	REPORT_FORMS_TYPE("00023", "报表类型"),
	REPORT_FORMS_TYPE_1("0002300001", "客服绩效"),
	REPORT_FORMS_TYPE_2("0002300002", "酒店实时交易"),
	REPORT_FORMS_TYPE_3("0002300003", "酒店间夜"),
	REPORT_FORMS_TYPE_4("0002300004", "供应商利润汇总"),
	REPORT_FORMS_TYPE_5("0002300005", "酒店利润明细"),
	REPORT_FORMS_TYPE_6("0002300006", "酒店账单明细"),
	REPORT_FORMS_TYPE_7("0002300007", "供应商付款明细"),
	
	/************************** ：00024  酒店标识 **************************/
	HOTEL_FLAG("00024", "酒店标识"),
	HOTEL_FLAG_1("0002400001", "精选酒店"),
	HOTEL_FLAG_2("0002400002", "协议酒店"),
	
	/************************** ：00025  供应商结算状态(付款状态) **************************/
	SUPPLIER_BALANCE_STATE("00025", " 供应商结算状态"),
	SUPPLIER_BALANCE_STATE_1("0002500001", "未付款"),
	SUPPLIER_BALANCE_STATE_2("0002500002", "已付款"),
	/************************** ：00026  供应商结算状态(付款状态) **************************/
	SUPPLIER_BALANCE_DATETYPE("00026", " 供应商结算日期类型"),
	SUPPLIER_BALANCE_DATETYPE_1("0002600001", "付款日期"),
	SUPPLIER_BALANCE_DATETYPE_2("0002600002", "创建日期"),
	/************************** ：00027  供应商结算状态(付款状态) **************************/
	SUPPLIER_BALANCE_USERTYPE("00027", "供应商结算用户类型"),
	SUPPLIER_BALANCE_USERTYPE_1("0002700001", "付款人"),
	SUPPLIER_BALANCE_USERTYPE_2("0002700002", "创建人"),
	
	/************************** ：00028  手工单设置房间价格类型 **************************/
	ROOM_PRICE_TYPE("00028", "房间价格类型"),
	ROOM_PRICE_TYPE_1("0002800001", "房间总价"),
	ROOM_PRICE_TYPE_2("0002800002", "房间单价"),
	
	/************************** ：00029  手工单设置房间价格类型 **************************/
	ORDER_TYPE("00029", "订单类型"),
	ORDER_TYPE_1("0002900001", "预订单"),
	ORDER_TYPE_2("0002900002", "手工单"),
	
	/************************** ：00030 未匹配到差旅政策-酒店超规显示规则 **************************/
	HOTEL_SHOW_TYPE("00030", "酒店显示规则"), 
	HOTEL_SHOW_TYPE_1("0003000001", "隐藏"), 
	HOTEL_SHOW_TYPE_2("0003000002", "正常显示"), 
	HOTEL_SHOW_TYPE_3("0003000003", "超标显示"),
	HOTEL_SHOW_TYPE_4("0003000004", "无"),
	
	/************************** ：00031  未匹配到差旅政策默认值 **************************/
	POLICY_DEFAULT("00031", "未匹配到差旅政策默认值 "), 
	POLICY_DEFAULT_1("0003100001", "超标"), 
	POLICY_DEFAULT_2("0003200002", "不超标"), 
	POLICY_DEFAULT_3("0003300003", "无"),
	;
	
	private DataTypeUtil(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private String key;
	private String value;

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	/************************** 公共方法 **************************/

	/**
	 * getDataTypeName:根据字典子项编号，获取字典子项名称
	 * 
	 * @author ZhangTao
	 * @param dataItemType
	 * @return
	 */
	public static String getDataTypeName(String dataItemType) {
		for (DataTypeUtil data : DataTypeUtil.values()) {
			if (data.key.equals(dataItemType)) {
				return data.value;
			}
		}
		return dataItemType;
	}

	/**
	 * getDataListBy:根据字典项编码，获取所有子项
	 * 
	 * @author ZhangTao
	 * @param dataType
	 * @return
	 */
	public static List<String[]> getDataListBy(String dataType) {
		List<String[]> resultList = new ArrayList<String[]>();
		for (DataTypeUtil data : DataTypeUtil.values()) {
			if (data.key.startsWith(dataType)) {
				if (dataType.length() == 5) {
					if (data.key.length() == 10) {
						String[] d = new String[2];
						d[0] = data.key;
						d[1] = data.value;
						resultList.add(d);
					}
				} else if (dataType.length() == 10) {
					if (data.key.length() == 15) {
						String[] d = new String[2];
						d[0] = data.key;
						d[1] = data.value;
						resultList.add(d);
					}
				}
			}
		}
		return resultList;
	}

	/**
	 * getDataListBy:根据字典项编码，获取所有子项
	 * 
	 * @author wangyi
	 * @param dataType
	 *            ，valueField，textField
	 * @return List<Map<String,String>>
	 */
	public static List<Map<String, String>> getDataListMapBy(String dataType, String valueField, String textField) {
		List<Map<String, String>> resultList = new ArrayList<Map<String, String>>();
		for (DataTypeUtil data : DataTypeUtil.values()) {
			if (data.key.startsWith(dataType)) {
				if (dataType.length() == 5) {
					if (data.key.length() == 10) {
						Map<String, String> m = new HashMap<String, String>();
						m.put(valueField, data.key);
						m.put(textField, data.value);
						resultList.add(m);
					}
				} else if (dataType.length() == 10) {
					if (data.key.length() == 15) {
						Map<String, String> m = new HashMap<String, String>();
						m.put(valueField, data.key);
						m.put(textField, data.value);
						resultList.add(m);
					}
				}
			}
		}
		return resultList;
	}
	
}
