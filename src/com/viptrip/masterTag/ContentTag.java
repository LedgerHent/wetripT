/**
 * 
 */
package com.viptrip.masterTag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * TODO 
 */
public class ContentTag extends BodyTagSupport {
	private static final long serialVersionUID = 1L;
	private String contentPlaceHolderId;
	@Override
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		  String content = this.bodyContent.getString();
		  try {
			this.pageContext.getRequest().setAttribute(this.getContentPlaceHolderId(), content);
			this.bodyContent.clear();
		  } catch (IOException e) {
		   e.printStackTrace();
		  }
		return EVAL_PAGE;//default
	}

	
	public String getContentPlaceHolderId() {
		return contentPlaceHolderId;
	}
	public void setContentPlaceHolderId(String contentPlaceHolderId) {
		this.contentPlaceHolderId = contentPlaceHolderId;
	}
	
	
}
