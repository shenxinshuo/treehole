package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.Admin;
import com.xinshuo.treehole.service.AdminService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/login")
    public ResponseJSON login(@RequestBody Admin admin, HttpSession session) {
        int result = adminService.login(admin);
        if (result == 0) {
            //登录成功
            //session.setAttribute("currentAdmin",admin);
            return new ResponseJSON(Constant.HTTP_CODE_OK, admin, "管理员登录成功");
        } else {
            //账号密码错误
            return new ResponseJSON(Constant.HTTP_CODE_NO_CONTENT, admin, "账号或密码错误");
        }
    }

    @RequestMapping("/getCurrentAdmin")
    public ResponseJSON getCurrentAdmin(HttpSession session) {
        Admin currentAdmin = (Admin) session.getAttribute("currentAdmin");
        return new ResponseJSON(Constant.HTTP_CODE_OK, currentAdmin, "获取当前登录管理员成功");
    }
}
