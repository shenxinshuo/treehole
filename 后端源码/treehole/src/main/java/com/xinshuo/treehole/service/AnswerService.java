package com.xinshuo.treehole.service;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Answer;

import java.util.List;

public interface AnswerService {
    Answer getAnswerByID(int id);
    PageInfo getAnswerByAnswerID(int aid, int pageNum, int pageSize);
    PageInfo getAnswerByQuestionID(int qid,int pageNum, int pageSize);
    PageInfo getAnswerByUID(int uid,int pageNum, int pageSize);
    List<Answer> getAnswers(int pageNum, int pageSize);
    void addAnswer(Answer answer);
    void updateAnswer(Answer answer);
    void deleteAnswer(int id);
    int getTotalOfAnswerForAnswer(int aid);
}
