package com.clover.repository;

import com.clover.model.FriendRequest;
import com.clover.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lkl on 2017/3/27.
 */
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Integer> {

    List<FriendRequest> findByRequestedId(Integer requestedId);
    void deleteByRequestedIdAndRequesterId(Integer requestedId, Integer requesterId);

    /**
     *
     * @param requestedId   被请求id
     * @param requesterId   用户id（发起请求的用户的id）
     * @return  请求
     */
    FriendRequest findByRequestedIdAndRequesterId(Integer requestedId, Integer requesterId);

}
