package com.maxoxo.baseproject.net.model;

/**
 * Created by maxoxo on 2017/7/19.
 */

public class BaseResult<T> {

    public String code;
    public String message;
    public T data;

    public String code() {
        return code;
    }
}
