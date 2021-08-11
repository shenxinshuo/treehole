package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Complain;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ComplainMapper {
    void addComplain(Complain complain);
    void updateComplain(Complain complain);
    void deleteComplain(int id);
    List<Complain> getComplainsOfNoDeal();
    Complain getComplainByID(int id);
}
