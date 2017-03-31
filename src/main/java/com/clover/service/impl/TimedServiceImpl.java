package com.clover.service.impl;

import com.clover.exception.ParameterMissingException;
import com.clover.model.Timed;
import com.clover.model.User;
import com.clover.repository.TimedRepository;
import com.clover.repository.UserRepository;
import com.clover.service.TimedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by lkl on 2017/3/31.
 */
@Service
@Transactional
public class TimedServiceImpl implements TimedService{

    private TimedRepository timedRepository;
    private UserRepository userRepository;

    @Autowired
    public TimedServiceImpl(TimedRepository timedRepository,
                            UserRepository userRepository) {
        this.timedRepository = timedRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Timed updateTimed(Integer uid,
                             Integer timedDay,
                             Integer timedWeek,
                             Integer timedMonth,
                             Long timedTotal) {

        if (timedDay == null ||
                timedWeek == null ||
                timedMonth == null ||
                timedTotal == null) {
            throw new ParameterMissingException();
        }

        User user = userRepository.findOne(uid);

        Timed timed = user.getTimed();
        if(timed == null) {
            timed = new Timed();
        }
        timed.setTimedDay(timedDay);
        timed.setTimedWeek(timedWeek);
        timed.setTimedMonth(timedMonth);
        timed.setTimedTotal(timedTotal);
        user.setTimed(timed);
        return userRepository.save(user).getTimed();
    }
}
