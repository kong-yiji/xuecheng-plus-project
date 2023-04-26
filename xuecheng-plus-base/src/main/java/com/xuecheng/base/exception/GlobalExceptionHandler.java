package com.xuecheng.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author : yin
 * @Date: 2023/4/26
 * @Description:TODO
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    //自定义异常
    @ResponseBody
     @ExceptionHandler(XueChengPlusException.class)
     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(XueChengPlusException ex){
         System.out.println("------------------------------代理");
        log.error("【系统异常】{}",ex.getErrMsg(),ex);
        return new RestErrorResponse(ex.getErrMsg());
    }
//普通异常
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse runTimeException(RuntimeException e){
        log.error("【系统异常】{}",e.getMessage(),e);
        return new RestErrorResponse(CommonError.UNKNOWN_ERROR.getErrMessage());
    }
}
