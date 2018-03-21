package com.viptrip.wetrip.model;

import java.util.List;

import com.viptrip.wetrip.model.base.Response_Base;

public class Response_RescheduledUpgradeFee extends Response_Base {

    /**
     * 
     */
    private static final long serialVersionUID = 7120229736600317110L;
    
    public List<Double> upgradeFees;

    public List<Double> getUpgradeFees() {
        return upgradeFees;
    }

    public void setUpgradeFees(List<Double> upgradeFees) {
        this.upgradeFees = upgradeFees;
    }

    
    
}
