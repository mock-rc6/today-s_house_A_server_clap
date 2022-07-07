package com.example.demo.src.comment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetPostsCommentsRes {
    private int userIdx;
    private String content;
    private String date;
    private List<GetUserRes> user;
}
