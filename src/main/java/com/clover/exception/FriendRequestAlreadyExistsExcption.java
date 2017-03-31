package com.clover.exception;

/**
 * Created by lkl on 2017/3/29.
 */
public class FriendRequestAlreadyExistsExcption extends RuntimeException{
    public FriendRequestAlreadyExistsExcption() {
        this("好友请求已发送，请等待对方处理");
    }

    public FriendRequestAlreadyExistsExcption(String message) {
        super(message);
    }
}
