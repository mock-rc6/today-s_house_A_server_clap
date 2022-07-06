package com.example.demo.src.search.model;

import com.example.demo.src.questionAnswer.model.GetQuestionAnswersPostRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetSearchAllRes {
    private List<GetSearchProductsRes> SearchStores;
    private List<GetSearchPostsRes> SearchPosts;
    private List<GetSearchHousewarmsRes> SearchHousewarms;
    private List<GetSearchKnowhowsRes> SearchKnowhows;
    private List<GetSearchQuestionAnswersRes> SearchQuestionAnswers;
}
