package com.viptrip.pay.service.impl;

import com.viptrip.pay.dao.PayInfoDao;
import com.viptrip.pay.entity.PayInfo;
import com.viptrip.pay.entity.PayNotify;
import com.viptrip.pay.service.IPayInfoService;
import com.viptrip.pay.task.Notify;
import com.viptrip.pay.task.TaskService;
import com.viptrip.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
@Service("unipayInfoService")
public class PayInfoService implements IPayInfoService, TaskService{

    @Resource
    private PayInfoDao dao;

    /**
     * 查找
     * @param orderno
     * @param status
     * @return
     */
    public List<PayInfo> query(String orderno, Integer status){
        List<PayInfo> result = null;
        if(null!=orderno){
            String hql = "from PayInfo where orderno=?";
            if(null!=status){
                hql += " and status=?";
            }
            result = dao.queryForList(hql,status==null?new Object[]{orderno}:new Object[]{orderno,status});
        }
        return result;
    }

    /**
     * 存在已经成功支付的记录
     * @param orderno
     * @return
     */
    public boolean existSuccessPayInfo(String orderno){
        boolean result = false;
        if(StringUtil.isNotEmpty(orderno)){
            String hql = "from PayInfo where orderno=? and status=2";
            List<PayInfo> list = dao.queryForList(hql,new Object[]{orderno});
            if(null!=list && list.size()>0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 存在已经成功支付的记录
     * @param orderno
     * @return
     */
    public boolean existCantPayRecode(String orderno){
        boolean result = false;
        if(StringUtil.isNotEmpty(orderno)){
            String hql = "from PayInfo where orderno=? and status in (2,3,4)";
            List<PayInfo> list = dao.queryForList(hql,new Object[]{orderno});
            if(null!=list && list.size()>0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 获取已经成功支付记录
     * @param orderno
     * @return
     */
    public PayInfo getSuccessPayInfo(String orderno){
        PayInfo result = null;
        if(StringUtil.isNotEmpty(orderno)){
            String hql = "from PayInfo where orderno=? and status in (2,4)";
            List<PayInfo> list = dao.queryForList(hql,new Object[]{orderno});
            if(null!=list && list.size()>0){
                result = list.get(0);
            }
        }
        return result;
    }

    /**
     * 获取支付信息
     * @param orderno
     * @param status
     * @return
     */
    public List<PayInfo> getPayInfo(String orderno,Integer status){
        List<PayInfo> result = null;
        if(StringUtil.isNotEmpty(orderno) && null!= status){
            String hql = "from PayInfo where orderno=? and status=?";
            result = dao.queryForList(hql,new Object[]{orderno,status});
        }
        return result;
    }

    /**
     * 获取支付信息
     * @param orderno
     * @param payType
     * @param status
     * @return
     */
    public PayInfo getPayInfo(String orderno,Integer payType,Integer status){
        PayInfo result = null;
        if(StringUtil.isNotEmpty(orderno) && null!= status && null!= payType){
            List<PayInfo> list = getPayInfo(orderno, status);
            if(null!=list && list.size()>0){
                for (PayInfo pi:list){
                    if(payType==pi.getType()){
                        result = pi;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public PayInfo getPayInfoByNoAndType(Integer payType,String orderNo){
        PayInfo result = null;
        if(null!=payType && StringUtil.isNotEmpty(orderNo)){
            String hql = "from PayInfo where orderno=? and type=?";
            List<PayInfo> objects = dao.queryForList(hql, new Object[]{orderNo, payType});
            if(null!=objects && objects.size()>0){
                result = objects.get(0);
            }
        }
        return result;
    }

    @Override
    public PayNotify getPayNotify(String orderno,Integer type) {
        PayNotify result = null;
        if(null!=orderno && null!=type){
            String hql = " from PayNotify where orderno=? and type=?";
            List<PayNotify> list = dao.queryForList(hql, new Object[]{orderno, type});
            if(null!=list && list.size()>0){
                result = list.get(0);
            }
        }
        return result;
    }

    /**
     * 修改
     * @param payInfo
     */
    public void update(PayInfo payInfo){
        if(null!=payInfo){
            dao.executeUpdate(payInfo);
        }
    }

    /**
     * 保存
     * @param payInfo
     */
    public void save(PayInfo payInfo){
        if(null!=payInfo){
            dao.executeUpdate(payInfo);
        }
    }

    @Override
    public Notify update(Notify notify) {
        if(null!=notify){
            return (Notify) dao.executeUpdate(notify);
        }
        return null;
    }
}
