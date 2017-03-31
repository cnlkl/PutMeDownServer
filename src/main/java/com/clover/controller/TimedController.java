package com.clover.controller;

import com.clover.annotation.AuthNeed;
import com.clover.annotation.CurrentUserId;
import com.clover.model.Response;
import com.clover.model.Timed;
import com.clover.service.TimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lkl on 2017/3/31.
 */
@RestController
@RequestMapping("/api")
public class TimedController {

    private TimedService timedService;

    @Autowired
    public TimedController(TimedService timedService) {
        this.timedService = timedService;
    }

    @AuthNeed
    @RequestMapping(value = "/timed", method = RequestMethod.PUT)
    public Response<Timed> updateTimed(@CurrentUserId Integer uid, Timed timed) {

        Timed result = timedService.updateTimed(uid,
                timed.getTimedDay(),
                timed.getTimedWeek(),
                timed.getTimedMonth(),
                timed.getTimedTotal());

        return new Response<>(200, "更新记录成功", result);
    }
}
