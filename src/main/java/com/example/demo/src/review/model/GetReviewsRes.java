package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetReviewsRes {
    private int userIdx;
    private String content;
    private String purchasePlace;
    private double rating;
    private String date;
    private List<GetUserRes> user;
}
