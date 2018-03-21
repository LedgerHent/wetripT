package com.viptrip.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by selfwhisper on 0004 2018/1/4.
 */
public class PayCountdownTag extends TagSupport{

    private String countdown;
    private boolean init;

    @Override
    public int doStartTag() throws JspException {
        String flag = pageContext.getSession().getId();
        String cName = "payCountdown_" + flag;
        String result = "";
        if(init){
            String time_to_sec_function = "function time_to_sec_" + flag + "(time){var s='';var len=time.split(':');if(len.length==1){s=Number(len[0]);}if(len.length==2){s=Number(len[0]*60)+Number(len[1]);}if(len.length==3){s=Number(len[0]*3600)+Number(len[1]*60)+Number(len[2]);}return s;};";

            String sec_to_time_function = "function sec_to_time_" + flag + "(s){var t;if(s>-1){var hour=Math.floor(s/3600);var min=Math.floor(s/60)%60;var sec=s%60;if(hour<10&&hour>0){t='0'+hour+\":\";}else if(hour==0){t=\"\";}else{t=hour+\":\";}if(min<10){t+=\"0\";}t+=min+\":\";if(sec<10){t+=\"0\";}t+=sec;}return t;};";


            result += "<script>"
                    + "var interV = [];"
                    + "function SetRemainTime_"  + flag + "(idx){var time=$(\"." + cName + "\").eq(idx).html();var timeStr=time_to_sec_" + flag + "(time);if(timeStr>0){timeStr=timeStr-1;$(\"." + cName +"\").eq(idx).html(sec_to_time_" + flag + "(timeStr));}else{$(\"." + cName + "\").eq(idx).html(\"已超时\");clearInterval(interV[idx]);}}"
                    + time_to_sec_function
                    + sec_to_time_function
                    + "</script>";
            result += "<script>"  + "$(document).ready(function(){$(\"." + cName + "\").each(function(idx){interV[idx]=window.setInterval(function(){SetRemainTime_" + flag +"(idx);},1000)});});" +  "</script>";
        }
        result += "<span class=\"" + cName + "\">" + countdown + "</span>";

        try {
            pageContext.getOut().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  SKIP_BODY;
    }

    public String getCountdown() {
        return countdown;
    }

    public void setCountdown(String countdown) {
        this.countdown = countdown;
    }

    public boolean isInit() {
        return init;
    }

    public void setInit(boolean init) {
        this.init = init;
    }
}
