package com.viptrip.intlAirticket.model.flightModels;

public class IntlOrderDetail_Change_Fee {
    
    public Integer psgrId;// 乘机人id 整型数字

    public Double upgrade;// 升舱费 双精度数字

    public Double update;// 改期费 双精度数字

    public Double service;// 服务费 双精度数字

	public Integer getPsgrId() {
		return psgrId;
	}

	public void setPsgrId(Integer psgrId) {
		this.psgrId = psgrId;
	}

	public Double getUpgrade() {
		return upgrade;
	}

	public void setUpgrade(Double upgrade) {
		this.upgrade = upgrade;
	}

	public Double getUpdate() {
		return update;
	}

	public void setUpdate(Double update) {
		this.update = update;
	}

	public Double getService() {
		return service;
	}

	public void setService(Double service) {
		this.service = service;
	}
    
    
}
