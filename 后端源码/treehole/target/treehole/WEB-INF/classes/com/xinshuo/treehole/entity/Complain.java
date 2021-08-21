package com.xinshuo.treehole.entity;

import java.util.Date;

public class Complain {
    private int id;
    private int qid;
    private int aid;
    private int uid;
    private String reason;
    private Date created_time;
    private Date updated_time;
    private int deal_flag = 0;//处理标识（0：正常，1：已删除,已处理）

    public Complain(int id, int qid, int aid, int uid, String reason, Date created_time, Date updated_time, int deal_flag) {
        this.id = id;
        this.qid = qid;
        this.aid = aid;
        this.uid = uid;
        this.reason = reason;
        this.created_time = created_time;
        this.updated_time = updated_time;
        this.deal_flag = deal_flag;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }

    public Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(Date updated_time) {
        this.updated_time = updated_time;
    }

    public int getDeal_flag() {
        return deal_flag;
    }

    public void setDeal_flag(int deal_flag) {
        this.deal_flag = deal_flag;
    }
}
