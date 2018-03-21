package com.viptrip.register.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ENTERPRISE_MAIL_SUFFIX")
public class EnterpriseMailSuffix implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5606023977356502424L;

	private String mailsuffix; //邮箱后缀，含@
	
	private Long parentcompanyid;//母公司企业编号
	
	private Long subcompanyid; //子公司企业编号
	
	private String parentcompanyname; //母公司名称
	
	private String subcompanyname; //子公司名称

	
	

	
	public EnterpriseMailSuffix() {
	}
	public EnterpriseMailSuffix(String mailsuffix) {
		this.mailsuffix = mailsuffix;
	}

	public EnterpriseMailSuffix(String mailsuffix, Long parentcompanyid,
			Long subcompanyid, String parentcompanyname, String subcompanyname) {
		this.mailsuffix = mailsuffix;
		this.parentcompanyid = parentcompanyid;
		this.subcompanyid = subcompanyid;
		this.parentcompanyname = parentcompanyname;
		this.subcompanyname = subcompanyname;
	}

	@Id
	@Column(name = "MAILSUFFIX", unique = true, nullable = false, length = 200, scale = 0)
	public String getMailsuffix() {
		return mailsuffix;
	}

	public void setMailsuffix(String mailsuffix) {
		this.mailsuffix = mailsuffix;
	}

	@Column(name = "PARENTCOMPANYID", precision = 12)
	public Long getParentcompanyid() {
		return parentcompanyid;
	}

	public void setParentcompanyid(Long parentcompanyid) {
		this.parentcompanyid = parentcompanyid;
	}

	@Column(name = "SUBCOMPANYID", precision = 12)
	public Long getSubcompanyid() {
		return subcompanyid;
	}

	public void setSubcompanyid(Long subcompanyid) {
		this.subcompanyid = subcompanyid;
	}

	@Column(name = "PARENTCOMPANYNAME", length = 500)
	public String getParentcompanyname() {
		return parentcompanyname;
	}

	public void setParentcompanyname(String parentcompanyname) {
		this.parentcompanyname = parentcompanyname;
	}

	@Column(name = "SUBCOMPANYNAME", length = 500)
	public String getSubcompanyname() {
		return subcompanyname;
	}

	public void setSubcompanyname(String subcompanyname) {
		this.subcompanyname = subcompanyname;
	}
	
	
}
