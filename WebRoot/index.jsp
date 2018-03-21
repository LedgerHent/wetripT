<%@page import="etuf.v1_0.model.base.Enum"%>
<%@page import="etuf.v1_0.common.Common"%>
<%@page import="etuf.v1_0.model.base.output.OutputResult"%>
<%@page import="com.viptrip.hotel.model.Response_GetOrgLikeName"%>
<%@page import="com.viptrip.hotel.controller.GetOrgLikeName"%>
<%@page import="com.viptrip.hotel.model.Request_GetOrgLikeName"%>
<%@page import="com.viptrip.hotel.controller.Demo"%>
<%@page import="com.viptrip.hotel.model.demo.RequestDemoData"%>
<%@page import="com.viptrip.hotel.model.Request_Demo"%>
<%@page import="com.viptrip.hotel.model.Response_Demo"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- 使用自定义弹框，必须要引用如下两个js和一个css -->
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/MyConfirm.js"></script>
<link rel="stylesheet" type="text/css" href="css/MyConfirm.css">

<!-- 使用 mobiscroll 日历控件，必须引入如下的js和css -->
<script type="text/javascript" src="js/mobiscroll.custom-3.0.0-beta2.min.js"></script>
<link rel="stylesheet"
	href="css/mobiscroll/mobiscroll.custom-3.0.0-beta2.min.css" type="text/css"></link>

</head>

<body>

		<br>
		<button onclick="showLoading(false)" style="z-index:9999999999">test show loading</button>
		<br>
	<div id="container">
		This is my JSP page.
		<br>
	
		<%
			
			//Request_Demo req=new Request_Demo();
			//req.method="demo";
			//req.versionId=1.0;
			//req.data=new RequestDemoData();
			//req.data.demoInt=1;
			//req.data.demoStr="aaa";
			//req.data.demoList=new ArrayList<String>();
			//req.data.demoList.add("abc");
			//req.data.demoList.add("123");
			Request_GetOrgLikeName req=new Request_GetOrgLikeName();
			//req.method="getOrgLikeName";
			//req.versionId=1.0;
			req.userId=294787;//企业--294863 客服--299386 客维--294787
			req.nameKey="测试";
			try	{
				GetOrgLikeName ct=new GetOrgLikeName();
				OutputResult<Response_GetOrgLikeName,String> or=
						ct.ClientRequest(req, Response_GetOrgLikeName.class);
				if(or.IsSucceed()){
					if(or.getResultObj().status==0){
						or.result;
					}
					else {
						
					}
				}else{
					or.result;
				}
				//Demo demo=new Demo();
				//demo.ClientRequest(req, Response_Demo.class);
			}catch(Exception ex){
				ex.printStackTrace();
			}
			
			response.getWriter().write(basePath);
		%>
		<br>
		<button onclick="showMessage()">test show message</button>
		<br>
		<button onclick="showLoading(true)">test show loading</button>
		<br>
		<input type="text" id="txtDate" />
		<br>
		<br>
		<hr>
		<input type="button" value="登录1" onclick="login1();" />
		<br>
		<br>
		<hr>
		<input type="button" value="登录2" onclick="login2();" />
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		var now = new Date(),
	    min = new Date(now.setDate(now.getDate() + 1)),
	    max = new Date(now.getFullYear()+1, now.getMonth() + 6, now.getDate());
		$("#txtDate").mobiscroll().calendar({
			theme : 'ios',
			lang : 'zh',
			display : 'bottom', //显示位置 bottom/top/center/bubble/inline 
			//controls : [ 'calendar', 'time' ], //日历，时间，默认只有日历
			showScrollArrows : true,
			headerText: '显示文字',
		    min: min,
			max: max,
			calendarScroll: 'vertical', //滑动放向 vertical/horizontal
			yearChange: true, //是否允许修改年
			months: 'auto',	//自动判别显示几个月份
			//counter: true,	//是否显示选择了几天，会覆盖 headerText
	        //select: 'multiple'//选择方式（控制多选天）
	        
		});
	});

	function showMessage() {
		//有很多可用参数，详见jquery插件，以下是用法示例
		$(this).MyConfirm({
			content : "提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！提示信息啊！！！",
			hasCancelBtn : true
		}, callback);
	}

	function callback(isConfirm) {
		$(this).MyConfirm({
			content : "你刚点击了【" + (isConfirm ? "确认" : "取消") + "】按钮。"
		});
	}

	
	function showLoading(isShow){
		if(isShow){
			$(this).LoadingShow();
		}else{
			$(this).LoadingHide();
		}
	}

	
	
	function login1(){
		window.location.href = "comm/login.act";
	}
	function login2(){
		var basePath = '<%=basePath%>' + "/wetrip";
		var url = "https://sso.viptrip.com/cas/login?service=" + basePath + "&user=110108196907293438&pass=123456&submitDirection=true";
		window.location.href = "";
	}

</script>

</html>
