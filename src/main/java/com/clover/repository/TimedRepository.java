package com.clover.repository;

import com.clover.model.Timed;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by lkl on 2017/3/20.
 */

public interface TimedRepository extends JpaRepository<Timed, Integer> {
}
