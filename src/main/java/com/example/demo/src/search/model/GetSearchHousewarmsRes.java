package com.example.demo.src.search.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetSearchHousewarmsRes {
    private List<GetHousewarmsRes> housewarms;
    private List<GetProHousewarmsRes> proHousewarms;
}
