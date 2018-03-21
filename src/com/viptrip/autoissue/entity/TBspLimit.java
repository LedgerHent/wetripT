package com.viptrip.autoissue.entity;

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


@Entity
@Table(name = " T_BSP_LIMIT ")
public class TBspLimit implements java.io.Serializable{
	private Long id;//id
	private Double bspLimits;//限额
	private Date createTime;//创建时间
	private Integer business;//业务入口（1：维护修改 2：自动出票消费）
	public TBspLimit() {
	}
	public TBspLimit(Long id, Double bspLimits, Date createTime, Integer business) {
		this.id = id;
		this.bspLimits = bspLimits;
		this.createTime = createTime;
		this.business = business;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_T_BSP_LIMIT")
    @SequenceGenerator(name = "SEQ_T_BSP_LIMIT", allocationSize = 1, initialValue = 1, sequenceName = "SEQ_T_BSP_LIMIT")
	@Column(name = " ID ",unique=true,nullable=false,precision=10,scale=0 )
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name= "BSPLIMITS",length=10 )
	public Double getBspLimits() {
		return bspLimits;
	}

	public void setBspLimits(Double bspLimits) {
		this.bspLimits = bspLimits;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name= "CREATETIME",length=7 )
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column( name ="BUSINESS",length=2)
	public Integer getBusiness() {
		return business;
	}

	public void setBusiness(Integer business) {
		this.business = business;
	}
}
