package com.viptrip.wetrip.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "T_COMPANYGROUP")
@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate = true)
public class TCOMPANYGROUP implements java.io.Serializable {

	private Long id ;
	//企业id
	private Long orgid;
	//企业分组
	private String companyGroup;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_COMPANYGROUP")
	@SequenceGenerator(name="SEQ_COMPANYGROUP",allocationSize=1,initialValue=1, sequenceName="SEQ_COMPANYGROUP")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "ORGID", length = 10)
	public Long getOrgid() {
		return orgid;
	}
	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}
	@Column(name = "COMPANYGROUP", length = 50)
	public String getCompanyGroup() {
		return companyGroup;
	}
	public void setCompanyGroup(String companyGroup) {
		this.companyGroup = companyGroup;
	}
	
}
