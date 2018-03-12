package com.yifushidai.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * Created by liumei on 2017/11/29 14:32.
 * desc:全局异常 封装
 */
@ControllerAdvice()
@ConfigurationProperties
public class GlobalExceptionHandler {
    protected Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
        log.info("GlobalExceptionHandler构建");
    }

    @ExceptionHandler(RRException.class)//自定义异常
    @ResponseBody
    public R RRExceptionHandler(RRException ex) {
        R r = R.error(ex.getCode(),ex.getMsg());
      return r;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public R missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        R r = R.error(ErrorCodeConstant.ARGUMENT_VANISH,"缺少参数   "+e.getMessage());
        return r;
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public R methodArgumentTypeMismatchExceptionExceptionHandler(MethodArgumentTypeMismatchException e) {
        R r = R.error(ErrorCodeConstant.ARGUMENT_TYPE_MISMATCH,"参数类型不正确");
        return r;
    }

    @ExceptionHandler({BindException.class,ConstraintViolationException.class})
    @ResponseBody
    public R ViolationException(Exception e) {
        R r = R.error(ErrorCodeConstant.ARGUMENT_VIOLATION,"参数校验异常");
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public R DuplicateKeyExceptionHandler( DuplicateKeyException e) {
        R r = R.error(ErrorCodeConstant.DUPLICATE,"数据已存在");
        return r;
    }

    @ExceptionHandler(Exception.class)//处理其他异常
    @ResponseBody
    public R exceptionHandler(Exception e) {
        R r = R.error();
        return r;
    }
}