package com.jk.mock.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Data
@AllArgsConstructor
@ToString
public class ResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private String desc;

    private Object data;

    public ResponseModel() {
        this.code = "200";
        this.msg = "success";
        this.data = new JSONObject();
    }

}
