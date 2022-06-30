package com.example.demo.src.knowhow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetKnowhowsPostRes {
    private int knowhowIdx;
    private String imageUrl;
    private String title;
    private String views;
    private String scraps;
    private List<GetUsersRes> users;
}
