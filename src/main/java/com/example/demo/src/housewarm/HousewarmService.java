package com.example.demo.src.housewarm;

import com.example.demo.config.BaseException;
import com.example.demo.src.housewarm.model.*;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.SHA256;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

// Service Create, Update, Delete 의 로직 처리
@Service

public class HousewarmService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final HousewarmDao housewarmDao;
    private final HousewarmProvider housewarmProvider;


    @Autowired
    public HousewarmService(HousewarmDao housewarmDao, HousewarmProvider housewarmProvider) {
        this.housewarmDao = housewarmDao;
        this.housewarmProvider = housewarmProvider;
    }

}
