package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetSearchQuestionAnswersRes {
    private int questionAnswerIdx;
    private String imgUrl;
    private String title;
    private String comment;
    private String views;
    private String date;
    private List<GetUsersRes> users;
    private List<GetTagsRes> tags;
}
