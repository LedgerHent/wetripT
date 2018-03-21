package com.viptrip.base.mem_file_cacher;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.viptrip.util.StringUtil;



/**
 * 文件名生成器 默认为yyyyMMddHHmmss_value[0]_value[1]...
 * @author selfwhisper
 *
 */
public class DefaultKeyGenerator implements KeyGenerator{
	private static String pattern = "yyyyMMddHHmmss";

	@Override
	public String exec(String...value) {
		String name = new SimpleDateFormat(pattern).format(new Date());
		if(null!=value&&value.length>0){
			for (int i = 0; i < value.length; i++) {
				String val = value[i];
				if(!StringUtil.isEmpty(val)&&!StringUtil.isEmpty(val.trim())){
					name += "_" + val;
				}
			}
		}
		return name;
	}

}
