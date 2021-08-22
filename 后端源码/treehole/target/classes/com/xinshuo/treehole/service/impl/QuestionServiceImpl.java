package com.xinshuo.treehole.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.LookCount;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.mapper.QuestionMapper;
import com.xinshuo.treehole.mapper.UserMapper;
import com.xinshuo.treehole.service.LookCountService;
import com.xinshuo.treehole.service.QuestionService;
import com.xinshuo.treehole.service.RedisService;
import com.xinshuo.treehole.util.KeyWordFilter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private LookCountService lookCountService;


    @Override
    public Question getQuestionByID(int id) {
        //浏览量加1
        redisService.addLookCount(id);
        Question question = questionMapper.getQuestionByID(id);
        long count = question.getLookCount() + redisService.getLookCountOfQestion(question.getId());
        //大于10000就除以1000，前端以X.X万展示
        count = count > 10000 ? count / 1000 : count;
        question.setLookCount(count);
        return question;
    }

    @Override
    public PageInfo<Question> getQuestions(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.getQuestions();
        for (Question question : questions) {
            question.setDescription(question.getDescription()
                    .substring(0, Math.min(question.getDescription().length(), 100)));
            //将redis中的浏览量加进去
            long count = question.getLookCount() + lookCountService.getLookCountOfQestion(question.getId());
            //大于10000就除以1000，前端以X.X万展示
            count = count > 10000 ? count / 1000 : count;
            question.setLookCount(count);
            System.out.println(question.getDescription());
        }
        PageInfo<Question> pageinfo = new PageInfo<>(questions);
        return pageinfo;
    }

    @Override
    public PageInfo<Question> getQuestionsByType(String type, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.getQuestionsByType(type);
        for (Question question : questions) {
            question.setDescription(question.getDescription()
                    .substring(0, Math.min(question.getDescription().length(), 100)));
            //将redis中的浏览量加进去
            long count = question.getLookCount() + lookCountService.getLookCountOfQestion(question.getId());
            //大于10000就除以1000，前端以X.X万展示
            count = count > 10000 ? count / 1000 : count;
            question.setLookCount(count);
            System.out.println(question.getDescription());
        }
        PageInfo<Question> pageinfo = new PageInfo<>(questions);
        return pageinfo;
    }

    @Override
    public PageInfo<Question> getQuestionsByUID(int uid, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.getQuestionsByUID(uid);
        for (Question question : questions) {
            question.setDescription(question.getDescription()
                    .substring(0, Math.min(question.getDescription().length(), 100)));
            //将redis中的浏览量加进去
            long count = question.getLookCount() + lookCountService.getLookCountOfQestion(question.getId());
            //大于10000就除以1000，前端以X.X万展示
            count = count > 10000 ? count / 1000 : count;
            question.setLookCount(count);
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    @Override
    public void addQuestion(Question question) {
        question.setCreated_time(new Date());
        //question.setUid(currentUser.getId());
        //从words.properties初始化正则表达式字符串
        KeyWordFilter.initPattern();
        //进行关键字过滤
        question.setTitle(KeyWordFilter.doFilter(question.getTitle()));
        question.setDescription(KeyWordFilter.doFilter(question.getDescription()));
        questionMapper.addQuestion(question);
    }

    @Override
    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
    }

    @Override
    public void deleteQuestion(int id) {
        questionMapper.deleteQuestion(id);
    }

    @Override
    public PageInfo<Question> getQuestionsBySearch(String search, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Question> questions = questionMapper.getQuestionsBySearch(search);
        for (Question question : questions) {
            question.setDescription(question.getDescription()
                    .substring(0, Math.min(question.getDescription().length(), 100)));
            System.out.println(question.getDescription());
        }
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        return pageInfo;
    }

    @Override
    public void addLookCountByQid(LookCount lookCount) {
        questionMapper.addLookCountByQid(lookCount);
    }
}
