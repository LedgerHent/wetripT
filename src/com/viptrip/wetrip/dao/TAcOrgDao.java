package com.viptrip.wetrip.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.viptrip.wetrip.entity.TAcOrg;


public interface TAcOrgDao extends PagingAndSortingRepository<TAcOrg, Long> {

}
