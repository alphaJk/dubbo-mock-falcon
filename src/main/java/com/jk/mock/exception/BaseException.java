package com.jk.mock.exception;

/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2023/2/23
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String code;

    private String msg;

    private String desc;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMsg());
        this.msg = errorCode.getMsg();
        this.code = errorCode.getCode();
        this.desc = errorCode.getMsg();
    }

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
        this.desc = msg;
        this.code = ErrorCode.SYSTEM_ERROR.getCode();
    }

    public BaseException(String code, String msg) {
        super(msg);
        this.code=code;
        this.msg = msg;
        this.desc = msg;
    }

    public BaseException(String code, String msg, String desc) {
        super(msg);
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(Exception ex) {
        super(ex);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
