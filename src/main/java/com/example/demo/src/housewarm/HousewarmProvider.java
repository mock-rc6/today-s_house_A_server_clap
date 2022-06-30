package com.example.demo.src.housewarm;

import com.example.demo.config.BaseException;
import com.example.demo.src.housewarm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class HousewarmProvider {

    private final HousewarmDao housewarmDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public HousewarmProvider(HousewarmDao housewarmDao) {
        this.housewarmDao = housewarmDao;
    }

    public List<GetHousewarmsRes> getHousewarms() throws BaseException{
        try{
            List<GetHousewarmsRes> getHousewarmsRes = housewarmDao.getHousewarms();
            return getHousewarmsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GethousewarmRes> gethousewarm(int housewarmIdx) throws BaseException{
//        if (checkhousewarmIdx(housewarmIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_housewarm);
//        }
//        try{
//            List<GethousewarmRes> gethousewarmRes = housewarmDao.gethousewarm(housewarmIdx);
//            return gethousewarmRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkhousewarmIdx(int housewarmIdx) throws BaseException{
//        try{
//            return housewarmDao.checkhousewarmIdx(housewarmIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
