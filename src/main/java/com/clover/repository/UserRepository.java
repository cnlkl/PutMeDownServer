package com.clover.repository;

import com.clover.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by lkl on 2017/3/20.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByAccount(String account);
    Page<User> findByUidIn(List<Integer> uidList, Pageable pageable);
}
