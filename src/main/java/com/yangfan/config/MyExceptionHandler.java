package com.yangfan.config;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice   //controller 增强器
public class MyExceptionHandler {
    //捕捉没有权限异常,没有权限返回的页面
    @ExceptionHandler(value = UnauthorizedException.class)
    public String noPermission(){
        return "noPermission";
    }
}
