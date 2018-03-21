package com.viptrip.common.constants;

import java.util.HashMap;

public interface TmcConstant {
	interface APPROVE_STATE {
		Integer WAITING = 1;
		Integer APPROVING = 2;
		Integer PASS = 11;
		Integer REFUSE=12;
		Integer CANCEL=21;
	    HashMap<Integer,String> STATE_KEY_VALUE=new HashMap<Integer,String>() {
	    	{
	    		put(WAITING,"待审核");
	    		put(APPROVING,"审核中");
	    		put(PASS,"审核通过");
	    		put(REFUSE,"审核驳回");
	    		put(CANCEL,"删除审批");
	    	}
	    };		
	}
}
