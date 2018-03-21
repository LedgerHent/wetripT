package com.viptrip.common.util;

/**
 * Created by hent on 2017/11/28.
 */
public class ApprovalEnum {

    //企业规则类型，1-按企业|2-按分组|3-按部门|4-按员工
    public enum OgrRuleType{
        按企业(1),按分组(3),按部门(2),按员工(4);
        private final int operateType;
        public int getOperateType(){
            return  operateType;
        }
        OgrRuleType(int operateType){
            this.operateType=operateType;
        }
    }

    //审批业务类型
    public enum ApprovalBussType{
        不限(0),机票(1),酒店(3),火车票(5);
        private final int operateType;
        public int getOperateType(){
            return operateType;
        }
        ApprovalBussType(int operateType){
            this.operateType=operateType;
        }
    }

    //审批规则表状态   状态，1-待发布|2-已发布|3-已删除
    public enum ApprovalState{
        待发布(1),已发布(2),已删除(3);
        private final int operateType;
        public int getOperateType(){
            return operateType;
        }
        ApprovalState(int operateType){
            this.operateType=operateType;
        }
    }

    //规则关联表   规则类型  1-差旅政策|2-审批规则
    public enum RuleType{
        差旅政策(1),审批政策(2);
        private final int operateType;
        public int getOperateType(){
            return operateType;
        }
        RuleType(int operateType){
            this.operateType=operateType;
        }
    }
}
