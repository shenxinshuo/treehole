package com.xinshuo.treehole.mapper;

import com.xinshuo.treehole.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    Question getQuestionByID(int id);
    List<Question> getQuestions();
    List<Question> getQuestionsByType(String type);
    List<Question> getQuestionsByUID(int uid);
    void addQuestion(Question question);
    void updateQuestion(Question question);
    void deleteQuestion(int id);
    List<Question> getQuestionsBySearch(String search);
}
