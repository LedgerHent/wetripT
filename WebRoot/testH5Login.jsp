<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>H5登录测试页面</title>
    
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
		
	</script>

  </head>
  
  <body>
  <form id="query" action="server/h5PlatformTest" method="post">
  	
	  <table>
	  	<tr>
	  	"如需帮助，请致电凯撒商旅24小时客服服务热线<a href='tel:4006-020-365'>4006-020-365</a>。"
  		<td>platformId</td><td><input type="text" value="${platformId}" name="platformId" /></td>
  			</tr>
  		<tr>
  			<td>pwd</td><td><input type="text" value="${pwd}" name="pwd" /></td>
  			</tr>
  		<tr>
  			<td>uid</td><td><input type="text" value="${uid}" name="uid" /></td>
  			</tr>
  		<tr>
  			<td>name</td><td><input type="text" value="${name}" name="name" /></td>
  			</tr>
  		<tr>
  			<td>mobile</td><td><input type="text" value="${mobile}" name="mobile" /></td>
  			</tr>
  		<tr>
  			<td>email</td><td><input type="text" value="${email}" name="email" /></td>
  			</tr>
  		<tr>
  			<td>idType</td><td><input type="text" value="${idType}" name="idType" /></td>
  			</tr>
  		<tr>
  			<td>idNum</td><td><input type="text" value="${idNum}" name="idNum" /></td>
  		</tr>
	  	
	  	<input type="submit" value="登录">
	  	
	  </table>
  </form>
  </body>
</html>
