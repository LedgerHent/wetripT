package com.viptrip.wetrip;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viptrip.base.common.support.ApplicationContextHelper;
import com.viptrip.util.JSON;
import com.viptrip.wetrip.action.CommAction;

public class TestServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1139079800043095352L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CommAction action = ApplicationContextHelper.getInstance().getAction(CommAction.class);
		
		System.out.println("TestServlet.doGet()==action==" + action);
		Map<String, String> map = action.get3Char2Airport();
		System.out.println("TestServlet.doGet()==map==" + JSON.get().toJson(map));
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
