package com.xinshuo.treehole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.mapper.UserMapper;
import com.xinshuo.treehole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByID(int id) {
        return userMapper.getUserByID(id);
    }

    @Override
    public PageInfo<User> getUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.getUsers();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    /**
     *
     * @param user
     * @return 0:添加成功 1：username被注册过
     */
    @Override
    public int addUser(User user) {
        //判断username是否被注册过
        User u = userMapper.getUserByUsername(user.getUsername());
        if (u != null) {
            //被注册过
            return 1;
        }
        user.setCreated_time(new Date());
        user.setUpdated_time(new Date());
        userMapper.addUser(user);
        return 0;
    }

    /**
     *
     * @param user
     * @return 0:修改成功 1：username被注册过 2：ID不存在
     */
    @Override
    public int updateUser(User user) {
        User userInDB = userMapper.getUserByID(user.getId());

        if (userInDB == null) {
            //该ID不存在
            return 2;
        } else {
            if (userInDB.getUsername().equals(user.getUsername())) {
                User after = this.updateUserField(user, userInDB);
                userMapper.updateUser(after);
                return 0;
            } else {
                if (userMapper.getUserByUsername(user.getUsername()) == null) {
                    //可以改
                    User after = this.updateUserField(user,userInDB);
                    userMapper.updateUser(after);
                    return 0;
                } else {
                    return 1;
                }
            }
        }
    }

    @Override
    public int updateUserImg(User user) {
        userMapper.updateUserImg(user);
        return 0;
    }

    private User updateUserField(User before, User after) {
        after.setUsername(before.getUsername());
        after.setBirthday(before.getBirthday());
        after.setPassword(before.getPassword());
        after.setArea(before.getArea());
        after.setTelephone(before.getTelephone());
        after.setUpdated_time(new Date());
        //after.setImg(before.getUsername()+".jpg");
        return after;
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        return userMapper.getUserByUsernameAndPassword(username, password);
    }

    /**
     *
     * @param user
     * @return
     * 0：查无此人 1：密码不正确 2：登录成功
     */
    @Override
    public int login(User user) {
        User userByUsername = this.getUserByUsername(user.getUsername());
        if (userByUsername == null) {
            return 0;
        } else {
            User userForLogin = this.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
            if (userForLogin == null) {
                //密码不正确
                return 1;
            } else {
                //登录成功
                return 2;
            }
        }
    }
}
