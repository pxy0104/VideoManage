package com.pxy.ggkt.result;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-15 20:21
 **/

import lombok.Data;

/**
 * 统一返回结果类
 */
@Data
public class Result<T> {

    private Integer code; //状态码code
    private String message; //返回状态信息

    private T data; //返回类型数据

    public Result() {
    }

    //成功的方法
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(20000);
//        result.setMessage("成功");
        return result;
    }

    //失败方法
    public static <T> Result<T> fail(T data) {
        Result<T> result = new Result<>();
        if (data != null) {
            result.setData(data);
        }
        result.setCode(20001);
//        result.setMessage("失败");
        return result;
    }

    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
