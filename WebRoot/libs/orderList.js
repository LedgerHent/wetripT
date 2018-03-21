

/*-----------保险开与关的效果实现----------*/
/*$('.circle .circleBtn').on('click', function() {
	var btn = $(this).parent();
	if(!btn.is('.active')) {
		$(this).animate({
			left: '0.1rem'
		}, 500, function() {
			$(this).parent().addClass('active');
		});

	} else {
		$(this).animate({
			left: '2.52rem'
		}, 500, function() {
			$(this).parent().removeClass('active');
		});

	}
});*/

/*----差旅说明和结算方式的默认样式-----*/
function defalutStyles(objStyles) {
	$(objStyles).eq(0).css('color', '#00AFEC');
	$(objStyles).find('.selected').eq(0).show();
};
defalutStyles('.payment .payment-choose p'); //结算方式
defalutStyles('.instructe .payment-choose p'); //差旅说明
defalutStyles('.department .payment-choose p'); //费用归属
defalutStyles('.excessreason .payment-choose p'); //费用归属

/*------结算方式种类的效果实现----------*/
var nums = ($('.payment .payment-choose p').length + 1) * 80 / 20 + 'rem';
$('.module').eq(1).on('click', function() {
	layerShow('.payment', '.payment .payment-con', nums)
});
$('.payment .payment-choose p').on('click', function() {
	$(this).css('color', '#00AFEC').siblings().css('color', '');
	$(this).find('.selected').show().parents('.payment .payment-choose p').siblings().find('.selected').hide();
	$('.payment').delay(500).fadeOut(0);
	$('.message-order .payment-type').html($(this).find('span').eq(0).html());
});

/*----------差旅说明类型的效果实现----------*/
var num = ($('.instructe .payment-choose p').length + 1) * 80 / 20 + 'rem';

$('.module').eq(0).on('click', function() {
	layerShow('.instructe', '.instructe .payment-con', num);
});
$('.instructe .payment-choose p').on('click', function() {
	$(this).css('color', '#00AFEC').siblings().css('color', '');
	$(this).find('.selected').show().parents('.instructe .payment-choose p').siblings().find('.selected').hide();
	$('.instructe').delay(500).fadeOut(0);
	$('.message-order .insurance-type').html($(this).find('span').eq(0).html());
});

/*-------费用归属的效果样式---------*/
var objindex;
$('.costbelong').on('click', function() {
	layerShow('.department', '.department .payment-con');
	objindex = $(this);
});



$('.department .payment-choose p').on('click', function() {
	$(this).css('color', '#00AFEC').siblings().css('color', '');
	$(this).find('.selected').show().parents('.department .payment-choose p').siblings().find('.selected').hide();
	$('.department').delay(500).fadeOut(0); 
	var costAttachNId=objindex.find(".costAttachId").val();
	var costAttachName=objindex.find(".costAttachName").val();
	objindex.find(".name1").html($(this).find('span').eq(0).html());
	objindex.find(".name1").find("input:eq(0)").attr("name",costAttachNId);
	objindex.find(".name1").find("input:eq(1)").attr("name",costAttachName);
	if(isShowAdmin!='0'){
		
	//管理员
	var orgId=objindex.find(".name1").find("input:eq(0)").attr("name",costAttachNId).val();//选择费用归属部门ID
	var htmlobj = $.ajax({url: "flight/admin.act",data:{orgId:orgId},async: false});
	var dataobj = htmlobj.responseText;
    var dataobjs=dataobj.split(',');//管理员id^管理员姓名
    //$("#admins").find('p').remove();
    objindex.parent().find(".admins").find('p').remove();
    if(dataobjs!=''){
    for(var i=0;i<dataobjs.length;i++){
    	var tdata=dataobjs[i].split("^");
        var sttr=`<p><span>`+tdata[0]+`</span><img src="img/choose.png" / class="selected"></p>`;
        objindex.parent().find(".admins").append(sttr);
    }
    objindex.parent().find(".name2").html(dataobjs[0].split("^")[0]);
    //存入缓存 
    $.cookie(objindex.parent().find('input:eq(0)').val()+":costid",$(this).find('span').eq(0).find("input:eq(0)").val());//费用归属id
    $.cookie(objindex.parent().find('input:eq(0)').val()+":costname",$(this).find('span').eq(0).html());//费用归属部门名称
    $.cookie(objindex.parent().find('input:eq(0)').val()+":admin",dataobjs[0].split("^")[0]);//所选的管理员
    $.cookie(objindex.parent().find('input:eq(0)').val()+":adminlist",objindex.parent().find(".admins").html());//管理员列表
    //$('.costd .name2').html(dataobjs[0].split("^")[0]);
    }else{
    	objindex.parent().find(".name2").html("");
    /*管理员*/
	 if(isShowAdmin=='2'){
	 		$(this).MyConfirm({
	  					content : "请维护企业管理员或者重新选择其他费用归属下的管理员!"		
	  					});
	return false;
	} 
    }
	}
});

/**
 * 管理员的效果与样式
 */
$('.adminss').click(function(){
	//$('.admin').fadeIn();
	$(this).next().fadeIn();
	layerShow($(this).next(), $(this).next().find('.payment-con'));
	//layerShow('.admin', '.admin .payment-con');
	adminindex=$(this);
	/*objindex = $(this);*/
});


$(function(){
	//获取所有乘机人id
	$('.detail').find('li').find('input:eq(0)').each(function(){
		var userid=$(this).val();
		var adminval=$.cookie(userid+":admin");
		if(!adminval){
			var orgId=	$(this).parent().find('.costbelong').find('.costid').val();
			//var orgId=$(".name1").last().find("input:eq(0)").val();//选择费用归属部门ID
			var htmlobj = $.ajax({url: "flight/admin.act",data:{orgId:orgId},async: false});
			var dataobj = htmlobj.responseText;
		    var dataobjs=dataobj.split(',');//管理员id^管理员姓名
		    var tdata=dataobjs[0].split("^");
		    $(this).parent().find('.costd .name2').html(tdata[0]);
		    //$('.costd .name2').html(tdata[0]);
		    if(dataobjs!=''){
		     for(var i=0;i<dataobjs.length;i++){
		     	var tdata=dataobjs[i].split("^");
		         var sttr=`<p><span>`+tdata[0]+`</span><img src="img/choose.png" / class="selected"></p>`;
		         $(this).parent().find('.admin').find(".admins").append(sttr); 
		     //$('.admin').find(".admins").append(sttr); 
		     //$("#admins").append(sttr);
		     }
		    }
		    
		    $.cookie(userid+":adminlist", $(this).parent().find('.admin').find(".admins").html());
		}else{
			
			var tdata=adminval.split(":");
			$(this).parent().find('.costd .name2').html(tdata[0]);
			var adminlist=	$.cookie(userid+":adminlist");	
			
			$(this).parent().find('.admin').find(".admins").append(adminlist);
			var costid=$.cookie(userid+":costid");
			var costname=$.cookie(userid+":costname");
			if(!costid&&!costname){
				return false;
			}else{
				$(this).parent().find('.costd .name1').html(costname);
				$(this).parent().find('.costd .name1').find("input:eq(0)").val(costid);
				$(this).parent().find('.costd .name1').find("input:eq(1)").val(costname);
			}
			
		}
	})
	
	
	
})


$(document).on('click','.admin .payment-choose p',function(){
	
	$(this).css('color', '#00AFEC').siblings().css('color', '');
	$(this).find('.selected').show().parents('.admin .payment-choose p').siblings().find('.selected').hide();
	$('.admin').delay(500).fadeOut(0);
	//$('.costd .name2').html($(this).find('span').eq(0).html());
	$(this).parent().parent().parent().parent().prev().children().find('.name2').html($(this).find('span').eq(0).html());
	
	$.cookie($(this).parent().parent().parent().parent().parent().find('input:eq(0)').val()+":admin",$(this).find('span').eq(0).html());//所选的管理员
});

/*------------结算方式、差旅说明、费用归属的遮罩层及内容展开的共同样式-------*/
function layerShow(obj, attrObj, numLength) {
	console.log(1)
	$(obj).fadeIn();
	$(attrObj).animate({
		height: numLength
	}, 1000)
};
/*--------点击除了div以外所有的空白区域，div隐藏---------*/

$('.instructe').bind('click', function(e) {
	hideLayer(e,'test','#test');
});

$('.payment').bind('click', function(e) {
	hideLayer(e,'tested','#tested');
});
$('.department').bind('click', function(e) {
	hideLayer(e,'tests','#tests');
});
$('.admin').bind('click', function(e) {
	hideLayer(e,'testdd','#testdd');
})
/*-----删除除了div之外任何区域，遮罩层隐藏----------*/
function hideLayer(e,Obj,idAttr){
	var e = e || window.event; //浏览器兼容性   
	var elem = e.target || e.srcElement;
	while(elem) { //循环判断至跟节点，防止点击的是div子元素   
		if(elem.id && elem.id == Obj) {
			return;
		}
		elem = elem.parentNode;
	}
	$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 
}
