<%@page import="com.google.gson.Gson"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="com.viptrip.wetrip.model.Response_Test"%>
<%@page import="etuf.v1_0.model.base.output.OutputResult"%>
<%@page import="com.viptrip.wetrip.controller.Test"%>
<%@page import="com.viptrip.wetrip.model.Request_Test"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
	<link rel="stylesheet" type="text/css" href="css/aui.css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/aui-dialog.js"></script>
	<script type="text/javascript">
		$(function(){
			
		});
		
		var dialog = new auiDialog();
		function test(){
				dialog.prompt({
					title:"取消订单",
	                text:"请填写取消理由",
	                buttons:['取消','确定']
				},function(ret){
	                    if(ret.buttonIndex == 2){
	                        dialog.alert({
	                            title:"提示",
	                            msg: "您输入的内容是："+ret.text,
	                            buttons:['确定']
	                        });
	                    }
	                });
       	}
       	
       	function alt(){
       		dialog.alert({
       			title:"这是一个提示框",
       			msg:"你还好吗？",
       			buttons:['确定']
       		});
       	}
       	
       	/* function loading(){
       		toast.loading({
                    title:"加载中",
                    duration:2000
                },function(ret){
                    console.log(ret);
                    setTimeout(function(){
                        toast.hide();
                    }, 3000)
                });
       	} */
       	
	</script>

  <body>
  
  	<div id="search">
	    <form id="J_Search" target="_blank">
	        <ul>
	            <li><label class="tit" for="J_DepDate">出发时间：</label><input style="border: none;font-size: 25;" id="J_DepDate" type="text" class="f-text" value="" /></li>
	            <li><label class="tit" for="J_EndDate">返程时间：</label><input style="border: none;font-size: 25;" id="J_EndDate" type="text" class="f-text" value="" /></li>
	            <li><label class="tit"></label><input id="J_search_btn" type="submit" class="f-btn" value="" /></li>
	        </ul>
	    </form>
	</div>
  
  <div>
  	<div class="aui-btn" onclick="test();">带输入的框</div>
  	<div class="aui-btn aui-btn-primary" onclick="alt();">alert</div>
  	<div class="aui-btn aui-btn-success" onclick="loading();">loading</div>
  </div>
  
  
  <form name="payForm" action="" method="post" style="display: none">
	  <input name="payType" />
	  <input name="amount" />
  </form>
  
  </body>
</html>