package com.viptrip.pay.service.impl;

import com.viptrip.pay.dao.PayRefundInfoDao;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.entity.PayRefundInfo;
import com.viptrip.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by selfwhisper on 0031 2018/1/31.
 */
@Service
public class PayRefundInfoService {

    @Resource
    private PayRefundInfoDao dao;

    public PayRefundInfo queryExistRefundInfo(String orderId,String refundNo,Integer payType){
        PayRefundInfo result = null;
        if(StringUtil.isNotEmpty(orderId)&&StringUtil.isNotEmpty(refundNo)){
            String hql = null;
            List<Object> params = new ArrayList<>();
            if(orderId.indexOf("_")>0){ //改期
                hql = "from PayRefundInfo where payId in (select id from PayInfo where orderno like ? " ;
                params.add(orderId + "%");
                if(null!=payType){
                    hql += " and type=?";
                    params.add(payType);
                }
                hql +=" and status in (2,4)) and refundId=?";
                params.add(refundNo);
            }else{
                hql = "from PayRefundInfo where payId in (select id from PayInfo where orderno=? " ;
                params.add(orderId);
                if(null!=payType){
                    hql += " and type=?";
                    params.add(payType);
                }
                hql +=" and status in (2,4)) and refundId=?";
                params.add(refundNo);
            }
            List<PayRefundInfo> refundInfos = dao.queryForList(hql,params.toArray());
            if(null!=refundInfos && refundInfos.size()>0){
                result = refundInfos.get(0);
            }
        }
        return result;
    }

    /**
     * 支付成功（退款完成）的支付记录
     * @param orderno
     * @return
     */
    public PayInfo getChangePayInfos(String orderno, String refundNo,Integer payType){
        PayInfo result = null;
        if(StringUtil.isNotEmpty(orderno)){
            if(orderno.indexOf("_")>0){
                String hql = "from PayInfo where orderno like ? and status in (2,4) ";
                if(null!=payType){
                    hql += " and type=?";
                }
                List<PayInfo> list = dao.queryForList(hql, null!=payType?new Object[]{orderno + "%",payType}:new Object[]{orderno + "%"});
                if(null!=list){
                    for (PayInfo payInfo : list) {
                        String orderno1 = payInfo.getOrderno();
                        String[] split = orderno1.split("_");
                        if(split.length == 4){
                            Long start = Long.parseLong(split[2]);
                            Long end = Long.parseLong(split[3]);
                            String[] split1 = refundNo.split("_");
                            Long s = Long.parseLong(split1[0]);
                            Long e = Long.parseLong(split1[split1.length-1]);
                            if(start<=s && end>=e){
                                result = payInfo;
                                break;
                            }
                        }
                    }
                }
            }else{
                String hql = "from PayInfo where orderno=? and status in (2,4)";
                if(null!=payType){
                    hql += " and type=?";
                }
                List<PayInfo> list = dao.queryForList(hql, null!=payType?new Object[]{orderno,payType}:new Object[]{orderno});
                if(null!=list && list.size()>0){
                    result = list.get(0);
                }
            }
        }
        return result;
    }

    /**
     * 获取已经退款的金额
     * @param payId
     * @return
     */
    public Double getRefundedAoumt(Long payId){
        Double result = null;
        if(null!=payId){
            String hql = "select nvl(sum(refundAmount),0) from PayRefundInfo where payId=?";
            result = dao.queryForDouble(hql, new Object[]{payId});
        }
        return result;
    }

    public void update(PayRefundInfo pri){
        if(null!=pri){
            dao.executeUpdate(pri);
        }
    }

    public void save(PayRefundInfo pri){
        if(null!=pri){
            dao.executeSave(pri);
        }
    }
}
