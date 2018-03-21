/**
* @Title: Abstract TmcOrderContacts
* @Description:  TmcOrderContacts.
* @author Tangqingshan
* @date 2017-07-18 19:22:01
* @version V1.0
*/
package com.viptrip.hotelHtml5.vo.tmc;

/**
* @ClassName:  Abstract TmcOrderContacts
* @Description:  TmcOrderContacts.
* @author Tangqingshan 
* @date 2017-07-18 19:22:01
*/
public class TmcOrderContacts implements java.io.Serializable {
private static final long serialVersionUID = 1L;
	private String dbid;
	private String orderNo;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 电话
	 */
	private String tel;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 序列  0表示第一人
	 */
	private long seq;
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
	public void setContact(String contact){
	this.contact=contact;
	}
	public String getContact(){
		return contact;
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
	public void setSeq(long seq){
	this.seq=seq;
	}
	public long getSeq(){
		return seq;
	}
}

