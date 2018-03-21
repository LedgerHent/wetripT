package com.viptrip.intlAirticket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.viptrip.intlAirticket.entity.TIntlOrder;
import com.viptrip.intlAirticket.model.Request_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Request_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Request_IntlAuditOrder;
import com.viptrip.intlAirticket.model.Response_CancelIntlOrder;
import com.viptrip.intlAirticket.model.Response_GetIntlOrderDetail;
import com.viptrip.intlAirticket.model.Response_IntlAuditOrder;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.TAcUser;

@Service
@Transactional
public interface IntlOrderDetailService {
    /**
     * 接口描述:国际机票订单详情查询
     * @param para
     * @return
     */
    public Response_GetIntlOrderDetail getIntlOrderDetail(Request_GetIntlOrderDetail para);
    
    /**
     * 接口描述:国际机票审核
     * @param para
     * @return
     */
    public Response_IntlAuditOrder intlAuditOrder(Request_IntlAuditOrder para);
    
    /**
     * 国际机票取消订单接口
     * @param para
     * @return
     */
    public Response_CancelIntlOrder cancelIntlOrder(Request_CancelIntlOrder para);
    
    public TAcUser getTAcUser(long userid);
    
    public TAcOrg getOrgids(Long orgId);
    
    public TIntlOrder getIntlOrder(Integer orderid);
    
}
