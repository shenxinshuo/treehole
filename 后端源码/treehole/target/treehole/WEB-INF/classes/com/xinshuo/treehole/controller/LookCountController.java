package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.UserLike;
import com.xinshuo.treehole.service.RedisService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LookCountController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/getLookCountFromRedis")
    public ResponseJSON getLookCountFromRedis() {
        List<LookCount> lookCountFromRedis = redisService.getLookCountFromRedis();
        return new ResponseJSON(Constant.HTTP_CODE_OK, lookCountFromRedis, "查询浏览量记录成功");
    }

    @RequestMapping("/getLookCountFromRedisButNotDelete")
    public ResponseJSON getLookCountFromRedisButNotDelete() {
        List<LookCount> lookCountFromRedis = redisService.getLookCountFromRedisButNotDelete();
        return new ResponseJSON(Constant.HTTP_CODE_OK, lookCountFromRedis, "查询浏览量记录成功");
    }
}
