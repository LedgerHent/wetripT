/**
* @Title: Abstract TmcOrderLog
* @Description:  TmcOrderLog.
* @author Tangqingshan
* @date 2017-06-28 20:11:51
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;
/**
* @ClassName:  Abstract TmcOrderLog
* @Description:  TmcOrderLog.
* @author Tangqingshan 
* @date 2017-06-28 20:11:51
*/
public class TmcOrderLog implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 操作人ID
	 */
	private String operaterId;
	/**
	 * 操作人姓名
	 */
	private String operaterName;
	/**
	 * 操作人部门ID
	 */
	private String operaterDeptId;
	/**
	 * 操作人部门名称
	 */
	private String operaterDeptName;
	/**
	 * MOUDLE
	 * 	OPERATE_LOG_TYPE_1("0001600001", "预订"),
		OPERATE_LOG_TYPE_2("0001600002", "退订"),
		OPERATE_LOG_TYPE_3("0001600003", "备注"),
		OPERATE_LOG_TYPE_4("0001600004", "收款审核"),
		OPERATE_LOG_TYPE_5("0001600005", "订单确认"),
		OPERATE_LOG_TYPE_6("0001600006", "订单取消"),
		OPERATE_LOG_TYPE_7("0001600007", "支付页面回调"),
		OPERATE_LOG_TYPE_8("0001600008", "申请退单"),
		OPERATE_LOG_TYPE_9("0001600009", "下单成功"),
	 */
	private String moudle;
	/**
	 * 操作日志
	 */
	private String operatLog;
	/**
	 * 操作时间
	 */
	private Date operatTime;

	private String operatTimeStr;
	
	public void setDbid(String dbid){
	this.dbid=dbid;
	}
	public String getDbid(){
		return dbid;
	}
	public void setOrderNo(String orderNo){
	this.orderNo=orderNo;
	}
	public String getOrderNo(){
		return orderNo;
	}
	public void setOperaterId(String operaterId){
	this.operaterId=operaterId;
	}
	public String getOperaterId(){
		return operaterId;
	}
	public void setOperaterName(String operaterName){
	this.operaterName=operaterName;
	}
	public String getOperaterName(){
		return operaterName;
	}
	public void setOperaterDeptId(String operaterDeptId){
	this.operaterDeptId=operaterDeptId;
	}
	public String getOperaterDeptId(){
		return operaterDeptId;
	}
	public void setOperaterDeptName(String operaterDeptName){
	this.operaterDeptName=operaterDeptName;
	}
	public String getOperaterDeptName(){
		return operaterDeptName;
	}
	public void setMoudle(String moudle){
	this.moudle=moudle;
	}
	public String getMoudle(){
		return moudle;
	}
	public void setOperatLog(String operatLog){
	this.operatLog=operatLog;
	}
	public String getOperatLog(){
		return operatLog;
	}
	public void setOperatTime(Date operatTime){
	this.operatTime=operatTime;
	}
	public Date getOperatTime(){
		return operatTime;
	}
	public String getOperatTimeStr() {
		return operatTimeStr;
	}
	public void setOperatTimeStr(String operatTimeStr) {
		this.operatTimeStr = operatTimeStr;
	}
	
}

