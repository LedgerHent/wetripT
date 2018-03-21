<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>js实现进度拖拽的效果 www.txahz.com</title>
<style>
body{margin:0;padding:0;font-size:12px;}
.scale{background: #ddd; width: 400px; height: 3px; position: relative;margin: 20px;}
.scale span{background:#aaa;width:8px;height:16px;position:absolute;left:-2px;top:-6px;cursor:pointer; border-radius: 3px;}
.scale div{background: #66D9EF; position: absolute; height: 3px; width: 0; left: 0; bottom: 0; }
</style>
</head>
<body>

<div class="scale" id="bar">
	<div></div>
	<span id="btn"></span>
</div>
 音量：<span id="title">0</span><br>
 <input id="num" max="65000" onkeydown="change();" />
<script>
var scale = function (btn,bar,title,num){
	this.btn = document.getElementById(btn);
	this.bar = document.getElementById(bar);
	this.title = document.getElementById(title);
	this.step = this.bar.getElementsByTagName("div")[0];
	this.num=document.getElementById(num);

	this.init = function (){
		var f=this,g=document,b=window,m=Math;
		f.btn.onmousedown=function (e){
			var x=(e||b.event).clientX;
			var l=this.offsetLeft;
			var max=f.bar.offsetWidth-this.offsetWidth;
			g.onmousemove=function (e){
				var thisX=(e||b.event).clientX;
				var to=m.min(max,m.max(-2,l+(thisX-x)));
				f.btn.style.left=to+'px';
				f.ondrag(m.round(m.max(0,to/max)*100),to);
				b.getSelection ? b.getSelection().removeAllRanges() : g.selection.empty();
			};
			g.onmouseup=new Function('this.onmousemove=null');
		};
	};
	this.ondrag = function (pos,x){
		this.step.style.width=Math.max(0,x)+'px';
		this.title.innerHTML=pos+'%';
		this.num.value=Math.round(pos*this.num.getAttribute("max")/100);
	};
	this.init();
	this.set = function(per){
		var p = Math.round(3.9*per + 2);
		this.ondrag(per, p);
		this.btn.style.left=p+'px';
	}
}

var scl = new scale('btn','bar','title','num').set(1); //实例化一个拖拽

function change(){
	var id = document.getElementById("num");
	var n = id.value;
	var max = id.getAttribute("max");
	if(!n){
		n = 0;
	}
	var per = Math.round(100*n/max);
	scl.set();
	
}
</script>
</body>
</html>
