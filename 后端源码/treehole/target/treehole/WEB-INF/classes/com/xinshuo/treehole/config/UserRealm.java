package com.xinshuo.treehole.config;

import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.service.UserService;
import com.xinshuo.treehole.util.JWTToken;
import com.xinshuo.treehole.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了shiro授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // TODO 权限管理 拿到当前的登录对象
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User) subject.getPrincipal();
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("=====执行了shiro认证=====");

        String token = (String) authenticationToken.getPrincipal();
        //通过jwt工具类获取username
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token认证失败");
        }
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (!JWTUtil.verify(token, username)) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(token, token,getName());

//        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
//        //连接数据库查询
//        User userByUsername = userService.getUserByUsername(userToken.getUsername());
//        if (userByUsername == null) {
//            return null;
//        } else {
//            //密码认证 shiro做
//            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userByUsername, userByUsername.getPassword(), getName());
//            return authenticationInfo;
//        }
    }
}
