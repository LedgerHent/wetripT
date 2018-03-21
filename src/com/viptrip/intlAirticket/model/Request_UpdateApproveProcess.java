package com.viptrip.intlAirticket.model;

import com.viptrip.common.model.ApproveAuditModel;
import com.viptrip.intlAirticket.model.flightModels.Req_orderBase;

/**
 * Created by hent on 2018/1/31.
 */
public class Request_UpdateApproveProcess extends Req_orderBase{
    public ApproveAuditModel audit;

    public ApproveAuditModel getAudit() {
        return audit;
    }

    public void setAudit(ApproveAuditModel audit) {
        this.audit = audit;
    }
}
