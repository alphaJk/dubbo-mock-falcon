package com.jk.mock.core.code.util;


import com.alibaba.fastjson.JSON;

import java.io.Serializable;


public class UtopiaResponse<T> implements Serializable {

    private Integer code = 200;
    private String msg = "";
    private T data = null;

    public UtopiaResponse() {
    }

    public UtopiaResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public UtopiaResponse(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public UtopiaResponse(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "UtopiaResponseModel(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ")";
    }

}
