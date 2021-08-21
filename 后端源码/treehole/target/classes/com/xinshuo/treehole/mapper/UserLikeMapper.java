package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.entity.UserLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserLikeMapper {
    int getLikeCountOfQuestion(int qid);//获取某篇文章点赞数量
    int getLikeCountOfAnswer(int aid);//获取某个回答点赞数量
    List<Integer> getLikeQuestionsOfUser(int uid);//获取某人点赞的问题
    List<Integer> getLikeAnswerOfUser(int uid);//获取某人点赞的回复
    void addLikeRecord(UserLike userLike);//往数据库存一条记录
    UserLike isUserLikedThisQuestion(int uid,int qid);//查询数据库中某人跟某篇文章的记录
    UserLike isUserLikedThisAnswer(int uid,int aid);//查询数据库中某人跟某个回答的记录
}
