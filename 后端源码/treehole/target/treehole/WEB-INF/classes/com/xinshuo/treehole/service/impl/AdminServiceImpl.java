package com.xinshuo.treehole.service.impl;

import com.xinshuo.treehole.entity.Admin;
import com.xinshuo.treehole.mapper.AdminMapper;
import com.xinshuo.treehole.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 0:登录成功 1：账号密码错误
     * @param admin
     * @return
     */
    @Override
    public int login(Admin admin) {
        Admin adminForLogin = adminMapper.getAdminByUsernameAndPassword(admin);
        if (adminForLogin != null) {
            //登录成功；
            return 0;
        } else {
            //用户名或密码错误
            return 1;
        }
    }
}
