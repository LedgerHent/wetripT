<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'page1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	
	<script type="text/javascript">
		
		function test1(){
			$(document).LoadingShow();
			window.location = "test/toTest2.act";
//			parent.window.document.getElementById("f1").src="test/toTest2.act";
		}
		
		function test2(){
			$(document).LoadingShow();
			window.location = "test/toTest2.act";
		}
		
		
	</script>

  </head>
  
  	<body onunload="$(this).LoadingHide();">
		<div id="container">
	  		
			this is page1.....
			<br>
			<input type="button" value="改变src" onclick="test1();" />
			<input type="button" value="页面跳转" onclick="test2();" />
		</div>
  </body>
</html>
