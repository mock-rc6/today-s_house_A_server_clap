package com.example.demo.src.questionAnswer;

import com.example.demo.config.BaseException;
import com.example.demo.src.questionAnswer.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class QuestionAnswerProvider {

    private final QuestionAnswerDao questionAnswerDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public QuestionAnswerProvider(QuestionAnswerDao questionAnswerDao) {
        this.questionAnswerDao = questionAnswerDao;
    }

    public List<GetQuestionAnswersRes> getQuestionAnswers() throws BaseException{
        try{
            List<GetQuestionAnswersRes> getQuestionAnswersRes = questionAnswerDao.getQuestionAnswers();
            return getQuestionAnswersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetquestionAnswerRes> getquestionAnswer(int questionAnswerIdx) throws BaseException{
//        if (checkquestionAnswerIdx(questionAnswerIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_questionAnswer);
//        }
//        try{
//            List<GetquestionAnswerRes> getquestionAnswerRes = questionAnswerDao.getquestionAnswer(questionAnswerIdx);
//            return getquestionAnswerRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkquestionAnswerIdx(int questionAnswerIdx) throws BaseException{
//        try{
//            return questionAnswerDao.checkquestionAnswerIdx(questionAnswerIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
