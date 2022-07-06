package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetProductRes {
    private int productIdx;
    private String brand;
    private String title;
    private double rating;
    private int reviewNum;
    private String discountRate;
    private String originalPrice;
    private String price;
    private String specialPrice;
    private String benefit;
    private String delivery;
    private String deliveryDetail;
    private String deliveryFeeDetail;
    private String deliveryDate;
    private List<GetProductImgRes> prodcutImg;
    private List<GetCategoriesRes> categories;
}
