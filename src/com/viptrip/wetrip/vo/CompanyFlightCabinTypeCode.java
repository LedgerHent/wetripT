package com.viptrip.wetrip.vo;

import java.io.Serializable;

public class CompanyFlightCabinTypeCode implements Serializable{
	private static final long serialVersionUID = -5741712357788488668L;
	private Integer classtype;
	private String aircompany;
	private String classcode;
	
	
	
	
	public CompanyFlightCabinTypeCode() {
	}
	
	public CompanyFlightCabinTypeCode(Integer classtype, String aircompany,
			String classcode) {
		this.classtype = classtype;
		this.aircompany = aircompany;
		this.classcode = classcode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aircompany == null) ? 0 : aircompany.hashCode());
		result = prime * result
				+ ((classcode == null) ? 0 : classcode.hashCode());
		result = prime * result
				+ ((classtype == null) ? 0 : classtype.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyFlightCabinTypeCode other = (CompanyFlightCabinTypeCode) obj;
		if (aircompany == null) {
			if (other.aircompany != null)
				return false;
		} else if (!aircompany.equals(other.aircompany))
			return false;
		if (classcode == null) {
			if (other.classcode != null)
				return false;
		} else if (!classcode.equals(other.classcode))
			return false;
		if (classtype == null) {
			if (other.classtype != null)
				return false;
		} else if (!classtype.equals(other.classtype))
			return false;
		return true;
	}
	public Integer getClasstype() {
		return classtype;
	}
	public void setClasstype(Integer classtype) {
		this.classtype = classtype;
	}
	public String getAircompany() {
		return aircompany;
	}
	public void setAircompany(String aircompany) {
		this.aircompany = aircompany;
	}
	public String getClasscode() {
		return classcode;
	}
	public void setClasscode(String classcode) {
		this.classcode = classcode;
	}
	
	
}
