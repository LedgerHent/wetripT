<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="js/layui/css/layui.css"  media="all">
  </head>
  
  <body>
  
  <form id="query" action="test/show.act" method="post">
  	用户名<input name="username" value="${username }" /><input type="submit" value="查询" />
  </form>
  
    <c:if test="${users != null}">
    	<table class="layui-table">
    	  <colgroup>
		    <col width="80">
		    <col width="30">
		    <col width="160">
		    <col width="150">
		    <col width="150">
		  </colgroup>
	    	<c:forEach items="${users }" var="user" >
	    		<tr>
	    			<td>${user.username }</td>
	    			<td>${user.usercode }</td>
	    			<td>${user.password }</td>
	    			<td>${user.loginname }</td>
	    			<td>${user.email }</td>
	    		</tr>
	    	</c:forEach>
    	</table>
    	<div id="page"></div>
    </c:if>
  </body>
</html>
<script src="js/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript">
	layui.use(['laypage','layer'],function(){
		var laypage = layui.laypage,layer = layui.layer;
		laypage({
			cont: page,
			curr:1,
			pages:100,
			jump:function(obj,first){
				//定义跳转
			}
		});
	});
</script>
