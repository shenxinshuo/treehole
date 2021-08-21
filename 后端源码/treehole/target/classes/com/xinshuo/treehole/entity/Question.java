package com.xinshuo.treehole.entity;

import java.util.Date;

public class Question {
    private int id;
    private int uid;
    private String title;
    private String type;
    private String description;
    private Date created_time;
    private int delete_flag = 0;//删除标识（0：正常，1：已删除）

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question() {
    }

    public Question(int id, int uid, String title, String type, String description, Date created_time) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.type = type;
        this.description = description;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }
}
