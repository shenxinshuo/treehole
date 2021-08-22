package com.xinshuo.treehole.service;

import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.UserLike;

import java.util.List;

public interface RedisService {

    /**
     * 点赞。状态为1
     * @param likeUserID 点赞人ID
     * @param likePostID 被点赞文章ID
     * @param likeAnswerID 被点赞回答ID
     */
    void saveLiked2Redis(Integer likeUserID, Integer likePostID, Integer likeAnswerID);

    /**
     * 取消点赞，将状态改为0，
     * @param likeUserID 点赞人ID
     * @param likePostID 被点赞文章ID
     * @param likeAnswerID 被点赞回答ID
     */
    void unlikeFromRedis(Integer likeUserID, Integer likePostID, Integer likeAnswerID);

    /**
     * 从redis中删除一条点赞数据
     * @param likeUserID 点赞人ID
     * @param likePostID 被点赞文章ID
     * @param likeAnswerID 被点赞回答ID
     */
    void deleteLikedFromRedis(Integer likeUserID,Integer likePostID, Integer likeAnswerID);

//    /**
//     * 该用户的点赞数加1
//     * @param likedUserId
//     */
//    void incrementLikedCount(String likedUserId);
//
//    /**
//     * 该用户的点赞数减1
//     * @param likedUserId
//     */
//    void decrementLikedCount(String likedUserId);

    /**
     * 获取redis中所有点赞数据
     * @return
     */
    List<UserLike> getLikeDataFromRedis();

    /**
     * 获取redis中所有点赞数据 取完数据不删除数据
     * @return
     */
    List<UserLike> getLikeDataFromRedisButNoDelete();

    /**
     * 获取redis中某问题的点赞数
     * @param qid 待查询问题ID
     * @return 点赞数
     */
    int getLikeCountOfQuestionFromRedis(int qid);

    /**
     * 获取redis中某回答的点赞数
     * @param aid 待查询回答ID
     * @return 点赞数
     */
    int getLikeCountOfAnswerFromRedis(int aid);

//    /**
//     * 获取Redis中存储的所有点赞数量
//     * @return
//     */
//    List<LikedCountDTO> getLikedCountFromRedis();

    /**
     * 帖子浏览量加1
     * @param qid 帖子id
     */
    void addLookCount(int qid);

    /**
     * 获取浏览量
     * @return int
     */
    List<LookCount> getLookCountFromRedis();

    /**
     * 获取浏览量(不删除数据)
     * @return int
     */
    List<LookCount> getLookCountFromRedisButNotDelete();

    /**
     * 获取某篇帖子的浏览量
     * @param qid 帖子id
     * @return 浏览量
     */
    Long getLookCountOfQestion(int qid);

}
