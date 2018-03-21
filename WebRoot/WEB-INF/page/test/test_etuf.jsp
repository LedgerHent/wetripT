<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test_etuf.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
	<script type="text/javascript">
		function send(){
			var url = $("#url").val();
			if(url){
				var method = $("#method").val();
				var para = $("#para").val();
				eval("var p = " + para);
				p.method = method;
				$("#result").val('');
				$.post(
					url,
					{'data': JSON.stringify(p)},
					function(data){
						$("#result").val(data);
					}
				);
			}
			
		}
		function reset(){
			$("input:not('button')").val('').html('');
		}
	</script>

  </head>
  
  <body>
    <fieldset>
    	<legend>ETUF框架测试</legend>
    	请求地址:<input id="url" type="text" size="50" value="http://localhost:8080/wetrip/server/ticketServer" /><br>
    	请求类名:<input id="method" type="text" size="40" value="Test" /><br>
    	其他参数:<textarea id="para" rows="10" cols="50">{"userId":"123456"}</textarea><br><hr>
    	返回结果:<textarea id="result" rows="10" cols="50"></textarea>
    	<br>
    	<input type="button"  value="发送请求" onclick="send();return false;"/> 
    	<input type="button" value="重置" onclick="reset();return false;" />
    </fieldset>
  </body>
</html>
