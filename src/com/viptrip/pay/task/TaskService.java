package com.viptrip.pay.task;

/**
 * Created by selfwhisper on 0020 2017/11/20.
 */
public interface TaskService<E extends Notify> {
    Notify update(E notify);
}
