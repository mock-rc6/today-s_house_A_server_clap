package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetCategories1Res {
    private int category1Idx;
    private String category1;
    private List<GetCategories2Res> categories2;
}
