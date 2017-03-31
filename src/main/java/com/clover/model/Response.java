package com.clover.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by lkl on 2017/3/21.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    public Response(Integer status, String msg) {
        this(status, msg, null);
    }

    private Integer status;

    private String msg;

    private T data;
}
