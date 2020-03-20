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
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
