package com.treehole.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.treehole.model.base.BaseModel;

import java.util.Date;

public class User extends BaseModel {
    private String username;
    private String password;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date birthday;
    private String area;
    private String img = "head.jpg";
    private int lock_flag = 0;//锁定标识（0：正常，1：锁定）
    private String telephone;

    public User() {
    }

    public User(int id, String username, String password, Date birthday, String area) {
        this.setId(id);
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


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", area='" + area + '\'' +
                ", img='" + img + '\'' +
                ", lock_flag=" + lock_flag +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
