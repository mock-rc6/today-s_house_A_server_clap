package com.example.demo.src.housewarm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetHousewarmsPostRes {
    private int housewarmIdx;
    private String imgUrl;
    private String title;
    private String scrap;
    private String views;
    private List<GetUsersRes> users;
}
