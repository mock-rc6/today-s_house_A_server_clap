package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetHousewarmsRes {
    private int housewarmIdx;
    private String imageUrl;
    private String content;
    private String scrap;
    private String view;
    private List<GetUsersRes> user;
}
