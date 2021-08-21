package com.xinshuo.treehole.controller;

import com.github.pagehelper.PageInfo;
import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.entity.Question;
import com.xinshuo.treehole.service.QuestionService;
import com.xinshuo.treehole.util.Constant;
import com.xinshuo.treehole.util.ResponseJSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import javax.xml.ws.Response;
import java.util.List;

@RestController
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping("/getQuestionByID/{id}")
    public ResponseJSON getQuestionByID(@PathVariable int id) {
        return new ResponseJSON(200, questionService.getQuestionByID(id), "获取问题成功");
    }


    @RequestMapping("/getQuestions")
    public ResponseJSON getQuestions(int pageNum, int pageSize) {
//        if (!SecurityUtils.getSubject().isAuthenticated()) {
//            return new ResponseJSON(500, null, "登录去吧你");
//        }
        return new ResponseJSON(200, questionService.getQuestions(pageNum, pageSize), "获取问题列表成功");
    }

    /**
     * 根据问题的种类查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getQuestionsByType")
    public ResponseJSON getQuestionsByType(String type,int pageNum, int pageSize) {
        return new ResponseJSON(200, questionService.getQuestionsByType(type, pageNum, pageSize), "获取问题列表成功");
    }

    /**
     * 根据用户ID查询问题列表
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/getQuestionsByUID/{uid}")
    public ResponseJSON getQuestionsByUID(@PathVariable int uid, int pageNum, int pageSize) {
        return new ResponseJSON(Constant.HTTP_CODE_OK, questionService.getQuestionsByUID(uid, pageNum, pageSize),"获取问题列表成功");
    }

    @RequiresAuthentication
    @RequestMapping("/addQuestion")
    public ResponseJSON addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseJSON(200, question, "添加问题成功");
    }

    @RequiresAuthentication
    @RequestMapping("/updateQuestion")
    public ResponseJSON updateQuestion(Question question) {
        questionService.updateQuestion(question);
        return new ResponseJSON(200, question, "修改问题成功");
    }

    @RequiresAuthentication
    @RequestMapping("/deleteQuestion/{id}")
    public ResponseJSON deleteQuestion(@PathVariable int id) {
        questionService.deleteQuestion(id);
        return new ResponseJSON(200, id, "删除问题成功");
    }

    @RequestMapping("/getQuestionsBySearch")
    public ResponseJSON getQuestionsBySearch(@PathParam("search") String search,int pageNum,int pageSize) {
        PageInfo<Question> questions = questionService.getQuestionsBySearch(search, pageNum, pageSize);
        return new ResponseJSON(Constant.HTTP_CODE_OK, questions,"获取问题列表成功");
    }
}
