package com.example.demo.src.questionAnswer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.questionAnswer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.utils.JwtService;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequestMapping("/questionAnswers")

public class QuestionAnswerController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final QuestionAnswerProvider questionAnswerProvider;
    @Autowired
    private final QuestionAnswerService questionAnswerService;

    @Autowired
    private final JwtService jwtService;

    public QuestionAnswerController(QuestionAnswerProvider questionAnswerProvider, QuestionAnswerService questionAnswerService, JwtService jwtService) {
        this.questionAnswerProvider = questionAnswerProvider;
        this.questionAnswerService = questionAnswerService;
        this.jwtService = jwtService;
    }

    /**
     * 크리에이터 조회 API
     * [GET] /questionAnswers
     * @return BaseResponse<List<GetquestionAnswersRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/questionAnswers
    public BaseResponse<List<GetQuestionAnswersRes>> getQuestionAnswers() {
        try{
            List<GetQuestionAnswersRes> getQuestionAnswerRes = questionAnswerProvider.getQuestionAnswers();
            return new BaseResponse<>(getQuestionAnswerRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

//    /**
//     * 특정크리에이터조회 API
//     * [GET] /questionAnswers/:questionAnswerIdx
//     * @return BaseResponse<GetquestionAnswerRes>
//     */
//    //Path-variable
//    @ResponseBody
//    @GetMapping("/{questionAnswerIdx}/{userIdx}") // (GET) 127.0.0.1:9000/app/genres
//    public BaseResponse<List<GetquestionAnswerRes>> getquestionAnswer(@PathVariable("userIdx") int userIdx, @PathVariable("questionAnswerIdx") int questionAnswerIdx) {
//        try{
//            int userIdxByJwt = jwtService.getUserIdx();
//            //userIdx와 접근한 유저가 같은지 확인
//            if(userIdx != userIdxByJwt){
//                return new BaseResponse<>(INVALID_USER_JWT);
//            }
//            List<GetquestionAnswerRes> getquestionAnswerRes = questionAnswerProvider.getquestionAnswer(questionAnswerIdx);
//            return new BaseResponse<>(getquestionAnswerRes);
//        } catch(BaseException exception){
//            return new BaseResponse<>((exception.getStatus()));
//        }
//    }

}
