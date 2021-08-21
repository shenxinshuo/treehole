package com.xinshuo.treehole.filter;


import com.xinshuo.treehole.util.JWTToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    /**
     *如果带有token，则对token进行检查，否则直接通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //判断请求的请求头是否带上“Token”
        if (isLoginAttempt(request, response)) {
            //如果存在，则进入executeLogin方法执行登入，检查token是否正确
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                //token错误
                //responseError(response, e.getMessage());
                return false;
            }
        }
        //如果请求头不存在token，则可能是执行登录操作或者是游客状态访问，无需检查token，直接返回true
        return true;
    }

    /**
     *判断用户是否想要登入。
     * 检查header里面是否有token字段
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        return token != null;
    }

    /**
     *执行登录操作
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("Token");
        JWTToken jwtToken = new JWTToken(token);
        //提交给Realm进行登入，如果错误会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        //如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        try {
            response.getWriter().write("{\"code\":500000,\"message\":\"未登录\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    private void responseError(ServletResponse response, String message) {
    }
}
