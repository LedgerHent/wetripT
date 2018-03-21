<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>百度地图</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />  
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GXkU8EHGVT4djH7BiaOiFyNi0WirfqNt"></script>
	<%--<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
	--%><link rel="stylesheet" type="text/css" href="<%=basePath%>/css/hotelHtml5/index.css" />
</head>
<body>
	<header id="headers" class="header" style="height: 4rem;">
	<div style="margin:auto 0;height: 4rem" >
		<img src="<%=basePath%>/hotelimg/header.png" / class="you" onclick="history.back();" style="margin-top: 1rem">
	</div>
		<%--<p>${hotelName }</p>--%>
	</header>
    <div id="allmap" style="width:100%; height:100%"></div>

<script type="text/javascript" src=" js/hotelHtml5/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");
    //var point = new BMap.Point(116.400102 , 39.913922);  // 创建点坐标
    var point;
    point = new BMap.Point(${longitude} , ${latitude}); 
    map.centerAndZoom(point, 15);// 初始化地图，设置中心点坐标和地图级别  
    var marker = new BMap.Marker(point);    
    map.addOverlay(marker); 
    map.enableScrollWheelZoom(true);//缩放
    map.addControl(new BMap.ScaleControl()); 
   // BMap.Convertor.translate(point,0,translateCallback);     //真实经纬度转成百度坐标
  	//坐标转换完之后的回调函数
    /*function translateCallback(point1){
        var marker1 = new BMap.Marker(point1);
        map.addOverlay(marker1);
        var label = new BMap.Label("转换后的百度坐标",{offset:new BMap.Size(20,-10)});
        marker1.setLabel(label); //添加百度label
        map.setCenter(point1);
    }*/
    
</script>


</body>
</html>