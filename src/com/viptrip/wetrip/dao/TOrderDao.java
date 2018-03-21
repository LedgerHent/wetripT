package com.viptrip.wetrip.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.viptrip.wetrip.entity.TOrder;

public interface TOrderDao extends PagingAndSortingRepository<TOrder, Long>{

}
