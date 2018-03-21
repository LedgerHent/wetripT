<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<style>
	tr{
		height: 50px;
	}
</style>
<body>
<%--	<iframe id="B" src="http://61.51.80.138:9999/testJsp.jsp?height=756&width=100" style="display: none;" ></iframe>--%>
<%--	<iframe id="B" src="http://172.16.249.222:9999/testJsp.jsp?height=756&width=100" style="display: none;" ></iframe>--%>
	<iframe id="B" src="http://localhost:8080/viptrip365/testJsp.jsp?height=756&width=100" style="display: none;" ></iframe>
<div class="pageContent" height="600" style="font-size: 20">
	<table style="font-size: 20">
	  <td/>
	  <br/>
	  		<tr>
	  			<td>url:</td>
	  		</tr>
	  		<tr>
	  			<td><a href="${url}">${url}</a></td>
	  		</tr>
	  		<tr>
	  			<td>desUrlParm:</td>
	  		</tr>
	  		<tr>
	  			<td>${desUrlParm}</td>
	  		</tr>
	  		<tr>
	  			<td>urlParm:</td>
	  		</tr>
	  		<tr>
	  			<td>${urlParm}</td>
	  		</tr>
	  		
	  		<tr>
	  			<td>url:</td>
	  		</tr>
	  		<tr>
	  			<td><a href="${url}">${url}</a></td>
	  		</tr>
	  		<tr>
	  			<td>desUrlParm:</td>
	  		</tr>
	  		<tr>
	  			<td>${desUrlParm}</td>
	  		</tr>
	  		<tr>
	  			<td>urlParm:</td>
	  		</tr>
	  		<tr>
	  			<td>${urlParm}</td>
	  		</tr>
	  	
	  </table>
</div>

</body>