package com.xinshuo.treehole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.entity.Complain;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.mapper.ComplainMapper;
import com.xinshuo.treehole.service.AnswerService;
import com.xinshuo.treehole.service.ComplainService;
import com.xinshuo.treehole.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainMapper complainMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @Override
    public void addComplain(Complain complain) {
        complain.setCreated_time(new Date());
        complainMapper.addComplain(complain);
    }

    //TODO
    @Override
    public void dealComplain(Complain complain, String isDeleteThing) {
        complain.setUpdated_time(new Date());
        complain.setDeal_flag(1);
        complainMapper.updateComplain(complain);
        if (complain.getQid() != 0) {
            //处理对Question的举报
            if ("true".equals(isDeleteThing)) {
                //软删除该问题
                Question question = questionService.getQuestionByID(complain.getQid());
                question.setDelete_flag(1);
                questionService.updateQuestion(question);
            }
        }
        if (complain.getAid() != 0) {
            //处理对Answer的举报
            if ("true".equals(isDeleteThing)) {
                //软删除该回答
                Answer answer = answerService.getAnswerByID(complain.getAid());
                answer.setDelete_flag(1);
                answerService.updateAnswer(answer);
            }
        }
    }

    @Override
    public void deleteComplain(int id) {
        complainMapper.deleteComplain(id);
    }

    @Override
    public PageInfo<Complain> getComplainsOfNoDeal(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Complain> complains = complainMapper.getComplainsOfNoDeal();
        PageInfo<Complain> pageInfo = new PageInfo<>(complains);
        return pageInfo;
    }

    @Override
    public Complain getComplainByID(int id) {
        return complainMapper.getComplainByID(id);
    }
}
