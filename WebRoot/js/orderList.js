/*该退政策提示框的显示和隐藏*/

$('.btna').on('click', function() {
	var height = $("body").height();
	$('.bounced').height(height);
	$('.bun').animate({
		height: 'show'
	}, 1500);
});
$('.closedBtn img').on('click', function() {
	$('.bounced').animate({
		height: 'hide'
	}, 1500);
});
/*航班详情提示框的显示和隐藏*/
$('.title-con').on('click', function() {
	var height = $("body").height();
	$('.bounced').height(height);
	$('.bounceds').animate({
		height: 'show'
	}, 1500);
});