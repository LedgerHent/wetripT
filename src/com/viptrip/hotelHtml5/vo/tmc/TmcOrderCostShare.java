/**
* @Title: Abstract TmcOrderCostShare
* @Description:  TmcOrderCostShare.
* @author Tangqingshan
* @date 2017-06-28 20:10:52
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

/**
* @ClassName:  Abstract TmcOrderCostShare
* @Description:  TmcOrderCostShare.
* @author Tangqingshan 
* @date 2017-06-28 20:10:52
*/
public class TmcOrderCostShare implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 费用部门ID
	 */
	private String shareDeptId;
	/**
	 * 费用部门
	 */
	private String shareDeptName;
	/**
	 * 分摊类型（金额0001500001、比例0001500002）
	 */
	private String shareType;
	/**
	 * 分摊值
	 */
	private double shareValue;
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
	public void setShareDeptId(String shareDeptId){
	this.shareDeptId=shareDeptId;
	}
	public String getShareDeptId(){
		return shareDeptId;
	}
	public void setShareDeptName(String shareDeptName){
	this.shareDeptName=shareDeptName;
	}
	public String getShareDeptName(){
		return shareDeptName;
	}
	public void setShareType(String shareType){
	this.shareType=shareType;
	}
	public String getShareType(){
		return shareType;
	}
	public void setShareValue(double shareValue){
	this.shareValue=shareValue;
	}
	public double getShareValue(){
		return shareValue;
	}
}

