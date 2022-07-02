package com.example.demo.src.following.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetUsersRes {
    private String nickname;
    private String profileImgUrl;
    private String message;
    private List<GetTagsRes> tags;
    private List<GetUserImgsRes> userImgs;
}
