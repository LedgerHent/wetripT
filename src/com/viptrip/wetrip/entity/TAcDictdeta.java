package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TAcDictdeta entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AC_DICTDETA")
public class TAcDictdeta implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7477102350788803337L;
	private Long dictid;
	private TAcDicttype TAcDicttype;
	private String dictname;
	private Short sortno;
	private String status;
	private String dictcode;
	private Timestamp createTime;
	private String createBy;
	private Timestamp lastModifyTime;
	private String lastModifyBy;

	// Constructors

	/** default constructor */
	public TAcDictdeta() {
	}

	/** minimal constructor */
	public TAcDictdeta(Long dictid, TAcDicttype TAcDicttype, String dictname,
			String dictcode) {
		this.dictid = dictid;
		this.TAcDicttype = TAcDicttype;
		this.dictname = dictname;
		this.dictcode = dictcode;
	}

	/** full constructor */
	public TAcDictdeta(Long dictid, TAcDicttype TAcDicttype, String dictname,
			Short sortno, String status, String dictcode, Timestamp createTime,
			String createBy, Timestamp lastModifyTime, String lastModifyBy) {
		this.dictid = dictid;
		this.TAcDicttype = TAcDicttype;
		this.dictname = dictname;
		this.sortno = sortno;
		this.status = status;
		this.dictcode = dictcode;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
	}

	// Property accessors
	@Id
	@Column(name = "DICTID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDictid() {
		return this.dictid;
	}

	public void setDictid(Long dictid) {
		this.dictid = dictid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DICTTYPE", nullable = false)
	public TAcDicttype getTAcDicttype() {
		return this.TAcDicttype;
	}

	public void setTAcDicttype(TAcDicttype TAcDicttype) {
		this.TAcDicttype = TAcDicttype;
	}

	@Column(name = "DICTNAME", nullable = false, length = 400)
	public String getDictname() {
		return this.dictname;
	}

	public void setDictname(String dictname) {
		this.dictname = dictname;
	}

	@Column(name = "SORTNO", precision = 4, scale = 0)
	public Short getSortno() {
		return this.sortno;
	}

	public void setSortno(Short sortno) {
		this.sortno = sortno;
	}

	@Column(name = "STATUS", length = 4)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "DICTCODE", nullable = false, length = 40)
	public String getDictcode() {
		return this.dictcode;
	}

	public void setDictcode(String dictcode) {
		this.dictcode = dictcode;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CREATE_BY", length = 40)
	public String getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	@Column(name = "LAST_MODIFY_TIME", length = 7)
	public Timestamp getLastModifyTime() {
		return this.lastModifyTime;
	}

	public void setLastModifyTime(Timestamp lastModifyTime) {
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