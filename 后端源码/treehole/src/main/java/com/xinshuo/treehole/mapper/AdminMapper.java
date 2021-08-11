package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    Admin getAdminByUsernameAndPassword(Admin admin);
}
