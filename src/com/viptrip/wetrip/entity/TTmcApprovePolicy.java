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


@Entity
@Table(name = "T_TMC_APPROVE_POLICY")
@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate = true)
public class TTmcApprovePolicy implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1335500144381445513L;
	private Long policyId;
	private String policuName;
	private String memo;
	private Long createUserId;
	private String createUserName;
	private Date createTime;
	private Long lastUpdUserId; 
	private String lastUpdUserName;
	private Date lastUpdTime;
	private Long enterId;
	private String enterName;
	private Long approveType;
	private Long approveMode;
	private Long approveRequire;
	private Long displayOrder;
	private Long state;
	
	@Id
	@Column(name = "APPROVE_POLICY_ID", unique = true, nullable = false, precision = 10, scale = 0)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPROVE_SEQ")
	@SequenceGenerator(name="APPROVE_SEQ",allocationSize=1, sequenceName="APPROVE_SEQ")
	public Long getPolicyId() {
		return policyId;
	}
	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}
	
	@Column(name = "APPROVE_POLICY_NAME", length = 200)
	public String getPolicuName() {
		return policuName;
	}
	public void setPolicuName(String policuName) {
		this.policuName = policuName;
	}
	@Column(name="MEMO",length=2000)
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	@Column(name="CREATE_USER_ID",length=12)
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	
	@Column(name="CREATE_USER_NAME",length=32)
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME",length=32)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="LAST_UPD_USER_ID",length=12)
	public Long getLastUpdUserId() {
		return lastUpdUserId;
	}
	public void setLastUpdUserId(Long lastUpdUserId) {
		this.lastUpdUserId = lastUpdUserId;
	}
	@Column(name="LAST_UPD_USER_NAME",length=32)
	public String getLastUpdUserName() {
		return lastUpdUserName;
	}
	public void setLastUpdUserName(String lastUpdUserName) {
		this.lastUpdUserName = lastUpdUserName;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_UPD_DATE",length=32)
	public Date getLastUpdTime() {
		return lastUpdTime;
	}
	public void setLastUpdTime(Date lastUpdTime) {
		this.lastUpdTime = lastUpdTime;
	}
	@Column(name="ENTERP_ID",length=12)
	public Long getEnterId() {
		return enterId;
	}
	public void setEnterId(Long enterId) {
		this.enterId = enterId;
	}
	@Column(name="ENTERP_NAME",length=512)
	public String getEnterName() {
		return enterName;
	}
	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}
	
	@Column(name="APPROVE_TYPE",length=5)
	public Long getApproveType() {
		return approveType;
	}
	public void setApproveType(Long approveType) {
		this.approveType = approveType;
	}
	
	@Column(name="APPROVE_MODE",length=5)
	public Long getApproveMode() {
		return approveMode;
	}
	public void setApproveMode(Long approveMode) {
		this.approveMode = approveMode;
	}
	@Column(name="APPROVE_REQUIRE",length=5)
	public Long getApproveRequire() {
		return approveRequire;
	}
	public void setApproveRequire(Long approveRequire) {
		this.approveRequire = approveRequire;
	}
	
	@Column(name="DISPLAY_ORDER",length=5)
	public Long getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Long displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	@Column(name="STATE",length=5)
	public Long getState() {
		return state;
	}
	public void setState(Long state) {
		this.state = state;
	}

}
