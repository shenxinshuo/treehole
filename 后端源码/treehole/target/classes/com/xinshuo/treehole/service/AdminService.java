package com.xinshuo.treehole.service;

import com.xinshuo.treehole.entity.Admin;
import com.xinshuo.treehole.entity.Complain;
import org.springframework.stereotype.Service;

public interface AdminService {
    int login(Admin admin);
}
