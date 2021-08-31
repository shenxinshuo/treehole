package com.xinshuo.treehole.service;

public interface LookCountService {

    void addLookCountRecords();//往数据库存redis中的任务记录，定时任务
    Long getLookCountOfQestion(int qid);//获取某篇帖子的浏览数
}
