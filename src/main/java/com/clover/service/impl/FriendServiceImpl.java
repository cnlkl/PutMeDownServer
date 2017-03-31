package com.clover.service.impl;

import com.clover.exception.AccountNotFoundException;
import com.clover.exception.FriendAlreadExistException;
import com.clover.exception.FriendRequestAlreadyExistsExcption;
import com.clover.exception.ParameterMissingException;
import com.clover.model.Friend;
import com.clover.model.FriendRequest;
import com.clover.model.User;
import com.clover.repository.FriendRepository;
import com.clover.repository.FriendRequestRepository;
import com.clover.repository.UserRepository;
import com.clover.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkl on 2017/3/24.
 */
@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    public static final int ACCEPT = 1;

    private FriendRepository friendRepository;

    private FriendRequestRepository requestRepository;

    private UserRepository userRepository;

    @Autowired
    public FriendServiceImpl(FriendRepository friendRepository,
                             UserRepository userRepository,
                             FriendRequestRepository requestRepository) {
        this.friendRepository = friendRepository;
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void sendFriendRqs(Integer uid, String phone) {

        if (StringUtils.isEmpty(phone)) {
            throw new ParameterMissingException();
        }

        User requestedUser = userRepository.findByAccount(phone);
        if (requestedUser == null) {
            throw new AccountNotFoundException("未查找到该用户");
        }

        //判断是否已经是好友关系
        Friend friend =
                friendRepository.findByUidAndFid(uid, requestedUser.getUid());

        if(friend != null) {
            throw new FriendAlreadExistException();
        }

        //判断好友请求是否已经请求过，并且尚未处理
        FriendRequest friendRequest =
                requestRepository
                        .findByRequestedIdAndRequesterId(requestedUser.getUid(), uid);

        if (friendRequest != null) {
            throw new FriendRequestAlreadyExistsExcption();
        }

        requestRepository.save(new FriendRequest(requestedUser.getUid(), uid));

    }

    @Override
    public List<User> getFriendRqsList(Integer requestedId, Pageable pageable) {

        if (requestedId == null) {
            throw new ParameterMissingException();
        }

        List<FriendRequest> requestList =
                requestRepository.findByRequestedId(requestedId);
        List<Integer> requesterIdList = new ArrayList<>(requestList.size());

        for (FriendRequest request : requestList) {
            requesterIdList.add(request.getRequesterId());
        }

        return userRepository.findByUidIn(requesterIdList, pageable).getContent();
    }

    @Override
    public void solveFriendRqs(Integer uid, Integer requesterId, Integer isAccept) {

        if (requesterId == null || isAccept == null) {
            throw new ParameterMissingException();
        }

        //判断该请求是否存在
        if (requestRepository.findByRequestedIdAndRequesterId(uid, requesterId) == null) {
            throw new AccountNotFoundException("没有来自用户 " + requesterId +" 的请求");
        }

        //判断是否已经是好友关系
        if (friendRepository.findByUidAndFid(uid, requesterId) != null) {
            throw new FriendAlreadExistException();
        }

        if (isAccept == ACCEPT) {
            friendRepository.addFriend(uid, requesterId);
        }
        requestRepository.deleteByRequestedIdAndRequesterId(uid, requesterId);

    }

    @Override
    public List<User> getFriends(Integer uid, Pageable pageable) {

        List<Friend> friends = friendRepository.findByUid(uid);
        List<Integer> fidList = new ArrayList<>(friends.size());
        for (Friend friend : friends) {
            fidList.add(friend.getFid());
        }
        return userRepository.findByUidIn(fidList, pageable).getContent();
    }


    @Override
    public void deleteFriend(Integer uid, Integer fid) {

        if (fid == null) {
            throw new ParameterMissingException();
        }

        friendRepository.deleteFriend(uid, fid);
    }
}
