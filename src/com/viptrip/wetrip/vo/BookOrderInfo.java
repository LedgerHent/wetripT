package com.viptrip.wetrip.vo;

import java.util.List;

public class BookOrderInfo {
	public String mapKey;
	public Integer traveType;
	public Integer source;//1=ios，2=android，3-微信
	public Integer childBabySuggestBook;// 是否走预订价  0：是  1：否
	public Integer payMethodId;//支付方式id
	public String payMethod;//支付方式
	public String dijiaReason;//低价理由
	public Long timestamp;//查询航班时的时间戳
	public Integer skipType;//1:添加乘机人  2：添加通知人  3：添加审核人
	public List<UserInfo> passengerInfos;//乘机人json
	public List<UserInfo> tongzhirens;//通知人json
	public List<UserInfo> checkMans;//审核人json
	public Integer flightType;
	public Integer jumpCount;//返回上一步跳转步数；
	public String excessinfo;
	
	public List<String> excessList;//
	public String tripexcessinfo;//去程
	public String retripexcessinfo;//回程
	
	public String quXuanzeReason;
	public String quTianxieReason;
	public String fanXuanzeReason;
	public String fanTianxieReason;
	
    
	public String getMapKey() {
		return mapKey;
	}
	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}
	public Integer getTraveType() {
		return traveType;
	}
	public void setTraveType(Integer traveType) {
		this.traveType = traveType;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getChildBabySuggestBook() {
		return childBabySuggestBook;
	}
	public void setChildBabySuggestBook(Integer childBabySuggestBook) {
		this.childBabySuggestBook = childBabySuggestBook;
	}
	
	public Integer getPayMethodId() {
		return payMethodId;
	}
	public void setPayMethodId(Integer payMethodId) {
		this.payMethodId = payMethodId;
	}
	public String getPayMethod() {
		return payMethod;
	}
	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}
	public String getDijiaReason() {
		return dijiaReason;
	}
	public void setDijiaReason(String dijiaReason) {
		this.dijiaReason = dijiaReason;
	}
	
	public List<UserInfo> getPassengerInfos() {
		return passengerInfos;
	}
	public void setPassengerInfos(List<UserInfo> passengerInfos) {
		this.passengerInfos = passengerInfos;
	}
	public List<UserInfo> getTongzhirens() {
		return tongzhirens;
	}
	public void setTongzhirens(List<UserInfo> tongzhirens) {
		this.tongzhirens = tongzhirens;
	}
	public List<UserInfo> getCheckMans() {
		return checkMans;
	}
	public void setCheckMans(List<UserInfo> checkMans) {
		this.checkMans = checkMans;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getSkipType() {
		return skipType;
	}
	public void setSkipType(Integer skipType) {
		this.skipType = skipType;
	}
	public Integer getFlightType() {
		return flightType;
	}
	public void setFlightType(Integer flightType) {
		this.flightType = flightType;
	}
	public Integer getJumpCount() {
		return jumpCount;
	}
	public void setJumpCount(Integer jumpCount) {
		this.jumpCount = jumpCount;
	}
    public String getExcessinfo() {
        return excessinfo;
    }
    public void setExcessinfo(String excessinfo) {
        this.excessinfo = excessinfo;
    }
    public String getTripexcessinfo() {
        return tripexcessinfo;
    }
    public void setTripexcessinfo(String tripexcessinfo) {
        this.tripexcessinfo = tripexcessinfo;
    }
    public String getRetripexcessinfo() {
        return retripexcessinfo;
    }
    public void setRetripexcessinfo(String retripexcessinfo) {
        this.retripexcessinfo = retripexcessinfo;
    }
    public String getQuXuanzeReason() {
        return quXuanzeReason;
    }
    public void setQuXuanzeReason(String quXuanzeReason) {
        this.quXuanzeReason = quXuanzeReason;
    }
    public String getQuTianxieReason() {
        return quTianxieReason;
    }
    public void setQuTianxieReason(String quTianxieReason) {
        this.quTianxieReason = quTianxieReason;
    }
    public String getFanXuanzeReason() {
        return fanXuanzeReason;
    }
    public void setFanXuanzeReason(String fanXuanzeReason) {
        this.fanXuanzeReason = fanXuanzeReason;
    }
    public String getFanTianxieReason() {
        return fanTianxieReason;
    }
    public void setFanTianxieReason(String fanTianxieReason) {
        this.fanTianxieReason = fanTianxieReason;
    }
    public List<String> getExcessList() {
        return excessList;
    }
    public void setExcessList(List<String> excessList) {
        this.excessList = excessList;
    }
	
	
}
