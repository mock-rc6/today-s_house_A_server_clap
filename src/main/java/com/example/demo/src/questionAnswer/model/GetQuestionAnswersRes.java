package com.example.demo.src.questionAnswer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetQuestionAnswersRes {
    private List<GetQuestionAnswersPostRes> questionAnswers;
}
