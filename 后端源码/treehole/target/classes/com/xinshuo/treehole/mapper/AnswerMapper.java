package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AnswerMapper {
    Answer getAnswerByID(int id);
    List<Answer> getAnswerByAnswerID(int aid);
    List<Answer> getAnswerByQuestionID(int qid);
    List<Answer> getAnswerByUID(int uid);
    List<Answer> getAnswers();
    void addAnswer(Answer answer);
    void updateAnswer(Answer answer);
    void deleteAnswer(int id);
    int getTotalOfAnswerForAnswer(int aid);
}
