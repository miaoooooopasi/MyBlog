package com.leon.myblog.utils.result;

/**
 * @author ：leon
 * @date ：Created in 2020-03-17 13:23
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result server_error(String msg) {
        Result result = new Result();
        result.setCode(ResultCode.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result fail(String msg) {
        Result result = new Result();
        result.setCode(ResultCode.FAIL.getCode());
        result.setMsg(msg);
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(ResultCode.FALSE.getCode());
        result.setMsg(msg);
        return result;
    }
}
