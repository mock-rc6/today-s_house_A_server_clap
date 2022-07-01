package com.example.demo.src.questionAnswer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetQuestionAnswersPostRes {
    private int questionAnswerIdx;
    private String imgUrl;
    private String title;
    private String comment;
    private String views;
    private String date;
    private List<GetUsersRes> users;
    private List<GetTagsRes> tags;
}
