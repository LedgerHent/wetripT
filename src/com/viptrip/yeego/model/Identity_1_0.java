package com.viptrip.yeego.model;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



@XmlRootElement(name="Identity_1_0")
public class Identity_1_0 extends Request_Base{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8427531683238219265L;
	
	@XmlElement(name="Operator")
	public  String Operator;//登录操作工号
	@XmlElement(name="Pwd")
	public  String Pwd;//密码
	@XmlElement(name="PassportID")
	public  String PassportID;//身份凭证号
	@XmlElement(name="Terminal")
	public  String Terminal;//终端号
	@XmlElement(name="UserType")
	public  String UserType;//登录身份类型
	/*public String getOperator() {
		return Operator;
	}
	public void setOperator(String operator) {
		Operator = operator;
	}
	public String getPwd() {
		return Pwd;
	}
	public void setPwd(String pwd) {
		Pwd = pwd;
	}
	public String getPassportID() {
		return PassportID;
	}
	public void setPassportID(String passportID) {
		PassportID = passportID;
	}
	public String getTerminal() {
		return Terminal;
	}
	public void setTerminal(String terminal) {
		Terminal = terminal;
	}
	public String getUserType() {
		return UserType;
	}
	public void setUserType(String userType) {
		UserType = userType;
	}*/
	public Identity_1_0()
	{}
	public Identity_1_0(String Operator,String Pwd,String PassportID,String Terminal,String UserType)
	{
		this.Operator=Operator;
		this.Pwd=Pwd;
		this.PassportID=PassportID;
		this.Terminal=Terminal;
		this.UserType=UserType;
	}
	
	
}
