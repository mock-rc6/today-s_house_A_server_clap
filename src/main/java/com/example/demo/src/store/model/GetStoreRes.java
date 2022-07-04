package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetStoreRes {
    private List<GetStoreEventBannersRes> storeEventBanners;
    private List<GetStoreEventIconsRes> storeEventIcons;
    private List<GetCategoriesRes> categories;
}
