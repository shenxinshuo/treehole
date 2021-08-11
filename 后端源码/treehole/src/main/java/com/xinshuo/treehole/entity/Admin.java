package com.xinshuo.treehole.entity;

import java.util.Date;

public class Admin {
    private int id;
    private String name;
    private String password;
    private Date created_time;

    public Admin(int id, String name, String password, Date created_time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.created_time = created_time;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(Date created_time) {
        this.created_time = created_time;
    }
}
