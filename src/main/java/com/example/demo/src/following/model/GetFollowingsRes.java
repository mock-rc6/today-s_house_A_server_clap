package com.example.demo.src.following.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetFollowingsRes {
    private int userIdx;
    private List<GetUsersRes> followingRecommends;
}
