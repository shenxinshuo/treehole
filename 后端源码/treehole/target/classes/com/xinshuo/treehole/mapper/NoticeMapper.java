package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper {
    Notice getNotice();//获取公告
    void updateNotice(Notice notice);//修改公告
}
