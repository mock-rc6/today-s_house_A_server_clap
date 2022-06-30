package com.example.demo.src.knowhow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetKnowhowsRes {
    private List<GetGuideBooksRes> guidebooks;
    private List<GetKnowhowsPostRes> knowhows;
}
