package com.viptrip.wetrip.model.flight;

import java.io.Serializable;

import com.viptrip.base.annotation.Description;

public class ReqData_BookAirTicket_Trip implements Serializable{
	private static final long serialVersionUID = 4121593698489999738L;
	@Description("航程编号")
	public Integer flowId;
	@Description("舱位key")
	public String mapKey;
	@Description("儿童是否按照建议预订")
	public Integer childBabySuggestBook;
	
	public Integer getFlowId() {
		return flowId;
	}
	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public Integer getChildBabySuggestBook() {
		return childBabySuggestBook;
	}
	public void setChildBabySuggestBook(Integer childBabySuggestBook) {
		this.childBabySuggestBook = childBabySuggestBook;
	}
	
	
}
