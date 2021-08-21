package com.xinshuo.treehole.controller;

import com.xinshuo.treehole.entity.Answer;
import com.xinshuo.treehole.service.AnswerService;
import com.xinshuo.treehole.util.ResponseJSON;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @RequestMapping("/getAnswerByID/{id}")
    public ResponseJSON getAnswerByID(@PathVariable int id) {
        return new ResponseJSON(200, answerService.getAnswerByID(id), "获取回复成功");
    }

    @RequestMapping("/getAnswerByAnswerID/{aid}")
    public ResponseJSON getAnswerByAnswerID(@PathVariable int aid,  int pageNum, int pageSize) {
        return new ResponseJSON(200, answerService.getAnswerByAnswerID(aid, pageNum, pageSize), "获取回复成功");
    }

    @RequestMapping("/getAnswerByQuestionID/{qid}")
    public ResponseJSON getAnswerByQuestionID(@PathVariable int qid, int pageNum, int pageSize) {
        return new ResponseJSON(200, answerService.getAnswerByQuestionID(qid, pageNum, pageSize), "获取回复列表成功");
    }

    @RequestMapping("/getAnswerByUID/{uid}")
    public ResponseJSON getAnswerByUID(@PathVariable int uid, int pageNum, int pageSize) {
        return new ResponseJSON(200, answerService.getAnswerByUID(uid, pageNum, pageSize), "获取回复列表成功");
    }

    @RequestMapping("/getAnswers")
    public ResponseJSON getAnswers(int pageNum, int pageSize) {
        return new ResponseJSON(200, answerService.getAnswers(pageNum, pageSize), "获取回复列表成功");
    }

    @RequiresAuthentication
    @RequestMapping("/addAnswer")
    public ResponseJSON addAnswer(@RequestBody Answer answer) {
        answerService.addAnswer(answer);
        return new ResponseJSON(200, answer, "添加回复成功");
    }

    @RequiresAuthentication
    @RequestMapping("/updateAnswer")
    public ResponseJSON updateAnswer(Answer answer) {
        answerService.updateAnswer(answer);
        return new ResponseJSON(200, answer, "修改回复成功");
    }

    @RequiresAuthentication
    @RequestMapping("/deleteAnswer/{id}")
    public ResponseJSON deleteAnswer(@PathVariable int id) {
        answerService.deleteAnswer(id);
        return new ResponseJSON(200, id, "删除回复成功");
    }

    @RequestMapping("/getTotalOfAnswerForAnswer/{aid}")
    public ResponseJSON getTotalOfAnswerForAnswer(@PathVariable int aid) {
        int total = answerService.getTotalOfAnswerForAnswer(aid);
        return new ResponseJSON(200, total, "获取回复数成功");
    }
}
