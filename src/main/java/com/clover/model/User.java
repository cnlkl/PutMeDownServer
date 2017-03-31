package com.clover.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lkl on 2017/3/18.
 */
@Entity
@Data
@NoArgsConstructor
public class User {

    //TODO 不同接口返回不同数据
    @Id
    @GeneratedValue
    private Integer uid;
    @JsonIgnore
    private String account;
    @JsonIgnore
    @Basic(fetch = FetchType.LAZY)
    private String password;
    private String name;
    private String phoneNumber;
    private String avatar;
    @JsonIgnore
    private String regTime;
    private String lastLoginTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Timed timed;

}
