package com.pxy.ggkt.exception;

import com.pxy.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-16 9:04
 **/

@ControllerAdvice  //aop不改变源代码的情况下，增强方法功能
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null).message("全局异常处理");
    }

    //ArithmeticException
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
//        Result<Object> result = new Result<>();
//        result.setMessage("数学定义异常");
        return Result.fail(null).message("ArithmeticException handler");
    }

    //custom exception handler; GgktException
    @ExceptionHandler(GgktException.class)
    @ResponseBody
    public Result error(GgktException e){
        e.printStackTrace();
//        Result<Object> result = new Result<>();
//        result.setMessage("数学定义异常");  custom exception handler
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }
}
