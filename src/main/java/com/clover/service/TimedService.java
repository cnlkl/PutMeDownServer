package com.clover.service;

import com.clover.model.Timed;

/**
 * Created by lkl on 2017/3/21.
 */
public interface TimedService {

    /**
     * 更新用户计时记录
     * @param uid   用户id
     * @param timedDay  日计时时间
     * @param timedWeek 周计时时间
     * @param timedMonth    月计时时间
     * @param timedTotal    总计时时间
     * @return  计时记录
     */
    Timed updateTimed(Integer uid,
                      Integer timedDay,
                      Integer timedWeek,
                      Integer timedMonth,
                      Long timedTotal);
}
