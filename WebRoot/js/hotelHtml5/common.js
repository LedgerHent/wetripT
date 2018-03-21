var base_path = "/wetrip/"
	
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name]
							.push(this.value === 0 ? 0 : ($.trim(this.value) || ''));
				} else {
					o[this.name] = this.value === 0 ? 0 : ($.trim(this.value) || '')
				}
			});
	return o;
};

/**
 * POST 请求方式的页面跳转
 */
$.extend({
    StandardPost:function(url,args){
        var form = $("<form method='post'></form>"),
            input;
        form.attr({"action":url});
        $.each(args,function(key,value){
            input = $("<input type='hidden'>");
            input.attr({"name":key});
            input.val(value);
            form.append(input);
        });
        form.submit();
    }
});