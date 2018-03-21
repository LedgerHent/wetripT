package com.viptrip.base.cache;


import java.util.HashMap;
import java.util.Map;



public class DefaultCacheProvider implements ICacheProvider{
	private Map<String,Object> data = new HashMap<String, Object>();
	@Override
	public Object get(String key) {
		return data.get(key);
	}
	@Override
	public void put(String key, Object value) {
		data.put(key, value);
	}

	@Override
	public boolean sync() {
		return false;
	}
}
