package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    private String email;
    private String password;
    private String passwordCheck;
    private String nickname;
    private String agreeAge;
    private String agreeTerms;
    private String agreePrivacy;
    private String agreeAlarm;
    private String code;
}
