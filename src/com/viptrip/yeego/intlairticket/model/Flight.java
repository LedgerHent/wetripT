package com.viptrip.yeego.intlairticket.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Flight {
	
	public Flight() {
	}
	// <RPH> AV 编号,从航班查询 S 节点获得
	// <BoardPoint> 出发机场,从航班查询 S 节点获得
	// <OffPoint> 目的机场,从航班查询 S 节点获得
	// <DepartureDate> 出发日期,从航班查询 S 节点获得
	// <AVReference> 航班信息节点,从航班查询S 节点获得
	
	//编号,从航班查询 S 节点获得
	@XmlElement(name = "RPH")
	public String avNumber;
	
	// 出发机场,从航班查询 S 节点获得
	@XmlElement(name = "BoardPoint")
	public String orgcity;

	// 目的机场,从航班查询 S 节点获得
	@XmlElement(name = "OffPoint")
	public String detcity;

	// 出发日期,从航班查询 S 节点获得
	@XmlElement(name = "DepartureDate")
	public String starttime;

	// 航班信息节点,从航班查询S 节点获得
	@XmlElement(name = "AVReference")
	public String flightNodeMessage;

	public void setAvNumber(String avNumber) {
		this.avNumber = avNumber;
	}

	public void setOrgcity(String orgcity) {
		this.orgcity = orgcity;
	}

	public void setDetcity(String detcity) {
		this.detcity = detcity;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public void setFlightNodeMessage(String flightNodeMessage) {
		this.flightNodeMessage = flightNodeMessage;
	}
	
	

	


}
