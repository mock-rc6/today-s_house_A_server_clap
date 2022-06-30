package com.example.demo.src.housewarm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetHousewarmsRes {
    private String totalViews;
    private List<GetHousewarmsPostRes> housewarms;
}
