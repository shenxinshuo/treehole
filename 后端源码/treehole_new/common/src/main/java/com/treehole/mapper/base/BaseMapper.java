package com.treehole.mapper.base;

import java.util.List;

/**
 * 通用mapper接口，封装一般的增删改查
 */
public interface BaseMapper<T> {
    int insert(T model);

    int update(T model);

    int delete(T model);

    T getByID(Integer id);

    List<T> getAll();

    List<T> search(T model);//做查询，非空都将作为查询条件
}
