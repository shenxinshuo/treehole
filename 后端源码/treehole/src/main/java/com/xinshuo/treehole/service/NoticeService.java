package com.xinshuo.treehole.service;

import com.xinshuo.treehole.entity.Notice;

public interface NoticeService {
    String getNotice();//获取公告
    void updateNotice(Notice notice);//修改公告
}
