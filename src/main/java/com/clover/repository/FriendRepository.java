package com.clover.repository;

import com.clover.model.Friend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by lkl on 2017/3/20.
 */
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    @Modifying
    @Query("delete from Friend where uid = ?1 and fid = ?2 or uid = ?2 and fid = ?1")
    void deleteFriend(Integer uid, Integer fid);

    @Modifying
    @Query(value = "insert into Friend (uid, fid) values (?1, ?2), (?2, ?1)", nativeQuery = true)
    int addFriend(Integer uid, Integer fid);

    List<Friend> findByUid(Integer uid);

    Friend findByUidAndFid(Integer uid, Integer fid);
}
