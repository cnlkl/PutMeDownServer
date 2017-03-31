package com.clover.controller;

import com.clover.annotation.AuthNeed;
import com.clover.annotation.CurrentUserId;
import com.clover.model.Response;
import com.clover.model.User;
import com.clover.service.FriendService;
import com.clover.service.impl.FriendServiceImpl;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by lkl on 2017/3/29.
 */
@RestController
@RequestMapping("/api")
public class FriendController {

    private FriendService friendService;

    @Autowired
    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @AuthNeed
    @RequestMapping(value = "/friendRequest", method = RequestMethod.POST)
    public Response sendFriendRequest(
            @CurrentUserId Integer uid,
            @RequestParam String friendPhone) {

        friendService.sendFriendRqs(uid, friendPhone);

        return new Response(200, "已发送好友请求");
    }

    @AuthNeed
    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    public Response solveFriendRequest(
            @CurrentUserId Integer uid,
            @RequestParam Integer fid,
            @RequestParam Integer isAccept) {

        friendService.solveFriendRqs(uid, fid, isAccept);

        Response response = new Response();
        response.setStatus(200);
        if (isAccept == FriendServiceImpl.ACCEPT) {
            response.setMsg("已添加对方为好友");
        } else {
            response.setMsg("已拒绝好友请求");
        }

        return response;
    }


    @AuthNeed
    @RequestMapping(value = "/friend", method = RequestMethod.DELETE)
    public Response deleteFriend(
            @CurrentUserId Integer uid,
            @RequestParam Integer fid) {
        friendService.deleteFriend(uid, fid);
        return new Response(200, "好友已删除");
    }

    @AuthNeed
    @RequestMapping(value = "/friendRequests", method = RequestMethod.GET)
    public Response<List<User>> getFriendRequests(
            @CurrentUserId Integer uid,
            @PageableDefault(sort = "uid", direction = Sort.Direction.DESC)
                    Pageable pageable) {

        List<User> requests = friendService.getFriendRqsList(uid, pageable);
        return new Response<>(200, "获取好友请求列表成功", requests);
    }

    @AuthNeed
    @RequestMapping(value = "/friends", method = RequestMethod.GET)
    public Response<List<User>> getFriends(
            @CurrentUserId Integer uid, Pageable pageable) {
        List<User> friends = friendService.getFriends(uid, pageable);
        return new Response<>(200, "获取好友列表成功", friends);
    }
}
