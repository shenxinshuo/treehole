package com.xinshuo.treehole.service;

import com.xinshuo.treehole.entity.UserLike;

import java.util.List;

public interface UserLikeService {
    int getLikeCountOfQuestion(int qid);//获取某篇文章点赞数量
    int getLikeCountOfAnswer(int aid);//获取某个回答点赞数量
    List<Integer> getLikeQuestionsOfUser(int uid);//获取某人点赞的问题
    List<Integer> getLikeAnswerOfUser(int uid);//获取某人点赞的回复
    void addLikeRecord(UserLike userLike);//往数据库存一条记录
    void addLikeRecords();//往数据库存redis中的任务记录，定时任务
    boolean isUserLikedThisQuestion(int uid, int qid);//查询某人是不是点赞过某问题
    boolean isUserLikedThisAnswer(int uid, int aid);//查询某人是不是点赞过某回答
    void testScheduled();//测试定时器
}
