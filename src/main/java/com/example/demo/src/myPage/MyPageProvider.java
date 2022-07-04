package com.example.demo.src.myPage;

import com.example.demo.config.BaseException;
import com.example.demo.src.myPage.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service

public class MyPageProvider {

    private final MyPageDao myPageDao;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public MyPageProvider(MyPageDao myPageDao) {
        this.myPageDao = myPageDao;
    }

    public GetMyPageRes getMyPage(int userIdx) throws BaseException{
        try{
            GetMyPageRes getMyPageRes = myPageDao.getMyPage(userIdx);
            return getMyPageRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

//    public List<GetmyPageRes> getmyPage(int myPageIdx) throws BaseException{
//        if (checkmyPageIdx(myPageIdx) == 0) {
//            throw new BaseException(GET_MOVIES_NONEXISTENCE_myPage);
//        }
//        try{
//            List<GetmyPageRes> getmyPageRes = myPageDao.getmyPage(myPageIdx);
//            return getmyPageRes;
//        }
//        catch (Exception exception) {
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }
//
//    public int checkmyPageIdx(int myPageIdx) throws BaseException{
//        try{
//            return myPageDao.checkmyPageIdx(myPageIdx);
//        } catch (Exception exception){
//            throw new BaseException(DATABASE_ERROR);
//        }
//    }


}
