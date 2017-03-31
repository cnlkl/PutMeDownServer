package com.clover.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lkl on 2017/3/27.
 */
@Entity
@Data
@NoArgsConstructor
public class FriendRequest {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer requestedId;
    private Integer requesterId;

    public FriendRequest(Integer requestedId, Integer requesterId) {
        this.requestedId = requestedId;
        this.requesterId = requesterId;
    }

}
