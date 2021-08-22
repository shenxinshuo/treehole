package com.xinshuo.treehole.service.impl;

import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.service.LookCountService;
import com.xinshuo.treehole.service.QuestionService;
import com.xinshuo.treehole.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LookCountServiceImpl implements LookCountService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private QuestionService questionService;

    @Override
    @Scheduled(cron = "0 0 0/1 * * ? ")//一个小时
//    @Scheduled(cron = "0 */1 * * * ?")//一分钟
    public void addLookCountRecords() {
        List<LookCount> lookCountFromRedis = redisService.getLookCountFromRedis();
        for (LookCount lookCount : lookCountFromRedis) {
            //调用questionService修改数据库
            System.out.println("触发浏览量定时任务，向数据库中存入redis中的数据，并删除redis中数据，执行时间：" + new Date().toString());
            Long count = questionService.getQuestionByID(lookCount.getQid()).getLookCount();
            lookCount.setLookCount(count + lookCount.getLookCount());
            questionService.addLookCountByQid(lookCount);
        }
    }


    @Override
    public Long getLookCountOfQestion(int qid) {
        //读redis
        Long lookCount = redisService.getLookCountOfQestion(qid);
        return lookCount;
    }
}
