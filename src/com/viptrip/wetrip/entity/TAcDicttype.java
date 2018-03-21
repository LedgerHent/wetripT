package com.viptrip.wetrip.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * TAcDicttype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_AC_DICTTYPE", uniqueConstraints = @UniqueConstraint(columnNames = "DICTTYPECODE"))
public class TAcDicttype implements java.io.Serializable {

	// Fields

	private Long dicttypeid;
	private String dicttypename;
	private String dicttypedesc;
	private String dicttypecode;
	private Timestamp createTime;
	private String createBy;
	private Timestamp lastModifyTime;
	private String lastModifyBy;
	private String sysCode;
	private Set<TAcDictdeta> TAcDictdetas = new HashSet<TAcDictdeta>(0);

	// Constructors

	/** default constructor */
	public TAcDicttype() {
	}

	/** minimal constructor */
	public TAcDicttype(Long dicttypeid, String dicttypename, String dicttypecode) {
		this.dicttypeid = dicttypeid;
		this.dicttypename = dicttypename;
		this.dicttypecode = dicttypecode;
	}

	/** full constructor */
	public TAcDicttype(Long dicttypeid, String dicttypename,
			String dicttypedesc, String dicttypecode, Timestamp createTime,
			String createBy, Timestamp lastModifyTime, String lastModifyBy,
			String sysCode, Set<TAcDictdeta> TAcDictdetas) {
		this.dicttypeid = dicttypeid;
		this.dicttypename = dicttypename;
		this.dicttypedesc = dicttypedesc;
		this.dicttypecode = dicttypecode;
		this.createTime = createTime;
		this.createBy = createBy;
		this.lastModifyTime = lastModifyTime;
		this.lastModifyBy = lastModifyBy;
		this.sysCode = sysCode;
		this.TAcDictdetas = TAcDictdetas;
	}

	// Property accessors
	@Id
	@Column(name = "DICTTYPEID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getDicttypeid() {
		return this.dicttypeid;
	}

	public void setDicttypeid(Long dicttypeid) {
		this.dicttypeid = dicttypeid;
	}

	@Column(name = "DICTTYPENAME", nullable = false, length = 40)
	public String getDicttypename() {
		return this.dicttypename;
	}

	public void setDicttypename(String dicttypename) {
		this.dicttypename = dicttypename;
	}

	@Column(name = "DICTTYPEDESC", length = 400)
	public String getDicttypedesc() {
		return this.dicttypedesc;
	}

	public void setDicttypedesc(String dicttypedesc) {
		this.dicttypedesc = dicttypedesc;
	}

	@Column(name = "DICTTYPECODE", unique = true, nullable = false, length = 40)
	public String getDicttypecode() {
		return this.dicttypecode;
	}

	public void setDicttypecode(String dicttypecode) {
		this.dicttypecode = dicttypecode;
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

	@Column(name = "SYS_CODE", length = 20)
	public String getSysCode() {
		return this.sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TAcDicttype")
	public Set<TAcDictdeta> getTAcDictdetas() {
		return this.TAcDictdetas;
	}

	public void setTAcDictdetas(Set<TAcDictdeta> TAcDictdetas) {
		this.TAcDictdetas = TAcDictdetas;
	}

}