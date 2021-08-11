package com.xinshuo.treehole.service.impl;

import com.xinshuo.treehole.entity.Notice;
import com.xinshuo.treehole.mapper.NoticeMapper;
import com.xinshuo.treehole.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public String getNotice() {
        Notice notice = noticeMapper.getNotice();
        return notice.getNotice();
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeMapper.updateNotice(notice);
    }
}
