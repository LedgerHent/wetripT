package com.viptrip.wetrip.model.userlogin;

import com.viptrip.wetrip.model.base.Req_NameMobileAndEmail;



/**
 * 
 * @author Administrator
 * 包含属性：ID，name,mobile,email,idType,idNum
 *
 */
@SuppressWarnings("serial")
public class UserModel extends Req_NameMobileAndEmail {
	public String uid  ;//    用户平台的id，唯一主键
	public int idType  ; //证件类型枚举（选填）数字，1=二代身份证，2=护照
	public String idNum  ; //证件号码（选填）
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

}
