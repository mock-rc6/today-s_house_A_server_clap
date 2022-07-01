package com.example.demo.src.proHousewarm;

import com.example.demo.config.BaseException;
import com.example.demo.src.proHousewarm.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class ProHousewarmProvider {

    private final ProHousewarmDao proHousewarmDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProHousewarmProvider(ProHousewarmDao proHousewarmDao) {
        this.proHousewarmDao = proHousewarmDao;
    }

    public List<GetProHousewarmsRes> getProHousewarms() throws BaseException{
        try{
            List<GetProHousewarmsRes> getProHousewarmsRes = proHousewarmDao.getProHousewarms();
            return getProHousewarmsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetproHousewarmRes> getproHousewarm(int proHousewarmIdx) throws BaseException{
//        if (checkproHousewarmIdx(proHousewarmIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_proHousewarm);
//        }
//        try{
//            List<GetproHousewarmRes> getproHousewarmRes = proHousewarmDao.getproHousewarm(proHousewarmIdx);
//            return getproHousewarmRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkproHousewarmIdx(int proHousewarmIdx) throws BaseException{
//        try{
//            return proHousewarmDao.checkproHousewarmIdx(proHousewarmIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
