package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Request_Base;
import com.viptrip.wetrip.vo.RescheduleObj;

public class Request_RescheduledPNR extends Request_Base {

    /**
     * 
     */
    private static final long serialVersionUID = -6815232968614252693L;
    
    public List<RescheduleObj> rescheduleList;

    public List<RescheduleObj> getRescheduleList() {
        return rescheduleList;
    }

    public void setRescheduleList(List<RescheduleObj> rescheduleList) {
        this.rescheduleList = rescheduleList;
    }
    
}
