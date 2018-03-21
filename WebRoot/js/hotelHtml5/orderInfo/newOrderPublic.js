
$("body").children().click(function () {});

$(function(){
	showFeeInfo();
	showApprovelPolicyList();
});

$(document).ready(function() {
   // 限制字符个数
   $('.cx').each(function() {
		var maxwidth = 4;
		if($(this).html().length > maxwidth) {
			 $(this).html($(this).html().substring(0, maxwidth));
			 $(this).html($(this).html() + '...');
		}
   	});
});

/**
 * 订单提交
 * @returns
 */
function submitOrder(confirmBooking){
	$(this).LoadingShow();
	var params = $("#sform").serializeObject();
	if(confirmBooking){
		params.confirmBooking = true;
	}
	//数据过滤
	for(var key in params){
		if(params[key] == null || params[key] == ''){
			delete params[key];
		}
	}
	$.ajax({
		type: 'post',
		data: params,
		url: base_path + 'neworder/saveOrder.act',
		success: function(data){
			dealWithOrderResult(data);
		},
		error: function(data){
			$(this).LoadingHide();
		}
	});
}
/**
 * 处理订单返回结果
 */
function dealWithOrderResult(data){
	$(this).LoadingHide();
	if(data.hotelCode == '1000'){
		doSuccessOrder(data.data);
	}else if(data.hotelCode == '-1'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1001'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '9999'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1011'){
		showMessage(data.hotelMsg, '变动提醒', true, '确认预订', '我再想想');
	}else if(data.hotelCode == '1012'){
		showMessage(data.hotelMsg, '变动提醒');
	}else if(data.hotelCode == '1013'){
		showOrderExceededRemind(data.msg);
	}else if(data.hotelCode == '1002'){
		showMessage(data.hotelMsg, '友情提醒');
	}else if(data.hotelCode == '1003'){
		showMessage(data.hotelMsg, '友情提醒');
	}
}
/**
 * 订单提交成功后处理
 * @param orderNo
 */
function doSuccessOrder(params){
	params.orderStatueName = encodeURIComponent(params.orderStatueName);
	window.location.href = base_path + 'neworder/toSuccess.act?' + $.param(params);
}
/**
 * 展示超标页面
 */
function showOrderExceededRemind(msg){
	$("#main_div").hide();
	$("#orderExceededRemind_div").show();
	$.ajax({
		type: 'post',
		data: {"userId": bookingUserId, "msg": msg},
		url: base_path + 'neworder/toChangeRemind.act',
		success: function(resp){
			$("#orderExceededRemind_div").html(resp);
		}
	});
}
/**
 * 选择超规理由,并赋值
 * @param superRuleReason
 */
function setSuperRuleReason(superRuleReason){
	$("#superRuleReason").val(superRuleReason);
}
/**
 * 超标页面--确认预订
 * @returns
 */
function exceededRemind_confirm(){
	$("#main_div").show();
	$("#orderExceededRemind_div").hide();
	var v = $("#superRuleReason").val();
	if(v == ''){
		showMessage("请输入超标理由！", '友情提醒');
		return false;
	}
	submitOrder(true);
}
/**
 * 超标页面--我再想想
 * @returns
 */
function exceededRemind_cancle(){
	$("#main_div").show();
	$("#orderExceededRemind_div").hide();
	$("#superRuleReason").val("");
}

/**
 * 展示审批政策列表
 */
function showApprovelPolicyList(){
	var guestIds = new Array();
	var costIds = new Array();
	$(".guestId").each(function(){
		guestIds.push($(this).val());
	});
	$(".costId").each(function(){
		costIds.push($(this).val());
	});
	$.ajax({
		type: 'post',
		data: {"userIds":JSON.stringify(guestIds), "deptIds":JSON.stringify(costIds), 
				"bookingUserId":bookingUserId, "bookingEnterpId":bookingEnterpId, "areaId":areaId,
				"hotelType":hotelType, "hotelStar":hotelStar,"hotelRoomPrice":hotelRoomPrice},
		url: base_path + '/neworder/toApprovePolicyDetail.act',
		success: function(resp){
			$("#approve_list").html(resp);
		}
	});
	
}
/**
 * 显示费用信息
 */
function showFeeInfo(){
	bookingTotalPrice = Number(bookingTotalPrice) * (Number(newRoomCount) / Number(roomCount));
	roomFee = Number(roomFee) * (Number(newRoomCount) / Number(roomCount));
	taxFee = Number(taxFee) * (Number(newRoomCount) / Number(roomCount));
	
	var roo_fee = Number(roomFee) / Number(newRoomCount);	//房间单价
	var taxe_fee = Number(taxFee) / Number(newRoomCount);	//税费单价
	
	$('.roos_fee').text(roo_fee + ' * ' + newRoomCount);
	$('.taxes_fee').text(taxe_fee == '0' ? '0' : taxe_fee + ' * ' + newRoomCount);
	$('#rooms_fee').val(roomFee);
	$('#taxess_fee').val(taxe_fee);
	$('#tmcOrderHotel_roomFee').val(roomFee);
	$('#tmcOrderHotel_taxFee').val(taxe_fee);
	
	$.ajax({
		type: 'post',
		async: false,
		data: {"userId":bookingUserId, "choiceAgreementHotelFlag":choiceAgreementHotelFlag, "amount": bookingTotalPrice, "nightCount": nightCount, "roomCount": newRoomCount},
		url: base_path + 'neworder/hotelServiceFee.act',
		success: function(data){
			var nightFee = data.nightFee;
			var bookingFee = data.bookingFee;
			$('.night_fee').text(nightFee + '.00');
			$('.booking_fee').text(bookingFee + '.00');
			$('#tmcOrderHotel_nightFee').val(nightFee);
			$('#tmcOrderHotel_bookingFee').val(bookingFee);
		}
	});
	getAllMoney();
}

/**
 * 查找公司/部门
 * @param obj
 * @returns
 */
function queryCompanyApt(obj){
	var namekey = $(obj).val();
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, namekey);
}
/**
 * 重载查找公司/部门页面
 */
function reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, namekey){
	$.ajax({
		type: 'post',
		url: base_path + 'neworder/toCompanyAptDetail.act',
		data: {"userId":bookingUserId, "enterpId":bookingEnterpId, "idFlag":idFlag, "namekey":namekey, "tripType": tripType},
		success: function(resp){
			$("#myTable").html(resp);
		}
	});
}
/**===========================================================================================================================================================
/**
 * -----明细的计算----
 * @returns
 */
function getAllMoney(){
	var allMoney = Number($('#rooms_fee').val()) + Number($('#taxess_fee').val()) + Number($('.booking_fee').html()) + Number($('.night_fee').html()) - Number($('.treatment').html());
	$('.f_left .money').html('¥' + allMoney + '.00');
	$("#orderTotalAmount").val(allMoney);
}

/*-----结算方式选择-----*/
var numLength = ($('.payment .payment-choose .rows').length + 1) * 80 / 20 + 'rem';
$('.method').click(function() {
	$('.payment').fadeIn();
	$('.payment-con').animate({
		height: numLength
	}, 1000);
	$("body,html").css({"overflow":"hidden"});
});

var html;
var htmlValue;
function chooseH(obj, ele) {
	html = $(obj).find('span').html();
	htmlValue = $(obj).find('input').val();
	if($(obj).find('span').hasClass('color') && $(obj).find('img').hasClass('selected')) {
		$(obj).find('span').removeClass('color');
		$(obj).find('img').removeClass('selected');
		html = '';
		htmlValue = '';
		return false;
	};
	$(obj).find('span').addClass('color').parents('.rows').siblings().find('span').removeClass('color');
	$(obj).find('img').addClass('selected').parents('.rows').siblings().find('img').removeClass('selected');
	$(ele).fadeOut();
	$("body,html").css({"overflow":""});
}
$('.payment-choose .rows').click(function() {
	chooseH(this, '.payment');
	$('.method .mode').html(html);
	$("#payType").val(htmlValue);
});

/*--------金额分摊/比例分摊---------*/		
$('.choose_share').click(function(){
	$('.share_').fadeIn();
	$('#share_').animate({
		height: 12 + 'rem'
	}, 1000);
	$('body,html').css({'overflow':'hidden'});
});
$('.share-choose .rows').click(function() {
	chooseH(this, '.share_');
	$('.choose_share input').val(html);
	$(".shareType").val(htmlValue);
});

$('.clsoeB').click(function(){
	$('.bounced').fadeOut();
	$('body,html').css({'overflow':''});
});

/*--------部门添加事件----------*/

$('.add').click(function() {
	addDepartent();
});

var costDeptIndex = parseInt($("#cost_dept_length").val()) - 1;
function addDepartent() {
/*	var shareType = '';
	$(".shareType").each(function(){
		shareType = $(this).val();
	});*/
	
	costDeptIndex++;
	var bmStr = '<li class="department" id="costDept_' + costDeptIndex + '">'
				+'	<input type="hidden" class="costId" id="costDept_' + costDeptIndex + '_id" name="tmcOrderCostShareList[' + costDeptIndex + '].shareDeptId" value="" />'
				+'	<input type="hidden" id="costDept_' + costDeptIndex + '_name" name="tmcOrderCostShareList[' + costDeptIndex + '].shareDeptName" value="" />'
				+'	<div style="display: flex;flex-direction: row;" class="aw">'
				+'		<p class="dptm">请选择</p>'
				+'		<div style="margin: auto 0;">'
				+'			<img src="../hotelimg/you.png" / class="you">'
				+'		</div>'
				+'	</div>'
				+'	<input type="text" name="tmcOrderCostShareList[' + costDeptIndex + '].shareValue" id="" value="" placeholder="请输入分摊百分比或金额" onblur="validateShareValue(this)" />'
				+'	<input type="hidden" name="tmcOrderCostShareList[' + costDeptIndex + '].shareType" class="shareType" value="0001500002" />'
				+'	<div style="margin: auto 0;">'
				+'		<img src="../hotelimg/jian.png" / class="jian">'
				+'	</div>'
				+'</li>';
	$('#proportion ul').append(bmStr);
}

/*-------部门删除事件------*/
$('body').on('click', '.department .jian', function() {
	$(this).parents('.department').remove();
});


/*--------明细点击事件-------*/
$('#detail').click(function() {
	$('.amount_').fadeIn();
	$("body,html").css({ "overflow": "hidden" });
	$('#amount').slideDown();
	$('footer').css('z-index', '999999991');
});


/*------------保险开关的点击事件-----*/
$("#integral_btn").click(function(){
	if(userIntegral == 0){
		showMessage("剩余积分0，不可用", '友情提醒');
		return false;
	}
	var c = $("#interest_btn").find('img').attr('class');
	if(c == 'circleBtn'){	//金旅豆关，积分可以开
		discountBtn(this);
	}else{
		showMessage("积分金旅豆二选一", '友情提醒');
	}
});
$("#interest_btn").click(function(){
	if(userIntegral == 0){
		showMessage("剩余金旅豆0，不可用", '友情提醒');
		return false;
	}
	var c = $("#integral_btn").find('img').attr('class');
	if(c == 'circleBtn'){	//积分关，金旅豆可以开
		setInterest();
		discountBtn(this);
	}else{
		showMessage("积分金旅豆二选一", '友情提醒');
	}
});
/**
 * 积分/金旅豆开关
 * @param btnSwitch	按钮开关（true:开；false:关）
 */
function discountBtn(obj){
	if(!this.flag) {
		$(obj).find('img').attr('class', 'circleBtns');
		$(obj).css('background', '#00AFEC');
		$(obj).parents('.integral').siblings().css('display','flex');
	} else {
		$(obj).find('img').attr('class', 'circleBtn');
		$(obj).css('background', '#CCCCCC');
		$(obj).parents('.integral').siblings().slideUp();
		$(obj).parent().parent().find('input').val("");
		$('.treatment').text('0.00');
		getAllMoney();
	}
	this.flag = !this.flag;
}
/**
 * 设置积分
 * @param obj
 */
function setIntegral(obj){
	var value = $(obj).val() == '' ? 0 : $(obj).val();
	var reg = /^\d*$/;
	if(reg.test(value)){
		value = parseInt(value);
		if(value % 100 == 0){
			value = value / 100;
			$("#integral_price_id").val(value);
			$('.treatment').text(value + '.00');
			getAllMoney();
		}
	}
}
/**
 * 积分校验
 * @param obj
 */
function valideIntegral(obj){
	var value = $(obj).val() == '' ? 0 : $(obj).val();
	value = parseInt(value);
	if(value % 100 != 0){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage('请输入100倍数的积分额', '友情提醒');
		return false;
	}else if(value > userIntegral){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage('积分额仅剩余' + userIntegral, '友情提醒');
		return false;
	}
	//不含优惠的价格
	var totVP = Number($('#rooms_fee').val()) + Number($('#taxess_fee').val()) + Number($('.booking_fee').html()) + Number($('.night_fee').html());
	//优惠价
	var curVP = parseInt(value) / 100;
	if(curVP > totVP){
		$(obj).val("");
		$('.treatment').text('0.00');
		getAllMoney();
		showMessage("您的优惠金额不能超过订单总额！", '友情提醒');		
		return false;
	}
}
/**
 * 设置金旅豆
 * @param obj
 */
function setInterest(){
	var interestPrice = userInterest;
	var totP = parseInt($("#orderTotalAmount").val());
	if(interestPrice > totP){
		interestPrice = totP;
	}
	$("#interest_price_id").val(interestPrice);
	$('.treatment').text(interestPrice + '.00');
	getAllMoney();
}

/*-----金额比例的算法-------*/
var array = [];
var arrays = [];
$('.footer .sure').click(function() {
	/*if($('body .dptm').val() == ""){
		return false;
	}*/
	array = [];
	arrays = []
	$('body .department').each(function() {
		if(isNaN(Number($(this).find('input').val()))) {
			array.push(Number($(this).find('input').val().replace(/%/, "")))
		} else {
			arrays.push(Number($(this).find('input').val()));
		}
	})

	var allN = array.reduce((count, v) => count + v, 0);

});

/*----------使用积分的提示框--------*/
$('.to_use .aw').click(function() {
	$('.prompting').fadeIn();
	$("body,html").css({ "overflow": "hidden" });
});

/*-------使用金旅豆的提示框-------*/
$('.interest .aw').click(function() {
	$('.interest_').fadeIn();
	$("body,html").css({ "overflow": "hidden" });
});

/*---------审批政策的效果------*/
function showApprovalDetail(approvalId){
	$('.examine').fadeIn();
	$('body,html').css({ 'overflow': 'hidden' });
	var html = '';
	$.ajax({
		type: 'post',
		url: base_path + 'neworder/getPolicyApprovalDetail.act',
		data: {"approvalId": approvalId},
		success: function(resp){
			$("#policy_approvalDetail").html(resp);
		}
	});
}

/*-----删除除了div之外任何区域，遮罩层隐藏----------*/
function hideLayer(e, Obj, idAttr) {
	var e = e || window.event; //浏览器兼容性   
	var elem = e.target || e.srcElement;
	while(elem) { //循环判断至跟节点，防止点击的是div子元素   
		if(elem.id && elem.id == Obj) {
			return;
		}
		elem = elem.parentNode;
	}
	$(idAttr).parents('.bounced').css('display', 'none'); //点击的不是div或其子元素 
	$('body,html').css({"overflow": ""});
}
$('.amount_').bind('click', function(e) {
	hideLayer(e, 'matter', '#matter');
	$('#amount').css('display', 'none');
	$('footer').css('z-index', '');
});

$('.payment').bind('click', function(e) {
	hideLayer(e, 'tested', '#tested');
});

$('.prompting').bind('click', function(e) {
	hideLayer(e, 'prompting', '#prompting');
});

$('.interest_').bind('click', function(e) {
	hideLayer(e, 'interest_', '#interest_');
});

$('.examine').bind('click', function(e) {
	hideLayer(e, 'policy_', '#policy_');
});

$('.share_').bind('click', function(e) {
	hideLayer(e, 'share_', '#share_');
});

$('.apt').bind('click', function(e) {
	hideLayer(e, 'apt', '#apt');
});


$('.nav .you').click(function(e) {
	e.stopPropagation();
	$('.apt').fadeOut();
	$('body,html').css({ "overflow": "" });
});
/*---------返回上一页--------*/
$('.top_m .you').click(function() {
	var params = $("#sform").serializeObject();
	//数据过滤
	for(var key in params){
		if(params[key] == null || params[key] == ''){
			delete params[key];
		};
	}
//	$.StandardPost(base_path + 'neworder/backStepOne.act', params);
	window.location.href = base_path + 'neworder/backStepOne.act?' + $.param(params);
});

$('.invoice input').click(function() {
	$('.apt').fadeIn();
	$('body,html').css({ "overflow": "hidden" });
	var idFlag = 'invoice';
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, "");
});
$('body').on('click','#myTable tr',function(){
	$('.bounced').fadeOut();
	$('body,html').css({ "overflow": "" });
});

$('body').on('click','.department .dptm',function(){
	$('.apt').fadeIn();
	$('body,html').css({ "overflow": "hidden" });
	var idFlag = $(this).parent().parent().attr("id");
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, "");
});
$('.department .dptm').click(function(){
	$('.apt').fadeIn();
	$('body,html').css({ "overflow": "hidden" });
	var idFlag = $(this).parent().parent().attr("id");
	reloadCompanyAptDetail(bookingUserId, bookingEnterpId, idFlag, "");
});
/**===========================================================================================================================================================
/**
 * 选择审批政策，并赋值
 * @param approvePolicyId
 */
function setApprovePolicyId(approvePolicyId){
	$("#approvePolicyId").val(approvePolicyId);
}
/**
 * 设置审批列表个数
 * @param approvePolicyId
 */
function setPlicyRuleListLength(length){
	$("#plicyRuleListLength").val(length);
}
/**
 *  设置开票公司信息/费用归属信息
 * @param idFlag
 * @param id
 * @param value
 */
function setDeptInfo(idFlag, id, name){
	$('#' + idFlag + '_id').val(id);
	$('#' + idFlag + '_name').val(name);
	//费用归属部门设置文本值
	if(idFlag.indexOf('costDept') > -1){
		if(name.length > 4){
			name = name.substring(0, 4) + '...';
		}
		$('#' + idFlag).find('.dptm').text(name);
		showApprovelPolicyList();
	}
}
/**
 * 校验费用归属的分摊金额/比例是否数值类型
 * @param obj
 */
function validateShareValue(obj){
	var value = $(obj).val();
	var reg = /^\d*(.\d{2})?$/;
	if(!reg.test(value)){
		showMessage("请输入正确的费用归属的分摊比例！！", '友情提醒');	
		$(obj).val('');
		return false;
	}
}
/**===============================================================================================================================================**/
/**
 * 提示框显示
 * @param mess
 */
function showMessage(mess, title, hasCancelBtn, confirmBName, cancelBName){
	$(this).MyConfirm({
		title: title,
		content: mess,
		hasCancelBtn: hasCancelBtn,
        confirmBtnName: confirmBName,
        cancelBtnName: cancelBName,
	}, function callback(isConfirm){
		if(isConfirm && hasCancelBtn){
			submitOrder(true);
		}
	});
}

