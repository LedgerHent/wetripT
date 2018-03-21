##全局提醒配置文件##
#是否启用全局提醒1-开启 0-关闭
globalWarning=1
#月结用户开始结算日期
startSettleDate=20170901
#日期格式
datePattern=yyyyMMdd
#是否为测试
isTest=1
#测试用户 格式为 【用户1手机号,用户1邮箱地址;用户2手机号,用户2邮箱地址......】
testUser=18513149989,hushuxiao@viptrip365.com;13071169344,dengfei@viptrip365.com
#全局提醒限额
globalWarningVal=5000
#全局通知内容
globalWarningText=您好，贵单位的余额不足，请尽快充值。此消息为系统自动发送，无需回复。
globalWarningTextBG=客维/财务人员，$oname的剩余额度为$left,低于当前设置的全局预警值预警值$global，请联系对方充值。
#预付款页面提示
pageTipPre=预付款余额不足，无法完成交易。请联系企业差旅管理员进行充值后再预订，或者选择在线支付。
#月结页面提示
pageTipMon=贵司授信额度已超额，无法完成交易。请联系企业差旅管理员处理。
#客服以及财务短信提醒模板，$oname-单位名称 $limit-限额值 $left-剩余额度
smsWarningtplBG=客维/财务人员，$oname的剩余额度为$left,低于当前设置的预警值$limit，请联系对方充值。
#客服以及财务邮件提醒模板内容，$oname-单位名称 $limit-限额值 $left-剩余额度
emailWarningtplContentBG=客维/财务人员，$oname的剩余额度为$left,低于当前设置的预警值$limit，请联系对方充值。
#客服以及财务邮件提醒模板标题，$oname-单位名称 $limit-限额值 $left-剩余额度
emailWarningtplTitleBG=额度不足提醒通知
#企业短信提醒模板，$oname-单位名称 $limit-限额值 $left-剩余额度
smsWarningtpl=您好，贵单位的余额不足，请尽快充值。此消息为系统自动发送，无需回复。
#企业邮件提醒模板内容，$oname-单位名称 $limit-限额值 $left-剩余额度
emailWarningtplContent=您好，贵单位的余额不足，请尽快充值。此消息为系统自动发送，无需回复。
#企业邮件提醒模板标题，$oname-单位名称 $limit-限额值 $left-剩余额度
emailWarningtplTitle=额度不足提醒通知
#客服财务邮件地址
emailAddrBG=lililliililiil@163.com,liwen@viptrip365.com
#短信userkey
userKey=【凯撒商旅】