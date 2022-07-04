package com.example.demo.src.myPage.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetMyPageRes {
    private int userIdx;
    private String point;
    private String nickname;
    private String eventBanner1;
    private String eventBanner2;
    private GetScrapNumRes scrapNum;
    private GetLikeNumRes likeNum;
    private GetCouponNumRes couponNum;
    private GetOrderNumRes orderNum;
    private GetFollowerRes follower;
    private GetFollowingRes following;
    private GetAllImgRes allImg;
    private GetLivingRoomImgRes livingRoomImg;
    private GetBedRoomImgRes bedRoomImg;
    private GetKitchenImgRes kitchenImg;
    private GetWorkRoomImgRes workRoomImg;
    private GetVerandaImgRes verandaImg;
    private GetBathRoomImgRes bathRoomImg;
    private GetDressRoomImgRes dressRoomImg;
    private GetFurnitureImgRes furnitureImg;
    private GetScrapImgRes scrapImg;
    private GetHousewarmNumRes housewarmNum;
    private GetKnowhowNumRes knowhowNum;
    private GetReviewNumRes reviewNum;
}
