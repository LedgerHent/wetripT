/**
* @Title: Abstract TmcOrderGuest
* @Description:  TmcOrderGuest.
* @author Tangqingshan
* @date 2017-06-28 20:11:25
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

import java.util.Date;

/**
* @ClassName:  Abstract TmcOrderGuest
* @Description:  TmcOrderGuest.
* @author Tangqingshan 
* @date 2017-06-28 20:11:25
*/
public class TmcOrderGuest implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 入住人名称
	 */
	private String guestName;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 证件类型
	 */
	private String certType;
	/**
	 * 证件号
	 */
	private String certNo;
	/**
	 * 年龄  成人为0
	 */
	private int guestAge;
	/**
	 * 入住人类型（内宾外宾）
	 */
	private String guestType;
	/**
	 * ID
	 */
	private String guestId;
	/**
	 * 房间号
	 */
	private String roomNo;
	/**
	 * 生日
	 */
	private Date birthday;
	
	/**
	 * 
	 */
	private String birthdayStr;
	
	/**
	 * 所属部门CODE
	 */
	private String deptId;
	/**
	 * 所属部门名称
	 */
	private String deptName;
	/**
	 * 年龄类型 1、成人 2、儿童
	 */
	private String ageType;
	
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
	public void setGuestName(String guestName){
	this.guestName=guestName;
	}
	public String getGuestName(){
		return guestName;
	}
	public void setTel(String tel){
	this.tel=tel;
	}
	public String getTel(){
		return tel;
	}
	public void setEmail(String email){
	this.email=email;
	}
	public String getEmail(){
		return email;
	}
	public void setCertType(String certType){
	this.certType=certType;
	}
	public String getCertType(){
		return certType;
	}
	public void setCertNo(String certNo){
	this.certNo=certNo;
	}
	public String getCertNo(){
		return certNo;
	}
	public void setGuestType(String guestType){
	this.guestType=guestType;
	}
	public String getGuestType(){
		return guestType;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	public int getGuestAge() {
		return guestAge;
	}
	public void setGuestAge(int guestAge) {
		this.guestAge = guestAge;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAgeType() {
		return ageType;
	}
	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}
	
}

