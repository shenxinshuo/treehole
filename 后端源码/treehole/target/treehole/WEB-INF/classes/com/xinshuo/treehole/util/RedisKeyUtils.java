package com.xinshuo.treehole.util;

public class RedisKeyUtils {

    //保存用户点赞数据的key
    public static final String MAP_KEY_USER_LIKED = "MAP_USER_LIKED";
    //保存用户被点赞数量的key
//    public static final String MAP_KEY_USER_LIKED_COUNT = "MAP_USER_LIKED_COUNT";
    //保存文章被点赞数量的key
    public static final String MAP_KEY_LOOKCOUNT = "MAP_KEY_LOOK_COUNT";


    /**
     * 拼接点赞用户ID，被点赞文章ID，被点赞回答ID作为key
     * 格式 1111::2222:3333
     *      点赞用户ID::被点赞文章ID::被点赞回答ID
     * @param likeUserID 点赞用户ID
     * @param likePostID 被点赞文章ID
     * @param likeAnswerID 被点赞回答ID
     * @return
     */
    public static String getLikedKey(Integer likeUserID, Integer likePostID, Integer likeAnswerID) {
        StringBuilder builder = new StringBuilder();
        builder.append(likeUserID);
        builder.append("::");
        builder.append(likePostID);
        builder.append("::");
        builder.append(likeAnswerID);
        return builder.toString();
    }

    /**
     * 获取hashkey，文章浏览量
     * @param qid 帖子id
     * @return String
     */
    public static String getMapHashKeyLookcount(int qid) {
        return Integer.toString(qid);
    }
}
