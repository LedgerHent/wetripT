/* *
 * 全局空间 Vcity
 * */
var Vcity = {};
Vcity.allCity = {};
/* *
 * 静态方法集
 * @name _m
 * */
Vcity._m = {
	/* 选择元素 */
	$: function(arg, context) {
		var tagAll, n, eles = [],
			i, sub = arg.substring(1);
		context = context || document;
		if(typeof arg == 'string') {
			switch(arg.charAt(0)) {
				case '#':
					return document.getElementById(sub);
					break;
				case '.':
					if(context.getElementsByClassName) return context.getElementsByClassName(sub);
					tagAll = Vcity._m.$('*', context);
					n = tagAll.length;
					for(i = 0; i < n; i++) {
						if(tagAll[i].className.indexOf(sub) > -1) eles.push(tagAll[i]);
					}
					return eles;
					break;
				default:
					return context.getElementsByTagName(arg);
					break;
			}
		}
	},

	/* 绑定事件 */
	on: function(node, type, handler) {
		node.addEventListener ? node.addEventListener(type, handler, false) : node.attachEvent('on' + type, handler);
	},

	/* 获取事件 */
	getEvent: function(event) {
		return event || window.event;
	},

	/* 获取事件目标 */
	getTarget: function(event) {
		return event.target || event.srcElement;
	},

	/* 获取元素位置 */
	getPos: function(node) {
		var scrollx = document.documentElement.scrollLeft || document.body.scrollLeft,
			scrollt = document.documentElement.scrollTop || document.body.scrollTop;
		var pos = node.getBoundingClientRect();
		return { top: pos.top + scrollt, right: pos.right + scrollx, bottom: pos.bottom + scrollt, left: pos.left + scrollx }
	},

	/* 添加样式名 */
	addClass: function(c, node) {
		if(!node) return;
		node.className = Vcity._m.hasClass(c, node) ? node.className : node.className + ' ' + c;
	},

	/* 移除样式名 */
	removeClass: function(c, node) {
		var reg = new RegExp("(^|\\s+)" + c + "(\\s+|$)", "g");
		if(!Vcity._m.hasClass(c, node)) return;
		node.className = reg.test(node.className) ? node.className.replace(reg, '') : node.className;
	},

	/* 是否含有CLASS */
	hasClass: function(c, node) {
		if(!node || !node.className) return false;
		return node.className.indexOf(c) > -1;
	},

	/* 阻止冒泡 */
	stopPropagation: function(event) {
		event = event || window.event;
		event.stopPropagation ? event.stopPropagation() : event.cancelBubble = true;
	},
	/* 去除两端空格 */
	trim: function(str) {
		return str.replace(/^\s+|\s+$/g, '');
	}
};

/* *
 * 城市控件构造函数
 * @CitySelector
 * */

Vcity.CitySelector = function() {
	this.initialize.apply(this, arguments);
};

Vcity.CitySelector.prototype = {

	constructor: Vcity.CitySelector,

	/* 初始化 */

	initialize: function(options) {
		var input = options.input;
		this.input = Vcity._m.$('#' + input);
		this.inputEvent();
	},

	/* *
	    

	/* *
	 * @createWarp
	 * 创建城市BOX HTML 框架
	 * */

	createWarp: function() {
		if($(".tab-item a").html()=='海外') {
			/* 城市HTML模板 */
			Vcity._template = [
				'<p class="tip">直接输入可搜索城市(支持汉字/拼音)</p>',
				'<ul>',
				'<li class="on">热门城市</li>',
				'<li>亚洲及大洋洲</li>',
				'<li>美洲</li>',
				'<li>欧洲及非洲</li>',
				'</ul>'
			];
		} else if($(".tab-item a").html()=='国内港澳台'){
			// 城市HTML模板 
			Vcity._template = [
				'<p class="tip">直接输入可搜索城市(支持汉字/拼音)</p>',
				'<ul>',
				'<li class="on">热门城市</li>',
				'<li>A</li>',
			    '<li>B</li>',
			    '<li>C</li>',
			    '<li>D</li>',
			    '<li>E</li>',
			    '<li>F</li>',
			    '<li>G</li>',
			    '<li>H</li>',
			    '<li>I</li>',
			    '<li>J</li>',
			    '<li>K</li>',
			    '<li>L</li>',
			    '<li>M</li>',
			    '<li>N</li>',
			    '<li>O</li>',
			    '<li>P</li>',
			    '<li>Q</li>',
			    '<li>R</li>',
			    '<li>S</li>',
			    '<li>T</li>',
			    '<li>U</li>',
			    '<li>V</li>',
			    '<li>W</li>',
			    '<li>x</li>',
			    '<li>y</li>',
			    '<li>Z</li>',
				'</ul>'
			];
		}
		var inputPos = Vcity._m.getPos(this.input);
		var div = this.rootDiv = document.createElement('div');
		var that = this;

		// 设置DIV阻止冒泡
		Vcity._m.on(this.rootDiv, 'click', function(event) {
			Vcity._m.stopPropagation(event);
		});
		// 设置点击文档隐藏弹出的城市选择框
		Vcity._m.on(document, 'click', function(event) {
			event = Vcity._m.getEvent(event);
			var target = Vcity._m.getTarget(event);
			if(target == that.input) return false;
			//console.log(target.className);
			if(that.cityBox) Vcity._m.addClass('hide', that.cityBox);
			if(that.ul) Vcity._m.addClass('hide', that.ul);
			if(that.myIframe) Vcity._m.addClass('hide', that.myIframe);
		});
		div.className = 'citySelector';
		div.style.position = 'absolute';
		div.style.left = 1 + 'rem';
		div.style.top = 29+ 'rem';
		div.style.zIndex = 999999;

		// 判断是否IE6，如果是IE6需要添加iframe才能遮住SELECT框
		var isIe = (document.all) ? true : false;
		var isIE6 = this.isIE6 = isIe && !window.XMLHttpRequest;
		if(isIE6) {
			var myIframe = this.myIframe = document.createElement('iframe');
			myIframe.frameborder = '0';
			myIframe.src = 'about:blank';
			myIframe.style.position = 'absolute';
			myIframe.style.zIndex = '-1';
			this.rootDiv.appendChild(this.myIframe);
		}

		var childdiv = this.cityBox = document.createElement('div');
		childdiv.className = 'cityBox';
		childdiv.id = 'cityBox';
		childdiv.innerHTML = Vcity._template.join('');
		var hotCity = this.hotCity = document.createElement('div');
		hotCity.className = 'hotCity';
		childdiv.appendChild(hotCity);
		div.appendChild(childdiv);
		this.createHotCity();
	},

	/* *
	 * @createHotCity
	 * TAB下面DIV：hot,a-h,i-p,q-z 分类HTML生成，DOM操作
	 * {HOT:{hot:[]},ABCDEFGH:{a:[1,2,3],b:[1,2,3]},IJKLMNOP:{},QRSTUVWXYZ:{}}
	 **/

	createHotCity: function() {
		if($(".tab-item a").html()=='海外') {
			var abroadHotSize = $("#abroadHotSize").val();
			var abroadStr = $("#abroadCityArray").html();
			var abroadArray = eval('(' + abroadStr + ')');
			Vcity.allCity = abroadArray;
			/* 正则表达式 筛选中文城市名、拼音、首字母 */
			Vcity.regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\d{3,6})\|([\u4E00-\u9FA5\uf900-\ufa2d]+)$/i;
			Vcity.regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/;
			var citys = Vcity.allCity,
				match, letter,
				regEx = Vcity.regEx,
				reg2 = /^([亚洲])\|([大洋洲])$/i,
				reg3 = /^[美洲]$/i,
				reg4 = /^([欧洲])\|([非洲])$/i;
			Vcity.oCity = undefined;
			if(!Vcity.oCity) {
				Vcity.oCity = { hot: {}, 亚洲及大洋洲: {}, 美洲: {}, 欧洲及非洲: {} };
				for(var i = 0, n = citys.length; i < n; i++) {
					match = regEx.exec(citys[i]);
					if(i >= abroadHotSize) {
						letter = match[3];
						if(letter == "亚洲" || letter == "大洋洲") {
							if(!Vcity.oCity.亚洲及大洋洲[letter]) Vcity.oCity.亚洲及大洋洲[letter] = [];
							Vcity.oCity.亚洲及大洋洲[letter].push(match[1] + '|' + match[2]);
						} else if(letter.indexOf("美洲") > 0) {
							if(!Vcity.oCity.美洲[letter]) Vcity.oCity.美洲[letter] = [];
							Vcity.oCity.美洲[letter].push(match[1] + '|' + match[2]);
						} else if(letter == "欧洲" || letter == "非洲") {
							if(!Vcity.oCity.欧洲及非洲[letter]) Vcity.oCity.欧洲及非洲[letter] = [];
							Vcity.oCity.欧洲及非洲[letter].push(match[1] + '|' + match[2]);
						}
					}
					/* 热门城市 前abroadHotSize条 */
					if(i < abroadHotSize) {
						if(!Vcity.oCity.hot['hot']) Vcity.oCity.hot['hot'] = [];
						Vcity.oCity.hot['hot'].push(match[1] + '|' + match[2]);
					}
				}
			}
			var odiv, odl, odt, odd, odda = [],
				str, key, ckey, sortKey, regEx = Vcity.regEx,
				oCity = Vcity.oCity;
			for(key in oCity) {
				odiv = this[key] = document.createElement('div');
				// 先设置全部隐藏hide
				odiv.className = key + ' ' + 'cityTab hide';
				sortKey = [];
				for(ckey in oCity[key]) {
					sortKey.push(ckey);
					// ckey按照ABCDEDG顺序排序
					sortKey.sort();
				}

				for(var j = 0, k = sortKey.length; j < k; j++) {
					odl = document.createElement('dl');
					odt = document.createElement('dt');
					odd = document.createElement('dd');
					odt.innerHTML = sortKey[j] == 'hot' ? '&nbsp;' : sortKey[j];
					odda = [];

					for(var i = 0, n = oCity[key][sortKey[j]].length; i < n; i++) {
						var st = oCity[key][sortKey[j]][i].split("|");
						str = '<a id="' + st[1] + '">' + st[0] + '</a>';
						odda.push(str);
					}
					odd.innerHTML = odda.join('');
					if($('input:radio[name=homeAbroadFlag]:checked').val() == -99) {

					} else {
						odl.appendChild(odt);
					}
					odl.appendChild(odd);
					odiv.appendChild(odl);
				}

				// 移除热门城市的隐藏CSS
				Vcity._m.removeClass('hide', this.hot);
				this.hotCity.appendChild(odiv);
			}
			document.body.appendChild(this.rootDiv);
			/* IE6 */
			this.changeIframe();

			this.tabChange();
			this.linkEvent();
		} else {
			//所有国内城市数据,可以按照格式自行添加（AreaId|CnName|PyName），前homeHotSize条为热门城市 
			var homeHotSize = $("#homeHotSize").val();
			var homeStr = $("#homeCityArray").html();
			var homeArray = eval('(' + homeStr + ')');
			Vcity.allCity = homeArray;
			//	Vcity.allCity = ['北京|beijingshi|bjsh|75021', '上海|shanghaishi|shhsh|75093', '天津|tianjinshi|tjsh|75022', '重庆|chongqingshi|chqsh|75274', '大连|dalianshi|dlsh|75058', '青岛|qingdaoshi|qdsh|75155', '西安|xianshi|xash|75328', '南京|nanjingshi|njsh|75094', '苏州|suzhoushi|szhsh|75098', '杭州|hangzhoushi|hzhsh|75107', '桂林|guilinshi|glsh|75243', '大理|dali|dl|75317', '丽江|lijiangshi|ljsh|75310', '成都|chengdushi|chdsh|75275', '厦门|xiamenshi|xmsh|75135', '广州|guangzhoushi|gzhsh|75220', '三亚|sanyashi|sysh|75256', '香港|xianggang|xg|75403', '澳门|aomen|am|75405', '乌鲁木齐|wulumuqishi|wlmqsh|75365', '长春|changchunshi|chchsh|75071', '沈阳|shenyangshi|shysh|75057', '长治|changzhishi|chzhsh|75037', '长沙|changshashi|chshsh|75206', '石家庄|shijiazhuang|sjz|75023', '唐山|tangshanshi|tshsh|75024', '秦皇岛|qinhuangdaoshi|qhdsh|75025', '邯郸|handanshi|hdsh|75026', '邢台|xingtaishi|xtsh|75027', '保定|baodingshi|bdsh|75028', '张家口|zhangjiakoushi|zhjksh|75029', '承德|chengdeshi|chdsh|75030', '沧州|cangzhoushi|czhsh|75031', '廊坊|langfangshi|lfsh|75032', '衡水|hengshuishi|hshsh|75033', '太原|taiyuanshi|tysh|75034', '大同|datongshi|dtsh|75035', '阳泉|yangquanshi|yqsh|75036', '晋城|jinchengshi|jchsh|75038', '朔州|shuozhoushi|shzhsh|75039', '晋中|jinzhongshi|jzhsh|75040', '运城|yunchengshi|ychsh|75041', '忻州|xinzhoushi|xzhsh|75042', '临汾|linfenshi|lfsh|75043', '吕梁|lvliangshi|llsh|75044', '呼和浩特|huhehaoteshi|hhhtsh|75045', '包头|baotoushi|btsh|75046', '乌海|wuhaishi|whsh|75047', '赤峰|chifengshi|chfsh|75048', '通辽|tongliaoshi|tlsh|75049', '鄂尔多斯|eerduosishi|eedssh|75050', '呼伦贝尔|hulunbeiershi|hlbesh|75051', '巴彦淖尔|bayannaoershi|bynesh|75052', '乌兰察布|wulanchabushi|wlchbsh|75053', '兴安盟|xinganmeng|xam|75054', '锡林郭勒盟|xilinguolemeng|xlglm|75055', '阿拉善盟|alashanmeng|alshm|75056', '鞍山|anshanshi|ashsh|75059', '抚顺|fushunshi|fshsh|75060', '本溪|benxishi|bxsh|75061', '丹东|dandongshi|ddsh|75062', '锦州|jinzhoushi|jzhsh|75063', '营口|yingkoushi|yksh|75064', '阜新|fuxinshi|fxsh|75065', '辽阳|liaoyangshi|lysh|75066', '盘锦|panjinshi|pjsh|75067', '铁岭|tielingshi|tlsh|75068', '朝阳|chaoyangshi|cysh|75069', '葫芦岛|huludaoshi|hldsh|75070', '吉林|jilinshi|jlsh|75072', '四平|sipingshi|spsh|75073', '辽源|liaoyuanshi|lysh|75074', '通化|tonghuashi|thsh|75075', '白山|baishanshi|bshsh|75076', '松原|songyuanshi|sysh|75077', '白城|baichengshi|bchsh|75078', '延边朝鲜族自治州|yanbianchaoxinzizhizhou|ybchxzzhzh|75079', '哈尔滨|haerbinshi|hebsh|75080', '齐齐哈尔|qiqihaershi|qqhesh|75081', '鸡西|jixishi|jxsh|75082', '鹤岗|hegangshi|hgsh|75083', '双鸭山|shuangyashanshi|shyshsh|75084', '大庆|daqingshi|dqsh|75085', '伊春|yichunshi|ychsh|75086', '佳木斯|jiamusishi|jmssh|75087', '七台河|qitaiheshi|qthsh|75088', '牡丹江|mudanjiangshi|mdjsh|75089', '黑河|heiheshi|hhsh|75090', '绥化|suihuashi|shsh|75091', '大兴安岭地区|daxinganlingdiqu|dxaldq|75092', '无锡|wuxishi|wxsh|75095', '徐州|xuzhoushi|xzhsh|75096', '常州|changzhoushi|chzhsh|75097', '南通|nantongshi|ntsh|75099', '连云港|lianyungangshi|lygsh|75100', '淮安|huaianshi|hash|75101', '盐城|yanchengshi|ychsh|75102', '扬州|yangzhoushi|yzhsh|75103', '镇江|zhenjiangshi|zhjsh|75104', '泰州|taizhoushi|tzhsh|75105', '宿迁|suqianshi|sqsh|75106', '宁波|ningboshi|nbsh|75108', '温州|wenzhoushi|wzhsh|75109', '嘉兴|jiaxingshi|jxsh|75110', '湖州|huzhoushi|hzhsh|75111', '绍兴|shaoxingshi|shxsh|75112', '金华|jinhuashi|jhsh|75113', '衢州|quzhoushi|qzhsh|75114', '舟山|zhoushanshi|zhshsh|75115', '台州|taizhoushi|tzhsh|75116', '丽水|lishuishi|lshsh|75117', '合肥|hefeishi|hfsh|75118', '芜湖|wuhushi|whsh|75119', '蚌埠|bengbushi|bbsh|75120', '淮南|huainanshi|hnsh|75121', '马鞍山|maanshanshi|mashsh|75122', '淮北|huaibeishi|hbsh|75123', '铜陵|tonglingshi|tlsh|75124', '安庆|anqingshi|aqsh|75125', '黄山|huangshanshi|hshsh|75126', '滁州|chuzhoushi|chzhsh|75127', '阜阳|fuyangshi|fysh|75128', '宿州|suzhoushi|szhsh|75129', '六安|liuanshi|lash|75130', '亳州|bozhoushi|bzhsh|75131', '池州|chizhoushi|chzhsh|75132', '宣城|xuanchengshi|xchsh|75133', '福州|fuzhoushi|fzhsh|75134', '莆田|putianshi|ptsh|75136', '三明|sanmingshi|smsh|75137', '泉州|quanzhoushi|qzhsh|75138', '漳州|zhangzhoushi|zhzhsh|75139', '南平|nanpingshi|npsh|75140', '龙岩|longyanshi|lysh|75141', '宁德|ningdeshi|ndsh|75142', '南昌|nanchangshi|nchsh|75143', '景德镇|jingdezhenshi|jdzhsh|75144', '萍乡|pingxiangshi|pxsh|75145', '九江|jiujiangshi|jjsh|75146', '新余|xinyushi|xysh|75147', '鹰潭|yingtanshi|ytsh|75148', '赣州|ganzhoushi|gzhsh|75149', '吉安|jianshi|jash|75150', '宜春|yichunshi|ychsh|75151', '抚州|fuzhoushi|fzhsh|75152', '上饶|shangraoshi|shrsh|75153', '济南|jinanshi|jnsh|75154', '淄博|ziboshi|zbsh|75156', '枣庄|zaozhuangshi|zzhsh|75157', '东营|dongyingshi|dysh|75158', '烟台|yantaishi|ytsh|75159', '潍坊|weifangshi|wfsh|75160', '济宁|jiningshi|jnsh|75161', '泰安|taianshi|tash|75162', '威海|weihaishi|whsh|75163', '日照|rizhaoshi|rzhsh|75164', '莱芜|laiwushi|lwsh|75165', '临沂|linyishi|lysh|75166', '德州|dezhoushi|dzhsh|75167', '聊城|liaochengshi|lchsh|75168', '滨州|binzhoushi|bzhsh|75169', '菏泽|hezeshi|hzsh|75170', '郑州|zhengzhoushi|zhzhsh|75171', '开封|kaifengshi|kfsh|75172', '洛阳|luoyangshi|lysh|75173', '平顶山|pingdingshanshi|pdshsh|75174', '安阳|anyangshi|aysh|75175', '鹤壁|hebishi|hbsh|75176', '新乡|xinxiangshi|xxsh|75177', '焦作|jiaozuoshi|jzsh|75178', '濮阳|puyangshi|pysh|75179', '许昌|xuchangshi|xchsh|75180', '漯河|luoheshi|lhsh|75181', '三门峡|sanmenxiashi|smxsh|75182', '南阳|nanyangshi|nysh|75183', '商丘|shangqiushi|shqsh|75184', '信阳|xinyangshi|xysh|75185', '周口|zhoukoushi|zhksh|75186', '驻马店|zhumadianshi|zhmdsh|75187', '济源|jiyuanshi|jysh|75188', '武汉|wuhanshi|whsh|75189', '黄石|huangshishi|hssh|75190', '十堰|shiyanshi|shysh|75191', '宜昌|yichangshi|ychsh|75192', '襄阳|xiangyangshi|xysh|75193', '鄂州|ezhoushi|ezhsh|75194', '荆门|jingmenshi|jmsh|75195', '孝感|xiaoganshi|xgsh|75196', '荆州|jingzhoushi|jzhsh|75197', '黄冈|huanggangshi|hgsh|75198', '咸宁|xianningshi|xnsh|75199', '随州|suizhoushi|szhsh|75200', '恩施土家族苗族自治州|enshitujiazumiaozuzizhizhou|eshtjzmzzzhzh|75201', '仙桃|xiantaoshi|xtsh|75202', '潜江|qianjiangshi|qjsh|75203', '天门|tianmenshi|tmsh|75204', '神农架林区|shennongjialinqu|shnjlq|75205', '株洲|zhuzhoushi|zhzhsh|75207', '湘潭|xiangtanshi|xtsh|75208', '衡阳|hengyangshi|hysh|75209', '邵阳|shaoyangshi|shysh|75210', '岳阳|yueyangshi|yysh|75211', '常德|changdeshi|chdsh|75212', '张家界|zhangjiajieshi|zhjjsh|75213', '益阳|yiyangshi|yysh|75214', '郴州|chenzhoushi|chzhsh|75215', '永州|yongzhoushi|yzhsh|75216', '怀化|huaihuashi|hhsh|75217', '娄底|loudishi|ldsh|75218', '湘西土家族苗族自治州|xiangxitujiazumiaozuzizhizhou|xxtjzmzzzhzh|75219', '韶关|shaoguanshi|shgsh|75221', '深圳|shenzhenshi|shzhsh|75222', '珠海|zhuhaishi|zhhsh|75223', '汕头|shantoushi|shtsh|75224', '佛山|foshanshi|fssh|75225', '江门|jiangmenshi|jmsh|75226', '湛江|zhanjiangshi|zhjsh|75227', '茂名|maomingshi|mmsh|75228', '肇庆|zhaoqingshi|zhqsh|75229', '惠州|huizhoushi|hzhsh|75230', '梅州|meizhoushi|mzhsh|75231', '汕尾|shanweishi|swsh|75232', '河源|heyuanshi|hysh|75233', '阳江|yangjiangshi|yjsh|75234', '清远|qingyuanshi|qysh|75235', '东莞|dongguanshi|dgsh|75236', '中山|zhongshanshi|zhshsh|75237', '潮州|chaozhoushi|chzhsh|75238', '揭阳|jieyangshi|jysh|75239', '云浮|yunfushi|yfsh|75240', '南宁|nanningshi|nnsh|75241', '柳州|liuzhoushi|lzhsh|75242', '梧州|wuzhoushi|wzhsh|75244', '北海|beihaishi|bhsh|75245', '防城港|fangchenggangshi|fchgsh|75246', '钦州|qinzhoushi|qzhsh|75247', '贵港|guigangshi|ggsh|75248', '玉林|yulinshi|ylsh|75249', '百色|baiseshi|bssh|75250', '贺州|hezhoushi|hzhsh|75251', '河池|hechishi|hchsh|75252', '来宾|laibinshi|lbsh|75253', '崇左|chongzuoshi|chzsh|75254', '海口|haikoushi|hksh|75255', '三沙|sanshashi|sshsh|75257', '五指山|wuzhishanshi|wzhshsh|75258', '琼海|qionghaishi|qhsh|75259', '儋州|danzhoushi|dzhsh|75260', '文昌|wenchangshi|wchsh|75261', '万宁|wanningshi|wnsh|75262', '东方|dongfangshi|dfsh|75263', '定安县|dinganxian|dax|75264', '屯昌县|tunchangxian|tcx|75265', '澄迈县|chengmaixian|cmx|75266', '临高县|lingaoxian|lgx|75267', '白沙黎族自治县|baishalizuzizhixian|bshlzzzhx|75268', '昌江黎族自治县|changjianglizuzizhixian|chjlzzzhx|75269', '乐东黎族自治县|ledonglizuzizhixian|ldlzzzx|75270', '陵水黎族自治县|lingshuilizuzizhixian|lshlzzzhx|75271', '保亭黎族苗族自治县|baotinglizumiaozuzizhixian|btlzmzzzhx|75272', '琼中黎族苗族自治县|qiongzhonglizumiaozuzizhixian|qzhlzmzzzhx|75273', '自贡|zigongshi|zgsh|75276', '攀枝花|panzhihuashi|pzhsh|75277', '泸州|luzhoushi|lzhsh|75278', '德阳|deyangshi|dysh|75279', '绵阳|mianyangshi|mysh|75280', '广元|guangyuanshi|gysh|75281', '遂宁|suiningshi|snsh|75282', '内江|neijiangshi|njsh|75283', '乐山|leshanshi|lshsh|75284', '南充|nanchongshi|nchsh|75285', '眉山|meishanshi|mshsh|75286', '宜宾|yibinshi|ybsh|75287', '广安|guanganshi|gash|75288', '达州|dazhoushi|dzhsh|75289', '雅安|yaanshi|yash|75290', '巴中|bazhongshi|bzhsh|75291', '资阳|ziyangshi|zysh|75292', '阿坝藏族羌族自治州|abazangzuqiangzuzizhizhou|abzzqzzzz|75293', '甘孜藏族自治州|ganzizangzuzizhizhou|gzzzzzz|75294', '凉山彝族自治州|liangshanyizuzizhizhou|lshyzzzhzh|75295', '贵阳|guiyangshi|gysh|75296', '六盘水|liupanshuishi|lpssh|75297', '遵义|zunyishi|zysh|75298', '安顺|anshunshi|ashsh|75299', '毕节|bijieshi|bjsh|75300', '铜仁|tongrenshi|trsh|75301', '黔西南布依族苗族自治州|qianxinanbuyizumiaozuzizhizhou|qxnbyzmzzzhzh|75302', '黔东南苗族侗族自治州|qiandongnanmiaozudongzuzizhizhou|qdnmzdzzzz|75303', '黔南布依族苗族自治州|qiannanbuyizumiaozuzizhizhou|qnbyzmzzzhzh|75304', '昆明|kunmingshi|kmsh|75305', '曲靖|qujingshi|qjsh|75306', '玉溪|yuxishi|yxsh|75307', '保山|baoshanshi|bshsh|75308', '昭通|zhaotongshi|zhtsh|75309', '普洱|puershi|pesh|75311', '临沧|lincangshi|lcsh|75312', '楚雄彝族自治州|chuxiongyizuzizhizhou|chxyzzzhzh|75313', '红河哈尼族彝族自治州|honghehanizuyizuzizhizhou|hhhnzyzzzz|75314', '文山壮族苗族自治州|wenshanzhuangzumiaozuzizhizhou|wshzhzmzzzhzh|75315', '西双版纳傣族自治州|xishuangbannadaizuzizhizhou|xshbndzzzhzh|75316', '德宏傣族景颇族自治州|dehongdaizujingpozuzizhizhou|dhdzjpzzzhzh|75318', '怒江傈僳族自治州|nujianglisuzuzizhizhou|njlszzzhzh|75319', '迪庆藏族自治州|diqingzangzuzizhizhou|dqzzzzz|75320', '拉萨|lasashi|lssh|75321', '昌都地区|changdudiqu|cddq|75322', '山南地区|shannandiqu|shndq|75323', '日喀则地区|rikazediqu|rkzdq|75324', '那曲地区|naqudiqu|nqdq|75325', '阿里地区|alidiqu|aldq|75326', '林芝地区|linzhidiqu|lzhdq|75327', '铜川|tongchuanshi|tchsh|75329', '宝鸡|baojishi|bjsh|75330', '咸阳|xianyangshi|xysh|75331', '渭南|weinanshi|wnsh|75332', '延安|yananshi|yash|75333', '汉中|hanzhongshi|hzhsh|75334', '榆林|yulinshi|ylsh|75335', '安康|ankangshi|aksh|75336', '商洛|shangluoshi|shlsh|75337', '兰州|lanzhoushi|lzhsh|75338', '嘉峪关|jiayuguanshi|jygsh|75339', '金昌|jinchangshi|jchsh|75340', '白银|baiyinshi|bysh|75341', '天水|tianshuishi|tshsh|75342', '武威|wuweishi|wwsh|75343', '张掖|zhangyeshi|zhysh|75344', '平凉|pingliangshi|plsh|75345', '酒泉|jiuquanshi|jqsh|75346', '庆阳|qingyangshi|qysh|75347', '定西|dingxishi|dxsh|75348', '陇南|longnanshi|lnsh|75349', '临夏回族自治州|linxiahuizuzizhizhou|lxhzzzhzh|75350', '甘南藏族自治州|gannanzangzuzizhizhou|gnzzzzz|75351', '西宁|xiningshi|xnsh|75352', '海东地区|haidongdiqu|hddq|75353', '海北藏族自治州|haibeizangzuzizhizhou|hbzzzzz|75354', '黄南藏族自治州|huangnanzangzuzizhizhou|hnzzzzz|75355', '海南藏族自治州|hainanzangzuzizhizhou|hnzzzzz|75356', '果洛藏族自治州|guoluozangzuzizhizhou|glzzzzz|75357', '玉树藏族自治州|yushuzangzuzizhizhou|yszzzzz|75358', '海西蒙古族藏族自治州|haiximengguzuzizhizhou|hxmgzzzz|75359', '银川|yinchuanshi|ychsh|75360', '石嘴山|shizuishanshi|szzsh|75361', '吴忠|wuzhongshi|wzhsh|75362', '固原|guyuanshi|gysh|75363', '中卫|zhongweishi|zhwsh|75364', '克拉玛依|kelamayishi|klmysh|75366', '吐鲁番地区|tulufandiqu|tlfdq|75367', '哈密地区|hamidiqu|hmdq|75368', '昌吉回族自治州|changjihuizuzizhizhou|chjhzzzhzh|75369', '博尔塔拉蒙古自治州|boertalamengguzizhizhou|betlmgzzhzh|75370', '巴音郭楞蒙古自治州|bayinguolengmengguzizhizhou|byglmgzzhzh|75371', '阿克苏地区|akesudiqu|aksdq|75372', '克孜勒苏柯尔克孜自治州|kezilesuheerzizizhizhou|kzlskezzzz|75373', '喀什地区|kashidiqu|ksdq|75374', '和田地区|hetiandiqu|htdq|75375', '伊犁哈萨克自治州|yilihasakezizhizhou|ylhskzzhzh|75376', '塔城地区|tachengdiqu|tchdq|75377', '阿勒泰地区|aletaidiqu|altdq|75378', '自治区直辖|zizhiquzhixia|zzhqzhx|75379', '台北|taibeishi|tbsh|75380', '高雄|gaoxiongshi|gxsh|75381', '基隆|jilongshi|jlsh|75382', '台中|taizhongshi|tzhsh|75383', '台南|tainanshi|tnsh|75384', '新竹|xinzhushi|xzhsh|75385', '嘉义|jiayishi|jysh|75386', '台北县|taibeixian|tbx|75387', '宜兰县|yilanxian|ylx|75388', '新竹县|xinzhuxian|xzhx|75389', '桃园县|taoyuanxian|tyx|75390', '苗栗县|miaolixian|mlx|75391', '台中县|taizhongxian|tzhx|75392', '彰化县|zhanghuaxian|zhhx|75393', '南投县|nantouxian|ntx|75394', '嘉义县|jiayixian|jyx|75395', '云林县|yunlinxian|ylx|75396', '台南县|tainanxian|tnx|75397', '高雄县|gaoxiongxian|gxx|75398', '屏东县|pingdongxian|pdx|75399', '台东县|taidongxian|tdx|75400', '花莲县|hualianxian|hlx|75401', '澎湖县|penghuxian|phx|75402', '新界|xinjie|xj|75404', '台北|taibei|tb|57672', '高雄|gaoxiong|gx|57673', '新竹|xinzhu|xzh|57674', '台南|tainan|tn|57675', '台东|taidong|td|57676', '台中|taizhong|tzh|57677', '嘉义|jiayi|jy|57826', '屏东|pingdong|pd|57827', '花莲|hualian|hl|57828', '金门|jinmen|jm|57829', '云林|yunlin|yl|57830', '南投|nantou|nt|57831', '桃园|taoyuan|ty|57832', '宜兰|yilan|yl|57833', '澎湖|penghu|ph|57987', '马祖|mazu|mz|57988', '苗栗|miaoli|ml|57989', '基隆|jilong|jl|57990', '彰化|zhanghua|zhh|57991', '新北|xinbeishi|xbsh|58002', '香港|hongkong|hk|75403', '澳门|macao|mc|75405']
			// 正则表达式 筛选中文城市名、拼音、首字母 、id
			Vcity.regEx = /^([\u4E00-\u9FA5\uf900-\ufa2d]+)\|(\w+)\|(\w)\w*\|(\d{3,6})$/i;
			Vcity.regExChiese = /([\u4E00-\u9FA5\uf900-\ufa2d]+)/;
			/*
			 * 格式化城市数组为对象oCity，按照a-h,i-p,q-z,hot热门城市分组：
			 * {HOT:{hot:[]},ABCDEFGH:{a:[1,2,3],b:[1,2,3]},IJKLMNOP:{i:[1.2.3],j:[1,2,3]},QRSTUVWXYZ:{}}
			 */
			var citys = Vcity.allCity,
				match, letter,
				regEx = Vcity.regEx,
				reg2 = /^[a-d]$/i,
				reg3 = /^[e-h]$/i,
				reg4 = /^[j-m]$/i,
				reg5 = /^[n-s]$/i,
				reg6 = /^[t-x]$/i,
				reg7 = /^[y-z]$/i;
			Vcity.oCity = undefined;
			if(!Vcity.oCity) {
				Vcity.oCity = { hot: {}, ABCD: {}, EFGH: {}, JKLM: {}, NOPQRS: {}, TUVWX: {}, YZ: {} };
				for(var i = 0, n = citys.length; i < n; i++) {
					match = regEx.exec(citys[i]);
					if(i >= homeHotSize) {
						letter = match[3].toUpperCase();
						if(reg2.test(letter)) {
							if(!Vcity.oCity.ABCD[letter]) Vcity.oCity.ABCD[letter] = [];
							Vcity.oCity.ABCD[letter].push(match[1] + '|' + match[4]);
						} else if(reg3.test(letter)) {
							if(!Vcity.oCity.EFGH[letter]) Vcity.oCity.EFGH[letter] = [];
							Vcity.oCity.EFGH[letter].push(match[1] + '|' + match[4]);
						} else if(reg4.test(letter)) {
							if(!Vcity.oCity.JKLM[letter]) Vcity.oCity.JKLM[letter] = [];
							Vcity.oCity.JKLM[letter].push(match[1] + '|' + match[4]);
						} else if(reg5.test(letter)) {
							if(!Vcity.oCity.NOPQRS[letter]) Vcity.oCity.NOPQRS[letter] = [];
							Vcity.oCity.NOPQRS[letter].push(match[1] + '|' + match[4]);
						} else if(reg6.test(letter)) {
							if(!Vcity.oCity.TUVWX[letter]) Vcity.oCity.TUVWX[letter] = [];
							Vcity.oCity.TUVWX[letter].push(match[1] + '|' + match[4]);
						} else if(reg7.test(letter)) {
							if(!Vcity.oCity.YZ[letter]) Vcity.oCity.YZ[letter] = [];
							Vcity.oCity.YZ[letter].push(match[1] + '|' + match[4]);
						}
					}
					//热门城市 前homeHotSize条 
					if(i < homeHotSize) {
						if(!Vcity.oCity.hot['hot']) Vcity.oCity.hot['hot'] = [];
						Vcity.oCity.hot['hot'].push(match[1] + '|' + match[4]);
					}
				}
			}

			var odiv, odl, odt, odd, odda = [],
				str, key, ckey, sortKey, regEx = Vcity.regEx,
				oCity = Vcity.oCity;
			for(key in oCity) {
				odiv = this[key] = document.createElement('div');
				// 先设置全部隐藏hide
				odiv.className = key + ' ' + 'cityTab hide';
				sortKey = [];
				for(ckey in oCity[key]) {
					sortKey.push(ckey);
					// ckey按照ABCDEDG顺序排序
					sortKey.sort();
				}
				for(var j = 0, k = sortKey.length; j < k; j++) {
					odl = document.createElement('dl');
					odt = document.createElement('dt');
					odd = document.createElement('dd');
					odt.innerHTML = sortKey[j] == 'hot' ? '' : sortKey[j];
					odda = [];
					for(var i = 0, n = oCity[key][sortKey[j]].length; i < n; i++) {
						var st = oCity[key][sortKey[j]][i].split("|");
						str = '<a id="' + st[1] + '">' + st[0] + '</a>';
						odda.push(str);
					}
					odd.innerHTML = odda.join('');
					if($('input:radio[name=homeAbroadFlag]:checked').val() == -99) {

					} else {
						odl.appendChild(odt);
					}
					odl.appendChild(odd);
					odiv.appendChild(odl);
				}

				// 移除热门城市的隐藏CSS
				Vcity._m.removeClass('hide', this.hot);
				this.hotCity.appendChild(odiv);
			}
			document.body.appendChild(this.rootDiv);
			/* IE6 */
			this.changeIframe();

			this.tabChange();
			this.linkEvent();
		}
	},

	/* *
	 *  tab按字母顺序切换
	 *  @ tabChange
	 * */

	tabChange: function() {
		var lis = Vcity._m.$('li', this.cityBox);
		var divs = Vcity._m.$('div', this.hotCity);
		var that = this;
		for(var i = 0, n = lis.length; i < n; i++) {
			lis[i].index = i;
			lis[i].onclick = function() {
				for(var j = 0; j < n; j++) {
					Vcity._m.removeClass('on', lis[j]);
					Vcity._m.addClass('hide', divs[j]);
				}
				Vcity._m.addClass('on', this);
				Vcity._m.removeClass('hide', divs[this.index]);
				/* IE6 改变TAB的时候 改变Iframe 大小*/
				that.changeIframe();
			};
		}
	},

	/* *
	 * 城市LINK事件
	 *  @linkEvent
	 * */

	linkEvent: function() {
		var links = Vcity._m.$('a', this.hotCity);
		var that = this;
		for(var i = 0, n = links.length; i < n; i++) {
			links[i].onclick = function() {
				that.input.value = this.innerHTML;
				var oCid = $(this).attr("id");
				$("#cityid").val(oCid);
				Vcity._m.addClass('hide', that.cityBox);
				/* 点击城市名的时候隐藏myIframe */
				Vcity._m.addClass('hide', that.myIframe);
			}
		}

	},

	/* *
	 * INPUT城市输入框事件
	 * @inputEvent
	 * */

	inputEvent: function() {
		var that = this;
		Vcity._m.on(this.input, 'click', function(event) {
			event = event || window.event;
			if(!that.cityBox) {
				that.createWarp();
			} else if(!!that.cityBox && Vcity._m.hasClass('hide', that.cityBox)) {
				// slideul 不存在或者 slideul存在但是是隐藏的时候 两者不能共存
				if(!that.ul || (that.ul && Vcity._m.hasClass('hide', that.ul))) {
					Vcity._m.removeClass('hide', that.cityBox);

					/* IE6 移除iframe 的hide 样式 */
					//alert('click');
					Vcity._m.removeClass('hide', that.myIframe);
					that.changeIframe();
				}
			}
		});

		Vcity._m.on(this.input, 'blur', function() {
			var cityName = Vcity._m.trim(that.input.value);
			var cityId = $("#cityid").val();
			if(cityId == "") {
				cityName = $("#defaultCityName").val();
				cityId = $("#defaultCityId").val();
			}

			var value = Vcity._m.trim(that.input.value);
			if(value != '') {
				var lis = Vcity._m.$('li', that.ul);
				if(typeof lis == 'object' && lis['length'] > 0) {
					var cls = lis[0].className;
					if("empty" == cls || "em" == cls) {
						Vcity._m.addClass('hide', that.ul);
					}
				} else {
					that.input.value = '';
				}
			}
			if(cityId != "" && cityName != "") {
				that.input.value = cityName;
				$("#cityid").val(cityId);
			} else {
				that.input.value = '';
			}

		});
		Vcity._m.on(this.input, 'keyup', function(event) {
			event = event || window.event;
			var keycode = event.keyCode;
			Vcity._m.addClass('hide', that.cityBox);
			that.createUl();

			/* 移除iframe 的hide 样式 */
			Vcity._m.removeClass('hide', that.myIframe);

			// 下拉菜单显示的时候捕捉按键事件
			if(that.ul && !Vcity._m.hasClass('hide', that.ul) && !that.isEmpty) {
				that.KeyboardEvent(event, keycode);
			}
		});
	},

	/* *
	 * 生成下拉选择列表
	 * @ createUl
	 * */

	createUl: function() {
		var str;
		var value = Vcity._m.trim(this.input.value);
		$("#cityid").val('');
		$("#defaultCityName").val('');
		$("#defaultCityId").val('');
		// 当value不等于空的时候执行
		if(value !== '') {
			var searchResult = [];
			thisKey = encodeURIComponent(encodeURIComponent(value)); //搜索词
			for(var j = 0 ;j < 8; j++) {
				str = '<li><b id="75268" class="cityname">白沙黎族自治县,中国</b></li>';
				searchResult.push(str);
			}

			/*	var url = contextPath+"/hotelSearch/getAssociateInfo2.do?poiType=1&content=" + thisKey;
        	//国际国内
        	 var homeAbroadFlag = $('input:radio[name=homeAbroadFlag]:checked').val();
        	 url += '&pid=' + homeAbroadFlag;
             $.ajax({
             	async: false,
             	url:url,
             	success:function(resp){
             		for (var i = 0, n = resp.length; i < n; i++) {
 	                    if (searchResult.length !== 0) {
 	                    	str = '<li><b id="' + resp[i].value + '" class="cityname">' + resp[i].label + '</b></li>';
 	                    } else {
 	                        str = '<li class="on"><b id="' + resp[i].value + '" class="cityname">' + resp[i].label + '</b></li>';
 	                    }
 	                    searchResult.push(str);
             		}
             		if(resp.length != 0){
             			$("#defaultCityName").val(resp[0].label);
             			$("#defaultCityId").val(resp[0].value);
             		}
             	}
             });*/

			this.isEmpty = false;
			// 如果搜索数据为空
			if(searchResult.length == 0) {
				this.isEmpty = true;
				str = '<li class="empty">对不起，没有找到 "<em>' + value + '</em>"</li>';
				searchResult.push(str);
			}
			// 如果slideul不存在则添加ul
			if(!this.ul) {
				var ul = this.ul = document.createElement('ul');
				ul.className = 'cityslide mCustomScrollbar';
				this.rootDiv && this.rootDiv.appendChild(ul);
				// 记录按键次数，方向键
				this.count = 0;
			} else if(this.ul && Vcity._m.hasClass('hide', this.ul)) {
				this.count = 0;
				Vcity._m.removeClass('hide', this.ul);
			}
			this.ul.innerHTML = searchResult.join('');

			/* IE6 */
			this.changeIframe();

			// 绑定Li事件
			this.liEvent();
		} else {
			Vcity._m.addClass('hide', this.ul);
			Vcity._m.removeClass('hide', this.cityBox);

			Vcity._m.removeClass('hide', this.myIframe);

			this.changeIframe();
		}
	},

	/*  IE6的改变遮罩SELECT 的 IFRAME尺寸大小 -----*/
	changeIframe: function() {
		if(!this.isIE6) return;
		this.myIframe.style.width = this.rootDiv.offsetWidth + 'px';
		this.myIframe.style.height = this.rootDiv.offsetHeight + 'px';
	},

	/* *
	 * 特定键盘事件，上、下、Enter键
	 * @ KeyboardEvent
	 * */

	KeyboardEvent: function(event, keycode) {
		var lis = Vcity._m.$('li', this.ul);
		var len = lis.length;
		switch(keycode) {
			case 40: //向下箭头↓
				this.count++;
				if(this.count > len - 1) this.count = 0;
				for(var i = 0; i < len; i++) {
					Vcity._m.removeClass('on', lis[i]);
				}
				Vcity._m.addClass('on', lis[this.count]);

				var city = lis[this.count].childNodes;
				var cityName = city[0].innerHTML;
				var cityId = city[0].id;
				$("#defaultCityName").val(cityName);
				$("#defaultCityId").val(cityId);
				break;
			case 38: //向上箭头↑
				this.count--;
				if(this.count < 0) this.count = len - 1;
				for(i = 0; i < len; i++) {
					Vcity._m.removeClass('on', lis[i]);
				}
				Vcity._m.addClass('on', lis[this.count]);

				var city = lis[this.count].childNodes;
				var cityName = city[0].innerHTML;
				var cityId = city[0].id;
				$("#defaultCityName").val(cityName);
				$("#defaultCityId").val(cityId);
				break;
			case 13: // enter键
				this.input.value = Vcity.regExChiese.exec(lis[this.count].innerHTML)[0];
				var test11 = lis[this.count].innerHTML;
				var test = Vcity.regExChiese.exec(lis[this.count].innerHTML);

				var city = lis[this.count].childNodes;
				var cityName = city[0].innerHTML;
				var cityId = city[0].id;
				this.input.value = cityName;
				$("#cityid").val(cityId);

				Vcity._m.addClass('hide', this.ul);
				Vcity._m.addClass('hide', this.ul);
				/* IE6 */
				Vcity._m.addClass('hide', this.myIframe);
				break;
			default:
				break;
		}
	},

	/* *
	 * 下拉列表的li事件
	 * @ liEvent
	 * */

	liEvent: function() {
		var that = this;
		var lis = Vcity._m.$('li', this.ul);
		for(var i = 0, n = lis.length; i < n; i++) {
			Vcity._m.on(lis[i], 'click', function(event) {
				event = Vcity._m.getEvent(event);
				var target = Vcity._m.getTarget(event);

				var cls = $(target).attr("class");
				if(cls == "cityname on") {
					var oCid = $(target).attr("id");
					that.input.value = $(target).html();
					$("#cityid").val(oCid);
				} else if("empty on" == cls || "em on" == cls) {

				} else {
					var oCid = $(this).find(".cityname").attr("id");
					that.input.value = $(target).find(".cityname").html();
					$("#cityid").val(oCid);
				}

				Vcity._m.addClass('hide', that.ul);
				/* IE6 下拉菜单点击事件 */
				Vcity._m.addClass('hide', that.myIframe);
			});

			Vcity._m.on(lis[i], 'mouseover', function(event) {
				event = Vcity._m.getEvent(event);
				var target = Vcity._m.getTarget(event);
				Vcity._m.addClass('on', target);
			});
			Vcity._m.on(lis[i], 'mouseout', function(event) {
				event = Vcity._m.getEvent(event);
				var target = Vcity._m.getTarget(event);
				Vcity._m.removeClass('on', target);
			})
		}
	}
};