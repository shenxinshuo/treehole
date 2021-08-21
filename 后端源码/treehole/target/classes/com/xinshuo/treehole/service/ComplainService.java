package com.xinshuo.treehole.service;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Complain;

import java.util.List;

public interface ComplainService {
    public void addComplain(Complain complain);
    public void dealComplain(Complain complain, String isDeleteThing);
    public void deleteComplain(int id);
    public PageInfo<Complain> getComplainsOfNoDeal(int pageNum, int pageSize);
    public Complain getComplainByID(int id);
}
