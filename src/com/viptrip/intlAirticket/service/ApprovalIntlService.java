package com.viptrip.intlAirticket.service;

import com.viptrip.intlAirticket.model.Request_UpdateApproveProcess;
import org.springframework.stereotype.Service;

/**
 * Created by hent on 2018/1/31.
 */
@Service
public interface ApprovalIntlService {

    public String updatePro(Request_UpdateApproveProcess req);
}
