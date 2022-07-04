package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetSearchesRes {
    private List<GetSearchHistoriesRes> searchHistories;
    private List<GetSearchWordsRes> searchWords;
    private List<GetCategories1Res> categories1;
}
