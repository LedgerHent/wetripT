(function ($) {

	//弹出提示框，背景高斯模糊效果参数
    var containerId = "#container";
    var blurClassName = "blur";
	
    /*
    *jQuery插件：confirm提示框
    *
    *@param {object} 参数列表
    *@param {function} 回调方法
    *
    *使用方法: 使用jQuery对象直接调用MyConfirm(options, callback)方法
    */
    $.fn.MyConfirm = function (options, callback) {

        var defaults = {
            imgUrl: "images/icon-37.png ",      //图片
            title: "温馨提示",                  	//标题
            titleBGColor: "#01AFEC",            //标题背景色
            titleColor: "#fff",                 //标题文字颜色
            content: "",                        //提示文字
            contentBgColor: "",                 //提示文字区域背景色
            contentFontColor: "#000",           //提示文字字体颜色
            hasCancelBtn: false,                //是否有取消键
            btnFontColor: "#01AFEC",            //按钮文字颜色
            btnBGColor: "",                     //按钮背景色
            overflowBGColor: "#ccc",            //遮罩层背景色
            ofOpacity: 0.5,                      //遮罩层透明度
            confirmBtnName: "确定",
            cancelBtnName: "取消"
        };
        var options = $.extend(defaults, options);
        //标题文字以及提示文字
        var title = (options.title == null || options.title == "") ? "温馨提示" : options.title;
        var content = (options.content == null || options.content == "") ? "" : options.content;
        var confirmBtnName = (options.confirmBtnName == null || options.title == "") ? "确定" : options.confirmBtnName;
        var cancelBtnName = (options.cancelBtnName == null || options.cancelBtnName == "") ? "取消" : options.cancelBtnName;
        //要生成的html页面
        var html = "<div class='tip' style='z-index:2147483510'><div class='tip-pop' style='z-index:2147483509'></div> <div class='tip-con' style='z-index:21474835119999999'><div class='tip-top'>" + title + "</div> <div class='tip-cer'><dl><dt>" + content + "</dt><dd>  ";
        
        if (options.hasCancelBtn)
            html += "<a id='' class='_over'>" + confirmBtnName + "</a><a id=''>" + cancelBtnName + "</a>";
        else
            html += "<a id='' class='_over' style='width:100%;'>" + confirmBtnName + "</a>";
        html += "</dd> </dl> </div></div></div>";


        //添加到body中
        $("body").append(html);
        //获取要设置的jQuery对象
        var $confirmDiv = $(".tip");
        var $overflow = $(".tip-pop");
        var $title = $(".tip-top");
        var $content = $(".tip-cer dl dt");
        var $btn = $(".tip-cer dl dd a");
        var $tipcon = $(".tip-con");
        //设置样式
        if (options.imgUrl != null && options.imgUrl != '')
            $title.css({ 'background-image': 'url(' + options.imgUrl + ')' });
        if (options.titleBGColor != null && options.titleBGColor != '')
            $title.css({ 'background-color': options.titleBGColor });
        if (options.titleColor != null && options.titleColor != '')
            $title.css({ 'color': options.titleColor });
        if (options.contentBgColor != null && options.contentBgColor != '')
            $content.css({ 'background-color': options.contentBgColor });
        if (options.contentFontColor != null && options.contentFontColor != '')
            $content.css({ 'color': options.contentFontColor });
        if (options.contentFontColor != null && options.contentFontColor != '')
            $content.css({ 'color': options.contentFontColor });
        if (options.width > 0 && options.width < 600)
            $tipcon.css({ 'width': options.width });
        if (options.overflowBGColor != null && options.overflowBGColor != '')
            $overflow.css({ 'background-color': options.overflowBGColor });
        if (options.ofOpacity >= 0 && options.ofOpacity <= 1)
            $overflow.css({ 'opacity': options.ofOpacity + '' });

        //设置背景高斯模糊
        $(containerId).addClass(blurClassName);
        
        this.each(function () {
            //绑定点击事件
            $($btn).each(function () {
            	//隐藏滚动条
            	hiddenScoll();
                var isConfirm = true;
                if ($(this).text() == cancelBtnName) {
                    isConfirm = false;
                }
                if (options.btnFontColor != null && options.btnFontColor != '')
                    $(this).css({ 'color': options.btnFontColor });
                if (options.btnBGColor != null && options.btnBGColor != '')
                    $(this).css({ 'background-color': options.btnBGColor });
                $(this).click(function () {
                    //删除弹层 移除背景高斯模糊
                    $confirmDiv.remove();
                    $(containerId).removeClass(blurClassName);

                    //回调函数
                    if (callback) callback(isConfirm);
                    //显示滚动条
                    showScoll();
                })
            });
        });

        if (options.btnFontColor != null && options.btnFontColor != '')
            $overflow.css({ 'color': options.btnFontColor });
        //设置完毕，显示confirm
        if ($overflow.attr('display') == 'none')
            $overflow.css({ 'display': 'block' });
    };
    
    /*
     * loading加载页面显示
     */
    $.fn.LoadingShow = function (){
    	 var html = "<div class='tip'><div class='tip-pop'>";
    	 html += "<div class='spinner'>"+
					  "<div class='spinner-container container1'>"+
					  "  <div class='circle1'></div>"+
					  "  <div class='circle2'></div>"+
					  "  <div class='circle3'></div>"+
					  "  <div class='circle4'></div>"+
					  "</div>"+
					  "<div class='spinner-container container2'>"+
					  "  <div class='circle1'></div>"+
					  "  <div class='circle2'></div>"+
					  "  <div class='circle3'></div>"+
					  "  <div class='circle4'></div>"+
					  "</div>"+
					  "<div class='spinner-container container3'>"+
					  "  <div class='circle1'></div>"+
					  "  <div class='circle2'></div>"+
					  "  <div class='circle3'></div>"+
					  "  <div class='circle4'></div>"+
					  "</div>"+
					"</div>";
    	 html += "</div></div>";
    	 //添加到body中
         $("body").append(html);
         //设置背景高斯模糊
         $(containerId).addClass(blurClassName);
         hiddenScoll();
    };
    
    
    /*
     * loading加载页面影藏
     */
    $.fn.LoadingHide = function (){
    	//删除弹层 移除背景高斯模糊
    	$(".tip").remove();
        $(containerId).removeClass(blurClassName);
        showScoll();
    };
    
    
    function hiddenScoll(){
    	 $("html").css("overflow-x","hidden");
         $("html").css("overflow-y","hidden");
    };
    
    function showScoll(){
    	$("html").css("overflow-x","auto");
        $("html").css("overflow-y","auto");
    };

    /*
     * 加载页面影藏
     */
    $.fn.layerShow = function (divClassName){
    	//弹层 背景高斯模糊
    	$(containerId).addClass(blurClassName);
    	$('.'+divClassName).fadeIn();
    };
    
    /*
     * 加载页面影藏
     */
    $.fn.layerHide = function (divClassName){
    	//弹层 背景高斯模糊
    	$('.'+divClassName).fadeOut();
    	$(containerId).removeClass(blurClassName);
    };
    
})(jQuery);