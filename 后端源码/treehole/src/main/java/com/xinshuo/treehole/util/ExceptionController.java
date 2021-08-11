package com.xinshuo.treehole.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {


    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseJSON handleUnauthenticatedException(Exception e) {
        return new ResponseJSON(Constant.HTTP_CODE_FORBIDDEN, e.getMessage(), "认证失败");
        //return "错误："+e.getMessage();
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseJSON handleAuthenticationException(Exception e) {
        return new ResponseJSON(Constant.HTTP_CODE_FORBIDDEN, e.getMessage(), e.getMessage());
        //return "失败："+e.getMessage();
    }

}
