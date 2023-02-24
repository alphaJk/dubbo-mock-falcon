package com.jk.mock.exception;

import org.springframework.util.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public enum ErrorCode {

    // 系统错误
    SYSTEM_ERROR("30201", "system error"),
    ERROR_CODE_30224("30224", "db error"),
    ERROR_CODE_30225("30225", "update response error"),
    ERROR_CODE_30226("30226", "save db error"),
    ERROR_CODE_30227("30227", "mock info is existed"),
    ERROR_CODE_30228("30228", "remove mock info error")
    ;


    private String code;
    private String msg;

    private ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String  getMsg() {
        return msg;
    }

    public static ErrorCode getByCode(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }
        for (ErrorCode item : values()) {
            if (item.getCode().equals(code)) {
                return item;
            }
        }
        return null;
    }
}
