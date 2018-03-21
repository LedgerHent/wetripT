package com.viptrip.base.common.support;


import java.util.List;
import java.util.Map;

import com.viptrip.base.cache.ICacheProvider;


public interface IDataDict extends ICacheProvider{
	
	List<Object> getList(String key);
	
	Map<String,Object> getMap(String key);
	
	void put(String key,Object data);
	
}
