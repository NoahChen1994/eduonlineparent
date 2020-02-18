package com.eduonline.eduservice.exception;

import com.eduonline.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ControllerAdvice:标注为aop
 * ExceptionHandler：指定要拦截的异常
 * ResponseBody：在页面显示
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e){
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
