package com.xinshuo.treehole.service.impl;

import com.xinshuo.treehole.entity.UserLike;
import com.xinshuo.treehole.mapper.UserLikeMapper;
import com.xinshuo.treehole.service.RedisService;
import com.xinshuo.treehole.service.UserLikeService;
import com.xinshuo.treehole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserLikeServiceImpl implements UserLikeService {

    @Autowired
    private UserLikeMapper userLikeMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public int getLikeCountOfQuestion(int qid) {
        int likeCountOfQuestion = userLikeMapper.getLikeCountOfQuestion(qid);
        //去redis查询缓存中的数据
        int count = redisService.getLikeCountOfQuestionFromRedis(qid);
        return likeCountOfQuestion + count;
    }

    @Override
    public int getLikeCountOfAnswer(int aid) {
        int likeCountOfAnswer = userLikeMapper.getLikeCountOfAnswer(aid);
        //去redis查询缓存中的数据
        int count = redisService.getLikeCountOfAnswerFromRedis(aid);
        return likeCountOfAnswer + count;
    }

    @Override
    public List<Integer> getLikeQuestionsOfUser(int uid) {
        List<Integer> list = userLikeMapper.getLikeQuestionsOfUser(uid);
        return list;
    }

    @Override
    public List<Integer> getLikeAnswerOfUser(int uid) {
        List<Integer> list = userLikeMapper.getLikeAnswerOfUser(uid);
        return list;
    }

    @Override
    public void addLikeRecord(UserLike userLike) {
        userLikeMapper.addLikeRecord(userLike);
    }

    @Override
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void addLikeRecords() {
        List<UserLike> list = redisService.getLikeDataFromRedis();
        for (UserLike ul : list) {
            System.out.println("触发定时任务，向数据库中存入redis中的数据，并删除redis中数据，执行时间："+new Date().toString());
            userLikeMapper.addLikeRecord(ul);
        }
    }

    @Override
    public boolean isUserLikedThisQuestion(int uid, int qid) {
        UserLike userLike = userLikeMapper.isUserLikedThisQuestion(uid, qid);
        if (userLike == null) {
            return false;
        }else if (userLike.getStatus() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isUserLikedThisAnswer(int uid, int aid) {
        UserLike userLike = userLikeMapper.isUserLikedThisAnswer(uid, aid);
        if (userLike == null) {
            return false;
        } else if (userLike.getStatus() == 0) {
            return false;
        }
        return true;
    }

    @Override
    @Scheduled(cron = "0 * * * * ?")
    public void testScheduled() {
//        System.out.println("触发定时任务,触发时间："+new Date().toString());
    }
}
