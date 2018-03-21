package com.viptrip.common.vo.sqlmap;

import java.math.BigDecimal;
import java.util.Date;

public class ApproveAuditVO implements java.io.Serializable {
	private BigDecimal APPROVE_LEVEL;
	private BigDecimal FLOWID;
	private BigDecimal APPROVE_USER_ID;
	private String APPROVE_USER_NAME;
	private String APPROVE_USER_MOBILE;
	private String APPROVE_USER_EMAIL;
	private BigDecimal APPROVE_FLOWID;
	private BigDecimal OPERATOR_ID;
	private String OPERATOR_NAME;
	private String OPERATOR_MOBILE;
	private String OPERATOR_EMAIL;
	private String MEMO;
	private BigDecimal STATE;
	private BigDecimal APPROVE_TYPE;
	private Date APPROVE_TIME;
	
	public BigDecimal getAPPROVE_LEVEL() {
		return APPROVE_LEVEL;
	}
	public void setAPPROVE_LEVEL(BigDecimal aPPROVE_LEVEL) {
		APPROVE_LEVEL = aPPROVE_LEVEL;
	}
	public BigDecimal getFLOWID() {
		return FLOWID;
	}
	public void setFLOWID(BigDecimal fLOWID) {
		FLOWID = fLOWID;
	}
	public BigDecimal getAPPROVE_USER_ID() {
		return APPROVE_USER_ID;
	}
	public void setAPPROVE_USER_ID(BigDecimal aPPROVE_USER_ID) {
		APPROVE_USER_ID = aPPROVE_USER_ID;
	}
	public String getAPPROVE_USER_NAME() {
		return APPROVE_USER_NAME;
	}
	public void setAPPROVE_USER_NAME(String aPPROVE_USER_NAME) {
		APPROVE_USER_NAME = aPPROVE_USER_NAME;
	}
	public String getAPPROVE_USER_MOBILE() {
		return APPROVE_USER_MOBILE;
	}
	public void setAPPROVE_USER_MOBILE(String aPPROVE_USER_MOBILE) {
		APPROVE_USER_MOBILE = aPPROVE_USER_MOBILE;
	}
	public String getAPPROVE_USER_EMAIL() {
		return APPROVE_USER_EMAIL;
	}
	public void setAPPROVE_USER_EMAIL(String aPPROVE_USER_EMAIL) {
		APPROVE_USER_EMAIL = aPPROVE_USER_EMAIL;
	}
	public BigDecimal getAPPROVE_FLOWID() {
		return APPROVE_FLOWID;
	}
	public void setAPPROVE_FLOWID(BigDecimal aPPROVE_FLOWID) {
		APPROVE_FLOWID = aPPROVE_FLOWID;
	}
	public BigDecimal getOPERATOR_ID() {
		return OPERATOR_ID;
	}
	public void setOPERATOR_ID(BigDecimal oPERATOR_ID) {
		OPERATOR_ID = oPERATOR_ID;
	}
	public String getOPERATOR_NAME() {
		return OPERATOR_NAME;
	}
	public void setOPERATOR_NAME(String oPERATOR_NAME) {
		OPERATOR_NAME = oPERATOR_NAME;
	}
	public String getOPERATOR_MOBILE() {
		return OPERATOR_MOBILE;
	}
	public void setOPERATOR_MOBILE(String oPERATOR_MOBILE) {
		OPERATOR_MOBILE = oPERATOR_MOBILE;
	}
	public String getOPERATOR_EMAIL() {
		return OPERATOR_EMAIL;
	}
	public void setOPERATOR_EMAIL(String oPERATOR_EMAIL) {
		OPERATOR_EMAIL = oPERATOR_EMAIL;
	}
	public String getMEMO() {
		return MEMO;
	}
	public void setMEMO(String mEMO) {
		MEMO = mEMO;
	}
	public BigDecimal getSTATE() {
		return STATE;
	}
	public void setSTATE(BigDecimal sTATE) {
		STATE = sTATE;
	}
	public BigDecimal getAPPROVE_TYPE() {
		return APPROVE_TYPE;
	}
	public void setAPPROVE_TYPE(BigDecimal aPPROVE_TYPE) {
		APPROVE_TYPE = aPPROVE_TYPE;
	}
	public Date getAPPROVE_TIME() {
		return APPROVE_TIME;
	}
	public void setAPPROVE_TIME(Date aPPROVE_TIME) {
		APPROVE_TIME = aPPROVE_TIME;
	}	

}
