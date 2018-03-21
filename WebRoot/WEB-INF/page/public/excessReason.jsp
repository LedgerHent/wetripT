<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../public/tag.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.viptrip.ticket.entity.*"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html style="overflow:hidden">
<head>
	 <base href="<%=basePath%>">
	
<style type="text/css">
.l-dialog-content{overflow-y:hidden}
</style>
 <script type="text/javascript"  src="./js/jquery-1.11.2.min.js"></script>
 <script type="text/javascript" src="./libs/gublic.js"></script>
 <script type="text/javascript"  src="./js/MyConfirm.js"></script>

<script type="text/javascript">
var qucheng="";
var fancheng="";

 $(document).ready(function(){
		 var quExcessDiv="";
		 var fanExcessDiv="";
		 var itemTrip='${bf.tripexcessinfo}';
		 //alert(itemTrip);
		 if(itemTrip != null && itemTrip != ""){
		 	 qucheng=1;
             quExcessDiv=document.getElementById('quExcessDiv');
       	 	 $("#trip").append(itemTrip);
         }else{
             $("#qu").remove();
         }
         
         var itemReTrip='${bf.retripexcessinfo}';
         if(itemReTrip != null && itemReTrip != ""){
         	 fancheng=1;
             fanExcessDiv=document.getElementById('fanExcessDiv');
       	  	 $("#reTrip").append(itemReTrip);
         }else{
             $("#fan").remove();
         }
         
         var array = new Array();  
         <c:forEach items="${bf.excessList}" var="item" varStatus="status" >  
	        array.push("${item}");  
	     </c:forEach> 
	     for(var i=0;i<array.length;i++){  
		      var excesscc=array[i];  
		      if(itemTrip != null && itemTrip != ""){
		        var quOption=new Option("   "+excesscc+"     ",excesscc);
		        quExcessDiv.add(quOption); //这个兼容IE与firefox
		      }
		      if(itemReTrip != null && itemReTrip != ""){
		        var fanOption=new Option("   "+excesscc+"     ",excesscc);
		        fanExcessDiv.add(fanOption); //这个兼容IE与firefox
		      }
		 }    
}); 

function queding(){
    if(qucheng!='0' && qucheng!=''){
        var quXuanzeReason=$("#quExcessDiv").val();
        var quTianxieReason=$("#quReason").val();
        //alert(quXuanzeReason);
        if((quXuanzeReason==null||quXuanzeReason==''||quXuanzeReason=='请选择')&&
         (quTianxieReason==null||quTianxieReason==''||quTianxieReason=='去程其他超标理由')){
	        alert('请选择去程超标理由或者填写去程超标理由');
	        return false;
        }else{
            if(quXuanzeReason!=null&&quXuanzeReason!=''&&quXuanzeReason!='请选择'){
                //art.dialog.data('quXuanzeReason',quXuanzeReason);
                $("#quXuanzeReason").val(quXuanzeReason);
            }else{
                //art.dialog.data('quXuanzeReason','');
                $("#quXuanzeReason").val();
            }
            if(quTianxieReason!=null&&quTianxieReason!=''&&quTianxieReason!='去程其他超标理由'){
                //art.dialog.data('quTianxieReason',quTianxieReason);
                $("#quTianxieReason").val(quTianxieReason);
            }else{
                //art.dialog.data('quTianxieReason','');
                $("#quTianxieReason").val();
            }
        }
        
    }
    if(fancheng!='0' && fancheng!=''){
        var fanXuanzeReason=$("#fanExcessDiv").val();
        var fanTianxieReason=$("#fanReason").val();
        if((fanXuanzeReason==null||fanXuanzeReason==''||fanXuanzeReason=='请选择')&&
         (fanTianxieReason==null||fanTianxieReason==''||fanTianxieReason=='回程其他超标理由')){
	        alert('请选择回程超标理由或者填写回程超标理由');
	        return false;
        }else{
            if(fanXuanzeReason!=null&&fanXuanzeReason!=''&&fanXuanzeReason!='请选择'){
               // art.dialog.data('fanXuanzeReason',fanXuanzeReason);
               $("#fanXuanzeReason").val(fanXuanzeReason);
            }else{
                //art.dialog.data('fanXuanzeReason','');
                $("#fanXuanzeReason").val();
            }
            if(fanTianxieReason!=null&&fanTianxieReason!=''&&fanTianxieReason!='回程其他超标理由'){
               // art.dialog.data('fanTianxieReason',fanTianxieReason);
               $("#fanTianxieReason").val(fanTianxieReason);
            }else{
              //  art.dialog.data('fanTianxieReason','');
               $("#fanTianxieReason").val();
            }
        }
    }
    
	$("#subForm").attr("action","flight/toBookTicket.act");
    $(document).LoadingShow();
	setTimeout(function(){
		 $("#subForm").submit();
	},100)
	//art.dialog.close();
}
function quOnfocus(obj){
   if(obj.value=='去程其他超标理由'){
       obj.value='';
       $("#quExcessDiv").hide();
       $("#quExcessDiv").val("请选择");
   }
}
function quOnblur(obj){
   if(obj.value==''){
       obj.value='去程其他超标理由';
       $("#quExcessDiv").show();
   }
}

function fanOnfocus(obj){
   if(obj.value=='回程其他超标理由'){
       obj.value='';
       $("#fanExcessDiv").hide();
       $("#fanExcessDiv").val("请选择");
   }
}
function fanOnblur(obj){
   if(obj.value==''){
       obj.value='回程其他超标理由';
       $("#fanExcessDiv").show();
   }
}

</script>
</head>
  
<body style="margin:0">
    <div  style="padding:20px; margin:0px;">
<div style="display:inline-block !important;display:inline;margin:4px 0; padding:20px; text-align:left; color:#000; border-radius:3px; border:1px solid #D7EAE2; " class="tips">
<!-- 查询条件 -->

<div class="">
		<h2 style="width:150px;">超标理由选择</h2>
</div>

<!-- <div style="height:340px;">
    <div id="quExcessDiv" style="float:left;width:200px;margin-left: 20px;margin-top: 40px">
        
    </div>
        
 <div style="float:right;width:200px;padding-right:150px;margin-top: 40px;">
	
	<textarea
	style="width: 234px; height: 50px; "
	onfocus="if(this.value=='请填写超标理由'){this.value='';this.style.color='#ccc';}"
	onblur="if (this.value==''){this.value='请填写超标理由';this.style.color='#ccc';}"
	id="quReason">请填写超标理由</textarea>
	<button type="button" class="btn-orange" style="float:left;margin-top: 20px;" onclick="queding()">确定</button>
	
 </div>
  
</div> -->
<form id="subForm" method="post">
<input type="hidden" id="bookinfo" name="bf" value='${bookinfo}' />
<input type="hidden" id="quXuanzeReason" name="quXuanzeReason" />
<input type="hidden" id="quTianxieReason" name="quTianxieReason" />
<input type="hidden" id="fanXuanzeReason" name="fanXuanzeReason" />
<input type="hidden" id="fanTianxieReason" name="fanTianxieReason" />
<div class="lid" style="height:100px;" id="qu">
	<div id = "trip"  >
	</div>
	<div style="margin-top:10px;"></div>
	<div style=" float: left;" >
		去程:<select style="width: 200px;" id="quExcessDiv">
			    <option>请选择</option>
		    </select>
	</div>
	<div style=" float: left;">
		<textarea
			style="width: 234px; height: 50px; margin-left: 30px;color:#ccc;"
			onfocus="quOnfocus(this)"
			onblur="quOnblur(this)"
			id="quReason">去程其他超标理由</textarea>
	</div>
</div>
<div style="margin-top:10px;"></div>
<div style="height: 100px;margin-top:100;" id="fan" >
	<div id = "reTrip">
	</div>
	<div style="margin-top:10px;"></div>
    <div style=" float: left;" >
		返程:<select style="width: 200px;" id="fanExcessDiv">
		        <option>请选择</option>
		    </select>
	</div>
	<div style=" float: left;">
		<textarea
			style="width: 234px; height: 50px; margin-left: 30px;color:#ccc;"
			onfocus="fanOnfocus(this)"
			onblur="fanOnblur(this)"
			id="fanReason">回程其他超标理由</textarea>
	</div>
 </div>
 </form>
 <div >
	<button type="button" class="btn-orange" style="float:left;margin-top: 20px;" onclick="queding()">确定</button>
 </div>
</div>
</div>	
<script type="">
//	  var fandivvalue=art.dialog.data('fancheng');
//	  //alert(fandivvalue);
//     if(fandivvalue*1>0){
//      	 fanDiv=document.getElementById('fan');
//      	 fanDiv.style.display = ""; 
//      }
</script>
  </body>
 
</html>
