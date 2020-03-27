package com.leon.myblog.utils.result;

/**
 * @author ：leon
 * @date ：Created in 2020-03-27 9:39
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public enum ResultCode {

    //成功
    SUCCESS(200),
    //失败
    FAIL(400),
    //错误
    FALSE(404),
    //内部错误
    ERROR(500);

    private Integer code;

    ResultCode(Integer code){
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
