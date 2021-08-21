package com.xinshuo.treehole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.mapper.AnswerMapper;
import com.xinshuo.treehole.service.AnswerService;
import com.xinshuo.treehole.service.UserService;
import com.xinshuo.treehole.util.KeyWordFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private UserService userService;

    @Override
    public Answer getAnswerByID(int id) {
        return answerMapper.getAnswerByID(id);
    }

    @Override
    public PageInfo<Answer> getAnswerByAnswerID(int aid,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> answerByAnswerID = answerMapper.getAnswerByAnswerID(aid);
        PageInfo<Answer> pageInfo = new PageInfo(answerByAnswerID);
        return pageInfo;
    }

    @Override
    public PageInfo<Answer> getAnswerByQuestionID(int qid,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> answerByQuestionID = answerMapper.getAnswerByQuestionID(qid);
        //给answer中的username赋值
        for (Answer answer : answerByQuestionID) {
            User userByID = userService.getUserByID(answer.getUid());
            answer.setUsername(userByID.getUsername());
        }
        PageInfo<Answer> pageInfo = new PageInfo(answerByQuestionID);
        return pageInfo;
    }

    @Override
    public PageInfo<Answer> getAnswerByUID(int uid,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Answer> answers = answerMapper.getAnswerByUID(uid);
        //给answer中的username赋值
        for (Answer answer : answers) {
            User userByID = userService.getUserByID(answer.getUid());
            answer.setUsername(userByID.getUsername());
        }
        PageInfo<Answer> pageInfo = new PageInfo<>(answers);
        return pageInfo;
    }


    @Override
    public List<Answer> getAnswers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return answerMapper.getAnswers();
    }

    @Override
    public void addAnswer(Answer answer) {
        answer.setCreated_time(new Date());
        //从words.properties初始化正则表达式字符串
        KeyWordFilter.initPattern();
        //关键字过滤
        answer.setContent(KeyWordFilter.doFilter(answer.getContent()));
        answerMapper.addAnswer(answer);
    }

    @Override
    public void updateAnswer(Answer answer) {
        answerMapper.updateAnswer(answer);
    }

    @Override
    public void deleteAnswer(int id) {
        answerMapper.deleteAnswer(id);
    }

    @Override
    public int getTotalOfAnswerForAnswer(int aid) {
        return answerMapper.getTotalOfAnswerForAnswer(aid);
    }
}
