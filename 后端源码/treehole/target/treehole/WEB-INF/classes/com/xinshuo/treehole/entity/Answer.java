package com.xinshuo.treehole.entity;

import java.util.Date;

public class Answer {
    private int id;
    private int qid;
    private int aid;
    private int uid;
    private String username;
    private String content;
    private Date created_time;
    private int delete_flag = 0;//删除标识（0：正常，1：已删除）

    //属于哪个用户 多对一
    private User user;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Answer() {
    }

    public Answer(int id, int qid, int aid, int uid, String content, Date created_time) {
        this.id = id;
        this.qid = qid;
        this.aid = aid;
        this.uid = uid;
        this.content = content;
        this.created_time = created_time;
    }

    public int getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(int delete_flag) {
        this.delete_flag = delete_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", qid=" + qid +
                ", aid=" + aid +
                ", uid=" + uid +
                ", username='" + username + '\'' +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", created_time=" + created_time +
                ", delete_flag=" + delete_flag +
                '}';
    }
}
