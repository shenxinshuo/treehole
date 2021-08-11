package com.xinshuo.treehole;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.entity.User;
import com.xinshuo.treehole.service.AnswerService;
import com.xinshuo.treehole.service.QuestionService;
import com.xinshuo.treehole.service.UserLikeService;
import com.xinshuo.treehole.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TreeholeApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserLikeService userLikeService;

    @Test
    void contextLoads() {
    }

    @Test
    void testGetUserByID() {
        User user = userService.getUserByID(1);
        System.out.println(user);
    }

    @Test
    void testGetUsers() {
        List<User> users = userService.getUsers(1, 2);
        System.out.println(users);
    }


    @Test
    void testGetAnswerByQuestionID() {
        PageInfo answers = answerService.getAnswerByQuestionID(1, 1, 10);
//        for (Answer answer: answers) {
//            System.out.println(answer);
//        }
    }

    @Test
    void testGetAnswerByAnswerID() {
        PageInfo answers = answerService.getAnswerByAnswerID(1, 1, 10);
        System.out.println(answers);
    }

    @Test
    void testIsUserLikedThisQuestion() {
        userLikeService.isUserLikedThisQuestion(1,12);
    }


}
