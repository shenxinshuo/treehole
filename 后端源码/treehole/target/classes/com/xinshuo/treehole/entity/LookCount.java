package com.xinshuo.treehole.entity;

/**
 * 浏览量实体类
 */
public class LookCount {
    /**
     * 帖子id
     */
    private Integer qid;
    /**
     * 浏览量
     */
    private Long lookCount;

    public LookCount(Integer qid, Long lookCount) {
        this.qid = qid;
        this.lookCount = lookCount;
    }

    public LookCount() {
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Long getLookCount() {
        return lookCount;
    }

    public void setLookCount(Long lookCount) {
        this.lookCount = lookCount;
    }
}
