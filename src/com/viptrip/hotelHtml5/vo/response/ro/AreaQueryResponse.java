package com.viptrip.hotelHtml5.vo.response.ro;

import java.io.Serializable;

public class AreaQueryResponse implements Serializable {

	private Long areaId;// 区域id

	private String cnName;// 区域名称

	private String cnShortname;// 名称（中简称）

	private String enName;// 名称（英）

	private String enShortname;// 名称（英简称）

	private String alias;// 别名

	private String pyName;// 名称全拼

	private String abName;// 首字母缩写

	private Integer lv;// 级别(1-4)

	private Long parentId;// 父级id
	

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getCnShortname() {
		return cnShortname;
	}

	public void setCnShortname(String cnShortname) {
		this.cnShortname = cnShortname;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getEnShortname() {
		return enShortname;
	}

	public void setEnShortname(String enShortname) {
		this.enShortname = enShortname;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPyName() {
		return pyName;
	}

	public void setPyName(String pyName) {
		this.pyName = pyName;
	}

	public String getAbName() {
		return abName;
	}

	public void setAbName(String abName) {
		this.abName = abName;
	}

	public Integer getLv() {
		return lv;
	}

	public void setLv(Integer lv) {
		this.lv = lv;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
