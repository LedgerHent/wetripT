package com.viptrip.wetrip.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TAcClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AC_CLASS")
public class TAcClass implements java.io.Serializable {

	// Fields

	private Long cid;
	private int classtype;
	private String classname;
	private String aircompany;
	private String isfull;
	private String classcode;
	private Date createTime;
	private String createBy;
	private Date lastModifyTime;
	private String lastModifyBy;

	// Constructors

	/** default constructor */
	public TAcClass() {
	}

	/** minimal constructor */
	public TAcClass(int classtype, String classname, String aircompany,
			String isfull, String classcode) {
		this.classtype = classtype;
		this.classname = classname;
		this.aircompany = aircompany;
		this.isfull = isfull;
		this.classcode = classcode;
	}

	/** full constructor */
	public TAcClass(int classtype, String classname, String aircompany,
			String isfull, String classcode, Date createTime,
			String createBy, Date lastModifyTime, String lastModifyBy) {
		this.classtype = classtype;
		this.classname = classname;
		this.aircompany = aircompany;
		this.isfull = isfull;
		this.classcode = classcode;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_AC_CLASS")
	@SequenceGenerator(name="SEQ_AC_CLASS", allocationSize=1,initialValue=1,sequenceName="SEQ_AC_CLASS")
	@Column(name = "CID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	@Column(name = "CLASSTYPE", nullable = false, precision = 1, scale = 0)
	public int getClasstype() {
		return this.classtype;
	}

	public void setClasstype(int classtype) {
		this.classtype = classtype;
	}

	@Column(name = "CLASSNAME", nullable = false, length = 400)
	public String getClassname() {
		return this.classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Column(name = "AIRCOMPANY", nullable = false, length = 5)
	public String getAircompany() {
		return this.aircompany;
	}

	public void setAircompany(String aircompany) {
		this.aircompany = aircompany;
	}

	@Column(name = "ISFULL", nullable = false, length = 1)
	public String getIsfull() {
		return this.isfull;
	}

	public void setIsfull(String isfull) {
		this.isfull = isfull;
	}

	@Column(name = "CLASSCODE", nullable = false, length = 10)
	public String getClasscode() {
		return this.classcode;
	}

	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_BY", length = 40)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFY_TIME")
	public Date getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	@Column(name = "LAST_MODIFY_BY", length = 40)
	public String getLastModifyBy() {
		return this.lastModifyBy;
	}

	public void setLastModifyBy(String lastModifyBy) {
		this.lastModifyBy = lastModifyBy;
	}

}