package com.example.demo.src.knowhow;

import com.example.demo.config.BaseException;
import com.example.demo.src.knowhow.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class KnowhowProvider {

    private final KnowhowDao knowhowDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public KnowhowProvider(KnowhowDao knowhowDao) {
        this.knowhowDao = knowhowDao;
    }

    public List<GetKnowhowsRes> getKnowhows() throws BaseException{
        try{
            List<GetKnowhowsRes> getKnowhowsRes = knowhowDao.getKnowhows();
            return getKnowhowsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetknowhowRes> getknowhow(int knowhowIdx) throws BaseException{
//        if (checkknowhowIdx(knowhowIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_knowhow);
//        }
//        try{
//            List<GetknowhowRes> getknowhowRes = knowhowDao.getknowhow(knowhowIdx);
//            return getknowhowRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkknowhowIdx(int knowhowIdx) throws BaseException{
//        try{
//            return knowhowDao.checkknowhowIdx(knowhowIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
