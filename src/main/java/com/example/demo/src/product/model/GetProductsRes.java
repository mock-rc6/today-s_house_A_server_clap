package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetProductsRes {
    private int productIdx;
    private String productImg;
    private String brand;
    private String title;
    private String discountRate;
    private String price;
    private double rating;
    private int reviewNum;
    private String delivery;
    private String specialPrice;
}
