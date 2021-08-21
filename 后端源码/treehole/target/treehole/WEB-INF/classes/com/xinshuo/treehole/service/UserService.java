package com.xinshuo.treehole.service;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.User;

import java.util.List;

public interface UserService {
    public User getUserByID(int id);
    //获取所有用户
    public PageInfo<User> getUsers(int pageNum, int pageSize);
    //添加用户
    public int addUser(User user);
    //修改用户信息
    public int updateUser(User user);
    //修改头像
    public int updateUserImg(User user);
    //删除用户
    public void deleteUser(int id);
    //根据用户名查找用户
    User getUserByUsername(String username);
    //根据用户名和密码查找用户
    User getUserByUsernameAndPassword(String username, String password);
    //登录
    int login(User user);
}
