/*$('.cancel-list').on('click',function(){
	var height = $("body").height();
	$('.bounced').height(height);
	$('.cancleBtn').slideDown()
});



//取消订单
$('.true-tips1').on('click',function(){
	if(confirm('确定要保存内容吗')){
	//var orderId = $(e).attr("orderId");
	orderId=$("#oid").attr("oid");
if ($("#cancel_reason").val() == "" || $("#cancel_reason").val() == "请填写取消理由") {
     //easyAlert1('提示信息', '请填写取消订单原因！', 'error');
     alert('请填写取消订单原因！');
	   } else {
	     if ($("#cancel_reason").val().length < 3) {
         alert('取消订单原因太少！');
     } else if ($("#cancel_reason").val().length > 100) {
        alert('提示信息', '取消订单原因太长！', 'error');
     } else {
         parm = "&orderId="+orderId + "&memo=" + $("#cancel_reason").val();
         htmlobj = $.ajax({type:'POST',url: "../auditorder/cancelOrder.act",dataType:"text",data:"parm="+parm, async: false});
         var cancelresult = htmlobj.responseText;
         
         if (cancelresult == "0") {
        	 alert('取消订单成功！');
             $(".audit").html('<h5 class="audit">订单取消</h5>');	               
             $(".cancel-list").hide();
             $(".refused-list").hide();	
   			 $(".audit-list").hide();		
             $('.bounced').slideUp();	
             return;		
         } else{
         alert("取消失败 ");
         $('.bounced').slideUp();
         }
          	             
     }

	}
	
	}else{
		$('.bounced').slideUp();
		return;
	}
});


//订单审核拒绝
$('.true-tips2').on('click',function(){

	if(confirm('确定要保存内容吗')){
	//orderId = $(this).attr("orderId");
	orderId=62373;
		if ($("#refuse_reason").val() == "" || $("#refuse_reason").val() == "请填写拒绝理由") {
    alert('请填写审核不通过原因！');
    } else {
    parm = "&orderId="+orderId + "&type=2&memo=" + $("#refuse_reason").val();
    htmlobj = $.ajax({type:'POST',url: "../auditorder/auditorder.act",dataType:"text",data:"parm="+parm, async: false});
    var noconfirm = htmlobj.responseText;
    if (noconfirm == "0") {  //成功拒绝审核
    alert("成功拒绝审核")
   	$(".audit").html('<h5 class="audit">审核拒绝</h5>');
   		
   		$(".refused-list").hide();	
   		$(".audit-list").hide();	
		$(".cancel-list").hide();	
   		$('.bounced').slideUp();
   		return;			
	}else{
		alert("拒绝审核失败")
		$('.bounced').slideUp();
		
		return;
	}
	}
	}else{
	
	$('.bounced').slideUp();

	return;
	}
 	});
 	
 	
$('.closed-tips a').on('click',function(){
	$('.bounced').slideUp();
});
//定单审核拒绝
$('.refused-list').on('click',function(){
	var height = $("body").height();
	$('.bounced').height(height);
	$('.resoultlBtn').slideDown()
});

//订单审核通过
$('.audit-list').on('click',function(){

//orderId = $(this).attr("orderId");
orderId=62373;
parm = "&orderId="+orderId + "&type=1";


$.ajax({
		type:"post",
		url:"../auditorder/auditorder.act",
		dataType:"text",
		data:"parm="+parm,
		success:function(status){
			if(status == 0){
				alert("审核通过")
				$(".audit").html('<h5 class="audit">审核通过</h5>');  
				$(".audit-list").hide();	
				$(".refused-list").hide();
				
			}else{
				
				alert("审核失败")
			
			}
		
		

		},
		error:function(){
			alert("请求失败");
		}
	})

	
});*/