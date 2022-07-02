package com.example.demo.src.popular;

import com.example.demo.config.BaseException;
import com.example.demo.src.popular.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class PopularProvider {

    private final PopularDao popularDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public PopularProvider(PopularDao popularDao) {
        this.popularDao = popularDao;
    }

    public List<GetPopularsRes> getPopulars() throws BaseException{
        try{
            List<GetPopularsRes> getPopularsRes = popularDao.getPopulars();
            return getPopularsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetpopularRes> getpopular(int popularIdx) throws BaseException{
//        if (checkpopularIdx(popularIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_popular);
//        }
//        try{
//            List<GetpopularRes> getpopularRes = popularDao.getpopular(popularIdx);
//            return getpopularRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkpopularIdx(int popularIdx) throws BaseException{
//        try{
//            return popularDao.checkpopularIdx(popularIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
