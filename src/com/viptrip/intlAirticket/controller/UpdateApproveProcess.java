package com.viptrip.intlAirticket.controller;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.common.model.Request_GetApprovalProcess;
import com.viptrip.common.model.Response_GetApprovalProcess;
import com.viptrip.common.service.TTmcApproveProcessService;
import com.viptrip.intlAirticket.IntlTicketClient;
import com.viptrip.intlAirticket.model.Request_UpdateApproveProcess;
import com.viptrip.intlAirticket.model.Response_UpdateApproveProcess;
import com.viptrip.intlAirticket.service.ApprovalIntlService;
import etuf.v1_0.common.Constant;
import etuf.v1_0.model.base.output.OutputResult;
import etuf.v1_0.model.base.output.OutputSimpleResult;

/**
 * Created by hent on 2018/1/31.
 */
public class UpdateApproveProcess extends IntlTicketClient<Request_UpdateApproveProcess,Response_UpdateApproveProcess> {

    @Override
    protected OutputSimpleResult DataValid(Request_UpdateApproveProcess request_updateApproveProcess) {
        OutputSimpleResult result = new OutputSimpleResult();
        result.code= Constant.Code_Succeed;

        return result;
    }

    @Override
    protected OutputResult<Response_UpdateApproveProcess, String> DoBusiness(
            Request_UpdateApproveProcess request_updateApproveProcess) {
        OutputResult result = new OutputResult();
        ApprovalIntlService service = ApplicationContextHelper.getInstance().getBean(ApprovalIntlService.class);
        TTmcApproveProcessService tmcservice = ApplicationContextHelper.getInstance().getBean(TTmcApproveProcessService.class);
        service.updatePro(request_updateApproveProcess);
        Request_GetApprovalProcess getProcess = new Request_GetApprovalProcess();
        getProcess.orderno=request_updateApproveProcess.orderno;
        getProcess.businessType=request_updateApproveProcess.businessType;
        Response_GetApprovalProcess approvalProcess = tmcservice.getApprovalProcess(getProcess);
        Response_UpdateApproveProcess response = new Response_UpdateApproveProcess();
        response.id=approvalProcess.id;
        response.approvals=approvalProcess.approvals;
        response.currentApproveLevel=approvalProcess.currentApproveLevel;
        response.mode=approvalProcess.mode;
        response.name=approvalProcess.name;
        response.state=approvalProcess.state;
        response.type=approvalProcess.type;

        result.setResultObj(response);
        result.code= Constant.Code_Succeed;


        return result;
    }
}
