package com.example.demo.src.popular.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetPopularsRes {
    private List<GetEventBannersRes> eventBanners;
    private List<GetEventIconsRes> eventIcons;
    private List<GetHousewarmsRes> housewarms;
    private List<GetKnowhowsRes> knowhows;
    private List<GetProHousewarmsRes> proHousewarms;
    private List<GetCategoriesRes> categories;
}
