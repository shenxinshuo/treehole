package com.xinshuo.treehole.service;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.Question;

import java.util.List;

public interface QuestionService {
    Question getQuestionByID(int id);
    PageInfo<Question> getQuestions(int pageNum, int pageSize);
    PageInfo<Question> getQuestionsByType(String type, int pageNum, int pageSize);
    PageInfo getQuestionsByUID(int uid, int pageNum, int pageSize);
    void addQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(int id);
    PageInfo<Question> getQuestionsBySearch(String search,int pageNum,int pageSize);
    void addLookCountByQid(LookCount lookCount);
}
