<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>保险列表</title>

  </head>
  
  <body>
    <div align = "center">
    	<h4 align = "center">保险列表加载</h4><br/>
         <c:if test="${ins.getResultObj().getData() != null }">
	    	<table>
		    	<tr>  
		             <td align = "center">保险编号</td>  
		             <td align = "center">保险名称</td>  
		             <td align = "center">保险价格</td> 
		             <td align = "center">保险价格</td> 
		             <td align = "center">保险价格</td>  
		           	<!--  <td align = "center">保险描述</td>  -->
		         </tr> 
		    	<c:forEach items="${ins.getResultObj().getData() }" var="ins" varStatus="vs">
			        <tr>  
			            <td align = "center">${ins.id}</td>  
			            <td align = "center">${ins.name}</td>  
			            <td align = "center">${ins.price}</td> 
			          	<td align = "center">${ins.description}</td>
			         </tr>
				</c:forEach>  
	        </table>  
         </c:if>  
         <c:if test="${ins.getResultObj().getData() == null }">
         	保险暂无数据
         </c:if>
    </div>
    <div align = "center">
    	<h4 align = "center">结算方式列表加载</h4><br/>
         <c:if test="${pay.getResultObj().getData() != null }">
    		<table>
    		<tr>  
	             <td align = "center">结算方式编号</td>  
	             <td align = "center">结算方式名称</td>  
	             <td align = "center">结算方式价格</td> 
	             <td align = "center">结算方式描述</td> 
	             <td align = "center">结算方式类型</td>  
         	</tr> 
	         	<c:forEach items="${pay.getResultObj().getData() }" var="pay" varStatus="vs">
	       		<tr>
	       			<td align = "center">${pay.id}</td>  
		             <td align = "center">${pay.name}</td>  
		             <td align = "center">${pay.price}</td> 
		             <td align = "center">${pay.description}</td> 
		             <td align = "center">${pay.businessType}</td> 
	       		</tr>
	         	</c:forEach>
	    	</table>
       	</c:if>
       	 <c:if test="${pay.getResultObj().getData() == null }">
       		保险暂无数据
       	</c:if>
    </div>
  </body>
</html>
