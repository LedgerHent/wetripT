$(function(){
	initCityMap();
});

var cityMap;//存放 城市三字码-城市名 map
/**
 * 城市
 */
function initCityMap(){
	$.ajax({
		url:'comm/get3CharCityName.act',
		async:false,
		success:function(data){
			cityMap = data;//初始化map
		},
		dataType:'json'
	});
}
/**
 * 提示信息
 * @param msg
 */
function showMsg(msg){
	$(document).MyConfirm({
        content: msg,
    })
	return ;
}

/**
 * 倒计时工具
 * objArr 需要倒计时的元素对象例如<div>14:29</div> 可以为一个数组
 * callback 倒计时结束需要执行的回调函数 函数的参数为 倒计时元素对象
 */
/*function Timer(objArr,callback){
	this.objs = [];
	this.interV = [];
	if($.isArray(objArr)){
		this.objs = objArr;
	}else{
		this.objs.push(objArr);
	}
	
	this.begin = function(){
		for(var i=0;i<this.objs.length;i++){
			var j = i;
			var obj = this.objs[i];
			this.interV[j] = window.setInterval(function(){
													var $o = $(obj);
													var time = $o.html();
													console.log("time=" + time);
													var sec = time_to_sec(time);
													if(sec > 0) {
														sec = sec -1;
														$o.html(sec_to_time(sec));
													}else{
														$o.html("已超时");
														clearInterval(this.interV[j]);
														if(callback && $.isFunction(callback)){
															callback(obj);
														}
													}
												},1000);
		}
	};
	var time_to_sec = function (time) {
	    var s = '';
	    var len = time.split(':');
	    
	    if(len.length==1){
	    	s = Number(len[0]);
	    }
	    if(len.length==2){
	    	s = Number(len[0]*60) + Number(len[1]);
	    }
	    if(len.length==3){
	    	s = Number(len[0]*3600) + Number(len[1]*60) + Number(len[2]);
	    }
	    return s;
	};
	var sec_to_time = function(s){
		var t;
	    if(s > -1){
	        var hour = Math.floor(s/3600);
	        var min = Math.floor(s/60) % 60;
	        var sec = s % 60;
	        if(hour < 10 && hour>0) {
	            t = '0'+ hour + ":";
	        }else if(hour==0){
	        	t = "";
	        } else {
	            t = hour + ":";
	        }
	
	        if(min < 10){t += "0";}
	        t += min + ":";
	        if(sec < 10){t += "0";}
	        t += sec;
	        //t += sec.toFixed(2);
	    }
	    return t;
	};
	return this;
}*/