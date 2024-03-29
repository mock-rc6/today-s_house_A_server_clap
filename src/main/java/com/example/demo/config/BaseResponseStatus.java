package com.example.demo.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_JWT(false, 2001, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2002, "유효하지 않은 JWT입니다."),
    INVALID_USER_JWT(false,2003,"권한이 없는 유저의 접근입니다."),

    // users
    USERS_EMPTY_USER_ID(false, 2010, "유저 아이디 값을 확인해주세요."),

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, 2015, "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, 2016, "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,2017,"중복된 이메일입니다."),

    POST_USERS_EXISTS_NICKNAME(false,2018,"중복된 닉네임입니다."),

    POST_USERS_WRONG_PASSWORD(false, 2019, "비밀번호 확인이 맞지 않습니다."),

    POST_USERS_EMPTY_PASSWORD(false, 2020, "비밀번호를 입력해주세요."),

    POST_USERS_EMPTY_PASSWORDCHECK(false, 2021, "비밀번호 확인을 해주세요."),

    POST_USERS_EMPTY_AGREEAGE(false, 2022, "만 14세 이상입니다는 필수입니다"),

    POST_USERS_EMPTY_AGREETERMS(false, 2023, "이용약관은 필수입니다"),

    POST_USERS_EMPTY_AGREEPRIVACY(false, 2024, "개인정보수집 및 이용동의는 필수입니다"),

    POST_USERS_LENGTH_NICKNAME(false, 2025, "별명을 2~15자 내로 입력해주세요"),

    // [POST] /users/logIn
    INVALID_USER(false,2026,"존재하지 않는 유저입니다."),

    POST_USERS_UNEXIST_EMAIL(false,2027,"존재하지 않는 이메일입니다."),

    FAILED_TO_LOGIN(false,2028,"없는 아이디거나 비밀번호가 틀렸습니다."),

    POST_Reviews_UNEXIST_PRODUCT(false,2029,"존재하지 않는 상품입니다."),

    POST_Comements_UNEXIST_Post(false,2030,"존재하지 않는 사진 게시물입니다."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, 3013, "중복된 이메일입니다."),



    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,4014,"유저네임 수정 실패"),

    POST_FAIL_REVIEW(false,4015,"리뷰 작성 실패"),

    POST_FAIL_COMMENT(false,4015,"댓글 작성 실패"),

    PASSWORD_ENCRYPTION_ERROR(false, 4011, "비밀번호 암호화에 실패하였습니다."),
    PASSWORD_DECRYPTION_ERROR(false, 4012, "비밀번호 복호화에 실패하였습니다.");


    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
