package com.example.demo.src.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public class GetCartsRes {
    private int productIdx;
    private String productImg;
    private String brand;
    private String title;
    private String delivery;
    private String deliveryDetail;
    private int originalPrice;
    private int deliveryFee;
    private int discount;
    private int price;
}
