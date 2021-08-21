package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.UserLike;
import com.xinshuo.treehole.service.UserLikeService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserLikeController {

    @Autowired
    private UserLikeService userLikeService;

    /**
     * 查询某个问题的点赞数
     * @param qid 待查询问题ID
     * @return
     */
    @RequestMapping("/getLikeCountOfQuestion/{qid}")
    public ResponseJSON getLikeCountOfQuestion(@PathVariable int qid) {
        int likeCountOfQuestion = userLikeService.getLikeCountOfQuestion(qid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, likeCountOfQuestion, "获取某个问题的点赞数成功");
    }

    /**
     * 查询某个回答的点赞数
     * @param aid 待查询回答ID
     * @return
     */
    @RequestMapping("/getLikeCountOfAnswer/{aid}")
    public ResponseJSON getLikeCountOfAnswer(@PathVariable int aid) {
        int likeCountOfAnswer = userLikeService.getLikeCountOfAnswer(aid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, likeCountOfAnswer, "获取某个回答的点赞数成功");
    }

    /**
     * 查询某个用户点赞过的问题列表
     * @param uid 用户ID
     * @return
     */
    @RequestMapping("/getLikeQuestionsOfUser/{uid}")
    public ResponseJSON getLikeQuestionsOfUser(@PathVariable int uid) {
        List<Integer> list = userLikeService.getLikeQuestionsOfUser(uid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, list, "获取某个用户点赞过的问题列表成功");
    }

    /**
     * 查询某个用户点赞过的回答列表
     * @param uid 用户ID
     * @return
     */
    @RequestMapping("/getLikeAnswerOfUser/{uid}")
    public ResponseJSON getLikeAnswerOfUser(@PathVariable int uid) {
        List<Integer> list = userLikeService.getLikeAnswerOfUser(uid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, list, "获取某个用户点赞过的回答列表成功");
    }

    /**
     * 向数据库添加记录
     * @param userLike 用户点赞实体类
     * @return
     */
    @RequestMapping("/addLikeRecord")
    public ResponseJSON addLikeRecord(@RequestBody UserLike userLike) {
        userLikeService.addLikeRecord(userLike);
        return new ResponseJSON(Constant.HTTP_CODE_OK, userLike, "添加点赞记录成功");
    }

    //查询用户是否点赞过某篇文章
    @RequestMapping("/isUserLikedThisQuestion/{uid}/{qid}")
    public ResponseJSON isUserLikedThisQuestion(@PathVariable int uid, @PathVariable int qid) {
        boolean userLikedThisQuestion = userLikeService.isUserLikedThisQuestion(uid, qid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, userLikedThisQuestion,"获取信息成功");
    }

    //查询用户是否点赞过某个回答
    @RequestMapping("/isUserLikedThisAnswer/{uid}/{aid}")
    public ResponseJSON isUserLikedThisAnswer(@PathVariable int uid, @PathVariable int aid) {
        boolean userLikedThisAnswer = userLikeService.isUserLikedThisAnswer(uid, aid);
        return new ResponseJSON(Constant.HTTP_CODE_OK, userLikedThisAnswer, "获取信息成功");
    }

}
