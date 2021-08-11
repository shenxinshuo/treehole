package com.xinshuo.treehole.controller;

import com.alibaba.fastjson.JSON;
import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.service.UserService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.JWTUtil;
import com.xinshuo.treehole.util.ResponseJSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/noLogin")
    public ResponseJSON noLogin() {
        return new ResponseJSON(500,null, "请先登录");
    }

    @RequestMapping("/getUserByID/{id}")
    public ResponseJSON getUserByID(@PathVariable int id) {
        User user = userService.getUserByID(id);
        return user == null ? null : new ResponseJSON(200, user, "查询学会成功");
    }

    //获取所有用户
    @RequestMapping("/getUsers")
    public ResponseJSON getUsers(int pageNum, int pageSize) {
        return new ResponseJSON(200, userService.getUsers(pageNum, pageSize), "查询学生成功");
    }
    //添加用户
    //@RequiresAuthentication
    @RequestMapping("/addUser")
    public ResponseJSON addUser(@RequestParam(value = "user",required = false) String user,
                                @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
        System.out.println("aaa");
        User u = JSON.parseObject(user, User.class);
        if (file != null) {
            String name = file.getOriginalFilename();
            InputStream in = file.getInputStream();
            File mkdir = new File("E:/treehole/upload/");
            if (!mkdir.exists()) {
                mkdir.mkdirs();
            }
            FileOutputStream os = new FileOutputStream(mkdir.getPath() + "\\" + u.getUsername()+".jpg");
            int len = 0;
            while((len = in.read()) != -1) {
                os.write(len);
            }
            os.flush();
            in.close();
            os.close();
            u.setImg(u.getUsername()+".jpg");
        }else {
            u.setImg("head.jpg");
        }


        //return null;
        int result = userService.addUser(u);
        if (result == 0) {
            return new ResponseJSON(200, u, "添加学生成功");
        } else {
            return new ResponseJSON(Constant.HTTP_CODE_BAD_REQUEST,u, "用户名已被注册过");
        }
    }
    //修改用户信息
    @RequiresAuthentication
    @RequestMapping("/updateUser")
    public ResponseJSON updateUser(@RequestBody User user) {
        int result = userService.updateUser(user);
        if (result == 0){
            return new ResponseJSON(Constant.HTTP_CODE_OK, user, "修改学生成功");
        } else {
            return new ResponseJSON(Constant.HTTP_CODE_BAD_REQUEST, user, "用户名已被注册过");
        }
    }

    //修改用户头像
    @RequiresAuthentication
    @RequestMapping("/updateUserImg")
    public ResponseJSON updateUserImg(@RequestParam(value = "user",required = false) String user,
                                      @RequestParam(value = "file",required = false) MultipartFile file) throws IOException {
        User u = JSON.parseObject(user, User.class);
        String name = file.getOriginalFilename();
        InputStream in = file.getInputStream();
        File mkdir = new File("E:/treehole/upload/");
        if (!mkdir.exists()) {
            mkdir.mkdirs();
        }
        FileOutputStream os = new FileOutputStream(mkdir.getPath() + "\\" + u.getUsername()+".jpg");
        int len = 0;
        while((len = in.read()) != -1) {
            os.write(len);
        }
        os.flush();
        in.close();
        os.close();
        u.setImg(u.getUsername()+".jpg");
        //return null;
        int result = userService.updateUserImg(u);
        if (result == 0) {
            return new ResponseJSON(200, u, "修改头像成功");
        } else {
            return new ResponseJSON(Constant.HTTP_CODE_BAD_REQUEST,u, "修改头像失败");
        }
    }

    //删除用户
    @RequiresAuthentication
    @RequestMapping("/deleteUser/{id}")
    public ResponseJSON deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseJSON(200, id, "删除学生成功");
    }

    //根据用户名查找用户
    @RequestMapping("/getUserByUsername/{username}")
    public ResponseJSON getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        ResponseJSON result = new ResponseJSON();
        if (user == null) {
            //用户不存在
            result.setStatus(Constant.HTTP_CODE_NO_CONTENT);
            result.setData(null);
            result.setMessage("该用户不存在");
        } else {
            result.setStatus(Constant.HTTP_CODE_OK);
            result.setData(user);
            result.setMessage("查询成功");
        }
        return result;
    }

    //登录
//    @RequestMapping("/user/login")
//    public ResponseJSON login(@RequestBody User user) {
//        int loginResult = userService.login(user);
//        ResponseJSON result = new ResponseJSON();
//        if (loginResult == 0) {
//            //查无此人
//            result.setStatus(Constant.HTTP_CODE_NO_CONTENT);
//            result.setData(user);
//            result.setMessage("该用户不存在");
//        } else if (loginResult == 1) {
//            //密码错误
//            result.setStatus(Constant.HTTP_CODE_OK);
//            result.setData(user);
//            result.setMessage("密码输入有误");
//        } else {
//            //登录成功
//            result.setStatus(Constant.HTTP_CODE_OK);
//            result.setData(user);
//            result.setMessage("登录成功");
//        }
//        return result;
//    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/user/login")
    public ResponseJSON login(@RequestBody User user) {
//        ResponseJSON result = new ResponseJSON();
        System.out.println("用户登录");
        System.out.println(user.getUsername()+"---"+user.getPassword());
        User userByUsername = userService.getUserByUsername(user.getUsername());
        if (userByUsername == null) {
            throw new AuthenticationException("用户不存在");
        } else {
            if (!userByUsername.getPassword().equals(user.getPassword())) {
                throw new AuthenticationException("密码错误");
            } else {
                Map<String, Object> map = new HashMap<>();
                map.put("currentUser", userByUsername);
                map.put("token", JWTUtil.createToken(user.getUsername()));
                return new ResponseJSON(Constant.HTTP_CODE_OK, map, "登录成功");
            }
        }

    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/user/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
