package com.example.demo.src.proHousewarm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetProHousewarmsRes {
    private String totalViews;
    private String event;
    private List<GetProHousewarmsPostRes> proHousewarms;
}
