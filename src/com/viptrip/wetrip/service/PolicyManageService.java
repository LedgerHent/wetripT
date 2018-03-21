package com.viptrip.wetrip.service;

import java.util.List;
import java.util.Map;

import com.viptrip.ibeserver.model.DispplayTrip;
import com.viptrip.wetrip.entity.TAcOrg;
import com.viptrip.wetrip.entity.OrgPolicyManage;
import com.viptrip.wetrip.entity.policy.TExcessManage;
import com.viptrip.wetrip.model.Request_GetFlightList;
import com.viptrip.wetrip.model.flight.ReqData_GetFlightList_Passenger;
import com.viptrip.wetrip.model.policy.BerthPolicyInfo;
import com.viptrip.wetrip.model.policy.PolicyTicketInfo;

/**
 * 国内机票差旅规则service
 * 
 * @author jetty
 * 
 */
public interface PolicyManageService {

    public PolicyTicketInfo getNewlyRule(List<PolicyTicketInfo> cangweiPolicyList, String aircomney, String cangwei);

    public int isMarryBerthPolicy(PolicyTicketInfo tPolicyTicket, BerthPolicyInfo berthPolicyInfo);

    public List<DispplayTrip> filterPolicyForTrips(long userid, String travelType,
            List<ReqData_GetFlightList_Passenger> passengers, List<DispplayTrip> entitys);
    /**
     * 
     * @param orgId
     * @return
     */
    public TAcOrg getSuperTAcOrg(Long orgId);
    
    /**
     * 查询维护的超标理由
     * 
     * @param type
     * 
     */
    public List<TExcessManage> getExcess() ;
    
}
