package com.clover.exception;

/**
 * Created by lkl on 2017/3/30.
 */
public class FriendAlreadExistException extends RuntimeException{
    public FriendAlreadExistException() {
        this("对方已经是您的好友了");
    }

    public FriendAlreadExistException(String message) {
        super(message);
    }
}
