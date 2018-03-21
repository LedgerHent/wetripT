package com.viptrip.hotelHtml5.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.viptrip.hotelHtml5.common.ConfigConstants;
import com.viptrip.util.StringUtil;



public class BedNameByTypeTag extends BodyTagSupport {

	private static final long serialVersionUID = -3891550854557068781L;

	private String bedTypes;

	@Override
	public int doEndTag() throws JspException {
		String name = "";
		if(StringUtil.isNotEmpty(bedTypes)){
			for (String bedType : bedTypes.split(",")) {
				String bedName = ConfigConstants.BED_TYPE_MAP.get(bedType);
				if(StringUtil.isEmpty(bedName)){
					continue;
				}
				if(StringUtil.isEmpty(name)){
					name = bedName;
				}else{
					name += "/"+bedName;
				}
			}
		}
		JspWriter out = pageContext.getOut();
	    try {
	      out.write(name);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }finally{
			try {
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return EVAL_PAGE;
	}

	public String getBedTypes() {
		return bedTypes;
	}

	public void setBedTypes(String bedTypes) {
		this.bedTypes = bedTypes;
	}

}
