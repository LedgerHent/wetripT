/*---禁止遮罩层后的body上下滑动---*/
function stop(){
  $('body,html').css({ 'overflow': "hidden" });
  document.ontouchmove=function(){return false;}
 }
/*---解除遮罩层后的body上下滑动---*/
 function remove(){
  $('body,html').css({ 'overflow': "" });
  document.ontouchmove=function(){return true;}
 }
