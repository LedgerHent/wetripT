package com.viptrip.taglib;

import com.viptrip.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by selfwhisper on 0003 2018/1/3.
 */
public class PayTag extends TagSupport {
    private static Logger logger = LoggerFactory.getLogger(PayTag.class);

    private Integer pageType;// 页面类型 1-PC 2-H5 3-WX
    private String url;
    private Double amount;

    @Override
    public int doStartTag() throws JspException {
        String js = "<script>$(function(){$(\"button.pay\").bind(\"click\",payButtonClicked);});function payButtonClicked(){var payType=$(this).attr(\"payType\");var url=$(this).attr(\"url\");if(payType&&url){$(\"#payForm>input[name='payType']\").val(payType);$(\"#payForm\").attr(\"action\",url);$(\"#payForm\").submit();}}</script>";
        String from = "<form id=\"payForm\"action=\"\"method=\"post\"style=\"display: none\"><input name=\"payType\"/><input name=\"amount\"/></form>";
        try {
            pageContext.getOut().write(js);
            pageContext.getOut().write(from);
        } catch (IOException e) {
            logger.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
        }
        return  SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String page = "";
        if(null!=pageType){
            if(3==pageType){
                page = "<button class=\"pay\" url=" + url + " payType=\"25\">立即支付</button>";
            }
            if(2==pageType){
                page = "<button class=\"pay\" url=" + url + " payType=\"11\">支付宝</button>";
                page += "<button class=\"pay\" url=" + url + " payType=\"21\">微信支付</button>";
                page += "<button class=\"pay\" url=" + url + " payType=\"31\">农行</button>";
            }
        }
        try {
            pageContext.getOut().write(page);
        } catch (IOException e) {
            logger.error(StringUtil.getLogInfo(null,null,StringUtil.getExceptionStr(e)));
        }
        return EVAL_PAGE;
    }

    public Integer getPageType() {
        return pageType;
    }

    public void setPageType(Integer pageType) {
        this.pageType = pageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
