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
import javax.persistence.Transient;



/**
 * TAcOrgBalance entity. @author MyEclipse Persistence Tools
 * 酒店预付款管理表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "T_AC_ORG_BALANCE")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class TAcOrgBalance  implements java.io.Serializable {

	// Fields

	private Integer id;
	private Long orgid;
	private String orgname;
	private String orderNo;
	private String flowStatus;
	private Double money;
	private Long userId;
	private String userName;
	private Date operatingDate;
	private String note;
	private String updateUserName;
	private Long updateUserId;
	private Date modifyDate;
	private int isModify;
	private String ticketno;
	
	//查询条件：开始时间（非数据库字段）
	private String beginDate;
	//查询条件：开始时间
	private String endDate;
		

	// Constructors

	/** default constructor */
	public TAcOrgBalance() {
	}

	/** full constructor */
	public TAcOrgBalance(Long orgid, String orgname, String orderNo,
			String flowStatus, Double money, Long userId, String userName,
			Date operatingDate, String note) {
		this.orgid = orgid;
		this.orgname = orgname;
		this.orderNo = orderNo;
		this.flowStatus = flowStatus;
		this.money = money;
		this.userId = userId;
		this.userName = userName;
		this.operatingDate = operatingDate;
		this.note = note;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_T_AC_ORG_BALANCE")
	@SequenceGenerator(name="SEQ_T_AC_ORG_BALANCE",allocationSize=1,initialValue=1, sequenceName="SEQ_T_AC_ORG_BALANCE")
	@Column(name = "ID", unique = true, nullable = false, precision = 22, scale = 0)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ORGID", precision = 10, scale = 0)
	public Long getOrgid() {
		return this.orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	@Column(name = "ORGNAME", length = 150)
	public String getOrgname() {
		return this.orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	@Column(name = "ORDER_NO", length = 15)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "FLOW_STATUS", length = 20)
	public String getFlowStatus() {
		return this.flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}

	@Column(name = "MONEY", precision = 10)
	public Double getMoney() {
		return this.money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	@Column(name = "USER_ID", precision = 10, scale = 0)
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME", length = 20)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATING_DATE", length = 7)
	public Date getOperatingDate() {
		return this.operatingDate;
	}

	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}

	@Column(name = "NOTE", length = 500)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Transient
	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	@Transient
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Column(name = "UPDATE_USER_NAME", length = 20)
	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	@Column(name = "UPDATE_USER_ID", precision = 10, scale = 0)
	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFY_DATE", length = 7)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name = "IS_MODIFY", precision = 22, scale = 0)
	public int getIsModify() {
		return isModify;
	}

	public void setIsModify(int isModify) {
		this.isModify = isModify;
	}

	public String getTicketno() {
		return ticketno;
	}
	@Column(name = "TICKETNO",length = 500)
	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}

}