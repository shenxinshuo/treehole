package com.xinshuo.treehole.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public class User {
    private int id;
    private String username;
    private String password;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date birthday;
    private String area;
    private String img = "head.jpg";
    private Date created_time;
    private Date updated_time;
    private int lock_flag = 0;//锁定标识（0：正常，1：锁定）
    private String telephone;
    public User() {
    }

    public User(int id, String username, String password, Date birthday, String area) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
        this.area = area;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getLock_flag() {
        return lock_flag;
    }

    public void setLock_flag(int lock_flag) {
        this.lock_flag = lock_flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", area='" + area + '\'' +
                ", img='" + img + '\'' +
                ", created_time=" + created_time +
                ", updated_time=" + updated_time +
                '}';
    }
}
