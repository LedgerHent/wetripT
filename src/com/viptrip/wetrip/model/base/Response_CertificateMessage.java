package com.viptrip.wetrip.model.base;

@SuppressWarnings("serial")
public class Response_CertificateMessage extends Response_Base{
	
	public Integer type;//证件类型 1-二代身份证|2-护照|3-海员证|4-回乡证|5-军官证|6-港澳通行证|7-台胞证|99-其他
	
	public String num;//证件号码

	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((num == null) ? 0 : num.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Response_CertificateMessage other = (Response_CertificateMessage) obj;
		if (num == null) {
			if (other.num != null)
				return false;
		} else if (!num.equals(other.num))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	

}
