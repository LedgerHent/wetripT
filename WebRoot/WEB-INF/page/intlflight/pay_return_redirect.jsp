<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>支付确认</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta name="viewport" content="maximum-scale=1.0,minimum-scale=1.0,user-scalable=0,width=device-width,initial-scale=1.0"/>
	<meta name="format-detection" content="telephone=no,email=no,date=no,address=no">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/aui.css" />
	
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript" src="js/aui-dialog.js"></script>
	<script type="text/javascript">
		var dialog = new auiDialog();
		var url = "${url}";
		function alt(){
			dialog.alert({
				title:"温馨提示",
				msg:"请确认是否支付完成",
                buttons:['重新支付','完成支付']
			},function(ret){
                    if(ret.buttonIndex == 2){
                        /* dialog.alert({
                            title:"提示",
                            msg: "您输入的内容是："+ret.text,
                            buttons:['确定']
                        }); */
                        if(url){
                        	window.location = url;
                        }
                        
                    }else if(ret.buttonIndex == 1){
                    	window.location = "<%=basePath%>intlflight/toPay.act?orderId=${orderId}";
                    }
            });
       	}
       	
       	$(function(){
       		alt();
       	});
	</script>

  </head>
  <body>
  
  </body>
</html>
