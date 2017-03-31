package com.clover.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by lkl on 2017/3/18.
 */
@Entity
@Data
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;
    private Integer uid;
    private Integer fid;
    public Friend(Integer uid, Integer fid) {
        this.uid = uid;
        this.fid = fid;
    }
}
