<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/masterpage" prefix="fms" %>
<%@include file="../public/tag.jsp" %>
<fms:ContentPage materPageId="master">

<fms:Content contentPlaceHolderId="title">
	单程中转订单详情
</fms:Content>

<%-- <fms:Content contentPlaceHolderId="nav">
	<!-- <h5>子页导航</h5>  -->
</fms:Content>
 --%>

<fms:Content contentPlaceHolderId="main">
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="css/intlAllOrder.css">
		<link rel="stylesheet" type="text/css" href="css/date/mobiscroll.css" />
		<link rel="stylesheet" type="text/css" href="css/date/mobiscroll_date.css" />
		<link rel="stylesheet" type="text/css" href="css/MyConfirm.css?_=<%=Math.random()%>">
	</head>

	<body >
		<div class="bounced typeAdd">
			<div class="payment-con" id="test">
				<div class="payment-title">
					<p>类型</p>
				</div>
				<div class="payment-choose">
					<p>
						<span>不限</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>经济舱</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>头等舱</span>
						<img src="img/choose.png" / class="selected">
					</p>
					<p>
						<span>公务舱</span>
						<img src="img/choose.png" / class="selected">
					</p>
				</div>
			</div>
		</div>
		<!--header头部-->
		<div class="wrap">
			<div class="bgTable">
				<img src="img/airbg.png" />
				<div class="opacity">
					<div class="typeC">
						<div class="oneWay formula forActive">单程</div>
						<div class="formula turnWay">往返</div>
					</div>
				</div>
			</div>
			<div>
			
				<!------单程------->
				<div class="oneWayList listway showLogin">
					<div class="chooseCity">
						<div class="chooseGO goCity">
							<p>出发城市</p>
							<div class="hCity" id="orgCity1">北京</div>
						</div>
						<div class="wayIcon">
							<img src="img/dc.png" / class="dc">
						</div>
						<div class="chooseGO adressCity">
							<p>到达城市</p>
							<div class="hCity" id="detCity1">香港</div>
						</div>
					</div>
					<div class="cityTime">
						<p>去程</p>
						<div class="dateTime">
							<div class="hCity oneTime"><input type="text" id="date" /></div>
							<span class="weekS">周四</span>
						</div>
					</div>
					<div class="ticketGight">
						<div class="ticketChoose classEco">
							<p>机票舱位</p>
							<div class="hCity" id="Ticketcangwei1">不限</div>
						</div>
						<div class="ticketChoose">
							<p>出行方式</p>
							<div class="hCity becaues" id="traveltype1" >因公出行</div>
						</div>
					</div>
					<div>
						<input type="text" placeholder="请添加乘机人" class="oneTravel" style="width: inherit" onclick="getPeople(this,'employ','one')">
					</div>
					<div class="oneSearch">
						<div>搜索</div>
					</div>
				</div>


				<!------往返------------>
				<div class="turnWayList listway">
					<div class="chooseCity">
						<div class="chooseGO goCityT">
							<p>出发城市</p>
							<div class="hCity" id="orgCity2">北京</div>
						</div>
						<div class="wayIcon">
							<img src="img/lc.png" / class="wf">
						</div>
						<div class="chooseGO adressCity">
							<p>到达城市</p>
							<div class="hCity" id="detCity2">香港</div>
						</div>
					</div>
					<div class="chooseCity">
						<div class="chooseGO cityTwo">
							<p>去程</p>
							<div class="dateTime">
								<div class="hCity"><input type="text" id="dateO" /></div>
								<span class="weekT">周五</span>
							</div>
						</div>
						<div class="wayIcon">
							<img src="img/data.png" />
						</div>
						<div class="chooseGO adressCity">
							<p>返程</p>
							<div class="dateTime" style="justify-content: flex-end;">
								<div class="hCity"><input type="text" id="dateT" /></div>
								<span class="weekW">周五</span>
							</div>
						</div>
					</div>
					<div class="ticketGight">
						<div class="ticketChoose classEco">
							<p>机票舱位</p>
							<div class="hCity" id="Ticketcangwei2">不限</div>
						</div>
						<div class="ticketChoose">
							<p>出行方式</p>
							<div class="hCity becaues" id="traveltype2">因公出行</div>
						</div>
					</div>
					<div>
						<input type="text" placeholder="请添加乘机人" class="twoTravel" style="width: inherit" onclick="getPeople(this,'employ','two')">
					</div>
					<div class="oneSearch">
						<div>搜索</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 常旅客修改or新增页面 -->
		<div class="updateOrAddPassenger" style="display:none">
			<iframe class="Iframe" name="updateIframe" id="IframeID" src="" style="display:block;width:100%;height:100%;"></iframe>
		</div>
		<div class="pages" style="display: none;">
			<header class="addH">
				<div class="turnIcon">
					<img  src="<%=basePath%>/img/header.png" />
				</div>
				<div class="title"></div>
				<div class="login">
					<img src="<%=basePath%>/img/addBtn.png">
				</div>
			</header>
			<section>
				<div class="Tab">
					<div class="tabTitle">
						<div class="flyer highlight">企业员工</div>
						<div class="flyer">常旅客</div>
						<div  style="" class="sure">确定</div>
					</div>

					<div class="tabContent" >

						<ul class="show">

						</ul>
						<ul class="pass">

						</ul>
					</div>
				</div>
			</section>
		</div>

		<!-- 新增常旅客常旅客,常旅客修改方法 -->
		<form action="" id="subForm">
			<input name="intlType" id="intlType"  value="" type="hidden"/>
			<input name="passengerId" id="passengerId"  value="" type="hidden"/>
		</form>

		<!-------------地点插件---------------->
		<div class="bounced" style="width:100%;display: none; position: absolute; left: 0; top: 0;">

			<div class="city-container" style="display:block;">			
				<div class="city-title" style="margin-top:0;">热门城市</div>
				<!--显示点击的是哪一个字母-->
				<div id="showLetter" class="showLetter"><span>A</span></div>
				<!--城市索引查询-->
				<div class="letter">
					<ul>
						<li>
							<a href="javascript:;">A</a>
						</li>
						<li>
							<a href="javascript:;">B</a>
						</li>
						<li>
							<a href="javascript:;">C</a>
						</li>
						<li>
							<a href="javascript:;">D</a>
						</li>
						<li>
							<a href="javascript:;">E</a>
						</li>
						<li>
							<a href="javascript:;">F</a>
						</li>
						<li>
							<a href="javascript:;">G</a>
						</li>
						<li>
							<a href="javascript:;">H</a>
						</li>
						<li>
							<a href="javascript:;">J</a>
						</li>
						<li>
							<a href="javascript:;">K</a>
						</li>
						<li>
							<a href="javascript:;">L</a>
						</li>
						<li>
							<a href="javascript:;">M</a>
						</li>
						<li>
							<a href="javascript:;">N</a>
						</li>
						<li>
							<a href="javascript:;">P</a>
						</li>
						<li>
							<a href="javascript:;">Q</a>
						</li>
						<li>
							<a href="javascript:;">R</a>
						</li>
						<li>
							<a href="javascript:;">S</a>
						</li>
						<li>
							<a href="javascript:;">T</a>
						</li>
						<li>
							<a href="javascript:;">W</a>
						</li>
						<li>
							<a href="javascript:;">X</a>
						</li>
						<li>
							<a href="javascript:;">Y</a>
						</li>
						<li>
							<a href="javascript:;">Z</a>
						</li>
					</ul>
				</div>
				<div class="rrrr">								
					<div class="guoji showContry">
						<ul class="city-list country " data-template="CityBlockList ">
							<li class="cityvalue " data-id="HKG ">香港</li>
							<li class="cityvalue " data-id="MFM ">澳门</li>
							<li class="cityvalue " data-id="TYO">东京</li>
							<li class="cityvalue " data-id="SIN">新加坡</li>
							<li class="cityvalue " data-id="TPE">台北</li>
							<li class="cityvalue " data-id="BKK">曼谷</li>
							<li class="cityvalue " data-id="OSA">大阪</li>
							<li class="cityvalue " data-id="MNL">马尼拉</li>
							<li class="cityvalue " data-id="NGO">名古屋</li>
							<li class="cityvalue " data-id="LON">伦敦</li>
							<li class="cityvalue " data-id="KUL">吉隆坡</li>
							<li class="cityvalue " data-id="PUS">釜山</li>
							<li class="cityvalue " data-id="SYD">悉尼</li>
							<li class="cityvalue " data-id="FRA">法兰克福</li>
							<li class="cityvalue " data-id="YVR">温哥华</li>
							<li class="cityvalue " data-id="PAR">巴黎</li>
							<li class="cityvalue " data-id="NYC">纽约</li>
							<li class="cityvalue " data-id="LAX">洛杉矶</li>

						</ul>
						<!--城市列表-->
						<div class="container">
							<div class="city">
								<div class="city-list"><span class="city-letter" id="A1">A</span>
									<p data-id="320100" class="cityvalue">阿巴坎</p>
									<p data-id="330200" class="cityvalue">阿比林</p>
									<p data-id="ABJ" class="cityvalue">阿比让</p>
									<p data-id="360100" class="cityvalue">阿伯茨福德</p>
									<p data-id="ABZ" class="cityvalue">阿伯丁(英国)</p>
									<p data-id="ABR" class="cityvalue">阿伯丁(美国)</p>
									<p data-id="ABV" class="cityvalue">阿布贾</p>
									<p data-id="AUH" class="cityvalue">阿布扎比</p>
									<p data-id="ADA" class="cityvalue">阿达那</p>
									<p data-id="ADL" class="cityvalue">阿德莱德</p>
									<p data-id="320600" class="cityvalue">阿德列尔</p>
									<p data-id="360100" class="cityvalue">阿德亚曼</p>
									<p data-id="320100" class="cityvalue">阿尔巴哈</p>
									<p data-id="ABQ" class="cityvalue">阿尔伯克基</p>
									<p data-id="320100" class="cityvalue">阿尔盖罗</p>
									<p data-id="320100" class="cityvalue">阿尔荷塞马</p>
									<p data-id="ALG" class="cityvalue">阿尔及尔</p>
									<p data-id="320600" class="cityvalue">阿尔梅里亚</p>
									<p data-id="360100" class="cityvalue">阿尔梅尼亚</p>
									<p data-id="320100" class="cityvalue">阿尔塔</p>
									<p data-id="330200" class="cityvalue">阿尔托纳</p>
									<p data-id="320100" class="cityvalue">艾尔乌拉</p>
									<p data-id="320100" class="cityvalue">阿尔滕莱茵</p>
									<p data-id="330200" class="cityvalue">阿格拉</p>
									<p data-id="320600" class="cityvalue">阿瓜迪亚</p>
									<p data-id="360100" class="cityvalue">阿瓜斯卡连特斯</p>
									<p data-id="320100" class="cityvalue">埃阿坎</p>
									<p data-id="330200" class="cityvalue">艾卜哈</p>
									<p data-id="320100" class="cityvalue">爱达荷福尔斯</p>
									<p data-id="YEG" class="cityvalue">埃德蒙顿</p>
									<p data-id="EDO" class="cityvalue">埃德米特</p>
									<p data-id="EDI" class="cityvalue">爱丁堡</p>
									<p data-id="360100" class="cityvalue">埃尔比勒</p>
									<p data-id="320100" class="cityvalue">埃尔多拉多</p>
									<p data-id="330200" class="cityvalue">爱尔福特</p>
									<p data-id="320100" class="cityvalue">埃尔津詹</p>
									<p data-id="320100" class="cityvalue">埃尔卡拉法特</p>
									<p data-id="330200" class="cityvalue">埃尔迈拉</p>
									<p data-id="320600" class="cityvalue">埃尔帕索</p>
									<p data-id="360100" class="cityvalue">艾尔斯岩</p>
									<p data-id="AMD" class="cityvalue">艾哈迈达巴德</p>
									<p data-id="330200" class="cityvalue">艾克赛特</p>
									<p data-id="320100" class="cityvalue">艾莱</p>
									<p data-id="320100" class="cityvalue">埃拉齐格</p>
									<p data-id="330200" class="cityvalue">埃拉特</p>
									<p data-id="320600" class="cityvalue">爱丽斯斯普林斯</p>
									<p data-id="EVN" class="cityvalue">埃里温</p>
									<p data-id="320100" class="cityvalue">埃默拉尔德</p>
									<p data-id="330200" class="cityvalue">埃莫西约</p>
									<p data-id="320100" class="cityvalue">埃努古</p>
									<p data-id="320100" class="cityvalue">艾沙尔顿</p>
									<p data-id="330200" class="cityvalue">艾斯利普</p>
									<p data-id="320600" class="cityvalue">埃斯佩兰斯</p>
									<p data-id="360100" class="cityvalue">埃文斯维尔</p>
									<p data-id="320100" class="cityvalue">埃沃内斯</p>
									<p data-id="330200" class="cityvalue">艾因</p>
									<p data-id="320100" class="cityvalue">爱因霍温</p>
									<p data-id="320100" class="cityvalue">阿加迪尔</p>
									<p data-id="330200" class="cityvalue">阿加尔塔拉</p>
									<p data-id="320600" class="cityvalue">阿卡普尔科</p>
									<p data-id="360100" class="cityvalue">阿克纠宾斯克</p>
									<p data-id="320100" class="cityvalue">阿克拉</p>
									<p data-id="320100" class="cityvalue">阿克伦/坎通</p>
									<p data-id="330200" class="cityvalue">阿克苏姆</p>
									<p data-id="320100" class="cityvalue">阿克塔</p>
									<p data-id="320100" class="cityvalue">阿克套</p>
									<p data-id="330200" class="cityvalue">阿拉</p>
									<p data-id="320600" class="cityvalue">阿莱恩斯</p>
									<p data-id="360100" class="cityvalue">阿莱姆港</p>
									<p data-id="320100" class="cityvalue">阿拉莫萨</p>
									<p data-id="330200" class="cityvalue">阿拉木图</p>
									<p data-id="320100" class="cityvalue">阿拉萨</p>
									<p data-id="320100" class="cityvalue">阿雷格里港</p>
									<p data-id="330200" class="cityvalue">阿雷基帕</p>
									<p data-id="320600" class="cityvalue">阿勒颇</p>
									<p data-id="360100" class="cityvalue">阿利坎特</p>
									<p data-id="320100" class="cityvalue">阿鲁巴</p>
									<p data-id="330200" class="cityvalue">阿伦敦</p>
									<p data-id="320100" class="cityvalue">阿马里洛</p>
									<p data-id="330200" class="cityvalue">阿米代尔</p>
									<p data-id="320600" class="cityvalue">阿姆利则</p>
									<p data-id="360100" class="cityvalue">阿姆斯特丹</p>
									<p data-id="320100" class="cityvalue">安阿伯</p>
									<p data-id="330200" class="cityvalue">阿纳帕</p>
									<p data-id="320100" class="cityvalue">安大略</p>
									<p data-id="320100" class="cityvalue">安德内斯</p>
									<p data-id="330200" class="cityvalue">阿哥拉城</p>
									<p data-id="320600" class="cityvalue">昂热</p>
									<p data-id="360100" class="cityvalue">阿尼亚克</p>
									<p data-id="320100" class="cityvalue">安卡拉</p>
									<p data-id="330200" class="cityvalue">安克雷奇</p>
									<p data-id="320100" class="cityvalue">安科纳</p>
									<p data-id="330200" class="cityvalue">安拉阿巴德</p>
									<p data-id="320600" class="cityvalue">安曼</p>
									<p data-id="360100" class="cityvalue">安纳巴</p>
									<p data-id="320100" class="cityvalue">安奈斯</p>
									<p data-id="330200" class="cityvalue">安塔利亚</p>
									<p data-id="320100" class="cityvalue">安特卫普</p>
									<p data-id="320100" class="cityvalue">安提瓜</p>
									<p data-id="330200" class="cityvalue">安托法加斯塔</p>
									<p data-id="320600" class="cityvalue">安汶</p>
									<p data-id="360100" class="cityvalue">奥尔巴尼(美国)</p>
									<p data-id="360100" class="cityvalue">奥尔巴尼</p>
									<p data-id="320100" class="cityvalue">奥尔堡</p>
									<p data-id="330200" class="cityvalue">奥尔比亚</p>
									<p data-id="320100" class="cityvalue">奥尔伯里</p>
									<p data-id="330200" class="cityvalue">奥尔金</p>
									<p data-id="320600" class="cityvalue">奥格登</p>
									<p data-id="360100" class="cityvalue">奥格登斯堡</p>
									<p data-id="320100" class="cityvalue">奥格斯堡</p>
									<p data-id="330200" class="cityvalue">奥古斯塔</p>
									<p data-id="320100" class="cityvalue">奥古斯塔</p>
									<p data-id="320100" class="cityvalue">奥赫里德</p>
									<p data-id="330200" class="cityvalue">奥胡斯</p>
									<p data-id="320600" class="cityvalue">奥克兰(新西兰)</p>
									<p data-id="360100" class="cityvalue">奥克兰(美国)</p>
									<p data-id="320100" class="cityvalue">奥兰</p>
									<p data-id="330200" class="cityvalue">奥兰多</p>
									<p data-id="320100" class="cityvalue">奥兰加巴德</p>
									<p data-id="330200" class="cityvalue">奥兰治</p>
									<p data-id="320600" class="cityvalue">奥勒松</p>
									<p data-id="360100" class="cityvalue">奥卢</p>
									<p data-id="320100" class="cityvalue">奥伦堡</p>
									<p data-id="330200" class="cityvalue">奥马哈</p>
									<p data-id="320100" class="cityvalue">奥三棉市</p>
									<p data-id="320100" class="cityvalue">奥什</p>
									<p data-id="330200" class="cityvalue">奥斯陆</p>
									<p data-id="320600" class="cityvalue">奥斯坦德</p>
									<p data-id="360100" class="cityvalue">奥斯汀</p>
									<p data-id="320100" class="cityvalue">奥瓦尔迪亚港</p>
									<p data-id="330200" class="cityvalue">阿平顿</p>
									<p data-id="320100" class="cityvalue">阿皮亚</p>
									<p data-id="320100" class="cityvalue">阿普尔顿</p>
									<p data-id="320100" class="cityvalue">阿让</p>
									<p data-id="320100" class="cityvalue">阿什贾巴特</p>
									<p data-id="320100" class="cityvalue">阿什维尔</p>
									<p data-id="320100" class="cityvalue">阿斯马拉</p>
									<p data-id="320100" class="cityvalue">阿斯彭</p>
									<p data-id="320100" class="cityvalue">阿斯塔纳</p>
									<p data-id="320100" class="cityvalue">阿斯图里亚斯</p>
									<p data-id="320100" class="cityvalue">阿斯旺</p>
									<p data-id="320100" class="cityvalue">安提劳</p>
									<p data-id="320100" class="cityvalue">阿西乌特</p>
									<p data-id="320100" class="cityvalue">阿雅克肖</p>
								</div>
								<div class="city-list"><span class="city-letter" id="B1">B</span>
									<p data-id="330200" class="cityvalue">巴淡岛</p>
									<p data-id="320600" class="cityvalue">巴东</p>
									<p data-id="360100" class="cityvalue">巴尔的摩</p>
									<p data-id="320100" class="cityvalue">巴尔杜福斯</p>
									<p data-id="330200" class="cityvalue">巴夫洛德</p>
									<p data-id="320600" class="cityvalue">巴港</p>
									<p data-id="360100" class="cityvalue">巴格达</p>
									<p data-id="320100" class="cityvalue">巴格多格拉</p>
									<p data-id="330200" class="cityvalue">巴格特维尔</p>
									<p data-id="320600" class="cityvalue">巴哈瓦尔布尔</p>
									<p data-id="360100" class="cityvalue">巴赫达尔</p>
									<p data-id="320100" class="cityvalue">拜达</p>
									<p data-id="330200" class="cityvalue">拜蒂克洛</p>
									<p data-id="320600" class="cityvalue">百慕大</p>
									<p data-id="360100" class="cityvalue">白沙瓦</p>
									<p data-id="411300" class="cityvalue">白特曼</p>
									<p data-id="320100" class="cityvalue">巴科洛德</p>
									<p data-id="320100" class="cityvalue">巴库</p>
									<p data-id="330200" class="cityvalue">巴拉</p>
									<p data-id="320600" class="cityvalue">巴兰基亚</p>
									<p data-id="360100" class="cityvalue">巴勒莫</p>
									<p data-id="411300" class="cityvalue">巴里</p>
									<p data-id="320100" class="cityvalue">巴黎</p>
									<p data-id="320100" class="cityvalue">巴利阿多里德</p>
									<p data-id="330200" class="cityvalue">巴里巴班</p>
									<p data-id="320600" class="cityvalue">巴厘岛</p>
									<p data-id="360100" class="cityvalue">巴林</p>
									<p data-id="411300" class="cityvalue">巴利那</p>
									<p data-id="320100" class="cityvalue">巴里萨尔</p>
									<p data-id="320100" class="cityvalue">巴马科</p>
									<p data-id="330200" class="cityvalue">巴拿马城</p>
									<p data-id="320600" class="cityvalue">班达亚齐</p>
									<p data-id="360100" class="cityvalue">邦达伯格</p>
									<p data-id="411300" class="cityvalue">班戈</p>
									<p data-id="320100" class="cityvalue">邦咯岛</p>
									<p data-id="330200" class="cityvalue">班吉</p>
									<p data-id="320600" class="cityvalue">班贾尔马辛</p>
									<p data-id="320100" class="cityvalue">班加罗尔</p>
									<p data-id="330200" class="cityvalue">班加西</p>
									<p data-id="320600" class="cityvalue">班梅苏奥特</p>
									<p data-id="360100" class="cityvalue">班珠尔</p>
									<p data-id="320100" class="cityvalue">包纳加尔</p>
									<p data-id="330200" class="cityvalue">巴塞尔米卢斯</p>
									<p data-id="320600" class="cityvalue">巴塞罗那</p>
									<p data-id="360100" class="cityvalue">巴色</p>
									<p data-id="411300" class="cityvalue">巴瑟尔顿</p>
									<p data-id="320100" class="cityvalue">巴瑟斯特</p>
									<p data-id="330200" class="cityvalue">巴示戈</p>
									<p data-id="320600" class="cityvalue">巴士拉</p>
									<p data-id="320100" class="cityvalue">巴斯蒂亚</p>
									<p data-id="330200" class="cityvalue">巴特那</p>
									<p data-id="320600" class="cityvalue">巴统</p>
									<p data-id="360100" class="cityvalue">巴吞鲁日</p>
									<p data-id="411300" class="cityvalue">巴务巴务</p>
									<p data-id="320100" class="cityvalue">巴西利亚</p>
									<p data-id="330200" class="cityvalue">巴亚尔塔港</p>
									<p data-id="320600" class="cityvalue">巴瑙尔</p>
									<p data-id="320100" class="cityvalue">北本德</p>
									<p data-id="330200" class="cityvalue">贝尔法斯特</p>
									<p data-id="320600" class="cityvalue">贝尔高姆</p>
									<p data-id="360100" class="cityvalue">贝尔格来德</p>
									<p data-id="411300" class="cityvalue">卑尔根</p>
									<p data-id="320100" class="cityvalue">贝尔热拉克</p>
									<p data-id="330200" class="cityvalue">贝尔维尔</p>
									<p data-id="320600" class="cityvalue">北干巴鲁</p>
									<p data-id="320100" class="cityvalue">贝贾亚</p>
									<p data-id="330200" class="cityvalue">北九州</p>
									<p data-id="320600" class="cityvalue">贝克莫</p>
									<p data-id="360100" class="cityvalue">贝克斯菲尔德</p>
									<p data-id="411300" class="cityvalue">贝拉</p>
									<p data-id="320100" class="cityvalue">贝劳</p>
									<p data-id="330200" class="cityvalue">贝灵厄姆</p>
									<p data-id="320600" class="cityvalue">贝伦</p>
									<p data-id="360100" class="cityvalue">贝洛奥里藏特</p>
									<p data-id="320100" class="cityvalue">贝鲁特</p>
									<p data-id="330200" class="cityvalue">北帕默斯顿</p>
									<p data-id="320600" class="cityvalue">北普拉特</p>
									<p data-id="360100" class="cityvalue">贝瑟尔</p>
									<p data-id="411300" class="cityvalue">本贝库拉</p>
									<p data-id="320100" class="cityvalue">本地治里</p>
									<p data-id="330200" class="cityvalue">本杰古尔</p>
									<p data-id="320600" class="cityvalue">奔萨</p>
									<p data-id="360100" class="cityvalue">毕阿克</p>
									<p data-id="320100" class="cityvalue">比得哥什</p>
									<p data-id="330200" class="cityvalue">彼得马里茨堡</p>
									<p data-id="320600" class="cityvalue">彼得斯堡</p>
									<p data-id="360100" class="cityvalue">别克斯</p>
									<p data-id="411300" class="cityvalue">毕尔巴鄂</p>
									<p data-id="320100" class="cityvalue">波尔姆</p>
									<p data-id="330200" class="cityvalue">比夫岛</p>
									<p data-id="320600" class="cityvalue">比拉德纳格尔</p>
									<p data-id="360100" class="cityvalue">比拉克</p>
									<p data-id="411300" class="cityvalue">比灵斯</p>
									<p data-id="320100" class="cityvalue">比隆</p>
									<p data-id="330200" class="cityvalue">比马/p>
										<p data-id="320600" class="cityvalue">比米尼群岛</p>
										<p data-id="320100" class="cityvalue">槟城</p>
										<p data-id="330200" class="cityvalue">宾厄姆顿</p>
										<p data-id="320600" class="cityvalue">比萨</p>
										<p data-id="360100" class="cityvalue">比沙</p>
										<p data-id="411300" class="cityvalue">比什凯可</p>
										<p data-id="320100" class="cityvalue">比亚埃尔莫萨</p>
										<p data-id="330200" class="cityvalue">比亚里茨</p>
										<p data-id="320600" class="cityvalue">博阿维斯塔</p>
										<p data-id="360100" class="cityvalue">伯班克</p>
										<p data-id="411300" class="cityvalue">波城</p>
										<p data-id="320100" class="cityvalue">波德戈里察</p>
										<p data-id="330200" class="cityvalue">博德鲁姆</p>
										<p data-id="320600" class="cityvalue">博多</p>
										<p data-id="320100" class="cityvalue">波恩</p>
										<p data-id="330200" class="cityvalue">波尔本德尔</p>
										<p data-id="320600" class="cityvalue">博尔德城</p>
										<p data-id="360100" class="cityvalue">波尔多</p>
										<p data-id="411300" class="cityvalue">伯尔尼</p>
										<p data-id="320100" class="cityvalue">波尔图</p>
										<p data-id="330200" class="cityvalue">波尔扎诺-波森</p>
										<p data-id="320600" class="cityvalue">波哥大</p>
										<p data-id="360100" class="cityvalue">波卡特洛</p>
										<p data-id="411300" class="cityvalue">博克拉</p>
										<p data-id="320100" class="cityvalue">波来古</p>
										<p data-id="330200" class="cityvalue">波里</p>
										<p data-id="320600" class="cityvalue">柏林</p>
										<p data-id="320100" class="cityvalue">伯灵顿</p>
										<p data-id="330200" class="cityvalue">伯利兹城</p>
										<p data-id="320600" class="cityvalue">波洛克瓦内</p>
										<p data-id="360100" class="cityvalue">波罗奈斯</p>
										<p data-id="411300" class="cityvalue">博洛尼亚</p>
										<p data-id="320100" class="cityvalue">波马拉</p>
										<p data-id="330200" class="cityvalue">博蒙特</p>
										<p data-id="320600" class="cityvalue">伯明翰(英国)</p>
										<p data-id="360100" class="cityvalue">伯明翰(美国)</p>
										<p data-id="411300" class="cityvalue">波纳斯</p>
										<p data-id="320100" class="cityvalue">伯尼</p>
										<p data-id="330200" class="cityvalue">博帕尔</p>
										<p data-id="320600" class="cityvalue">波蓬德塔</p>
										<p data-id="320100" class="cityvalue">波萨里卡-德伊达尔戈</p>
										<p data-id="330200" class="cityvalue">波士顿</p>
										<p data-id="320600" class="cityvalue">博斯特</p>
										<p data-id="360100" class="cityvalue">波索</p>
										<p data-id="411300" class="cityvalue">波特兰(俄勒冈州)</p>
										<p data-id="320100" class="cityvalue">波特兰(缅因州)</p>
										<p data-id="330200" class="cityvalue">泊特莫尔斯比港</p>
										<p data-id="320600" class="cityvalue">博伊西</p>
										<p data-id="360100" class="cityvalue">波由特</p>
										<p data-id="411300" class="cityvalue">博兹曼</p>
										<p data-id="320100" class="cityvalue">波兹南</p>
										<p data-id="330200" class="cityvalue">布巴内斯瓦尔</p>
										<p data-id="320600" class="cityvalue">布达佩斯</p>
										<p data-id="320100" class="cityvalue">布尔戈斯</p>
										<p data-id="330200" class="cityvalue">布尔萨</p>
										<p data-id="320600" class="cityvalue">布法罗</p>
										<p data-id="360100" class="cityvalue">布哈拉</p>
										<p data-id="411300" class="cityvalue">布加勒斯特</p>
										<p data-id="320100" class="cityvalue">布加斯</p>
										<p data-id="330200" class="cityvalue">布拉柴维尔</p>
										<p data-id="320600" class="cityvalue">布拉茨克</p>
										<p data-id="360100" class="cityvalue">布拉迪斯拉发</p>
										<p data-id="411300" class="cityvalue">布拉格</p>
										<p data-id="320100" class="cityvalue">布拉格维申斯科</p>
										<p data-id="330200" class="cityvalue">布莱尔港</p>
										<p data-id="320600" class="cityvalue">布莱克普尔</p>
										<p data-id="220800" class="cityvalue">不来梅</p>
										<p data-id="150200" class="cityvalue">布兰顿</p>
										<p data-id="150800" class="cityvalue">布朗肯山</p>
										<p data-id="130600" class="cityvalue">布朗斯维尔</p>
										<p data-id="210500" class="cityvalue">布兰太尔</p>
										<p data-id="220600" class="cityvalue">布劳恩斯魏克</p>
										<p data-id="341600" class="cityvalue">布雷斯特</p>
										<p data-id="340300" class="cityvalue">布连海姆</p>
										<p data-id="371600" class="cityvalue">布林迪西</p>
										<p data-id="620400" class="cityvalue">布里奇敦</p>
										<p data-id="610300" class="cityvalue">布里斯班</p>
										<p data-id="530500" class="cityvalue">布里斯托尔</p>
										<p data-id="469030" class="cityvalue">布里维拉加拉德</p>
										<p data-id="451000" class="cityvalue">布隆方丹</p>
										<p data-id="522401" class="cityvalue">布鲁明顿诺马尔</p>
										<p data-id="450500" class="cityvalue">布鲁姆</p>
										<p data-id="511900" class="cityvalue">布鲁塞尔</p>
										<p data-id="469035" class="cityvalue">布琼布拉</p>
										<p data-id="652800" class="cityvalue">布山加</p>
										<p data-id="652700" class="cityvalue">布宜诺斯艾利斯</p>
										<p data-id="110100" class="cityvalue">怡宝</p>
								</div>
								<div class="city-list"><span class="city-letter" id="C1">C</span>
									<p data-id="360100" class="cityvalue">查尔巴</p>
									<p data-id="411300" class="cityvalue">查尔斯顿</p>
									<p data-id="411300" class="cityvalue">查尔斯顿</p>
									<p data-id="320100" class="cityvalue">查理维尔</p>
									<p data-id="330200" class="cityvalue">查漠</p>
									<p data-id="320600" class="cityvalue">昌迪加尔</p>
									<p data-id="360100" class="cityvalue">长崎</p>
									<p data-id="411300" class="cityvalue">长滩</p>
									<p data-id="320100" class="cityvalue">查塔努加</p>
									<p data-id="330200" class="cityvalue">查亚普拉</p>
									<p data-id="320600" class="cityvalue">车里雅宾斯克</p>
									<p data-id="360100" class="cityvalue">赤塔</p>
									<p data-id="411300" class="cityvalue">冲绳</p>
									<p data-id="320100" class="cityvalue">钏路</p>
									<p data-id="330200" class="cityvalue">楚莱</p>
									<p data-id="320600" class="cityvalue">出云</p>
									<p data-id="360100" class="cityvalue">茨城</p>
									<p data-id="411300" class="cityvalue">兹霍布</p>
								</div>
								<div class="city-list"><span class="city-letter" id="D1">D</span>
									<p data-id="621100" class="cityvalue">大阪</p>
									<p data-id="441900" class="cityvalue">大本德</p>
									<p data-id="621100" class="cityvalue">达博</p>
									<p data-id="441900" class="cityvalue">大不里士</p>
									<p data-id="621100" class="cityvalue">大草原城</p>
									<p data-id="441900" class="cityvalue">大岛</p>
									<p data-id="621100" class="cityvalue">达尔本丁</p>
									<p data-id="441900" class="cityvalue">达尔文</p>
									<p data-id="621100" class="cityvalue">大分</p>
									<p data-id="441900" class="cityvalue">大福克斯</p>
									<p data-id="621100" class="cityvalue">带广</p>
									<p data-id="441900" class="cityvalue">代顿</p>
									<p data-id="621100" class="cityvalue">代尼兹利</p>
									<p data-id="441900" class="cityvalue">代托纳比奇</p>
									<p data-id="621100" class="cityvalue">代瓦迪米</p>
									<p data-id="441900" class="cityvalue">大急流城</p>
									<p data-id="621100" class="cityvalue">达卡</p>
									<p data-id="441900" class="cityvalue">达喀尔</p>
									<p data-id="621100" class="cityvalue">大开曼岛</p>
									<p data-id="441900" class="cityvalue">打拉根</p>
									<p data-id="621100" class="cityvalue">达拉曼</p>
									<p data-id="441900" class="cityvalue">达拉姆萨拉</p>
									<p data-id="621100" class="cityvalue">达拉斯</p>
									<p data-id="441900" class="cityvalue">达累斯萨拉姆</p>
									<p data-id="621100" class="cityvalue">达勒姆提斯瓦雷</p>
									<p data-id="441900" class="cityvalue">达曼</p>
									<p data-id="621100" class="cityvalue">大马士革</p>
									<p data-id="441900" class="cityvalue">丹佛</p>
									<p data-id="621100" class="cityvalue">达尼丁</p>
									<p data-id="441900" class="cityvalue">丹吉尔</p>
									<p data-id="621100" class="cityvalue">丹戎彭登</p>
									<p data-id="441900" class="cityvalue">丹戎槟榔</p>
									<p data-id="621100" class="cityvalue">丹兑</p>
									<p data-id="441900" class="cityvalue">道奇城</p>
									<p data-id="621100" class="cityvalue">道森克里克</p>
									<p data-id="441900" class="cityvalue">大邱</p>
									<p data-id="621100" class="cityvalue">达沃</p>
									<p data-id="441900" class="cityvalue">大西洋城</p>
									<p data-id="621100" class="cityvalue">大章克申</p>
									<p data-id="441900" class="cityvalue">大呖</p>
									<p data-id="621100" class="cityvalue">德班</p>
									<p data-id="441900" class="cityvalue">德岛</p>
									<p data-id="621100" class="cityvalue">德尔佐尔</p>
									<p data-id="441900" class="cityvalue">德黑兰</p>
									<p data-id="621100" class="cityvalue">德拉加齐汗</p>
									<p data-id="441900" class="cityvalue">德拉伊斯梅尔汗</p>
									<p data-id="621100" class="cityvalue">德雷达瓦</p>
									<p data-id="441900" class="cityvalue">德雷斯顿</p>
									<p data-id="621100" class="cityvalue">得里</p>
									<p data-id="441900" class="cityvalue">德里奥</p>
									<p data-id="621100" class="cityvalue">的黎波里</p>
									<p data-id="441900" class="cityvalue">的里亚斯特</p>
									<p data-id="621100" class="cityvalue">德芦斯</p>
									<p data-id="441900" class="cityvalue">得梅因</p>
									<p data-id="621100" class="cityvalue">邓迪</p>
									<p data-id="441900" class="cityvalue">德帕里佐</p>
									<p data-id="621100" class="cityvalue">德瑞司</p>
									<p data-id="441900" class="cityvalue">得土安</p>
									<p data-id="621100" class="cityvalue">德文波特</p>
									<p data-id="441900" class="cityvalue">德希</p>
									<p data-id="621100" class="cityvalue">奠边府</p>
									<p data-id="441900" class="cityvalue">刁曼</p>
									<p data-id="621100" class="cityvalue">迪拜</p>
									<p data-id="441900" class="cityvalue">迪比克</p>
									<p data-id="621100" class="cityvalue">第比利斯</p>
									<p data-id="621100" class="cityvalue">迪波洛格</p>
									<p data-id="441900" class="cityvalue">迪布鲁格尔</p>
									<p data-id="621100" class="cityvalue">迪金逊</p>
									<p data-id="441900" class="cityvalue">帝力</p>
									<p data-id="621100" class="cityvalue">蒂鲁吉拉伯利</p>
									<p data-id="441900" class="cityvalue">帝马鲁</p>
									<p data-id="621100" class="cityvalue">帝米卡</p>
									<p data-id="441900" class="cityvalue">蒂米什瓦拉/p>
										<p data-id="621100" class="cityvalue">第聂伯罗彼得罗夫斯克</p>
										<p data-id="441900" class="cityvalue">迪石</p>
										<p data-id="621100" class="cityvalue">底特津</p>
										<p data-id="441900" class="cityvalue">蒂瓦特</p>
										<p data-id="621100" class="cityvalue">第乌</p>
										<p data-id="441900" class="cityvalue">迪亚尼克尔</p>
										<p data-id="621100" class="cityvalue">洞海</p>
										<p data-id="441900" class="cityvalue">东京</p>
										<p data-id="621100" class="cityvalue">董里</p>
										<p data-id="441900" class="cityvalue">东伦敦</p>
										<p data-id="621100" class="cityvalue">斗湖</p>
										<p data-id="441900" class="cityvalue">杜阿拉</p>
										<p data-id="621100" class="cityvalue">都柏林</p>
										<p data-id="232700" class="cityvalue">杜波依斯</p>
										<p data-id="140200" class="cityvalue">杜布罗夫尼克</p>
										<p data-id="230600" class="cityvalue">杜蒂戈林</p>
										<p data-id="321181" class="cityvalue">杜兰戈(DGO)</p>
										<p data-id="210200" class="cityvalue">杜兰戈(DRO)</p>
										<p data-id="210600" class="cityvalue">都灵</p>
										<p data-id="370500" class="cityvalue">杜马格特</p>
										<p data-id="371400" class="cityvalue">多巴哥岛</p>
										<p data-id="511700" class="cityvalue">多哈</p>
										<p data-id="532900" class="cityvalue">多伦多</p>
										<p data-id="469003" class="cityvalue">多米尼加岛</p>
										<p data-id="469025" class="cityvalue">多内茨克</p>
										<p data-id="533400" class="cityvalue">多尼戈尔</p>
										<p data-id="510600" class="cityvalue">多森</p>
										<p data-id="469007" class="cityvalue">多特蒙德</p>
										<p data-id="533100" class="cityvalue">杜塞尔多夫</p>
										<p data-id="441900" class="cityvalue">杜尚别</p>
										<p data-id="621100" class="cityvalue">楠德德</p>
								</div>
								<div class="city-list"><span class="city-letter" id="E1">E</span>
									<p data-id="350700" class="cityvalue">厄尔巴岛</p>
									<p data-id="350900" class="cityvalue">俄克拉何马城</p>
									<p data-id="320100" class="cityvalue">鄂木斯克</p>
									<p data-id="330200" class="cityvalue">恩德培</p>
									<p data-id="320600" class="cityvalue">恩多拉</p>
									<p data-id="360100" class="cityvalue">恩菲达</p>
									<p data-id="411300" class="cityvalue">恩甲美那</p>
									<p data-id="350700" class="cityvalue">恩琼贝</p>
									<p data-id="350900" class="cityvalue">恩舍尔兹维克</p>
									<p data-id="150600" class="cityvalue">俄斯特拉伐</p>
									<p data-id="420700" class="cityvalue">厄斯特松德</p>
									<p data-id="422800" class="cityvalue">俄祖汝穆</p>
								</div>
								<div class="city-list"><span class="city-letter" id="F1">F</span>

									<p data-id="360300" class="cityvalue">法恩伯勒</p>
									<p data-id="360300" class="cityvalue">法戈</p>
									<p data-id="211100" class="cityvalue">法克法克</p>
									<p data-id="360300" class="cityvalue">法兰克福</p>
									<p data-id="211100" class="cityvalue">法兰西堡</p>
									<p data-id="360300" class="cityvalue">法鲁</p>
									<p data-id="211100" class="cityvalue">法明顿</p>
									<p data-id="360300" class="cityvalue">凡城</p>
									<p data-id="360300" class="cityvalue">法普鲁</p>
									<p data-id="211100" class="cityvalue">费城</p>
									<p data-id="360300" class="cityvalue">费尔班克斯</p>
									<p data-id="360300" class="cityvalue">费加里.科西嘉</p>
									<p data-id="211100" class="cityvalue">费拉格尔斯塔夫</p>
									<p data-id="360300" class="cityvalue">费里敦</p>
									<p data-id="211100" class="cityvalue">费萨拉巴德</p>
									<p data-id="360300" class="cityvalue">菲斯</p>
									<p data-id="360300" class="cityvalue">腓特烈斯港</p>
									<p data-id="211100" class="cityvalue">费耶特维尔</p>
									<p data-id="360300" class="cityvalue">凤凰城</p>
									<p data-id="360300" class="cityvalue">丰沙尔</p>
									<p data-id="211100" class="cityvalue">佛罗伦萨</p>
									<p data-id="360300" class="cityvalue">佛洛斯</p>
									<p data-id="211100" class="cityvalue">佛统</p>
									<p data-id="360300" class="cityvalue">富埃特文图拉岛</p>
									<p data-id="360300" class="cityvalue">福岛</p>
									<p data-id="211100" class="cityvalue">伏尔加格勒</p>
									<p data-id="360300" class="cityvalue">福冈</p>
									<p data-id="360300" class="cityvalue">富国岛</p>
									<p data-id="211100" class="cityvalue">弗雷德里克顿</p>
									<p data-id="210900" class="cityvalue">弗雷斯诺</p>
									<p data-id="210400" class="cityvalue">弗里波特</p>
									<p data-id="350181" class="cityvalue">弗林特</p>
									<p data-id="341200" class="cityvalue">弗卢里亚诺波利斯</p>
									<p data-id="370983" class="cityvalue">弗罗茨瓦夫</p>
									<p data-id="361000" class="cityvalue">福麦木瑞</p>
									<p data-id="350100" class="cityvalue">釜山</p>
									<p data-id="440600" class="cityvalue">富山</p>
									<p data-id="450600" class="cityvalue">福塔莱萨</p>
								</div>
								<div class="city-list"><span class="city-letter" id="G1">G</span>
									<p data-id="360300" class="cityvalue">盖恩斯维尔</p>
									<p data-id="360300" class="cityvalue">甘岛</p>
									<p data-id="211100" class="cityvalue">冈山</p>
									<p data-id="360300" class="cityvalue">甘米银/东米萨米斯</p>
									<p data-id="360300" class="cityvalue">干尼亚</p>
									<p data-id="211100" class="cityvalue">高松</p>
									<p data-id="360300" class="cityvalue">高雄</p>
									<p data-id="360300" class="cityvalue">高知</p>
									<p data-id="211100" class="cityvalue">哥本哈根</p>
									<p data-id="360300" class="cityvalue">哥打巴鲁</p>
									<p data-id="360300" class="cityvalue">哥打巴卢</p>
									<p data-id="211100" class="cityvalue">哥打京那巴鲁</p>
									<p data-id="360300" class="cityvalue">格但斯克</p>
									<p data-id="360300" class="cityvalue">哥德堡</p>
									<p data-id="211100" class="cityvalue">格尔夫波特</p>
									<p data-id="360300" class="cityvalue">格拉茨</p>
									<p data-id="211100" class="cityvalue">格拉德斯通</p>
									<p data-id="360300" class="cityvalue">格拉夫顿</p>
									<p data-id="360300" class="cityvalue">格拉夫顿</p>
									<p data-id="211100" class="cityvalue">格拉纳达</p>
									<p data-id="360300" class="cityvalue">格兰德岛</p>
									<p data-id="360300" class="cityvalue">格劳斯特</p>
									<p data-id="211100" class="cityvalue">格拉斯哥</p>
									<p data-id="360300" class="cityvalue">格雷特瀑布</p>
									<p data-id="360300" class="cityvalue">格勒诺布尔</p>
									<p data-id="211100" class="cityvalue">格里菲斯</p>
									<p data-id="360300" class="cityvalue">格林贝</p>
									<p data-id="211100" class="cityvalue">格林纳达</p>
									<p data-id="360300" class="cityvalue">格林斯伯勒/海波因特</p>
									<p data-id="360300" class="cityvalue">格林威尔</p>
									<p data-id="211100" class="cityvalue">格林维尔</p>
									<p data-id="360300" class="cityvalue">哥伦比亚</p>
									<p data-id="360300" class="cityvalue">哥伦布</p>
									<p data-id="211100" class="cityvalue">哥伦打洛</p>
									<p data-id="360300" class="cityvalue">格罗宁根</p>
									<p data-id="360300" class="cityvalue">根西岛</p>
									<p data-id="211100" class="cityvalue">哥印拜陀</p>
									<p data-id="360300" class="cityvalue">贡达尔</p>
									<p data-id="360300" class="cityvalue">宫古</p>
									<p data-id="211100" class="cityvalue">宫崎</p>
									<p data-id="360300" class="cityvalue">瓜达拉哈拉</p>
									<p data-id="360300" class="cityvalue">瓜德尔</p>
									<p data-id="211100" class="cityvalue">瓜拉丁加奴</p>
									<p data-id="360300" class="cityvalue">关丹</p>
									<p data-id="360300" class="cityvalue">关岛</p>
									<p data-id="440100" class="cityvalue">广岛</p>
									<p data-id="360700" class="cityvalue">光州</p>
									<p data-id="510800" class="cityvalue">瓜亚基尔</p>
									<p data-id="511600" class="cityvalue">古邦</p>
									<p data-id="450300" class="cityvalue">归仁</p>
									<p data-id="450800" class="cityvalue">古晋</p>
									<p data-id="520100" class="cityvalue">古拉亚特</p>
									<p data-id="513300" class="cityvalue">古农西托利</p>
									<p data-id="623000" class="cityvalue">果阿</p>
									<p data-id="640400" class="cityvalue">古斯塔夫斯</p>
									<p data-id="632600" class="cityvalue">古瓦哈蒂</p>
								</div>
								<div class="city-list"><span class="city-letter" id="H1">H</span>
									<p data-id="211100" class="cityvalue">哈巴罗夫斯克</p>
									<p data-id="360300" class="cityvalue">哈博罗内</p>
									<p data-id="360300" class="cityvalue">哈尔格萨</p>
									<p data-id="211100" class="cityvalue">哈尔科夫</p>
									<p data-id="360300" class="cityvalue">海参崴</p>
									<p data-id="360300" class="cityvalue">海得拉巴</p>
									<p data-id="211100" class="cityvalue">海登</p>
									<p data-id="360300" class="cityvalue">海恩尼斯</p>
									<p data-id="360300" class="cityvalue">海法</p>
									<p data-id="211100" class="cityvalue">海防</p>
									<p data-id="360300" class="cityvalue">海曼岛</p>
									<p data-id="360300" class="cityvalue">海斯</p>
									<p data-id="211100" class="cityvalue">海于格生德</p>
									<p data-id="360300" class="cityvalue">哈科特港</p>
									<p data-id="360300" class="cityvalue">哈拉雷</p>
									<p data-id="211100" class="cityvalue">哈里法克斯</p>
									<p data-id="360300" class="cityvalue">哈灵根</p>
									<p data-id="360300" class="cityvalue">哈里森</p>
									<p data-id="211100" class="cityvalue">哈里斯堡</p>
									<p data-id="360300" class="cityvalue">哈密尔顿</p>
									<p data-id="360300" class="cityvalue">哈密尔顿岛</p>
									<p data-id="211100" class="cityvalue">汉班托特</p>
									<p data-id="360300" class="cityvalue">汉堡</p>
									<p data-id="360300" class="cityvalue">汉伯塞</p>
									<p data-id="211100" class="cityvalue">函馆</p>
									<p data-id="360300" class="cityvalue">汉考克</p>
									<p data-id="360300" class="cityvalue">汉密尔顿</p>
									<p data-id="211100" class="cityvalue">汉诺威</p>
									<p data-id="360300" class="cityvalue">汉普顿</p>
									<p data-id="360300" class="cityvalue">汉斯威尔</p>
									<p data-id="211100" class="cityvalue">哈塔伊</p>
									<p data-id="360300" class="cityvalue">哈特福德</p>
									<p data-id="360300" class="cityvalue">哈瓦那</p>
									<p data-id="211100" class="cityvalue">哈伊勒</p>
									<p data-id="360300" class="cityvalue">哈伊马角</p>
									<p data-id="360300" class="cityvalue">哈伊尼斯</p>
									<p data-id="211100" class="cityvalue">合艾</p>
									<p data-id="360300" class="cityvalue">赫尔格达</p>
									<p data-id="360300" class="cityvalue">赫尔辛堡</p>
									<p data-id="211100" class="cityvalue">赫尔辛基</p>
									<p data-id="360300" class="cityvalue">黑德兰港</p>
									<p data-id="211100" class="cityvalue">黑格斯敦</p>
									<p data-id="360300" class="cityvalue">黑角</p>
									<p data-id="360300" class="cityvalue">赫拉克利翁</p>
									<p data-id="211100" class="cityvalue">赫拉特</p>
									<p data-id="360300" class="cityvalue">赫雷斯</p>
									<p data-id="360300" class="cityvalue">赫勒纳</p>
									<p data-id="211100" class="cityvalue">赫林斯多夫</p>
									<p data-id="360300" class="cityvalue">赫罗纳</p>
									<p data-id="360300" class="cityvalue">荷马</p>
									<p data-id="211100" class="cityvalue">河内</p>
									<p data-id="360300" class="cityvalue">亨廷顿</p>
									<p data-id="211100" class="cityvalue">赫维湾</p>
									<p data-id="360300" class="cityvalue">荷兹普鲁伊特</p>
									<p data-id="360300" class="cityvalue">华尔顿堡</p>
									<p data-id="211100" class="cityvalue">怀阿拉</p>
									<p data-id="360300" class="cityvalue">怀特霍斯</p>
									<p data-id="360300" class="cityvalue">华雷斯城</p>
									<p data-id="211100" class="cityvalue">花莲</p>
									<p data-id="360300" class="cityvalue">黄金海岸</p>
									<p data-id="360300" class="cityvalue">华沙</p>
									<p data-id="211100" class="cityvalue">华盛顿</p>
									<p data-id="360300" class="cityvalue">滑铁卢</p>
									<p data-id="360300" class="cityvalue">华欣</p>
									<p data-id="211100" class="cityvalue">惠灵顿</p>
									<p data-id="360300" class="cityvalue">胡梅拉</p>
									<p data-id="360300" class="cityvalue">胡纳</p>
									<p data-id="211100" class="cityvalue">霍奥莱胡阿</p>
									<p data-id="360300" class="cityvalue">霍巴特</p>
									<p data-id="360300" class="cityvalue">霍夫</p>
									<p data-id="211100" class="cityvalue">霍基蒂卡</p>
									<p data-id="360300" class="cityvalue">霍洛</p>
									<p data-id="360300" class="cityvalue">霍尼亚拉</p>
									<p data-id="211100" class="cityvalue">胡志明市</p>
									<p data-id="360300" class="cityvalue">香港</p>
								</div>
								<div class="city-list"><span class="city-letter" id="J1">J</span>

									<p data-id="211100" class="cityvalue">贾巴尔普尔</p>
									<p data-id="360300" class="cityvalue">加德满都</p>
									<p data-id="410400" class="cityvalue">加帝夫</p>
									<p data-id="211100" class="cityvalue">加尔各答</p>
									<p data-id="360300" class="cityvalue">加济安泰普</p>
									<p data-id="410400" class="cityvalue">加拉加斯</p>
									<p data-id="211100" class="cityvalue">加里宁格斯</p>
									<p data-id="360300" class="cityvalue">甲米</p>
									<p data-id="211100" class="cityvalue">甲描育</p>
									<p data-id="360300" class="cityvalue">贾姆纳加尔</p>
									<p data-id="211100" class="cityvalue">兼德扎</p>
									<p data-id="360300" class="cityvalue">江布尔州</p>
									<p data-id="410400" class="cityvalue">剑桥</p>
									<p data-id="211100" class="cityvalue">焦特布尔</p>
									<p data-id="360300" class="cityvalue">加希姆</p>
									<p data-id="410400" class="cityvalue">贾赞</p>
									<p data-id="211100" class="cityvalue">吉布提</p>
									<p data-id="360300" class="cityvalue">吉达</p>
									<p data-id="211100" class="cityvalue">吉大港</p>
									<p data-id="360300" class="cityvalue">吉德拉尔</p>
									<p data-id="211100" class="cityvalue">基蒂拉</p>
									<p data-id="360300" class="cityvalue">基督城</p>
									<p data-id="410400" class="cityvalue">基多</p>
									<p data-id="211100" class="cityvalue">杰克逊(JAC)</p>
									<p data-id="360300" class="cityvalue">杰克逊(JAN)</p>
									<p data-id="410400" class="cityvalue">杰克逊(MKL)</p>
									<p data-id="211100" class="cityvalue">杰克逊维尔(美国)</p>
									<p data-id="360300" class="cityvalue">杰克逊维尔</p>
									<p data-id="211100" class="cityvalue">杰拉尔敦</p>
									<p data-id="360300" class="cityvalue">基尔</p>
									<p data-id="211100" class="cityvalue">杰索尔</p>
									<p data-id="211100" class="cityvalue">基辅</p>
									<p data-id="360300" class="cityvalue">基加利</p>
									<p data-id="410400" class="cityvalue">吉吉加</p>
									<p data-id="211100" class="cityvalue">吉勒台</p>
									<p data-id="360300" class="cityvalue">基联恩金克</p>
									<p data-id="211100" class="cityvalue">基林</p>
									<p data-id="360300" class="cityvalue">吉隆坡</p>
									<p data-id="211100" class="cityvalue">基洛纳</p>
									<p data-id="360300" class="cityvalue">基律纳</p>
									<p data-id="410400" class="cityvalue">季马</p>
									<p data-id="211100" class="cityvalue">金边</p>
									<p data-id="360300" class="cityvalue">金伯利</p>
									<p data-id="410400" class="cityvalue">金岛</p>
									<p data-id="211100" class="cityvalue">京都</p>
									<p data-id="360300" class="cityvalue">静冈</p>
									<p data-id="211100" class="cityvalue">鲸湾港</p>
									<p data-id="360300" class="cityvalue">金曼</p>
									<p data-id="211100" class="cityvalue">金奈</p>
									<p data-id="360300" class="cityvalue">金萨蒙</p>
									<p data-id="410400" class="cityvalue">金沙萨</p>
									<p data-id="211100" class="cityvalue">金斯敦</p>
									<p data-id="360300" class="cityvalue">金斯顿</p>
									<p data-id="410400" class="cityvalue">金斯科特</p>
									<p data-id="211100" class="cityvalue">金斯维尔</p>
									<p data-id="360300" class="cityvalue">晋州</p>
									<p data-id="211100" class="cityvalue">金瓯</p>
									<p data-id="360300" class="cityvalue">基奇纳</p>
									<p data-id="211100" class="cityvalue">基桑加尼</p>
									<p data-id="360300" class="cityvalue">吉斯伯恩</p>
									<p data-id="410400" class="cityvalue">基苏木</p>
									<p data-id="211100" class="cityvalue">旧金山</p>
									<p data-id="360300" class="cityvalue">基韦斯特</p>
									<p data-id="410400" class="cityvalue">基西拉</p>
									<p data-id="211100" class="cityvalue">基希讷乌</p>
									<p data-id="360300" class="cityvalue">济州岛</p>
									<p data-id="211100" class="cityvalue">居茶</p>
									<p data-id="360300" class="cityvalue">巨港</p>
									<p data-id="211100" class="cityvalue">君士坦丁</p>
								</div>
								<div class="city-list"><span class="city-letter" id="K1">K</span>
									<p data-id="360300" class="cityvalue">卡昂</p>
									<p data-id="410400" class="cityvalue">喀布尔</p>
									<p data-id="211100" class="cityvalue">卡尔古利</p>
									<p data-id="360300" class="cityvalue">卡尔加里</p>
									<p data-id="211100" class="cityvalue">卡尔加里</p>
									<p data-id="360300" class="cityvalue">卡尔库龙</p>
									<p data-id="211100" class="cityvalue">卡尔马</p>
									<p data-id="360300" class="cityvalue">卡尔帕索斯</p>
									<p data-id="410400" class="cityvalue">卡尔斯鲁厄</p>
									<p data-id="211100" class="cityvalue">卡尔斯塔德</p>
									<p data-id="360300" class="cityvalue">卡尔维</p>
									<p data-id="211100" class="cityvalue">卡胡卢伊</p>
									<p data-id="360300" class="cityvalue">凯恩斯</p>
									<p data-id="211100" class="cityvalue">开尔斯</p>
									<p data-id="360300" class="cityvalue">凯法利尼亚</p>
									<p data-id="410400" class="cityvalue">凯里郡</p>
									<p data-id="211100" class="cityvalue">凯里凯里</p>
									<p data-id="360300" class="cityvalue">开罗</p>
									<p data-id="211100" class="cityvalue">凯马纳</p>
									<p data-id="360300" class="cityvalue">凯米/托尼奥</p>
									<p data-id="211100" class="cityvalue">开普敦</p>
									<p data-id="360300" class="cityvalue">凯奇坎</p>
									<p data-id="410400" class="cityvalue">开塞利</p>
									<p data-id="211100" class="cityvalue">凯苏马</p>
									<p data-id="360300" class="cityvalue">凯塔依亚</p>
									<p data-id="211100" class="cityvalue">卡加延德奥罗</p>
									<p data-id="360300" class="cityvalue">卡拉干达</p>
									<p data-id="211100" class="cityvalue">卡拉曼马拉斯</p>
									<p data-id="360300" class="cityvalue">卡拉马塔</p>
									<p data-id="410400" class="cityvalue">卡拉马祖</p>
									<p data-id="211100" class="cityvalue">卡拉奇</p>
									<p data-id="360300" class="cityvalue">卡拉特哈</p>
									<p data-id="211100" class="cityvalue">卡利</p>
									<p data-id="360300" class="cityvalue">卡利博</p>
									<p data-id="211100" class="cityvalue">卡里马瓜</p>
									<p data-id="360300" class="cityvalue">卡利斯佩尔</p>
									<p data-id="410400" class="cityvalue">卡利亚里</p>
									<p data-id="211100" class="cityvalue">卡卢普</p>
									<p data-id="360300" class="cityvalue">卡门城</p>
									<p data-id="211100" class="cityvalue">坎贝尔河</p>
									<p data-id="360300" class="cityvalue">坎贝尔镇</p>
									<p data-id="211100" class="cityvalue">勘察加</p>
									<p data-id="360300" class="cityvalue">坎大哈</p>
									<p data-id="410400" class="cityvalue">康诺特</p>
									<p data-id="211100" class="cityvalue">康瑟尔</p>
									<p data-id="360300" class="cityvalue">康提</p>
									<p data-id="211100" class="cityvalue">康英克斯</p>
									<p data-id="360300" class="cityvalue">卡尼</p>
									<p data-id="211100" class="cityvalue">卡尼亚</p>
									<p data-id="360300" class="cityvalue">坎昆</p>
									<p data-id="410400" class="cityvalue">坎卢普斯</p>
									<p data-id="211100" class="cityvalue">坎佩尔</p>
									<p data-id="360300" class="cityvalue">堪培拉</p>
									<p data-id="211100" class="cityvalue">坎佩切</p>
									<p data-id="360300" class="cityvalue">坎普尔</p>
									<p data-id="211100" class="cityvalue">堪萨斯城</p>
									<p data-id="360300" class="cityvalue">卡诺</p>
									<p data-id="410400" class="cityvalue">考爱岛</p>
									<p data-id="211100" class="cityvalue">考夫曼考弗</p>
									<p data-id="360300" class="cityvalue">卡萨布兰卡</p>
									<p data-id="211100" class="cityvalue">喀山</p>
									<p data-id="360300" class="cityvalue">卡斯尔加</p>
									<p data-id="211100" class="cityvalue">卡斯卡韦尔</p>
									<p data-id="360300" class="cityvalue">卡斯特尔</p>
									<p data-id="410400" class="cityvalue">卡斯珀</p>
									<p data-id="211100" class="cityvalue">卡塔赫纳</p>
									<p data-id="360300" class="cityvalue">卡塔曼</p>
									<p data-id="211100" class="cityvalue">卡塔尼亚</p>
									<p data-id="360300" class="cityvalue">卡提克兰</p>
									<p data-id="211100" class="cityvalue">喀土穆</p>
									<p data-id="360300" class="cityvalue">卡托维茨</p>
									<p data-id="410400" class="cityvalue">卡瓦拉</p>
									<p data-id="211100" class="cityvalue">卡瓦廷</p>
									<p data-id="360300" class="cityvalue">卡乌</p>
									<p data-id="211100" class="cityvalue">卡宴</p>
									<p data-id="360300" class="cityvalue">科策布</p>
									<p data-id="211100" class="cityvalue">科迪</p>
									<p data-id="360300" class="cityvalue">科迪亚克</p>
									<p data-id="410400" class="cityvalue">科尔德港</p>
									<p data-id="211100" class="cityvalue">科夫斯港</p>
									<p data-id="360300" class="cityvalue">克加拉</p>
									<p data-id="211100" class="cityvalue">克基拉</p>
									<p data-id="360300" class="cityvalue">克久拉霍</p>
									<p data-id="211100" class="cityvalue">科克</p>
									<p data-id="360300" class="cityvalue">科科岛</p>
									<p data-id="410400" class="cityvalue">科科拉</p>
									<p data-id="211100" class="cityvalue">科科舍塔</p>
									<p data-id="360300" class="cityvalue">科克斯巴扎尔</p>
									<p data-id="211100" class="cityvalue">科科斯群岛</p>
									<p data-id="360300" class="cityvalue">柯克沃尔</p>
									<p data-id="211100" class="cityvalue">克拉根福特</p>
									<p data-id="360300" class="cityvalue">克莱柏特</p>
									<p data-id="410400" class="cityvalue">克莱蒙费朗</p>
									<p data-id="211100" class="cityvalue">克拉克斯堡</p>
									<p data-id="360300" class="cityvalue">克拉马斯福尔斯</p>
									<p data-id="211100" class="cityvalue">克兰布鲁克</p>
									<p data-id="360300" class="cityvalue">克朗克里</p>
									<p data-id="410400" class="cityvalue">克拉斯诺达尔</p>
									<p data-id="211100" class="cityvalue">克拉斯诺亚尔斯克</p>
									<p data-id="360300" class="cityvalue">克拉特夫</p>
									<p data-id="211100" class="cityvalue">克利夫兰</p>
									<p data-id="360300" class="cityvalue">科利马</p>
									<p data-id="410400" class="cityvalue">克利奇站</p>
									<p data-id="211100" class="cityvalue">克里斯蒂安桑</p>
									<p data-id="360300" class="cityvalue">克里斯蒂安松</p>
									<p data-id="211100" class="cityvalue">科隆</p>
									<p data-id="360300" class="cityvalue">科伦坡</p>
									<p data-id="410400" class="cityvalue">科罗尔</p>
									<p data-id="211100" class="cityvalue">科罗拉多斯普林</p>
									<p data-id="360300" class="cityvalue">克罗托尼</p>
									<p data-id="211100" class="cityvalue">克洛维斯</p>
									<p data-id="360300" class="cityvalue">克卢日</p>
									<p data-id="410400" class="cityvalue">克麦罗沃</p>
									<p data-id="211100" class="cityvalue">科纳</p>
									<p data-id="360300" class="cityvalue">科纳克里</p>
									<p data-id="211100" class="cityvalue">肯达里</p>
									<p data-id="360300" class="cityvalue">科尼亚</p>
									<p data-id="410400" class="cityvalue">科斯</p>
									<p data-id="211100" class="cityvalue">科苏梅尔</p>
									<p data-id="360300" class="cityvalue">科塔巴托</p>
									<p data-id="211100" class="cityvalue">科特斯</p>
									<p data-id="360300" class="cityvalue">科托努</p>
									<p data-id="410400" class="cityvalue">科威特</p>
									<p data-id="211100" class="cityvalue">科希策</p>
									<p data-id="360300" class="cityvalue">科泽科德</p>
									<p data-id="211100" class="cityvalue">克孜勒奥尔达</p>
									<p data-id="360300" class="cityvalue">科珀斯克里斯蒂</p>
									<p data-id="410400" class="cityvalue">孔敬</p>
									<p data-id="211100" class="cityvalue">库奥皮欧</p>
									<p data-id="360300" class="cityvalue">库伯佩迪</p>
									<p data-id="211100" class="cityvalue">库德贾德</p>
									<p data-id="360300" class="cityvalue">魁北克</p>
									<p data-id="410400" class="cityvalue">奎达</p>
									<p data-id="211100" class="cityvalue">奎雷塔罗</p>
									<p data-id="360300" class="cityvalue">库拉索(加勒比)</p>
									<p data-id="211100" class="cityvalue">库利阿坎</p>
									<p data-id="360300" class="cityvalue">库里蒂巴</p>
									<p data-id="410400" class="cityvalue">坤甸</p>
									<p data-id="211100" class="cityvalue">库内奥</p>
									<p data-id="360300" class="cityvalue">昆仑岛</p>
									<p data-id="320583" class="cityvalue">昆斯敦</p>
									<p data-id="410200" class="cityvalue">库努纳拉</p>
									<p data-id="530100" class="cityvalue">库奇</p>
									<p data-id="650200" class="cityvalue">库萨莫</p>
									<p data-id="653000" class="cityvalue">库斯科</p>
									<p data-id="653100" class="cityvalue">库亚巴</p>
								</div>
								<div class="city-list"><span class="city-letter" id="L1">L</span>
									<p data-id="211100" class="cityvalue">拉巴斯(LAP)</p>
									<p data-id="360300" class="cityvalue">拉巴斯(LPB)</p>
									<p data-id="211100" class="cityvalue">拉巴特</p>
									<p data-id="360300" class="cityvalue">拉伯克</p>
									<p data-id="410400" class="cityvalue">拉布安</p>
									<p data-id="211100" class="cityvalue">拉布哈</p>
									<p data-id="360300" class="cityvalue">拉菲特</p>
									<p data-id="211100" class="cityvalue">拉夫哈</p>
									<p data-id="360300" class="cityvalue">拉各斯</p>
									<p data-id="410400" class="cityvalue">拉哈达图</p>
									<p data-id="211100" class="cityvalue">拉哈尔</p>
									<p data-id="360300" class="cityvalue">莱昂</p>
									<p data-id="211100" class="cityvalue">莱吧嫩</p>
									<p data-id="360300" class="cityvalue">莱比锡</p>
									<p data-id="211100" class="cityvalue">赖布尔</p>
									<p data-id="360300" class="cityvalue">莱城</p>
									<p data-id="410400" class="cityvalue">莱加斯皮</p>
									<p data-id="211100" class="cityvalue">莱克查尔斯</p>
									<p data-id="360300" class="cityvalue">莱斯星顿</p>
									<p data-id="211100" class="cityvalue">莱斯布里奇</p>
									<p data-id="360300" class="cityvalue">拉贾蒙德里</p>
									<p data-id="410400" class="cityvalue">拉杰果德</p>
									<p data-id="211100" class="cityvalue">拉杰沙希</p>
									<p data-id="360300" class="cityvalue">拉克鲁利亚</p>
									<p data-id="211100" class="cityvalue">拉克鲁斯</p>
									<p data-id="360300" class="cityvalue">拉克塞尔夫</p>
									<p data-id="410400" class="cityvalue">拉廊</p>
									<p data-id="211100" class="cityvalue">拉雷多</p>
									<p data-id="360300" class="cityvalue">拉里贝拉</p>
									<p data-id="211100" class="cityvalue">拉罗马纳</p>
									<p data-id="360300" class="cityvalue">拉罗汤加</p>
									<p data-id="410400" class="cityvalue">拉罗歇尔</p>
									<p data-id="211100" class="cityvalue">拉默齐亚</p>
									<p data-id="360300" class="cityvalue">拉奈</p>
									<p data-id="211100" class="cityvalue">拉纳卡(拉纳克斯)</p>
									<p data-id="360300" class="cityvalue">朗勃拉邦</p>
									<p data-id="410400" class="cityvalue">蓝格尔(LUV)</p>
									<p data-id="211100" class="cityvalue">蓝格尔(WRG)</p>
									<p data-id="360300" class="cityvalue">朗里奇</p>
									<p data-id="410400" class="cityvalue">琅南塔</p>
									<p data-id="211100" class="cityvalue">朗塞斯顿</p>
									<p data-id="211100" class="cityvalue">拉尼永</p>
									<p data-id="211100" class="cityvalue">兰卡威</p>
									<p data-id="360300" class="cityvalue">兰佩杜萨</p>
									<p data-id="410400" class="cityvalue">兰契</p>
									<p data-id="211100" class="cityvalue">兰塞里亚</p>
									<p data-id="360300" class="cityvalue">兰萨罗特</p>
									<p data-id="410400" class="cityvalue">兰辛</p>
									<p data-id="211100" class="cityvalue">劳德代尔</p>
									<p data-id="211100" class="cityvalue">劳顿</p>
									<p data-id="211100" class="cityvalue">拉皮德城</p>
									<p data-id="360300" class="cityvalue">拉斯帕尔马斯</p>
									<p data-id="410400" class="cityvalue">拉斯维加斯</p>
									<p data-id="211100" class="cityvalue">拉塔基亚</p>
									<p data-id="360300" class="cityvalue">拉特兰</p>
									<p data-id="410400" class="cityvalue">拉特罗布</p>
									<p data-id="211100" class="cityvalue">拉瓦格</p>
									<p data-id="211100" class="cityvalue">拉希姆亚尔可罕</p>
									<p data-id="211100" class="cityvalue">勒阿弗尔</p>
									<p data-id="360300" class="cityvalue">雷德蒙德</p>
									<p data-id="410400" class="cityvalue">雷丁</p>
									<p data-id="211100" class="cityvalue">雷恩</p>
									<p data-id="360300" class="cityvalue">雷焦卡拉布里亚</p>
									<p data-id="410400" class="cityvalue">雷克亚未克</p>
									<p data-id="211100" class="cityvalue">雷诺萨</p>
									<p data-id="211100" class="cityvalue">雷文斯索普</p>
									<p data-id="360300" class="cityvalue">雷乌斯</p>
									<p data-id="410400" class="cityvalue">累西腓</p>
									<p data-id="211100" class="cityvalue">勒克瑙</p>
									<p data-id="360300" class="cityvalue">勒芒</p>
									<p data-id="410400" class="cityvalue">黎</p>
									<p data-id="211100" class="cityvalue">里昂</p>
									<p data-id="211100" class="cityvalue">利伯勒尔</p>
									<p data-id="360300" class="cityvalue">利伯维尔</p>
									<p data-id="410400" class="cityvalue">里查兹贝</p>
									<p data-id="211100" class="cityvalue">列城</p>
									<p data-id="360300" class="cityvalue">里尔</p>
									<p data-id="410400" class="cityvalue">列日</p>
									<p data-id="211100" class="cityvalue">利尔蒙斯</p>
									<p data-id="211100" class="cityvalue">里弗顿</p>
									<p data-id="360300" class="cityvalue">里加</p>
									<p data-id="410400" class="cityvalue">里贾纳</p>
									<p data-id="211100" class="cityvalue">利浪</p>
									<p data-id="360300" class="cityvalue">利隆圭</p>
									<p data-id="410400" class="cityvalue">利马</p>
									<p data-id="211100" class="cityvalue">里米尼</p>
									<p data-id="360300" class="cityvalue">利摩日</p>
									<p data-id="410400" class="cityvalue">利姆诺斯岛</p>
									<p data-id="211100" class="cityvalue">林茨</p>
									<p data-id="360300" class="cityvalue">林肯</p>
									<p data-id="410400" class="cityvalue">林肯港</p>
									<p data-id="211100" class="cityvalue">林奇堡</p>
									<p data-id="360300" class="cityvalue">里诺</p>
									<p data-id="410400" class="cityvalue">林雪平</p>
									<p data-id="211100" class="cityvalue">里士满</p>
									<p data-id="360300" class="cityvalue">里士满(美国)</p>
									<p data-id="410400" class="cityvalue">丽水</p>
									<p data-id="211100" class="cityvalue">里斯本</p>
									<p data-id="360300" class="cityvalue">利斯莫尔</p>
									<p data-id="410400" class="cityvalue">刘易斯堡</p>
									<p data-id="211100" class="cityvalue">刘易斯顿</p>
									<p data-id="360300" class="cityvalue">利韦里亚</p>
									<p data-id="410400" class="cityvalue">利文斯通</p>
									<p data-id="211100" class="cityvalue">里沃夫</p>
									<p data-id="360300" class="cityvalue">利物浦</p>
									<p data-id="410400" class="cityvalue">利雅得</p>
									<p data-id="211100" class="cityvalue">里耶卡</p>
									<p data-id="360300" class="cityvalue">黎逸</p>
									<p data-id="410400" class="cityvalue">里约热内卢</p>
									<p data-id="211100" class="cityvalue">利兹</p>
									<p data-id="360300" class="cityvalue">龙目岛</p>
									<p data-id="410400" class="cityvalue">隆内比</p>
									<p data-id="211100" class="cityvalue">鲁安</p>
									<p data-id="360300" class="cityvalue">卢本巴希</p>
									<p data-id="410400" class="cityvalue">卢布尔雅那</p>
									<p data-id="211100" class="cityvalue">鹿儿岛</p>
									<p data-id="360300" class="cityvalue">卢尔德/塔布</p>
									<p data-id="410400" class="cityvalue">鹿湖</p>
									<p data-id="211100" class="cityvalue">卢加诺</p>
									<p data-id="360300" class="cityvalue">卢克拉</p>
									<p data-id="410400" class="cityvalue">卢克索</p>
									<p data-id="211100" class="cityvalue">轮岛</p>
									<p data-id="360300" class="cityvalue">伦敦德里</p>
									<p data-id="410400" class="cityvalue">伦敦(英国)</p>
									<p data-id="211100" class="cityvalue">伦敦(加拿大)</p>
									<p data-id="360300" class="cityvalue">罗安达</p>
									<p data-id="410400" class="cityvalue">罗阿诺克</p>
									<p data-id="211100" class="cityvalue">罗得岛</p>
									<p data-id="360300" class="cityvalue">罗德兹</p>
									<p data-id="410400" class="cityvalue">罗凡涅米</p>
									<p data-id="211100" class="cityvalue">罗克福德</p>
									<p data-id="360300" class="cityvalue">罗克汉普顿</p>
									<p data-id="410400" class="cityvalue">罗克兰</p>
									<p data-id="211100" class="cityvalue">罗克赛斯城</p>
									<p data-id="360300" class="cityvalue">洛坤</p>
									<p data-id="410400" class="cityvalue">罗利</p>
									<p data-id="211100" class="cityvalue">洛里昂</p>
									<p data-id="360300" class="cityvalue">罗马</p>
									<p data-id="410400" class="cityvalue">洛美</p>
									<p data-id="211100" class="cityvalue">罗切斯特(ROC)</p>
									<p data-id="360300" class="cityvalue">罗切斯特(RST)</p>
									<p data-id="410400" class="cityvalue">洛杉矶</p>
									<p data-id="211100" class="cityvalue">洛斯莫奇斯</p>
									<p data-id="360300" class="cityvalue">罗斯托夫</p>
									<p data-id="410400" class="cityvalue">罗斯托尼</p>
									<p data-id="211100" class="cityvalue">罗托鲁瓦</p>
									<p data-id="360300" class="cityvalue">卢萨卡</p>
									<p data-id="410400" class="cityvalue">卢森堡</p>
									<p data-id="211100" class="cityvalue">鹿特丹</p>
									<p data-id="360300" class="cityvalue">卢武克</p>
									<p data-id="410400" class="cityvalue">路易斯威尔</p>
									<p data-id="211100" class="cityvalue">吕勒奥</p>
									<p data-id="360300" class="cityvalue">吕宋岛</p>
									<p data-id="410400" class="cityvalue">槟榔</p>

								</div>
								<div class="city-list"><span class="city-letter" id="M1">M</span>
									<p data-id="410900" class="cityvalue">澳门</p>
									<p data-id="350300" class="cityvalue">马埃岛</p>
									<p data-id="211100" class="cityvalue">马德里</p>
									<p data-id="211100" class="cityvalue">马丁</p>
									<p data-id="360300" class="cityvalue">马杜赖</p>
									<p data-id="410400" class="cityvalue">马恩岛</p>
									<p data-id="410900" class="cityvalue">马尔多那多港</p>
									<p data-id="350300" class="cityvalue">马尔默</p>
									<p data-id="211100" class="cityvalue">马耳他</p>
									<p data-id="211100" class="cityvalue">麦格拉斯</p>
									<p data-id="360300" class="cityvalue">马格尼托格尔斯克</p>
									<p data-id="410400" class="cityvalue">摩亨朱达罗</p>
									<p data-id="410900" class="cityvalue">迈阿密</p>
									<p data-id="350300" class="cityvalue">麦迪纳</p>
									<p data-id="211100" class="cityvalue">麦迪逊</p>
									<p data-id="211100" class="cityvalue">迈尔斯堡</p>
									<p data-id="360300" class="cityvalue">麦凯</p>
									<p data-id="410400" class="cityvalue">迈克艾伦</p>
									<p data-id="410900" class="cityvalue">麦夸里港</p>
									<p data-id="350300" class="cityvalue">麦库克</p>
									<p data-id="211100" class="cityvalue">迈索尔</p>
									<p data-id="360300" class="cityvalue">马加丹</p>
									<p data-id="211100" class="cityvalue">马凯特</p>
									<p data-id="211100" class="cityvalue">马拉博</p>
									<p data-id="360300" class="cityvalue">马拉加</p>
									<p data-id="410400" class="cityvalue">马拉喀什</p>
									<p data-id="410900" class="cityvalue">玛琅</p>
									<p data-id="350300" class="cityvalue">马老奇</p>
									<p data-id="211100" class="cityvalue">马拉提亚</p>
									<p data-id="360300" class="cityvalue">马累</p>
									<p data-id="211100" class="cityvalue">玛丽港</p>
									<p data-id="211100" class="cityvalue">马林迪</p>
									<p data-id="360300" class="cityvalue">马六甲</p>
									<p data-id="410400" class="cityvalue">马鲁奇多</p>
									<p data-id="410900" class="cityvalue">马默斯莱克斯</p>
									<p data-id="350300" class="cityvalue">马穆朱</p>
									<p data-id="211100" class="cityvalue">马那瓜</p>
									<p data-id="360300" class="cityvalue">马瑙斯</p>
									<p data-id="211100" class="cityvalue">曼彻斯特</p>
									<p data-id="211100" class="cityvalue">曼德勒</p>
									<p data-id="360300" class="cityvalue">芒特艾萨</p>
									<p data-id="410400" class="cityvalue">芒特甘比尔</p>
									<p data-id="410900" class="cityvalue">曼谷</p>
									<p data-id="350300" class="cityvalue">曼哈顿</p>
									<p data-id="211100" class="cityvalue">马尼拉</p>
									<p data-id="360300" class="cityvalue">曼齐尼</p>
									<p data-id="211100" class="cityvalue">曼萨尼略</p>
									<p data-id="360300" class="cityvalue">马诺夸里</p>
									<p data-id="410400" class="cityvalue">毛里求斯</p>
									<p data-id="410900" class="cityvalue">毛梅里</p>
									<p data-id="350300" class="cityvalue">马普托</p>
									<p data-id="211100" class="cityvalue">马赛</p>
									<p data-id="360300" class="cityvalue">马塞卢</p>
									<p data-id="211100" class="cityvalue">马塞纳</p>
									<p data-id="360300" class="cityvalue">马塞约</p>
									<p data-id="410400" class="cityvalue">马萨葡萄园</p>
									<p data-id="410900" class="cityvalue">马萨特兰</p>
									<p data-id="350300" class="cityvalue">马什哈德</p>
									<p data-id="211100" class="cityvalue">马斯巴特</p>
									<p data-id="360300" class="cityvalue">马斯垂克</p>
									<p data-id="211100" class="cityvalue">马斯而肖尔斯</p>
									<p data-id="360300" class="cityvalue">马斯基根</p>
									<p data-id="410400" class="cityvalue">马斯喀特</p>
									<p data-id="410900" class="cityvalue">马斯特敦</p>
									<p data-id="350300" class="cityvalue">马塔兰</p>
									<p data-id="211100" class="cityvalue">马塔莫罗斯</p>
									<p data-id="360300" class="cityvalue">马亚奎兹</p>
									<p data-id="410400" class="cityvalue">马扎里沙里夫</p>
									<p data-id="211100" class="cityvalue">马朱罗</p>
									<p data-id="211100" class="cityvalue">梅德福</p>
									<p data-id="360300" class="cityvalue">梅迪辛哈特</p>
									<p data-id="410400" class="cityvalue">梅尔济丰</p>
									<p data-id="410900" class="cityvalue">梅兰瓜内</p>
									<p data-id="350300" class="cityvalue">梅里达</p>
									<p data-id="211100" class="cityvalue">梅利拉</p>
									<p data-id="360300" class="cityvalue">梅诺卡</p>
									<p data-id="410400" class="cityvalue">湄索</p>
									<p data-id="211100" class="cityvalue">梅兹南希</p>
									<p data-id="211100" class="cityvalue">门多萨</p>
									<p data-id="360300" class="cityvalue">蒙巴萨</p>
									<p data-id="410400" class="cityvalue">蒙彼利埃</p>
									<p data-id="410900" class="cityvalue">蒙德维的亚</p>
									<p data-id="350300" class="cityvalue">门格洛尔</p>
									<p data-id="211100" class="cityvalue">孟菲斯</p>
									<p data-id="360300" class="cityvalue">蒙哥马利</p>
									<p data-id="410400" class="cityvalue">蒙克顿</p>
									<p data-id="211100" class="cityvalue">蒙罗维亚</p>
									<p data-id="360300" class="cityvalue">孟买</p>
									<p data-id="211100" class="cityvalue">蒙斯特</p>
									<p data-id="360300" class="cityvalue">蒙特哥贝</p>
									<p data-id="410400" class="cityvalue">蒙特卡洛</p>
									<p data-id="410900" class="cityvalue">蒙特雷(美国)</p>
									<p data-id="350300" class="cityvalue">蒙特雷(墨西哥)</p>
									<p data-id="211100" class="cityvalue">蒙特利尔</p>
									<p data-id="360300" class="cityvalue">蒙特罗斯</p>
									<p data-id="410400" class="cityvalue">蒙特斯克拉劳斯</p>
									<p data-id="211100" class="cityvalue">门罗</p>
									<p data-id="360300" class="cityvalue">棉兰</p>
									<p data-id="211100" class="cityvalue">米德兰</p>
									<p data-id="360300" class="cityvalue">米蒂利尼</p>
									<p data-id="410400" class="cityvalue">米尔迪拉</p>
									<p data-id="410900" class="cityvalue">密尔沃基</p>
									<p data-id="350300" class="cityvalue">密尔沃基</p>
									<p data-id="211100" class="cityvalue">米卡萨拉</p>
									<p data-id="360300" class="cityvalue">米克诺斯</p>
									<p data-id="410400" class="cityvalue">米拉尔耶沃德</p>
									<p data-id="211100" class="cityvalue">米兰</p>
									<p data-id="360300" class="cityvalue">米拉务</p>
									<p data-id="211100" class="cityvalue">米里</p>
									<p data-id="211100" class="cityvalue">米卢斯</p>
									<p data-id="360300" class="cityvalue">米纳蒂特兰</p>
									<p data-id="410400" class="cityvalue">民都鲁</p>
									<p data-id="410900" class="cityvalue">名古屋</p>
									<p data-id="350300" class="cityvalue">明尼阿波利斯</p>
									<p data-id="211100" class="cityvalue">明斯克</p>
									<p data-id="360300" class="cityvalue">米苏拉</p>
									<p data-id="410400" class="cityvalue">米子</p>
									<p data-id="211100" class="cityvalue">莫阿布</p>
									<p data-id="360300" class="cityvalue">莫比尔</p>
									<p data-id="211100" class="cityvalue">墨尔本</p>
									<p data-id="211100" class="cityvalue">莫尔德</p>
									<p data-id="360300" class="cityvalue">摩尔曼斯科</p>
									<p data-id="410400" class="cityvalue">摩根敦</p>
									<p data-id="410900" class="cityvalue">摩加迪沙</p>
									<p data-id="350300" class="cityvalue">默克莱</p>
									<p data-id="211100" class="cityvalue">摩拉雅</p>
									<p data-id="360300" class="cityvalue">莫雷利亚</p>
									<p data-id="410400" class="cityvalue">莫里</p>
									<p data-id="211100" class="cityvalue">默里姆布拉</p>
									<p data-id="360300" class="cityvalue">莫林</p>
									<p data-id="211100" class="cityvalue">莫里斯敦</p>
									<p data-id="211100" class="cityvalue">莫罗尼</p>
									<p data-id="360300" class="cityvalue">莫娜斯提尔</p>
									<p data-id="410400" class="cityvalue">默塞德</p>
									<p data-id="410900" class="cityvalue">莫斯科</p>
									<p data-id="350300" class="cityvalue">莫斯塔尔</p>
									<p data-id="211100" class="cityvalue">默特尔比奇</p>
									<p data-id="360300" class="cityvalue">墨西哥城</p>
									<p data-id="410400" class="cityvalue">墨西卡利</p>
									<p data-id="211100" class="cityvalue">姆班达卡</p>
									<p data-id="360300" class="cityvalue">姆巴亚尔罗</p>
									<p data-id="211100" class="cityvalue">木尔坦</p>
									<p data-id="231000" class="cityvalue">穆尔西亚</p>
									<p data-id="340500" class="cityvalue">穆兰巴赫</p>
									<p data-id="510700" class="cityvalue">慕尼黑</p>
									<p data-id="511400" class="cityvalue">木浦</p>
									<p data-id="440900" class="cityvalue">慕斯</p>
									<p data-id="441400" class="cityvalue">姆万扎</p>
								</div>
								<div class="city-list"><span class="city-letter" id="N1">N</span>
									<p data-id="360300" class="cityvalue">那霸</p>
									<p data-id="410400" class="cityvalue">南邦</p>
									<p data-id="410900" class="cityvalue">纳比雷</p>
									<p data-id="350300" class="cityvalue">那不勒斯</p>
									<p data-id="211100" class="cityvalue">纳尔维克</p>
									<p data-id="360300" class="cityvalue">内夫谢希尔</p>
									<p data-id="410400" class="cityvalue">那格浦尔</p>
									<p data-id="211100" class="cityvalue">纳杰夫</p>
									<p data-id="360300" class="cityvalue">纳兰德拉</p>
									<p data-id="211100" class="cityvalue">那拉提瓦</p>
									<p data-id="360300" class="cityvalue">纳雷</p>
									<p data-id="410400" class="cityvalue">纳奈莫</p>
									<p data-id="410900" class="cityvalue">南安普敦</p>
									<p data-id="350300" class="cityvalue">南榜市</p>
									<p data-id="211100" class="cityvalue">南本德</p>
									<p data-id="360300" class="cityvalue">楠迪</p>
									<p data-id="410400" class="cityvalue">难府</p>
									<p data-id="211100" class="cityvalue">南纪白浜</p>
									<p data-id="360300" class="cityvalue">南萨哈林斯克</p>
									<p data-id="211100" class="cityvalue">南特</p>
									<p data-id="360300" class="cityvalue">纳皮尔-黑斯廷斯</p>
									<p data-id="410400" class="cityvalue">拿骚</p>
									<p data-id="410900" class="cityvalue">那什维尔</p>
									<p data-id="350300" class="cityvalue">纳塔尔</p>
									<p data-id="211100" class="cityvalue">纳图纳拉奈</p>
									<p data-id="360300" class="cityvalue">那牙</p>
									<p data-id="410400" class="cityvalue">纳组尔</p>
									<p data-id="211100" class="cityvalue">内比都</p>
									<p data-id="360300" class="cityvalue">内尔斯普雷特</p>
									<p data-id="211100" class="cityvalue">内基兰</p>
									<p data-id="360300" class="cityvalue">内罗毕</p>
									<p data-id="410400" class="cityvalue">内维斯</p>
									<p data-id="410900" class="cityvalue">内乌肯</p>
									<p data-id="350300" class="cityvalue">鸟取</p>
									<p data-id="211100" class="cityvalue">尼泊尔根杰</p>
									<p data-id="360300" class="cityvalue">尼尔森</p>
									<p data-id="410400" class="cityvalue">尼斯</p>
									<p data-id="211100" class="cityvalue">纽堡</p>
									<p data-id="360300" class="cityvalue">纽黑文</p>
									<p data-id="410400" class="cityvalue">纽基</p>
									<p data-id="410900" class="cityvalue">纽卡斯尔</p>
									<p data-id="350300" class="cityvalue">纽伦堡</p>
									<p data-id="211100" class="cityvalue">纽曼</p>
									<p data-id="360300" class="cityvalue">纽约</p>
									<p data-id="410400" class="cityvalue">尼亚加拉瀑布</p>
									<p data-id="320100" class="cityvalue">尼亚美</p>
									<p data-id="330200" class="cityvalue">努库斯</p>
									<p data-id="320600" class="cityvalue">努美阿</p>
									<p data-id="360100" class="cityvalue">诺丁汉</p>
									<p data-id="411300" class="cityvalue">诺尔雪平</p>
									<p data-id="350700" class="cityvalue">诺福克</p>
									<p data-id="350900" class="cityvalue">诺福克岛</p>
									<p data-id="350583" class="cityvalue">诺克斯维尔</p>
									<p data-id="542400" class="cityvalue">诺姆</p>
									<p data-id="450100" class="cityvalue">诺斯贝</p>
									<p data-id="511300" class="cityvalue">诺维奇</p>
									<p data-id="511000" class="cityvalue">努瓦克肖特</p>
									<p data-id="533300" class="cityvalue">女满别</p>
								</div>
								<div class="city-list"><span class="city-letter" id="O1">O</span>

									<p data-id="350583" class="cityvalue">欧布雷贡城</p>
									<p data-id="542400" class="cityvalue">欧登塞</p>
									<p data-id="450100" class="cityvalue">欧德萨</p>
									<p data-id="511300" class="cityvalue">欧克莱尔</p>
									<p data-id="511000" class="cityvalue">欧里亚克</p>
									<p data-id="533300" class="cityvalue">欧文斯伯勒</p>
								</div>
								<div class="city-list"><span class="city-letter" id="P1">P</span>
									<p data-id="410900" class="cityvalue">帕迪尤卡</p>
									<p data-id="350300" class="cityvalue">帕尔马</p>
									<p data-id="211100" class="cityvalue">帕府</p>
									<p data-id="360300" class="cityvalue">帕福斯</p>
									<p data-id="410400" class="cityvalue">帕格尔布尔</p>
									<p data-id="410900" class="cityvalue">帕果帕果</p>
									<p data-id="350300" class="cityvalue">帕加迪安</p>
									<p data-id="510400" class="cityvalue">帕克斯</p>
									<p data-id="530800" class="cityvalue">帕克斯堡</p>
									<p data-id="211100" class="cityvalue">帕拉博鲁瓦</p>
									<p data-id="360300" class="cityvalue">帕拉布尔舍</p>
									<p data-id="410400" class="cityvalue">帕拉马里博</p>
									<p data-id="211100" class="cityvalue">帕朗卡巴亚</p>
									<p data-id="360300" class="cityvalue">帕拉帕拉乌姆海滩</p>
									<p data-id="410400" class="cityvalue">帕卢</p>
									<p data-id="410900" class="cityvalue">帕伦克</p>
									<p data-id="350300" class="cityvalue">帕罗</p>
									<p data-id="510400" class="cityvalue">帕姆斯普林斯</p>
									<p data-id="530800" class="cityvalue">帕纳马城</p>
									<p data-id="211100" class="cityvalue">潘普洛纳</p>
									<p data-id="360300" class="cityvalue">潘泰莱里亚</p>
									<p data-id="410400" class="cityvalue">潘提顿</p>
									<p data-id="211100" class="cityvalue">帕皮堤</p>
									<p data-id="360300" class="cityvalue">帕萨迪纳</p>
									<p data-id="410400" class="cityvalue">帕斯科</p>
									<p data-id="410900" class="cityvalue">帕特泊恩</p>
									<p data-id="350300" class="cityvalue">帕特雷</p>
									<p data-id="510400" class="cityvalue">帕伊拉瓦</p>
									<p data-id="530800" class="cityvalue">佩皮尼扬</p>
									<p data-id="211100" class="cityvalue">佩奇</p>
									<p data-id="360300" class="cityvalue">佩斯卡拉</p>
									<p data-id="410400" class="cityvalue">彭巴</p>
									<p data-id="410900" class="cityvalue">彭德尔顿</p>
									<p data-id="350300" class="cityvalue">澎湖列岛</p>
									<p data-id="510400" class="cityvalue">朋库卢</p>
									<p data-id="530800" class="cityvalue">蓬塞</p>
									<p data-id="211100" class="cityvalue">彭萨科拉</p>
									<p data-id="360300" class="cityvalue">彭世洛</p>
									<p data-id="410400" class="cityvalue">蓬塔德尔加达</p>
									<p data-id="410900" class="cityvalue">蓬塔戈尔达</p>
									<p data-id="350300" class="cityvalue">蓬塔卡纳</p>
									<p data-id="510400" class="cityvalue">皮奥里亚</p>
									<p data-id="530800" class="cityvalue">皮尔</p>
									<p data-id="211100" class="cityvalue">皮克岛</p>
									<p data-id="360300" class="cityvalue">平壤</p>
									<p data-id="410400" class="cityvalue">皮特尔角城</p>
									<p data-id="410900" class="cityvalue">匹兹堡</p>
									<p data-id="350300" class="cityvalue">珀勒德布尔</p>
									<p data-id="510400" class="cityvalue">珀斯</p>
									<p data-id="530800" class="cityvalue">普茨茅斯</p>
									<p data-id="211100" class="cityvalue">普尔曼</p>
									<p data-id="360300" class="cityvalue">普吉岛</p>
									<p data-id="410400" class="cityvalue">普杰</p>
									<p data-id="410900" class="cityvalue">普拉</p>
									<p data-id="350300" class="cityvalue">普拉茨堡</p>
									<p data-id="510400" class="cityvalue">普拉兰岛</p>
									<p data-id="530800" class="cityvalue">普拉塔港</p>
									<p data-id="211100" class="cityvalue">普雷斯科特</p>
									<p data-id="360300" class="cityvalue">普雷图河畔圣若泽</p>
									<p data-id="410400" class="cityvalue">普雷韦扎莱夫卡斯</p>
									<p data-id="410900" class="cityvalue">普林塞萨港</p>
									<p data-id="350300" class="cityvalue">普里什蒂纳</p>
									<p data-id="510400" class="cityvalue">普罗瑟派恩</p>
									<p data-id="530800" class="cityvalue">普罗维登斯</p>
									<p data-id="211100" class="cityvalue">普罗维登夏莱斯</p>
									<p data-id="360300" class="cityvalue">普罗温斯敦</p>
									<p data-id="410400" class="cityvalue">普罗沃</p>
									<p data-id="410900" class="cityvalue">蒲那</p>
									<p data-id="350300" class="cityvalue">普瓦捷</p>
									<p data-id="510400" class="cityvalue">普韦布洛</p>
									<p data-id="530800" class="cityvalue">普项</p>
									<p data-id="620800" class="cityvalue">楠普拉</p>
								</div>
								<div class="city-list"><span class="city-letter" id="Q1">Q</span>
									<p data-id="" class="cityvalue">乔巴山</p>
									<p data-id="" class="cityvalue">乔哈特</p>
									<p data-id="" class="cityvalue">乔普林</p>
									<p data-id="" class="cityvalue">乔治城</p>
									<p data-id="" class="cityvalue">乔治敦</p>
									<p data-id="" class="cityvalue">乔治王子城</p>
									<p data-id="130300" class="cityvalue">七岛港</p>
									<p data-id="230200" class="cityvalue">切列波维茨</p>
									<p data-id="230900" class="cityvalue">切图马尔</p>
									<p data-id="350500" class="cityvalue">奇科</p>
									<p data-id="429005" class="cityvalue">乞力马</p>
									<p data-id="370200" class="cityvalue">奇穆肯特</p>
									<p data-id="330800" class="cityvalue">清莱</p>
									<p data-id="441800" class="cityvalue">清迈</p>
									<p data-id="522700" class="cityvalue">青蓬</p>
									<p data-id="450700" class="cityvalue">青森</p>
									<p data-id="530300" class="cityvalue">青州</p>
									<p data-id="522300" class="cityvalue">芹苴</p>
									<p data-id="621000" class="cityvalue">秋明</p>
									<p data-id="522600" class="cityvalue">秋田</p>
									<p data-id="469002" class="cityvalue">奇瓦瓦</p>
									<p data-id="469036" class="cityvalue">群山</p>
								</div>
								<div class="city-list"><span class="city-letter" id="R1">R</span>
									<p data-id="" class="cityvalue">热那亚</p>
									<p data-id="" class="cityvalue">热舒夫</p>
									<p data-id="320682" class="cityvalue">日内瓦</p>
									<p data-id="371082" class="cityvalue">日惹</p>
									<p data-id="371100" class="cityvalue">荣市</p>
									<p data-id="542301" class="cityvalue">若昂佩索阿</p>
								</div>
								<div class="city-list"><span class="city-letter" id="S1">S</span>
									<p data-id="" class="cityvalue">萨比哈哥克塞恩</p>
									<p data-id="" class="cityvalue">萨德伯里</p>
									<p data-id="" class="cityvalue">萨尔</p>
									<p data-id="" class="cityvalue">萨尔布吕肯</p>
									<p data-id="" class="cityvalue">萨尔茨堡</p>
									<p data-id="" class="cityvalue">萨尔蒂洛</p>
									<p data-id="" class="cityvalue">萨尔瓦多</p>
									<p data-id="" class="cityvalue">萨凡纳</p>
									<p data-id="" class="cityvalue">萨克勒布</p>
									<p data-id="" class="cityvalue">塞班</p>
									<p data-id="" class="cityvalue">赛杜纳</p>
									<p data-id="" class="cityvalue">塞拉莱</p>
									<p data-id="" class="cityvalue">塞米伊</p>
									<p data-id="" class="cityvalue">塞维利亚</p>
									<p data-id="" class="cityvalue">萨基诺</p>
									<p data-id="" class="cityvalue">萨卡特卡斯</p>
									<p data-id="" class="cityvalue">萨克拉门托</p>
									<p data-id="" class="cityvalue">撒拉纳克莱克</p>
									<p data-id="" class="cityvalue">萨拉热窝</p>
									<p data-id="" class="cityvalue">萨拉索塔</p>
									<p data-id="" class="cityvalue">萨利纳</p>
									<p data-id="" class="cityvalue">萨利乌尔法</p>
									<p data-id="" class="cityvalue">萨洛尼奇</p>
									<p data-id="" class="cityvalue">萨马拉</p>
									<p data-id="" class="cityvalue">萨马纳</p>
									<p data-id="" class="cityvalue">撒莫斯</p>
									<p data-id="" class="cityvalue">萨姆松</p>
									<p data-id="" class="cityvalue">萨那</p>
									<p data-id="" class="cityvalue">三宝拢</p>
									<p data-id="" class="cityvalue">三宝颜</p>
									<p data-id="" class="cityvalue">桑阿桑阿</p>
									<p data-id="" class="cityvalue">桑德贝</p>
									<p data-id="" class="cityvalue">桑给巴尔</p>
									<p data-id="" class="cityvalue">桑坦德</p>
									<p data-id="" class="cityvalue">桑托斯将军城</p>
									<p data-id="" class="cityvalue">三泽</p>
									<p data-id="" class="cityvalue">萨斯卡通</p>
									<p data-id="" class="cityvalue">萨翁林纳</p>
									<p data-id="" class="cityvalue">瑟梅拉</p>
									<p data-id="" class="cityvalue">森巴瓦</p>
									<p data-id="" class="cityvalue">森讷堡</p>
									<p data-id="" class="cityvalue">沙德伦</p>
									<p data-id="" class="cityvalue">沙点市</p>
									<p data-id="" class="cityvalue">沙功那空</p>
									<p data-id="" class="cityvalue">沙鲁拉</p>
									<p data-id="" class="cityvalue">沙姆沙伊赫湾</p>
									<p data-id="" class="cityvalue">山打根</p>
									<p data-id="" class="cityvalue">山达霍德</p>
									<p data-id="" class="cityvalue">尚贝里</p>
									<p data-id="" class="cityvalue">尚佩恩</p>
									<p data-id="" class="cityvalue">山形</p>
									<p data-id="" class="cityvalue">沙利文弯</p>
									<p data-id="" class="cityvalue">沙迦</p>
									<p data-id="" class="cityvalue">设得兰群岛</p>
									<p data-id="" class="cityvalue">设拉子</p>
									<p data-id="" class="cityvalue">圣保罗岛</p>
									<p data-id="" class="cityvalue">圣艾蒂安</p>
									<p data-id="" class="cityvalue">圣安东尼奥</p>
									<p data-id="" class="cityvalue">圣巴巴拉</p>
									<p data-id="" class="cityvalue">圣保罗</p>
									<p data-id="" class="cityvalue">圣彼得堡</p>
									<p data-id="" class="cityvalue">圣彼德斯堡</p>
									<p data-id="" class="cityvalue">圣达菲</p>
									<p data-id="" class="cityvalue">圣诞岛</p>
									<p data-id="" class="cityvalue">圣丹尼斯</p>
									<p data-id="" class="cityvalue">圣迭戈</p>
									<p data-id="" class="cityvalue">圣地亚哥(智利)</p>
									<p data-id="" class="cityvalue">圣地亚哥</p>
									<p data-id="" class="cityvalue">圣地亚哥德</p>
									<p data-id="" class="cityvalue">圣多明戈</p>
									<p data-id="" class="cityvalue">圣港</p>
									<p data-id="" class="cityvalue">圣何塞(美国)</p>
									<p data-id="" class="cityvalue">圣和塞</p>
									<p data-id="" class="cityvalue">圣何塞(哥斯达黎加)</p>
									<p data-id="" class="cityvalue">圣荷西/p>
										<p data-id="" class="cityvalue">圣胡安</p>
										<p data-id="" class="cityvalue">圣基茨</p>
										<p data-id="" class="cityvalue">圣卡洛斯.德巴里洛切</p>
										<p data-id="" class="cityvalue">圣克劳德</p>
										<p data-id="" class="cityvalue">圣克鲁斯</p>
										<p data-id="" class="cityvalue">圣克鲁斯</p>
										<p data-id="" class="cityvalue">圣克鲁斯岛</p>
										<p data-id="" class="cityvalue">圣克鲁斯画图考</p>
										<p data-id="" class="cityvalue">圣罗莎</p>
										<p data-id="" class="cityvalue">圣卢西亚</p>
										<p data-id="" class="cityvalue">圣路易斯</p>
										<p data-id="" class="cityvalue">圣路易斯(美国)</p>
										<p data-id="" class="cityvalue">圣路易斯-奥比斯波</p>
										<p data-id="" class="cityvalue">圣路易斯波托西</p>
										<p data-id="" class="cityvalue">圣马丽亚</p>
										<p data-id="" class="cityvalue">圣马滕</p>
										<p data-id="" class="cityvalue">圣佩德罗苏拉</p>
										<p data-id="" class="cityvalue">圣皮埃尔</p>
										<p data-id="" class="cityvalue">圣乔治</p>
										<p data-id="" class="cityvalue">圣萨尔瓦多</p>
										<p data-id="" class="cityvalue">圣萨尔瓦卡岛</p>
										<p data-id="" class="cityvalue">圣塞瓦斯蒂安</p>
										<p data-id="" class="cityvalue">圣塔安那</p>
										<p data-id="" class="cityvalue">圣塔伦</p>
										<p data-id="" class="cityvalue">圣托马斯</p>
										<p data-id="" class="cityvalue">圣维森特</p>
										<p data-id="" class="cityvalue">圣约翰</p>
										<p data-id="" class="cityvalue">圣约翰堡</p>
										<p data-id="" class="cityvalue">圣约翰斯</p>
										<p data-id="" class="cityvalue">神户</p>
										<p data-id="" class="cityvalue">圣乔治岛</p>
										<p data-id="" class="cityvalue">史凯威</p>
										<p data-id="" class="cityvalue">什里夫波特</p>
										<p data-id="" class="cityvalue">史密斯堡</p>
										<p data-id="" class="cityvalue">什切青</p>
										<p data-id="" class="cityvalue">石垣</p>
										<p data-id="" class="cityvalue">首尔</p>
										<p data-id="" class="cityvalue">守护神</p>
										<p data-id="" class="cityvalue">顺化</p>
										<p data-id="" class="cityvalue">斯波坎</p>
										<p data-id="" class="cityvalue">斯丹顿</p>
										<p data-id="" class="cityvalue">斯德哥尔摩</p>
										<p data-id="" class="cityvalue">斯基亚索斯</p>
										<p data-id="" class="cityvalue">斯科茨布拉夫</p>
										<p data-id="" class="cityvalue">斯科普里</p>
										<p data-id="" class="cityvalue">斯库库扎</p>
										<p data-id="" class="cityvalue">斯里巴加湾市</p>
										<p data-id="" class="cityvalue">斯利那加</p>
										<p data-id="" class="cityvalue">司马威</p>
										<p data-id="" class="cityvalue">斯普林菲尔德</p>
										<p data-id="" class="cityvalue">斯普林菲尔德(布兰森地区)</p>
										<p data-id="" class="cityvalue">斯普利特</p>
										<p data-id="" class="cityvalue">泗水</p>
										<p data-id="" class="cityvalue">斯塔夫罗波尔</p>
										<p data-id="" class="cityvalue">斯泰特科利奇</p>
										<p data-id="220300" class="cityvalue">斯塔万格</p>
										<p data-id="231200" class="cityvalue">斯特拉斯堡</p>
										<p data-id="220700" class="cityvalue">斯图加特</p>
										<p data-id="320500" class="cityvalue">斯托克顿</p>
										<p data-id="310100" class="cityvalue">斯托克马克内斯</p>
										<p data-id="321300" class="cityvalue">斯托诺韦</p>
										<p data-id="330600" class="cityvalue">斯瓦尔巴</p>
										<p data-id="140600" class="cityvalue">泗务</p>
										<p data-id="230500" class="cityvalue">松妮亚</p>
										<p data-id="210100" class="cityvalue">松山</p>
										<p data-id="330682" class="cityvalue">松兹瓦尔</p>
										<p data-id="130100" class="cityvalue">苏城</p>
										<p data-id="440500" class="cityvalue">苏丹港</p>
										<p data-id="350400" class="cityvalue">苏尔古特</p>
										<p data-id="429021" class="cityvalue">苏福尔斯</p>
										<p data-id="361100" class="cityvalue">素可泰</p>
										<p data-id="411400" class="cityvalue">苏库尔</p>
										<p data-id="421300" class="cityvalue">苏莱曼尼亚</p>
										<p data-id="341300" class="cityvalue">苏拉特古加拉特</p>
										<p data-id="411200" class="cityvalue">苏里高</p>
										<p data-id="420300" class="cityvalue">苏黎世</p>
										<p data-id="440300" class="cityvalue">苏梅岛</p>
										<p data-id="430500" class="cityvalue">索非亚</p>
										<p data-id="440200" class="cityvalue">索科托</p>
										<p data-id="441500" class="cityvalue">梭罗</p>
										<p data-id="510900" class="cityvalue">索荣</p>
										<p data-id="611000" class="cityvalue">索伟拉</p>
										<p data-id="542200" class="cityvalue">苏珊玛利</p>
										<p data-id="460200" class="cityvalue">苏圣玛丽</p>
										<p data-id="640200" class="cityvalue">宿雾</p>
										<p data-id="659001" class="cityvalue">俾斯麦</p>
								</div>
								<div class="city-list"><span class="city-letter" id="T1">T</span>
									<p data-id="" class="cityvalue">塔比拉兰</p>
									<p data-id="" class="cityvalue">塔布科</p>
									<p data-id="" class="cityvalue">塔尔萨</p>
									<p data-id="" class="cityvalue">塔尔图</p>
									<p data-id="" class="cityvalue">台北</p>
									<p data-id="" class="cityvalue">泰基尔达</p>
									<p data-id="" class="cityvalue">台拉登</p>
									<p data-id="" class="cityvalue">泰勒</p>
									<p data-id="" class="cityvalue">泰里岛因纳赫布里德斯</p>
									<p data-id="" class="cityvalue">台南</p>
									<p data-id="" class="cityvalue">太特</p>
									<p data-id="" class="cityvalue">台中</p>
									<p data-id="" class="cityvalue">太子港</p>
									<p data-id="" class="cityvalue">塔克岛</p>
									<p data-id="" class="cityvalue">塔克洛班</p>
									<p data-id="" class="cityvalue">塔拉哈西</p>
									<p data-id="" class="cityvalue">塔里</p>
									<p data-id="" class="cityvalue">塔林</p>
									<p data-id="" class="cityvalue">塔姆沃恩</p>
									<p data-id="" class="cityvalue">塔拿那利弗</p>
									<p data-id="" class="cityvalue">坦博拉卡</p>
									<p data-id="" class="cityvalue">努库阿洛法</p>
									<p data-id="" class="cityvalue">唐克斯特</p>
									<p data-id="" class="cityvalue">汤斯维尔</p>
									<p data-id="" class="cityvalue">坦帕</p>
									<p data-id="" class="cityvalue">坦佩雷</p>
									<p data-id="" class="cityvalue">坦皮科</p>
									<p data-id="" class="cityvalue">陶波</p>
									<p data-id="" class="cityvalue">陶朗阿加</p>
									<p data-id="" class="cityvalue">塔帕丘拉</p>
									<p data-id="" class="cityvalue">塔什干</p>
									<p data-id="" class="cityvalue">塔斯卡卢萨</p>
									<p data-id="" class="cityvalue">塔威塔威</p>
									<p data-id="" class="cityvalue">塔伊夫</p>
									<p data-id="" class="cityvalue">特尔纳特岛</p>
									<p data-id="" class="cityvalue">特古西加尔巴</p>
									<p data-id="" class="cityvalue">特克萨卡纳</p>
									<p data-id="" class="cityvalue">特拉布宗</p>
									<p data-id="" class="cityvalue">特拉弗斯城</p>
									<p data-id="" class="cityvalue">特拉帕尼</p>
									<p data-id="" class="cityvalue">特拉维夫</p>
									<p data-id="" class="cityvalue">特柳莱德</p>
									<p data-id="" class="cityvalue">特隆赫姆</p>
									<p data-id="" class="cityvalue">特伦顿</p>
									<p data-id="" class="cityvalue">特罗姆瑟</p>
									<p data-id="" class="cityvalue">特鲁希略</p>
									<p data-id="" class="cityvalue">特内里费</p>
									<p data-id="" class="cityvalue">特皮克</p>
									<p data-id="" class="cityvalue">特温福尔斯</p>
									<p data-id="" class="cityvalue">提华纳</p>
									<p data-id="" class="cityvalue">提拉纳</p>
									<p data-id="" class="cityvalue">提林库特</p>
									<p data-id="" class="cityvalue">提鲁帕提</p>
									<p data-id="140100" class="cityvalue">提鲁瓦南萨普拉姆</p>
									<p data-id="211200" class="cityvalue">桐艾府</p>
									<p data-id="220500" class="cityvalue">图尔伯德</p>
									<p data-id="130200" class="cityvalue">图尔库</p>
									<p data-id="320585" class="cityvalue">图尔斯</p>
									<p data-id="120100" class="cityvalue">图芬诺</p>
									<p data-id="321200" class="cityvalue">土格加劳</p>
									<p data-id="150500" class="cityvalue">图莱夫</p>
									<p data-id="331000" class="cityvalue">土伦</p>
									<p data-id="370900" class="cityvalue">图卢兹</p>
									<p data-id="429006" class="cityvalue">突尼斯</p>
									<p data-id="340700" class="cityvalue">托卡特</p>
									<p data-id="522201" class="cityvalue">托莱多</p>
									<p data-id="469026" class="cityvalue">托雷翁</p>
									<p data-id="610200" class="cityvalue">托卢卡</p>
									<p data-id="620500" class="cityvalue">托木斯克</p>
									<p data-id="654200" class="cityvalue">图森</p>
									<p data-id="659003" class="cityvalue">图斯特拉-古铁雷斯</p>
									<p data-id="652100" class="cityvalue">吐丕洛</p>
									<p data-id="710100" class="cityvalue">楠塔基特岛</p>
								</div>
								<div class="city-list"><span class="city-letter" id="W1">W</span>
									<p data-id="" class="cityvalue">瓦代兹</p>
									<p data-id="" class="cityvalue">瓦多达拉</p>
									<p data-id="" class="cityvalue">瓦尔纳</p>
									<p data-id="" class="cityvalue">瓦哈卡</p>
									<p data-id="" class="cityvalue">瓦加杜古</p>
									<p data-id="" class="cityvalue">瓦卡塔尼</p>
									<p data-id="" class="cityvalue">瓦拉德</p>
									<p data-id="" class="cityvalue">瓦拉瓦</p>
									<p data-id="" class="cityvalue">瓦伦西亚</p>
									<p data-id="" class="cityvalue">瓦纳卡</p>
									<p data-id="" class="cityvalue">旺阿雷</p>
									<p data-id="" class="cityvalue">旺阿努伊</p>
									<p data-id="" class="cityvalue">万隆</p>
									<p data-id="" class="cityvalue">万伦</p>
									<p data-id="" class="cityvalue">万象</p>
									<p data-id="" class="cityvalue">万鸦老</p>
									<p data-id="" class="cityvalue">瓦萨</p>
									<p data-id="" class="cityvalue">瓦英阿普</p>
									<p data-id="" class="cityvalue">危地马拉城</p>
									<p data-id="" class="cityvalue">维多利亚</p>
									<p data-id="" class="cityvalue">维多利亚大瀑布</p>
									<p data-id="" class="cityvalue">韦恩堡</p>
									<p data-id="" class="cityvalue">韦尔</p>
									<p data-id="" class="cityvalue">威尔克斯巴里</p>
									<p data-id="" class="cityvalue">威尔明顿</p>
									<p data-id="" class="cityvalue">威尔明顿(美国)</p>
									<p data-id="" class="cityvalue">维尔纽斯</p>
									<p data-id="" class="cityvalue">威尔诺</p>
									<p data-id="" class="cityvalue">维哥</p>
									<p data-id="" class="cityvalue">韦吉</p>
									<p data-id="" class="cityvalue">韦科</p>
									<p data-id="" class="cityvalue">威克</p>
									<p data-id="" class="cityvalue">维港湾</p>
									<p data-id="" class="cityvalue">韦拉克鲁斯</p>
									<p data-id="" class="cityvalue">威廉斯波特</p>
									<p data-id="" class="cityvalue">威利斯顿</p>
									<p data-id="" class="cityvalue">维罗纳</p>
									<p data-id="" class="cityvalue">威尼斯</p>
									<p data-id="" class="cityvalue">威奇托市</p>
									<p data-id="" class="cityvalue">维塞利亚</p>
									<p data-id="" class="cityvalue">维萨卡帕特南</p>
									<p data-id="" class="cityvalue">蔚山</p>
									<p data-id="" class="cityvalue">维斯堡</p>
									<p data-id="" class="cityvalue">韦斯特兰</p>
									<p data-id="" class="cityvalue">维斯特切斯特郡</p>
									<p data-id="" class="cityvalue">维也纳</p>
									<p data-id="" class="cityvalue">纹别</p>
									<p data-id="" class="cityvalue">温德和克</p>
									<p data-id="" class="cityvalue">温顿</p>
									<p data-id="" class="cityvalue">温哥华</p>
									<p data-id="" class="cityvalue">文纳齐</p>
									<p data-id="" class="cityvalue">温尼伯</p>
									<p data-id="" class="cityvalue">温泉</p>
									<p data-id="" class="cityvalue">温莎</p>
									<p data-id="WGA" class="cityvalue">沃加沃加</p>
									<p data-id="WRL" class="cityvalue">沃兰</p>
									<p data-id="VOZ" class="cityvalue">沃洛涅日</p>
									<p data-id="AUW" class="cityvalue">沃索</p>
									<p data-id="YOW" class="cityvalue">渥太华</p>
									<p data-id="ART" class="cityvalue">沃特敦</p>
									<p data-id="WAT" class="cityvalue">沃特福德</p>
									<p data-id="MWX" class="cityvalue">务安</p>
									<p data-id="UDI" class="cityvalue">乌贝蓝迪亚</p>
									<p data-id="UDR" class="cityvalue">乌代布尔</p>
									<p data-id="BXU" class="cityvalue">武端</p>
									<p data-id="UGC" class="cityvalue">乌尔根奇</p>
									<p data-id="UFA" class="cityvalue">乌法</p>
									<p data-id="OUD" class="cityvalue">乌杰达</p>
									<p data-id="URA" class="cityvalue">乌拉尔斯克</p>
									<p data-id="ULN" class="cityvalue">乌兰巴托</p>
									<p data-id="UUD" class="cityvalue">乌兰乌德</p>
									<p data-id="BFV" class="cityvalue">武里南</p>
									<p data-id="ULY" class="cityvalue">乌里扬诺夫斯克</p>
									<p data-id="UTH" class="cityvalue">伍隆</p>
									<p data-id="UPN" class="cityvalue">乌卢阿潘</p>
									<p data-id="YTT" class="cityvalue">乌姆塔塔</p>
									<p data-id="UNK" class="cityvalue">乌纳拉克里</p>
									<p data-id="UPG" class="cityvalue">乌戎潘当</p>
									<p data-id="USH" class="cityvalue">乌斯怀亚</p>
									<p data-id="UKK" class="cityvalue">乌斯季卡缅诺戈尔斯克</p>
									<p data-id="ORH" class="cityvalue">伍斯特</p>
									<p data-id="UTP" class="cityvalue">五塔帕奥</p>
									<p data-id="UBP" class="cityvalue">乌汶(乌汶叻差他尼)</p>
									<p data-id="UYU" class="cityvalue">乌尤尼</p>
								</div>
								<div class="city-list"><span class="city-letter" id="X1">X</span>
									<p data-id="LBJ" class="cityvalue">下拉布安</p>
									<p data-id="CHO" class="cityvalue">夏洛茨维尔</p>
									<p data-id="CLT" class="cityvalue">夏洛特</p>
									<p data-id="YYG" class="cityvalue">夏洛特敦</p>
									<p data-id="DAD" class="cityvalue">岘港</p>
									<p data-id="SNN" class="cityvalue">香农</p>
									<p data-id="YNF" class="cityvalue">襄阳</p>
									<p data-id="REP" class="cityvalue">暹粒</p>
									<p data-id="SDJ" class="cityvalue">仙台</p>
									<p data-id="GOJ" class="cityvalue">下诺夫哥罗德</p>
									<p data-id="SOW" class="cityvalue">肖罗</p>
									<p data-id="LIT" class="cityvalue">小石城</p>
									<p data-id="KMQ" class="cityvalue">小松</p>
									<p data-id="TVF" class="cityvalue">小偷河瀑布市</p>
									<p data-id="HNL" class="cityvalue">夏威夷.火奴鲁鲁</p>
									<p data-id="CYS" class="cityvalue">夏延</p>
									<p data-id="POS" class="cityvalue">西班牙港</p>
									<p data-id="HIB" class="cityvalue">西宾</p>
									<p data-id="SBZ" class="cityvalue">锡比乌</p>
									<p data-id="CDC" class="cityvalue">锡达城</p>
									<p data-id="CID" class="cityvalue">锡达拉皮兹</p>
									<p data-id="SFT" class="cityvalue">谢莱夫特奥</p>
									<p data-id="SHR" class="cityvalue">谢里丹</p>
									<p data-id="HHH" class="cityvalue">希尔顿黑德</p>
									<p data-id="ZYL" class="cityvalue">锡尔赫特</p>
									<p data-id="KKN" class="cityvalue">希尔克内斯</p>
									<p data-id="SXZ" class="cityvalue">锡尔特</p>
									<p data-id="JKH" class="cityvalue">希俄斯</p>
									<p data-id="WSZ" class="cityvalue">西港</p>
									<p data-id="KOS" class="cityvalue">西哈努克城</p>
									<p data-id="WYS" class="cityvalue">西黄石</p>
									<p data-id="SIT" class="cityvalue">夕拉</p>
									<p data-id="JTR" class="cityvalue">锡拉</p>
									<p data-id="SYR" class="cityvalue">锡拉丘兹</p>
									<p data-id="ITO" class="cityvalue">希洛</p>
									<p data-id="MSY" class="cityvalue">新奥尔良</p>
									<p data-id="EWB" class="cityvalue">新贝德福德</p>
									<p data-id="" class="cityvalue">新伯尔尼</p>
									<p data-id="SYD" class="cityvalue">悉尼(澳大利亚)</p>
									<p data-id="YQY" class="cityvalue">悉尼(加拿大)</p>
									<p data-id="SIN" class="cityvalue">新加坡</p>
									<p data-id="NLD" class="cityvalue">新拉莱多</p>
									<p data-id="EUM" class="cityvalue">新明斯特</p>
									<p data-id="NPL" class="cityvalue">新普利茅茨</p>
									<p data-id="JHB" class="cityvalue">新山</p>
									<p data-id="OVB" class="cityvalue">新西伯利亚</p>
									<p data-id="KIJ" class="cityvalue">新泻</p>
									<p data-id="CVG" class="cityvalue">辛辛那提</p>
									<p data-id="KMJ" class="cityvalue">熊本</p>
									<p data-id="HGB" class="cityvalue">休恩登</p>
									<p data-id="HON" class="cityvalue">休伦</p>
									<p data-id="HOU" class="cityvalue">休斯敦</p>
									<p data-id="VAS" class="cityvalue">锡瓦斯</p>
									<p data-id="SKT" class="cityvalue">锡亚尔科特</p>
									<p data-id="SEA" class="cityvalue">西雅图</p>
									<p data-id="PBI" class="cityvalue">西棕榈滩</p>
									<p data-id="TBB" class="cityvalue">宣化</p>
									<p data-id="AKJ" class="cityvalue">旭川</p>
								</div>
								<div class="city-list"><span class="city-letter" id="Y1">Y</span>
									<p data-id="ADD" class="cityvalue">亚的斯亚贝巴</p>
									<p data-id="ATH" class="cityvalue">雅典</p>
									<p data-id="JKT" class="cityvalue">雅加达</p>
									<p data-id="YKM" class="cityvalue">雅吉瓦</p>
									<p data-id="AQJ" class="cityvalue">亚喀巴</p>
									<p data-id="YKS" class="cityvalue">雅库茨克</p>
									<p data-id="ALY" class="cityvalue">压力山大</p>
									<p data-id="AOR" class="cityvalue">亚罗士打</p>
									<p data-id="YNB" class="cityvalue">延布</p>
									<p data-id="NGA" class="cityvalue">扬</p>
									<p data-id="RGN" class="cityvalue">仰光</p>
									<p data-id="YNG" class="cityvalue">扬斯敦</p>
									<p data-id="SLC" class="cityvalue">盐湖城</p>
									<p data-id="ASJ" class="cityvalue">奄美大岛</p>
									<p data-id="YAP" class="cityvalue">雅浦群岛</p>
									<p data-id="ASU" class="cityvalue">亚松森</p>
									<p data-id="ATL" class="cityvalue">亚特兰大</p>
									<p data-id="AVN" class="cityvalue">亚维侬</p>
									<p data-id="YAO" class="cityvalue">雅温得</p>
									<p data-id="IAS" class="cityvalue">雅西</p>
									<p data-id="NHA" class="cityvalue">芽庄</p>
									<p data-id="SVX" class="cityvalue">叶卡捷琳堡</p>
									<p data-id="YZF" class="cityvalue">耶洛奈夫</p>
									<p data-id="IKT" class="cityvalue">伊尔库茨克</p>
									<p data-id="IVL" class="cityvalue">伊伐洛</p>
									<p data-id="IGU" class="cityvalue">伊瓜苏福尔斯</p>
									<p data-id="IBZ" class="cityvalue">伊比萨</p>
									<p data-id="ERI" class="cityvalue">伊利</p>
									<p data-id="PLZ" class="cityvalue">伊丽莎白港</p>
									<p data-id="ILO" class="cityvalue">伊洛伊洛</p>
									<p data-id="SVC" class="cityvalue">银城</p>
									<p data-id="IND" class="cityvalue">印第安纳波利斯</p>
									<p data-id="IDR" class="cityvalue">印多尔</p>
									<p data-id="IVC" class="cityvalue">因佛卡吉尔</p>
									<p data-id="INV" class="cityvalue">因佛內斯</p>
									<p data-id="ENE" class="cityvalue">英德</p>
									<p data-id="IMF" class="cityvalue">英帕尔</p>
									<p data-id="IPL" class="cityvalue">因皮里尔</p>
									<p data-id="INN" class="cityvalue">因斯布鲁克</p>
									<p data-id="ZIH" class="cityvalue">印坦巴</p>
									<p data-id="IFN" class="cityvalue">伊斯法罕</p>
									<p data-id="ITH" class="cityvalue">伊萨卡</p>
									<p data-id="ISB" class="cityvalue">伊斯兰堡</p>
									<p data-id="IZM" class="cityvalue">伊兹密尔</p>
									<p data-id="EUG" class="cityvalue">尤金</p>
									<p data-id="YUM" class="cityvalue">尤马</p>
									<p data-id="UBJ" class="cityvalue">宇部</p>
									<p data-id="JNB" class="cityvalue">约翰内斯堡</p>
									<p data-id="JYV" class="cityvalue">于伐斯居拉</p>
									<p data-id="UME" class="cityvalue">于默奥</p>
								</div>
								<div class="city-list"><span class="city-letter" id="Z1">Z</span>
									<p data-id="DHA" class="cityvalue">宰赫兰</p>
									<p data-id="JER" class="cityvalue">泽西</p>
									<p data-id="ZAD" class="cityvalue">扎达尔</p>
									<p data-id="SPK" class="cityvalue">札幌</p>
									<p data-id="JAI" class="cityvalue">斋普尔</p>
									<p data-id="ZTH" class="cityvalue">扎金索斯</p>
									<p data-id="DJB" class="cityvalue">占碑</p>
									<p data-id="GIB" class="cityvalue">直布罗陀</p>
									<p data-id="CHI" class="cityvalue">芝加哥</p>
									<p data-id="WKJ" class="cityvalue">稚内</p>
									<p data-id="SHB" class="cityvalue">中标津</p>
									<p data-id="SYO" class="cityvalue">庄内</p>
									<p data-id="JUB" class="cityvalue">朱巴</p>
									<p data-id="JCK" class="cityvalue">朱利亚河</p>
									<p data-id="JNU" class="cityvalue">朱诺</p>
									<p data-id="HSG" class="cityvalue">佐贺</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
		<!-- 单程 -->
		<form id="form1" action="intlflight/getFlightList.act" method="post" >

		<input type="hidden" id="OrgCity1" name="orgCity" value="PEK"/>
		<input type="hidden" id="DetCity1" name="detCity" value="HKG"/>
		<input type="hidden" id="dates1" name="dates" value=""/>
		<input type="hidden" id="weeks1" name="weeks" value=""/>
		<input type="hidden" id="startTime1"  name="startTime" value=""/>
		<input type="hidden" name="travelType" value="1"/>
		<input type="hidden" id="travelfangshi1" name="travelfangshi" />
		<input type="hidden" id="cangwei1" name="cangwei" />  
		
		</form>

	

		
		<!-- 往返 -->
		<form id="form2" action="intlflight/getFlightList.act" method="post" >

		<input type="hidden" id="OrgCity2" name="orgCity" value="PEK"/>
		<input type="hidden" id="DetCity2" name="detCity" value="HKG"/>
		<input type="hidden" id="dates2" name="dates" value=""/>
		<input type="hidden" id="weeks2" name="weeks" value=""/>
		<input type="hidden" id="startTime2"  name="startTime" value=""/>
		<input type="hidden" id="arrviTime2"  name="arrviTime" value=""/>
		<input type="hidden" name="travelType" value="2"/>
		<input type="hidden" id="travelfangshi2" name="travelfangshi" />
		<input type="hidden" id="cangwei2" name="cangwei" /> 

		</form>
	</body>

<%-- <script type="text/javascript" src="<%=basePath%>libs/jquery.min.js"></script> --%>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="libs/data/mobiscroll_date.js" charset="gb2312"></script>
<script type="text/javascript" src="libs/data/mobiscroll.js"></script>
<script type="text/javascript" src="<%=basePath%>/libs/Myconfirm.js"></script>
<script type="text/javascript" src="js/MyConfirm.js?_=<%=Math.random()%>"></script>
<script type="text/javascript">
	/*----------我的页面跳转----------*/	
	/* $('.login').on('click',function(){
		window.location.href = "examples/my.html"
	}) */
	/*------选项卡的实现------*/
	$('.formula').on('click', function() {
			var num = $(this).index();
			$(this).addClass('forActive').siblings().removeClass('forActive');
			$(this).parents('body').find('.listway').eq(num).addClass('showLogin').siblings('.listway').removeClass('showLogin');

		})
		/*--------因私出行的转换-------*/
	$('.oneWayList .becaues').on('click', function() {
		turnType(this);
	}); //单程
	$('.turnWayList .becaues').on('click', function() {
		turnType(this);
	}); //往返
	function turnType(obj) {
		if($(obj).html() == "因公出行") {
			$(obj).html("因私出行");
		} else {
			$(obj).html("因公出行");
		}
	};
	/*----------------单程、往返城市的切换---------*/
	$('.wayIcon .dc').click(function(){
		toggleHtml($('.goCity .hCity'),0);
		var c="";
		var o1=$("#OrgCity1").val();
		var d1=$("#DetCity1").val();
		c=o1;
		o1=d1;
		d1=c;
		$("#OrgCity1").val(o1);
		$("#DetCity1").val(d1);
		
	});
	$('.wayIcon .wf').click(function(){
		toggleHtml($('.goCityT .hCity'),1)
		var c="";
		var o2=$("#OrgCity2").val();
		var d2=$("#DetCity2").val();
		c=o2;
		o2=d2;
		d2=c;
		$("#OrgCity2").val(o2);
		$("#DetCity2").val(d2);
	});
	function toggleHtml(obj,index){
		var go = obj.html();
		var come = $('.adressCity .hCity').eq(index).html();
		obj.html(come); 
		$('.adressCity .hCity').eq(index).html(go);
	};
	
	/*-------机票舱位的选择------*/
	var num = ($('.typeAdd .payment-choose p').length + 1) * 80 / 20 + 'rem';
	/*---------默认属性-------------*/
	$('.typeAdd .payment-choose p').eq(0).css('color', '#00AFEC');
	$('.typeAdd .payment-choose p').find('.selected').eq(0).show();
	var objindex;
	$('.classEco .hCity').on('click', function() {
		objindex = $(this);
	
		sameTips('.typeAdd', '.typeAdd .payment-con', num, $(this).html());
	});

	$('.typeAdd .payment-choose p').on('click', function() {
		$(this).css('color', '#00AFEC').siblings().css('color', '');
		$(this).find('.selected').show().parents('.typeAdd .payment-choose p').siblings().find('.selected').hide();
		$('.typeAdd').delay(500).fadeOut(0);
		objindex.html($(this).find('span').eq(0).html());
	});

	function sameTips(objParent, objSilbling, numLength, ele) {
		$(objParent).fadeIn();
		$(objSilbling).animate({
			height: numLength
		}, 1000);
		/*-------设置默认属性------*/
		$('.typeAdd .payment-choose p').each(function() {
			if($(this).find('span').html() == ele) {
				$(this).css('color', '#00AFEC').siblings().css('color', '');
				$(this).find('.selected').eq(0).show().parents('.typeAdd .payment-choose p').siblings().find('.selected').hide();
			}
		})
	};

	/*-----地点的选择----------*/
	$('.oneWayList .goCity .hCity').on('click', function() {
		cityShow($(this))
	});
	$('.oneWayList .adressCity .hCity').on('click', function() {
		cityShow($(this))
	});
	$('.turnWayList .adressCity .hCity').on('click', function() {
		cityShow($(this))
	});
	$('.turnWayList .goCityT .hCity').on('click', function() {
		cityShow($(this))
	});
	$('body').on('click', '.cityvalue', function() {
		$('.bounced').fadeOut();
		$this.html($(this).html());
	});

	function cityShow(obj) {
		$this = obj;
		$('.bounced').fadeIn();
	}
	//点击索引查询城市
	$('body').on('click', '.letter a', function() {
		var s = $(this).html();
		$(window).scrollTop($('#' + s + '1').offset().top);
		$("#showLetter span").html(s);
		$("#showLetter").show().delay(500).hide(0);
	});
	//中间的标记显示
	$('body').on('onMouse', '.showLetter span', function() {
		$("#showLetter").show().delay(500).hide(0);
	});
	/*-----获取本机当地时间并补零------*/
	var datess = p(new Date().getDate());
	var mon = p(new Date().getMonth() + 1);
	var year=new Date().getFullYear().toString();
 		
	/*----------默认日期为今天---------*/
	$('.hCity input').val(mon + "-" + datess);
	$('.hCity input').attr("date",year + "-"+mon + "-" + datess);
	var ssdate = new Date(new Date().getFullYear(), parseInt(mon-1), datess)
	var ssss = ssdate.getDay();
	weekZhou(ssss, '.weekS'); //单程星期的默认
	weekZhou(ssss, '.weekT'); //往返去程星期的默认
	weekZhou(ssss, '.weekW'); //往返返程星期的默认
	//var year;
	/*-----单程搜索保存的日期时间------*/
	$('.oneSearch').eq(0).on('click', function() {
	
		/* if($('#date').val() < (mon + "-" + datess)) {
			mess = "去程日期不能晚于当天";
			showMessage(mess);
			return false;
		} */
		if($('#date').attr("date") < (mon + "-" + datess)) {
			mess = "去程日期不能晚于当天";
			showMessage(mess);
			return false;
		}
		if($('.goCity .hCity').html() == $('.adressCity .hCity').html()) {
			mess = "出发城市不能和到达城市相同";
			showMessage(mess);
			return false;
		}


		/* var dates = $('.oneTime input').val(); */
		var dates = $('#date').attr("date");
		var weeks = $('.weekS').html();
		var Ticketcangwei1=$("#Ticketcangwei1").val();
		$("#dates1").val(dates);
		$("#weeks1").val(weeks);
		$("cangwei1").val(Ticketcangwei1);
		
		/* if(!year){
		var date=new Date;
 		year=date.getFullYear().toString(); 
 		
		} */
		/* $("#startTime1").val(year+"-"+dates); */
		$("#startTime1").val(dates);
		$("#cangwei1").val($("#Ticketcangwei1").html());
		$("#travelfangshi1").val($('#traveltype1').html());
		
		/* $("form1").on('submit',function(){
				$(document).LoadingShow();
				}); */
		
		$(document).LoadingShow();
		$("#form1").submit();
		 
		$(window).unload(function(){$(document).LoadingHide();});
		//window.location.href = "examples/ticketList.html?name=" + dates + "&cont=" + weeks;
	});
	

	$(".hCity").on('click',function(){
		$(this).attr("arrorde","1");
	})
	
	$(".cityvalue").on('click',function(){
	    var v1=	$("#orgCity1").attr("arrorde");
		var v2=	$("#detCity1").attr("arrorde");
		
		var v11=$("#orgCity2").attr("arrorde");
		var v22=$("#detCity2").attr("arrorde");
		
		var id = $(this).attr("data-id");
		if(v1==1){
		/* alert("出发城市"+id); */
		$("#OrgCity1").val(id);
		}
		if(v2==1){
		/* alert("到达城市"+id); */
		$("#DetCity1").val(id);
		}
		if(v11==1){
		/* alert("出发城市"+id); */
		$("#OrgCity2").val(id);
		}
		if(v22==1){
		/* alert("到达城市"+id); */
		$("#DetCity2").val(id);
		}		
	    $(".hCity").attr("arrorde","");
				
				
	})

	/*-------往返搜索保存的日期时间----*/
	$('.oneSearch').eq(1).on('click', function() {
		if($('#dateO').val() < (mon + "-" + datess)) {
			mess = "去程日期不能晚于当天";
			showMessage(mess);
			return false;
		}
		
		
		if($('#dateT').attr("date") <= $('#dateO').attr("date")) {
			mess = "返程时间应大于去程去时间";
			showMessage(mess);
			return false;
		}
		if($('.goCityT .hCity').html() == $('.turnWayList .adressCity .hCity').html()) {
			mess = "出发城市不能和到达城市相同";
			showMessage(mess);
			return false;
		}
		var dates = $('#dateO').attr("date");
		var weeks = $('.weekT').html();
		var datet =$('#dateT').attr("date");
		$("#dates2").val(dates);
		$("#weeks2").val(weeks);
		
		var Ticketcangwei2=$("#Ticketcangwei2").val();
		
		$("cangwei2").val(Ticketcangwei2);
		
		/* if(!year){
		var date=new Date;
 		year=date.getFullYear().toString(); 
 		
		} */
		
		/* $("#startTime2").val(year+"-"+dates);
		$("#arrviTime2").val(year+"-"+datet); */
		$("#startTime2").val(dates);
		$("#arrviTime2").val(datet);
		$("#cangwei2").val($("#Ticketcangwei2").html());
		$("#travelfangshi2").val($('#traveltype2').html());
		
		$(document).LoadingShow();
		
		$("#form2").submit();
		 
		$(window).unload(function(){$(document).LoadingHide();});
		
		
		/* window.location.href = "examples/ticketList.html?name=" + dates + "&cont=" + weeks; */
	});
	
	/*-------------日期插件的引用--------*/
	$(function() {
		var currYear = (new Date()).getFullYear();
		var opt = {};
		opt.date = {
			preset: 'date'
		};
		opt.datetime = {
			preset: 'datetime'
		};
		opt.time = {
			preset: 'time'
		};
		opt.default = {
			theme: 'android-ics light', //皮肤样式
			display: 'modal', //显示方式 
			mode: 'scroller', //日期选择模式
			dateFormat: 'yyy-mm-dd',
			lang: 'zh',
			showNow: true,
			nowText: "今天",
			startYear: (new Date()).getFullYear(), //开始年份
			minDate: new Date(), //最小日期，就是指当前
			endYear: currYear + 10 //结束年份
		};

		$("#date").mobiscroll($.extend(opt['date'], opt['default']));
		$("#dateO").mobiscroll($.extend(opt['date'], opt['default']));
		$("#dateT").mobiscroll($.extend(opt['date'], opt['default']));
	});
	//var year;
	/*------单程，往返。联程的星期随着日期变化----*/
	valueChange("#date", '.weekS'); //单程
	valueChange("#dateO", '.weekT'); //往返去程
	valueChange("#dateT", '.weekW'); //往返返程
	function valueChange(obj, element) {
		$(obj).change(function() {
	
			var str = $(obj).val().split('-');
			$(obj).attr("date",$(obj).val());
			$(obj).val(str[1] + '-' + str[2]);
			
			//year = str[0];

			var ssdate = new Date(str[0], parseInt(str[1] - 1), str[2])
			var ssss = ssdate.getDay();
			weekZhou(ssss, element);
		});
	}
	/*------------转换星期，变成周几---------*/
	function weekZhou(day, obj) {
		switch(day) {
			case 0:
				$(obj).html("周日");
				break;
			case 1:
				$(obj).html("周一");
				break;
			case 2:
				$(obj).html("周二");
				break;
			case 3:
				$(obj).html("周三");
				break;
			case 4:
				$(obj).html("周四");
				break;
			case 5:
				$(obj).html("周五");
				break;
			case 6:
				$(obj).html("周六");
				break;
		}
	};
	/*---------补零---------*/
	function p(s) {
		return s < 10 ? '0' + s : s;
	};
	/*--------提示框显示---------*/
	function showMessage(mess) {

		 $(this).MyConfirm({
			content: mess
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
	}
	$('.typeAdd').bind('click', function(e) {
		hideLayer(e, 'test', '#test');
	});
	var strs="";
	var onetwo="";
	var travelTypeOut="";
	$(".sure").on('click',function(){
		strs="";
		$(".show").find("li").each(function (){
			if($(this).find("img").attr("src") =='<%=basePath%>/hotelimg/choose.png'){
				var name=$(this).find(".passener-name").html();
				if(strs !=""){
					strs += ",";
				}
				strs += name;
			}
		});

		$(".pass").find("li").each(function (){
			if($(this).find("img").eq(0).attr("src") =='<%=basePath%>/hotelimg/choose.png'){
				var pname=$(this).find(".passener-name").html();
				if(strs !=""){
					strs += ",";
				}
				strs += pname;
			}
		});

		if(strs!=""){
			if(onetwo =='one'){
				$(".oneTravel").attr("value","");
				$(".oneTravel").removeAttr('placeholder');
				$(".oneTravel").attr('value',strs);
				$(".twoTravel").attr("placeholder","请添加乘机人");
			}else{
				$(".oneTravel").attr("value","");
				$(".twoTravel").removeAttr('placeholder');
				$(".twoTravel").val(strs);
				$(".oneTravel").attr("placeholder","请添加乘机人");
			}
		}

		$(".pages").fadeOut();
		$(".wrap").fadeIn();

	});


	//获取企业员工
	function getPeople(obj,which,travel){
		onetwo=travel;
		var travelType=$(obj).siblings().find(".traveltype1");

		if(travelType ==null){    //往返
			travelType=$('#traveltype2').html();
		}else{
			travelType=$('#traveltype1').html();
		}
		travelTypeOut=travelType;
		var str="";
		$.post("<%=basePath%>/intlflight/travels.act",{'travelType':travelType,'which':which},function(data){
			if(data){
				for(var da in data){
					var personcard=getIdType(data[da].ids[0]==null?"":data[da].ids[0].type);
				str=`<li  class="left-passener">
						<div class="">
							<div class="middle-table">
                    <div> <span>
						<img src="<%=basePath%>/hotelimg/wei.png" class="choose"/>
					</span></div>
								<p><span class="passener-name">`+data[da].name+`</span></p>
								<p>
									<span class="name">手机号</span>
									<span class="passener-phone">`+data[da].mobile+`</span>
								</p>
								<p>
								<span class="TypeName"><span class="name"><span>`+personcard+`</span>
								</span></span>
									<span class="passener-type">`+(data[da].ids[0]==null?"":data[da].ids[0].num)+`</span>
								</p>
							</div>
						</div>
					</li>
						</ul>`;
					$(".tabContent").find(".show").append(str);
				$(".addH .title").html("添加乘机人");
			}
				$(".wrap").fadeOut();
				$(".pages").fadeIn();
			}
		},'json');

		/*var names=strs.split(",");
		$('.show').find("li").each(function(){
			$(this).find("img").attr("src","<%=basePath%>/hotelimg/wei.png");
			var name=$(this).find(".passener-name").html();
			for(var i=0;i<names.length;i++){
				if(names[i]==name){
					$(this).find("img").attr("src","<%=basePath%>/hotelimg/choose.png");
				}
			}
		})*/

	}

	$(".flyer").on('click',function(){
		var index=$(this).index();
		if(index==1){
			$(this).parents('body').find('.addH .login img').css('display','block');
			$(".show").fadeOut();
			$(".pass").fadeIn();
			getEmployee();
		}else{
			$(this).parents('body').find('.addH .login img').css('display','none');
			$(".show").fadeIn();
			$(".pass").fadeOut();
		}
		$(this).addClass('highlight').siblings().removeClass('highlight');
		//$(this).parents('.tabTitle').siblings().find('ul').eq(index).addClass('show').siblings().removeClass('show');
	})

	function getEmployee(){
		$.post("<%=basePath%>/intlflight/travels.act",{'travelType':travelTypeOut,'which':""},function(datas){
			if(datas){
				var str="";
				for(var id in datas){
					var idType=getIdType(datas[id].ids[0].type);

					str =`<li class="passengerup">

								<div ins="ins" >
<span>
						<img src="<%=basePath%>/hotelimg/wei.png" class="choose"/>
					</span>
									<p class="userName" class="editor"><span class="passener-name">`+datas[id].name+`</span></p>

									<p>
									<span class="name">手机号</span>
									<span class="passener-phone">`+datas[id].mobile+`</span>
								</p>
								<p>
								<span class="TypeName"><span class="name"><span>`+idType+`</span>
								 </span></span>
								<span class="passener-type">`+datas[id].ids[0].num+`</span>
								</p>
								</div>

						<div class="right-table"  onclick="addPassenger('2',`+datas[id].id+`)">
							<a href="javascript:;" ><img src="<%=basePath%>img/bianji.png" /></a>
						</div>
					</li>`
					$(".tabContent").find(".pass").append(str);
				}

				$(".show").fadeOut();
			}

		},'json');


	}

	function addPassenger(type,pid){
		//1:新增常旅客 2:编辑常旅客
		if(type=='1'){
			$("#subForm").attr("action","<%=basePath%>/ppp/addd.act");

		}else if(type=='2'){
			window.frames['updateIframe'].location = "<%=basePath%>/ppp/updatee.act?passengerId="+pid+"&intlflag=intl";
			$('.updateOrAddPassenger').fadeIn();
			$('.wrap').fadeOut()
			$('.pages').fadeOut();}


	}

	function newPassengers(){
		var htmlobj = $.ajax({url: "<%=basePath%>ppp/passAjax.act",async: false});
		var text = htmlobj.responseText;
		eval("var json="+dataobj);
		var json=json.data;

		for(var i in json){
			var personcard=getIdType(json[i].ids[0].type);
			var str=`<li class="passengerup">

                     <div><span>
						<img src="<%=basePath%>/hotelimg/wei.png" class="choose"/>
					</span></div>
								<div ins="ins" >

									<p class="userName" class="editor"><span class="passener-name">`+json[i].name+`</span></p>

									<p>
									<span class="name">手机号</span>
									<span class="passener-phone">`+json[i].mobile+`</span>
								</p>
								<p>
								<span class="TypeName"><span class="name"><span>`+personcard+`</span>
								 </span></span>
								<span class="passener-type">`+json[i].ids[0].num+`</span>
								</p>
								</div>

						<div class="right-table"  onclick="addPassenger('2',`+json[i].id+`)">
							<a href="javascript:;" ><img src="<%=basePath%>/img/bianji.png" /></a>
						</div>
					</li>`;

			$(".tabContent").find(".pass").append(str);

		}
		$(".updateOrAddPassenger").fadeOut();
		$(".pages").fadeIn();

	}

	$('.login img').on('click', function(){

		window.frames['updateIframe'].location = "<%=basePath%>ppp/addd.act?intlflag=intl";
		$('.updateOrAddPassenger').fadeIn();
		$('.wrap').fadeOut();
		$('.pages').fadeOut();

		return false;
	})

	$("body").on('click','.passengerup,.left-passener',function(){
		$(this).find('.choose').attr('src',$(this).find('.choose').attr('src')== '<%=basePath%>/hotelimg/wei.png'?'<%=basePath%>/hotelimg/choose.png':'<%=basePath%>/hotelimg/wei.png');
	})



	function getIdType(number){
		var personcard="";
		switch(number)
		{
			case 1:
				personcard="身份证";
				break;
			case 2:
				personcard="护照";
				break;
			case 3:
				personcard="海员证";
				break;
			case 4:
				personcard="回乡证";
				break;
			case 5:
				personcard="军官证";
				break;
			case 6:
				personcard="港澳通行证";
				break;
			case 7:
				personcard="台胞证";
				break;
			case 99:
				personcard="其他";
				break;
			default:
				var personcard="";
		}
		return personcard;
	}
</script>
</html>
</fms:Content>
</fms:ContentPage>