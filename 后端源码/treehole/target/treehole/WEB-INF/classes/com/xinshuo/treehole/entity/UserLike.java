package com.xinshuo.treehole.entity;

public class UserLike {
    private Integer id;//主键ID
    private Integer likeUserID;//点赞人ID
    private Integer likePostID;//被点赞文章ID
    private Integer likeAnswerID;//被点赞回答ID
    private Integer status = 0;//点赞的状态，默认未点赞0 0：未点赞 1：已点赞

    public UserLike() {
    }

    public UserLike(Integer likeUserID, Integer likePostID, Integer likeAnswerID, Integer status) {
        this.likeUserID = likeUserID;
        this.likePostID = likePostID;
        this.likeAnswerID = likeAnswerID;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLikeUserID() {
        return likeUserID;
    }

    public void setLikeUserID(Integer likeUserID) {
        this.likeUserID = likeUserID;
    }

    public Integer getLikePostID() {
        return likePostID;
    }

    public void setLikePostID(Integer likePostID) {
        this.likePostID = likePostID;
    }

    public Integer getLikeAnswerID() {
        return likeAnswerID;
    }

    public void setLikeAnswerID(Integer likeAnswerID) {
        this.likeAnswerID = likeAnswerID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
