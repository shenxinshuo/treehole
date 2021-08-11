package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.UserLike;
import com.xinshuo.treehole.service.RedisService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 为点赞功能专提供的控制器
 */
@RestController
public class RedisLikeController {

    @Autowired
    private RedisService redisService;

    //点赞
    @RequiresAuthentication
    @RequestMapping("/userLike")
    public ResponseJSON userLike(@RequestBody UserLike userLike) {
        redisService.saveLiked2Redis(userLike.getLikeUserID(), userLike.getLikePostID(), userLike.getLikeAnswerID());
        return new ResponseJSON(Constant.HTTP_CODE_OK, null, "点赞成功");
    }

    //取消点赞
    @RequiresAuthentication
    @RequestMapping("/userUnLike")
    public ResponseJSON userUnLike(@RequestBody UserLike userLike) {
        redisService.unlikeFromRedis(userLike.getLikeUserID(), userLike.getLikePostID(), userLike.getLikeAnswerID());
        return new ResponseJSON(Constant.HTTP_CODE_OK, null, "取消点赞成功");
    }

    //获取redis中关于点赞的数据
    @RequestMapping("/getDataFromRedis")
    public ResponseJSON getDataFromRedis(){
        List<UserLike> data = redisService.getLikeDataFromRedis();
        return new ResponseJSON(Constant.HTTP_CODE_OK, data, "获取redis中的点赞数据成功");
    }

    //获取redis中关于点赞的数据不删除数据
    @RequestMapping("/getDataFromRedisButNoDelete")
    public ResponseJSON getDataFromRedisButNoDelete(){
        List<UserLike> data = redisService.getLikeDataFromRedisButNoDelete();
        return new ResponseJSON(Constant.HTTP_CODE_OK, data, "获取redis中的点赞数据成功");
    }
}
