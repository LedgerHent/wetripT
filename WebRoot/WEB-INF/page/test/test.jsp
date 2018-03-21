<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="mt" uri="/mytag" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>test iframe</title>
    <link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
    <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
	<script type="text/javascript">
		var loading = false;
		/*$(function(){
			
		    var oFrm = document.getElementById('f1');
		    
		    oFrm.onload = oFrm.onreadystatechange = function() {
		    	console.log("readyState=" + this.readyState);
		        if (this.readyState && this.readyState != 'complete') 
		        	return;
		        else
		            loadingHide();
		    }
	
			
		})
		
		function loadingShow(){
			console.log("show.loading=" + loading);
			if(!loading){
	        	loading = true;
				$(document).LoadingShow();
	        }
		}
		
		function loadingHide(){
			console.log("hide.loading=" + loading);
			if(loading){
				$(document).LoadingHide();
				loading = false;
			}
		}*/

		$(function () {
            $("body").append('<mt:countDown countdown="11:11" init="false" />');
        });
		
	</script>
  </head>
  <body>
  	<%--<div id="container">
	  	<iframe id="f1" name="f1" src="test/toTest1.act" style="width: 100%;height: 100%;border: 0px"></iframe>
  	</div>--%>
    <%--<mt:pay pageType="3" url="test.act" amount="100.00"  />--%>

  <mt:countDown countdown="15:45" init="true" />
  <mt:countDown countdown="12:20" init="false" />
  <mt:countDown countdown="45:59" init="false" />

  </body>
</html>
