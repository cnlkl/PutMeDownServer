package com.clover.service;

import com.clover.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by lkl on 2017/3/21.
 */
public interface FriendService {

    /**
     * 发送好友请求
     * @param uid 用户id
     * @param phone 请求添加的用户手机
     * @return  被添加用户
     */
    void sendFriendRqs(Integer uid, String phone);


    /**
     * 获取好友请求列表
     * @param uid   用户id
     * @return  请求列表
     */
    List<User> getFriendRqsList(Integer uid, Pageable pageable);

    /**
     *处理好友请求
     * @param uid   用户id
     * @param requesterId   发起请求的用户id
     * @return  接受的好友
     */
    void solveFriendRqs(Integer uid, Integer requesterId, Integer isAccept);

    /**
     * 获取好友列表
     * @param uid    用户id
     * @param pageable  分页信息
     * @return  好友列表
     */
    List<User> getFriends(Integer uid, Pageable pageable);

    void deleteFriend(Integer uid, Integer fid);
}
