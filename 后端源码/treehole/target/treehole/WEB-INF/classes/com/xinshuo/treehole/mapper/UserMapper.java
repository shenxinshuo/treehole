package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    //根据ID获取用户
    public User getUserByID(int id);
    //获取所有用户
    public List<User> getUsers();
    //添加用户
    public void addUser(User user);
    //修改用户信息
    public void updateUser(User user);
    //修改用户头像
    public void updateUserImg(User user);
    //删除用户
    public void deleteUser(int id);
    //根据用户名查找用户
    User getUserByUsername(String username);
    //根据用户名和密码查找用户
    User getUserByUsernameAndPassword(String username, String password);
}
